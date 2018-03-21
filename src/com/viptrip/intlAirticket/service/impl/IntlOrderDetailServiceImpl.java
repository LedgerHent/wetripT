package com.viptrip.intlAirticket.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityTransaction;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.intlAirticket.entity.TIntlAirTicketItinerary;
import com.viptrip.intlAirticket.entity.TIntlComment;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.entity.TIntlTicketRefund;
import com.viptrip.intlAirticket.entity.TIntlTicketTravel;
import com.viptrip.intlAirticket.entity.TIntlTicketsRescheduled;
import com.viptrip.intlAirticket.entity.TIntlTravelItinerary;
import com.viptrip.intlAirticket.model.Request_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Request_IntlAuditOrder;
import com.viptrip.intlAirticket.model.Response_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_IntlAuditOrder;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Airline;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Change;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Change_Fee;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Change_Ticket;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_CheckMan;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Flight;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Informer;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Passenger;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Refund;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Refund_Fee;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Segment;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderDetail_Ticket;
import com.viptrip.intlAirticket.model.flightModels.RescdAttachInfo;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_IntlOrderDetail;
import com.viptrip.intlAirticket.model.flightModels.Seg4Detail;
import com.viptrip.intlAirticket.service.IntlOrderDetailService;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.service.BalancePay;
import com.viptrip.wetrip.service.CancelOrderService;
import com.viptrip.wetrip.service.IUserService;

import etuf.v1_0.common.Constant;

@Service
@Transactional
public class IntlOrderDetailServiceImpl implements IntlOrderDetailService {

    private static Logger logger = LoggerFactory.getLogger(IntlOrderDetailServiceImpl.class);

    @Resource
    private ComDao comDao;

    @Resource
    private BalancePay balancePay;
    
    @Resource
    private CancelOrderService cancelOrderService;
    
    @Resource
    private IUserService iUserService;

    /**
     * @功能：加载登录用户信息
     * @return
     */
    public TAcUser getTAcUser(long userid) {
        TAcUser tAcUser = (TAcUser) comDao.queryForEntity(userid, TAcUser.class);
        return tAcUser;
    }

    /**
     * @功能：加载总公司机构信息
     * @param orgId
     * @return
     */
    public TAcOrg getOrgids(Long orgId) {
        TAcOrg org = null;
        org = (TAcOrg) comDao.queryForEntity(orgId, TAcOrg.class);// 子公司
        org = (TAcOrg) comDao.queryForEntity(Long.parseLong(org.getCompany()), TAcOrg.class);// 总公司

        return org == null ? null : org;
    }
    


    /* //订单详情开始 -------------0000 */
    @Override
    public Response_GetIntlOrderDetail getIntlOrderDetail(Request_GetIntlOrderDetail para) {
        Response_GetIntlOrderDetail res = new Response_GetIntlOrderDetail();
        if (para.getOrderID() != null && para.getOrderID().intValue() > 0) {
            try {
                Integer oid = Integer.valueOf(para.getOrderID());
                TIntlOrder order = comDao.queryForEntity(oid, TIntlOrder.class);
                if (order != null) {
                    List<TIntlAirTicketItinerary> segList = comDao
                            .queryForList("from TIntlAirTicketItinerary where orderid=" + oid + " order by tripid asc");
                    List<TIntlTravelItinerary> pList = comDao
                            .queryForList("from TIntlTravelItinerary where tIntlOrder=" + oid + " order by passid asc");
                    if (segList != null && segList.size() > 0 && pList != null && pList.size() > 0) {
                        List<TIntlTicketTravel> ticketList = comDao
                                .queryForList("from TIntlTicketTravel where orderid=" + oid
                                        + " order by associatedid asc");

                        List<TIntlTicketsRescheduled> changeList = comDao
                                .queryForList("from TIntlTicketsRescheduled where orderid=" + oid
                                        + " order by rescdid asc");
                        List<TIntlTicketRefund> refundList = comDao
                                .queryForList("from TIntlTicketRefund where orderid=" + oid + " order by rfdid asc");

                        Resp_Data_IntlOrderDetail detail = new Resp_Data_IntlOrderDetail();
                        detail.orderId = oid;
                        detail.orderNo = order.getIntlOrderno();
                        detail.type = StringUtil.isEmpty(order.getTravelType()) ? 0 : Integer.valueOf(order
                                .getTravelType());// //出行类型: 0:因公出行 1:因私出行
                        detail.payMethod = Integer.valueOf(order.getIntlPayMethod());
                        detail.bookDatetime = DateUtil.date2Str(order.getIntlSubscribeDate(), "yyyy-MM-dd HH:mm");
                        detail.pnr = segList.get(0).getIntlPnr();
                        Seg4Detail item = getSeg4Detail(segList);
                        detail.travelType = "OW".equals(item.travelType) ? 1 : 2;
                        detail.orgCityName = item.orgCityName;
                        detail.detCityName = item.detCityName;
                        detail.flightDate = item.flightDate;
                        detail.amount = getDoubleStringWithoutDot(order.getIntlOrdernoPrice().doubleValue());
                        detail.status = GetOrderStatusDesc(order.getIntlOrderStatus());

                        detail.segments = getSegments4Detail(segList, order);
                        detail.checkMan = getCheckMan4Detail(order);
                        detail.passengers = getPassengers4Detail(pList);
                        detail.informaers = getInformers4Detail(order);
                        detail.tickets = getTickets4Detail(ticketList);
                        detail.changes = (changeList != null && changeList.size() > 0) ? getChanges4Detail(segList,
                                ticketList, changeList) : null;
                        detail.refunds = (refundList != null && refundList.size() > 0) ? getRefunds4Detail(segList,
                                ticketList, refundList) : null;
                        res.data = detail;
                        res.status = Constant.Code_Succeed;
                    } else {
                        res.data = new Resp_Data_IntlOrderDetail();
                        res.status = Constant.Code_Failed;
                        logger.info("错误代码：" + order.getIntlOrderno() + "，错误信息：未找到订单对应的详情信息。");
                    }
                } else {
                    res.data = new Resp_Data_IntlOrderDetail();
                    res.status = Constant.Code_Failed;
                    logger.info("无效的订单ID：" + oid + "，错误信息：未找到订单对应的详情信息。");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.info("错误代码：" + para.getOrderID() + "，错误信息：" + ex.getMessage());
            }
        } else {
            res.status = Constant.Code_Failed;
            res.data = new Resp_Data_IntlOrderDetail();
            logger.info("参数无效：" + para.getOrderID());
        }
        return res;
    }

    /*
     * 返回不带小数点的数字字符串，有小数点，直接进位
     */
    public static Double getDoubleStringWithoutDot(double d) {
        return Double.valueOf(Math.round(Math.ceil(d)));
        // return String.valueOf(d);
    }

    private ArrayList<IntlOrderDetail_Passenger> getPassengers4Detail(List<TIntlTravelItinerary> pList) {
        ArrayList<IntlOrderDetail_Passenger> passengers = new ArrayList<IntlOrderDetail_Passenger>();
        for (TIntlTravelItinerary p : pList) {
            IntlOrderDetail_Passenger psg = new IntlOrderDetail_Passenger();
            psg.id = p.getPassid();
            psg.name = p.getIntlPassengerName();
            psg.email = GetStringWhitoutNull(p.getIntlMail());
            psg.tel = GetStringWhitoutNull(p.getIntlPassengerTel());
            psg.orgid = p.getIntlCostOfBelonging();
            psg.project = p.getIntlProjectNo();
            psg.idType = StringUtils.isEmpty(p.getIntlPassengerIdtype()) ? 1 : Integer.valueOf(p
                    .getIntlPassengerIdtype());
            psg.idNum = p.getIntlPassengerId();
            psg.insuranceNum = StringUtils.isEmpty(p.getIntlAssuranceNum()) ? 0 : Integer.valueOf(p
                    .getIntlAssuranceNum());
            psg.insurancePrince = getDoubleStringWithoutDotWithNull(p.getIntlAssurancePrice());
            psg.taxPrice = getDoubleStringWithoutDotWithNull(p.getIntlSingleTax());
            psg.ticketPrice = getDoubleStringWithoutDotWithNull(p.getIntlTicketPrice());
            psg.servicePrice = getDoubleStringWithoutDotWithNull(getDoubleStringWithoutDotWithNull(p.getServiceFee())
                    + getDoubleStringWithoutDotWithNull(p.getNightFee()));
            passengers.add(psg);
        }
        return passengers;
    }

    private ArrayList<IntlOrderDetail_Segment> getSegments4Detail(List<TIntlAirTicketItinerary> segList,
            TIntlOrder order) {
        ArrayList<IntlOrderDetail_Segment> al = new ArrayList<IntlOrderDetail_Segment>();
        boolean flagOne = false;
        String flagChangeTimes = "初始";
        boolean flagTwo = false;
        String flagGoOrBack = "初始";
        IntlOrderDetail_Segment s = null;
        IntlOrderDetail_Airline airline = null;
        for (TIntlAirTicketItinerary seg : segList) {
            String temps = GetStringWhitoutNull(seg.getRescheduleTimes());
            if (!flagChangeTimes.equals(temps)) {
                flagOne = true;
                flagChangeTimes = temps;
            }
            if (flagOne) {
                s = new IntlOrderDetail_Segment();
                if ("0".equals(seg.getIntlVoyageStatus())) {
                    s.type = 1;
                    s.flowid = 0;
                } else {
                    s.type = 2;
                    s.flowid = Integer.valueOf(seg.getRescheduleTimes());
                }
                s.airlines = new ArrayList<IntlOrderDetail_Airline>();
            }
            temps = GetStringWhitoutNull(seg.getFlightType() == null ? "" : String.valueOf(seg.getFlightType()));
            if (flagOne || !flagGoOrBack.equals(temps)) {
                flagTwo = true;
                flagGoOrBack = temps;
            }
            if (flagTwo) {
                airline = new IntlOrderDetail_Airline();
                airline.type = seg.getFlightType() != null && seg.getFlightType() == 2 ? 2 : 1;// 111
                if ("3".equals(order.getIsExport())) {// 线上预定
                    airline.rule = seg.getEndorserule();
                } else {
                    airline.rule = order.getIntlEndorseTheBackRules();
                }
                airline.flights = new ArrayList<IntlOrderDetail_Flight>();
            }

            IntlOrderDetail_Flight f = new IntlOrderDetail_Flight();
            f.flowid = seg.getIntlSegmentNo();
            f.carrier = seg.getIntlAirlineCompany();
            f.carrierStr = GetAirCompanyNameByCode(f.carrier);
            f.flightNo = seg.getIntlFlightNumber();
            f.shareFlightNo = "";
            f.orgAirPortStr = GetPortNameByCode(seg.getIntlDepartureAirport());
            f.detAirPortStr = GetPortNameByCode(seg.getIntlReachAirport());
            f.orgTerm = seg.getIntlDepartureTerminal();
            f.detTerm = seg.getIntlReachTerminal();
            f.startDateTime = GetDateTimeFormatStr(seg.getIntlDepartureTime());
            f.endDateTime = GetDateTimeFormatStr(seg.getIntlReachTime());
            f.planeType = "国际暂无";
            f.cangwei = seg.getIntlCangwei();
            f.cangweiType = GetCabinTypeName(seg.getIntlClassType());
            airline.flights.add(f);

            if (flagOne) {
                al.add(s);
                flagOne = false;
            }
            if (flagTwo) {
                s.airlines.add(airline);
                flagTwo = false;
            }
        }

        return al;
    }

    /**
     * 获取国际订单状态描述
     * 
     * @param status
     * @return
     */
    public String GetOrderStatusDesc(String status) {
        // 1 1 待审核
        // 2 103 已驳回
        // 3 104 已驳回
        // 4 2 待支付
        // 5 3 审核已通过
        // 6 4 已支付
        // 7 5 审核已拒绝
        // 8 6 已取消
        // 9 7 已删除
        String statusDesc = "";
        if ("1".equals(status)) {
            statusDesc = "待审核";
        } else if ("2".equals(status)) {
            statusDesc = "待支付";
        } else if ("3".equals(status) || "103".equals(status)) {
            statusDesc = "审核已通过";
        } else if ("4".equals(status)) {
            statusDesc = "已支付";
        } else if ("5".equals(status)) {
            statusDesc = "审核已拒绝";
        } else if ("6".equals(status) || "106".equals(status)) {
            statusDesc = "已取消";
        } else if ("7".equals(status)) {
            statusDesc = "已删除";
        } else if ("103".equals(status) || "104".equals(status)) {
            statusDesc = "已驳回";
        }
        return statusDesc;
        // this.getDictNameByIdFromCache("INTL_STATUS", status);
    }

    private Double getDoubleStringWithoutDotWithNull(Double d) {
        if (d == null) {
            return 0d;
        }
        return d;
    }

    public String GetCabinTypeName(String type) {
        String name = "经济舱";
        if ("2".equals(type)) {
            name = "公务舱";
        } else if ("3".equals(type)) {
            name = "头等舱";
        }
        return name;
    }

    private Seg4Detail getSeg4Detail(List<TIntlAirTicketItinerary> segList) {
        Seg4Detail item = new Seg4Detail();
        for (int i = 0; i < segList.size(); i++) {
            TIntlAirTicketItinerary p = segList.get(i);
            if (!"0".equals(p.getIntlVoyageStatus())) {
                break;
            }
            if (i == 0) {
                item.orgCityName = GetCityNameByCode(p.getIntlDepartureCity());
                item.flightDate = DateUtil.date2Str(p.getIntlDepartureTime(), "yyyy-MM-dd");
            }
            // 会重复赋值
            if (!"RT".equals(item.travelType)) {
                item.travelType = p.getFlightType() != null && p.getFlightType() == 2 ? "RT" : "OW";
                item.detCityName = GetCityNameByCode(p.getIntlReachCity());
            }
        }
        return item;
    }

    public String GetCityNameByCode(String code) {
        HashMap<String, String> hashMap = (HashMap<String, String>) RedisCacheManager.get(Const.APP_MAP_3CHAR_CITY,
                Map.class);
        Iterator<String> keys = hashMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals(code)) {
                return hashMap.get(key);
            }
        }
        return code;
    }

    public String GetAirCompanyNameByCode(String code) {
        HashMap<String, String> hashMap = (HashMap<String, String>) RedisCacheManager.get(Const.APP_MAP_AC2NAME,
                Map.class);
        Iterator<String> keys = hashMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals(code)) {
                return hashMap.get(key);
            }
        }
        return code;
    }

    public String GetPortNameByCode(String code) {
        HashMap<String, String> hashMap = (HashMap<String, String>) RedisCacheManager.get(
                Const.APP_MAP_3CHAR_AIRPORTNAME, Map.class);
        Iterator<String> keys = hashMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals(code)) {
                return hashMap.get(key);
            }
        }
        return code;
    }

    private String GetStringWhitoutNull(String s) {
        if (s == null) {
            s = "";
        }
        return s;
    }

    public String GetDateTimeFormatStr(Date date) {
        return DateUtil.date2Str(date, "yyyy-MM-dd HH:mm");
    }

    private IntlOrderDetail_CheckMan getCheckMan4Detail(TIntlOrder order) {
        IntlOrderDetail_CheckMan bp = new IntlOrderDetail_CheckMan();
        bp.id = 0;
        bp.name = order.getIntlCheckordermen();
        bp.email = "";
        bp.tel = "";
        List<TNotifyPartyInformation> list = comDao.queryForList("from TNotifyPartyInformation where orderid="
                + order.getOrderid() + " and position='审核人' and nationalityStatus='1'");
        if (list != null && list.size() > 0) {
            bp.email = list.get(0).getMail();
            bp.tel = list.get(0).getPassengerTel();
        }
        
        if(StringUtil.isNotEmpty(bp.tel)){
        	TAcUser user=iUserService.getUserByLoginUsername(bp.tel);
        	if(user!=null && user.getUsername().equals(bp.name)){
        		 bp.id =user.getUserid().intValue();
        	}
        }
        if(bp.id==0 && StringUtil.isNotEmpty(bp.email)){
        	TAcUser user=iUserService.getUserByLoginUsername(bp.email);
        	if(user!=null && user.getUsername().equals(bp.name)){
        		 bp.id =user.getUserid().intValue();
        	}
        }
        return bp;
    }

    private ArrayList<IntlOrderDetail_Informer> getInformers4Detail(TIntlOrder order) {
        List<TNotifyPartyInformation> list = comDao.queryForList("from TNotifyPartyInformation where orderid="
                + order.getOrderid() + " and position='通知人' and nationalityStatus='1'");
        if (list != null && list.size() > 0) {
            ArrayList<IntlOrderDetail_Informer> al = new ArrayList<IntlOrderDetail_Informer>();
            for (TNotifyPartyInformation t : list) {
                IntlOrderDetail_Informer bp = new IntlOrderDetail_Informer();
                bp.id = 0;
                bp.name = t.getNotifyName();
                bp.email = t.getMail();
                bp.tel = t.getPassengerTel();
                
                if(StringUtil.isNotEmpty(bp.tel)){
                	TAcUser user=iUserService.getUserByLoginUsername(bp.tel);
                	if(user!=null && user.getUsername().equals(bp.name)){
                		 bp.id =user.getUserid().intValue();
                	}
                }
                if(bp.id==0 && StringUtil.isNotEmpty(bp.email)){
                	TAcUser user=iUserService.getUserByLoginUsername(bp.email);
                	if(user!=null && user.getUsername().equals(bp.name)){
                		 bp.id =user.getUserid().intValue();
                	}
                }
                al.add(bp);
            }
            return al;
        } else {
            return null;
        }
    }

    private ArrayList<IntlOrderDetail_Ticket> getTickets4Detail(List<TIntlTicketTravel> ticketList) {
        // 只要原始的票信息，一个票号一条记录
        ArrayList<IntlOrderDetail_Ticket> al = new ArrayList<IntlOrderDetail_Ticket>();
        IntlOrderDetail_Ticket ticket = null;
        boolean isNeedNew = false;
        String tNo = "原始记录";
        for (TIntlTicketTravel t : ticketList) {
            String tempNo = GetStringWhitoutNull(t.getIntlTicketNo());
            if ("".equals(tempNo)) {
                continue;
            }
            String status = t.getIntlTicketStatus();
            if (!"0".equals(status) && !"1".equals(status)) {
                break;
            }
            if (!tNo.equals(tempNo)) {
                tNo = tempNo;
                isNeedNew = true;
                ticket = new IntlOrderDetail_Ticket();
                ticket.ticketNo = tempNo;
                ticket.psgrId = t.getPassid();
                ticket.opDatetime = GetDateTimeFormatStr(t.getIntlIssuedDate());
                ticket.status = GetTicketStatusDesc(status);

            }

            if (ticket.flightNos != null) {
                ticket.flightNos += ",";
            } else {
                ticket.flightNos = "";
            }
            ticket.flightNos += getFligtNoByTripID(t.getTripid());
            if (isNeedNew) {
                al.add(ticket);
                isNeedNew = false;
            }
        }

        return al;
    }

    private String getFligtNoByTripID(Integer tripid) {
        return GetStringWhitoutNull((comDao.queryForEntity(tripid, TIntlAirTicketItinerary.class))
                .getIntlFlightNumber());
    }

    public String GetTicketStatusDesc(String status) {
        String statusDesc = "待出票";
        if ("1".equals(status)) {
            statusDesc = "已出票";
        } else if ("2".equals(status)) {
            statusDesc = "改期申请";
        } else if ("3".equals(status) || "103".equals(status)) {
            statusDesc = "改期通过";
        } else if ("4".equals(status)) {
            statusDesc = "改期拒绝";
        } else if ("5".equals(status)) {
            statusDesc = "退票申请";
        } else if ("6".equals(status) || "106".equals(status)) {
            statusDesc = "退票通过";
        } else if ("7".equals(status)) {
            statusDesc = "退票拒绝";
        }
        // 国际机票状态（0-待出票，1-已出票，2-改期申请，3-改期通过，4-改期拒绝，5-退票申请，6-退票通过，7-退票拒绝，103-已驳回）

        return statusDesc;
    }

    private ArrayList<IntlOrderDetail_Change> getChanges4Detail(List<TIntlAirTicketItinerary> segList,
            List<TIntlTicketTravel> ticketList, List<TIntlTicketsRescheduled> changeList) {
        ArrayList<IntlOrderDetail_Change> al = new ArrayList<IntlOrderDetail_Change>();

        boolean flagOne = false;
        String flagChangeTimes = "初始";
        IntlOrderDetail_Change c4d = null;
        ArrayList<String> tNos = null;
        for (TIntlAirTicketItinerary seg : segList) {
            if ("0".equals(seg.getIntlVoyageStatus())) {
                continue;
            }
            List<RescdAttachInfo> list = GetRescdIdAndPsgIdByTripId(ticketList, seg.getTripid());
            if (list != null && list.size() > 0) {
                String temp = seg.getRescheduleTimes();
                if (!flagChangeTimes.equals(temp)) {
                    flagChangeTimes = temp;
                    flagOne = true;
                }
                if (flagOne) {
                    c4d = new IntlOrderDetail_Change();
                    c4d.flowid = Integer.valueOf(temp);
                    c4d.fees = new ArrayList<IntlOrderDetail_Change_Fee>();
                    for (RescdAttachInfo rp : list) {
                        IntlOrderDetail_Change_Fee fee = new IntlOrderDetail_Change_Fee();
                        fee.psgrId = rp.psgId;
                        TIntlTicketsRescheduled tempChange = comDao.queryForEntity(rp.rescdId,
                                TIntlTicketsRescheduled.class);
                        fee.upgrade = getDoubleStringWithoutDotWithNull(tempChange.getIntlUpgradesReceive());
                        fee.update = getDoubleStringWithoutDotWithNull(tempChange.getIntlRescheduledReceive());
                        fee.service = getDoubleStringWithoutDotWithNull(getDoubleStringWithoutDotWithNull(tempChange
                                .getChangeServiceFee()) + getDoubleStringWithoutDotWithNull(tempChange.getNightFee()));
                        c4d.fees.add(fee);
                    }
                    tNos = new ArrayList<String>();
                    c4d.tickets = new ArrayList<IntlOrderDetail_Change_Ticket>();
                }
                // 票信息
                for (RescdAttachInfo rp : list) {
                    IntlOrderDetail_Change_Ticket t = new IntlOrderDetail_Change_Ticket();
                    String changeTicketNo = rp.ticketNo;
                    if (changeTicketNo == null || "".equals(changeTicketNo)) {
                        changeTicketNo = String.valueOf(rp.psgId);
                    }
                    if (tNos.contains(changeTicketNo)) {
                        continue;
                    }
                    tNos.add(changeTicketNo);
                    t.ticketNo = "";// 暂无
                    t.newTicketNo = rp.ticketNo == null ? "" : rp.ticketNo;
                    t.psgrId = rp.psgId;
                    t.flightNos = GetFlightNosByTicketNo(segList, ticketList, rp.ticketNo, String.valueOf(t.psgrId));
                    t.opDatetime = GetChangeOPDateTime(changeList, rp.rescdId);
                    t.status = GetTicketStatusDesc(rp.status);
                    c4d.tickets.add(t);
                }
                if (flagOne) {
                    al.add(c4d);
                    flagOne = false;
                }
            }
        }

        return al;
    }

    private List<RescdAttachInfo> GetRescdIdAndPsgIdByTripId(List<TIntlTicketTravel> ticketList, Integer tripid) {
        List<RescdAttachInfo> list = new ArrayList<RescdAttachInfo>();
        for (TIntlTicketTravel t : ticketList) {
            if (t.getTripid().toString().equals(tripid.toString())) {
                RescdAttachInfo rp = new RescdAttachInfo();
                rp.rescdId = t.getRescdid();
                rp.psgId = t.getPassid();
                rp.ticketNo = t.getIntlTicketNo();
                rp.status = t.getIntlTicketStatus();
                list.add(rp);
            }
        }
        return list;
    }

    private String GetFlightNosByTicketNo(List<TIntlAirTicketItinerary> segList, List<TIntlTicketTravel> ticketList,
            String ticketNo, String psgId) {
        String nos = "";
        for (TIntlTicketTravel t : ticketList) {
            if (ticketNo != null) {
                if (!ticketNo.equals(t.getIntlTicketNo())) {
                    continue;
                }
            }
            for (TIntlAirTicketItinerary seg : segList) {
                if (seg.getTripid().toString().equals(t.getTripid().toString())) {
                    if ((ticketNo != null && !"".equals(ticketNo))
                            || (psgId.equals(String.valueOf(t.getPassid())) && "1".equals(seg.getIntlVoyageStatus()))) {
                        if (!"".equals(nos)) {
                            nos += ",";
                        }
                        nos += seg.getIntlFlightNumber();
                    }
                }
            }
        }
        return nos;
    }

    private String GetChangeOPDateTime(List<TIntlTicketsRescheduled> changeList, Integer rescdId) {
        String dt = "";

        for (TIntlTicketsRescheduled t : changeList) {
            if (t.getRescdid().toString().equals(rescdId.toString())) {
                if (t.getIntlAuditDate() != null) {
                    dt = GetDateTimeFormatStr(t.getIntlAuditDate());
                } else if (t.getIntlApplyDate() != null) {
                    dt = GetDateTimeFormatStr(t.getIntlApplyDate());
                }
                break;
            }
        }

        return dt;
    }

    @SuppressWarnings("rawtypes")
    private ArrayList<IntlOrderDetail_Refund> getRefunds4Detail(List<TIntlAirTicketItinerary> segList,
            List<TIntlTicketTravel> ticketList, List<TIntlTicketRefund> refundList) {
        ArrayList<IntlOrderDetail_Refund> al = new ArrayList<IntlOrderDetail_Refund>();
        for (TIntlTicketRefund f : refundList) {
            IntlOrderDetail_Refund d = new IntlOrderDetail_Refund();
            d.type = "1".equals(f.getBusinessType()) ? 2 : 1;
            d.ticketNo = f.getTicketno();
            d.flowid = d.type.equals("0") ? 0 : GetChangeTimes4Refund(ticketList, f.getRfdid());
            if (f.getIntlRfdAuditDate() != null) {
                d.opDatetime = GetDateTimeFormatStr(f.getIntlRfdAuditDate());
            } else if (f.getIntlRfdApplyDate() != null) {
                d.opDatetime = GetDateTimeFormatStr(f.getIntlRfdApplyDate());
            }
            d.reason = f.getIntlRfdReson() == null ? "未填写退票理由" : f.getIntlRfdReson();
            d.status = GetIntlTicketRefundStatusDesc(f.getIntlRfdStatus());
            d.fees = new ArrayList<IntlOrderDetail_Refund_Fee>();
            IntlOrderDetail_Refund_Fee fee = new IntlOrderDetail_Refund_Fee();
            fee.psgrId = f.getPassid();

            fee.refund = getDoubleStringWithoutDotWithNull(getDoubleStringWithoutDotWithNull(f.getIntlOrgRefundFee())
                    + getDoubleStringWithoutDotWithNull(f.getIntlOrgRefundUpdateFee()));
            fee.service = getDoubleStringWithoutDot(getDoubleStringWithoutDotWithNull(f.getRefundServiceFee())
                    + getDoubleStringWithoutDotWithNull(f.getNightFee()));
            d.fees.add(fee);
            al.add(d);
        }
        Collections.sort(al, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                IntlOrderDetail_Refund rd1 = (IntlOrderDetail_Refund) o1;
                IntlOrderDetail_Refund rd2 = (IntlOrderDetail_Refund) o2;
                int result = rd1.fees.get(0).psgrId.compareTo(rd2.fees.get(0).psgrId);
                if (result == 0) {
                    return rd1.flowid.compareTo(rd2.flowid);
                } else {
                    return result;
                }
            }
        });

        return al;
    }

    private Integer GetChangeTimes4Refund(List<TIntlTicketTravel> ticketList, Integer rfdid) {
        Integer times = 1;
        for (TIntlTicketTravel t : ticketList) {
            if (t.getRfdid() != null && !"".equals(t.getRfdid())) {
                if (rfdid.toString().equals(t.getRfdid().toString())) {
                    String temp = t.getRescheduleTimes();
                    if (temp != null) {
                        times = Integer.valueOf(temp) + 1;
                    }
                    break;
                }
            }
        }

        return times;
    }

    public String GetIntlTicketRefundStatusDesc(String status) {
        String desc = "退票申请";
        if ("1".equals(status)) {
            desc = "退票同意";
        } else if ("2".equals(status)) {
            desc = "退票拒绝";
        } else if ("9".equals(status) || "109".equals(status)) {
            desc = "待航司退票";
        }
        return desc;
    }

    /**
     * 国际机票审核逻辑
     */
    @Override
    public Response_IntlAuditOrder intlAuditOrder(Request_IntlAuditOrder para) {
        Response_IntlAuditOrder res = new Response_IntlAuditOrder();

        if (para.getData().getOrderId() > 0) {
            TAcUser user = getTAcUser(para.userId);
            TIntlOrder order = getIntlOrder(para.getData().getOrderId());
            
//            EntityTransaction transaction = comDao.getEntityManager().getTransaction();
//            transaction.begin();
            try {
                if (1==para.getData().getType()) {// 审核通过
                    order.setIntlOrderStatus("3");
                    this.setAutoComment(user.getUsername(), "审核通过", order.getOrderid().toString());
                } else if (2==para.getData().getType()) {// 审核不通过
                    order.setIntlOrderStatus("5");
                    // 若支付方式为预付款支付 则进行预付款退款
                    String payMethed = order.getIntlPayMethod();
                    if (payMethed.equals("4") || "1".equals(payMethed)) {
                        String orgid = order.getCompanyOfAffiliationId();
                        TAcOrg tacorg = this.getOrgids(Long.valueOf(orgid));

                        Object[] obj = this.queryBalanceCount(order.getIntlOrderno());
                        if (obj != null) {
                            for (int i = 0; i < obj.length; i++) {
                                balancePay.balancePay(Config.JPTK, Double.valueOf(obj[i].toString()) * -1,
                                        order.getIntlOrderno(), "", tacorg.getOrgid(), tacorg.getOrgname(), payMethed,
                                        "");
                            }
                        }
                    }
                    this.setAutoComment(user.getUsername(), "审核拒绝", order.getOrderid().toString());
                }
                comDao.executeUpdate(order);
                res.status = Constant.Code_Succeed;
//                transaction.commit();
                return res;
            } catch (Exception e) {
                logger.info("国际订单审核异常"+DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
                e.printStackTrace();
                String titles="";
                for (int ii = 0; ii < e.getStackTrace().length; ii++) {
                    titles+="<p>"+e.getStackTrace()[ii].toString()+"</p>";
                }
                logger.info(titles);
                logger.info("国际订单审核异常结束"+DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
                try {
                    comDao.getEntityManager().getTransaction().rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                comDao.getEntityManager().close();
                res.status = Constant.Code_Failed;
                return res;
                // return "错误代码：201701181759。错误信息："+e.getMessage();
            }
        } else {
            res.status = Constant.Code_Failed;
            logger.info("参数无效：" + para.getData().getOrderId());
        }
        return res;
    }

    /**
     * @param auditInfo
     */
    public TIntlOrder getIntlOrder(Integer orderId) {
        TIntlOrder treturn = comDao.queryForEntity(orderId, TIntlOrder.class);
        return treturn;
    }

    /**
     * 自动添加订单备注(操作人，操作内容，订单ID)
     */
    
    public boolean setAutoComment(String name, String comment, String orderID) {
        String comment_msg = name + ":" + comment;
        TIntlComment coment=new TIntlComment();
        coment.setCommentDate(comDao.getDBTime().toString());
        coment.setCommentMsg(comment_msg);
        coment.setOId(Long.parseLong(orderID));
        coment.setPassengerName("系统");
        boolean flag = false;
        try {
            comDao.executeSave(coment);
            flag =true;;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return flag;
    }

    @SuppressWarnings("rawtypes")
    public Object[] queryBalanceCount(String intlOrderno) {
        StringBuffer sql = new StringBuffer();
        Object[] param = new Object[1];
        sql.append("SELECT T.MONEY  " + " FROM T_AC_ORG_BALANCE T WHERE 1=1  and T.FLOW_STATUS='2' and t.order_no =?");
        param[0] = intlOrderno;
        List blist = comDao.queryBySQL(sql.toString(), param);
        return blist.toArray();
    }

    @Override
    public Response_CancelIntlOrder cancelIntlOrder(Request_CancelIntlOrder para) {
        Response_CancelIntlOrder res=new Response_CancelIntlOrder();
        long intlOrderId=para.getOrderId();
        TIntlOrder intlorder=comDao.queryForEntity((int)intlOrderId, TIntlOrder.class);
        TAcUser tacUser=this.getTAcUser(para.userId);
        try{
            //国际订单状态（1-待审核，2-待支付，7-已删除，3-审核已同过，审核已拒绝，4-已支付,6-已取消）
            Date date = new Date();
            if ("1".equals(intlorder.getIntlLockStatus())
                    && !intlorder.getIntlOpUser().equals(
                            tacUser.getUsername())) { 
                // 如果提交取消订单的时候，已经被别人锁定，则提示不能取消
                res.status = Constant.Code_Failed;
            } else if("1".equals(intlorder.getIntlOrderStatus())
                    ||"2".equals(intlorder.getIntlOrderStatus())
                    ||(
//                            "0".equals(isNotickeiNo)&&
                            ("3".equals(intlorder.getIntlOrderStatus())||"4".equals(intlorder.getIntlOrderStatus())))
                    ){   
                    intlorder.setIntlOrderStatus("6");//机票   已取消
                    String flag=cancelOrderService.cancelOrDeleteOrder("2","1",intlorder.getOrderid());
                if(flag!=null){
                    //取消失败
                    res.status = Constant.Code_Failed;
                }else{
                    comDao.executeUpdate(intlorder);
                    String comment="取消订单";
                    this.setAutoComment(tacUser.getUsername(), comment, intlorder.getOrderid().toString());
                    res.status = Constant.Code_Succeed;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            res.status = Constant.Code_Failed;
        }
        return res;
    }
}
