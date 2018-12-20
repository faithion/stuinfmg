package com.zm.web.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


public abstract class ServletSupport extends HttpServlet {
	protected final Map<String, Object> createDto(HttpServletRequest request) {
		//��ȡҳ������
		Map<String, String[]> tem=request.getParameterMap();
		//ʵ����DTO���
		Map<String, Object> dto=new HashMap<>();
		//��ȡ���еļ�ֵ�Լ���
		Set<Entry<String,String[]>> entrySet=tem.entrySet();
		
		//�������value���ֵ�����
		String val[]=null;
		//�������ϣ���ȡÿһ����ֵ��
		for(Entry<String, String[]> entry:entrySet){
			//����entry��ȡvalue
			val=entry.getValue();
			//�������鳤���жϿռ�����
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
