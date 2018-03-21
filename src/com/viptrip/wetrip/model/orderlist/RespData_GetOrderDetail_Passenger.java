package com.viptrip.wetrip.model.orderlist;

import com.viptrip.wetrip.model.base.Response_IdAndName;

public class RespData_GetOrderDetail_Passenger extends Response_IdAndName{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488226689967711504L;

	//电话号码
	public String mobile;
	//电子邮箱
	public String email;
	//证件类型
	public Integer idType;
	//证件号码
	public String idNum;
	//成本中心
	public String costCenter;
	//项目号
	public String projectNo;
	//保险编号
	public Integer insuranceId;
	//保险数量
	public Integer insuranceNum;
	//保险价格
	public Double insurancePrice;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public Integer getInsuranceNum() {
		return insuranceNum;
	}
	public void setInsuranceNum(Integer insuranceNum) {
		this.insuranceNum = insuranceNum;
	}
	public Double getInsurancePrice() {
		return insurancePrice;
	}
	public void setInsurancePrice(Double insurancePrice) {
		this.insurancePrice = insurancePrice;
	}
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
