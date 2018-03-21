package com.viptrip.hotel.service;

import java.util.List;

import com.viptrip.hotel.entity.CompanyInfo;



public interface GetDepartmentsByIdsService {
	CompanyInfo getAllOrg(int orgid,String ids, CompanyInfo topTacOrg,
			List<CompanyInfo> childs);
	List<CompanyInfo> getOrgChildById(int id,String ids);
}
