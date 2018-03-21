package com.viptrip.wetrip.controller;

import java.util.List;
import java.util.regex.Pattern;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_GetReschedueFlightList;
import com.viptrip.wetrip.model.Response_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.ReqData_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.type.TripType;
import com.viptrip.wetrip.service.IFlightService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetReschedueFlightList extends TicketClient<Request_GetReschedueFlightList, Response_GetReschedueFlightList>{
	
	@Override
	protected OutputSimpleResult DataValid(Request_GetReschedueFlightList para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code = 0;
		if(null==para || null==para.getData()){
			osr.code = 1;
			osr.result = Msg.IncompletePara.getInfo();
			return osr;
		}else{
		    ReqData_GetReschedueFlightList data = para.getData();
//			Integer userId = para.getUserId();
		    if(null==data.getAirline()){//航程类型
                osr.code = 1;
                osr.result = "改期航班不能为空";
                return osr;
            }
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
	protected OutputResult<Response_GetReschedueFlightList, String> DoBusiness(
			Request_GetReschedueFlightList para) {
		OutputResult<Response_GetReschedueFlightList, String> or = new OutputResult<>();
		List<RespData_GetReschedueFlightList> list = null;
		IFlightService service = ApplicationContextHelper.getInstance().getBean(IFlightService.class);
		list = service.getReschedueFlight(para);
		Response_GetReschedueFlightList result = new Response_GetReschedueFlightList(list);
		or.setResultObj(result);
		or.code = Msg.Success.getCode();
		return or;
	}
	
}
