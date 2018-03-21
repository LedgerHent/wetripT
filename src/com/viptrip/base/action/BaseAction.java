package com.viptrip.base.action;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.GsonBuilder;
import com.viptrip.base.cache.UserCacheHelper;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.UserPlatform;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.base.resource.Constant;
import com.viptrip.util.StringUtil;
import com.viptrip.wechat.action.WechatAvoidLoginAction;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.service.impl.UserService;

import etuf.v1_0.model.base.output.OutputResult;

@Controller
public class BaseAction {

	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected String baseURL;

	private Model model;
	
	private static final Logger log = LoggerFactory.getLogger(BaseAction.class);
	/**
	 * 保存用户登录信息
	 * 
	 * @param user
	 * @param platform
	 */
	public void SaveUserLoginInfo(HttpServletRequest request,HttpServletResponse response,TAcUser user, MyEnum.UserPlatform platform) {
		if(request!=null) setReq(request);
		if(response!=null) setResp(response);
		if (req!=null) {
			setPlatform(platform);
			if (user != null){
				setUser(user);
				setUserId(user.getUserid());
			}
		}
	}

	/**
	 * 保存用户登录信息
	 * 
	 * @param request
	 * @param response
	 * @param loginName
	 *            登录名
	 * @param platform
	 */
	public void SaveUserLoginInfo(HttpServletRequest request,HttpServletResponse response,String loginName, MyEnum.UserPlatform platform) {
		TAcUser user = getUser(loginName);
		SaveUserLoginInfo(request,response,user, platform);
	}

	/**
	 * 用户登出，清理redis信息
	 */
	public void UserLogout(){
		if(req!=null){
			deleteUserInfo();
		}
	}
	
	@ModelAttribute
	public void preExecute(Model model) {
		this.model = model;
		model.addAttribute(Constant.PRO_USERID, getUserId());
		model.addAttribute(Constant.PRO_USER, getUser());
		if (!isAjaxMethod()) {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
		}

	}

	protected void addAttr(String key, Object obj) {
		model.addAttribute(key, obj);
	}

	protected void addAttr(Object obj) {
		model.addAttribute(obj);
	}

	protected void addAttr(Collection<?> collection) {
		model.addAllAttributes(collection);
	}

	protected void addAttr(Map<String, ?> map) {
		model.addAllAttributes(map);
	}

	/**
	 * 返回AjaxResp对象
	 * 
	 * @param code
	 * @param obj
	 * @return
	 */
	protected AjaxResp resp(Integer code, Object obj) {
		return new AjaxResp(code, obj);
	}

	/**
	 * 返回成功
	 * 
	 * @return
	 */
	protected AjaxResp success() {
		return new AjaxResp(AjaxStatus.success);
	}

	/**
	 * 返回成功 自定义消息
	 * 
	 * @return
	 */
	protected AjaxResp success(Object obj) {
		AjaxResp result = new AjaxResp(AjaxStatus.success);
		if (null != obj)
			result.setData(obj);
		return result;
	}

	/**
	 * 返回失败
	 * 
	 * @return
	 */
	protected AjaxResp fail() {
		return new AjaxResp(AjaxStatus.fail);
	}

	/**
	 * 返回失败 自定义消息
	 * 
	 * @return
	 */
	protected AjaxResp fail(Object obj) {
		AjaxResp result = new AjaxResp(AjaxStatus.fail);
		if (null != obj)
			result.setData(obj);
		return result;
	}

	/**
	 * 判断请求是否为ajax请求
	 * 
	 * @return
	 * @throws IOException
	 */
	protected boolean isAjaxMethod() {
		// X-Requested-With
		// XMLHttpRequest
		if ("XMLHttpRequest"
				.equalsIgnoreCase(req.getHeader("X-Requested-With"))) {
			return true;
		}
		return false;
	}

	/**
	 * 异步写入消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	protected void ajaxWrite(String msg) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		if (!StringUtil.isEmpty(msg)) {
			resp.getOutputStream().write(msg.getBytes("utf-8"));
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}
	}

	/**
	 * 异步返回jsonobject
	 * 
	 * @param obj
	 * @throws IOException
	 */
	protected void ajaxWrite(Object obj) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		if (null != obj) {
			String msg = new GsonBuilder().create().toJson(obj);
			if (!StringUtil.isEmpty(msg)) {
				resp.getOutputStream().write(msg.getBytes("utf-8"));
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}
		}
	}

	public HttpServletRequest getReq() {
		return req;
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public HttpServletResponse getResp() {
		return resp;
	}

	public void setResp(HttpServletResponse resp) {
		this.resp = resp;
	}

	/*
	 * public FileCacher getCacher() { return cacher; }
	 * 
	 * public void setCacher(FileCacher cacher) { this.cacher = cacher; }
	 */

	public Long getUserId() {
		Long userId = null;
		if (req != null) {
			OutputResult<Long, String> or = UserCacheHelper
					.GetUserIdFromCache(req.getSession());
			if (or.IsSucceed()) {
				userId = or.getResultObj();
			}
		}
		return userId;
	}

	public void setUserId(Long userId) {
		UserCacheHelper.SaveUserIdToCache(req.getSession(), userId.longValue());
	}
	
	public TAcUser getUser() {
		TAcUser user = null;
		Long userId = getUserId();
		if (userId != null &&req!=null) {
			OutputResult<TAcUser, String> orUser = UserCacheHelper
					.GetUserFromCache(req.getSession(), userId.longValue());
			if (orUser.IsSucceed()) {
				user = orUser.getResultObj();
			}
		}
		return user;
	}

	public TAcUser getUser(String loginName) {
		TAcUser user = getUser();
		if (user == null) {
			UserService us = ApplicationContextHelper.getInstance().getBean(
					UserService.class);
			user = us.getUserByLoginUsername(loginName);
		}
		return user;
	}

	public void setUser(TAcUser user) {
		UserCacheHelper.SaveUserToCache(req.getSession(), user);
	}
	
	public void deleteUserInfo(){
		Long userId=getUserId();
		if(userId!=null){
			UserCacheHelper.DeleteUserFromCache(req.getSession(), userId);
		}
		UserCacheHelper.DeleteUserIdFromCache(req.getSession());
	}

	public Model getModel() {
		return model;
	}

	/**
	 * 判断用户是否已登录
	 * 
	 * @return
	 */
	public boolean isUserLogin() {
		return getUser() != null;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public MyEnum.UserPlatform getPlatform() {
		MyEnum.UserPlatform p = UserPlatform.H5;
		if (req != null) {
			OutputResult<MyEnum.UserPlatform, String> or = UserCacheHelper
					.GetUserPlatformFromCache(req.getSession());
			if (or.IsSucceed()) {
				p = or.getResultObj();
			}
		}
		
		return p;
	}

	public void setPlatform(MyEnum.UserPlatform platform) {
		UserCacheHelper.SaveUserPlatformToCache(req.getSession(), platform);
	}
}
