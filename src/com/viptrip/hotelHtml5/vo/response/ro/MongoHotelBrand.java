package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class MongoHotelBrand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String  brandId;             //品牌id，多个用，分开
    private String  brandCnName;         //品牌中文名
    private String  brandEnName;         //品牌英文名
    
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandCnName() {
		return brandCnName;
	}
	public void setBrandCnName(String brandCnName) {
		this.brandCnName = brandCnName;
	}
	public String getBrandEnName() {
		return brandEnName;
	}
	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}
}
