package com.crossrun.sunion.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.crossrun.sunion.util.Session;

/**
 * 构造请求参数
 * @author gjyuan
 *
 */
public class NetParameter {

	/**
	 * 登录参数
	 * @return
	 */
	public static List<NameValuePair> loginParameters(String email,String pwd){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("action", String.valueOf(MyActionType.USER_LOGIN)));
		
		parameters.add(new BasicNameValuePair("email", email));
		
		parameters.add(new BasicNameValuePair("pwd", pwd));
		
		return parameters;
	}
	
	/**
	 * 注册参数
	 * @param email
	 * @param pwd
	 * @return
	 */
	public static List<NameValuePair> registerParameters(String email,String pwd){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("action", String.valueOf(MyActionType.USER_REGISTER)));
		
		parameters.add(new BasicNameValuePair("email", email));
		
		parameters.add(new BasicNameValuePair("pwd", pwd));
		
//		parameters.add(new BasicNameValuePair("token", (String)Session.get(Session.TOKEN)));
		
		return parameters;
	}
}
