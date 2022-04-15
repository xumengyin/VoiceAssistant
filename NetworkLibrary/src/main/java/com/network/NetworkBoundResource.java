package com.network;


import android.content.Context;
import android.text.TextUtils;

import com.network.Exception.NetworkException;
import com.network.utils.NetworkUtil;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @param <ResultType>
 * @author batman
 */
public abstract class NetworkBoundResource<ResultType> {

    private final AppExecutors mAppExecutors;

    private final String TAG = "NetworkBoundResource";

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(Context context) {

        this.mAppExecutors = AppExecutors.getInstance();
        if (!NetworkUtil.isNetAvailable(context)) {

            result.setValue((Resource<ResultType>) Resource.error(NetworkException.NO_NETWORK_MESSAGE, null, NetworkException.NO_NETWORK));

//            result.setValue((Resource<ResultType>) Resource.complete(null));


            return;
        }


        result.setValue((Resource<ResultType>) Resource.loading(null));
        final Observable<ResultType> dbSource = loadFromDb();
        fetchFromNetwork(dbSource);
    }

    private Disposable mDisposable;

    private void fetchFromNetwork(final Observable<ResultType> dbSource) {

        final Observable<ApiResponse<ResultType>> apiResponse = createCall();
        apiResponse.observeOn(AndroidSchedulers.mainThread())

                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ApiResponse<ResultType>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(final ApiResponse<ResultType> response) {
                        if (response.isSuccessful()) {
                            mAppExecutors.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    saveCallResult(processResponse(response));
                                    mAppExecutors.mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            Resource<ResultType> res = Resource.success(processResponse(response), response.msg, response.token);
                                            result.setValue(res);
                                        }
                                    });
                                }
                            });

                        } else {
                            result.setValue(Resource.error(response.msg, processResponse(response), response.code));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        LogUtils.e(TAG, e.getMessage(), e);
                        result.setValue((Resource<ResultType>) Resource.throwable(null, e));

                    }


                    @Override
                    public void onComplete() {

                    }

                });
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    public void cancel() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    /**
     * 处理网络请求获取的数据
     */
    @WorkerThread
    protected ResultType processResponse(ApiResponse<ResultType> response) {
        return response.body;
    }

    /**
     * 保存网络请求获取的数据到数据库
     *
     * @param item
     */
    @WorkerThread
    protected void saveCallResult(@NonNull ResultType item) {

    }


    protected boolean shouldFetch(@Nullable ResultType data) {
        return true;
    }

    /**
     * 从数据库获取数据
     *
     * @return
     */
    protected Observable<ResultType> loadFromDb() {
        return null;
    }

    /**
     * 调取网络请求获取数据
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<ResultType>> createCall();


}