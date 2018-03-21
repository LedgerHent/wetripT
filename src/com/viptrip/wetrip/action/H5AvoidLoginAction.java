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
import com.viptrip.base.common.MyEnum;
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
@RequestMapping("/h5Login")
@Scope("prototype")
public class H5AvoidLoginAction extends BaseAction{
	private static Logger logger = LoggerFactory.getLogger(H5AvoidLoginAction.class);
	private static final String toError = "public/failure";//跳转页面

	@RequestMapping("/h5.act")
	public String test(String token) throws Exception {
		String path = req.getContextPath();
		String port = req.getServerPort()==80?"":":"+req.getServerPort();
		String basePath = req.getScheme()+"://"+req.getServerName()+port+path+"/";
		
		String errorMessage = "";//提示信息
		
//		String accessTokenParm = MessageFormat.format("appId={0}&appSecret={1}", Config.appId,Config.appSecret);
//		OutputSimpleResult  out = etuf.v1_0.common.HttpHelper.HttpGet(Config.ubanBaseUrl+"token/get", accessTokenParm);
		Request_H5UserLogin rqul = null;
		Response_H5UserLogin rpul = null;
		//获取accessToken
		String accessToken = null;
		CaiyunAccessToken ctoken = CommUtil.getAccessToken();
		if(null == ctoken){
			ctoken = CommUtil.refreshAccessToken();
		}
		if(null != ctoken){
			if(0==ctoken.getCode()){
				accessToken = ctoken.getAccessToken();
			}
		}
		
/*		String accessToken = CommUtil.getAccessToken();
		if(etuf.v1_0.common.Common.IsNullOrEmpty(accessToken)){
			accessToken = CommUtil.refreshAccessToken();
		}
*/		if(!etuf.v1_0.common.Common.IsNullOrEmpty(accessToken)){
			//获取用户信息
			String tokenParm = MessageFormat.format("token={0}&accessToken={1}", token,accessToken);
			logger.info("调用讯盟接口getUserInfoByToken获取用户信息，参数为→" + tokenParm);
			OutputSimpleResult  out = etuf.v1_0.common.HttpHelper.HttpGet(Config.ubanBaseUrl+"auth/getUserInfoByToken", tokenParm);
			logger.info("调用讯盟接口getUserInfoByToken获取用户信息，结果为→" + JSON.get().nullSerialize(true).toJson(out));
			if(out.IsSucceed()){
				//解析获取的用户信息
				UserInfoByToken userToken = new Gson().fromJson(out.result, UserInfoByToken.class);
				if(userToken!=null&&userToken.status==0){
					try {
						//将获取的用户信息赋值到免登请求model
						rqul = new Request_H5UserLogin();
						rqul.data=new LoginMessage();
						rqul.data.user = new UserModel();
						rqul.data.platformId=Config.platformId;//TODO 固定值，测试用，以后修改
						rqul.data.user.uid=userToken.data.uid;//AQAIAAAAAAAAALDB2QoAAAAA
						rqul.data.user.name=userToken.data.name;
						if(etuf.v1_0.common.Common.IsNullOrEmpty(userToken.data.mobile))
							userToken.data.mobile="18701244585";
						rqul.data.user.mobile=userToken.data.mobile;
		//				rqul.data.user.email=avoidL.email;
		//				rqul.data.user.idType=avoidL.idType;
		//				rqul.data.user.idNum=avoidL.idNum;
						
						//调用H5
						H5UserLogin ul = new H5UserLogin();
						OutputResult<Response_H5UserLogin,Response_BaseMsg> or= 
								ul.ClientRequest(rqul,Response_H5UserLogin.class,Response_BaseMsg.class);
						
						if(or.IsSucceed()){//调用成功
							rpul=or.getResultObj();
							if(rpul.status== etuf.v1_0.model.base.Enum.StatusType.Succeed.getStatus()){//返回结果成功
								if(rpul.data!=null){
									if(rpul.data.userId!=0){
										errorMessage = "成功";//单点登录
									}
								}
							}else{
								errorMessage = or.getErrorObj().msg;
							}
						}else{
							errorMessage = or.getErrorObj().msg;
						}
					} catch (Exception e) {
						errorMessage = "转换错误或系统bug";
					}
				}else errorMessage = "".equals(userToken.message)?out.result:userToken.message+"\n " + out.result;
			}else{
				System.out.println("out.result======"+out.result);
				errorMessage = out.result;
			}
		}else{
			errorMessage = ctoken.getMsg();
		}
		
		if("成功".equals(errorMessage)){
			//设置基本登录信息。
			SaveUserLoginInfo(req,resp,rqul.data.user.mobile, MyEnum.UserPlatform.杭州讯盟);
			logger.info("=================================="+rqul.data.user.mobile+"  杭州讯盟");
			
			String callBackUrl = URLEncoder.encode(basePath,"utf-8");//回调URL
			String loginURL = PropertiesUtils.getProperty(Const.URL_CAS_LOGIN,Const.FILE_CONFIG);
			String logoutURL = PropertiesUtils.getProperty(Const.URL_CAS_LOGOUT,Const.FILE_CONFIG);
			SSOLoginVo login = new SSOLoginVo();
			login.setUser(rqul.data.user.mobile);
			login.setPass(DigestUtils.md5Hex(com.viptrip.base.common.Config.defaultPassword));
			login.setSubmitDirection(true);
			
			
			/*String url = MessageFormat.format("{0}?{1}",loginURL, 
					MessageFormat.format(com.viptrip.base.common.Config.ssoGetParamsFormat, 
							callBackUrl,rqul.data.user.mobile,DigestUtils.md5Hex(
									com.viptrip.base.common.Config.defaultPassword),"true"));*/
			
			String url = MessageFormat.format("{0}?{1}",loginURL, 
					MessageFormat.format(com.viptrip.base.common.Config.ssoGetParamsFormat, 
							callBackUrl,URLEncoder.encode(DESEncrypt.encrypt3DES(JSON.get().toJson(login)),"utf-8")));
			
			

			if(logger.isDebugEnabled())
				logger.debug("H5AvoidLoginAction.test()==url==" + url);
			return "redirect:" +  url;
			
		}else{
			if(!errorMessage.contains("24小时客服")){
				errorMessage = errorMessage + 
						MessageFormat.format( "如需帮助，请致电凯撒商旅24小时客服服务热线<a href=\"tel:{0}\">{0}</a>。",
								com.viptrip.base.common.Config.customerHotService);
			}
			addAttr("result",errorMessage);
			if(logger.isDebugEnabled())
				logger.debug("H5AvoidLoginAction.test()==errorMessage==" + errorMessage);
			return toError;
		}
		
	}
}
