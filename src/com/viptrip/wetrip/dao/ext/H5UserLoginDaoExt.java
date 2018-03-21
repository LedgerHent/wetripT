package com.viptrip.wetrip.dao.ext;

import java.util.List;


import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import com.viptrip.base.dao.BaseDaoForJPA;
import com.viptrip.wetrip.entity.PlatformUserMap;

@Component
public class H5UserLoginDaoExt extends BaseDaoForJPA {
	/**
	 * 根据主键ID查询实体,实体必须使用符合JPA规范的注解进行配置
	 * @param id
	 * @return
	 */
	public <T> T queryEntityById(Long id, Class<T> clazz){
		
		return queryForEntity(id, clazz);
	}
	
	
	/**
	 * 根据传入参数集合List，查找符合条件记录;使用HQL 进行实体的查询 实体必须使用符合JPA规范的注解进行配置
	 * @param obj
	 * @return
	 */
	public List<Object> queryForList(String hql,List<Object> parm){
		
		List<Object> qResult = queryForList(hql, parm.toArray());

		return qResult;
	}
	
	
	/**
	 * 根据传入参数集合List，查找符合条件记录数
	 * @param obj
	 * @return
	 */
	public Integer queryForInt(String hql,List<Object> parm){
		Integer qResult = null;
		try {
			qResult = queryForInt(hql, parm.toArray());
			
		} catch (NoResultException e) {
			qResult = null;
		}catch (NonUniqueResultException e1) {
			qResult = -1;//有多条记录
		}
		return qResult;
	}

	/**
	 * 保存
	 * @param entity
	 */
	public void executeSave(PlatformUserMap entity){
		executeSave(entity);
	}
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(Object entity){
		executeUpdate(entity);
	}


}
