package com.batman.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

import com.batman.baselibrary.RouterConstants;
import com.batman.baselibrary.preference.UserPres;
import com.batman.baselibrary.utils.LoadingDialogUtils;
import com.batman.baselibrary.utils.RouteActivityUtils;
import com.batman.utils.ActivityUtils;
import com.batman.utils.ToastUtils;
import com.network.Exception.NetworkException;
import com.network.Resource;
import com.network.Status;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public abstract class BaseObserver<T> implements Observer<Resource<T>> {


    public static final String ERROR_CODE_024 = "024";

    private Context mContext;
    protected boolean mIsShow = true;

    public BaseObserver(Context context) {
        this.mContext = context;
    }

    public BaseObserver(Context context, boolean isShow) {
        this.mContext = context;
        this.mIsShow = isShow;
    }

    @Override
    public void onChanged(@Nullable Resource<T> tResource) {


        if (tResource.status.equals(Status.LOADING)) {

            if (mIsShow) {
                uiLoading();
            }
        } else {
            dismissDialog();
            if (tResource.status.equals(Status.SUCCESS)) {
                uiSuccessful(tResource);
                uiComplete(tResource);
            } else if (tResource.status.equals(Status.ERROR)) {
                if (!beforeUiError(tResource)) {
                    uiError(tResource);
                }

            } else if (tResource.status.equals(Status.THROWABLE)) {
                uiFailure(tResource);
            }
        }
    }

    public void uiSuccessful(Resource<T> tResource) {

    }

    public boolean beforeUiError(Resource<T> tResource) {
        if (!TextUtils.isEmpty(tResource.errorCode) && tResource.errorCode.equals("005")) {

            UserPres.getInstance().logout();
            ActivityUtils.finishAllActivities();
            Bundle bundle = new Bundle();
            bundle.putInt(RouterConstants.KEY_INDEX, RouterConstants.KEY_INDEX_4);
            RouteActivityUtils.openLoginNewTaskPage(mContext, bundle);
            if (mContext instanceof Activity) {
                ((Activity) mContext).finish();
            }

            return true;
        }
        return false;


    }


    public void uiError(Resource<T> tResource) {

        if (!TextUtils.isEmpty(tResource.message)) {
            ToastUtils.showLong(tResource.message);
        }

    }


    public void uiFailure(Resource<T> tResource) {
        String str = NetworkException.handleException(tResource.mException).getMessage();
        tResource.message = str;
        if (!TextUtils.isEmpty(str)) {
            ToastUtils.showLong(str);
        }
    }

    public void uiLoading() {
        LoadingDialogUtils.showProgressDialog(mContext, "", true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                uiCancel(dialog);
            }
        });
    }

    public void uiCancel(DialogInterface dialog) {

    }

    public void dismissDialog() {
        LoadingDialogUtils.dismissProgressDialog();
    }

    public void uiComplete(Resource<T> tResource) {
    }
}
