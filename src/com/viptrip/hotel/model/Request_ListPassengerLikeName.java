package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

public class Request_ListPassengerLikeName extends Request_Base{
	
	/**
	 * 2017-7-19 hanzhicheng
	 */
	private static final long serialVersionUID = 6240289998166027246L;
	public String nameKey;
	public Page page;
}
