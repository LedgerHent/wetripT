package com.viptrip.intlAirticket.model;

import java.util.List;

import com.viptrip.intlAirticket.model.base.Response_Base;
import com.viptrip.intlAirticket.model.flightModels.IntlFlightListResp;
/**
 * 国际机票列表查询model
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Response_GetIntlFlightList extends Response_Base{
	public List<IntlFlightListResp> data;
}
