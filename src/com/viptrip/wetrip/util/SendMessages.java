package com.viptrip.wetrip.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.dxpt.entity.Message;
import com.dxpt.service.MessageService;
import com.dxpt.service.impl.MessageServiceImpl;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.resource.Const;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;

public class SendMessages{

	protected HttpServletRequest req;

	@SuppressWarnings("rawtypes")
	public String SendCheckMen(TOrder order){
		String rt = "1";
		try{
		
		HashMap hashMap = (HashMap)RedisCacheManager.get(Const.APP_MAP_3CHAR_CITY,Map.class);
		HashMap hashcang = (HashMap)RedisCacheManager.get(Const.APP_MAP_CABINNAME,Map.class);
		DateFormat df = new SimpleDateFormat("MM月dd日HH:mm");
		String message = "";
		String chengjiDes = "乘机人:";
		
	    message = "尊敬的" + order.getCheckmen() + "先生/女士您好，贵公司" + order.getSubscribeName() + "预订了";
        if (order.getProcessstate().equals("1")) {
            for (TPnr tpnr : order.getTPnrs()) {
                for (TTravelItinerary tti :tpnr.getTTravelItineraries()) {
                    chengjiDes += tti.getPassengerName()+ "，";
                }
                tpnr.setCangwei(tpnr.getCangwei() == null ? "" : tpnr.getCangwei());
                message += df.format(tpnr.getOriginTime()) + (String) hashMap.get(tpnr.getOriginCity()) + "至"
                        + (String) hashMap.get(tpnr.getDestinationCity()) + tpnr.getAirline() + "次航班"
                        + (String) hashcang.get(tpnr.getAirlineCompany()+"_"+tpnr.getCangwei())+ "，";
                message += "，总价：" + order.getTotalPrice().toString() + "元" + chengjiDes
                        + "因预订随时会被航空公司取消，请尽快登录弘景商旅系统进行审核或直接回复“确认”代审，谢谢！\r\n\r\n";

            }
        } else {
            /*
             * 往返内容：/*尊敬的**先生/女士您好，贵公司**预订了3月13日11:35杭州至北京MU5167航班经济舱
             * 2016-02-22 19:10:00昆明至保山MU5895次航班经济舱，总价：1100元，乘机人：***，
             * 因预订随时会被航空公司取消，请尽快登录弘景商旅系统进行审核或直接回复“确认”代审，谢谢！【凯撒商旅】
             */

            String quCheng = "";
            String fanCheng = "";
            int a=1;
            for (TPnr tpnr : order.getTPnrs()) {
                tpnr.setCangwei(tpnr.getCangwei() == null ? "" : tpnr.getCangwei());
                if (tpnr.getFlighttype().equals("1")) {
                    quCheng = df.format(tpnr.getOriginTime()) + (String) hashMap.get(tpnr.getOriginCity()) + "至"
                            + (String) hashMap.get(tpnr.getDestinationCity()) + tpnr.getAirline() + "次航班"
                            + (String) hashcang.get(tpnr.getAirlineCompany()+"_"+tpnr.getCangwei())+ "，";
                } else {
                    fanCheng = df.format(tpnr.getOriginTime()) + tpnr.getOriginCity() + "至"
                            + tpnr.getDestinationCity() + tpnr.getAirline() + "次航班"
                            + (String) hashcang.get(tpnr.getAirlineCompany()+"_"+tpnr.getCangwei())+ "，";
                }
                if(a==1){
                	for (TTravelItinerary tti :tpnr.getTTravelItineraries()) {
                        chengjiDes += tti.getPassengerName()+ "，";
                    }
                }
                a++;
            }
            message += quCheng + fanCheng;
            
            message += "总价：" + order.getTotalPrice().toString() + "元" + chengjiDes
                    + "因预订随时会被航空公司取消，请尽快登录弘景商旅系统进行审核或直接回复“确认”代审，谢谢！\r\n\r\n";
        }
		
        MessageService ms = new MessageServiceImpl();
        Message msg = new Message();
        	msg.setSendname("系统录入");
        	msg.setMobliephone(order.getChecktel());
        	msg.setUser_key("【凯撒商旅】");
        	msg.setMessage(message);
        	msg.setUvalue(order.getOrderno());
        	msg.setExtf1("2");
        	msg.setRedirect_uri("/msgAudit/msgAuditOrder.hlt");
        	msg.setSendtype("待审核");
        	msg.setOrdertype("3");
        	ms.saveMsg(msg);
		}catch (Exception e) {
			e.printStackTrace();
			rt = "0";
		}
        return rt;
	}

	public String SendSubscribeMen(TOrder order) {
		String rt = "1";
		try{
		/*"尊敬的{ydr}先生/女士您好，您已成功预订{originDate} {origincity}至" +
		  "{destinationcity}{fightnumber}次航班{cangwei}，起飞时间{origintime}，" +
		  "到达时间{destinationtime}，乘机人{panssengername}，总价{totalPrice}元，" +
		  "我们会尽快为您出票，请留意出票信息，谢谢！";
		*/
		HashMap hashMap = (HashMap)RedisCacheManager.get(Const.APP_MAP_3CHAR_CITY,Map.class);
		HashMap hashcang = (HashMap)RedisCacheManager.get(Const.APP_MAP_CABINNAME,Map.class);
		DateFormat df = new SimpleDateFormat("MM月dd日HH:mm");
		String message = "";
		String chengjiDes = "乘机人:";
		
	    message = "尊敬的" + order.getSubscribeName() + "先生/女士您好，您已成功预订了";
        if (order.getProcessstate().equals("1")) {
            for (TPnr tpnr : order.getTPnrs()) {
                for (TTravelItinerary tti :tpnr.getTTravelItineraries()) {
                    chengjiDes += tti.getPassengerName()+ "，";
                }
                tpnr.setCangwei(tpnr.getCangwei() == null ? "" : tpnr.getCangwei());
                message += df.format(tpnr.getOriginTime()) + (String) hashMap.get(tpnr.getOriginCity()) + "至"
                        + (String) hashMap.get(tpnr.getDestinationCity()) + tpnr.getAirline() + "次航班"
                        + (String) hashcang.get(tpnr.getAirlineCompany()+"_"+tpnr.getCangwei())+ "舱，"+ chengjiDes;
                message += "总价：" + order.getTotalPrice().toString() + "元，" 
                        + "我们会尽快为您出票，请留意出票信息，谢谢！\r\n\r\n";

            }
        } else {
          

            String quCheng = "";
            String fanCheng = "";
            int a=1;
            for (TPnr tpnr : order.getTPnrs()) {
                tpnr.setCangwei(tpnr.getCangwei() == null ? "" : tpnr.getCangwei());
                if (tpnr.getFlighttype().equals("1")) {
                    quCheng = df.format(tpnr.getOriginTime()) + (String) hashMap.get(tpnr.getOriginCity()) + "至"
                            + (String) hashMap.get(tpnr.getDestinationCity()) + tpnr.getAirline() + "次航班"
                            + (String) hashcang.get(tpnr.getAirlineCompany()+"_"+tpnr.getCangwei())+ "舱，";
                } else {
                    fanCheng = df.format(tpnr.getOriginTime()) + tpnr.getOriginCity() + "至"
                            + tpnr.getDestinationCity() + tpnr.getAirline() + "次航班"
                            + (String) hashcang.get(tpnr.getAirlineCompany()+"_"+tpnr.getCangwei())+ "舱，";
                }
                if(a==1){
                	for (TTravelItinerary tti :tpnr.getTTravelItineraries()) {
                        chengjiDes += tti.getPassengerName()+ "，";
                    }
                }
                a++;
            }
            message += quCheng + fanCheng + chengjiDes;
            
            message += "总价：" + order.getTotalPrice().toString() + "元，" 
                    + "我们会尽快为您出票，请留意出票信息，谢谢！\r\n\r\n";
        }
		
        MessageService ms = new MessageServiceImpl();
        Message msg = new Message();
        	msg.setSendname("系统录入");
        	msg.setMobliephone(order.getSubscribeTel());
        	msg.setUser_key("【凯撒商旅】");
        	msg.setMessage(message);
        	msg.setUvalue(order.getOrderno());
        	msg.setExtf1("");
        	msg.setRedirect_uri("");
        	msg.setSendtype("待发送");
        	msg.setOrdertype("3");
        	ms.saveMsg(msg);
		}catch (Exception e) {
			e.printStackTrace();
			rt = "0";
		}
        return rt;
	}
}
