package com.zm.web.impl;

import com.zm.services.StudentServices;
import com.zm.web.support.ServletSupport;
import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modify.html")
public class ModifyServlet extends ServletSupport implements Servlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//1.获取页面数据
			Map<String, Object> dto=this.createDto(request);
			//2.实例化Services
			StudentServices services=new StudentServices(dto);
			String msg=services.modify()?"修改成功":"修改失败";
			request.setAttribute("msg", msg);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "网络出问题啦");
		}
		request.getRequestDispatcher("/Add.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
