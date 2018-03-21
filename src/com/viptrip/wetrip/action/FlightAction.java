package com.viptrip.wetrip.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nethsoft.zhxq.core.util.ObjectUtil;
import com.viptrip.autoissue.service.AutoTicketService;
import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.BussinessType;
import com.viptrip.base.common.MyEnum.IdType;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.controller.GetRulesByEnterprise;
import com.viptrip.common.model.Request_GetRulesByEnterprise;
import com.viptrip.common.model.Response_GetRulesByEnterprise;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.ibeserver.service.ReadIBEDataService;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wechat.model.WechatBase;
import com.viptrip.wetrip.controller.BookAirTicket;
import com.viptrip.wetrip.controller.GetChildBabyPrice;
import com.viptrip.wetrip.controller.GetFlightDetail;
import com.viptrip.wetrip.controller.GetFlightList;
import com.viptrip.wetrip.controller.GetPayMethodList;
import com.viptrip.wetrip.controller.ToPay;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TEndorse;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.policy.TExcessManage;
import com.viptrip.wetrip.model.Request_BookAirTicket;
import com.viptrip.wetrip.model.Request_GetChildBabyPrice;
import com.viptrip.wetrip.model.Request_GetFlightDetail;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.Request_GetPayMethodList;
import com.viptrip.wetrip.model.Request_ToPay;
import com.viptrip.wetrip.model.Response_BookAirTicket;
import com.viptrip.wetrip.model.Response_GetChildBabyPrice;
import com.viptrip.wetrip.model.Response_GetFlightDetail;
import com.viptrip.wetrip.model.Response_GetFlightList;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.model.Response_ToPay;
import com.viptrip.wetrip.model.base.Response_PriceAndDescription;
import com.viptrip.wetrip.model.employees.ListEmployee;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_PassengerMessage;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.flight.type.TripType;
import com.viptrip.wetrip.model.policy.DomTicketModel;
import com.viptrip.wetrip.service.BookOrderService;
import com.viptrip.wetrip.service.IFlightService;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.service.PolicyManageService;
import com.viptrip.wetrip.util.CommUtil;
import com.viptrip.wetrip.util.JsonUtil;
import com.viptrip.wetrip.vo.BookJson;
import com.viptrip.wetrip.vo.BookOrderInfo;
import com.viptrip.wetrip.vo.FlightListFilterParam;
import com.viptrip.wetrip.vo.UserInfo;

import etuf.v1_0.model.base.Enum.PassengerType;
import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/flight")
@Scope("prototype")
public class FlightAction extends BaseAction{
	private static Logger logger = LoggerFactory.getLogger(FlightAction.class);
	
	private static final String flightsearch = "flight/ticket_search";//机票列表页面
	private static final String flightlist = "flight/ticket_list";//机票列表页面
	private static final String flightDetail = "flight/ticket_detail";//机票详情页面
	private static final String childBabyPrice = "flight/price_child";//跳转页面
	private static final String toBookTicket = "public/orderList";//跳转页面
	private static final String toExcessReason = "public/excessReason";//跳转页面
	
	@SuppressWarnings("unused")
	@Resource
	private IFlightService service;
	
	@Resource
	private ReadIBEDataService rimservice;
	
	@Resource
	private BookOrderService bookOrderService;
	
	@Resource
	private IGetOrderService orderService;
	
	@Resource
	private AutoTicketService autoTicketService;
	
	@Resource
	private PolicyManageService policymanageService;
	
	@RequestMapping("search.act")
	public String toFlightSearch(){
		TAcUser user = getUser();
		Long userId = getUserId();
		addAttr("user",user);
		addAttr("userId",userId);
		TAcOrg taco = policymanageService.getSuperTAcOrg(user.getOrgid());
//		getRulesByEnterprise
		
		GetRulesByEnterprise rulesClient=new GetRulesByEnterprise();
		Request_GetRulesByEnterprise req=new Request_GetRulesByEnterprise();
		List<Integer> businessType=new ArrayList<Integer>();
		businessType.add(BussinessType.国内机票.getValue());
		addAttr("policyControl", 0);//默认0，未开启
		req.businessTypes=businessType;
		req.enterpriseId=taco.getOrgid().intValue();
		req.filter="1110";
		OutputResult<Response_GetRulesByEnterprise, String> response=rulesClient.ClientRequest(req, Response_GetRulesByEnterprise.class);
		if(response.IsSucceed() && response.getResultObj()!=null){
		    DomTicketModel ticket = response.getResultObj().data.getTicket();
		    if(ticket!=null){
                Integer state=ticket.state;
    		    addAttr("policyControl", state);
		    }
		}
		response.getResultObj();
		return flightsearch;
	}
	
//	@RequestMapping("/toExcessReson.act")
	@RequestMapping(value = "/toExcessReson.act",method={RequestMethod.GET,RequestMethod.POST} )
	public String toExcessReson(BookOrderInfo bf,RedirectAttributes attr) throws Exception{
        List<TExcessManage> excess=policymanageService.getExcess();
        List<String> excessList=new ArrayList<String>();
        for (int i = 0; i < excess.size(); i++) {
            if(!excessList.contains(excess.get(i).getReason()) && excess.get(i).getType()<1000){
                excessList.add(excess.get(i).getReason());
            }
        }
        bf.setExcessList(excessList);
	    addAttr("bf",bf);
        String json=JsonUtil.ObjectToJsonStr(bf);
        addAttr("bookinfo",json);
	    return toExcessReason;
	}
	
	//机票列表查询
	@RequestMapping(value = "/getFlightList.act",method={RequestMethod.GET,RequestMethod.POST} )
	public String getFlightList(Long timestamp,Integer flag,Integer tripType,Integer cabinType,String airline,Integer[] flowId,String[] depAirport,String[] arrAirport,String[] date,Integer type,FlightListFilterParam fp,String order,String direction,String mapKey,String mapKey1) throws IOException{
		String selectusers=this.getReq().getParameter("selectusers");
//      110101198711094550_294274_5660_5660_管理审核员（测试）
//    |110101198001010117_302980_5660_5660_测试锋
//    |_299438_5660_5660_钱晔
//    |110101198001010133_302981_5660_5660_李搜索
	      //差旅可预订规则信息
        String excessinfo1=this.getReq().getParameter("excessinfo1");
        addAttr("excessinfo1",excessinfo1);
        
	    GetFlightList getFlightList = new GetFlightList();
	    ReqData_GetFlightList para = new ReqData_GetFlightList();
		if(StringUtils.isNotEmpty(selectusers)){
		      //乘机人信息
	        List<ReqData_GetFlightList_Passenger> passengers=new ArrayList<ReqData_GetFlightList_Passenger>();
	        ReqData_GetFlightList_Passenger passenger=new ReqData_GetFlightList_Passenger();
	        String[] splits = selectusers.split("\\|");
	        List<ReqData_GetFlightList_PassengerMessage> passengerMessages = converToPassengerList(splits);
		    passenger.setPassengers(passengerMessages);
		    passenger.setType(PassengerType.成人.getPassengerType());
		    passenger.setCount(splits.length);
		    passengers.add(passenger);
            para.setPassenger(passengers);
		}
		Set<String> alcodes = new HashSet<String>();
		para.setTripType(tripType);
		para.setAirline(airline);
		para.setCabinType(cabinType);
		
		List<ReqData_GetFlightList_TripInfo> trips = new ArrayList<>();
		if(null==flowId){
			flowId = new Integer[]{1};
		}
		for(int i=0;i< flowId.length;i++){
			ReqData_GetFlightList_TripInfo tripinfo = new ReqData_GetFlightList_TripInfo(flowId[i], depAirport[i], arrAirport[i], date[i]);
			trips.add(tripinfo);
		}
		if(trips.size()==2){
			if(2==flag){//如果是第二程
//				Collections.reverse(trips);
				para.setTripInfo(trips.subList(1, 2));
			}else{//如果是第一程
				para.setTripInfo(trips.subList(0, 1));
			}
		}else{
			para.setTripInfo(trips);
		}
		para.setTripWay(type);
		
		if(TripType.OneWay.getCode() == para.getTripType()){//单程
			List<ReqData_GetFlightList_TripInfo> tripInfo = para.getTripInfo();
			if(null!=tripInfo && tripInfo.size()>0){
				for(ReqData_GetFlightList_TripInfo ti:tripInfo){
					ti.setFlowId(1);//设置航程id
				}
			}
		}
		Request_GetFlightList request_GetFlightList = new Request_GetFlightList(para);
		request_GetFlightList.setUserId(getUserId());
		
		OutputResult<Response_GetFlightList, String> result = getFlightList.ClientRequest(request_GetFlightList, Response_GetFlightList.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			if(null!=result.getResultObj()){
				List<RespData_GetFlightList> list = filterParam(result.getResultObj().getData(), fp);//过滤条件
				list = sortList(list, order, direction);//排序
				if(list!=null && list.size()>0){
					for(RespData_GetFlightList d:list){
						alcodes.add(d.getAirline());
					}
				}
				obj = success(list);
			}
		}else{
			obj = fail(result.result);
		}

		String excessinfo=this.getReq().getParameter("excessinfo");
        addAttr("excessinfo",excessinfo);
        
		addAttr("alcodes",alcodes);//存放所有的航空公司二字码
		addAttr("timestamp",timestamp);
		addAttr("trips",trips);
		addAttr("mapKey",mapKey);
		addAttr("mapKey1",mapKey1);
		addAttr("flag",flag);
		addAttr("type",type);
		addAttr("tripType",tripType);
		addAttr("cabinType",cabinType);
		addAttr("airline",airline);
		addAttr("result",obj);
		addAttr("fp",fp);
		addAttr("order",order);
		addAttr("direction",direction);
		addAttr("selectusers",selectusers);
		if(obj.getStatus() == 0){
			return flightlist;
		}else{
			if(1==flag){
				return flightsearch;
			}else{
				return flightlist;
			}
		}
		
	}

    public List<ReqData_GetFlightList_PassengerMessage> converToPassengerList(String[] splits) {
        List<ReqData_GetFlightList_PassengerMessage> passengerMessages=new ArrayList<ReqData_GetFlightList_PassengerMessage>();
        for (int i = 0; i < splits.length; i++) {
            ReqData_GetFlightList_PassengerMessage passengerMessage=new ReqData_GetFlightList_PassengerMessage();
            String names=splits[i];
            passengerMessage.idcard=names.split("_")[0];
            passengerMessage.userid=Long.valueOf(names.split("_")[1]);
            passengerMessage.deptid=Long.valueOf(names.split("_")[2]);
            passengerMessage.orgid=Long.valueOf(names.split("_")[3]);
            passengerMessage.type=IdType.二代身份证.getValue();
            passengerMessage.username=names.split("_")[4];
            passengerMessages.add(passengerMessage);
        }
        return passengerMessages;
    }
	
	/**
	 * 异步查询前一天、后一天的最低价
	 * @param depAirport
	 * @param arrAirport
	 * @param cabinType
	 * @param date
	 * @return
	 */
	@RequestMapping("/getBottomPrice.act")
	@ResponseBody
	public AjaxResp getFlightThreeDayBottomPrice(String airline,String depAirport,String arrAirport,Integer cabinType,String date){
		AjaxResp result = null;
		if(!StringUtil.isEmpty(depAirport)&&!StringUtil.isEmpty(arrAirport)&&!StringUtil.isEmpty(date)&&null!=cabinType){
			Double[] prices = new Double[2];
			GetFlightList getFlightList = new GetFlightList();
			
			String[] d = new String[2];
			try {
				Date d1 = DateUtil.SDF_yyyyOMMOdd.parse(date);
				d[0] = DateUtil.SDF_yyyyOMMOdd.format(new Date(d1.getTime()-24*3600*1000l));
				d[1] = DateUtil.SDF_yyyyOMMOdd.format(new Date(d1.getTime()+24*3600*1000l));
				System.out
						.println("FlightAction.getFlightThreeDayBottomPrice()==d0==" + d[0] + ",==d1==" + d[1]);
			} catch (ParseException e) {
				logger.error(StringUtil.getExceptionStr(e));
			}
			for(int i=0;i<d.length;i++){
				String dt = d[i];
				if(dt!=null){
					ReqData_GetFlightList para = new ReqData_GetFlightList();
					para.setAirline(airline);
					para.setCabinType(cabinType);
					para.setPassenger(null);
					para.setTripType(1);
					List<ReqData_GetFlightList_TripInfo> tripInfo = new ArrayList<ReqData_GetFlightList_TripInfo>();
					tripInfo.add(new ReqData_GetFlightList_TripInfo(1, depAirport, arrAirport, dt));
					para.setTripInfo(tripInfo);
					Request_GetFlightList request = new Request_GetFlightList(para);
					request.setUserId(getUserId());
					OutputResult<Response_GetFlightList, String> or = getFlightList.ClientRequest(request, Response_GetFlightList.class);
					if(or.IsSucceed()){
						List<RespData_GetFlightList> data = or.getResultObj().getData();
						if(data!=null && data.get(0)!=null){
							RespData_GetFlightList respData_GetFlightList = data.get(0);
							prices[i] = respData_GetFlightList.getPrice();
						}
					}
				}
			}
			result = success(prices);
		}else{
			result = fail();
		}
		return result;
	}
    /**
     * 查询维护的超标理由
     * 
     * @param type
     * 
     */
    public List<TExcessManage> getExcess(String type) {
        String hql = "select distinct r.reason from TExcessManage r where type < 1000";
        List<TExcessManage> list =comDao.queryForList(hql,null);
//        List<TExcessManage> list =comDaocomDao.queryBySQL(hql,"");// this.dao.findByHQL(hql);
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
    
    public ArrayList<String> getResson(String qucheng) {
        String travel = "";
        ArrayList<String> list=new ArrayList<String>();
        if(qucheng != null && qucheng.length() > 1){
            String[] qstr = qucheng.split("~");
            if(qstr.length > 0){
                for(String str : qstr){
                    int num = 0 ; 
                    boolean IsExist = true ;
                    String names = "";
                    for(int j= 0;j<list.size();j++){
                        if(list.get(j).toString().split("_")[0].toString().equals(str.split("_")[0].toString()))
                        {
                            num = j;
                            IsExist = false;
                         break;
                        }                       
                    }
                        if(IsExist)
                        list.add(str);
                        else
                        list.set(num, list.get(num).toString()+"、"+str.split("_")[2].toString());
                }
            }
        }
        return list;
    }
    
	//机票详情查询
	@RequestMapping("/getFlightDetail.act")
	public String getFlightDetail(Long timestamp,Integer flag,Integer tripType,Integer cabinType,String airline,Integer[] flowId,String[] depAirport,String[] arrAirport,String[] date,Integer type,FlightListFilterParam fp,String order,String direction,String mapKey,String mapKey1) throws IOException{
		GetFlightDetail gd = new GetFlightDetail();
		String selectusers=this.getReq().getParameter("selectusers");
		String tripWay=this.getReq().getParameter("type");
		List<ReqData_GetFlightList_PassengerMessage> passengerMessages =null;
		if(StringUtils.isNotEmpty(selectusers)){
		  //乘机人信息
		  String[] splits = selectusers.split("\\|");
		  passengerMessages = converToPassengerList(splits);
		}
		
		OutputResult<Response_GetFlightDetail, String> result = gd.ClientRequest(new Request_GetFlightDetail(mapKey,getUserId(),Integer.valueOf(tripWay),passengerMessages), Response_GetFlightDetail.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			if(null != result.getResultObj()){
				obj = success(result.getResultObj().getData());
			}else{
				obj =success(result.getResultObj());
			}
		}else{
			obj = fail(result.result);
		}
		
		
		List<ReqData_GetFlightList_TripInfo> trips = new ArrayList<>();
		for(int i=0;i< flowId.length;i++){
			ReqData_GetFlightList_TripInfo tripinfo = new ReqData_GetFlightList_TripInfo(flowId[i], depAirport[i], arrAirport[i], date[i]);
			trips.add(tripinfo);
		}
		
		//判断是ajax请求还是常规请求
		if(isAjaxMethod()){
			ajaxWrite(obj);
			return null;
		}else{
		    String excessinfo1=this.getReq().getParameter("excessinfo1");
	        addAttr("excessinfo1",excessinfo1);
			addAttr("timestamp",timestamp);
			addAttr("result",obj);
			addAttr("mapKey",mapKey);
			addAttr("mapKey1",mapKey1);
			addAttr("flag",flag);
			addAttr("type",type);
			addAttr("trips",trips);
			addAttr("tripType",tripType);
			addAttr("cabinType",cabinType);
			addAttr("airline",airline);
			addAttr("selectusers",selectusers);
			return flightDetail;
		}
	}
	
	//儿童婴儿票价查询
	@RequestMapping("/getChildBabyPrice.act")
	public String getChildBabyPrice(Request_GetChildBabyPrice para) throws IOException{
		GetChildBabyPrice gcbp = new GetChildBabyPrice();
		OutputResult<Response_GetChildBabyPrice, String> result = gcbp.ClientRequest(para, Response_GetChildBabyPrice.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			obj = success(result.getResultObj());
		}else{
			obj = fail(result.result);
		}
		//判断是ajax请求还是常规请求
		if(isAjaxMethod()){
			ajaxWrite(obj);
			return null;
		}else{
			addAttr("result",obj);
			return childBabyPrice;
		}
	}
	
	//机票预定
	@RequestMapping("/bookAirTicket.act")
	public String bookAirTicket(BookOrderInfo bf,RedirectAttributes attr) throws Exception{
		/*Request_BookAirTicket para=new Request_BookAirTicket();
		para.source=bf.getSource();
		para.orderType=1;
		para.businessType=bf.getTraveType();*/
		Request_BookAirTicket para =bookOrderService.getRequestBookOrderInfo(bf,getUserId());
		if(para!=null){
			ReqData_BookAirTicket data=para.data;
			if(null!=data){
				Long timestamp = data.getTimestamp();
				if(null == timestamp || timestamp <= 0){
					data.setTimestamp(System.currentTimeMillis());
				}
				BookAirTicket bt = new BookAirTicket();
				OutputResult<Response_BookAirTicket, String> result = bt.ClientRequest(para, Response_BookAirTicket.class);
				AjaxResp obj = null;
				if(result.IsSucceed()){
					obj = success(result.getResultObj());
				}else{
					obj = fail(result.result);
					attr.addFlashAttribute("result",result.result);
					return "redirect:/error/returnError.act";
				}
				if(result.getResultObj().data.orderId<0){
					if(result.getResultObj().data.orderId==-13){
						//String error=ErrShareUtil.get();
						String error=result.getResultObj().data.getErrorStr();
						attr.addFlashAttribute("result",error);
					}else{
						attr.addFlashAttribute("result",MyEnum.BookErrorTypeCode.getBookErrorStr(result.getResultObj().data.orderId));
					}
					return "redirect:/error/returnError.act";
				}
					/*addAttr("result",obj);
					
					attr.addAttribute("orderId", Long.valueOf(result.getResultObj().data.orderId));
					return "/public/pay";*/
				attr.addAttribute("orderId",result.getResultObj().data.orderId);
				return "redirect:/flight/toPay.act";
			       //toPay(Long.valueOf(result.getResultObj().data.orderId),attr);
			}else{
				attr.addFlashAttribute("result",MyEnum.BookErrorTypeCode.getBookErrorStr(MyEnum.BookErrorTypeCode.参数不全.getValue()));
				return "redirect:/error/returnError.act";
			}
		}else{
			attr.addFlashAttribute("result",MyEnum.BookErrorTypeCode.getBookErrorStr(MyEnum.BookErrorTypeCode.参数不全.getValue()));
			return "redirect:/error/returnError.act";	
		}
		
		
	}
	
	/**
	 * 列表查询过滤数据
	 * @param data
	 * @param fp
	 * @return
	 */
	private List<RespData_GetFlightList> filterParam(List<RespData_GetFlightList> data,FlightListFilterParam fp){
		List<RespData_GetFlightList> result = null;
		if(ObjectUtil.isNotEmpty(data)){
			if(null == fp){
				result = data;
			}else{
				String cabin = fp.getListParam_cabin();
				String code = fp.getListParam_code();
				Integer area = fp.getListParam_timeArea();
				if(StringUtil.isEmpty(code)&&StringUtil.isEmpty(cabin)&&null==area){
					result = data;
				}else{
					Iterator<RespData_GetFlightList> iterator = data.iterator();
					while (iterator.hasNext()) {
						RespData_GetFlightList respData_GetFlightList = (RespData_GetFlightList) iterator
								.next();
						if(!StringUtil.isEmpty(code) && code.equals(respData_GetFlightList.getAirline())){//过滤航空公司
							iterator.remove();
							continue;
						}
						if(!StringUtil.isEmpty(cabin)){//过滤舱位
							WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
							ServletContext sc = webApplicationContext.getServletContext();
							@SuppressWarnings("unchecked")
							Map<String,List<String>> map = (Map<String, List<String>>) sc.getAttribute(Const.APP_MAP_CABINCODE);
							List<String> list = map.get(respData_GetFlightList.getAirline() + "_" + cabin);
							if(null==list || !list.contains(respData_GetFlightList.getCabin())){//舱位类型不匹配
								iterator.remove();
								continue;
							}
							
						}
						if(null!=area&&area>0){//过滤时间
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
							Date depDateTime = respData_GetFlightList.getDepDateTime();
							String time = DateUtil.format(depDateTime, pattern);
							try {
								Date s = DateUtil.parse(start, pattern);
								Date e = DateUtil.parse(end, pattern);
								Date t = DateUtil.parse(time, pattern);
								if(t.getTime()<s.getTime() || t.getTime()>e.getTime()){//如果不在范围内
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
	
	private List<RespData_GetFlightList> sortList(List<RespData_GetFlightList> data,String order,String direction){
		if(!StringUtil.isEmpty(order)){
			if(null!=data && data.size()>0){
				if("date".equals(order)){
					Collections.sort(data, new Comparator<RespData_GetFlightList>() {
						@Override
						public int compare(RespData_GetFlightList o1,
								RespData_GetFlightList o2) {
							Date time1 = o1.getDepDateTime();
							Date time2 = o2.getDepDateTime();
							return time1.compareTo(time2);
							/*if(time1.getTime()>time2.getTime()){
								return 1;
							}else if(time1.getTime()==time2.getTime()){
								return 0;
							}else{
								return -1;
							}*/
						}
					});
					if("desc".equals(direction)){
						Collections.reverse(data);
					}
				}
				if("price".equals(order)){
					Collections.sort(data, new Comparator<RespData_GetFlightList>() {
						@Override
						public int compare(RespData_GetFlightList o1,
								RespData_GetFlightList o2) {
							Double price1 = o1.getPrice();
							Double price2 = o2.getPrice();
							return price1.compareTo(price2);
							/*if(price1>price2){
								return 1;
							}else if(price1==price2){
								return 0;
							}else{
								return -1;
							}*/
						}
					});
					if("desc".equals(direction)){
						Collections.reverse(data);
					}
				}
			}
		}
		return data;
	}
	
	
	@RequestMapping("/LoadDataToBookTicket.act")
	public String LoadDataToBookTicket(String bf,UserInfo userinfo) throws IOException{
		BookOrderInfo bookinfo=new BookOrderInfo();
		bookinfo=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
		addAttr("bookinfo",bookinfo);
		return "redirect:toBookTicket.act"; 
		
	}
	@Resource
	private ComDao comDao;
	
	//跳转到订单填写页  hx
	@RequestMapping("/toBookTicket.act")
	public String toBookTicket(String mapKey,Integer type,Long timestamp,String bf,UserInfo userinfo,RedirectAttributes attr) throws IOException{
		try {
		    if(bf==null){
		        //http://10.67.141.137:8080/wetrip/flight/toBookTicket.act
		       // {"checkMans":[],"childBabySuggestBook":0,"dijiaReason":"起飞或达到时间不合适","flightType":2,"jumpCount":1,"mapKey":"RT_ADT_A_PEK_SHA_2018-01-29|HU7603#U-PUBLIC,RT_ADT_A_SHA_PEK_2018-01-31|MU564#R-PUBLIC","passengerInfos":[{"costAttachId":"5660","costAttachName":"测试企业","email":"","id":"294274","insuancePrice":"20.0","insuraceId":"240","isSaleInsuance":"0","passengerType":"1","project":"","userId":"110101198711094550","userIdType":"1","userIdTypeStr":"","userName":"管理审核员（测试）","userPhone":"","userType":"1"},{"costAttachId":"5660","costAttachName":"测试企业","email":"","id":"302980","insuancePrice":"20.0","insuraceId":"240","isSaleInsuance":"0","passengerType":"1","project":"","userId":"110101198001010117","userIdType":"1","userIdTypeStr":"","userName":"测试锋","userPhone":"","userType":"1"}],"payMethod":"预付款支付","payMethodId":4,"skipType":1,"source":3,"timestamp":0,"tongzhirens":[],"traveType":1}
		    }
		    String excessinfo=this.getReq().getParameter("excessinfo");
		    String selectusers=this.getReq().getParameter("selectusers");
			

		    String quXuanzeReason=this.getReq().getParameter("quXuanzeReason");
		    String quTianxieReason=this.getReq().getParameter("quTianxieReason");
		    
		    String fanXuanzeReason=this.getReq().getParameter("fanXuanzeReason");
            String fanTianxieReason=this.getReq().getParameter("fanTianxieReason");
		    
		    BookJson bookVo=new BookJson();
			if(!isUserLogin()){
				bookVo.setMapKey(mapKey);
				bookVo.setType(type);
				bookVo.setTimestamp(timestamp);
				bookVo.setExcessinfo(excessinfo);
				bookVo.setSelectusers(selectusers);
				String json=JsonUtil.ObjectToJsonStr(bookVo);
				BaseAction4Wechat.SaveRedirectParams(req.getSession(), "/flight/toBookTicket.act", false, json);
				return "redirect:/weAvoidLogin/login.act";
			}
			if((mapKey==null||"".equals(mapKey))&&(bf==null||"".equals(bf))){
				WechatBase wb = BaseAction4Wechat.GetWechatBaseInfo(req.getSession());
				if(wb==null||wb.getRedirectParamStr()==null||"".equals(wb.getRedirectParamStr())){
					String errorStr="";
					if(getUserId()!=null){
						errorStr="数据已丢失,请返回重新点击!";
					}else{
						errorStr="登录已超时,请重新登录!";
					}
					attr.addFlashAttribute("result",errorStr);
					return "redirect:/error/returnError.act";
				}else{
				    bookVo=JsonUtil.JsonToObject(BookJson.class, wb.getRedirectParamStr());
				    mapKey=bookVo.getMapKey();
					type=bookVo.getType();
					timestamp=bookVo.getTimestamp();
					excessinfo=bookVo.getExcessinfo();
		            selectusers=bookVo.getSelectusers();
				}

			}else if(bf!=null&&!"".equals(bf)){
				BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
				type=as.getTraveType();
			}
			
			
			
			
			BookOrderService bos = ApplicationContextHelper.getInstance().getBean(BookOrderService.class);
			Long userId=getUserId();
			TAcOrg tacorg=bos.getTAcOrgByUserId(userId);
			
			
			
	        
	        
			
			 //针对企业保险查询  hanzhicheng
			/*-----------start---------*/
			TAcUser tacu = comDao.queryForEntity(userId, TAcUser.class);
			TAcOrg taco = comDao.queryForEntity(tacu.getOrgid(), TAcOrg.class);   
			Response_PriceAndDescription baoxian=new Response_PriceAndDescription();
			String sql="select oi.id,"
	            +" oi.user_id,"
	            +" oi.name,"
	            +" oi.operating_date,"
	            +" oi.price,"
	            +" oi.insurance_id,"
	            +" oi.orgid,"
	            +" oi.orgname,"
	            +" ai.floor_price,"
	            +" ai.note,"
	            +" ai.max_count"
	            +" from t_ac_org_insurance oi,T_AC_INSURANCE ai where oi.insurance_id= ai.id"
				+" and	oi.orgid = "+taco.getCompany();
			TAcInsurance tAcInsurance=null;
			List<?> list = comDao.queryBySQL(sql, null);
			List<TAcInsurance> result = new ArrayList<TAcInsurance>();
			for (int i = 0; i < list.size(); i++) {
				tAcInsurance=new TAcInsurance();
				Object[] obj = (Object[])list.get(i); 
				tAcInsurance.setId(Integer.valueOf(obj[5].toString()));
				tAcInsurance.setPrice(Double.valueOf(obj[4].toString()));
				tAcInsurance.setFloorPrice(Double.valueOf(obj[8].toString()));
				tAcInsurance.setNote(obj[9].toString());
				tAcInsurance.setMaxCount(Integer.valueOf(obj[10].toString()));
				
				result.add(tAcInsurance);
			}
			//查询总公司
			String sql1="select ORGID from t_ac_org tt where tt.orgtype  = '2' start with orgid="+tacu.getOrgid()+" connect by prior tt.parentid = tt.orgid ";
			List orglist =  comDao.queryBySQL(sql1, null);
			if(orglist==null || orglist.size()==0){
				attr.addFlashAttribute("result","当前账号不能预订!");
				return "redirect:/error/returnError.act";
			}
			Long aOrgid=0l;
			if(orglist.size()>0){
				aOrgid = Long.valueOf(orglist.get(0).toString());
			}
			TAcOrg org=comDao.queryForEntity(aOrgid, TAcOrg.class);
			//费用归属状态 0 不显示，1 选填，2 必填（1和2都显示）
			String control="";
			if(StringUtil.isNotEmpty(org.getCostbelongmust())){
				if("1".equals(org.getCostbelongmust())){
					control="2"; 
				}else if("0".equals(org.getCostbelongmust())){
					control="1";
				}else{
					control="0"; 
				}
			}else{
				control="1";
			}
			
	      //查询保险状态 不显示 必填 选填	hanzhicheng****管理员状态
			/*======start---------*/
	        String isShowInsruance="1";
	        String isShowAdmin="1";
	        
	    	/*TAcUser tacu = comDao.queryForEntity(userId, TAcUser.class);
			TAcOrg taco = comDao.queryForEntity(tacu.getOrgid(), TAcOrg.class); */  
			String sqll="select SWITCHTYPE,STATE from ORG_SWITCHS where ORGID="+taco.getCompany()+" and BUSINESSTYPE='1'";
			@SuppressWarnings("unchecked")
			List<Object[]> orgsWitchs = (List<Object[]>) comDao.queryBySQL(sqll, null);
	
	        if(orgsWitchs!=null && orgsWitchs.size()>0){//0:SWITCHTYPE(开关类型，1-保险|2-管理员)   1:STATE(状态，0-不显示|1-选填|2-必填，默认值为1)
	        	for (Object[] orgsWitch : orgsWitchs) {
	        		if(orgsWitch[1]!=null){
						if("1".equals(orgsWitch[0].toString())){//1-保险
							isShowInsruance=orgsWitch[1].toString();
						}else if("2".equals(orgsWitch[0].toString())){//2-管理员
							isShowAdmin=orgsWitch[1].toString();
						}
	        		}
				}
	        }
	       
	        String insuraceId=null;
	        if(result!=null&&result.size()>0){
	        	
	        	insuraceId=result.get(0).getId().toString();
	        }
	       
	        addAttr("InsruanceIsNull",insuraceId!=null?insuraceId:"-1");
	        addAttr("isShowInsruance",isShowInsruance);
	        addAttr("isShowAdmin",isShowAdmin);
	        addAttr("control",control);
	    	/*------------end-------------*/
			
	        
	        /*----管理员列表查询---*/
	        
	        
	        
	        
			/*结算方式，拷贝邓飞代码*/
			Request_GetPayMethodList gpml = new Request_GetPayMethodList();
			gpml.setUserId(userId);
			gpml.setBusinessType(type.toString());
			GetPayMethodList pml = new GetPayMethodList();
			OutputResult<Response_GetPayMethodList, String> payMethod = pml.ClientRequest(gpml, Response_GetPayMethodList.class);
			if(payMethod.getResultObj().getData()==null||payMethod.getResultObj().getData().size()==0){
				attr.addFlashAttribute("result","请维护结算方式");
				return "redirect:/error/returnError.act";
			}
			addAttr("payMethod",payMethod.getResultObj());
			BookOrderInfo bookinfo=null;
			//如果选择了乘客，则直接赋值。
			if(StringUtils.isNotEmpty(selectusers)){
			    String json="";
			    if(mapKey!=null && !"".equals(mapKey)){
		            bookinfo=new BookOrderInfo();
		            bookinfo.setSource(3);
		            bookinfo.setChildBabySuggestBook(0);
		            bookinfo.setDijiaReason("起飞或达到时间不合适");
		            bookinfo.setMapKey(mapKey);
		            bookinfo.setPayMethodId(payMethod.getResultObj().getData().get(0).getId());
		            bookinfo.setPayMethod(payMethod.getResultObj().getData().get(0).getName());
		            bookinfo.setTraveType(type);
		            bookinfo.setTimestamp(timestamp);
		            bookinfo.setSkipType(new Integer(1));
		            if(bookinfo!=null&&bookinfo.getMapKey()!=null&&!"".equals(bookinfo.getMapKey())){
//		                bookinfo.setJumpCount((bookinfo.getJumpCount()==null?1:bookinfo.getJumpCount())+1);
	                    json=JsonUtil.ObjectToJsonStr(bookinfo);
	                }
		        }
			    String[] splits=selectusers.split("\\|");
			    UserInfo userinfo2=null;
			    for (int i = 0; i < splits.length; i++) {
			        userinfo2=new UserInfo();
		            String names=splits[i];
		            userinfo2.id=names.split("_")[1];
		            userinfo2.userName=names.split("_")[4];
		            userinfo2.userId=names.split("_")[0];
		            userinfo2.userType="1";
		            userinfo2.passengerType="1";
		            userinfo2.userIdType=String.valueOf(IdType.二代身份证.getValue());
		            userinfo2.userIdTypeStr=IdType.二代身份证.toString();
		            bookinfo=bookOrderService.getBookOrderInfo(type, timestamp, json, userinfo2,userId, payMethod, result);
		            json=JsonUtil.ObjectToJsonStr(bookinfo);
			    }
			    if(bookinfo!=null&&bookinfo.getMapKey()!=null&&!"".equals(bookinfo.getMapKey())){
//                  bookinfo.setJumpCount((bookinfo.getJumpCount()==null?1:bookinfo.getJumpCount())+1);
                    bf=JsonUtil.ObjectToJsonStr(bookinfo);
                }
			}else{ 
			     bookinfo=bookOrderService.getBookOrderInfo(mapKey, type, timestamp, bf, userinfo,userId, payMethod, result);
			     bookinfo.setJumpCount(bookinfo.getJumpCount()==null?1:(bookinfo.getJumpCount()+1));
			}
			mapKey=bookinfo.getMapKey();
			type=bookinfo.getTraveType();
			
			String[] mapKays=mapKey.split(",");
			DispplayTrip quTrip=rimservice.getIBEDataForBook(mapKays[0],userId);
			DispplayTrip fanTrip=null;
			bookinfo.setFlightType(1);
//			 public String quXuanzeReason;
//			    public String quTianxieReason;
//			    public String fanXuanzeReason;
//			    public String fanTianxieReason;
			if(StringUtils.isNotEmpty(quXuanzeReason)){
			    bookinfo.setQuXuanzeReason(quXuanzeReason);
			}
			if(StringUtils.isNotEmpty(quTianxieReason)){
                bookinfo.setQuTianxieReason(quTianxieReason);
            }
			if(StringUtils.isNotEmpty(fanXuanzeReason)){
                bookinfo.setFanXuanzeReason(fanXuanzeReason);
            }
			if(StringUtils.isNotEmpty(fanTianxieReason)){
                bookinfo.setFanTianxieReason(fanTianxieReason);
            }
			Double totalTicketPrice =quTrip.getPrice()+quTrip.getTaxfee()+quTrip.getFueltax();
			if(mapKays.length>1){
			    fanTrip=rimservice.getIBEDataForBook(mapKays[1],userId);
			    addAttr("fanTrip",fanTrip);
			    addAttr("fanTripWeek",DateUtil.getWeek(fanTrip.getStartdate()));
			    bookinfo.setFlightType(2);
			    if("UN".equals(mapKays[0].substring(0, 2))){
			        bookinfo.setFlightType(3);
			    }
			    totalTicketPrice=totalTicketPrice+fanTrip.getPrice()+fanTrip.getTaxfee()+fanTrip.getFueltax();
			}
			
			List<TEndorse> endorse=CommUtil.getEndorse(quTrip.getAirline().substring(0, 2),DateUtil.date2Str(quTrip.getStartdate(), "yyyy-MM-dd HH:mm"),"yyyy-MM-dd HH:mm");
			if(endorse!=null){
    			for (TEndorse tEndorse : endorse) {
    			    if(quTrip.getCangwei().equals(tEndorse.getCangwei())){
    			        addAttr("quEndorse",tEndorse);
    			    }
    			    if(mapKays.length>1){
    			        if(fanTrip.getCangwei().equals(tEndorse.getCangwei())){
    			            addAttr("fanEndorse",tEndorse);
    			        }
    			    }
    			}
			}
			String trip=""; 
			String reTrip = "";
			
			//处理差旅规则信息
			if(StringUtils.isEmpty(excessinfo) && StringUtil.isNotEmpty(bookinfo.getExcessinfo())){
			    excessinfo=bookinfo.getExcessinfo();
			}
			if(StringUtils.isNotEmpty(excessinfo)){
			    String[] split = excessinfo.split(",");
                String qucheng=split[0];
			    trip = getTripInfo(trip, qucheng);
			    if(split.length>1){
			        String huicheng=split[1];
			        reTrip = getTripInfo(reTrip, huicheng);
			    }
			    bookinfo.setExcessinfo(excessinfo);
			}
			
//			System.out.println(trip);
//			System.out.println(reTrip);
//			
//			addAttr("trip",trip);
//			addAttr("reTrip",reTrip);
			if(StringUtil.isNotEmpty(trip)){
			    bookinfo.setTripexcessinfo(trip);
			}
			if(StringUtil.isNotEmpty(reTrip)){
			    bookinfo.setRetripexcessinfo(reTrip);
			}
			
			addAttr("bf",bookinfo);
			
			addAttr("totalTicketPrice",totalTicketPrice);
			addAttr("quTrip",quTrip);
			addAttr("quTripWeek",DateUtil.getWeek(quTrip.getStartdate()));
			
			List<ListEmployee> ListEmployees=bookOrderService.getListEmployees(tacorg.getOrgid());
			addAttr("ListEmployees",ListEmployees);
			
			
			addAttr("timestamp",timestamp);
			addAttr("mapKey",mapKey);
			//addAttr("userId",userId);
			String verify = tacorg.getVerify();
            addAttr("verify",verify);
			addAttr("nolowpriceReason",tacorg.getNolowpricereason());
			addAttr("traveType",type);
			addAttr("excessinfo",excessinfo);
			addAttr("selectusers",selectusers);
			//跳转到订单信息填写页面前 做自动出票相关信息判断
			String autoissueConfirm="NO_CONFIRM";// 默认不需要确认
			if(verify.equals("0")){
//			    List<String> airlines=new ArrayList<String>();
//			    if(quTrip!=null && StringUtils.isNotEmpty(quTrip.getAirline())){
//			        if(!airlines.contains(quTrip.getAirline().substring(0,2))){
//			            airlines.add(quTrip.getAirline().substring(0,2));
//			        }
//			    }
//			    //autoissueBeforeValidate( airlines, companyName, payMethod, onePNR, allAdult)
//			    boolean onePNR=true;
//			    if(airlines.size()>0){
//			        onePNR=false;
//			    }
//			    boolean allAdult=true;
//			    List<UserInfo>  userInfoList=bookinfo.getPassengerInfos();
//			    for (int i = 0; i < userInfoList.size(); i++) {
//			        UserInfo usrinfo=userInfoList.get(i);
//			        if(usrinfo.getPassengerType().equals("2")){
//			            allAdult=false;
//			        }
//                }
//			    autoTicketService.autoissueBeforeValidate(airlines,tacorg.getOrgname(),payMethod,onPNR,) ;
			}
			addAttr("autoissueConfirm",autoissueConfirm);
			return toBookTicket;
		} catch (Exception e) {
			e.printStackTrace();
			attr.addFlashAttribute("result",MyEnum.BookErrorTypeCode.getBookErrorStr(MyEnum.BookErrorTypeCode.参数不全.getValue()));
			return "redirect:/error/returnError.act";
		}
	}

    public String getTripInfo(String trip, String qucheng) {
        ArrayList<String> quchenglist=this.getResson(qucheng);
        for(String lala : quchenglist){
            trip += "【"+lala.split("_")[2].toString()+"】"+ MyEnum.ExcessinfoString.getName(Integer.parseInt(lala.split("_")[0].toString())) ;
        }
        return trip;
    }
	

	//查询费用归属部门的管理员
	@RequestMapping("/admin.act")
	@ResponseBody
	public void getAdminInfo(String orgId){
        StringBuilder adminInfo = new StringBuilder();
        List list = new ArrayList();
        String sql = "select u.username, u.userid from t_ac_org o, t_ac_user u, t_ac_userrole ur"
                + " where u.orgid = o.orgid and o.orgid = "+orgId+" and ur.userid = u.userid" + " and ur.roleid in (55, 65)";
        List l = comDao.queryBySQL(sql, null);
        String adminInfos="";
        if (l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                Object[] obj = (Object[]) l.get(i);
                adminInfo.append(obj[0] == null ? "" : obj[0].toString());
                adminInfo.append("^");
                adminInfo.append(obj[1] == null ? "" : obj[1].toString());
                adminInfo.append(",");
                adminInfos=adminInfo.toString().substring(0, adminInfo.toString().length() - 1);
            }
        }else{
        	adminInfos="";
        }
        try {
            //ServletActionContext.getResponse().getWriter().print(adminInfos);
        	PrintWriter pw;
        	pw = resp.getWriter();
			pw.write(adminInfos);
			pw.flush();
			pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	@RequestMapping("/deleteFlush.act")
	public String deleteFlush(BookOrderInfo bf,RedirectAttributes attr) throws Exception{
		String json=JsonUtil.ObjectToJsonStr(bf);
		
		return this.toBookTicket(null,null,null,json,null,attr);
		
		//return toBookTicket;
	}
	
	@RequestMapping("/toPay.act")
	public String toPay(Long orderId,RedirectAttributes attr) throws Exception{
		ToPay bt = new ToPay();
		Request_ToPay para=new Request_ToPay();
		para.setOrderID(orderId);
		OutputResult<Response_ToPay, String> result = bt.ClientRequest(para, Response_ToPay.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			obj = success(result.getResultObj());
		}else{
			obj = fail(result.result);
			attr.addFlashAttribute("result",result.result);
			return "redirect:/error/returnError.act";
		}
		if(result.getResultObj().data.orderId<0){
			attr.addFlashAttribute("result",MyEnum.BookErrorTypeCode.getBookErrorStr(result.getResultObj().data.orderId));
			return "redirect:/error/returnError.act";
		}
		addAttr("result",obj);
	    
		TOrder torder=orderService.getOrderById(orderId);
		 
		String countDownTimeStr=DateUtil.getCountDownTimeStr(torder.getSubscribeDate(),900);
		
		addAttr("countDownTimeStr", countDownTimeStr);
		
		addAttr("orderId", Long.valueOf(result.getResultObj().data.orderId));
		addAttr("isWechat",getPlatform()== MyEnum.UserPlatform.微信?1:0);
		return "/public/pay";
	}
	/**
     * 验证乘机人、出发时间、起飞地点 和数据库是否用一致的， 如果有说明此乘机人预订了重复的机票，给他一个提示 
     * @return
     * @throws IOException 
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/passengercheck.act")
	@ResponseBody
    public String passengercheck(String passengeridStr,String mapKeys) throws IOException{
		String[] ms=mapKeys.split(",");
		String[] passengerids=passengeridStr.split(",");
		 DispplayTrip dispplayTrip=new DispplayTrip();
		 Boolean flag=false;
		 String pid=null;
		 if(getUserId()!=null){
			l1: for (int j = 0; j < passengerids.length; j++) {
				 if(!flag){
					 List tts=bookOrderService.getBookFlights(passengerids[j]);
							 for (int i = 0; i < ms.length; i++) {
								 if(!flag){
									 dispplayTrip=rimservice.getIBEDataForBook(ms[i],getUserId());
								 for (Object[] tt : (List<Object[]>)tts) {
									if(tt[0].toString().equals
											(DateUtil.date2Str(dispplayTrip.getStartdate(), "YYYY-MM-dd"))
											&& tt[1].toString().equals(dispplayTrip.getAirline())
											&&tt[2].toString().equals(dispplayTrip.getCangwei())){
										flag=true;
										pid=passengerids[j];
										break l1;
									}
							 } 
						 }
					 }
				 }
			 }
		 }
        return pid;
        
    }
}
