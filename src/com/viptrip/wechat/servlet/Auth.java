package com.viptrip.wechat.servlet;

import javax.servlet.http.HttpServletRequest;

import com.viptrip.wechat.config.Config;

import weixin.mp.api.WxMpInMemoryConfigStorage;

@SuppressWarnings("serial")
public class Auth extends etuf.v1_0.wechat.AuthIndex {

	
	@Override
	protected String GetOAuth2RedirectUrl(HttpServletRequest request) {
		String path=request.getRequestURL().toString();
		int lastIndex =path.lastIndexOf("/");
		if(lastIndex>0){
			path=path.substring(0,lastIndex);
			path+="/wechatDispatch";
		}
		return path; 
	}

	@Override
	protected String GetOAuth2UrlParameterUserExtendInfo(HttpServletRequest request) {
		return request.getParameter(Config.Wechat_HttpParameterKey_UserExtendInfo);
	}

	@Override
	protected WxMpInMemoryConfigStorage GetWechatConfig() {
		return Config.GetWechatConfig();
	}

	
	


}
