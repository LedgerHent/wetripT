package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/1/10.
 */
public class GetIntlFlightList {

	public Map<String, Airport> airports;//机场信息
	public Map<String,Carrier> carriers;//航空公司信息
	public Map<String,PlaneType> planeTypes;//机型信息
	public List<Trips> trips;

}
