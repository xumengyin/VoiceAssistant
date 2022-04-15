package com.batman.baselibrary.utils;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.util.Log;

import com.batman.baselibrary.widget.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadingDialogUtils {

    private static final int DEFAULT_NUM = 1;
    private static final int DEFAULT_NUM_NO = 0;

    private static WeakReference<LoadingDialog> sProgressDialogRef;

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static LoadingDialog showProgressDialog(Context context, String message,
                                                   boolean canCancelable, OnCancelListener listener) {

        atomicInteger.getAndIncrement();
        LoadingDialog dialog = getDialog();

        if (dialog != null && dialog.getContext() != context) {

            atomicInteger.set(DEFAULT_NUM_NO);
            // maybe existing dialog is running in a destroyed activity cotext we should recreate one
            dismissProgressDialog();
            Log.e("dialog", "there is a leaked window here,orign context: " + dialog.getContext() + " now: " + context);
            dialog = null;
        }

        if (dialog == null) {
            dialog = new LoadingDialog(context);
            sProgressDialogRef = new WeakReference<>(dialog);

            dialog.setCancelable(canCancelable);
            dialog.setOnCancelListener(listener);
            dialog.show();
            return dialog;
        } else {
            return dialog;

        }

    }

    public static void dismissProgressDialog() {

        atomicInteger.getAndDecrement();
        if (atomicInteger.get() <= DEFAULT_NUM_NO) {
            atomicInteger.set(DEFAULT_NUM_NO);
        }

        LoadingDialog dialog = getDialog();
        if (null == dialog) {
            return;
        }

        if (atomicInteger.get() == DEFAULT_NUM_NO) {
            sProgressDialogRef.clear();
            if (dialog.isShowing()) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    // maybe we catch IllegalArgumentException here.
                }
            }
        }

    }

//    public static void setMessage(String message) {
//        LoadingDialog dialog = getDialog();
//        if (null != dialog && dialog.isShowing() && !TextUtils.isEmpty(message)) {
//            dialog.setMessage(message);
//        }
//    }

//    public static void updateLoadingMessage(String message) {
//        LoadingDialog dialog = getDialog();
//        if (null != dialog && dialog.isShowing() && !TextUtils.isEmpty(message)) {
//            dialog.updateLoadingMessage(message);
//        }
//    }

    public static boolean isShowing() {
        LoadingDialog dialog = getDialog();
        return (dialog != null && dialog.isShowing());
    }

    private static LoadingDialog getDialog() {
        return sProgressDialogRef == null ? null : sProgressDialogRef.get();
    }
}
