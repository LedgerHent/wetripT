package com.viptrip.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class StringUtil {
	/**
	 * 获取日志信息  （ 调用者类名 方法名 添加的字段名、值  标志位  ）
	 * （例如：LoginAction.login:name=NEC success ） fieldName为name value为NEC flag为额外信息
	 * @param fieldName 字段属性名
	 * @param value 字段属性值
	 * @param flag 额外信息如 成功或失败
	 * @return
	 */
	public static String getLogInfo(String fieldName,Object value,String flag){
		StackTraceElement[] trace = new Throwable().getStackTrace();   
        String classNamef =  trace[1].getClassName();
        String className =  classNamef.substring(classNamef.lastIndexOf(".")+1);
        String methodName =  trace[1].getMethodName();
        return className+"."+methodName+":"+(fieldName==null?flag:fieldName+"="+
        		(value==null?"null":value.toString())+" "+flag);
	}
	/**
	 * 获取异常的字符串信息
	 * @param e 异常
	 * @return 字符串
	 */
	public static String getExceptionStr(Exception e){
		StringBuffer sb = new StringBuffer(e.toString());
		StackTraceElement[] messages = e.getStackTrace();
		for (int i = 0; i < messages.length; i++) {
			sb.append("\r\n").append("	at "+messages[i].toString());
		}
		return sb.toString();
	}
	/**
	 * 获取客户机ip字符串 0.0.0.0格式 
	 * @param request 请求
	 * @return 字符串
	 */
	public static String getIpAddr(HttpServletRequest request) { 
		 String ip = request.getHeader("x-forwarded-for"); 
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			 ip = request.getHeader("Proxy-Client-IP"); 
		 } 
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			 ip = request.getHeader("WL-Proxy-Client-IP"); 
		 } 
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		     ip = request.getRemoteAddr(); 
		 } 
		 if(ip!=null&&ip.trim().equalsIgnoreCase("0:0:0:0:0:0:0:1")){
			 ip = "127.0.0.1";
		 }
		 return ip; 
	} 
	/**
	 * 判断字符串是否为空
	 * @param value 字符串
	 * @return 结果 布尔类型 null 或 "" 都为空
	 */
	public static boolean isEmpty(String value){
		boolean f = true;
		if(value!=null&&!value.trim().equals("")){
			f = false;
		}
		return f;
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param value 字符串
	 * @return 结果 布尔类型 null 或 "" 都为空
	 */
	public static boolean isNotEmpty(String value){
		boolean f = true;
		if(value==null||value.trim().equals("")){
			f = false;
		}
		return f;
	}
	
	/*public static void main(String[] args) {
		 System.out.println(DigestUtils.md5Hex("123456"));
	}*/
	
	
	/**
	 * 字符串转int，-1有可能是转换异常
	 * @param str
	 * @return
	 */
	public static int getIntValue(String str){
		int value=-1;
		if(!isEmpty(str)){
			try {
				value = Integer.parseInt(str);
			} catch (NumberFormatException e) {
			    e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 *  获取请求头
	 * @param request
	 * @return
	 */
	public static Map<String,String>  getHeaders(HttpServletRequest request){
		Map<String,String> result = new HashMap<>();
		if(null!=request){
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()){
				String s = headerNames.nextElement();
				result.put(s,request.getHeader(s));
			}
		}
		return result;
	}
}
