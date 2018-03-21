package com.viptrip.pay.wx.api.bean;


/**
 * 统一下单请求实体
 * @author selfwhisper
 *
 */
public class WXMwebPayReq extends WXMwebReqBaseBean{
//	protected String appid;
//	protected String mch_id;
//	protected String nonce_str;
//	protected String sign;
//	protected String sign_type;
//	protected String out_trade_no;
	private String body;
	private Integer total_fee;
	private String spbill_create_ip;
	private String notify_url;
	private String trade_type;
	private String scene_info;
	
	public WXMwebPayReq() {
		super();
		this.trade_type = "MWEB";
		this.scene_info = "{\"h5_info\":{\"type\":\"WAP\",\"wap_url\":\"http://we.viptrip365.com/wetrip/\",\"wap_name\":\"凯撒商旅\"}}";
	}
	
	public WXMwebPayReq(String notifyURL) {
		super();
		this.trade_type = "MWEB";
		this.notify_url = notifyURL;
		this.scene_info = "{\"h5_info\":{\"type\":\"WAP\",\"wap_url\":\"http://we.viptrip365.com/wetrip/\",\"wap_name\":\"凯撒商旅\"}}";
	}
	
	public WXMwebPayReq(String out_trade_no,Integer total_fee,String ip,String body,String notifyURL) {
		super(out_trade_no);
		this.body = body;
		this.total_fee = total_fee;
		this.spbill_create_ip = ip;
		
		this.trade_type = "MWEB";
		this.notify_url = notifyURL;
		this.scene_info = "{\"h5_info\":{\"type\":\"WAP\",\"wap_url\":\"http://we.viptrip365.com/wetrip/\",\"wap_name\":\"凯撒商旅\"}}";
	}
	
	/**
	 * 将实体转为map
	 * @return
	 *//*
	private SortedMap<String, Object> getParam(){
		SortedMap<String, Object> result = new TreeMap<>();
		Field[] fields = getClass().getDeclaredFields();
		Field[] superField = getClass().getSuperclass().getDeclaredFields();
		String[] fnames = new String[fields.length + superField.length];
		int i = 0;
		for (; i < fields.length; i++) {
			fnames[i] = fields[i].getName();
		}
		for(;i<fnames.length;i++){
			fnames[i] = superField[i-fields.length].getName();
		}
		Arrays.sort(fnames);
		for(String fname:fnames){
			String getterName = "get" + (fname.charAt(0)+"").toUpperCase() + fname.substring(1);
			Object value = null;
			try {
				Method method = getClass().getDeclaredMethod(getterName);
				value = method.invoke(this);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			if(null!=value&&!"".equals(value)){
				result.put(fname, value);
			}
		}
		return result;
	}
	
	*//**
	 * 获取请求xml字符串
	 * @return
	 *//*
	public String getReqXML(){
		SortedMap<String, Object> params = getParam();
		//判断签名类型 调用不同的签名方法
		String sign = PayCommonUtil.createSign(WxConfig.CHARSET.UTF8.val(), params, WxConfig.KEY);
		params.put("sign", sign);
		String xml = PayCommonUtil.getRequestXml(params);
		return xml;
	}
	
	*//**
	 * 获取请求参数map
	 * @param signType
	 * @return
	 *//*
	public Map<String, Object> getReqMap(){
		SortedMap<String, Object> params = getParam();
		String sign = PayCommonUtil.createSign(WxConfig.CHARSET.UTF8.val(), params, WxConfig.KEY);
		params.put("sign", sign);
		return params;
	}*/

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}
	
}
