package com.batman.baselibrary.utils.update;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

import com.batman.baselibrary.widget.CustomOneDialog;

import com.update.base.InstallNotifier;
import com.update.util.SafeDialogHandle;

import java.lang.reflect.Field;

public class InstallAppNotification extends InstallNotifier {


    @Override
    public Dialog create(Activity context) {

        String updateContent;

        updateContent = "安装包已就绪，是否安装？";

        CustomOneDialog dialog = new CustomOneDialog(context);
//        dialog.tvTitle.setText("安装提示");
        dialog.tvTitle.setText(updateContent);
        dialog.tvTitle.setVisibility(View.VISIBLE);
        if (update.isForced()) {
            dialog.buttonCancel.setVisibility(View.GONE);
        } else {
            dialog.buttonCancel.setVisibility(View.VISIBLE);
        }
        dialog.buttonCancel.setText("取消");
        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserCancel();
                SafeDialogHandle.safeDismissDialog((Dialog) dialog);
            }
        });
        dialog.buttonOK.setText("确定");
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update.isForced()) {
                    preventDismissDialog(dialog);
                } else {
                    SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                }
                sendToInstall();
            }
        });
        dialog.setCancelable(false);
        return dialog;

    }

    /**
     * 通过反射 阻止自动关闭对话框
     */
    private void preventDismissDialog(DialogInterface dialog) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            //设置mShowing值，欺骗android系统
            field.set(dialog, false);
        } catch (Exception e) {
            // ignore
        }
    }
}
