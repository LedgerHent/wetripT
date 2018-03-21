package com.viptrip.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.dao.HotelComDao;
import com.viptrip.hotel.entity.HotelPayInfo;
import com.viptrip.hotel.service.IHotelPayInfoService;
import com.viptrip.util.StringUtil;

@Service
public class HotelPayInfoService implements IHotelPayInfoService{
	
	@Resource
	private HotelComDao dao;
	
	public HotelPayInfo getHotelPayInfoByOrderno(String orderno){
		HotelPayInfo result = null;
		if(!StringUtil.isEmpty(orderno)){
			result = dao.queryForEntity(orderno, HotelPayInfo.class);
		}
		return result;
	}

	public void save(HotelPayInfo hpi){
		if(null!=hpi)
			dao.executeSave(hpi);
	}
	
	public void update(HotelPayInfo hpi){
		if(null!=hpi)
			dao.executeUpdate(hpi);
	}
	
}
