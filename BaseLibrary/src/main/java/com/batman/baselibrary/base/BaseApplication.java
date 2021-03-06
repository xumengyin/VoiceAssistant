package com.batman.baselibrary.base;

import android.app.Application;
import android.app.Notification;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.alibaba.android.arouter.launcher.ARouter;
import com.batman.baselibrary.BuildConfig;
import com.batman.baselibrary.Constant;
import com.batman.baselibrary.R;
import com.batman.baselibrary.RouterConstants;
import com.batman.baselibrary.delegate.ApplicationDelegate;
import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.batman.baselibrary.delegate.IApplicationDelegate;
import com.batman.baselibrary.event.RefreshTokenEvent;
import com.batman.baselibrary.preference.FictionPreference;
import com.batman.baselibrary.preference.NewsPreference;
import com.batman.baselibrary.preference.SettingPreference;
import com.batman.baselibrary.preference.UserPres;
import com.batman.baselibrary.preference.WatchVideoPreference;
import com.batman.baselibrary.preference.WeatherPreference;
import com.batman.baselibrary.utils.ChannelUtils;
import com.batman.baselibrary.utils.RouteActivityUtils;
import com.batman.utils.ProcessUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.network.utils.LogUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.android.agoo.huawei.HuaWeiRegister;
import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * ????????????????????????????????????Application???????????????????????????????????????Application???onCreate????????????????????????
 * ????????????????????????Application???????????????
 */
public class BaseApplication extends ApplicationDelegate implements IApplicationDelegate {

    private String WEB_ADDRESS;
    public static String WEB_AGREEMENT;
    public static String WEB_PRIVACY;
    public static String WEB_APPLY;
    public static String WEB_SHARE;
    public static String WEB_FIND_CODE;
    public static String WEB_GAME_RULES;
    public static String WEB_INVITE_FRIEND;
    public static String WEB_MY_WALLET;
    public static String WEB_RECHARGE;
    public static String WEB_RECORD;
    public static String WEB_SDK;
    public static String WEB_ANSWER;
    public static String WEB_HELP;
    public static String WEB_GONG_LUV;

    private static final String TAG = BaseApplication.class.getName();

    private Handler handler;

    public static String Install_File = "";

    public static BaseApplication application;


    @Override
    public int getLevel() {
        return LEVEL_BASE_LIB;
    }

    @Override
    public Class[] subDelegates() {
        return null;
    }

    @Override
    public void onCreateDelegate() {
        application = this;

        //??????url
        WEB_ADDRESS = Constant.getConfig(Constant.CONFIGS_ADDRESS, 1);
        WEB_AGREEMENT = WEB_ADDRESS + "serverAgreement.html";
        WEB_PRIVACY = WEB_ADDRESS + "privacy.html";
        WEB_APPLY = WEB_ADDRESS + "apply.html";
        WEB_SHARE = "https://h5.xmzb.haojianli.com/app-h5/downloadApp.html";
        WEB_FIND_CODE = WEB_ADDRESS + "fillInCode.html";
        WEB_GAME_RULES = WEB_ADDRESS + "gameRules.html";
        WEB_INVITE_FRIEND = WEB_ADDRESS + "inviteFriends.html";
        WEB_MY_WALLET = WEB_ADDRESS + "myWallet.html";
        WEB_RECHARGE = WEB_ADDRESS + "recharge.html";
        WEB_RECORD = WEB_ADDRESS + "record.html";
        WEB_SDK = WEB_ADDRESS + "sdkList.html";
        WEB_ANSWER = WEB_ADDRESS + "answer.html";
        WEB_HELP = WEB_ADDRESS + "helpAndFeedback.html";
        WEB_GONG_LUV = WEB_ADDRESS + "strategy.html";


        //bugly
        //??????
        /**
         * ???????????????????????????AndroidManifest.xml????????????appkey???channel?????????????????????????????????????????????
         */
        String umChannel =  ChannelUtils.getChannel();

        UMConfigure.init(getApplicationContext(), Constant.UM_KEY, umChannel, UMConfigure.DEVICE_TYPE_PHONE, Constant.UM_PUSH_ID);

        // ??????AUTO??????????????????
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        if (BuildConfig.DEBUG) {
            UMConfigure.setLogEnabled(true);
            //Tracking.setDebugMode(true);
        }

        initUpush();
        CrashReport.initCrashReport(getApplicationContext(), Constant.CRASH_REPORT_ID, false);

        // ?????????????????????????????? SDK??????????????????????????????????????????????????????????????????????????? SDK???
      //  GDTADManager.getInstance().initWith(getApplicationContext(), Constant.YOU_LIANG_HUI_ID);

//        GlobalSetting.setChannel(1);
//        GlobalSetting.setEnableMediationTool(true);

        if (ProcessUtils.isMainProcess()) {
            //
//            DPAPI.getInstance(getApplicationContext()).closeSQL();
//            if (BuildConfig.DEBUG) {
//                DPAPI.getInstance(getApplicationContext(), Constant.CRASH_DEEP_ID).setLogEnable(true);
//            } else {
//                DPAPI.getInstance(getApplicationContext(), Constant.CRASH_DEEP_ID).setLogEnable(false);
//            }

            //??????
           // ShareApp.register((Application) getApplicationContext());

            //?????????SDK?????????
            //??????????????????????????????Application#onCreate()??????????????????????????????content???null?????????
           // TTAdManagerHolder.init(getApplicationContext());

            registerComponentCallbacks(new ComponentCallbacks2() {
                @Override
                public void onTrimMemory(int level) {
//                MemorySizeCalculator calculator = new MemorySizeCalculator(getApplicationContext ());
//                int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
//                int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
                    //???????????????gc
//                Glide.get(getApplicationContext()).clearMemory();
                    System.gc();
                }

                @Override
                public void onConfigurationChanged(Configuration newConfig) {

                }

                @Override
                public void onLowMemory() {

                }
            });

//        initX5Webview();
            inARouter();
            initPreference();
            initDebug();
        }
    }


    private void initPreference() {

        SettingPreference.getInstance();
        UserPres.getInstance();
        WatchVideoPreference.getInstance();
        FictionPreference.getInstance();
        WeatherPreference.getInstance();
        NewsPreference.getInstance();
    }


    private void initDebug() {
        DoraemonKit.install((Application) getApplicationContext());
        if (BuildConfig.DEBUG) {
            LogUtils.DEBUG = true;
            initLog();
//            initStrictModeVmPolicy();
//            initStrictModeThreadPolicy();
        }
    }

    protected void initLog() {
        inTimber();
        inDebugARouter();
    }

    protected void inTimber() {
        Timber.plant(new Timber.DebugTree());
    }


    protected void inDebugARouter() {
        ARouter.openLog();
        ARouter.openDebug();
    }

    protected void inARouter() {
        ARouter.init((Application) ApplicationDispatcher.get().getApplicationContext());
    }

    //????????????
    private void initStrictModeVmPolicy() {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()/*??????Activity????????????*/
                .detectLeakedClosableObjects()/*??????????????????Closable??????*/
                .detectLeakedSqlLiteObjects() /*??????Sqlite??????????????????*/
                /*???????????????detectAll()?????????????????????????????????*/
                .penaltyLog().build());
    }

    private void initStrictModeThreadPolicy() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls() /* ??????????????????????????????*/
                .detectDiskReads()/*????????????????????????*/
                .detectDiskWrites()/*????????????????????????*/
                .detectNetwork() /*??????????????????*/
                /*???????????????detectAll()?????????????????????????????????*/
                .penaltyLog().build());
    }


    /**
     * ??????
     */
    private void initUpush() {
        PushAgent mPushAgent = PushAgent.getInstance(getApplicationContext());
        handler = new Handler();
//        mPushAgent.setResourcePackageName("com.yingxiangli.makestep");

        //sdk??????????????????
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        // sdk??????????????????
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // ??????????????????????????????
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

        // mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            /**
             * ???????????????????????????????????????????????????
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //??????super??????????????????????????????super????????????????????????
                super.dealWithNotificationMessage(context, msg);
            }

            /**
             * ??????????????????????????????
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // ??????????????????????????????????????????????????????
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //??????????????????????????????
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //??????????????????????????????
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            /**
             * ???????????????????????????????????????
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //?????????0???????????????builder_id?????????????????????????????????
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * ????????????????????????????????????????????????????????????-????????????????????????-???????????????????????????
         * UmengNotificationClickHandler??????BroadcastReceiver??????????????????
         * ???????????????Activity????????????Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void launchApp(Context context, UMessage msg) {
                if (msg.extra != null) {
                    String type = msg.extra.get("type");
                    if (!TextUtils.isEmpty(type)) {
                        if (type.equals("step_by_step")) {
                            Bundle bundle = new Bundle();
                            bundle.putInt(RouterConstants.KEY_INDEX, RouterConstants.KEY_INDEX_1);
                            RouteActivityUtils.openActivityNewTask(context.getApplicationContext(), RouterConstants.MAIN_COMPONENT_MAIN_PATH, bundle);
                        }
                    }
                }

//                super.launchApp(context, msg);
            }

            @Override
            public void openUrl(Context context, UMessage msg) {

            }

            @Override
            public void openActivity(Context context, UMessage msg) {
                super.openActivity(context, msg);
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //??????????????????NotificationHandler
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        //?????????????????? ????????????register?????????????????????
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                Log.i(TAG, "device token: " + deviceToken);
                UserPres.savePushToken(deviceToken);
                EventBus.getDefault().post(new RefreshTokenEvent(deviceToken));
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i(TAG, "register failed: " + s + " " + s1);

            }
        });

        //???????????????????????????
//        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);

        //????????????
       // MiPushRegistar.register(getApplicationContext(), Constant.XIAMI_ID, Constant.XIAMI_KEY);
        //????????????
        HuaWeiRegister.register((Application) getApplicationContext());
        //????????????
      //  MeizuRegister.register(getApplicationContext(), Constant.MEIZHU_ID, Constant.MEIZHU_KEY);
        //OPPO???????????????1???app key?????????2???app secret
      //  OppoRegister.register(this, Constant.OPPO_KEY, Constant.OPPO_SECRET);
        //vivo ??????
     //   VivoRegister.register(getApplicationContext());

        // ??????glide??????https????????????
        try {
            Glide.get(this).getRegistry().replace(
                    GlideUrl.class, InputStream.class,
                    new OkHttpUrlLoader.Factory(getSSLOkHttpClient()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ??????https ?????????????????????????????????????????????
     *
     * @throws Exception
     */
    private OkHttpClient getSSLOkHttpClient() throws Exception {
        final X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
    }

}