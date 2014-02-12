package com.crossrun.sunion.engine.manager;

import java.util.Iterator;
import java.util.Stack;

import com.crossrun.sunion.engine.IManager;
import com.crossrun.sunion.view.base.BaseActivity;

/**
 * 页面管理器，负责页面栈的进栈、出栈、查询等操作
 * 
 */

public class ActivityManager implements IManager
{

	/**
     * 应用中的Activity栈
     */
    private Stack<BaseActivity> mActivityStack;
    
    
    /**
     * 默认构造方法
     */
    public ActivityManager()
    {
        mActivityStack = new Stack<BaseActivity>();
    }
    
    /**
     * 将页面压入栈中，在页面被拉起后调用
     * 
     * @param activity Activity
     */
    public void pushActivity(BaseActivity activity)
    {
        removeActivity(activity);

        mActivityStack.push(activity);
    }
    
    /**
     * 将页面移除栈，在页面被终止时调用
     * 
     * @param activity 页面
     */
    public void removeActivity(BaseActivity activity)
    {
        if (mActivityStack.contains(activity))
        {
            mActivityStack.remove(activity);
        }
    }
    
    
    /**
     * 获取当前活跃显示的页面
     * 
     * @return 显示的页面
     */
    public BaseActivity currentActivity()
    {

        if (mActivityStack.isEmpty())
        {
            return null;
        }

        return mActivityStack.peek();
    }
    
    /**
     * 结束一个页面，根据页面Id
     * 
     * @param activityId 页面Id
     */
    public void finishActivity(byte activityId)
    {
        if (mActivityStack != null && !mActivityStack.empty())
        {
            BaseActivity tempActivity = null;
            for (Iterator<BaseActivity> it = mActivityStack.iterator(); it.hasNext();)
            {
                tempActivity = it.next();
                if (tempActivity.activityId() == activityId)
                {
                    tempActivity.finish();
                }
            }
        }

    }
    
    /**
     * 查询是否包含此页面，根据页面ID查询
     * 
     * @param activityId 页面ID
     * @return 是否存在，true表示存在
     */
    public boolean containsActivity(byte activityId)
    {
        if (mActivityStack != null && !mActivityStack.empty())
        {
            BaseActivity tempActivity = null;
            for (Iterator<BaseActivity> it = mActivityStack.iterator(); it.hasNext();)
            {
                tempActivity = it.next();
                if (tempActivity.activityId() == activityId)
                {
                    return true;
                }
            }
        }

        return false;
    }
    
    /**
     * 获取具体页面，根据页面Id查询获取
     * 
     * @param activityId 页面Id
     * @return 具体页面
     */
    public BaseActivity getActivity(byte activityId)
    {
        BaseActivity tempActivity = null;
        if (mActivityStack != null && !mActivityStack.empty())
        {

            for (Iterator<BaseActivity> it = mActivityStack.iterator(); it.hasNext();)
            {
                tempActivity = it.next();
                if (tempActivity.activityId() == activityId)
                {
                    break;
                }
                else
                {
                    tempActivity = null;
                }
            }
        }

        return tempActivity;
    }
    
    /**
     * 是否仅存1页面
     * 
     * @param activityId 页面Id
     * @return ture表示只存在播放页面
     */
    public boolean oneActivity(byte activityId)
    {
        return mActivityStack == null
                || mActivityStack.empty()
                || (mActivityStack.size() == 1 && mActivityStack.peek().activityId() == activityId);
    }
    
    /**
     * 根据Activity在栈中位置获取实例
     * 
     * @param position 位置 0表示顶层一个，1 ++
     * @return Activity实例
     */
    public BaseActivity getActivityByPosition(int position)
    {
        // 判断缓存栈
        if (mActivityStack == null || mActivityStack.empty())
        {
            return null;
        }

        // 判断position
        if (position <= 0)
        {
            return currentActivity();
        }

        if (position >= mActivityStack.size())
        {
            return null;
        }

        return mActivityStack.get(mActivityStack.size() - 1 - position);

    }
 
    /**
     * 关闭所有栈内的Activity，退出时用
     */
    public void clearAllActivity()
    {
        BaseActivity tempActivity = null;
        if (mActivityStack != null && !mActivityStack.empty())
        {

            for (Iterator<BaseActivity> it = mActivityStack.iterator(); it.hasNext();)
            {
                tempActivity = it.next();
                tempActivity.finish();
            }
        }
    }
    
	@Override
	public byte managerId() 
	{
		return ACTIVITY_ID;
	}
}