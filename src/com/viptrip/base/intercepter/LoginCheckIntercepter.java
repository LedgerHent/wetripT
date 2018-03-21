package com.viptrip.base.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 登录拦截器
 * 
 * @author selfwhisper
 *
 */
public class LoginCheckIntercepter extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse resp, Object handler) throws Exception {
		
		return super.preHandle(request, resp, handler);
	}

}
