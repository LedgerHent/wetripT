package com.viptrip.wetrip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_RescheduledUpgradeFee;
import com.viptrip.wetrip.model.Response_RescheduledUpgradeFee;
import com.viptrip.wetrip.service.BookOrderService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 计算升舱费用接口
 * @author jetty
 *
 */
public class RescheduledUpgradeFee extends TicketClient<Request_RescheduledUpgradeFee, Response_RescheduledUpgradeFee> {

    @Override
    protected OutputSimpleResult DataValid(Request_RescheduledUpgradeFee req) {
        OutputSimpleResult osr = new OutputSimpleResult();
        osr.code = Msg.Success.Success.getCode();
        if(req==null){
            osr.code=Msg.IncompletePara.getCode();
        }else{
            if(StringUtils.isEmpty(req.getOrderno())){
                osr.code=Msg.IncompletePara.getCode();
                osr.result="订单号不能为空";
            }
            if(req.getTicketNumbers()==null || req.getTicketNumbers().size()==0){
                osr.code=Msg.IncompletePara.getCode();
                osr.result="选择要改期的票号不能为空";
            }
            if(req.getChangeTripinfos()==null || req.getChangeTripinfos().size()==0){
                osr.code=Msg.IncompletePara.getCode();
                osr.result="选择的新航班数据不能为空";
            }
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_RescheduledUpgradeFee, String> DoBusiness(Request_RescheduledUpgradeFee req) {
        OutputResult<Response_RescheduledUpgradeFee, String> outreslut=new OutputResult<Response_RescheduledUpgradeFee, String>();
        Response_RescheduledUpgradeFee res=new Response_RescheduledUpgradeFee();
        // 获取service层实例
        BookOrderService bookOrderService = ApplicationContextHelper.getInstance().getBean(BookOrderService.class);
        HashMap<String, Double> changeTrips=new HashMap<String, Double>();
        for (int i = 0; i < req.getChangeTripinfos().size(); i++) {
            changeTrips.put(req.getChangeTripinfos().get(i).getFlightStart()+req.getChangeTripinfos().get(i).getFlightEnd(),req.getChangeTripinfos().get(i).ticketPrice);
        }
        HashMap<String, Double> feeMap=(HashMap<String, Double>) bookOrderService.getRescheduledUpgradeFeeMap(req.getOrderno(), req.getTicketNumbers(), changeTrips);
        List<Double> fees=new ArrayList<Double>();
        
        for(String numbers:req.getTicketNumbers()){
            Double e = feeMap.get(numbers);
            if(e==-1000000){
                outreslut.code=Msg.IncompletePara.getCode();
                outreslut.result="订单号与票号不匹配，未查询到相关数据。";
                break;
            }
            if(e.doubleValue()<0){
                fees.add(0d);
            }else
                fees.add(e);
        }
        outreslut.code=etuf.v1_0.common.Constant.Code_Succeed;
        res.status=etuf.v1_0.common.Constant.Code_Succeed;
        res.setUpgradeFees(fees);
        outreslut.setResultObj(res);
        return outreslut;
    }

}
