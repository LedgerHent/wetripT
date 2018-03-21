package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_GetPayQuota extends Request_Base{

	public Integer type;//额度类别，1-月结授信，2-预付款
	
}
