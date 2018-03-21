package com.viptrip.yeego.intlairticket.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Passenger {
	public String Name;
	public String PsgType;
	public String CardType;
	public String CardNo;
	public String MobilePhone;
	public String Fare;
	public String ShouldPay;
	public String TaxAmount;
	public String Sex;
	public String BirthDay;
	public String Country;
	public void setName(String name) {
		Name = name;
	}
	public void setPsgType(String psgType) {
		PsgType = psgType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public void setFare(String fare) {
		Fare = fare;
	}
	public void setShouldPay(String shouldPay) {
		ShouldPay = shouldPay;
	}
	public void setTaxAmount(String taxAmount) {
		TaxAmount = taxAmount;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public void setBirthDay(String birthDay) {
		BirthDay = birthDay;
	}
	public void setCountry(String country) {
		Country = country;
	}
	
}
