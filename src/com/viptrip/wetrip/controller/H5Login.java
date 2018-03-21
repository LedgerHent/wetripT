package com.viptrip.wetrip.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.model.Request_H5UserLogin;
import com.viptrip.wetrip.model.Response_H5UserLogin;
import com.viptrip.wetrip.model.userlogin.LoginMessage;
import com.viptrip.wetrip.model.userlogin.UserModel;
import com.viptrip.wetrip.service.impl.H5LoginService;

import etuf.v1_0.common.Common;
import etuf.v1_0.model.base.output.OutputResult;

public class H5Login extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -71345227242724373L;
	
	//获取service层实例
	H5LoginService loginSer = ApplicationContextHelper.getInstance().getBean(H5LoginService.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setStatus(HttpServletResponse.SC_OK);
		
		String platformId = req.getParameter("platformId");
		String pwd = req.getParameter("pwd");
			
		String errorMessage = "";//提示信息
		if(checkPfNull(platformId,pwd)&&checkPidPwd(platformId,pwd)){
			//用户信息
			String uid = req.getParameter("uid");
			String name = req.getParameter("name");
			String mobile = req.getParameter("mobile");
			name = new String(name.getBytes("iso-8859-1"), "utf-8");  
			String email  = req.getParameter("email");
			String idType = req.getParameter("idType");
			String idNum = req.getParameter("idNum");
			
			Request_H5UserLogin rqul = new Request_H5UserLogin();
			rqul.data=new LoginMessage();
			rqul.data.user = new UserModel();
			rqul.data.platformId=Long.valueOf(platformId);
			rqul.data.user.uid=uid;
			rqul.data.user.name=name;
			rqul.data.user.mobile=mobile;
			rqul.data.user.email=email;
			rqul.data.user.idType=Common.IsNullOrEmpty(idType)==true?0:Integer.parseInt(idType);
			rqul.data.user.idNum=idNum;
			
			
			
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
			errorMessage = "平台ID或密码不能为空，请确认输入！";
		}
		
		req.setAttribute("result", errorMessage);
		if("成功".equals(errorMessage)){
			req.getRequestDispatcher("/WEB-INF/page/public/testH5Success.jsp").forward(req, res);
			
		}else req.getRequestDispatcher("/WEB-INF/page/public/failure.jsp").forward(req, res);

	}

	/**
	 * 非空判断
	 * @param platformId
	 * @param pwd
	 * @return
	 */
	private boolean checkPfNull(String platformId, String pwd) {
		if(!Common.IsNullOrEmpty(platformId)){
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
	private boolean checkPidPwd(String pid, String pwd) {
		if(loginSer.checkPlatf(Long.valueOf(pid),pwd)){//密码正确
			return true;
		}else return false;
	}
	
	
}
