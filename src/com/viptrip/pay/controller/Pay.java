package com.viptrip.pay.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.pay.PayClient;
import com.viptrip.pay.vo.E;
import com.viptrip.pay.vo.Request_Pay;
import com.viptrip.pay.vo.Response_Pay;
import com.viptrip.util.StringUtil;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.common.HttpHelper;
import etuf.v1_0.model.base.Config;
import etuf.v1_0.model.base.Enum;
import etuf.v1_0.model.base.Response_Base;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Pay extends PayClient<Request_Pay,Response_Pay> {

    private static Logger logger = LoggerFactory.getLogger(Pay.class);

    //public Integer payType;
    //public String orderId;
    //public Double price;
    //public String title;
    //public String body;
    //public String notifyURL;
    //public String returnURL;
    //public String ip;

    @Override
    protected OutputSimpleResult DataValid(Request_Pay request_pay) {
        OutputSimpleResult osr = new OutputSimpleResult();
        if(null == request_pay.payType || StringUtil.isEmpty(request_pay.orderId) || null==request_pay.price || StringUtil.isEmpty(request_pay.title) ||StringUtil.isEmpty(request_pay.notifyURL) || StringUtil.isEmpty(request_pay.returnURL)){
            osr.result = "参数不完整";
        }else{
            Integer payType = request_pay.payType;
            if(E.PayType.微信H5.getCode()/10 == payType/10 && StringUtil.isEmpty(request_pay.ip)){
                osr.result = "参数不完整";
            }else{
                osr.result = "成功";
                osr.code = Constant.Code_Succeed;
            }
        }
        return osr;
    }

    /*@Override
    protected OutputResult<Response_Pay, String> DoBusiness(Request_Pay request_pay) {
        OutputResult or = new OutputResult();
        or.code = Constant.Code_Succeed;
        com.viptrip.pay.service.Pay pay = ApplicationContextHelper.getInstance().getBean(com.viptrip.pay.service.Pay.class);
        try {
            pay.onlinePay(request_pay.httpServletRequest,request_pay.httpServletResponse,E.PayType.as(request_pay.payType),request_pay.orderId,request_pay.price,request_pay.title,request_pay.body,request_pay.notifyURL,request_pay.returnURL,request_pay.ip);
        } catch (IOException e) {
            logger.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
        }
        or.setResultObj(null);
        return or;
    }*/

    @Override
    protected OutputResult<Response_Pay, String> DoBusiness(Request_Pay request_pay) {
        OutputResult or = new OutputResult();
        or.code = Constant.Code_Succeed;
        com.viptrip.pay.service.Pay pay = ApplicationContextHelper.getInstance().getBean(com.viptrip.pay.service.Pay.class);
        HttpServletRequest request = request_pay.httpServletRequest;
        Map<String,String> headers = StringUtil.getHeaders(request);
        String s = pay.onlinePay(E.PayType.as(request_pay.payType), request_pay.orderId, request_pay.price, request_pay.title, request_pay.body, request_pay.notifyURL, request_pay.returnURL, request_pay.ip,headers);
        or.setResultObj(new Response_Pay(s));
        return or;
    }


}
