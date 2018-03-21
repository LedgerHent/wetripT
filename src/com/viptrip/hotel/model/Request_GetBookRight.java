package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_GetBookRight extends Request_Base {

	public Integer businessType;//业务类型:整型数字，1-因公|2-因私
}
