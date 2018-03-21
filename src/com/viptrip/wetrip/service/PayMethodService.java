package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.base.Response_BusinessType;
/**
 * 获取保险列表
 */
public interface PayMethodService {

	public TAcUser findUser(Long userID);
	
	public List<Response_BusinessType> getPayMethodList(Long userID,String travelType);

}
