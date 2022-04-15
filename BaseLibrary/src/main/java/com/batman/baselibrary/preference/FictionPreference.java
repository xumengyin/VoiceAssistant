package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.delegate.ApplicationDispatcher;


public class FictionPreference implements SharedPreferences.OnSharedPreferenceChangeListener {


    public static final String KEY_PREFERENCE_NAME = "KEY_FICTION";

    public static final int TOTAL_RUN_DURATION_TIME_1 = 60 * 10;
    public static final int TOTAL_RUN_DURATION_TIME_2 = 60 * 25;
    public static final int TOTAL_RUN_DURATION_TIME_3 = 60 * 40;
    public static final int TOTAL_RUN_DURATION_TIME_4 = 60 * 60;
    public static final int TOTAL_RUN_DURATION_TIME_5 = 60 * 90;

    public int GOLD_1 = 25;
    public int GOLD_2 = 35;
    public int GOLD_3 = 50;
    public int GOLD_4 = 60;
    public int GOLD_5 = 75;


    public int GOLD_MUL_1 = 2;
    public int GOLD_MUL_2 = 2;
    public int GOLD_MUL_3 = 2;
    public int GOLD_MUL_4 = 2;
    public int GOLD_MUL_5 = 2;

    //次数
    public static final int WAHCH_TIME_1 = 1;
    public static final int WAHCH_TIME_2 = 2;
    public static final int WAHCH_TIME_3 = 3;
    public static final int WAHCH_TIME_4 = 4;
    public static final int WAHCH_TIME_5 = 5;

    //当天是否登录过
    public static final String KEY_DAY_FIRST_ENTER_DATE = "KEY_DAY_FIRST_ENTER_DATE";
    public static final String KEY_WATCH_TIME = "KEY_WATCH_TIME";
    public static final String KEY_WATCH_CURRENT_VALUE = "KEY_WATCH_CURRENT_VALUE";

    public static final String KEY_GOLD_1 = "KEY_GOLD_1";
    public static final String KEY_GOLD_2 = "KEY_GOLD_2";
    public static final String KEY_GOLD_3 = "KEY_GOLD_3";
    public static final String KEY_GOLD_4 = "KEY_GOLD_4";
    public static final String KEY_GOLD_5 = "KEY_GOLD_5";

    public static final String KEY_GOLD_MUL_1 = "KEY_GOLD_MUL_1";
    public static final String KEY_GOLD_MUL_2 = "KEY_GOLD_MUL_2";
    public static final String KEY_GOLD_MUL_3 = "KEY_GOLD_MUL_3";
    public static final String KEY_GOLD_MUL_4 = "KEY_GOLD_MUL_4";
    public static final String KEY_GOLD_MUL_5 = "KEY_GOLD_MUL_5";

    //是否是当天
    public String isDayEnterDate;

    //当前次数
    public int mWatchTime;
    //当前观看进度
    public int mWatchCurrentValue;

    private static FictionPreference mUserPres;

    private FictionPreference() {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        loadPrefs(getSharedPreferences());
    }

    public static FictionPreference getInstance() {

        if (mUserPres == null) {
            mUserPres = new FictionPreference();
            mEditor = getSharedPreferences().edit();
        }
        return mUserPres;
    }

    private void loadPrefs(SharedPreferences prefs) {

        this.isDayEnterDate = prefs.getString(KEY_DAY_FIRST_ENTER_DATE, "");
        this.mWatchTime = prefs.getInt(KEY_WATCH_TIME, WAHCH_TIME_1);
        this.mWatchCurrentValue = prefs.getInt(KEY_WATCH_CURRENT_VALUE, 0);

        try {
            this.GOLD_1 = Integer.parseInt(prefs.getString(KEY_GOLD_1, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //金币数
        try {
            this.GOLD_2 = Integer.parseInt(prefs.getString(KEY_GOLD_2, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_3 = Integer.parseInt(prefs.getString(KEY_GOLD_3, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_4 = Integer.parseInt(prefs.getString(KEY_GOLD_4, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_5 = Integer.parseInt(prefs.getString(KEY_GOLD_5, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //翻倍数
        try {
            this.GOLD_MUL_1 = Integer.parseInt(prefs.getString(KEY_GOLD_MUL_1, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_MUL_2 = Integer.parseInt(prefs.getString(KEY_GOLD_MUL_2, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_MUL_3 = Integer.parseInt(prefs.getString(KEY_GOLD_MUL_3, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_MUL_4 = Integer.parseInt(prefs.getString(KEY_GOLD_MUL_4, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            this.GOLD_MUL_5 = Integer.parseInt(prefs.getString(KEY_GOLD_MUL_5, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


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
