package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class RespData_GetChildBabyPrice_PriceInfo implements Serializable{
	private static final long serialVersionUID = -6825317903142499955L;
	@Description("价格类型")
	public Integer type ;// 整型数字，1=儿童，2=婴儿
	@Description("儿童票原价")
	public Double price ;
	@Description("儿童票优惠价")
	public Double rebatePrice ;
	@Description("价格来源")
	public String priceSource ;
	@Description("机场建设费")
	public Double airportTax ;
	@Description("燃油费")
	public Double fuelSurTax ;
	@Description("建议")
	public RespData_GetChildBabyPrice_Suggest suggest ;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airportTax == null) ? 0 : airportTax.hashCode());
		result = prime * result
				+ ((fuelSurTax == null) ? 0 : fuelSurTax.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((priceSource == null) ? 0 : priceSource.hashCode());
		result = prime * result
				+ ((rebatePrice == null) ? 0 : rebatePrice.hashCode());
		result = prime * result + ((suggest == null) ? 0 : suggest.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespData_GetChildBabyPrice_PriceInfo other = (RespData_GetChildBabyPrice_PriceInfo) obj;
		if (airportTax == null) {
			if (other.airportTax != null)
				return false;
		} else if (!airportTax.equals(other.airportTax))
			return false;
		if (fuelSurTax == null) {
			if (other.fuelSurTax != null)
				return false;
		} else if (!fuelSurTax.equals(other.fuelSurTax))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceSource == null) {
			if (other.priceSource != null)
				return false;
		} else if (!priceSource.equals(other.priceSource))
			return false;
		if (rebatePrice == null) {
			if (other.rebatePrice != null)
				return false;
		} else if (!rebatePrice.equals(other.rebatePrice))
			return false;
		if (suggest == null) {
			if (other.suggest != null)
				return false;
		} else if (!suggest.equals(other.suggest))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRebatePrice() {
		return rebatePrice;
	}
	public void setRebatePrice(Double rebatePrice) {
		this.rebatePrice = rebatePrice;
	}
	public String getPriceSource() {
		return priceSource;
	}
	public void setPriceSource(String priceSource) {
		this.priceSource = priceSource;
	}
	public Double getAirportTax() {
		return airportTax;
	}
	public void setAirportTax(Double airportTax) {
		this.airportTax = airportTax;
	}
	public Double getFuelSurTax() {
		return fuelSurTax;
	}
	public void setFuelSurTax(Double fuelSurTax) {
		this.fuelSurTax = fuelSurTax;
	}
	public RespData_GetChildBabyPrice_Suggest getSuggest() {
		return suggest;
	}
	public void setSuggest(RespData_GetChildBabyPrice_Suggest suggest) {
		this.suggest = suggest;
	}

	
}
