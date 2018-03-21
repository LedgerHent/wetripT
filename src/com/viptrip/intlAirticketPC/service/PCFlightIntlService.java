package com.viptrip.intlAirticketPC.service;

import java.util.Hashtable;

import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticketPC.model.Request_GetIntlPCFlightList;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCCabinList;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCFlightList;
import com.viptrip.yeego.intlairticket.model.Request_QueryWebFlightsI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.model.base.output.OutputResult;

public interface PCFlightIntlService {
	/**
	 * 查询航班信息,包括航班详情和返程列表信息
	 * @param req
	 * @return
	 */
	public OutputResult<Response_QueryWebFlightsI_1_0, String> queryIntlFlight( Request_GetIntlPCFlightList req);
	
	public OutputResult<Response_QueryWebFlightsI_1_0,String> queryWebFlightsIntl(
			Request_QueryWebFlightsI_1_0 qryFlight);
	
	/**
	 * 获取查询国际航班的key，格式为：PEK_HKG_ADT_RT_2017-02-20_2017-02-25_C
	 */
	public String getQueryIntlFlightKey(String orgCityCode,
			String detCityCode, String psgType, String travelType,
			String goDatetime, String backDatetime,String ticketGroup);
	public String getQueryIntlFlightKey(Request_GetIntlPCFlightList req,String ticketGroup);

	/**
	 * 获取查询国际航班的key，格式为：PEK_HKG_ADT_RT_2017-02-20_2017-02-25
	 */
	public String getQueryIntlFlightKey(Request_GetIntlPCFlightList req);

	/**
	 * 将航司接口返回数据转换为接口对象model
	 * @param req 
	 * @return 
	 */
	public OutputResult<Response_GetIntlPCFlightList, String> transition2Object(Response_QueryWebFlightsI_1_0 resq, Request_GetIntlPCFlightList req);

	/**
	 * 更多舱位查询时，获取航班信息方法。
	 * @param userId
	 * @param mapKey 
	 * @param type 方法调用区分   rule--查询退改规则入口，null--查询更多舱位入口
	 * @param String 舱位信息
	 * @return
	 */
	public OutputResult<FlightResult, String> getFlightResult(Long userId, String mapKey, String type, String cangwei);

	/**
	 * 格式化返回舱位信息
	 * @param htFs
	 * @param result 
	 * @return
	 */
	public OutputResult<Response_GetIntlPCCabinList, String> formatResp4Cabin(
			Hashtable<String, Hashtable<String, Object[]>> htFs, FlightResult result);
	
	
}
