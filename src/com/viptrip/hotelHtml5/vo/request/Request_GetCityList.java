package com.viptrip.hotelHtml5.vo.request;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_GetCityList extends Request_Base{
	private String parentId;
	private String pageSize;
	private String pageNum;
	private String lv;
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	
}
