package com.viptrip.wetrip.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONObject;


public class JsonUtil {
	/**
	 * @param 对象转字符串
	 * @return
	 */
	public static <T> String ObjectToJsonStr(Object T){
		JSONObject json = JSONObject.fromObject(T); 
		return json.toString();
	}
	/**
	 * @param 字符串转对象
	 * @return
	 */
	public static<T> T JsonToObject(Class<T> clazz,String json){
		ObjectMapper mapper = new ObjectMapper();
		T t=null;
		try {
			t = mapper.readValue(json, clazz);
			return t;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
