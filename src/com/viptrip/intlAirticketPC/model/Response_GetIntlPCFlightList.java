package com.viptrip.intlAirticketPC.model;

import com.viptrip.intlAirticketPC.model.base.Response_Base;
import com.viptrip.intlAirticketPC.model.flightModels.GetIntlFlightList;
/**
 * 国际机票列表查询响应model
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Response_GetIntlPCFlightList extends Response_Base{
	public GetIntlFlightList data;

}
