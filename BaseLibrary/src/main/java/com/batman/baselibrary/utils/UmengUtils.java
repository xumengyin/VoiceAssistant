package com.batman.baselibrary.utils;

import android.content.Context;

import com.batman.baselibrary.event.StatServiceEvent;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class UmengUtils {


    public static final String zhou_0 = "zhou_0";
    public static final String kan_0 = "kan_0";
    public static final String zhuan_0 = "zhuan_0";
    public static final String wo_0 = "wo_0";

    //新手奖励红包
    public static final String freshman_0 = "freshman_0";

    //随机金币
    public static final String zoucoin_1 = "zoucoin_1";
    //走步金币
    public static final String zoucoin_2 = "zoucoin_2";
    //唤醒金币
    public static final String zoucoin_3 = "zoucoin_3";
    //随机金币翻倍
    public static final String zoucoin_4 = "zoucoin_4";
    //走步金币翻倍
    public static final String zoucoin_5 = "zoucoin_5";
    //唤醒金币翻倍
    public static final String zoucoin_6 = "zoucoin_6";

    //任务气泡
    public static final String zoutask_1 = "zoutask_1";
    //抽奖
    public static final String zoutask_2 = "zoutask_2";
    //每日运动
    public static final String zoutask_3 = "zoutask_3";
    //达标赛
    public static final String zoutask_4 = "zoutask_4";
    //夺宝答题
    public static final String zoutask_5 = "zoutask_5";
    //免费小说
    public static final String zoutask_6 = "zoutask_6";

    //抽奖资格
    public static final String prize_1 = "prize_1";
    //刮卡资格
    public static final String prize_2 = "prize_2";

    //运动开始
    public static final String sports_1 = "sports_1";
    //运动奖励
    public static final String sports_2 = "sports_2";
    //运动奖励翻倍
    public static final String sports_3 = "sports_3";

    public static final String match_1 = "match_1";
    public static final String match_2 = "match_2";
    public static final String match_3 = "match_3";

    public static final String answer_1 = "answer_1";

    //签到翻倍
    public static final String zhuansign_1 = "zhuansign_1";
    //每日运动
    public static final String zhuannormal_1 = "zhuannormal_1";
    //每日答题
    public static final String zhuannormal_2 = "zhuannormal_2";
    //达标赛go
    public static final String zhuannormal_3 = "zhuannormal_3";
    //达标赛领取
    public static final String zhuannormalcoin_3 = "zhuannormalcoin_3";
    //邀请好友
    public static final String zhuannormal_4 = "zhuannormal_4";
    //分享
    public static final String zhuannormal_5 = "zhuannormal_5";
    //分享领取
    public static final String zhuannormalcoin_5 = "zhuannormalcoin_5";
    //赚赚新闻计时器
    public static final String zhuannormal_6 = "zhuannormal_6";
    //赚赚新闻计时器领取
    public static final String zhuannormalcoin_6 = "zhuannormalcoin_6";
    //绑定微信
    public static final String zhuanonce_1 = "zhuanonce_1";
    //绑定微信奖励
    public static final String zhuanoncecoin_1 = "zhuanoncecoin_1";
    //绑定手机
    public static final String zhuanonce_2 = "zhuanonce_2";
    //绑定手机奖励
    public static final String zhuanoncecoin_2 = "zhuanoncecoin_2";
    //第一笔提现
    public static final String zhuanonce_3 = "zhuanonce_3";
    //第一笔提现奖励
    public static final String zhuanoncecoin_3 = "zhuanoncecoin_3";
    //开启通知
    public static final String zhuanonce_4 = "zhuanonce_4";
    //开启通知奖励
    public static final String zhuanoncecoin_4 = "zhuanoncecoin_4";

    //新闻计时器金币领取
    public static final String newscoin_1 = "newscoin_1";

    //小说章节激励视频
    public static final String story_1 = "story_1";
    //小说计时器
    public static final String story_2 = "story_2";
    //小说计时器翻倍领取
    public static final String storycoin_2 = "storycoin_2";


    public static final String kan_coin = "kan_coin";
    public static final String index_weather = "index_weather";
    public static final String index_weatherout = "index_weatherout";
    public static final String index_mc = "index_mc";


    public static void uMengClick(Context context, String key) {
        //友盟
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("click", "click");//自定义参数：音乐类型，值：流行
            MobclickAgent.onEventObject(context, key, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //百度
        EventBus.getDefault().post(new StatServiceEvent(key));
        //热云


    }

}
