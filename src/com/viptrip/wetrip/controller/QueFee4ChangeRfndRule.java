package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.model.Response_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.model.Response_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.service.ChangeRfndRuleOnlineService;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 在线退票或改期时的费用查询接口。
 * @author lcl
 *
 */
public class QueFee4ChangeRfndRule
		extends
		TicketClient<Request_QueFee4ChangeRfndRule, Response_QueFee4ChangeRfndRule> {
	//获取service层实例
	ChangeRfndRuleOnlineService service = ApplicationContextHelper.getInstance().getBean(ChangeRfndRuleOnlineService.class);
			
	@Override
	protected OutputSimpleResult DataValid(Request_QueFee4ChangeRfndRule req) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.air2char)){
			if(!Common.IsNullOrEmpty(req.cangwei)){
				if(!Common.IsNullOrEmpty(req.type)){
					if(!Common.IsNullOrEmpty(req.date)){
//						if(null!=req.userId){
							if(null!=req.price){
								osr.code=Constant.Code_Succeed;
							}else osr.result="机票价格不能为空！请确认后重试。";
//						}else osr.result="用户id不能为空！请确认后重试。";
					}else osr.result="起飞时间不能为空！请确认后重试。";
				}else osr.result="退票改期标志不能为空！请确认后重试。";
			}else osr.result="舱位代码不能为空！请确认后重试。";
		}else osr.result="航空公司不能为空！请确认后重试。";
		return osr;
	}

	@Override
	protected OutputResult<Response_QueFee4ChangeRfndRule, String> DoBusiness(
			Request_QueFee4ChangeRfndRule req) {
		OutputResult<Response_QueFee4ChangeRfndRule, String> or = new OutputResult<Response_QueFee4ChangeRfndRule, String>();
		OutputResult<Response_QueChangeRfndRuleOnline, String> orRuleId = new OutputResult<Response_QueChangeRfndRuleOnline, String>();
		orRuleId = service.queStatus4ChangeRfndRule(req);

		if(orRuleId.IsSucceed() && 0==orRuleId.getResultObj().returnStatic){//可以在线退票或改期
			or=service.queFee(orRuleId.result,req);//result 暂时存放 退改规则id
		}else{
			or.result=orRuleId.result;
		}
		return or;
	}

}
