package com.viptrip.wetrip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TTicketRefund;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.Request_BookAirTicket;
import com.viptrip.wetrip.model.Request_ToPay;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.model.employees.ListEmployee;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.vo.BookOrderInfo;
import com.viptrip.wetrip.vo.UserInfo;

import etuf.v1_0.model.base.output.OutputResult;

public interface BookOrderService {
    /**
     * 根据登陆人id查询所在企业信息
     * 
     * @param userId
     *            当前登陆人id
     * @return
     */
    public TAcOrg getTAcOrgByUserId(Long userId);

    /**
     * 预定输参数
     * 
     * @param
     * @return
     */
    public BookOrderInfo getBookOrderInfo(String mapKey, Integer type, Long timestamp, String bf, UserInfo userinfo,
            Long userId, OutputResult<Response_GetPayMethodList, String> payMethod, List<TAcInsurance> result);

    public BookOrderInfo getBookOrderInfo(Integer type,Long timestamp,String bf,UserInfo userinfo,Long userId,
            OutputResult<Response_GetPayMethodList, String> payMethod,List<TAcInsurance> result);
    
    /**
     * 组装预定所需的参数
     * 
     * @param
     * @return
     */
    public Request_BookAirTicket getRequestBookOrderInfo(BookOrderInfo bf, Long userId);

    /**
     * 查询该部门或者公司下所有的子部门
     * 
     * @param
     * @return
     */
    public List<ListEmployee> getListEmployees(Long orgid);

    public String createPNR(TOrder order);

    public String[] getPNR(TOrder order);

    public String[] getBigplait(long oid, String[] pnr);

    public void updateOrder(TOrder t, String[] pnr, String[] bigplait);

    public RespData_BookAirTicket toPay(Request_ToPay para);

    public List getBookFlights(String passengerid);

    public String getTotalPath(Long orgid);

    /**
     * 计算升舱费用
     * 
     * @param orderno
     * @param ticketNumbers
     *            如果是从改期表获取的数据，则需要传换开票号；如果从订单票号表来的数据，则需要给票号。
     * @param changeTrips
     *            HashMap<String,Double> String:PEKSHA Double:1024，即航段、票面价
     * @return -100000 表示数据有误，票号与订单号不匹配;如果改期的升舱费用小于0,则返回0.
     */
    public Double getRescheduledUpgradeFee(String orderno, List<String> ticketNumbers,
            HashMap<String, Double> changeTrips);
    
    public HashMap<String,Double> getRescheduledUpgradeFeeMap(String orderno, List<String> ticketNumbers,
            HashMap<String, Double> changeTrips);
            
    public TTravelPassenger getPassenger(String pid);
    
    
    public List<TUpdateDate> getTUpdateDates(List<Long> tids);
    
    public List<TTravelItinerary> getTTIts(List<Long> tids);

    public List getBookIntlFlights(String passengerStr,String airline,String flightDate);
    
    public List<TTicketRefund> getTTicketRefundList(List<Long> tids );
    
}
