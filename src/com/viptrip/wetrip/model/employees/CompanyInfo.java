package com.viptrip.wetrip.model.employees;

import java.io.Serializable;
import java.util.List;

import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.model.base.Response_IdAndName;

@SuppressWarnings("serial")
public class CompanyInfo extends Response_IdAndName implements Serializable{
	
	public List<CompanyInfo> child;//子部门

	public String isbottom;//是否有下级
	
	
	public List<CompanyInfo> getChild() {
		return child;
	}

	public void setChild(List<CompanyInfo> child) {
		this.child = child;
	}

	public String getIsbottom() {
		return isbottom;
	}

	public void setIsbottom(String isbottom) {
		this.isbottom = isbottom;
	}
	
}
