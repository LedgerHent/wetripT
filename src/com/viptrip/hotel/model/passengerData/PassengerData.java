package com.viptrip.hotel.model.passengerData;

import java.util.List;

import com.viptrip.hotel.model.page.Page;
import com.viptrip.wetrip.model.passenger.Req_Passenger;

public class PassengerData {

	public Page page;
	
	public List<Req_Passenger> list;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Req_Passenger> getList() {
		return list;
	}

	public void setList(List<Req_Passenger> list) {
		this.list = list;
	}

	
	
	
}
