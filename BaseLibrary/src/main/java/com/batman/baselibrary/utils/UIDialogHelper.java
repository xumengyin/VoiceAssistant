package com.batman.baselibrary.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;

import com.batman.baselibrary.widget.CustomDialog;
import com.batman.baselibrary.widget.CustomOneDialog;
import com.batman.baselibrary.widget.CustomTitleDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.util.List;

public class UIDialogHelper {


    public interface ActionListener {
        void onClick(Dialog dialog, int index);
    }


    public static void showCommonDialog(Context context, String title, String message,
                                        String negativeText, ActionListener negativeListener,
                                        String positiveText, ActionListener positiveListener) {

        CustomDialog dialog = new CustomDialog(context);
        dialog.tvTitle.setText(message);
        dialog.buttonCancel.setText(negativeText);
        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                negativeListener.onClick(dialog, 0);
            }
        });
        dialog.buttonOK.setText(positiveText);
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveListener.onClick(dialog, 1);
            }
        });
        dialog.show();
    }

    public static void showCommonDialog(Context context, String title, int color, SpannableStringBuilder message,
                                        String negativeText, ActionListener negativeListener,
                                        String positiveText, ActionListener positiveListener) {

        CustomTitleDialog dialog = new CustomTitleDialog(context);

        dialog.subTvTitle.setText(title);

        dialog.mTextView.setGravity(Gravity.START);
        dialog.mTextView.setHighlightColor(color);
        dialog.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        dialog.mTextView.setText(message);


        dialog.buttonCancel.setText(negativeText);
        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                negativeListener.onClick(dialog, 0);
            }
        });
        dialog.buttonOK.setText(positiveText);
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveListener.onClick(dialog, 1);
            }
        });
        dialog.show();
    }


    public static CustomDialog showCommonDialog(Context context, String message,
                                        ActionListener negativeListener,
                                        ActionListener positiveListener) {

        CustomDialog dialog = new CustomDialog(context);
        dialog.tvTitle.setText(message);
        dialog.buttonCancel.setText("取消");
        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                negativeListener.onClick(dialog, 0);
            }
        });
        dialog.buttonOK.setText("确定");
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveListener.onClick(dialog, 1);
            }
        });
        dialog.show();
        return dialog;
    }


    public static Dialog showCommonDialog(Context context, String title, String message, boolean isForse,
                                          ActionListener negativeListener,
                                          ActionListener positiveListener) {

        CustomOneDialog dialog = new CustomOneDialog(context);
        dialog.tvTitle.setText(message);
        dialog.subTvTitle.setText(title);
        dialog.subTvTitle.setVisibility(View.VISIBLE);
        if (isForse) {
            dialog.buttonCancel.setVisibility(View.GONE);
        } else {
            dialog.buttonCancel.setVisibility(View.VISIBLE);
        }
        dialog.buttonCancel.setText("取消");
        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                negativeListener.onClick(dialog, 0);
            }
        });
        dialog.buttonOK.setText("确定");
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveListener.onClick(dialog, 1);
            }
        });
        dialog.setCancelable(false);
        return dialog;
    }

    public static void showCommonDialog(Context context, String message,
                                        String positiveText,

                                        ActionListener positiveListener) {

        CustomDialog dialog = new CustomDialog(context);
        dialog.tvTitle.setText(message);
        dialog.buttonCancel.setText("取消");
        dialog.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.buttonOK.setText(positiveText);
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveListener.onClick(dialog, 1);
            }
        });
        dialog.show();
    }

    public static void showCommonDialog(Context context, String message,
                                        String positiveText,

                                        ActionListener positiveListener, boolean isvisible) {

        CustomOneDialog dialog = new CustomOneDialog(context);
        dialog.tvTitle.setText(message);

        dialog.buttonOK.setText(positiveText);
        dialog.buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveListener.onClick(dialog, 1);
            }
        });
        dialog.show();
    }

    /**
     * ListBottomSheet
     *
     * @param context
     * @param items
     * @param listener
     */

    public static void showListBottomSheet(Context context, List<String> items, QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener listener) {
        QMUIBottomSheet.BottomListSheetBuilder bottomListSheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(context);
        for (String item :
                items) {
            bottomListSheetBuilder
                    .addItem(item);
        }
        bottomListSheetBuilder
                .setOnSheetItemClickListener(listener)
                .build()
                .show();
    }


}
