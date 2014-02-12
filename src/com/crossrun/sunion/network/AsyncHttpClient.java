package com.crossrun.sunion.network;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.crossrun.sunion.hand.HttpResponseHand;

/**
 * http发送类
 * @author gjyuan
 *
 */
public class AsyncHttpClient {

	private static HttpClient client;
	
	private ThreadPoolExecutor threadPool;
	
	static{
		client = new DefaultHttpClient();		
	}
	
	public AsyncHttpClient(){
		threadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();	
	}
	/**
	 * 发送post请求
	 * @param uri
	 * @param parameters
	 */
	public  void sendPost(String uri,List<NameValuePair> parameters,final HttpResponseHand hand){
		 threadPool.submit(new AsyncHttpRequest(client, hand, uri, parameters));
		
//		ResultDes result = new ResultDes();
//		if(!isOpenNetwork()){
//			result.is = false;
//			result.des = HttpConnectMessage.HTTP_CLOSED;
//			hand.onFailed(result);
//			return ;
//		}
//		HttpPost post = new HttpPost(uri);
//		try {
//			post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
//			
//			HttpResponse response = client.execute(post);
//			
//			if(response.getStatusLine().getStatusCode()==200){
//				result.is = true;
//				result.des = EntityUtils.toString(response.getEntity());
//				hand.onSuccess(result);
//			}else{
//				result.is = false;
//				result.des = HttpConnectMessage.HTTP_FAILED;
//				hand.onFailed(result);
//			}
//			
//		} catch (IOException e) {
//			result.is = false;
//			result.des = HttpConnectMessage.HTTP_FAILED;
//			hand.onFailed(result);
//			e.printStackTrace();
//		}
		
		
	}
//	/** 
//	 * 对网络连接状态进行判断 
//	 * @return  true, 可用； false， 不可用 
//	 */  
//	private static boolean isOpenNetwork() {  		
//	    ConnectivityManager connManager = (ConnectivityManager) ((ActivityManager) AppEngine.getInstance().getManager(IManager.ACTIVITY_ID)).currentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);  
//	    if(connManager.getActiveNetworkInfo() != null) {  
//	        return connManager.getActiveNetworkInfo().isAvailable();  
//	    }  
//	  
//	    return false;  
//	}
	

}
