package com.viptrip.wetrip.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.common.Config;
import com.viptrip.base.common.MyEnum;
import com.viptrip.register.model.RegisterUserInfoModel;
import com.viptrip.register.service.UserRegister;
import com.viptrip.wetrip.action.H5AvoidLoginAction;
import com.viptrip.wetrip.dao.ext.H5UserLoginDaoExt;
import com.viptrip.wetrip.entity.Platform;
import com.viptrip.wetrip.entity.PlatformUserMap;
import com.viptrip.wetrip.entity.PlatformUserMapKeys;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Response_H5UserLogin;
import com.viptrip.wetrip.model.base.UserInfo4Bind;
import com.viptrip.wetrip.model.userlogin.LoginMessage;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;

@Service
@Transactional
public class H5UserLoginServiceImpl {
	@Resource
	private UserRegister userRegister;

	@Resource
	private H5UserLoginDaoExt dao;
	private static Logger logger = LoggerFactory.getLogger(H5AvoidLoginAction.class);
	/**
	 * 验证平台是否已维护，且状态为正常的，若没有返回false
	 * @param platformId
	 * @return
	 */
	public boolean checkHasPlatformId(Long platformId) {
		//是否是维护平台，且状态为正常的，不是返回false
		String hqlUser="select pid from Platform where pid=? and status=1 ";
		List<Object> parmKs = new ArrayList<Object>();
		parmKs.add(platformId);//平台ID
		Integer userIdOk = dao.queryForInt(hqlUser, parmKs);
		
		return userIdOk==null?false:true;
	}

	/**
	 * 根据合作方提供的信息，注册或者登录用户账户前的绑定操作：
	 * 平台用户是否存在系统中，没有增加user表，平台用户是否关联,如果未关联，添加关联
	 * @param data
	 * @param or
	 * @param 
	 * @return 返回 UserInfo4Bind
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public UserInfo4Bind doUserLoginMap(LoginMessage data, OutputResult<Response_H5UserLogin, String> or) {
		
		TAcUser tUser = null;
		
		//关联关系表是否有记录，取出用户ID
		Integer userIdOk = queryUidFromPlatf(data);
		
		if(userIdOk!=null){//用户存在于平台关联信息
			if(userIdOk==-1){
				or.result =  "出现多条关联信息。";
				return null;
			}else{
				//获取用户信息
				tUser = dao.queryEntityById(Long.valueOf(userIdOk),TAcUser.class);
				if(logger.isDebugEnabled())
					logger.debug("H5UserLoginServiceImpl.doUserLoginMap()==UserPhone&mobile==" + tUser.getPhone()+",=="+data.user.mobile);
				if(tUser!=null){
					//用户不可登录
					if(!("0".equals(tUser.getStatus())||"1".equals(tUser.getStatus()))){
						or.result = "用户状态为不允许登录系统。";
						return null;
					}
					
					//用户登录信息与原有关联信息不对应
					if(!tUser.getPhone().equals(data.user.mobile)){
						or.result = "用户登录信息与原有关联信息不对应。";
						return null;
					}
					
				}else{
					or.result = "用户信息不存在。";
					return null;
				}
			}
			
		}else{//T_AC_User是否有用户信息
			Integer userIdHas = queryUidFromUser(data);
			if(userIdHas!=null){//用户存在于用户信息表中
				if(userIdHas==-1){
					or.result = "该手机号已经绑定多个用户。";
					return null;
				}else{
					//获取用户信息
					tUser = dao.queryEntityById(Long.valueOf(userIdHas),TAcUser.class);
					Platform pf = new Platform();
					pf = dao.queryForEntity(data.platformId,Platform.class);
					if(pf.getOrgid().longValue()!=tUser.getOrgid().longValue()){
						or.result = "该手机号已经绑定不同用户。" ;
						return null;
					}
					//用户不可登录
					if(!("0".equals(tUser.getStatus())||"1".equals(tUser.getStatus()))){
						or.result = "用户状态为不允许登录系统。" ;
						return null;
					}
				}
				
			}else{//新建用户信息
				RegisterUserInfoModel uModel = new RegisterUserInfoModel();
				uModel = queryUserInfo(uModel,data);
				OutputResult<TAcUser, String> outUser  = userRegister.saveUser4Register(uModel);//用户注册
				if(!outUser.IsSucceed()){
					or.result = outUser.result;
					return null;
				}else{
					tUser = outUser.getResultObj();
				}
			}
			
			//保存平台用户关联信息表
			if(userIdHas!=null && idInPlatformUserMap(userIdHas)){//用户信息不存在PlatformUserMap关联表中
				or.result = "当前手机号已绑定到其他帐户。" ;
				return null;
			}else{
				PlatformUserMap pfu = savePlatformUserMap(data,tUser.getUserid());
				dao.update(pfu);
			}
		}
		
		//保存返回信息
		UserInfo4Bind userbind = new UserInfo4Bind();
		userbind = queryUserBind(userbind,tUser);
		
		or.code=Constant.Code_Succeed;

		return userbind;
		
		
	}

	/**
	 * 判断用户信息是否存在PlatformUserMap关联表中
	 * @param userIdHas
	 * @return
	 */
	private boolean idInPlatformUserMap(Integer userIdHas) {
		boolean exist = true;
		String hql = "from PlatformUserMap where id.userId = ? ";
		try {
			List<Long> list = dao.queryForList(hql, new Object[]{userIdHas});
			if (list == null || list.size() <= 0) {
				exist = false;
			}
		} catch (Exception e) {
			exist = true;// 报错
		}
		return exist;
	}

	/**
	 * 关联关系表是否有记录，取出用户ID
	 * @param data
	 * @return
	 */
	private Integer queryUidFromPlatf(LoginMessage data) {
		String hqlpf="select id.userId from PlatformUserMap where id.pid=? and id.platformUserId=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(data.platformId);//平台ID
		parm.add(data.user.uid);//平台用户ID
		return dao.queryForInt(hqlpf, parm);
	}
	
	/**
	 * 从系统用户表查询是否有用户信息，根据用户姓名和手机号查询
	 * @param data
	 * @return
	 */
	private Integer queryUidFromUser(LoginMessage data) {
//		String hqlUser="select u.userid from TAcUser u where u.username=? and u.phone=? and u.status is not null";
		String hqlUser="select u.userid from TAcUser u where u.phone=? and u.status is not null";
		List<Object> parmKs = new ArrayList<Object>();
//		parmKs.add(data.user.name);//用户姓名
		parmKs.add(data.user.mobile);//用户手机号
		return dao.queryForInt(hqlUser, parmKs);
	}

	private UserInfo4Bind queryUserBind(UserInfo4Bind userbind, TAcUser tUser) {
		userbind.userId=tUser.getUserid();//凯撒用户表用户id
		userbind.userName=tUser.getUsername();
		userbind.userType=tUser.getOrgid()==-1?1:2;//1=散客，2=企业用户
		TAcOrg org = dao.queryForEntity(tUser.getOrgid(), TAcOrg.class);
		userbind.enterpriseId=tUser.getOrgid()==-1l?-1l:Long.valueOf(org.getCompany());//散客为-1，企业客户为自然数(>0)
		
		return userbind;
	}

	/**
	 * 保存用户信息model
	 * @param data
	 * @param userid
	 * @return userModel
	 */
	private RegisterUserInfoModel queryUserInfo(RegisterUserInfoModel userInfo, LoginMessage data) {
		userInfo = new RegisterUserInfoModel();
		//根据平台ID查询平台关联的机构ID，保存到用户表中
		Platform pf = new Platform();
		pf = dao.queryForEntity(data.platformId,Platform.class);
		userInfo.setUsername(data.user.name);//姓名
		userInfo.setOrgid(pf.getOrgid());//机构id
		userInfo.setPhone(data.user.mobile);//手机号码
		userInfo.setEmail(data.user.email);
		if(data.user.idType!=0){//证件类型枚举（选填）	数字，1=二代身份证，2=护照
			if(data.user.idType==MyEnum.IdType.二代身份证.getValue()){
				userInfo.setIdcard(data.user.idNum);
			}else if(data.user.idType==MyEnum.IdType.护照.getValue()){
				userInfo.setPassport(data.user.idNum);
			}
		}
		
		return userInfo;
	}


	/**
	 * 保存平台用户关联信息表
	 * @param data
	 * @param userid
	 * @return PlatformUserMap
	 */
	private PlatformUserMap savePlatformUserMap(LoginMessage data, Long userid) {
		PlatformUserMap pfu = new PlatformUserMap();
		PlatformUserMapKeys pfuKey = new PlatformUserMapKeys();
		pfuKey.setPid(data.platformId);
		pfuKey.setPlatformUserId(data.user.uid);
		pfuKey.setUserId(userid);
		pfu.setId(pfuKey);
		pfu.setExtend(null);
		
		return pfu;
		
	}

}
