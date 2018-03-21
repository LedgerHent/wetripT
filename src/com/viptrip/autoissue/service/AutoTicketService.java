package com.viptrip.autoissue.service;

import java.util.List;

import com.viptrip.autoissue.entity.TAutoTicket;
import com.viptrip.autoissue.entity.TAutoissueAirline;
import com.viptrip.autoissue.entity.TAutoissueSwitch;
import com.viptrip.autoissue.entity.TBspLimit;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TOrder;

public interface AutoTicketService {
    /**
     * 根据订单号 作为条件
     * 
     * @param orderno
     * @param fromFlag
     *            :1:支付入口；2：审核入口；3：下单入口（月结、预支付无需审核的）4:财务直接修改支付状态入口（个人支付、
     *            现结走线下付款的入口）
     * @return
     */
    public String addFromPay(String orderno, String fromFlag);

    /**
     * 增加自动出票记录数据逻辑
     * 
     * @param fromFlag
     * @param order
     * @return
     */
    public String addTAuTicket(String fromFlag, TOrder order);

    /**
     * 用订单ID列表做条件 录入数据
     * 
     * @param orderIdList
     * @param fromFlag
     * @return
     */
    public String addFromPay(List<String> orderIdList, String fromFlag);

    /**
     * 获取配置文件是否开启自动出票
     * 
     * @return
     */
    public String getAutomaticTicket();

    /**
     * 判断各个订单入口是否可以做自动出票处理
     * 
     * @param orderno
     * @param fromFlag
     * @return
     */
    public String autoissueValidate(TOrder order, String fromFlag);
    
    /**
     * 未下单之前自动出票条件的判断
     * @param airlines
     * @param companyName
     * @param payMethod
     * @param onePNR
     * @param allAdult
     * @return
     */
    public String autoissueBeforeValidate(List<String> airlines,String companyName,String payMethod,boolean onePNR,boolean allAdult);

    /**
     * 通过机构名称查询出该机构的总公司
     * 
     * @param orgname
     * @return
     */
    public TAcOrg getOrgByOrgName(String orgname);

    // 判断BSP额度是否可以自动出票
    public String bspLimits();

    /**
     * 查询BSP限额信息
     */
    public TBspLimit queryBspLimits();

    /**
     * 查询采购价之和
     */
    public String queryPurchasePrice(int status);

    /**
     * 根据订单号获取订单
     * 
     * @param orderno
     * @return
     */
    public TOrder getOrderByNo(String orderno);

    // 根据ID查找TAcOrg
    public TAcOrg queryForTAcOrg(Object id, TAcOrg tac);

    /**
     * 从记录表中根据创建时间优先处理的原则获取 一条数据
     */

    public TAutoTicket getOneByDataTime();

    /**
     * 判断自动出票航司、企业小开关
     * 
     * @param airlines
     * @return
     */
    public List<TAutoissueSwitch> getAutoissueSwich(List<String> airlines, String orgname, String orgid);

    /**
     * 判断自动出票航司开关
     * 
     * @param airlines
     * @return
     */
    public List<TAutoissueAirline> getAutoissueAirline(List<String> airlines);

}
