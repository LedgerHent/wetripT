package com.viptrip.wetrip.dao.ext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.viptrip.base.dao.BaseDaoForJPA;
@Component
public class TestDaoExt2{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public void list(){
		
	}

}
