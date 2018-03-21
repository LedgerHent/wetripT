package com.viptrip.wetrip.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.wetrip.dao.ext.ComDao;

@Service
@Transactional
public class H5LoginService {
	@Resource
	private ComDao cDao;
	/**
	 * 是否是正确的ID和密码
	 * @param pid
	 * @param pwd
	 * @return 
	 */
	public boolean checkPlatf(Long pid, String authCode) {
		String hql="select pid from Platform where pid=? and authCode=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(pid);
		parm.add(authCode);
		boolean resultInt =true;
		try {
			cDao.queryForInt(hql, parm.toArray());
			
		} catch (NoResultException e) {
			resultInt = false;
		}catch (NonUniqueResultException e1) {
			resultInt = false;//有多条记录
		}
		
		return resultInt;
	}

}
