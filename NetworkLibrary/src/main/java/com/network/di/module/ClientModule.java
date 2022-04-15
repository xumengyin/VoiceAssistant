package com.network.di.module;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;

import com.network.http.HttpHandler;
import com.network.http.RequestIntercept;
import com.network.utils.CustomGsonConverterFactory;

import java.io.File;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author batman
 */
@Module
public class ClientModule {

    private static final int TOME_OUT = 15;
    //缓存文件最大值为10Mb
    public static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private HttpUrl mApiUrl;
    private HttpHandler mHandler;
    private Interceptor[] mInterceptors;

    private ClientModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mHandler = builder.handler;
        this.mInterceptors = builder.interceptors;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @param
     * @description:提供OkhttpClient
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(Cache cache, Interceptor intercept) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return configureClient(okHttpClient, cache, intercept);
    }

    /**
     * @param client
     * @param httpUrl
     * @description: 提供retrofit
     */
   // @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, HttpUrl httpUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        return configureRetrofit(builder, client, httpUrl);
    }
//    @Singleton
//    @Provides
//    Retrofit provideRetrofit2(OkHttpClient client, HttpUrl httpUrl) {
//        final Retrofit.Builder builder = new Retrofit.Builder();
//        return configureRetrofit(builder, client, httpUrl);
//    }

    /**
     * 提供缓存地址
     */

    @Singleton
    @Provides
    File provideCacheFile(Application application) {

        String path = "";
        File cacheDir = application.getCacheDir();

        if (cacheDir == null) {

            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)
                    || !Environment.isExternalStorageRemovable()) {

                File externalCacheDir = application.getExternalCacheDir();

                if (externalCacheDir == null) {

                    path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    File file = new File(path, "xiongmaozoubu");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    path = file.getAbsolutePath();
                } else {
                    path = externalCacheDir.getAbsolutePath();
                }

            }
        } else {
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            path = cacheDir.getAbsolutePath();
        }


        return new File(path);

//        return new File(application.getExternalCacheDir().getAbsolutePath());
    }

    @Singleton
    @Provides
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);//设置缓存路径和大小
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mApiUrl;
    }

//    @Singleton
//    @Provides
//    Cache provideCache(File cacheFile) {
//        return new Cache(cacheFile, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);//设置缓存路径和大小
//    }


    @Singleton
    @Provides
    Interceptor provideIntercept() {
        return new RequestIntercept(mHandler);//打印请求信息的拦截器
    }

    /**
     * 提供RXCache客户端
     *
     * @param cacheDir 缓存路径
     * @return
     */
//    @Singleton
//    @Provides
//    RxCache provideRxCache(File cacheDir) {
//        return new RxCache
//                .Builder()
//                .persistence(cacheDir, new GsonSpeaker());
//    }


    /**
     * 提供权限管理类,用于请求权限,适配6.0的权限管理
     * @param application
     * @return
     */
//    @Singleton
//    @Provides
//    RxPermissions provideRxPermissions(Application application) {
//        return RxPermissions.getInstance(application);
//    }


    /**
     * @param builder
     * @param client
     * @param httpUrl
     * @return
     * @author: jess
     * @description:配置retrofit
     */
    private Retrofit configureRetrofit(Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl) {
        return builder

                .baseUrl(httpUrl)
                //设置okhttp
                .client(client)
                //使用rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用Gson
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new CustomGsonConverterFactory())
                .build();
    }

    /**
     * 配置okhttpclient
     *
     * @param okHttpClient
     * @return
     */
    private OkHttpClient configureClient(OkHttpClient.Builder okHttpClient, Cache cache, Interceptor intercept) {

        // log用拦截器
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//
//        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
//        if (BuildConfig.LOG_DEBUG) {
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        }
        final ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

        OkHttpClient.Builder builder = null;
        try {
            builder = okHttpClient
                    .connectTimeout(TOME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TOME_OUT, TimeUnit.SECONDS)
                    .cache(cache)//设置缓存
                    .sslSocketFactory(getSSLSocketFactory()).hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .cookieJar(new CookieJar() {//这里可以做cookie传递，保存等操作
                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {//可以做保存cookies操作
                            cookieStore.put(url.host(), cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {//加载新的cookies
                            List<Cookie> cookies = cookieStore.get(url.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }

                    })
                    .addNetworkInterceptor(intercept);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mInterceptors != null && mInterceptors.length > 0) {//如果外部提供了interceptor的数组则遍历添加
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        OkHttpClient okHttp = RetrofitUrlManager.getInstance()
                .with(builder)
                .build();
        return okHttp;
    }

    public static final class Builder {
        private HttpUrl apiUrl = HttpUrl.parse("");
        private HttpHandler handler;
        private Interceptor[] interceptors;

        private Builder() {
        }

        //基础url
        public Builder baseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl)) {
                throw new IllegalArgumentException("base_url can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        //用来处理http响应结果
        public Builder httpHandler(HttpHandler handler) {
            this.handler = handler;
            return this;
        }

        //动态添加任意个interceptor
        public Builder interceptors(Interceptor[] interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public ClientModule build() {
            if (apiUrl == null) {
                throw new IllegalStateException("base_url is required");
            }
            return new ClientModule(this);
        }


    }

    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }


}
