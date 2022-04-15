package com.batman.baselibrary.utils.update;

import android.content.Context;

import com.batman.utils.Utils;
import com.update.base.FileCreator;
import com.update.model.Update;

import java.io.File;

public class MyFileCreator extends FileCreator {
    @Override
    public File create(Update update) {
        File cacheDir = getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir, "update_normal_" + update.getVersionName());
    }

    @Override
    public File createForDaemon(Update update) {
        File cacheDir = getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir, "update_daemon_" + update.getVersionName());
    }

    private File getCacheDir() {
        Context context = Utils.getApp();
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        cacheDir = new File(cacheDir, "update");
        return cacheDir;
    }
}
