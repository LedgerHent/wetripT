package com.viptrip.wetrip.model.policy;

public class BerthPolicyInfo {
    public String berth;//舱位     Y
    public String airCompany;//航司   CZ
    public String airline;//航线    PEKSHA
    public String flightnum;//航班号 CZ1256
	public String getBerth() {
		return berth;
	}
	/**
	 * 舱位     Y
	 * @param berth
	 */
	public void setBerth(String berth) {
		this.berth = berth;
	}
	public String getAirCompany() {
		return airCompany;
	}
	public void setAirCompany(String airCompany) {
		this.airCompany = airCompany;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlightnum() {
		return flightnum;
	}
	public void setFlightnum(String flightnum) {
		this.flightnum = flightnum;
	}
    
}
