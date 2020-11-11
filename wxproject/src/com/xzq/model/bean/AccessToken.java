package com.xzq.model.bean;

public class AccessToken {
	//access_token
	private String accessToken;
	//超时时间
	private long expireTime;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public AccessToken(String accessToken,String expireIn) {
		this.accessToken = accessToken;
		this.expireTime =System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}
	//过期时间
	public boolean expiredStatus() {
		//如果返回true，身份过期
		return System.currentTimeMillis()>expireTime;
	}
	
}
