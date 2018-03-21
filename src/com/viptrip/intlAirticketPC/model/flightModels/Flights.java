package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */
public class Flights {
    public Integer flowId;
    public String carrier;
    public String flightNo;
    public Share share;
    public DepAirPort depAirPort;
    public ArrAirPort arrAirPort;
    public List<Stops> stops;
    public Type type;
    public Cabin4Flight cabin;
    public Integer flyMinutes;
    public String meal;
    public Integer mileages;
    public Integer fun;
    public Integer eTicket;
    public Integer seats;

}
