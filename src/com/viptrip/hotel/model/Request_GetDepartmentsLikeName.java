package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

public class Request_GetDepartmentsLikeName extends Request_Base{

	
	/**
	 * 2017-7-10 hanzhicheng 
	 */
	private static final long serialVersionUID = 1424684739054595741L;
	public Integer orgId;
	public String nameKey;
	public Page page;
	
}
