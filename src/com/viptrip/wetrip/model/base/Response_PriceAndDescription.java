package com.viptrip.wetrip.model.base;

/**
 * 保险产品 - 返回
 * id-保险编号
 * name-保险名称
 */
public class Response_PriceAndDescription extends Response_IdAndName{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1689655166229370835L;

	
	/**
	 * 保险价格
	 */
	public double price;
	/**
	 * 保险描述
	 */
	public String description;
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Response_PriceAndDescription [price=" + price
				+ ", description=" + description + ", id=" + id + ", name="
				+ name + ", status=" + status + "]";
	}
	
	
	
}
