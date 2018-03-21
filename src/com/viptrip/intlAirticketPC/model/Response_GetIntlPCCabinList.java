package com.viptrip.intlAirticketPC.model;

import java.util.List;

import com.viptrip.intlAirticketPC.model.base.Response_Base;
import com.viptrip.intlAirticketPC.model.cabinModels.RespData_Cabins;

public class Response_GetIntlPCCabinList extends Response_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<RespData_Cabins> data;		//舱位信息

}
