package com.viptrip.intlAirticket.model.flightModels;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

public class FlightResult {

	public boolean succeed = false;

	// PEK_HKG_ADT_RT_2017-02-20_2017-02-25|CA1234_MU520_FM1122*CA1235_MU521_FM1123
	public String unformatMapkey;
	// 基本行程相关的key：PEK_HKG_ADT_RT_2017-02-20_2017-02-25
	public String baseTripKey;
	// 航班相关的key：CA1234_MU520_FM1122*CA1235_MU521_FM1123
	public String flightNoKey;
	public Response_QueryWebFlightsI_1_0 resultObj = null;
	public Set<Entry<String, Hashtable<String, Object[]>>> targetFs = null;

}
