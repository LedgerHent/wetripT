package com.viptrip.test;

import com.viptrip.wetrip.controller.RescheduledPNR;
import com.viptrip.wetrip.model.Request_RescheduledPNR;
import com.viptrip.wetrip.model.Response_RescheduledPNR;
import com.viptrip.wetrip.model.flight.RespData_RescheduledPNR;
import com.viptrip.wetrip.vo.RescheduleObj;
import etuf.v1_0.model.base.output.OutputResult;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selfwhisper on 0012 2018/1/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestReschedulePNR {

    @org.junit.Test
    public void test(){

        //ChangeInfoObj result = null;

        String newFlightStart = "PVG";
        String newFlightEnd = "CAN";
        String newCangwei = "M";
        String newdate = "2018-01-26 ";
        String newFlightNo = "CZ3546";
        Double newPrice = 1130.00;
        Double changeFee = 0.0;
        Double upgradeFee = 0.0;
        Double serviceFee = 10.0;
        Long ticketId = 106128L;
        Request_RescheduledPNR req = new Request_RescheduledPNR();
        List<RescheduleObj> list = new ArrayList<>();
        RescheduleObj obj = new RescheduleObj();
        obj.setNewArrCity(newFlightEnd);
        obj.setNewOrgCity(newFlightStart);
        obj.setNewCangwei(newCangwei);
        obj.setNewDate(newdate);
        obj.setNewFlightNo(newFlightNo);
        obj.setNewPrice(newPrice);
        obj.setRescheduleFee(changeFee);
        obj.setServiceFee(serviceFee);
        obj.setUpgradeFee(upgradeFee);
        List<Long> tids = new ArrayList<>();
        tids.add(ticketId);
        obj.setTids(tids);
        list.add(obj);
        req.setRescheduleList(list);
        OutputResult<Response_RescheduledPNR, String> response_rescheduledPNRStringOutputResult = new RescheduledPNR().ClientRequest(req,Response_RescheduledPNR.class);
        if(null!=response_rescheduledPNRStringOutputResult && response_rescheduledPNRStringOutputResult.IsSucceed()){
            Response_RescheduledPNR resultObj = response_rescheduledPNRStringOutputResult.getResultObj();
            List<RespData_RescheduledPNR> pnrList = resultObj.getPnrList();
            if(null!=pnrList && pnrList.size()>0){
                RespData_RescheduledPNR respData_rescheduledPNR = pnrList.get(0);
                System.out.println(respData_rescheduledPNR.getNewPnr());
                System.out.println(respData_rescheduledPNR.getNewTravelItineraryNo());
            }
        }
    }

}
