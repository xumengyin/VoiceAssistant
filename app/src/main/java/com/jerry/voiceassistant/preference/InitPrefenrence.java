/**
 * @Title: InitPrefenrence.java
 * @Package com.dna.app.pref
 * @Description: TODO
 */
package com.jerry.voiceassistant.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.batman.baselibrary.delegate.ApplicationDispatcher;
import com.batman.baselibrary.preference.BasePreference;

/**
 * @author wangwenbin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2013-4-27
 */
public class InitPrefenrence extends BasePreference {

    public static InitPrefenrence instance;
    public static String NAME = "initpre";
    public String emailMsg = "";
    public String smsMsg = "";
    public String vehicle_picUrl = "";
    public String moreExamDetail = "";
    public String insuranceLogoPath = "";
    public String pinganNavi = "";
    public String examTime = "";
    public String picShowInterval = "";

    public String PKPicUrl = "";
    public String PKHtmlUrl = "";
    public String vehiclePriceAssessHtmlUrl = "";
    public String serviceItemHtml = "";
    public String cxzAppShareHtml = "";
    public String LOADINGAPPPLASH = "";
    public String pushTime = "";
    public String goods_prebook_type = "";
    public String goods_order_status = "";
    public String myInsuranceOrderUrl = "";
    public int firstapp;
    public boolean isShowPrivacy=true;


    public static InitPrefenrence getInstance()
    {
        if(instance==null)
        {
            instance=new InitPrefenrence();
        }
        return instance;
    }
    public InitPrefenrence() {

    }
    @Override
    public void loadPrefs(SharedPreferences sharedPreferences) {
        this.emailMsg = preferences.getString(PrefenrenceKeys.emailMsg, "");
        this.smsMsg = preferences.getString(PrefenrenceKeys.smsMsg, "");
        this.vehicle_picUrl = preferences
                .getString(PrefenrenceKeys.vehicle_picUrl, "");
        this.moreExamDetail = preferences
                .getString(PrefenrenceKeys.moreExamDetail, "");
        this.insuranceLogoPath = preferences.getString(
                PrefenrenceKeys.insuranceLogoPath, "");
        this.pinganNavi = preferences.getString(PrefenrenceKeys.pinganNavi, "");
        this.examTime = preferences.getString(PrefenrenceKeys.examTime, "");
        this.picShowInterval = preferences.getString(PrefenrenceKeys.picShowInterval,
                "");

        this.PKPicUrl = preferences.getString(PrefenrenceKeys.PKPicUrl, "");
        this.PKHtmlUrl = preferences.getString(PrefenrenceKeys.PKHtmlUrl, "");
        this.vehiclePriceAssessHtmlUrl = preferences.getString(PrefenrenceKeys.vehiclePriceAssessHtmlUrl, "");
        this.cxzAppShareHtml = preferences.getString(PrefenrenceKeys.cxzAppShareHtml, "");
        this.serviceItemHtml = preferences.getString(PrefenrenceKeys.serviceItemHtml, "");
        this.LOADINGAPPPLASH = preferences.getString(PrefenrenceKeys.LOADINGAPPPLASH, "");
        this.pushTime = preferences.getString(PrefenrenceKeys.PUSHTIME, "");
        this.goods_prebook_type = preferences.getString(PrefenrenceKeys.goods_prebook_type, "");
        this.goods_order_status = preferences.getString(PrefenrenceKeys.goods_order_status, "");
        this.myInsuranceOrderUrl = preferences.getString(PrefenrenceKeys.myInsuranceOrderUrl, "");
        this.firstapp = preferences.getInt(
                PrefenrenceKeys.firstapp, 1);
        this.isShowPrivacy = preferences.getBoolean(
                PrefenrenceKeys.isShowPrivacy, true);
    }


    @Override
    public String getPreferenceName() {
        return NAME;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadPrefs(sharedPreferences);
    }
}
