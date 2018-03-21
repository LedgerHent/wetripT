package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.viptrip.yeego.model.Request_Base;

@SuppressWarnings("serial")
@XmlRootElement(name="OrderInterByPnr_1_0")
public class Request_OrderInterByPnr_1_0 extends Request_Base {
	@XmlElement(name="Pnr")
	public String Pnr;
	@XmlElement(name="PnrText")
	public String PnrText;
	@XmlElement(name="PolicyId")
	public String PolicyId;
	@XmlElement(name="PlatCode")
	public String PlatCode;
	@XmlElement(name="AccountLevel")
	public String AccountLevel;
	@XmlElement(name="BalanceMoney")
	public String BalanceMoney;
	@XmlElement(name="Remark")
	public String Remark;
	@XmlElement(name="CommitKey")
	public String CommitKey;
	@XmlElement(name="TravelType")
	public String TravelType;
	@XmlElement(name="NotifyUrl")
	public String NotifyUrl;
	
	@XmlElementWrapper(name = "InterFlights")  
    @XmlElement(name = "IFlights")  
	public List<IFlights> InterFlights;
	
	@XmlElementWrapper(name = "Passengers")  
    @XmlElement(name = "Passenger") 
	public List<Passenger> Passengers;
	public void setPnr(String pnr) {
		Pnr = pnr;
	}
	public void setPnrText(String pnrText) {
		PnrText = pnrText;
	}
	public void setPolicyId(String policyId) {
		PolicyId = policyId;
	}
	public void setPlatCode(String platCode) {
		PlatCode = platCode;
	}
	public void setAccountLevel(String accountLevel) {
		AccountLevel = accountLevel;
	}
	public void setBalanceMoney(String balanceMoney) {
		BalanceMoney = balanceMoney;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public void setCommitKey(String commitKey) {
		CommitKey = commitKey;
	}
	public void setTravelType(String travelType) {
		TravelType = travelType;
	}
	public void setNotifyUrl(String notifyUrl) {
		NotifyUrl = notifyUrl;
	}
	
	public void setPassengers(List<Passenger> passengers) {
		Passengers = passengers;
	}
	public void setInterFlights(List<IFlights> interFlights) {
		InterFlights = interFlights;
	}
	
	
}
