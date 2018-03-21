package com.viptrip.intlAirticket.model;

import java.util.List;

import com.viptrip.intlAirticket.model.base.Response_Base;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_QuitUpdateTicket;

public class Response_GetIntlTicketRule extends Response_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Resp_Data_QuitUpdateTicket> data;		//退改签信息

	public List<Resp_Data_QuitUpdateTicket> getData() {
		return data;
	}

	public void setData(List<Resp_Data_QuitUpdateTicket> data) {
		this.data = data;
	}

	
}
