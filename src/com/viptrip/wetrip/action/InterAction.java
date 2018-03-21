package com.viptrip.wetrip.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.AjaxStatus;
import com.viptrip.base.action.BaseAction;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail;
import com.viptrip.wetrip.service.ICaiyunPayService;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.service.IUserService;
import com.viptrip.wetrip.util.ConvertorFactory;
import com.viptrip.wetrip.vo.CaiyunResp;

/**
 * 对外接口
 * @author selfwhisper
 *
 */
@Controller
@RequestMapping("/inter")
public class InterAction extends BaseAction{
	private static Logger logger = LoggerFactory.getLogger(InterAction.class);
	
	@Resource
	private ICaiyunPayService service;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IGetOrderService orderService;
	
	
	@RequestMapping(value = "/caiyunPay/prePay.act",produces = "application/json;charset=utf-8")
	@ResponseBody
	public CaiyunResp prePay(Long userId,String orderId,Double price,String extend){
		CaiyunResp<?> prePayCheck = new CaiyunResp<>();
		if(!StringUtil.isEmpty(orderId) && null!=userId && null != price && price>=0){
			prePayCheck = service.prePay(userId, orderId, price, extend);
		}
		return prePayCheck;
	}
	
	@RequestMapping(value = "/caiyunPay/refund.act",produces = "application/json;charset=utf-8")
	@ResponseBody
	public CaiyunResp refund(Long userId,String orderId,Double price,String extend){
		logger.debug("com.viptrip.wetrip.action.InterAction.refund(),userId=" + userId +",orderId=" + orderId + ",price=" + price + ",extend=" + extend);
		CaiyunResp<?> prePayCheck = new CaiyunResp<>();
		if(!StringUtil.isEmpty(orderId) && null!=userId && null != price && price>=0){
			prePayCheck = service.payRefund(userId, orderId, price, extend);
			if(null==prePayCheck){
				prePayCheck = new CaiyunResp<>();
			}
		}
		return prePayCheck;
	}
	
	@RequestMapping(value = "/caiyunPay/close.act",produces = "application/json;charset=utf-8")
	@ResponseBody
	public CaiyunResp close(Long userId,String orderId,Double price,String extend){
		CaiyunResp<?> prePayCheck = new CaiyunResp<>();
		if(!StringUtil.isEmpty(orderId) && null!=userId && null != price && price>=0){
			prePayCheck = service.payClose(userId, orderId, price, extend);
			if(null==prePayCheck){
				prePayCheck = new CaiyunResp<>();
			}
		}
		return prePayCheck;
	}
	
	@RequestMapping(value = "/user/isUserBelongsToUban.act",produces = "application/json;charset=utf-8")
	@ResponseBody
	public AjaxResp isUserId(Long userId){
		AjaxResp result = new AjaxResp();
		if(null!=userId&&userId>0){
			result = service.isUserBelongsToUban360(userId);
		}else{
			result = new AjaxResp(AjaxStatus.fail);
		}
		return result;
	}
	
	@RequestMapping(value = "/order/getOrderDetail.act",produces = "application/json;charset=utf-8")
	@ResponseBody
	public AjaxResp queryOrderDetail(String orderno){
		AjaxResp result = new AjaxResp();
		if(StringUtil.isNotEmpty(orderno)){
			TOrder order = orderService.getOrderByOrderno(orderno);
			if(null!=order){
				RespData_GetOrderDetail data = ConvertorFactory.instance().getConvertor(TOrder.class, RespData_GetOrderDetail.class).convert(order);
				result = success(JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(data));
			}
		}else{
			result = new AjaxResp(AjaxStatus.fail,"订单号不能为空");
		}
		return result;
	}
	
	
}
