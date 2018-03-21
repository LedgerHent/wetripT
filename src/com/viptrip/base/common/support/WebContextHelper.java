package com.viptrip.base.common.support;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebContextHelper {
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attr==null?null:attr.getRequest().getSession();
	}
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attr==null?null:attr.getRequest();
	}
}
