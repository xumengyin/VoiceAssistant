package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.delegate.ApplicationDispatcher;


public class NewsPreference implements SharedPreferences.OnSharedPreferenceChangeListener {


    public static final String KEY_PREFERENCE_NAME = "KEY_NEWS";

    //观看一圈时间
    public static final int RUN_DURATION_TIME_1 = 20 * 10;

    public static final int TOTAL_RUN_DURATION_TIME_1 = 20;

    //当前次数
    public static final int WAHCH_TIME_1 = 1;

    //当天是否登录过
    public static final String KEY_DAY_FIRST_ENTER_DATE = "KEY_DAY_FIRST_ENTER_DATE";
    public static final String KEY_WATCH_TIME = "KEY_WATCH_TIME";
    public static final String KEY_WATCH_GOLD = "KEY_WATCH_GOLD";
    public static final String KEY_WATCH_CURRENT_VALUE = "KEY_WATCH_CURRENT_VALUE";

    //总次数
    public static final int WAHCH_TIME_5 = 20;
    //每5次领一次奖励
    public static final int WAHCH_TIME_DEFAULT = 5;
    public static final int WAHCH_TIME_DEFAULT_TIME = 4;
    public static final int WAHCH_TOTAL_GOLD = 500;


    //是否是当天
    public String isDayEnterDate;

    //当前次数
    public int mWatchTime;
    //当前观看进度
    public int mWatchCurrentValue;

    public String mWatchGold;

    private static NewsPreference mUserPres;

    private NewsPreference() {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        loadPrefs(getSharedPreferences());
    }

    public static NewsPreference getInstance() {

        if (mUserPres == null) {
            mUserPres = new NewsPreference();
            mEditor = getSharedPreferences().edit();
        }
        return mUserPres;
    }

    private void loadPrefs(SharedPreferences prefs) {

        this.isDayEnterDate = prefs.getString(KEY_DAY_FIRST_ENTER_DATE, "");
        this.mWatchTime = prefs.getInt(KEY_WATCH_TIME, WAHCH_TIME_1);
        this.mWatchCurrentValue = prefs.getInt(KEY_WATCH_CURRENT_VALUE, 0);
        this.mWatchGold = prefs.getString(KEY_WATCH_GOLD, "");

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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadPrefs(getSharedPreferences());
    }

    @Override
    protected void finalize() {
        getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
