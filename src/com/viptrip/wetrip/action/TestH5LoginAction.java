package com.viptrip.wetrip.action;

import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.viptrip.base.action.BaseAction;
import com.viptrip.resource.Const;
import com.viptrip.uban360.config.Config;
import com.viptrip.util.DESEncrypt;
import com.viptrip.util.JSON;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.wetrip.controller.H5UserLogin;
import com.viptrip.wetrip.model.Request_H5UserLogin;
import com.viptrip.wetrip.model.Response_H5UserLogin;
import com.viptrip.wetrip.model.userlogin.LoginMessage;
import com.viptrip.wetrip.model.userlogin.UserInfoByToken;
import com.viptrip.wetrip.model.userlogin.UserModel;
import com.viptrip.wetrip.util.CommUtil;
import com.viptrip.wetrip.vo.CaiyunAccessToken;
import com.viptrip.wetrip.vo.SSOLoginVo;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;
@Controller
@RequestMapping("/testH5Login")
@Scope("prototype")
public class TestH5LoginAction extends BaseAction{
	private static Logger logger = LoggerFactory.getLogger(TestH5LoginAction.class);
	private static final String toError = "public/failure";//跳转页面

	@RequestMapping("/testH5.act")
	public String test(String token) throws Exception {
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		
		logger.debug("basePath=" + basePath);
		
		String callBackUrl = URLEncoder.encode(basePath,"utf-8");//回调URL
		String loginURL = PropertiesUtils.getProperty(Const.URL_CAS_LOGIN,Const.FILE_CONFIG);
//		String logoutURL = PropertiesUtils.getProperty(Const.URL_CAS_LOGOUT,Const.FILE_CONFIG);
		SSOLoginVo login = new SSOLoginVo();
		login.setUser("18701244585");
		login.setPass(DigestUtils.md5Hex(com.viptrip.base.common.Config.defaultPassword));
		login.setSubmitDirection(true);
		
		String url = MessageFormat.format("{0}?{1}",loginURL, 
				MessageFormat.format(com.viptrip.base.common.Config.ssoGetParamsFormat, 
						callBackUrl,URLEncoder.encode(DESEncrypt.encrypt3DES(JSON.get().toJson(login)),"utf-8")));
		
		

		if(logger.isDebugEnabled())
			logger.debug("TestH5LoginAction.test()==url==" + url);
		return "redirect:" +  url;
			
		
	}
}
