package com.viptrip.wetrip.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.aspect.CacheAspect;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.util.DESEncrypt;
import com.viptrip.util.JSON;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.wechat.config.Config;
import com.viptrip.wechat.model.WechatBase;
import com.viptrip.wechat.service.cache;
import com.viptrip.wechat.servlet.WechatDispatch;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.service.impl.UserService;
import com.viptrip.wetrip.vo.SSOLoginVo;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class BaseAction4Wechat {
	private static boolean isPCTest = false;

	private static String myTestOpenid = "oHDrpvxGgXF5mDexZ52M-yBg9qnk";
	private static String myTestMobile = "13260062199";
	
	private static final Logger log = LoggerFactory.getLogger(BaseAction4Wechat.class);

	/**
	 * 保存微信绑定基础信息
	 * 
	 * @param session
	 * @param wb
	 * @return
	 */
	public static OutputSimpleResult SaveWechatBaseInfo(HttpSession session,
			WechatBase wb) {
		return cache.SaveToCache(session, wb);
	}

	/**
	 * 获取微信绑定基础信息
	 * 
	 * @param session
	 * @return
	 */
	public static WechatBase GetWechatBaseInfo(HttpSession session) {
		WechatBase wb = null;

		if (isPCTest) {
			wb = new WechatBase();
			wb.setPAID(Config.Wechat_AppID);
			wb.setOpenID(myTestOpenid);
			wb.setMobile(myTestMobile);
		} else {
			OutputResult<WechatBase, String> or = cache.GetFromCache(
					WechatBase.class, session);
			if (or.IsSucceed()) {
				wb = or.getResultObj();
			}
		}

		return wb;
	}

	/**
	 * 删除微信绑定基础信息
	 * 
	 * @param session
	 */
	public static void DeleteWechatBaseInfo(HttpSession session) {
		cache.DeleteFromCache(WechatBase.class, session);
	}

	/**
	 * 保存用户绑定信息
	 * 
	 * @param session
	 * @param ub
	 * @return
	 */
	public static OutputSimpleResult SaveUserBindingInfo(HttpSession session,
			Userbinding ub) {
		return cache.SaveToCache(session, ub);
	}

	/**
	 * 获取用户绑定信息
	 * 
	 * @param session
	 * @return
	 */
	public static Userbinding GetUserBindingInfo(HttpSession session) {
		Userbinding ub = null;

		if (isPCTest) {
			ub = new Userbinding();
			ub.setUserId(299910l);// 测试销售，绑定的手机号是13260062199
			ub.setPaid(Config.Wechat_AppID);
			ub.setOpenid(myTestOpenid);
			ub.setMobile(myTestMobile);
			ub.setBindState((long) MyEnum.WechatBindState.已绑定.getValue());
		} else {
			OutputResult<Userbinding, String> or = cache.GetFromCache(
					Userbinding.class, session);
			if (or.IsSucceed()) {
				ub = or.getResultObj();
			}
		}
		//if(log.isDebugEnabled()){
			log.info(MessageFormat.format("isPCTest:{0}", isPCTest));
			log.info(MessageFormat.format("UserBindingInfo:{0}", ub!=null?
					etuf.v1_0.common.JSON.ObjectToJson(ub):"null"));
		//}

		return ub;
	}
	
	public static OutputSimpleResult UpdateUserBindingInfo(HttpSession session,MyEnum.WechatBindState bindState){
		OutputSimpleResult osr=new OutputSimpleResult();
		Userbinding ub=GetUserBindingInfo(session);
		if(ub!=null){
			if(ub.getBindState().intValue()!=bindState.getValue()){
				ub.setBindState((long)bindState.getValue());
				osr=SaveUserBindingInfo(session, ub);
			}
		}
		
		return osr;
	}

	/**
	 * 删除用户绑定信息
	 * 
	 * @param session
	 */
	public static void DeleteUserBindingInfo(HttpSession session) {
		cache.DeleteFromCache(Userbinding.class, session);
	}

	/**
	 * 用户是否已经绑定（即是否已经微信登录） 该方法已废弃，请调用BaseAction的isUserLogin方法
	 * 
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean isUserBinding(HttpSession session) {
		boolean isBind = false;
		Userbinding ub = GetUserBindingInfo(session);
		if (ub != null) {
			isBind = ub.getBindState().intValue() == MyEnum.WechatBindState.已绑定.getValue();
		}

		return isBind;
	}

	/**
	 * 保存重定向url以及参数信息
	 * 
	 * @param session
	 * @param redirectUrl
	 * @param isAbsolutePath
	 * @param paramStr
	 * @return
	 */
	public static OutputSimpleResult SaveRedirectParams(HttpSession session,
			String redirectUrl, boolean isAbsolutePath, String paramStr) {
		OutputSimpleResult osr = new OutputSimpleResult();

		WechatBase wb = GetWechatBaseInfo(session);
		if (wb != null) {
			wb.setAbsolutePath(isAbsolutePath);
			wb.setRedirectUrl(redirectUrl);
			wb.setRedirectParamStr(paramStr);
			osr = SaveWechatBaseInfo(session, wb);
		} else {
			osr.result = "微信网页授权信息丢失，请回到公众号主页后重新操作。";
		}

		return osr;
	}

	/**
	 * 微信cas免登
	 * @param req
	 * @param resp
	 * @param callbackUrl cas回调相对地址，为空时，系统将自动处理，跳转到首页
	 * @param loginName
	 * @return
	 */
	public static OutputSimpleResult CASLogin4Wechat(HttpServletRequest req,
			HttpServletResponse resp, String callbackUrl,String loginName) {
		OutputSimpleResult osr = new OutputSimpleResult();
		WechatBase wb = GetWechatBaseInfo(req.getSession());
		if (wb != null) {
			// 绑定成功，CAS登录
			try {
				String encodeCallBackUrl = GetEncodedFullCallBackUrl(req,resp,callbackUrl);
				String loginURL = PropertiesUtils.getProperty(
						Const.URL_CAS_LOGIN, Const.FILE_CONFIG);
				SSOLoginVo login = new SSOLoginVo();
				// 获取service层实例
				UserService service = ApplicationContextHelper.getInstance()
						.getBean(UserService.class);
				TAcUser user = service.getUserByLoginUsername(loginName);
				if (user == null)
					user = new TAcUser();
				login.setUser(loginName);
				login.setPass(user.getPassword());
				login.setSubmitDirection(true);
				String url;
				url = MessageFormat
						.format("{0}?{1}",
								loginURL,
								MessageFormat
										.format(com.viptrip.base.common.Config.ssoGetParamsFormat,
												encodeCallBackUrl,
												URLEncoder.encode(
														DESEncrypt
																.encrypt3DES(JSON
																		.get()
																		.toJson(login)),
														"utf-8")));
				osr.code = Constant.Code_Succeed;
				osr.result = url;
			} catch (Exception e) {
				e.printStackTrace();
				osr.result = "错误代码：201708160756。错误信息：异常错误。";
			}
		}else{
			osr.result = "错误代码：201708171204。错误信息：微信基本参数信息为空，请尝试重新进入公众号重试。";
		}
		return osr;
	}
	
	private static String GetEncodedFullCallBackUrl(HttpServletRequest req,HttpServletResponse resp,String callbackUrl) 
			throws IOException{
		String fullPath="";
		String path = req.getRequestURL().toString();
		String rootPath = path.replaceAll(req.getServletPath(), "");				
		if(etuf.v1_0.common.Common.IsNullOrEmpty(callbackUrl)){
			if(!rootPath.endsWith("/")){
				rootPath+="/";//iis映射到tomcat网站，需要后面的斜线
			}
			fullPath = WechatDispatch.Dispath_Redirect(req.getSession(), resp,rootPath);
		}else{
			fullPath=MessageFormat.format("{0}{2}{1}", rootPath,callbackUrl,
					(!rootPath.endsWith("/") && !callbackUrl.startsWith("/"))?"/":"");
		}
		
		return URLEncoder.encode(fullPath, "utf-8");// 回调URL;
	}

	/**
	 * 微信cas登出
	 * @param req
	 * @param resp
	 * @param callbackUrl
	 * @return
	 */
	public static OutputSimpleResult CASLogout4Wechat(HttpServletRequest req,
			HttpServletResponse resp, String callbackUrl){
		OutputSimpleResult osr = new OutputSimpleResult();
			try {
				String encodeCallBackUrl = GetEncodedFullCallBackUrl(req,resp,callbackUrl);
				String logoutURL = PropertiesUtils.getProperty(
						Const.URL_CAS_LOGOUT, Const.FILE_CONFIG);
				String url;
				url = MessageFormat
						.format("{0}?{1}",
								logoutURL,
								MessageFormat
										.format(com.viptrip.base.common.Config.ssoLogoutGetParamsFormat,
												encodeCallBackUrl));
				osr.code = Constant.Code_Succeed;
				osr.result = url;
			} catch (Exception e) {
				e.printStackTrace();
				osr.result = "错误代码：201708251053。错误信息：异常错误。";
			}
		return osr;
	}
}
