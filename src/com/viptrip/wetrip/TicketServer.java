package com.viptrip.wetrip;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.util.StringUtil;

import etuf.v1_0.model.base.Config;

public class TicketServer extends etuf.v1_0.controller.base.v2.base.Server{
	private static final long serialVersionUID = 7821425178524069725L;
	private static Logger logger = LoggerFactory.getLogger(TicketServer.class);
	
	
	private static final String TICKET_PREFIX = "ticket";//配置文件属性前缀
	
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
