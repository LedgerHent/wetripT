package com.viptrip.wetrip.model.base;


/**
 * 结算方式-data数据
 * id-结算方式编号
 * name-结算方式名称
 * price-结算方式附加费用
 * description-结算方式描述
 */
public class Response_BusinessType extends Response_PriceAndDescription {
	private static final long serialVersionUID = -788323155138582624L;


	/**
	 * 适合的业务类型-0-不限|1-因公|2-因私
	 */
	public int businessType;
	
	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}
	
	@Override
	public String toString() {
		return "Response_BusinessType [businessType=" + businessType
				+ ", price=" + price + ", description=" + description + ", id="
				+ id + ", name=" + name + ", status=" + status + "]";
	}
	
}
