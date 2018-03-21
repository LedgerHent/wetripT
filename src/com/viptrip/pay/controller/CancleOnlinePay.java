package com.viptrip.pay.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.pay.PayClient;
import com.viptrip.pay.vo.PayResp;
import com.viptrip.pay.vo.Request_CancleOnlinePay;
import com.viptrip.pay.vo.Response_CancleOnlinePay;
import com.viptrip.util.StringUtil;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * Created by selfwhisper on 0023 2018/1/23.
 */
public class CancleOnlinePay extends PayClient<Request_CancleOnlinePay,Response_CancleOnlinePay>{
    @Override
    protected OutputSimpleResult DataValid(Request_CancleOnlinePay request_cancleOnlinePay) {
        OutputSimpleResult osr = new OutputSimpleResult();
        if(null!=request_cancleOnlinePay&& StringUtil.isEmpty(request_cancleOnlinePay.orderId)){
            osr.result = "参数不完整";
        }else{
            osr.result = "成功";
            osr.code = Constant.Code_Succeed;
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_CancleOnlinePay, String> DoBusiness(Request_CancleOnlinePay request_cancleOnlinePay) {
        OutputResult or = new OutputResult();
        or.code = Constant.Code_Succeed;
        com.viptrip.pay.service.Pay pay = ApplicationContextHelper.getInstance().getBean(com.viptrip.pay.service.Pay.class);
        PayResp payResp = pay.cancleOnlinePay(request_cancleOnlinePay.payType, request_cancleOnlinePay.orderId);
        Response_CancleOnlinePay cop = new Response_CancleOnlinePay(payResp.getCode(),payResp.getMsg());
        or.setResultObj(cop);
        return or;
    }
}
