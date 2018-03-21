package com.viptrip.wetrip.service;

import com.viptrip.wetrip.model.Request_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.model.Request_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.model.Response_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.model.Response_QueFee4ChangeRfndRule;

import etuf.v1_0.model.base.output.OutputResult;

/**
 * 在线退改相关查询Service类
 * @author Administrator
 *
 */
public interface ChangeRfndRuleOnlineService {
	/**
	 * 是否可在线退票、改期状态查询
	 * @param req 请求参数model
	 * @return
	 */
	public OutputResult<Response_QueChangeRfndRuleOnline, String> queStatus4ChangeRfndRule(Request_QueChangeRfndRuleOnline req);
	/**
	 * 获取可在线退改时的规则id。
	 * @param req 请求参数model
	 * @return
	 */
	public OutputResult<Response_QueChangeRfndRuleOnline, String> queStatus4ChangeRfndRule(
			Request_QueFee4ChangeRfndRule req);
	/**
	 * 在线退改的费用查询
	 * @param req 请求参数model
	 * @return
	 */
	public OutputResult<Response_QueFee4ChangeRfndRule, String> queFee(
			String result, Request_QueFee4ChangeRfndRule req);
	
}
