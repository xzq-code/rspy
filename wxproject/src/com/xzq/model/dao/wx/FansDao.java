package com.xzq.model.dao.wx;

import java.util.List;
import java.util.Map;

import com.xzq.annotaion.MyTransaction;
import com.xzq.base.Dao;

public interface FansDao<Fans> extends Dao<Fans>{
	@MyTransaction
	public void addFans(Fans fans);
	
	public void queryTest() ;

	public Fans query4Login(String openid);

	public List<Fans> pageQueryData(Map<String, Object> map);
}
