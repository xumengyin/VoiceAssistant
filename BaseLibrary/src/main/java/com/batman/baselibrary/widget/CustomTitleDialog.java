package com.batman.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.batman.baselibrary.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUIWrapContentScrollView;
import com.qmuiteam.qmui.widget.textview.QMUISpanTouchFixTextView;


public class CustomTitleDialog extends Dialog {


    public TextView subTvTitle;

    public Button buttonOK;
    public Button buttonCancel;

    private QMUIWrapContentScrollView mScrollContainer;
    public QMUISpanTouchFixTextView mTextView;

    private int mContentAreaMaxHeight = -1;

    private Context mContext;

    public CustomTitleDialog(Context context) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    public CustomTitleDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    private void init(Context context) {

        mContext = context;
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_custom_title, null);

        mTextView = new QMUISpanTouchFixTextView(context);
        assignMessageTvWithAttr(mTextView, true, R.attr.qmui_dialog_message_content_style);
//        mTextView.setText(mMessage);
        mTextView.setMovementMethodDefault();


        mScrollContainer = view.findViewById(R.id.ui_con);
        mScrollContainer.setMaxHeight(getContentAreaMaxHeight());
        mScrollContainer.setVerticalScrollBarEnabled(false);
        mScrollContainer.addView(mTextView);


        subTvTitle = (TextView) view.findViewById(R.id.tv_sub_tile);
        buttonCancel = (Button) view.findViewById(R.id.btn_cancel);
        buttonOK = (Button) view.findViewById(R.id.btn_ok);
        setContentView(view);
    }


    protected int getContentAreaMaxHeight() {
        if (mContentAreaMaxHeight == -1) {
            // 屏幕高度的0.85 - 预估的 title 和 action 高度
            return (int) (QMUIDisplayHelper.getScreenHeight(mContext) * 0.85) - QMUIDisplayHelper.dp2px(mContext, 100);
        }
        return mContentAreaMaxHeight;
    }

    public static void assignMessageTvWithAttr(TextView messageTv, boolean hasTitle, int defAttr) {
        QMUIResHelper.assignTextViewWithAttr(messageTv, defAttr);

        if (!hasTitle) {
            TypedArray a = messageTv.getContext().obtainStyledAttributes(null,
                    R.styleable.QMUIDialogMessageTvCustomDef, defAttr, 0);
            int count = a.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.QMUIDialogMessageTvCustomDef_qmui_paddingTopWhenNotTitle) {
                    messageTv.setPadding(
                            messageTv.getPaddingLeft(),
                            a.getDimensionPixelSize(attr, messageTv.getPaddingTop()),
                            messageTv.getPaddingRight(),
                            messageTv.getPaddingBottom()
                    );
                }
            }
            a.recycle();
        }
    }
}
