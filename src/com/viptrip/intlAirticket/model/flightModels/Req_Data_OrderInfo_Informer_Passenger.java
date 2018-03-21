package com.viptrip.intlAirticket.model.flightModels;
/**
 * 乘机人信息
 */
public class Req_Data_OrderInfo_Informer_Passenger {

	private int type;			//类型：1-企业员工，2-常旅客
	
	private long id;			//企业员工或常旅客id
	
	private int idType;			//证件类型：1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
	
	private long orgid;			//费用归属id（公司月结和预付款，此项必填）
	
	private String project;		//项目号
	
	private String mileage;		//里程号
	
	private int insuranceNum;	//保险份数
	
	private long insuranceId;	//保险id

	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public int getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(int insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public long getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(long insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	
}
