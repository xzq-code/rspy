package com.xzq.model.configuration;

import java.util.Map;

import com.xzq.model.bean.UserInfo;
import com.xzq.utils.StringUtils;




public class TextTemplate {
	//1 关注回复  2自动回复  3其他回复
	public static String getContent(int type) {
		switch(type) {
		case 1:return "欢迎nickname关注xzq的公众测试号/:B-)";
		case 2:return "感谢你的留言";
		}
		return null;
	}
	
	//获取自动回复
	public static String getTextTemplate(Map<String,String> xmlMap) {
		String template="<xml>\r\n" + 
					"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
					"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
					"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
					"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
					"  <Content><![CDATA["+getContent(2)+"]]></Content>\r\n" + 
					"</xml>";
		return template;
	}
	public static String getImgTemplate(Map<String,String> xmlMap,String mediaId) {
		String template="<xml>\r\n" + 
					"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
					"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
					"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
					"  <MsgType><![CDATA[image]]></MsgType>\r\n" + 
					"  <Image>\r\n" + 
					"    <MediaId><![CDATA["+mediaId+"]]></MediaId>\r\n" + 
					"  </Image>" + 
					"</xml>";
		return template;
	}
	
	//带参数关注公众号
	public static String getEventWithParamsTemplate(Map<String, String> xmlMap,UserInfo user) {
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+getContent(1).replace("nickname", user.getNickname())+"]]></Content>\r\n" + 
				"</xml>";
		return template;		
	}
	//不带参数公众号
	public static String getEventWithoutParamsTemplate(Map<String, String> xmlMap,UserInfo user) {
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+getContent(1).replace("nickname", user.getNickname())+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	//直接取出参数
	public static String getEventParamsTemplate(Map<String, String> xmlMap) {
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA[回复“海报”领取您的专属海报]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	//客服模板选用
	public static String getCustomerTemplate(String content,Map<String, String> xmlMap) {
		String result="{\r\n" + 
				"    \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+content+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
	//客服回复图片
	public static String getCustomerImgTemplate(String mediaId,Map<String, String> xmlMap) {
		String result="{\r\n" + 
				"    \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"image\",\r\n" + 
				"    \"image\":\r\n" + 
				"    {\r\n" + 
				"      \"media_id\":\""+mediaId+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}
}
