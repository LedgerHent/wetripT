package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class MongoHotelFacilittyItem extends MongoHotelObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//设施项ID
    private String  itemId;
    //设施项名称
    private String  itemName;
    //设施类型（1、网络连接 2、停车场 3、综合设施 4、活动设施 5、服务项目）
    private String  itemType;
    //设施类型名（网络连接、停车场、综合设施、活动设施、服务项目）
    private String  typeName;
    
    //设施id
    private String facilitiesId;
    //是否免费（1、免费2、收费）
    private String isFree;
    //备注
    private String remark;
    
    
    
    
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFacilitiesId() {
		return facilitiesId;
	}
	public void setFacilitiesId(String facilitiesId) {
		this.facilitiesId = facilitiesId;
	}
	public String getIsFree() {
		return isFree;
	}
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
