package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_ListAuditor;
import com.viptrip.wetrip.model.Response_ListAuditor;

public interface ListAuditorService {
	
	public List<String> getOrgCheckManId(Request_ListAuditor para);
	
	//查询审核人姓名、邮箱等
	public List<TAcUser> queryTAcUserPhoneandEmail(List<String> checkman);

	public TAcOrg queryForTAcOrg(Object id,TAcOrg tac);

	
	
}
