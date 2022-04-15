package com.batman.baselibrary.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.network.utils.AESUtil;
import com.network.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UrlUtils {


    public static HashMap<String, String> createGetMap() {
        return new HashMap<>();
    }

    /**
     * 获取 对象的属性和参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParamMap(Object request) {
        Map<String, String> namesAndValues = createGetMap();
        Field[] fields = request.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String name = field.getName();
            String value = "";
            try {
                value = (String) fields[i].get(request);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //参数不能为空，要么不传
            if (!TextUtils.isEmpty(value)) {
                namesAndValues.put(name, value);
            }
        }
        return namesAndValues;
    }


    public static RequestBody getEncodeBody(Object request) {
        Gson gson = new Gson();
        String content = gson.toJson(request);
//        String authParam = "";
//        try {
//            JSONObject root = new JSONObject(content);
//            getHeadParams(root);
//            authParam = root.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
        return requestBody;
    }

    public static HashMap getEncodeMap(Object request) {
        Gson gson = new Gson();
        String contentRequest = gson.toJson(request);
        HashMap content = gson.fromJson(contentRequest, HashMap.class);
//        String authParam = "";
//        try {
//            JSONObject root = new JSONObject(content);
//            getHeadParams(root);
//            authParam = root.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return content;
    }

    /**
     * 拼接url参数
     *
     * @param namesAndValues
     * @return
     */
    public static String namesAndValuesToQueryString(HashMap<String, String> namesAndValues) {
        StringBuilder out = new StringBuilder();

        int i = 0;
        for (Map.Entry<String, String> entry : namesAndValues.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (i > 0) {
                out.append('&');
            }
            i++;
            out.append(name);
            if (value != null) {
                out.append('=');
                try {

                    out.append((value));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        out.append('&');
        out.append("timestamp");
        out.append("=");
        out.append(System.currentTimeMillis());
        LogUtils.d("httpParam", out.toString());
        return out.toString();
    }

    public static RequestBody getEncryptBody(Object request) {

        HashMap hashMap = getEncodeMap(request);
        String value = namesAndValuesToQueryString(hashMap);
        String content = "";
        try {
            content = AESUtil.encryptAES(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        HashMap jsonParams = new HashMap();
//        jsonParams.put("param", value);
//        Gson gson = new Gson();
//        String content = gson.toJson(jsonParams);
//        String authParam = "";
        JSONObject root = null;
        try {
            root = new JSONObject();
//            getHeadParams(root);
//            authParam = root.toString();
            root.put("param", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), root.toString());
        return requestBody;
    }


}
