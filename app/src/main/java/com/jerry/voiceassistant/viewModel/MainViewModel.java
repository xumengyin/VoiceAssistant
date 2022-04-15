package com.jerry.voiceassistant.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.batman.baselibrary.data.result.NoParamResult;
import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.batman.baselibrary.viewmodel.BaseDataViewModel;
import com.jerry.voiceassistant.api.ApiService;
import com.jerry.voiceassistant.data.InitBean;
import com.network.ApiResponse;
import com.network.NetworkBoundResource;
import com.network.Resource;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class MainViewModel extends BaseDataViewModel<ApiService> {

    public LiveData<Resource<InitBean>> init(RequestBody param) {

        return new NetworkBoundResource<InitBean>(ApplicationDispatcher.get().getApplicationContext()) {

            @NonNull
            @Override
            protected Observable<ApiResponse<InitBean>> createCall() {

                return mApiService.init(param);
            }

        }.asLiveData();
    }
}
