package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Response_Base;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_GetIntlOrderList;

public class Response_GetIntlOrderList extends Response_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 
    
    private Resp_Data_GetIntlOrderList data; 


    public Resp_Data_GetIntlOrderList getData() {
        return data;
    }

    public void setData(Resp_Data_GetIntlOrderList data) {
        this.data = data;
    }
    
    
}
