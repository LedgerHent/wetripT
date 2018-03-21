package com.viptrip.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.ConfigUtil;

import etuf.v1_0.model.base.Config;

public class CommonServer extends etuf.v1_0.controller.base.v2.base.Server {

	private static final long serialVersionUID = 7205551622303631331L;
	private static Logger logger = LoggerFactory.getLogger(CommonServer.class);
	private static final String COMMON_PREFIX = "common";//配置文件属性前缀
	
	@Override
	public Config GetConfig() {
		Config c= null;
		try {
			c = ConfigUtil.config(COMMON_PREFIX);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		return c;
	}

}
