package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;


public class Request_GetIntlFlightDetail extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mapKey; 		//航班信息Key

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	
	
	
	
	
	
	
}
