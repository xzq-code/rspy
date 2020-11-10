package com.xzq.model.dao.wx;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xzq.model.bean.UserInfo;
import com.xzq.model.configuration.TextTemplate;
import com.xzq.model.configuration.TokenConfig;
import com.xzq.utils.ImageUtil;
import com.xzq.utils.MyMeidaIdTab;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class WxDao {

	//处理xml信息
	public static Map<String,String> handleMap(HttpServletRequest request){
		try {
			InputStream in=request.getInputStream();
			//准备一个map
			Map<String,String> xmlMap=new HashMap<String,String>();
			//dom4j
			SAXReader reader=new SAXReader();
			//获取到整个xml内容
			Document document=reader.read(in);
			//获取到第一个节点，所有节点的父节点
			Element root=document.getRootElement();
			//拿到所有子节点
			List<Element> elements=root.elements();
			for(Element e:elements) {
				String tagName=e.getName();
				String value=e.getStringValue();
				xmlMap.put(tagName, value);
			}
			return xmlMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//回送微信端字符串
	public static String getResponseStr(Map<String, String> xmlMap) {
		String msgType=xmlMap.get("MsgType");
		String resultXml="";
		switch(msgType) {
		//处理留言
		case "text":resultXml=handleTextMessage(xmlMap);break;
		//处理事件
		case "event":resultXml=handleEventMessage(xmlMap);break;
		}
		return resultXml;
	}
	
	public static UserInfo getUserInfo(Map<String, String> xmlMap) {
		//调用获取用户信息的接口
		String userInfoUrl=TokenConfig.getUserInfoUrl(xmlMap);
		//获取用户信息
		String res=HttpUtil.get(userInfoUrl);
		//处理json数据
		JSONObject jObj=JSONUtil.parseObj(res);
		//封装对象
		UserInfo user=new UserInfo();
		user.setNickname(jObj.getStr("nickname"));//昵称
		user.setHeadimgurl(jObj.getStr("headimgurl"));//头像地址
		return user;
	}
	
	//获取ticket
	public static String getTicket(Map<String, String> xmlMap) {
		String tempTicketUrl=TokenConfig.getTempTicketUrl();
		String param="{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+xmlMap.get("FromUserName")+"\"}}}";
		String res=HttpUtil.post(tempTicketUrl, param);
		JSONObject ticketObj=JSONUtil.parseObj(res);
		String ticket=ticketObj.getStr("ticket");
		return ticket;
	}
	
	//处理事件
	private static String handleEventMessage(Map<String, String> xmlMap) {
		//筛选处理事件类型
		String eventType=xmlMap.get("Event");
		UserInfo user=getUserInfo(xmlMap);
		String url=TokenConfig.getCustomerUrl();
		switch(eventType) {
		case "subscribe":
			//获取ticket元素
			String ticket=xmlMap.get("Ticket");
			String mediaId=createPost(xmlMap,user);
			String result=TextTemplate.getCustomerImgTemplate(mediaId, xmlMap);
			HttpUtil.post(url, result);
			if(ticket!=null&&ticket.length()>0) {
				
				//扫描了带参数的二维码，并点击了关注
				toReply(xmlMap,user,url);
				return TextTemplate.getEventWithParamsTemplate(xmlMap,user);
			}else {
				//直接关注，或者是二维码没有带参数
				return 	TextTemplate.getEventWithoutParamsTemplate(xmlMap,user);
			}
		case "SCAN":
			//已关注，扫了带参数的二维码
			toReply(xmlMap,user,url);
			return TextTemplate.getEventParamsTemplate(xmlMap);
		}
		return null;
	}
	
	public static void toReply(Map<String, String> xmlMap,UserInfo user,String url) {
		String toUser=xmlMap.get("EventKey").replace("qrscene_","");
		Map<String,String> map=new HashMap<String,String>();
		map.put("FromUserName", toUser);
		String scanMessage=null;
		scanMessage=TextTemplate.getCustomerTemplate(user.getNickname()+"扫描了你的二维码", map);
		HttpUtil.post(url, scanMessage);
	}
	
	//处理文本内容
	private static String handleTextMessage(Map<String, String> xmlMap) {
		//xmlMap.put("FromUserName", "openid");  这个位置可以增加业务
		//增加一个客服回复功能
		
		//调用客服接口
//		String url=TokenConfig.getCustomerUrl();
		
//		for(int i=0;i<10;i++) {
//			String result=TextTemplate.getCustomerTemplate("很高兴为您服务", xmlMap);
//			HttpUtil.post(url, result);
//		}
		
		UserInfo user=getUserInfo(xmlMap);
		
//		String result=TextTemplate.getCustomerImgTemplate(mediaId, xmlMap);
//		HttpUtil.post(url, result);
		if(xmlMap.get("MsgType").equals("text")&&xmlMap.get("Content").equals("海报")) {
			String mediaId=createPost(xmlMap,user);
			return TextTemplate.getImgTemplate(xmlMap, mediaId);
		}
		
		return TextTemplate.getTextTemplate(xmlMap);
	}
	
	private static String createPost(Map<String, String> xmlMap,UserInfo user) {
		String mediaId=MyMeidaIdTab.getMediaId(xmlMap.get("FromUserName"));
		if(null==mediaId) {
			//首次获取海报，用户次数初始化
			user.setCount(3);
			//获得ticket
			String ticket=getTicket(xmlMap);
			//url编码
			ticket=URLEncoder.encode(ticket);
			//获取下载二维码的URL
			String ticketurl=TokenConfig.getTicketUrl((ticket));
			//下载二维码
			HttpUtil.downloadFile(ticketurl, FileUtil.file("../../img/tmp/ticket.jpg"));
			
			//将头像下载到本地
			HttpUtil.downloadFile(user.getHeadimgurl(), FileUtil.file("../../img/tmp/headimg.jpg"));
			//添加昵称
			ImageUtil.addText("../../img/tmp/haibao.png","../../img/tmp/haibao_text.png",user.getNickname());
			//添加头像
			ImageUtil.addImg("../../img/tmp/haibao_text.png","../../img/tmp/haibao_text_img.png","../../img/tmp/headimg.jpg");
			//添加二维码
			String imgPath=ImageUtil.addImg("../../img/tmp/haibao_text_img.png", "../../img/tmp/haibao_text_img_ticket.png","../../img/tmp/ticket.jpg");
			//将新图片添加到素材库
			String tempFileUrl=TokenConfig.getTempFileUrl();
			//使用map保存表单信息
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("file", FileUtil.file(imgPath));
			String storeFileJson=HttpUtil.post(tempFileUrl,map);
			//获得mediaId
			JSONObject storeFileJsonObj=JSONUtil.parseObj(storeFileJson);
			mediaId=storeFileJsonObj.getStr("media_id");
			
			//将mediaId保存
			MyMeidaIdTab.add(xmlMap.get("FromUserName"), mediaId);
		}
		return mediaId;
	}
	
	
}
