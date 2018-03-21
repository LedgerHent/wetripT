package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlOrderListInfo;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo;

public class Request_GetIntlOrderList extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Req_Data_IntlOrderListInfo data;			//订单列表请求信息

	public Req_Data_IntlOrderListInfo getData() {
		return data;
	}

	public void setData(Req_Data_IntlOrderListInfo data) {
		this.data = data;
	}
	
	

}
