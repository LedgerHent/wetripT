package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_PayByAdvance extends Request_Base{
	public Integer type;
	public String orderNo;
	public Double amount;
}
