package com.xzq.test;

import com.xzq.model.pojo.User;
import com.xzq.model.dao.crm.UserDao;
import com.xzq.model.dao.crm.impl.UserDaoImpl;
import com.xzq.model.factory.DaoFactory;

public class QueryTest {
public static void main(String[] args) {
	UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
//	User user=new User();
//	user.setName("a");
//	user.setPassword("123456");
//	userDao.addUser(user);
	System.out.println(userDao.query4Login("as","123456"));
}
}
