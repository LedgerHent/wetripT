package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.orglikename.Orglike;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_GetOrgLike extends Request_Base{
	public Orglike key;
	public Page page;
}
