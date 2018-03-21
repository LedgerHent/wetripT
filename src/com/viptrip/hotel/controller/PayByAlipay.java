package com.viptrip.hotel.controller;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_PayByAlipay;
import com.viptrip.hotel.model.Response_PayByAlipay;
import com.viptrip.hotel.model.pay.PayData;
import com.viptrip.hotel.service.IPayService;
import com.viptrip.pay.service.Pay;
import com.viptrip.pay.vo.E.PayType;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class PayByAlipay extends HotelClient<Request_PayByAlipay, Response_PayByAlipay>{

	@Override
	protected OutputSimpleResult DataValid(Request_PayByAlipay para) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(null==para || null==para.getData()){
			osr.result = "参数错误";
		}else{
			PayData data = para.getData();
			if(Common.IsNullOrEmpty(data.getOrderNo()) || null==data.getAmount() || Common.IsNullOrEmpty(data.getReturnUrl())){
				osr.result = "必填参数不能为空" ;
			}else{
				if(data.getAmount()<=0){
					osr.result = "支付金额有误";
				}else{
					osr.code = Constant.Code_Succeed;
					osr.result = "成功";
				}
			}
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_PayByAlipay, String> DoBusiness(
			Request_PayByAlipay para) {
		OutputResult<Response_PayByAlipay, String> or = new OutputResult<>();
		PayData data = para.getData();
		
//		IPayService payService = ApplicationContextHelper.getInstance().getBean(IPayService.class);
//		String page = payService.alipay(data.getOrderNo(), data.getAmount()/100D, data.getReturnUrl(), data.getNotifyUrl(), data.getSubject(), data.getBody(), data.getNonceStr());
		
		Pay pay = ApplicationContextHelper.getInstance().getBean(Pay.class);
		String page =pay.onlinePay(PayType.支付宝PC, data.getOrderNo(), data.getAmount()/100D, data.getSubject(), data.getBody(),  data.getNotifyUrl(), data.getReturnUrl(), null, null);
		or.code = Constant.Code_Succeed;
		or.setResultObj(new Response_PayByAlipay(page));
		return or;
	}
	
	
}
