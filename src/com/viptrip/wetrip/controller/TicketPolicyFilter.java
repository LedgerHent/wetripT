package com.viptrip.wetrip.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.intlAirticket.controller.res.Msg;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_TicketPolicyFilter;
import com.viptrip.wetrip.model.Response_TicketPolicyFilter;
import com.viptrip.wetrip.service.PolicyManageService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 国内差旅管控统一调用方法
 * @author jetty
 *
 */
public class TicketPolicyFilter extends TicketClient<Request_TicketPolicyFilter, Response_TicketPolicyFilter> {

    @Override
    protected OutputSimpleResult DataValid(Request_TicketPolicyFilter req) {
        OutputSimpleResult osr = new OutputSimpleResult();
        osr.code=0;
        if(req.passengers==null || req.passengers.size()==0){
            osr.code=1;
            osr.result="出行人信息为空";
        }
        if(StringUtils.isEmpty(req.tripWay) || req.tripWay.equals("0")){
            osr.code=1;
            osr.result="因公因私为空";
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_TicketPolicyFilter, String> DoBusiness(Request_TicketPolicyFilter res) {
        OutputResult<Response_TicketPolicyFilter, String> outreslut=new OutputResult<Response_TicketPolicyFilter, String>();
        Response_TicketPolicyFilter resfilter=new Response_TicketPolicyFilter();
        // 获取service层实例
        PolicyManageService policyManageService = ApplicationContextHelper.getInstance().getBean(PolicyManageService.class);
        List<DispplayTrip> newEntitys=policyManageService.filterPolicyForTrips(res.userid,res.tripWay,res.passengers,res.entitys);
        resfilter.entitys=newEntitys;
        resfilter.status=etuf.v1_0.common.Constant.Code_Succeed;
        outreslut.code=Msg.Success.getCode();
        outreslut.setResultObj(resfilter);
        return outreslut;
    }
}
