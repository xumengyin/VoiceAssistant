package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.delegate.ApplicationDispatcher;

public abstract class BasePreference implements SharedPreferences.OnSharedPreferenceChangeListener {


    private SharedPreferences.Editor mEditor;

    protected SharedPreferences preferences;

    public BasePreference() {

        preferences = getSharedPreferences();
        preferences.registerOnSharedPreferenceChangeListener(this);
        loadPrefs(preferences);
    }
    protected void loadPrefs(SharedPreferences sharedPreferences)
    {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public abstract String getPreferenceName();

    public void save(String key, Object value) {
        //保存数据
        save(key, value, true);
    }

    public SharedPreferences getSharedPreferences() {
        return ApplicationDispatcher.get().getApplicationContext().getSharedPreferences(getPreferenceName(), Context.MODE_PRIVATE);
    }

    public void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.commit();
        // loadPrefs(getSharedPreferences());
    }

    public void save(String key, Object value, boolean isSave) {
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

    public void apply(String key, Object value) {
        //保存数据
        apply(key, value, true);
    }

    public void apply(String key, Object value, boolean isSave) {
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
}
