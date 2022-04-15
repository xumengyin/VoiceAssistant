package com.jerry.voiceassistant.api;


import com.batman.baselibrary.utils.UrlUtils;

import java.util.HashMap;

import okhttp3.RequestBody;

public class ApiParams {


    /**
     * 接口前缀
     */
    public static final String URL_PATH = "saasapi/saasapi";


    /**
     * json
     *
     * @param request
     * @return
     */
    public static RequestBody getRequestBody(Object request) {

        return UrlUtils.getEncodeBody(request);
    }

    /**
     * map
     *
     * @param request
     * @return
     */
    public static HashMap getRequestMap(Object request) {

        return UrlUtils.getEncodeMap(request);
    }

}
