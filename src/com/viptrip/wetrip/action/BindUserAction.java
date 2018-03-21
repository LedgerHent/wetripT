package com.viptrip.wetrip.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.MyEnum;
import com.viptrip.wechat.model.WechatBase;
import com.viptrip.wetrip.controller.BindUser;
import com.viptrip.wetrip.controller.GetSmsCheckCode;
import com.viptrip.wetrip.controller.UnbindUser;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.model.Request_BindUser;
import com.viptrip.wetrip.model.Request_GetSmsCheckCode;
import com.viptrip.wetrip.model.Request_UnbindUser;
import com.viptrip.wetrip.model.Response_BindUser;
import com.viptrip.wetrip.model.Response_GetSmsCheckCode;
import com.viptrip.wetrip.model.Response_UnbindUser;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;

@Controller
@RequestMapping("/bindSmsCheckCode")
public class BindUserAction extends BaseAction {
	private static final String toError = "public/failure";// 跳转页面

	/**
	 * 微信短信验证码发送
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/smsCode.act")
	@ResponseBody
	public AjaxResp sendSmsCheckCode(String mobile) {
		AjaxResp result = null;
		GetSmsCheckCode smsCode = new GetSmsCheckCode();
		Request_GetSmsCheckCode reqSms = new Request_GetSmsCheckCode();
		reqSms.data = new MobileAndCheckCode();
		reqSms.data.mobile = mobile;
		// 调用发送短信验证码接口
		OutputResult<Response_GetSmsCheckCode, Response_BaseMsg> out = smsCode
				.ClientRequest(reqSms, Response_GetSmsCheckCode.class,Response_BaseMsg.class);
		if (out.IsSucceed()) {
			result = success(out.getResultObj());
		} else {
			result = fail(out.getErrorObj());
		}
		return result;

	}

	/**
	 * 微信用户信息绑定
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/bindingUser.act")
	public String toBindUser(MobileAndCheckCode data) throws IOException {

		BindUser bind = new BindUser();
		Request_BindUser reqBind = new Request_BindUser();
		reqBind.data = data;
		WechatBase wb = BaseAction4Wechat.GetWechatBaseInfo(req.getSession());
		reqBind.uuid = wb.getOpenID();
		OutputResult<Response_BindUser, Response_BaseMsg> out = bind
				.ClientRequest(reqBind, Response_BindUser.class,
						Response_BaseMsg.class);

		if (out.IsSucceed()) {
			BaseAction4Wechat.UpdateUserBindingInfo(req.getSession(),MyEnum.WechatBindState.已绑定);
			OutputSimpleResult osr=BaseAction4Wechat.CASLogin4Wechat(req, resp,null, data.mobile);
			if(osr.IsSucceed()){
				return "redirect:" + osr.result;
			}else{
				addAttr("result", osr.result);
				return toError;
			}
		} else {
			// 返回错误提示信息
			addAttr("result", out.result);
			return toError;
		}

	}
	
	@RequestMapping("/unbindUser.act")
	public String UnBind(){
		TAcUser user=getUser();
		if(user!=null){
			Userbinding ub=BaseAction4Wechat.GetUserBindingInfo(req.getSession());
			if(ub!=null &&ub.getBindState().intValue()== MyEnum.WechatBindState.已绑定.getValue()){
				Request_UnbindUser req=new Request_UnbindUser();
				req.userId=user.getUserid();
				req.uuid=ub.getOpenid();
				UnbindUser c=new UnbindUser();
				OutputResult<Response_UnbindUser, Response_BaseMsg> or=
						c.ClientRequest(req, Response_UnbindUser.class, Response_BaseMsg.class);
				if(or.IsSucceed()){
					//清空用户登录信息，退出cas登录。因为cas退出，在本地没有实现退出功能，因此需要变更session，伪造一个新的请求，让cas以为是新的请求
					UserLogout();
					BaseAction4Wechat.DeleteUserBindingInfo(getReq().getSession());
					OutputSimpleResult osr= BaseAction4Wechat.CASLogout4Wechat(getReq(), getResp(), 
							com.viptrip.base.common.Config.wechatLoginActionUrl);
					//获取WechatBase信息，删除原有session中的缓存，原有session失效，之后在新的session中保存原来的WechatBase
					WechatBase wb=BaseAction4Wechat.GetWechatBaseInfo(getReq().getSession());
					BaseAction4Wechat.DeleteWechatBaseInfo(getReq().getSession());
					getReq().getSession().invalidate();
					BaseAction4Wechat.SaveWechatBaseInfo(getReq().getSession(), wb);
					if(osr.IsSucceed()){
						return "redirect:" + osr.result;
					}else{
						addAttr("result", osr.result);
						return toError;
					}
				}else{
					addAttr("result", or.getErrorObj().msg);
					return toError;
				}
			}else{
				addAttr("result", "当前用户未绑定，错误状态。");
				return toError;
			}
		}else{
			addAttr("result", "获取用户身份信息失败。");
			return toError;
		}
	}

}
