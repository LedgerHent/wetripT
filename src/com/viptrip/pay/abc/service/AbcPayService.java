package com.viptrip.pay.abc.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.abc.pay.client.Base64;
import com.abc.pay.client.ebus.RefundRequest;
import com.viptrip.pay.abc.api.resource.ResultCode;
import com.viptrip.pay.abc.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.QueryOrderRequest;
import com.viptrip.pay.abc.api.resource.QueryType;
import com.viptrip.util.StringUtil;
import org.springframework.util.Assert;


@Service("abc_unipay")
public class AbcPayService {
	private static Logger log = LoggerFactory.getLogger(AbcPayService.class);
	
	/**
	 * 统一支付
	 * @param response
	 * @param mpr
	 */
	public void unipay(HttpServletResponse response,MPaymentRequest mpr){
		try {
			if(null!=mpr){
				Resp<String> resp = mpr.request();
				if(null!=resp&&resp.isSuccess()){
					if(null!=resp.getData()){
						response.sendRedirect(resp.getData().toString());
					}
				}else{
					response.getWriter().write(resp.getMsg());
				}
			}
		} catch (Exception e) {
			try {
				response.getWriter().write("支付发生异常");
			} catch (IOException e1) {
				log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e1)));
			}
			log.error(StringUtil.getLogInfo(null,null,"调用农行支付发生异常\r\n" + StringUtil.getExceptionStr(e)));
		}
	}

	public String unipay(MPaymentRequest mpr){
		String result = null;
		try {
			if(null!=mpr){
				Resp<String> resp = mpr.request();
				if(null!=resp&&resp.isSuccess()){
					if(null!=resp.getData()){
						result = resp.getData().toString();
					}
				}else{
					result = resp.getMsg();
				}
			}
		} catch (Exception e) {
			result = "支付发生异常";
			log.error(StringUtil.getLogInfo(null,null,"调用农行支付发生异常\r\n" + StringUtil.getExceptionStr(e)));
		}
		return result;
	}

	/**
	 * 查询
	 * @param type 查询类型
	 * @param orderNo 订单号
	 * @param detailFlag 是否订单详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Resp query(QueryType type,String orderNo,boolean detailFlag){
		Resp<QueryRes> result = new Resp<QueryRes>();
		if(null!=type && StringUtil.isNotEmpty(orderNo)){
			QueryOrderRequest tQueryRequest = new QueryOrderRequest();
			tQueryRequest.queryRequest.put("PayTypeID", type.code());    //设定交易类型
			tQueryRequest.queryRequest.put("OrderNo", orderNo);    //设定订单编号 （必要信息）
			tQueryRequest.queryRequest.put("QueryDetail", Boolean.toString(detailFlag));//设定查询方式
			JSON json = tQueryRequest.postRequest();
			Assert.notNull(json);
			String code = json.GetKeyValue("ReturnCode");
			String orderInfo = json.GetKeyValue("Order");
			String msg = json.GetKeyValue("ErrorMessage");
			if(ResultCode.C0000 == ResultCode.convert(code)){
				if(StringUtil.isEmpty(orderInfo)){
					result.success("查询结果为空",null);
				}else{
					Base64 tBase64 = new Base64();
					String orderDetail = new String(tBase64.decode(orderInfo));
					json.setJsonString(orderDetail);
					Class clz = null;
					switch (type){
						case 直接支付:
							clz = QueryResImmediate.class;
							break;
						case 退款:
							clz = QueryResRefund.class;
							break;
					}
					QueryRes<?> res = buildObjFromJSON(json,clz,detailFlag);
					result.success(res);
				}
			}else{
				result.fail(msg);
			}

		}else{
			result.fail("请求的参数不正确");
		}
		return result;
	}

	/**
	 * 组装查询结果对象
	 * @param json 包含查询信息的JSON对象
	 * @param clz 结果对象的泛型类型
	 * @param flag 是否为查询明细
	 * @return
	 */
	private QueryRes buildObjFromJSON(JSON json,Class<? extends QueryResObj> clz,boolean flag){
		QueryRes result = new QueryRes();
		if(null!= json){
			//设置公共属性
			Field[] fields = result.getClass().getDeclaredFields();
			for (Field f :
					fields) {
				String fName = f.getName();
				try {
					PropertyDescriptor descriptor = new PropertyDescriptor(fName, result.getClass());
					Method method = descriptor.getWriteMethod();
					if(String.class == f.getType()){
                        method.invoke(result,json.GetKeyValue(fName));
                    }
				} catch (Exception e) {
					log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
				}
			}
			//设置差异属性
			if(flag && null!=clz){
				QueryResObj obj = null;
				try {
					obj = (QueryResObj)clz.newInstance();
					setProperty(json,obj);
				} catch (Exception e) {
					log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
				}
				result.setData(obj);
			}
			//以下设置商品明细
			LinkedHashMap hashMap = json.GetArrayValue("OrderItems");
			if(null!=hashMap && hashMap.size()>0){
				List<ProductItem> list = new ArrayList<>();
				Iterator iter = hashMap.entrySet().iterator();
				while (iter.hasNext()){
					Map.Entry entry = (Map.Entry) iter.next();
					//Object key = entry.getKey();
					Hashtable val = (Hashtable)entry.getValue();
					ProductItem pi = new ProductItem();
					setProperty(val,pi);
					list.add(pi);
				}
				result.setList(list);
			}
		}
		return result;
	}

	/**
	 * 退款
	 * @param orderNo 原订单号
	 * @param newOrderNo 退款订单号
	 * @param amount 退款金额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Resp<RefundRes> refund(String orderNo,String newOrderNo,Double amount){
		Resp<RefundRes> result = new Resp<RefundRes>();
		if(StringUtil.isNotEmpty(orderNo) && StringUtil.isNotEmpty(newOrderNo) && null!=amount && amount>0){
			Resp query = query(QueryType.直接支付, orderNo, false);//查询支付信息
			QueryRes qr = query.isSuccess()?(QueryRes)query.getData():null;
			if(null!=qr){
				RefundReq tRequest = new RefundReq(qr.getOrderDate(),qr.getOrderTime(),orderNo,newOrderNo,amount);
				JSON json = tRequest.postRequest();
				Assert.notNull(json);
				String code = json.GetKeyValue("ReturnCode");
				String msg = json.GetKeyValue("ErrorMessage");
				if(ResultCode.C0000 == ResultCode.convert(code)){
					Class clz = RefundRes.class;
					RefundRes obj = null;
					if("办理成功".equals(msg)) {
						try {
							obj = (RefundRes) clz.newInstance();
							setProperty(json, obj);
						} catch (Exception e) {
							log.error(StringUtil.getLogInfo(null, null, StringUtil.getExceptionStr(e)));
						}
					}
					result.success(msg,obj);
				}else{
					result.success(msg,null);
				}
			}
		}else{
			result.fail("退款请求参数不完整");
		}
		return result;
	}

	/**
	 * 反射从JSON中获取对象的属性值
	 * 		JSON中的key必须与对象中的属性名称保持一致
	 * @param json JSON对象
	 * @param obj 需要设置值的对象
	 */
	private void setProperty(JSON json,Object obj){
		Assert.notNull(json);
		Assert.notNull(obj);
		Class clz = obj.getClass();
		try {
			Field[] fields = clz.getDeclaredFields();
			for (Field f :
					fields) {
				String fName = f.getName();
				PropertyDescriptor pd = new PropertyDescriptor(fName, clz);
				Method method = pd.getWriteMethod();
				method.invoke(obj, json.GetKeyValue(fName));
			}
		}catch (Exception e){
			log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
		}
	}
	/**
	 * 反射从JSON中获取对象的属性值
	 * 		JSON中的key必须与对象中的属性名称保持一致
	 * @param json JSON对象
	 * @param obj 需要设置值的对象
	 */
	private void setProperty(Hashtable json,Object obj){
		Assert.notNull(json);
		Assert.notNull(obj);
		Class clz = obj.getClass();
		try {
			Field[] fields = clz.getDeclaredFields();
			for (Field f :
					fields) {
				String fName = f.getName();
				PropertyDescriptor pd = new PropertyDescriptor(fName, clz);
				Method method = pd.getWriteMethod();
				method.invoke(obj, json.get(fName));
			}
		}catch (Exception e){
			log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
		}
	}

	
}
