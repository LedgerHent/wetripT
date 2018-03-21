package com.viptrip.hotel.controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.dxpt.entity.Message;
import com.dxpt.service.impl.MessageServiceImpl;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_SendEmail4Hotel;
import com.viptrip.hotel.model.Response_SendEmail4Hotel;
import com.viptrip.hotel.model.Response_SendSMS4Hotel;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.sendSMS4Hotel.SMS4HotelMessage;
import com.viptrip.wetrip.util.PropertiesUtil;
import com.viptrip.wetrip.util.Sendemail;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class SendEmail4Hotel extends HotelClient<Request_SendEmail4Hotel, Response_SendEmail4Hotel>{

	@Override
	protected OutputSimpleResult DataValid(Request_SendEmail4Hotel arg0) {
	OutputSimpleResult osr = new OutputSimpleResult();
		
		
	
		if (!Common.IsNullOrEmpty(arg0.userId+"")){
			if(arg0.data!=null){
				if(!Common.IsNullOrEmpty(arg0.data.type+"")){
					if(!Common.IsNullOrEmpty(arg0.data.memo+"")){
						if(arg0.data.emails!=null&&arg0.data.emails.size()>0){
							if(!Common.IsNullOrEmpty(arg0.data.orderNo+"")){
								if(!Common.IsNullOrEmpty(arg0.data.subject+"")){
									if(!Common.IsNullOrEmpty(arg0.data.content+"")){				
										osr.code=Constant.Code_Succeed;
									}else osr.result="邮件内容为空";
								}else osr.result="邮件主题为空";
							}else osr.result="业务订单号为空";
						}else osr.result="邮箱为空";
					}else osr.result="短信描述为空";
				}else osr.result="邮件类型为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_SendEmail4Hotel, String> DoBusiness(
			Request_SendEmail4Hotel arg0) {
		OutputResult<Response_SendEmail4Hotel, String> or = new OutputResult<Response_SendEmail4Hotel, String>();
		

		Response_SendEmail4Hotel rs = new	Response_SendEmail4Hotel();
		String host=null;
		String user=null;
		String pwd=null;
		String from=null;
		
		String cc="";
		List<String> emaillist = arg0.data.emails;
		try {
			Properties props = PropertiesUtil.loadProperties("/com/viptrip/register/properties/ApplicationResources.properties");
			 host = props.getProperty("email_host"); // smtp服务器
			 user = props.getProperty("email_user"); // 用户名
			 pwd = props.getProperty("email_pwd"); // 密码
			 from = props.getProperty("email_from"); // 发件人地址
			
			 
			 
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		boolean flag=true;
		for (int i = 0; i < emaillist.size(); i++) {
			
			try {
				
				Sendemail.sendHTML(host, from, user, pwd, emaillist.get(i), cc, null, arg0.data.subject,arg0.data.content);
			} catch (Exception e) {
				e.printStackTrace();
				flag=false;
				break;
			}
		}
		if(flag)
		or.code = Constant.Code_Succeed;// 成功标志
		else{ 
			rs.status=1;
					}
		or.setResultObj(rs);
		return or;
	}
	
	
}
