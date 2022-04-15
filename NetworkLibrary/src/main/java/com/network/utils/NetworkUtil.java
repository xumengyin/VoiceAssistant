package com.network.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;



public class NetworkUtil {

    public static final String TAG = "NetworkUtil";

    /**
     * 判断是否有网络可用
     *
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        } else {
            return false;
        }
    }


    /**
     * 获取可用的网络信息
     *
     * @param context
     * @return
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }
}
