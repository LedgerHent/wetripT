package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_GetBookStaffList extends Request_Base{
	public Integer businessType;
	public Page page;

}
