package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo;

public class Request_BookIntlOrder extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Req_Data_OrderInfo data;			//订单信息

	public Req_Data_OrderInfo getData() {
		return data;
	}

	public void setData(Req_Data_OrderInfo data) {
		this.data = data;
	}
	
	

}
