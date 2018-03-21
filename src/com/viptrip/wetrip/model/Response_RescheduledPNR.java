package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.flight.RespData_RescheduledPNR;

public class Response_RescheduledPNR extends Response_Base {

    /**
     * 
     */
    private static final long serialVersionUID = -8604331649060970879L;

    public List<RespData_RescheduledPNR> pnrList;
    
    public String message;
    
    public Integer code;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RespData_RescheduledPNR> getPnrList() {
        return pnrList;
    }

    public void setPnrList(List<RespData_RescheduledPNR> pnrList) {
        this.pnrList = pnrList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    
    
}
