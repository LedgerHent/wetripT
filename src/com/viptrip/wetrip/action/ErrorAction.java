package com.viptrip.wetrip.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
@Controller
@RequestMapping("/error")
public class ErrorAction extends BaseAction {
	@RequestMapping("/returnError.act")
	public String returnError(String result){
		//addAttr("result",result);
		return "/public/failure";
	}
}
