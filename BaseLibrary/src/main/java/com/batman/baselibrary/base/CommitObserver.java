package com.batman.baselibrary.base;

import android.content.Context;
import android.content.DialogInterface;

import com.batman.baselibrary.utils.TipDialogUtils;

public abstract class CommitObserver<T> extends BaseObserver<T> {


    private Context mContext;

    public CommitObserver(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommitObserver(Context context, boolean isShow) {
        super(context, isShow);
        this.mContext = context;
    }


    @Override
    public void uiLoading() {
        TipDialogUtils.showProgressDialog(mContext, "加载中", true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                uiCancel(dialog);
            }
        });
    }


    @Override
    public void dismissDialog() {
        TipDialogUtils.dismissProgressDialog();
    }

}
