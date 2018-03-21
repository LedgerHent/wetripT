package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_PriceAndDescription;
/**
 * 获取保险列表
 */
public interface InsuranceService {

	public List<Response_PriceAndDescription> getInsuranceList();
	
}
