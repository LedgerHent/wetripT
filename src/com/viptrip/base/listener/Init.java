package com.viptrip.base.listener;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.common.CacheInit;
import com.viptrip.wetrip.util.CaiyunUtil;
import com.viptrip.wetrip.vo.CaiyunAccessToken;

import ch.qos.logback.classic.Logger;

/**
 * 初始化资源
 * @author selfwhisper
 *
 */
public class Init implements ServletContextListener{
	
	final static Logger log =  (Logger) LoggerFactory.getLogger(Init.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext app = event.getServletContext();
		
		try {
			
			String ctx = app.getContextPath();
			app.setAttribute(Const.APP_CTX, ctx);
			String ICON_SERVER_URL = PropertiesUtils.getProperty(Const.URL_ICON_SERVER, Const.FILE_CONFIG);
			app.setAttribute(Const.URL_ICON_SERVER, ICON_SERVER_URL );
			
			//缓存数据初始化
			CacheInit.init();
//			ApplicationContextHelper.getInstance().getBean(null);
			Map<String, String> cityMap = RedisCacheManager.get(Const.APP_MAP_3CHAR_CITY, Map.class);
			Map<String, String> airportNameMap = RedisCacheManager.get(Const.APP_MAP_3CHAR_AIRPORTNAME, Map.class);
			Map<String, String> planeTypeMap = RedisCacheManager.get(Const.APP_MAP_PLANETYPE, Map.class);
			Map<String, String> cabinNameMap = RedisCacheManager.get(Const.APP_MAP_CABINNAME, Map.class);
			Map<String, List<String>> cabinCodeMap = RedisCacheManager.get(Const.APP_MAP_CABINCODE, Map.class);
			Map<String, String> ac2NameMap = RedisCacheManager.get(Const.APP_MAP_AC2NAME, Map.class);
			Map<String, String> payTypeMap = RedisCacheManager.get(Const.APP_MAP_PAYTYPE, Map.class);
			Map<String, String> idcardTypeMap = RedisCacheManager.get(Const.APP_MAP_IDCARDTYPE, Map.class);
			Map<String, String> personnelTypeMap = RedisCacheManager.get(Const.APP_MAP_PERSONNELTYPE, Map.class);
			
			
			app.setAttribute(Const.APP_MAP_3CHAR_CITY, cityMap);//设置 三字码-城市名map
			app.setAttribute(Const.APP_MAP_3CHAR_AIRPORTNAME, airportNameMap);//设置 三字码-机场名map
			app.setAttribute(Const.APP_MAP_PLANETYPE, planeTypeMap);//设置 机型-机型名称 map
			app.setAttribute(Const.APP_MAP_CABINNAME, cabinNameMap);//设置 航空公司二字码_舱位码-舱位名称  map
			app.setAttribute(Const.APP_MAP_CABINCODE, cabinCodeMap);//设置 航空公司二字码_舱位名称-舱位码list  map
			app.setAttribute(Const.APP_MAP_AC2NAME, ac2NameMap);//设置 航空公司二字码-航空公司简称  map
			app.setAttribute(Const.APP_MAP_PAYTYPE, payTypeMap);//设置 结算类型code-name map
			app.setAttribute(Const.APP_MAP_IDCARDTYPE, idcardTypeMap);//设置 证件类型ID-证件名称
			app.setAttribute(Const.APP_MAP_PERSONNELTYPE, personnelTypeMap);//设置 证件类型ID-证件名称
			
			//结算类型 设置缓存code-name
			//RedisCacheManager.save(Const.CACHE_PAYTYPE_MAP, payTypeMap,MyEnum.CacheKeepTime.永久.getValue());
			
			
		} catch (Exception e) {
			log.error(StringUtil.getLogInfo(null, null, StringUtil.getExceptionStr(e)));
		}
	}
	
	
	/**
	 * 加载比对队列
	 */
	@SuppressWarnings("unused")
	private void loadMatchQueue(ServletContextEvent event){
		/*WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		DllMatchService dms = wac.getBean(DllMatchService.class);
		LinkedBlockingQueue<MatchObj> queue = dms.getQueue();
		try {
			Properties p = ProUtils.loadFile(Const.QUEUE_NAME);
			if(p!=null&&p.size()>0){
				Set<Entry<Object, Object>> entrySet = p.entrySet();
				for(Entry<Object, Object> entry:p.entrySet()){
					String key = (String) entry.getKey();
					String val = (String) entry.getValue();
					if(!StringUtil.isEmpty(key)&&!StringUtil.isEmpty(val)){
						queue.add(new MatchObj(Integer.parseInt(key), Integer.parseInt(val)));
					}
				}
			}
			log.info("加载比对队列成功");
		} catch (IOException e) {
			log.error("加载比对队列异常："+StringUtil.getExceptionStr(e));
		}*/
	}

}
