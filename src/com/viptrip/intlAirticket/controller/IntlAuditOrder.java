package com.viptrip.intlAirticket.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.model.Request_IntlAuditOrder;
import com.viptrip.intlAirticket.model.Response_IntlAuditOrder;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlAuditOrder;
import com.viptrip.intlAirticket.service.IntlOrderDetailService;
import com.viptrip.util.DateUtil;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class IntlAuditOrder extends IntlTicketClient<Request_IntlAuditOrder, Response_IntlAuditOrder> {

    IntlOrderDetailService service = ApplicationContextHelper.getInstance().getBean(IntlOrderDetailService.class);
    private static Logger logger = LoggerFactory.getLogger(IntlAuditOrder.class);
    
    @Override
    protected OutputSimpleResult DataValid(Request_IntlAuditOrder para) {
        OutputSimpleResult osr=new OutputSimpleResult();
        osr.code = 0;
        Long userid= para.userId;
        if (null == para || para.getData() == null||userid==null) {
            osr.code = Msg.IncompletePara.getCode();
            osr.result = Msg.IncompletePara.getInfo();
        } else {
            Req_Data_IntlAuditOrder  req_info= para.getData();
            if(null==req_info||req_info.getOrderId()<=0 ||req_info.getType()==0){
                osr.code =  Msg.IncompletePara.getCode();;
                osr.result = "请求参数不全";
                logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss")+ "------>"+osr.result);
                return osr;
            }
          
            TAcUser tacUser=service.getTAcUser(userid);
            
            TAcOrg tAcOrg=service.getOrgids(tacUser.getOrgid());
            String strorgid=tAcOrg.getOrgid().toString();
            if ("121".equals(strorgid) || "2443".equals(strorgid)) {  //这里暂时写死，也许有更好的判断方式
                
            }else{
                //验证是否为此订单的待审核人
                TIntlOrder intlOrder=service.getIntlOrder(para.getData().getOrderId());
                if(!intlOrder.getIntlCheckordermen().equals(tacUser.getUsername())){
                    osr.code =  Msg.IncompletePara.getCode();;
                    osr.result = "订单记录的审核人与当前登录人不一致。";
                    logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss")+ "------>"+osr.result);
                    return osr;
                }
            }
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_IntlAuditOrder, String> DoBusiness(Request_IntlAuditOrder para) {
            OutputResult<Response_IntlAuditOrder, String> or = new OutputResult<Response_IntlAuditOrder, String>();
//            IntlOrderDetailService service2 = ApplicationContextHelper.getInstance().getBean(IntlOrderDetailService.class);
            Response_IntlAuditOrder data= service.intlAuditOrder(para);
            or.setResultObj(data);
            or.code = Msg.Success.getCode();
            return or;
    }

}
