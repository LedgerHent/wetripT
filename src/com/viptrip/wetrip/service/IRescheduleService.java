package com.viptrip.wetrip.service;

import com.viptrip.wetrip.controller.GetOrderDetail;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail;
import com.viptrip.wetrip.vo.RescheduleObj;
import com.viptrip.wetrip.vo.Status;
import etuf.v1_0.model.base.output.OutputResult;

import java.util.List;

/**
 * Created by selfwhisper on 0008 2017/12/8.
 */
public interface IRescheduleService {

    /**
     * 改期业务
     * @param list 改期业务list
     * @param orderNo 订单号
     * @param loginUser 当前登录用户
     * @param payMethod 支付方式
     */
    public Status reschedule(List<RescheduleObj> list, String orderNo, TAcUser loginUser, int payMethod);


    /**
     * 获取改期航班列表
     * @param ro
     * @return
     */
    public List<RespData_GetReschedueFlightList> queryRescheduleFlightList(RescheduleObj ro);

    /**
     * 根据票号查找
     * @param tid 票号表id
     * @return
     */
    public TTravelItinerary queryTicketById(Long tid);

    /**
     * 计算并设置费用 改期费 服务费 升舱费
     * @param userId 用户id
     * @param para
     * @return
     */
    List<RescheduleObj> calculateFee(Long userId,List<RescheduleObj> para);


    /**
     * 用户是否为客服
     * @param user 用户
     * @return
     */
    public boolean isCustomServiceUser(TAcUser user);

    /**
     * 获取订单详情
     * @param orderId 订单id
     * @param userId 用户id
     * @return
     */
    public OutputResult<Response_GetOrderDetail, String> getOrderDetail(Long orderId,Long userId);


    /**
     * 计算需要支付的金额
     * @param updateIdStart 改期表id
     * @param updateIdEnd 改期表id
     * @return
     */
    Double calculateAmonutToBePay(Long updateIdStart, Long updateIdEnd);

    /**
     * 获取需要支付的改期记录
     * @param tid 票号表id
     * @return
     */
    public List<TUpdateDate> getTicketToBePayed(Long tid);

    /**
     * 通过订单id组建待支付的改期信息
     * @param orderId
     * @return
     */
    List<RescheduleObj> buildRescheduleObj(Long orderId);
}
