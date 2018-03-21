package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class ReqData_BookAirTicket_Passenger implements Serializable{
	private static final long serialVersionUID = 6755640570135785860L;
	@Description("乘机人类型")
	public Integer type;//1企业员工 2常旅客
	@Description("乘机人id")
	public Integer id;
	@Description("证件类型")
	public Integer idType;//1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
	@Description("证件号码")
	public String idNum;
	@Description("成本中心（费用归属）id")
	public Integer costCenter;
	@Description("项目号")
	public String projectNo;
	@Description("保险编号")
	public Integer insuranceId;
	@Description("保险数量")
	public Integer insuranceNum;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(Integer costCenter) {
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
}
