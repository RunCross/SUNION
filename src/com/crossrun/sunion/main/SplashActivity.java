package com.crossrun.sunion.main;

import android.os.Bundle;

import com.crossrun.sunion.view.base.BaseActivity;

public class SplashActivity extends BaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public byte activityId() {
		return SPLASH_ID;
	}

}
