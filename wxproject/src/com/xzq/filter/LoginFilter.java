package com.xzq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzq.filter.CharacterEncodingFilter.MyRequest;
import com.xzq.model.dao.crm.UserDao;
import com.xzq.model.factory.DaoFactory;

public class LoginFilter implements Filter{
	//过滤器  核对登录页面输入的参数
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//获取输入的密码和账号
		String account=req.getParameter("account");
		String pwd=req.getParameter("password");
		System.out.println("账号---"+account+"/br"+"密码---"+pwd);
		UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		if(null!=userDao.query4Login(account,pwd)) {
			chain.doFilter(req, resp); 
		}else {
			resp.getWriter().print("<script>alert(\"用户名或密码输入错误,请重新输入\");</script>") ; 
			resp.setHeader("refresh", "0.001;url=/wxproject/jsp/login.jsp");
		}
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
