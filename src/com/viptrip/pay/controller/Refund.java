package com.viptrip.pay.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.pay.PayClient;
import com.viptrip.pay.vo.PayRefundResp;
import com.viptrip.pay.vo.Request_Refund;
import com.viptrip.pay.vo.Response_Refund;
import com.viptrip.util.StringUtil;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Refund extends PayClient<Request_Refund,Response_Refund> {
    @Override
    protected OutputSimpleResult DataValid(Request_Refund para) {
        OutputSimpleResult osr = new OutputSimpleResult();
        if(null==para || null==para.orderId || null==para.refundAmount || StringUtil.isEmpty(para.refundNo) || StringUtil.isEmpty(para.refundReason)){
            osr.result="参数不正确";
        }else{
            osr.code = Constant.Code_Succeed;
            osr.result = "成功";
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_Refund, String> DoBusiness(Request_Refund para) {
        OutputResult<Response_Refund, String> or = new OutputResult<>();
        or.code = Constant.Code_Succeed;
        com.viptrip.pay.service.Pay pay = ApplicationContextHelper.getInstance().getBean(com.viptrip.pay.service.Pay.class);
        HttpServletRequest req = para.httpServletRequest;
        Map<String,String> headers = StringUtil.getHeaders(req);
        PayRefundResp payRefundResp = null;
        if(null==para.payType){
            payRefundResp = pay.onlineRefund(para.orderId, para.refundNo, para.refundAmount, para.refundReason,headers);
        }else
            payRefundResp = pay.onlineRefund(para.payType, para.orderId, para.refundNo, para.refundAmount, para.refundReason,headers);
        Response_Refund result = new Response_Refund(payRefundResp.getCode(),payRefundResp.getMsg(),payRefundResp.getRefundAmount(),payRefundResp.getRefundTime());
        or.setResultObj(result);
        return or;
    }
}
