package com.viptrip.hotelHtml5.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.viptrip.hotelHtml5.vo.EmpAndOrg;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.policy.TTmcEnterpAreaLv;


public interface EmployeeService {

	
	/*public List<TAcUser> queryUser(String username,Long orgid);
	
	public TAcOrg queryOrg(Long orgid);
	*/
	public List<EmpAndOrg> queryData(String username,TAcUser user);
}
