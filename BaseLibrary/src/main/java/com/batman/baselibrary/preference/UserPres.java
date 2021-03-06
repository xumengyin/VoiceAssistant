package com.batman.baselibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.batman.baselibrary.data.result.UserResult;
import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.network.Resource;

public class UserPres implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_userId = "KEY_userId";
    public static final String KEY_userName = "KEY_userName";
    public static final String KEY_headPortrait = "KEY_headPortrait";
    public static final String KEY_mobilePhone = "KEY_mobilePhone";
    public static final String KEY_inviteCode = "KEY_inviteCode";
    public static final String KEY_wechat = "KEY_wechat";
    public static final String KEY_openid = "KEY_openid";
    public static final String KEY_status = "KEY_status";
    public static final String KEY_token = "KEY_token";
    public static final String KEY_is_tourist = "KEY_is_tourist";

    public static final String KEY_COUNTRY = "KEY_COUNTRY";
    public static final String KEY_PROVINCE = "KEY_PROVINCE";
    public static final String KEY_CITY = "KEY_CITY";
    public static final String KEY_DISTRICT = "KEY_DISTRICT";
    public static final String KEY_STREET = "KEY_STREET";

    public static final String KEY_GOLD = "KEY_GOLD";
    public static final String KEY_MONEY = "KEY_MONEY";
    public static final String KEY_GOLD_RATE = "KEY_GOLD_RATE";
    public static final String KEY_TODAY_GOLD = "KEY_TODAY_GOLD";

    public static final String KEY_PUSH_TOKEN = "KEY_PUSH_TOKEN";


    public String userId;
    public String userName;
    public String userHeadPortrait;
    public String mobilePhone;
    public String inviteCode;
    public String wechat;
    public String openid;
    public String status;
    public String mToken;
    public String mIsTourist;

    public String country;
    public String province;
    public String city;
    public String district;
    public String street;

    public String mGold;
    public String mMoney;
    public String mGoldRate;
    public String mTodayGold;

    public String mPushToken;


    public static final String IS_TOURIST_1 = "1"; //??????
    public static final String IS_TOURIST_2 = "2"; //????????????
    public static final String IS_TOURIST_3 = "3"; //??????
    //??????token???????????????????????????


    private static UserPres mUserPres;

    private UserPres() {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        loadPrefs(getSharedPreferences());
    }

    public static UserPres getInstance() {

        if (mUserPres == null) {
            mUserPres = new UserPres();
            mEditor = getSharedPreferences().edit();
        }
        return mUserPres;
    }

    private void loadPrefs(SharedPreferences prefs) {

        this.userId = prefs.getString(KEY_userId, "");
        this.userName = prefs.getString(KEY_userName, "");
        this.userHeadPortrait = prefs.getString(KEY_headPortrait, "");
        this.mobilePhone = prefs.getString(KEY_mobilePhone, "");
        this.inviteCode = prefs.getString(KEY_inviteCode, "");
        this.wechat = prefs.getString(KEY_wechat, "");
        this.openid = prefs.getString(KEY_openid, "");
        this.status = prefs.getString(KEY_status, "");
        this.mToken = prefs.getString(KEY_token, "");
        this.mIsTourist = prefs.getString(KEY_is_tourist, "");
        this.mPushToken = prefs.getString(KEY_PUSH_TOKEN, "");

        //??????

        this.mGold = prefs.getString(KEY_GOLD, "");
        this.mGoldRate = prefs.getString(KEY_GOLD_RATE, "");
        this.mMoney = prefs.getString(KEY_MONEY, "");
        this.mTodayGold = prefs.getString(KEY_TODAY_GOLD, "");

        //?????????????????????
        this.country = prefs.getString(KEY_COUNTRY, "");
        this.province = prefs.getString(KEY_PROVINCE, "");
        this.city = prefs.getString(KEY_CITY, "");
        this.district = prefs.getString(KEY_DISTRICT, "");
        this.street = prefs.getString(KEY_STREET, "");

    }

    static SharedPreferences getSharedPreferences() {
        return ApplicationDispatcher.get().getApplicationContext().getSharedPreferences("IM_USER", Context.MODE_PRIVATE);
    }

    /**
     * ??????????????????
     *
     * @param resource
     */
    public static void save(Resource<UserResult> resource) {
        //??????

        if (resource.data != null) {
            UserPres.save(KEY_userId, resource.data.id, false);
            UserPres.save(KEY_userName, resource.data.userName, false);
            UserPres.save(KEY_headPortrait, resource.data.headPortrait, false);
            UserPres.save(KEY_mobilePhone, resource.data.mobilePhone, false);
            UserPres.save(KEY_inviteCode, resource.data.inviteCode, false);
            UserPres.save(KEY_wechat, resource.data.wechat, false);
            UserPres.save(KEY_openid, resource.data.openid, false);
            UserPres.save(KEY_status, resource.data.status, false);

            if (TextUtils.isEmpty(resource.data.isTouris)) {
                //???????????????????????????
                UserPres.save(KEY_is_tourist, IS_TOURIST_1, false);
            } else {
                UserPres.save(KEY_is_tourist, resource.data.isTouris, false);
            }
        }else {
            //?????? data ???????????????????????????????????? ?????????
            UserPres.save(KEY_is_tourist, IS_TOURIST_3, false);
        }

        UserPres.save(KEY_token, resource.token, false);
        mEditor.commit();
    }

    public static void savePushToken(String token) {

        UserPres.save(KEY_PUSH_TOKEN, token, true);
    }

    /**
     * ??????
     */
    public void clear() {
        userId = "";
        userName = "";
        userHeadPortrait = "";
        mobilePhone = "";
        inviteCode = "";
        wechat = "";
        openid = "";
        status = "";
        mToken = "";
        mIsTourist = IS_TOURIST_3;

        mGold = "";
        mMoney = "";
        mGoldRate = "";
        mTodayGold = "";
        mPushToken = "";

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY_userId, "");
        editor.putString(KEY_userName, "");
        editor.putString(KEY_headPortrait, "");
        editor.putString(KEY_mobilePhone, "");
        editor.putString(KEY_inviteCode, "");
        editor.putString(KEY_wechat, "");
        editor.putString(KEY_openid, "");
        editor.putString(KEY_status, "");
        editor.putString(KEY_token, "");
        editor.putString(KEY_is_tourist, IS_TOURIST_3);

        editor.putString(KEY_GOLD, "");
        editor.putString(KEY_GOLD_RATE, "");
        editor.putString(KEY_MONEY, "");
        editor.putString(KEY_TODAY_GOLD, "");

        editor.putString(KEY_PUSH_TOKEN, "");

        editor.commit();

    }

    /**
     * ??????
     */
    public void logout() {

        userId = "";
        userName = "";
        userHeadPortrait = "";
        mobilePhone = "";
        inviteCode = "";
        wechat = "";
        openid = "";
        status = "";
        mToken = "";
        mIsTourist = IS_TOURIST_3;

        mGold = "";
        mMoney = "";
        mGoldRate = "";
        mTodayGold = "";

        mPushToken = "";


        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY_userId, "");
        editor.putString(KEY_userName, "");
        editor.putString(KEY_headPortrait, "");
        editor.putString(KEY_mobilePhone, "");
        editor.putString(KEY_inviteCode, "");
        editor.putString(KEY_wechat, "");
        editor.putString(KEY_openid, "");
        editor.putString(KEY_status, "");
        //token??????
        editor.putString(KEY_token, "");
        editor.putString(KEY_is_tourist, IS_TOURIST_3);

        editor.putString(KEY_GOLD, "");
        editor.putString(KEY_GOLD_RATE, "");
        editor.putString(KEY_MONEY, "");
        editor.putString(KEY_TODAY_GOLD, "");

        editor.putString(KEY_PUSH_TOKEN, "");


        editor.commit();
    }


    private static SharedPreferences.Editor mEditor;

    public static void save(String key, Object value) {
        //????????????
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

    /**
     * ???????????????
     *
     * @return
     */
    public static boolean isIsTourist() {
        if (!TextUtils.isEmpty(UserPres.getInstance().mToken) && UserPres.IS_TOURIST_1.equals(UserPres.getInstance().mIsTourist)) {
            return true;

        }
        return false;
    }


    public static boolean isLogout() {
        if (TextUtils.isEmpty(UserPres.getInstance().mToken) && UserPres.IS_TOURIST_3.equals(UserPres.getInstance().mIsTourist)) {
            return true;

        }
        return false;
    }


    /**
     * ????????????token
     *
     * @return
     */
    public static boolean isHaveToken() {
        if (!TextUtils.isEmpty(UserPres.getInstance().mToken)) {
            return true;

        }
        return false;
    }


}
