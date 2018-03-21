package com.viptrip.yeego.intlairticket.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.viptrip.yeego.model.Response_Base;

@SuppressWarnings("serial")
@XmlRootElement(name="YeeGo.OrderInterByPnr_1_0")
public class Response_OrderInterByPnr_1_0 extends Response_Base {
	public String ErrorCode;
	public String ErrorMsg;
	public String PlatCode;
	public String PlatOrderNo;
	public String OrderNo;
	public String PlatformName;
	public String Pnr;
	public String TicketOffice;
	public String PsgCount;
	public String BalanceMoney;
	public String Fare;
	public String TaxAmount;
	public String CommAmount;
	public String CommRate;
	public String CommMoney;
	public String YeeGoStatus;
	
	public ResultObj resultObj;

}
