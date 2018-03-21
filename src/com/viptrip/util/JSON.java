package com.viptrip.util;


import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import etuf.v1_0.model.base.output.OutputResult;


public class JSON {
	private GsonBuilder builder;
	
	private JSON(){
		this.builder = new GsonBuilder();
	}
	
	/**
	 * 设置是否原样转换
	 * @param flag 是否原样转换 即 不需要转码
	 * @return
	 */
	public JSON notEscapeHTML(boolean flag){
		if(flag)
			this.builder = builder.disableHtmlEscaping();
		return this;
	}
	/**
	 * 设置是否转换null
	 * @param flag
	 * @return
	 */
	public JSON nullSerialize(boolean flag){
		if(flag)
			this.builder = builder.serializeNulls();
		return this;
	}
	
	/**
	 * 获取gson对象
	 * @return
	 */
	public Gson gson(){
		return builder.create();
	}
	
	public String toJson(Object obj){
		return builder.create().toJson(obj);
	}
	
	public <E> E fromJson(String json, Class<? extends E> clz){
		return builder.create().fromJson(json, clz);
	}
	
	public <E> E fromJson(String json, Type type){
		return builder.create().fromJson(json, type);
	}

	public static JSON get() {
		return new JSON();
	}
	
	public T JsonDeserialize(Class<T> cla,String str){
		T t;
		t = new JSON().fromJson(str, cla);
		return t;
	}
	
	/**
	 * 返回结果的json串转换为对象，并设置返回的一些标记信息，code result等
	 * @param cla 返回结果的类型
	 * @param str 返回的待转换的json串
	 * @param out 返回的封装model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Boolean JsonDeserialize(Class cla,String str,OutputResult<T,String> out) throws Exception{   
		Boolean flag=true;
		try { 
		
			T Obj=null;
			Obj=(T) JsonToObject(cla,str );
			if(Obj==null){
	    	    flag=false;
				out.setResultObj(null);
		        out.code=1;
		        out.result=str;
	       }else{
	    	    out.setResultObj(Obj);
		        out.code=0;
		        out.result="成功";
		        out.exception=null;
	       }
		} catch (Exception e) {  
			flag=false;
			out.setResultObj(null);
	        out.code=1;
	        out.result=str;
	        out.exception=e;
            e.printStackTrace();  
        }  
		return flag;
		
	}
	
	/**
	 * JSON 转换为对象
	 * @param clazz
	 * @param json
	 * @return Page_IndexAndSize
	 */
	public static<T> T JsonToObject(Class<T> clazz,String json){
		ObjectMapper mapper = new ObjectMapper();
		T t;
		try {
			t = mapper.readValue(json, clazz);
			return t;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将对象转换为字符串
	 * @param t
	 * @return
	 */
	public static<T> String ObjectToJson(T t){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
