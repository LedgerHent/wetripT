package com.viptrip.wetrip.model;


import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice;

/**
 * 儿童婴儿票价查询返回结果
 * @author selfwhisper
 *
 */
public class Response_GetChildBabyPrice extends Response_Base{
	private static final long serialVersionUID = -8986270711398085795L;
	
	public List<RespData_GetChildBabyPrice> data;

	
	public Response_GetChildBabyPrice() {
		super();
	}

	public Response_GetChildBabyPrice(List<RespData_GetChildBabyPrice> data) {
		this.data = data;
	}

	public List<RespData_GetChildBabyPrice> getData() {
		return data;
	}

	public void setData(List<RespData_GetChildBabyPrice> data) {
		this.data = data;
	}
	
	
}
