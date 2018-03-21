package com.viptrip.base.db;


import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.nethsoft.zhxq.core.util.ObjectUtil;
import com.viptrip.base.resource.Constant;
import com.viptrip.util.StringUtil;





import cn.sucng.util.RSAUtils;
import ch.qos.logback.classic.Logger;

public class DecryptJdbcProperty extends PropertyPlaceholderConfigurer{
	final static Logger log =  (Logger) LoggerFactory.getLogger(DecryptJdbcProperty.class);
    
	@SuppressWarnings("unchecked")
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory factory,
			Properties props) throws BeansException {
		
		Enumeration<String> names = (Enumeration<String>) props.propertyNames();
		Set<String> pools = new HashSet<String>();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			if(name.indexOf(".")>0){
				pools.add(name.substring(0, name.indexOf(".")));
			}
		}
		if(!pools.isEmpty()){
			Iterator<String> iterator = pools.iterator();
			while(iterator.hasNext()){
				String pool = iterator.next();
				String encryptProStr = props.getProperty(pool  + "." + Constant.JDBC_ENCRYPT_PROPERTY);
				if(!ObjectUtil.isEmpty(encryptProStr)){
					String[] encryptPros = encryptProStr.split(",");
					if(encryptPros.length>0){
						for(String prop:encryptPros){
							try {
								String pname = pool  + "." + prop;
								props = setProp(props, pname, props.getProperty(pname), props.getProperty(pname+".key"));
							} catch (Exception e) {
								log.error(StringUtil.getLogInfo(null, null, "jdbc连接预解密异常\r\n" + StringUtil.getExceptionStr(e)));
							}
						}
					}
				}
			}
		}
		super.processProperties(factory, props);
	}
	
	
	private Properties setProp(Properties p,String name,String encryptValue,String publicKey) throws Exception{
		if(null!=p){
			if(!StringUtil.isEmpty(encryptValue)&&!StringUtil.isEmpty(publicKey)){
				String value = RSAUtils.decryptByPublicKey(encryptValue, publicKey);
				p.setProperty(name, value);
			}
		}
		return p;
	}
	
}
