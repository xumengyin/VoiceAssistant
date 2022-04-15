package com.jerry.voiceassistant.api;


import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

import com.batman.baselibrary.Constant;
import com.batman.baselibrary.data.result.AdResult;
import com.batman.baselibrary.data.result.UserResult;
import com.jerry.voiceassistant.data.InitBean;
import com.network.ApiResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {

//    @POST(ApiParams.QUERY_TOURIST_LOGIN)
//    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
//    Observable<ApiResponse<UserResult>> touristLogin(@QueryMap HashMap<String, String> params);
//
//    @POST(ApiParams.QUERY_AD_ID)
//    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME})
//    Observable<ApiResponse<List<AdResult>>> getAdId(@QueryMap HashMap<String, String> params);
//
//    @POST(ApiParams.QUERY_AD_ID)
//    @Headers({DOMAIN_NAME_HEADER + Constant.HEADER_NAME, Constant.HEADER_SP_AD + Constant.HEADER_NAME})
//    Observable<ApiResponse<List<AdResult>>> getSpAdId(@QueryMap HashMap<String, String> params);
    @POST(ApiParams.URL_PATH)
    Observable<ApiResponse<InitBean>> init(@Body RequestBody body);


}
