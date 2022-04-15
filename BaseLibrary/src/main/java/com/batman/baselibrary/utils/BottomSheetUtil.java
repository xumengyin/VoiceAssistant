package com.batman.baselibrary.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.batman.baselibrary.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSheetUtil {

    public TextView tvTitle;
    public TextView tvDetail;
    public RecyclerView rv;

    private Context mContext;

    public BottomSheetUtil(Context context) {
        this.mContext = context;
    }

    public BottomSheetDialog showBottomSheet() {

        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
        View cancelView = LayoutInflater.from(mContext).inflate(R.layout.view_custom_bottom_sheet, null);
        TextView imgCancel = (TextView) cancelView.findViewById(R.id.tv_cancel);
        tvTitle = (TextView) cancelView.findViewById(R.id.tv_title);
        tvDetail = (TextView) cancelView.findViewById(R.id.tv_detail);
        rv = (RecyclerView) cancelView.findViewById(R.id.rv);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setContentView(cancelView);
        View view1 = mBottomSheetDialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        view1.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view1);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        return mBottomSheetDialog;
    }


}
