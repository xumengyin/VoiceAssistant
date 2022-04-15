package com.batman.baselibrary.viewmodel;

import androidx.lifecycle.ViewModel;

import com.batman.baselibrary.api.ApiService;
import com.batman.baselibrary.delegate.ApplicationDelegate;
import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.batman.baselibrary.di.component.AppComponent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Retrofit;

public class BaseDataViewModel<T> extends ViewModel {
    protected T mApiService;

    public BaseDataViewModel() {
        AppComponent mAppComponent = ((ApplicationDelegate) (ApplicationDispatcher.get().getApplicationContext())).getAppComponent();
        Retrofit retrofit = mAppComponent.getRetrofit();

        ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        mApiService = (T) retrofit.create((Class<T>) actualTypeArguments[0]);
    }
}
