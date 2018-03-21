package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_BusinessType;

import etuf.v1_0.model.v2.base.Response_Base;
/**
 * 结算方式
 * data-
 * data[0].id	结算方式编号	字符串
 * data[0].name	结算方式名称	字符串
 * data[0].price	结算方式附加费用	双精度数字，0表示免费
 * data[0].description	结算方式描述	字符串
 * data[0].businessType	适合的业务类型	数字，0-不限|1-因公|2-因私
 */
public class Response_GetPayMethodList extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1689655166229370835L;
	
	/**
	 * data--结算详情
	 */
	public List<Response_BusinessType> data;

	
	
	
	public List<Response_BusinessType> getData() {
		return data;
	}

	public void setData(List<Response_BusinessType> data) {
		this.data = data;
	}
	
	
	
	
}
