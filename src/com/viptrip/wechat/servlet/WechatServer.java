package com.viptrip.wechat.servlet;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.service.impl.WechatBinding;
import com.viptrip.wetrip.util.Sendemail;

import etuf.v1_0.model.base.output.OutputSimpleResult;

import weixin.common.api.WxConsts;
import weixin.common.exception.WxErrorException;
import weixin.common.session.WxSessionManager;
import weixin.mp.api.WxMpInMemoryConfigStorage;
import weixin.mp.api.WxMpMessageHandler;
import weixin.mp.api.WxMpService;
import weixin.mp.bean.message.WxMpXmlMessage;
import weixin.mp.bean.message.WxMpXmlOutMessage;
import weixin.mp.bean.message.WxMpXmlOutTextMessage;

@SuppressWarnings("serial")
public class WechatServer extends etuf.v1_0.wechat.WechatServletAbs {

	private static final Logger log = LoggerFactory.getLogger(WechatServer.class);
	
	private String welcomeText=MessageFormat.format("【测试环境】凯撒商旅，服务保证。\n欢迎关注凯撒商旅，如需帮助，请拨打24小时客服服务热线  <a href=\"tel:{0}\">{0}</a>",
    		com.viptrip.base.common.Config.customerHotService);
	
	@SuppressWarnings("unused")
	private void recordLog(String info){
		log.info(MessageFormat.format("********************wechat:{0}***********************",info));
	}
	
	@Override
	public WxMpInMemoryConfigStorage GetWechatConfig() {
		return Config.GetWechatConfig();
	}

	@Override
	protected void WxMpMessageHandle() {
		//recordLog("begin WxMpMessageHandle impl");
		//在这里做特定的业务逻辑即可，比如 回复一条文本消息
		wxMpMessageRouter
		.rule()
        .async(false)
        .handler(getWxMpMessageHandler(welcomeText))
        .next();
		
		//recordLog("begin WxMpMessageHandle_Event_SubscribeOrUnSubscribe");
		//处理 关注/取消关注 事件
		WxMpMessageHandle_Event_SubscribeOrUnSubscribe();
		//recordLog("WxMpMessageHandle_Event_SubscribeOrUnSubscribe end");
		//recordLog("WxMpMessageHandle impl end");
	}
	
	/**
	 * 事件：关注/取消关注
	 */
	private void WxMpMessageHandle_Event_SubscribeOrUnSubscribe(){
		WxMpMessageHandler handler4Subscribe= new WxMpMessageHandler(){
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
					Map<String, Object> context, WxMpService wxMpService,
					WxSessionManager sessionManager) throws WxErrorException {
				HandleEvent_Subscribe_UnSubscribe(wxMessage,true);
				WxMpXmlOutTextMessage m
	              = WxMpXmlOutMessage.TEXT().content(welcomeText).fromUser(wxMessage.getToUser())
	              .toUser(wxMessage.getFromUser()).build();
	          return m;
			}
	      };
	      WxMpMessageHandler handler4UnSubscribe= new WxMpMessageHandler(){
				@Override
				public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
						Map<String, Object> context, WxMpService wxMpService,
						WxSessionManager sessionManager) throws WxErrorException {
					HandleEvent_Subscribe_UnSubscribe(wxMessage,false);
					WxMpXmlOutTextMessage m
		              = WxMpXmlOutMessage.TEXT().content(welcomeText).fromUser(wxMessage.getToUser())
		              .toUser(wxMessage.getFromUser()).build();
		          return m;
				}
		      };
	      
		wxMpMessageRouter
		//关注
		.rule()
		.async(false)
		.event(WxConsts.EVT_SUBSCRIBE)
		.handler(handler4Subscribe)
		.end()
		//取消关注
		.rule()
		.async(false)
		.event(WxConsts.EVT_UNSUBSCRIBE)
		.handler(handler4UnSubscribe)
		.end();
	}
	
	private void HandleEvent_Subscribe_UnSubscribe(WxMpXmlMessage wxMessage,boolean isSubscribe){
		// 获取service层实例
		WechatBinding service = ApplicationContextHelper.getInstance().getBean(
				WechatBinding.class);
		OutputSimpleResult osr=service.HandleEvent_Subscribe_Unsubscribe(config.getAppId(), wxMessage.getFromUser(),isSubscribe);
		if(!osr.IsSucceed()){
			//处理 关注/取消关注失败，邮件通知
			String body=MessageFormat.format("微信用户【{0}】{1}公众号【{2}】发生异常。\n\t\n\t{3}", 
					wxMessage.getFromUser(),isSubscribe?"关注":"取消关注",config.getAppId(),osr.exception.getStackTrace());
			Sendemail.sendHTML("jishubu.list@caissa.com.cn", "微信关注/取消关注处理", body);
		}
	}
	
	
	
}
