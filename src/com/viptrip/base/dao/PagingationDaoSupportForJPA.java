package com.viptrip.base.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
/**
 * 分页查询基类
 * @author selfwhisper
 *
 */
public abstract class PagingationDaoSupportForJPA extends BaseDaoForJPA {
	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param preparedParameterArray
	 *            new Object[]{13}
	 * @param queryForListQHL
	 *            </br>HQL语句 <code>select o from User o where o.id >?
	 * </br>或者from User o where o.id >?</code>
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			int pageSize, String queryForListHQL,
			Object[] preparedParameterArray) {
		int dataCount = queryCount(queryForListHQL, preparedParameterArray);
		PageObject<T> pageObject = new PageObject<T>(dataCount, currentPage,
				pageSize);
		pageObject.setPageList(queryForList(queryForListHQL,
				preparedParameterArray, pageObject.getStartPoint(),
				pageObject.getEndPoint()));
		return pageObject;
	}
	
	/**
	 * 根据HQL分页查询记录 带查询参数
	 * @param pp
	 * 		分页查询参数 PageParam
	 * @param queryForListHQL
	 * 		HQL语句  e.g. select u from User u where u.id > ?
	 * @param preparedParameterArray
	 * 		预处理参数  e.g. new Object[]{10}
	 * @return
	 */
	protected final <T> PageObject<T> queryForPaginationList(PageParam pp, String queryForListHQL,
			Object[] preparedParameterArray) {
		return queryForPaginationList(pp.getPageNum(), pp.getNumPerPage(), queryForListHQL, preparedParameterArray);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param queryForListQHL
	 *            </br>HQL语句 <code>select o from User o where o.id > 10
	 * </br>或者from User o where o.id >10</code>
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			int pageSize, String queryForListHQL) {
		return this.queryForPaginationList(currentPage, pageSize,
				queryForListHQL, null);
	}
	
	/**
	 * 根据HQL分页查询记录 不带查询参数
	 * @param pp
	 * 		分页查询参数 PageParam
	 * @param queryForListHQL
	 * 		HQL语句  from User u where u.id > 10
	 * @return
	 */
	protected final <T> PageObject<T> queryForPaginationList(PageParam pp, String queryForListHQL) {
		return queryForPaginationList(pp, queryForListHQL, null);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 * @param orderBy
	 *            put("id","asc")
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			int pageSize, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray,
			LinkedHashMap<String, String> orderBy) {
		String queryForListHQL = this
				.getQueryString(clazz, conditions, orderBy);
		return this.queryForPaginationList(currentPage, pageSize,
				queryForListHQL, preparedParameterArray);
	}
	
	/**
	 * 根据实体类型，开始记录数 记录条数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param start
	 *            开始记录数
	 * @param count
	 *            记录条数
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 * @param orderBy
	 *            put("id","asc")
	 */
	@SuppressWarnings("unchecked")
	protected final <T> PageObject<T> queryForPaginationListByIdx(int start,int count, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray,
			LinkedHashMap<String, String> orderBy) {
		PageObject<T> result = null;
		String queryForListHQL = this
				.getQueryString(clazz, conditions, orderBy);
		int dataCount = queryCount(queryForListHQL, preparedParameterArray);
		if(start < dataCount){
//			int maxResult = (start+count-1)>dataCount?dataCount:start+count-1;
			List<T> list = queryForList(queryForListHQL, preparedParameterArray, start, count);
			result = new PageObject<>(dataCount, 1);
			result.setPageList(list);
		}
		return result;
	}
	
	/**
	 * 根据实体类型分页查询记录  条件自行拼接 不带排序
	 * @param pp
	 * 		分页查询参数 PageParam
	 * @param clazz
	 * 		实体类Class
	 * @param conditions
	 * 		查询条件 LinkedHashMap<String,String> e.g. map.put("username","like")
	 * @param preparedParameterArray
	 * 		预处理参数 Object[] e.g. new Object[]{"%张三%"}
	 * @param orderBy
	 * 		排序字段 LinkedHashMap<String,String> e.g. map.put("id","desc");
	 * @return
	 */
	protected final <T> PageObject<T> queryForPaginationList(PageParam pp, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray,
			LinkedHashMap<String, String> orderBy) {
		return queryForPaginationList(pp.getPageNum(), pp.getNumPerPage(), clazz, conditions, preparedParameterArray, orderBy);
	}

	/**
	 * 根据实体类型分页查询记录  条件自行拼接 可以排序
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			int pageSize, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray) {
		return queryForPaginationList(currentPage, pageSize, clazz, conditions,
				preparedParameterArray, null);
	}
	
	/**
	 * 根据实体类型分页查询记录  条件自行拼接 不带排序
	 * @param pp
	 * 		分页查询参数 PageParam
	 * @param clazz
	 * 		实体类Class
	 * @param conditions
	 * 		查询条件 LinkedHashMap<String,String> e.g. map.put("username","like")
	 * @param preparedParameterArray
	 * 		预处理参数 Object[] e.g. new Object[]{"%张三%"}
	 * @return
	 */
	protected final <T> PageObject<T> queryForPaginationList(PageParam pp, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray) {
		return queryForPaginationList(pp, clazz, conditions,
				preparedParameterArray, null);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class 分页查询记录 实体必须使用符合JPA规范的注解进行配置
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param clazz
	 *            需要查询的实体类
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			int pageSize, Class<T> clazz) {
		return queryForPaginationList(currentPage, pageSize, clazz, null, null);
	}
	
	/**
	 * 根据实体类型分页查询 不带条件参数
	 * @param pp
	 * 		分页查询参数 PageParam
	 * @param clazz
	 * 		实体类Class
	 * @return
	 */
	protected final <T> PageObject<T> queryForPaginationList(PageParam pp, Class<T> clazz) {
		return queryForPaginationList(pp, clazz, null, null);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class 分页查询记录 实体必须使用符合JPA规范的注解进行配置 默认单页数据记录数为10
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param clazz
	 *            需要查询的实体类
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			Class<T> clazz) {
		return queryForPaginationList(currentPage, clazz, null, null);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录 实体必须使用符合JPA规范的注解进行配置 默认单页数据记录数为10
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			Class<T> clazz, LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray) {
		return queryForPaginationList(currentPage, clazz, conditions,
				preparedParameterArray, null);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置 默认单页数据记录数为10
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param preparedParameterArray
	 *            new Object[]{13}
	 * @param queryForListQHL
	 *            </br>HQL语句 <code>select o from User o where o.id >?
	 * </br>或者from User o where o.id >?</code>
	 * 
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			String queryForListHQL, Object[] preparedParameterArray) {
		int dataCount = queryCount(queryForListHQL, preparedParameterArray);
		PageObject<T> pageObject = new PageObject<T>(dataCount, currentPage);
		pageObject.setPageList(queryForList(queryForListHQL,
				preparedParameterArray, pageObject.getStartPoint(),
				pageObject.getEndPoint()));
		return pageObject;
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置 默认单页数据记录数为10
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            单页记录数
	 * @param queryForListQHL
	 *            </br>HQL语句 <code>select o from User o where o.id > 10
	 * </br>或者from User o where o.id >10</code>
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			String queryForListHQL) {
		return this.queryForPaginationList(currentPage, queryForListHQL, null);
	}

	/**
	 * 根据实体 当前页码，单页记录数 Class,条件，参数 分页查询记录，并且排序 实体必须使用符合JPA规范的注解进行配置 默认单页数据记录数为10
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param clazz
	 *            需要查询的实体类
	 * @param conditions
	 *            put("userName","like")
	 * @param parameterArray
	 *            new Object[]{"%zhang%"}
	 * @param orderBy
	 *            put("id","asc")
	 */
	protected final <T> PageObject<T> queryForPaginationList(int currentPage,
			Class<T> clazz, LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray,
			LinkedHashMap<String, String> orderBy) {
		String queryForListHQL = this
				.getQueryString(clazz, conditions, orderBy);
		return this.queryForPaginationList(currentPage, queryForListHQL,
				preparedParameterArray);
	}

	private final int queryCount(String queryForListHQL,
			Object[] preparedParameterArray) {

		StringBuilder countHQLBuilder = new StringBuilder(" select count(*) ");
		try {
			countHQLBuilder.append(queryForListHQL.substring(queryForListHQL
					.toLowerCase().indexOf("from")));
		} catch (RuntimeException ex) {
			throw new BadSqlGrammarException("SQL  :  ", queryForListHQL, null);
		}
		return queryForInt(countHQLBuilder.toString(), preparedParameterArray);
	}
}
