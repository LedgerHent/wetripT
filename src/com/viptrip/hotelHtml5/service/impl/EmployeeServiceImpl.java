package com.viptrip.hotelHtml5.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotelHtml5.service.EmployeeService;
import com.viptrip.hotelHtml5.vo.EmpAndOrg;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.dao.ext.ListStaffDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.policy.TTmcEnterpAreaLv;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Resource
	private ComDao dao;

	/**
	 * 模糊查询员工
	 *//*
	@Override
	public List<TAcUser> queryUser(String username, Long orgid) {
		String hql="from TAcUser t where t.username like '%"+username+"%' and t.orgid="+orgid;
		List<TAcUser> list=dao.queryForList(hql);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	*//**
	 * 根据ID查找部门信息
	 *//*
	@Override
	public TAcOrg queryOrg(Long orgid) {
		// TODO Auto-generated method stub
		TAcOrg org =  (TAcOrg)dao.queryForEntity( orgid,TAcOrg.class);
		if(org!=null){
			return org;
		}
		return null;
	}*/

	@Override
	public List<EmpAndOrg> queryData(String username, TAcUser user) {
		List<EmpAndOrg> eolist = new ArrayList<EmpAndOrg>();
		EmpAndOrg eao;
		TAcOrg org=new TAcOrg();
		org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
		Long orgid=Long.valueOf(org.getCompany());
		//String sql = "select a.username,a.orgn,oo.orgname,a.id from(select u.userid id,u.username username,o.orgid orgid,o.orgname orgn,o.company company from t_ac_org o,t_ac_user u where u.orgid=o.orgid and o.orgid="+orgid+" and u.username like '%"+username+"%')a,t_ac_org oo where a.company=oo.orgid";
		String sql = "select a.username,a.orgn,oo.orgname,a.id from(select u.userid id,u.username username,o.orgid orgid,o.orgname orgn,o.company company from t_ac_org o,t_ac_user u where u.orgid=o.orgid and o.company="+orgid+" and u.username like '%"+username+"%')a,t_ac_org oo where a.company=oo.orgid";
		List<Object[]> list = (List<Object[]>) dao.queryBySQL(sql, null);
		for (Object[] eo : list) {
			eao = new EmpAndOrg();
			eao.setUsername(eo[0] == null ? "" : eo[0].toString());
			eao.setOrgname(eo[1] == null ? "" : eo[1].toString());
			eao.setCompanyname(eo[2] == null ? "" : eo[2].toString());
			eao.setUserid(eo[3] == null ? "" : eo[3].toString());
			eolist.add(eao);
		}
		return eolist;
	}
	
	

}
