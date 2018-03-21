package com.viptrip.intlAirticket.model.flightModels;

public class RespData_Cabins {

	private Double totalPrice;			//票价（不含税）
	
	private Double totalTaxPrice;		//税总价
	
	private String cangwei;			//舱位
	
	private int isAgreementPrice;	//是否企业协议价
	
	private int seatsLeft;			//剩余票数
	
	private String discount;		//折扣
	
	private String cangweiDesc;		//舱位类型


	public String getCangwei() {
		return cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

	public int getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(int seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCangweiDesc() {
		return cangweiDesc;
	}

	public void setCangweiDesc(String cangweiDesc) {
		this.cangweiDesc = cangweiDesc;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalTaxPrice() {
		return totalTaxPrice;
	}

	public void setTotalTaxPrice(Double totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	public int getIsAgreementPrice() {
		return isAgreementPrice;
	}

	public void setIsAgreementPrice(int isAgreementPrice) {
		this.isAgreementPrice = isAgreementPrice;
	}
	
}
