package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.delegate.ApplicationDispatcher;


public class WeatherPreference implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREFERENCE_NAME = "KEY_WEATHER";

    public static final int DEFAULT_ID = -1;

    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_DISTRICT = "KEY_DISTRICT";
    public static final String KEY_ADDRESS = "KEY_ADDRESS";
    public static final String KEY_TEM = "KEY_TEM";
    public static final String KEY_CONDITION = "KEY_CONDITION";

    public int mId;
    public String mDistrict;
    public String mAddress;
    public String mTem;
    public String mCondition;

    private static WeatherPreference mUserPres;

    private WeatherPreference() {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        loadPrefs(getSharedPreferences());
    }

    public static WeatherPreference getInstance() {

        if (mUserPres == null) {
            mUserPres = new WeatherPreference();
            mEditor = getSharedPreferences().edit();
        }
        return mUserPres;
    }

    private void loadPrefs(SharedPreferences prefs) {

        this.mId = prefs.getInt(KEY_ID, DEFAULT_ID);
        this.mDistrict = prefs.getString(KEY_DISTRICT, "");
        this.mAddress = prefs.getString(KEY_ADDRESS, "");
        this.mTem = prefs.getString(KEY_TEM, "");
        this.mCondition = prefs.getString(KEY_CONDITION, "");

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
