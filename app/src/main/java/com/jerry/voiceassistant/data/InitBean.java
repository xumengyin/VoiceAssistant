package com.jerry.voiceassistant.data;

import java.util.List;

public class InitBean {

    public SysConfig sysConfig;
    public List<SysCodeDict> sysCodeDictList;


    static class SysConfig
    {
        public String VecIconDomain;
    }
    static class SysCodeDict
    {
        public String catatoryId;
    }
}
