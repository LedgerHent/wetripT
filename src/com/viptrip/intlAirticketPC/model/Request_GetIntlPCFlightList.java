package com.viptrip.intlAirticketPC.model;

import com.viptrip.intlAirticketPC.model.base.Request_Base;
import com.viptrip.intlAirticketPC.model.flightModels.IntlPCFlightListQue;


@SuppressWarnings("serial")
public class Request_GetIntlPCFlightList extends Request_Base{
	public IntlPCFlightListQue data;
}
