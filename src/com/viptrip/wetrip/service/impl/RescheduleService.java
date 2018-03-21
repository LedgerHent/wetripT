package com.viptrip.wetrip.service.impl;

import com.viptrip.util.DateUtil;
import com.viptrip.wetrip.controller.GetOrderDetail;
import com.viptrip.wetrip.controller.GetReschedueFlightList;
import com.viptrip.wetrip.controller.QueFee4ChangeRfndRule;
import com.viptrip.wetrip.controller.RescheduledUpgradeFee;
import com.viptrip.wetrip.dao.TAcOrgDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.*;
import com.viptrip.wetrip.model.*;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.ReqData_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.ReqData_RescheduledUpgradeFee_Tripinfo;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;
import com.viptrip.wetrip.service.BalancePay;
import com.viptrip.wetrip.service.IRescheduleService;
import com.viptrip.wetrip.vo.RescheduleObj;
import com.viptrip.wetrip.vo.Status;
import etuf.v1_0.model.base.output.OutputResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by selfwhisper on 0008 2017/12/8.
 */
@Service
@Transactional
public class RescheduleService implements IRescheduleService {
    @Resource
    private ComDao cdao;
    @Resource
    private TAcOrgDao orgDao;
    @Resource
    private BalancePay balancePay;


    /*
     * 改期申请
     */
    public Status reschedule(List<RescheduleObj> list, String orderNo, TAcUser loginUser, int payMethod){
        Status result = Status.fail(null);
        if(null!=list){
            TAcOrg org = orgDao.findOne(loginUser.getOrgid());
            boolean flag = true;
            String msg = null;
            if(1==payMethod || 4==payMethod){//预付或者月结
                Double totalAmount = 0d;
                for(RescheduleObj obj:list){
                    totalAmount += obj.getTids().size()*((null==obj.getRescheduleFee()?0d:obj.getRescheduleFee()) + (null==obj.getServiceFee()?0d:obj.getServiceFee()) + (null==obj.getUpgradeFee()?0d:obj.getUpgradeFee()));
                }
                Map<String, String> map = balancePay.balancePay("5", totalAmount, orderNo, "机票改期", org.getOrgid(), org.getOrgname(), payMethod + "", null);
                String status = map.get("status");
                flag = "0".equals(status);
                msg = map.get("message");
            }
            if(flag){
                TPnr tpnr = null;//TODO 获取新的pnr
                String  newTravelNo = null;//TODO 获取新的票号
                for (RescheduleObj ro:list){
                    List<Long> tids = ro.getTids();
                    Double upgradeFee = ro.getUpgradeFee();
                    Double serviceFee = ro.getServiceFee();
                    Double rescheduleFee = ro.getRescheduleFee();
                    for(Long tid:tids){
                        TTravelItinerary t = queryTicketById(tid);

                        if(null!=t){
                            TUpdateDate tud = new TUpdateDate();
                            tud.setTId(tid);
                            tud.setTravelItineraryNo(t.getTravelItineraryNo());
                            tud.setFlightStart(tpnr.getOriginCity());
                            tud.setFlightEnd(tpnr.getDestinationCity());
                            tud.setFlightNumber(tpnr.getAirline());
                            tud.setOrgterm(tpnr.getOrgterm());
                            tud.setDetterm(tpnr.getDetterm());
                            tud.setCangwei(tpnr.getCangwei());
                            tud.setFlightTime(tpnr.getFlightDate());
                            tud.setDestinationTime(tpnr.getDestinationTime());
                            tud.setPlanetype(tpnr.getPlanetype());
                            tud.setAirlineCompany(tpnr.getAirlineCompany());
                            tud.setNewPnr(tpnr.getPnr());
                            tud.setChangeBanks(newTravelNo);
                            tud.setPlanetype(tpnr.getPlanetype());
                            tud.setPayment(payMethod + "");
                            tud.setCustomerPaymentPattern(payMethod + "");//支付方式
                            tud.setCustomerPaymentState("0");//待支付
                            tud.setClassFee(upgradeFee);//升舱费
                            tud.setChangeServiceFee(serviceFee);//服务费
                            tud.setChangeTheFreight(rescheduleFee);//改期费
                            tud.setNightFee(0d);
                            tud.setOrderno(tpnr.getTOrder().getOrderno());
                            tud.setUsertype("0");
                            tud.setApplyName(loginUser.getUsername());
                            tud.setApplyDate(new Date());
                            tud.setTicketPrice(ro.getNewPrice());
                            tud.setFlightStatus("3");//3-改期申请 5-申请通过 6-申请拒绝
                            tud.setSuppliername("凯撒景洪");
                            tud.setAgreementcode("凯撒景洪");

                            cdao.executeUpdate(tud);

                            t.setFlightStatus("3");//已申请改期
                            cdao.executeUpdate(t);
                        }
                    }
                }
                result = Status.success();
            }else{
                result.setMsg(msg);
            }
        }
        return result;
    }


    public List<RespData_GetReschedueFlightList> queryRescheduleFlightList(RescheduleObj ro){
        List<RespData_GetReschedueFlightList> flightLists = null;
        List<Long> tids = ro.getTids();
        if(null!=tids){
            TTravelItinerary itinerary = queryTicketById(tids.get(0));
            String flightNumber = itinerary.getFlightNumber();
            String flightStart = itinerary.getFlightStart();
            String flightEnd = itinerary.getFlightEnd();
            GetReschedueFlightList getReschedueFlightList = new GetReschedueFlightList();
            Request_GetReschedueFlightList reqS = new Request_GetReschedueFlightList();
            ReqData_GetReschedueFlightList data = new ReqData_GetReschedueFlightList();
            data.airline = flightNumber.substring(0,2);
            List<ReqData_GetFlightList_TripInfo> tripInfos = new ArrayList<>();
            ReqData_GetFlightList_TripInfo tripInfo = new ReqData_GetFlightList_TripInfo();
            tripInfo.setDate(ro.getNewDate());
            tripInfo.setArrAirport(flightEnd);
            tripInfo.setDepAirport(flightStart);
            tripInfos.add(tripInfo);
            data.tripInfo = tripInfos;
            reqS.setData(data);
            OutputResult<Response_GetReschedueFlightList, String> response_getReschedueFlightListStringOutputResult = getReschedueFlightList.ClientRequest(reqS, Response_GetReschedueFlightList.class);
            if(null!=response_getReschedueFlightListStringOutputResult && response_getReschedueFlightListStringOutputResult.IsSucceed()){
                Response_GetReschedueFlightList resultObj = response_getReschedueFlightListStringOutputResult.getResultObj();
                flightLists = resultObj.getData();
            }
        }
        return flightLists;
    }

    public TTravelItinerary queryTicketById(Long tid){
        TTravelItinerary result = null;
        if(null!=tid){
            String hql = "from TTravelItinerary where TId=?";
            result = (TTravelItinerary)cdao.queryForObject(hql, new Object[]{tid});
        }
        return result;
    }

    @Override
    public List<RescheduleObj> calculateFee(Long userId,List<RescheduleObj> para) {
        List<RescheduleObj> result = null;
        if(null!=para){
            result = new ArrayList<>();
            for (RescheduleObj ro:para){
                QueFee4ChangeRfndRule queFee4ChangeRfndRule = new QueFee4ChangeRfndRule();
                Long tid = ro.getTids().get(0);
                TTravelItinerary tTravelItinerary = queryTicketById(tid);
                Request_QueFee4ChangeRfndRule req = null;
                req.type = "2";//改期
                req.cangwei = tTravelItinerary.getTPnr().getCangwei();
                req.air2char = tTravelItinerary.getTPnr().getAirlineCompany();
                req.date = DateUtil.format(tTravelItinerary.getTPnr().getOriginTime(),"yyyy-MM-dd HH:mm");
                req.price = tTravelItinerary.getTicketPrice();
                req.discount = tTravelItinerary.getProxyRate().intValue();
                req.userId = userId;
                req.changeNum = queryUpdateCount(tid);
                OutputResult<Response_QueFee4ChangeRfndRule, String> fee = queFee4ChangeRfndRule.ClientRequest(req, Response_QueFee4ChangeRfndRule.class);
                if(null!=fee && fee.IsSucceed()){
                    Response_QueFee4ChangeRfndRule resultObj = fee.getResultObj();
                    ro.setRescheduleFee(resultObj.price);
                    ro.setServiceFee(resultObj.servicePrice);
                }

                Request_RescheduledUpgradeFee rsu = new Request_RescheduledUpgradeFee();
                List<String> tnos = new ArrayList<>();
                for(Long id:ro.getTids()){
                    tnos.add(Long.toString(id));
                }
                rsu.setTicketNumbers(tnos);
                rsu.setOrderno(tTravelItinerary.getTPnr().getTOrder().getOrderno());
                List<ReqData_RescheduledUpgradeFee_Tripinfo> trips = new ArrayList<>();
                ReqData_RescheduledUpgradeFee_Tripinfo trip = new ReqData_RescheduledUpgradeFee_Tripinfo();
                trip.setFlightStart(tTravelItinerary.getFlightStart());
                trip.setFlightEnd(tTravelItinerary.getFlightEnd());
                trip.setTicketPrice(tTravelItinerary.getTicketPrice());
                trips.add(trip);
                rsu.setChangeTripinfos(trips);
                RescheduledUpgradeFee upgradeFee = new RescheduledUpgradeFee();
                OutputResult<Response_RescheduledUpgradeFee, String> response_rescheduledUpgradeFeeStringOutputResult = upgradeFee.ClientRequest(rsu, Response_RescheduledUpgradeFee.class);
                if(null!=response_rescheduledUpgradeFeeStringOutputResult&&response_rescheduledUpgradeFeeStringOutputResult.IsSucceed()){
                    ro.setUpgradeFee(response_rescheduledUpgradeFeeStringOutputResult.getResultObj().getUpgradeFees().get(0));
                }

                result.add(ro);
            }
        }
        return result;
    }


    public List<TTravelItinerary> getCanRescheduledTicket(String orderId){
        String hql = "from TTravelItinerary where flightStatus=12 and TId in";

        return null;
    }

    private Integer queryUpdateCount(Long tid){
        Integer result = 0;
        if(null!=tid){
            String hql = "select count(TId) from TUpdateDate where TId=? group by TId";
            result = cdao.queryInteger(hql, new Object[]{tid});
            if(null==result){
                result = 0;
            }
        }
        return result;
    }


    public boolean isCustomServiceUser(TAcUser user){
        boolean result = false;
        if(null!=user){
            String hql = "select role from TAcUser user,TAcRole role ,TAcUserrole ur where user.userid=? and role.roleid= ur.roleid and ur.userid=user.userid";
            TAcRole role = (TAcRole)cdao.queryForObject(hql, new Object[]{user.getUserid()});
            if(null!=role){
                String rolename = role.getRolename();
                result = Pattern.compile("(客服)+").matcher(rolename).matches();
            }
        }
        return result;
    }


    public OutputResult<Response_GetOrderDetail, String> getOrderDetail(Long orderId,Long userId){
        if(null!=orderId && null!=userId){
            GetOrderDetail getOrderDetail = new GetOrderDetail();
            Request_GetOrderDetail para = new Request_GetOrderDetail();
            para.setOrderID(orderId);
            para.setUserId(userId);
            return getOrderDetail.ClientRequest(para, Response_GetOrderDetail.class);
        }else{
            return null;
        }
    }

    @Override
    public Double calculateAmonutToBePay(Long updateIdStart, Long updateIdEnd) {
        Double result = 0d;
        if(null!=updateIdStart && null!=updateIdEnd){
            String hql = "from TUpdateDate where and flightStatus in(3,5) and UId>=? and UId<=? ";
            result = cdao.queryForDouble(hql, new Object[]{updateIdStart, updateIdEnd});
        }
        return result;
    }

    public List<TUpdateDate> getTicketToBePayed(Long tid){
        List<TUpdateDate> result = null;
        if(null!=tid){
            String hql = "from TUpdateDate where flightStatus in(3,5) and TId=?";
            result = cdao.queryForList(hql, new Object[]{tid});
        }
        return result;
    }


    public List<RescheduleObj> buildRescheduleObj(Long orderId) {
        if(null!=orderId){
            List<RescheduleObj> result = new ArrayList<>();
            String hql = "select order from TOrder order where OId=?1";
            TOrder o = (TOrder) cdao.queryForObject(hql, new Object[]{orderId});
            if(null!=o){
                List<TTravelItinerary> list = new ArrayList<>();
                Set<TPnr> tPnrs = o.getTPnrs();
                for(TPnr pnr:tPnrs){
                    Set<TTravelItinerary> tTravelItineraries = pnr.getTTravelItineraries();
                    list.addAll(tTravelItineraries);
                }
                Collections.sort(list, new Comparator<TTravelItinerary>() {
                    @Override
                    public int compare(TTravelItinerary o1, TTravelItinerary o2) {
                        return Long.valueOf(o1.getTId()-o2.getTId()).intValue();
                    }
                });
                List<Long> tids = new ArrayList<>();
                Map<Long,TTravelItinerary> maps = new HashMap<>();
                for(TTravelItinerary t:list){
                    tids.add(t.getTId());
                    maps.put(t.getTId(),t);
                }
                hql = "select tud from TUpdateDate tud where flightStatus in(3,5) and TId in (?1)";
                Query query = cdao.getEntityManager().createQuery(hql);
                query.setParameter(1,list);
                List<TUpdateDate> resultList = query.getResultList();

                if(null!=resultList && !resultList.isEmpty()){
                    for(TUpdateDate tud:resultList){
                        TTravelItinerary itinerary = maps.get(tud.getTId());
                        RescheduleObj obj = new RescheduleObj();
                        obj.setNewPrice(tud.getTicketPrice());
                        obj.setRescheduleFee(tud.getChangeTheFreight());
                        obj.setServiceFee(tud.getChangeServiceFee());
                        obj.setUpgradeFee(obj.getUpgradeFee());
                        obj.setNewFlightNo(tud.getFlightNumber());
                        obj.setNewCangwei(tud.getCangwei());
                        obj.setNewArrCity(itinerary.getTPnr().getDestinationCity());
                        obj.setNewDate(null);
                        obj.setNewOrgCity(itinerary.getTPnr().getOriginCity());
                        obj.setTids(null);
                        boolean flag = true;
                        RescheduleObj old = null;
                        for(RescheduleObj ro:result){
                            if(ro.equalsWithoutTidsAndMapkey(obj)){
                                flag = false;
                                old = ro;
                                break;
                            }
                        }
                        if(flag){
                            List<Long> tids1 = new ArrayList<>();
                            tids1.add(tud.getTId());
                            obj.setTids(tids1);
                            result.add(obj);
                        }else{
                            old.getTids().add(tud.getTId());
                        }
                    }
                }

            }
            return result;
        }else{
            return null;
        }
    }

}
