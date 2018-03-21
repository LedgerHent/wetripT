package com.viptrip.wetrip.service;

public interface H5UserLoginService {
	/**
	 * 验证平台是否已维护，没有增加维护信息
	 * @param platformId
	 * @return
	 */
	public boolean checkHasPlatformId(Long platformId);
}
