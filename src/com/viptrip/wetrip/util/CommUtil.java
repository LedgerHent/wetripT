package com.viptrip.wetrip.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.uban360.config.Config;
import com.viptrip.util.DateUtil;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TEndorse;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.service.IComService;
import com.viptrip.wetrip.vo.CaiyunAccessToken;

/**
 * 通用工具
 * @author selfwhisper
 *
 */
public class CommUtil {
	private static Logger logger = LoggerFactory.getLogger(CommUtil.class);
	
	/**
	 * 获取航司票规
	 * @param airlineCode 航司二字码
	 * @param date 日期
	 * @param datePattern 日期样式
	 * @return
	 */
	public static List<TEndorse> getEndorse(String airlineCode,String date,String datePattern){
		@SuppressWarnings("unchecked")
		List<TEndorse> result = RedisCacheManager.get(Const.CACHE_ENDORSE, List.class);
		if(null!=result && !StringUtil.isEmpty(airlineCode)&&!StringUtil.isEmpty(date)){
			Iterator<TEndorse> iterator = result.iterator();
			while(iterator.hasNext()){
				TEndorse endorse = iterator.next();
				if(!airlineCode.equals(endorse.getAir2char())){
					iterator.remove();
					continue;
				}else{
					Date effectivedate = endorse.getEffectivedate();
					Date expirydate = endorse.getExpirydate();
					Date d;
					try {
						d = DateUtil.parse(date, datePattern);
						if(!(effectivedate.compareTo(d)<=0 && expirydate.compareTo(d)>=0)){
							iterator.remove();
							continue;
						}
					} catch (ParseException e) {
						logger.error(StringUtil.getExceptionStr(e));
						continue;
					}
				}
				
			}
		}
		return result;
	}
	
	/**
	 * 获取航司舱位票规
	 * @param airlineCode 航司二字码
	 * @param cabinCode 舱位
	 * @param date 日期
	 * @param datePattern 日期样式
	 * @return
	 */
	public static List<TEndorse> getEndorse(String airlineCode,String cabinCode,String date,String datePattern){
		@SuppressWarnings("unchecked")
		List<TEndorse> result = RedisCacheManager.get(Const.CACHE_ENDORSE, List.class);
		if(null!=result && !StringUtil.isEmpty(airlineCode) && !StringUtil.isEmpty(date) && !StringUtil.isEmpty(datePattern)){
			Iterator<TEndorse> iterator = result.iterator();
			while(iterator.hasNext()){
				TEndorse endorse = iterator.next();
				if(!airlineCode.equals(endorse.getAir2char())){
					iterator.remove();
					continue;
				}else if(!StringUtil.isEmpty(cabinCode)&&!cabinCode.equals(endorse.getCangwei())){
					iterator.remove();
					continue;
				}else{
					Date effectivedate = endorse.getEffectivedate();
					Date expirydate = endorse.getExpirydate();
					Date d;
					try {
						d = DateUtil.parse(date, datePattern);
						if(!(effectivedate.compareTo(d)<=0 && expirydate.compareTo(d)>=0)){
							iterator.remove();
							continue;
						}
					} catch (ParseException e) {
						logger.error(StringUtil.getExceptionStr(e));
						continue;
					}
				}
				
			}
		}
		return result;
	}
	
	/**
	 * 通过订单id获取联系人list
	 * @param oid
	 * @return
	 */
	public static List<TNotifyPartyInformation> getContactList(Long oid,Integer type){
		List<TNotifyPartyInformation> list = null;
		if(null!=oid){
			if(null==type){
				type=0;
			}
			IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
			list = service.getByContactsByOid(oid,type);
		}
		return list;
	}
	
	/**
	 * 获取彩云集团accessToken
	 * @return
	 */
	public static CaiyunAccessToken getAccessToken(){
		CaiyunAccessToken token = null;
		String key=MessageFormat.format("{0}_{1}", Const.CACHE_CAIYUN_CACHE_KEY,Config.appId);
		token = RedisCacheManager.get(key, CaiyunAccessToken.class);
		if(null==token){
			token = CaiyunUtil.getToken();
			if(logger.isDebugEnabled())
				logger.debug("获取集团彩云accessToken,返回结果:" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(token));
			if(null!=token && 0==token.getCode()){
				long time = token.getExpiresIn().longValue();
				if(time>200){
					time = time - 200;
				}
				RedisCacheManager.save(key, token, time);
			}
		}
		return token;
	}
	
	/**
	 * 刷新彩云集团accessToken
	 * @return
	 */
	public static CaiyunAccessToken refreshAccessToken(){
		CaiyunAccessToken token = null;
		token = CaiyunUtil.getToken();
		if(logger.isDebugEnabled())
			logger.debug("刷新集团彩云accessToken,返回结果:" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(token));
		if(null!=token){
			if(0==token.getCode()){
				long time = token.getExpiresIn().longValue();
				if(time>200){
					time = time - 200;
				}
				RedisCacheManager.save(Const.CACHE_CAIYUN_CACHE_KEY, token, time);
			}
		}
		return token;
	}
	
}
