package com.viptrip.wetrip.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_ListPassenger;
import com.viptrip.wetrip.model.Response_ListPassenger;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.impl.PassengerService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListPassenger extends
		TicketClient<Request_ListPassenger, Response_ListPassenger> {

	@Override
	protected OutputSimpleResult DataValid(Request_ListPassenger para) {

		OutputSimpleResult osr = new OutputSimpleResult();
		if (null == para || 0l == para.getUserId()) {
			osr.result = Msg.IncompletePara.getInfo();
		} else {

			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}

		return osr;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected OutputResult<Response_ListPassenger, String> DoBusiness(
			Request_ListPassenger para) {
		OutputResult<Response_ListPassenger, String> or = new OutputResult<Response_ListPassenger, String>();
		or.code = Constant.Code_Succeed;// 成功标志

		List<TTravelPassenger> tTravelP = null;

		/*String key = new Gson().toJson(para);
		if (RedisCacheManager.exists(key)) { // 读取缓存
			TypeToken<List<TTravelPassenger>> typeToken = new TypeToken<List<TTravelPassenger>>() {
			};
			tTravelP = (List<TTravelPassenger>) RedisCacheManager.get(key,
					typeToken.getClass());
		} else {
			PassengerService service = ApplicationContextHelper.getInstance()
					.getBean(PassengerService.class);
			tTravelP = service.getListPassenger(para);
			if (null != tTravelP) {
				RedisCacheManager.save(key, tTravelP);// 写入缓存
			}
		}*/

		PassengerService service = ApplicationContextHelper.getInstance()
				.getBean(PassengerService.class);
		tTravelP = service.getListPassenger(para);
		List<Req_Passenger> list1 = new ArrayList<Req_Passenger>();
		Response_ListPassenger passenger = new Response_ListPassenger();
		for (int i = 0; i < tTravelP.size(); i++) {
			List<ReqData_CertificateMessage> list2 = new ArrayList<ReqData_CertificateMessage>();
			TTravelPassenger passengers = tTravelP.get(i);
			ReqData_CertificateMessage reqMessage = new ReqData_CertificateMessage();
			Req_Passenger personMessage = new Req_Passenger();
			int intValue = passengers.getId().intValue();
			personMessage.setId(intValue);
			personMessage.setName(passengers.getName());
			personMessage.setMobile(passengers.getMobilephone());
			personMessage.setEmail(passengers.getEmail());
			personMessage.setSex(null);
			personMessage.setBirthday(null);
			String passengerType = passengers.getPassengerType();
			if (StringUtil.isEmpty(passengerType)
					|| "null".equals(passengerType)) {
				personMessage.setType(null);
			} else {
				personMessage.setType(Integer.valueOf(passengerType));
			}
			String idtype = passengers.getIdtype();
			if (StringUtil.isEmpty(idtype)) {
				reqMessage.setType(null);
			} else {
				reqMessage.setType(Integer.valueOf(idtype));
			}
			reqMessage.setNum(passengers.getIdnumber());
			list2.add(reqMessage);
			personMessage.setIds(list2);
			list1.add(personMessage);
		}
		passenger.setData(list1);
		or.setResultObj(passenger);
		return or;
	}
}
