package com.viptrip.base.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.viptrip.base.annotation.LogAnnotation;
import com.viptrip.base.annotation.PermissionAnnotation;
import com.viptrip.resource.Const;
import com.viptrip.util.StringUtil;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志记录切面
 * @author selfwhisper
 *
 */

@Aspect
@Component
public class LogAspect {
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	
//	@Resource
//	private LogService logService;

	//切入点
	@Pointcut("@annotation(com.viptrip.base.annotation.LogAnnotation) || @annotation(com.viptrip.base.annotation.PermissionAnnotation)")
	public void logAspect() {
	}
	
	/*@SuppressWarnings("unchecked")
	@AfterThrowing(pointcut = "logAspect()",throwing="e")
    public void afterThrowing(JoinPoint jp, Throwable e){  
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			Userinfo user = (Userinfo) session.getAttribute(Const.SESSION_LOGIN_USER);
			List<Permission> permisisons = (List<Permission>) session.getAttribute(Const.SESSION_LOGIN_PERMISSIONS);
			Map<String, String> map = getMsg(jp,permisisons);
			Log log = new Log();
			if(null!=user)
				log.setUsername(user.getName());
			else
				log.setUsername("空");
			log.setIp(StringUtil.getIpAddr(request));
			log.setTime(new Date());
			log.setModule(map.get("module"));
			log.setOperation(map.get("operation"));
			log.setDescription(map.get("desc"));
			log.setFlag(e.getMessage());
			logService.addLog(log);
		} catch (Exception ex) {
			log.error("添加日志失败\r\n"+StringUtil.getExceptionStr(ex));
		}
    }*/  
	
	/*@SuppressWarnings("unchecked")
	@AfterReturning(pointcut = "logAspect()", returning="rtv")
	public void afterReturnning(JoinPoint jp, Object rtv){
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			Userinfo user = (Userinfo) session.getAttribute(Const.SESSION_LOGIN_USER);
			List<Permission> permisisons = (List<Permission>) session.getAttribute(Const.SESSION_LOGIN_PERMISSIONS);
			Map<String, String> map = getMsg(jp,permisisons);
			Log log = new Log();
			if(null!=user)
				log.setUsername(user.getName());
			else
				log.setUsername("空");
			log.setIp(StringUtil.getIpAddr(request));
			log.setTime(new Date());
			log.setModule(map.get("module"));
			log.setOperation(map.get("operation"));
			log.setDescription(map.get("desc"));
			if(null!=rtv){
				if(rtv.getClass()==String.class){
					log.setFlag((String)rtv);
				}else if(rtv.getClass()==DWZAjaxResponesObj.class){
					DWZAjaxResponesObj obj = (DWZAjaxResponesObj) rtv;
					log.setFlag(obj.getMessage());
				}else{
					log.setFlag(new Gson().toJson(rtv));
				}
			}
			logService.addLog(log);
		} catch (Exception e) {
			log.error("添加日志失败\r\n"+StringUtil.getExceptionStr(e));
		}
	}*/
	
	
	/**
	 * 获取信息
	 * @param jp
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	/*@SuppressWarnings("rawtypes")
	public static Map<String,String> getMsg(JoinPoint jp,List<Permission> permisisons) throws ClassNotFoundException, NoSuchMethodException, SecurityException{
		Map<String, String> map = new HashMap<String, String>();
		String module = null;
		String operation = null;
		String desc = null;
		String targetName = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getDeclaredMethods();
		if(null!=methods&&methods.length>0){
			Method method = null;
			for(Method m:methods){
				if(methodName.equals(m.getName())){
					method = m;
					break;
				}
			}
			if(null!=method){
				PermissionAnnotation pa = method.getAnnotation(PermissionAnnotation.class);
				if(null!=pa){
					module = pa.module();
					operation = pa.operation();
					if(!StringUtil.isEmpty(module)){
						for(Permission p:permisisons){
							if(module.equals(p.getModule())){
								module = p.getModuleDesc();
								break;
							}
						}
					}
					if(!StringUtil.isEmpty(operation)){
						for(Permission p:permisisons){
							if(operation.equals(p.getOperation())){
								operation = p.getOperationDesc();
								break;
							}
						}
					}
				}
				LogAnnotation la = method.getAnnotation(LogAnnotation.class);
				if(null!=la){
					desc = la.desc();
				}
			}
		}
		map.put("module", module);
		map.put("operation", operation);
		map.put("desc", desc);
		return map;
	}*/
}