package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;
/**
 * 企业分组模糊查询
 * @author jh
 *
 */
@SuppressWarnings("serial")
public class Request_GetOrgGroupsLikeName extends Request_Base{
	
	public Integer orgId;
	public String nameKey;
	public Page page;
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getNameKey() {
		return nameKey;
	}
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	
}
