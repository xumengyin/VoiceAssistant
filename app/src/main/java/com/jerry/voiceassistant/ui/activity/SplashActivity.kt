package com.jerry.voiceassistant.ui.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.batman.baselibrary.base.BaseActivity
import com.batman.baselibrary.base.BaseObserver
import com.jerry.voiceassistant.Constants
import com.jerry.voiceassistant.MainActivity
import com.jerry.voiceassistant.MainApplication
import com.jerry.voiceassistant.R
import com.jerry.voiceassistant.api.PackagePostData
import com.jerry.voiceassistant.data.InitBean
import com.jerry.voiceassistant.preference.SettingPreference
import com.jerry.voiceassistant.ui.dialog.PrivacyDialog
import com.jerry.voiceassistant.viewModel.MainViewModel
import com.network.Resource
import com.network.utils.LogUtils
import com.tbruyelle.rxpermissions3.RxPermissions


class SplashActivity : BaseActivity() {

    val viewModel by lazy{
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    var argeementDialog: PrivacyDialog? = null
    val rxPermissions = RxPermissions(this@SplashActivity)
    val ads by lazy {
        //ATSplashAd(this@SplashActivity, Constants.ADS_SPLASH, null, this@SplashActivity, 5000)
    }
    var isFromBackground = false
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    fun initData() {
        viewModel.init(PackagePostData.init("")).observe(this,object:BaseObserver<InitBean>(this){
            override fun uiSuccessful(tResource: Resource<InitBean>?) {
                LogUtils.d("xuxu",tResource.toString())
            }
        })
    }

    override fun loadData(savedInstanceState: Bundle?) {
        initData()

//        val oaidHelper = OaidHelper(object : OaidHelper.AppIdsUpdater {
//            override fun OnIdsAvalidError() {
//                LogUtils.logd("oaidHelper----OnIdsAvalidError")
//            }
//
//            override fun OnIdsAvalid(oaid: String) {
//                LogUtils.logd("OnIdsAvalid----${oaid}")
//            }
//
//        })
        //      oaidHelper.getDeviceIds(this)
        //ATSDK.integrationChecking(applicationContext);
    }

    fun dealPrivacy() {
        argeementDialog?.apply {
            if (isShowing) {
                dismiss()
            }
            argeementDialog = null
        }
        val settingPreference = SettingPreference.getInstance()
        if (settingPreference.isAgreement) {
            argeementDialog = PrivacyDialog(this)
            argeementDialog?.apply {
                setCallback(object : PrivacyDialog.DialogCallback {
                    override fun onSure() {
                        SettingPreference.save(SettingPreference.KEY_USER_AGREEMENT, false)
                        //PushHelper.init(applicationContext)
                        MainApplication.getInstance().initPush()
                        dismiss()
                        init()
                    }

                    override fun onCancel() {
                        dismiss()
                        finish()
                    }
                })
                show()
            }
        } else {
            init()
        }
    }

    var hasJump = false
    fun gotoMain() {
        if (isFromBackground) {
            finish()
        } else {
            if (!hasJump) {
                hasJump = true
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }


    override fun initViews() {
        isFromBackground = intent.getBooleanExtra(Constants.INTENT_KEY, false)
        isFromBackground.apply {

            if (!this) {
                //首页进入
                dealPrivacy()
            } else {
                //直接展示广告
                //loadAds()
            }


        }
    }

    fun init() {
        rxPermissions.request(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe { grant ->
            if (grant) {
                gotoMain()
                //  loadAds()
            } else {
                // todo
                gotoMain()
                //  loadAds()
            }

        }
    }

    companion object {

    }

}