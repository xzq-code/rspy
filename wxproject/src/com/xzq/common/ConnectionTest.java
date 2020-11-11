package com.xzq.common;

import java.sql.Connection;

import org.junit.Test;

import com.xzq.utils.JdbcUtil;




public class ConnectionTest {

	@Test
	public void test() throws Exception{
		Connection conn=JdbcUtil.getConnection();
		System.out.println(conn);
	}
	
}
