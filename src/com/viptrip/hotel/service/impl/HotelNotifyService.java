package com.viptrip.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.dao.HotelComDao;
import com.viptrip.hotel.entity.HotelNotify;
import com.viptrip.hotel.service.IHotelNotifyService;
import com.viptrip.util.StringUtil;
@Service
public class HotelNotifyService implements IHotelNotifyService{
	@Resource
	private HotelComDao dao;
	
	public void save(HotelNotify notify){
		if(null!=notify){
			dao.executeSave(notify);
		}
	}
	
	public void update(HotelNotify notify){
		if(null!=notify){
			dao.executeUpdate(notify);
		}
	}
	
	public void updateCount(String orderno,int count){
		String hql = "update HotelNotify set count=? where orderno=?";
		dao.executeUpdate(hql, new Object[]{count,orderno});
	}

	public List<HotelNotify> query(String hql) {
		if(StringUtil.isNotEmpty(hql)){
			return dao.queryForList(hql);
		}
		return null;
	}
}
