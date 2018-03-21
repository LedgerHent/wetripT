package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.wetrip.entity.TUpdateDate;

public interface IUpdateDateService {
	List<TUpdateDate> queryByTicketIds(String hql,List<Long> tids);
}
