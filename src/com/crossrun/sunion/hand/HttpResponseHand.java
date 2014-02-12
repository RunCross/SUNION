package com.crossrun.sunion.hand;

import com.crossrun.sunion.bean.ResultDes;

/**
 * http请求消息返回处理
 * @author gjyuan
 *
 */
public interface HttpResponseHand {

	public void onSuccess(ResultDes result);
	
	public void onFailed(ResultDes result);
}
