package com.batman.baselibrary.utils.update;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.utils.ToastUtils;

import com.update.base.CheckCallback;
import com.update.base.DownloadCallback;
import com.update.model.Update;

import java.io.File;

import static com.batman.baselibrary.utils.update.UpdatePreference.KEY_IS_DOWNLOAD;

/**
 * @author haoge on 2018/1/9.
 */

public class ToastCallback implements CheckCallback, DownloadCallback {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Context mContext;
    public int status = 0;

    public ToastCallback(Context context) {
        this.mContext = context;
        sharedPreferences = UpdatePreference.getSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    private void show(String message) {
        status = 0;
        ToastUtils.showLong(message);
    }

    @Override
    public void onCheckStart() {
//        show("启动更新任务");
        status = 2;
    }

    @Override
    public void hasUpdate(Update update) {
//        show("检测到有更新");
        status = 1;
    }

    @Override
    public void noUpdate() {
//        show("检测到没有更新");
        status = 0;
    }

    @Override
    public void onCheckError(Throwable t) {
        status = -1;
        show("更新检查失败：" + t.getMessage());
    }

    @Override
    public void onUserCancel() {
        status = 3;
//        show("用户取消更新");
    }

    @Override
    public void onCheckIgnore(Update update) {
//        show("用户忽略此版本更新");
        status = 3;
    }

    @Override
    public void onDownloadStart() {
        show("开始下载");
        status = 4;
    }

    @Override
    public void onDownloadComplete(File file) {
//        show("下载完成");

        editor.putBoolean(KEY_IS_DOWNLOAD, true);
        editor.commit();
        status = 6;
    }

    @Override
    public void onDownloadProgress(long current, long total) {
        status = 4;

    }

    @Override
    public void onDownloadError(Throwable t) {
        show("下载失败");
        status = 5;
    }
}
