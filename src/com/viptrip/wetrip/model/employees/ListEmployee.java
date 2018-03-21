package com.viptrip.wetrip.model.employees;

import com.viptrip.wetrip.model.base.Response_PersonMessage;

@SuppressWarnings("serial")
public class ListEmployee extends Response_PersonMessage{
	
	public int departmentId;//部门编号
	
	public String departmentName;//部门名称
	
	public String mileageCardNo;//里程卡号
	
	public boolean isplatFormOrg;//是否是平台企业

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getMileageCardNo() {
		return mileageCardNo;
	}

	public void setMileageCardNo(String mileageCardNo) {
		this.mileageCardNo = mileageCardNo;
	}

	public boolean isIsplatFormOrg() {
		return isplatFormOrg;
	}

	public void setIsplatFormOrg(boolean isplatFormOrg) {
		this.isplatFormOrg = isplatFormOrg;
	}
	

	
	
	

	
	

}
