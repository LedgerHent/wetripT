package com.viptrip.common.model;

import com.viptrip.hotel.model.page.Page;

public class Request_ListApprovalPending extends Request_OrderBase {	
	public Long auditorId;
	public String travellerName;
	public String costName;
	public Integer state;
	public Page page=new Page();

}
