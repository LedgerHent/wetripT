package com.viptrip.intlAirticket.controller;

import java.util.List;

import com.viptrip.base.common.MyEnum.IntlpayMethod;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_BookIntlOrder;
import com.viptrip.intlAirticket.model.Response_BookIntlOrder;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo_Informer;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo_Informer_Passenger;
import com.viptrip.intlAirticket.service.BookIntlOrderService;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
public class BookIntlOrder extends IntlTicketClient<Request_BookIntlOrder, Response_BookIntlOrder> {

    @Override
    protected OutputSimpleResult DataValid(Request_BookIntlOrder para) {
        OutputSimpleResult osr = new OutputSimpleResult();
        osr.code = 0;
        if (null == para || para.getData() == null) {
            osr.code = Msg.IncompletePara.getCode();
            osr.result = Msg.IncompletePara.getInfo();
        } else {

            Req_Data_OrderInfo req_info = para.getData();// 预定数据
            String verifyStr = req_info.getVerify();// 是否需要审核 1 需要 0不需要
            String mapkey = req_info.getMapKey();// 预定mapKey
            long checkManId = req_info.getCheckManId();// 审核人ID
            int payMethod = req_info.getPayMethod();// 支付方式
                                                    // 1-公司月结，2-在线支付，3-线下支付，4-预付款支付
            int travleType = req_info.getTravelType();// 出行类型 0-因公出行，1-因私出行
            String cangwei = req_info.getCangwei();// 舱位

            List<Req_Data_OrderInfo_Informer> infomerList = req_info.getInformerList();// 通知人信息
            List<Req_Data_OrderInfo_Informer_Passenger> passengerList = req_info.getPassengerList();// 乘机人信息
            if (0 == payMethod) {
                osr.code = 1;
                osr.result = "支付方式不能为空";
                return osr;
            }
            if ("1".equals(verifyStr)) {
                if (payMethod == IntlpayMethod.公司月结.getIntlPayMethod()
                        || payMethod == IntlpayMethod.预付款支付.getIntlPayMethod()) {
                    if (checkManId == 0) {
                        osr.code = 1;
                        osr.result = "需审核企业，审核人不能为空";
                        return osr;
                    }
                }
            }
//            if (0 == travleType) {
//                osr.code = 1;
//                osr.result = "航程类型不能为空";
//                return osr;
//            }
            if (null == mapkey) {
                osr.code = 1;
                osr.result = "航程信息不能为空";
                return osr;
            }

            if (null == passengerList || passengerList.size() <= 0) {
                osr.code = 1;
                osr.result = "航程信息不能为空";
                return osr;
            } else {
                for (Req_Data_OrderInfo_Informer_Passenger p : passengerList) {
                    Integer type = p.getType();
                    Long id = p.getId();
                    Integer idType = p.getIdType();
                    Long orgid = p.getOrgid(); // 费用归属id（公司月结和预付款，此项必填）
                    String projectNo = p.getProject();
                    Long insuranceId = p.getInsuranceId();
                    Integer insuranceNum = p.getInsuranceNum();
                    String mileage = p.getMileage();// 里程号
                    if (null == type || null == id || null == idType || null == insuranceId || null == insuranceNum) {
//                            || null == projectNo) {
                        osr.code = 1;
                        osr.result = "旅客信息不完整";
                        return osr;
                    }
                }
            }
            if (null != infomerList && infomerList.size() > 0) {
                for (Req_Data_OrderInfo_Informer contact : infomerList) {
                    String name = contact.getToName();
                    String mobile = contact.getToTel();
                    if (StringUtil.isEmpty(name) || StringUtil.isEmpty(mobile)) {
                        osr.code = 1;
                        osr.result = "联系人信息不完整";
                        return osr;
                    }
                }
            }

        }
        return osr;
    }

    @Override
    protected OutputResult<Response_BookIntlOrder, String> DoBusiness(Request_BookIntlOrder para) {
        OutputResult<Response_BookIntlOrder, String> or = new OutputResult<Response_BookIntlOrder, String>();
        // FlightIntlService service =
        // ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
        BookIntlOrderService service = ApplicationContextHelper.getInstance().getBean(BookIntlOrderService.class);
        Response_BookIntlOrder data = service.goBookIntlOrder(para);
        // Response_BookIntlOrder result = new Response_BookIntlOrder(data);
        or.setResultObj(data);
        or.code = Msg.Success.getCode();
        return or;
    }

}
