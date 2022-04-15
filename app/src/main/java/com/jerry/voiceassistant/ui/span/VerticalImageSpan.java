package com.jerry.voiceassistant.ui.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import com.jerry.voiceassistant.utils.Logs;

public class VerticalImageSpan extends ImageSpan {

    private Drawable drawable;
    public VerticalImageSpan(Drawable drawable) {
        super(drawable);
        this.drawable=drawable;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
        Drawable drawable = getDrawable();
        if(drawable==null){
            drawable= this.drawable;
        }
        Rect rect = drawable.getBounds();
        Logs.d("aaaa","getSize"+rect.toString());
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            Logs.d("aaaa","FontMetricsInt:"+fmPaint.toString());
            int fontHeight = fmPaint.bottom - fmPaint.top;
            int drHeight = rect.bottom - rect.top;

            int top = drHeight / 2 - fontHeight / 4;
            int bottom = drHeight / 2 + fontHeight / 4;

//            fontMetricsInt.ascent = -64;
//            fontMetricsInt.top = -64;
//            fontMetricsInt.bottom = 0;
//            fontMetricsInt.descent = 0;
            fontMetricsInt.ascent = -bottom;
            fontMetricsInt.top = -bottom;
            fontMetricsInt.bottom = top;
            fontMetricsInt.descent = top;
        }
        return rect.right;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        Logs.d("aaaa","draw:"+drawable.getBounds());
        Logs.d("aaaa","bottom:"+bottom+"top:"+top);
        //bottom top 返回此时该画canvas坐标
        int transY = ((bottom - top) - drawable.getBounds().bottom) / 2 + top;
        Logs.d("aaaa","transY:"+transY+"y:"+y);
        canvas.translate(x, transY);
       // canvas.translate(x, 0);
        drawable.draw(canvas);
        canvas.restore();
    }
}
