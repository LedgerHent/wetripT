package com.viptrip.hotel.model;

import java.util.List;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

/**
 * 企业分组查询-按id序列
 * @author jh
 *
 */
@SuppressWarnings("serial")
public class Request_GetOrgGroupsByIds extends Request_Base{

	public Integer orgId;
	public List<Integer> ids;
	
}
