package com.viptrip.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.common.entity.TTmcAreaInfo;
import com.viptrip.common.entity.TTmcEnterpAreaLv;
import com.viptrip.common.entity.TTmcTripRule;
import com.viptrip.common.model.CityLevelModel;
import com.viptrip.common.model.Request_GetCityGrade;
import com.viptrip.common.model.Request_GetCustomerCityGrade;
import com.viptrip.common.model.Request_GetRuleById;
import com.viptrip.common.model.Request_GetRulesByEnterprise;
import com.viptrip.common.model.Request_GetRulesByTravller;
import com.viptrip.hotel.model.Request_GetBookRight;
import com.viptrip.hotel.model.Request_GetBookStaffList;
import com.viptrip.hotel.model.Request_GetOrgGroups;
import com.viptrip.hotel.model.Request_GetOrgGroupsByIds;
import com.viptrip.hotel.model.Request_GetOrgGroupsLikeName;
import com.viptrip.hotel.model.Request_GetOrgList;
import com.viptrip.hotel.model.Request_GetPayQuota;
import com.viptrip.hotel.model.Request_HotelServiceFee;
import com.viptrip.hotel.model.Request_ListPassenger;
import com.viptrip.hotel.model.Request_ListPassengerLikeName;
import com.viptrip.hotel.model.Request_ListStaffLike;
import com.viptrip.hotel.model.Request_PayByAdvance;
import com.viptrip.hotel.model.Response_GetHotelServiceFee;
import com.viptrip.hotel.model.Response_HotelServiceFee;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.Response_ListPassengerLikeName;
import com.viptrip.hotel.model.fee.FeeService;
import com.viptrip.hotel.model.fee.Protocol;
import com.viptrip.hotel.model.key.Key;
import com.viptrip.hotel.model.org.OrgInfoPage;
import com.viptrip.hotel.model.orggroups.OrgGroup;
import com.viptrip.hotel.model.orggroups.OrgGroupData;
import com.viptrip.hotel.model.orggroups.OrgGroupDataIds;
import com.viptrip.hotel.model.orglikename.OrgLikeName;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.passengerData.PassengerData;
import com.viptrip.hotel.model.staff.Ids;
import com.viptrip.hotel.model.staff.Org;
import com.viptrip.hotel.model.staff.StaffGroup;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.IComService;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.AuditOrderDao;
import com.viptrip.wetrip.dao.TAcUserDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.OrgPolicyManage;
import com.viptrip.wetrip.entity.OrgPolicyTicket;
import com.viptrip.wetrip.entity.PolicyIntlTicket;
import com.viptrip.wetrip.entity.PolicyIntlTicketRule;
import com.viptrip.wetrip.entity.PolicyTicketRule;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcOrgBalance;
import com.viptrip.wetrip.entity.TAcRole;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TAcUserrole;
import com.viptrip.wetrip.entity.TTmcApprovePolicy;
import com.viptrip.wetrip.entity.TTmcApprovePolicyFlow;
import com.viptrip.wetrip.entity.TTmcApprovePolicyLable;
import com.viptrip.wetrip.entity.TTmcPolicy;
import com.viptrip.wetrip.entity.TTmcPolicyBusiness;
import com.viptrip.wetrip.entity.TTmcPolicyBusinessId;
import com.viptrip.wetrip.entity.TTmcPolicyDetail;
import com.viptrip.wetrip.entity.TTmcPolicyDetailId;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_GetApprovalDetail;
import com.viptrip.wetrip.model.Request_ListApprovalByTravller;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.model.policy.ApprovalsModel;
import com.viptrip.wetrip.model.policy.ApprovalsPerson;
import com.viptrip.wetrip.model.policy.ApproveTravller;
import com.viptrip.wetrip.model.policy.DomTicketModel;
import com.viptrip.wetrip.model.policy.GetRulesByTravllerModel;
import com.viptrip.wetrip.model.policy.HATPolicyModel;
import com.viptrip.wetrip.model.policy.HATRules;
import com.viptrip.wetrip.model.policy.IntlTicketModel;
import com.viptrip.wetrip.model.policy.PolicyByenterprise;
import com.viptrip.wetrip.model.policy.RelationRules;
import com.viptrip.wetrip.model.policy.Relations;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;
import com.viptrip.wetrip.model.policy.RuleMaps;
import com.viptrip.wetrip.model.policy.TicketItems;
import com.viptrip.wetrip.model.policy.TicketRules;
import com.viptrip.wetrip.model.policy.Travller;


@Service
public class ComServiceImpl implements IComService {
	@Resource
	private ComDao dao;
	@Resource
	private TAcUserDao userDao;
	
	public static final Integer defaultNum = -1;
	
	@SuppressWarnings("unchecked")
	@Override
	public OrgInfoPage getOrgNameList(Integer userId,String key,
			String type,Page page) {
		OrgInfoPage vo = new OrgInfoPage();
		
		List<OrgLikeName> olist = null;
		olist = new ArrayList<OrgLikeName>();
		OrgLikeName oln = null;
		String sql = "select  orgid,orgname,linkman,phone,projectmust,verify,HOTEL_AUTO_SEND_MSG,parentid,HOTEL_AUTO_SEND_EMAIL from" +
				" t_ac_org where 1=1 and orgtype='2' and status ='1'";
		if ("1".equals(type)) {
			if (key != null && !"".equals(key)) {
				sql += "and orgname like '%" + key + "%'";
			}
		} else if ("2".equals(type)) {
			if (key != null && !"".equals(key)) {
				sql += "and LINKMAN like '%" + key + "%'";
			}
		} else if ("3".equals(type)) {
			if (key != null && !"".equals(key)) {
				sql += "and orgid in (" + key + ")";
			}
		}else if("4".equals(type)){
			String[] skey= key.split("_");
			if(skey.length > 1){
				if (!"1".equals(skey[0])) {
					sql += "and orgname like '%" + skey[0] + "%' ";
				}
				if (!"2".equals(skey[1])) {
					sql += "and LINKMAN like '%" + skey[1] + "%'";
				}
			}else{
				if (!"1".equals(skey[0])) {
					sql += "and orgname like '%" + skey[0] + "%' ";
				}
			}
		}else if("7".equals(type)){
			sql += " and clientname ="+key;
		}else if("6".equals(type)){
			sql += " and sellname ="+key;
		}else if("5".equals(type)){							
			sql += " and orgid = " +key;
		}
			// 初始化分页参数
			vo.page = newPage(sql, page);

					// 分页查询
		List<Object[]> list = getPageist(sql,page);
		if (list != null && list.size() > 0) {
			olist = new ArrayList<OrgLikeName>();
			for (Object[] org : list) {
				oln = new OrgLikeName();
				oln.id = Integer.parseInt(org[0].toString());
				oln.name = org[1].toString();
				oln.contactName = org[2] == null ? "" : org[2].toString();
				oln.contactTel = org[3] == null ? "" : org[3].toString();
				oln.projectNo = Integer.parseInt(org[4] == null ? "0":
					org[4].toString().equals("0")?"1":org[4].toString().equals("1")?"2":"0");
				oln.verify =   Integer.parseInt(org[5] == null ? "1":org[5].toString());
				oln.sms =  Integer.parseInt(org[6] == null ? "2":org[6].toString());
				oln.email =  Integer.parseInt(org[8] == null ? "2":org[6].toString());
				if("10".equals(org[7].toString())){
					oln.localCity = "北京";
				}else{
					oln.localCity = "上海";
				}
				olist.add(oln);
			}
		}
		vo.list = olist;
		return vo;
	}
	public List<OrgLikeName> getOrgNameListIds(Integer userId,String key,
			String type) {
		List<OrgLikeName> olist = null;
		olist = new ArrayList<OrgLikeName>();
		OrgLikeName oln = null;
		String hql = "";
		hql += "from TAcOrg where 1=1 and orgtype='2' and status ='1'";
		if ("1".equals(type)) {
			if (key != null && !"".equals(key)) {
				hql += "and orgname like '%" + key + "%'";
			}
		} else if ("2".equals(type)) {
			if (key != null && !"".equals(key)) {
				hql += "and LINKMAN like '%" + key + "%'";
			}
		} else if ("3".equals(type)) {
			if (key != null && !"".equals(key)) {
				hql += "and orgid in (" + key + ")";
			}
		}else{
			String[] skey= key.split("_");
			if (!"1".equals(skey[0])) {
				hql += "and orgname like '%" + skey[0] + "%' ";
			}
			
			if (!"2".equals(skey[1])) {
				hql += "and LINKMAN like '%" + skey[1] + "%'";
			}
			
		}
		List<TAcOrg> list = dao.queryForList(hql);
		if (list != null && list.size() > 0) {
			olist = new ArrayList<OrgLikeName>();
			for (TAcOrg org : list) {
				oln = new OrgLikeName();
				oln.id = org.getOrgid().intValue();
				oln.name = org.getOrgname();
				oln.contactName = org.getLinkman() == null ? "" : org
						.getLinkman();
				oln.contactTel = org.getPhone() == null ? "" : org.getPhone();
				oln.projectNo = Integer
						.parseInt(org.getProjectmust() == null ? "0" : org
								.getProjectmust().equals("0")?"1":org
										.getProjectmust().equals("1")?"2":"0");
				oln.verify = Integer.parseInt(org.getVerify() == null ? "1"
						: org.getVerify());
				oln.sms = Integer
						.parseInt(org.getHotelAutoSendMsg() == null ? "2" : org
								.getHotelAutoSendMsg());
				oln.email = Integer
						.parseInt(org.getHotelAutoSendEmail() == null ? "2" : org
								.getHotelAutoSendEmail());
				if(org.getParentid()==10){
					oln.localCity = "北京";
				}else{
					oln.localCity = "上海";
				}
				olist.add(oln);
			}
		}
		return olist;
	}
	@SuppressWarnings("unchecked")
	@Override
	public OrgGroupData getOrgGroupList(Request_GetOrgGroups arg0) {
		List<OrgGroup> oglist = new ArrayList<OrgGroup>();
		OrgGroup ogVo = null;
//		Object[] param = new Object[] { orgId };
		String sql = "";
		sql += "select groupid,group_name,group_desc,orderby from org_group where 1=1";
		/*if (orgId != null) {
			sql += " and orgid = ?";
		}
		List<Object[]> list = (List<Object[]>) dao.queryBySQL(sql, param);*/
		if (arg0.orgId != null) {
			sql += " and orgid ="+arg0.orgId;
		}
		
		List<Object[]> list = (List<Object[]>)getPageist(sql, arg0.page);
		Page newPage = newPage(sql, arg0.page);
		
		
		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				ogVo = new OrgGroup();
				ogVo.id = Integer.parseInt(obj[0].toString());
				ogVo.name = obj[1] == null ? "" : obj[1].toString();
				ogVo.memo = obj[2] == null ? "" : obj[2].toString();
				ogVo.orderBy = Integer.parseInt(obj[3].toString());
				oglist.add(ogVo);
			}
		}
		OrgGroupData data = new OrgGroupData();
		
		data.list=oglist;
		data.page=newPage;
		return data;

	}
	/**
	 * 企业分组模糊查询
	 */
	public OrgGroupData getOrgGroupsLikeNameList(Request_GetOrgGroupsLikeName arg0){
		List<OrgGroup> oglist = new ArrayList<OrgGroup>();
		OrgGroup ogVo = null;
		//Object[] param = new Object[]{orgId};
		String sql = "";
		sql += "select groupid,group_name,group_desc,orderby from org_group where 1=1";
		/*if(arg0.orgId !=null){
			sql += " and orgid = ?";
		}*/
		if(arg0.orgId !=null){
			sql += " and orgid ="+arg0.orgId;
		}
		if(arg0.nameKey !=null){
			
			sql += " and group_name like '%"+arg0.nameKey+"%'";
		}
		//List<Object[]> list = (List<Object[]>) dao.queryBySQL(sql, param);
		List<Object[]> list = (List<Object[]>)getPageist(sql, arg0.page);
		Page newPage = newPage(sql, arg0.page);
		if(list!=null&&list.size()>0){ 
			for(Object[] obj : list){
				ogVo = new OrgGroup();
				ogVo.id = Integer.parseInt(obj[0].toString());
				ogVo.name = obj[1]==null?"":obj[1].toString();
				ogVo.memo = obj[2]==null?"":obj[2].toString();
				ogVo.orderBy = Integer.parseInt(obj[3].toString());
				oglist.add(ogVo);
			}
		}
		
		OrgGroupData data = new OrgGroupData();
		data.list=oglist;
		data.page=newPage;
		return data;
		
		
	}
	
	/**
	 * 企业分组查询-按id序列
	 */
	@Override
	public OrgGroupDataIds getOrgGroupsByIdsList(Request_GetOrgGroupsByIds arg) {
		List<OrgGroup> oglist = new ArrayList<OrgGroup>();
		OrgGroup ogVo = null;
		String sql = "";
		String idList="";
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < arg.ids.size(); i++) {
			sb.append(arg.ids.get(i)).append(",");
		}
		idList=sb.substring(0, sb.length()-1).toString();
		sql += "select groupid,group_name,group_desc,orderby from org_group where 1=1";
		if(arg.orgId !=null){
			sql += " and orgid = '"+arg.orgId+"'";
		}
		if(arg.ids !=null){
			sql += " and groupid in ("+ idList +")";
		}
		List<Object[]> list = (List<Object[]>) dao.queryBySQL(sql, null);
		if(list!=null&&list.size()>0){
			for(Object[] obj : list){
				ogVo = new OrgGroup();
				ogVo.id = Integer.parseInt(obj[0].toString());
				ogVo.name = obj[1]==null?"":obj[1].toString();
				ogVo.memo = obj[2]==null?"":obj[2].toString();
				ogVo.orderBy = Integer.parseInt(obj[3].toString());
				oglist.add(ogVo);
			}
		}
		OrgGroupDataIds data = new OrgGroupDataIds();
		data.list=oglist;
		return data;
		
		
	}
	public List<StaffGroup> getStaffInfo(List<Object[]> orgs) {
		List<StaffGroup> list = new ArrayList<StaffGroup>();
		for (Object[] obj : orgs) {
			StaffGroup sg = new StaffGroup();
			sg.id = Integer.parseInt(obj[0].toString());
			sg.name = obj[1].toString();
			sg.mobile = obj[2] == null ? "" : obj[2].toString();
			sg.email = obj[3] == null ? "" : obj[3].toString();
			sg.type = Integer
					.parseInt(obj[4] == null ? "0" : obj[4].toString());
			sg.sex = Integer.parseInt(obj[5] == null ? "0" : obj[5].toString());
			sg.birthday = obj[6] == null ? "" : obj[6].toString();
			sg.groupId = Integer.parseInt(obj[7].toString());
			sg.groupName = obj[8] == null ? "" : obj[8].toString();
			sg.departmentId = Integer.parseInt(obj[9].toString());
			sg.departmentName = obj[10] == null ? "" : obj[10].toString();
			sg.mileageCardNo = obj[11] == null ? "" : obj[11].toString();
			List<Ids> iList = new ArrayList<Ids>();
			Ids ids = null;
			// 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
			if (obj[12] != null) {
				ids = new Ids();
				ids.type = 1;
				ids.num = obj[12].toString();
				iList.add(ids);
			}
			if (obj[13] != null) {
				ids = new Ids();
				ids.type = 2;
				ids.num = obj[13].toString();
				iList.add(ids);
			}
			if (obj[14] != null) {
				ids = new Ids();
				ids.type = 3;
				ids.num = obj[14].toString();
				iList.add(ids);
			}
			if (obj[15] != null) {
				ids = new Ids();
				ids.type = 4;
				ids.num = obj[15].toString();
				iList.add(ids);
			}
			if (obj[16] != null) {
				ids = new Ids();
				ids.type = 5;
				ids.num = obj[16].toString();
				iList.add(ids);
			}
			if (obj[17] != null) {
				ids = new Ids();
				ids.type = 6;
				ids.num = obj[17].toString();
				iList.add(ids);
			}
			if (obj[18] != null) {
				ids = new Ids();
				ids.type = 7;
				ids.num = obj[18].toString();
				iList.add(ids);
			}
			if (obj[19] != null) {
				ids = new Ids();
				ids.type = 99;
				ids.num = obj[19].toString();
				iList.add(ids);
			}
			sg.ids = iList;
			list.add(sg);
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes" })
	private  List getPageist(String sql, Page page) {
		String pageSql = "select * from (SELECT t.*, ROWNUM AS rowno FROM"
				+ " (" + sql + ") t where rownum <= " + page.index * page.size
				+ ") t1 " + " where t1.rowno > " + (page.index - 1)
				* page.size + " ";
		List list = dao.queryBySQL(pageSql, null);
		System.out.println(list);
		return list;
	}

	@SuppressWarnings("rawtypes")

	private Page newPage(String sql, Page page) {
	
		List list = dao.queryBySQL("select count(*) from (" + sql + ")", null);
		Integer count = Integer.parseInt(list.get(0).toString());
		page.count = count;
		return page;
	}

	private String getStaffSql(String orgid, Object[] val) {
		String sql = "select "
				+ " t.userid,t.username,t.phone,t.email,t.usertype,t.sex,t.birthday,"
				+ // 0-6
				" nvl(grp.groupid,-1),nvl(grp.group_name,''),tao.orgid,tao.orgname,t.mileage"
				+ // 7-11
				" ,t.idcard,t.PASSPORT,''as hyz,t.hxz,t.jgz,t.gatxz,t.rtz,t.othernum"
				+ // 12-19
				" from t_ac_user t "
				+ " left join t_ac_org tao  on t.orgid = tao.orgid "
				+ " left join (select ogr.groupid,og.group_name,ogr.id from  org_group_relation ogr "
				+ " inner join org_group og on ogr.orgid=og.orgid and ogr.groupid = og. groupid ) grp"
				+ " on t.userid = grp.id or t.orgid = grp.id"
				+ " where tao.orgid in ("
				+ " select orgid from t_Ac_org o where o.orglayer like '%."
				+ orgid + ".%' or orgid = '" + orgid + "') ";
		if (val != null && "inGroup".equals(val[0].toString())) {
			sql += "and grp.groupid = '" + val[1].toString() + "' ";
		}
		if (val != null && "likeName".equals(val[0].toString())) {
			sql += " and t.username like '%" + val[1].toString() + "%'";
		}
		if (val != null && "likeTel".equals(val[0].toString())) {
			sql += " and t.phone  like '%" + val[1].toString() + "%'";
		}
		if (val != null && "byIds".equals(val[0].toString())) {
			sql += "and t.userid in (" + val[1].toString() + ")";
		}
		if (val != null && "byIdsAndName".equals(val[0].toString())) {
			sql += "and t.userid in (" + val[1].toString() + ")";
			sql += " and t.username like '%" + val[2].toString() + "%'";
		}
		
		sql += "order by t.userid ";
		return sql;
	}
	@Override
	public Integer BookStaffOrg(Request_GetBookStaffList arg) {
		String companyId = "";
		TAcUser user = null;
		user = userDao.findOne(Long.parseLong(arg.userId.toString()));
		if(user!=null){
			if(!"4".equals(user.getStatus())&&user.getOrgid() != -1){
				TAcOrg org=dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
				if(org!=null){
					 companyId = org.getTreelayer().split("\\.")[3].toString();
				}
			}
		}
		return Integer.parseInt(companyId);
	}
	public Integer BookStaffOrg(Integer userId) {
		String companyId = "";
		TAcUser user = null;
		user = userDao.findOne(Long.parseLong(userId.toString()));
		if(user!=null){
			if(!"4".equals(user.getStatus())&&user.getOrgid() != -1){
				TAcOrg org=dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
				if(org!=null){
					 companyId = org.getTreelayer().split("\\.")[3].toString();
				}
			}
		}
		return Integer.parseInt(companyId);
	}

	private String getListStaffLikeName(String orgid, Key key) {
		String sql = "select "
				+ " t.userid,t.username,t.phone,t.email,t.usertype,t.sex,t.birthday,"
				+ // 0-6
				" nvl(grp.groupid,-1),nvl(grp.group_name,''),tao.orgid,tao.orgname,t.mileage"
				+ // 7-11
				" ,t.idcard,t.PASSPORT,''as hyz,t.hxz,t.jgz,t.gatxz,t.rtz,t.othernum"
				+ // 12-19
				" from t_ac_user t "
				+ " left join t_ac_org tao  on t.orgid = tao.orgid "
				+ " left join (select ogr.groupid,og.group_name,ogr.id from  org_group_relation ogr "
				+ " inner join org_group og on ogr.orgid=og.orgid and ogr.groupid = og. groupid ) grp"
				+ " on t.userid = grp.id or t.orgid = grp.id"
				+ " where tao.orgid in ("
				+ " select orgid from t_Ac_org o where o.orglayer like '%."
				+ orgid + ".%' or orgid = '" + orgid + "') ";

		if (key.key1 != null && !"".equals(key.key1)) {
			sql += " and t.username like '%" + key.key1 + "%'";
		}
		if (key.key2 != null && !"".equals(key.key2)) {
			sql += " and (t.phone like '%" + key.key2 + "%' or t.cellphone like '%" + key.key2 + "%')";
		}
		sql += "order by t.userid ";
		return sql;
	}
	
	//常旅客信息列表
	public Response_ListPassenger ListPassenger(Request_ListPassenger arg0){
		List listobj = null;
		String sql="select ID,NAME,MOBILEPHONE,EMAIL,PASSENGER_TYPE,IDTYPE,IDNUMBER  from t_travel_passenger where userid= "+arg0.userId;
		
		Response_ListPassenger passenger = new Response_ListPassenger();
		listobj = getPageist(sql, arg0.page);
		List<TTravelPassenger> tTravelP = getPassengerInfo(listobj);
		Page newPage = newPage(sql, arg0.page);
		List<Req_Passenger> list1 = new ArrayList<Req_Passenger>();
		for (int i = 0; i < tTravelP.size(); i++) {
			List<ReqData_CertificateMessage> list2 = new ArrayList<ReqData_CertificateMessage>();
			TTravelPassenger passengers = tTravelP.get(i);
			ReqData_CertificateMessage reqMessage = new ReqData_CertificateMessage();
			Req_Passenger personMessage = new Req_Passenger();
			int intValue = passengers.getId().intValue();
			personMessage.setId(intValue);
			personMessage.setName(passengers.getName());
			personMessage.setMobile(passengers.getMobilephone());
			personMessage.setEmail(passengers.getEmail());
			
			String passengerType = passengers.getPassengerType();
			if (StringUtil.isEmpty(passengerType)
					) {
			} else {
				personMessage.setType(Integer.valueOf(passengerType));
			}
			String idtype = passengers.getIdtype();
			if (StringUtil.isEmpty(idtype)) {
			} else {
				reqMessage.setType(Integer.valueOf(idtype));
			}
			reqMessage.setNum(passengers.getIdnumber());
			list2.add(reqMessage);
			personMessage.setIds(list2);
			list1.add(personMessage);
		}
		
		passenger.data = new PassengerData();
		passenger.data.setList(list1);
		passenger.data.setPage(newPage);
		return passenger;		
	}
	//常旅客模糊查询
	public Response_ListPassengerLikeName ListPassengerLikeName(Request_ListPassengerLikeName arg0){
		List listobj = null;
		String sql="select ID,NAME,MOBILEPHONE,EMAIL,PASSENGER_TYPE,IDTYPE,IDNUMBER  from t_travel_passenger where userid= "+arg0.userId;
		if(arg0.nameKey!=null&&!"".equals(arg0.nameKey)){
			
			sql+=" and NAME like '%"+arg0.nameKey+"%'";
		}
		
		Response_ListPassengerLikeName passenger = new Response_ListPassengerLikeName();
		listobj = getPageist(sql, arg0.page);
		List<TTravelPassenger> tTravelP = getPassengerInfo(listobj);
		Page newPage = newPage(sql, arg0.page);
		List<Req_Passenger> list1 = new ArrayList<Req_Passenger>();
		for (int i = 0; i < tTravelP.size(); i++) {
			List<ReqData_CertificateMessage> list2 = new ArrayList<ReqData_CertificateMessage>();
			TTravelPassenger passengers = tTravelP.get(i);
			ReqData_CertificateMessage reqMessage = new ReqData_CertificateMessage();
			Req_Passenger personMessage = new Req_Passenger();
			int intValue = passengers.getId().intValue();
			personMessage.setId(intValue);
			personMessage.setName(passengers.getName());
			personMessage.setMobile(passengers.getMobilephone());
			personMessage.setEmail(passengers.getEmail());
			personMessage.setSex(null);
			personMessage.setBirthday(null);
			String passengerType = passengers.getPassengerType();
			if (StringUtil.isEmpty(passengerType)
					|| "null".equals(passengerType)) {
				personMessage.setType(null);
			} else {
				personMessage.setType(Integer.valueOf(passengerType));
			}
			String idtype = passengers.getIdtype();
			if (StringUtil.isEmpty(idtype)) {
				reqMessage.setType(null);
			} else {
				reqMessage.setType(Integer.valueOf(idtype));
			}
			reqMessage.setNum(passengers.getIdnumber());
			list2.add(reqMessage);
			personMessage.setIds(list2);
			list1.add(personMessage);
		}
		
		passenger.data = new PassengerData();
		passenger.data.setList(list1);
		passenger.data.setPage(newPage);
		return passenger;		
	}
	
	
	public List<TTravelPassenger> getPassengerInfo(List<Object[]> orgs) {
		List<TTravelPassenger> list = new  ArrayList<TTravelPassenger>();
		for(Object[] obj : orgs){
			TTravelPassenger tp = new TTravelPassenger();
			tp.setId(Long.parseLong(obj[0].toString()));
			tp.setName(obj[1].toString());
			tp.setMobilephone(obj[2]==null?"":obj[2].toString());
			tp.setEmail(obj[3]==null?"":obj[3].toString());
			tp.setPassengerType(obj[4]==null?"0":obj[4].toString());
			tp.setIdtype(obj[5]==null?"0":obj[5].toString());
			tp.setIdnumber(obj[6]==null?"":obj[6].toString());		
			list.add(tp);
		}
		return list;
	}
	
	
	
	private String getListStaffByIdsSql(String orgid, String ids) {
		String sql = "select "
				+ " t.userid,t.username,t.phone,t.email,t.usertype,t.sex,t.birthday,"
				+ // 0-6
				" nvl(grp.groupid,-1),nvl(grp.group_name,''),tao.orgid,tao.orgname,t.mileage"
				+ // 7-11
				" ,t.idcard,t.PASSPORT,''as hyz,t.hxz,t.jgz,t.gatxz,t.rtz,t.othernum"
				+ // 12-19
				" from t_ac_user t "
				+ " left join t_ac_org tao  on t.orgid = tao.orgid "
				+ " left join (select ogr.groupid,og.group_name,ogr.id from  org_group_relation ogr "
				+ " inner join org_group og on ogr.orgid=og.orgid and ogr.groupid = og. groupid ) grp"
				+ " on t.userid = grp.id or t.orgid = grp.id"
				+ " where tao.orgid in ("
				+ " select orgid from t_Ac_org o where o.orglayer like '%."
				+ orgid + ".%' or orgid = '" + orgid + "') ";

		if (ids != null && !"".equals(ids)) {
			sql += "and t.userid in (" + ids + ")";
		}
		sql += "order by t.userid ";
		return sql;
	}



	@Override
	public StaffList getListStaffLike(
			Request_ListStaffLike arg) {
		StaffList res = new StaffList();
		String sql = "";
		TAcOrg org = dao.queryForEntity(Long.parseLong(arg.orgId.toString()),
				TAcOrg.class);
		TAcOrg org2 = dao.queryForEntity(Long.parseLong(org.getCompany()),TAcOrg.class);
		Org o = new Org();
		o.id = org2.getOrgid().intValue();
		o.name = org2.getOrgname();
		res.org = o;
		List<StaffGroup> list = null;
		if (org != null) {
			sql = getListStaffLikeName(String.valueOf(org.getOrgid()),
					arg.key);
			// 初始化分页参数
			res.page = newPage(sql, arg.page);

			// 分页查询
			List<Object[]> orgs = getPageist(sql, res.page);
			if (orgs != null && orgs.size() > 0) {
				list = getStaffInfo(orgs);
			} else {
				list = new ArrayList<StaffGroup>();
			}
		}

		res.list = list;
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public StaffList ListStaff(Integer orgId, Page page, Object... val) {
		StaffList res = new StaffList();
		String sql = "";
		List<StaffGroup> list = null;
		TAcOrg org = dao.queryForEntity(Long.parseLong(orgId.toString()),TAcOrg.class);
		Org o = new Org();
		if(org != null ){
		TAcOrg org2 = dao.queryForEntity(Long.parseLong(org.getCompany()),TAcOrg.class);
		o.id = org2.getOrgid().intValue();
		o.name = org2.getOrgname();
		res.org = o;
		}
		if (org != null) {
			sql = getStaffSql(org.getOrgid().toString(), val);
			// 初始化分页参数
			res.page = newPage(sql, page);
			// 分页查询
			List<Object[]> orgs = getPageist(sql, res.page);
			if (orgs != null && orgs.size() > 0) {
				list = getStaffInfo(orgs);
			} else {
				list = new ArrayList<StaffGroup>();
			}
		}
		res.list = list;
		return res;
	}

		@Resource
		private	AuditOrderDao auditOrderDao;
	
		public OrgInfoPage GetOrgList(Request_GetOrgList req){
			Long orgId= null;
			String rolename="";
			String flag= "";
			TAcUser  user = auditOrderDao.queryForEntity(Long.parseLong(req.userId.toString()), TAcUser.class);
			if("121".equals(user.getOrgid()+"") ||  "2443".equals(user.getOrgid()+"")){
				List<TAcUserrole> tlist = auditOrderDao.queryForList("from TAcUserrole t where t.userid= "+req.userId);
				Long roleid = tlist.get(0).getRoleid();
				List<TAcRole> rlist = auditOrderDao.queryForList("from TAcRole t where t.roleid= "+Long.parseLong(roleid.toString()));
				rolename=rlist.get(0).getRolename();
				if("64".equals(roleid+"")){
					flag = "1";
				}
				if("20".equals(roleid+"")){
					flag = "2";
				}
				if(rolename.contains("高级客维")){
					flag="3";
				}
			}else{
				TAcOrg  org = auditOrderDao.queryForEntity(user.getOrgid(), TAcOrg.class);
				orgId = Long.parseLong(org.getCompany());
				flag="4";
			}
			if("4".equals(flag))
				return   getOrgNameList(req.userId,orgId.toString(),"5",req.page) ;
			else if("2".equals(flag))
				return   getOrgNameList(req.userId,user.getUsername(),"6",req.page) ;
			else if("1".equals(flag))
				return	getOrgNameList(req.userId,user.getUsername(),"7",req.page) ;
			else if("3".equals(flag))
				return	getOrgNameList(req.userId,"","",req.page) ;
			return new OrgInfoPage();
				
	}
	
	public Integer GetBookRight(Request_GetBookRight arg0){
		Integer powercode=0;
	
		String sql="select POWER_CODE  from BOOKPOWER where USER_ID= "+arg0.userId;
		
		
		Object[] param = new Object[]{arg0.businessType-1};
		
				
		sql+=" and TRAVELTYPE in(?,2) order by TRAVELTYPE";
		
		
		List<?> powercodelist = dao.queryBySQL(sql,param);
	
		if(powercodelist!=null&&powercodelist.size()>0){
			
			powercode= Integer.parseInt(powercodelist.get(0).toString()); 
		}
		
		if(powercode==4||powercode==5){
			powercode-=1;
			}
		if(powercode==255){
			powercode=0;
		}
		return powercode;
	}
		@Override
		public Response_GetHotelServiceFee getServiceFee(Integer userId) {
			Response_GetHotelServiceFee obj = new  Response_GetHotelServiceFee();
			obj.data = new FeeService();
			obj.data.protocols = null;
			List<Protocol> list = new ArrayList<Protocol>();
			TAcUser user = null;
			user = userDao.findOne(Long.parseLong(userId.toString()));
			if(user != null){
				TAcOrg depatement = dao.queryForEntity(user.getOrgid(),TAcOrg.class);
				if(depatement != null){
					TAcOrg org = dao.queryForEntity(Long.parseLong(depatement.getCompany()),TAcOrg.class);
					//HOTEL_ERP_NOWPAY_TYPE
					list.add(new Protocol(1,2,Integer.parseInt(org.getHotelErpNowpayType()),org.getHotelErpNowpayFee()==null?0:org.getHotelErpNowpayFee()));
					list.add(new Protocol(1,1,Integer.parseInt(org.getHotelErpPrepaidType()),org.getHotelErpPrepaidFee()==null?0:org.getHotelErpPrepaidFee()));
					//HOTEL_PERP_NOWPAY_TYPE
					list.add(new Protocol(2,2,Integer.parseInt(org.getHotelPerpNowpayType()),org.getHotelPerpNowpayFee()==null?0:org.getHotelPerpNowpayFee()));
					list.add(new Protocol(2,1,Integer.parseInt(org.getHotelPerpPrepaidType()),org.getHotelPerpPrepaidFee()==null?0:org.getHotelPerpPrepaidFee()));
					//HOTEL_NOWPAY_TYPE
					list.add(new Protocol(3,2,Integer.parseInt(org.getHotelNowpayType()),org.getHotelNowpayFee()==null?0:org.getHotelNowpayFee()));
					list.add(new Protocol(3,1,Integer.parseInt(org.getHotelPrepaidType()),org.getHotelPrepaidFee()==null?0:org.getHotelPrepaidFee()));
					//HOTEL_CHANGE_TYPE
					list.add(new Protocol(4,0,Integer.parseInt(org.getHotelChangeType()),org.getHotelChangeFee()==null?0:org.getHotelChangeFee()));
					//HOTEL_NIGHT_TYPE
					list.add(new Protocol(5,0,Integer.parseInt(org.getHotelNightType()),org.getHotelNightFee()==null?0:org.getHotelNightFee()));
					obj.feeState = Integer.parseInt(org.getHotelServiceStatus());
					obj.data.type = Integer.parseInt(org.getHotelServicefeeType());
				}
				obj.data.protocols = list;
				
			}
			return obj;
		}
	@Override
	/**
	 * 服务费计算
	 */
	public Response_HotelServiceFee countServiceFee(Request_HotelServiceFee arg) {
		Response_HotelServiceFee obj = new  Response_HotelServiceFee();
		TAcUser user = null;
		user = userDao.findOne(Long.parseLong(arg.userId.toString()));
		if(user != null){
			TAcOrg depatement = dao.queryForEntity(user.getOrgid(),TAcOrg.class);
			if(depatement != null){
				TAcOrg org = dao.queryForEntity(Long.parseLong(depatement.getCompany()),TAcOrg.class);
				org.setHotelChangeFee(org.getHotelChangeFee()==null?0.0:org.getHotelChangeFee());
				org.setHotelNightFee(org.getHotelNightFee()==null?0.0:org.getHotelNightFee());
				if("2".equals(org.getHotelServiceStatus())){//收取服务费
					//预订服务费
					if(1==arg.data.payType){//预付
						if(1==arg.data.protocolType){//协议酒店
							obj.fee = "1".equals(org.getHotelErpPrepaidType())?org.getHotelErpPrepaidFee()
									:arg.data.amount*org.getHotelErpPrepaidFee()/100;
						}else if(2==arg.data.protocolType){//平台酒店
							obj.fee = "1".equals(org.getHotelPerpPrepaidType())?org.getHotelPerpPrepaidFee()
									:arg.data.amount*org.getHotelPerpPrepaidFee()/100;
						}else if(3==arg.data.protocolType){//景鸿协议预订服务费
							obj.fee = "1".equals(org.getHotelPrepaidType())?org.getHotelPrepaidFee()
									:arg.data.amount*org.getHotelPrepaidFee()/100;
						}else{
							obj.fee=0.0;
						}
					}else if(2==arg.data.payType){//现付
						if(1==arg.data.protocolType){//协议酒店
							obj.fee = "1".equals(org.getHotelErpNowpayType())?org.getHotelErpNowpayFee()
									:arg.data.amount*org.getHotelErpNowpayFee()/100;
						}else if(2==arg.data.protocolType){//平台酒店
							obj.fee = "1".equals(org.getHotelPerpNowpayType())?org.getHotelPerpNowpayFee()
									:arg.data.amount*org.getHotelPerpNowpayFee()/100;
						}else if(3==arg.data.protocolType){//景鸿协议预订服务费
							obj.fee = "1".equals(org.getHotelNowpayType())?org.getHotelNowpayFee()
									:arg.data.amount*org.getHotelNowpayFee()/100;
						}else{
							obj.fee=0.0;
						}
					}
					
					//变更服务费
					if(4==arg.data.protocolType){
						obj.fee = "1".equals(org.getHotelChangeType())?org.getHotelChangeFee()
								:arg.data.amount*org.getHotelChangeFee()/100;
					}
					
					//夜间服务费
					if(DateUtil.isNight(arg.data.time)){
						obj.nightFee = "1".equals(org.getHotelNightType())?org.getHotelNightFee()
								:arg.data.amount*org.getHotelNightFee()/100;
					}else{
						obj.nightFee = 0.0;
					}
					
				}else{
					obj.fee=0.0;
					obj.nightFee=0.0;
				}
			}
			
		}
		return obj;
	}
	
		@SuppressWarnings({ "unused", "unchecked" })
		@Override
		public StaffList BokeListStaff(Integer userId, Page page,
				Integer businessType, String nameKey) {
			StaffList data = new StaffList();
			Integer type = null;
			if(businessType == 1)
				type = 0;
			if(businessType == 2)
				type = 1;
			if(businessType == 0)
				type = 2;
			 Integer companyId = BookStaffOrg(userId);
			List<Object[]> bookPower = (List<Object[]>) dao.queryBySQL("select bookpower_id,user_id,traveltype,power_code from bookpower where user_id = '"+userId.toString()+"' and traveltype in ('"+type+"','2') order by traveltype",null); 
				if(bookPower != null && bookPower.size() > 0){
					Object[] obj = bookPower.get(0);
					//不限
					if("255".equals(obj[3].toString())){
						if("".equals(nameKey))
					       data = ListStaff(companyId,page,"inDep");
						else
						   data = ListStaff(companyId,page,"likeName",nameKey);
					}
					if("1".equals(obj[3].toString())){
					  data = ListStaff(companyId,page,"byIdsAndName",userId,nameKey);
					}
					if("2".equals(obj[3].toString())){
						String sql = "";
						sql = "select person_id from bookpower_mapping where bookpower_id = '"+obj[0].toString()+"'";
						List<Object[]> mapping = (List<Object[]>) dao.queryBySQL(sql,null); 
						String ids = "";
							if(mapping != null && mapping.size() > 0){
								ids = mapping.toString().replaceAll(" +","");
								ids = ids.substring(1,ids.length()-1);
							}
						  data = ListStaff(companyId,page,"byIdsAndName",ids,nameKey);
					}
					
					if("4".equals(obj[3].toString())){
						String sql = "";
						  //data = ListBookStaff(companyId,page,userId,nameKey);
					}
					if("5".equals(obj[3].toString())){
						  String sql = "";
						  data = ListBookStaffAndMy(companyId,page,userId,nameKey);
					}
				}else{
					if("".equals(nameKey))
					       data = ListStaff(companyId,page,"inDep");
						else
						   data = ListStaff(companyId,page,"likeName",nameKey);
				}
			return data;
		}
		@SuppressWarnings("unused")
		private StaffList ListBookStaffAndMy(Integer companyId, Page page,
				Integer userId, String nameKey) {
				StaffList data = new StaffList();
				//StaffList data1 = new StaffList();
				//user
				 data = ListStaff(companyId,page,"byIdsAndName",userId,nameKey);
				//passenger
				// data1 = ListBookStaffAndMe(companyId,page,userId,nameKey);
				// if(data1 != null){
				//	 data.page.count = data.list.size()+ data1.list.size();
				//	 data.list.addAll(data1.list);
			    //	 }
				// if(page.index > 1){
				//	 return data1;
				// }else{
				 //}
				 return data;
		}
		@SuppressWarnings({ "rawtypes" })
		private  List getPagelist(String sql, Page page) {
			Integer num = ((page.index - 1) * page.size)-1;
			Integer num1 =page.index * page.size -1;
			if(num < 0)
				num = 0;
			String pageSql = "select * from (SELECT t.*, ROWNUM AS rowno FROM"
					+ " (" + sql + ") t where rownum <= " + num1
					+ ") t1 " + " where t1.rowno > " + num + " ";
			List list = dao.queryBySQL(pageSql, null);
			System.out.println(list);
			return list;
		}
		@SuppressWarnings("unchecked")
		private StaffList ListBookStaff(Integer companyId, Page page,
				Integer userId, String nameKey) {
			StaffList st = new StaffList();
			List<StaffGroup> relist = new ArrayList<StaffGroup>();
			StaffGroup sg = null;
			Org o = new Org();
			String sql="select ID,NAME,MOBILEPHONE,EMAIL,PASSENGER_TYPE,IDTYPE,IDNUMBER  from t_travel_passenger where userid= "+userId;
			if(!"".equals(nameKey)&& nameKey !=null){
				sql +=" and name like '%"+nameKey+"%'";
			}
			List<Object[]> list = getPageist(sql, page);
			Page newPage = newPage(sql,page);
			TAcOrg org2 = dao.queryForEntity(Long.parseLong(companyId.toString()),TAcOrg.class);
			if(org2 != null ){
				o.id = org2.getOrgid().intValue();
				o.name = org2.getOrgname();
			}
			for(Object[] obj : list){
				sg = new StaffGroup();
				List<Ids> ids = new ArrayList<Ids>();
				Ids id = new Ids();
				sg.id = Integer.parseInt(obj[0].toString());
				sg.name = obj[1].toString();
				sg.mobile = obj[2] == null ? "" : obj[2].toString();
				sg.email = obj[3] == null ? "" : obj[3].toString();
				sg.type = Integer.parseInt(obj[4] == null ? "0" : obj[4].toString());
				sg.sex = 0;
				sg.birthday = "";
				if(obj[5] != null){
					id.type = Integer.parseInt(obj[5].toString());
					id.num = obj[6].toString();
				}
				sg.ids = ids;
				relist.add(sg);
			}
			st.org = o;
			st.page = newPage;
			st.list = relist;
			return st;
		}
		@SuppressWarnings("unchecked")
		private StaffList ListBookStaffAndMe(Integer companyId, Page page,
				Integer userId, String nameKey) {
			StaffList st = new StaffList();
			StaffGroup sg = null;
			Org o = new Org();
			String sql="select ID,NAME,MOBILEPHONE,EMAIL,PASSENGER_TYPE,IDTYPE,IDNUMBER  from t_travel_passenger where userid= "+userId;
			if(!"".equals(nameKey)&& nameKey !=null){
				sql +=" and name like '%"+nameKey+"%'";
			}
			List<Object[]> list = getPagelist(sql, page);
			Page newPage = newPage(sql,page);
			TAcOrg org2 = dao.queryForEntity(Long.parseLong(companyId.toString()),TAcOrg.class);
			if(org2 != null ){
				o.id = org2.getOrgid().intValue();
				o.name = org2.getOrgname();
			}
			List<StaffGroup> relist = new ArrayList<StaffGroup>();
			for(Object[] obj : list){
				sg = new StaffGroup();
				List<Ids> ids = new ArrayList<Ids>();
				Ids id = new Ids();
				sg.id = Integer.parseInt(obj[0].toString());
				sg.name = obj[1].toString();
				sg.mobile = obj[2] == null ? "" : obj[2].toString();
				sg.email = obj[3] == null ? "" : obj[3].toString();
				sg.type = Integer.parseInt(obj[4] == null ? "0" : obj[4].toString());
				sg.sex = 0;
				sg.birthday = "";
				if(obj[5] != null){
					id.type = Integer.parseInt(obj[5].toString());
					id.num = obj[6].toString();
				}
				sg.ids = ids;
				relist.add(sg);
			}
			st.org = o;
			st.page = newPage;
			st.list = relist;
			return st;
		}
		
		public double GetPayQuota(Request_GetPayQuota arg0){
			Double data=0.0;
			TAcUser user = null;
			user = userDao.findOne(Long.parseLong(arg0.userId.toString()));
			
			TAcOrg org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
			org = dao.queryForEntity(Long.parseLong(org.getCompany().toString()), TAcOrg.class);
			
				//月结授权
				if(arg0.type==1&&"0".equals(org.getIsBalancePay())){
					data=  org.getCreditLine()==null?0:org.getCreditLine();		
					
				}
				//预付款
				if(arg0.type==2&&"1".equals(org.getIsBalancePay())){
					
					
					//查询当前余额
					
					String hql="SELECT nvl(SUM(money),0.0) FROM TAcOrgBalance T WHERE 1=1 AND T.orgid = "+org.getCompany();
					
					Double balance= dao.queryForDouble(hql);
					
					data=balance==null?0:balance;		
					
					
				}
						
			
			return data;
		}
		@Override
		@Transactional
		public Double PayBalance(Request_PayByAdvance arg0) {
			TAcUser user = null;
			Double b = 0D;
	        user = userDao.findOne(Long.parseLong(arg0.userId.toString()));
			TAcOrg org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
			if(org != null){
				TAcOrg org2 = dao.queryForEntity(Long.parseLong(org.getCompany().toString()), TAcOrg.class);
				if(org2 != null){
					String sql = "select sum(money) from t_ac_org_balance t  where t.orgid = '"+org2.getOrgid()+"'" +
							" group by orgid ";
					List orgMoney = dao.queryBySQL(sql, null);
					if(orgMoney != null && orgMoney.size() > 0){
						String moneyStr = orgMoney.get(0).toString();
						Double money = Double.parseDouble(moneyStr.toString());
						if(money - arg0.amount >= 0){
							TAcOrgBalance bal = new TAcOrgBalance();
							bal.setOrgid(org2.getOrgid());
							bal.setOrgname(org2.getOrgname());
							bal.setUserId(user.getUserid());
							bal.setUserName(user.getUsername());
							bal.setMoney(arg0.amount * -1);
							bal.setFlowStatus(arg0.type == 1 ? "1" : "-2");
							bal.setOrderNo(arg0.orderNo);
							bal.setIsModify(0);
							bal.setOperatingDate(dao.str2Date(dao.getDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
							dao.executeSave(bal);
							b = money + arg0.amount * -1;
						}else{
							 b = -1D;
						}
					}else{
						 b = -1D;
					}
				}else{
					 b = -1D;
				}
			}else{
				 b = -1D;
			}
			return b;
		}
		@Override
		public String getUserInfoByPhone(String phone) {
			 List list =  dao.queryBySQL("select userId from t_ac_user t where t.phone='"+phone+"'", null);
			 if(list != null && list.size()>0){
				 if(list.get(0) != null && !"".equals(list.get(0).toString()))
				 return list.get(0).toString();
				 else return "-1";
			 }else return "-1";
		}
		@Override
		public Integer CheckIsUnneedAuditByBookUser(Integer userId) {
			TAcUser user = null;
			Integer data = 0;
			List list =  dao.queryBySQL("select userId from unneedaudituser t where t.userid='"+userId+"'", null);
			if(list != null && list.size()>0){
			    data = 1 ;
			}else {
				user = userDao.findOne(userId.longValue());
				if(user != null){
					TAcOrg org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
					if(org != null){
						if("1".equals(org.getVerify()))
							data = 1;
					}
				}
			}
			return data;
		}
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
		public GetRulesByTravllerModel getPolicyDetail(Request_GetRulesByTravller arg) {
			GetRulesByTravllerModel vo = new GetRulesByTravllerModel();
			TAcUser user = null;
			TAcOrg org = null;
			OrgPolicyManage opm = null;
			try{
				user = userDao.findOne(arg.travellers.get(0).longValue());
				org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
				Long companyId = Long.parseLong(org.getCompany());
				//查询政策开关
				opm = dao.queryForEntity(companyId, OrgPolicyManage.class);
				if(opm != null&& opm.getPolicyControl() == 1){
					TTmcPolicyBusiness ttpb = null;
					DomTicketModel dtm = null;
					IntlTicketModel itm = null;
					TTmcPolicy ttp = null;
					List<HATPolicyModel> list = new ArrayList<HATPolicyModel>();
					HATPolicyModel hat = null;
					List<RuleMaps> ruleMaps = null;
					List<RuleMaps> ruleIntlMaps = null;
					List<TicketRules> ticketRuleList = null;
					RuleMaps userMaps = null;
					for(Integer type : arg.businessTypes){
						ttpb = dao.queryForEntity(new TTmcPolicyBusinessId(companyId,type), TTmcPolicyBusiness.class);
						//---------------各业务开关------------------------------
						if(ttpb != null){
							if(ttpb.getId().getBussinesstype() == 1 ){
								dtm = new DomTicketModel();
								dtm.audit = ttpb.getOverproofaudit();
								dtm.book = ttpb.getOverproofbook();
								dtm.policyDefault = ttpb.getOverproofdefault();
								dtm.show = ttpb.getOverproofshow();
								dtm.state = ttpb.getPolicycontrol();
								vo.ticket = dtm;
								ruleMaps = new ArrayList<RuleMaps>();
								
							}
							if(ttpb.getId().getBussinesstype() == 2 ){
								itm = new IntlTicketModel();
								itm.audit = ttpb.getOverproofaudit();
								itm.book = ttpb.getOverproofbook();
								itm.policyDefault = ttpb.getOverproofdefault();
								itm.show = ttpb.getOverproofshow();
								itm.state = ttpb.getPolicycontrol();
								vo.intlTicket = itm;
								ruleIntlMaps = new ArrayList<RuleMaps>();
							}
							if(ttpb.getId().getBussinesstype() > 2 ){
								hat = new HATPolicyModel();
								hat.audit = ttpb.getOverproofaudit();
								hat.book = ttpb.getOverproofbook();
								hat.policyDefault = ttpb.getOverproofdefault();
								hat.show = ttpb.getOverproofshow();
								hat.state = ttpb.getPolicycontrol();
								hat.busnessType = ttpb.getId().getBussinesstype();
								hat.rules = new ArrayList<HATRules>();
								list.add(hat);
							}
						}
						if(list.size() > 0 ){
							vo.policy = list;
						}
					//---------------各业务开关结束------------------------------
						//循环userid，分别获取各自政策信息
						List<Travller> tlist = new ArrayList<Travller>();
						Travller travller = null;
						for(Integer userId : arg.travellers){
							travller = new Travller();
							travller.traveller = userId;
							user = new TAcUser();
							org = new TAcOrg();
							user = userDao.findOne(userId.longValue());
							org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
							String groupid = null;
							String policyId = null;
							String sql = "";
							sql += "select  og.orgid,og.groupid,ogr.type from org_group og left join org_group_relation ogr on og.orgid = ogr.orgid and og.groupid = ogr.groupid where ogr.id ='"+user.getUserid()+"' and ogr.type=4" +
									" union " +
									" select og.orgid,og.groupid,ogr.type from org_group og left join org_group_relation ogr on og.orgid = ogr.orgid and og.groupid = ogr.groupid where ogr.id = '"+user.getOrgid()+"' and ogr.type=3" +
									" union " +
									" select og.orgid,og.groupid,ogr.type from org_group og left join org_group_relation ogr on og.orgid = ogr.orgid and og.groupid = ogr.groupid where ogr.id = '"+companyId+"' and ogr.type=1";
							List groupids =  dao.queryBySQL("select groupid from ("+sql+") t where t.orgid='"+companyId+"' order by type desc ",null);
							groupid = getString(groupids);
							String policySql = "";
							  policySql += "select t1.enterp_id,t1.rule_type_id,org_rule_type,t1.create_time from T_TMC_TRIP_RULE t1 where t1.rule_type = 1 and t1.org_rule_type = 4 and state=1 and t1.org_rule like '%"+user.getUserid()+"%' ";
							  policySql +=" union ";
							  policySql += "select t2.enterp_id,t2.rule_type_id,org_rule_type,t2.create_time  from T_TMC_TRIP_RULE t2 where t2.rule_type = 1 and t2.org_rule_type = 2 and state=1 and t2.org_rule like '%"+user.getOrgid()+"%' ";
							 if(groupid != null && !"".equals(groupid)){
								 policySql +=" union ";
								 policySql += "select t3.enterp_id,t3.rule_type_id,org_rule_type,t3.create_time  from T_TMC_TRIP_RULE t3 where t3.rule_type = 1 and t3.org_rule_type = 3 and state=1 and t3.org_rule like '%,"+groupid+",%' ";
							 }
							  policySql +=" union ";
							  policySql += "select t4.enterp_id,t4.rule_type_id,org_rule_type,t4.create_time  from T_TMC_TRIP_RULE t4 where t4.rule_type = 1 and t4.org_rule_type = 1  and state=1 and t4.org_rule like '%"+companyId+"%' ";
	
							List policyIds = dao.queryBySQL("select rule_type_id  from ("+policySql+") t where t.enterp_id='"+companyId+"' order by org_rule_type desc,create_time desc",null);
							policyId = getString(policyIds);
							if(policyId != null && !"".equals(policyId)){
								ttp = dao.queryForEntity(Long.parseLong(policyId), TTmcPolicy.class);
								travller.id = Integer.parseInt(policyId);
								travller.name = ttp.getPolicyName();
								if(ttp.getState() == 2){
									if(type == 1){ //国内机票
										if(ruleMaps != null){
											userMaps = new RuleMaps();
											userMaps.traveller = userId;
											ticketRuleList = new ArrayList<TicketRules>();
											List tickets = dao.queryBySQL("select * from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
											if(tickets != null && tickets.size() > 0 ){
												TicketRules ticketRules = null;
												List<OrgPolicyTicket> optlist = dao.queryForList("from OrgPolicyTicket t where t.id.orgid = '"+companyId+"' and t.id.ruleid like '"+policyId+"%' ");
												if(optlist != null && optlist.size() > 0){
													for(OrgPolicyTicket opt : optlist){
														ticketRules = new TicketRules();
														ticketRules.id = opt.getId().getRuleid();
														ticketRules.audit = opt.getOverproofaudit();
														ticketRules.book = opt.getOverproofbook();
														ticketRules.show = opt.getOverproofshow();
														ticketRules.type = opt.getPolicyType();
														List<PolicyTicketRule> trlist = dao.queryForList("from PolicyTicketRule t where t.id.ruleid = '"+opt.getId().getRuleid()+"' ");
														List<TicketItems> items = null;
														if(trlist != null && trlist.size() > 0){
															items = new ArrayList<TicketItems>();
															TicketItems ti = null;
															for(PolicyTicketRule trVo : trlist){
																ti = new TicketItems();
																ti.flowId = trVo.getId().getFlowid();
																ti.type = trVo.getRuletype();
																ti.lowerLimit = trVo.getLowerLimit();
																ti.upperLimit = trVo.getUpperLimit();
																ti.rule = trVo.getRules();
																items.add(ti);
															}
															ticketRules.items = items;
														}
														ticketRuleList.add(ticketRules);
													}
												}
											}
											userMaps.rules = ticketRuleList;
											ruleMaps.add(userMaps);
										}
									}else if (type == 2){//国际机票
										if(ruleIntlMaps != null){
											userMaps = new RuleMaps();
											userMaps.traveller = userId;
											ticketRuleList = new ArrayList<TicketRules>();
											List tickets = dao.queryBySQL("select * from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
											if(tickets != null && tickets.size() > 0 ){
												TicketRules ticketRules = null;
												List<PolicyIntlTicket> optlist = dao.queryForList("from PolicyIntlTicket t where t.id.orgid = '"+companyId+"' and t.id.ruleid like '"+policyId+"%' ");
												if(optlist != null && optlist.size() > 0){
													for(PolicyIntlTicket opt : optlist){
														ticketRules = new TicketRules();
														ticketRules.id = opt.getId().getRuleid().intValue();
														ticketRules.audit = opt.getOverproofaudit();
														ticketRules.book = opt.getOverproofbook();
														ticketRules.show = opt.getOverproofshow();
														ticketRules.type = opt.getPolicytype();
														List<PolicyIntlTicketRule> trlist = dao.queryForList("from PolicyIntlTicketRule t where t.id.ruleid = '"+opt.getId().getRuleid()+"' ");
														List<TicketItems> items = null;
														if(trlist != null && trlist.size() > 0){
															items = new ArrayList<TicketItems>();
															TicketItems ti = null;
															for(PolicyIntlTicketRule trVo : trlist){
																ti = new TicketItems();
																ti.flowId = trVo.getId().getFlowid();
																ti.type = trVo.getRuletype();
																ti.lowerLimit = trVo.getLowerlimit();
																ti.upperLimit = trVo.getUpperlimit();
																ti.rule = trVo.getRules();
																items.add(ti);
															}
															ticketRules.items = items;
														}
														ticketRuleList.add(ticketRules);
													}
												}
											}
											userMaps.rules = ticketRuleList;
											ruleIntlMaps.add(userMaps);
										}
									}else if (type == 3 || type == 5){//火车票、酒店
										
										if(vo.policy != null && vo.policy.size() > 0){
											for(HATPolicyModel model : vo.policy){
												//查询政策， 后期增加有效期，在这里添加
												if(model.busnessType == type && model.state == 1){
														TTmcPolicyDetail  ddpd = null;
														HATRules rules = null;
														String flowid = null;
														List flowids = dao.queryBySQL("select flowid from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
														flowid = getString(flowids);
														if(flowid != null && !"".equals(flowid)){
															ddpd = dao.queryForEntity(new TTmcPolicyDetailId(Long.parseLong(policyId),Long.parseLong(flowid)), TTmcPolicyDetail.class);
															if(ddpd != null){
																rules = new HATRules();
																rules.policyId = ttp.getPolicyId();
																rules.policyName = ttp.getPolicyName();
																rules.traveller = userId;
																rules.bookingRemindType = ddpd.getBookingRemindType();
																rules.auditRemindType = ddpd.getAuditRemindType();
																rules.displayRemindType = ddpd.getDisplayRemindType();
																rules.policyDetailValue = ddpd.getPolicyDetailValue();
																model.rules.add(rules);
															}
														}
													}
												}
											}
										}// 火车票、酒店
									}
							}
							tlist.add(travller);
						//--------------policyId if  end---------------------------	
						}
						if(tlist.size() > 0){
							vo.maps = tlist;
						}
						if(ruleMaps != null && ruleMaps.size() > 0){
							vo.ticket.ruleMaps = ruleMaps;
						}
						
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		@SuppressWarnings("rawtypes")
		public String getString(List list) {
			String str = null;
			if(list != null && list.size() > 0){
				str = list.get(0).toString();
			}
			return str;
		}
		@Override
		public Res_ApprovalsDetail getApprovalDetail(Request_GetApprovalDetail id) {
			
			Res_ApprovalsDetail detail = new Res_ApprovalsDetail();
			try {
				TTmcApprovePolicy policy = dao.queryForEntity(Long.parseLong(id.approvalId.toString()),TTmcApprovePolicy.class);
				detail.id=Integer.valueOf(policy.getPolicyId().toString());
				detail.desc=policy.getMemo();
				detail.name=policy.getPolicuName();
				detail.type=Integer.valueOf(policy.getApproveType().toString());
				String hql=" from TTmcApprovePolicyFlow t where approve_policy_id ="+id.approvalId;
				List<TTmcApprovePolicyFlow> ttps = dao.queryForList(hql);
				//List<TTmcApprovePolicyFlow> ttps = (List<TTmcApprovePolicyFlow>) dao.queryBySQL(hql,null);
				String hsql="select approve_level from T_TMC_APPROVE_FLOW t where  approve_policy_id ="+id.approvalId+"  group by approve_level";
				List list =(List) dao.queryBySQL(hsql, null);
				String hqls="select approve_policy_id,lable_value,match_type from T_TMC_APPROVE_POLICY_LABLE where approve_policy_id= "+id.approvalId+" and lable_type =2";
				List<Object[]> lable=(List<Object[]> )dao.queryBySQL(hqls, null);
				Object [] dd=lable.get(0);
				if("1".equals(dd[2].toString())){
					detail.approvalType=0;
				}else{
					detail.approvalType=Integer.valueOf(dd[1].toString());
				}
				String hsql2="select approve_policy_id,lable_value,match_type from T_TMC_APPROVE_POLICY_LABLE where approve_policy_id= "+dd[0]+" and lable_type =1";
				List<Object[]> busslable=(List<Object[]> )dao.queryBySQL(hsql2, null);
				Object [] dds=busslable.get(0);
				if("1".equals(dds[2].toString())){
					detail.businessType=0;
				}else{
					detail.businessType=Integer.valueOf(dds[1].toString());
				}
				List<ApprovalsModel> resList = new ArrayList<ApprovalsModel>();
				for (int i = 0; i < list.size(); i++) {
					ApprovalsModel model = new ApprovalsModel();
					List<ApprovalsPerson> persons = new ArrayList<ApprovalsPerson>();
					model.flowId=Integer.valueOf(list.get(i).toString());
					for(TTmcApprovePolicyFlow ttp:ttps){
						if(ttp.getApprLevel().toString().equals(list.get(i).toString())){
							ApprovalsPerson person = new ApprovalsPerson();
							person.id=Integer.valueOf(ttp.getApproveUserId().toString());
							person.name=ttp.getApproveUserName();
							person.email=ttp.getApproveUserEmail();
							person.mobile=ttp.getApproveUserMobile();
							persons.add(person);
						}
					}
					model.auditors=persons;
					resList.add(model);
				}
				detail.approvals=resList;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return detail;
		}
		
		
		@Override
		public List<Res_ApprovalByTravller> listApprovalByTravller(Request_ListApprovalByTravller arg) {
			List<Res_ApprovalByTravller> finals=new ArrayList<Res_ApprovalByTravller>();
			try {
				
				String pids="";   //出行人id
				String orgids="";  //部门id
				List<ApproveTravller> list=arg.travellers;
				List idsList=new ArrayList();
				for(ApproveTravller at:list){
					if(!"".equals(pids) && StringUtils.isNotEmpty(String.valueOf(at.id))){
						pids +=",";
					}
					pids+=at.id;
					
					if(StringUtils.isNotEmpty(orgids) && StringUtils.isNotEmpty(String.valueOf(at.orgId))){
						orgids+=",";
						orgids+=at.orgId;
					}
						
					String string = StringUtils.join(at.costIds, ",");
					if(string!=null){
						if(StringUtils.isNotEmpty(orgids)){
							orgids+=",";
						}
						orgids+=string;
					}
				}
				
				String fin="";
				if(!"".equals(pids)){
					if(!"".equals(orgids)){
						fin=pids+","+orgids;
					}else{
						fin=pids;
					}
				}
				
				//先根据    部门id和员工id  查询分组id
				String sql="select distinct groupid from org_group_relation where id in ("+fin+")";
				List<Object> groupIds = (List<Object>) dao.queryBySQL(sql, null);
				List<Object> ruleMaps = new ArrayList<Object>();
				//根据分组id查审批关联表查出审批规则id；
				for (int i = 0; i < groupIds.size(); i++) {
					String fenzu="select rule_type_id from T_TMC_TRIP_RULE t where org_rule like '%"+groupIds.get(i)+"%' and rule_type=2 and org_rule_type =2 and state=1";
					List<Object> queryBySQL = (List<Object>)dao.queryBySQL(fenzu, null);
					ruleMaps.addAll(queryBySQL);
				}
				
				//根据部门id去查关联表
				if(!"".equals(orgids) && orgids!=null){
					String[] split = orgids.split(",");
					for(String orgid:split){
						String orgs="select rule_type_id from T_TMC_TRIP_RULE t where org_rule like '%"+orgid+"%' and rule_type=2 and state=1 and org_rule_type in (1,3)";
						List<Object> queryBySQL = (List<Object>)dao.queryBySQL(orgs, null);
						ruleMaps.addAll(queryBySQL);
					}
				}
				
				//根据员工id去查关联表
				if(!"".equals(pids) && pids!=null){
					String[] split = pids.split(",");
					for(String pid:split){
						String orgs="select rule_type_id from T_TMC_TRIP_RULE t where org_rule like '%"+pid+"%' and rule_type=2 and org_rule_type =4 and state=1";
						List<Object> queryBySQL = (List<Object>)dao.queryBySQL(orgs, null);
						ruleMaps.addAll(queryBySQL);
					}
				}
				
				//如果关联不到政策，返回空
				if(ruleMaps==null || ruleMaps.size()<1){
					return finals;
				}
				
				//关联政策，通过 业务类型和审批类型筛选
				String join = StringUtils.join(ruleMaps.toArray(),",");
				String hql=" from TTmcApprovePolicyLable where approve_policy_id in ("+join+")";
				List<TTmcApprovePolicyLable> lablelist = dao.queryForList(hql);
				for(TTmcApprovePolicyLable tta:lablelist){
					if(arg.businessType==0){
						if(arg.approvalType==0){   //审批类型不限
							idsList.add(tta.getApprPolicyId());
						}else{
							if(tta.getLableType()==2){
								if(tta.getLableValue()==Long.valueOf(arg.approvalType)){
									idsList.add(tta.getApprPolicyId());
								}
							}
						}
					}else{
						if(arg.approvalType==0){
							if(tta.getLableType()==1){
								if(tta.getLableValue()==Long.valueOf(arg.businessType)){
									idsList.add(tta.getApprPolicyId());
								}
							}
						}else{
							if(tta.getLableType()==1){  //业务类型
								if(tta.getLableValue()==Long.valueOf(arg.businessType)){
									idsList.add(tta.getApprPolicyId());
								}
							}
							
							if(tta.getLableType()==2){  //审批类型
								if(tta.getLableValue()==Long.valueOf(arg.approvalType)){
									idsList.add(tta.getApprPolicyId());
								}
							}
							
						}
					}
				}
				
				String finalIds = StringUtils.join(idsList.toArray(),",");
				if(StringUtils.isNotEmpty(finalIds)){
					hql=" from TTmcApprovePolicy where approve_policy_id in ("+finalIds+")";
					List<TTmcApprovePolicy> policys = dao.queryForList(hql);
					for(TTmcApprovePolicy po:policys){
						Res_ApprovalByTravller travller = new Res_ApprovalByTravller();
						travller.approvalType=arg.approvalType;
						travller.businessType=arg.businessType;
						travller.id=Integer.valueOf(po.getPolicyId().toString());
						travller.name=po.getPolicuName();
						travller.desc=po.getMemo();
						finals.add(travller);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return finals;
		}
		public static void main(String[] args) {
			Integer [] costids={2,4,6,8};
			String string = StringUtils.join(costids, ",");
			System.out.println(string);
		}
		@Override
		public CityLevelModel getCityLevel(Request_GetCityGrade arg) {
			CityLevelModel data = null;
			try{
				TAcUser user = null;
				TAcOrg org = null;
				user = userDao.findOne(arg.userId);
				org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
				Long companyId = Long.parseLong(org.getCompany());
				List<TTmcEnterpAreaLv> list = null;
				if(arg.businessType == 3){
					list = dao.queryForList("from TTmcEnterpAreaLv t where t.enterpId ='"+companyId+"' and t.cityList like '%"+arg.cityId+"%' and t.delFlag=1");
					if(list != null && list.size() > 0){
						data = new CityLevelModel();
						data.id = Integer.parseInt(list.get(0).getLineLv());
						data.name = list.get(0).getLineLvName();
					}else{
						data = new CityLevelModel();
					}
				}else if(arg.businessType == 4){
					TTmcAreaInfo area = getCityInfo(arg.cityId);
					if(area != null){
						data = new CityLevelModel();
						data.id = area.getAreaId().intValue();
						data.name = area.getCnName();
					}else{
						data = new CityLevelModel();
					}
					
				}
				
			}catch(Exception e ){
				e.printStackTrace();
				data = new CityLevelModel();
			}
			return data;
		}
		private TTmcAreaInfo getCityInfo(Integer cityId) {
			TTmcAreaInfo area = null;
			List<TTmcAreaInfo> list = dao.queryForList("from TTmcAreaInfo t where areaId="+cityId.longValue());
			if(list != null && list.size() > 0){
				if(list.get(0).getParentId() != null){
					area = getCityInfo(list.get(0).getParentId());
				}else{
					area = list.get(0);
				}
			}else{
				area = new TTmcAreaInfo();
			}
			
			return area;
		}
		@Override
		public GetRulesByTravllerModel getPolicyById(Request_GetRuleById arg) {
			GetRulesByTravllerModel vo = new GetRulesByTravllerModel();
			try{
				//查询政策开关
					DomTicketModel dtm = null;
					IntlTicketModel itm = null;
					TTmcPolicy ttp = null;
					List<HATPolicyModel> list = new ArrayList<HATPolicyModel>();
					HATPolicyModel hat = null;
					List<RuleMaps> ruleMaps = null;
					List<RuleMaps> ruleIntlMaps = null;
					List<TicketRules> ticketRuleList = null;
					RuleMaps userMaps = null;
					Integer type = arg.businessType;
						//---------------各业务开关------------------------------
							if(type == 1 ){
								dtm = new DomTicketModel();
								dtm.audit = defaultNum;
								dtm.book = defaultNum;
								dtm.policyDefault = defaultNum;
								dtm.show = defaultNum;
								dtm.state = defaultNum;
								vo.ticket = dtm;
								ruleMaps = new ArrayList<RuleMaps>();
								
							}
							if(type == 2 ){
								itm = new IntlTicketModel();
								itm.audit = defaultNum;
								itm.book = defaultNum;
								itm.policyDefault = defaultNum;
								itm.show = defaultNum;
								itm.state = defaultNum;
								vo.intlTicket = itm;
								ruleIntlMaps = new ArrayList<RuleMaps>();
							}
							if(type > 2 ){
								hat = new HATPolicyModel();
								hat.audit = defaultNum;
								hat.book = defaultNum;
								hat.policyDefault = defaultNum;
								hat.show = defaultNum;
								hat.state = defaultNum;
								hat.busnessType = type;
								hat.rules = new ArrayList<HATRules>();
								list.add(hat);
							}
						if(list.size() > 0 ){
							vo.policy = list;
						}
					//---------------各业务开关结束------------------------------
						//循环userid，分别获取各自政策信息
						List<Travller> tlist = new ArrayList<Travller>();
						Travller travller = null;
							travller = new Travller();
							travller.traveller = defaultNum;
							String policyId = arg.ruleId.toString();
							if(policyId != null && !"".equals(policyId)){
								ttp = dao.queryForEntity(Long.parseLong(policyId), TTmcPolicy.class);
								if(ttp != null){
									travller.id = Integer.parseInt(policyId);
									travller.name = ttp.getPolicyName();
									Long companyId = ttp.getEnterpId();
									if(type == 1){ //国内机票
										if(ruleMaps != null){
											userMaps = new RuleMaps();
											userMaps.traveller = defaultNum;
											ticketRuleList = new ArrayList<TicketRules>();
											List tickets = dao.queryBySQL("select * from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
											if(tickets != null && tickets.size() > 0 ){
												TicketRules ticketRules = null;
												List<OrgPolicyTicket> optlist = dao.queryForList("from OrgPolicyTicket t where t.id.orgid = '"+companyId+"' and t.id.ruleid like '"+policyId+"%' ");
												if(optlist != null && optlist.size() > 0){
													for(OrgPolicyTicket opt : optlist){
														ticketRules = new TicketRules();
														ticketRules.id = opt.getId().getRuleid();
														ticketRules.audit = opt.getOverproofaudit();
														ticketRules.book = opt.getOverproofbook();
														ticketRules.show = opt.getOverproofshow();
														ticketRules.type = opt.getPolicyType();
														List<PolicyTicketRule> trlist = dao.queryForList("from PolicyTicketRule t where t.id.ruleid = '"+opt.getId().getRuleid()+"' ");
														List<TicketItems> items = null;
														if(trlist != null && trlist.size() > 0){
															items = new ArrayList<TicketItems>();
															TicketItems ti = null;
															for(PolicyTicketRule trVo : trlist){
																ti = new TicketItems();
																ti.flowId = trVo.getId().getFlowid();
																ti.type = trVo.getRuletype();
																ti.lowerLimit = trVo.getLowerLimit();
																ti.upperLimit = trVo.getUpperLimit();
																ti.rule = trVo.getRules();
																items.add(ti);
															}
															ticketRules.items = items;
														}
														ticketRuleList.add(ticketRules);
													}
												}
											}
											userMaps.rules = ticketRuleList;
											ruleMaps.add(userMaps);
										}
									}else if (type == 2){//国际机票
										if(ruleIntlMaps != null){
											userMaps = new RuleMaps();
											userMaps.traveller = defaultNum;
											ticketRuleList = new ArrayList<TicketRules>();
											List tickets = dao.queryBySQL("select * from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
											if(tickets != null && tickets.size() > 0 ){
												TicketRules ticketRules = null;
												List<PolicyIntlTicket> optlist = dao.queryForList("from PolicyIntlTicket t where t.id.orgid = '"+companyId+"' and t.id.ruleid like '"+policyId+"%' ");
												if(optlist != null && optlist.size() > 0){
													for(PolicyIntlTicket opt : optlist){
														ticketRules = new TicketRules();
														ticketRules.id = opt.getId().getRuleid().intValue();
														ticketRules.audit = opt.getOverproofaudit();
														ticketRules.book = opt.getOverproofbook();
														ticketRules.show = opt.getOverproofshow();
														ticketRules.type = opt.getPolicytype();
														List<PolicyIntlTicketRule> trlist = dao.queryForList("from PolicyIntlTicketRule t where t.id.ruleid = '"+opt.getId().getRuleid()+"' ");
														List<TicketItems> items = null;
														if(trlist != null && trlist.size() > 0){
															items = new ArrayList<TicketItems>();
															TicketItems ti = null;
															for(PolicyIntlTicketRule trVo : trlist){
																ti = new TicketItems();
																ti.flowId = trVo.getId().getFlowid();
																ti.type = trVo.getRuletype();
																ti.lowerLimit = trVo.getLowerlimit();
																ti.upperLimit = trVo.getUpperlimit();
																ti.rule = trVo.getRules();
																items.add(ti);
															}
															ticketRules.items = items;
														}
														ticketRuleList.add(ticketRules);
													}
												}
											}
											userMaps.rules = ticketRuleList;
											ruleIntlMaps.add(userMaps);
										}
									}else if (type == 3 || type == 5){//火车票、酒店
										
										if(vo.policy != null && vo.policy.size() > 0){
											for(HATPolicyModel model : vo.policy){
												//查询政策， 后期增加有效期，在这里添加
												if(model.busnessType == type ){
														TTmcPolicyDetail  ddpd = null;
														HATRules rules = null;
														String flowid = null;
														List flowids = dao.queryBySQL("select flowid from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
														flowid = getString(flowids);
														if(flowid != null && !"".equals(flowid)){
															ddpd = dao.queryForEntity(new TTmcPolicyDetailId(Long.parseLong(policyId),Long.parseLong(flowid)), TTmcPolicyDetail.class);
															if(ddpd != null){
																rules = new HATRules();
																rules.policyId = ttp.getPolicyId();
																rules.policyName = ttp.getPolicyName();
																rules.traveller = defaultNum;
																rules.bookingRemindType = ddpd.getBookingRemindType();
																rules.auditRemindType = ddpd.getAuditRemindType();
																rules.displayRemindType = ddpd.getDisplayRemindType();
																rules.policyDetailValue = ddpd.getPolicyDetailValue();
																model.rules.add(rules);
															}
														}
													}
												}
											}
										}// 火车票、酒店
									}
							}
							tlist.add(travller);
						//--------------policyId if  end---------------------------	
						if(tlist.size() > 0){
							vo.maps = tlist;
						}
						if(ruleMaps != null && ruleMaps.size() > 0){
							vo.ticket.ruleMaps = ruleMaps;
						}
						
			}catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		@Override
		public PolicyByenterprise getPolicyByEnterprise(Request_GetRulesByEnterprise arg) {
			PolicyByenterprise vo = new PolicyByenterprise();
			TAcUser user = null;
			TAcOrg org = null;
			OrgPolicyManage opm = null;
			try{
				Long companyId = arg.enterpriseId.longValue();
				//查询政策开关
				opm = dao.queryForEntity(companyId, OrgPolicyManage.class);
				if(opm != null&& opm.getPolicyControl() == 1){
					TTmcPolicyBusiness ttpb = null;
					DomTicketModel dtm = null;
					IntlTicketModel itm = null;
					List<HATPolicyModel> list = new ArrayList<HATPolicyModel>();
					HATPolicyModel hat = null;
					List<RuleMaps> ruleMaps = null;
					List<RuleMaps> ruleIntlMaps = null;
					List<TicketRules> ticketRuleList = null;
					RuleMaps userMaps = null;
					for(Integer type : arg.businessTypes){
						ttpb = dao.queryForEntity(new TTmcPolicyBusinessId(companyId,type), TTmcPolicyBusiness.class);
						//---------------各业务开关------------------------------
						if(ttpb != null){
							if(ttpb.getId().getBussinesstype() == 1 ){
								dtm = new DomTicketModel();
								dtm.audit = ttpb.getOverproofaudit();
								dtm.book = ttpb.getOverproofbook();
								dtm.policyDefault = ttpb.getOverproofdefault();
								dtm.show = ttpb.getOverproofshow();
								dtm.state = ttpb.getPolicycontrol();
								vo.ticket = dtm;
								ruleMaps = new ArrayList<RuleMaps>();
								
							}
							if(ttpb.getId().getBussinesstype() == 2 ){
								itm = new IntlTicketModel();
								itm.audit = ttpb.getOverproofaudit();
								itm.book = ttpb.getOverproofbook();
								itm.policyDefault = ttpb.getOverproofdefault();
								itm.show = ttpb.getOverproofshow();
								itm.state = ttpb.getPolicycontrol();
								vo.intlTicket = itm;
								ruleIntlMaps = new ArrayList<RuleMaps>();
							}
							if(ttpb.getId().getBussinesstype() > 2 ){
								hat = new HATPolicyModel();
								hat.audit = ttpb.getOverproofaudit();
								hat.book = ttpb.getOverproofbook();
								hat.policyDefault = ttpb.getOverproofdefault();
								hat.show = ttpb.getOverproofshow();
								hat.state = ttpb.getPolicycontrol();
								hat.busnessType = ttpb.getId().getBussinesstype();
								hat.rules = new ArrayList<HATRules>();
								list.add(hat);
							}
						}
						if(list.size() > 0 ){
							vo.policy = list;
						}
					}
					List<TTmcPolicy> policyList = dao.queryForList("from TTmcPolicy t where t.enterpId ='"+arg.enterpriseId+"' and t.state != 3");
					if(policyList != null && policyList.size() > 0){
						List<Relations> relist = new ArrayList<Relations>();
						List<RelationRules> ruleList = null;
						TTmcTripRule tripRule = null;
						Relations  relation = null;
						RelationRules relationRule = null;
						for(TTmcPolicy ttp : policyList){
							relation = new Relations();
							relation.policyId = ttp.getPolicyId().intValue();
							relation.policyName = ttp.getPolicyName();
							List<TTmcTripRule> trip = dao.queryForList("from TTmcTripRule t where t.enterpId ='"+arg.enterpriseId+"' and t.ruleTypeId ='"+ttp.getPolicyId()+"' and t.ruleType != 4");
							if(trip != null && trip.size() > 0){
								tripRule = trip.get(0);
								ruleList = new ArrayList<RelationRules>();
								relation.type = tripRule.getOrgRuleType();
								if(tripRule.getOrgRule() != null && !"".equals(tripRule.getOrgRule())){
									String[] ruleIds = tripRule.getOrgRule().split(",");
									if(tripRule.getOrgRuleType() != 3){
										for(String ruleId : ruleIds){
											if(!"".equals(ruleId)){
												org = null;
												org = dao.queryForEntity(Long.parseLong(ruleId.toString()), TAcOrg.class);
												if(org != null){
													relationRule = new RelationRules();
													relationRule.id = org.getOrgid().intValue();
													relationRule.name = org.getOrgname();
													ruleList.add(relationRule);
												}
											}
										}
									}else{
										for(String ruleId : ruleIds){
											if(!"".equals(ruleId)){
												String sql="select distinct groupid,group_name from org_group where groupid ='"+ruleId+"' and orgid ='"+companyId+"' ";
												List<Object[]> groupIds = (List<Object[]>) dao.queryBySQL(sql, null);
												if(groupIds != null && groupIds.size() > 0){
													relationRule = new RelationRules();
													Object[] obj = groupIds.get(0);
													relationRule.id = Integer.parseInt(obj[0].toString());
													relationRule.name = obj[1].toString();
													ruleList.add(relationRule);
												}
											}
										}
									}
									relation.rules = ruleList;
								}
								relist.add(relation);
							}
							List<Travller> tlist = new ArrayList<Travller>();
							Travller travller = null;
							travller = new Travller();
							travller.traveller = defaultNum;
							String policyId = ttp.getPolicyId().toString();
							travller.id = Integer.parseInt(policyId);
							travller.name = ttp.getPolicyName();
							for(Integer type : arg.businessTypes){
								if(type == 1){ //国内机票
									if(ruleMaps != null){
										userMaps = new RuleMaps();
										userMaps.traveller = defaultNum;
										ticketRuleList = new ArrayList<TicketRules>();
										List tickets = dao.queryBySQL("select * from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
										if(tickets != null && tickets.size() > 0 ){
											TicketRules ticketRules = null;
											List<OrgPolicyTicket> optlist = dao.queryForList("from OrgPolicyTicket t where t.id.orgid = '"+companyId+"' and t.id.ruleid like '"+policyId+"%' ");
											if(optlist != null && optlist.size() > 0){
												for(OrgPolicyTicket opt : optlist){
													ticketRules = new TicketRules();
													ticketRules.id = opt.getId().getRuleid();
													ticketRules.audit = opt.getOverproofaudit();
													ticketRules.book = opt.getOverproofbook();
													ticketRules.show = opt.getOverproofshow();
													ticketRules.type = opt.getPolicyType();
													List<PolicyTicketRule> trlist = dao.queryForList("from PolicyTicketRule t where t.id.ruleid = '"+opt.getId().getRuleid()+"' ");
													List<TicketItems> items = null;
													if(trlist != null && trlist.size() > 0){
														items = new ArrayList<TicketItems>();
														TicketItems ti = null;
														for(PolicyTicketRule trVo : trlist){
															ti = new TicketItems();
															ti.flowId = trVo.getId().getFlowid();
															ti.type = trVo.getRuletype();
															ti.lowerLimit = trVo.getLowerLimit();
															ti.upperLimit = trVo.getUpperLimit();
															ti.rule = trVo.getRules();
															items.add(ti);
														}
														ticketRules.items = items;
													}
													ticketRuleList.add(ticketRules);
												}
											}
										}
										userMaps.rules = ticketRuleList;
										ruleMaps.add(userMaps);
									}
								}else if (type == 2){//国际机票
									if(ruleIntlMaps != null){
										userMaps = new RuleMaps();
										userMaps.traveller = defaultNum;
										ticketRuleList = new ArrayList<TicketRules>();
										List tickets = dao.queryBySQL("select * from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
										if(tickets != null && tickets.size() > 0 ){
											TicketRules ticketRules = null;
											List<PolicyIntlTicket> optlist = dao.queryForList("from PolicyIntlTicket t where t.id.orgid = '"+companyId+"' and t.id.ruleid like '"+policyId+"%' ");
											if(optlist != null && optlist.size() > 0){
												for(PolicyIntlTicket opt : optlist){
													ticketRules = new TicketRules();
													ticketRules.id = opt.getId().getRuleid().intValue();
													ticketRules.audit = opt.getOverproofaudit();
													ticketRules.book = opt.getOverproofbook();
													ticketRules.show = opt.getOverproofshow();
													ticketRules.type = opt.getPolicytype();
													List<PolicyIntlTicketRule> trlist = dao.queryForList("from PolicyIntlTicketRule t where t.id.ruleid = '"+opt.getId().getRuleid()+"' ");
													List<TicketItems> items = null;
													if(trlist != null && trlist.size() > 0){
														items = new ArrayList<TicketItems>();
														TicketItems ti = null;
														for(PolicyIntlTicketRule trVo : trlist){
															ti = new TicketItems();
															ti.flowId = trVo.getId().getFlowid();
															ti.type = trVo.getRuletype();
															ti.lowerLimit = trVo.getLowerlimit();
															ti.upperLimit = trVo.getUpperlimit();
															ti.rule = trVo.getRules();
															items.add(ti);
														}
														ticketRules.items = items;
													}
													ticketRuleList.add(ticketRules);
												}
											}
										}
										userMaps.rules = ticketRuleList;
										ruleIntlMaps.add(userMaps);
									}
								}else if (type == 3 || type == 5){//火车票、酒店
									
									if(vo.policy != null && vo.policy.size() > 0){
										for(HATPolicyModel model : vo.policy){
											//查询政策， 后期增加有效期，在这里添加
											if(model.busnessType == type ){
													TTmcPolicyDetail  ddpd = null;
													HATRules rules = null;
													String flowid = null;
													List flowids = dao.queryBySQL("select flowid from T_TMC_POLICY_RELATION where policy_id = '"+policyId+"' and business_type = '"+type+"' and state = 1 ", null);
													flowid = getString(flowids);
													if(flowid != null && !"".equals(flowid)){
														ddpd = dao.queryForEntity(new TTmcPolicyDetailId(Long.parseLong(policyId),Long.parseLong(flowid)), TTmcPolicyDetail.class);
														if(ddpd != null){
															rules = new HATRules();
															rules.policyId = ttp.getPolicyId();
															rules.policyName = ttp.getPolicyName();
															rules.traveller = defaultNum;
															rules.bookingRemindType = ddpd.getBookingRemindType();
															rules.auditRemindType = ddpd.getAuditRemindType();
															rules.displayRemindType = ddpd.getDisplayRemindType();
															rules.policyDetailValue = ddpd.getPolicyDetailValue();
															model.rules.add(rules);
														}
													}
												}
											}
										}
									}// 火车票、酒店
							}
							tlist.add(travller);
							//--------------policyId if  end---------------------------	
							if(tlist.size() > 0){
								vo.maps = tlist;
							}
							if(ruleMaps != null && ruleMaps.size() > 0){
								vo.ticket.ruleMaps = ruleMaps;
							}
						}
						vo.relations = relist;
					}
					
				}else{
					vo = new PolicyByenterprise();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return vo;
		}
		@Override
		public List<CityLevelModel> getCustomerCityGrade(
				Request_GetCustomerCityGrade arg) {
			CityLevelModel data = null;
			List<CityLevelModel> clist = new ArrayList<CityLevelModel>();
			try{
				List<TTmcEnterpAreaLv> list = null;
					list = dao.queryForList("from TTmcEnterpAreaLv t where t.enterpId ='"+arg.enterpriseId+"' and  t.delFlag=1");
					if(list != null && list.size() > 0){
						for(TTmcEnterpAreaLv vo : list){
							data = new CityLevelModel();
							data.id = Integer.parseInt(vo.getLineLv());
							data.name = vo.getLineLvName();
							clist.add(data);
						}
						
					}
			}catch(Exception e ){
				e.printStackTrace();
				data = new CityLevelModel();
			}
			return clist;
		}
}
