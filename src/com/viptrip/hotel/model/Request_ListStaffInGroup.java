package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_ListStaffInGroup extends Request_Base{
	public Integer orgId;
	public Integer groupId;
	public Page page;
}
