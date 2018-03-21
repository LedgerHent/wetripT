package com.viptrip.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.service.GetDepartmentsService;
import com.viptrip.wetrip.dao.ext.GetEnterpriseFrameworkDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.model.employees.CompanyInfo;
@Service
public class GetDepartmentsServiceImpl implements GetDepartmentsService {

	@Resource
	private GetEnterpriseFrameworkDaoExt dao;
	
	
	@Override
	public CompanyInfo getAllOrg(int orgid, CompanyInfo topTacOrg,
			List<CompanyInfo> childs) {
				//获取最上面层的企业
				if(topTacOrg==null){
					
					TAcOrg tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
				/*	Long companyId=Long.parseLong(tOrg.getCompany());
					
					tOrg=(TAcOrg)dao.queryForEntity(companyId,TAcOrg.class);*/
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
					if(orgChild!=null){
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
		
				String	hql="select t.orgid,t.orgname from T_AC_ORG t where t.parentid = '"+id+"' and status ='1' ";
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
