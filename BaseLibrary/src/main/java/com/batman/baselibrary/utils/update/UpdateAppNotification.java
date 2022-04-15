package com.batman.baselibrary.utils.update;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;

import com.batman.baselibrary.Constant;
import com.jakewharton.rxbinding3.view.RxView;
import com.update.base.CheckNotifier;
import com.update.util.SafeDialogHandle;

public class UpdateAppNotification extends CheckNotifier {


    @Override
    public Dialog create(Activity context) {

        String updateContent;
        if (TextUtils.isEmpty(update.getUpdateContent())) {
            updateContent = "你有新版本需要更新";
        } else {
            updateContent = update.getUpdateContent();
        }
        DownLoadDialog dialog = new DownLoadDialog(context);

        if (update.isForced()) {
            dialog.buttonCancel.setVisibility(View.GONE);
        } else {
            dialog.buttonCancel.setVisibility(View.VISIBLE);
        }
        dialog.btnVersion.setText(update.getVersionName());
        dialog.tvContent.setText(updateContent);

        RxView.clicks(dialog.buttonOK)
                .throttleFirst(Constant.WINDOW_DURATION, Constant.WINDOW_DURATION_UNIT)
                .subscribe(granted -> {
                    sendDownloadRequest();
                    SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                });

        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserCancel();
                SafeDialogHandle.safeDismissDialog((Dialog) dialog);
            }
        });
        dialog.setCancelable(false);
        return dialog;

    }
}
