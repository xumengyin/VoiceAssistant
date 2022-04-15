package com.batman.baselibrary.api;


import com.batman.baselibrary.utils.UrlUtils;

import java.util.HashMap;

import okhttp3.RequestBody;

public class ApiParams {

    /**
     * 接口前缀
     */

    public static final String QUERY_WATCH_VIDEO = "swagger-gold/gold/getGoldByWatchVideo";
    public static final String QUERY_GEME_GETCURRENCY = "swagger-game/game/getCurrency";
    public static final String QUERY_SIGNIN = "swagger-gold/gold/signIn";
    public static final String QUERY_GET_ADVERT_ID = "swagger-gold/gold/getAdvertId";
    public static final String QUERY_GET_WATCH_VIDEO_ENCRYPT = "swagger-gold/gold/getGoldByWatchVideo_encrypt";


    public static RequestBody getRequestBody(Object request) {

        return UrlUtils.getEncryptBody(request);
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
