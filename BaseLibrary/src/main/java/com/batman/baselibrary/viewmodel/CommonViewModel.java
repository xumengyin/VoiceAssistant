package com.batman.baselibrary.viewmodel;

import com.batman.baselibrary.data.result.NoParamResult;
import com.batman.baselibrary.data.result.SignDetailResult;
import com.batman.baselibrary.delegate.ApplicationDelegate;
import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.network.ApiResponse;
import com.network.NetworkBoundResource;
import com.network.Resource;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * base ViewModel
 */
public class CommonViewModel extends BaseViewModel {

    /**
     * 看视频获得金币
     *
     * @param param
     * @return
     */
    public LiveData<Resource<NoParamResult>> getWatchVideo(HashMap param) {

        return new NetworkBoundResource<NoParamResult>(ApplicationDispatcher.get().getApplicationContext()) {

            @NonNull
            @Override
            protected Observable<ApiResponse<NoParamResult>> createCall() {

                return mApiService.getWatchVideo(param);
            }

        }.asLiveData();

    }

    public LiveData<Resource<NoParamResult>> getGoldByWatchVideoEncrypt(@Body RequestBody param) {

        return new NetworkBoundResource<NoParamResult>(ApplicationDispatcher.get().getApplicationContext()) {

            @NonNull
            @Override
            protected Observable<ApiResponse<NoParamResult>> createCall() {

                return mApiService.getGoldByWatchVideoEncrypt(param);
            }

        }.asLiveData();

    }

    /**
     * 领取货币翻倍
     *
     * @param param
     * @return
     */
    public LiveData<Resource<NoParamResult>> getGameCurrency(HashMap param) {

        return new NetworkBoundResource<NoParamResult>(ApplicationDispatcher.get().getApplicationContext()) {


            @NonNull
            @Override
            protected Observable<ApiResponse<NoParamResult>> createCall() {

                return mApiService.getGameCurrency(param);
            }

        }.asLiveData();

    }


    /**
     * 签到
     *
     * @param param
     * @return
     */

    public LiveData<Resource<SignDetailResult>> getSignin(HashMap param) {

        return new NetworkBoundResource<SignDetailResult>(ApplicationDispatcher.get().getApplicationContext()) {

            @Override
            protected Observable<ApiResponse<SignDetailResult>> createCall() {

                return mApiService.getSignin(param);
            }

        }.asLiveData();

    }

    /**
     * 广告id
     * @param param
     * @return
     */
    public LiveData<Resource<String>> getAdbvertId(HashMap param) {

        return new NetworkBoundResource<String>(ApplicationDispatcher.get().getApplicationContext()) {

            @Override
            protected Observable<ApiResponse<String>> createCall() {

                return mApiService.getAdbvertId(param);
            }

        }.asLiveData();

    }


}
