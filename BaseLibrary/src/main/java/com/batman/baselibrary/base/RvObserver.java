package com.batman.baselibrary.base;

import android.content.Context;
import android.view.View;

import com.batman.baselibrary.widget.MyUIEmptyView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.network.Exception.NetworkException;
import com.network.Resource;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

public abstract class RvObserver<T> extends BaseObserver<T> {

    private BaseQuickAdapter baseQuickAdapter;

    private Context mContext;

    private MyUIEmptyView mUiEmptyView;

    public RvObserver(Context context, BaseQuickAdapter quickAdapter) {
        super(context);
        this.mContext = context;
        this.baseQuickAdapter = quickAdapter;
        mUiEmptyView = new MyUIEmptyView(mContext);
//        View emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
        baseQuickAdapter.setEmptyView(mUiEmptyView);
        mUiEmptyView.hide();
    }

    public RvObserver(Context context, BaseQuickAdapter quickAdapter, boolean isShow) {
        super(context, isShow);
        this.mContext = context;
        this.baseQuickAdapter = quickAdapter;

        this.baseQuickAdapter = quickAdapter;
        mUiEmptyView = new MyUIEmptyView(mContext);
//        View emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
        baseQuickAdapter.setEmptyView(mUiEmptyView);
        mUiEmptyView.hide();
    }


    @Override
    public void uiError(Resource<T> tResource) {

        if (tResource.errorCode.equals(NetworkException.NO_NETWORK)) {
            if (mUiEmptyView != null) {
                mUiEmptyView.showNetError(tResource.message, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uiRefresh(view);
                    }
                });
            }
        } else {
            super.uiError(tResource);
        }
    }

    @Override
    public void uiComplete(Resource<T> tResource) {
        if (baseQuickAdapter.getData() == null || baseQuickAdapter.getData().size() == 0) {
            mUiEmptyView.showEmptyData("空");
        }
    }

    public void uiRefresh(View view) {

    }

}
