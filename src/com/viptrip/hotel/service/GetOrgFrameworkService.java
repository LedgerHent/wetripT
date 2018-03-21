package com.viptrip.hotel.service;

import java.util.List;

import com.viptrip.hotel.model.Request_GetOrgFramework;
import com.viptrip.wetrip.model.employees.CompanyInfo;

public interface GetOrgFrameworkService {
	CompanyInfo getAllOrg(Request_GetOrgFramework req,CompanyInfo topTacOrg,List<CompanyInfo> childs);
	List<CompanyInfo> getOrgChildById(int id);
}
