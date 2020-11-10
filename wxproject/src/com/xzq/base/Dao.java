package com.xzq.base;

import java.util.List;
import com.xzq.annotaion.MyTransaction;

public interface Dao<T> {
	@MyTransaction
	int add(String sql,Object... args);
	@MyTransaction
	int update(String sql,Object... args);
	@MyTransaction
	int delete(String sql,Object... args);
	T queryForBean(String sql,Object... args);
	List<T> queryForList(String sql,Object... args);
	
}
