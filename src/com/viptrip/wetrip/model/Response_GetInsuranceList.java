package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_BusinessType;
import com.viptrip.wetrip.model.base.Response_PriceAndDescription;

import net.sf.json.JSONArray;

import etuf.v1_0.model.v2.base.Response_Base;
/**
 * 保险产品 - 返回
 * id-保险编号
 * name-保险名称
 */
public class Response_GetInsuranceList extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1689655166229370835L;
	
	/**
	 * data
	 */
	public List<Response_PriceAndDescription> data;

	
	
	
	public List<Response_PriceAndDescription> getData() {
		return data;
	}

	public void setData(List<Response_PriceAndDescription> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response_GetInsuranceList [data=" + data + ", status=" + status
				+ "]";
	}
	
	
	
	
}
