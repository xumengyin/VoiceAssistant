package com.batman.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.batman.baselibrary.RouterConstants;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class RouteActivityUtils {

    /**
     * 通过 aRouter
     *
     * @param path
     */

    public static void openActivity(Context context, String path) {
        ARouter.getInstance().build(path).navigation(context);
    }

    public static void openActivity(Context context, String path, int requestCode) {
        ARouter.getInstance().build(path).navigation((Activity) context, requestCode);
    }

    public static void openActivity(Context context, String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation(context);
    }

    public static void openActivity(Activity context, String path, Bundle bundle, int requestCode) {
        ARouter.getInstance().build(path).with(bundle).navigation(context, requestCode);
    }

    public static void openActivityNewTask(Context context, String path) {
        ARouter.getInstance().build(path).withFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK).navigation(context);
    }

    public static void openActivityNewTask(Context context, String path, Bundle bundle) {
        ARouter.getInstance().build(path).withFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK).with(bundle).navigation(context);
    }

    /**
     * 跳转主页
     *
     * @param context
     */
    public static void openLoginPage(Context context) {
        RouteActivityUtils.openActivity(context, RouterConstants.LOGIN_COMPONENT_LOGIN_PATH);
    }

    public static void openLoginNewTaskPage(Context context, Bundle bundle) {
        RouteActivityUtils.openActivityNewTask(context, RouterConstants.LOGIN_COMPONENT_LOGIN_PATH, bundle);
    }

}
