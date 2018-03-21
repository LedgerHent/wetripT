package com.viptrip.base.intercepter;


import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.viptrip.base.action.AjaxStatus;
import com.viptrip.base.annotation.TokenValidAnnotation;
import com.viptrip.resource.Const;
import com.viptrip.util.StringUtil;
import com.viptrip.util.TokenGenerator;


/**
 * 重复提交拦截器
 * @author selfwhisper
 *
 */
public class TokenIntercepter extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
		TokenValidAnnotation annotation = method.getAnnotation(TokenValidAnnotation.class);
		if(null!=annotation){
			String name = annotation.name();
			if(!StringUtil.isEmpty(name)){
				HttpSession session = request.getSession();
				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) session.getAttribute("token");
				boolean add = annotation.add();
				if(add){
					String token = TokenGenerator.generator();
					map.put(name, token);
//					System.out.println("TokenIntercepter.preHandle()==token==" + token);
//					session.setAttribute(name, token);
				}
				boolean remove = annotation.remove();
				if(remove){
					if(isRepeatSub(request, name)){
						response.setCharacterEncoding("utf-8");
						response.setContentType("application/json;charset=utf-8");
						AjaxStatus s = AjaxStatus.fail;
						s.setText("保存失败,验证令牌失效,请关闭当前窗口后再试");
						response.getWriter().write(new DWZAjaxResponesObj(s).toJson());
						map.remove(name);
						return false;
					}else{
						map.remove(name);
					}
//					session.removeAttribute(name);
				}
				
			}
		}*/
		return super.preHandle(request, response, handler);
	}
	
	private boolean isRepeatSub(HttpServletRequest request,String tokenName){
		boolean result = false;
		if(null!=request&&!StringUtil.isEmpty(tokenName)){
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) request.getSession().getAttribute(Const.SESSION_CONFIG_TOKEN);
			String serverToken = (String) map.get(tokenName);
			if(StringUtil.isEmpty(serverToken)){
				result = true;
			}else{
				String clientToken = request.getParameter(tokenName);
//				System.out.println("TokenIntercepter.isRepeatSub()==serverToken==" + serverToken);
//				System.out.println("TokenIntercepter.isRepeatSub()==clientToken==" + clientToken);
				if(StringUtil.isEmpty(clientToken)){
					result = true;
				}else{
					if(!serverToken.equals(clientToken)){
						result = true;
					}
				}
			}
		}
		return result;
	}
}
