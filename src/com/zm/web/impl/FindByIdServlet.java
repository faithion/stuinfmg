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
				//��������ҳ�淴��
				request.setAttribute("ins", ins);
			}else{
				request.setAttribute("msg", "���ѯ�����ݲ�����,���ֹ����!");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "���������");
			e.printStackTrace();
		}
		//����Add.jsp
		request.getRequestDispatcher("/Add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
