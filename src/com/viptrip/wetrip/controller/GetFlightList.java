package com.viptrip.wetrip.controller;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.Response_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.flight.type.TripType;
import com.viptrip.wetrip.service.IFlightService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetFlightList extends TicketClient<Request_GetFlightList, Response_GetFlightList>{
	
	@Override
	protected OutputSimpleResult DataValid(Request_GetFlightList para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code = 0;
		if(null==para || null==para.getData()){
			osr.code = 1;
			osr.result = Msg.IncompletePara.getInfo();
			return osr;
		}else{
			ReqData_GetFlightList data = para.getData();
//			Integer userId = para.getUserId();
			if(null==data.getTripType()){//航程类型
				osr.code = 1;
				osr.result = "航程类型不能为空";
				return osr;
			}else{
				Integer tripType = data.getTripType();
				TripType type = TripType.getTripType(tripType);
				List<ReqData_GetFlightList_TripInfo> tripInfo = data.getTripInfo();
				if(null==tripInfo || tripInfo.size()<=0){
					osr.code = 1;
					osr.result = "查询条件不完整";
					return osr;
				}else{
					for(int i=0;i<tripInfo.size();i++){
						ReqData_GetFlightList_TripInfo info = tripInfo.get(0);
						if(null==info.getFlowId() || info.getFlowId()<=0){//设置航程id
							info.setFlowId(i+1);
						}
						String date = info.getDate();
						String depAirport = info.getDepAirport();
						String arrAirport = info.getArrAirport();
						if(StringUtil.isEmpty(depAirport)){
							osr.code = 1;
							osr.result = "起飞城市不正确";
							return osr;
						}else if(StringUtil.isEmpty(arrAirport)){
							osr.code = 1;
							osr.result = "到达城市不正确";
							return osr;
						}
						if(!(Pattern.matches("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", date)||Pattern.matches("^\\d{8}$", date))){
							osr.code = 1;
							osr.result = "日期格式不正确";
							return osr;
						}
						/*try {
//							DateUtil.SDF_yyyyOMMOdd.parse(date);
								
							}
						} catch (ParseException e) {
							osr.code = 1;
							osr.result = "日期格式不正确";
							return osr;
						}*/
					}
				}
			}
			
		}
		return osr;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected OutputResult<Response_GetFlightList, String> DoBusiness(
			Request_GetFlightList para) {
		OutputResult<Response_GetFlightList, String> or = new OutputResult<>();
//		Integer userId = para.getUserId();
		
		List<RespData_GetFlightList> list = null;
		
//		String key = new Gson().toJson(para);
//		if(RedisCacheManager.exists(key)){//读取缓存
//			TypeToken<List<RespData_GetFlightList>> typeToken = new TypeToken<List<RespData_GetFlightList>>(){};
//			list = (List<RespData_GetFlightList>) RedisCacheManager.get(key, typeToken.getClass());
//		}else{//读库
			IFlightService service = ApplicationContextHelper.getInstance().getBean(IFlightService.class);
			list = service.getFlightList(para);
//			if(null!=list){
//				RedisCacheManager.save(key, list);//写入缓存
//			}
//		}
		
		
		
		Response_GetFlightList result = new Response_GetFlightList(list);
		or.setResultObj(result);
		or.code = Msg.Success.getCode();
		return or;
	}
	
}
