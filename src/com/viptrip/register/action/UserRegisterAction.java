package com.viptrip.register.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viptrip.register.model.RegisterUserInfoModel;
import com.viptrip.register.model.Response_Result;
import com.viptrip.register.service.UserRegister;

import etuf.v1_0.common.JSON;
import etuf.v1_0.model.base.output.OutputSimpleResult;

@Controller
@RequestMapping("/register")
public class UserRegisterAction {
	@Resource
	private UserRegister userRegister;
    
	@RequestMapping("/registeruser.act")
	public String registerUser(RegisterUserInfoModel user,Model model){
		if(null==user.getOrgid()){
			user.setOrgid(-1l);
		}
		OutputSimpleResult osr = userRegister.saveUser4Register(user);
		if(osr.IsSucceed()){
			model.addAttribute("result", osr.result);
			return "/userRegister/success";
		}else{
			model.addAttribute("result", osr.result);
			return "/public/failure";
		}
	}
	
	@RequestMapping("/registeroneuser.act")
	@ResponseBody
	public Response_Result toRegisterUser(String user,Model model){
		OutputSimpleResult osr=new OutputSimpleResult();
		Response_Result resp = new Response_Result();
		if(user!=""){
			RegisterUserInfoModel oneUser=JSON.JsonToObject(RegisterUserInfoModel.class, user);
			 osr = userRegister.saveUser4Register(oneUser);
		}else{
			resp.setCode(1);
			resp.setResult("toRegisterUser=====用户信息转换异常。");
		}
		if(osr!=null){
			resp.setCode(osr.code);
			resp.setResult(osr.result);
		}else{
			resp.setCode(1);
			resp.setResult("toRegisterUser=====saveUser4Register===返回结果为空。");
		}
		return resp;
	}
	
	@RequestMapping("/toregister.act")
	public String toRegister(Model model,String o,String i,String t,String e){
		try {
			String oo=o.replace(" ", "+");
			String ii=i.replace(" ", "+");
			String tt=t.replace(" ", "+");
			String ee=e.replace(" ", "+");
			
			String time=etuf.v1_0.common.EncryptHelper.DESDecrypt(tt);
			String orgname=etuf.v1_0.common.EncryptHelper.DESDecrypt(oo);
			String email=etuf.v1_0.common.EncryptHelper.DESDecrypt(ee);
			String orgid=etuf.v1_0.common.EncryptHelper.DESDecrypt(ii);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = df.parse(time);
			
			model.addAttribute("email", email);
			model.addAttribute("orgname", orgname);
			model.addAttribute("orgid", orgid);
			date.setTime(date.getTime()+2*60*60*1000);  //将传过来的时间加上两个小时，再用现在的时间对比
			Date date2=new Date();
			if(date2.getTime()<date.getTime()){
				
				return "/userRegister/register";
			}
				//String date2Str = DateUtil.date2Str(date,DateUtil.DefaultDateTimeFormat);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		model.addAttribute("result", "注册链接时效性已过，请重新注册！");
		return "/public/failure";  //跳到错误页面提示    时间超时
		
	}
	
	
	/**
	 * 跳转中国银行注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/b.act")
	public String BOCRegister(Model model){
		return "/userRegister/bcRegister";
	}
	
	
	/**
	 * 跳转散客注册页
	 * @return
	 */
	@RequestMapping("/indvRegister.act")
	public String indvRegister(){
		
		return "/userRegister/individualRegister";
	}
	
	public static void main(String[] args) {
		String sr=etuf.v1_0.common.EncryptHelper.DESEncrypt("caozhenlkong@viptrip365.com");
		String sfr=etuf.v1_0.common.EncryptHelper.DESDecrypt(sr);
		System.out.println(sfr);
	}
}
