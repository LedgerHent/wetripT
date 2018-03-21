package com.viptrip.hotelHtml5.vo.tmc;

public class StaffGroup extends StaffInfo implements JhData{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3906878150989505494L;
	private Integer groupId;
	private String groupName;
	private Integer departmentId;
	private String departmentName;
	//里程卡号
	private String mileageCardNo;
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
