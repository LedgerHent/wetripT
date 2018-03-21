package com.viptrip.wetrip.service;

import java.util.List;

import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_DelPassenger;
import com.viptrip.wetrip.model.Request_ListPassenger;

/**
 * 常旅客查询
 * @author t420
 *
 */
public interface IPassengerService {

	/**
	 * 获取常旅客信息
	 * @param para
	 * @param pp
	 * @return
	 */
	

    public Integer addPassenger(TTravelPassenger para);
    
    public void deletePassenger(Request_DelPassenger para);
    
    public void updatePassenger(TTravelPassenger para);
    
    public TTravelPassenger getOneById(long id);

	public List<TTravelPassenger> getListPassenger(Request_ListPassenger para);

    public List<TTravelPassenger> getPassByIdcard(String idcard,long userid);
    
    public void updatePersonalInfo(TTravelPassenger para);
    
}
