package com.xzq.model.dao.crm;

import java.util.List; 
import java.util.Map;

import com.xzq.annotaion.MyTransaction;
import com.xzq.base.Dao;

public interface RoleDao<Role> extends Dao<Role>{
	public List<Role> pageQueryData(Map<String, Object> map);
	public int getDefaultTotalSize();
	public List<Role> pageQueryForParamData(Map<String, Object> map);
	public int pageQueryForParamDataSize(Map<String, Object> map);
	public Boolean existRole(String roleName);
	public void insert(Role role);
}
