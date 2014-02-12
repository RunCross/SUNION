package com.crossrun.sunion;

import com.crossrun.sunion.engine.AppEngine;

import android.app.Application;

public class SunionApplication extends Application {

	
	public SunionApplication() {
		AppEngine.getInstance().setApplication(this);
	}
	

}
