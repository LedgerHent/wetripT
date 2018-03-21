package com.viptrip.hotelHtml5.vo.tmc.response;


import com.viptrip.hotelHtml5.util.PageUtil;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderInfoH5;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_ListTmcOrder extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	private PageUtil<TmcOrderInfoH5> pageInfo;

	public PageUtil<TmcOrderInfoH5> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageUtil<TmcOrderInfoH5> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
