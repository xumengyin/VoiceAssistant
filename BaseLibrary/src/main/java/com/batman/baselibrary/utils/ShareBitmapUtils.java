package com.batman.baselibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

import com.batman.baselibrary.R;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class ShareBitmapUtils {


    /**
     * 用具体 px
     *
     * @param context
     * @param resourceId
     * @param url
     * @param step
     * @param gold
     * @param code
     * @return
     */

    public static Bitmap drawBitmap(Context context, int resourceId, String url, String step, String gold, String code) {

        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        Paint mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(72);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);

        int bitParentWidth;

        int bitParentHeight;

        int bitWidth;
        int bitHeight;

        //底图
        BitmapFactory.Options options = new BitmapFactory.Options();
        TypedValue value = new TypedValue();
        context.getResources().openRawResource(resourceId, value);
        options.inTargetDensity = value.density;
        options.inScaled = false;//不缩放

        Bitmap parentBitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options).copy(Bitmap.Config.ARGB_8888, true);

        bitParentWidth = parentBitmap.getWidth();
        bitParentHeight = parentBitmap.getHeight();
//        ToastUtils.showLong("bitParentWidth " + bitParentWidth + "bitParentHeight " + bitParentHeight);
//        parentBitmap = Bitmap.createBitmap(bitParentWidth, bitParentHeight);
        Canvas canvas = new Canvas(parentBitmap);

        int top = 1494;
        int left = 315;

        canvas.drawText(step, left, top, mTextPaint);

        left = 669;
        canvas.drawText(gold, left, top, mTextPaint);

        mTextPaint.setTextSize(36);
        mTextPaint.setColor(context.getResources().getColor(R.color.ui_config_color_main));

        top = 1839;
        left = 592;

        canvas.drawText(code, left, top, mTextPaint);


        //launcher
//        Bitmap bitmap = QRUtils.Create2DCode(url, 188, 188);
        Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(url, 188);

        if (bitmap != null) {
            Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            bitWidth = bitmap.getWidth();
            bitHeight = bitmap.getHeight();

            //位置
            left = 228;
            top = 1695;
            int right = left + bitWidth;
            int bottom = top + bitHeight;
            Rect dst = new Rect(left, top, right, bottom);
            canvas.drawBitmap(bitmap, src, dst, mPaint);
        }


        return parentBitmap;

    }


    public static Bitmap drawGameBitmap(Context context, int resourceId, String url, String step, String gold) {

        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        Paint mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(72);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);

        int bitParentWidth;

        int bitParentHeight;

        int bitWidth;
        int bitHeight;

        BitmapFactory.Options options = new BitmapFactory.Options();
        TypedValue value = new TypedValue();
        context.getResources().openRawResource(resourceId, value);
        options.inTargetDensity = value.density;
        options.inScaled = false;//不缩放
        //底图
        Bitmap parentBitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options).copy(Bitmap.Config.ARGB_8888, true);

        bitParentWidth = parentBitmap.getWidth();
        bitParentHeight = parentBitmap.getHeight();
//        parentBitmap = Bitmap.createBitmap(bitParentWidth, bitParentHeight);
        Canvas canvas = new Canvas(parentBitmap);

        int top = 1830;
        int left = 315;

        canvas.drawText(step, left, top, mTextPaint);

        left = 678;
        canvas.drawText(gold, left, top, mTextPaint);

        mTextPaint.setTextSize(36);
        mTextPaint.setColor(context.getResources().getColor(R.color.ui_config_color_main));


        //launcher
        Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(url, 188);

        if (bitmap != null) {
            Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            bitWidth = bitmap.getWidth();
            bitHeight = bitmap.getHeight();

            //位置
            left = 810;
            top = 1488;
            int right = left + bitWidth;
            int bottom = top + bitHeight;
            Rect dst = new Rect(left, top, right, bottom);
            canvas.drawBitmap(bitmap, src, dst, mPaint);
        }


        return parentBitmap;

    }
}
