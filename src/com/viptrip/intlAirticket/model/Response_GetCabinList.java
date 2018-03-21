package com.viptrip.intlAirticket.model;

import java.util.List;

import com.viptrip.intlAirticket.model.base.Response_Base;
import com.viptrip.intlAirticket.model.flightModels.RespData_Cabins;

public class Response_GetCabinList extends Response_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<RespData_Cabins> data;		//舱位信息

}
