package com.batman.baselibrary.utils.update;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class UpdatePreference implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_IS_DOWNLOAD = "is_download";
    private final SharedPreferences prefs;

    public boolean isDownLoad = false;


    public UpdatePreference(SharedPreferences _prefs) {
        this.prefs = _prefs;
        prefs.registerOnSharedPreferenceChangeListener(this);
        loadPrefs(prefs);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadPrefs(prefs);
    }

    @Override
    protected void finalize() {
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void loadPrefs(SharedPreferences prefs) {
        this.isDownLoad = prefs.getBoolean(KEY_IS_DOWNLOAD, false);
    }

    public static void clearUsePre(Context context) {
        SharedPreferences sharef = UpdatePreference.getSharedPreferences(context);
        SharedPreferences.Editor editor = sharef.edit();
        editor.putBoolean(KEY_IS_DOWNLOAD, false);
        editor.commit();
    }
}
