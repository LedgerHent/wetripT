package com.viptrip.wetrip.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_UpdatePassenger;
import com.viptrip.wetrip.model.Response_UpdatePassenger;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.impl.PassengerService;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class UpdatePassenger extends
		TicketClient<Request_UpdatePassenger, Response_UpdatePassenger> {

	@SuppressWarnings("unused")
	@Override
	protected OutputSimpleResult DataValid(Request_UpdatePassenger arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		Req_Passenger addP = arg0.getAddP();
		//电话号验证
		Pattern mobiles=Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
		Matcher phoneMatcher=mobiles.matcher(addP.getMobile());
		//邮箱验证
		String reg3 = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"; // 邮箱正则表达式
		
		List<ReqData_CertificateMessage> ids = addP.getIds();
		ReqData_CertificateMessage idAndNum = ids.get(0);
		//身份证正则验证
		Pattern p = Pattern.compile("^\\d{15}$|^\\d{17}[0-9Xx]$");
		Matcher idMatcher = p.matcher(idAndNum.getNum());
		if (!Common.IsNullOrEmpty(arg0.getUserId()+"")){
			if(addP!=null){
				if(!Common.IsNullOrEmpty(addP.getName())){
					if(!Common.IsNullOrEmpty(addP.getMobile())||!phoneMatcher.matches()){
						//if(!Common.IsNullOrEmpty(addP.getEmail())||!addP.getEmail().matches(reg3)){
							if(!Common.IsNullOrEmpty(addP.getType()+"")){
								if(!Common.IsNullOrEmpty(addP.getSex()+"")){
									//if(!Common.IsNullOrEmpty(addP.getBirthday())){
										if(!Common.IsNullOrEmpty(idAndNum.getType()+"")){
											if(!Common.IsNullOrEmpty(idAndNum.getNum())||!idMatcher.matches()){
												osr.code=Constant.Code_Succeed;
											}osr.result="身份证不符合要求";
										}osr.result="证件类型为空";
									//}else osr.result="生日为空";
								}else osr.result="性别为空";
							}else osr.result="员工类型为空";
						//}else osr.result="邮箱不符合要求";
					}else osr.result="用户电话不符合要求";
				}else osr.result="用户姓名为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_UpdatePassenger, String> DoBusiness(
			Request_UpdatePassenger arg0) {
		OutputResult<Response_UpdatePassenger, String> or = new OutputResult<Response_UpdatePassenger, String>();
		or.code = Constant.Code_Succeed;// 成功标志

		PassengerService service = ApplicationContextHelper.getInstance()
				.getBean(PassengerService.class);
		TTravelPassenger oldPassenger = service.getOneById(arg0.getAddP()
				.getId().longValue());
		Req_Passenger p = arg0.getAddP();
		oldPassenger.setName(p.getName());
		oldPassenger.setMobilephone(p.getMobile());
		oldPassenger.setEmail(p.getEmail());
		oldPassenger.setPassengerType(p.getType()+"");
		List<ReqData_CertificateMessage> ids = p.getIds();
		ReqData_CertificateMessage person = ids.get(0);
		Integer type = person.getType();
		if(type==null || "".equals(type)){
			oldPassenger.setIdtype(null);
		}else{
			oldPassenger.setIdtype(String.valueOf(type));
		}
		oldPassenger.setIdnumber(person.getNum());

		service.updatePassenger(oldPassenger);

		or.setResultObj(new Response_UpdatePassenger());
		return or;
	}

}
