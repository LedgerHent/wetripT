package com.viptrip.wetrip.service;




import java.util.List;
import java.util.Map;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.entity.OrgData;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_ListStaff;

public interface ListStaffService {
	
	public List<TAcUser> getListStaff(Request_ListStaff para);
	
	//根据部门ID查找部门信息
	public OrgData getOrgData(Long orgid);
	public Map<Long,String> getOrgDataMap(List<Long> orgid);
}
