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
			//1.��ȡҳ������
			Map<String, Object> dto=this.createDto(request);
			//2.ʵ����Services
			StudentServices services=new StudentServices(dto);
			//3.���÷�����ɴ���
			String msg=services.addStudent()?"��ӳɹ�":"���ʧ��";
			//��Servlet����ҳ��д������
			request.setAttribute("msg", msg);
		}catch (Exception e) {
			request.setAttribute("msg", "�������������");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Add.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		this.doGet(request, response);
	}
}
