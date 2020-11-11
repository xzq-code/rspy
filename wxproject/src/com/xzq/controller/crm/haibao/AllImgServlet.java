package com.xzq.controller.crm.haibao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzq.utils.ImageUtil;



public class AllImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	int start = 0;//每一页数据在数据表中开始截取的位置
	        int count = 5;//每一页截取的条数
	 
	        try {
	            start = Integer.parseInt(request.getParameter("start"));
	        } catch (NumberFormatException e) {
	            // 当浏览器没有传参数start时
	        }
	        
	        int next = start + count;
	        int pre = start - count;
		
		List<String> allimgsList=ImageUtil.getFileName();
		List<String> imgsList=new ArrayList<String>();
		for (int i = start; i < start+4; i++) {
			imgsList.add(allimgsList.get(i));
		}
		request.setAttribute("next", next);
        request.setAttribute("pre", pre);
		request.setAttribute("imgsList",imgsList);
		
        request.getRequestDispatcher("jsp/imgs.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
