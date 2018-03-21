
package com.viptrip.wetrip.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties Util函数.
 * 
 *  
 */
public class PropertiesUtil {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 载入多个properties文件, 相同的属性最后载入的文件将会覆盖之前的载入.
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... locations) throws IOException {
		Properties props = new Properties();
		for (String location : locations) {
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}
	public static String getConfigProperty(String property,String company) {
		try {
			Properties props = PropertiesUtil.loadProperties("/com/viptrip/"+company+"/properties/config.properties");
			return props.getProperty(property);
		} catch (IOException e) {
			return "";
		}
	}
	
	public static String getReportProperty(String property,String company) {
		try {
			Properties props = PropertiesUtil.loadProperties("/com/viptrip/"+company+"/properties/report.properties");
			return props.getProperty(property);
		} catch (IOException e) {
			return "";
		}
	}
	
	public static String getProperty(String source,String property,String company) {
		try {
			Properties props = PropertiesUtil.loadProperties("/com/viptrip/"+company+"/properties/"+source+".properties");
			return props.getProperty(property);
		} catch (IOException e) {
			return "";
		}
	}
	public static String getResourcesProperty(String property) {
		try {
			Properties props = PropertiesUtil.loadProperties("/com/viptrip/base/properties/ApplicationResources.properties");
			return props.getProperty(property);
		} catch (IOException e) {
			return "";
		}
	}
	
	
	/**
	* @描述: 可传入任意路径
	* 
	* @日期：2012-5-17 下午05:25:37
	* @param property
	* @param configFile
	* @return
	* @return String
	*/
	public static String getAnyConfigProperty(String property,String configFile) {
		try {
			Properties props = PropertiesUtil.loadProperties(configFile);
			return props.getProperty(property);
		} catch (IOException e) {
			return "";
		}
	}
}
