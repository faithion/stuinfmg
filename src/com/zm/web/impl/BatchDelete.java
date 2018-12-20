package com.zm.web.impl;

import com.zm.services.StudentServices;
import com.zm.web.support.ServletSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/batchDelete.html")
public class BatchDelete extends ServletSupport implements Servlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map<String, Object> dto=this.createDto(request);
			StudentServices services=new StudentServices(dto);
			String msg=services.batchDelete()?"É¾³ý³É¹¦!":"É¾³ýÊ§°Ü!";
			request.setAttribute("msg", msg);
			List<Map<String, String>>rows=services.query();
			if(rows.size()>0){
				request.setAttribute("rows", rows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("Query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
