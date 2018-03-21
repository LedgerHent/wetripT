package com.viptrip.intlAirticket.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.dao.IntlAirTicketComDao;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderList;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderList;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlOrderListInfo;
import com.viptrip.intlAirticket.service.BookIntlOrderService;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetIntlOrderList extends IntlTicketClient<Request_GetIntlOrderList, Response_GetIntlOrderList> {

    @Resource
    private IntlAirTicketComDao dao;
    
    
    
    private static Logger logger = LoggerFactory.getLogger(GetIntlOrderList.class);
    
    /**
     * @功能：加载登录用户信息
     * @return
     */
    public TAcUser getTAcUser(long userid) {
        TAcUser tAcUser = (TAcUser) dao.queryForEntity(userid, TAcUser.class);
        return tAcUser;
    }
    
    
    /**
     * @功能：加载总公司机构信息
     * @param orgId
     * @return
     */
    public TAcOrg getOrgids(Long orgId){
        
        TAcOrg org = null;
        org = (TAcOrg) dao.queryForEntity(orgId, TAcOrg.class);//子公司
        org = (TAcOrg) dao.queryForEntity(Long.parseLong(org.getCompany()), TAcOrg.class);//总公司

        return org==null?null:org;
    }
    
    @Override
    protected OutputSimpleResult DataValid(Request_GetIntlOrderList para) {
        OutputSimpleResult osr=new OutputSimpleResult();
        osr.code = 0;
        if (null == para || para.getData() == null) {
            osr.code = Msg.IncompletePara.getCode();
            osr.result = Msg.IncompletePara.getInfo();
        } else {
            Req_Data_IntlOrderListInfo  req_info= para.getData();
            if(null==req_info||req_info.getStatus()==null){
                osr.code = 1;
                osr.result = "请求参数不全";
                return osr;
            }
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_GetIntlOrderList, String> DoBusiness(Request_GetIntlOrderList para) {
        OutputResult<Response_GetIntlOrderList, String> or = new OutputResult<Response_GetIntlOrderList, String>();
        // FlightIntlService service =
        // ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
        BookIntlOrderService service = ApplicationContextHelper.getInstance().getBean(BookIntlOrderService.class);
        Response_GetIntlOrderList data = service.getIntlOrderList(para);
        // Response_BookIntlOrder result = new Response_BookIntlOrder(data);
        or.setResultObj(data);
        or.code = Msg.Success.getCode();
        return or;
    }

}
