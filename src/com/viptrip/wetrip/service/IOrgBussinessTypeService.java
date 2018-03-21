package com.viptrip.wetrip.service;

import com.viptrip.wetrip.entity.TAcOrg;

public interface IOrgBussinessTypeService {

	public Integer getBussinessState(long orgid,Integer bussType,Integer source);
	
	public TAcOrg getOrgById(long orgid);
}
