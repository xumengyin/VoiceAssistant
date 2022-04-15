package com.batman.baselibrary.utils;

import com.batman.baselibrary.R;
import com.batman.baselibrary.manage.WalkManage;

import java.text.ParseException;

public class TimeUtils {


    /**
     * 是否是夜晚
     *
     * @return
     */
    public static boolean isNigth() {
        boolean isNight = false;
        String currentTime = com.batman.utils.TimeUtils.getNowString(WalkManage.simpleDateFormat_2);
        String currentDay = com.batman.utils.TimeUtils.getNowString(WalkManage.simpleDateFormat_1);
        String day1 = currentDay + " 00:00:00";
        String day2 = currentDay + " 06:00:00";
        String day3 = currentDay + " 19:00:00";
        String day4 = currentDay + " 23:59:59";
        try {
            long longTime = WalkManage.simpleDateFormat_2.parse(currentTime).getTime();
            long longDay1 = WalkManage.simpleDateFormat_2.parse(day1).getTime();
            long longDay2 = WalkManage.simpleDateFormat_2.parse(day2).getTime();
            long longDay3 = WalkManage.simpleDateFormat_2.parse(day3).getTime();
            long longDay4 = WalkManage.simpleDateFormat_2.parse(day4).getTime();
            if ((longTime - longDay2) > 0 && (longTime - longDay3) < 0) {
                isNight = false;
            } else {
                isNight = true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isNight;
    }

}
