package com.batman.baselibrary.utils.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import com.batman.baselibrary.R;
import com.batman.utils.ActivityUtils;
import com.batman.utils.AppUtils;
import com.update.base.DownloadCallback;
import com.update.base.DownloadNotifier;
import com.update.model.Update;
import com.update.util.SafeDialogHandle;

import java.io.File;
import java.util.UUID;

import androidx.core.app.NotificationCompat;

public class NotificationDownloadCreator extends DownloadNotifier {
    @Override
    public DownloadCallback create(Update update, Activity activity) {
        // 返回一个UpdateDownloadCB对象用于下载时使用来更新界面。
        if (update.isForced()) {
            final ProgressDialog dialog = new ProgressDialog(activity);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.setProgress(0);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            SafeDialogHandle.safeShowDialog(dialog);
            return new DownloadCallback() {
                @Override
                public void onDownloadStart() {
                }

                @Override
                public void onDownloadComplete(File file) {
                    SafeDialogHandle.safeDismissDialog(dialog);
                }

                @Override
                public void onDownloadProgress(long current, long total) {
                    int percent = (int) (current * 1.0f / total * 100);
                    dialog.setProgress(percent);
                }

                @Override
                public void onDownloadError(Throwable t) {
                    SafeDialogHandle.safeDismissDialog(dialog);
                    createRestartDialog();
                }
            };
        } else {
            return new NotificationCB(activity);
        }
    }

    private static class NotificationCB implements DownloadCallback {

        NotificationManager manager;
        NotificationCompat.Builder builder;
        int id;
        int preProgress;

        NotificationCB(Activity activity) {

            manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
            //适配8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = new NotificationCompat.Builder(activity, "111");
                NotificationChannel notificationChannel = new NotificationChannel("111", "下载", NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(notificationChannel);
            } else {
                builder = new NotificationCompat.Builder(activity);
            }
            builder.setProgress(100, 0, false)
                    .setContentTitle("下载中...")
                    .setSmallIcon(R.mipmap.ic_logo_launcher)
                    .setAutoCancel(false);
            id = Math.abs(UUID.randomUUID().hashCode());
        }

        @Override
        public void onDownloadStart() {
            // 下载开始时的通知回调。运行于主线程
            manager.notify(id, builder.build());
        }

        @Override
        public void onDownloadComplete(File file) {
            // 下载完成的回调。运行于主线程
            manager.cancel(id);
        }

        @Override
        public void onDownloadProgress(long current, long total) {
            // 下载过程中的进度信息。在此获取进度信息。运行于主线程
            int progress = (int) (current * 1f / total * 100);
            // 过滤不必要的刷新进度
            if (preProgress < progress) {
                preProgress = progress;
                builder.setProgress(100, progress, false);
                manager.notify(id, builder.build());
            }
        }

        @Override
        public void onDownloadError(Throwable t) {
            // 下载时出错。运行于主线程
            manager.cancel(id);
        }
    }

    private void createRestartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUtils.getTopActivity())
                .setCancelable(!update.isForced())
                .setTitle("下载更新失败")
                .setMessage("是否重新下载？")
                .setNeutralButton(update.isForced() ? "退出" : "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (update.isForced()) {
                            AppUtils.exitApp();
                        } else {
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartDownload();
                    }
                });

        builder.show();
    }
}
