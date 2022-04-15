package com.batman.baselibrary.api;


import com.batman.baselibrary.Constant;
import com.batman.baselibrary.data.result.NoParamResult;
import com.batman.baselibrary.data.result.SignDetailResult;
import com.network.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

public interface ApiService {
    
    /**
     * 观看视频获得金币
     *
     * @param params
     * @return
     */
    @POST(ApiParams.QUERY_WATCH_VIDEO)
    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
    Observable<ApiResponse<NoParamResult>> getWatchVideo(@QueryMap HashMap<String, String> params);

    @POST(ApiParams.QUERY_GET_WATCH_VIDEO_ENCRYPT)
    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
    Observable<ApiResponse<NoParamResult>> getGoldByWatchVideoEncrypt(@Body RequestBody params);


    /**
     * 货币领取金币
     *
     * @param params
     * @return
     */
    @POST(ApiParams.QUERY_GEME_GETCURRENCY)
    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
    Observable<ApiResponse<NoParamResult>> getGameCurrency(@QueryMap HashMap<String, String> params);

    /**
     * 签到
     *
     * @param params
     * @return
     */
    @POST(ApiParams.QUERY_SIGNIN)
    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
    Observable<ApiResponse<SignDetailResult>> getSignin(@QueryMap HashMap<String, String> params);

    @POST(ApiParams.QUERY_GET_ADVERT_ID)
    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
    Observable<ApiResponse<String>> getAdbvertId(@QueryMap HashMap<String, String> params);

}
