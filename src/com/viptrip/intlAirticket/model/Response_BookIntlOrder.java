package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Response_Base;
import com.viptrip.intlAirticket.model.flightModels.RespData_BookIntlOrder_OrderInfo;

@SuppressWarnings("serial")
public class Response_BookIntlOrder extends Response_Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    public RespData_BookIntlOrder_OrderInfo data;			//常旅客信息列表
    
	public Response_BookIntlOrder() {
        // TODO Auto-generated constructor stub
    }
	
	public Response_BookIntlOrder(RespData_BookIntlOrder_OrderInfo data) {
       this.data = data;
    }
	

    public RespData_BookIntlOrder_OrderInfo getData() {
		return data;
	}

	public void setData(RespData_BookIntlOrder_OrderInfo data) {
		this.data = data;
	}

	
	
}
