package com.viptrip.hotelHtml5.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nethsoft.zhxq.core.util.StringUtil;
import com.viptrip.base.action.BaseAction;
import com.viptrip.common.model.Request_GetApprovalDetail;
import com.viptrip.common.service.PolicyService;
import com.viptrip.hotel.model.Request_GetBookRight;
import com.viptrip.hotel.model.Request_GetDepartmentsLikeName;
import com.viptrip.hotel.model.Request_GetOrgFramework;
import com.viptrip.hotel.model.Request_HotelServiceFee;
import com.viptrip.hotel.model.Request_ListPassenger;
import com.viptrip.hotel.model.Request_ListPassengerLikeName;
import com.viptrip.hotel.model.Response_HotelServiceFee;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.Response_ListPassengerLikeName;
import com.viptrip.hotel.model.fee.Param4HotelServiceFee;
import com.viptrip.hotel.model.getDepartmentsLikeName.CompanyInfoGDLN;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.staff.StaffGroup;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.GetDepartmentsLikeNameService;
import com.viptrip.hotel.service.GetOrgFrameworkService;
import com.viptrip.hotel.service.GetOverReason4HotelService;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotelHtml5.common.DataTypeUtil;
import com.viptrip.hotelHtml5.service.TmcHotelOrderService;
import com.viptrip.hotelHtml5.util.DateUtil;
import com.viptrip.hotelHtml5.vo.request.Request_SaveTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.HotelBookingVo;
import com.viptrip.hotelHtml5.vo.tmc.OrderDetailRequestVo;
import com.viptrip.hotelHtml5.vo.tmc.ParameterResponseVO;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderGuest;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListApprovePolicyInfo;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListApprovePolicyInfo;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListApprovePolicyInfo.ApprovalInfo;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder.OrderResponse;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;

import etuf.v1_0.common.Constant;

@Controller
@RequestMapping("neworder")
public class NewOrderWriteAction extends BaseAction{
	
	private static final Logger log = LoggerFactory.getLogger(NewOrderWriteAction.class);
	
	/**
	 * 第一个页面
	 */
	private static final String NEW_ORDER_PRE = "hotelHtml5/orderInfo/newOrderWrite"; 
	private static final String ADD_CUSTOMER_INFO = "hotelHtml5/orderInfo/add_customer_info"; 
	/**
	 * 因公页面
	 */
	private static final String NEW_ORDER_PUBLIC = "hotelHtml5/orderInfo/newOrderPublic"; 
	/**
	 * 因私页面
	 */
	private static final String NEW_ORDER_PRIVATE = "hotelHtml5/orderInfo/newOrderPrivate"; 
	
	private static final String APPROVEPOLICYLIST = "hotelHtml5/orderInfo/approvePolicyList"; 
	private static final String APPROVEPOLICYDETAIL = "hotelHtml5/orderInfo/approvePolicyDetail"; 
	private static final String COMPANY_APT = "hotelHtml5/orderInfo/company_apt"; 
	private static final String ORDEREXCEEDEDREMIND = "hotelHtml5/orderInfo/orderExceededRemind"; 
	private static final String ORDER_SUCCESS = "hotelHtml5/orderInfo/orderSueecss"; 
	
	@Autowired
	private TmcHotelOrderService tmcHotelOrderService;
	@Autowired
	private IComService iComService; 
	@Autowired
	private GetOrgFrameworkService getOrgFrameworkService; 
	@Autowired
	private GetOverReason4HotelService getOverReason4HotelService; 
	@Autowired
	private GetDepartmentsLikeNameService getDepartmentsLikeNameService; 
	@Autowired
	private PolicyService policyService; 
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 跳转到新单填写页面,前半部分
	 * @return
	 * @author zhaojian
	 * @date 2017年11月9日 下午5:05:53 
	 */
	@RequestMapping("toNewOrderWriteBefore")
	public String toNewOrderWriteBefore(Request_GetHotelBooking request){
		String pagePath = NEW_ORDER_PRE;
		HotelBookingVo hotelBooking = new HotelBookingVo();
		Response_GetHotelBooking response = tmcHotelOrderService.getHotelBooking(request);
		if(response != null && response.status == Constant.Code_Succeed && response.getHotelBooking() != null){
			hotelBooking = response.getHotelBooking();
			hotelBooking.setCheckInDate(DateUtil.parseDate(hotelBooking.getCheckInDateStr()));
			hotelBooking.setCheckOutDate(DateUtil.parseDate(hotelBooking.getCheckOutDateStr()));
		}
		
		boolean isShowGuest = false;	//是否展示默认预订人
		try {
			Integer uId = Integer.valueOf(hotelBooking.getBookingUserId());
			Integer businessType = 1; 	//1、因公；2、因私
			if("0000700002".equals(hotelBooking.getTripType())){
				businessType = 2;
			}
			Page page = new Page();
			page.index = 1;
			page.size = 100;	
			List<StaffGroup> staffList = new ArrayList<StaffGroup>();
			StaffList staffListResponse = iComService.BokeListStaff(uId, page, businessType, "");
			if(staffListResponse != null && !CollectionUtils.isEmpty(staffListResponse.list)){
				staffList = staffListResponse.list;
				for(StaffGroup sg : staffList){
					if(sg != null && sg.getId() != null && hotelBooking.getBookingUserId().equals(sg.getId().toString())){
						isShowGuest =  true;
						break;
					}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttr("isShowGuest", isShowGuest);
		addAttr("hotelBooking", hotelBooking);
		addAttr("hotelBookingRequest", JSON.toJSONString(request));
		return pagePath;
	}
	/**
	 * 由后半部分页面返回前半部分页面
	 * @param hotelBookingRequest
	 * @param orderDetail
	 * @return
	 * @author zhaojian
	 * @date 2017年11月10日 上午11:10:13 
	 */
	@RequestMapping("backStepOne")
	public String backStepOne(String hotelBookingRequest, OrderDetailRequestVo orderDetail){
		String pagePath = NEW_ORDER_PRE;
		Request_GetHotelBooking request = JSON.parseObject(hotelBookingRequest, Request_GetHotelBooking.class);
		HotelBookingVo hotelBooking = new HotelBookingVo();
		Response_GetHotelBooking response = tmcHotelOrderService.getHotelBooking(request);
		if(response != null && response.status == Constant.Code_Succeed && response.getHotelBooking() != null){
			hotelBooking = response.getHotelBooking();
			hotelBooking.setCheckInDate(DateUtil.parseDate(hotelBooking.getCheckInDateStr()));
			hotelBooking.setCheckOutDate(DateUtil.parseDate(hotelBooking.getCheckOutDateStr()));
		}
		addAttr("hotelBooking", hotelBooking);
		addAttr("orderDetail", orderDetail);
		addAttr("hotelBookingRequest", hotelBookingRequest);
		log.info("页面回退==》" + pagePath);
		return pagePath;
	}
	
	/**
	 * 前半部分页面的校验（入住人、联系人等）
	 * @param orderDetail
	 * @return
	 */
	@RequestMapping("validateStepOne")
	@ResponseBody
	public Map<String, String> validateStepOne(OrderDetailRequestVo orderDetail){
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "1000");
		try {
			result = this.tmcHotelOrderService.validateStepOne(orderDetail);
		} catch (Exception e) {
			result.put("code", "1001");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 添加客人信息
	 * @param userId
	 * @param key
	 * @param tripType
	 * @return
	 * @author zhaojian
	 * @date 2017年11月14日 下午2:24:04 
	 */
	@RequestMapping("toCustomerInfo")
	public String toCustomerInfo(String userId, String key, String tripType, String userType, String bookingEnterpId){
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(tripType)){
			return ADD_CUSTOMER_INFO;
		}
		try {
			Integer uId = Integer.valueOf(userId);
			Integer businessType = 1; 	//1、因公；2、因私
			if("0000700002".equals(tripType)){
				businessType = 2;
			}
			Page page = new Page();
			page.index = 1;
			page.size = 30;		//默认前三十条
			
			Request_GetBookRight bookRightRequest = new Request_GetBookRight();
			bookRightRequest.businessType = businessType;
			bookRightRequest.userId = uId;
			Integer bookRight = iComService.GetBookRight(bookRightRequest);
			
			//可预订员工
			if(key == null){
				key = "";
			}
			key = key.trim();
			List<StaffGroup> staffList = new ArrayList<StaffGroup>();
			List<Req_Passenger> passengerList = new ArrayList<Req_Passenger>();
			if(userType == null){
				
			}else if("guest".equals(userType)){
				StaffList staffListResponse = iComService.BokeListStaff(uId, page, businessType, key);
				if(staffListResponse != null && !CollectionUtils.isEmpty(staffListResponse.list)){
					staffList = staffListResponse.list;
				}
			}else if("contact".equals(userType)){	//联系人
				StaffList staffListResp = iComService.ListStaff(Integer.valueOf(bookingEnterpId), page, "likeName", key);
				if(staffListResp != null && !CollectionUtils.isEmpty(staffListResp.list)){
					staffList = staffListResp.list;
				}
			}
			//常旅客
			if(StringUtils.isEmpty(key)){
				Request_ListPassenger request = new Request_ListPassenger();
				request.setPage(page);
				request.userId = uId;
				Response_ListPassenger pgResponse = iComService.ListPassenger(request);
				if(pgResponse.data != null && pgResponse.data.getList().size() > 0){
					passengerList = pgResponse.data.getList();
				}
			}else{
				Request_ListPassengerLikeName request = new Request_ListPassengerLikeName();
				request.nameKey = key;
				request.page = page;
				request.userId = uId;
				Response_ListPassengerLikeName pgResponse = iComService.ListPassengerLikeName(request);
				if(pgResponse.data != null && pgResponse.data.getList().size() > 0){
					passengerList = pgResponse.data.getList();
				}
			}
			if((bookRight == 1 || bookRight == 2) && "guest".equals(userType)){	//自己/企业员工
				passengerList = new ArrayList<Req_Passenger>();
			}
			addAttr("staffList", staffList);
			addAttr("passengerList", passengerList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			addAttr("staffList", new ArrayList<StaffGroup>());
			addAttr("passengerList", new ArrayList<Req_Passenger>());
			e.printStackTrace();
		}
		return ADD_CUSTOMER_INFO;
	}
	
	/**
	 * 跳转到新单填写页面,后半部分
	 * @param hotelBookingRequest
	 * @param orderDetail
	 * @return
	 * @author zhaojian
	 * @date 2017年11月10日 上午11:10:13 
	 */
	@RequestMapping("toNewOrderWriteAfter")
	public String toNewOrderWriteAfter(String hotelBookingRequest, OrderDetailRequestVo orderDetail){
		String pagePath = "";
		Request_GetHotelBooking request = JSON.parseObject(hotelBookingRequest, Request_GetHotelBooking.class);
		String tripType = request.getTripType();
		if("0000700001".equals(tripType)){
			pagePath = NEW_ORDER_PUBLIC;
		}else if("0000700002".equals(tripType)){
			pagePath = NEW_ORDER_PRIVATE;
		}
		HotelBookingVo hotelBooking = new HotelBookingVo();
		Response_GetHotelBooking response = tmcHotelOrderService.getHotelBooking(request);
		if(response != null && response.status == Constant.Code_Succeed && response.getHotelBooking() != null){
			hotelBooking = response.getHotelBooking();
			hotelBooking.setCheckInDate(DateUtil.parseDate(hotelBooking.getCheckInDateStr()));
			hotelBooking.setCheckOutDate(DateUtil.parseDate(hotelBooking.getCheckOutDateStr()));
		}
		//员工所属部门
		Set<String> costDeptSets = new HashSet<String>();
		List<ParameterResponseVO> costDeptList = new ArrayList<ParameterResponseVO>();
		if(orderDetail != null && !CollectionUtils.isEmpty(orderDetail.getTmcOrderGuestList())){
			for(TmcOrderGuest g : orderDetail.getTmcOrderGuestList()){
				if(g != null && !StringUtil.isBlank(g.getDeptId()) && !g.getDeptId().equals("undefined")){
					ParameterResponseVO vo = new ParameterResponseVO();
					vo.setV(g.getDeptId());
					vo.setN(g.getDeptName());
					if(costDeptSets.add(g.getDeptId())){
						costDeptList.add(vo);
					}
				}
			}
		}
		addAttr("hotelBooking", hotelBooking);
		addAttr("orderDetail", orderDetail);
		addAttr("costDepts", costDeptList);
		addAttr("hotelBookingRequest", hotelBookingRequest);
		log.info("页面跳转==》" + pagePath);
		return pagePath;
	}
	
	/**
	 * 
	 * 1002 CaissaException
	 * 1003 Exception
	 * 1001 酒店变价
	 * 9999 酒店无房
	 * -1       酒店下单失败
	 * 1011 差旅政策不匹配，提示是否继续预订
	 * 1012 差旅政策不匹配，不可预订
	 * 1013 差旅政策不匹配，选择超规原因
	 * @param orderDetail
	 * @return
	 * @author zhaojian
	 * @date 2017年11月9日 下午5:30:06 
	 */
	@RequestMapping("saveOrder")
	@ResponseBody
	public Response_SaveTmcOrder saveOrder(OrderDetailRequestVo orderDetail) {
		Response_SaveTmcOrder response = new Response_SaveTmcOrder();
		try {
			Request_SaveTmcOrder request = new Request_SaveTmcOrder();
			request.setOrderDetailRequestVo(orderDetail);
			response = tmcHotelOrderService.saveTmcOrder(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setHotelMsg(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 获取酒店服务费和预订费
	 * @param userId
	 * @param choiceAgreementHotelFlag
	 * @param amount
	 * @param nightCount
	 * @param roomCount
	 * @return
	 * @author zhaojian
	 * @date 2017年11月16日 下午3:02:28 
	 */
	@RequestMapping("hotelServiceFee")
	@ResponseBody
	public Map<String, Double> hotelServiceFee(Integer userId, String choiceAgreementHotelFlag, double amount, int nightCount, int roomCount){
		Map<String, Double> map = new HashMap<String, Double>();
		double bookingFee = 0.0;
		double nightFee = 0.0;
		
		try {
			int nightRooms = nightCount * roomCount;
			Integer payType = 1;	//预付
			Integer protocolType = 1;
			if("0002400001".equals(choiceAgreementHotelFlag)){	//1、协议酒店；2、精选酒店
				protocolType = 2;
			}
			
			Request_HotelServiceFee request = new Request_HotelServiceFee();
			Param4HotelServiceFee requestFee = new Param4HotelServiceFee();
			request.userId = Integer.valueOf(userId);
			requestFee.nightRooms = nightRooms;
			requestFee.payType = payType;
			requestFee.protocolType = protocolType;
			requestFee.amount = amount;
			requestFee.time = DateUtil.formatTime2(DateUtil.getSysDate());
			request.data = requestFee;
			Response_HotelServiceFee response = iComService.countServiceFee(request);
			if(response != null && response.status == Constant.Code_Succeed){
				bookingFee = response.fee;
				nightFee = response.nightFee;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("bookingFee", bookingFee);
		map.put("nightFee", nightFee);
		return map;
	}
	
	/**
	 * 审批列表页面
	 * @param userIds
	 * @param deptIds
	 * @param bookingUserId
	 * @param isSuperRule
	 * @return
	 */
	@RequestMapping("toApprovePolicyDetail")
	public String toApprovePolicyDetail(String userIds, String deptIds, String bookingEnterpId,
			String bookingUserId, String hotelType, String areaId, Integer hotelStar, Double hotelRoomPrice){
		List<ApprovalInfo> list = new ArrayList<ApprovalInfo>();
		try {
			Request_ListApprovePolicyInfo request = new Request_ListApprovePolicyInfo();
			request.setBookingEnterpId(bookingEnterpId);
			request.setBookingUserId(bookingUserId);
			request.setHotelType(hotelType);
			request.setUserIds(userIds);
			request.setDeptIds(deptIds);
			request.setAreaId(areaId);
			request.setHotelStar(hotelStar);
			request.setHotelRoomPrice(hotelRoomPrice);
			Response_ListApprovePolicyInfo result = tmcHotelOrderService.listApprovePolicyInfo(request);
			if(result != null && !CollectionUtils.isEmpty(result.getData())){
				list = result.getData();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttr("approvalList", list);
		addAttr("plicyRuleListLength", list.size());
		return APPROVEPOLICYLIST;
	}
	
	/**
	 * 审批政策详情
	 * @param approvalId
	 * @return
	 */
	@RequestMapping("getPolicyApprovalDetail")
	public String getPolicyApprovalDetail(String approvalId){
		Request_GetApprovalDetail request = new Request_GetApprovalDetail();
		Res_ApprovalsDetail approvalsDetail = null;
		try {
			request.approvalId = Integer.valueOf(approvalId);
			approvalsDetail = policyService.getApprovalDetail(request);
		} catch (Exception e) {
			approvalsDetail = null;
			e.printStackTrace();
		}
		addAttr("approvalsDetail", approvalsDetail);
		return APPROVEPOLICYDETAIL;
	}
	
	/**
	 * 查询部门/公司
	 * @param userId
	 * @param enterpId
	 * @param namekey
	 * @param tripType
	 * @param idFlag	invoice/id="costDept_${cost_i.index }
	 * @return
	 */
	@RequestMapping("toCompanyAptDetail")
	public String toCompanyAptDetail(String userId, String enterpId, String namekey, String tripType, String idFlag){
		List<ParameterResponseVO> list = new ArrayList<ParameterResponseVO>();
		try {
			if(org.apache.commons.lang.StringUtils.isBlank(namekey)){
				CompanyInfo topTacOrg = null;
				List<CompanyInfo> child = null;
				Request_GetOrgFramework req = new Request_GetOrgFramework();
				req.type = 0;	//查询类别，凯撒商旅员工允许使用所有类别，普通企业员工只允许用0，此时orgId字段无意义。0-用户所在企业，1-指定企业，2-所有企业
				req.userId = Integer.valueOf(userId);
				req.orgId = Integer.valueOf(enterpId);
				CompanyInfo companyInfo = getOrgFrameworkService.getAllOrg(req, topTacOrg, child);
				list = this.tmcHotelOrderService.getOrgList(companyInfo, list, "");
			}else{
				Request_GetDepartmentsLikeName req = new Request_GetDepartmentsLikeName();
				req.userId = Integer.valueOf(userId);
				req.orgId = Integer.valueOf(enterpId);
				req.nameKey = namekey;
				Page page = new Page();
				page.index = 1;
				page.size = 20;	//默认20条
				req.page = page;
				CompanyInfoGDLN topTacOrg=null;
				List<CompanyInfoGDLN> childs=null;
				CompanyInfoGDLN result = getDepartmentsLikeNameService.getAllOrg(req.orgId, req, topTacOrg, childs);
				if(null != result && !CollectionUtils.isEmpty(result.list)){
					for(CompanyInfoGDLN og : result.list){
						ParameterResponseVO ccg = new ParameterResponseVO();
						ccg.setAttribute(og.name);
						ccg.setN(og.name);
						ccg.setV(og.id.toString());
						list.add(ccg);
					}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("invoice".equals(idFlag) && DataTypeUtil.TRIP_TYPE_2.getKey().equals(tripType)){
			//因私且开票信息
			ParameterResponseVO ccg = new ParameterResponseVO();
			ccg.setAttribute("个人");
			ccg.setN("个人");
			ccg.setV("");
			list.add(0, ccg);
		}
		addAttr("list", list);
		addAttr("idFlag", idFlag);
		return COMPANY_APT;
	}
	
	/**
	 * 超标理由
	 * @param userId
	 * @return
	 */
	@RequestMapping("toChangeRemind")
	public String toChangeRemind(String userId, String msg){
		List<String> list = new ArrayList<String>();
		try {
			list = getOverReason4HotelService.getOverReason4HotelList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttr("list", list);
		addAttr("msg", msg);
		return ORDEREXCEEDEDREMIND;
	}
	
	/**
	 * 下单成功之后的页面
	 * @param orderNo
	 * @param orderState
	 * @param orderTotalAmount
	 * @param serviceFee
	 * @return
	 */
	@RequestMapping("toSuccess")
	public String toSuccess(OrderResponse request){
		if(request != null){
			try {
				String orderStatueName = request.getOrderStatueName();
				orderStatueName = URLDecoder.decode(orderStatueName, "UTF-8");
				request.setOrderStatueName(orderStatueName);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		addAttr("orderResponse", request);
		return ORDER_SUCCESS;
	}
	
}
