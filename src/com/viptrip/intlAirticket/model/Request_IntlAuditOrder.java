package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;
import com.viptrip.intlAirticket.model.base.Request_UserId;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlAuditOrder;

@SuppressWarnings("serial")
public class Request_IntlAuditOrder extends Request_Base{

	public Req_Data_IntlAuditOrder data;

	public Req_Data_IntlAuditOrder getData() {
		return data;
	}

	public void setData(Req_Data_IntlAuditOrder data) {
		this.data = data;
	}
	
	
}
