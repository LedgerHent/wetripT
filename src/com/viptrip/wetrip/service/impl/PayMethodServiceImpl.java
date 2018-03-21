package com.viptrip.wetrip.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.viptrip.wetrip.dao.TAcOrgDao;
import com.viptrip.wetrip.dao.TAcUserDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.OrgSettlement;
import com.viptrip.wetrip.entity.Settlement;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.base.Response_BusinessType;
import com.viptrip.wetrip.service.PayMethodService;
@Service
public class PayMethodServiceImpl implements PayMethodService {

	@Resource
	private TAcUserDao tud;	//用户
	
	@Resource
	private TAcOrgDao tod;	//企业
	
	@Resource
	private ComDao comdao;	//
	
	
	/**
	 * 根据userID查询指定用户
	 * */
	public TAcUser findUser(Long userID) {
		
		// 根据id查询用户对象
		TAcUser user = tud.findOne(userID);
		// 根据企业id查询企业对象
//		TAcOrg org = tod.findOne(user.getOrgid());
		
		return user;
	}

	/**
	 * 根据用户的企业ID查询所在公司
	 * */
	@Override
	public  List<Response_BusinessType> getPayMethodList(Long orgId,String travelType) {
		TAcOrg tacorg= comdao.queryForEntity(orgId, TAcOrg.class);
		TAcOrg org= comdao.queryForEntity(Long.valueOf(tacorg.getCompany()), TAcOrg.class);
		//根据企业id和因公因私查询该企业所支持的结算方式
		String hql = " from OrgSettlement where orgid= " + org.getOrgid() ;
		if("1".equals(travelType)){
			hql += " and ruletype in ('0','1') ";
		}
		if("2".equals(travelType)){
			hql += " and ruletype in ('0','2') ";
		}
		List<OrgSettlement> osList = comdao.queryForList(hql);
		String sids="";
		for (OrgSettlement orgSettlement : osList) {
			sids+=orgSettlement.getId().getSid()  +",";
		}
		sids=sids.substring(0, sids.length()-1);
		//查询该用户的身份信息
		
		List<Response_BusinessType> btList = new ArrayList<Response_BusinessType>();
		//判断是否为企业员工，非企业员工只能选择个人支付
		if(!"".equals(org) && org != null){
			String hql1 = " from Settlement where state=1 and sid in("+sids+") order by orderby ";
			System.out.println(hql1);
			List<Settlement> allSett = comdao.queryForList(hql1);
				
			for(int i = 0;i < allSett.size();i++){
				Response_BusinessType bt = new Response_BusinessType();
				bt.setId(Integer.parseInt(allSett.get(i).getSid().toString()));
				bt.setName(allSett.get(i).getName());
				bt.setPrice(0);
				bt.setBusinessType(Integer.parseInt(travelType));
				btList.add(bt);
			}
			return btList;
		}else{
			Response_BusinessType bt = new Response_BusinessType();
			bt.setId(2);
			bt.setName("个人支付");
			bt.setPrice(0);
			bt.setBusinessType(Integer.parseInt(travelType));
			btList.add(bt);
			return btList;
		}
	}
}
