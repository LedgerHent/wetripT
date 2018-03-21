/**
* @Title: Abstract TmcOrderDayPrice
* @Description:  TmcOrderDayPrice.
* @author Tangqingshan
* @date 2017-07-10 14:47:15
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderDayPrice
* @Description:  TmcOrderDayPrice.
* @author Tangqingshan 
* @date 2017-07-10 14:47:15
*/
public class TmcOrderDayPrice implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	/**
	 * 订单酒店表主键
	 */
	private String orderHotelId;
	/**
	 * 日期
	 */
	private Date priceDate;
	/**
	 * 税费
	 */
	private double tax;
	/**
	 * 预订房间单价
	 */
	private double price;
	/**
	 * 预订房间成本
	 */
	private double cost;
	/**
	 * 预订房间总价
	 */
	private double totalPrice;

	private String priceDateStr;
	
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getOrderHotelId() {
		return orderHotelId;
	}
	public void setOrderHotelId(String orderHotelId) {
		this.orderHotelId = orderHotelId;
	}
	public Date getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPriceDateStr() {
		return priceDateStr;
	}
	public void setPriceDateStr(String priceDateStr) {
		this.priceDateStr = priceDateStr;
	}
	
}

