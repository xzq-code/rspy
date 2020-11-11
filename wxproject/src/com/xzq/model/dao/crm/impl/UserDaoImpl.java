package com.xzq.model.dao.crm.impl;

import java.util.List;
import java.util.Map;

import com.xzq.base.BaseDao;
import com.xzq.model.dao.crm.UserDao;
import com.xzq.model.pojo.User;



public class UserDaoImpl extends BaseDao<User> implements UserDao<User> {

	public void addUser(User user) {
		String sql="insert into user (name,password) values(?,?)";
		Object[] params=new Object[] {user.getName(),user.getPassword()};
		add(sql,params);
	}

	public void queryTest() {
		String sql="select * from user where id=?";
		Object[] params=new Object[] {2};
		User user=queryForBean(sql, params);
		System.out.println(user);
	}

	@Override
	public User query4Login(String loginacct,String pwd) {
		String sql="select * from `user` where `name`=? and `password`=?";
		Object[] params=new Object[] {loginacct,pwd};
		User user=queryForBean(sql, params);
		return user;
	}

	@Override
	public List<User> pageQueryData(Map<String, Object> map) {
		int start=(int) map.get("start");
		int size=(int) map.get("size");
		String sql="select * from `user` order by createtime desc limit ?,?";
		Object[] params=new Object[] {start,size};
		List<User> users=queryForList(sql, params);
		return users;
	}
	
}
