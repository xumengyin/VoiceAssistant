package com.batman.baselibrary.ui.adapter;

import android.graphics.Color;
import android.text.TextUtils;

import com.batman.baselibrary.R;
import com.batman.baselibrary.data.result.SignDetailResult.SignInDetail;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DialogSignAdapter extends BaseQuickAdapter<SignInDetail, BaseViewHolder> {


    public DialogSignAdapter(List<SignInDetail> data) {
        super(R.layout.item_dialog_sign, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignInDetail result) {
        if (result != null) {


            int today_serial_number = result.today_serial_number - 1;
            int position = helper.getAdapterPosition();
//            //横线
//            if (position == 6) {
//                helper.setGone(R.id.view, false);
//                helper.getView(R.id.ll).getLayoutParams().width = UIDisplayHelper.dpToPx(34);
//                helper.getView(R.id.ll).invalidate();
//            } else {
//                helper.setGone(R.id.view, true);
//                helper.getView(R.id.ll).getLayoutParams().width = UIDisplayHelper.dpToPx(48);
//                helper.getView(R.id.ll).invalidate();
//            }
//            //提示
            if (position == 6) {
                helper.setVisible(R.id.tv_warn, true);
                helper.setText(R.id.tv_warn, "神秘礼盒");
            } else {
                if (today_serial_number == position) {
                    helper.setVisible(R.id.tv_warn, true);
                    helper.setText(R.id.tv_warn, "+" + result.basisGold + "金币");
                } else {
                    helper.setVisible(R.id.tv_warn, false);
                }
            }
            //金币样式
            if (position == 6) {
                helper.setBackgroundRes(R.id.tv_gold, R.drawable.ic_task_gift);

                if (!TextUtils.isEmpty(result.isSignIn) && result.isSignIn.equals("2")) {

                    if (!TextUtils.isEmpty(result.isDouble) && result.isDouble.equals("2")) {
                        //已翻倍
                        helper.setText(R.id.tv_status, "已领");
                        helper.setTextColor(R.id.tv_status, Color.parseColor("#969799"));

                    } else {
                        //未翻倍
                        if (today_serial_number == position) {
                            helper.setText(R.id.tv_status, "双倍奖励");
                            helper.setTextColor(R.id.tv_status, Color.parseColor("#F52631"));
                        } else {
                            helper.setText(R.id.tv_status, position + 1 + "天");
                            helper.setTextColor(R.id.tv_status, Color.parseColor("#181818"));
                        }


                    }


                } else {
                    helper.setText(R.id.tv_status, position + 1 + "天");
                    helper.setTextColor(R.id.tv_status, Color.parseColor("#181818"));
                }
            } else {
                if (!TextUtils.isEmpty(result.isSignIn) && result.isSignIn.equals("2")) {

                    if (!TextUtils.isEmpty(result.isDouble) && result.isDouble.equals("2")) {
                        //已翻倍

                        helper.setBackgroundRes(R.id.tv_gold, R.drawable.ic_sign_gold_sign);
                        helper.setText(R.id.tv_status, "已领");
                        helper.setTextColor(R.id.tv_status, Color.parseColor("#969799"));
                        helper.setText(R.id.tv_gold, result.basisGold);

                    } else {
                        //未翻倍
                        if (today_serial_number == position) {
                            helper.setBackgroundRes(R.id.tv_gold, R.drawable.ic_sign_video);
                            helper.setText(R.id.tv_status, "双倍奖励");
                            helper.setTextColor(R.id.tv_status, Color.parseColor("#F52631"));
                            helper.setText(R.id.tv_gold, "");
                        } else {
                            helper.setBackgroundRes(R.id.tv_gold, R.drawable.ic_sign_gold_sign);
                            helper.setText(R.id.tv_status, position + 1 + "天");
                            helper.setTextColor(R.id.tv_status, Color.parseColor("#181818"));
                            helper.setText(R.id.tv_gold, result.basisGold);
                        }
                    }


                } else {
                    helper.setBackgroundRes(R.id.tv_gold, R.drawable.ic_sign_gold_sign);
                    helper.setText(R.id.tv_status, position + 1 + "天");
                    helper.setTextColor(R.id.tv_status, Color.parseColor("#181818"));
                    helper.setText(R.id.tv_gold, result.basisGold);
                }
            }


        }

    }
}
