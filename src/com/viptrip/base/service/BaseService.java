package com.viptrip.base.service;

import javax.annotation.Resource;

import com.viptrip.base.dao.BaseDaoForJPA;

public class BaseService {
	@Resource
	public BaseDaoForJPA dao;
	
}
