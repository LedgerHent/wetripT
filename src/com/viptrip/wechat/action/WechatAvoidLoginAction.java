package com.viptrip.wechat.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.util.DESEncrypt;
import com.viptrip.util.JSON;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.wechat.model.WechatBase;
import com.viptrip.wechat.servlet.WechatDispatch;
import com.viptrip.wetrip.action.BaseAction4Wechat;
import com.viptrip.wetrip.action.BindUserAction;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_BindUser;
import com.viptrip.wetrip.service.impl.UserService;
import com.viptrip.wetrip.service.impl.WechatAvoidLogin;
import com.viptrip.wetrip.vo.SSOLoginVo;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 微信免登陆
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/weAvoidLogin")
public class WechatAvoidLoginAction extends BaseAction{
	private static final String toError = "public/failure";//跳转页面
	private static final String toLogin = "public/login";//跳转页面
	
	private static final Logger log = LoggerFactory.getLogger(WechatAvoidLoginAction.class);
	
	@RequestMapping("/login.act")
	public String weLogin() throws ServletException, IOException{
		log.debug("++begin login++");
		// 获取service层实例
		WechatAvoidLogin service = ApplicationContextHelper.getInstance().getBean(WechatAvoidLogin.class);
		log.debug("++get WechatAvoidLogin service++");
		WechatBase wb = BaseAction4Wechat.GetWechatBaseInfo(req.getSession());
		log.debug("++get WechatBase++");
		if(wb==null){
			log.debug("++    WechatBase:null++");
			//返回错误提示信息
			addAttr("result","微信网页授权信息丢失，请回到公众号主页后重新操作。");
			return toError;
		}else{
			log.debug("++    WechatBase:"+etuf.v1_0.common.JSON.ObjectToJson(wb));
			log.debug("++ SaveUserLoginInfo "+wb.getMobile()+"++");
			//设置基本登录信息。
			log.info("=========1========================="+wb.getMobile()+"  微信");
			SaveWechatLoginInfo(getReq(),getResp(),wb.getMobile());
			log.debug("++ ifBinding2Login++");
			//查看是否已绑定是否可登录
			OutputResult<Request_BindUser, String> or = service.ifBinding2Login(wb);
			//判断是否已绑定
			if(or.IsSucceed()){
				//已绑定，重定向到目标页面。调用CAS
				OutputSimpleResult osr=BaseAction4Wechat.CASLogin4Wechat(req, resp, null,wb.getMobile());
				if(osr.IsSucceed()){
					return "redirect:" + osr.result;
				}else{
					addAttr("result", osr.result);
					return toError;
				}
			}else{
				if("redirect".equals(or.result)){
					//跳转到绑定页面，传值
					addAttr("data",or.getResultObj().data);
					return toLogin;
				}else{
					//返回错误提示信息
					addAttr("result",or.result);
					return toError;
					
				}
			}
		}
	}
	
	public void SaveWechatLoginInfo(HttpServletRequest request,HttpServletResponse response,String loginName){
		SaveUserLoginInfo(request,response,loginName, MyEnum.UserPlatform.微信);
	}
}
