package com.batman.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.batman.baselibrary.R;


/**
 * Created by Administrator on 2016/5/23.
 */
public class CustomDialog extends Dialog {


    public TextView tvTitle;

    public Button buttonOK;
    public Button buttonCancel;


    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    private void init(Context context) {
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_custom, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_tile);

        buttonCancel = (Button) view.findViewById(R.id.btn_cancel);
        buttonOK = (Button) view.findViewById(R.id.btn_ok);
        setContentView(view);
    }



}
