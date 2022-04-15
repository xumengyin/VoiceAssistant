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
 * 由于多个组件均含有各自的Application。使用多组件时需要手动派发Application的onCreate时间。使用此类做
 * 约束。避免同一个Application被多次调用
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

        //配置url
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
        //友盟
        /**
         * 注意：如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数。
         */
        String umChannel =  ChannelUtils.getChannel();

        UMConfigure.init(getApplicationContext(), Constant.UM_KEY, umChannel, UMConfigure.DEVICE_TYPE_PHONE, Constant.UM_PUSH_ID);

        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        if (BuildConfig.DEBUG) {
            UMConfigure.setLogEnabled(true);
            //Tracking.setDebugMode(true);
        }

        initUpush();
        CrashReport.initCrashReport(getApplicationContext(), Constant.CRASH_REPORT_ID, false);

        // 通过调用此方法初始化 SDK。如果需要在多个进程拉取广告，每个进程都需要初始化 SDK。
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

            //微信
           // ShareApp.register((Application) getApplicationContext());

            //穿山甲SDK初始化
            //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
           // TTAdManagerHolder.init(getApplicationContext());

            registerComponentCallbacks(new ComponentCallbacks2() {
                @Override
                public void onTrimMemory(int level) {
//                MemorySizeCalculator calculator = new MemorySizeCalculator(getApplicationContext ());
//                int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
//                int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
                    //内存不够，gc
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

    //严苛模式
    private void initStrictModeVmPolicy() {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()/*检测Activity内存泄露*/
                .detectLeakedClosableObjects()/*检测未关闭的Closable对象*/
                .detectLeakedSqlLiteObjects() /*检测Sqlite对象是否关闭*/
                /*也可以采用detectAll()来检测所有想检测的东西*/
                .penaltyLog().build());
    }

    private void initStrictModeThreadPolicy() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls() /* 侦测自定义的耗时操作*/
                .detectDiskReads()/*磁盘读取操作检测*/
                .detectDiskWrites()/*检测磁盘写入操作*/
                .detectNetwork() /*检测网络操作*/
                /*也可以采用detectAll()来检测所有想检测的东西*/
                .penaltyLog().build());
    }


    /**
     * 友盟
     */
    private void initUpush() {
        PushAgent mPushAgent = PushAgent.getInstance(getApplicationContext());
        handler = new Handler();
//        mPushAgent.setResourcePackageName("com.yingxiangli.makestep");

        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        // sdk关闭通知声音
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // 通知声音由服务端控制
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

        // mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            /**
             * 通知的回调方法（通知送达时会回调）
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super，会展示通知，不调用super，则不展示通知。
                super.dealWithNotificationMessage(context, msg);
            }

            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            /**
             * 自定义通知栏样式的回调方法
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
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
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
        //使用自定义的NotificationHandler
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        //注册推送服务 每次调用register都会回调该接口
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

        //使用完全自定义处理
//        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);

        //小米通道
       // MiPushRegistar.register(getApplicationContext(), Constant.XIAMI_ID, Constant.XIAMI_KEY);
        //华为通道
        HuaWeiRegister.register((Application) getApplicationContext());
        //魅族通道
      //  MeizuRegister.register(getApplicationContext(), Constant.MEIZHU_ID, Constant.MEIZHU_KEY);
        //OPPO通道，参数1为app key，参数2为app secret
      //  OppoRegister.register(this, Constant.OPPO_KEY, Constant.OPPO_SECRET);
        //vivo 通道
     //   VivoRegister.register(getApplicationContext());

        // 解决glide加载https证书问题
        try {
            Glide.get(this).getRegistry().replace(
                    GlideUrl.class, InputStream.class,
                    new OkHttpUrlLoader.Factory(getSSLOkHttpClient()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置https 访问的时候对所有证书都进行信任
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