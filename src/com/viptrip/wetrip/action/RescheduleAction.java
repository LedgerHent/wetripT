package com.viptrip.wetrip.action;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.MyEnum;
import com.viptrip.pay.service.Pay;
import com.viptrip.pay.vo.E;
import com.viptrip.pay.vo.PayResp;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;
import com.viptrip.wetrip.service.IRescheduleService;
import com.viptrip.wetrip.vo.FeeObj;
import com.viptrip.wetrip.vo.RescheduleObj;
import com.viptrip.wetrip.vo.Status;
import etuf.v1_0.model.base.output.OutputResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 改期
 * Created by selfwhisper on 0008 2017/12/8.
 */
@Controller
@RequestMapping("/reschedule")
public class RescheduleAction extends BaseAction{
    private static Logger logger = LoggerFactory.getLogger(RescheduleAction.class);

    private static final String query = "reschedule/select";//改期航班选择页面
    private static final String selectReschedulePerson = "reschedule/selectReschedulePerson";// 选择需要改期人员
    private static final String showRescheduleInfo = "reschedule/showRescheduleInfo";// 改期信息页面
    private static final String confirmRescheduleInfo = "reschedule/confirmRescheduleInfo";// 改期信息确认页面
    private static final String payPage = "reschedule/pay";//支付页面
    private static final String payInfo = "reschedule/payInfo";// 支付完成页面

    private static final String orderDetail = "redirect:/order/detail.act";//订单详情页面

    @Resource
    private IRescheduleService rescheduleService;
    @Resource
    private Pay pay;


    @RequestMapping("/selectReschedulePerson.act")
    public String selectReschedulePerson(String orderId){

        return selectReschedulePerson;
    }

    /**
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/showRescheduleInfo.act")
    public String showRescheduleInfo(Long orderId){
        List<RescheduleObj> cachePara = getCachePara();
        if(null==cachePara || cachePara.isEmpty()){
            cachePara = rescheduleService.buildRescheduleObj(orderId);
        }
        if(null!=cachePara&&!cachePara.isEmpty()){
            Long tid = cachePara.get(0).getTids().get(0);
            TTravelItinerary tTravelItinerary = rescheduleService.queryTicketById(tid);
            TOrder tOrder = tTravelItinerary.getTPnr().getTOrder();
            OutputResult<Response_GetOrderDetail, String> result = rescheduleService.getOrderDetail(orderId, getUserId());
            addAttr("result",result);//订单信息
            addAttr("orderNo",tOrder.getOrderno());//订单号
            addAttr("para",cachePara);//改期待支付数据

            Long start = Long.MAX_VALUE;
            Long end = Long.MIN_VALUE;
            List<TUpdateDate> toBePayed = new ArrayList<>();
            for (RescheduleObj obj : cachePara) {
                for (Long ticketId : obj.getTids()) {
                    toBePayed.addAll(rescheduleService.getTicketToBePayed(ticketId));
                }
            }
            for (TUpdateDate tUpdateDate : toBePayed) {
                Long uId = tUpdateDate.getUId();
                if(start> uId){
                    start = uId;
                }
                if(end<uId){
                    end = uId;
                }
            }
            addAttr("updateIdStart",start);
            addAttr("updateIdEnd",end);

        }
        return showRescheduleInfo;
    }

    //去支付
    @RequestMapping("/toPay.act")
    public String toPay(Long updateIdStart,Long updateIdEnd,String orderNo){
        //获取页面显示金额
        Double amount = rescheduleService.calculateAmonutToBePay(updateIdStart,updateIdEnd);
        addAttr("amount",amount);
        addAttr("orderNo",orderNo);
        return payPage;
    }

    //支付
    @RequestMapping("/pay.act")
    @ResponseBody
    public void pay(Long updateIdStart,Long updateIdEnd,Double amount,Integer payType,String orderNo){

        String notifyURL = getBaseURL() + "reschedule/payNotify.act";
        String returnURL = getBaseURL() + "reschedule/payReturn.act";
        try {
            Double realAmount = rescheduleService.calculateAmonutToBePay(updateIdStart,updateIdEnd);
            if(realAmount!=amount){
                resp.getWriter().print("支付金额不正确");
            }else{
                //body传的是t_update_date表的起始uid  例如10000_10001 如果只有一个 传入相同的即可
                pay.onlinePay(req,resp, E.PayType.as(payType),orderNo,amount,"国内改期机票支付",updateIdStart+"_" + updateIdEnd,notifyURL,returnURL, StringUtil.getIpAddr(req));
            }
        } catch (IOException e) {
            try {
                resp.getWriter().print("支付发生异常");
            } catch (IOException e1) {
                logger.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e1)));
            }
        }
    }

    //支付回跳
    @RequestMapping("/payReturn.act")
    public String payReturn(Integer code,String msg,String orderId,Integer payType,Double amount){
        if(StringUtil.isNotEmpty(orderId)&&null!=amount){
            PayResp payResp = pay.queryPayStatus(orderId);
            if(null!=payResp){
                boolean paySuccess = 2==payResp.getCode()?true:false;
                addAttr("paySuccess",paySuccess);
            }
        }
        return payInfo;
    }

    //支付通知
    @RequestMapping("/payNotify.act")
    @ResponseBody
    public void payNotify(String orderId,Double amount,String title,String body,Integer payType,Integer payStatus,String payMemo) throws IOException {
        if(StringUtil.isNotEmpty(orderId)&&null!=amount&&StringUtil.isNotEmpty(body)&&null!=payStatus){
            String[] uids = body.split("_");//body传的是t_update_date表的起始uid  例如10000_10001 如果只有一个 传入相同的即可
            if(null!=uids&&uids.length==2){
                Double amoutToBePay = rescheduleService.calculateAmonutToBePay(Long.parseLong(uids[0]), Long.parseLong(uids[1]));
                if(amount==amoutToBePay){//金额一致
                    if(1==payStatus){//支付成功
                        //TODO 执行支付成功相应的逻辑

                    }
                }
            }
        }
        resp.getWriter().write("success");
    }


    /**
     * 申请改期
     * @param fees 实际的费用
     * @param payMethod 支付方式
     * @param aduitUserId 审核人id
     */
    @RequestMapping("/reschedule.act")
    @ResponseBody
    public void reschedule(List<FeeObj> fees,String orderNo,Integer payMethod,Long aduitUserId,Boolean isCustomServiceUser) throws IOException {
        List<RescheduleObj> para = getCachePara();
        if(null!=fees&&!fees.isEmpty()){
            List<RescheduleObj> list = new ArrayList<>();
            int idx = 0;
            for(FeeObj obj:fees){
                RescheduleObj rescheduleObj = para.get(idx);
                if(null!=rescheduleObj){
                    rescheduleObj.setUpgradeFee(obj.getUpgradeFee());
                    rescheduleObj.setServiceFee(obj.getServiceFee());
                    rescheduleObj.setRescheduleFee(obj.getRescheduleFee());
                    list.add(rescheduleObj);
                }
            }
            para = list;
        }
        TAcUser user = getUser();
        Status status = rescheduleService.reschedule(para, orderNo, user, payMethod);
        ajaxWrite(status);
    }

    /**
     * 列表查询
     * @return
     */
    @RequestMapping("/list.act")
    public String query(Integer flow,List<RescheduleObj> para, String flightNo,String cangwei,Double price,String mapKey,RedirectAttributes ra){
        String result = null;
        if(null==flow){
            flow = 0;
            setCachePara(para);
        }

        para = getCachePara();

        if(flow>0){
            RescheduleObj rescheduleObj = para.get(flow - 1);
            rescheduleObj.setNewCangwei(cangwei);
            rescheduleObj.setNewFlightNo(flightNo);
            rescheduleObj.setNewPrice(price);
            rescheduleObj.setMapKey(mapKey);
        }

        if(para!=null && para.size()==flow){ //已经完成所有航班选择 进入到改期信息确认页面
            TTravelItinerary itinerary = rescheduleService.queryTicketById(para.get(0).getTids().get(0));
            if(null!=itinerary){
                TOrder tOrder = itinerary.getTPnr().getTOrder();
                addAttr("orderId",tOrder.getOId());
                addAttr("orderNo",tOrder.getOrderno());
            }
            para = rescheduleService.calculateFee(getUserId(),para);

            //判断当前是否为客服 如果为客服 则页面的费用为可更改状态 否则费用不可更改
            boolean customServiceUser = rescheduleService.isCustomServiceUser(getUser());
            addAttr("isCustomServiceUser",customServiceUser);//是否为客服
            addAttr("isOnlinePay","2".equals(itinerary.getTPnr().getTOrder().getPayMethod()));//是否需要客户自己支付


            addAttr("para",para);
            result = confirmRescheduleInfo;
        }else{//还要选择航班

            RescheduleObj ro = para.get(flow);
            if(null!=ro){
                List<RespData_GetReschedueFlightList> flightLists = rescheduleService.queryRescheduleFlightList(ro);
                ra.addAttribute("flights",flightLists);
            }
            ra.addAttribute("flow",flow+1);
            ra.addAttribute("para",para);
            result = query;
        }
        setCachePara(para);
        return result;
    }


    private void setCachePara(List<RescheduleObj> para){
        RedisCacheManager.save(getCacheParaKey(),para, MyEnum.CacheKeepTime.一天.getValue());
    }

    private List<RescheduleObj> getCachePara(){
        return RedisCacheManager.get(getCacheParaKey(),List.class);
    }

    private String getCacheParaKey(){
        HttpSession session = getReq().getSession();
        return session.getId()+ "_" + "reschedule_para";
    }





}
