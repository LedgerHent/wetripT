package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_QuitUpdateTicket;

public class Request_GetIntlTicketRule extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Req_Data_QuitUpdateTicket data;		//退改签查询信息

}
