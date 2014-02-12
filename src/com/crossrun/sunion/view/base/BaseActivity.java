package com.crossrun.sunion.view.base;

import com.crossrun.sunion.engine.AppEngine;
import com.crossrun.sunion.engine.IManager;
import com.crossrun.sunion.engine.manager.ActivityManager;

import android.app.Activity;

public abstract class BaseActivity extends Activity
{
	//欢迎页面
	public final static byte SPLASH_ID = 0;
	
	//登陆页面
	public final static byte LOGIN_ID = 1;
	
	//注册页面
	public final static byte REGISTER_ID =2;
		
	//忘记密码页面
	public final static byte FORGETPWD_ID = 3;
	
	//主页面
	public final static byte MAIN_ID = 4;
	
	
	 @Override
	 protected void onResume()
	 {
		 super.onResume();
	     ((ActivityManager) AppEngine.getInstance().getManager(
	                IManager.ACTIVITY_ID)).pushActivity(this);
	}
	
	 @Override
	 protected void onDestroy()
	 {
		 super.onDestroy();
	     ((ActivityManager) AppEngine.getInstance().getManager(
	                IManager.ACTIVITY_ID)).removeActivity(this);
	 }
	 
	  

    /**
     * 返回页面唯一ID，以供管理和缓存
     * 
     * @return 页面ID
     */
    public abstract byte activityId();
}