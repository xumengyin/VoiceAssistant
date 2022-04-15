package com.batman.baselibrary.data.request;

public class UploadHomeGoldRequest {


    //答题
    public static final String TYPE_2 = "2";
    //看看视频
    public static final String TYPE_3 = "3";
    //唤醒随机金币
    public static final String TYPE_6 = "6";
    //随机赠送金币
    public static final String TYPE_7 = "7";
    //兑换步数金币
    public static final String TYPE_8 = "8";
    //任务
    public static final String TYPE_9 = "9";

    public static final String TYPE_21 = "21";
    public static final String TYPE_22 = "22";
    public String gold;
    public String type;
    public String stepExchange;
    public String version;
    public String reportId;
}
