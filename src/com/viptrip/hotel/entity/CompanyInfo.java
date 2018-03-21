package com.viptrip.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.viptrip.wetrip.model.base.Response_IdAndName;


public class CompanyInfo extends Response_IdAndName implements Serializable{
	public List<CompanyInfo> list;//子部门

	public String Isbottom;
	
	
	

	public List<CompanyInfo> getList() {
		return list;
	}

	public void setList(List<CompanyInfo> list) {
		this.list = list;
	}

	public String getIsbottom() {
		return Isbottom;
	}

	public void setIsbottom(String isbottom) {
		Isbottom = isbottom;
	}
}
