package com.jerry.voiceassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.batman.baselibrary.base.BaseActivity
import com.batman.baselibrary.base.BaseObserver
import com.jerry.voiceassistant.R
import com.jerry.voiceassistant.api.PackagePostData
import com.jerry.voiceassistant.data.InitBean
import com.jerry.voiceassistant.ui.span.jumpSpan.JumpingBeans
import com.jerry.voiceassistant.viewModel.MainViewModel
import com.network.Resource
import com.network.utils.LogUtils
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.throttleClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
      return  R.layout.activity_main
    }
    val viewModel by lazy{
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    fun initData() {
        viewModel.init(PackagePostData.init("")).observe(this,object: BaseObserver<InitBean>(this){
            override fun uiSuccessful(tResource: Resource<InitBean>?) {
                LogUtils.d("xuxu",tResource.toString())
            }
        })
    }
    override fun initViews() {
//        mainText.setOnClickListener {
//            MainApplication.getInstance().sendTTsMsg("支付宝到账")
//        }
        mainText.setOnClickListener(throttleClick(200) {
           // MainApplication.getInstance().sendTTsMsg("支付宝到账")
            initData()
        })
//        dip(20)
//        var jumpingBeans = JumpingBeans.with(mainText)
//            .appendJumpingDots()
//           .setLoopDuration(1500)
//           .build();
    }

    override fun loadData(savedInstanceState: Bundle?) {
        //initData()
    }
}