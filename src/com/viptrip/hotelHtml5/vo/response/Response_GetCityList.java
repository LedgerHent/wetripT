package com.viptrip.hotelHtml5.vo.response;

import java.util.List;

import com.viptrip.hotelHtml5.vo.response.ro.AreaInfo;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetCityList extends Response_Base{
	private static final long serialVersionUID = 1L;
	private List<AreaInfo> busiData;
	private String code;
	private String message;
	private Integer total;
	
	public List<AreaInfo> getBusiData() {
		return busiData;
	}

	public void setBusiData(List<AreaInfo> busiData) {
		this.busiData = busiData;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
