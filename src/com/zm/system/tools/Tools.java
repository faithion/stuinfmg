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
	 * 数组转字符串
	 * @param val
	 * @return
	 */
	public static String joinArray(Object val){
		if(val==null){
			return "";
		}
		//判断val到底是个啥
	
		if(val instanceof java.lang.String[]){
			//1.将val还原成字符串数组
			String arr[] = (String[])val;
			//2.计算数组的长度
			int len=arr.length;
			//3.定义StringBuilder对象
			StringBuilder tem=new StringBuilder(arr[0]);
			//4.循环读取后继元素
			for(int i=1;i<len;i++){
				tem.append(",").append(arr[i]);
			}
			return tem.toString();
		} else{
			return (String)val;
		}
	}
}






