package com.viptrip.intlAirticketPC.model;

import java.util.List;

import com.viptrip.intlAirticketPC.model.base.Response_Base;
import com.viptrip.intlAirticketPC.model.ruleModels.RespIntlTicketRuleModel;

public class Response_GetIntlPCTicketRule extends Response_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<RespIntlTicketRuleModel> data;		//退改签信息

}
