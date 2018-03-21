package com.viptrip.wetrip.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.uban360.config.Config;
import com.viptrip.wetrip.model.userlogin.UserInfoByToken;
import com.viptrip.wetrip.service.impl.H5LoginService;

import etuf.v1_0.common.Common;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class H5AvoidLogin extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -71345227242724373L;
	
	//获取service层实例
	H5LoginService loginSer = ApplicationContextHelper.getInstance().getBean(H5LoginService.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
/*
		String accessTokenParm = MessageFormat.format("appId={0}&appSecret={1}", Config.appId,Config.appSecret);
		//获取accessToken
		OutputSimpleResult  out = etuf.v1_0.common.HttpHelper.HttpGet(Config.ubanBaseUrl+"token/get", accessTokenParm);
		if(out.IsSucceed()){
//			out.result;//返回结果
			UbanAccessToken uban = new Gson().fromJson(out.result, UbanAccessToken.class);
			if(uban!=null&&uban.status==0){
				//
				System.out.println(uban.data.accessToken);
				
			}
		}else{
			System.out.println(out.result);
		}*/
		
		
		
		
		
		String tokenParm = MessageFormat.format("token=1&accessToken={1}", "accessToken");
		//获取accessToken
		OutputSimpleResult  out = etuf.v1_0.common.HttpHelper.HttpGet(Config.ubanBaseUrl+"auth/getUserInfoByToken", tokenParm);
		if(out.IsSucceed()){
//			out.result;//返回结果
			UserInfoByToken userToken = new Gson().fromJson(out.result, UserInfoByToken.class);
			if(userToken!=null&&userToken.status==0){
				//
				System.out.println(userToken.data);
				
			}else System.out.println(out.exception);
		}else{
			System.out.println(out.result);
		}
		
		/*res.setStatus(HttpServletResponse.SC_OK);
		
		String data = req.getParameter("data");
		data = URLDecoder.decode(data, "utf-8");
		
		String errorMessage = "";//提示信息
		try {
			AvoidLoginH5Model avoidL = new Gson().fromJson(data, AvoidLoginH5Model.class);
			
			if(checkPfNull(avoidL.pid,avoidL.authcode)){
				if(checkPidPwd(avoidL.pid,avoidL.authcode)){
					//用户信息
					
					Request_H5UserLogin rqul = new Request_H5UserLogin();
					rqul.data=new LoginMessage();
					rqul.data.user = new UserModel();
					rqul.data.platformId=Long.valueOf(avoidL.pid);
					rqul.data.user.uid=avoidL.uid;
					avoidL.name = new String(avoidL.name.getBytes("iso-8859-1"), "utf-8"); 
					rqul.data.user.name=avoidL.name;
					rqul.data.user.mobile=avoidL.mobile;
					rqul.data.user.email=avoidL.email;
					rqul.data.user.idType=avoidL.idType;
					rqul.data.user.idNum=avoidL.idNum;
					
					
					
					//调用H5
					H5UserLogin ul = new H5UserLogin();
					OutputResult<Response_H5UserLogin,String> or= 
							ul.ClientRequest(rqul,Response_H5UserLogin.class);
					
					if(or.IsSucceed()){//调用成功
						Response_H5UserLogin rpul=or.getResultObj();
						if(rpul.status== etuf.v1_0.model.base.Enum.StatusType.Succeed.getStatus()){//返回结果成功
							if(rpul.data!=null){
								if(rpul.data.userId!=0){
									//单点登录
									errorMessage = "成功";
									
									
									req.setAttribute("platformId", rqul.data.platformId);
									req.setAttribute("uid", rqul.data.user.uid);
									req.setAttribute("name", rqul.data.user.name);
									req.setAttribute("mobile", rqul.data.user.mobile);
									req.setAttribute("email", rqul.data.user.email);
									req.setAttribute("idType", rqul.data.user.idType);
									req.setAttribute("idNum", rqul.data.user.idNum);
		
									
								}
							}
							
						}else{
							errorMessage = or.result;
						}
						
					}else{
						errorMessage = "调用失败:"+or.result;
					}
					
				}else{
					errorMessage = "平台ID或授权密钥信息不正确，请确认输入！";
				}
				
			}else{
				errorMessage = "平台ID或授权密钥不能为空，请输入！";
			}
		} catch (Exception e) {
			errorMessage = "转换错误或系统bug";
		}
		
		req.setAttribute("result", errorMessage);
		if("成功".equals(errorMessage)){
			req.getRequestDispatcher("/WEB-INF/page/public/testH5Success.jsp").forward(req, res);
			
		}else req.getRequestDispatcher("/WEB-INF/page/public/failure.jsp").forward(req, res);

		*/
		
		
	}

	/**
	 * 非空判断
	 * @param platformId
	 * @param pwd
	 * @return
	 */
	private boolean checkPfNull(int pid, String pwd) {
		if(pid!=0){
			if(!Common.IsNullOrEmpty(pwd)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验密码，返回信息未实现 TODO 返回信息 
	 * @param pid
	 * @param pwd
	 * @return
	 */
	private boolean checkPidPwd(int pid, String pwd) {
		if(loginSer.checkPlatf(Long.valueOf(pid),pwd)){//密码正确
			return true;
		}else return false;
	}
	
	
}
