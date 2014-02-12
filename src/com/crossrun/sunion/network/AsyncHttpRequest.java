package com.crossrun.sunion.network;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.crossrun.sunion.bean.ResultDes;
import com.crossrun.sunion.engine.AppEngine;
import com.crossrun.sunion.engine.IManager;
import com.crossrun.sunion.engine.manager.ActivityManager;
import com.crossrun.sunion.hand.HttpResponseHand;
import com.crossrun.sunion.mess.HttpConnectMessage;

public class AsyncHttpRequest implements Runnable {
	private final DefaultHttpClient client;
	private HttpResponseHand hand;
	private String uri;
	private List<NameValuePair> parameters;
		
	
	public AsyncHttpRequest(HttpClient client, HttpResponseHand hand,
			String uri, List<NameValuePair> parameters) {
		super();
		this.client = (DefaultHttpClient) client;
		this.hand = hand;
		this.uri = uri;
		this.parameters = parameters;
	}

	@Override
	public void run() {		
		ResultDes result = new ResultDes();
		if(!isOpenNetwork()){
			result.is = false;
			result.des = HttpConnectMessage.HTTP_CLOSED;
			hand.onFailed(result);
			return ;
		}
		HttpPost post = new HttpPost(uri);
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
			
			HttpResponse response = client.execute(post);
			
			if(response.getStatusLine().getStatusCode()==200){
				result.is = true;
				result.des = EntityUtils.toString(response.getEntity());
				hand.onSuccess(result);
			}else{
				result.is = false;
				result.des = HttpConnectMessage.HTTP_FAILED;
				hand.onFailed(result);
			}
			
		} catch (IOException e) {
			result.is = false;
			result.des = HttpConnectMessage.HTTP_FAILED;
			hand.onFailed(result);
			e.printStackTrace();
		}
	}

	/** 
	 * 对网络连接状态进行判断 
	 * @return  true, 可用； false， 不可用 
	 */  
	private boolean isOpenNetwork() {  		
	    ConnectivityManager connManager = (ConnectivityManager) ((ActivityManager) AppEngine.getInstance().getManager(IManager.ACTIVITY_ID)).currentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);  
	    if(connManager.getActiveNetworkInfo() != null) {  
	        return connManager.getActiveNetworkInfo().isAvailable();  
	    }  
	  
	    return false;  
	}
}
