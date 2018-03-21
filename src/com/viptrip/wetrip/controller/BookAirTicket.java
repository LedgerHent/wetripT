package com.viptrip.wetrip.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_BookAirTicket;
import com.viptrip.wetrip.model.Response_BookAirTicket;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Contact;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_OverReason;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Trip;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;
import com.viptrip.wetrip.service.IFlightService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class BookAirTicket extends TicketClient<Request_BookAirTicket, Response_BookAirTicket>{

	@Override
	protected OutputSimpleResult DataValid(Request_BookAirTicket para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code = 0;
		if(null == para){
			osr.code = Msg.IncompletePara.getCode();
			osr.result = Msg.IncompletePara.getInfo();
		}else{
			Integer businessType = para.getBusinessType();
			Integer orderType = para.getOrderType();
			Integer source = para.getSource();
			String token = para.getToken();
			String tradePassword = para.getTradePassword();
			long userId = para.getUserId();
			ReqData_BookAirTicket data = para.getData();
			if(null==businessType || null==orderType || null==source || userId<=0 || null==data){
				osr.code = Msg.IncompletePara.getCode();
				osr.result = Msg.IncompletePara.getInfo();
			}else{
				Integer auditorId = data.getAuditorId();//审核人id
				Integer payMethod = data.getPayMethod();//支付方式
				Long timestamp = data.getTimestamp();//时间戳
				Integer tripType = data.getTripType();//航程类型
				List<ReqData_BookAirTicket_Trip> trips = data.getTrips();//航程信息
				List<ReqData_BookAirTicket_Passenger> passengers = data.getPassengers();//支付方式
				if(null==payMethod){
					osr.code = 1;
					osr.result = "支付方式不能为空";
					return osr;
				}
				if(null==tripType){
					osr.code = 1;
					osr.result = "航程类型不能为空";
					return osr;
				}
				if(null==timestamp){
					osr.code = 1;
					osr.result = "时间戳不能为空";
					return osr;
				}
				if(null==trips || trips.size()<=0){
					osr.code = 1;
					osr.result = "航程信息不能为空";
					return osr;
				}else{
					for(ReqData_BookAirTicket_Trip trip:trips){
						Integer flowId = trip.getFlowId();
						String mapKey = trip.getMapKey();
						if(null==flowId || StringUtil.isEmpty(mapKey)){
							osr.code = 1;
							osr.result = "航程信息不完整";
							return osr;
						}
					}
				}
				if(null==passengers || passengers.size()<=0){
					osr.code = 1;
					osr.result = "航程信息不能为空";
					return osr;
				}else{
					for(ReqData_BookAirTicket_Passenger p:passengers){
						Integer type = p.getType();
						Integer id = p.getId();
						Integer idType = p.getIdType();
						String idNum = p.getIdNum();
						Integer costCenter = p.getCostCenter();
						String projectNo = p.getProjectNo();
						Integer insuranceId = p.getInsuranceId();
						Integer insuranceNum = p.getInsuranceNum();
						if(null==type||null==id||null==idType||StringUtil.isEmpty(idNum)||null==costCenter||null==insuranceId||null==insuranceNum){
							osr.code = 1;
							osr.result= "旅客信息不完整";
							return osr;
						}
					}
				}
				List<ReqData_BookAirTicket_Contact> contacts = data.getContacts();//联系人信息
				List<ReqData_BookAirTicket_OverReason> overReason = data.getOverReason();//超标理由
				if(null!=contacts && contacts.size()>0){
					for(ReqData_BookAirTicket_Contact contact:contacts){
						String name = contact.getName();
						String mobile = contact.getMobile();
						if(StringUtil.isEmpty(name)){
							osr.code = 1;
							osr.result= "联系人信息不完整";
							return osr;
						}
					}
				}
				if(null!=overReason && overReason.size()>0){
					for(ReqData_BookAirTicket_OverReason or:overReason){
						Integer type = or.getType();
						String reason = or.getReason();
						if(null==type){
							osr.code = 1;
							osr.result= "超标理由不完整";
							return osr;
						}
					}
				}
			}
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_BookAirTicket, String> DoBusiness(
			Request_BookAirTicket para) {
		OutputResult<Response_BookAirTicket, String> or = new OutputResult<Response_BookAirTicket, String>();
		
		IFlightService service = ApplicationContextHelper.getInstance().getBean(IFlightService.class);
		RespData_BookAirTicket data = service.bookAirTicket(para);
		Response_BookAirTicket result = new Response_BookAirTicket(data);
		
		or.setResultObj(result);
		or.code = Msg.Success.getCode();
		return or;
	}
	

}
