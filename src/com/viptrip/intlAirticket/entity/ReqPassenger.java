package com.viptrip.intlAirticket.entity;

public class ReqPassenger {
	
	
	/*public int type;			//类型，1：企业员工，2：常旅客
	public long id;				//员工id
	public int idtype;			//证件类型
	public long orgid;			//费用归属
	public String project;		//项目号
	public String mileage;		//里程号
	public int insuranceNum;	//保险份数
	public long insuranceId;	//保险id
*/	
	
	public String type;			//类型，1：企业员工，2：常旅客
	public String id;				//员工id
	public String idtype;			//证件类型
	public String orgid;			//费用归属
	public String project;		//项目号
	public String mileage;		//里程号
	public String insuranceNum;	//保险份数
	public String insuranceId;	//保险id
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
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
	public String getInsuranceNum() {
		return insuranceNum;
	}
	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	
}
