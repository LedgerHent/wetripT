package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Request_Base;
import com.viptrip.wetrip.model.flight.ReqData_RefundETktForOrder_model;

public class Request_RefundETktForOrder extends Request_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 6408776571186889904L;
    
    public List<ReqData_RefundETktForOrder_model> refundETktList;

    public List<ReqData_RefundETktForOrder_model> getRefundETktList() {
        return refundETktList;
    }

    public void setRefundETktList(List<ReqData_RefundETktForOrder_model> refundETktList) {
        this.refundETktList = refundETktList;
    }
    
    
}
