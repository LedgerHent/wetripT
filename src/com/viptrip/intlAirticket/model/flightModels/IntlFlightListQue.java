package com.viptrip.intlAirticket.model.flightModels;

public class IntlFlightListQue {
	public String orgCity;      //出发城市 例如：BJS	字符串                      
	public String detCity;      //目的城市，例如：SIN	字符串                      
	public String startTime;    //出发日期，例如：2015-04-01	字符串              
	public String arrviTime;    //返程日期，例如：2015-04-10	字符串              
	public int travelType;   //1=单程，2=往返，3=联程(若选2必须选返程日期)	整型数字     
	public int passengerType;//乘机人类型，1=成人，2=儿童，3=婴儿	整型数字         
	public String ticketGroup;//分组
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	public String getDetCity() {
		return detCity;
	}
	public void setDetCity(String detCity) {
		this.detCity = detCity;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getArrviTime() {
		return arrviTime;
	}
	public void setArrviTime(String arrviTime) {
		this.arrviTime = arrviTime;
	}
	public int getTravelType() {
		return travelType;
	}
	public void setTravelType(int travelType) {
		this.travelType = travelType;
	}
	public int getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(int passengerType) {
		this.passengerType = passengerType;
	}
	public String getTicketGroup() {
		return ticketGroup;
	}
	public void setTicketGroup(String ticketGroup) {
		this.ticketGroup = ticketGroup;
	}
	
	
}
