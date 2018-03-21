package com.viptrip.intlAirticketPC.model;

import com.viptrip.intlAirticketPC.model.base.Request_Base;
import com.viptrip.intlAirticketPC.model.ruleModels.IntlTicketRuleModel;

public class Request_GetIntlPCTicketRule extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IntlTicketRuleModel data;		//退改签查询信息

}
