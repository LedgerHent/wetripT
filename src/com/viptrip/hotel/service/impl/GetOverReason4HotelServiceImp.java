package com.viptrip.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.service.GetOverReason4HotelService;
import com.viptrip.wetrip.dao.ext.ComDao;

@Service
public class GetOverReason4HotelServiceImp implements GetOverReason4HotelService{

	@Resource
	private ComDao dao;

	@Override
	public List<String> getOverReason4HotelList() {
		String sql="select t.reason from T_EXCESS_MANAGE t where t.type between 1000 and 2000 ";
		return (List<String>) dao.queryBySQL(sql,null);
	}
	
	
	
	
}
