package com.zm.web.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


public abstract class ServletSupport extends HttpServlet {
	protected final Map<String, Object> createDto(HttpServletRequest request) {
		//获取页面数据
		Map<String, String[]> tem=request.getParameterMap();
		//实例化DTO组件
		Map<String, Object> dto=new HashMap<>();
		//获取所有的键值对集合
		Set<Entry<String,String[]>> entrySet=tem.entrySet();
		
		//定义代表value部分的数组
		String val[]=null;
		//遍历集合，获取每一个键值对
		for(Entry<String, String[]> entry:entrySet){
			//依据entry获取value
			val=entry.getValue();
			//依据数组长度判断空间类型
			if(val.length==1){
				if(val[0]!=null&&!val[0].equals("")){
					dto.put(entry.getKey(), val[0]);
				}
			}else{
				for(String string:val){
					System.out.println(string);
				}
				dto.put(entry.getKey(), val);
			}
		}
		System.out.println(dto);
		return dto;
	}
}
