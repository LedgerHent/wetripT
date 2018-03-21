package com.viptrip.wetrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.common.MyEnum;
import com.viptrip.wechat.model.WechatBase;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.model.Request_BindUser;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;

import etuf.v1_0.model.base.output.OutputResult;

@Service
@Transactional
public class WechatAvoidLogin {

	@Resource
	private ComDao dao;

	
	/**
	 * 判断是否有绑定信息，且绑定状态为已绑定
	 * @param wb
	 * @param or
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public OutputResult<Request_BindUser, String> ifBinding2Login(WechatBase wb) {
		OutputResult<Request_BindUser, String> or = new OutputResult<Request_BindUser, String>();
		Userbinding bindUser = new Userbinding();
		Request_BindUser reqBind = new Request_BindUser();
		if(wb.getOpenID()==null)
			or.result = "您的OpenID信息不存在，请联系客服解决。";
		else{
			bindUser = dao.queryForEntity(wb.getOpenID(), Userbinding.class);
			//有绑定信息且是已关注状态
			//if(null!=bindUser&&bindUser.getCareState()==MyEnum.WechatCareState.已关注.getValue()){
				TAcUser user = (bindUser==null || bindUser.getUserId()==null || bindUser.getUserId()==-1l) ?null:
					dao.queryForEntity(bindUser.getUserId(), TAcUser.class);
				if(user!=null){
					if(MyEnum.WechatBindState.已绑定.getValue()==bindUser.getBindState()){
						if("4"!=user.getStatus()){//用户不是停用状态
							or.code=etuf.v1_0.common.Constant.Code_Succeed;
						}else {// 4 停用
							or.result = "您的账户已停用，请联系客服解决。";
						} 
					}else {//有信息，但状态为未绑定。
						reqBind.data = new MobileAndCheckCode(); 
						reqBind.data.mobile = user.getPhone();
						or.setResultObj(reqBind);
						or.result = "redirect";
					}
				}else {
//					or.result = "您的账户信息user不存在，请联系客服解决。";
					reqBind.data = new MobileAndCheckCode(); 
					reqBind.data.mobile = wb.getMobile();
					or.setResultObj(reqBind);
					or.result = "redirect";
				}
//			}else{//没有关注信息的，或者未关注状态的
//				or.result = "您的关注信息bindUser不存在，请先关注。";
//			}
		}
		return or;
		
	}


}
