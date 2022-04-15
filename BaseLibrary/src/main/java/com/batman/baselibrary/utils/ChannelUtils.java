package com.batman.baselibrary.utils;

import com.batman.baselibrary.BuildConfig;
import com.batman.baselibrary.Constant;
import com.batman.utils.MetaDataUtils;

public class ChannelUtils {

    public static String getChannel() {
        if (BuildConfig.DEBUG) {

            return "vivostore";
//        return "oppostore";
//            return  "HUAWEIstore";
//        return "yingyongbaostore";
//            return "yingyongbaostore";
//            return "bytedance";
        } else {
            return MetaDataUtils.getMetaDataInApp(Constant.UM_CHANNEL);
        }

    }

}
