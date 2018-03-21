package com.viptrip.wetrip.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.wetrip.dao.ext.GetEnterpriseFrameworkDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import com.viptrip.wetrip.service.GetEnterpriseFrameworkService;

@Service
public class GetEnterpriseFrameworkServiceImpl implements GetEnterpriseFrameworkService{
	
	@Resource
	private GetEnterpriseFrameworkDaoExt dao;
	
//	private TAcOrg topTacOrg;
	
	
	
	@Override
	public CompanyInfo getAllOrg(int id,CompanyInfo topTacOrg,List<CompanyInfo> childs) {
		//获取最上面层的企业
		if(topTacOrg==null){
			String hql1="select orgid from TAcUser t where t.userid = "+id+"";
			long orgid=dao.queryForInt(hql1);
			TAcOrg tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
			Long companyId=Long.parseLong(tOrg.getCompany());
			/*if(tOrg!=null){
				String treeLayer=tOrg.getTreelayer();//获取机构树
				String[] spilt=treeLayer.split("\\.");
				if(spilt.length>2){
					companyId=Long.parseLong(spilt[3]);
				}
			}*/
			tOrg=(TAcOrg)dao.queryForEntity(companyId,TAcOrg.class);
			topTacOrg=new CompanyInfo();
			topTacOrg.setId(Integer.valueOf(tOrg.getOrgid().intValue()));
			topTacOrg.setName(tOrg.getOrgname());
			topTacOrg.setIsbottom(tOrg.getIsbottom());
		}
		//获取该企业下的所有部门以及部门下的子部门直到最后
		childs=this.getOrgChildById(topTacOrg.getId());
		topTacOrg.setChild(childs);
		for (int i = 0; i <childs.size(); i++) {
			CompanyInfo orgChild=childs.get(i);
			if(orgChild!=null && orgChild.getIsbottom().equals("1")){
				List<CompanyInfo> childs_=null;
				getAllOrg(orgChild.getId(),orgChild,childs_);
			}
		}
		return topTacOrg;
	}
	
	@Override
	public List<CompanyInfo> getOrgChildById(int id) {
		//通过parentid获取企业下的部门集合
		List<CompanyInfo> tacOrgList=new ArrayList<CompanyInfo>();
	/*	String hql1="select orgid from TAcUser t where t.userid = "+id+"";
		long orgid=dao.queryForInt(hql1);*/
		String	hql="select t.orgid,t.orgname,t.isbottom from T_AC_ORG t where t.parentid = '"+id+"'";
		List<Object[]>  orgObj=(List<Object[]>)dao.queryBySQL(hql,null);
		for (int i = 0; i < orgObj.size(); i++) {
			Object[] orgobj=orgObj.get(i);
			CompanyInfo org=new CompanyInfo();
			org.setId(Integer.valueOf(String.valueOf(orgobj[0])));
			org.setName(String.valueOf(orgobj[1]));
			org.setIsbottom(String.valueOf(orgobj[2]));
			tacOrgList.add(org);
		}
		return tacOrgList;
	}
	
	/*@Override
	public List<TAcOrg> getOrgChildById(Long  orgid) {
		// TODO 通过id获取企业下的部门
		String hql="from TAcOrg t where t.parentid = '"+orgid+"'";
		return dao.queryForList(hql);
	}*/
	/*@Override
	public TAcOrg getOrg(Long orgid) {
		TAcOrg org =  (TAcOrg)dao.queryForEntity( orgid,TAcOrg.class);
		if(!"2".equals(org.getOrgtype())){
			org=getOrg(org.getParentid());
		}
		return org;
	}

	@Override
	public List<TAcOrg> getOrgChild(Long  company) {
		// TODO 通过company获取企业下的部门集合
		String hql="from TAcOrg t where t.company = '"+company+"'";
		return dao.queryForList(hql);
	}*/
	
	
	
}
