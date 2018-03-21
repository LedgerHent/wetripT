package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;
/**
 * 国际航段信息
 * @author Administrator
 *
 */
public class Segment {

	private int flightType;			//1-去程，2-回程
	
	private List<IntlFlight> intlFlights;		//航班信息集合

	public int getFlightType() {
		return flightType;
	}

	public void setFlightType(int flightType) {
		this.flightType = flightType;
	}

	public List<IntlFlight> getIntlFlights() {
		return intlFlights;
	}

	public void setIntlFlights(
			List<IntlFlight> intlFlights) {
		this.intlFlights = intlFlights;
	}
	
	
	
}
