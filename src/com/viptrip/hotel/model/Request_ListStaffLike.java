package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.key.Key;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_ListStaffLike extends Request_Base{

	public Integer orgId;
	public Key key;
	public Page page;

}
