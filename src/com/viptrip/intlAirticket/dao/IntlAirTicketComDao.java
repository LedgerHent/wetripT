package com.viptrip.intlAirticket.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.viptrip.base.dao.BaseDaoForJPA;

@Component
public class IntlAirTicketComDao extends BaseDaoForJPA{
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 使用HQL查询整数(任何可以返回整数的HQL语句) 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryHQL
	 *            select o.id from User o where o.id =36
	 * @param parameterArray
	 *            new Object[]{36}
	 */
	public final Object queryForObj(String queryIntHQL, Object[] parameterArray) {
		Query query = entityManager.createQuery(queryIntHQL);
		setQueryParameterValues(parameterArray, query);
		Object result = query.getSingleResult();
		return result;
	}
	
	private final void setQueryParameterValues(Object[] parameterArray, Query query) {
		if (parameterArray == null || parameterArray.length == 0)
			return;
		for (int i = 0; i < parameterArray.length; i++) {
			query.setParameter(i + 1, parameterArray[i]);
		}
	}
}
