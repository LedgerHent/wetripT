package com.viptrip.pay.controller;

import com.viptrip.pay.PayClient;
import com.viptrip.pay.vo.E;
import com.viptrip.pay.vo.PagePayTypeObj;
import com.viptrip.pay.vo.Request_QueryPayType;
import com.viptrip.pay.vo.Response_QueryPayType;
import com.viptrip.util.StringUtil;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

import java.util.ArrayList;
import java.util.List;

import static com.viptrip.pay.vo.E.PayType.*;

/**
 * Created by selfwhisper on 0031 2018/1/31.
 */
public class QueryPayType extends PayClient<Request_QueryPayType,Response_QueryPayType> {
    @Override
    protected OutputSimpleResult DataValid(Request_QueryPayType request_queryPayType) {
        OutputSimpleResult osr = new OutputSimpleResult();
        if(null==request_queryPayType || StringUtil.isEmpty(request_queryPayType.source)){
            osr.result="参数不全";
        }else{
            osr.code = Constant.Code_Succeed;
            osr.result = "成功";
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_QueryPayType, String> DoBusiness(Request_QueryPayType request_queryPayType) {
        OutputResult<Response_QueryPayType, String> or = new OutputResult<>();
        or.code = Constant.Code_Succeed;
        List<PagePayTypeObj> list =  new ArrayList<>();
        E.PayType[]  all =new E.PayType[]{
                支付宝H5, //0
                支付宝扫码付, //1
                支付宝APP, //2
                支付宝PC, //3
                微信H5, //4
                微信扫码付, //5
                微信APP, //6
                微信刷卡支付, //7
                微信公众号, //8
                微信小程序支付, //9
                农行H5, //10
                农行APP, //11
                农行PC, //12
                易宝H5, //13
                易宝PC, //14
                易宝扫码, //15
        };
        E.PayTypeSource payTypeSource = E.PayTypeSource.as(request_queryPayType.source);
        if (null == payTypeSource)
            payTypeSource = E.PayTypeSource.PC;
        switch (payTypeSource){
            case PC:
                list.add(new PagePayTypeObj(all[3].getCode(),"支付宝",true));
               // list.add(new PagePayTypeObj(all[12].getCode(),"农行快付",false));
                list.add(new PagePayTypeObj(all[14].getCode(),"易宝支付",true));
                break;
            case H5:
                list.add(new PagePayTypeObj(all[0].getCode(),"支付宝",true));
                list.add(new PagePayTypeObj(all[4].getCode(),"微信",true));
                list.add(new PagePayTypeObj(all[13].getCode(),"易宝支付",false));
               // list.add(new PagePayTypeObj(all[10].getCode(),"农行快付",false));
                break;
            case WX:
                list.add(new PagePayTypeObj(all[8].getCode(),"微信",true));
                break;
            case APP:
                list.add(new PagePayTypeObj(all[2].getCode(),"支付宝",true));
                list.add(new PagePayTypeObj(all[6].getCode(),"微信",true));
               // list.add(new PagePayTypeObj(all[11].getCode(),"农行快付",false));
                break;
        }
        Response_QueryPayType result = new Response_QueryPayType(list);
        or.setResultObj(result);
        return or;
    }
}
