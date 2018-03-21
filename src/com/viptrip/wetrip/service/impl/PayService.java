package com.viptrip.wetrip.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.autoissue.service.AutoTicketService;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.service.IPayService;

@Service
@Transactional
public class PayService implements IPayService{
	
	@Resource
	private IGetOrderService orderService;
	
	@Resource
	private ComDao cdao;
	
	@Resource
    private AutoTicketService autoTicketService;
	
	@Override
	public void paySuccessCheckOrderStatus(Integer flag, Long orderId,Date payTime,String tradeNo,String payType) {
		if(1==flag){//国内机票业务
			TOrder order = orderService.getOrderById(orderId);
			if(null==order){
				order = orderService.getOrderByOrderno(orderId.longValue() + "");
			}
			if(null!=order){
				String orderStatus = order.getOrderStatus();
					if("2".equals(orderStatus)){//原状态为待支付
						order.setOrderStatus("4");
						order.setPayTime(payTime==null?new Date():payTime);
						order.setTradeNo(tradeNo);
						order.setPayMethodDetail(payType);

						Set<TPnr> tPnrs = order.getTPnrs();
						for (TPnr tPnr : tPnrs) {
							Set<TTravelItinerary> tTravelItineraries = tPnr.getTTravelItineraries();
							for (Iterator<TTravelItinerary> iterator = tTravelItineraries
									.iterator(); iterator.hasNext();) {
								TTravelItinerary tTravelItinerary = (TTravelItinerary) iterator
										.next();
								tTravelItinerary.setFlightStatus("11");//待出票
								cdao.executeUpdate(tTravelItinerary);
							}
						}
						cdao.executeUpdate(order);
						
						//自动出票逻辑，在线支付返回后，修改国内机票订单状态
						String autoMaticFlag=autoTicketService.getAutomaticTicket();
			            if(StringUtils.isNotEmpty(autoMaticFlag) && autoMaticFlag.equals("open")){
			                autoTicketService.addFromPay(order.getOrderno(), "1");
			            }
			            
					}
			}
		}
	}

}
