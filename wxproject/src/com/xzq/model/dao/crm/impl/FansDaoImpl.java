package com.xzq.model.dao.crm.impl;

import java.util.List;
import java.util.Map;

import com.xzq.base.BaseDao;
import com.xzq.model.dao.wx.FansDao;
import com.xzq.model.pojo.Fans;

public class FansDaoImpl extends BaseDao<Fans> implements FansDao<Fans>{

	@Override
	public void addFans(Fans fans) {
		String sql="insert into wxos_fans values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[] 
				{null,fans.getSubscribe(),fans.getOpenid(),fans.getNickname(),fans.getSex(),fans.getCity(),fans.getProvince(),//
				fans.getCountry(),fans.getLanguage(),fans.getHeadimgurl(),fans.getSubscribe_time(),fans.getRemark(),fans.getGroupid(),//
				fans.getTagid_list(),fans.getSubscribe_scene(),fans.getQr_scene(),fans.getQr_scene_str(),fans.getUnionid(),fans.getParentid(),//
				fans.getChildid(),fans.getFansnum()};
		add(sql,params);
		
	}

	@Override
	public void queryTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fans query4Login(String openid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fans> pageQueryData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
