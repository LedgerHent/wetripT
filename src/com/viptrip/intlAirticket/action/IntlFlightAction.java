package com.viptrip.intlAirticket.action;

import com.google.gson.Gson;
import com.nethsoft.zhxq.core.util.ObjectUtil;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.controller.CreateTTmcApproveProcess;
import com.viptrip.common.controller.GetApprovalProcess;
import com.viptrip.common.model.*;
import com.viptrip.hotel.controller.GetBookRight;
import com.viptrip.hotel.controller.GetBookStaffList;
import com.viptrip.hotel.model.Request_GetBookRight;
import com.viptrip.hotel.model.Request_GetBookStaffList;
import com.viptrip.hotel.model.Response_GetBookRight;
import com.viptrip.hotel.model.Response_GetBookStaffList;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.staff.StaffGroup;
import com.viptrip.intlAirticket.controller.*;
import com.viptrip.intlAirticket.entity.ReqParam;
import com.viptrip.intlAirticket.entity.ReqPassenger;
import com.viptrip.intlAirticket.entity.RrqInformer;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.model.*;
import com.viptrip.intlAirticket.model.flightModels.*;
import com.viptrip.intlAirticket.service.impl.IntlOrderService;
import com.viptrip.resource.Const;
import com.viptrip.taglib.DateFunction;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.controller.GetPayMethodList;
import com.viptrip.wetrip.controller.ListAuditor;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.*;
import com.viptrip.wetrip.model.base.Response_CertificateMessage;
import com.viptrip.wetrip.model.employees.Auditor;
import com.viptrip.wetrip.model.employees.ListEmployee;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.BookOrderService;
import com.viptrip.wetrip.service.IPassengerService;
import com.viptrip.wetrip.service.IUserService;
import com.viptrip.wetrip.service.ListStaffService;
import com.viptrip.wetrip.service.impl.ListStaffServiceImpl;
import com.viptrip.wetrip.service.impl.UserService;
import com.viptrip.wetrip.vo.FlightListFilterParam;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hanzhicheng 2017-9-8
 */
@Controller
@RequestMapping("/intlflight")
@Scope("prototype")
public class IntlFlightAction extends BaseAction{

    private static Logger logger = LoggerFactory.getLogger(IntlFlightAction.class);


    @Resource
    private ComDao comDao;
    @Resource
    private BookOrderService bookOrderService;
    @Resource
    private IPassengerService passengerService;
    @Resource
    private ListStaffService listStaffService;

    @Resource
    private IUserService  userService;

    @Resource
    private IntlOrderService intlOrderService;

    private static final String flightsearch = "intlflight/intlticket_search";//国际机票查询页面
    private static final String flightlist = "intlflight/ticketList";//机票列表页面
    private static final String orderList = "intlflight/allOrder";//订单列表
    private static final String orderDetail = "intlflight/orderDetails";  //订单详情

    BookOrderService bos = ApplicationContextHelper.getInstance().getBean(BookOrderService.class);

    @RequestMapping("search.act")
    public String toFlightSearch() {
        TAcUser user = getUser();
        Long userId = getUserId();
        addAttr("user", user);
        addAttr("userId", userId);
        addAttr("flag", "1");
        addAttr("pageType", "机票预订");
        return flightsearch;
    }

    //http://172.16.249.100:8080/wetrip/intlflight/search.act
    //机票列表查询
    @RequestMapping(value = "/getFlightList.act", method = {RequestMethod.GET, RequestMethod.POST})
    public String getFlightList(IntlFlightListQue intlFlightListQue, String weeks, String travelfangshi, FlightListFilterParam fp, String cangwei) throws IOException {


        intlFlightListQue.passengerType = 1;

        Request_GetIntlFlightList rg = new Request_GetIntlFlightList();
        rg.userId = getUserId();
        rg.data = intlFlightListQue;
        OutputResult<Response_GetIntlFlightList, String> result = null;
        List<IntlFlightListResp> list = null;
        try {
            GetIntlFlightList getIntlFlightList = new GetIntlFlightList();//获取国际机票接口
            result = getIntlFlightList.ClientRequest(rg, Response_GetIntlFlightList.class);


            if (result.IsSucceed()) {
                if (null != result.getResultObj()) {

						/*if(!"不限".equals(cangwei)){
						fp.setListParam_cabin(cangwei);
						}
						list = filterParam(result.getResultObj().data, fp);//过滤条件*/
                    list = sortList(result.getResultObj().data, "date", null);//默认按价格升序排序

                }
            }

            addAttr("weeks", weeks);
            addAttr("result", list);
            addAttr("dates", intlFlightListQue.startTime);
            addAttr("travelfangshi", travelfangshi);
            addAttr("intlFlightListQue", intlFlightListQue);
            addAttr("flag", "1");
            addAttr("cangwei", cangwei);
        } catch (Exception e) {

            e.printStackTrace();
            logger.debug("获取国际机票接口数据失败！");

        }

        if (list == null || result.getResultObj().status == 0) {
            return flightlist;
        } else {
            return flightsearch;

        }


    }


    //国际机票返程列表
    @RequestMapping(value = "/getIntlFlightReurnList.act")
    public String getIntlFlightReurnList(String mapKey, String travelfangshi, String weeks) {

        GetIntlFlightReurnList gfrl = new GetIntlFlightReurnList();//获取国际机票返程列表接口
        Request_GetIntlFlightReurnList req = new Request_GetIntlFlightReurnList();
        req.mapKey = mapKey;
        try {
            OutputResult<Response_GetIntlFlightReurnList, String> result = gfrl.ClientRequest(req, Response_GetIntlFlightReurnList.class);

            Response_GetIntlFlightReurnList obj = result.getResultObj();//获取国际机票返回航班列表数据
            List<IntlFlightListResp> list = obj.data;

            IntlFlightListQue intlFlightListQue = new IntlFlightListQue();
            //PEK_SHA_1_1_2017-09-14_A|CA1501
            //PEK_SIN_1_2_2017-09-21_2017-09-23_A|MU5138_MU565
            String[] keys = mapKey.split("_");
			
			/*//拆分mapKey 把值存到intlFlightListQue中 交换始发地
			intlFlightListQue.startTime=keys[5];
			intlFlightListQue.orgCity=keys[1];
			intlFlightListQue.detCity=keys[0];
			intlFlightListQue.travelType= Integer.valueOf(keys[3]);
			intlFlightListQue.passengerType=1;*/


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int day = sdf.parse(keys[5]).getDay();

            switch (day) {
                case 0:
                    weeks = "周日";

                    break;
                case 1:
                    weeks = "周一";

                    break;
                case 2:
                    weeks = "周二";

                    break;
                case 3:
                    weeks = "周三";

                    break;
                case 4:
                    weeks = "周四";

                    break;
                case 5:
                    weeks = "周五";

                    break;
                case 6:
                    weeks = "周六";

                    break;
            }


            addAttr("weeks", weeks);
            addAttr("result", list);
            addAttr("dates", keys[5]);
            addAttr("travelfangshi", travelfangshi);
            addAttr("intlFlightListQue", intlFlightListQue);
            addAttr("flag", "2");
            addAttr("cangwei", "不限");

        } catch (Exception e) {

            e.printStackTrace();
            logger.debug("获取国际机票返程接口数据失败!");
        }


        return flightlist;

    }


    /**
     * 获取前一天后一天航班列表最低价
     *
     * @author hanzhicheng
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/getLowestPrice.act", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void getLowestPrice(String orgCity, String detCity, String NextTime, String YesterTime, String travelType, String arrviTime) {
        String lowprice = "";

        IntlFlightListQue intlFlightListQue = new IntlFlightListQue();
        intlFlightListQue.setOrgCity(orgCity);
        intlFlightListQue.setDetCity(detCity);
        intlFlightListQue.passengerType = 1;
        intlFlightListQue.setTravelType(Integer.valueOf(travelType));
        intlFlightListQue.setStartTime(NextTime);
        intlFlightListQue.setArrviTime(arrviTime);

        String lowpriceN = aquireLowPrice(intlFlightListQue);

        String lowpriceY = "￥0";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowdate = sdf.format(new Date());
        Date d;
        try {
            d = sdf.parse(YesterTime);

            long Ydate = d.getTime();
            long date = sdf.parse(nowdate).getTime();
            if (Ydate >= date) {
                intlFlightListQue.setStartTime(YesterTime);
                lowpriceY = aquireLowPrice(intlFlightListQue);
            }
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        lowprice = lowpriceN + "_" + lowpriceY;

        PrintWriter pw;
        try {
            pw = resp.getWriter();
            pw.write(lowprice);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String aquireLowPrice(IntlFlightListQue intlFlightListQue) {
        double lowprice = 0;
        Request_GetIntlFlightList rg = new Request_GetIntlFlightList();
        rg.userId = getUserId();
        rg.data = intlFlightListQue;
        OutputResult<Response_GetIntlFlightList, String> result = null;
        List<IntlFlightListResp> list = null;

        GetIntlFlightList getIntlFlightList = new GetIntlFlightList();//获取国际机票接口
        result = getIntlFlightList.ClientRequest(rg, Response_GetIntlFlightList.class);


        if (result.IsSucceed()) {
            if (null != result.getResultObj()) {
                list = sortList(result.getResultObj().data, "price", null);//默认按时间升序排序
                lowprice = list.get(0).getTotalPrice();
            }
        }
        BigDecimal bd = new BigDecimal(lowprice);
        bd = bd.setScale(0, RoundingMode.HALF_UP);

        return "￥" + bd.toString();
    }

    /**
     * 列表查询筛选数据
     *
     * @param data
     * @param fp
     * @return
     */
    @SuppressWarnings("unused")
    private static List<IntlFlightListResp> filterParam(List<IntlFlightListResp> data, FlightListFilterParam fp) {
        List<IntlFlightListResp> result = null;
        if (ObjectUtil.isNotEmpty(data)) {
            if (null == fp) {
                result = data;
            } else {
                String cabin = fp.getListParam_cabin();//航空公司二字码
                String code = fp.getListParam_code();//舱位码
                Integer area = fp.getListParam_timeArea();//时间段
                if (StringUtil.isEmpty(code) && StringUtil.isEmpty(cabin) && null == area) {
                    result = data;
                } else {
                    Iterator<IntlFlightListResp> iterator = data.iterator();
                    while (iterator.hasNext()) {
                        IntlFlightListResp intlFlightListResp = (IntlFlightListResp) iterator
                                .next();
                        if (!StringUtil.isEmpty(code) && code.equals(intlFlightListResp.getCarrier())) {//过滤航空公司
                            iterator.remove();
                            continue;
                        }
                        if (!StringUtil.isEmpty(cabin)) {//过滤舱位
                            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
                            ServletContext sc = webApplicationContext.getServletContext();
                            @SuppressWarnings("unchecked")
                            Map<String, List<String>> map = (Map<String, List<String>>) sc.getAttribute(Const.APP_MAP_CABINCODE);
                            List<String> list = map.get(intlFlightListResp.getCangwei());
                            if (null == list || !list.contains(intlFlightListResp.getCangwei())) {//舱位类型不匹配
                                iterator.remove();
                                continue;
                            }

                        }
                        if (null != area && area > 0) {//过滤时间
                            String pattern = "HHmm";
                            String start = "0000";
                            String end = "2359";
                            switch (area) {
                                case 1:
                                    end = "0800";
                                    break;
                                case 2:
                                    start = "0800";
                                    end = "1200";
                                    break;
                                case 3:
                                    start = "1200";
                                    end = "1300";
                                    break;
                                case 4:
                                    start = "1300";
                                    break;
                            }
                            String depDateTime = intlFlightListResp.start;
                            String time = DateUtil.strToDateEng(depDateTime, pattern);

                            try {
                                Date s = DateUtil.parse(start, pattern);
                                Date e = DateUtil.parse(end, pattern);
                                Date t = DateUtil.parse(time, pattern);
                                if (t.getTime() < s.getTime() || t.getTime() > e.getTime()) {//如果不在范围内
                                    iterator.remove();
                                    continue;
                                }
                            } catch (ParseException e) {
                                logger.error(StringUtil.getExceptionStr(e));
                            }
                        }
                    }
                    result = data;
                }
            }

        }
        return result;
    }

    //排序
    private static List<IntlFlightListResp> sortList(List<IntlFlightListResp> data, String order, String direction) {
        if (!StringUtil.isEmpty(order)) {
            if (null != data && data.size() > 0) {
                if ("date".equals(order)) {
                    Collections.sort(data, new Comparator<IntlFlightListResp>() {
                        @Override
                        public int compare(IntlFlightListResp o1,
                                           IntlFlightListResp o2) {
                            String time1 = o1.start;
                            String time2 = o2.start;
                            return time1.compareTo(time2);

                        }
                    });
                    if ("desc".equals(direction)) {
                        Collections.reverse(data);
                    }
                }


                if ("price".equals(order)) {
                    Collections.sort(data, new Comparator<IntlFlightListResp>() {
                        @Override
                        public int compare(IntlFlightListResp o1,
                                           IntlFlightListResp o2) {
                            Double price1 = o1.totalPrice;
                            Double price2 = o2.totalPrice;
                            return price1.compareTo(price2);
                        }
                    });
                    if ("desc".equals(direction)) {
                        Collections.reverse(data);
                    }
                }
            }
        }
        return data;
    }


    /**
     * 跳转至订单填写
     */
    public String orderFill() {


        return "";
    }


    /**
     * 国际机票下单费用归属 管理员 保险控制
     * hanzhicheng 2017-12-5
     *
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public void statusControl() {

        try {
            Long userId = getUserId();
            TAcOrg tacorg = bos.getTAcOrgByUserId(userId);

            //费用归属状态 0 不显示，1 选填，2 必填（1和2都显示）
            String costbelongStatus = "";
            if (StringUtil.isNotEmpty(tacorg.getCostbelongmust())) {
                if ("1".equals(tacorg.getCostbelongmust())) {
                    costbelongStatus = "2";
                } else if ("0".equals(tacorg.getCostbelongmust())) {
                    costbelongStatus = "1";
                } else {
                    costbelongStatus = "0";
                }
            } else {
                costbelongStatus = "1";
            }

            //查询保险状态 不显示 必填 选填	hanzhicheng****管理员状态
            String isShowInsruance = "1";
            String isShowAdmin = "1";

            String sqll = "select SWITCHTYPE,STATE from ORG_SWITCHS where ORGID=" + tacorg.getCompany() + " and BUSINESSTYPE=2";

            List<Object[]> orgsWitchs = (List<Object[]>) comDao.queryBySQL(sqll, null);

            if (orgsWitchs != null && orgsWitchs.size() > 0) {//0:SWITCHTYPE(开关类型，1-保险|2-管理员)   1:STATE(状态，0-不显示|1-选填|2-必填，默认值为1)
                for (Object[] orgsWitch : orgsWitchs) {
                    if (orgsWitch[1] != null) {
                        if ("1".equals(orgsWitch[0].toString())) {//1-保险
                            isShowInsruance = orgsWitch[1].toString();
                        } else if ("2".equals(orgsWitch[0].toString())) {//2-管理员
                            isShowAdmin = orgsWitch[1].toString();
                        }
                    }
                }
            }


            //针对企业保险查询
            String sql = "select oi.id,"
                    + " oi.user_id,"
                    + " oi.name,"
                    + " oi.operating_date,"
                    + " oi.price,"
                    + " oi.insurance_id,"
                    + " oi.orgid,"
                    + " oi.orgname,"
                    + " ai.floor_price,"
                    + " ai.note,"
                    + " ai.max_count"
                    + " from t_ac_org_insurance oi,T_AC_INSURANCE ai where oi.insurance_id= ai.id"
                    + " and	oi.orgid = " + tacorg.getCompany() + " order by oi.price ";
            TAcInsurance tAcInsurance = null;
            List<?> list = comDao.queryBySQL(sql, null);
            List<TAcInsurance> result = new ArrayList<TAcInsurance>();
            for (int i = 0; i < list.size(); i++) {
                tAcInsurance = new TAcInsurance();
                Object[] obj = (Object[]) list.get(i);
                tAcInsurance.setId(Integer.valueOf(obj[5].toString()));
                tAcInsurance.setPrice(Double.valueOf(obj[4].toString()));
                tAcInsurance.setFloorPrice(Double.valueOf(obj[8].toString()));
                tAcInsurance.setNote(obj[9].toString());
                tAcInsurance.setMaxCount(Integer.valueOf(obj[10].toString()));

                result.add(tAcInsurance);
            }

            String insuraceId = null;
            if (result != null && result.size() > 0) {

                insuraceId = result.get(0).getId().toString();
            }


            addAttr("InsruanceIsNull", insuraceId != null ? insuraceId : "-1");
            addAttr("isShowInsruance", isShowInsruance);
            addAttr("isShowAdmin", isShowAdmin);
            addAttr("costbelongStatus", costbelongStatus);
            addAttr("insurancelist", result);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    /**
     * H5国际机票预订提交订单
     *
     * @param result
     * @author jh
     */


    @RequestMapping(value = "/bookIntlOrder.act", method = {RequestMethod.GET, RequestMethod.POST})
    public String bookIntlOrder(String mapkey, String cangwei, ReqParam param, String traveltype, String payMethod,String approvalId) {

        try {
            RrqInformer shenhe = null;    //审核人
            List<RrqInformer> riList = new ArrayList<>();    //通知人
            //for (int i = 0;i < param.ri.size();i++){
            if (param.ri != null) {
                for (RrqInformer ri : param.ri) {

                    if ("审核人".equals(ri.toType)) {
                        shenhe = ri;
                    } else if ("通知人".equals(ri.toType)) {
                        riList.add(ri);
                    }

                }
            }


//                String s="true";
//              IntlAirOrderBookManager intlAirOrderBookManager = (IntlAirOrderBookManager) Struts2Util.getBean("intlAirOrderBookManager");
//                String param="{'userId':'294865','mapKey':'BJS_SIN_ADT_OW_2017-03-26|CA969','checkManId':'299530'," +
//                        "'payMethod':'4','travelType':'0','cangwei':'S','informerList':[{'toName':'张三'," +
//                        "'toTel':'18736567893','toMail':'421089712@qq.com',}],'passengerList':" +
//                        "[{'type':'1','id':'299530','idType':'1','orgid':'5660','project':'123','mileage':'','insuranceNum':'1','insuranceId':'220'}]}";
//                
            Request_BookIntlOrder para = new Request_BookIntlOrder();
            Req_Data_OrderInfo doi = new Req_Data_OrderInfo();
            Req_Data_OrderInfo_Informer doii = new Req_Data_OrderInfo_Informer();


            List<Req_Data_OrderInfo_Informer> informerList = new ArrayList<Req_Data_OrderInfo_Informer>();
            List<Req_Data_OrderInfo_Informer_Passenger> passengerList = new ArrayList<Req_Data_OrderInfo_Informer_Passenger>();

            para.userId = getUserId();
            doi.setMapKey(mapkey);

            if (payMethod.indexOf("公司月结") > -1) {
                doi.setPayMethod(1);
                doi.setCheckManId(Long.valueOf(shenhe.getToId()).longValue());
            } else if (payMethod.indexOf("在线支付") > -1) {
                doi.setPayMethod(2);
            } else if (payMethod.indexOf("线下支付") > -1) {
                doi.setPayMethod(3);
            } else if (payMethod.indexOf("预付款支付") > -1) {
                doi.setPayMethod(4);
                doi.setCheckManId(Long.valueOf(shenhe.getToId()).longValue());
            }
            doi.setTravelType(Integer.valueOf(traveltype).intValue());
            doi.setCangwei(cangwei);

            //添加通知人
            for (int i = 0; i < riList.size(); i++) {
                doii.setToName(riList.get(i).toName);
                doii.setToTel(riList.get(i).toTel);
                doii.setToMail(riList.get(i).toMail);
                informerList.add(doii);
                doi.setInformerList(informerList);
                para.data = doi;
            }
            //添加乘机人
            for (int i = 0; i < param.rp.size(); i++) {
                Req_Data_OrderInfo_Informer_Passenger doiip = new Req_Data_OrderInfo_Informer_Passenger();
                doiip.setType(Integer.valueOf(param.rp.get(i).type).intValue());            //乘机人类型
                doiip.setId(Long.valueOf(param.rp.get(i).id).longValue());                //乘机人id
                doiip.setIdType(Integer.valueOf(param.rp.get(i).idtype).intValue());        //证件类型
                //doiip.setOrgid(Integer.valueOf(param.rp.get(i).orgid).intValue());
                doiip.setOrgid(param.rp.get(i).orgid != null && !"".equals(param.rp.get(i).orgid) ? Long.valueOf(param.rp.get(i).orgid) : -1);//费用归属
                doiip.setProject(param.rp.get(i).project);                                //项目号
                doiip.setMileage(param.rp.get(i).mileage);                                //里程号
                doiip.setInsuranceNum(param.rp.get(i).insuranceNum != null ? Integer.valueOf(param.rp.get(i).insuranceNum) : 0);//保险份数
                doiip.setInsuranceId(param.rp.get(i).insuranceId != null ? Long.valueOf(param.rp.get(i).insuranceId).longValue() : -1);//保险id

                passengerList.add(doiip);

            }
            doi.setPassengerList(passengerList);
            para.data = doi;

            BookIntlOrder bt = new BookIntlOrder();
            OutputResult<Response_BookIntlOrder, String> result = bt.ClientRequest(para, Response_BookIntlOrder.class);

            Response_BookIntlOrder resultObj = result.getResultObj();
            if (resultObj != null) {
                RespData_BookIntlOrder_OrderInfo data = resultObj.getData();
                addAttr("data", data);

                //查询服务费
                Double totalServiceFee = comDao.queryForEntity(data != null ? Integer.valueOf(data.getOrderId() + "") : -1, TIntlOrder.class).getTotalServiceFee();
                addAttr("totalServiceFee", totalServiceFee);
                addAttr("payMethod", payMethod);

//                    System.out.println(data.getOrderId()+"   "+data.getOrderno()+"  "+data.getTotalPrice());


                //创建审批流
                Request_CreateTTmcApproveProcess process = new Request_CreateTTmcApproveProcess();
                TTmcApproveProcessInfo info = new TTmcApproveProcessInfo();
                process.approvalId=Long.valueOf(approvalId);
                info.businessType= MyEnum.BussinessType.国内机票.getValue();
                info.approvalType=0;      //审批类型  常规超规
                info.orderid=Integer.valueOf(String.valueOf(data.getOrderId()));
                info.orderno=data.getOrderno();
                TAcOrg org = bookOrderService.getTAcOrgByUserId(getUserId());
                info.orgId=org.getOrgid();
                info.orgName=org.getOrgname();
                info.amount=new BigDecimal(data.getTotalPrice());

                if (payMethod.indexOf("公司月结") > -1) {
                    info.payType=1;
                } else if (payMethod.indexOf("在线支付") > -1) {
                    info.payType=2;
                } else if (payMethod.indexOf("线下支付") > -1) {
                    info.payType=3;
                } else if (payMethod.indexOf("预付款支付") > -1) {
                    info.payType=4;
                }

                info.travelType=Integer.valueOf(traveltype).intValue();
                TIntlOrder order = intlOrderService.getOrderByOrderNO(data.getOrderno());
                info.bookTime=order.getIntlSubscribeDate();
                //info.productName=????
                info.productDetail=cangwei;
                TAcUser user = getUser();
                BookerInfo bookerInfo = new BookerInfo();
                bookerInfo.id=user.getUserid();
                bookerInfo.name=user.getUsername();
                bookerInfo.mobile=user.getCellphone();
                bookerInfo.email=user.getEmail();
                info.booker=bookerInfo;
                ArrayList<ApproveProcessTravellerMode> listPass = new ArrayList<ApproveProcessTravellerMode>();

                if(param.rp !=null && param.rp.size()>0){
                    ArrayList<ReqPassenger> objects = new ArrayList<ReqPassenger>();
                    objects=param.rp;
                    for(ReqPassenger rpp:objects){  //类型，1：企业员工，2：常旅客
                        if("1".equals(rpp.type)){
                            TAcUser usere = userService.getUserById(Long.valueOf(rpp.getId()));
                            ApproveProcessTravellerMode travelModel = new ApproveProcessTravellerMode();
                            if(usere !=null){
                                travelModel.id=usere.getUserid();
                                travelModel.email=usere.getEmail();
                                travelModel.mobile=usere.getCellphone();
                                travelModel.name=usere.getUsername();
                                travelModel.orgId=usere.getOrgid();
                                travelModel.orgName=comDao.queryForEntity(usere.getOrgid(),TAcOrg.class).getOrgname();
                                listPass.add(travelModel);
                            }
                        }else if("2".equals(rpp.type)){
                            TTravelPassenger pass = passengerService.getOneById(Long.valueOf(rpp.getId()));
                            ApproveProcessTravellerMode travelModel = new ApproveProcessTravellerMode();
                            if(pass!=null){
                                travelModel.id=-1l;
                                travelModel.email=pass.getEmail();
                                travelModel.mobile=pass.getMobilephone();
                                travelModel.name=pass.getName();
                                travelModel.orgId=pass.getDept();
                                travelModel.orgName=pass.getDeptname();
                                listPass.add(travelModel);
                            }
                        }
                    }
                    info.traveller=listPass;
                }

                process.data=info;
                Response_CreateTTmcApproveProcess pro = new Response_CreateTTmcApproveProcess();
                CreateTTmcApproveProcess createProcess = new CreateTTmcApproveProcess();
                OutputResult<Response_CreateTTmcApproveProcess, String> resu = createProcess.ClientRequest(process, Response_CreateTTmcApproveProcess.class);

                //创建审批流结束
            }





        } catch (Exception e) {
            e.printStackTrace();
                //  returnError("提交订单失败！");
        }
        return "intlflight/pay";
//        return "";
    }

    /**
     * H5国际机票订单列表查询
     *
     * @param result
     * @author jh
     */
    @RequestMapping(value = "/getIntlOrderList.act", method = {RequestMethod.GET, RequestMethod.POST})
    public String getIntlOrderList(Integer orderStatus) {
        try {
            Request_GetIntlOrderList para = new Request_GetIntlOrderList();

            Class<? extends Request_GetIntlOrderList> aClass = para.getClass();
            Request_GetIntlOrderList request_getIntlOrderList = aClass.newInstance();


            Req_Data_IntlOrderListInfo data = new Req_Data_IntlOrderListInfo();
            data.setCount(50);
            data.setStart(1);
            data.setStatus(orderStatus);
            para.setData(data);
//                GetIntlOrderList bt = new GetIntlOrderList();

            //OutputResult<Response_GetIntlOrderList, String> result = bt.ClientRequest(para, Response_GetIntlOrderList.class);
            //Response_GetIntlOrderList response = result.getResultObj();

        } catch (Exception e) {
            e.printStackTrace();
        }
        TAcUser user = getUser();
        Long userId = getUserId();
        addAttr("status", orderStatus);
        addAttr("user", user);
        addAttr("userId", userId);
        addAttr("flag", "2");
        addAttr("pageType", "全部订单");
        return orderList;
    }


    /**
     * flag=0:
     * 国际机票机票详情&更多仓位
     * <p/>
     * flag=1:
     * 跳转订单填写页面
     */
    @SuppressWarnings("null")
    @RequestMapping(value = "/getFlightInfo.act", method = {RequestMethod.GET, RequestMethod.POST})
    public String getIntlTicket(String mapKey, String travelfangshi, Model model) {
        int flag = 0;        //标记页面跳转：0-跳转此页面，1-跳转订单填写页面
        String hangcheng = "";    //1-单程，2-往返，3-联程
        String flags = req.getParameter("flag");
        String cangwei = req.getParameter("cangwei");
        String cangweidesc = req.getParameter("cangweidesc");
        String price = req.getParameter("price");
        String mapkey1 = req.getParameter("mapkey");
        String traveltype = req.getParameter("traveltype");
        String seatsLeft = req.getParameter("seatsLeft");
//        	String flight = req.getParameter("flights");
        if (flags != null && !"".equals(flags)) {
            flag = Integer.parseInt(flags);
        }

        Gson gson = new Gson();
        String[] flights = new String[5];        //多程 转json传参	0-mapKey,1-航班详情，2-舱位详情，
        flights[0] = mapKey;
        IntlFlights intlFlights = new IntlFlights();        //多程存储 航班详情 和 舱位详情
        List<RespData_Cabins> cabinList = null;        //更多仓位

        Long userId = getUserId();

        System.out.println("*******************进入");
        //mapKey = "PEK_SIN_ADT_OW_2017-09-29_A|CA975"; OW-单程  RT-往返
        String type = "因公出行".equals(travelfangshi) ? "0" : "1";
        addAttr("traveltype", type);
        try {
            Request_GetIntlFlightDetail gifd = new Request_GetIntlFlightDetail();
//        		gifd.mapKey = mapKey;
            gifd.userId = getUserId();
            gifd.mapKey = flag == 0 ? mapKey : mapkey1;

            OutputResult<Response_GetCabinList, String> cabinResult;
            GetIntlFlightDetail getIntlFlightDetail = new GetIntlFlightDetail();
            OutputResult<Response_GetIntlFlightDetail, String> result = null;
            if (flag == 0) {
                //更多舱位查询
                cabinResult = getCabinList(gifd.mapKey);
                if (cabinResult != null && !"".equals(cabinResult)) {
                    cabinList = new ArrayList<RespData_Cabins>();
                    cabinList = cabinResult.getResultObj().data;
                    addAttr("cabinList", cabinList);
                    if (cabinList != null) {
                        String tojson = gson.toJson(cabinList);
                        flights[2] = tojson;
                    }

                }
            }

            //航班详情查询
            result = getIntlFlightDetail.ClientRequest(gifd, Response_GetIntlFlightDetail.class);
            if (result != null && !"".equals(result) && result.getResultObj() != null) {
                hangcheng = result.getResultObj().data.getSegments().size() + "";
                addAttr("hangcheng", hangcheng);


                String StartTime = result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getStartStringTime();
                String[] StartTimes = result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getStartStringTime().split(" ");
                String EndTime = result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getEndStringTime();
                String[] EndTimes = result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getEndStringTime().split(" ");
                String[] startDatas = StartTimes[0].split("-");
                String startData = startDatas[1] + "-" + startDatas[2];
                String startDataCH = startDatas[1] + "月" + startDatas[2] + "日";
                String times = DateFunction.getDistanceTime(result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getFlightTime());
                String[] StartTimes1 = null;
                if ("2".equals(hangcheng)) {
                    StartTimes1 = result.getResultObj().data.getSegments().get(1).getIntlFlights().get(0).getStartStringTime().split(" ");
                    String StartTime1 = result.getResultObj().data.getSegments().get(1).getIntlFlights().get(0).getStartStringTime();
                    String EndTime1 = result.getResultObj().data.getSegments().get(1).getIntlFlights().get(0).getEndStringTime();
                    String[] EndTimes1 = result.getResultObj().data.getSegments().get(1).getIntlFlights().get(0).getEndStringTime().split(" ");
                    String[] startDatas1 = StartTimes1[0].split("-");
                    String startData1 = startDatas1[1] + "-" + startDatas1[2];
                    String startDataCH1 = startDatas1[1] + "月" + startDatas1[2] + "日";
                    String times1 = DateFunction.getDistanceTime(result.getResultObj().data.getSegments().get(1).getIntlFlights().get(0).getFlightTime());

                    addAttr("startTime1", StartTime1);
                    addAttr("EndTimes1", EndTime1);
                    addAttr("startData1", startData1);
                    addAttr("times1", times1);
                    addAttr("startDataCH1", startDataCH1);
                }


                //获取订单详情页面航班信息的星期几
                String tojson = gson.toJson(result.getResultObj().data);
                flights[1] = tojson;
                if (flag == 1) {
                    type = traveltype;
                    addAttr("seatsLeft", seatsLeft);
                    addAttr("traveltype", type);
                    addAttr("cangwei", cangwei);
                    addAttr("price", price);
                    addAttr("mapkey1", mapkey1);
                    addAttr("cangweidesc", cangweidesc);
                    int weeks = getDayofweek(StartTimes[0]);
                    if (weeks == 1) {
                        addAttr("week", "周日");
                    } else if (weeks == 2) {
                        addAttr("week", "周一");
                    } else if (weeks == 3) {
                        addAttr("week", "周二");
                    } else if (weeks == 4) {
                        addAttr("week", "周三");
                    } else if (weeks == 5) {
                        addAttr("week", "周四");
                    } else if (weeks == 6) {
                        addAttr("week", "周五");
                    } else if (weeks == 7) {
                        addAttr("week", "周六");
                    }

                    if ("2".equals(hangcheng)) {
                        int weeks1 = getDayofweek(StartTimes1[0]);
                        if (weeks1 == 1) {
                            addAttr("week1", "周日");
                        } else if (weeks == 2) {
                            addAttr("week1", "周一");
                        } else if (weeks == 3) {
                            addAttr("week1", "周二");
                        } else if (weeks == 4) {
                            addAttr("week1", "周三");
                        } else if (weeks == 5) {
                            addAttr("week1", "周四");
                        } else if (weeks == 6) {
                            addAttr("week1", "周五");
                        } else if (weeks == 7) {
                            addAttr("week1", "周六");
                        }
                    }

                    //结算方式
                    Request_GetPayMethodList gpml = new Request_GetPayMethodList();
                    gpml.setUserId(getUserId());
                    gpml.setBusinessType(type);
                    GetPayMethodList pml = new GetPayMethodList();
                    OutputResult<Response_GetPayMethodList, String> payMethod = pml.ClientRequest(gpml, Response_GetPayMethodList.class);
                    addAttr("payMethod", payMethod.getResultObj().data);

                    TAcOrg tacorg = bos.getTAcOrgByUserId(userId);
                    List<ListEmployee> ListEmployees = bookOrderService.getListEmployees(tacorg.getOrgid());
                    addAttr("ListEmployees", ListEmployees);





        			/*//保险，拷贝邓飞代码  
        			Request_GetInsuranceList gilist = new Request_GetInsuranceList();
        			gilist.setUserId(userId);
        			GetInsuranceList gins = new GetInsuranceList();
        			OutputResult<Response_GetInsuranceList, String> or = gins.ClientRequest(gilist, Response_GetInsuranceList.class);
        			Response_PriceAndDescription baoxian=new Response_PriceAndDescription();
        			for (Response_PriceAndDescription rp : or.getResultObj().getData()) {
        				if(240==rp.getId()){
        					baoxian=rp;
        				}
        			}
        			addAttr("baoxian",baoxian);*/


                    //前台传参
                    addAttr("segments", result.getResultObj().data.getSegments());
                    addAttr("startTime", StartTime);
                    addAttr("EndTimes", EndTime);
                    addAttr("startData", startData);
                    addAttr("times", times);
                    addAttr("startDataCH", startDataCH);
                    addAttr("traveltype", type);
                    addAttr("cangweidesc", cangweidesc);
                    addAttr("Verify", tacorg.getVerify() != null ? tacorg.getVerify() : '0');

                    addAttr("projectMust", tacorg != null && tacorg.getProjectmust() != null ? tacorg.getProjectmust() : "0");
        			
        	
        			
        			/*------预订权限 hanzhicheng添加  2017-10-11-----*/


                    //获取预订权限
                    Integer bookRight = getBookRight(userId, type);
                    Response_ListPassenger passenger = null;
                    List<StaffGroup> listStaff = null;
                    if (bookRight == 0 || bookRight == 4) {
                        //获取可预订员工列表
                        listStaff = getBookStaff(userId, type);
                        addAttr("listStaff", listStaff);
                        //获取常旅客列表
                        passenger = getPassenger();
                        addAttr("Tlist", passenger.data);
                    } else if (bookRight == 1 || bookRight == 2) {
                        listStaff = getBookStaff(userId, type);
                        addAttr("listStaff", listStaff);
                        addAttr("Tlist", null);

                    } else if (bookRight == 3) {
                        //获取常旅客列表
                        passenger = getPassenger();
                        addAttr("Tlist", passenger.data);
                    }

                    statusControl();//费用归属 保险 管理员状态控制
    				
        			/*//获取常旅客列表 
        			Response_ListPassenger passenger = getPassenger();
        			addAttr("Tlist",passenger.data);
        			
        			//获取企业员工列表
        			List<ListEmployee> listStaff = getListStaff();
        			addAttr("listStaff",listStaff);*/
        			
        			
        			/*---------------------------------------------------*/

                }


                //前台传参
                addAttr("segments", result.getResultObj().data.getSegments());
                addAttr("startTime", StartTime);
                addAttr("EndTimes", EndTime);
                addAttr("startData", startData);
                addAttr("times", times);
                addAttr("mapKey", mapKey);
                addAttr("flights", flights);
            }


            //测试数据后台打印
            System.out.println("出发机场：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getOrgAirPortStr());
            System.out.println("到达机场：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getDetAirPortStr());
            System.out.println("起飞时间:" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getStartStringTime());
            System.out.println("到达时间:" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getEndStringTime());
            System.out.println("航空公司名称：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getCarrierStr());
            System.out.println("航班：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getAirline());
            System.out.println("机型：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getPlaneType());
            System.out.println("剩余票数：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getSeatsLeft());

            System.out.println("总时长：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getFlightTime());
            System.out.println("餐饮：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getFood());
            System.out.println("是否中转：" + result.getResultObj().data.getSegments().get(0).getIntlFlights().get(0).getTransitCount());

            if (cabinList != null) {
                for (RespData_Cabins c : cabinList) {
                    System.out.println("票价,不含税:" + c.getTotalPrice());
                    System.out.println("税总价:" + c.getTotalTaxPrice());
                    System.out.println("舱位:" + c.getCangwei());
                    System.out.println("企业协议:" + c.getIsAgreementPrice());
                    System.out.println("剩余票数:" + c.getSeatsLeft());
                    System.out.println("折扣:" + c.getDiscount());
                    System.out.println("舱位类型:" + c.getCangweiDesc());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag == 0) {
            return "intlflight/spaceList";
        } else {
            return "intlflight/orderList";
        }
    }

    @RequestMapping(value="/travels.act")
    @ResponseBody
    public  List getTravellers(String travelType,String which){
        Long userId = getUserId();
        String type = "因公出行".equals(travelType) ? "0" : "1";

        List<Req_Passenger> travels=null;

        //获取预订权限
        Integer bookRight = getBookRight(userId, type);
        Response_ListPassenger passenger = null;
        List<StaffGroup> listStaff = null;
        if (bookRight == 0 || bookRight == 4) {
            //获取可预订员工列表
            listStaff = getBookStaff(userId, type);
            //获取常旅客列表
            passenger = getPassenger();
            travels=passenger.data;
            if("employ".equals(which)){
                return listStaff;
            }
            return travels;
        } else if (bookRight == 1 || bookRight == 2) {
            listStaff = getBookStaff(userId, type);
            if("employ".equals(which)){
                return listStaff;
            }

            return travels;
        } else if (bookRight == 3) {
            //获取常旅客列表
            passenger = getPassenger();
            travels=passenger.data;

            if("employ".equals(which)){
                return listStaff;
            }
            return travels;
        }


        return null;
    }


    /**
     * 获取审核人列表
     *
     * @return
     */
    @RequestMapping(value = "/getAuditorList.act")
    @ResponseBody
    public String getAuditorList() {
        List<Auditor> list = null;
        ListAuditor ls = new ListAuditor();
        Request_ListAuditor para = new Request_ListAuditor();
        para.setUserId(getUserId());
        OutputResult<Response_ListAuditor, Response_BaseMsg> result = ls.ClientRequest(para,
                Response_ListAuditor.class, Response_BaseMsg.class);

        Response_ListAuditor resultObj = result.getResultObj();
        if (resultObj != null) {
            list = resultObj.getData();
        }
        try {
            ajaxWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response_ListPassenger getPassenger() {
        Request_ListPassenger para = new Request_ListPassenger();
        para.userId = getUserId();
        List<TTravelPassenger> tTravelP = passengerService.getListPassenger(para);

        List<Req_Passenger> list1 = new ArrayList<Req_Passenger>();
        Response_ListPassenger passenger = new Response_ListPassenger();
        for (int i = 0; i < tTravelP.size(); i++) {
            List<ReqData_CertificateMessage> list2 = new ArrayList<ReqData_CertificateMessage>();
            TTravelPassenger passengers = tTravelP.get(i);
            ReqData_CertificateMessage reqMessage = new ReqData_CertificateMessage();
            Req_Passenger personMessage = new Req_Passenger();
            int intValue = passengers.getId().intValue();
            personMessage.setId(intValue);
            personMessage.setName(passengers.getName());
            personMessage.setMobile(passengers.getMobilephone());
            personMessage.setEmail(passengers.getEmail());
            personMessage.setSex(null);
            personMessage.setBirthday(null);
            String passengerType = passengers.getPassengerType();
            if (StringUtil.isEmpty(passengerType)
                    || "null".equals(passengerType)) {
                personMessage.setType(null);
            } else {
                personMessage.setType(Integer.valueOf(passengerType));
            }
            String idtype = passengers.getIdtype();
            if (StringUtil.isEmpty(idtype)) {
                reqMessage.setType(null);
            } else {
                reqMessage.setType(Integer.valueOf(idtype));
            }
            reqMessage.setNum(passengers.getIdnumber());
            list2.add(reqMessage);
            personMessage.setIds(list2);
            list1.add(personMessage);
        }
        passenger.setData(list1);
        return passenger;
    }

    /**
     * 更多舱位
     */
    private OutputResult<Response_GetCabinList, String> getCabinList(String mapKey) {
        try {
            Request_GetCabinList gcl = new Request_GetCabinList();
            gcl.mapKey = mapKey;
            gcl.userId = getUserId();

            GetCabinList getCabinList = new GetCabinList();

            OutputResult<Response_GetCabinList, String> result = getCabinList.ClientRequest(gcl, Response_GetCabinList.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退改签
     * 更多舱位页面Ajax请求
     */
    @RequestMapping(value = "/getIntlTicketRule.act", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private void getIntlTicketRule(/*String mapKey,String cangwei*/) {
        String mapKey = req.getParameter("mapKey");
        String cangwei = req.getParameter("cangwei");
        try {
            Request_GetIntlTicketRule gitr = new Request_GetIntlTicketRule();
            Req_Data_QuitUpdateTicket data = new Req_Data_QuitUpdateTicket();
            data.mapKey = mapKey;
            data.cangwei = cangwei;

            gitr.userId = getUserId();
            gitr.data = data;
//        		gitr.data.cangwei = cangwei;

            GetIntlTicketRule getIntlTicketRule = new GetIntlTicketRule();
            List<Resp_Data_QuitUpdateTicket> ruleResult = null;

            OutputResult<Response_GetIntlTicketRule, String> result = getIntlTicketRule.ClientRequest(gitr, Response_GetIntlTicketRule.class);
            if (result.getResultObj() != null && !"".equals(result.getResultObj().data)) {
                ruleResult = result.getResultObj().data;
            }

            String tuigai = "";
            String wuji = "";            //误机
            String flightType = "";        //1-去程，2-返程
            String tuipiao = "";        //退票
            String gaiqian = "";        //改签

            if (ruleResult != null && !"".equals(ruleResult)) {
                gaiqian = ruleResult.get(0).getGaiqian();
                flightType = ruleResult.get(0).getFlightType() + "";
                wuji = ruleResult.get(0).getWuji();
                tuipiao = ruleResult.get(0).getTuipiao();
            }
            PrintWriter pw;
            try {
                tuigai = flightType + "|" + tuipiao + "|" + gaiqian + "|" + wuji;
                pw = resp.getWriter();
                pw.write(tuigai);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

//        		return ruleResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        	return null;
    }


    @RequestMapping(value = "/getIntlOrderJSON.act", method = {RequestMethod.GET})
    @ResponseBody
    public Response_GetIntlOrderList getIntlOrderJSON(Integer status, Integer start, Integer count) {
        Response_GetIntlOrderList response = null;
        try {
            Request_GetIntlOrderList para = new Request_GetIntlOrderList();
            Req_Data_IntlOrderListInfo data = new Req_Data_IntlOrderListInfo();
            data.setCount(count);
            data.setStart(start);
            data.setStatus(status);
            para.setData(data);
            para.userId = getUserId();
            GetIntlOrderList bt = new GetIntlOrderList();

            OutputResult<Response_GetIntlOrderList, String> result = bt.ClientRequest(para, Response_GetIntlOrderList.class);
            response = result.getResultObj();

        } catch (Exception e) {
        }
        return response;
    }

    @RequestMapping("/orderDetail.act")
    public String getIntlOrderDetail(Integer orderId) {
        try {
            Response_GetIntlOrderDetail resp = null;
            Request_GetIntlOrderDetail detail = new Request_GetIntlOrderDetail();
            detail.userId = getUserId();
            detail.setOrderID(orderId);
            GetIntlOrderDetail orderDetails = new GetIntlOrderDetail();
            OutputResult<Response_GetIntlOrderDetail, String> result = orderDetails.ClientRequest(detail, Response_GetIntlOrderDetail.class);
            Response_GetIntlOrderDetail resultObj = result.getResultObj();
            Resp_Data_IntlOrderDetail res = resultObj.data;
            List<IntlOrderDetail_Segment> airlines = new ArrayList();
            List<IntlOrderDetail_Segment> list = res.getSegments();
            for (IntlOrderDetail_Segment seg : list) {
                if (seg.type == 2) {
                    airlines.add(seg);
                }

            }

            //审批流
            GetApprovalProcess gp = new GetApprovalProcess();
            Request_GetApprovalProcess req = new Request_GetApprovalProcess();
            req.businessType=1;
            req.orderno=resultObj.data.orderNo;
            OutputResult<Response_GetApprovalProcess, String> result1 = gp.ClientRequest(req, Response_GetApprovalProcess.class);

            addAttr("user",getUser());
            addAttr("result1",result1.getResultObj());
            addAttr("changes", airlines);
            addAttr("flag", "2");
            addAttr("pageType", "订单详情");
            addAttr("userId", getUserId());
            addAttr("result", resultObj.data);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + resultObj.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    /**
     * @param date是为则默认今天日期、可自行设置“2013-06-03”格式的日期
     * @return 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
     */

    public static int getDayofweek(String date) {
        Calendar cal = Calendar.getInstance();
        //cal.setTime(new Date(System.currentTimeMillis()));
        if (date.equals("")) {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else {
            cal.setTime(new Date(getDateByStr2(date).getTime()));
        }
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getDateByStr2(String dd) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sd.parse(dd);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }
        return date;
    }


    public List<ListEmployee> getListStaff() {
        ListStaffServiceImpl service = ApplicationContextHelper.getInstance().getBean(ListStaffServiceImpl.class);
        UserService userService = ApplicationContextHelper.getInstance().getBean(UserService.class);
        Request_ListStaff rls = new Request_ListStaff();
        rls.userId = getUserId();
        List<TAcUser> lta = listStaffService.getListStaff(rls);

        List<ListEmployee> el = new ArrayList<ListEmployee>();
        for (int i = 0; i < lta.size(); i++) {
            TAcUser ta = lta.get(i);
            ListEmployee lel = new ListEmployee();
            lel.setId(ta.getUserid().intValue());
            lel.setName(ta.getUsername());
            lel.setMobile(ta.getPhone());
            lel.setEmail(ta.getEmail());
            lel.setType(1);
            if (ta.getBirthday() != null && !"".equals(ta.getBirthday())) {
                lel.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(ta.getBirthday()));
            }
            lel.setSex(ta.getSex() == null ? 2 : Integer.valueOf(ta.getSex()));
            //数字 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
            List<Response_CertificateMessage> rc = new ArrayList<Response_CertificateMessage>();
            Response_CertificateMessage cm = null;
            if (ta.getIdcard() != null && !"".equals(ta.getIdcard())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getIdcard());
                cm.setType(1);
                rc.add(cm);
            }

            if (!"".equals(ta.getPassport()) && ta.getPassport() != null) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getPassport());
                cm.setType(2);
                rc.add(cm);
            }
            if (ta.getDlwltxz() != null && !"".equals(ta.getDlwltxz())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getDlwltxz());
                cm.setType(3);
                rc.add(cm);
            }
            if (ta.getHxz() != null && !"".equals(ta.getHxz())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getHxz());
                cm.setType(4);
                rc.add(cm);
            }
            if (ta.getJgz() != null && !"".equals(ta.getJgz())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getJgz());
                cm.setType(5);
                rc.add(cm);
            }
            if (ta.getGatxz() != null && !"".equals(ta.getGatxz())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getGatxz());
                cm.setType(6);
                rc.add(cm);
            }
            if (ta.getRtz() != null && !"".equals(ta.getRtz())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getRtz());
                cm.setType(7);
                rc.add(cm);
            }
            if (ta.getOtherNum() != null && !"".equals(ta.getOtherNum())) {
                cm = new Response_CertificateMessage();
                cm.setNum(ta.getOtherNum());
                cm.setType(99);
                rc.add(cm);
            }
            lel.setIds(rc);
            if (ta.getOrgid() != null && !"".equals(ta.getOrgid())) {
                lel.setDepartmentId(ta.getOrgid().intValue());
            }
            lel.setDepartmentName(service.getOrgData(ta.getOrgid()).getOrgname());
            lel.setMileageCardNo(ta.getMileage());
            Long userId = rls.getUserId();
            lel.setIsplatFormOrg(userService.isPlatFormOrg(userId));
            el.add(lel);
        }

        return el;

    }

    //可预订权限查询
    public Integer getBookRight(Long userId, String type) {
        Request_GetBookRight rg = new Request_GetBookRight();
        rg.userId = Integer.valueOf(userId.toString());
        rg.businessType = Integer.valueOf(type) + 1;
        GetBookRight getBookRight = new GetBookRight();//可预订权限查询接口
        OutputResult<Response_GetBookRight, String> clientRequest = getBookRight.ClientRequest(rg, Response_GetBookRight.class);
        Integer data = clientRequest.getResultObj().data;//预订权限类别：0-不限制预订；1-仅允许给自己预订；2-仅允许给指定企业员工预订；3-仅允许给常旅客预订；4-允许给自己和常旅客预订

        return data;
    }

    //可预订员工列表
    public List<StaffGroup> getBookStaff(Long userId, String type) {
        Request_GetBookStaffList request_GetBookStaffList = new Request_GetBookStaffList();
        request_GetBookStaffList.userId = Integer.valueOf(userId.toString());
        request_GetBookStaffList.businessType = Integer.valueOf(type) + 1;
        Page page = new Page();
        page.index = 1;
        page.size = 100;
        request_GetBookStaffList.page = page;
        GetBookStaffList getBookStaffList = new GetBookStaffList();
        Response_GetBookStaffList resultObj = getBookStaffList.ClientRequest(request_GetBookStaffList, Response_GetBookStaffList.class).getResultObj();
        List<StaffGroup> list = resultObj.data.list;

        return list;
    }

    //ajax获取费用归属部门
    @RequestMapping(value = "/getListEmployee.act", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ListEmployee> getListEmployee(String userId) {
        List<ListEmployee> ListEmployees = null;
        if (userId != null) {
            TAcOrg tacorg = bos.getTAcOrgByUserId(Long.valueOf(userId));
            ListEmployees = bookOrderService.getListEmployees(tacorg.getOrgid());
            addAttr("ListEmployees", ListEmployees);

        }

        return ListEmployees;
    }


    @RequestMapping(value = "/getPassengerById.act", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TTravelPassenger getPassengerById(String pid) {
        TTravelPassenger passenger = null;
        if (pid != null && !"".equals(pid)) {

            passenger = bookOrderService.getPassenger(pid);

        }

        return passenger;
    }

    /**
     * 国际机票验证乘机人、出发时间、起飞地点 和数据库是否用一致的， 如果有说明此乘机人预订了重复的机票，给他一个提示
     *
     * @return
     */
    @RequestMapping(value = "/passengerCheck.act", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String passengercheck(String passengerStrIds, String airline, String cangwei, String flightDate) {
        List<Object[]> bookIntlFlights = null;
        String pid = null;
        boolean flag = false;

        if (passengerStrIds != null && airline != null && cangwei != null && flightDate != null) {
            flightDate = flightDate.split(" ")[0].trim();
            String[] pids = passengerStrIds.split(",");
            l1:
            for (int i = 0; i < pids.length; i++) {
                if (!flag) {
                    bookIntlFlights = bookOrderService.getBookIntlFlights(pids[i], airline, flightDate);
                    if (bookIntlFlights != null && bookIntlFlights.size() > 0) {

                        for (int j = 0; j < bookIntlFlights.size(); j++) {
                            if (cangwei.contains(bookIntlFlights.get(j)[2].toString())) {
                                pid = pids[i];
                                flag = true;
                                break l1;
                            }

                        }
                    }
                }


            }
        }

        return pid;
    }


}
