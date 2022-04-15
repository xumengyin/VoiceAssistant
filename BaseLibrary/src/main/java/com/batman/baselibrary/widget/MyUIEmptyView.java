package com.batman.baselibrary.widget;

/*
 * Tencent is pleased to support the open source community by making UI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.widget.QMUIEmptyView;


/**
 * 用于显示界面的 loading、错误信息提示等状态。
 * <p>
 * 提供了一个 LoadingView、一行标题、一行说明文字、一个按钮, 可以使用 {@link #show(boolean, String, String, String, OnClickListener)} 系列方法控制这些控件的显示内容
 * </p>
 */
public class MyUIEmptyView extends QMUIEmptyView {



    public MyUIEmptyView(Context context) {
        this(context, null);
    }

    public MyUIEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyUIEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
    }
    public void showNetError(String detailText, OnClickListener onButtonClickListener) {
        setLoadingShowing(false);
        setTitleText(null);
        setDetailText(detailText);
      //  setImage(R.drawable.ic_network_error);
        setButton("点我刷新", onButtonClickListener);
        show();
    }
    public void showEmptyData(String detailText) {
        setLoadingShowing(false);
        setTitleText(null);
        setDetailText(detailText);
       // setImage(R.drawable.ic_no_data);
        setButton(null, null);
        show();
    }
//    public void setImage(int resId) {
//        mImageView.setImageResource(resId);
//    }
}
