package com.viptrip.wetrip.dao.ext;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.base.dao.PagingationDaoSupportForJPA;
import com.viptrip.wetrip.entity.TOrder;

@Component
public class GetOrderListDaoExt extends PagingationDaoSupportForJPA {
	
	public PageObject<TOrder> getPage(int start,int count,String hql,LinkedHashMap<String, String> conditions,Object[] params, LinkedHashMap<String, String> orderBy){
		return queryForPaginationListByIdx(start, count, TOrder.class, conditions, params, orderBy);
	}
	
}
