package com.viptrip.hotel.model;

import java.util.List;

import com.viptrip.hotel.model.base.Request_Base;

public class Request_GetDepartmentsByIds extends  Request_Base{
	
	/**
	 * 2017-7-10 hanzhicheng
	 */
	private static final long serialVersionUID = 5380035490649036139L;
	public Integer orgId;
	public List<Integer> ids;

}
