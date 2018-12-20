package com.zm.system.tools;

import org.apache.jasper.tagplugins.jstl.core.If;import com.sun.corba.se.impl.naming.namingutil.IIOPEndpointInfo;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class Tools {
	private Tools(){
		
	}
	public static void main(String[] args) {
		String arr[]={"1","2","3","4","5"};
		System.out.println(Tools.joinArray(arr));
	}
	/**
	 * ����ת�ַ���
	 * @param val
	 * @return
	 */
	public static String joinArray(Object val){
		if(val==null){
			return "";
		}
		//�ж�val�����Ǹ�ɶ
	
		if(val instanceof java.lang.String[]){
			//1.��val��ԭ���ַ�������
			String arr[] = (String[])val;
			//2.��������ĳ���
			int len=arr.length;
			//3.����StringBuilder����
			StringBuilder tem=new StringBuilder(arr[0]);
			//4.ѭ����ȡ���Ԫ��
			for(int i=1;i<len;i++){
				tem.append(",").append(arr[i]);
			}
			return tem.toString();
		} else{
			return (String)val;
		}
	}
}






