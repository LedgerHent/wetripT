package com.viptrip.wetrip.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.IGetOrderListDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.dao.ext.GetOrderListDaoExt;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Request_GetOrderList;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.model.Response_GetOrderList;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderList;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.util.ConvertorFactory;
import com.viptrip.wetrip.util.ListObjectConverter;
@Service
public class GetOrderService implements IGetOrderService{
	
	private static Logger logger = LoggerFactory.getLogger(GetOrderService.class);
	
	@SuppressWarnings("unused")
	@Resource
	private ComDao cdao;
	@Resource
	private IGetOrderListDao dao;
	@Resource
	private GetOrderListDaoExt daoExt;
	
	public Response_GetOrderList getOrderList(Request_GetOrderList para){
		Response_GetOrderList result = null;
		int status = para.getData().getStatus();
		int start = para.getData().getStart();
		int count = para.getData().getCount();
		String hql = "from TOrder";
		LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
		List<Object> params = new ArrayList<>();
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("OId", "desc");
		
		int rStatus = status<100?status:status-100;
		
		if(0==rStatus){
			conditions.put("orderStatus", "<>");
			params.add(7+"");
		} 
		if(1==rStatus){//待审核订单
			conditions.put("orderStatus", "=");
			params.add(1+"");
		}
		if(2==rStatus){//审核通过
			conditions.put("orderStatus", "=");
			conditions.put("checkresult", "=");
			params.add(3+"");
			params.add("Y");
		}
		if(3==rStatus){//审核拒绝
			conditions.put("orderStatus", "=");
			conditions.put("checkresult", "=");
			params.add(3+"");
			params.add("N");
		}
		if(4==rStatus){//待支付
			conditions.put("orderStatus", "=");
			params.add(2+"");
		}
		if(5==rStatus){//已完成:已审核，已支付，已取消
			conditions.put("orderStatus", "in");
			params.add(new String[]{3+"",4+"",5+""});
		}
	
		if(status<100){
			conditions.put("subscribeId", "=");
			params.add(para.getUserId() + "");
		}else if(1==rStatus){
			conditions.put("checkmenId", "=");
			params.add(para.getUserId());
		}
			
		
		PageObject<TOrder> page = daoExt.getPage(start, count, hql, conditions, params.toArray(), orderBy);
		if(null!=page){
			int dataCount = page.getDataCount();
			List<TOrder> orders = page.getPageList();
			List<RespData_GetOrderList> list = null;
			if(null!=orders && orders.size()>0){
//				list = new ArrayList<RespData_GetOrderList>();
				list = ListObjectConverter.convert(orders, list, ConvertorFactory.instance().getConvertor(TOrder.class, RespData_GetOrderList.class));
			}
			result = new Response_GetOrderList(dataCount, list);
			
		}
		return result;
	}
	
	public Response_GetOrderDetail getOrderDetail(Request_GetOrderDetail para){
		Response_GetOrderDetail result = null;
		if(null!=para&&null!=para.getOrderID()){
			TOrder order = dao.findOne(para.getOrderID());
			RespData_GetOrderDetail data = ConvertorFactory.instance().getConvertor(TOrder.class, RespData_GetOrderDetail.class).convert(order);
			result = new Response_GetOrderDetail(data);
		}
		logger.debug("GetOrderService.getOrderDetail()==result==" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(result));
		return result;
	}
	
	public TOrder getOrderById(Long orderId){
		return dao.findOne(orderId);
	}
	
	public TOrder getOrderByOrderno(String orderno){
		TOrder result = null;
		if(!StringUtil.isEmpty(orderno)){
			String hql = "from TOrder where orderno = ?";
			List<TOrder> list = cdao.queryForList(hql, new Object[]{orderno});
			if(null!=list && list.size()>0){
				result = list.get(0);
			}
		}
		return result;
	}

}
