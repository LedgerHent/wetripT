package com.viptrip.wetrip.dao.ext;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.base.dao.PagingationDaoSupportForJPA;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.entity.TAcUser;

/**
 * 扩展dao，在基础dao不能满足业务需求的时候 使用当前dao
 * 在com.viptrip.base.dao下提供了两个封装好的基类dao，可以选择继承自该基类
 * 当然如果上述两个基类不满足业务需求，则可以自己手动编写，只需注入entityManager即可，可以参考BaseDaoForJPA
 * @author selfwhisper
 *
 */
@Component
public class TestDaoExt extends PagingationDaoSupportForJPA{
	
	public PageObject<TAcUser> list(String hql,Object[] params,PageParam pp){
		return queryForPaginationList(pp.getPageNum(), pp.getNumPerPage(), hql, params);
	}
	
	
	public PageObject<T> list_(String hql,Object[] params,PageParam pp){
        return queryForPaginationList(pp.getPageNum(), pp.getNumPerPage(), hql, params);
    }
}
