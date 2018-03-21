package com.viptrip.register.service;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dxpt.entity.Message;
import com.dxpt.service.MessageService;
import com.dxpt.service.impl.MessageServiceImpl;
import com.viptrip.base.common.Config;
import com.viptrip.register.common.UserDefaultInfo;
import com.viptrip.register.model.RegisterUserInfoModel;
import com.viptrip.util.DateUtil;
import com.viptrip.util.IDCardUtil;
import com.viptrip.util.RegUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

@Service
@Transactional
public class UserRegister {
	@Resource
	private  ComDao dao;

	/**
	 * 保存用户注册信息及关联角色信息
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public  OutputResult<TAcUser,String> saveUser4Register(RegisterUserInfoModel user) {
		OutputSimpleResult osr = new OutputSimpleResult();
		OutputResult<TAcUser,String> or = new OutputResult<TAcUser,String>();

		//去除用户信息中两端空格
		user = removeSpace4BothEnds(user);
		
		// 校验用户信息合法性
		osr = checkUserInfo(user);
		if (osr.IsSucceed()) {
			TAcUser tUser = new TAcUser();
			// 判断用户是否已存在系统中
			osr = hasUserCheck(user);
//			System.out.println("*****************uId:"+ uId);
			// 保存用户信息及角色信息
			if (osr.IsSucceed()) {
				String pwd = user.getPwd()==null?"":user.getPwd();
				if(Common.IsNullOrEmpty(pwd)){//密码为空，生成用户密码 
					pwd = UserDefaultInfo.createPwd(user.getIdcard(), false);
					tUser.setPassword(DigestUtils.md5Hex(pwd));//加密密码
				}
				
				tUser = saveUserInfo(tUser, user, true);
				dao.executeSave(tUser);// 保存用户信息
				dao.executeUpdate(UserDefaultInfo.saveUserRole(tUser
						.getUserid()));// 保存用户角色
				if(user.getOrgid()!=7960){//杭州讯盟平台不发短信
					//发送短信
					or = sendSMS(user.getPhone(), pwd);
				}
				
				or.code=Constant.Code_Succeed;
				or.setResultObj(tUser);
			} else {
				or.result=osr.result;
			}

		}else{
			or.result=osr.result;
		}

		return or;
	}

	private RegisterUserInfoModel removeSpace4BothEnds( RegisterUserInfoModel user) {
		if(!Common.IsNullOrEmpty(user.getUsername())){
			user.setUsername(user.getUsername().trim());
		}if(!Common.IsNullOrEmpty(user.getPhone())){
			user.setPhone(user.getPhone().trim());
		}if(!Common.IsNullOrEmpty(user.getEmail())){
			user.setEmail(user.getEmail().trim());
		}if(!Common.IsNullOrEmpty(user.getIdcard())){
			user.setIdcard(user.getIdcard().trim());
		}if(!Common.IsNullOrEmpty(user.getPassport())){
			user.setPassport(user.getPassport().trim());
			if(!Common.IsNullOrEmpty(user.getPassportEn())){
				user.setPassportEn(user.getPassportEn().trim());
			}
		}
		return user;
	}

	/**
	 * 用户是否存在校验
	 * 
	 * @param user
	 * @return
	 */
	private  OutputSimpleResult hasUserCheck(RegisterUserInfoModel user) {
		OutputSimpleResult out = new OutputSimpleResult();
		List<Object> parm = new ArrayList<Object>();
		String filter = "";
		if (!Common.IsNullOrEmpty(user.getPhone())) {
			if (!Common.IsNullOrEmpty(filter)) {
				filter += " and ";
			}
			filter += " phone=? ";
			parm.add(user.getPhone());
		}
		if (!Common.IsNullOrEmpty(user.getEmail())) {
			if (!Common.IsNullOrEmpty(filter)) {
				filter += " or ";
			}
			filter += " email=? ";
			parm.add(user.getEmail());
		}
		if (!Common.IsNullOrEmpty(user.getIdcard())) {
			if (!Common.IsNullOrEmpty(filter)) {
				filter += " or ";
			}
			filter += " idcard=? ";
			parm.add(user.getIdcard());
		}
		if (!Common.IsNullOrEmpty(user.getPassport())) {
			if (!Common.IsNullOrEmpty(filter)) {
				filter += " or ";
			}
			filter += " passport=? ";
			parm.add(user.getPassport());
		}
		String hql = "select u from TAcUser u";
		if (parm.size() > 0) {
			hql += " where " + filter;
		}

		try {
			List<TAcUser> list = dao.queryForList(hql, parm.toArray());
			if (list != null && list.size() > 0) {
				if (list.size() == 1) {
					TAcUser u = list.get(0);
					if(!Common.IsNullOrEmpty(user.getPhone()) && user.getPhone().equals(u.getPhone())){
						out.result = MessageFormat.format("此手机号已注册，请直接登录。" +
								"如无法正常登录，请致电凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a>",
								Config.customerHotService);
					}else if(!Common.IsNullOrEmpty(user.getEmail()) && user.getEmail().equals(u.getEmail())){
						out.result = MessageFormat.format("此邮箱已注册，请直接登录。" +
								"如无法正常登录，请致电凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a>",
								Config.customerHotService);
					}else if(!Common.IsNullOrEmpty(user.getIdcard()) && u.getIdcard().equals(user.getIdcard())){
						out.result = MessageFormat.format("此身份证号已注册，请直接登录。" +
								"如无法正常登录，请致电凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a>",
								Config.customerHotService);
					}else if(!Common.IsNullOrEmpty(user.getPassport()) && u.getPassport().equals(user.getPassport())){
						out.result = MessageFormat.format("此护照号已注册，请直接登录。" +
								"如无法正常登录，请致电凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a>",
								Config.customerHotService);
					}else{
						out.result = MessageFormat.format("注册信息验证是否重复发生未知错误。" +
								"如无法正常登录，请致电凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a>",
								Config.customerHotService);
					}
				} else if (list.size() > 1) {
					out.result = MessageFormat.format( "系统检测到多个可能重复的用户信息，" +
							"请拨打凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a> 联系客服处理。",
							Config.customerHotService);
				}
			} else out.code = Constant.Code_Succeed;
		} catch (Exception e2) {
			out.result = MessageFormat.format( "查询用户信息发生未知错误，" +
					"请拨打凯撒商旅24小时客服热线 <a href=\"tel:{0}\">{0}</a> 联系客服。",
					Config.customerHotService);
		}
		return out;
	}

	/**
	 * 初始用户信息
	 * @param tUser 系统用户
	 * @param user 注册信息
	 * @param newUser 是否是新用户
	 * @return
	 */
	private  TAcUser saveUserInfo(TAcUser tUser, RegisterUserInfoModel user,
			boolean newUser) {
		if (newUser) {
			tUser = UserDefaultInfo.saveDefaultInfo(tUser);// 基本默认信息
		}
		tUser.setUsername(user.getUsername());
		tUser.setEmail(user.getEmail());
		tUser.setPhone(user.getPhone());
		tUser.setOrgid(user.getOrgid());
		tUser.setLoginname(user.getPhone());//登录账户
		if (Common.IsNullOrEmpty(tUser.getPassword())){
			tUser.setPassword(DigestUtils.md5Hex(user.getPwd()));//加密密码
		}
		//身份证
		if (!Common.IsNullOrEmpty(user.getIdcard())){
			tUser.setIdcard(user.getIdcard());
		}
		//护照，及护照相关信息
		if (!Common.IsNullOrEmpty(user.getPassport())){
			tUser.setPassport(user.getPassport());
			tUser.setNationalityTwo(user.getNationalityTwo());
			tUser.setPassportEn(user.getPassportEn());
			Date parse;
			try {
				parse = DateUtil.parse(user.getPassportEnd(),DateUtil.P_yyyyOMMOdd);
				tUser.setPassportEnd(parse);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return tUser;
	}

	/**
	 * 校验用户信息准确性
	 * @param user
	 * @param out
	 * @param doCheck 是否校验,企业客户-全部；散客或第三方-手机号、姓名，其他校验正确性
	 * @return
	 */
	private  OutputSimpleResult checkUserInfo(RegisterUserInfoModel user) {
		OutputSimpleResult out = new OutputSimpleResult();
		if (user != null) {
			boolean isEnterpriceRegister = check是否需要全部判断(user.getOrgid());
					
			if (!Common.IsNullOrEmpty(user.getUsername())) {
				if (!Common.IsNullOrEmpty(user.getPhone())) {
					if (RegUtil.isValidMobile(user.getPhone())) {
						boolean flag = true;
						if (isEnterpriceRegister
								|| !Common.IsNullOrEmpty(user.getEmail())) {
							if (!RegUtil.isValidEmail(user.getEmail())) {
								flag = false;
								out.result = "无效的用户邮箱，请确认后重试。";
							}
						}
						if (flag && isEnterpriceRegister
								&& Common.IsNullOrEmpty(user.getIdcard())
								&& Common.IsNullOrEmpty(user.getPassport())) {
							flag = false;
							out.result = "身份证和护照不能同时为空。";
						}
						if (flag && !Common.IsNullOrEmpty(user.getIdcard())) {
							if (!IDCardUtil.isIDCard(user.getIdcard())) {
								flag = false;
								out.result = "无效的用户身份证号码，请确认后重试。";
							}
						}
						if (flag && !Common.IsNullOrEmpty(user.getPassport())) {
							if (user.getPassport().length() < 5 || user.getPassport().length() > 10) {
								flag = false;
								out.result = "无效的用户护照号码，请确认后重试。";
							}
							//校验护照相关信息
							if(Common.IsNullOrEmpty(user.getNationalityTwo())){
								flag = false;
								out.result = "用户护照国籍不能为空，请确认后重试。";
							}
							if(Common.IsNullOrEmpty(user.getPassportEn())){
								flag = false;
								out.result = "用户护照有效期结束日期不能为空，请确认后重试。";
							}
							if(Common.IsNullOrEmpty(user.getPassportEn())){
								flag = false;
								out.result = "用户护照英文名不能为空，请确认后重试。";
							}
						}
						if (flag) {
							out.code = Constant.Code_Succeed;
							
						}
					} else
						out.result = "无效的用户手机号，请确认后重试。";
				} else
					out.result = "用户手机号不能为空。";
			} else
				out.result = "用户姓名不能为空。";
		} else
			out.result = "用户信息为空，不予处理。";

		return out;
	}

	private boolean checkIsPlatform(Long orgid) {
		boolean isPlat = false;
		if(orgid != null&& orgid>0){
			String hql = "select pid from Platform where orgid=?";
			List<Long> list = dao.queryForList(hql, new Object[]{orgid});
			if(list!=null&&list.size()>0){
				isPlat = true;
			}
		}
		
		return isPlat;
	}
	private boolean check是否需要全部判断(Long orgid) {
		if(orgid != null&& orgid>0)
			return !checkIsPlatform(orgid);
		return false;
	}

	/**
	 * 校验机构信息，合法且上线
	 * 
	 * @param orgid
	 * @return
	 */
	private boolean checkOrg(Long orgid) {
		boolean status = true;
		if (orgid != -1) {
			TAcOrg org = new TAcOrg();
			try {
				org = dao.queryForEntity(orgid, TAcOrg.class);
				if (org != null && "1".equals(org.getStatus()))// 企业存在且为启用状态
					status = true;
				else
					status = false;

			} catch (Exception e) {
				status = false;
			}
		}
		return status;
	}
	
	/**
	 * 发送短信
	 * @param mobile
	 * @param num6  sendInfoModelo
	 */
	private OutputResult<TAcUser, String> sendSMS(String mobile, String num6) {
		OutputResult<TAcUser,String> or = new OutputResult<TAcUser,String>();
		MessageService ms = new MessageServiceImpl();
		Message msg = new Message();
		msg.setMobliephone(mobile);
		msg.setUser_key("【凯撒商旅】");
		msg.setMessage(MessageFormat.format("您已注册成功，初始密码为{0}，请尽快登录凯撒商旅系统进行修改。", num6));
		msg.setSendname("用户密码");
		msg.setUvalue(null);
		msg.setSendtype("用户默认注册密码");
		
		if(ms.saveMsg(msg)){
			or.code = Constant.Code_Succeed;
		}else{
			or.result = MessageFormat.format("默认密码短信发送失败。您已经注册成功，请拨打热线电话 {0} 联系客服获取登录密码。",
					Config.customerHotService);
		}
		return or;
	}
}
