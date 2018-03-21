package com.viptrip.wetrip.model.employees;

import com.viptrip.wetrip.model.base.Response_IdAndName;
import com.viptrip.wetrip.model.base.Response_PersonMessage;

@SuppressWarnings("serial")
public class Auditor extends Response_IdAndName{
	
	public String auditDepartmentIds;//可审核部门编号序列，逗号分隔整型数字

	public String mobile;
	
	public String email;
	
	public int departmentId;//部门编号
	
	public String departmentName;//部门名称
	
	public String getAuditDepartmentIds() {
		return auditDepartmentIds;
	}

	public void setAuditDepartmentIds(String auditDepartmentIds) {
		this.auditDepartmentIds = auditDepartmentIds;
	}

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
	
	
}
