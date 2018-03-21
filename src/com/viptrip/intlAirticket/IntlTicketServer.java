package com.viptrip.intlAirticket;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.ConfigUtil;


import etuf.v1_0.model.base.Config;

public class IntlTicketServer extends etuf.v1_0.controller.base.v2.base.Server {

	private static final long serialVersionUID = 7205551622303631331L;
	private static Logger logger = LoggerFactory.getLogger(IntlTicketServer.class);
	private static final String INTLTICKET_PREFIX = "intlTicket";//配置文件属性前缀
	
	@Override
	public Config GetConfig() {
		Config c= null;
		try {
			c = ConfigUtil.config(INTLTICKET_PREFIX);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		return c;
		
//		Config c= new Config();
//		c.setMaxCountsInGivenTime(new MaxCountsInGivenTime(2, 10, getClass().getName()));
//		c.setControllerNameSpace("com.viptrip.hotel.controller");
//		c.setModelNameSpace("com.viptrip.hotel.model");
//		c.setParamsEncrypt(true);//默认值就是true
//		c.setDesKey("凯撒酒店");//des加解密密钥，可随意设置，后端会做处理，只取8个字节
//		c.setDesIV("iv4Hotel");//des加解密向量，可随意设置，后端会做处理，只取8个字节
//		c.setCharset(Charset.forName("UTF-8"));
//		return c;
	}

}
