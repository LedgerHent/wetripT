package com.viptrip.base.intercepter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.cache.UserCacheHelper;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.service.IUserService;

import etuf.v1_0.model.base.output.OutputResult;


/**
 * BaseAction基类 设置
 * 设置request、response、用户、用户ID
 * @author selfwhisper
 *
 */
public class BaseActionIntercepter extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod m = (HandlerMethod) handler;
			if(m.getBean() instanceof BaseAction){
				BaseAction ba = (BaseAction) m.getBean();
				ba.setReq(request);
				ba.setResp(response);
				ba.setBaseURL(request.getScheme()+"://"+request.getServerName()+ (request.getServerPort()==80?"":(":"+request.getServerPort()))+request.getContextPath()+"/");
				//Long userId = (Long) request.getSession().getAttribute(Constant.PRO_USERID);
				//TAcUser user = (TAcUser) request.getSession().getAttribute(Constant.PRO_USER);
				Long userId = null;
				TAcUser user =null;
				HttpSession session = request.getSession();
				if(null != request.getUserPrincipal()){
					String username = request.getUserPrincipal().getName();
					if(StringUtil.isEmpty(username)){
						//清空缓存
						clearSession(session,0);
					}else{
						OutputResult<Long,String> or = UserCacheHelper.GetUserIdFromCache(request.getSession());
						if(or.IsSucceed()){
							userId = or.getResultObj();
							OutputResult<TAcUser,String> orUser=UserCacheHelper.GetUserFromCache(request.getSession(), userId.longValue());
							if(orUser.IsSucceed()){
								user = orUser.getResultObj();
								if(username.equals(user.getPhone())||username.equals(user.getEmail())
										||username.equals(user.getLoginname())||username.equals(user.getIdcard())){
									
								}else{
									clearSession(session,userId);
									userId = null;
									user = null;
								}
							}else{
								userId = null;
							}
						}
					}
				}else{
					//清空缓存
					clearSession(session,0);
					userId = null;
					user = null;
				}
				
			/*	OutputResult<Long,String> or=UserCacheHelper.GetUserIdFromCache(request.getSession());
				if(or.IsSucceed()){
					userId=or.getResultObj();
					OutputResult<TAcUser,String> orUser=UserCacheHelper.GetUserFromCache(request.getSession(), userId.longValue());
					if(orUser.IsSucceed()){
						user=orUser.getResultObj();
						
						
					}
				}*/
				if(null==userId){
					String isTest = PropertiesUtils.getProperty(Const.isTest, Const.FILE_CONFIG);
					if("1".equals(isTest)){
						userId = Long.parseLong(PropertiesUtils.getProperty(Const.TEST_USERID, Const.FILE_CONFIG));
					}else{
						if(null != request.getUserPrincipal()){
							String username = request.getUserPrincipal().getName();
							if(!StringUtil.isEmpty(username)){
								IUserService userSerivce = ApplicationContextHelper.getInstance().getBean(IUserService.class);
								user = userSerivce.getUserByLoginUsername(username);
								userId = (user==null?null:user.getUserid());
								//request.getSession().setAttribute(Constant.PRO_USER, user);
								//request.getSession().setAttribute(Constant.PRO_USERID, userId);
								
								UserCacheHelper.SaveUserIdToCache(request.getSession(), userId.longValue());
								UserCacheHelper.SaveUserToCache(request.getSession(), user);
							}
						}
					}
					//测试数据 写死
					/*IUserService userSerivce = ApplicationContextHelper.getInstance().getBean(IUserService.class);
					user = userSerivce.getUserById(userId);*/
//					request.getSession().setAttribute(Constant.PRO_USER, user);
//					request.getSession().setAttribute(Constant.PRO_USERID, userId);
//					Userbinding ub = new Userbinding();
//					BaseAction4Wechat.SaveUserBindingInfo(request.getSession(), ub);
					//测试数据 写死
				}
//				ba.setUserId(userId);
//				ba.setUser(user);
			}
		}
		return super.preHandle(request, response, handler);
	}
	
	
	
	private void clearSession(HttpSession session,long userId){
		UserCacheHelper.DeleteUserIdFromCache(session);
		if(userId>0){
			UserCacheHelper.DeleteUserFromCache(session, userId);
		}
		
	}

}
