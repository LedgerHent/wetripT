package com.viptrip.wetrip.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.viptrip.base.action.BaseAction;
import com.viptrip.register.model.Response_Result;
import com.viptrip.util.JSON;
import com.viptrip.wetrip.controller.QueFee4ChangeRfndRule;
import com.viptrip.wetrip.model.Request_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.model.Response_GetSmsCheckCode;
import com.viptrip.wetrip.model.Response_H5UserLogin;
import com.viptrip.wetrip.model.Response_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;
import com.viptrip.wetrip.service.impl.SmsCheckCodeService;
import com.viptrip.wetrip.service.impl.UserBindService;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;
/**
 * 对外公用接口方法调用
 * @author lcl
 *
 */
@Controller
@RequestMapping("/foreignMethod")
public class PublicInterface extends BaseAction{
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(PublicInterface.class);
	
	@Resource
	private SmsCheckCodeService service;
	@Resource
	private UserBindService checkSer;
	
	/**
	 * 各种类型的短信验证码发送，保存验证码表
	 * @param mobile
	 * @param smsType  1-用户绑定|2-用户解绑|3-用户注册|4-密码重置|5-敏感信息修改
	 * @return
	 */
	@RequestMapping("/sendSmsCode")
	@ResponseBody
	public Response_Result sendSmsCheckCode (String mobile, int smsType){
		Response_Result resp = new Response_Result();
		if(!Common.IsNullOrEmpty(mobile) && smsType!=0){
			MobileAndCheckCode data = new MobileAndCheckCode();
			data.setMobile(mobile);
			OutputResult<Response_GetSmsCheckCode, String> or = service.sendExceptBindSmsCode(data, smsType);//req.getSmsType()
			if(or!=null){
				resp.setCode(or.code);
				resp.setResult(or.result);
			}else{
				or = new OutputResult<Response_GetSmsCheckCode, String>();
				resp.setCode(or.code);
				resp.setResult("没有返回结果，系统错误！");
			}
		}else{
			resp.setCode(1);
			resp.setResult("参数为空或smsType为0，请确认。");
		}
		return resp;
	}
	
	/**
	 * 短信验证码合法性校验
	 * @param mobile 需校验手机号
	 * @return
	 */
	@RequestMapping("/CheckSmsCodeRight")
	@ResponseBody
	public Response_Result checkSmsCodeRight (String mobile,String smsCode,int smsType){
		Response_Result resp = new Response_Result();
		OutputSimpleResult or = new OutputSimpleResult();
		MobileAndCheckCode data = new MobileAndCheckCode();
		if(!Common.IsNullOrEmpty(mobile) && !Common.IsNullOrEmpty(smsCode)){
			data.setMobile(mobile);
			data.setCheckCode(smsCode);
			or = checkSer.CheckSMSCodeValid(data,smsType);
			
			resp.setCode(or.code);
			resp.setResult(or.result);
		}else{
			resp.setCode(1);
			resp.setResult("参数为空，请确认。");
		}
		
		return resp;
	}
	
	
	@RequestMapping("/invoking2Price")
	@ResponseBody
	public Response_Result invoking2Price (String parm){
		Response_Result rr = new Response_Result();
		List<Response_QueFee4ChangeRfndRule> l_resP = new ArrayList<Response_QueFee4ChangeRfndRule>();
		Response_QueFee4ChangeRfndRule resp = new Response_QueFee4ChangeRfndRule();
		try {
			List<Request_QueFee4ChangeRfndRule> l_req = new ArrayList<Request_QueFee4ChangeRfndRule>();
			//解析请求参数数组
			JsonArray array = new JsonParser().parse(parm).getAsJsonArray();  
	        for(final JsonElement elem : array){  
	        	l_req.add(new Gson().fromJson(elem, Request_QueFee4ChangeRfndRule.class));  
	        } 
			for (int i=0;i<l_req.size();i++) {
				QueFee4ChangeRfndRule fee = new QueFee4ChangeRfndRule();
				OutputResult<Response_QueFee4ChangeRfndRule,Response_BaseMsg> or= 
						fee.ClientRequest(l_req.get(i), Response_QueFee4ChangeRfndRule.class,Response_BaseMsg.class);
				if(or.IsSucceed()&&or.code==Constant.Code_Succeed){//调用成功
					resp = or.getResultObj();
					l_resP.add(resp);
					rr.setCode(Constant.Code_Succeed);
					rr.setResult(new Gson().toJson(l_resP));
					
				}else{
					l_resP=null;
					logger.error("退改价格计算接口调用Controller报错，错误代码：201712041844li;错误信息："+or.result);
					rr.setCode(Constant.Code_Failed);
					rr.setResult(or.result);
					break;
				}
			
			}
			
		} catch (Exception e) {
			l_resP=null;
			String msg = "调用退改价格计算接口报错，错误代码：201712041830li;错误信息："+e.getMessage();
			logger.error(msg);
			rr.setCode(Constant.Code_Failed);
			rr.setResult(msg);
		}
		
		
		return rr;
	}
	
	
	
	
}
