package com.viptrip.wetrip.controller;


import com.google.gson.Gson;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_GetFlightDetail;
import com.viptrip.wetrip.model.Response_GetFlightDetail;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail;
import com.viptrip.wetrip.service.IFlightService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetFlightDetail extends TicketClient<Request_GetFlightDetail, Response_GetFlightDetail>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetFlightDetail para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code = 0;
		if(null == para || StringUtil.isEmpty(para.getMapKey())){
			osr.code = 1;
			osr.result = Msg.IncompletePara.getInfo();
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetFlightDetail, String> DoBusiness(
			Request_GetFlightDetail para) {
		OutputResult<Response_GetFlightDetail, String> or = new OutputResult<>();
		
		RespData_GetFlightDetail flightDetail = null;
		
		String key = new Gson().toJson(para);
		if(RedisCacheManager.exists(key)){//读取缓存
			flightDetail = RedisCacheManager.get(key, RespData_GetFlightDetail.class);
		}else{//读取数据库
			IFlightService service = ApplicationContextHelper.getInstance().getBean(IFlightService.class);
			flightDetail = service.getFlightDetail(para);
			if(null!=flightDetail){
				RedisCacheManager.save(key, flightDetail);
			}
		}
		Response_GetFlightDetail result = new Response_GetFlightDetail(flightDetail);
		or.setResultObj(result);
		or.code = Msg.Success.getCode();
		return or;
	}

}
