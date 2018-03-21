package com.viptrip.wetrip;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

import etuf.v1_0.model.base.Config;
import etuf.v1_0.model.base.MaxCountsInGivenTime;
/**
 * server配置
 * @author selfwhisper
 *
 */
public class ConfigUtil {
	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	
	private static final int DEFAULT_SEC = 1;//默认访问时间设置
	private static final int DEFAULT_COUNT_IN_SEC = 10;//默认访问次数设置
	
	public static Config config(String prefix) throws IOException{
		Config config = null;
		Assert.notNull(prefix,"配置文件前缀为空");
		String prefixes = PropertiesUtils.getProperty(Const.PRO_SERVER_PREFIX, Const.FILE_CONFIG);
		if(!StringUtil.isEmpty(prefixes)){
			String[] pres = prefixes.split(",");
			if(null!=pres && pres.length>0){
				List<String> list = Arrays.asList(pres);
				if(list.contains(prefix)){
					config = new Config();
					String controllerNS = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_CONTROLLER_NS, Const.FILE_CONFIG);
					String modelNS = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_MODEL_NS, Const.FILE_CONFIG);
					String desKey = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_DESKEY, Const.FILE_CONFIG);
					String desIV = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_DESIV, Const.FILE_CONFIG);
					String charset = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_CHARSET, Const.FILE_CONFIG);
					config.setControllerNameSpace(controllerNS);
					config.setModelNameSpace(modelNS);
					config.setDesKey(desKey);
					config.setDesIV(desIV);
					config.setCharset(Charset.forName(charset));
					
					config.setMaxCountsInGivenTime(GetMaxCountsInGivenTime(prefix));
					
					if(logger.isDebugEnabled()){
						logger.debug("ConfigUtil.config()→controllerNamespace=" + controllerNS + ",length=" + controllerNS.length());
						logger.debug("ConfigUtil.config()→modelNamespace=" + modelNS + ",length=" + modelNS.length());
						logger.debug("ConfigUtil.config()→desKey=" + desKey + ",length=" + desKey.length());
						logger.debug("ConfigUtil.config()→desIV=" + desIV + ",length=" + desIV.length());
						logger.debug("ConfigUtil.config()→charset=" + charset + ",length=" + charset.length());
					}
				}
			}
		}
		return config;
	}
	
	
	private static MaxCountsInGivenTime GetMaxCountsInGivenTime(String prefix) {
		int sec = 0;
		int countInSec = 0;
		try {
			String secStr = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_SEC, Const.FILE_CONFIG);
			sec = Integer.parseInt(secStr);
		} catch (IOException e) {
			sec = DEFAULT_SEC;
			logger.error(StringUtil.getExceptionStr(e));
		}
		try {
			String countInSecStr = PropertiesUtils.getProperty(prefix + "." + Const.PRO_SERVER_COUNT_IN_SEC, Const.FILE_CONFIG);
			countInSec = Integer.parseInt(countInSecStr);
		} catch (IOException e) {
			countInSec = DEFAULT_COUNT_IN_SEC;
			logger.error(StringUtil.getExceptionStr(e));
		}
		if(logger.isDebugEnabled()){
			logger.debug("TicketServer.GetMaxCountsInGivenTime()→sec=" + sec + ";countInSec=" + countInSec);
		}
		return new MaxCountsInGivenTime(sec,countInSec,prefix);
//		return new MaxCountsInGivenTime(sec,countInSec,getClass().getName());
	}
	
	
}
