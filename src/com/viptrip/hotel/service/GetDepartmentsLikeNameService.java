package com.viptrip.hotel.service;

import java.util.List;

import com.viptrip.hotel.model.Request_GetDepartmentsLikeName;
import com.viptrip.hotel.model.getDepartmentsLikeName.CompanyInfoGDLN;
import com.viptrip.wetrip.model.employees.CompanyInfo;

public interface GetDepartmentsLikeNameService {
	
	CompanyInfoGDLN getOrgChildById(int id,Request_GetDepartmentsLikeName arg0);
	CompanyInfoGDLN getAllOrg(int orgid, Request_GetDepartmentsLikeName arg0,
			CompanyInfoGDLN topTacOrg, List<CompanyInfoGDLN> childs);
}
