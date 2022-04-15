package com.batman.baselibrary.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 首页4个金币领取
 */
@Entity
public class TaskEntity {

    @Ignore
    public static final int IS_COMPLETE = 1;
    @Ignore
    public static final int IS_UN_COMPLETE = 0;

    @Ignore
    public static final int DEFAULT_NONE_GOLD = 0;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_name")
    public String name;

    //最大次数/进步数  或者最大 步数
    @ColumnInfo(name = "max_receive_count")
    public int maxReceiveCount;

    @ColumnInfo(name = "today_complete")
    public int todayComplete;

    //完成时更新  上一次金币数 只有随机和唤醒，没有走路
    @ColumnInfo(name = "last_receive_gold")
    public int lastReceiveGold;

    //当前次数或者当前步数
    @ColumnInfo(name = "current_count")
    public int currentCount;

    //分配好的金币
    @ColumnInfo(name = "today_random_gold")
    public String todayRandomGold;

    @ColumnInfo(name = "multiple")
    public int multiple;

    //当天时间，用来计算 唤醒 和 随机
    @ColumnInfo(name = "time")
    public String time;

    //随机金币的时间
    @ColumnInfo(name = "current_min")
    public int currentMin;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "date")
    public String date;


}
