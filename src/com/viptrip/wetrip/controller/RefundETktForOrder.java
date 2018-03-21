package com.viptrip.wetrip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.ibeserver.service.ReadIBEManagerService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TTicketRefund;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.Request_RefundETktForOrder;
import com.viptrip.wetrip.model.Response_RefundETktForOrder;
import com.viptrip.wetrip.model.Response_RescheduledPNR;
import com.viptrip.wetrip.model.flight.ReqData_RefundETktForOrder_model;
import com.viptrip.wetrip.model.flight.RespData_RefundETktForOrder_model;
import com.viptrip.wetrip.service.BookOrderService;
import com.viptrip.wetrip.wsclient.TrfdResult;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class RefundETktForOrder extends TicketClient<Request_RefundETktForOrder, Response_RefundETktForOrder> {

    @Override
    protected OutputSimpleResult DataValid(Request_RefundETktForOrder req) {
        OutputSimpleResult osr = new OutputSimpleResult();
        osr.code = Msg.Success.Success.getCode();
        if (req == null) {
            osr.code = Msg.IncompletePara.getCode();
        } else {
            List<ReqData_RefundETktForOrder_model> objs = req.getRefundETktList();
            if (objs == null || objs.size() == 0) {
                osr.code = Msg.IncompletePara.getCode();
            }
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_RefundETktForOrder, String> DoBusiness(Request_RefundETktForOrder resp) {
        OutputResult<Response_RefundETktForOrder, String> result = new OutputResult<Response_RefundETktForOrder, String>();
        Response_RefundETktForOrder res = new Response_RefundETktForOrder();
        //响应数据
        List<RespData_RefundETktForOrder_model> resRefundETktList = new ArrayList<RespData_RefundETktForOrder_model>();

        List<ReqData_RefundETktForOrder_model> reqModelList = resp.getRefundETktList();// 请求对象集合
        BookOrderService bookOrderService = ApplicationContextHelper.getInstance().getBean(BookOrderService.class);
        ReadIBEManagerService readIBEService = ApplicationContextHelper.getInstance().getBean(
                ReadIBEManagerService.class);

        Map<String, List<ReqData_RefundETktForOrder_model>> reqOriginalMap = new HashMap<String, List<ReqData_RefundETktForOrder_model>>(); // 所有原始的要退票的票号
        Map<String, List<ReqData_RefundETktForOrder_model>> reqChangeStrMap = new HashMap<String, List<ReqData_RefundETktForOrder_model>>();// 所有改期的要退票的票号

        List<Long> tids = new ArrayList<Long>();// 所有要退票的TID
        for (int i = 0; i < reqModelList.size(); i++) {
            ReqData_RefundETktForOrder_model reqModel = reqModelList.get(i);
            tids.add(reqModel.getTid());
            String travelItineraryNo = reqModel.getTravelItineraryNo();
            // 数据库里对于往返航段在同一个票号里的数据加A区分
            if (travelItineraryNo.indexOf("A") > -1) {
                travelItineraryNo = travelItineraryNo.replace("A", "");
            }
            if (reqModel.getBusinessType() == 1) {
                if (reqOriginalMap.get(travelItineraryNo) == null) {
                    List<ReqData_RefundETktForOrder_model> models = new ArrayList<ReqData_RefundETktForOrder_model>();
                    models.add(reqModel);
                    reqOriginalMap.put(travelItineraryNo, models);
                } else {
                    List<ReqData_RefundETktForOrder_model> models = reqOriginalMap.get(travelItineraryNo);
                    models.add(reqModel);
                    reqOriginalMap.put(travelItineraryNo, models);
                }
            }
            if (reqModel.getBusinessType() == 2) {
                if (reqChangeStrMap.get(travelItineraryNo) == null) {
                    List<ReqData_RefundETktForOrder_model> models = new ArrayList<ReqData_RefundETktForOrder_model>();
                    models.add(reqModel);
                    reqChangeStrMap.put(travelItineraryNo, models);
                } else {
                    List<ReqData_RefundETktForOrder_model> models = reqChangeStrMap.get(travelItineraryNo);
                    models.add(reqModel);
                    reqChangeStrMap.put(travelItineraryNo, models);
                }
            }
        }

        // 获取所有---有退票的数据
        List<TTicketRefund> ttRefunds = bookOrderService.getTTicketRefundList(tids);

        // 获取所有申请退票的---原始数据
        List<TTravelItinerary> ttravels = bookOrderService.getTTIts(tids);

        // 获取所有申请退票的---改期数据
        List<TUpdateDate> updateDatas = bookOrderService.getTUpdateDates(tids);

        //处理原始订单退票
        dealOriginalRefund(resRefundETktList, readIBEService, reqOriginalMap, ttRefunds, ttravels);
        
        //处理改期订单退票
        dealChangeRefund(resRefundETktList, readIBEService, reqChangeStrMap, ttRefunds, updateDatas);
        
        result.code = Constant.Code_Succeed;
        res.setResRefundETktList(resRefundETktList);
        res.setMessage("");
        result.setResultObj(res);
        return result;
    }

    public void dealChangeRefund(List<RespData_RefundETktForOrder_model> resRefundETktList,
            ReadIBEManagerService readIBEService, Map<String,List<ReqData_RefundETktForOrder_model>> reqChangeStrMap, 
            List<TTicketRefund> ttRefunds,List<TUpdateDate> updateDatas) {
        
        Iterator<String> reqChangeKey = reqChangeStrMap.keySet().iterator();
        while (reqChangeKey.hasNext()) {
            String travelItineraryNo = reqChangeKey.next();
            List<ReqData_RefundETktForOrder_model> models = reqChangeStrMap.get(travelItineraryNo);
            boolean refunded = false;
//            TTicketRefund selectRefund = null;
            for (int j = 0; j < ttRefunds.size(); j++) {
                TTicketRefund refund = ttRefunds.get(j);
                if (refund.getTicketNo().contains(travelItineraryNo)) {
                    // 调用手动退票的逻辑方法
                    refunded = true;
//                    selectRefund = refund;
                }
            }
            //有相同票号的航段已经发生过退票记录
            if (refunded) {
                // 对退票对象的数据状态判断，是否进行在线退票。调用手动退票的逻辑方法
               /* selectRefund.getTicketNo();
                selectRefund.getIsChangedate();//是否为改期退票
                selectRefund.getRefundStatus();//1-申请 2-同意 3-拒绝 4-待航空公司退票*/ 
                
                String messageStr="同票号下有退票信息，转手工。";
                manualHandedRespModel(resRefundETktList, models, messageStr);
            } else {
                // 原始行程退票
                // 用于检查此票号是否为部分退票
                String oldstr="";
                for (int i = 0; i < updateDatas.size(); i++) {
                    oldstr+=updateDatas.get(i).getFlightStart()+"_"+ updateDatas.get(i).getFlightEnd();
                }
                
                double refund=0d;//净退款
                for (int j = 0; models!=null && j < models.size(); j++) {
                    String citys=models.get(j).getOrgcity()+"_"+models.get(j).getArrcity();
                    oldstr.replace(citys, "");
                    refund+=models.get(j).getNetRefund().doubleValue();
                }
                
                if(StringUtils.isEmpty(oldstr)){
                    if(models.size()==0){
                        //全部退票，一个票号只有一段的
                        dealOneFlight(resRefundETktList, readIBEService, travelItineraryNo, models, refund);
                    }else{
                        //全部申请退票,一个票号对应多段的处理
                        /*TrfdResult trfdResult=readIBEService.automaticRefund(travelItineraryNo);
                        if(trfdResult.getNetRefund()==refund){
                            String refundNumber =  readIBEService.confirmAutomaticRefund(travelItineraryNo, trfdResult);
                            
                        }*/
                        String messageStr="同票号下有多段信息全退，转手工。";
                        manualHandedRespModel(resRefundETktList, models, messageStr);
                    }
                }else{
                    //有部分航段(oldstr里存放)未申请退票 ，只能退对应的航段，手工处理。
                    String messageStr="同票号下有未申请退票航段，转手工。";
                    manualHandedRespModel(resRefundETktList, models, messageStr);
                }
            }
        }
    }

    /**
     * 处理单票单航段退票
     * @param resRefundETktList
     * @param readIBEService
     * @param travelItineraryNo
     * @param models
     * @param refund
     */
    public void dealOneFlight(List<RespData_RefundETktForOrder_model> resRefundETktList,
            ReadIBEManagerService readIBEService, String travelItineraryNo,
            List<ReqData_RefundETktForOrder_model> models, double refund) {
        TrfdResult trfdResult=readIBEService.automaticRefund(travelItineraryNo);
        RespData_RefundETktForOrder_model respModel=new RespData_RefundETktForOrder_model();
        respModel.setArrcity(models.get(0).getArrcity());
        respModel.setBusinessType(models.get(0).getBusinessType());
        respModel.setOrgcity(models.get(0).getOrgcity());
        respModel.setPnr(models.get(0).getPnr());
        respModel.setTid(models.get(0).getTid());
        respModel.setTravelItineraryNo(models.get(0).getTravelItineraryNo());
        if(trfdResult.getNetRefund()==refund){
            String refundNumber =  readIBEService.confirmAutomaticRefund(travelItineraryNo, trfdResult);
            respModel.setRefundno(refundNumber);
            respModel.setCode(Constant.Code_Succeed);
        }else{
            respModel.setCode(Constant.Code_Succeed);
            respModel.setRefundno("");
            respModel.setMessage("净退票价"+trfdResult.getNetRefund()+"与IBE票价"+refund+"有差。");
        }
        resRefundETktList.add(respModel);
    }

    public void dealOriginalRefund(List<RespData_RefundETktForOrder_model> resRefundETktList,
            ReadIBEManagerService readIBEService, Map<String, List<ReqData_RefundETktForOrder_model>> reqOriginalMap,
            List<TTicketRefund> ttRefunds, List<TTravelItinerary> ttravels) {
        Iterator<String> reqOriginalKey = reqOriginalMap.keySet().iterator();
        while (reqOriginalKey.hasNext()) {
            String travelItineraryNo = reqOriginalKey.next();
            List<ReqData_RefundETktForOrder_model> models = reqOriginalMap.get(travelItineraryNo);
            boolean refunded = false;
//            TTicketRefund selectRefund = null;
            for (int j = 0; j < ttRefunds.size(); j++) {
                TTicketRefund refund = ttRefunds.get(j);
                if (refund.getTicketNo().contains(travelItineraryNo)) {
                    // 调用手动退票的逻辑方法
                    refunded = true;
//                    selectRefund = refund;
                }
            }
            //有相同票号的航段已经发生过退票记录
            if (refunded) {
                // 对退票对象的数据状态判断，是否进行在线退票。调用手动退票的逻辑方法
                /*selectRefund.getTicketNo();
                selectRefund.getIsChangedate();//是否为改期退票
                selectRefund.getRefundStatus();//1-申请 2-同意 3-拒绝 4-待航空公司退票
                */        
                String messageStr="同票号下有退票信息，转手工。";
                manualHandedRespModel(resRefundETktList, models, messageStr);
            } else {
                // 原始行程退票
                // 用于检查此票号是否为部分退票
                String oldstr="";
                for (int i = 0; i < ttravels.size(); i++) {
                    oldstr+=ttravels.get(i).getFlightStart()+"_"+ ttravels.get(i).getFlightEnd();
                }
                
                double refund=0d;//净退款
                for (int j = 0; models!=null && j < models.size(); j++) {
                    String citys=models.get(j).getOrgcity()+"_"+models.get(j).getArrcity();
                    oldstr.replace(citys, "");
                    refund+=models.get(j).getNetRefund().doubleValue();
                }
                
                if(StringUtils.isEmpty(oldstr)){
                    if(models.size()==0){
                        dealOneFlight(resRefundETktList, readIBEService, travelItineraryNo, models, refund);
                    }else{
                        //全部申请退票,一个票号对应多段的处理
                        /*TrfdResult trfdResult=readIBEService.automaticRefund(travelItineraryNo);
                        if(trfdResult.getNetRefund()==refund){
                            String refundNumber =  readIBEService.confirmAutomaticRefund(travelItineraryNo, trfdResult);
                            
                        }*/
                        String messageStr="同票号下有多段信息全退，转手工。";
                        manualHandedRespModel(resRefundETktList, models, messageStr);
                    }
                }else{
                    //有部分航段(oldstr里存放)未申请退票 ，只能退对应的航段，手工处理。
                    String messageStr="同票号下有未申请退票航段，转手工。";
                    manualHandedRespModel(resRefundETktList, models, messageStr);
                }
            }
        }
    }

    /**
     * 转手工处理集合
     * @param resRefundETktList
     * @param models
     * @param messageStr
     */
    public void manualHandedRespModel(List<RespData_RefundETktForOrder_model> resRefundETktList,
            List<ReqData_RefundETktForOrder_model> models, String messageStr) {
        for (int j = 0; models!=null && j < models.size(); j++) {
            RespData_RefundETktForOrder_model respModel=new RespData_RefundETktForOrder_model();
            respModel.setArrcity(models.get(j).getArrcity());
            respModel.setBusinessType(models.get(j).getBusinessType());
            respModel.setOrgcity(models.get(j).getOrgcity());
            respModel.setPnr(models.get(j).getPnr());
            respModel.setRefundno("");
            respModel.setTid(models.get(j).getTid());
            respModel.setTravelItineraryNo(models.get(j).getTravelItineraryNo());
            respModel.code=Constant.Code_Failed;
            respModel.message=messageStr;
            resRefundETktList.add(respModel);
        }
    }

}
