package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.delegate.ApplicationDispatcher;


/**
 * 首页任务金币入口
 */
public class TaskPreference implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREFERENCE_NAME = "KEY_TASK";

    //当天是否登录过
    public static final String KEY_DAY_FIRST_ENTER_DATE = "KEY_DAY_FIRST_ENTER_DATE";
    public static final String KEY_DATA = "KEY_DATA";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_DO = "KEY_DO";


    public String mData;
    public String eventName;
    public boolean isDo;
    //是否是当天
    public String isDayEnterDate;


    private static TaskPreference mUserPres;

    private TaskPreference() {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        loadPrefs(getSharedPreferences());
    }

    public static TaskPreference getInstance() {

        if (mUserPres == null) {
            mUserPres = new TaskPreference();
            mEditor = getSharedPreferences().edit();
        }
        return mUserPres;
    }

    private void loadPrefs(SharedPreferences prefs) {

        this.isDayEnterDate = prefs.getString(KEY_DAY_FIRST_ENTER_DATE, "");
        this.mData = prefs.getString(KEY_DATA, "");
        this.eventName = prefs.getString(KEY_NAME, "");
        this.isDo = prefs.getBoolean(KEY_DO, false);

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
