package com.viptrip.hotel.controller;



import com.dxpt.entity.Message;
import com.dxpt.service.impl.MessageServiceImpl;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_SendSMS4Hotel;
import com.viptrip.hotel.model.Response_SendSMS4Hotel;
import com.viptrip.hotel.model.sendSMS4Hotel.SMS4HotelMessage;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class SendSMS4Hotel extends HotelClient<Request_SendSMS4Hotel,Response_SendSMS4Hotel>{

	@Override
	protected OutputSimpleResult DataValid(Request_SendSMS4Hotel arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		SMS4HotelMessage data = arg0.getData();
	
		if (!Common.IsNullOrEmpty(arg0.userId+"")){
			if(data!=null){
				if(!Common.IsNullOrEmpty(data.getType()+"")){
					if(!Common.IsNullOrEmpty(data.getMemo())){						
							if(data.getMobiles().size()>0){
								if(!Common.IsNullOrEmpty(data.getOrderNo())){									
										if(!Common.IsNullOrEmpty(data.getContent())){											
												osr.code=Constant.Code_Succeed;
										}else osr.result="短信为空";
								}else osr.result="业务订单号为空";
							}else osr.result="手机号码序列为空";
					}else osr.result="短信描述为空";
				}else osr.result="短信类型为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_SendSMS4Hotel, String> DoBusiness(
			Request_SendSMS4Hotel arg0) {
		OutputResult<Response_SendSMS4Hotel, String> or = new OutputResult<Response_SendSMS4Hotel, String>();
		

		Response_SendSMS4Hotel rs = new	Response_SendSMS4Hotel();
		MessageServiceImpl msi =new MessageServiceImpl();
		Message msg = new Message();//获取短信实体类
		SMS4HotelMessage data = arg0.getData();
		
		for (int i = 0; i < data.getMobiles().size(); i++) {
			
			if(data.getType()==0){
				msg.setExtf1("3");//短信类型 通知类
			}
			if(data.getType()==1){
				msg.setExtf1("2");//短信类型 审核类
			}
			msg.setMobliephone(data.getMobiles().get(i));
			msg.setUvalue(data.getOrderNo());
			msg.setMessage(data.getContent());
			msg.setMark("1");
			msg.setSendtype(data.getMemo());
			msg.setUser_key("【凯撒商旅】");
			msi.saveMsg(msg);
		}
		
		
		or.code = Constant.Code_Succeed;// 成功标志       金立M2017？
		or.setResultObj(rs);
		return or;
	}

}
