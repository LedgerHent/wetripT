package com.viptrip.hotel.controller;

import java.text.SimpleDateFormat;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_RefundByAlipay;
import com.viptrip.hotel.model.Response_RefundByAlipay;
import com.viptrip.hotel.model.pay.RefundByAlipay_Data;
import com.viptrip.hotel.service.IPayService;
import com.viptrip.hotel.service.impl.PayService;
import com.viptrip.pay.service.Pay;
import com.viptrip.pay.vo.PayRefundResp;
import com.viptrip.util.StringUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class RefundByAlipay extends HotelClient<Request_RefundByAlipay, Response_RefundByAlipay>{

	@Override
	protected OutputSimpleResult DataValid(Request_RefundByAlipay para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(null!=para){
			String orderNo = para.getOrderNo();
			Integer amount = para.getRefundAmount();
			if(!StringUtil.isEmpty(orderNo)&&null!=amount&&amount>=0){
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
	protected OutputResult<Response_RefundByAlipay, String> DoBusiness(
			Request_RefundByAlipay para) {
	    
//		IPayService payService = ApplicationContextHelper.getInstance().getBean(IPayService.class);
//		AlipayTradeRefundResponse resp = payService.aliRefund(para.getOrderNo(), para.getRefundAmount()/100D, para.getReason(), para.getSubNo());
		
//		RefundByAlipay_Data data = new RefundByAlipay_Data();
//		data.setCode(Integer.parseInt(resp.getCode()));
//		data.setMsg(resp.getMsg());
//		if("10000".equals(resp.getCode())){
//			if(StringUtil.isNotEmpty(resp.getOutTradeNo()) && StringUtil.isNotEmpty(resp.getRefundFee()) && null!=resp.getGmtRefundPay()){
//				data.setCode(0);
//				data.setOrderNo(resp.getOutTradeNo());
//				data.setRefundAmount((int)(Double.parseDouble(resp.getRefundFee())*100));
//				data.setRefundTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resp.getGmtRefundPay()));
//				data.setBuyerId(resp.getBuyerLogonId());
//			}
//		}
		/**
		 * 2018.02.24 改为PC端退款统一接口
		 */
		Pay pay = ApplicationContextHelper.getInstance().getBean(Pay.class);
		PayRefundResp resp=pay.onlineRefund(para.getOrderNo(), para.getSubNo(), para.getRefundAmount()/100D, para.getReason(), null);
        RefundByAlipay_Data data = new RefundByAlipay_Data();
        data.setCode(resp.getCode());
        data.setMsg(resp.getMsg());
        if(resp.getCode()==0){
            data.setCode(0);
            data.setOrderNo(para.getOrderNo());
            data.setRefundAmount((int)(resp.getRefundAmount()*100));
            data.setRefundTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resp.getRefundTime()));
            data.setBuyerId("");//
        }
		OutputResult<Response_RefundByAlipay, String> or = new OutputResult<>();
		or.code = Constant.Code_Succeed;
		or.setResultObj(new Response_RefundByAlipay(data));
		return or;
	}

}
