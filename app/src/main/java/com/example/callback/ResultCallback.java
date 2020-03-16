package com.example.callback;

/**
 * 功能描述：网络请求返回的结果回调接口
 * 
 *
 */
public interface ResultCallback {
	
	public void onSuccess(Object obj);

	public void onFail(int errorCode);

}
