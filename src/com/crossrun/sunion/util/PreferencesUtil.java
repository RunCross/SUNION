package com.crossrun.sunion.util;


import com.crossrun.sunion.engine.AppEngine;

import android.content.Context;
import android.content.SharedPreferences;



public abstract class PreferencesUtil
{

    private static SharedPreferences mSharePreferences;

    private static final String CONFIG_FILE_NAME = "sunion_config";	
    
    public static final String KEY_USER_EMAIL = "user_email";
    
    public static final String KEY_USER_PWD = "user_pwd";
    
    public static final String KEY_PWD_SHOW = "pwd_show";

    /**
     * 允许推送
     */
    public static final String IS_NOTICATION = "is_notication";
    public static final String IS_PUSH_SETTING = "is_push_setting";
    
    /**
     * 推送开始时间
     */
    public static final String KEY_START_HOUR = "key_start_hour";
    public static final String KEY_START_WEEK = "key_start_week";
    public static final String KEY_END_HOUR = "key_end_hour";
    public static final String KEY_end_week = "key_end_week";
    

    
    

    private synchronized static SharedPreferences preferences()
    {
        if (mSharePreferences == null)
        {
            mSharePreferences = AppEngine.getInstance().getContext().getSharedPreferences(
                    CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharePreferences;

    }

    public static String getString(String key, String defValue)
    {
        return preferences().getString(key, defValue);
    }

    public static int getInt(String key, int defValue)
    {
        return preferences().getInt(key, defValue);
    }

    public static long getLong(String key, long defValue)
    {
        return preferences().getLong(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue)
    {
        return preferences().getBoolean(key, defValue);
    }

    public static boolean commit(String key, int value)
    {
        return preferences().edit().putInt(key, value).commit();
    }

    public static boolean commit(String key, String value)
    {
        return preferences().edit().putString(key, value).commit();
    }

    public static boolean commit(String key, long value)
    {
        return preferences().edit().putLong(key, value).commit();
    }

    public static boolean commit(String key, Boolean value)
    {
        return preferences().edit().putBoolean(key, value).commit();
    }

}
