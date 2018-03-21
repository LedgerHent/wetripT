package com.viptrip.common.dao;

import org.springframework.stereotype.Component;


import com.viptrip.base.common.support.PageObject;
//import com.viptrip.base.dao.BaseDaoForJPA;
import com.viptrip.base.dao.PagingationDaoSupportForJPA;
@Component
public class TTmcApproveProcessDao extends PagingationDaoSupportForJPA {
	public PageObject queryForPagList(int currentPage,int pageSize,String queryForListHQL,Object[] preparedParameterArray) {
	    return this.queryForPaginationList(currentPage, pageSize, queryForListHQL, preparedParameterArray);
	}
}
