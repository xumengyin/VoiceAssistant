/**
 * @Title: AppPrefenrence.java
 * @Package com.dna.app.pref
 * @Description: TODO
 */
package com.jerry.voiceassistant.preference;

import android.content.SharedPreferences;

import com.batman.baselibrary.preference.BasePreference;
import com.network.utils.LogUtils;

/**
 * @Description: 保存 init login返回的一些初始值
 * @author wangwenbin
 * @date 2013-4-27
 */
public class UserPrefenrence extends BasePreference {

	private static UserPrefenrence instance;
	public String TAG = this.getClass().getSimpleName();
	public String username;
	public String password;
	public String userId;
	public String mobile;
	public String nickName;
	public String realName;
	public String email;
	public String birthday;
	public String userType;
	public String corpId;
	public String corpName;
	public String deptId;
	public String deptName;
	public String provinceId;
	public String provinceName;
	public String cityId;
	public String cityName;
	public String longitude;
	public String latitude;
	public String defaultPrivObjId;
	public String privLpno;
	public String privIdName;

	public String qqTokenExpireTime;
	public String sinaTokenExpireTime;
	public String operatorDeptId;
	public String operatorDeptName;
	public String operatorCorpId;
	public String vspId;
	public String authId;
	public String isBind;
	public String openAccessorySet;
	public String openSendDangerSet;
	public String openResuceSet;
	public String hasDailyAttendence;
	public String vipLevel;
	
	public String task_examScore;
	public String task_examResultType;
	//public ArrayList<CarInfo> privateCars;
	public int defalutPriIndex;

	// pushid
	public String pushId;
	public int firstOpen;
	public int pass;//手机号是否通过验证，0-否 1-是
	public String login_token;
	public String third_platform;
	public String open_id;
	public String access_token;
	public String sex;
	public String shopurl;


	public long dailyAttendence;

	public static UserPrefenrence getInstance()
	{
		if(instance==null)
		{
			instance=new UserPrefenrence();
		}
		return instance;
	}

	private UserPrefenrence() {

		//prefs.registerOnSharedPreferenceChangeListener(this);
//		prefs = getSharedPreferences();
//		loadPrefs(prefs);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		LogUtils.i(TAG, "onSharedPreferenceChanged(): " + key);
		loadPrefs(preferences);
	}

	@Override
	protected void loadPrefs(SharedPreferences prefs) {
		long start = System.currentTimeMillis();

		this.username = prefs.getString(PrefenrenceKeys.USENAME, "");
		this.password = prefs.getString(PrefenrenceKeys.PASSWORD, "");

		this.userId = prefs.getString(PrefenrenceKeys.userId, "");
		this.mobile = prefs.getString(PrefenrenceKeys.mobile, "");
		this.nickName = prefs.getString(PrefenrenceKeys.nickName, "");
		this.realName = prefs.getString(PrefenrenceKeys.realName, "");
		this.email = prefs.getString(PrefenrenceKeys.email, "");
		this.userType = prefs.getString(PrefenrenceKeys.userType, "");
		this.corpId = prefs.getString(PrefenrenceKeys.corpId, "");
		this.corpName = prefs.getString(PrefenrenceKeys.corpName, "");
		this.birthday = prefs.getString(PrefenrenceKeys.birthday, "");
		this.deptId = prefs.getString(PrefenrenceKeys.deptId, "");
		this.operatorDeptId = prefs.getString(PrefenrenceKeys.operatorDeptId,
				"");
		this.operatorDeptId = prefs.getString(PrefenrenceKeys.operatorDeptId,
				"");
		this.operatorDeptName = prefs.getString(
				PrefenrenceKeys.operatorDeptName, "");
		this.operatorCorpId = prefs.getString(PrefenrenceKeys.operatorCorpId,
				"");
		this.vspId = prefs.getString(PrefenrenceKeys.vspId, "");
		this.authId = prefs.getString(PrefenrenceKeys.authId, "");
		this.isBind = prefs.getString(PrefenrenceKeys.isBind, "");
		this.deptName = prefs.getString(PrefenrenceKeys.deptName, "");
		this.provinceId = prefs.getString(PrefenrenceKeys.provinceId, "");
		this.provinceName = prefs.getString(PrefenrenceKeys.provinceName, "");
		this.cityId = prefs.getString(PrefenrenceKeys.cityId, "");
		this.cityName = prefs.getString(PrefenrenceKeys.cityName, "");
		this.longitude = prefs.getString(PrefenrenceKeys.longitude, "");
		this.latitude = prefs.getString(PrefenrenceKeys.latitude, "");
		this.defaultPrivObjId = prefs.getString(
				PrefenrenceKeys.defaultPrivObjId, "");
		this.privLpno = prefs.getString(PrefenrenceKeys.privLpno, "");
		this.privIdName = prefs.getString(PrefenrenceKeys.privIdName, "");

		this.qqTokenExpireTime = prefs.getString(
				PrefenrenceKeys.qqTokenExpireTime, "");
		this.sinaTokenExpireTime = prefs.getString(
				PrefenrenceKeys.sinaTokenExpireTime, "");

		String mycar = prefs.getString(PrefenrenceKeys.MYCARS, "");

//		Type listType = new TypeToken<ArrayList<CarInfo>>() {
//		}.getType();
		//this.privateCars = OFJsonUtil.fromJson(mycar, listType);
		this.defalutPriIndex = prefs.getInt(PrefenrenceKeys.defaultPriIndex, 0);

		this.pushId = prefs.getString(PrefenrenceKeys.pushId, "");
		this.firstOpen = prefs.getInt(PrefenrenceKeys.firstOpen,
				1);//Constants.version + "_" + this.username

		this.dailyAttendence = prefs.getLong(PrefenrenceKeys.dailyAttendence,
				1);//Constants.version + "_" + this.username

		this.openAccessorySet = prefs.getString(
				PrefenrenceKeys.openAccessorySet, "");

		this.openSendDangerSet = prefs.getString(
				PrefenrenceKeys.openSendDangerSet, "");
		this.openResuceSet = prefs.getString(PrefenrenceKeys.openResuceSet, "");
		long end = System.currentTimeMillis();
		LogUtils.i(TAG, "loadPrefs time:" + (end - start));
		
		this.hasDailyAttendence = prefs.getString(
				PrefenrenceKeys.hasDailyAttendence, "");
		this.vipLevel = prefs.getString(
				PrefenrenceKeys.vipLevel, "");
		this.task_examScore = prefs.getString(
				PrefenrenceKeys.task_examScore, "");
		this.task_examResultType = prefs.getString(
				PrefenrenceKeys.task_examResultType, "");

		this.pass = prefs.getInt(
				PrefenrenceKeys.pass, 0);
		this.login_token = prefs.getString(
				PrefenrenceKeys.LOGIN_TOKEN, "");
		this.third_platform = prefs.getString(
				PrefenrenceKeys.THIRD_PLATFORM, "");
		this.open_id = prefs.getString(
				PrefenrenceKeys.OPEN_ID, "");
		this.access_token = prefs.getString(
				PrefenrenceKeys.ACCESS_TOKEN, "");
		this.sex = prefs.getString(
				PrefenrenceKeys.sex, "");
		this.shopurl = prefs.getString(
				PrefenrenceKeys.shopurl, "");
	}

	@Override
	public String getPreferenceName() {
		return "userPreference";
	}
}
