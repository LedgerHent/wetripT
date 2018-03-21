package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.wetrip.entity.TTicketRefund;

public interface ITicketRefundService {
	List<TTicketRefund> queryByTicketIds(String hql,List<Long> tids);
}
