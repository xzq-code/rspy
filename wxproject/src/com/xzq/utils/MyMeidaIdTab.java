package com.xzq.utils;

import java.util.HashMap;
import java.util.Map;

public class MyMeidaIdTab {
	private static Map<String,String> map=new HashMap<String,String>();
	public static void add(String openId,String mediaId) {
		map.put(openId,mediaId);
	}
	public static String getMediaId(String openId) {
		return map.get(openId);
	}
}
