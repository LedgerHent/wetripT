package com.viptrip.common.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.Config;
import com.viptrip.wetrip.entity.TAcUser;
@Controller
@RequestMapping("/common")
@Scope("prototype")
public class HomeAction extends BaseAction{
	private static Logger logger = LoggerFactory.getLogger(HomeAction.class);
	
	@RequestMapping("/toHomePage.act")
	public String toHomePage(){
		return "/public/homePage";
	}
	@RequestMapping("/toBusiness.act")
	public String toBusiness(String type,RedirectAttributes attr){
		TAcUser user = getUser();
		Long userId = getUserId();
		attr.addFlashAttribute("user",user);
		attr.addFlashAttribute("userId",userId);
		if("国内机票".equals(type)){
			return "redirect:"+Config.ticketIndex;
		}else if("国际机票".equals(type)){
			return "redirect:"+Config.inlticketIndex;
		}else if("酒店".equals(type)){
			attr.addFlashAttribute("roomCount","1");
			attr.addFlashAttribute("audltCount","2");
			attr.addFlashAttribute("childCount","0");
			return "redirect:"+Config.hotelIndex;
		}else if("国内机票订单列表".equals(type)){
			return "redirect:"+Config.ticketOrderList;
		}else if("酒店订单列表".equals(type)){
			attr.addFlashAttribute("orderState","0");
			return "redirect:"+Config.hotelOrderList;
		}else{
			attr.addFlashAttribute("result","该业务暂无开通");
			return "redirect:/error/returnError.act";
		}
		
	}
}
