package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Response_Base;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_IntlOrderDetail;
@SuppressWarnings("serial")
public class Response_GetIntlOrderDetail extends Response_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Resp_Data_IntlOrderDetail data;//返回信息   JSONObject

	public Resp_Data_IntlOrderDetail getData() {
		return data;
	}

	public void setData(Resp_Data_IntlOrderDetail data) {
		this.data = data;
	}
    
    
    
}
