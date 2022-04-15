package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.BuildConfig;
import com.batman.baselibrary.delegate.ApplicationDispatcher;


public class SettingPreference implements SharedPreferences.OnSharedPreferenceChangeListener {


    public static final String KEY_PREFERENCE_NAME = "KEY_SETTING";
    //首屏隐私协议
    public static final String KEY_USER_AGREEMENT = "KEY_USER_AGREEMENT";

    //第一次安装
    public static final String KEY_FIRST_RANDOM = "KEY_FIRST_RANDOM";
    public static final String KEY_FIRST_WAKE = "KEY_FIRST_WAKE";
    public static final String KEY_FIRST_RANDOM_HAND = "KEY_FIRST_RANDOM_HAND";

    //每天
    public static final String KEY_TODAY_FIRST_RANDOM = "KEY_TODAY_FIRST_RANDOM";
    public static final String KEY_TODAY_FIRST_RANDOM_HAND = "KEY_TODAY_FIRST_RANDOM_HAND";

    //金币音效
    public static final String KEY_USER_RING = "KEY_USER_RING";

    public static final String KEY_VIDEO_WARN = "KEY_VIDEO_WARN";
    public static final String KEY_FICTION_WARN = "KEY_FICTION_WARN";
    public static final String KEY_NEWS_WARN = "KEY_NEWS_WARN";

    //当天是否登录过
    public static final String KEY_DAY_FIRST_ENTER_DATE = "KEY_DAY_FIRST_ENTER_DATE";


    //上传运动数据
    public static final String KEY_FIRST_NET_WALK = "KEY_FIRST_NET_WALK";

    //deep
    public static final String KEY_DEEP_DATE = "KEY_DEEP_DATE";

    //deep
    public static final String KEY_DEEP_AD_CAN = "KEY_DEEP_AD_CAN";

    //tab
    public static final String KEY_TAB_DATE = "KEY_TAB_DATE";
    public static final String KEY_TAB_TASK_CLICK = "KEY_TAB_TASK_CLICK";

    public boolean isAgreement;
    public boolean isRing;
    public boolean isVideoWarn;
    public boolean isFictionWarn;
    public boolean isNewsWarn;

    //第一次 安装 app 即刻生产 随机，唤醒 金币
    public boolean isFirstRandom;
    public boolean isFirstWake;
    public boolean isFirstRandomHand;

    //每天 打开 即刻产生 随机金币 和小手动画
    public boolean isToadyFirstRandom;
    public boolean isToadyFirstRandomHand;

    //运动数据
    public String isDayEnterDate;

    public boolean isFirstNetWalk;

    //深知
    public String isDeepDate;

    public boolean deepAdCan = true;

    //tab
    public String isTabDate;
    public String isTabTaskClick;

    private static SettingPreference mUserPres;

    private SettingPreference() {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        loadPrefs(getSharedPreferences());
    }

    public static SettingPreference getInstance() {

        if (mUserPres == null) {
            mUserPres = new SettingPreference();
            mEditor = getSharedPreferences().edit();
        }
        return mUserPres;
    }

    private void loadPrefs(SharedPreferences prefs) {

        this.isAgreement = prefs.getBoolean(KEY_USER_AGREEMENT, false);

        this.isRing = prefs.getBoolean(KEY_USER_RING, true);
        this.isDayEnterDate = prefs.getString(KEY_DAY_FIRST_ENTER_DATE, "");
        this.isFirstNetWalk = prefs.getBoolean(KEY_FIRST_NET_WALK, false);
        this.isVideoWarn = prefs.getBoolean(KEY_VIDEO_WARN, true);

        this.isDeepDate = prefs.getString(KEY_DEEP_DATE, "");
        this.deepAdCan = prefs.getBoolean(KEY_DEEP_AD_CAN, true);

        this.isFirstRandom = prefs.getBoolean(KEY_FIRST_RANDOM, false);
        this.isFirstWake = prefs.getBoolean(KEY_FIRST_WAKE, false);
        this.isFirstRandomHand = prefs.getBoolean(KEY_FIRST_RANDOM_HAND, false);

        this.isToadyFirstRandom = prefs.getBoolean(KEY_TODAY_FIRST_RANDOM, false);
        this.isToadyFirstRandomHand = prefs.getBoolean(KEY_TODAY_FIRST_RANDOM_HAND, false);

        this.isTabDate = prefs.getString(KEY_TAB_DATE, "");
        this.isTabTaskClick = prefs.getString(KEY_TAB_TASK_CLICK, "");
        this.isFictionWarn = prefs.getBoolean(KEY_FICTION_WARN, true);
        this.isNewsWarn = prefs.getBoolean(KEY_NEWS_WARN, true);
    }


    public static SharedPreferences getSharedPreferences() {
        return ApplicationDispatcher.get().getApplicationContext().getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.apply();
        loadPrefs(getSharedPreferences());

    }

    private static SharedPreferences.Editor mEditor;

    public static void save(String key, Object value) {
        //保存数据
        save(key, value, true);
    }

    public static void save(String key, Object value, boolean isSave) {
        if (mEditor == null) {
            mEditor = getSharedPreferences().edit();
        }
        if (value instanceof String) {
            mEditor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            mEditor.putInt(key, (int) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(key, (float) value);
        } else if (value instanceof Long) {
            mEditor.putLong(key, (long) value);
        } else if (value instanceof Boolean) {
            mEditor.putBoolean(key, (boolean) value);
        }
        if (isSave) {
            mEditor.commit();
        }
    }

    public static void apply(String key, Object value) {
        //保存数据
        apply(key, value, true);
    }

    public static void apply(String key, Object value, boolean isSave) {
        if (mEditor == null) {
            mEditor = getSharedPreferences().edit();
        }
        if (value instanceof String) {
            mEditor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            mEditor.putInt(key, (int) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(key, (float) value);
        } else if (value instanceof Long) {
            mEditor.putLong(key, (long) value);
        } else if (value instanceof Boolean) {
            mEditor.putBoolean(key, (boolean) value);
        }
        if (isSave) {
            mEditor.apply();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadPrefs(getSharedPreferences());
    }

    @Override
    protected void finalize() {
        getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * 是否展示广告
     *
     * @return
     */
    public static boolean isShowAd() {
        if (BuildConfig.DEBUG) {
            return true;
        } else {
            return getInstance().deepAdCan;
        }
    }
}
