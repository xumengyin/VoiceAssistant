package com.batman.baselibrary.manage;

import android.text.TextUtils;

import com.batman.baselibrary.preference.UserPres;
import com.batman.baselibrary.utils.DecimalUtil;


/**
 * Synopsis     余额管理
 */
public class BalanceManager {
    /**
     * 收入
     */
    public static void revenue(String money) {
        if (!TextUtils.isEmpty(money)) {
            UserPres.save(UserPres.KEY_GOLD, DecimalUtil.add(TextUtils.isEmpty(money) ? "0" : money, TextUtils.isEmpty(UserPres.getInstance().mGold) ? "0" : UserPres.getInstance().mGold));
        }
    }

    /**
     * 支出
     */
    public static void pay(String money) {
        if (!TextUtils.isEmpty(money)) {
            UserPres.save(UserPres.KEY_GOLD, DecimalUtil.subtract(TextUtils.isEmpty(UserPres.getInstance().mGold) ? "0" : UserPres.getInstance().mGold, TextUtils.isEmpty(money) ? "0" : money));
        }
    }

    /**
     * 刷新
     */
    public static void refresh(String money) {
        if (!TextUtils.isEmpty(money)) {
            UserPres.save(UserPres.KEY_GOLD, money);
        }
    }
}
