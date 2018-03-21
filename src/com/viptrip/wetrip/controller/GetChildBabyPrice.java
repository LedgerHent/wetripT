package com.viptrip.wetrip.controller;

import java.util.Arrays;

import com.google.gson.Gson;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_GetChildBabyPrice;
import com.viptrip.wetrip.model.Response_GetChildBabyPrice;
import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice;
import com.viptrip.wetrip.service.IFlightService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetChildBabyPrice extends TicketClient<Request_GetChildBabyPrice, Response_GetChildBabyPrice>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetChildBabyPrice para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code = 0;
		if(null == para || null == para.getMapKeys() || para.getMapKeys().length <= 0 || null == para.getType()){
			osr.code = 1;
			osr.result = Msg.IncompletePara.getInfo();
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetChildBabyPrice, String> DoBusiness(
			Request_GetChildBabyPrice para) {
		OutputResult<Response_GetChildBabyPrice, String> or = new OutputResult<>();
		RespData_GetChildBabyPrice childBabyPrice = null;
		
		String key = new Gson().toJson(para);
		if(RedisCacheManager.exists(key)){//读取缓存
			childBabyPrice = RedisCacheManager.get(key, RespData_GetChildBabyPrice.class);
		}else{//读取数据库
			IFlightService service = ApplicationContextHelper.getInstance().getBean(IFlightService.class);
			childBabyPrice = service.getChildBabyPrice(para);
			if(null!=childBabyPrice){
				RedisCacheManager.save(key, childBabyPrice);
			}
		}
		Response_GetChildBabyPrice result = new Response_GetChildBabyPrice(Arrays.asList(new RespData_GetChildBabyPrice[]{childBabyPrice}));
		or.setResultObj(result);
		or.code = Msg.Success.getCode();
		return or;
	}

}
