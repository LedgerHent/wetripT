package com.viptrip.wetrip.action;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.BaseAction;
import com.viptrip.wetrip.controller.GetOrderDetail;
import com.viptrip.wetrip.controller.GetOrderList;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Request_GetOrderList;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.model.Response_GetOrderList;
import com.viptrip.wetrip.model.orderlist.ReqData_GetOrderList;

import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/order")
public class GetOrderAction extends BaseAction{

	private static String list = "order/order_list";
	private static String detail = "order/order_detail1";
	
	@RequestMapping("/list.act")
	public String getOrderList(Integer start,Integer count,Integer status){
		/*if(null==start){
			start = 0;
		}
		if(null==count){
			count = 10;
		}
		if(null==status){
			status = 0;
		}
		GetOrderList getOrderList = new GetOrderList();
		Request_GetOrderList para = new Request_GetOrderList();
		para.setData(new ReqData_GetOrderList(start, count, status));
		para.setUserId(getUserId());
		OutputResult<Response_GetOrderList, String> result = getOrderList.ClientRequest(para , Response_GetOrderList.class);
		addAttr("result",result);*/
		addAttr("status",status);
		return list;
	}
	
	@RequestMapping("/listJSON.act")
	@ResponseBody
	public AjaxResp getOrderListJSON(Integer start,Integer count,Integer status){
		if(null==start){
			start = 0;
		}
		if(null==count){
			count = 10;
		}
		if(null==status){
			status = 0;
		}
		GetOrderList getOrderList = new GetOrderList();
		Request_GetOrderList para = new Request_GetOrderList();
		para.setData(new ReqData_GetOrderList(start, count, status));
		para.setUserId(getUserId());
		OutputResult<Response_GetOrderList, String> result = getOrderList.ClientRequest(para , Response_GetOrderList.class);
		AjaxResp ajax = null;
		if(null!=result){
			if(result.IsSucceed()){
				ajax = new AjaxResp(result.code, result.getResultObj());
			}else{
				ajax = new AjaxResp(result.code, result.result);
			}
		}else{
			ajax = new AjaxResp(1, "无数据");
		}
		return ajax;
	}
	
	@RequestMapping("/detail.act")
	public String getOrderDetail(Long orderId){
//		if(null==start){
//			start = 1;
//		}
//		if(null==count){
//			count = 10;
//		}
//		if(null==status){
//			status = 0;
//		}
//		GetOrderList getOrderList = new GetOrderList();
//		Request_GetOrderList para = new Request_GetOrderList();
//		para.setData(new ReqData_GetOrderList(start, count, status));
//		para.setUserId(getUserId());
//		OutputResult<Response_GetOrderList, String> result = getOrderList.ClientRequest(para , Response_GetOrderList.class);
//		addAttr("result",result);
//		System.out.println("GetOrderAction.getOrderList()==result==" + new Gson().toJson(result));
		GetOrderDetail getOrderDetail = new GetOrderDetail();
		Request_GetOrderDetail para = new Request_GetOrderDetail();
		para.setOrderID(orderId);
		para.setUserId(getUserId());
		OutputResult<Response_GetOrderDetail, String> result = getOrderDetail.ClientRequest(para, Response_GetOrderDetail.class);
		addAttr("result",result);
		return detail;
	}
	
	
}
