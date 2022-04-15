package com.batman.baselibrary.manage;

import java.text.SimpleDateFormat;


public class WalkManage {

    public static SimpleDateFormat simpleDateFormat_1 = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat simpleDateFormat_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat simpleDateFormat_3 = new SimpleDateFormat("HH:mm:ss");

    public static final String KEY_CEILING = "ceiling";
    public static final String KEY_RANDOM = "random";

    public static final String KEY_EVENT_WALK = "KEY_EVENT_WALK";
    public static final String KEY_EVENT_WAKE = "KEY_EVENT_WAKE";
    public static final String KEY_EVENT_RANDOM = "KEY_EVENT_RANDOM";


    //计步
    public static final int WALK_LEVE_1 = 550;
    public static final int WALK_LEVE_2 = 1500;

    //唤醒
    public static final int WAKE_TIME = 30 * 60 * 1000;//秒
    public static final int WAKE_MAX_CONNT = 5;

    //随机基础金币
    public static final int RANDOM_TIME = 30;//分钟
    public static final int RANDOM_MAX_CONNT = 5;



}
