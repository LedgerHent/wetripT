package com.viptrip.hotel.model.getDepartmentsLikeName;

import java.util.List;

import com.viptrip.hotel.model.page.Page;
import com.viptrip.wetrip.model.base.Response_IdAndName;


public class CompanyInfoGDLN extends   Response_IdAndName{
  
	/**
	 * 2017-7-11 hanzhicheng
	 */
	private static final long serialVersionUID = -4821546088066079210L;
	public List<CompanyInfoGDLN> list;//子部门
	public Page page;
	public List<CompanyInfoGDLN> getList() {
		return list;
	}
	public void setList(List<CompanyInfoGDLN> list) {
		this.list = list;
	}
	
	
	
	
}
