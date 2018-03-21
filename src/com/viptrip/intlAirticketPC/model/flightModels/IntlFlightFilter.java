package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.List;

/**
 * Created by admin on 2018/1/9.
 */
public class IntlFlightFilter {
    public Integer cabinType;//出发舱位类型枚举 0=不限，1=经济舱，2=公务舱，3=头等舱，23=公务舱和头等舱
    public String airline;//航空公司二字码偏好
    public List<Passenger> passenger;//乘客信息

}
