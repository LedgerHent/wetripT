package com.viptrip.intlAirticket.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderList;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlOrderListInfo;
import com.viptrip.intlAirticket.service.BookIntlOrderService;
import com.viptrip.intlAirticket.service.IntlOrderDetailService;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetIntlOrderDetail extends IntlTicketClient<Request_GetIntlOrderDetail, Response_GetIntlOrderDetail> {

    @Override
    protected OutputSimpleResult DataValid(Request_GetIntlOrderDetail para) {
        OutputSimpleResult osr=new OutputSimpleResult();
        osr.code = 0;
        if (null == para || para.getOrderID() == null) {
            osr.code = Msg.IncompletePara.getCode();
            osr.result = Msg.IncompletePara.getInfo();
            return osr;
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_GetIntlOrderDetail, String> DoBusiness(Request_GetIntlOrderDetail para) {
        OutputResult<Response_GetIntlOrderDetail, String> or = new OutputResult<Response_GetIntlOrderDetail, String>();
        IntlOrderDetailService service = ApplicationContextHelper.getInstance().getBean(IntlOrderDetailService.class);
        Response_GetIntlOrderDetail data = service.getIntlOrderDetail(para);
        or.setResultObj(data);
        or.code = Msg.Success.getCode();
        return or;    
     }

}
