package com.batman.baselibrary.utils.update;


import com.update.base.UpdateStrategy;
import com.update.model.Update;

/**
 * Created by guqian on 2018/7/12.
 */

public class DialogStrategy extends UpdateStrategy {
    @Override
    public boolean isShowUpdateDialog(Update update) {
        // 在检查到有更新时，是否显示弹窗通知。
        return true;
    }

    @Override
    public boolean isAutoInstall() {
        // 在下载完成后。是否自动进行安装
        return false;
    }

    @Override
    public boolean isShowDownloadDialog() {
        // 在下载过程中，是否显示下载进度通知
        return true;
    }
}

