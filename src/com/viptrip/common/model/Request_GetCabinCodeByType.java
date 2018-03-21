package com.viptrip.common.model;

import com.viptrip.wetrip.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_GetCabinCodeByType extends Request_Base{
	public int cabinType;//1-经济舱，2-公务舱，3-头等舱，4-超值头等舱，5-不限
}
