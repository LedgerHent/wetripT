package com.viptrip.wetrip.model;

import java.util.List;

import com.sun.org.glassfish.gmbal.Description;
import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.flight.ReqData_RescheduledUpgradeFee_Tripinfo;

public class Request_RescheduledUpgradeFee extends Request_UserId {

    /**
     * 
     */
    private static final long serialVersionUID = -446162167507979455L;
    
    @Description("订单号")
    public String orderno;
    @Description("要改期的票号")
    public List<String> ticketNumbers;
    @Description("改期后的航班信息")
    public List<ReqData_RescheduledUpgradeFee_Tripinfo> changeTripinfos;
    public String getOrderno() {
        return orderno;
    }
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
    public List<String> getTicketNumbers() {
        return ticketNumbers;
    }
    public void setTicketNumbers(List<String> ticketNumbers) {
        this.ticketNumbers = ticketNumbers;
    }
    public List<ReqData_RescheduledUpgradeFee_Tripinfo> getChangeTripinfos() {
        return changeTripinfos;
    }
    public void setChangeTripinfos(List<ReqData_RescheduledUpgradeFee_Tripinfo> changeTripinfos) {
        this.changeTripinfos = changeTripinfos;
    }
    
    
    
}
