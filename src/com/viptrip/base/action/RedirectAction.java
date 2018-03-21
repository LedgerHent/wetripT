package com.viptrip.base.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.util.StringUtil;


@Controller
@Scope("prototype")
public class RedirectAction {
	
	@RequestMapping("/goto")
	public String dispather(String page){
		if(!StringUtil.isEmpty(page)){
			if(page.indexOf(".jsp")>0){
				page = page.substring(0, page.indexOf(".jsp"));
			}
			return page;
		}
		return null;
	}
	
	@RequestMapping("/redirect")
	public String redirect(String page){
		if(!StringUtil.isEmpty(page)){
			return "redirect:/goto?page=" + page;
		}
		return null;
	}
}
