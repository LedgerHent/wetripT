package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.pay.PayData;

public class Request_PayByAlipay extends Request_Base{
	private static final long serialVersionUID = 2614055877484231733L;
	
	private PayData data;

	public PayData getData() {
		return data;
	}

	public void setData(PayData data) {
		this.data = data;
	}

}
