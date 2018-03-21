package com.viptrip.intlAirticket.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_GetIntlFlightList;
import com.viptrip.intlAirticket.model.Response_GetIntlFlightList;
import com.viptrip.intlAirticket.model.flightModels.CabinAndPrice;
import com.viptrip.intlAirticket.model.flightModels.IntlFlightListResp;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 国际机票列表查询接口
 * @author Administrator
 *
 */
public class GetIntlFlightList extends 
				IntlTicketClient<Request_GetIntlFlightList, Response_GetIntlFlightList>{
	
	@Override
	protected OutputSimpleResult DataValid(Request_GetIntlFlightList req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.data.orgCity)){
			if(!Common.IsNullOrEmpty(req.data.detCity)){
				if(!Common.IsNullOrEmpty(req.data.startTime)){
					if(req.data.passengerType!=0){
						if(req.data.travelType!=0){
							if(req.data.travelType!=2){
								osr.code=Constant.Code_Succeed;
							}else if(req.data.travelType==2&&!Common.IsNullOrEmpty(req.data.arrviTime)){
								osr.code=Constant.Code_Succeed;
							}else osr.result="返程日期为空，请确认输入后重试。";
						}else osr.result="行程类型为空，请确认输入后重试。";
					}else osr.result="乘机人类型为空，请确认输入后重试。";
				}else osr.result="出发日期为空，请确认输入后重试。";
			}else osr.result="目的城市为空，请确认输入后重试。";
		}else osr.result="出发城市为空，请确认输入后重试。";
		return osr;
	}


	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	@Override
	protected OutputResult<Response_GetIntlFlightList, String> DoBusiness(Request_GetIntlFlightList req) {
		OutputResult<Response_GetIntlFlightList, String> out = 
				new OutputResult<Response_GetIntlFlightList, String>();
		TAcUser  user=null;
		TAcOrg  org =null;
		if(req.userId!=null && req.userId.longValue()>0){
			user = service.getTAcUser(req.userId);
			org =  service.getOrgids(user.getOrgid());
		}
		try {
			boolean isHaveRight=true;
			if(req.userId!=null && req.userId.longValue()>0 && "0".equals(org.getIntlServiceStatus())){
				isHaveRight=false;
			}
			if(isHaveRight){
				OutputResult<Response_QueryWebFlightsI_1_0, String> resultOut = service.queryIntlFlight(req);
				if (resultOut.code==Constant.Code_Succeed ){
					Response_QueryWebFlightsI_1_0 result = resultOut.getResultObj();
					if(result == null) {
						out.result="未获取到航班信息，请重新查询或联系客服。";
					}else{
						String commonKey = service.getQueryIntlFlightKey(req,result.ticketGroup);
						Response_GetIntlFlightList intlTripsList = 
								AnalysisIntlFlightList(result, result.flights.entrySet(), commonKey, false);
						out.setResultObj(intlTripsList);
						out.code=Constant.Code_Succeed;
					}
				}else out.result=resultOut.result;
			}else{
				out.result="未开启国际机票预订权限！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.result="系统异常，错误原因为："+e.getMessage();
		}
		
		
		
		return out;
	}

	@SuppressWarnings("rawtypes")
	private Response_GetIntlFlightList AnalysisIntlFlightList(Response_QueryWebFlightsI_1_0 resq,
			Set<Entry<String, Hashtable<String, Object[]>>> flightsTable,
			String commonKey,boolean isNeedReturnTrip) {
		// 获取到数据后 解析为返回对象
		// 航段、航程
		Response_GetIntlFlightList intlTripsList = new Response_GetIntlFlightList();
		intlTripsList.data = new ArrayList<IntlFlightListResp>();
		// 准备提交订单用参数字符串
		// PEK_HKG_ADT_RT_2017-02-20_2017-02-25|CA1234_MU520_FM1122*CA1235_MU521_FM1123
	  
		ArrayList<String> tempAl = new ArrayList<String>();
		// 解析易购结果
		for (Entry<String, Hashtable<String, Object[]>> entry : flightsTable) {
			String fKey = entry.getKey();// F1 F2 F3
			Object[] trip = entry.getValue().get(isNeedReturnTrip?"S2":"S1");// 去程，如果是往返行程，去程有相同的，需要过滤
			Object[] segments = ((ArrayList) trip[0]).toArray();
			Object[] flights = ((ArrayList) trip[1]).toArray();
			int zhongzhuanCount = flights.length - 1;// 中转次数
			Object[] firstFlight = ((ArrayList) flights[0]).toArray();
			String flightNo = firstFlight[1].toString();
			IntlFlightListResp f = new IntlFlightListResp();
			String tempKey = CommonMethodUtils.getZhongZhuanFlightNos(entry.getValue(), isNeedReturnTrip);
			ArrayList<String> cities = new ArrayList<String>();
			Object[] zhongzhuans = ((ArrayList) ((ArrayList) trip[0]).toArray()[13]).toArray();
			if (zhongzhuanCount > 0) {
				cities = CommonMethodUtils.getZhongZhuanCitys(resq,zhongzhuans);
			}

			if (!tempAl.contains(tempKey)) {// 相同航班可能存在不同地区的中转
				tempAl.add(tempKey);
			} else {
				continue;
			}
			f.mapKey=CommonMethodUtils.GetFormatMapkey(commonKey + "|" + tempKey);
			f.carrier=firstFlight[0].toString();
			f.carrierStr=CommonMethodUtils.getCarrierName(resq,f.carrier);
			f.airline=flightNo;
			f.orgAirPortStr=CommonMethodUtils.getAirportName(resq,segments[2].toString());
			f.orgTerm=segments[3].toString();
			f.start=CommonMethodUtils.getDateTime(segments[4].toString(),segments[5].toString());
			f.detAirPortStr=CommonMethodUtils.getAirportName(resq,segments[6].toString());
			f.detTerm=segments[7].toString();
			f.end=CommonMethodUtils.getDateTime(segments[8].toString(),segments[9].toString());
			f.planeType=CommonMethodUtils.getPlaneTypeName(resq,firstFlight[9].toString());
			f.transitCount=zhongzhuanCount;
			f.transitCities=cities;
			CabinAndPrice f1 = service.getFlightCabinAndPrice(
					resq,fKey,CommonMethodUtils.getPassengerType(commonKey));
			f.totalPrice=f1.getTotalPrice();
			f.totalTaxPrice = CommonMethodUtils.getDoubleStringWithoutDot(
					Double.valueOf(f1.getTotalPriceWithTax())- Double.valueOf(f1.getTotalPrice()));
			f.cangwei=f1.getCangwei();
			f.cangweiDesc=CommonMethodUtils.getCabinTypeName(resq, f1.getCangweiType());
			intlTripsList.data.add(f);
		}
		return intlTripsList;
	}
	
}
