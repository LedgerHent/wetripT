package com.viptrip.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.entity.CompanyInfo;
import com.viptrip.hotel.service.GetDepartmentsByIdsService;
import com.viptrip.wetrip.dao.ext.GetEnterpriseFrameworkDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;

@Service
public class GetDepartmentsByIdsServiceImpl implements  GetDepartmentsByIdsService{
	@Resource
	private GetEnterpriseFrameworkDaoExt dao;
	@Override
	public CompanyInfo getAllOrg(int orgid, String  ids, CompanyInfo topTacOrg,
			List<CompanyInfo> childs) {
		topTacOrg=new CompanyInfo();
		//获取最上面层的企业
		/*if(topTacOrg==null){
			
			TAcOrg tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
	
			topTacOrg=new CompanyInfo();
			topTacOrg.setId(Integer.valueOf(tOrg.getOrgid().intValue()));
			topTacOrg.setName(tOrg.getOrgname());
			
		}*/
		
		//TAcOrg tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
		
		//获取该企业下的指定id序列部门
		/*childs=this.getOrgChildById(topTacOrg.getId(),ids);*/
		childs=this.getOrgChildById(orgid,ids);
		topTacOrg.setList(childs);
		/*for (int i = 0; i <childs.size(); i++) {
			CompanyInfo orgChild=childs.get(i);
			if(orgChild!=null ){
				List<CompanyInfo> childs_=null;
				getAllOrg(orgChild.getId(),"",orgChild,childs_);
			}
		}*/
		return topTacOrg;
	}

	@Override
	public List<CompanyInfo> getOrgChildById(int id, String ids) {
		//通过parentid获取企业下的部门集合
		List<CompanyInfo> tacOrgList=new ArrayList<CompanyInfo>();
	
		TAcOrg tcompany = dao.queryForEntity(Long.valueOf(id), TAcOrg.class);
		
		//String	hql="select t.orgid,t.orgname from T_AC_ORG t where t.parentid = '"+id+"'  and status ='1'";
		String	hql="select t.orgid,t.orgname from T_AC_ORG t where t.company = "+tcompany.getCompany()+"  and status ='1'";
		
		if(ids!=null&&!"".equals(ids)){
			hql=hql+" and orgid in (" + ids + ")";
		}
		
		List<Object[]>  orgObj=(List<Object[]>)dao.queryBySQL(hql,null);
		for (int i = 0; i < orgObj.size(); i++) {
			Object[] orgobj=orgObj.get(i);
			CompanyInfo org=new CompanyInfo();
			org.setId(Integer.valueOf(String.valueOf(orgobj[0])));
			org.setName(String.valueOf(orgobj[1]));
			
			tacOrgList.add(org);
		}
		return tacOrgList;
	}

}
