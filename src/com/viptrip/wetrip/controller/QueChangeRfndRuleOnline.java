package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.model.Response_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.service.ChangeRfndRuleOnlineService;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
/**
 * 是否可在线退票、改期状态查询,返回状态 0：允许在线预订；1：不允许在线预订.
 * @author lcl
 * 
 */
public class QueChangeRfndRuleOnline
		extends
		TicketClient<Request_QueChangeRfndRuleOnline, Response_QueChangeRfndRuleOnline> {
	//获取service层实例
	ChangeRfndRuleOnlineService service = ApplicationContextHelper.getInstance().getBean(ChangeRfndRuleOnlineService.class);
			
	@Override
	protected OutputSimpleResult DataValid(Request_QueChangeRfndRuleOnline req) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.air2char)){
			if(!Common.IsNullOrEmpty(req.cangwei)){
				if(!Common.IsNullOrEmpty(req.type)){
					osr.code=Constant.Code_Succeed;
				}else osr.result="退票改期标志不能为空！请确认后重试。";
			}else osr.result="舱位代码不能为空！请确认后重试。";
		}else osr.result="航空公司不能为空！请确认后重试。";
		return osr;
	}

	@Override
	protected OutputResult<Response_QueChangeRfndRuleOnline, String> DoBusiness(
			Request_QueChangeRfndRuleOnline req) {
		OutputResult<Response_QueChangeRfndRuleOnline, String> or = new OutputResult<Response_QueChangeRfndRuleOnline, String>();
		or = service.queStatus4ChangeRfndRule(req);
		return or;
	}

}
