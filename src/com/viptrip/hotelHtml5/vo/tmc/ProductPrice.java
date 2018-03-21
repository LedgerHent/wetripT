package com.viptrip.hotelHtml5.vo.tmc;

import java.io.Serializable;
import java.util.Date;

public class ProductPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 房价日期
	 */
	private Date priceDay;
	/**
	 * 房价日期(yyyy-MM-dd)
	 */
	private String priceDayStr;
	/**
	 * 当日税金
	 */
	private Double taxPrice;
	
	/**
	 * 货币代码
	 */
	private String currency;
	
	private Double finalPrice;
	
	private Double finalPriceCNY;
	
	/**
	 * 当日税金
	 */
	private Double taxPriceCNY;
	
	//餐食信息
	private String mealsInfo;
	//酒水信息
	private String drinksInfo;
	//是否含酒水
	private Short hasWine;    
	//是否含饮料
	private Short hasDrinks;
	//早餐份数
	private Short breakfastNumber;
	//午餐份数
	private Short lunchNumber;   
	//晚餐份数
	private Short supperNumber;
	//drr名称
	private String priceDrrNames;
	
	private Double finalCost;
	
	private Double finalCostCNY;
	
	public Date getPriceDay() {
		return priceDay;
	}
	public void setPriceDay(Date priceDay) {
		this.priceDay = priceDay;
	}
	public String getPriceDayStr() {
		return priceDayStr;
	}
	public void setPriceDayStr(String priceDayStr) {
		this.priceDayStr = priceDayStr;
	}
	public Double getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Double getFinalPriceCNY() {
		return finalPriceCNY;
	}
	public void setFinalPriceCNY(Double finalPriceCNY) {
		this.finalPriceCNY = finalPriceCNY;
	}
	public Double getTaxPriceCNY() {
		return taxPriceCNY;
	}
	public void setTaxPriceCNY(Double taxPriceCNY) {
		this.taxPriceCNY = taxPriceCNY;
	}
	public String getMealsInfo() {
		return mealsInfo;
	}
	public void setMealsInfo(String mealsInfo) {
		this.mealsInfo = mealsInfo;
	}
	public String getDrinksInfo() {
		return drinksInfo;
	}
	public void setDrinksInfo(String drinksInfo) {
		this.drinksInfo = drinksInfo;
	}
	public Short getHasWine() {
		return hasWine;
	}
	public void setHasWine(Short hasWine) {
		this.hasWine = hasWine;
	}
	public Short getHasDrinks() {
		return hasDrinks;
	}
	public void setHasDrinks(Short hasDrinks) {
		this.hasDrinks = hasDrinks;
	}
	public Short getBreakfastNumber() {
		return breakfastNumber;
	}
	public void setBreakfastNumber(Short breakfastNumber) {
		this.breakfastNumber = breakfastNumber;
	}
	public Short getLunchNumber() {
		return lunchNumber;
	}
	public void setLunchNumber(Short lunchNumber) {
		this.lunchNumber = lunchNumber;
	}
	public Short getSupperNumber() {
		return supperNumber;
	}
	public void setSupperNumber(Short supperNumber) {
		this.supperNumber = supperNumber;
	}
	public String getPriceDrrNames() {
		return priceDrrNames;
	}
	public void setPriceDrrNames(String priceDrrNames) {
		this.priceDrrNames = priceDrrNames;
	}
	public Double getFinalCost() {
		return finalCost;
	}
	public void setFinalCost(Double finalCost) {
		this.finalCost = finalCost;
	}
	public Double getFinalCostCNY() {
		return finalCostCNY;
	}
	public void setFinalCostCNY(Double finalCostCNY) {
		this.finalCostCNY = finalCostCNY;
	}   

}
