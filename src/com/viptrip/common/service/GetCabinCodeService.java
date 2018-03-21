package com.viptrip.common.service;


import com.viptrip.common.model.Request_GetCabinCodeByType;
import com.viptrip.common.model.Response_GetCabinCodeByType;

public interface GetCabinCodeService {
	/**
	 * 根据舱位等级查询舱位
	 * @param para
	 * @return
	 */
	public abstract Response_GetCabinCodeByType GetCabinCodeByType(Request_GetCabinCodeByType para);
}
