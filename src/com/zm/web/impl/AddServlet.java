package com.zm.web.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zm.services.StudentServices;
import com.zm.web.support.ServletSupport;

@WebServlet("/add.html")
public class AddServlet extends ServletSupport {
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		try{
			//1.获取页面数据
			Map<String, Object> dto=this.createDto(request);
			//2.实例化Services
			StudentServices services=new StudentServices(dto);
			//3.调用方法完成处理
			String msg=services.addStudent()?"添加成功":"添加失败";
			//从Servlet中向页面写入数据
			request.setAttribute("msg", msg);
		}catch (Exception e) {
			request.setAttribute("msg", "网络故障啦！！");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Add.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		this.doGet(request, response);
	}
}
