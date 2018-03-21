package com.viptrip.wetrip.controller;

import java.util.List;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_GetPayMethodList;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.model.base.Response_BusinessType;
import com.viptrip.wetrip.model.base.Response_PriceAndDescription;
import com.viptrip.wetrip.service.InsuranceService;
import com.viptrip.wetrip.service.PayMethodService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetPayMethodList extends TicketClient<Request_GetPayMethodList, Response_GetPayMethodList>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetPayMethodList gpml) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if (null == gpml) {
			osr.result = Msg.IncompletePara.getInfo();
		} else {

			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}

		return osr;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected OutputResult<Response_GetPayMethodList, String> DoBusiness(
			Request_GetPayMethodList gpml) {
		 OutputResult<Response_GetPayMethodList, String> or = new OutputResult<Response_GetPayMethodList, String>();
         or.code = Constant.Code_Succeed;//成功标志
         
//         PayMethodService service = ApplicationContextHelper.getInstance().getBean(PayMethodService.class);
         Response_GetPayMethodList gpmlist = new Response_GetPayMethodList();
         
         List<Response_BusinessType> gpmList = null;
//         String key = new Gson().toJson(gpml);
//	      if(RedisCacheManager.exists(key)){//读取缓存
//	 			TypeToken<List<Response_BusinessType>> typeToken = new TypeToken<List<Response_BusinessType>>(){};
//	 			gpmList = (List<Response_BusinessType>) RedisCacheManager.get(key, typeToken.getClass());
//	 		}else{//读库
		 PayMethodService service = ApplicationContextHelper.getInstance().getBean(PayMethodService.class);
	 	 TAcUser user = service.findUser(gpml.getUserId());
		 gpmList = service.getPayMethodList(user.getOrgid(),gpml.getBusinessType());
//	 			if(null!=gpmList){
//	 				RedisCacheManager.save(key, gpmList);//写入缓存
//	 			}
//	 	}
         
         
//         //根据userID查询用户对象
//         TAcUser user = service.findUser(gpml.getUserId());
//         //根据用户企业机构查询可用结算方式
//         List<Response_BusinessType> gpmList = service.getPayMethodList(user.getOrgid());
         gpmlist.setData(gpmList);
         or.setResultObj(gpmlist);
         return or;
	}

}
