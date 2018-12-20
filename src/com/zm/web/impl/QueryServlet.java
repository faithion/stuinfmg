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
			//1.获取dto
			Map<String, Object> dto=this.createDto(request);
			//2.实例化当前业务逻辑组件
			StudentServices services = new StudentServices(dto);
			//3.对于所给出的条件执行查询
			List<Map<String,String>> rows=services.query();
			//4.判断是否查询到数据
			if(rows.size()>0){
				//向jsp反馈查询结果
				request.setAttribute("rows", rows);
			}else{
				request.setAttribute("msg", "没有符合条件的数据!");
			}
		}catch(Exception e){
			request.setAttribute("msg", "网络故障啦");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
