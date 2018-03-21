package com.viptrip.wetrip.service;

import java.util.List;
import java.util.Map;

import com.viptrip.wetrip.entity.TAcClass;
import com.viptrip.wetrip.entity.TEndorse;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.entity.TPreferentialRules;


public interface IComService{
	/**
	 * 获取 三字码-城市名 map
	 * @return
	 */
	public Map<String,String> get3CharCityMap();
	
	/**
	 * 获取 三字码-城市名 List
	 * @return
	 */
	public List<String> get3CharCity();
	
	/**
	 * 获取 三字码-机场名 map
	 * @return
	 */
	public Map<String,String> get3CharAirportNameMap();
	
	/**
	 * 获取 机型-机型名称 map
	 * @return
	 */
	public Map<String,String> getPlaneTypeMap();
	
	/**
	 * 获取 航空公司二字码_舱位码-舱位名称 map
	 * @return
	 */
	public Map<String,String> getCompanyCabinname();
	
	/**
	 * 获取 航空公司二字码_舱位码-舱位等级 map
	 * @return
	 */
	public Map<String,String> getCompanyCabinlevel();
	
	/**
	 * 获取 航空公司二字码_舱位名称-舱位代码list  map
	 * 例如 map的key为CA_经济舱 value为['W','T','S','V']
	 * 
	 * @return
	 */
	public Map<String,List<String>> getCompanyCabinCode();
	
	 /**
	  * 根据传入的实体查询优惠规则
	  * @return
	  */
	public List<TPreferentialRules> quyRules();
	
	/**
	 * 获取所有票规
	 * @return
	 */
	public List<TEndorse> getAllEndorse();
	
	/**
	 * 根据订单id获取联系人
	 * @param oid 订单id
	 * @param type 国内国外类型
	 * @return
	 */
	public List<TNotifyPartyInformation> getByContactsByOid(Long oid,Integer type);
	
	/**
     * 获取航司二字码-航司简称map
     * @return
     */
    public Map<String,String> getAC2NameMap();
    
    /**
     * 获取客户结算类型map
     * @return
     */
    public Map<String,String> getPayTypeMap();
    
    
    /**
     * 获取各航空公司舱位 对应的全价舱舱位
     * @return
     */
    public Map<String,TAcClass> getAllFullTacClass();
    
    /**
     * 获取乘客证件类型map
     * @return
     */
    public Map<String,String> getIdcardTypeMap();
    /**
     * 获取乘客类型Map
     * @return
     */
    public Map<String,String> getPersonnelTypeMap();
    
}
