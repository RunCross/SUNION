package com.crossrun.sunion.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓冲信息存放
 * @author gjyuan
 *
 */
public class Session {

	public static final String USER_EMAIL = "email";
	public static final String USER_PWD = "pwd";
	public static final String TOKEN = "token";
	
	private static final Map<String, Object> session;
	static{
		session = new HashMap<String, Object>();
	}
	
	/**
	 * 存入会话信息
	 * @param str
	 * @param obj
	 * @return
	 */
	public static boolean put(String key,Object obj){
		session.put(key, obj);
		return true;
	}
	
	/**
	 * 取出会话信息
	 * @param str
	 * @return
	 */
	public static Object get(String str){
		return session.get(str);
		
	}
	
	/**
	 * 判断空
	 * @return
	 */
	public static boolean isEmpty(){
		return session.isEmpty();
	}
	
	/**
	 * 清空
	 */
	public static void clear(){
		session.clear();
	}
	
	/**
	 * 判断key存在
	 * @param key
	 * @return
	 */
	public static boolean containsKey(String key){
		return session.containsKey(key);
	}
}
