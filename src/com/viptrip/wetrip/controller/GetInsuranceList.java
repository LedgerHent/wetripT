package com.viptrip.wetrip.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_GetInsuranceList;
import com.viptrip.wetrip.model.Response_GetInsuranceList;
import com.viptrip.wetrip.model.base.Response_PriceAndDescription;
import com.viptrip.wetrip.service.InsuranceService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetInsuranceList extends TicketClient<Request_GetInsuranceList, Response_GetInsuranceList>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetInsuranceList gil) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if (null == gil) {
			osr.result = Msg.IncompletePara.getInfo();
		} else {
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}

		return osr;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected OutputResult<Response_GetInsuranceList, String> DoBusiness(
			Request_GetInsuranceList gins) {
		 OutputResult<Response_GetInsuranceList, String> or = new OutputResult<Response_GetInsuranceList, String>();
         or.code = Constant.Code_Succeed;//成功标志
         
         List<Response_PriceAndDescription> gilist = null;
         String key = new Gson().toJson(gins);
	      if(RedisCacheManager.exists(key)){//读取缓存
	 			TypeToken<List<Response_PriceAndDescription>> typeToken = new TypeToken<List<Response_PriceAndDescription>>(){};
	 			gilist = (List<Response_PriceAndDescription>) RedisCacheManager.get(key, typeToken.getClass());
	 		}else{//读库
	 			InsuranceService service = ApplicationContextHelper.getInstance().getBean(InsuranceService.class);
	 			gilist = service.getInsuranceList();
	 			if(null!=gilist){
	 				RedisCacheManager.save(key, gilist);//写入缓存
	 			}
	 	}
         
//         InsuranceService service = ApplicationContextHelper.getInstance().getBean(InsuranceService.class);
//         gilist = service.getInsuranceList();
	      Response_GetInsuranceList gil = new Response_GetInsuranceList();
         gil.setData(gilist);
         or.setResultObj(gil);
         return or;
	}

}
