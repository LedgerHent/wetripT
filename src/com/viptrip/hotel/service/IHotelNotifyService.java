package com.viptrip.hotel.service;

import java.util.List;

import com.viptrip.hotel.entity.HotelNotify;

public interface IHotelNotifyService {
	/**
	 * 重试次数+1
	 * @param orderno
	 */
	public void updateCount(String orderno,int count);
	
	/**
	 * 保存
	 * @param notify
	 */
	public void save(HotelNotify notify);
	
	/**
	 * 更新
	 * @param notify
	 */
	public void update(HotelNotify notify);
	
	/**
	 * 查询
	 * @param hql
	 */
	public List<HotelNotify> query(String hql);
}
