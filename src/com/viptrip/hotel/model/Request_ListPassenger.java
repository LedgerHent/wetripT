package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

public class Request_ListPassenger extends Request_Base{

	/**
	 * 2017-7-5 hanzhicheng
	 */
	private static final long serialVersionUID = -1744183726819122191L;

	public Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	

}
