package com.viptrip.register.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.register.entity.EnterpriseMailSuffix;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcUser;

@Service
public class EmailSuffixService {
	
	@Resource
	private ComDao dao;
	
	public EnterpriseMailSuffix queryForMail(String emailsuffix ) {
		EnterpriseMailSuffix mail = dao.queryForEntity(emailsuffix, EnterpriseMailSuffix.class);
		return mail;
		
	} 
	
	public TAcUser isExistEmail(String email){
		String  hql="from TAcUser t where t.email = '"+email +"' ";
		List<TAcUser> tacuser=dao.queryForList(hql);
		TAcUser user =new TAcUser();
		if(tacuser.size()>0){
			user=tacuser.get(0);
			return user;
		}
		return null;
	}

}
