package com.xzq.model.configuration;

import java.util.Map;

import com.xzq.model.bean.AccessToken;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TokenConfig {

	//获取access_token的url
	private static String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String APPID="wxa66d3a1ae8980b5b";
	private static String APPSECRET="c56695e699c2da712faa626d332dd9f1";
	
	//客服的url
	private static String customerUrl="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	//获取用户信息的URL
	private static String userInfoUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	//新增临时素材的URL
	private static String tempFileUrl="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	//获取临时带参数二维码的URL
	private static String tempTicketUrl="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	//下载二维码的URL
	private static String getTicketUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET ";
	private static AccessToken at=null;
	//初始化我的token
	private static void initToken() {
		//替换accessTokenUrl-APPID  -APPSECRET
		String url=accessTokenUrl.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
		//从微信服务器获取对应权限
		String tokenStr=HttpUtil.get(url);
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(tokenStr);
		//取出token和expire
		String token=jsonObject.getStr("access_token");
		String expireIn=jsonObject.getStr("expires_in");
		//封装进入token值
		at=new AccessToken(token, expireIn);
	}
	//获取token
	public static String getAccessToken() {
		if(at==null||at.expiredStatus()) {
			initToken();
		}
		return at.getAccessToken();
	}
	//获取客服调用接口
	public static String getCustomerUrl() {
		String newCustomerUrl=customerUrl.replace("ACCESS_TOKEN",TokenConfig.getAccessToken());
		return newCustomerUrl;
	}
	
	//获取用户信息调用接口
	public static String getUserInfoUrl(Map<String, String> xmlMap) {
		String newUserInfoUrl=userInfoUrl.replace("ACCESS_TOKEN",TokenConfig.getAccessToken()).replace("OPENID",xmlMap.get("FromUserName"));
		return newUserInfoUrl;
	}
	
	//获取新增临时素材接口
	public static String getTempFileUrl() {
		String newTempFileUrl=tempFileUrl.replace("ACCESS_TOKEN",TokenConfig.getAccessToken()).replace("TYPE", "image");
		return newTempFileUrl;
	}
	
	//获取获取二维码的接口
	public static String getTempTicketUrl() {
		String newTempTicketUrl=tempTicketUrl.replace("TOKEN",TokenConfig.getAccessToken());
		return newTempTicketUrl;
	}
	
	//下载二维码图片
	public static String getTicketUrl(String ticket) {
		String newGetTicketUrl=getTicketUrl.replace("TICKET", ticket);
		return newGetTicketUrl;
	}
}
