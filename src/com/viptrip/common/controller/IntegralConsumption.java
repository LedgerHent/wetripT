package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.Request_IntegralConsumption;
import com.viptrip.common.model.Response_IntegralConsumption;
import com.viptrip.common.service.IntegralAndInterestService;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class IntegralConsumption extends TicketClient<Request_IntegralConsumption, Response_IntegralConsumption>{

	@Override
	protected OutputSimpleResult DataValid(Request_IntegralConsumption para) {
		OutputSimpleResult o = new OutputSimpleResult();
		if(null==para){
			o.result = Msg.IncompletePara.getInfo();
		}else if(0==para.userId){
			o.code=Constant.Code_Failed;	
			o.result = "用户编号不能为空";
		}else if(0!=para.businessType && 1!=para.businessType && 2!=para.businessType && 3!=para.businessType 
				&& 4!=para.businessType && 5!=para.businessType && 6!=para.businessType && 7!=para.businessType){
			o.code=Constant.Code_Failed;	
			o.result = "请检查业务类型";
		}else if(0==para.businessType ||  5==para.businessType || 6==para.businessType || 7==para.businessType){
			o.code=Constant.Code_Failed;	
			o.result = "暂不支持该业务类型";
		}else if(StringUtil.isEmpty(para.orderNo)){
			o.code=Constant.Code_Failed;	
			o.result = "订单编号不能为空";
		}else if(para.amount<=0){
			o.code=Constant.Code_Failed;	
			o.result = "积分数量不能为空或者小于0";
		}else{
			o.code=Constant.Code_Succeed;	
		}
		return o;
	}

	@Override
	protected OutputResult<Response_IntegralConsumption, String> DoBusiness(
			Request_IntegralConsumption para) {
		OutputResult<Response_IntegralConsumption, String> or=new OutputResult<Response_IntegralConsumption, String>();
		Response_IntegralConsumption o=new Response_IntegralConsumption();
		IntegralAndInterestService service = ApplicationContextHelper.getInstance().getBean(IntegralAndInterestService.class);
		String status = service.integralConsumption(para);
		if("0".equals(status)){//表示成功
			or.code = Constant.Code_Succeed;
			or.setResultObj(o);
		}else{
			or.code=Constant.Code_Failed;	
			or.result = status;
			or.setResultObj(o);
		}
		
		return or;
	}

}
