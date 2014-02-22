package com.crossrun.sunion.engine.manager;

import org.json.JSONException;
import org.json.JSONObject;

import com.crossrun.sunion.bean.ResultDes;
import com.crossrun.sunion.engine.IManager;
import com.crossrun.sunion.hand.HttpResponseHand;
import com.crossrun.sunion.network.AsyncHttpClient;
import com.crossrun.sunion.network.NetParameter;
import com.crossrun.sunion.util.Session;


/**
 * 与后台交互所有接口的提供者
 * 
 */
public class NetworkManager implements IManager {

	/**
	 * 请求地址
	 */
	private static final String HTTP_HEAD = "http://0.sunion.duapp.com/transfer";
	
//	private static final String LOGIN = HTTP_HEAD+"transfer";
	
	public NetworkManager() {

	}

	/**
	 * 登录请求
	 * @param email
	 * @param pwd
	 * @param hand
	 */
	public  void login(final String email,final String pwd,final HttpResponseHand hand){
//		MyHttpConnection.sendPost(null, null, hand);
		AsyncHttpClient client = new AsyncHttpClient();
		client.sendPost(HTTP_HEAD, NetParameter.loginParameters(email, pwd), new HttpResponseHand() {
			
			@Override
			public void onSuccess(ResultDes result) {
//				System.out.println(result.des); 
				
				try {
					JSONObject obj = new JSONObject(result.des);
					
					int code = obj.getInt("code");
					if(code == 0){
						
						Session.put(Session.TOKEN, obj.getString("message"));
						
						Session.put(Session.USER_EMAIL, email);
						
						Session.put(Session.USER_PWD, pwd);
						
						hand.onSuccess(result);
						
					}else{
						result.des =  obj.getString("message");
						hand.onFailed(result);
					}
					
					
				} catch (JSONException e) {
					e.printStackTrace();
					hand.onFailed(result);	
				}
			}
			
			@Override
			public void onFailed(ResultDes result) {
				hand.onFailed(result);
			}
		});
	}
	
	
	public void register(final String email,final String pwd,final HttpResponseHand hand){
		AsyncHttpClient client = new AsyncHttpClient();
		client.sendPost(HTTP_HEAD, NetParameter.registerParameters(email, pwd), new HttpResponseHand() {

			@Override
			public void onSuccess(ResultDes result) {
				try {
					JSONObject obj = new JSONObject(result.des);
					
					int code = obj.getInt("code");
					if(code == 0){
						
//						Session.put(Session.TOKEN, obj.getString("message"));
						
						Session.put(Session.USER_EMAIL, email);
						
						Session.put(Session.USER_PWD, pwd);
						
						hand.onSuccess(result);
						
					}else{
						result.des =  obj.getString("message");
						hand.onFailed(result);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					hand.onFailed(result);	
				}
			}

			@Override
			public void onFailed(ResultDes result) {
				hand.onFailed(result);
			}
			
		});
	}	
	
	
	
	@Override
	public byte managerId() {
		return NETWOTK_ID;
	}
	
	
}