package com.viptrip.ibeserver.service;

import java.util.List;

import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.ibeserver.model.Request_IBEData;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.Request_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetReschedueFlightList;

/**
 * @描述 从IBE读取航班数据
 * @author wjt
 * 
 */
public interface ReadIBEDataService {
    /**
     * 从IBE接口获取数据的方法
     * 
     * @param sOCity
     * @param sDCity
     * @param sDate
     * @return
     */
    public List<DispplayTrip> getIBEData(String sOCity, String sDCity, String sDate, String pricetype, String airlines);

    /**
     * 从IBE接口获取数据列表
     * 
     * @param para
     * @return
     */
    public List<DispplayTrip> getIBEData(Request_GetFlightList para);
    
    /**
     * 从IBE接口获取数据列表，为改期提供的接口
     * @param para
     * @return
     */
    public List<DispplayTrip> getIBEData(Request_GetReschedueFlightList para);
    

    /**
     * 获取航班详情
     * 
     * @param flightKey
     *            OW_ADT_A_PEK_SHA_2017-04-15|CA1234
     * @return
     */
    public List<DispplayTrip> getIBEData(String flightKey,Long userid);

    /**
     * 获取预定要用的dispplayTrip
     * 
     * @param flightKey
     * @param userid
     * @return
     */
    public DispplayTrip getIBEDataForBook(String flightKey,Long userid);

    /**
     * 生成KEY左半部分-OW_ADT_C_PEK_SHA_20170504
     * 
     * @param para
     * @author wjt
     * @return
     */
    public String getLeftFlightKey(ReqData_GetFlightList para);

    /**
     * 航班列表KEY
     * 
     * @param leftKey
     * @param trip
     * @return
     */
    public String getDetailFlightListKey(String leftKey, DispplayTrip trip);

    public Request_IBEData getBaseFlightKey(String reqKeystr);

    public String getBaseFlightKey(ReqData_GetFlightList para);

    /**
     * 在字典表中查找传入三字码之外的机场三字码。传入的参数是逗号分隔的机场三字码
     * 
     * @param city3char
     * @return
     */
    public String getothercity(String city3char);
    
    public String getReschedueBaseFlightKey(ReqData_GetReschedueFlightList para);
    
    public String getReschedueLeftFlightKey(ReqData_GetReschedueFlightList para);
    
}
