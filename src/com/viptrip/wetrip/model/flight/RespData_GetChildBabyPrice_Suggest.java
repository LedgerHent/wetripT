package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;
/**
 * 建议 推荐最优预定
 * @author selfwhisper
 *
 */
public class RespData_GetChildBabyPrice_Suggest implements Serializable{
	private static final long serialVersionUID = -4822433439921198290L;
	@Description("推荐预定舱位代码") 
	public String cabin;// 字符串
	@Description("推荐预定舱位儿童票原价") 
	public Double price;// 双精度数字
	@Description("推荐预定舱位儿童票优惠价") 
	public Double rebatePrice;// 双精度数字
	@Description("推荐预定舱位价格来源") 
	public String priceSource;// 字符串
	@Description("推荐预定舱位机场建设费") 
	public Double airportTax;// 双精度数字
	@Description("推荐预定舱位燃油费") 
	public Double fuelSurTax;// 双精度数字
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airportTax == null) ? 0 : airportTax.hashCode());
		result = prime * result + ((cabin == null) ? 0 : cabin.hashCode());
		result = prime * result
				+ ((fuelSurTax == null) ? 0 : fuelSurTax.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((priceSource == null) ? 0 : priceSource.hashCode());
		result = prime * result
				+ ((rebatePrice == null) ? 0 : rebatePrice.hashCode());
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
		RespData_GetChildBabyPrice_Suggest other = (RespData_GetChildBabyPrice_Suggest) obj;
		if (airportTax == null) {
			if (other.airportTax != null)
				return false;
		} else if (!airportTax.equals(other.airportTax))
			return false;
		if (cabin == null) {
			if (other.cabin != null)
				return false;
		} else if (!cabin.equals(other.cabin))
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
		return true;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
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
	
}
