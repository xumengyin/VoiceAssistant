package com.jerry.voiceassistant.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jerry.voiceassistant.BuildConfig;
import com.jerry.voiceassistant.Constants;
import com.jerry.voiceassistant.R;
import com.jerry.voiceassistant.ui.activity.ServiceWebActivity;


public class PrivacyDialog extends Dialog implements View.OnClickListener
{

	CheckBox vCheck; ;
	View vCancel,vSure;
	TextView vPricacy1,vPricacy2;
	DialogCallback callback;
	public PrivacyDialog(Context context)
	{
		super(context, R.style.Dialog);
		setContentView(R.layout.privacy_dialog_layout);
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		vCancel=findViewById(R.id.cancel);
		vSure=findViewById(R.id.sure);
		vPricacy1=findViewById(R.id.privacy1);
		vPricacy2=findViewById(R.id.privacy2);
		vCancel.setOnClickListener(this);
		vSure.setOnClickListener(this);
		initPrivacy();
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();
		WindowManager.LayoutParams p = getWindow().getAttributes();
		p.width = (int) (d.getWidth() * 0.8);
		//p.height = (int) (d.getHeight() * 0.8);
		getWindow().setAttributes(p);

	}

	private void initPrivacy()
	{
		//getContext().getpac
		vPricacy1.setText(String.format(getContext().getString(R.string.privacy_tip), BuildConfig.appTitle));
		SpannableStringBuilder SpannableString = new SpannableStringBuilder(getContext().getString(R.string.privacy_tip2));
		SpannableString.setSpan(new ClickableSpan() {
			@Override
			public void onClick(View widget) {
//				Intent intent = new Intent(QuickRegisterActivity.this,
//						HelpServiceActivity.class);
//				intent.putExtra("requested_page_key", "0");
//				startActivity(intent);
				ServiceWebActivity.start(getContext(), Constants.USER_RULE,"用户协议");
			}

			@Override
			public void updateDrawState(TextPaint ds) {
				ds.setUnderlineText(false);
				ds.clearShadowLayer();
				ds.setColor(getContext().getResources().getColor(R.color.common_color));
			}
		}, 7, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		SpannableString.setSpan(new ClickableSpan() {
			@Override
			public void onClick(View widget) {
//				Intent intent = new Intent(QuickRegisterActivity.this,
//						HelpServiceActivity.class);
//				intent.putExtra("requested_page_key", "0");
//				startActivity(intent);
				ServiceWebActivity.start(getContext(), Constants.PRIVACY, "隐私政策");
			}

			@Override
			public void updateDrawState(TextPaint ds) {
				ds.setUnderlineText(false);
				ds.clearShadowLayer();
				ds.setColor(getContext().getResources().getColor(R.color.common_color));
			}
		}, 14, SpannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		vPricacy2.setHighlightColor(getContext().getResources().getColor(R.color.transparent));
		vPricacy2.setMovementMethod(LinkMovementMethod.getInstance());
		vPricacy2.setText(SpannableString);
	}
	@Override
	public void show()
	{
		super.show();
	}

	@Override
	public void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
//		if (mWebView.getParent()instanceof ViewGroup)
//		{
//			((ViewGroup)mWebView.getParent()).removeView(mWebView);
//			mWebView.destroy();
//		}
	}

	public void setCallback(DialogCallback callback)
	{
		this.callback = callback;
	}

	@Override
	public void onClick(View v)
	{
		dismiss();
		if (callback!=null)
		{
			if(v.getId()== R.id.sure)
			{
				callback.onSure();
			}else if(v.getId()== R.id.cancel)
			{
				callback.onCancel();
			}
		}


	}
	public interface DialogCallback
	{
		void onSure();
		void onCancel();
	}
}
