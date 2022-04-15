package com.batman.baselibrary.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.batman.baselibrary.R;
import com.batman.baselibrary.RouterConstants;
import com.batman.baselibrary.utils.RouteActivityUtils;
import com.umeng.message.UmengNotifyClickActivity;

import org.android.agoo.common.AgooConstants;

public class MipushTestActivity extends UmengNotifyClickActivity {
    private static String TAG = MipushTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mipush);
    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);
        final String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
//        ToastUtils.showLong(body);
        if (!TextUtils.isEmpty(body)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RouteActivityUtils.openActivity(MipushTestActivity.this, RouterConstants.MAIN_COMPONENT_MAIN_PATH);
                    finish();
                }
            });
        }
    }
}
