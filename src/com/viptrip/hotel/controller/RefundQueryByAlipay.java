package com.viptrip.hotel.controller;

import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_RefundQueryByAlipay;
import com.viptrip.hotel.model.Response_RefundQueryByAlipay;
import com.viptrip.hotel.model.pay.RefundQueryByAlipay_Data;
import com.viptrip.hotel.service.IPayService;
import com.viptrip.hotel.service.impl.PayService;
import com.viptrip.util.StringUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class RefundQueryByAlipay extends HotelClient<Request_RefundQueryByAlipay, Response_RefundQueryByAlipay>{

	@Override
	protected OutputSimpleResult DataValid(Request_RefundQueryByAlipay para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(null!=para){
			String orderNo = para.getOrderNo();
			if(!StringUtil.isEmpty(orderNo)){
				osr.code = Constant.Code_Succeed;
				osr.result = "通过";
			}else{
				osr.code = Constant.Code_Failed;
				osr.result = "参数不合法";
			}
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_RefundQueryByAlipay, String> DoBusiness(
			Request_RefundQueryByAlipay para) {
		OutputResult<Response_RefundQueryByAlipay, String> or = new OutputResult<Response_RefundQueryByAlipay, String>();
		IPayService payService = ApplicationContextHelper.getInstance().getBean(IPayService.class);
		String orderno = para.getOrderNo();
		String subno = StringUtil.isEmpty(para.getSubNo())?para.getOrderNo():para.getSubNo();
		AlipayTradeFastpayRefundQueryResponse resp = payService.aliRefundQuery(orderno, subno);
		RefundQueryByAlipay_Data data = new RefundQueryByAlipay_Data(Integer.parseInt(resp.getCode()), resp.getMsg());
		if("10000".equals(resp.getCode()) && !StringUtil.isEmpty(resp.getTradeNo())){
			if(StringUtil.isNotEmpty(resp.getOutTradeNo()) && StringUtil.isNotEmpty(resp.getOutRequestNo()) && StringUtil.isNotEmpty(resp.getRefundReason()) && null != resp.getRefundAmount()){
				data.setCode(0);
				data.setOrderNo(resp.getOutTradeNo());
				data.setSubNo(resp.getOutRequestNo());
				data.setRefundReason(resp.getRefundReason());
				data.setRefundAmount((int)(Double.parseDouble(resp.getRefundAmount())*100));
				data.setAmount((int)(Double.parseDouble(resp.getTotalAmount())*100));
			}
		}
		or.code = Constant.Code_Succeed;
		or.setResultObj(new Response_RefundQueryByAlipay(data));
		return or;
	}

}
