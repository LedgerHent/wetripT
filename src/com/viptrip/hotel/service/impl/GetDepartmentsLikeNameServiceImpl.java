package com.viptrip.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.model.Request_GetDepartmentsLikeName;
import com.viptrip.hotel.model.getDepartmentsLikeName.CompanyInfoGDLN;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.service.GetDepartmentsLikeNameService;
import com.viptrip.wetrip.dao.ext.GetEnterpriseFrameworkDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;


@Service
public class GetDepartmentsLikeNameServiceImpl implements GetDepartmentsLikeNameService{
	@Resource
	private GetEnterpriseFrameworkDaoExt dao;
	
	
	@Override
	public CompanyInfoGDLN getAllOrg(int orgid,Request_GetDepartmentsLikeName arg0, CompanyInfoGDLN topTacOrg,
			List<CompanyInfoGDLN> childs) {
		topTacOrg=new CompanyInfoGDLN();
				//获取最上面层的企业
				/*if(topTacOrg==null){
					
					TAcOrg tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
				
					topTacOrg=new CompanyInfoGDLN();
					topTacOrg.setId(Integer.valueOf(tOrg.getOrgid().intValue()));
					topTacOrg.setName(tOrg.getOrgname());
				
				}*/
				//获取该企业下的所有部门
				TAcOrg tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
				
				/*CompanyInfoGDLN orgChildById= getOrgChildById(topTacOrg.getId(),arg0);*/
				CompanyInfoGDLN orgChildById= getOrgChildById(Integer.valueOf(tOrg.getOrgid().intValue()),arg0);
				
				
				childs=orgChildById.list;
				
				topTacOrg.setList(childs);
				if(orgChildById.page!=null){
				topTacOrg.page=orgChildById.page;
				}
				
				
			
				
				/*for (int i = 0; i <childs.size(); i++) {
					CompanyInfoGDLN orgChild=childs.get(i);
					if(orgChild!=null){
						List<CompanyInfoGDLN> childs_=null;
						
						getAllOrg(orgChild.getId(),new Request_GetDepartmentsLikeName(),orgChild,childs_);
					}
				}*/
				return topTacOrg;
	}

	
	
	public CompanyInfoGDLN getOrgChildById(int id,Request_GetDepartmentsLikeName arg0) {
				//通过parentid获取企业下指定的部门集合
				List<CompanyInfoGDLN> tacOrgList=new ArrayList<CompanyInfoGDLN>();
				CompanyInfoGDLN companyInfoGDLN = new CompanyInfoGDLN();
				String	sql="select t.orgid,t.orgname from T_AC_ORG t where t.company = '"+id+"'  and status ='1'";
				
				if(arg0.nameKey!=null&&!"".equals(arg0.nameKey)){
					sql=sql+" and t.orgname like '%"+ arg0.nameKey +"%'";
					
				}
				List<Object[]> orgObj =null;
				Page newPage=null;
				if(arg0.page!=null){
					orgObj = (List<Object[]>)getPageist(sql,arg0.page);
					 newPage = newPage(sql,arg0.page);
				}else{
					orgObj = (List<Object[]>) dao.queryBySQL(sql, null);
				}				
				
				
				for (int i = 0; i < orgObj.size(); i++) {
					Object[] orgobj=orgObj.get(i);
					CompanyInfoGDLN org=new CompanyInfoGDLN();
					org.setId(Integer.valueOf(String.valueOf(orgobj[0])));
					org.setName(String.valueOf(orgobj[1]));
					
					tacOrgList.add(org);
				}
				
			
				companyInfoGDLN.list=tacOrgList;
				companyInfoGDLN.page=newPage;
				return companyInfoGDLN;
	}
	
	private  List getPageist(String sql, Page page) {
		String pageSql = "select * from (SELECT t.*, ROWNUM AS rowno FROM"
				+ " (" + sql + ") t where rownum <= " + page.index * page.size
				+ ") t1 " + " where t1.rowno >= " + (page.index - 1)
				* page.size + " ";
		List list = dao.queryBySQL(pageSql, null);
		System.out.println(list);
		return list;
	}
	
	private Page newPage(String sql, Page page) {
		
		List list = dao.queryBySQL("select count(*) from (" + sql + ")", null);
		Integer count = Integer.parseInt(list.get(0).toString());
		page.count = count;
		return page;
	}



}
