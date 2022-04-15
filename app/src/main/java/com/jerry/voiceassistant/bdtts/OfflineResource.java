package com.jerry.voiceassistant.bdtts;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.res.AssetManager;


import com.jerry.voiceassistant.utils.Logs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by fujiayi on 2017/5/19.
 */

public class OfflineResource implements IOfflineResourceConst
{

    private AssetManager assets;
    private String destPath;

    private String textFilename;
    private String modelFilename;

    private static HashMap<String, Boolean> mapInitied = new HashMap<String, Boolean>();

    public OfflineResource(Context context, String voiceType) throws IOException {
        context = context.getApplicationContext();
        this.assets = context.getApplicationContext().getAssets();
        this.destPath = TtsFileUtil.createTmpDir(context);
        setOfflineVoiceType(voiceType);
    }

    public String getModelFilename() {
        return modelFilename;
    }

    public String getTextFilename() {
        return textFilename;
    }

    public void setOfflineVoiceType(String voiceType) throws IOException {
        String text = TEXT_MODEL;
        String model;
        if (VOICE_MALE.equals(voiceType)) {
            model = VOICE_MALE_MODEL;
        } else if (VOICE_FEMALE.equals(voiceType)) {
            model = VOICE_FEMALE_MODEL;
        } else if (VOICE_DUXY.equals(voiceType)) {
            model = VOICE_DUXY_MODEL;
        } else if (VOICE_DUYY.equals(voiceType)) {
            model = VOICE_DUYY_MODEL;
        } else {
            throw new RuntimeException("voice type is not in list");
        }
        textFilename = copyAssetsFile(text);
        modelFilename = copyAssetsFile(model);
    }


    private String copyAssetsFile(String sourceFilename) throws IOException {
        String destFilename = destPath + "/" + sourceFilename;
        boolean recover = false;
        Boolean existed = mapInitied.get(sourceFilename); // 启动时完全覆盖一次
        if (existed == null || !existed) {
            recover = true;
        }
        File file =new File(destFilename);
        if(file.exists())
        {
            Logs.i(TAG, "文件已存在：" + destFilename);
        }else
        {
            TtsFileUtil.copyFromAssets(assets, sourceFilename, destFilename, recover);
            Logs.i(TAG, "文件复制成功：" + destFilename);
        }


        return destFilename;
    }


}
