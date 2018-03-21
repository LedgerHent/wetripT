package com.viptrip.wetrip.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxpt.entity.Message;
import com.dxpt.service.MessageService;
import com.dxpt.service.impl.MessageServiceImpl;
import com.viptrip.register.common.UserDefaultInfo;
import com.viptrip.util.DateUtil;
import com.viptrip.util.EnumUtil;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.ext.GetSmsCheckCodeDaoExt;
import com.viptrip.wetrip.entity.SmsCheckCode;
import com.viptrip.wetrip.entity.SmsCheckCodeId;
import com.viptrip.wetrip.model.Response_GetSmsCheckCode;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;

@Service
@Transactional
public class SmsCheckCodeService {

	@Resource(name="GetSmsCheckCodeDao")
	private GetSmsCheckCodeDaoExt dao;

	/**
	 * 查询用户绑定信息表中是否有手机号相关记录，且返回记录的绑定状态
	 * @param data
	 * @return 返回绑定状态，没有记录的返回空
	 */
	public Integer checkMobelBind(MobileAndCheckCode data) {
		String hql = "select bindState from Userbinding where mobile=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(data.mobile);
		Integer status=null;
		try {
			status=dao.queryForInt(hql,parm.toArray());//如报错增加Long方法
			
		} catch (NoResultException e) {
			status = null;//没有用户记录
		} catch (NonUniqueResultException e1) {
			status = -1;//有多条记录
		}
		
		return status;
	}

	/**
	 * 没有绑定记录的短信验证码发送，包含用户注册，用户绑定，充值密码等。
	 * @param data
	 * @param smsType  1-用户绑定|2-用户解绑|3-用户注册|4-密码重置|5-敏感信息修改
	 * @return
	 */
	public OutputResult<Response_GetSmsCheckCode, String> sendExceptBindSmsCode(MobileAndCheckCode data ,int smsType) {
		OutputResult<Response_GetSmsCheckCode, String> or = new OutputResult<Response_GetSmsCheckCode, String>();
		
		String status = checkUserHas(data);
		if ("-1".equals(status)) {
			if(3==smsType){//用户注册,发送验证码
				or = saveCheckCode2Sms(data,smsType);
			}else or.result = "手机号不存在系统中，请与客服联系！";
		} else if ("more".equals(status)) {
			// 查出多条记录，提示与客服联系
			or.result = "出现多条手机号，请与客服联系！";
		} else if ("4".equals(status)) {
			or.result = "该用户状态为停用，请与客服联系！";
		} else {
			if(3==smsType){
				or.result = "手机号已存在系统中，请直接登录！";
			}else{
				// 发送验证码,先保存验证码信息，在发短信
				or = saveCheckCode2Sms(data,smsType);
			}
		}
		return or;
	}
	
	/**
	 * 从用户表查手机号的用户的状态
	 * @param data
	 * @return 用户的状态信息
	 */
	public String checkUserHas(MobileAndCheckCode data) {
		String  hql ="select status from TAcUser where phone=? or loginname=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(data.mobile);
		parm.add(data.mobile);
		String status = null;
		try {
			Object obj=dao.queryForObject(hql,parm.toArray());
			if(obj==null) status = "-1";//没有用户记录
			else status=String.valueOf(obj);
		}  catch (NoResultException e) {
			status = "-1";//没有用户记录
		}catch (NonUniqueResultException e1) {
			status = "more";//有多条记录
		}
		
		return status;
	}

	/**
	 * 发送验证码,先保存验证码信息，在发短信
	 * @param data
	 * @return
	 */
	public OutputResult<Response_GetSmsCheckCode, String> saveCheckCode2Sms(
			MobileAndCheckCode data,int smsType) {
		OutputResult<Response_GetSmsCheckCode, String> or = new OutputResult<Response_GetSmsCheckCode, String>();
		
		//根据手机号获取当前用户的ID
		Long userId = queryUser(data.mobile);
		if(userId==-1){
			if(3==smsType){//用户注册,发送验证码
				or = sendSms(data,userId,smsType);//发送短信验证码,保存短信验证码记录。
			}else or.result="手机号不存在，请联系客服。";
			
		}else if(userId==-2){
			or.result="手机号码存在多条，请与客服联系。";
		}else{
			if(3==smsType){//用户注册,发送验证码
				or.result="手机号已存在，请直接登录。";
			}else{
				//发送短信验证码,保存短信验证码记录。
				or = sendSms(data,userId,smsType);
			}
			
		}
		return or;
	}

	/**
	 * 发送短信验证码,保存短信验证码记录。
	 * @param data
	 * @param userId
	 * @param smsType
	 * @return
	 */
	private OutputResult<Response_GetSmsCheckCode, String> sendSms(MobileAndCheckCode data, Long userId, int smsType) {
		
		OutputResult<Response_GetSmsCheckCode, String> or = new OutputResult<Response_GetSmsCheckCode, String>();
		/*
		 * 验证是否有恶意绑定： 根据同一个userID不能发送的限制条件加判断
		 * 恶意绑定检测标准：10分钟内达到5次发送短信记录，视为恶意发送短信
		 * 机器代码攻击标准：1分钟以内短信发送超过2次
		 */
		int count = queryCountTen(data,userId);
		if(count<5){
			count = queryCountOne(data,userId);
			if(count<=2){//短信发送
				String num6 = UserDefaultInfo.createRandom();//生成6位随机数
				if(sendSMS(data.mobile,num6,smsType)){
					//保存发送验证码记录，更新其他同手机号同类型的验证码state为4-已覆盖
					dao.executeUpdate("update SmsCheckCode set state=4 where id.mobile=? and smsType=?",
							new Object[]{data.mobile,Long.valueOf(smsType)});
					dao.executeSave(makeInfo(data,num6,userId,smsType));
					
					or.result="发送成功！";
					or.code = Constant.Code_Succeed;
				}else or.result="短信发送失败，请重新获取或联系";
				
			}else or.result="您发送短信的频率过于频繁，请稍后重试。";
		}else or.result="您发送短信的频率过于频繁，请稍后重试。";
		return or;
	}

	/**
	 * 10分钟内达到5次发送短信记录，视为恶意发送短信
	 * @param data
	 * @param userId 
	 * @return
	 */
	private int queryCountTen(MobileAndCheckCode data, Long userId) {
		//当前时间的10分钟前的时间
		String tenBefore = DateUtil.getString_AddMinutes(DateUtil.SDF_yyyy_MM_dd_HH_mm_ss, new Date(), 10);
		String hql = "select count(mobile) from SmsCheckCode where userid=? and to_char(sendTime,'"+DateUtil.DefaultDateTimeFormat4DB+"')>?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(userId);
		parm.add(tenBefore);
		
		return dao.queryForInt(hql, parm.toArray());
	}
	
	/**
	 * 1分钟以内短信发送超过2次
	 * @param data
	 * @param userId 
	 * @return
	 */
	private int queryCountOne(MobileAndCheckCode data, Long userId) {
		//当前时间的1分钟前的时间
		String tenBefore = DateUtil.getString_AddMinutes(DateUtil.SDF_yyyy_MM_dd_HH_mm_ss, new Date(), 1);
		String hql = "select count(mobile) from SmsCheckCode where userid=? and to_char(sendTime,'"+DateUtil.DefaultDateTimeFormat4DB+"')>?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(userId);
		parm.add(tenBefore);
		
		return dao.queryForInt(hql, parm.toArray());

	}

	/**
	 * 保存短信内容到短信实体中
	 * @param data
	 * @param num6 验证码
	 * @param userId
	 * @param smsType 短信验证码类型 int
	 * @return
	 */
	private SmsCheckCode makeInfo(MobileAndCheckCode data, String num6, Long userId ,int smsType) {
		SmsCheckCode sms = new SmsCheckCode();
		SmsCheckCodeId smsId = new SmsCheckCodeId();
		smsId.setMobile(data.mobile);
		smsId.setFlowid(queryMaxFlowID(data.mobile));//查询max流水号增1.
		sms.setId(smsId);//短信主键
		sms.setUserid(userId);//用户id
		sms.setSmsType(Long.valueOf(smsType));//短信验证码类型，1-用户绑定|2-用户解绑|3-用户注册|4-密码重置|5-敏感信息修改
		sms.setSmsCode(num6);//短信验证码
		sms.setSmsAgent(0l);//短信运营商，0-未知|1-维纳多|2-麦讯通
		sms.setExtend(null);

		sms.setSendTime(new Timestamp(new Date().getTime()));
		sms.setValidTime(new Timestamp(DateUtil.getDate_AddMinutes(new Date(), Config.SMS_CheckCodeValidMinutes).getTime()));
		
		sms.setSid(BigDecimal.valueOf(-1));//TODO 短信ID
		sms.setReportState(0l);
		sms.setState(1l);
		return sms;
	}

	/**
	 * 发送短信
	 * @param mobile
	 * @param num6
	 * @param smsType  1-用户绑定|2-用户解绑|3-用户注册|4-密码重置|5-敏感信息修改
	 */
	private boolean sendSMS(String mobile, String num6 ,int smsType) {
		MessageService ms = new MessageServiceImpl();
		Message msg = new Message();
		msg.setMobliephone(mobile);
		msg.setUser_key("【凯撒商旅】");
		String message = "";
		message = MessageFormat.format(Config.SMS_SendInfoModel,
				EnumUtil.SmsType.getEnum(smsType).toString(),num6,
				Config.SMS_CheckCodeValidMinutes,
				EnumUtil.SmsType.getEnum(smsType).toString());
		msg.setMessage(message);
		msg.setSendname("系统验证");
		msg.setUvalue(null);
		msg.setSendtype(MessageFormat.format("{0}短信验证",EnumUtil.SmsType.getEnum(smsType).toString()));
		boolean state = ms.saveMsg(msg);
		return state;
	}

	


	/**
	 * 查询max流水号
	 * @return
	 */
	private Long queryMaxFlowID(String mobile) {
		String hql = "select nvl(max(s.id.flowid),1) from SmsCheckCode s where s.id.mobile=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(mobile);
		Integer flowid = null;
		try {
			flowid = dao.queryForInt(hql,parm.toArray());
		}  catch (NoResultException e) {
			flowid = 1;//没有记录,计为 1 。
		}
		
		return Long.valueOf(flowid+1);
	}
	
	
	/**
	 * 根据用户手机号查询用户id
	 * @param data
	 * @return 用户的状态信息
	 */
	public Long queryUser(String mobile) {
		String  hql ="select userid from TAcUser where phone=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(mobile);
		Integer userid = null;
		try {
			userid=dao.queryForInt(hql,parm.toArray());
			
		}  catch (NoResultException e) {
			userid = -1;//没有用户记录
		}catch (NonUniqueResultException e1) {
			userid = -2;//有多条记录
		}
		
		return Long.valueOf(userid);
	}
	
}
