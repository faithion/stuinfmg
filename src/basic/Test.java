package basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Test {
	public static void main(String[] args) {
		Map<String, String[]> tem=new HashMap<>();
		String [] strings={"a","b"};
		String [] strings2={"c"};
		tem.put("1", strings);
		tem.put("2", strings2);
		//实例化DTO组件
		Map<String, Object> dto=new HashMap<>();
		//获取所有的键值对集合Entry<K,V>表示键值对
		Set<Entry<String,String[]>> entrySet=tem.entrySet();
		for(String key:tem.keySet()){
			System.out.println(key+"\t"+tem.get(key));
		}
	}
}
