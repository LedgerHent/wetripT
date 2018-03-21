package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

import com.viptrip.intlAirticket.entity.TIntlAirTicketItinerary;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.entity.TIntlTravelItinerary;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;



public class BookIntlOrderParam{
	public TIntlOrder intlOrder;
	public List<TIntlAirTicketItinerary> intlXingChegn;
	public List<TIntlTravelItinerary> intlPassengers;
	public List<com.viptrip.wetrip.entity.TNotifyPartyInformation> npinfo;
	public TAcUser tacuser;
	public TAcOrg tacorg;
	
	public TIntlOrder getIntlOrder() {
		return intlOrder;
	}
	public void setIntlOrder(TIntlOrder intlOrder) {
		this.intlOrder = intlOrder;
	}
	public List<TIntlAirTicketItinerary> getIntlXingChegn() {
		return intlXingChegn;
	}
	public void setIntlXingChegn(List<TIntlAirTicketItinerary> intlXingChegn) {
		this.intlXingChegn = intlXingChegn;
	}
	public List<TIntlTravelItinerary> getIntlPassengers() {
		return intlPassengers;
	}
	public void setIntlPassengers(List<TIntlTravelItinerary> intlPassengers) {
		this.intlPassengers = intlPassengers;
	}
	public List<TNotifyPartyInformation> getNpinfo() {
		return npinfo;
	}
	public void setNpinfo(List<TNotifyPartyInformation> npinfo) {
		this.npinfo = npinfo;
	}
	public TAcUser getTacuser() {
		return tacuser;
	}
	public void setTacuser(TAcUser tacuser) {
		this.tacuser = tacuser;
	}
	public TAcOrg getTacorg() {
		return tacorg;
	}
	public void setTacorg(TAcOrg tacorg) {
		this.tacorg = tacorg;
	}
	
	
}
