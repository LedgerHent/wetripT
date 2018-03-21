package com.viptrip.wetrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.ibeserver.service.ReadIBEManagerService;
import com.viptrip.util.DateUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.Request_RescheduledPNR;
import com.viptrip.wetrip.model.Response_RescheduledPNR;
import com.viptrip.wetrip.model.flight.RespData_RescheduledPNR;
import com.viptrip.wetrip.service.BookOrderService;
import com.viptrip.wetrip.vo.RescheduleObj;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 在线改期处理方法，处理流程和规则：
 * 1、无效的PNR转手工改期,此次请求全部会转手工处理
 * 2、
 * @author jetty
 *
 */
public class RescheduledPNR extends TicketClient<Request_RescheduledPNR, Response_RescheduledPNR> {
    
    
    @Override
    protected OutputSimpleResult DataValid(Request_RescheduledPNR req) {
        OutputSimpleResult osr = new OutputSimpleResult();
        osr.code = Msg.Success.Success.getCode();
        if(req==null){
            osr.code=Msg.IncompletePara.getCode();
        }else{
            List<RescheduleObj>  objs=req.getRescheduleList();
            if(objs==null || objs.size()==0){
                osr.code=Msg.IncompletePara.getCode();
            }
        }
        return osr;
    }

    @Override
    protected OutputResult<Response_RescheduledPNR, String> DoBusiness(Request_RescheduledPNR req) {
        OutputResult<Response_RescheduledPNR, String> result = new OutputResult<Response_RescheduledPNR, String>();
        Response_RescheduledPNR res = new Response_RescheduledPNR();
        //返回对象集信息
        List<RespData_RescheduledPNR> respDatas= new ArrayList<RespData_RescheduledPNR>();
        BookOrderService bookOrderService =ApplicationContextHelper.getInstance().getBean(BookOrderService.class);
        ReadIBEManagerService readIBEService =ApplicationContextHelper.getInstance().getBean(ReadIBEManagerService.class);
        //二次改期原始数据
        List<TUpdateDate> allUpdateDate=new ArrayList<TUpdateDate>();
        
        //初次改期原始数据
        List<TTravelItinerary> allttravels=new ArrayList<TTravelItinerary>();
        
        //通过拿到的需要改期的信息，判断需要做哪个判断 1仅仅改日期  2改动PNR 3分离PNR再改日期或者PNR
        List<RescheduleObj> ListObjs=req.getRescheduleList();
        //获取所有的tid集合
        List<Long> tids=new ArrayList<Long>();
        for (int i = 0; i < ListObjs.size(); i++) {
            List<Long> tids_current=ListObjs.get(i).getTids();
            for (int j = 0; j < tids_current.size(); j++) {
                if(!tids.contains(tids_current.get(j))){
                    tids.add(tids_current.get(j));
                }
            }
        }
       
        //此次请求中的所有Tid原始数据
        List<TTravelItinerary>  allttis=bookOrderService.getTTIts(tids);
        Map<Long,TTravelItinerary> ttmap=new HashMap<Long, TTravelItinerary>();
        for (int i = 0; i < allttis.size(); i++) {
            TTravelItinerary ttravel=allttis.get(i);
            ttmap.put(ttravel.getTId(), ttravel);
        }
        
        //获取所有二次改期的数据
        HashMap<Long,Boolean> tid_current=new HashMap<Long,Boolean>();
        allUpdateDate=bookOrderService.getTUpdateDates(tids);
        for (int j = 0; allUpdateDate!=null && j < allUpdateDate.size(); j++) {
            tid_current.put(allUpdateDate.get(j).getTId(),true);
        }
        //获取是初次改期的信息数据
        if(tids.size()>tid_current.keySet().size()){
            List<Long> tidss=new ArrayList<Long>();
            for (int j = 0; j < tids.size(); j++) {
                if(tid_current.get(tids.get(j))==null || (tid_current.get(tids.get(j))!=null && !tid_current.get(tids.get(j)))){
                    tidss.add(tids.get(j));
                }
            }
            if(tidss.size()>0){
                List<TTravelItinerary> ttsList= bookOrderService.getTTIts(tidss);
                for (int j = 0; ttsList!=null && j < ttsList.size(); j++) {
                    allttravels.add(ttsList.get(j));
                }
            }
        }
        if((allUpdateDate==null || allUpdateDate.size()==0 )
                && (allttravels==null ||allttravels.size()==0) ){
            result.code=Constant.Code_Failed;
            res.setPnrList(null);
            res.code=etuf.v1_0.common.Constant.Code_Failed;
            result.setResultObj(res);
            return result;
        }
        
        //先判断所有涉及的PNR 是否都能改期
        HashMap<String,List<TUpdateDate>> currentUpdates=null;
        HashMap<String,List<String>> pnrListMap=new HashMap<String, List<String>>();
        if(allUpdateDate.size()>0){
            //对同是二次改期的数据 再根据PNR分组数据，再每组判断如何去处理改期。
            currentUpdates=getCurrentUpdateMap(allUpdateDate);
            Iterator<String> keys=currentUpdates.keySet().iterator();
            getPnrMessage(readIBEService, pnrListMap, keys);
        }
        HashMap<String,List<TTravelItinerary>> currentTTravels=null;
        if(allttravels.size()>0){
            currentTTravels=getCurrentTTravelsMap(allttravels);
            Iterator<String> keys=currentTTravels.keySet().iterator();
            getPnrMessage(readIBEService, pnrListMap, keys);
        }
        
        Iterator<String> pnrkeyIterators=pnrListMap.keySet().iterator();
        while(pnrkeyIterators.hasNext()){
            String nextPnr = pnrkeyIterators.next();
            if(pnrListMap.get(nextPnr)==null){
                if(currentUpdates!=null){
                    List<TUpdateDate> pnrTupdates= currentUpdates.get(nextPnr);
                    if(pnrTupdates!=null){
                        for(TUpdateDate tobj:pnrTupdates){
                            RespData_RescheduledPNR respData=new RespData_RescheduledPNR();
                            respData.setArrcity(tobj.getFlightEnd());
                            respData.setBusinessType(2);
                            respData.setMessage("PNR无效");
                            respData.setNewPnr("");
                            respData.setNewTravelItineraryNo("");
                            respData.setOrgcity(tobj.getFlightStart());
                            respData.setPnr(tobj.getNewPnr());
                            respData.setTid(tobj.getTId());
                            respData.setTravelItineraryNo(tobj.getChangeBanks());
                            respDatas.add(respData);
                        }
                    }
                }
                if(currentTTravels!=null){
                    List<TTravelItinerary> pnrTTI= currentTTravels.get(nextPnr);
                    if(pnrTTI!=null){
                        for(TTravelItinerary tobj:pnrTTI){
                            RespData_RescheduledPNR respData=new RespData_RescheduledPNR();
                            respData.setArrcity(tobj.getFlightEnd());
                            respData.setBusinessType(1);
                            respData.setMessage("PNR无效");
                            respData.setNewPnr("");
                            respData.setNewTravelItineraryNo("");
                            respData.setOrgcity(tobj.getFlightStart());
                            respData.setPnr(tobj.getTPnr().getPnr());
                            respData.setTid(tobj.getTId());
                            respData.setTravelItineraryNo(tobj.getTravelItineraryNo());
                            respDatas.add(respData);
                        }
                    }
                }
                
            }
        }
        if(respDatas!=null && respDatas.size()>0){
            result.code=etuf.v1_0.common.Constant.Code_Succeed;
            res.setPnrList(respDatas);
            res.setMessage("部分要改期的航程所在PNR过期，需要手工处理。");
            res.code=etuf.v1_0.common.Constant.Code_Failed;
            result.setResultObj(res);
            return result;
        }
        
        //处理二次改期的
        if(currentUpdates!=null && currentUpdates.size()>0){
            handleTUpdateDate(respDatas, readIBEService, ListObjs, ttmap, currentUpdates, pnrListMap);
        }
        
        //处理原始改期的
        if(currentTTravels!=null && currentTTravels.size()>0){
            handleTTravelItinerary(respDatas, readIBEService, ListObjs, ttmap, currentTTravels,pnrListMap);
        }
        result.code=Constant.Code_Succeed;
        res.setPnrList(respDatas);
        res.setMessage("");
        res.code=etuf.v1_0.common.Constant.Code_Succeed;
        result.setResultObj(res);
        return result;
    }
    /**
     * 处理原始改期的
     * @param respDatas
     * @param readIBEService
     * @param ListObjs
     * @param ttmap
     * @param currentTTravels
     * @param pnrListMap
     */
    public void handleTTravelItinerary(List<RespData_RescheduledPNR> respDatas, ReadIBEManagerService readIBEService,
            List<RescheduleObj> ListObjs, Map<Long, TTravelItinerary> ttmap,HashMap<String, List<TTravelItinerary>> currentTTravels
            , HashMap<String, List<String>> pnrListMap) {
        //对同是二次改期的数据 再根据PNR分组数据，再每组判断如何去处理改期。
        Iterator<String> keys=currentTTravels.keySet().iterator();
        while(keys.hasNext()){
            String keypnr=keys.next();//PNR
            List<String> pnrlist=pnrListMap.get(keypnr);
            boolean onlyChangeDate =true;
            //这一组数据是同一个PNR为Key的
            List<TTravelItinerary> tuobjss=currentTTravels.get(keypnr); //这个集合里 是同个PNR下的改期，即有去程也有回程，还可能有其他人不改期。
            for (int i = 0; i < tuobjss.size(); i++) {
                TTravelItinerary ttraveobj= tuobjss.get(i);
                RescheduleObj robjs=getRescheduleByTid(ListObjs, ttraveobj.getTId());
                onlyChangeDate=changeType(null,ttraveobj, robjs);
                if(!onlyChangeDate){
                    break;
                }
            }
            //1、判断PNR是否过期，如果过期需要重新预定PNR（本次处理 直接转手工处理，已经在前面做了校验处理）；
            //2、如果PNR不过期，PNR里有不需要改期的人存在，则需要分离PNR，再改航段信息
            String namenumbers=pnrlist.get(3);
            HashMap<String,TTravelItinerary> needchangePassengername=new HashMap<String,TTravelItinerary>();
            StringBuffer nameBuffer=new StringBuffer();
            //判断是否有不需要改期的人存在,
            for (int i = 0; i < tuobjss.size(); i++) {
                TTravelItinerary ttraveobj= tuobjss.get(i);
                String passengername=ttmap.get(ttraveobj.getTId()).getPassengerName();
                needchangePassengername.put(passengername,ttraveobj);
                String partStr = passengername+"_"+ttraveobj.getFlightStart()+ttraveobj.getFlightEnd()+"_"+ttraveobj.getTravelItineraryNo();
                if(namenumbers.indexOf(partStr)>-1){
                    nameBuffer.append(partStr);
                    namenumbers= namenumbers.replace(partStr + "|","");
                }
            }
            if(namenumbers.length()==0){
                //说明全部需要改期,无需分离PNR,
                String ok="";
                if(onlyChangeDate){
                    //没有产生升舱费用，仅仅是改期操作的情况
                    TTravelItinerary tUpdateDate = tuobjss.get(0);
                    RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                    String dateold=DateUtil.getDDMM(DateUtil.date2Str(tUpdateDate.getFlightTime(), "yyyy-MM-dd HH:mm:ss"));
                    String datenew=DateUtil.getDDMM(robjs.getNewDate());
                    //只修改航班号与日期即可
                    ok=readIBEService.changeDate(keypnr, tUpdateDate.getFlightNumber(), dateold, robjs.getNewFlightNo(), datenew);
                }else{
                    //产生升舱费用需要调用的改期方法逻辑--oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice
                    List<String> airsegStrs=new ArrayList<String>();
                    for(TTravelItinerary tUpdateDateobj:tuobjss){
                        RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDateobj.getTId());
                        String newstr=tUpdateDateobj.getFlightStart()+"_"+tUpdateDateobj.getFlightEnd()+"_"+robjs.getNewFlightNo()+"_"+
                                robjs.getNewCangwei()+"_"+robjs.getNewPrice()+"_"+tUpdateDateobj.getTaxPrice()+"_"+tUpdateDateobj.getFueltax()+
                                "_"+robjs.getNewDate()+"_"+String.valueOf(tUpdateDateobj.getTicketPrice().intValue());
                        if(!airsegStrs.contains(newstr)){
                            airsegStrs.add(newstr);
                        }
                    }
                    ok=readIBEService.changePnr(keypnr, airsegStrs);
                }
                for(TTravelItinerary tUpdateDateobj:tuobjss){
                    RespData_RescheduledPNR respData=new RespData_RescheduledPNR(tUpdateDateobj.getFlightEnd(),2,
                            tUpdateDateobj.getFlightStart(),tUpdateDateobj.getTPnr().getPnr(),tUpdateDateobj.getTId(),tUpdateDateobj.getTravelItineraryNo());
                    if(ok.indexOf("OK")>-1){
                        respData.setMessage("");
                        respData.setNewPnr(keypnr);
                        respData.setNewTravelItineraryNo(tUpdateDateobj.getTravelItineraryNo());
                    }else{
                        respData.setMessage("改期失败");
                        respData.setNewPnr("");
                        respData.setNewTravelItineraryNo("");
                    }
                    respDatas.add(respData);
                }
            }else{
                //说明部分需要改期,把需要改期的人从PNR分离出去
                String[] nameNumberList=namenumbers.split("\\|");
                for (int i = 0; i < nameNumberList.length; i++) {
                    String name=nameNumberList[i].split("_")[0];
                    TTravelItinerary tUpdateDate=needchangePassengername.get(name);
                    if(i == nameNumberList.length-1){
                        //直接改期，一般为分离后的最后一个人
                        String ok="";
                        if(onlyChangeDate){
                            RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                            String dateold=DateUtil.getDDMM(DateUtil.date2Str(tUpdateDate.getFlightTime(), "yyyy-MM-dd HH:mm:ss"));
                            String datenew=DateUtil.getDDMM(robjs.getNewDate());
                            ok=readIBEService.changeDate(keypnr, tUpdateDate.getFlightNumber(), dateold, robjs.getNewFlightNo(), datenew);
                        }else{
                            //否则需要改动舱位信息,如果产生升舱费用，需要改动舱位信息
                            //产生升舱费用需要调用的改期方法逻辑--oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice
                            List<String> airsegStrs=new ArrayList<String>();
                            RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                            String newstr=tUpdateDate.getFlightStart()+"_"+tUpdateDate.getFlightEnd()+"_"+robjs.getNewFlightNo()+"_"+
                                    robjs.getNewCangwei()+"_"+robjs.getNewPrice()+"_"+tUpdateDate.getTaxPrice()+"_"+tUpdateDate.getFueltax()+
                                    "_"+robjs.getNewDate()+"_"+String.valueOf(tUpdateDate.getTicketPrice().intValue());
                            if(!airsegStrs.contains(newstr)){
                                airsegStrs.add(newstr);
                            }
                            ok=readIBEService.changePnr(keypnr, airsegStrs);
                        }
                        RespData_RescheduledPNR respData=new RespData_RescheduledPNR(tUpdateDate.getFlightEnd(),2,
                                tUpdateDate.getFlightStart(),tUpdateDate.getTPnr().getPnr(),tUpdateDate.getTId(),tUpdateDate.getTravelItineraryNo());
                        if(ok.indexOf("OK")>-1){
                            respData.setMessage("");
                            respData.setNewPnr(tUpdateDate.getTPnr().getPnr());
                            respData.setNewTravelItineraryNo(tUpdateDate.getTravelItineraryNo());
                        }else{
                            respData.setMessage("改期失败");
                            respData.setNewPnr("");
                            respData.setNewTravelItineraryNo("");
                        }
                        respDatas.add(respData);
                    }else{
                        if(tUpdateDate!=null){
                            //分离PNR，再改期
                            String ok="";
                            String newPNR=readIBEService.splitPnrByName(keypnr, name, true);
                            if(newPNR!=null){
                                if(onlyChangeDate){
                                    RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                                    String dateold=DateUtil.getDDMM(DateUtil.date2Str(tUpdateDate.getFlightTime(), "yyyy-MM-dd HH:mm:ss"));
                                    String datenew=DateUtil.getDDMM(robjs.getNewDate());
                                    ok=readIBEService.changeDate(newPNR, tUpdateDate.getFlightNumber(), dateold, robjs.getNewFlightNo(), datenew);
                                }else{
                                  //否则需要改动舱位信息,如果产生升舱费用，需要改动舱位信息
                                  //产生升舱费用需要调用的改期方法逻辑--oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice
                                    List<String> airsegStrs=new ArrayList<String>();
                                    RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                                    String newstr=tUpdateDate.getFlightStart()+"_"+tUpdateDate.getFlightEnd()+"_"+robjs.getNewFlightNo()+"_"+
                                            robjs.getNewCangwei()+"_"+robjs.getNewPrice()+"_"+tUpdateDate.getTaxPrice()+"_"+tUpdateDate.getFueltax()+
                                            "_"+robjs.getNewDate()+"_"+String.valueOf(tUpdateDate.getTicketPrice().intValue());
                                    if(!airsegStrs.contains(newstr)){
                                        airsegStrs.add(newstr);
                                    }
                                    ok=readIBEService.changePnr(keypnr, airsegStrs);
                                }
                            }   
                            RespData_RescheduledPNR respData=new RespData_RescheduledPNR(tUpdateDate.getFlightEnd(),2,
                                    tUpdateDate.getFlightStart(),tUpdateDate.getTPnr().getPnr(),tUpdateDate.getTId(),tUpdateDate.getTravelItineraryNo());
                            if(ok.indexOf("OK")>-1){
                                respData.setMessage("");
                                respData.setNewPnr(newPNR);
                                respData.setNewTravelItineraryNo(tUpdateDate.getTravelItineraryNo());
                            }else{
                                respData.setMessage("改期失败,已经产生分离PNR:"+newPNR);
                                respData.setNewPnr(newPNR);
                                respData.setNewTravelItineraryNo("");
                            }
                            respDatas.add(respData);
                        }
                    }
                }
            }
        }
    }

    /**
     * 处理二次改期数据
     * @param respDatas
     * @param readIBEService
     * @param ListObjs
     * @param ttmap
     * @param currentUpdates
     * @param pnrListMap
     */
    public void handleTUpdateDate(List<RespData_RescheduledPNR> respDatas, ReadIBEManagerService readIBEService,
            List<RescheduleObj> ListObjs, Map<Long, TTravelItinerary> ttmap,
            HashMap<String, List<TUpdateDate>> currentUpdates, HashMap<String, List<String>> pnrListMap) {
        //对同是二次改期的数据 再根据PNR分组数据，再每组判断如何去处理改期。
        Iterator<String> keys=currentUpdates.keySet().iterator();
        while(keys.hasNext()){
            String keypnr=keys.next();//PNR
            List<String> pnrlist=pnrListMap.get(keypnr);
            
            boolean onlyChangeDate =true;
            //这一组数据是同一个PNR为Key的
            List<TUpdateDate> tuobjss=currentUpdates.get(keypnr); //这个集合里 是同个PNR下的改期，即有去程也有回程，还可能有其他人不改期。
            for (int i = 0; i < tuobjss.size(); i++) {
                TUpdateDate tupdatedate= tuobjss.get(i);
                RescheduleObj robjs=getRescheduleByTid(ListObjs, tupdatedate.getTId());
                onlyChangeDate=changeType(tupdatedate,null, robjs);
                if(!onlyChangeDate){
                    break;
                }
            }
            
            //1、判断PNR是否过期，如果过期需要重新预定PNR（本次处理 直接转手工处理，已经在前面做了校验处理）；
            //2、如果PNR不过期，PNR里有不需要改期的人存在，则需要分离PNR，再改航段信息
            String namenumbers=pnrlist.get(3);
            HashMap<String,TUpdateDate> needchangePassengername=new HashMap<String,TUpdateDate>();
            StringBuffer nameBuffer=new StringBuffer();
            //判断是否有不需要改期的人存在,
            for (int i = 0; i < tuobjss.size(); i++) {
                TUpdateDate tupdatedate= tuobjss.get(i);
                String passengername=ttmap.get(tupdatedate.getTId()).getPassengerName();
                needchangePassengername.put(passengername,tupdatedate);
                String partStr = passengername+"_"+tupdatedate.getFlightStart()+tupdatedate.getFlightEnd()+"_"+tupdatedate.getChangeBanks();
                if(namenumbers.indexOf(partStr)>-1){
                    nameBuffer.append(partStr);
                    namenumbers= namenumbers.replace(partStr + "|","");
                }
            }
           
            if(namenumbers.length()==0){
                //说明全部需要改期,无需分离PNR,
                String ok="";
                if(onlyChangeDate){
                    //没有产生升舱费用，仅仅是改期操作的情况
                    TUpdateDate tUpdateDate = tuobjss.get(0);
                    RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                    String dateold=DateUtil.getDDMM(DateUtil.date2Str(tUpdateDate.getFlightTime(), "yyyy-MM-dd HH:mm:ss"));
                    String datenew=DateUtil.getDDMM(robjs.getNewDate());
                    //只修改航班号与日期即可
                    ok=readIBEService.changeDate(keypnr, tUpdateDate.getFlightNumber(), dateold, robjs.getNewFlightNo(), datenew);
                }else{
                    //产生升舱费用需要调用的改期方法逻辑--oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice
                    List<String> airsegStrs=new ArrayList<String>();
                    for(TUpdateDate tUpdateDateobj:tuobjss){
                        RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDateobj.getTId());
                        String newstr=tUpdateDateobj.getFlightStart()+"_"+tUpdateDateobj.getFlightEnd()+"_"+robjs.getNewFlightNo()+"_"+
                                robjs.getNewCangwei()+"_"+robjs.getNewPrice()+"_"+tUpdateDateobj.getTaxPrice()+"_"+tUpdateDateobj.getFueltax()+
                                "_"+robjs.getNewDate()+"_"+String.valueOf(tUpdateDateobj.getTicketPrice().intValue());
                        if(!airsegStrs.contains(newstr)){
                            airsegStrs.add(newstr);
                        }
                    }
                    ok=readIBEService.changePnr(keypnr, airsegStrs);
                }
                for(TUpdateDate tUpdateDateobj:tuobjss){
                    RespData_RescheduledPNR respData=new RespData_RescheduledPNR(tUpdateDateobj.getFlightEnd(),2,
                            tUpdateDateobj.getFlightStart(),tUpdateDateobj.getNewPnr(),tUpdateDateobj.getTId(),tUpdateDateobj.getChangeBanks());
                    if(ok.indexOf("OK")>-1){
                        respData.setMessage("");
                        respData.setNewPnr(keypnr);
                        respData.setNewTravelItineraryNo(tUpdateDateobj.getTravelItineraryNo());
                    }else{
                        respData.setMessage("改期失败");
                        respData.setNewPnr("");
                        respData.setNewTravelItineraryNo("");
                    }
                    respDatas.add(respData);
                }
            }else{
                //说明部分需要改期,把需要改期的人从PNR分离出去
                String[] nameNumberList=namenumbers.split("\\|");
                for (int i = 0; i < nameNumberList.length; i++) {
                    String name=nameNumberList[i].split("_")[0];
                    TUpdateDate tUpdateDate=needchangePassengername.get(name);
                    if(i == nameNumberList.length-1){
                        //直接改期，一般为分离后的最后一个人
                        String ok="";
                        if(onlyChangeDate){
                            RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                            String dateold=DateUtil.getDDMM(DateUtil.date2Str(tUpdateDate.getFlightTime(), "yyyy-MM-dd HH:mm:ss"));
                            String datenew=DateUtil.getDDMM(robjs.getNewDate());
                            ok=readIBEService.changeDate(keypnr, tUpdateDate.getFlightNumber(), dateold, robjs.getNewFlightNo(), datenew);
                        }else{
                            //否则需要改动舱位信息,如果产生升舱费用，需要改动舱位信息
                            //产生升舱费用需要调用的改期方法逻辑--oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice
                            List<String> airsegStrs=new ArrayList<String>();
                            RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                            String newstr=tUpdateDate.getFlightStart()+"_"+tUpdateDate.getFlightEnd()+"_"+robjs.getNewFlightNo()+"_"+
                                    robjs.getNewCangwei()+"_"+robjs.getNewPrice()+"_"+tUpdateDate.getTaxPrice()+"_"+tUpdateDate.getFueltax()+
                                    "_"+robjs.getNewDate()+"_"+String.valueOf(tUpdateDate.getTicketPrice().intValue());
                            if(!airsegStrs.contains(newstr)){
                                airsegStrs.add(newstr);
                            }
                            ok=readIBEService.changePnr(keypnr, airsegStrs);
                        }
                        RespData_RescheduledPNR respData=new RespData_RescheduledPNR(tUpdateDate.getFlightEnd(),2,
                                tUpdateDate.getFlightStart(),tUpdateDate.getNewPnr(),tUpdateDate.getTId(),tUpdateDate.getChangeBanks());
                        if(ok.indexOf("OK")>-1){
                            respData.setMessage("");
                            respData.setNewPnr(tUpdateDate.getNewPnr());
                            respData.setNewTravelItineraryNo(tUpdateDate.getTravelItineraryNo());
                        }else{
                            respData.setMessage("改期失败");
                            respData.setNewPnr("");
                            respData.setNewTravelItineraryNo("");
                        }
                        respDatas.add(respData);
                    }else{
                        if(tUpdateDate!=null){
                            //分离PNR，再改期
                            String ok="";
                            String newPNR=readIBEService.splitPnrByName(keypnr, name, true);
                            if(newPNR!=null){
                                if(onlyChangeDate){
                                    RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                                    String dateold=DateUtil.getDDMM(DateUtil.date2Str(tUpdateDate.getFlightTime(), "yyyy-MM-dd HH:mm:ss"));
                                    String datenew=DateUtil.getDDMM(robjs.getNewDate());
                                    ok=readIBEService.changeDate(newPNR, tUpdateDate.getFlightNumber(), dateold, robjs.getNewFlightNo(), datenew);
                                }else{
                                  //否则需要改动舱位信息,如果产生升舱费用，需要改动舱位信息
                                  //产生升舱费用需要调用的改期方法逻辑--oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice
                                    List<String> airsegStrs=new ArrayList<String>();
                                    RescheduleObj robjs=getRescheduleByTid(ListObjs, tUpdateDate.getTId());
                                    String newstr=tUpdateDate.getFlightStart()+"_"+tUpdateDate.getFlightEnd()+"_"+robjs.getNewFlightNo()+"_"+
                                            robjs.getNewCangwei()+"_"+robjs.getNewPrice()+"_"+tUpdateDate.getTaxPrice()+"_"+tUpdateDate.getFueltax()+
                                            "_"+robjs.getNewDate()+"_"+String.valueOf(tUpdateDate.getTicketPrice().intValue());
                                    if(!airsegStrs.contains(newstr)){
                                        airsegStrs.add(newstr);
                                    }
                                    ok=readIBEService.changePnr(keypnr, airsegStrs);
                                }
                            }   
                            RespData_RescheduledPNR respData=new RespData_RescheduledPNR(tUpdateDate.getFlightEnd(),2,
                                    tUpdateDate.getFlightStart(),tUpdateDate.getNewPnr(),tUpdateDate.getTId(),tUpdateDate.getChangeBanks());
                            if(ok.indexOf("OK")>-1){
                                respData.setMessage("");
                                respData.setNewPnr(newPNR);
                                respData.setNewTravelItineraryNo(tUpdateDate.getTravelItineraryNo());
                            }else{
                                respData.setMessage("改期失败,已经产生分离PNR:"+newPNR);
                                respData.setNewPnr(newPNR);
                                respData.setNewTravelItineraryNo("");
                            }
                            respDatas.add(respData);
                        }
                    }
                }
            }
        }
    }

    public void getPnrMessage(ReadIBEManagerService readIBEService, HashMap<String, List<String>> pnrListMap,
            Iterator<String> keys) {
        while(keys.hasNext()){
            String key=keys.next();//PNR
            //这一组数据是同一个PNR为Key的
            List<String> pnrlist=null;
            try {
                pnrlist=readIBEService.getPnr(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(pnrlist==null||pnrlist.size()<2){
                pnrListMap.put(key, null);
            }else{
                pnrListMap.put(key, pnrlist);
            }
         }
    }

    /**
     * 判断是否仅仅是改期操作
     * @param tUpdateDate
     * @param robjs
     * @return
     */
    public boolean changeType(TUpdateDate tUpdateDate,TTravelItinerary ttraveobj, RescheduleObj robjs) {
        boolean onlyChangeDate=true;
        //如果产生升舱费，有舱位价格差，则不能直接改航段信息
        if(robjs.getUpgradeFee()!=null && robjs.getUpgradeFee()>0){
            onlyChangeDate=false;
        }
        if(tUpdateDate!=null){
            //航司是否一样
            if(!robjs.getNewFlightNo().substring(0,2).equals(tUpdateDate.getAirlineCompany())){
                onlyChangeDate=false;
            }
            //起飞机场是否一样
            if(!robjs.getNewOrgCity().equals(tUpdateDate.getFlightStart())){
                onlyChangeDate=false;
            }
            //到达机场是否一样
            if(!robjs.getNewArrCity().equals(tUpdateDate.getFlightEnd())){
                onlyChangeDate=false;
            }
            //舱位是否一样
            if(!robjs.getNewCangwei().equals(tUpdateDate.getCangwei())){
                onlyChangeDate=false;
            }
        }
        if(ttraveobj!=null){
            //航司是否一样
            if(!robjs.getNewFlightNo().substring(0,2).equals(ttraveobj.getFlightNumber().substring(0,2))){
                onlyChangeDate=false;
            }
            //起飞机场是否一样
            if(!robjs.getNewOrgCity().equals(ttraveobj.getFlightStart())){
                onlyChangeDate=false;
            }
            //到达机场是否一样
            if(!robjs.getNewArrCity().equals(ttraveobj.getFlightEnd())){
                onlyChangeDate=false;
            }
            //舱位是否一样
            if(!robjs.getNewCangwei().equals(ttraveobj.getTPnr().getCangwei())){
                onlyChangeDate=false;
            }
        }
        return onlyChangeDate;
    }

    public HashMap<String,List<TUpdateDate>> getCurrentUpdateMap(List<TUpdateDate> allUpdateDate) {
        HashMap<String,List<TUpdateDate>> currentUpdates=new HashMap<String, List<TUpdateDate>>();
        for (int i = 0; i < allUpdateDate.size(); i++) {
            TUpdateDate tUpdateDate= allUpdateDate.get(i);
//            String key = tUpdateDate.getNewPnr()+"_"+tUpdateDate.getFlightStart()+"_"+tUpdateDate.getFlightEnd();
            String key = tUpdateDate.getNewPnr();
            List<TUpdateDate> currentUpdateList=currentUpdates.get(key);
            if(currentUpdateList==null){
                currentUpdateList=new ArrayList<TUpdateDate>();
            }
            currentUpdateList.add(tUpdateDate);
            currentUpdates.put(key, currentUpdateList);
        }
        return currentUpdates;
    }
    
    public HashMap<String,List<TTravelItinerary>> getCurrentTTravelsMap(List<TTravelItinerary> allTTravelList) {
        HashMap<String,List<TTravelItinerary>> currentUpdates=new HashMap<String, List<TTravelItinerary>>();
        for (int i = 0; i < allTTravelList.size(); i++) {
            TTravelItinerary tUpdateDate= allTTravelList.get(i);
            String key = tUpdateDate.getTPnr().getPnr();;
            List<TTravelItinerary> currentUpdateList=currentUpdates.get(key);
            if(currentUpdateList==null){
                currentUpdateList=new ArrayList<TTravelItinerary>();
            }
            currentUpdateList.add(tUpdateDate);
            currentUpdates.put(key, currentUpdateList);
        }
        return currentUpdates;
    }
    
    public RescheduleObj getRescheduleByTid(List<RescheduleObj> ListObjs, Long Tid) {
        RescheduleObj currentObj=null;
        for (int k = 0; k < ListObjs.size(); k++) {
            List<Long> tids_current=ListObjs.get(k).getTids();
            for (int j = 0; j < tids_current.size(); j++) {
                if(Tid.longValue()==tids_current.get(j).longValue()){
                    currentObj=ListObjs.get(k);
                    break;
                }
            }
        }
        return currentObj;
    }
    
    public static void main(String[] args) {
        //
        String namenumbers="戴权_PEKCSX_880-5160592826|刘鲁_PEKCSX_880-5160592827|刘鲁_PEKCSX_880-5160592827|盛子豪_PEKCSX_880-5160592828|吴锋_PEKCSX_880-5160592829|曾艺_PEKCSX_880-5160592830|";
        
        String[] nameList=namenumbers.split("\\|");
        for (int i = 0; i < nameList.length; i++) {
            System.out.println(nameList[i]);
        }
        String numbersstr="刘鲁_PEKCSX_880-5160592827";
        String[] nameList2=numbersstr.split("_");
        for (int i = 0; i < nameList2.length; i++) {
            System.out.println(nameList2[i]);
        }
        //判断是否有不需要改期的人存在
//        for (int i = 0; i < tuobjss.size(); i++) {
//            TUpdateDate tupdatedate= tuobjss.get(i);
//            String passengername=ttmap.get(tupdatedate.getTId()).getPassengerName();
            if(namenumbers.indexOf("刘鲁_PEKCSX_880-5160592827|")>-1){
                namenumbers= namenumbers.replace("刘鲁_PEKCSX_880-5160592827|","" );
            }
            System.out.println(namenumbers);
//        }
    
    }

}
