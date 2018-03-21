package com.viptrip.intlAirticket.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.wetrip.dao.ext.ComDao;

@Service
public class IntlOrderService {

	@Resource
	private ComDao cdao;
	
	public TIntlOrder getOrderById(Long orderId){
		return cdao.queryForEntity(orderId, TIntlOrder.class);
	}
	
	public TIntlOrder getOrderByOrderNO(String orderNo){
		TIntlOrder result = null;
		String hql = "from TIntlOrder where intlOrderno=?";
		result = (TIntlOrder) cdao.queryForObject(hql, new Object[]{orderNo});
		return result;
	}
	
}
