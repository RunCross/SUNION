package com.crossrun.sunion.engine;

import android.app.Activity;
import android.content.Context;
import com.crossrun.sunion.SunionApplication;
import com.crossrun.sunion.engine.manager.ActivityManager;

public final class AppEngine
{
	public final static String TAG = "AppEngine";
	
	private static AppEngine mInstance;
	
	private ManagerFactory mManagerFactory;
	
	private SunionApplication mApplication;
	
	private AppEngine()
	{
		mManagerFactory = new ManagerFactory();
	}
	
	public static synchronized AppEngine getInstance()
	{
		if (mInstance == null)
	    {
			mInstance = new AppEngine();
	    }

	    return mInstance;
	}
	
	public IManager getManager(final byte mId)
    {
        return mManagerFactory.getManager(mId == IManager.ACTIVITY_ID ? mApplication : getContext(), mId);
    }
	
	public Context getContext()
    {

        // 优先Activity
        final Activity activity = ((ActivityManager) getManager(IManager.ACTIVITY_ID)).currentActivity();
        return activity == null ? mApplication : activity;

    }
	
	public static void quitApplication(Context context)
	{
	    ((ActivityManager)AppEngine.getInstance().getManager(IManager.ACTIVITY_ID)).clearAllActivity(); 
	    System.exit(0);
	}
	public void setApplication(SunionApplication app)
	{
		mApplication = app;
	}
}