package com.viptrip.hotel.service;

import com.viptrip.hotel.entity.HotelPayInfo;

public interface IHotelPayInfoService {

	/**
	 * 通过orderno获取支付信息
	 * @param orderno
	 * @return
	 */
	public HotelPayInfo getHotelPayInfoByOrderno(String orderno);
	
	/**
	 * 保存
	 * @param hpi
	 */
	public void save(HotelPayInfo hpi);
	
	/**
	 * 更新
	 * @param hpi
	 */
	public void update(HotelPayInfo hpi);
}
