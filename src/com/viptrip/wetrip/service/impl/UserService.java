package com.viptrip.wetrip.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Service;

import com.viptrip.hotel.model.userinfo4hotel.UserInfo4Hotel;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.TAcUserDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.service.IUserService;
@Service
public class UserService implements IUserService{
	@Resource
	private ComDao cdao;
	@Resource
	private TAcUserDao dao;
	
	@Override
	public TAcUser getUserByUsername(String username) {
		TAcUser user = null;
		if(!StringUtil.isEmpty(username)){
			List<TAcUser> list = cdao.queryForList("from TAcUser where username='" + username + "'");
			if(null!=list&&list.size()>0){
				user = list.get(0);
			}
		}
		return user;
	}
	
	
	/**
	 * 根据登录名取用户
	 * 	登录名：身份证号-手机号-邮箱-护照号-用户id
	 * @param username
	 * @return
	 */
	public TAcUser getUserByLoginUsername(String username) {
		TAcUser user = null;
		if(!etuf.v1_0.common.Common.IsNullOrEmpty(username)){
			user = findUserByidcard(username);
			if(null==user)
				user = findUserByLoginName(username);
			if(null==user)
				user = findUserByPhone(username);
			if(null==user)
				user = findUserByEmail(username);
			if(null==user)
				user = findUserByExtf1(username);
			if(null==user)
				user = finduserById(username);
		}
		return user;
	}
	
	/**
	 * 根据登录名取用户信息
	 * @param extf1
	 * @return
	 */
	public TAcUser findUserByLoginName(String loginName){
		TAcUser user = null;
		List<TAcUser> list = cdao.queryForList("from TAcUser where loginname='" + loginName + "'");
		if(null!=list&&list.size()>0){
			user = list.get(0);
		}
		return user;
	}
	
	/**
	 * 根据ID取用户信息
	 * @param userId
	 * @return
	 */
	public TAcUser finduserById(String userId){
		TAcUser user = null;
		if(!etuf.v1_0.common.Common.IsNullOrEmpty(userId)){
		List<TAcUser> list = cdao.queryForList("from TAcUser where where userid='" + userId + "'");
		if(null!=list&&list.size()>0){
			user = list.get(0);
		}}
		return user;
	}
	
	/**
	 * 根据邮箱取用户信息
	 * @return
	 */
	public TAcUser findUserByEmail(String email){
		TAcUser user = null;
		// 定义邮箱验证正则表达式
		String check = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		// 将给定的正则表达式编译到模式中
		Pattern regex = Pattern.compile(check);
		// 创建匹配给定输入与此模式的匹配器
		Matcher matcher = regex.matcher(email);
		// 尝试将整个区域与模式匹配（如果匹配成功，返回true）
		
		if(matcher.matches()){
			List<TAcUser> list = cdao.queryForList("from TAcUser where email='" + email + "'");
			if(null!=list&&list.size()>0){
				user = list.get(0);
			}
		}
		return user;
		
	}
	
	/**
	 * 根据电话号码取用户信息
	 * @return
	 */
	public TAcUser findUserByPhone(String phone){
		// 定义手机验证正则表达式
		//String check = PropertiesUtil.getResourcesProperty("regex_mobile");//"^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		// 将给定的正则表达式编译到模式中
		Pattern regex = Pattern.compile("^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$");
//		Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		// 创建匹配给定输入与此模式的匹配器
		Matcher matcher = regex.matcher(phone);
		// 尝试将整个区域与模式匹配（如果匹配成功，返回true）
		
		TAcUser user = null;
		if(matcher.matches()){
			List<TAcUser> list = cdao.queryForList("from TAcUser where phone='" + phone + "'");
			if(null!=list&&list.size()>0){
				user = list.get(0);
			}
		}
		return user;
		
	}
	
	/**
	 * 根据身份证取用户信息
	 * @return
	 */
	public TAcUser findUserByidcard(String idcard){
		// 定义手机验证正则表达式
		String check = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";
		// 将给定的正则表达式编译到模式中
		Pattern regex = Pattern.compile(check);
		// 创建匹配给定输入与此模式的匹配器
		Matcher matcher = regex.matcher(idcard);
		// 尝试将整个区域与模式匹配（如果匹配成功，返回true）
		TAcUser user = null;
		if(matcher.matches()){
			List<TAcUser> list = cdao.queryForList("from TAcUser where idcard='" + idcard + "'");
			if(null!=list&&list.size()>0){
				user = list.get(0);
			}
		}
		return user;
		
	}
	
	
	
	/**
	 * 根据护照取用户信息
	 * @param extf1
	 * @return
	 */
	public TAcUser findUserByExtf1(String extf1){
		TAcUser user = null;
		List<TAcUser> list = cdao.queryForList("from TAcUser where extf1='" + extf1 + "'");
		if(null!=list&&list.size()>0){
			user = list.get(0);
		}
		return user;
		
		
	}
	
	
	public TAcUser getUserById(Long id) {
		TAcUser user = null;
		if(null!=id && id>0){
			user = dao.findOne(id);
		}
		return user;
	}
	
	/**
	 * 根据凯撒系统userID获取彩云平台uID
	 * 
	 * @return uid 正整数：正常平台uId，null：平台ID不存在，-1：有多条记录，
	 */
	public String getUban360UId(Long userId){
		
		String uid = getPlatformUid(userId,1l);//TODO Uban360平台ID为1。
		
		return uid;
	}

	/**
	 * 根据平台ID和系统用户ID查询平台用户ID
	 * @param userId
	 * @param platformId
	 */
	public String getPlatformUid(Long userId, Long platformId) {
		String hql = "select id.platformUserId from PlatformUserMap where id.pid=? and id.userId=?";
		List<Long> parm = new ArrayList<Long>();
		
		//平台id，默认设置为Uban360即彩云平台id，若不是，重新查询
		if(platformId!=1l){
			platformId = getPlatformIdByUserId(userId);
			if(platformId==null) platformId = -1l;
		}
		parm.add(platformId);
		parm.add(userId);
		
		String uid = null;
		try {
			uid = (String) cdao.queryForObject(hql, parm.toArray());
		} catch (NoResultException e) {
			uid = "0";
		} catch (NonUniqueResultException e) {
			uid = "-1";
		}
			
		
		return uid;
	}
	
	
	/**
	 * 根据凯撒用户ID确认用户是否是平台用户
	 * 
	 */
	public boolean isPlatFormOrg(Long userId){
		Long platformid = getPlatformIdByUserId(userId);
		if(null!=platformid&&platformid>=0){
			return true;
		}else return false;
	}

	/**
	 * 根据凯撒userID获取平台ID,平台为正常使用的状态。
	 * @return platformId 正整数：正常平台ID，null：平台ID不存在，-1：有多条记录，
	 */
	public Long getPlatformIdByUserId(Long userId){
		Long platformId = null;
		String hql = "select p.pid from Platform p where p.orgid=" +
				"(select u.orgid from TAcUser u where u.userid=?) and p.status=1";
		try {
			platformId = Long.valueOf(cdao.queryForInt(hql, new Object[]{userId}));
			
		} catch (NoResultException e) {
			platformId = null;
		} catch (NonUniqueResultException e1) {
			platformId = -1l;//有多条记录
		}
		
		return platformId;
	}


	@SuppressWarnings("unchecked")
	@Override
	public UserInfo4Hotel getUserInfo4Hotel(long userid) {
		UserInfo4Hotel info = new UserInfo4Hotel();
		 Object[] param = new Object[]{userid};
		String sql = "";
		       sql += "select " +
		       		" t.userid,t.username,t.usertype,tao.orgid,tao.orgname,tao.company,tar.rolename,tao2.orgname as companyname,grp.groupid from " +
		       		" t_ac_user t  left join t_ac_org tao  on t.orgid=tao.orgid " +
		       		" left join t_ac_userrole tau on t.userid = tau.userid " +
		       		" left join t_ac_role tar on tau.roleid = tar.roleid " +
		       		" left join t_ac_org tao2 on tao.company=tao2.orgid" +
		       	    " left join (select ogr.groupid,og.group_name,ogr.id from  org_group_relation ogr "+
					" inner join org_group og on ogr.orgid=og.orgid and ogr.groupid = og. groupid ) grp"+
					" on t.userid = grp.id or t.orgid = grp.id"+
		       		" where t.userid= ?";
		    List list = cdao.queryBySQL(sql, param);
		    if(list!=null&&list.size()>0){
		    	Object[] obj = (Object[]) list.get(0);
		    	info.userId = Integer.parseInt(obj[0].toString());
		    	info.userName = obj[1].toString();
		    	info.departmentId = Integer.parseInt(obj[3].toString());
		    	if(info.departmentId == -1)
		    		info.userType = 1;
		    	else if(info.departmentId == 121 || info.departmentId == 2443)
		    		info.userType = 3;
		    	else
		    		info.userType = 2;
		    	info.departmentName = obj[4].toString();
		    	info.enterpriseId  = Integer.parseInt(obj[5].toString());
		    	info.role = obj[6].toString();
		    	info.enterpriseName = obj[7].toString();
		    	info.groupId = Integer.parseInt(obj[8]==null?"-1":obj[8].toString());
		    }
		    
		return info;
	}


	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public Boolean isMenu(Long userid,Integer menuId) {
		boolean b = false;
		 Object[] param = new Object[]{userid};
		String sql = "select nr.menuid from " +
				" t_ac_userrole t left join " +
				" t_ac_new_roleright nr on t.roleid=nr.roleid and userId = ?";
		  List list = cdao.queryBySQL(sql, param);
		  if(list != null && list.size() > 0 ){
			  String lis = list.toString();
			  if(lis.indexOf(menuId.toString()) > -1){
				  b = true;
			  }
		  }
		return b;
		 
	}

}
