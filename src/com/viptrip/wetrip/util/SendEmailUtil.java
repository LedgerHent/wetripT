/*package com.viptrip.wetrip.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

*//**
 * 
 * @author lrc
 * 本文件夹中第5个文件，李荣春 检查完毕 2015年6月12日 10:22:20
 *//*
public class SendEmailUtil {
	private static final String host = PropertiesUtil.getResourcesProperty("email_host"); // smtp服务器
	private static final String user = PropertiesUtil.getResourcesProperty("email_user"); // 用户名
	private static final String pwd = PropertiesUtil.getResourcesProperty("email_pwd"); // 密码
	private static final String from = PropertiesUtil.getResourcesProperty("email_from"); // 发件人地址
	private static final String local_url = PropertiesUtil.getResourcesProperty("local_url"); // 发件人地址

	public void handSendEmail(String body, String type, String to, String cc, String title) {
		try {
			Sendemail.sendHTML(host, from, user, pwd, to, cc, null, title, body);
		} catch (Exception e) {
//			e.printStackTrace();
			//system.out.println(e.getMessage());
		}
	}
	*//**
	 * 根据订单号发送对应模板邮件(原理：根据订单号和邮件类型生成html邮件页面，httpclient获取html代码发送)
	 * @param orderNo 订单号
	 * @param airorhotel 1机票/2酒店
	 * @param type 1 check 2 issued 3 change 4 refund
	 * @param to 收件人，多个收件人用英文,隔开
	 * @param cc 抄送人
	 * @param title 邮件标题
	 * @return
	 *//*
	public boolean autoSendEmail(String orderNo,String airorhotel, String type, String to,String cc,String title){
		
		if(isEmpty(to) || (!isEmpty(to) && !to.contains("@"))){
			
			return false;
		}
		
		//以上代码需替换掉，换为通过邮件模板组合，然后拼出的邮件内容
		String body = produceEmail(orderNo, airorhotel, type);  //根据订单号，场景，获取该订单号应发送的邮件内容
		
		try {
			Sendemail.sendHTML(host, from, user, pwd, to, cc, null, title, body);
		} catch (Exception e) {
//			e.printStackTrace();
			//system.out.println(e.getMessage());
		}
		return true;
	}
	
	public static void main(String[] args) {
		SendEmailUtil sem = new SendEmailUtil();
		sem.autoSendEmail("20140813A011", "1", "1", "lirongchun@viptrip365.com", null, "测试称呼");
	}
	*//**
	 * 根据订单号发送对应模板邮件(原理：根据订单号和邮件类型生成html邮件页面，httpclient获取html代码发送)
	 * @param orderNo 订单号
	 * @param airorhotel 1机票/2酒店
	 * @param type 1 check 2 issued 3 change 4 refund
	 * @param to 收件人，多个收件人用英文,隔开
	 * @param cc 抄送人
	 * @param title 邮件标题
	 * @return
	 *//*
	public boolean autoSendEmail1(String orderNo,String airorhotel, String type, String to,String cc,String title,String body){
		
		if(isEmpty(to) || (!isEmpty(to) && !to.contains("@"))){
			
			return false;
		}
		try {
			Sendemail.sendHTML(host, from, user, pwd, to, cc, null, title, body);
		} catch (Exception e) {
//			e.printStackTrace();
			//system.out.println(e.getMessage());
		}
		return true;
	}
	
	*//**
	 * 根据订单号发送对应模板邮件(原理：根据订单号和邮件类型生成html邮件页面，httpclient获取html代码发送)
	 * @param orderNo 订单号
	 * @param airorhotel 1机票/2酒店
	 * @param type 1 check 2 issued 3 change 4 refund
	 * @param to 收件人，多个收件人用英文,隔开
	 * @param cc 抄送人
	 * @param title 邮件标题
	 * @param hx 修改
	 * @return
	 *//*
	public boolean autoUpdateSendEmail(String orderNo,String airorhotel, String type, String to,String cc,String title,String[] uids){
		
		if(isEmpty(to) || (isEmpty(to) && !to.contains("@"))){
			
			return false;
		}
		
		//以上代码需替换掉，换为通过邮件模板组合，然后拼出的邮件内容
		String body = produceEmail1(orderNo, airorhotel, type,uids);  //根据订单号，场景，获取该订单号应发送的邮件内容
		
		//String body = html;//
		try {
			Sendemail.sendHTML(host, from, user, pwd, to, cc, null, title, body);
		} catch (Exception e) {
//			e.printStackTrace();
			//system.out.println(e.getMessage());
		}
		return true;
	}
	*//**
	 * 根据传入的订单号和类型（机票/酒店）及场景（1/2/3/4或1/2，与类型有关）获取邮件内容
	 * @param orderNo 订单号
	 * @param type    1机票/2酒店
	 * @param scence  1待审核/2出票成功/3改期成功/4退票成功或1确认邮件/2审核邮件
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public String produceEmail(String orderNo, String type, String scence) {
		TOrder order = orderManager.getOneOrder(orderNo);
		HOrder horder = hotelSmsModelManager.getOneHOrder(orderNo);
		String compname = "";
		//先根据类型，订单号查询出该订单属于哪个企业
		if (type.equals("1")) {
			//查询机票订单所属企业
			compname = order.getCompanyName();
		} else if (type.equals("2")) {
			//查询酒店订单所属企业
			compname = horder.getCompanyName();
		}
		String strReturn = "";
		//根据订单实体的内容及模板内容组合，使用模板引擎替换
		try {
			Configuration cfg = new Configuration();    
			StringTemplateLoader stl =  new StringTemplateLoader();
			Map params = new HashMap(); 
			params.put("comp", compname);  //邮件场景，根据企业查询条件及公用查询条件查，在java中筛选
			params.put("business", type);
			params.put("scene", scence);
			page = findPageListBySql(page, "TicketSQL.queryEmailTemplateDesc", null, null, params);
			System.out.println(((List<HashMap>) page.getResult()).size());
			if (((List<HashMap>) page.getResult()).size() > 1) {  
				//如果查出多于一条的数据，则会查出公用的和该企业专用。需使用企业专用的，所以公司字段不应为空，且需要是生效的
				for (HashMap resMap : (List<HashMap>) page.getResult()) {
					if (resMap.get("COMPANY") != null && ((String) resMap.get("ISUSE")).equals("1")) {
						stl.putTemplate("", StringUtil.nullTo(resMap.get("TDESC1"), "") + 
								StringUtil.nullTo(resMap.get("TDESC2"), ""));   //因oracle所限，需分开查询，在java中组合
					}
				}
			} else {  //否则只能查到一条数据
				HashMap resMap = ((List<HashMap>) page.getResult()).get(0);
				stl.putTemplate("", StringUtil.nullTo(resMap.get("TDESC1"), "") + 
						StringUtil.nullTo(resMap.get("TDESC2"), ""));   //因oracle所限，需分开查询，在java中组合
			}
			cfg.setTemplateLoader(stl);    
			Template template = cfg.getTemplate("");  //模板数据准备完毕
			Map mapvar = new HashMap();  
			if (type.equals("1")) { //机票业务发送
				//准备订单数据，根据订单号查询订单信息
				if("4".equals(scence)){
					List<TicketRefundEmail> refundList = orderManager.selectTicketRefundByOrderNo(orderNo);
					mapvar=readyAirdataRefund(order,refundList);
				}else if("3".equals(scence)){
					List<TUpdateDate> updateDateList = orderManager.getAirUpdate(orderNo);
					mapvar=readyAirdataupdate(order,updateDateList);
				}else{
					mapvar = readyAirdata(order);	
				}
			} else if (type.equals("2")) {  //酒店业务发送				
				mapvar = readyHoteldata(horder);				
			}
			StringWriter writer = new StringWriter();    
			template.process(mapvar, writer);            //调用模板方法组合数据
			//System.out.println(writer.toString());    
			strReturn = writer.toString();               //组合好的邮件内容，直接发送即可
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strReturn;
	}
	*//**
	 * 根据传入的订单号和类型（机票/酒店）及场景（1/2/3/4或1/2，与类型有关）获取邮件内容
	 * @param orderNo 订单号
	 * @param type    1机票/2酒店
	 * @param scence  1待审核/2出票成功/3改期成功/4退票成功或1确认邮件/2审核邮件
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public String produceEmail1(String orderNo, String type, String scence,String[] uids) {
		TOrder order = orderManager.getOneOrder(orderNo);
		HOrder horder = hotelSmsModelManager.getOneHOrder(orderNo);
		String compname = "";
		//先根据类型，订单号查询出该订单属于哪个企业
		if (type.equals("1")) {
			//查询机票订单所属企业
			compname = order.getCompanyName();
		} else if (type.equals("2")) {
			//查询酒店订单所属企业
			compname = horder.getCompanyName();
		}
		String strReturn = "";
		//根据订单实体的内容及模板内容组合，使用模板引擎替换
		try {
			Configuration cfg = new Configuration();    
			StringTemplateLoader stl =  new StringTemplateLoader();
			Page page  = new Page(Constants.PAGESIZE);
			Map params = new HashMap(); 
			params.put("comp", compname);  //邮件场景，根据企业查询条件及公用查询条件查，在java中筛选
			params.put("business", type);
			params.put("scene", scence);
			page = findPageListBySql(page, "TicketSQL.queryEmailTemplateDesc", null, null, params);
			System.out.println(((List<HashMap>) page.getResult()).size());
			if (((List<HashMap>) page.getResult()).size() > 1) {  
				//如果查出多于一条的数据，则会查出公用的和该企业专用。需使用企业专用的，所以公司字段不应为空，且需要是生效的
				for (HashMap resMap : (List<HashMap>) page.getResult()) {
					if (resMap.get("COMPANY") != null && ((String) resMap.get("ISUSE")).equals("1")) {
						stl.putTemplate("", StringUtil.nullTo(resMap.get("TDESC1"), "") + 
								StringUtil.nullTo(resMap.get("TDESC2"), ""));   //因oracle所限，需分开查询，在java中组合
					}
				}
			} else {  //否则只能查到一条数据
				HashMap resMap = ((List<HashMap>) page.getResult()).get(0);
				stl.putTemplate("", StringUtil.nullTo(resMap.get("TDESC1"), "") + 
						StringUtil.nullTo(resMap.get("TDESC2"), ""));   //因oracle所限，需分开查询，在java中组合
			}
			cfg.setTemplateLoader(stl);    
			Template template = cfg.getTemplate("");  //模板数据准备完毕
			Map mapvar = new HashMap();  
			if (type.equals("1")) { //机票业务发送
				//准备订单数据，根据订单号查询订单信息
				if("4".equals(scence)){
					List<TicketRefundEmail> refundList = orderManager.selectTicketRefundByOrderNo(orderNo);
					mapvar=readyAirdataRefund(order,refundList);
				}else if("3".equals(scence)){
					List<TUpdateDate> updateDateList = orderManager.getAirUpdate(orderNo);
					mapvar=readyAirdataupdate1(order,updateDateList,uids);
				}else{
					mapvar = readyAirdata(order);	
				}
			} else if (type.equals("2")) {  //酒店业务发送				
				mapvar = readyHoteldata(horder);				
			}
			StringWriter writer = new StringWriter();   
			if("0".equals(mapvar.get("isSuccess"))){
			    template.process(mapvar, writer);            //调用模板方法组合数据
			    strReturn = writer.toString();
			}else if("1".equals(mapvar.get("isSuccess"))){
				strReturn ="查询失败,请联系管理员";
			}else{
				strReturn="无改期成功信息";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strReturn;
	}
	*//**
	 * 判断字符串是否为空
	 * @param value 字符串
	 * @return 结果 布尔类型 null 或 "" 都为空
	 *//*
	public static boolean isEmpty(String value){
		boolean f = true;
		if(value!=null&&!value.trim().equals("")){
			f = false;
		}
		return f;
	}
}*/