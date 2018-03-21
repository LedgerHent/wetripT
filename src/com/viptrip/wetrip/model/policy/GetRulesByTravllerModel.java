package com.viptrip.wetrip.model.policy;

import java.util.List;

public class GetRulesByTravllerModel {
	public List<Travller> maps;
	public DomTicketModel ticket;
	public IntlTicketModel intlTicket;
	public List<HATPolicyModel> policy;
	public DomTicketModel getTicket() {
		return ticket;
	}
	public void setTicket(DomTicketModel ticket) {
		this.ticket = ticket;
	}
	public IntlTicketModel getIntlTicket() {
		return intlTicket;
	}
	public void setIntlTicket(IntlTicketModel intlTicket) {
		this.intlTicket = intlTicket;
	}
	public List<HATPolicyModel> getPolicy() {
		return policy;
	}
	public void setPolicy(List<HATPolicyModel> policy) {
		this.policy = policy;
	}
	
	
}
