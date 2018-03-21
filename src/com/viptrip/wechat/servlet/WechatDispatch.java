package com.viptrip.wechat.servlet;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import com.viptrip.wechat.action.WechatAvoidLoginAction;
import com.viptrip.wechat.config.Config;
import com.viptrip.wechat.model.WechatBase;
import com.viptrip.wetrip.action.BaseAction4Wechat;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.service.impl.WechatBinding;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

import weixin.mp.api.WxMpInMemoryConfigStorage;
import weixin.mp.bean.result.WxMpOAuth2AccessToken;
import weixin.mp.bean.result.WxMpUser;

@Component(value="wechatDispatch")
@SuppressWarnings("serial")
public class WechatDispatch extends etuf.v1_0.wechat.OAuth2 {

	@Override
	protected WxMpInMemoryConfigStorage GetWechatConfig() {
		return Config.GetWechatConfig();
	}
	private static final Logger log = LoggerFactory.getLogger(WechatDispatch.class);
	@Override
	protected OutputSimpleResult DoDispatch(HttpServletRequest request,
			HttpServletResponse response, WxMpOAuth2AccessToken accessToken,
			WxMpUser user, String extendInfo) {
		OutputSimpleResult osr = new OutputSimpleResult();
		// 获取service层实例
		WechatBinding service = ApplicationContextHelper.getInstance().getBean(
				WechatBinding.class);
		OutputResult<Userbinding, String> or = service
				.HandleOAuth2BaseInfo(accessToken);
		if (or.IsSucceed()) {
			// 保存用户基本信息、用户绑定信息到redis

			// 根据扩展信息做页面跳转或者重定向
			OutputResult<MyEnum.WechatMenuType, String> orMT = GetWechatMenuType(extendInfo);
			if (orMT.IsSucceed()) {
				// 保存微信用户基本信息到缓存
				WechatBase wb = new WechatBase();
				wb.setPAID(config.getAppId());
				wb.setOpenID(accessToken.getOpenId());
				wb.setUnionID(accessToken.getUnionId());
				wb.setFromMenuType(orMT.getResultObj());
				wb.setMobile("");
				wb.setRedirectUrl("");
				wb.setRedirectParamStr("");
				osr = BaseAction4Wechat.SaveWechatBaseInfo(GetSession(), wb);
				if (osr.IsSucceed()) {
					// 保存用户绑定信息到缓存
					osr = BaseAction4Wechat.SaveUserBindingInfo(GetSession(),
							or.getResultObj());
					if (osr.IsSucceed()) {
						RedirectByMenuType(request, response,
								orMT.getResultObj(), or.getResultObj());
					}
				}
			} else {
				osr.result = orMT.result;
			}
		} else {
			osr.result = or.result;
			osr.exception = or.exception;
		}
		return osr;
	}

	private OutputResult<MyEnum.WechatMenuType, String> GetWechatMenuType(
			String extendInfo) {
		OutputResult<MyEnum.WechatMenuType, String> or = new OutputResult<>();
		if (!Common.IsNullOrEmpty(extendInfo)) {
			int value = StringUtil.getIntValue(extendInfo);
			if (value != -1) {
				MyEnum.WechatMenuType mt = MyEnum.WechatMenuType.getEnum(value);
				if (mt != null) {
					or.setResultObj(mt);
					or.code = Constant.Code_Succeed;
				} else {
					or.result = "微信授权用户扩展信息类型未知，请确认。";
				}
			} else {
				or.result = "微信授权用户扩展信息为数字，请确认。";
			}
		} else {
			or.result = "微信授权用户扩展信息为空，请确认。";
		}
		return or;
	}

	/**
	 * 根据菜单类型进行页面重定向
	 * 
	 * @param mt
	 *            菜单
	 * @param ub
	 *            用户绑定信息
	 */
	private void RedirectByMenuType(HttpServletRequest request,
			HttpServletResponse response, MyEnum.WechatMenuType mt,
			Userbinding ub) {
		if (ub == null) {
			ub = BaseAction4Wechat.GetUserBindingInfo(GetSession());
		}
		try {
			String path = request.getRequestURL().toString();
			String rootPath = path.replaceAll(request.getServletPath(), "");
			if (ub == null) {
				response.sendRedirect(rootPath
						+ "/failure.jsp?result=user binding info not found.");
			} else {
				String callback="/failure.jsp?result=invalid menutype.";					
				switch (mt) {
					case 重定向:
						callback=null;
						break;
					default:
						String tempUrl=GetDispatchUrl(mt.toString());
						if(!etuf.v1_0.common.Common.IsNullOrEmpty(tempUrl))
							callback=tempUrl;
						break;
				}
				boolean isBind = ub.getBindState() == MyEnum.WechatBindState.已绑定.getValue();
				if(!isBind){//未绑定，先绑定
					BaseAction4Wechat.SaveRedirectParams(request.getSession(), callback, false, "");
					response.sendRedirect(rootPath + com.viptrip.base.common.Config.wechatLoginActionUrl);
				}else{//已经绑定，CAS免登
					//保存信息到BaseAction 如何调用，已解决
					WechatAvoidLoginAction wa=ApplicationContextHelper.getInstance().getAction(WechatAvoidLoginAction.class);
					log.info("=========2========================="+ub.getMobile()+"  微信");
					wa.SaveWechatLoginInfo(request,response,ub.getMobile());
					OutputSimpleResult osr=BaseAction4Wechat.CASLogin4Wechat(request, response,callback, ub.getMobile());
					if(osr.IsSucceed()){
						System.out.println("***************"+osr.result);
						response.sendRedirect(osr.result);
					}else{
						response.sendRedirect(rootPath
								+ "/failure.jsp?result="+osr.result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String GetDispatchUrl(String menuName)
	{
		String url=null;
		String configKey=MessageFormat.format("{0}.{1}", Const.PRO_WECHAT_DISPATCHER,menuName);
		try {
			url=PropertiesUtils.getProperty(configKey, Const.FILE_WECHAT_CONFIG);
		} catch (IOException e) {
			url="/failure.jsp?result=Read wechat dispatcher config fail:"+configKey;
		}
		return url;
	}

	public static String Dispath_Redirect(HttpSession session,HttpServletResponse response, String rootPath)
			throws IOException {
		response.setHeader("referer","we.viptrip365.com/wetrip/");
		WechatBase wb = BaseAction4Wechat
				.GetWechatBaseInfo(session);
		String url = "";
		if (wb != null) {
			if (!etuf.v1_0.common.Common.IsNullOrEmpty(wb
					.getRedirectUrl())) {
				url = wb.getRedirectUrl();
					if (!wb.isAbsolutePath()) {
						if (!url.startsWith("/"))
							url = "/" + url;
						url = rootPath + url;
					}
			} else {//退出后，重登陆，此时为空，为空时，默认去首页
				url = rootPath;
				if (!url.endsWith("/")){
					url+="/";
				}
						//+ "/failure.jsp?result=redirect url is null.";
			}
		} else {
			url = rootPath
					+ "/failure.jsp?result=wechat base info is null.";
		}
		return url;
	}
/*	public static void Dispath_Redirect(HttpSession session,HttpServletResponse response, String rootPath)
			throws IOException {
		WechatBase wb = BaseAction4Wechat
				.GetWechatBaseInfo(session);
		if (wb != null) {
			if (!etuf.v1_0.common.Common.IsNullOrEmpty(wb
					.getRedirectUrl())) {
				String url = wb.getRedirectUrl();
				if (!etuf.v1_0.common.Common.IsNullOrEmpty(wb
						.getRedirectParamStr())) {
					url = MessageFormat.format("{0}{1}{2}={3}",
							url, url.contains("?") ? "&" : "?",
									Config.Wechat_RedirectParameterKey,
									wb.getRedirectParamStr());
					if (wb.isAbsolutePath()) {
						response.sendRedirect(url);
					} else {
						if (!url.startsWith("/"))
							url = "/" + url;
						response.sendRedirect(rootPath + url);
					}
				}
			} else {
				response.sendRedirect(rootPath
						+ "/error/returnError.act?result=redirect url is null.");
			}
		} else {
			response.sendRedirect(rootPath
					+ "/error/returnError.act?result=wechat base info is null.");
		}
	}
*/
}
