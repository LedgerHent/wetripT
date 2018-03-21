package com.viptrip.wetrip.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import weixin.mp.bean.result.WxMpOAuth2AccessToken;

import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.WechatBindState;
import com.viptrip.util.DateUtil;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.BindingRecord;
import com.viptrip.wetrip.entity.BindingRecordId;
import com.viptrip.wetrip.entity.Userbinding;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.ConfigData;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

@Service
@Transactional
public class WechatBinding {
	@Resource
	private ComDao cDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public OutputResult<Userbinding, String> HandleOAuth2BaseInfo(
			WxMpOAuth2AccessToken accessToken) {
		OutputResult<Userbinding, String> or = new OutputResult<Userbinding, String>();
		try {
			Userbinding ub = cDao.queryForEntity(accessToken.getOpenId(),
					Userbinding.class);
			if (ub != null) {// 更新用户绑定信息
				if (!Common.IsNullOrEmpty(ub.getUnionid())
						&& !Common.IsNullOrEmpty(accessToken.getUnionId())) {
					ub.setUnionid(accessToken.getUnionId());
					cDao.executeUpdate(ub);
				}
			} else {
				// 新增绑定信息
				ub = new Userbinding();
				ub.setOpenid(accessToken.getOpenId());
				ub.setPaid(Config.Wechat_AppID);
				ub.setBindState((long) MyEnum.WechatBindState.未绑定.getValue());
				ub.setUserId(-1l);
				ub.setBindtime(DateUtil.Date2Timestamp(ConfigData.DefaultDateTime));
				ub.setCareState((long) MyEnum.WechatCareState.未关注.getValue());
				ub.setCaretime(DateUtil.Date2Timestamp(new Date()));
				cDao.executeSave(ub);
				//新增绑定记录
				AddBindRecord(accessToken.getOpenId(), MyEnum.WechatBindType.网页授权);
			}
			or.code = etuf.v1_0.common.Constant.Code_Succeed;
			or.setResultObj(ub);
		} catch (Exception e) {
			e.printStackTrace();
			or.exception = e;
			or.result="错误代码：201704091434。错误信息：处理微信用户授权基本信息发生异常。";
		}
		return or;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public OutputSimpleResult HandleEvent_Subscribe_Unsubscribe(String paid,String openid,boolean isSubscribe){
		OutputSimpleResult osr=new OutputSimpleResult();
		
		try
		{
			long subsValue=isSubscribe ? 1l : 2l;
			MyEnum.WechatBindType bindType=isSubscribe?MyEnum.WechatBindType.关注:MyEnum.WechatBindType.取消关注;
			Userbinding ub = cDao.queryForEntity(openid,
					Userbinding.class);
			if (ub != null) {// 更新用户绑定信息
				long careState=2;
				if(ub.getCareState()!=null){
					careState=ub.getCareState().intValue();
				}
				if(careState!=subsValue){//是否关注状态发生变化，修改绑定信息
					ub.setCareState(subsValue);
					ub.setCaretime(DateUtil.Date2Timestamp(new Date()));
					ub.setPaid(paid);
					cDao.executeUpdate(ub);
					//新增绑定记录
					AddBindRecord(openid, bindType);
					osr.code=etuf.v1_0.common.Constant.Code_Succeed;
				} else{
					osr.code=etuf.v1_0.common.Constant.Code_Succeed;
				}
			} else {//新增用户绑定信息
				ub=new Userbinding();
				ub.setOpenid(openid);
				ub.setPaid(paid);
				ub.setBindState((long)WechatBindState.未绑定.getValue());
				ub.setUserId(-1l);
				ub.setBindtime(DateUtil.Date2Timestamp(new Date()));
				ub.setCareState(subsValue);
				ub.setCaretime(DateUtil.Date2Timestamp(new Date()));
				cDao.executeSave(ub);
				//新增绑定记录
				AddBindRecord(openid, bindType);
				osr.code=etuf.v1_0.common.Constant.Code_Succeed;
			}
		}catch (Exception e) {
			e.printStackTrace();
			osr.exception = e;
			osr.result="错误代码：201708150936。错误信息：处理微信用户关注/取消关注发生异常。";
		}
		
		return osr;
	}

	/**
	 * 新增绑定记录
	 * @param openid
	 * @param bindType
	 */
	private void AddBindRecord(String openid, MyEnum.WechatBindType bindType) {
		long flowId=1;
		List<BindingRecord> brs=cDao.queryForList("" +
				"from BindingRecord where id.openid= ? order by id.flowid desc",new Object[]{openid});
		if(brs!=null && brs.size()>0){
			flowId+=brs.get(0).getId().getFlowid().longValue();
		}
		BindingRecord br=new BindingRecord();
		BindingRecordId id=new BindingRecordId();
		id.setOpenid(openid);
		id.setFlowid(flowId);
		br.setId(id);
		br.setType((long)bindType.getValue());
		br.setCreateTime(DateUtil.Date2Timestamp(new Date()));
		br.setExtend("");
		br.setMemo(bindType.toString());
		cDao.executeSave(br);
	}
	
}
