package com.viptrip.wetrip;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

import etuf.v1_0.model.base.Config;
import etuf.v1_0.model.v2.base.Request_Base;
import etuf.v1_0.model.v2.base.Response_Base;

public abstract class TicketClient <T extends Request_Base,K extends Response_Base> extends etuf.v1_0.controller.base.v2.base.Client<T,K>{
	private static Logger logger = LoggerFactory.getLogger(TicketClient.class);
	private static final String TICKET_PREFIX = "ticket";
	@Override
	protected void SetServerUrl() {
		try {
			String url = PropertiesUtils.getProperty(TICKET_PREFIX + "." + Const.PRO_SERVER_URL, Const.FILE_CONFIG);
			Assert.notNull(url);
			setServerUrl(url);
			if(logger.isDebugEnabled()){
				logger.debug("TicketClient.SetServerUrl()→url=" + url);
			}
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
	}
	
	/**
	 * 配置信息，客户端和服务器要统一设置，否则没办法通过签名认证
	 */
	@Override
	public Config GetConfig() {
		Config c= null;
		try {
			c = ConfigUtil.config(TICKET_PREFIX);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		return c;
	}
}
