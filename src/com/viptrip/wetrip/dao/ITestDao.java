package com.viptrip.wetrip.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.viptrip.wetrip.entity.TAcUser;

/**
 * 基础dao接口 直接继承PagingAndSortingRepository<Entity类,主键类型> 无需添加实现类
 * 如果该接口方法满足不了业务需求 则在ext包下添加对应的扩展dao
 * @author selfwhisper
 *
 */
public interface ITestDao extends PagingAndSortingRepository<TAcUser, Long>{
	
}
