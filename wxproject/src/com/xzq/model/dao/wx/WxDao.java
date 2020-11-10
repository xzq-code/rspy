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

import com.xzq.model.bean.User;
import com.xzq.model.configuration.MsgTemplate;
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
	
	//消息处理
	public static void handleMessage(Map<String, String> xmlMap) {
		//获取消息类型
		String msgType=xmlMap.get("MsgType");
		switch(msgType) {
		//处理留言
		case "text":handleTextMessage(xmlMap);break;
		//处理事件
		case "event":handleEventMessage(xmlMap);break;
		}
	}
	
	public static User createUser(Map<String, String> xmlMap) {
		//调用获取用户信息的接口
		String userInfoUrl=TokenConfig.getUserInfoUrl(xmlMap);
		//获取用户信息
		String res=HttpUtil.get(userInfoUrl);
		//处理json数据
		JSONObject jObj=JSONUtil.parseObj(res);
		//封装对象
		User user=new User();
		user.setNickname(jObj.getStr("nickname"));//昵称
		user.setHeadimgurl(jObj.getStr("headimgurl"));//头像地址
		return user;
	}
	
	//获取ticket
	public static String getTicket(Map<String, String> xmlMap) {
		String tempTicketUrl=TokenConfig.getCreateTicketUrl();
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
		//封装用户信息
		User user=createUser(xmlMap);
		String url=TokenConfig.getCustomerUrl();
		switch(eventType) {
		//事件类型---关注------分为扫描带参数的二维码关注或直接关注（搜索公众号添加关注，扫描不带参数二维码添加关注）
		case "subscribe":
			//当事件类型为关注时，
			//回复欢迎消息
			String welcomeMsg=MsgTemplate.getTextCustomerTemplate("感谢"+user.getNickname()+"关注本公众号/:B-)/:B-)/:B-)", xmlMap);
			HttpUtil.post(url, welcomeMsg);
			//回复dm单
			String mediaId=createPoster(xmlMap,user);
			String posterMsg=MsgTemplate.getImgCustomerTemplate(mediaId, xmlMap);
			HttpUtil.post(url, posterMsg);
			//获取ticket元素
			String ticket=xmlMap.get("Ticket");
			System.out.println("牢牢");
			//如果是扫描了带参数的二维码添加的关注，则向参数中的openId所有者发送助力信息
			if(ticket!=null&&ticket.length()>0) {
				//助力成功
				String helpSuccessMsg=MsgTemplate.getTextCustomerTemplate("已成功为好友助力", xmlMap);
				HttpUtil.post(url, helpSuccessMsg);
				//获取二维码参数，去除前缀为dm单所有者的openId
				String newFromUserName=xmlMap.get("EventKey").replace("qrscene_", "");
				//HashMap键相同值被覆盖 更新其中的openId
				xmlMap.put("FromUserName", newFromUserName);
				String hasHelpedMsg=MsgTemplate.getTextCustomerTemplate(user.getNickname()+"已为你助力", xmlMap);
				HttpUtil.post(url, hasHelpedMsg);
			}
			break;
		case "SCAN":
			//已关注，扫了带参数的二维码
			//toReply(xmlMap,user,url);
			//return TextTemplate.getEventParamsTemplate(xmlMap);
		}
		return null;
	}

	//处理文本内容
	private static void handleTextMessage(Map<String, String> xmlMap) {
		//封装当期用户信息
		User user=createUser(xmlMap);//此处应保存数据库
		//固定消息回复
		if(xmlMap.get("MsgType").equals("text")&&xmlMap.get("Content").equals("海报")) {
			String customerUrl=TokenConfig.getCustomerUrl();
			String mediaId=createPoster(xmlMap,user);
			String message=MsgTemplate.getImgCustomerTemplate(mediaId, xmlMap);
			HttpUtil.post(customerUrl, message);
		}else {
			String customerUrl=TokenConfig.getCustomerUrl();
			String message=MsgTemplate.getTextCustomerTemplate("ヾ(◍°∇°◍)ﾉﾞ", xmlMap);
			HttpUtil.post(customerUrl, message);
		}
	}
	
	private static String createPoster(Map<String, String> xmlMap,User user) {
		String mediaId=MyMeidaIdTab.getMediaId(xmlMap.get("FromUserName"));
		if(null==mediaId) {
			//首次获取海报，用户次数初始化
			user.setCount(3);
			//获得ticket
			String ticket=getTicket(xmlMap);
			//url编码
			ticket=URLEncoder.encode(ticket);
			//获取下载二维码的URL
			String ticketurl=TokenConfig.getGainTicketUrl(ticket);
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
			String tempFileUrl=TokenConfig.getTempMaterialUrl();
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
