//package com.jerry.voiceassistant.ui.activity
//
//import android.graphics.Color
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.Spannable
//import android.text.SpannableString
//import android.text.SpannableStringBuilder
//import android.text.Spanned
//import android.text.style.ForegroundColorSpan
//import android.view.View
//import androidx.core.content.ContextCompat
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ObservableArrayList
//import androidx.databinding.ObservableInt
//import com.jerry.voiceassistant.R
//import com.jerry.voiceassistant.data.NotifyData
//import com.jerry.voiceassistant.data.User
//import com.jerry.voiceassistant.databinding.ActivityTest2Binding
//import com.jerry.voiceassistant.ui.span.VerticalImageSpan
//import com.jerry.voiceassistant.ui.span.jumpSpan.JumpingBeans
//import com.jerry.voiceassistant.utils.Logs
//import kotlinx.android.synthetic.main.activity_test2.*
//import kotlin.random.Random
//
//class TestActivity : AppCompatActivity() {
//   lateinit var binding :ActivityTest2Binding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        testDataBinding()
//       // setContentView(R.layout.activity_test2)
//        //initSpan3()
//    }
//
//    init {
//        Logs.d("aaa","init")
//    }
//
//   fun add(view: View)
//    {
//        binding.user?.apply {
//            age++
//           // binding.user= User("xu",age)
//
//            observeData.set(observeData.get()+1)
//            observeList[0] = Random.nextInt(0,100).toString()
//        }
//        binding.notify?.age=Random.nextInt(0,100).toString()
//    }
//   fun testDataBinding()
//    {
//        val list =ObservableArrayList<String>()
//        list.apply {
//            add("fuck")
//            add("you")
//        }
//        binding=DataBindingUtil.setContentView(this,R.layout.activity_test2)
//        ActivityTest2Binding.inflate(layoutInflater)
//        binding.user= User("xu",30, ObservableInt(1),list)
//        val notifyData=NotifyData()
//        notifyData.apply {
//            name="meng"
//            age="12"
//            binding.notify=this
//        }
//
//    }
//    fun initSpan3()
//    {
//      val jumpingBeans1=  JumpingBeans.with(spanText).appendJumpingDots().build()
//    }
//    fun initSpan2()
//    {
//        val span1=SpannableString("0123456")
//        span1.setSpan(ForegroundColorSpan(Color.RED),0,4,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        spanText.text = span1
//    }
//    fun initSpan()
//    {
//       val drawable= ContextCompat.getDrawable(this,R.drawable.empji)
//        drawable?.setBounds(0,0,drawable.intrinsicWidth,drawable.intrinsicHeight)
//        val imageSpan=VerticalImageSpan(drawable)
//        val builder =SpannableStringBuilder("fuck you hah1 hah2 haha3 haha4")
//
//        builder.apply {
//
//            setSpan(imageSpan,10,11, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
//        }
//        //SpanUtils.with(spanText,this).append()
//        spanText.text = builder
//    }
////    class Proxy{
////        fun add()
////        {
////            binding.user.age++
////        }
////    }
//}