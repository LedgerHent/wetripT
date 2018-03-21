package com.viptrip.hotelHtml5.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viptrip.base.action.BaseAction;
import com.viptrip.common.model.Request_ListApprovalPending;
import com.viptrip.common.model.Response_ListApprovalPending;
import com.viptrip.common.model.TTmcApproveProcessInfo;
import com.viptrip.common.service.TTmcApproveProcessService;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotelHtml5.service.TmcHotelOrderService;
import com.viptrip.hotelHtml5.util.PageUtil;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderInfoH5;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListTmcOrder;

 /** 
  * 订单信息
 * @ClassName: HotelOrderInfoAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 
 * @date 2017年11月17日 上午11:13:27 
 *
 */
@RequestMapping("orderInfo")
@Controller
public class HotelOrderInfoAction extends BaseAction {

	@Autowired
	private TmcHotelOrderService tmcHotelOrderService;
	@Autowired
	private TTmcApproveProcessService tTmcApproveProcessService;
	
	/**
	 * 全部订单初始化页面
	 * @param userId
	 * @return
	 */
	@RequestMapping("listAllOrderInit")
	public String listInit(String userId, String orderState){
		Long id = getUserId();
		if(id != null){
			userId = id.toString();
		}
		addAttr("userId", userId);
		addAttr("orderState", orderState);
		addAttr("searchType", 1);
		return "hotelHtml5/orderInfo/tmc_orderInit";
	}

	/**
	 * @param userId
	 * @return
	 * @author zhaojian
	 * @date 2017年11月17日 上午11:30:21 
	 */
	@RequestMapping("listOrderInfo")
	public String listOrderInfo(String userId, String searchType, String orderState, @RequestParam(defaultValue = " 10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageNum){
		Request_ListTmcOrder request = new Request_ListTmcOrder();
		PageUtil<TmcOrderInfoH5> page = new PageUtil<TmcOrderInfoH5>();
		request.setPageNum(pageNum);
		request.setPageSize(pageSize);
		request.setBookingUserId(userId);
		request.setSearchType(Integer.valueOf(searchType));
		request.setOrderState(orderState);
		Response_ListTmcOrder response = tmcHotelOrderService.listTmcOrder(request);
		if(response != null && response.getPageInfo() != null){
			page = response.getPageInfo();
		}
		addAttr("page", page);
		return "hotelHtml5/orderInfo/tmc_orderList";
	}

	/**
	 * 全部订单初始化页面
	 * @param userId
	 * @param apState  0:未审核订单（我的待批）; 2：审核过的订单（我的已批）
	 * @return
	 */
	@RequestMapping("listApprovalPendingOrderInit")
	public String listApprovalPendingOrderInit(String userId, String apState){
		Long id = getUserId();
		if(id != null){
			userId = id.toString();
		}
		addAttr("userId", userId);
		addAttr("searchType", 2);
		addAttr("apState", 0);
		return "hotelHtml5/orderInfo/tmc_orderInit";
	}
	/**
	 * @param userId 审核人ID
	 * @param apState  0:未审核订单; 2：审核过的订单
	 * @return
	 * @author zhaojian
	 * @date 2017年11月17日 上午11:30:21 
	 */
	@RequestMapping("listApprovalPendingOrder")
	public String listApprovalPendingOrder(String userId, String searchType, String apState, @RequestParam(defaultValue = " 10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageNum){
		
		PageUtil<TmcOrderInfoH5> page = new PageUtil<TmcOrderInfoH5>();
		
		Request_ListApprovalPending requestApproval = new Request_ListApprovalPending();
		requestApproval.businessType = 3;
		requestApproval.state = Integer.valueOf(apState);
		requestApproval.auditorId = Long.parseLong(userId);
		Page pageApproval = new Page();
		pageApproval.index = pageNum;
		pageApproval.size = pageSize;
		requestApproval.page = pageApproval;
		Response_ListApprovalPending responseApproval = tTmcApproveProcessService.listApprovalPending(requestApproval);
		if(responseApproval != null && !CollectionUtils.isEmpty(responseApproval.list)){
			int totalRows = 0;
			if(responseApproval.page != null && responseApproval.page.count != null){
				totalRows = responseApproval.page.count;
			}
			List<String> orders = new ArrayList<String>();
			for(TTmcApproveProcessInfo ap : responseApproval.list){
				if(ap != null && ap.orderno != null){
					orders.add(ap.orderno);
				}
			}
			if(!CollectionUtils.isEmpty(orders)){
				Request_ListTmcOrder request = new Request_ListTmcOrder();
				request.setPageNum(1);
				request.setPageSize(pageSize);
				request.setSearchType(Integer.valueOf(searchType));
				request.setOrderNoList(orders);
				request.setTotalRows(totalRows);
				Response_ListTmcOrder response = tmcHotelOrderService.listTmcOrder(request);
				if(response != null && response.getPageInfo() != null){
					page = response.getPageInfo();
				}
			}
		}
		addAttr("page", page);
		return "hotelHtml5/orderInfo/tmc_orderList";
	}
}
