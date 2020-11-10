package com.xzq.model.configuration;

import java.util.Map;

import com.xzq.model.bean.User;
import com.xzq.utils.StringUtils;




public class MsgTemplate {
	
	//客服回复文字
	public static String getTextCustomerTemplate(String content,Map<String, String> xmlMap) {
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
	public static String getImgCustomerTemplate(String mediaId,Map<String, String> xmlMap) {
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
