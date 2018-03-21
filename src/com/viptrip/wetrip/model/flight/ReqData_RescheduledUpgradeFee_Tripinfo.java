package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import com.viptrip.base.annotation.Description;

public class ReqData_RescheduledUpgradeFee_Tripinfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1439365786855981872L;

    @Description("起飞城市三字码")
    public String flightStart;
    @Description("到达城市三字码")
    public String flightEnd;
    @Description("票面价")
    public double ticketPrice;
    public String getFlightStart() {
        return flightStart;
    }
    public void setFlightStart(String flightStart) {
        this.flightStart = flightStart;
    }
    public String getFlightEnd() {
        return flightEnd;
    }
    public void setFlightEnd(String flightEnd) {
        this.flightEnd = flightEnd;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
}
