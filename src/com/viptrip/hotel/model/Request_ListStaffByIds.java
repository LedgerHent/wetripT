package com.viptrip.hotel.model;

import java.util.List;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_ListStaffByIds extends Request_Base{
 
	public Integer orgId;
	public List<Integer> ids;
	public Page page;
}
