package com.jerry.voiceassistant.api;

import android.content.SharedPreferences;

import com.jerry.voiceassistant.Constants;
import com.jerry.voiceassistant.MainApplication;
import com.jerry.voiceassistant.preference.UserPrefenrence;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PackagePostData {


    public static RequestBody convertRequestBody(String params)
    {
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), params);
        return  body;
    }

    /**
     * @param root
     * @return String
     * @throws
     * @Description:
     */
    private static String autoAdd(JSONObject root) {
        JSONObject auth = new JSONObject();

        UserPrefenrence pres = UserPrefenrence.getInstance();

        try {
            if (pres != null && pres.username != null)
                auth.put("userName", pres.username);
            if (pres != null && pres.password != null)
                auth.put("password", pres.password);
            if (pres != null && pres.login_token != null)
                auth.put("loginToken", pres.login_token);
            // auth.put("pushId", pushid);
            auth.put("mapType", Constants.MAPTYPE);
            auth.put("appName", Constants.APPNAME);
            auth.put("platformType", "android");
//            if (MyApplication.imei == null) {
//                SharedPreferences sharedPreferences = MyApplication.getIMIPreference();
//                MyApplication.imei = sharedPreferences.getString("imei", "");
//                MyApplication.imsi = sharedPreferences.getString("imsi", "");
//            }
//            auth.put("imei", MyApplication.imei);
            root.put("auth", auth);

            return root.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return root.toString();
        }
    }
    /**
     * 初始化基础信息
     *
     * @return
     */
    public static RequestBody init(String cityName) {
        JSONObject root = new JSONObject();
        JSONObject params = new JSONObject();
        try {
            root.put("cmd", "init");
            root.put("params", params);

            params.put("ua", MainApplication.deviceName);
            params.put("sdk", MainApplication.sdk);
//            if (MyApplication.imei == null) {
//                SharedPreferences sharedPreferences = MyApplication.getIMIPreference();
//                MyApplication.imei = sharedPreferences.getString("imei", "");
//                MyApplication.imsi = sharedPreferences.getString("imsi", "");
//            }
//            params.put("imei", MyApplication.imei);
//            params.put("imsi", MyApplication.imsi);
            params.put("appVersion", Constants.version);
            params.put("mapType", Constants.MAPTYPE);
            params.put("appName", Constants.APPNAME);
            params.put("platformType", "android");
            params.put("cityName", cityName);
            return convertRequestBody(root.toString());
        } catch (JSONException e) {

            e.printStackTrace();
            return null;
        }
    }
}
