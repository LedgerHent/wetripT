package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_GetIntlOrderDetail extends Request_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer orderID;//订单ID

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }
    
    
}
