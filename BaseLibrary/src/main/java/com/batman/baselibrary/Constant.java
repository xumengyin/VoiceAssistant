package com.batman.baselibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Constant {


    public static final String VERSON_NAME = BuildConfig.VERSION_NAME;

    public static final int HANDLER_DELAY = 100;

    //计算在线时长
    public static final long TIME_IN_APP = 1 * 60 * 1000;

    //循环取当前时刻的步数中间的间隔时间
    public static final long TIME_INTERVAL_REFRESH = 15 * 1000;

    //随机金币 时间
    public static final int RANDOM_STEP_DELAY = 1000 * 60;

    public static final String CRASH_DEEP_ID = "4f268814c46f11839d836c19d00cb54c";

    public static final String CRASH_REPORT_ID = "61b62f0b70";

    public static final String UM_CHANNEL = "UMENG_CHANNEL";
    public static final String UM_KEY = "6062db83de41b946ab34e5b1";
    public static final String UM_PUSH_ID = "bd9ffea6b16a7e570265329933830572";

    public static final String XIAMI_ID = "";
    public static final String XIAMI_KEY = "";

    public static final String MEIZHU_ID = "";
    public static final String MEIZHU_KEY = "";

    public static final String OPPO_KEY = "";
    public static final String OPPO_SECRET = "";

    public static final String YOU_LIANG_HUI_ID = "";


    //deep场景值
    public static final String DEEP_SENSE_LOGIN = "login";
    public static final String DEEP_SENSE_TOPUP = "topUp";
    //热云
    public static final String RE_YUN_APP_KEY = "";
    public static final String RE_YUN_AD_NAME = "";
    public static final String RE_YUN_AD_YOU_LIANG_HUI = "";

    public static final String PLATFORM = "2";

    public static final String FILE_PATH_PROVIDER = "com.xinchu.walkstep.fileprovider";

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_NUM = 10;


    public static final String HEADER_NAME = "MAKE_STEP";
    public static final String HEADER_SP_AD = "HEADER_SP_AD" + ": ";
    public static final String HEADER_SP_AD_ = "HEADER_SP_AD";

    public static final String NEWS_BASE_URL = "http://tt.gmsx.gmw.cn";
    public static final String FICTION_BASE_URL = "http://app.yemaozi.vip/";

    public static Map<Integer, String[]> CONFIG = new HashMap<Integer, String[]>();

    //默认金币翻倍倍数
    public static final int DEFAULT_MULTIPLE = 1;
    public static final int DEFAULT_MULTIPLE_0 = 0;
    //默认 步数与金币汇率
    public static final int DEFAULT_STEP_MULTIPLE = 10;
    //重复点击时间控制
    public static final int WINDOW_DURATION = 1000;
    public static final TimeUnit WINDOW_DURATION_UNIT = TimeUnit.MILLISECONDS;

    public static final String CHANNEL = "";

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


    public static final Integer MYTEST = 999;

    public static int CONFIGS_ADDRESS = BuildConfig.enviromentAddress;


    static {

        String[] config = new String[20];

        config[0] = "https://api.xmzb.haojianli.com/";
        config[1] = "https://h5.xmzb.haojianli.com/app-h5/";
        config[2] = "https://xmzb.weatherat.com/#/?cityId=";
        config[3] = "https://h5.xmzb.haojianli.com/zz-game/index.html";


        CONFIG.put(RELEASE, config);

        config = new String[20];

        config[0] = "http://121.40.230.20/";
        config[1] = "http://121.40.230.20/app-h5/";
        config[2] = "https://xmzb.weatherat.com/#/?cityId=";
        config[3] = "http://h5.xmzb.tt1l.cn/zz-game/index.html";

        CONFIG.put(BETA, config);


        config = new String[20];

        config[0] = "http://api.xmzb.tt1l.cn/";
        config[1] = "http://h5.xmzb.tt1l.cn/app-h5/";
        config[2] = "https://xmzb.weatherat.com/#/?cityId=";
        config[3] = "http://h5.xmzb.tt1l.cn/zz-game/index.html";
        CONFIG.put(DEV, config);

        config = new String[20];

        config[0] = "http://api.xmzb.tt1l.cn/";
        config[1] = "http://h5.xmzb.tt1l.cn/app-h5/";
        config[2] = "https://xmzb.weatherat.com/#/?cityId=";
        config[3] = "http://h5.xmzb.tt1l.cn/zz-game/index.html";

        CONFIG.put(MYTEST, config);

    }

    public static String getConfig(int i, int index) {
        return CONFIG.get(i)[index];
    }


}
