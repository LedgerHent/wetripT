package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Response_Base;
import com.viptrip.hotel.model.fee.FeeService;

@SuppressWarnings("serial")
public class Response_GetHotelServiceFee extends Response_Base{
	public Integer feeState;
	public FeeService data;
}
