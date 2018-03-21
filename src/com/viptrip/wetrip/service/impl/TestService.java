package com.viptrip.wetrip.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.dao.ITestDao;
import com.viptrip.wetrip.dao.ext.TestDaoExt;
import com.viptrip.wetrip.entity.TAcUser;
@Service
public class TestService {
	@Resource
	private TestDaoExt dao;//扩展testdao
	
	@Resource
	private ITestDao idao;//基础testdao
	
	public PageObject<TAcUser> list(){
		PageParam pp = new PageParam(2,20);
		String hql = "from TAcUser";
		PageObject<TAcUser> list = dao.list(hql, null, pp);
		return list;
	}
	
	public TAcUser get(Long id){
		TAcUser user = idao.findOne(id);
		return user;
	}
}
