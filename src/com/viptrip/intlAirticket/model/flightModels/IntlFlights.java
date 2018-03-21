package com.viptrip.intlAirticket.model.flightModels;

import java.util.ArrayList;
import java.util.List;

/**
 * 国际往返航班信息跳转存储
 */
public class IntlFlights {
	
	/**
	 * 往返航班详情对象
	 */
	public List<RespData_IntlTicketData> intlTicketDatas = new ArrayList<>();
	
	/**
	 * 往返航班舱位对象
	 */
	public List<RespData_Cabins> cabinsData = new ArrayList<>();
}
