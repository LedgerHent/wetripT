package com.viptrip.wetrip.service.impl;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.AjaxStatus;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.service.ICaiyunPayService;
import com.viptrip.wetrip.service.IUserService;
import com.viptrip.wetrip.util.CaiyunUtil;
import com.viptrip.wetrip.util.CommUtil;
import com.viptrip.wetrip.vo.CaiyunAccessToken;
import com.viptrip.wetrip.vo.CaiyunOrderStatus;
import com.viptrip.wetrip.vo.CaiyunPayPara;
import com.viptrip.wetrip.vo.CaiyunPayParaExt;
import com.viptrip.wetrip.vo.CaiyunResp;
@Service
public class CaiyunPayService implements ICaiyunPayService{
	private static Logger logger = LoggerFactory.getLogger(CaiyunPayService.class);
	@Resource
	private IUserService userService;
	
	/**
	 * 预付款  先风控再回调
	 * @param userId
	 * @param orderId
	 * @param price
	 * @param extend
	 * @return
	 */
	public CaiyunResp<?> prePay(Long userId,String orderId,Double price,String extend){
		CaiyunResp<?> prePayCheck = prePayCheck(userId, orderId, price, extend);
		if(null!=prePayCheck){
			if(0==prePayCheck.getStatus()&&null!=prePayCheck.getSuccess()&&prePayCheck.getSuccess()){
				prePayCheck = paySuccess(userId, orderId, price, extend);
				if(null==prePayCheck){
					prePayCheck = new CaiyunResp<>();
				}
			}
		}else{
			prePayCheck = new CaiyunResp<>();
		}
//		ErrShareUtil.set(JSON.get().toJson(prePayCheck));
		return prePayCheck;
	}
	
	public CaiyunResp<?> prePayCheck(Long userId,String orderId,Double price,String extend){
		logger.info("调用讯盟风控接口prePayCheck，参数：userId→" + userId + ",orderId→" + orderId + ",price→" + price + ",extend→" + extend);
		CaiyunResp<?> resp = null;
		if(null!=price && price>0){
			price = price*100;
		}
		String uid = userService.getUban360UId(userId);
		if("0".equals(uid)){
			resp = new CaiyunResp<>(1, "没有找到关联的用户", null);
		}else if("-1".equals(uid)){
			resp = new CaiyunResp<>(2, "找到不唯一的关联用户", null);
		}else{
			Object obj = extend;
			if(StringUtil.isNotEmpty(extend)){
				try {
					obj = JSONObject.fromObject(extend);
				} catch (Exception e) {
					logger.error("extend转json对象失败，extend=" + extend);
				}
			}
			CaiyunPayPara para = new CaiyunPayPara(orderId, price.intValue(), obj);
			CaiyunAccessToken accessToken = CommUtil.getAccessToken();
			resp = CaiyunUtil.prePayCheck(accessToken.getAccessToken() , uid, para);
		}
//		ErrShareUtil.set(JSON.get().notEscapeHTML(true).toJson(resp));
		return resp;
	}
	
	public CaiyunResp<?> payCallBack(Long userId,CaiyunPayParaExt para){
		CaiyunResp<?> resp = null;
		String uid = userService.getUban360UId(userId);
		if("0".equals(uid)){
			resp = new CaiyunResp<>(1, "没有找到关联的用户", null);
		}else if("-1".equals(uid)){
			resp = new CaiyunResp<>(2, "找到不唯一的关联用户", null);
		}else{
			CaiyunAccessToken accessToken = CommUtil.getAccessToken();
			resp = CaiyunUtil.orderCallBack(accessToken.getAccessToken(), uid, para);
		}
//		ErrShareUtil.set(JSON.get().notEscapeHTML(true).toJson(resp));
		return resp;
	}
	
	public CaiyunResp<?> paySuccess(Long userId,String orderId,Double price,Object extend){
		logger.info("调用讯盟回调接口PAY_SUCCESS，参数：userId→" + userId + ",orderId→" + orderId + ",price→" + price + ",extend→" + extend);
		return payCallBack(userId, orderId, price, extend, CaiyunOrderStatus.PAY_SUCCESS);
	}
	
	public CaiyunResp<?> payRefund(Long userId,String orderId,Double price,Object extend){
		logger.info("调用讯盟回调接口REFUND_SUCCESS，参数：userId→" + userId + ",orderId→" + orderId + ",price→" + price + ",extend→" + extend);
		return payCallBack(userId, orderId, price, extend, CaiyunOrderStatus.REFUND_SUCCESS);
	}
	
	public CaiyunResp<?> payClose(Long userId,String orderId,Double price,Object extend){
		logger.info("调用讯盟回调接口TRADE_CLOSE，参数：userId→" + userId + ",orderId→" + orderId + ",price→" + price + ",extend→" + extend);
		return payCallBack(userId, orderId, price, extend, CaiyunOrderStatus.TRADE_CLOSE);
	}
	
	public CaiyunResp<?> payCallBack(Long userId,String orderId,Double price,Object extend,CaiyunOrderStatus status){
		if(null!=price && price>0){
			price = price*100;
		}
		if(null!=extend){
			try {
				extend = JSONObject.fromObject(extend);
			} catch (Exception e) {
				logger.error("extend转json对象失败，extend=" + extend);
			}
		}
		CaiyunPayParaExt para = new CaiyunPayParaExt(orderId, price.intValue(), extend, status);
		return payCallBack(userId, para );
	}
	
	public AjaxResp isUserBelongsToUban360(Long userId){
		AjaxResp result = new AjaxResp();
		String id = userService.getUban360UId(userId);
		if(StringUtil.isEmpty(id) || "0".equals(id)){
			result = new AjaxResp(AjaxStatus.fail);
		}else{
			result = new AjaxResp(AjaxStatus.success);
		}
		return result;
	}
}
