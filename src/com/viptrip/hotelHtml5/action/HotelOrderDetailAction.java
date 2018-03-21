package com.viptrip.hotelHtml5.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.viptrip.base.action.BaseAction;
import com.viptrip.common.controller.GetApprovalProcess;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.hotelHtml5.service.TmcHotelOrderService;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.util.PageUtil;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.response.Response_CancelOrder;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.tmc.OrderDetailVo;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderInfoH5;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_CancelOrder;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder.OrderResponse;
import com.viptrip.pay.ali.util.AliConfig;
import com.viptrip.pay.vo.Request_Pay;
import com.viptrip.pay.vo.Response_Pay;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TAcUser;


import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/hotelOrder")
public class HotelOrderDetailAction extends BaseAction{
	
	private static final String orderPage="hotelHtml5/hotel-orderDetail";
	private static final String orderPage1="hotelHtml5/hotel-orderDetails";
	private static final String orderSuccess="hotelHtml5/orderInfo/orderSueecss";
	private static final String orderPageList="hotelHtml5/orderInfo/tmc_orderInit";
	
	private static Logger logger = LoggerFactory.getLogger(HotelOrderDetailAction.class);
	
	@Autowired
	private TmcHotelOrderService tmcHotelOrderService;
	
	
	@RequestMapping("/orderpage.act")
	public String orderDetail(String flag,String orderNo){
		try {
			Request_GetTmcOrderDetail orderReq=new Request_GetTmcOrderDetail();
			orderReq.method="getTmcOrderDetail";
			//orderReq.setOrderNo(orderNo);
			orderReq.setOrderNo(orderNo);
			Response_GetTmcOrderDetail result=JhUtil.sendReqJh(orderReq, Response_GetTmcOrderDetail.class);
			System.out.println("orderDetail====="+result);
			/*审批*/
			GetApprovalProcess gp = new GetApprovalProcess();
			Request_GetApprovalProcess req = new Request_GetApprovalProcess();
			req.businessType=3;
			req.orderno=orderNo;
			OutputResult<Response_GetApprovalProcess, String> result1 = gp.ClientRequest(req, Response_GetApprovalProcess.class);
			
			TAcUser user= getUser();
			addAttr("user", user);
			//addAttr("userid", 305347);
			addAttr("result", result);
			addAttr("result1", result1.getResultObj());
			System.out.println(result1.getResultObj());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@==============="+result1.result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!"".equals(flag)&&"2".equals(flag)){
			addAttr("flag", flag);
			return orderPage;
		}else if(!"".equals(flag)&&"1".equals(flag)){
			addAttr("flag", flag);
			return orderPage1;
		}
		return null;
	}
	
	/**
	 * 取消订单
	 * @param orderNo
	 * @param memo
	 * @param flag
	 * @return
	 */
	@RequestMapping("/cancelOrder.act")
	public String cancelOrder(String orderNo, String memo,String flag){
		try {
			Request_CancelOrder cancelOrder = new Request_CancelOrder();
			String userId = getUserId().toString();
			cancelOrder.method="CancelOrder";
			cancelOrder.setOrderNo(orderNo);
			cancelOrder.setMemo(memo);
			Response_CancelOrder result=JhUtil.sendReqJh(cancelOrder, Response_CancelOrder.class);
			System.out.println(result.status);
			
			//进入订单列表
			Request_ListTmcOrder request = new Request_ListTmcOrder();
			PageUtil<TmcOrderInfoH5> page = new PageUtil<TmcOrderInfoH5>();
			request.setSearchType(1);
			request.setPageNum(1);
			request.setPageSize(10);
			request.setBookingUserId(userId);
			Response_ListTmcOrder response = tmcHotelOrderService.listTmcOrder(request);
			if(response != null && response.getPageInfo() != null){
				page = response.getPageInfo();
			}
			addAttr("page", page);
			getReq().setAttribute("searchType", 1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderPageList;
		
	}
	
	@RequestMapping("/hotelid.act")
	@ResponseBody
	public void queryHotel(String hotelId){
		try {
			Request_GetHotelDetail request = new Request_GetHotelDetail();
			request.method = "GetHotelDetail";
			request.setPtHotelId(hotelId);
			Response_GetHotelDetail result = JhUtil.sendReqJh(request,
					Response_GetHotelDetail.class);
			String jsonS = JSONObject.fromObject(result.getBusiData()).toString();
			PrintWriter pw;
				pw = resp.getWriter();
				pw.write(jsonS);
				pw.flush();
				pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/payOrder.act")
	@ResponseBody
	public void payOrder(String from,String orderId,String price,String payType){
		Request_Pay res_pay = new Request_Pay();
		res_pay.orderId=orderId;
		res_pay.payType=Integer.valueOf(payType);
		res_pay.title="酒店H5订单支付测试";
		res_pay.price=Double.valueOf(price);
		//res_pay.price=0.01;
		res_pay.body="酒店H5订单支付测试";
		try {
			res_pay.notifyURL=PropertiesUtils.getProperty(Const.PRO_PAY_HOTELH5_NOTIFY_URL_R, Const.FILE_PAY_HOTEL_CONFIG);
			//res_pay.returnURL=PropertiesUtils.getProperty(Const.PRO_PAY_HOTELH5_RETURN_URL_R, Const.FILE_PAY_HOTEL_CONFIG);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//res_pay.returnURL="http://10.67.141.115:8080/wetrip/hotelOrder/returnPay.act";
		res_pay.returnURL= baseURL + "hotelOrder/returnPay.act";
		//res_pay.notifyURL="http://10.0.8.53:8081/caissa-tmc/pay/payCallbackNew.do";
		res_pay.ip=StringUtil.getIpAddr(req);
		com.viptrip.pay.controller.Pay pay = new com.viptrip.pay.controller.Pay();
		OutputResult<Response_Pay, String> result = pay.ClientRequest(res_pay, Response_Pay.class);
		
		if(result.IsSucceed()){
			String form=result.getResultObj().form;
			Pattern pattern = Pattern.compile("(http|ftp|https):\\/\\/([\\w.]+\\/?)\\S*");
	        Matcher matcher = pattern.matcher(form);
			if(matcher.matches()){
				try {
					getResp().sendRedirect(form);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				getResp().setContentType("text/html;charset=" + AliConfig.CHARSET.UTF8.value());
				//直接跳转到支付页面
				try {
					getResp().getWriter().write(form);
					getResp().getWriter().flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 订单支付完成之后回到订单列表
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/returnPay.act")
	public String returnURL(String orderId){
		OrderDetailVo detail =null;
		try {
			Request_GetTmcOrderDetail orderReq=new Request_GetTmcOrderDetail();
			orderReq.method="getTmcOrderDetail";
			orderReq.setOrderNo(orderId);
			Response_GetTmcOrderDetail result=JhUtil.sendReqJh(orderReq, Response_GetTmcOrderDetail.class);
			detail = result.getOrderDetail();
		} catch (Exception e) {
			e.printStackTrace();
		}
			if("支付成功".equals(detail.getPayStatusName())){
				
				String userId = getUserId().toString();
				
				//进入订单列表
				Request_ListTmcOrder request = new Request_ListTmcOrder();
				PageUtil<TmcOrderInfoH5> page = new PageUtil<TmcOrderInfoH5>();
				request.setSearchType(1);
				request.setPageNum(1);
				request.setPageSize(10);
				request.setBookingUserId(userId);
				Response_ListTmcOrder response = tmcHotelOrderService.listTmcOrder(request);
				if(response != null && response.getPageInfo() != null){
					page = response.getPageInfo();
				}
				getReq().setAttribute("searchType", 1);
				addAttr("page", page);
				return orderPageList;
			}else{    //支付失败
				logger.info("支付失败，回到订单详情去支付页面");
				return this.orderDetail("1", orderId);  //回到我的订单，订单详情
			}
	}
	
	
	@RequestMapping("/detailToPay.act")
	public String detailToPay(String payType,String orderId,String price,String serviceFee){
		OrderResponse response = new Response_SaveTmcOrder.OrderResponse();
		response.setOrderNo(orderId);
		response.setActualPayablePrice(Double.valueOf(price));
		response.setServiceFee(Double.valueOf(serviceFee));
		response.setOrderStatue("0000400002");
		req.setAttribute("orderResponse", response);
		req.setAttribute("isDetail", "detail");
		return orderSuccess;
	}
	
	@RequestMapping("/listProcess.act")
	@ResponseBody
	public Response_GetApprovalProcess listProcess(String orderNo){
		Request_GetApprovalProcess req = new Request_GetApprovalProcess();
		GetApprovalProcess gp = new GetApprovalProcess();
		req.businessType=3;
		req.orderno=orderNo;
		OutputResult<Response_GetApprovalProcess, String> result = gp.ClientRequest(req, Response_GetApprovalProcess.class);
		return result.getResultObj();
		
	}
	
}
