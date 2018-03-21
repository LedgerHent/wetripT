package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_PayByAdvance;
import com.viptrip.hotel.model.Response_GetOrgLike;
import com.viptrip.hotel.model.Response_PayByAdvance;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class PayByAdvance extends HotelClient<Request_PayByAdvance, Response_PayByAdvance> { {
	
}

@Override
protected OutputSimpleResult DataValid(Request_PayByAdvance arg) {
	OutputSimpleResult osr=new OutputSimpleResult();
	if(HotelUtil.isUser(arg.userId)){
		if(arg.type!=null && !"".equals(arg.type.toString())){
			if(arg.orderNo!=null && !"".equals(arg.orderNo)){
				if(arg.amount!=null && !"".equals(arg.amount.toString())){
					osr.code=Constant.Code_Succeed;
				}else{
					osr.result="无效的请求参数。金额不能为空";
				}
			}else{
				osr.result="无效的请求参数。订单号不能为空";
			}
		}else{
			osr.result="无效的请求参数。付款类别不能为空";
		}
	}else{
		osr.result="无效的请求参数。用户id不能为空";
	}
	return osr;
}

@Override
protected OutputResult<Response_PayByAdvance, String> DoBusiness(
		Request_PayByAdvance arg0) {
	OutputResult<Response_PayByAdvance, String> or=new OutputResult<>();
	Response_PayByAdvance res=new Response_PayByAdvance();
	IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
	res.data = service.PayBalance(arg0);
	or.code=Constant.Code_Succeed;
	or.setResultObj(res);
	return or;
}

}
