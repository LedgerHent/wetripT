package com.viptrip.wetrip.service.impl;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.common.MyEnum;
import com.viptrip.register.model.RegisterUserInfoModel;
import com.viptrip.register.service.UserRegister;
import com.viptrip.util.DateUtil;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.BindingRecord;
import com.viptrip.wetrip.entity.BindingRecordId;
import com.viptrip.wetrip.entity.SmsCheckCode;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.model.Request_BindUser;
import com.viptrip.wetrip.model.Request_UnbindUser;
import com.viptrip.wetrip.model.Response_UnbindUser;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;
import com.viptrip.wetrip.model.base.UserInfo4Bind;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

@Service
@Transactional
public class UserBindService {
	@Resource
	private UserRegister userRegister;
	@Resource
	private ComDao cDao;

	public OutputSimpleResult CheckSMSCodeValid(MobileAndCheckCode macc,int smsType) {
		OutputSimpleResult osr = new OutputSimpleResult();
//		String hql = MessageFormat.format(
//				"from SmsCheckCode where id.mobile={0}"
//						+ " and smsCode={1} and smsType={2} order by id.flowid desc",
//				macc.mobile, macc.checkCode,smsType);
		String hql = "from SmsCheckCode where id.mobile=?"
						+ " and smsCode=? and smsType=? order by id.flowid desc";
		List<Object> parm = new ArrayList<Object>();
		parm.add(macc.mobile);
		parm.add(macc.checkCode);
		parm.add(Long.valueOf(smsType));
		List<SmsCheckCode> list = cDao.queryForList(hql, parm.toArray());
		if (list != null && list.size() > 0) {
			SmsCheckCode sms = list.get(0);
			if (sms.getState() == 1l) {// long 判断相等，是这么判断吗？
				int state = 3;// 已过期，只要是有效期时间之外的，都认为是已过期
				Date now = new Date();
				if (sms.getSendTime().before(now)) {
					if (sms.getValidTime().after(now)) {
						state = 2;// 已使用
						osr.code = Constant.Code_Succeed;
					} else {
						osr.result = "短信验证码已超出时效，请重新获取。";
					}
				} else {
					osr.result = "无效的短信验证码。";
				}
				// 更新短信验证码状态
				sms.setState((long) state);
				cDao.executeSave(sms);
			} else {
				osr.result = "验证码已失效，请重新获取。";
			}
		} else {
			osr.result = "无效的短信验证码，请输入正确的短信验证码。";
		}
		return osr;
	}

	public OutputResult<TAcUser, String> GetOrAddUser(String mobile) {
		OutputResult<TAcUser, String> or = new OutputResult<>();
		TAcUser user = null;
		String hql="from TAcUser where phone=? and status is not null ";
		List<TAcUser> list = cDao.queryForList(hql,new Object[]{mobile});
		if (list != null && list.size() > 0) {
			if (list.size() == 1) {
				user = list.get(0);
				if ("4".equals(user.getStatus())) {// 4 停用
					or.result = "当前绑定用户已被停用，请联系客服进行解决。";
				} else {
					or.setResultObj(user);
					or.code = Constant.Code_Succeed;
				}
			} else {
				or.result = "系统检测到当前手机号已经绑定多个用户，请联系客服解决。";
			}
		} else {
			RegisterUserInfoModel uModel = new RegisterUserInfoModel();
			uModel.setPhone(mobile);
			or  = userRegister.saveUser4Register(uModel);//用户注册
			
		}

		return or;
	}

	/**
	 * 新增用户绑定信息到数据库或者更新用户绑定信息 1 已存在用户绑定记录 1.1 是绑定状态 1.1.1
	 * 绑定的是同一个用户，更新绑定手机号码，提示用户绑定成功 1.1.2 绑定的是不同的用户，不允许用户再绑定，必须先解绑 1.2
	 * 不是绑定状态，绑定用户 2 不存在用户绑定记录，新增绑定信息
	 * 
	 * @param reqStruct
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public OutputResult<Userbinding, String> AddOrUpdateUserBinding(
			Request_BindUser reqStruct, long userId) {
		OutputResult<Userbinding, String> or = new OutputResult<>();

		Userbinding user = cDao.queryForEntity(reqStruct.uuid,
				Userbinding.class);
		if (user != null) {
			boolean isNeedUpdate = false;
			if (user.getBindState().intValue() == MyEnum.WechatBindState.已绑定
					.getValue())// 绑定状态
			{
				if (user.getUserId() == userId) {
					if (reqStruct.data.mobile.equals(user.getMobile()))
						isNeedUpdate = true;
				} else {
					or.result = "当前微信号已经绑定其他用户，若需要重新绑定，请解绑后再绑定。";
				}
			} else {
				isNeedUpdate = true;
			}
			// 更新用户绑定信息，新增绑定记录
			if (isNeedUpdate) {
				user.setBindState((long) MyEnum.WechatBindState.已绑定.getValue());
				user.setUserId(userId);
				user.setMobile(reqStruct.data.mobile);
				user.setBindtime(DateUtil.Date2Timestamp(new Date()));
				cDao.executeSave(user);

				BindingRecord br1 = NewBindingRecord(reqStruct, userId, true);
				cDao.executeSave(br1);
			}
		} else {
			user = new Userbinding();
			user.setOpenid(reqStruct.uuid);
			user.setPaid(Config.Wechat_AppID);
			user.setMobile(reqStruct.data.mobile);
			user.setBindState((long) MyEnum.WechatBindState.已绑定.getValue());
			user.setUserId(userId);
			user.setBindtime(DateUtil.Date2Timestamp(new Date()));
			user.setCareState((long) MyEnum.WechatCareState.未关注.getValue());
//			user.setBindtime(DateUtil.Date2Timestamp(ConfigData.DefaultDateTime));
			cDao.executeUpdate(user);
			BindingRecord br1 = NewBindingRecord(reqStruct, userId, false);
			cDao.executeUpdate(br1);
		}
		or.setResultObj(user);
		or.code = Constant.Code_Succeed;

		return or;
	}

	private BindingRecord NewBindingRecord(Request_BindUser reqStruct,
			long userId, boolean isUpdate) {
		BindingRecord br1 = NewBindingRecord(reqStruct.uuid, userId, false,3);
		return br1;
	}

	/**
	 * 增加绑定记录
	 * @param uuid 微信用户id，物理主键，内部操作使用
	 * @param userId
	 * @param isUpdate 
	 * @param bindType 1-关注|2-取消关注|3-绑定|4-取消绑定|5-网页授权
	 * @return
	 */
	private BindingRecord NewBindingRecord(String uuid,
			long userId, boolean isUpdate,int bindType) {
		long flowId = 1;
		String hql = "from BindingRecord where id.openid=? order by id.flowid desc";
		List<BindingRecord> list = cDao.queryForList(hql, new Object[] { uuid });
		if (list != null && list.size() > 0) {
			BindingRecord br = list.get(0);
			if (br != null) {
				flowId = br.getId().getFlowid() + 1;
			}
		}
		BindingRecord br1 = new BindingRecord();
		BindingRecordId keyID = new BindingRecordId();
		keyID.setOpenid(uuid);
		keyID.setFlowid(flowId);
		br1.setId(keyID);
		if(bindType==3){
			br1.setType((long) MyEnum.WechatBindType.绑定.getValue());
			br1.setMemo((isUpdate ? "更新" : "新增") + "用户绑定信息");
		}else if(bindType==4){
			br1.setType((long) MyEnum.WechatBindType.取消绑定.getValue());
			br1.setMemo("新增用户取消绑定信息");
		}
		br1.setCreateTime(DateUtil.Date2Timestamp(new Date()));
		br1.setExtend(String.valueOf(userId));
		return br1;
	}

	public OutputResult<UserInfo4Bind, String> GetUserInfo4Bind(long userId) {
		OutputResult<UserInfo4Bind, String> or = new OutputResult<>();

		TAcUser user = cDao.queryForEntity(userId, TAcUser.class);
		if (user != null) {
			UserInfo4Bind ub = new UserInfo4Bind();
			ub.userId = userId;
			ub.userType = 1;// 后续再赋值
			ub.enterpriseId = -1;
			ub.userName = Common.IsNullOrEmpty(user.getUsername()) ? "未命名"
					: user.getUsername();
			ub.tradePwdState = 0;
			long depId = user.getOrgid();
			if (depId != Config.Default_IntegerValue) {
				long companyId=depId;
				TAcOrg org = cDao.queryForEntity(depId, TAcOrg.class);
				if(org!=null){
					String treelayer=org.getTreelayer();
					String[] split=treelayer.split("\\.");
					if(split.length>2){
						companyId=Long.parseLong(split[2]);
					}
					ub.enterpriseId=companyId;
					ub.userType=2;
					or.setResultObj(ub);
					or.code=Constant.Code_Succeed;
				}else{
					or.result="未找到员工所属的部门信息，请联系客服。";
				}
			}else{
				or.setResultObj(ub);
				or.code=Constant.Code_Succeed;
			}
		} else {
			or.result = MessageFormat.format("未找到编号为[{0}]的用户信息，请联系客服。", userId);
		}

		return or;
	}

	/**
	 * 解绑操作
	 * @param arg0
	 * @return
	 */
	public OutputResult<Response_UnbindUser, String> doUnBindUser(
			Request_UnbindUser unbu) {
		OutputResult<Response_UnbindUser, String> outResult = new OutputResult<Response_UnbindUser, String>();
		Userbinding userBd = null;
		try {
			userBd = cDao.queryForEntity(unbu.uuid,Userbinding.class);
			if(userBd!=null){
				userBd.setBindState(2l);
				userBd.setUserId(-1l);
				userBd.setBindtime(new Timestamp(new Date().getTime()));
				//修改绑定信息为解绑内容
				cDao.executeUpdate(userBd);
				
				//增加解绑的绑定记录
				BindingRecord br = NewBindingRecord(unbu.uuid,unbu.userId,true,4);
				cDao.executeSave(br);
				
				outResult.code = Constant.Code_Succeed;
			}
		} catch (Exception e) {
			outResult.result="用户信息不存在，请联系客服。";
		}
		//
		return outResult;
	}


}
