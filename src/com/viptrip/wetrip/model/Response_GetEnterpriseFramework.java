package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.base.Response_IdAndName;
import com.viptrip.wetrip.model.employees.CompanyInfo;

@SuppressWarnings("serial")
public class Response_GetEnterpriseFramework extends Response_Base{

	public List<CompanyInfo> data;

	
	@Override
	public String toString() {
		return "Response_GetEnterpriseFramework [data=" + data + ", status="
				+ status + ", getData()=" + getData() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public List<CompanyInfo> getData() {
		return data;
	}

	public void setData(List<CompanyInfo> data) {
		this.data = data;
	}
	
	
	
}
