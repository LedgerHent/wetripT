package com.viptrip.common.model;

import java.util.List;

import com.viptrip.hotel.model.page.Page;
import com.viptrip.wetrip.model.base.Response_Base;

public class Response_ListApprovalPending extends Response_Base {
	public Page page=new Page();
	public List<TTmcApproveProcessInfo> list;

}
