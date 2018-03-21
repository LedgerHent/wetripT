package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.model.employees.CompanyInfo;

public interface GetEnterpriseFrameworkService {

//	public TAcOrg getOrg(Long orgid);
	
//	public List<TAcOrg> getOrgChild(Long  company);
	
//	public List<TAcOrg> getOrgChildById(Long  orgid);
	
//	public TAcOrg getAllOrg(Long orgid,TAcOrg topTacOrg,List<TAcOrg> childs);
	
	public CompanyInfo getAllOrg(int orgid,CompanyInfo topTacOrg,List<CompanyInfo> childs);
	
	public List<CompanyInfo> getOrgChildById(int orgid);
}
