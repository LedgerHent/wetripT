package com.viptrip.register.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.register.entity.EnterpriseMailSuffix;
import com.viptrip.register.service.EmailSuffixService;

import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.util.Sendemail;



@Controller
@RequestMapping("/send")
public class EmailSuffixAction extends BaseAction{
	
	private static final String emailSuffix ="userRegister/emailRegister";
	
	@RequestMapping("/email.act")
	@ResponseBody
	public void queryForEmail(String emailsuffix,String email){
		EmailSuffixService emailService = ApplicationContextHelper.getInstance().getBean(EmailSuffixService.class);
		EnterpriseMailSuffix ems=new EnterpriseMailSuffix();
		ems = emailService.queryForMail(emailsuffix);
		TAcUser isemail=emailService.isExistEmail(email);
		PrintWriter pw;
		if(null==isemail){
	if(!"".equals(ems)&&null!=ems){
			try {
				String subject ="用户注册";
				Long orgid=ems.getSubcompanyid();//分公司id
				String orgname=ems.getSubcompanyname();//分公司名称
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String time=dateFormat.format(now);
				String str1=etuf.v1_0.common.EncryptHelper.DESEncrypt(orgid+"");
				String str2=etuf.v1_0.common.EncryptHelper.DESEncrypt(orgname);
				String str3=etuf.v1_0.common.EncryptHelper.DESEncrypt(time);
				String str4=etuf.v1_0.common.EncryptHelper.DESEncrypt(email);
				String path = getReq().getContextPath();
				String basePath = getReq().getScheme()+"://"+getReq().getServerName()+":"+getReq().getServerPort()+path+"/";
				String url = basePath
						+ "register/toregister.act?i=" + str1 + "&o="
						+ str2 + "&t=" + str3 + "&e=" + str4;
				String body=
						"<div>" +
						"  <tr> <div style='word-wrap:break-word;'" + 
						"    <td> " + 
						"    <p>尊敬的"+orgname+"用户: </p> " +
								" <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好! </p> " + 
								" <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您正在注册凯撒景鸿（北京）商务旅游有限责任公司的企业会员用户，您的所属公司为"+orgname+"。请确认上述信息的正确性，如果不是您本人操作，请忽略该邮件。如果是您本人操作，请务必仔细阅读如下申明：</p> " +
								" <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【免责申明】 </p> "+
								" <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您同意以上申明内容，请点击<a href=\""+url+ "\">这里</a>继续完成注册步骤，注册链接有效时长为2小时，请尽快完成注册。</p> "+
								" <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;若无法点选上面的链接，请复制下面的网址到您的浏览器中，并继续进行注册。注册网址："+url+" </p> " +
										"</td> </br>" + 
						"  </div></tr></br></br>" + 
						"  </div> "
						;
				Sendemail.sendHTML(email,subject,body);
				try {
					pw = resp.getWriter();
					pw.write("true");
					pw.flush();
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				pw = resp.getWriter();
				pw.write("false");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}else{
			try {
				pw = resp.getWriter();
				pw.write("exist");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	@RequestMapping("/eemail.act")
	public String getPage(){
		return emailSuffix;
	}
	
}
