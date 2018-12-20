package com.zm.web.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zm.services.StudentServices;
import com.zm.web.support.ServletSupport;


@WebServlet("/findById.html")
public class FindByIdServlet extends ServletSupport {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map<String, Object> dto=this.createDto(request);
			StudentServices services=new StudentServices(dto);
			Map<String, String> ins=services.findById();
			
			if(ins!=null){
				//将数据向页面反馈
				request.setAttribute("ins", ins);
			}else{
				request.setAttribute("msg", "你查询的数据不存在,或禁止访问!");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "网络故障啦");
			e.printStackTrace();
		}
		//跳到Add.jsp
		request.getRequestDispatcher("/Add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
