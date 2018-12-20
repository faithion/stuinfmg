package com.zm.web.impl;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zm.services.StudentServices;
import com.zm.web.support.ServletSupport;

/**
 * Servlet implementation class Query
 */
@WebServlet("/query.html")
public class QueryServlet extends ServletSupport {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//1.��ȡdto
			Map<String, Object> dto=this.createDto(request);
			//2.ʵ������ǰҵ���߼����
			StudentServices services = new StudentServices(dto);
			//3.����������������ִ�в�ѯ
			List<Map<String,String>> rows=services.query();
			//4.�ж��Ƿ��ѯ������
			if(rows.size()>0){
				//��jsp������ѯ���
				request.setAttribute("rows", rows);
			}else{
				request.setAttribute("msg", "û�з�������������!");
			}
		}catch(Exception e){
			request.setAttribute("msg", "���������");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
