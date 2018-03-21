package com.viptrip.hotel.model.staff;

public class StaffGroup extends StaffInfo{
	public Integer groupId;
	public String groupName;
	public Integer departmentId;
	public String departmentName;
	//里程卡号
	public String mileageCardNo;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
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

	
	
}
