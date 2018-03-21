package com.viptrip.common.model;

import com.viptrip.wetrip.model.base.Request_UserId;

@SuppressWarnings("serial")
public class Request_IntegralConsumption extends Request_UserId {
	public int businessType;//0-不限|1-国内机票|2-国际机票|3-国内酒店|4-国际酒店|5-火车票|6-签证|7-租车
	public String orderNo;
	public long amount;
}
