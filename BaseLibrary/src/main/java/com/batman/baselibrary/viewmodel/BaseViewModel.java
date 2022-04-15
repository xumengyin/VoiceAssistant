package com.batman.baselibrary.viewmodel;

import com.batman.baselibrary.api.ApiService;
import com.batman.baselibrary.delegate.ApplicationDelegate;
import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.batman.baselibrary.di.component.AppComponent;

import androidx.lifecycle.ViewModel;
import retrofit2.Retrofit;

public class BaseViewModel extends ViewModel {

    protected ApiService mApiService;

    public BaseViewModel() {
        AppComponent mAppComponent = ((ApplicationDelegate) (ApplicationDispatcher.get().getApplicationContext())).getAppComponent();
        Retrofit retrofit = mAppComponent.getRetrofit();
        mApiService = retrofit.create(ApiService.class);
    }



}

