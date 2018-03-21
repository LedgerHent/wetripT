package com.viptrip.pay.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.pay.PayClient;
import com.viptrip.pay.vo.PayRefundResp;
import com.viptrip.pay.vo.PayResp;
import com.viptrip.pay.vo.Request_QueryStatus;
import com.viptrip.pay.vo.Response_QueryStatus;
import com.viptrip.util.StringUtil;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class QueryStatus extends PayClient<Request_QueryStatus,Response_QueryStatus> {
    @Override
    protected OutputSimpleResult DataValid(Request_QueryStatus para) {
        OutputSimpleResult osr = new OutputSimpleResult();
        if(null==para || null==para.orderId ){
            osr.result="参数不全";
        }else{
            osr.code = Constant.Code_Succeed;
            osr.result = "成功";
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_QueryStatus, String> DoBusiness(Request_QueryStatus para) {
        OutputResult<Response_QueryStatus, String> or = new OutputResult<>();
        or.code = Constant.Code_Succeed;
        com.viptrip.pay.service.Pay pay = ApplicationContextHelper.getInstance().getBean(com.viptrip.pay.service.Pay.class);
        PayResp payResp = null;
        if(null == para.payType){
            payResp = pay.queryPayStatus(para.orderId);
        }else{
            payResp = pay.queryPayStatus(para.payType, para.orderId);
        }
        Response_QueryStatus result = new Response_QueryStatus(payResp.getCode(),payResp.getMsg());
        or.setResultObj(result);
        return or;
    }
}
