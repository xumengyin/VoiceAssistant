package com.jerry.voiceassistant;


import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.batman.baselibrary.Constant;
import com.batman.baselibrary.delegate.ApplicationDelegate;
import com.batman.baselibrary.delegate.IApplicationDelegate;
import com.google.gson.JsonObject;
import com.jerry.push.PushHelper;
import com.jerry.voiceassistant.bdtts.TtsHelper;
import com.jerry.voiceassistant.preference.SettingPreference;
import com.jerry.voiceassistant.utils.Logs;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.entity.UMessage;

import org.json.JSONObject;


public class MainApplication extends ApplicationDelegate {

    public static final String deviceName = Build.MODEL;
    @SuppressWarnings("deprecation")
    public static final String sdk = Build.VERSION.SDK;
    public static String IP_ADDRESS = Constants.getConfig(Constants.BETA, 0);

    //http
    public static String BASE_URL = IP_ADDRESS;

    private static MainApplication mInstance;
    private TtsHelper mTtsManager;
    private PushHelper pushHelper =new PushHelper();
    private void adapterWebView() {
        //Android 9及以上必须设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName();
            if (!getPackageName().equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }
    }

    int activityCount = 0;
    private void addActivityListener() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                activityCount++;
//                boolean isSplash = activity instanceof SplashActivity;
//                if(true)
//                    return;
//                if (activityCount == 1 && !isSplash) {
//                    Intent intent= new Intent(activity,SplashActivity.class);
//                    intent.putExtra(Constants.INTENT_KEY,true);
//                    activity.startActivity(intent);
//                }
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                activityCount--;
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    private void initThrid() {
        initUmeng();
        initBdTts();
    }

    private void initBdTts()
    {
        boolean isMainProcess = UMUtils.isMainProgress(this);
        if (isMainProcess) {
            mTtsManager = TtsHelper.getInstance(this);
        }
    }
    public void sendTTsMsg(String pushMsg)
    {
        //点火和路况播报一起放 导致音频焦点被抢了 延时一会在播放
//        mainHandler.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                //getMInstance().stack.push(pushMsg);
                mTtsManager.speak(pushMsg);
//            }
//        }, 2000);
    }
    private void initUmeng()
    {
        UMConfigure.setLogEnabled(true);
        //预初始化
        PushHelper.preInit(this);
        //是否同意隐私政策
        boolean isAgreement = SettingPreference.getInstance().isAgreement;
        if (isAgreement) {
            return;
        }
        boolean isMainProcess = UMUtils.isMainProgress(this);
        if (isMainProcess) {
            //启动优化：建议在子线程中执行初始化
            new Thread(new Runnable() {
                @Override
                public void run() {
                    initPush();
                }
            }).start();
        } else {
            //若不是主进程（":channel"结尾的进程），直接初始化sdk，不可在子线程中执行
            initPush();
        }

    }
    public void initPush()
    {
        pushHelper.setRegisterCallBack(new PushHelper.IRegisterCallBack() {
            @Override
            public void onRegisterSuccess(String deviceToken) {

            }

            @Override
            public void onFail(String errCode, String errDesc) {

            }

            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {

            }

            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                //适配8.0无法语音播报,8.0以上的声音设置需要在channel里设置，一旦创建好就无法修改
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {

                    if (uMessage.play_sound &&
                            !TextUtils.isEmpty(uMessage.sound))
                    {
                        Logs.d("xuxu", "sound:" + uMessage.sound);
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + uMessage.sound);
                        //Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                        //注意channelid要唯一
                        NotificationChannel channel = new NotificationChannel(uMessage.sound, "cpsdna", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager notificationManager =
                                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        channel.setSound(uri, Notification.AUDIO_ATTRIBUTES_DEFAULT);
                        notificationManager.createNotificationChannel(channel);
                        builder.setChannelId(uMessage.sound);
                        builder.setSound(uri)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle(uMessage.title)
                                .setContentText(uMessage.text)
                                .setTicker(uMessage.ticker)
                                .setAutoCancel(true);
                        return builder.build();
                    }
                }
               return null;
            }

            @Override
            public void dealWithCustomMessage(Context context, UMessage msg) {
                if(!TextUtils.isEmpty(msg.custom))
                {
                    sendTTsMsg(msg.custom);
                }
            }

            @Override
            public void openActivity(Context context, UMessage msg) {

            }

            @Override
            public void launchApp(Context context, UMessage msg) {

            }

            @Override
            public void dismissNotification(Context context, UMessage msg) {
//                JSONObject object =new JSONObject(msg.custom);



            }
        });
        pushHelper.init(getApplicationContext());

    }

    public static MainApplication getInstance() {
        return mInstance;
    }

    @Override
    public int getLevel() {
        return IApplicationDelegate.LEVEL_APP;
    }

    @Override
    public Class[] subDelegates() {
        return new Class[0];
    }

    @Override
    public void onCreateDelegate() {
        mInstance=this;
        adapterWebView();
        initThrid();
        addActivityListener();
    }
}
