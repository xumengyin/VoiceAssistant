package com.batman.baselibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.batman.baselibrary.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * 页面加载提示框
 */
public class LoadingDialog extends Dialog {

    ImageView mImgLoading;

    private Context mContext;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.CustomDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setCanceledOnTouchOutside(false);


        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);
        setContentView(view);
        mImgLoading=view.findViewById(R.id.img_loading);

        //去掉动画
        Window window = getWindow();
        window.setWindowAnimations(R.style.NoAnimationDialog); // 添加动画

        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = 1f;
        ((Activity) mContext).getWindow().setAttributes(lp);

        WindowManager.LayoutParams params = getWindow().getAttributes();

        int screenWidth = QMUIDisplayHelper.getScreenWidth(getContext());
        int screenHeight = QMUIDisplayHelper.getScreenHeight(getContext());
        params.width = screenWidth;
        params.height = screenHeight;
        getWindow().setAttributes(params);

        Glide.with(mContext).asGif().load(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(mImgLoading);

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mContext != null) {
            WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
            lp.alpha = 1f;
            ((Activity) mContext).getWindow().setAttributes(lp);
        }
    }
}
