package com.batman.baselibrary.data.result;

import java.util.List;

public class SignDetailResult {


    /**
     *
     * basis_gold基础金币
     * multiple倍数
     * is_sign_in是否签到（1:未签到，2：已签到）
     * sign_in_Time签到时间
     * is_double是否翻倍（1:未翻倍，2：已翻倍）
     * double_time翻倍时间
     * sign_in_day连续签到天数
     *
     *
     * today_serial_number : 1
     * signInDetail : [{"id":null,"basisGold":"80","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"},{"id":null,"basisGold":"100","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"},{"id":null,"basisGold":"120","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"},{"id":null,"basisGold":"140","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"},{"id":null,"basisGold":"160","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"},{"id":null,"basisGold":"180","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"},{"id":null,"basisGold":"300","multiple":"2","isSignIn":"1","signInTime":null,"isDouble":"1","doubleTime":null,"signInDay":"0"}]
     */

    public int today_serial_number;
    public boolean today_is_signIn;
    public List<SignInDetail> signInDetail;

    public static class SignInDetail {
        /**
         * id : null
         * basisGold : 80
         * multiple : 2
         * isSignIn : 1
         * signInTime : null
         * isDouble : 1
         * doubleTime : null
         * signInDay : 0
         */

        public String id;
        public String basisGold;
        public String multiple;
        public String isSignIn;
        public String signInTime;
        public String isDouble;
        public String doubleTime;
        public String signInDay;

        public int today_serial_number;
        public boolean today_is_signIn;
    }
}
