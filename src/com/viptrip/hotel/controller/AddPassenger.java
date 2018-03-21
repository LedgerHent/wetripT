package com.viptrip.hotel.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_AddPassenger;
import com.viptrip.wetrip.entity.TTravelPassenger;

import com.viptrip.wetrip.model.Response_AddPassenger;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.impl.PassengerService;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class AddPassenger extends HotelClient<Request_AddPassenger,Response_AddPassenger>{

	@Override
	protected OutputSimpleResult DataValid(Request_AddPassenger arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		Req_Passenger addP = arg0.getData();
		//电话号验证
		Pattern mobiles=Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
		/*Matcher phoneMatcher=mobiles.matcher(addP.getMobile());
		boolean matches = phoneMatcher.matches();*/
		//邮箱验证
		String reg3 = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"; // 邮箱正则表达式
	
		
		List<ReqData_CertificateMessage> ids = addP.getIds();
		//ReqData_CertificateMessage idAndNum = ids.get(0);
		//身份证正则验证
		Pattern p = Pattern.compile("^\\d{15}$|^\\d{17}[0-9Xx]$");
		//Matcher idMatcher = p.matcher(idAndNum.getNum());
		if (!Common.IsNullOrEmpty(arg0.getUserId()+"")){
			if(addP!=null){
				if(!Common.IsNullOrEmpty(addP.getName())){
					if(!Common.IsNullOrEmpty(addP.getMobile())&&mobiles.matcher(addP.getMobile()).matches()){
						//if(!Common.IsNullOrEmpty(addP.getEmail())||!addP.getEmail().matches(reg3)){
							if(!Common.IsNullOrEmpty(addP.getType()+"")){
								if(!Common.IsNullOrEmpty(addP.getSex()+"")){
									//if(!Common.IsNullOrEmpty(addP.getBirthday())){
										if(ids.size()>0&&!Common.IsNullOrEmpty(ids.get(0).getType()+"")){
											if(ids.size()>0&&!Common.IsNullOrEmpty(ids.get(0).getNum())&&p.matcher(ids.get(0).getNum()).matches()){
												osr.code=Constant.Code_Succeed;
											}osr.result="身份证不符合要求";
 										}osr.result="证件类型为空";
									//}else osr.result="生日为空";
								}else osr.result="性别为空";
							}else osr.result="员工类型为空";
						//}else osr.result="邮箱不符合要求";
					}else osr.result="用户电话为空或格式不对";
				}else osr.result="用户姓名为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_AddPassenger, String> DoBusiness(Request_AddPassenger passenger) {
		OutputResult<Response_AddPassenger, String> or = new OutputResult<Response_AddPassenger, String>();
		or.code = Constant.Code_Succeed;// 成功标志

		PassengerService service = ApplicationContextHelper.getInstance()
				.getBean(PassengerService.class);

		TTravelPassenger tTravelP = new TTravelPassenger();
		Req_Passenger p = passenger.getData();
		tTravelP.setName(p.getName());
		tTravelP.setMobilephone(p.getMobile());
		tTravelP.setEmail(p.getEmail());
		tTravelP.setPassengerType(p.getType()+"");
		List<ReqData_CertificateMessage> list = p.getIds();
		for(ReqData_CertificateMessage rc:list){
			tTravelP.setIdnumber(rc.getNum());
			Integer type = rc.getType();
			if(type==null || "".equals(type)){
				tTravelP.setIdtype(null);
			}else{
				tTravelP.setIdtype(String.valueOf(type));
			}
			
			tTravelP.setIdnumber(rc.getNum());
		}
		tTravelP.setUserid(passenger.getUserId());
		
		Integer maxId = service.addPassenger(tTravelP);
		Response_AddPassenger responsePassenger = new Response_AddPassenger();
        responsePassenger.setNumber(maxId);
		or.setResultObj(responsePassenger);
		return or;
	}

}
