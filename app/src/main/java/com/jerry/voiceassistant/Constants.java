package com.jerry.voiceassistant;

import android.text.TextUtils;


import com.jerry.voiceassistant.utils.Logs;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Map<Integer, String[]> CONFIG = new HashMap<Integer, String[]>();
    /**
     * 开发环境
     */
    public static final Integer DEV = 0;
    /**
     * 测试环境
     */
    public static final Integer BETA = 1;
    /**
     * 运营环境
     */
    public static final Integer RELEASE = 2;
    /**
     * 仿真环境
     */
    public static final Integer EMULATE = 3;
    //要配置的key
    public static final String WX_KEY="wxc7fba27aa071c9c4";
    public static final String WX_SECRET="f60e55218f2ae52278e9f1e819da2ffd";
    public static final String UMENG_KEY="4ffd162f527015049d00004d";
    public static final String UMENG_SECRET="e3ed814adcca3846a2afe46ad572060f";
    public static final String BUGLY_APP_ID="80a0bd6a1e";
    public static final String XIAOMI_ID = "2882303761517136111";
    public static final String XIAOMI_KEY = "5721713615111";
    public static final String QQ_ID = "1105795812";
    public static final String QQ_KEY = "okSUQRC7YUwtE8aO";
    public static final String MINI_PROGRAM_ZSC = "gh_bc53c5ffdfde"; //小程序掌上车
    public static final String MINI_PROGRAM_SC = "gh_a9a1b6c67be1"; //小程序商城
    //百度相关的key
    public static String baiDuAppId = "11794182";
    public static String baiDuApiKey = "smK4CAW6t0qv8b87WZE4vtEn";
    public static String baiDuSecretKey = "9wbs1hYNySh5xkMprZ1AeSOflxmiNlZU";
    //配置的key结束

    public static final String MAPTYPE = "google";
    public static final String APPNAME = BuildConfig.appName;
    public static final String DEMONAME = "demo";
    public static int OnePageNum = 20;
    public static int PageNum = 20;
    public static String CompanyPhone = "4000666733";
    public static final int DEFAULT_ZOOM = 17;
    /**
     * 车友互动聊天数据库，更换包名需要同步 AndroidManifest.xml 更改
     */
    public static String PROVIDE_CHAT = BuildConfig.APPLICATION_ID+".professional.provider.Chats";
    public static String PROVIDE_ROSTER = BuildConfig.APPLICATION_ID+".professional.provider.Roster";

    public static final String version = BuildConfig.VERSION_NAME;
    public static final String productVersion =  BuildConfig.VERSION_NAME;// 产品版本号

    public static final int loginuserVersion = 201;
    public static final int messagedb_version = 200;

    public static final String pinganpecc = "http://www.pingan.com/ebusiness/auto/mobile/upingan/peccancyIndex.html";
    public static final String RESCUEAPPID = "P0005447@1060";
    public static final String RESCUEAPPSECRET = "1060@app321";
    //周边服务入口 乐车邦add 2018/07/30
    public static final String AROUND_SERVICE = "https://m.lechebang.com/duijie/h5/index?utm_source=2222_2223&utm_campaign=1848";
    public static final String UBI_BIND_PAGE = "/vsp/app/module/view/buyInsuranceNoDevice.html";


    //图片剪裁 宽高比例 若是2.29:1 ==>229：100
    public static int clipViewWeight = 229;
    public static int clipViewHeight = 100;

    public static final String PRIVACY = "http://www.cpsdna.com/Privacy-cxz-huawei.html";
    public static final String USER_RULE = "file:///android_asset/UserAgreement.html";

    public static String FILE_PROVIDER_AUTH = BuildConfig.APPLICATION_ID+".fileprovider";
    public static final String OFFSICIAL_WEBSITE="http://www.cpsdna.com/";
    public static final String OFFSICIAL_PHONE="4000666733";
    static {
        /** 日志打印 配置 */
        Logs.mLogout = BuildConfig.LOG_DEBUG;
        /** 日志打印 配置 */

        String[] config = new String[10];

        config[0] = "https://iov.cpsdna.com/";
        CONFIG.put(RELEASE, config);

        config = new String[10];
        config[0] = "https://test.cpsdna.com/";
        CONFIG.put(BETA, config);

        config = new String[10];
        config[0] = "http://dev.cpsdna.org/micloud";

        CONFIG.put(DEV, config);

        config = new String[10];
        config[0] = "http://58.215.166.6:19080/saasapi";
        CONFIG.put(EMULATE, config);
    }

    public static String getConfig(int i, int index) {
        return CONFIG.get(i)[index];
    }

    public static final String INTENT_KEY="INTENT_KEY";
}
