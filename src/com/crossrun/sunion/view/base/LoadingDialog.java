package com.crossrun.sunion.view.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog {
	private ProgressDialog mProgressDialog = null;
	private Activity mActivity = null;

	public LoadingDialog(Activity act) {
		mActivity = act;
	}

	// 弹出进度条对话框
	public void showDialog(String msg) {
		if (mActivity == null)
			return;
		if (mProgressDialog == null) {
		    Context context = mActivity.getParent();
		    if ( null == context )
		        context = mActivity;
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setCanceledOnTouchOutside(false);
		}
		mProgressDialog.setMessage(msg);
		mProgressDialog.show();
	}

	public void dismissDialog() {
		if (mActivity != null && !mActivity.isFinishing()) {
			if (mProgressDialog != null && mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
				mProgressDialog = null;
			}
		}
	}
}
