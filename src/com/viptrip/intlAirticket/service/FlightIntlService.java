package com.viptrip.intlAirticket.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.poi.ss.formula.functions.T;

import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.intlAirticket.model.Request_GetIntlFlightList;
import com.viptrip.intlAirticket.model.Response_GetIntlFlightDetail;
import com.viptrip.intlAirticket.model.flightModels.CabinAndPrice;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.yeego.intlairticket.model.Response_GetAirRulesI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.model.base.output.OutputResult;

public interface FlightIntlService {
	
	/**
	 * @功能：加载登录用户信息
	 * @return
	 */
	public TAcUser getTAcUser(long userid);
	/**
	 * @功能：加载机构信息
	 * @param orgId
	 * @return
	 */
	public TAcOrg getOrgids(Long orgId);
	
	/**
	 * 查询航班信息
	 * @param req
	 * @return
	 */
	public OutputResult<Response_QueryWebFlightsI_1_0, String> queryIntlFlight(
			Request_GetIntlFlightList req);
	/**
	 * 获取航班key
	 * @param req
	 * @param ticketGroup
	 * @return
	 */
	public String getQueryIntlFlightKey(Request_GetIntlFlightList req,String ticketGroup);

	/**
	 * 根据航班key获取价格和舱位
	 */
	public CabinAndPrice getFlightCabinAndPrice(
			Response_QueryWebFlightsI_1_0 resq, String fKey,
			String passengerType);
	/**
     * 根据航班key和舱位获取价格
     */
    public CabinAndPrice getFlightCabinAndPrice(
            Response_QueryWebFlightsI_1_0 resq, String fKey,
            String passengerType,String cangwei);
	/**
	 * 返程航班列表查询
	 */
	public OutputResult<FlightResult,String> getFlightResult(Long userId,String mapKey,
			IntlAitTicketGetTargetFType type,String cangwei);
	
	public int getSeatsLeftByCabin(String exp, String cabin);
	/**
	 * 查询国际机票更多舱位
	 * @param result
	 * @return
	 */
	public OutputResult<Response_QueryWebFlightsI_1_0,String> getIntlCabinList(FlightResult result);
	/**
	 * 获取国际航班退改签
	 * @param result
	 * @param cabin
	 * @return
	 */
	public OutputResult<Response_GetAirRulesI_1_0,String> getIntlFlightRule(
			FlightResult result,String cabin);
	
	
}