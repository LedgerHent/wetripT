package com.viptrip.base.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.viptrip.util.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库查询基类
 * @author selfwhisper
 *
 */
public abstract class BaseDaoForJPA {

	private static Logger logger = LoggerFactory.getLogger(BaseDaoForJPA.class);

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 保存实体,实体必须使用符合JPA规范的注解进行配置 保存之后会给主键自动赋值(具体请查阅JPA EntityManager.persist
	 * 文档说明)
	 */
	public final void executeSave(Object entity) {
		this.entityManager.persist(entity);
	}

	/**
	 * 更新实体,实体必须使用符合JPA规范的注解进行配置 保存之后会给主键自动赋值(具体请查阅JPA EntityManager.merge 文档说明)
	 */
	public final Object executeUpdate(Object entity) {
		return this.merge(entity);
	}

	/**
	 * 根据主键ID查询实体,实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param id
	 *            主键
	 * @param clazz
	 *            需要查询的实体类
	 */
	public final <T> T queryForEntity(Object id, Class<T> clazz) {
		T entity = entityManager.find(clazz, id);
		return entity;
	}

	/**
	 * 根据实体Class 查询改实体所有的记录， 实体必须使用符合JPA规范的注解进行配置 由于是查询所有记录，输出效率考虑 请慎重使用
	 * 
	 * @param clazz
	 *            需要查询的实体类
	 */
	public final <T> List<T> queryForList(Class<T> clazz) {
		return this.queryForList(clazz, null, null);
	}

	/**
	 * 更新实体,实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param updateHQL
	 *            语言
	 *            <code>update User o set o.userName = ?  where o.id = ?</code>
	 * @param parameterArray
	 *            <code>new Object{"张三",1} ; </code>
	 */
	public final boolean executeUpdate(String updateHQL,
			Object[] parameterArray) {
		return this.execute(updateHQL, parameterArray);
	}

	/**
	 * 更新实体,实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param updateHQL
	 *            语言
	 *            <code>update User o set o.userName = ?  where o.id = ?</code>
	 */
	public final boolean executeUpdate(String updateHQL) {
		return this.executeUpdate(updateHQL, null);
	}

	/**
	 * 删除实体,实体必须使用符合JPA规范的注解进行配置
	 */
	public final <T> void executeDelete(T entity) {
		this.entityManager.remove(entity);
	}

	/**
	 * 根据主键ID删除实体,实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param id
	 *            主键
	 * @param clazz
	 *            需要删除实体类
	 */
	public final <T> void executeDelete(Class<T> clazz, Object id) {
		this.executeDelete(this.queryForEntity(id, clazz));
	}

	/**
	 * 删除实体,实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param deleteHQL
	 *            语言 <code>delete  User o  where o.id = ?</code>
	 *@param parameterArray
	 */
	public final <T> boolean executeDelete(String deleteHQL,
			Object[] parameterArray) {
		return this.execute(deleteHQL, parameterArray);
	}

	/**
	 * 根据实体Class,条件，参数 查询记录， 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 */
	public final <T> List<T> queryForList(Class<T> clazz,
			LinkedHashMap<String, String> conditions, Object[] parameterArray) {
		return this.queryForList(clazz, conditions, parameterArray, null);
	}

	/**
	 * 根据实体Class,条件，参数 查询记录，可以对相应的记录进行排序 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 * @param orderBy
	 *            put("id","asc")
	 */
	public final <T> List<T> queryForList(Class<T> clazz,
			LinkedHashMap<String, String> conditions, Object[] parameterArray,
			LinkedHashMap<String, String> orderBy) {
		String queryHQL = getQueryString(clazz, conditions, orderBy);
		return queryForList(queryHQL, parameterArray);
	}

	/**
	 * 使用HQL 进行实体的查询 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryHQL
	 *            select o from User o where o.id = 36
	 */
	public final <T> List<T> queryForList(String queryHQL) {
		return this.queryForList(queryHQL, null);
	}

	/**
	 * 根据实体Class 查询改实体所有的记录，可以对相应的记录进行排序 实体必须使用符合JPA规范的注解进行配置 由于是查询所有记录，输出效率考虑
	 * 请慎重使用
	 * 
	 * @param clazz
	 *            需要查询的实体类
	 * @param orderBy
	 *            put("id","asc")
	 */
	public final <T> List<T> queryForList(Class<T> clazz,
			LinkedHashMap<String, String> orderBy) {
		return this.queryForList(clazz, null, null, orderBy);
	}

	/**
	 * 使用HQL 进行实体的查询 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryHQL
	 *            select o from User o where o.id =?
	 * @param parameterArray
	 *            new Object[]{36}
	 */
	public final <T> List<T> queryForList(String queryHQL,
			Object[] parameterArray) {
		return this.queryForList(queryHQL, parameterArray, 0, 0);
	}

	/**
	 * 使用HQL查询整数(任何可以返回整数的HQL语句) 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryIntHQL
	 *            select o.id from User o where o.id =36
	 */
	public final int queryForInt(String queryIntHQL) {
		return this.queryForInt(queryIntHQL, null);
	}

	/**
	 * 使用HQL查询整数(任何可以返回整数的HQL语句) 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryIntHQL
	 *            select o.id from User o where o.id =36
	 * @param parameterArray
	 *            new Object[]{36}
	 */
	public final int queryForInt(String queryIntHQL, Object[] parameterArray) {
		Query query = entityManager.createQuery(queryIntHQL);
		setQueryParameterValues(parameterArray, query);
		int result = Integer.parseInt(query.getSingleResult().toString());
		return result;
	}
	/**
	 * 使用HQL查询   实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryHQL
	 *            select o.id from User o where o.id =36
	 * @param parameterArray
	 *            new Object[]{36}
	 */
	public final Object queryForObject(String queryHQL, Object[] parameterArray) {
		Query query = entityManager.createQuery(queryHQL);
		setQueryParameterValues(parameterArray, query);
		Object result = null;
		try {
			result = query.getSingleResult();
		} catch (Exception e) {
			logger.info(StringUtil.getLogInfo(null,null,"没有查询到数据"));
		}
		return result;
	}
	
	/**
	 * 使用HQL查询整数(任何可以返回整数的HQL语句) 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryIntHQL
	 *            select o.id from User o where o.id =36
	 */
	public final Double queryForDouble(String queryIntHQL) {
		return this.queryForDouble(queryIntHQL, null);
	}
	
	/**
	 * 使用HQL查询整数(任何可以返回整数的HQL语句) 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param queryIntHQL
	 *            select o.id from User o where o.id =36
	 * @param parameterArray
	 *            new Object[]{36}
	 */
	public final Double queryForDouble(String queryIntHQL, Object[] parameterArray) {
		Query query = entityManager.createQuery(queryIntHQL);
		setQueryParameterValues(parameterArray, query);
		Double result = Double.parseDouble(query.getSingleResult().toString());
		return result;
	}

	/**
	 * 查询整数
	 * @param hql
	 * @param parameterArray
	 * @return
	 */
	public final Integer queryInteger(String hql,Object[] parameterArray){
		Integer i = null;
		Query query = entityManager.createQuery(hql);
		List list = query.getResultList();
		if(null!=list && !list.isEmpty()){
			i = Integer.valueOf(list.get(0).toString());
		}
		return i;
	}
	
	/**
	 * 使用原生sql查询
	 * @param sql
	 * @param param
	 * @return
	 */
	public final List<?> queryBySQL(String sql,Object[] param){
		Query query = entityManager.createNativeQuery(sql);
		setQueryParameterValues(param, query);
		List<?> resultList = query.getResultList();
		return resultList;
	}

	/**
	 * 使用原生sql查询
	 * @param sql
	 * @param param
	 * @return
	 */
	public final <E> E queryObjectBySQL(String sql,Object[] param,Class<E> clz){
		Query query = entityManager.createNativeQuery(sql);
		setQueryParameterValues(param, query);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clz));
		List<E> resultList = query.getResultList();
		return (resultList==null||resultList.size()<=0)?null:resultList.get(0);
	}
	
	/**
	 * 使用原生sql查询,返回元素为map，如果记录数过多，会有性能影响
	 * @param sql
	 * @param param
	 * @param clazz
	 * @return List<T>
	 */
	public final <T> List<T> queryBySQLForObject(String sql,Object[] param,Class<T> clazz){
		Query query = entityManager.createNativeQuery(sql);		
		setQueryParameterValues(param, query);
		Object responseTypeObj=null;
		try {
			responseTypeObj = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (responseTypeObj instanceof Map) {
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  			
		} else {
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));  
		}		
		List<T> resultList = query.getResultList();
		return resultList;
	}

	final List queryForList(String queryHQL, Object[] parameterArray,
			int firstResult, int maxResult) {
		Query query = entityManager.createQuery(queryHQL);
		setQueryParameterValues(parameterArray, query);
		if (firstResult >= 0)
			query.setFirstResult(firstResult);
		if (maxResult > 0)
			query.setMaxResults(maxResult);
		return query.getResultList();
	}

	final String getOrderBy(LinkedHashMap<String, String> orderBy) {
		if (orderBy == null || orderBy.size() == 0)
			return "";
		StringBuilder orderByBuilder = new StringBuilder(" order by ");
		Set<String> orderFields = orderBy.keySet();
		for (String orderField : orderFields) {
			orderByBuilder.append(" o.").append(orderField).append(" ")
					.append(orderBy.get(orderField)).append(",");
		}
		orderByBuilder.delete(orderByBuilder.length() - 1,
				orderByBuilder.length());
		return orderByBuilder.toString();
	}

	final void setQueryParameterValues(Object[] parameterArray, Query query) {
		if (parameterArray == null || parameterArray.length == 0)
			return;
		for (int i = 0; i < parameterArray.length; i++) {
			query.setParameter(i + 1, parameterArray[i]);
		}
	}

	final <T> String getQueryString(Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			LinkedHashMap<String, String> orderBy) {
		StringBuilder queryBuilder = new StringBuilder("  select o from  ");
		queryBuilder.append(getEntityFullName(clazz)).append(" o ")
				.append(getCondtitons(conditions)).append(getOrderBy(orderBy));
		return queryBuilder.toString();
	}

	final <T> String getEntityFullName(Class<T> clazz) {
		return clazz.getName();
	}

	final String getCondtitons(LinkedHashMap<String, String> conditions) {
		StringBuilder conditionsBuilder = new StringBuilder("  where 1=1  ");
		if (conditions == null || conditions.size() == 0) {
			return conditionsBuilder.toString();
		}
		Set<String> conditonFields = conditions.keySet();
		for (String conditionField : conditonFields) {
			conditionsBuilder.append(" and o.");
			conditionsBuilder.append(conditionField);
			conditionsBuilder.append(" ");
			conditionsBuilder.append(conditions.get(conditionField));
			conditionsBuilder.append("  ?  ");
		}
		return conditionsBuilder.toString();
	}

	private final boolean execute(String execuetHQL, Object[] parameterArray) {
		Query query = entityManager.createQuery(execuetHQL);
		this.setQueryParameterValues(parameterArray, query);
		return query.executeUpdate() > 0 ? true : false;
	}

	/**
	 * 执行原生sql
	 * @param execuetSQL
	 * @param parameterArray
	 */
	public void executeSQL(String execuetSQL, Object[] parameterArray) {
		Query query = entityManager.createNativeQuery(execuetSQL);
		this.setQueryParameterValues(parameterArray, query);
		query.executeUpdate();
		//return query.executeUpdate() > 0 ? true : false;
	}

	private final <T> T merge(T entity) {
		return entityManager.merge(entity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
