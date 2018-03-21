package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.base.annotation.Description;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.wetrip.model.base.Request_Base;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_PassengerMessage;

public class Request_TicketPolicyFilter extends Request_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public long userid;
    public String tripWay;
    public List<ReqData_GetFlightList_Passenger> passengers;
    public List<DispplayTrip> entitys;
    
}
