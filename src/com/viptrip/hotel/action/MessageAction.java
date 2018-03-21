package com.viptrip.hotel.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.model.Request_SmsCallBack;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;
import com.viptrip.hotel.util.UrlConfig;
import com.viptrip.util.HttpPost;

@Controller
@RequestMapping("/hotelMessage")
public class MessageAction extends BaseAction{
	// public String message;
	@SuppressWarnings("static-access")
	@RequestMapping("/reply.act")
	public void autoReply(HttpServletRequest request,HttpServletResponse response){
		
		 String url = UrlConfig.CaissaSmsUrl;
		 String message = HotelUtil.nvl(request.getParameter("message"));
		 String orderNo = HotelUtil.nvl(request.getParameter("orderNo"));
		 String phone = HotelUtil.nvl(request.getParameter("mobile"));
		 String userId = "";
		 String param = "";
		 IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class); 
		 userId = service.getUserInfoByPhone(phone);
		 Request_SmsCallBack msg = new Request_SmsCallBack();
		 msg.setOrderNo(orderNo);
		 msg.userId = Integer.parseInt(userId);
		 msg.method = "smsCallBack";
		 msg.setUpSmsContent(message);
		 msg.setMobile(phone);
		 param = HotelUtil.getEncryptParamStr(msg);
		 param ="data="+param;
		 System.out.println("加密串："+param);
		 //url = "http://localhost:8080/wetrip/server/hotelServer";
		 String result = new HttpPost().Post(url, param);
		 System.out.println(result);
	}
}
