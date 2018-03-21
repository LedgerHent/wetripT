package com.viptrip.pay.abc.api.resource;


import com.viptrip.util.StringUtil;

public enum ResultCode {
	C0000("0000","交易成功"),
	C1000("1000","无法读取商户端配置文件"),
	C1001("1001","商户端配置文件中参数设置错误"),
	C1002("1002","无法读取商户证书文档"),
	C1003("1003","无法读取商户私钥"),
	C1004("1004","无法写入交易日志文档"),
	C1005("1005","证书过期"),
	C1006("1006","证书格式错误"),
	C1100("1100","商户提交的交易资料不完整"),
	C1101("1101","商户提交的交易资料不合法"),
	C1102("1102","签名交易报文时发生错误"),
	C1103("1103","无法连线签名服务器"),
	C1104("1104","签名服务器返回签名错误"),
	C1201("1201","无法连线网上支付平台"),
	C1202("1202","提交交易时发生网络错误"),
	C1203("1203","无法接受到网上支付平台的响应"),
	C1204("1204","接收网上支付平台响应报文时发生网络错误"),
	C1205("1205","无法辨识网上支付平台的响应报文"),
	C1206("1206","网上支付平台服务暂时停止"),
	C1301("1301","网上支付平台的响应报文不完整"),
	C1302("1302","网上支付平台的响应报文签名验证失败"),
	C1303("1303","无法辨识网上支付平台的交易结果"),
	C1999("1999","系统发生无法预期的错误"),
	
	C2000("2000","无法读取网上支付平台系统配置文件"),
	C2001("2001","网上支付平台系统配置文件中参数设置错误"),
	C2002("2002","无法读取网上支付平台证书"),
	C2003("2003","无法读取网上支付平台私钥"),
	C2004("2004","数据库错误"),
	C2006("2006","证书格式错误"),
	C2100("2100","商户提交的交易资料不完整"),
	C2101("2101","商户提交的交易资料不合法"),
	C2102("2102","签名响应报文时发生错误"),
	C2201("2201","无法连线银行后台系统"),
	C2202("2202","接收商户交易请求时发生网络错误"),
	C2205("2205","无法辨识商户提交的交易请求报文"),
	C2301("2301","商户提交的交易请求报文不完整"),
	C2302("2302","商户提交的交易请求报文签名验证失败"),
	C2303("2303","商户提交的商户号与签名所用的证书不匹配"),
	C2304("2304","商户状态不允许交易"),
	C2305("2305","商户不存在"),
	C2306("2306","订单状态不允许进行此种交易"),
	C2307("2307","无此订单"),
	C2308("2308","商户无可用的支付方式"),
	C2309("2309","无法取得商户证书"),
	C2310("2310","网上支付平台未开放此种类的交易"),
	C2311("2311","商户未开放制定的商品种类"),
	C2400("2400","后台系统响应交易失败"),
	C2500("2500","所有交易已测试通过，请通知银行开放可以进行正式交易"),
	C2501("2501","测试 交易种类错误，请按照网上支付平台所指示的顺序进行测试"),
	C2600("2600","未到可以下载对账单的时间，请在可以下载对账单的时间再下载"),
	C2999("2999","系统发生无法预期的错误");
	
	
	private String code;
	private String msg;
	
	private ResultCode(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static String msg(String code){
		String result = null;
		if(StringUtil.isNotEmpty(code)){
			ResultCode[] codes = ResultCode.values();
			for (ResultCode resultCode : codes) {
				if(resultCode.code().equals(code)){
					result = resultCode.msg();
					break;
				}
			}
		}
		return result;
	}
	
	public static ResultCode convert(String code){
		ResultCode result = null;
		if(StringUtil.isNotEmpty(code)){
			ResultCode[] codes = ResultCode.values();
			for (ResultCode resultCode : codes) {
				if(resultCode.code().equals(code)){
					result = resultCode;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean isSuccess(){
		return this.code==ResultCode.C0000.code;
	}
	
	public String code(){
		return this.code;
	}
	public String msg(){
		return this.msg;
	}
	
}
