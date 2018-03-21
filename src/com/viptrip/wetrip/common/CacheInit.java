package com.viptrip.wetrip.common;

import java.util.List;
import java.util.Map;

import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.wetrip.entity.TEndorse;
import com.viptrip.wetrip.service.IComService;
/**
 * 初始化缓存数据
 * @author selfwhisper
 *
 */
public class CacheInit {
	
	private static IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
	
	public static void init(){
		setEndorse();
		set3CHAR_AIRPORTNAME();
		set3CHAR_CITY();
		setAC2NAME();
		setCABINCODE();
		setCABINNAME();
		setPAYTYPE();
		setPLANETYPE();
		setDICTTYPECODE();
		setPERSONNELTYPE();
	}
	
	/**
	 * 设置票规
	 */
	public static void setEndorse(){
		List<TEndorse> endorses = service.getAllEndorse();
		RedisCacheManager.save(Const.CACHE_ENDORSE, endorses,24*3600l);
	}
	
	/**
	 * 设置票规
	 * @param endorses
	 */
	public static void setEndorse(List<TEndorse> endorses){
		RedisCacheManager.save(Const.CACHE_ENDORSE, endorses,24*3600l);
	}
	
	/**
	 * 设置三字码-城市名map
	 */
	public static void set3CHAR_CITY(){
		Map<String, String> cityMap = service.get3CharCityMap();
		RedisCacheManager.save(Const.APP_MAP_3CHAR_CITY, cityMap,24*3600l);
	}
	
	/**
	 * 设置三字码-城市名map
	 * @param 
	 */
	public static void set3CHAR_CITY(Map<String, String> cityMap){
		RedisCacheManager.save(Const.APP_MAP_3CHAR_CITY, cityMap,24*3600l);
	}
	
	/**
	 * 设置三字码-机场名map
	 */
	public static void set3CHAR_AIRPORTNAME(){
		Map<String, String> airportNameMap = service.get3CharAirportNameMap();
		RedisCacheManager.save(Const.APP_MAP_3CHAR_AIRPORTNAME, airportNameMap,24*3600l);
	}
	
	/**
	 * 设置三字码-机场名map
	 * @param endorses
	 */
	public static void set3CHAR_AIRPORTNAME(Map<String, String> airportNameMap){
		RedisCacheManager.save(Const.APP_MAP_3CHAR_AIRPORTNAME, airportNameMap,24*3600l);
	}
	/**
	 * 设置机型-机型名称 map
	 */
	public static void setPLANETYPE(){
		Map<String, String> planeTypeMap = service.getPlaneTypeMap();
		RedisCacheManager.save(Const.APP_MAP_PLANETYPE, planeTypeMap,24*3600l);
	}
	
	/**
	 * 设置机型-机型名称 map
	 * @param endorses
	 */
	public static void setPLANETYPE(Map<String, String> planeTypeMap){
		RedisCacheManager.save(Const.APP_MAP_PLANETYPE, planeTypeMap,24*3600l);
	}
	/**
	 * 设置 航空公司二字码_舱位码-舱位名称  map
	 */
	public static void setCABINNAME(){
		Map<String, String> cabinNameMap = service.getCompanyCabinname();
		RedisCacheManager.save(Const.APP_MAP_CABINNAME, cabinNameMap,24*3600l);
	}
	
	/**
	 * 设置 航空公司二字码_舱位码-舱位名称  map
	 * @param endorses
	 */
	public static void setCABINNAME(Map<String, String> cabinNameMap){
		RedisCacheManager.save(Const.APP_MAP_CABINNAME, cabinNameMap,24*3600l);
	}
	/**
	 * 设置 航空公司二字码_舱位名称-舱位码list  map
	 */
	public static void setCABINCODE(){
		Map<String, List<String>> cabinCodeMap = service.getCompanyCabinCode();
		RedisCacheManager.save(Const.APP_MAP_CABINCODE, cabinCodeMap,24*3600l);
	}
	
	/**
	 * 设置 航空公司二字码_舱位名称-舱位码list  map
	 * @param endorses
	 */
	public static void setCABINCODE(Map<String, List<String>> cabinCodeMap){
		RedisCacheManager.save(Const.APP_MAP_CABINCODE, cabinCodeMap,24*3600l);
	}
	/**
	 * 设置 航空公司二字码-航空公司简称  map
	 */
	public static void setAC2NAME(){
		Map<String, String> ac2NameMap = service.getAC2NameMap();
		RedisCacheManager.save(Const.APP_MAP_AC2NAME, ac2NameMap,24*3600l);
	}
	
	/**
	 * 设置 航空公司二字码-航空公司简称  map
	 * @param endorses
	 */
	public static void setAC2NAME(Map<String, String> ac2NameMap){
		RedisCacheManager.save(Const.APP_MAP_AC2NAME, ac2NameMap,24*3600l);
	}
	/**
	 * 设置 结算类型code-name map
	 */
	public static void setPAYTYPE(){
		Map<String, String> payTypeMap = service.getPayTypeMap();
		RedisCacheManager.save(Const.APP_MAP_PAYTYPE, payTypeMap,24*3600l);
	}
	
	/**
	 * 设置 结算类型code-name map
	 * @param endorses
	 */
	public static void setPAYTYPE(Map<String, String> payTypeMap){
		RedisCacheManager.save(Const.APP_MAP_PAYTYPE, payTypeMap,24*3600l);
	}
	
	/**
     * 设置 结算类型code-name map
     */
    public static void setDICTTYPECODE(){
        Map<String, String> idcardTypeMap = service.getIdcardTypeMap();
        RedisCacheManager.save(Const.APP_MAP_IDCARDTYPE, idcardTypeMap,24*3600l);
    }
    
    /**
     * 设置 结算类型code-name map
     * @param endorses
     */
    public static void setDICTTYPECODE(Map<String, String> idcardTypeMap){
        RedisCacheManager.save(Const.APP_MAP_IDCARDTYPE, idcardTypeMap,24*3600l);
    }
	
    /**
     * 设置 结算类型code-name map
     */
    public static void setPERSONNELTYPE(){
        Map<String, String> personnelTypeMap = service.getPersonnelTypeMap();
        RedisCacheManager.save(Const.APP_MAP_PERSONNELTYPE, personnelTypeMap,24*3600l);
    }
    
    /**
     * 设置 结算类型code-name map
     * @param endorses
     */
    public static void setPERSONNELTYPE(Map<String, String> personnelTypeMap){
        RedisCacheManager.save(Const.APP_MAP_PERSONNELTYPE, personnelTypeMap,24*3600l);
    }
}
