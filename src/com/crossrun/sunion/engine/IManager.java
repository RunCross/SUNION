package com.crossrun.sunion.engine;

/**
 * 应用中自定义管理器需要实现的接口 需要自定义唯一ID
 * 
 */
public interface IManager
{
    /**
     * ActivityManager ID
     */
    byte ACTIVITY_ID = 0;
    
    /**
     * NetworkManagerID
     */
    byte NETWOTK_ID = 1;
    
    /**
     * DBManager ID
     */
    byte DB_ID = 2;
    
    /**
     * res classifier manager ID
     */
    byte CLASSIFIER_ID =3;
    
    
    /**
     * 返回管理器ID
     * 
     * @return 管理器ID
     */
    byte managerId();
}
