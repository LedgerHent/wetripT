package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;


public class Request_GetCabinList extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String mapKey;		//若是单程传入单程返回的mapKey,若是往返行程,传入返程返回的mapKey


	public String getMapKey() {
		return mapKey;
	}


	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	
	
	

}
