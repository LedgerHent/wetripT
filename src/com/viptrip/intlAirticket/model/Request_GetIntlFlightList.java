package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;
import com.viptrip.intlAirticket.model.flightModels.IntlFlightListQue;

@SuppressWarnings("serial")
public class Request_GetIntlFlightList extends Request_Base{
	public IntlFlightListQue data;
}
