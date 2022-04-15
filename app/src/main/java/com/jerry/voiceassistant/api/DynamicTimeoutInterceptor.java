package com.jerry.voiceassistant.api;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DynamicTimeoutInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request reequest = chain.request();
        String questUrl = reequest.url().toString();
        Iterator<String> it = reequest.headers().names().iterator();
        boolean isCloseApi = false;
//        while (it.hasNext()) {
//            String str = it.next();
//            //首页广告请求设置超时时间
//            if (!TextUtils.isEmpty(str) && str.equals(Constant.HEADER_SP_AD_) && questUrl.contains(QUERY_AD_ID)) {
//                isCloseApi = true;
//            }
//
//        }
        if (isCloseApi) {
            return chain.withConnectTimeout(3, TimeUnit.SECONDS)
                    .withReadTimeout(3, TimeUnit.SECONDS)
                    .withWriteTimeout(3, TimeUnit.SECONDS)
                    .proceed(reequest);
        } else {
            return chain.withConnectTimeout(15, TimeUnit.SECONDS)
                    .withReadTimeout(15, TimeUnit.SECONDS)
                    .withWriteTimeout(15, TimeUnit.SECONDS)
                    .proceed(reequest);
        }
//        return chain.proceed(reequest);
    }
}