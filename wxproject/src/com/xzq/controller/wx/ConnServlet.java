package com.xzq.controller.wx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzq.model.dao.wx.WxDao;
import com.xzq.utils.StringUtils;

public class ConnServlet extends HttpServlet {

	/**
	 * 开发者通过检验signature对请求进行校验（下面有校验方式）。 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，
	 * 则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
	 * 
	 * 1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String timestamp = request.getParameter("timestamp");
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = "abc";

		// 字典排序
		List<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		String str = StringUtils.sha1(list.get(0) + list.get(1) + list.get(2));
		if (str.equals(signature)) {
			response.getWriter().print(echostr);
		} else {
			System.out.println("接入失败");
		}
	}

	/*
	 * <xml> <ToUserName><![CDATA[gh_1e30af6e5329]]></ToUserName>
	 * <FromUserName><![CDATA[oGqeZxGP1G_hG3wQSdpst7UBLBzM]]></FromUserName>
	 * <CreateTime>1604457128</CreateTime> <MsgType><![CDATA[text]]></MsgType>
	 * <Content><![CDATA[a]]></Content> <MsgId>22970301925259613</MsgId> </xml>
	 * ---dom4j
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// utf8
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		// 获取微信端的内容
		Map<String, String> xmlMap = WxDao.handleMap(request);
		// 针对内容给微信端做回复响应
		String responseStr = WxDao.getResponseStr(xmlMap);
		// 回送给微信服务器
		response.getWriter().print(responseStr);
		
	}

}
