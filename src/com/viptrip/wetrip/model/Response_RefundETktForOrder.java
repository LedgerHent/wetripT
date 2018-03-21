package com.viptrip.wetrip.model;

import java.util.List;
import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.flight.RespData_RefundETktForOrder_model;

public class Response_RefundETktForOrder extends Response_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 1167922057015561953L;
    
    public List<RespData_RefundETktForOrder_model> resRefundETktList;
    
    public String message;
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RespData_RefundETktForOrder_model> getResRefundETktList() {
        return resRefundETktList;
    }

    public void setResRefundETktList(List<RespData_RefundETktForOrder_model> resRefundETktList) {
        this.resRefundETktList = resRefundETktList;
    }


    
}
