package com.viptrip.hotelHtml5.service;

import java.util.List;
import java.util.Map;

import com.viptrip.hotelHtml5.vo.request.Request_SaveTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.OrderDetailRequestVo;
import com.viptrip.hotelHtml5.vo.tmc.ParameterResponseVO;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListApprovePolicyInfo;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListApprovePolicyInfo;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder;
import com.viptrip.wetrip.model.employees.CompanyInfo;

public interface TmcHotelOrderService {
	
	/**
	 * 点击预订按钮时，获取酒店、房型、景鸿企业员工等相关信息
	 * @param request
	 * @return
	 * @author zhaojian
	 * @date 2017年11月9日 下午4:07:58 
	 */
	public Response_GetHotelBooking getHotelBooking(Request_GetHotelBooking request);
	
	/**
	 * 下单
	 * @param request
	 * @return
	 * @author zhaojian
	 * @date 2017年11月9日 下午4:12:47 
	 */
	public Response_SaveTmcOrder saveTmcOrder(Request_SaveTmcOrder request);

	/**
	 * 查询订单列表
	 * @param request
	 * @return
	 * @author zhaojian
	 * @date 2017年11月2日 下午5:07:52 
	 */
	public Response_ListTmcOrder listTmcOrder(Request_ListTmcOrder request);
	
	/**
	 * 获取订单详情
	 * @param request
	 * @return
	 * @author zhaojian
	 * @date 2017年11月9日 下午4:14:23 
	 */
	public Response_GetTmcOrderDetail getTmcOrderDetail(Request_GetTmcOrderDetail request);
	
	/**
	 * 获取审批列表
	 * @param request
	 * @return
	 */
	public Response_ListApprovePolicyInfo listApprovePolicyInfo(Request_ListApprovePolicyInfo request);
	
	/**
	 * 获取企业框架列表
	 * @param companyInfo
	 * @param list
	 * @param temp
	 * @return
	 */
	public List<ParameterResponseVO> getOrgList(CompanyInfo companyInfo, List<ParameterResponseVO> list, String temp);
	
	/**
	 * @param orderDetail
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> validateStepOne(OrderDetailRequestVo orderDetail) throws Exception;
}
