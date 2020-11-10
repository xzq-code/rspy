package com.xzq.model.bean;

public class UserInfo {
	private String nickname;
	private String headimgurl;
	private int count;
	public UserInfo(String nickname, String headimgurl, int count) {
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.count = count;
	}
	public UserInfo() {
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
