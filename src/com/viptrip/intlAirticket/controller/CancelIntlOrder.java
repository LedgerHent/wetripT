package com.viptrip.intlAirticket.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.dao.IntlAirTicketComDao;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.model.Request_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Response_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Response_IntlAuditOrder;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlAuditOrder;
import com.viptrip.intlAirticket.service.IntlOrderDetailService;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
/**
 * 订单取消
 * @author jetty
 *
 */
public class CancelIntlOrder extends IntlTicketClient<Request_CancelIntlOrder, Response_CancelIntlOrder> {
    private static Logger logger = LoggerFactory.getLogger(CancelIntlOrder.class);
    @Override
    protected OutputSimpleResult DataValid(Request_CancelIntlOrder para) {
        OutputSimpleResult osr=new OutputSimpleResult();
        osr.code = 0;
        Long userid= para.userId;
        if (null == para || para.getOrderId()==0||userid==null) {
            osr.code = Msg.IncompletePara.getCode();
            osr.result = Msg.IncompletePara.getInfo();
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_CancelIntlOrder, String> DoBusiness(Request_CancelIntlOrder para) {
        OutputResult<Response_CancelIntlOrder, String> or = new OutputResult<Response_CancelIntlOrder, String>();
        IntlOrderDetailService service = ApplicationContextHelper.getInstance().getBean(IntlOrderDetailService.class);
        Response_CancelIntlOrder data= service.cancelIntlOrder(para);
        or.setResultObj(data);
        or.code = Msg.Success.getCode();
        return or;
    }

}
