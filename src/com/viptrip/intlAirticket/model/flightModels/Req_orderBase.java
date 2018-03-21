package com.viptrip.intlAirticket.model.flightModels;

import com.viptrip.intlAirticket.model.base.Request_Base;

/**
 * Created by hent on 2018/1/31.
 */
public class Req_orderBase  extends Request_Base {
    public Integer businessType;
    public String orderno;
    public Integer getBusinessType() {
        return businessType;
    }
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
    public String getOrderno() {
        return orderno;
    }
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
}
