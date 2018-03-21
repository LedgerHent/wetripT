package com.viptrip.base.intercepter;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.viptrip.base.annotation.PermissionAnnotation;



/**
 * 权限拦截器
 * 验证权限
 * @author selfwhisper
 *
 */
public class PermissionCheck extends HandlerInterceptorAdapter {
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
		PermissionAnnotation annotation = method.getAnnotation(PermissionAnnotation.class);
		/*if(null!=annotation){
			String module = annotation.module();
			String operation = annotation.operation();
//			System.out.println("PermissionCheck.preHandle()==module==" + module);
//			System.out.println("PermissionCheck.preHandle()==operation==" + operation);
			if(!StringUtil.isEmpty(module)){
				List<Permission> permissions = (List<Permission>) request.getSession().getAttribute(Const.SESSION_LOGIN_PERMISSIONS);
				Map<String,List<String>> moMap = new HashMap<String, List<String>>();
				if(null!=permissions&&permissions.size()>0){
					for(Permission p:permissions){
						List<String> ops = moMap.get(p.getModule());
						if(null==ops){
							List<String> list = new ArrayList<String>();
							list.add(p.getOperation());
							moMap.put(p.getModule(), list);
						}else{
							ops.add(p.getOperation());
						}
					}
				}
				if(null==moMap || moMap.size()<=0){
					return fail(request, response);
				}else{
					List<String> op = moMap.get(module);
					if(null==op || op.size()<=0){
						return fail(request, response);
					}else{
						if(!StringUtil.isEmpty(operation)){
							if(!op.contains(operation)){
								return fail(request, response);
							}
						}
					}
				}
			}
		}*/
		return super.preHandle(request, response, handler);
	}
	
	private boolean fail(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		/*resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
			AjaxStatus s = AjaxStatus.fail;
			s.setText("没有权限");
			resp.getWriter().write(new DWZAjaxResponesObj(s).toJson());
			return false;
		}else{
			resp.sendRedirect(req.getContextPath() + "/goto?page=public/error.jsp");
			return false;
		}*/
		return true;
	}
}
