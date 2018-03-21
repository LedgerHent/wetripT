package com.viptrip.intlAirticketPC.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.model.flightModels.CabinAndPrice;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.intlAirticketPC.IntlTicketPCClient;
import com.viptrip.intlAirticketPC.model.Request_GetIntlPCFlightList;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCFlightList;
import com.viptrip.intlAirticketPC.model.flightModels.HangCheng;
import com.viptrip.intlAirticketPC.model.flightModels.IntlPCFlightListResp;
import com.viptrip.intlAirticketPC.service.PCFlightIntlService;
import com.viptrip.intlAirticketPC.util.HangChengUtil;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.yeego.intlairticket.model.Request_QueryWebFlightsI_1_0;
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
public class GetIntlPCFlightList extends 
				IntlTicketPCClient<Request_GetIntlPCFlightList, Response_GetIntlPCFlightList>{
	private static Logger logger = LoggerFactory.getLogger(GetIntlPCFlightList.class); 
	@Override
	protected OutputSimpleResult DataValid(Request_GetIntlPCFlightList req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		
		if(!Common.IsNullOrEmpty(req.data.trip.segments.get(0).depAirport)){
			if(!Common.IsNullOrEmpty(req.data.trip.segments.get(0).arrAirport)){
				if(!Common.IsNullOrEmpty(req.data.trip.segments.get(0).date)){
					if(req.data.trip.type!=0){
						if(req.data.trip.type!=2){
							osr.code=Constant.Code_Succeed;
						}else if(req.data.trip.type==2&&!Common.IsNullOrEmpty(req.data.trip.segments.get(1).date)){
							osr.code=Constant.Code_Succeed;
						}else osr.result="返程日期为空，请确认输入后重试。";
					}else osr.result="行程类型为空，请确认输入后重试。";
				}else osr.result="出发日期为空，请确认输入后重试。";
			}else osr.result="目的城市为空，请确认输入后重试。";
		}else osr.result="出发城市为空，请确认输入后重试。";
							//TODO 校验
		return osr;
	}


	PCFlightIntlService servicePC = 
			ApplicationContextHelper.getInstance().getBean(PCFlightIntlService.class);
	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	@Override
	protected OutputResult<Response_GetIntlPCFlightList, String> DoBusiness(Request_GetIntlPCFlightList req) {
		OutputResult<Response_GetIntlPCFlightList, String> out = 
				new OutputResult<Response_GetIntlPCFlightList, String>();
		TAcUser  user=null;
		TAcOrg  org =null;
		if(req.userId!=null && req.userId.longValue()>0){
			user = service.getTAcUser(req.userId);
			org = null!=user?service.getOrgids(user.getOrgid()):null;
		}
		try {
			boolean isHaveRight=true;
			if(req.userId==null || null==org){
				out.result="用户ID错误，为查找到相关企业信息。请确认后重试。";
			}else{
				if(req.userId.longValue()>0 && "0".equals(org.getIntlServiceStatus())){
					isHaveRight=false;
				}
				if(isHaveRight){
					//查询航班信息
					Request_QueryWebFlightsI_1_0 qryFlight = new Request_QueryWebFlightsI_1_0();
					OutputResult<Response_QueryWebFlightsI_1_0, String> resultOut = servicePC.queryIntlFlight(req);
					if (resultOut.IsSucceed()){
						Response_QueryWebFlightsI_1_0 result = resultOut.getResultObj();
						if(result == null) {
							out.result="未获取到航班信息，请重新查询或联系客服。";
						}else{
							logger.info("国际机票列表查询接口====航班信息："+result);
							//响应信息格式化
							out = servicePC.transition2Object(result,req);
						}
					}else out.result=resultOut.result;
				}else{
					out.result="未开启国际机票预订权限！";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.result="系统异常，错误原因为："+e.getMessage();
		}
		
		return out;
	}

}
