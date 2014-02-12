package com.crossrun.sunion.engine;


import java.util.HashMap;
import java.util.Map;

import com.crossrun.sunion.engine.manager.ActivityManager;
import com.crossrun.sunion.engine.manager.NetworkManager;
import android.content.Context;

public class ManagerFactory
{
	
	 private transient Map<Byte, IManager> mManagerMap = null;
	 
	 protected ManagerFactory()
	 {
		 mManagerMap = new HashMap<Byte, IManager>();
	 }
	 
	 protected IManager getManager(final Context context, final byte mId)
	 {
		 IManager manager = mManagerMap.get(mId);

	     if (manager == null)
	     {
	    	 switch (mId)
	         {
		         case IManager.ACTIVITY_ID:
		         {
		        	 manager = new ActivityManager();
			         break;
		         }
		         
		         case IManager.NETWOTK_ID:
		         {
		        	 manager = new NetworkManager();
		        	 break;
		         }
		         
		         case IManager.DB_ID:
		         {
//		        	 manager = DBManager.getInstance(context.getApplicationContext());
		        	 break;
		         }
		         
		         case IManager.CLASSIFIER_ID:
		         {
//		        	 manager = new ResClassifierManager();
		        	 break;
		         }

	             default:
	                break;
	         }
	         mManagerMap.put(mId, manager);
	     }
	     return manager;
	 }
	 
	 protected void releaseManager(final byte mId)
	 {
		 if (mManagerMap.containsKey(mId))
	     {
			 mManagerMap.remove(mId);
	     }
	 }

}