package com.batman.baselibrary.utils.update;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batman.baselibrary.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;


/**
 * Created by Administrator on 2016/5/23.
 */
public class DownLoadDialog extends Dialog {


    public QMUIRoundButton btnVersion;
    public TextView tvContent;
    public Button buttonOK;
    public ImageView buttonCancel;


    public DownLoadDialog(Context context) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    public DownLoadDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    private void init(Context context) {
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_download, null);
        btnVersion = (QMUIRoundButton) view.findViewById(R.id.btn_version);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        buttonOK = (Button) view.findViewById(R.id.btn_ok);
        buttonCancel = (ImageView) view.findViewById(R.id.img_cancel);
        setContentView(view);
    }

}
