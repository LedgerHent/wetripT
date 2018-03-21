package com.viptrip.intlAirticket.model.flightModels;

public class CabinAndPrice {
	private Double totalPrice;
	private Double totalPriceWithTax;
	private String cangwei;
	private String cangweiType;
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalPriceWithTax() {
		return totalPriceWithTax;
	}
	public void setTotalPriceWithTax(Double totalPriceWithTax) {
		this.totalPriceWithTax = totalPriceWithTax;
	}
	public String getCangwei() {
		return cangwei;
	}
	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}
	public String getCangweiType() {
		return cangweiType;
	}
	public void setCangweiType(String cangweiType) {
		this.cangweiType = cangweiType;
	}

}
