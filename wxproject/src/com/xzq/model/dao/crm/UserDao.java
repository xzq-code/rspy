package com.xzq.model.dao.crm;

import java.util.List; 
import java.util.Map;

import com.xzq.annotaion.MyTransaction;
import com.xzq.base.Dao;

public interface UserDao<User> extends Dao<User>{
	@MyTransaction
	public void addUser(User user);
	
	public void queryTest() ;

	public User query4Login(String loginacct);

	public List<User> pageQueryData(Map<String, Object> map);

}
