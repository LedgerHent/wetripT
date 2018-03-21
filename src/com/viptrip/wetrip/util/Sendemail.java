/**
* <p>文件名: Sendemail.java</p>
* <p>描述：发送邮件</p>
* <p>版权: Copyright (c) 2013 Viptrip Co. Ltd.</p>
* <p>公司: Viptrip Beijing Office</p>
* <p>All right reserved.</p>
* @创建时间： 2010-12-23 下午04:29:03 
* 
* @版本： V1.0
* <p>类修改者		 修改日期			修改说明   </p>   
* 
*/
package com.viptrip.wetrip.util;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.URLName;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类描述: 邮件发送 ---- 一期直接移植代码
 * 
 */
public class Sendemail {
	
	private static Logger logger = LoggerFactory.getLogger(Sendemail.class);
	
	/**
	 * 用默认邮箱发送html格式的email
	 * @param toUser 收件人，多个收件人以英文逗号分隔即可
	 * @param subject 主题
	 * @param body 内容
	 */
	public static void sendHTML(String toUser, // 收信人 
			String subject, // 主题 
			String body // 内容 
			) {
		sendHTML(toUser,"","",subject,body);
	}
	
	/**
	 * 用默认邮箱发送html格式的email
	 * @param to 收件人，多个收件人以英文逗号分隔即可
	 * @param cc 抄送人，多个抄送人以英文逗号分隔即可
	 * @param bcc 暗送人，多个暗送人以英文逗号分隔即可
	 * @param subject 主题
	 * @param body 内容
	 */
	public static void sendHTML(String to, // 收信人 
			String cc, // 抄送人 
			String bcc, // 暗送人 
			String subject, // 主题 
			String body // 内容 
			) {
		try{
		String host = PropertiesUtil.getResourcesProperty("email_host"); // smtp服务器
		String from=PropertiesUtil.getResourcesProperty("email_user"); // 用户名
		String fromuser=PropertiesUtil.getResourcesProperty("email_pwd"); // 密码
		String frompass=PropertiesUtil.getResourcesProperty("email_from"); // 发件人地址
		if(StringUtils.isEmpty(host) || StringUtils.isEmpty(from) 
				|| StringUtils.isEmpty(fromuser)||StringUtils.isEmpty(frompass)){
			host = "smtp.163.com"; 
			from="viptrip365service@163.com";
			fromuser="viptrip365service@163.com";
			frompass="hongjing@vip";
		}
		sendHTML(host,from, fromuser, frompass, to, cc, bcc, subject, body);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("******发送邮件发生异常，异常信息为:******", e.getCause());
		}
	}
	
	/**
	 * 李荣春 2010年11月28日 15:58:00 增加，发送html内容的邮件
	 * @param smtp
	 * @param from
	 * @param fromuser
	 * @param frompass
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param body
	 * @throws java.lang.Exception
	 */
	public static void sendHTML(String smtp, // SMTP主机地址 
		String from, // 发信人邮件地址 
		String fromuser, //发信人登录smtp的用户名
		String frompass, //登录smtp的密码
		String to, // 收信人 
		String cc, // 抄送人 
		String bcc, // 暗送人 
		String subject, // 主题 
		String body // 内容 
		) throws java.lang.Exception {
		// 变量声明
		java.util.Properties props; // 系统属性
		javax.mail.Session mailSession; // 邮件会话对象
		javax.mail.internet.MimeMessage mimeMsg; // MIME邮件对象

		// 设置系统属性
		props = java.lang.System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", smtp); // 设置SMTP主机
		props.put("mail.smtp.auth", "true");       
		props.put("mail.transport.protocol", "smtp");   

		// 必须通过一个Authenticator的子类SmtpAuth的对象auth来进行用户名和密码验证       
		SmtpAuth auth = new SmtpAuth();       
		auth.setUserinfo(fromuser, frompass);      
		
		// 获得邮件会话对象
		mailSession = Session.getInstance(props, auth);
		mailSession.setPasswordAuthentication(new URLName(smtp), auth.getPasswordAuthentication());    

		// 创建MIME邮件对象
		mimeMsg = new javax.mail.internet.MimeMessage(mailSession);

		// 设置发信人
		mimeMsg.setFrom(new javax.mail.internet.InternetAddress(from));
		
		// 设置收信人
		if (to != null && !"".equals(to)) {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.TO,
					javax.mail.internet.InternetAddress.parse(to));
		}

		// 设置抄送人
		if (cc != null && !"".equals(cc)) {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC,
					javax.mail.internet.InternetAddress.parse(cc));
		}

		// 设置暗送人
		if (bcc != null && !"".equals(bcc)) {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.BCC,
					javax.mail.internet.InternetAddress.parse(bcc));
		}

		// 设置邮件主题
		mimeMsg.setSubject(subject, "UTF-8");

		// 设置邮件内容
		Multipart mp = new MimeMultipart("related");// related意味着可以发送html格式的邮件    
        BodyPart bodyPart = new MimeBodyPart();// 正文    
        bodyPart.setDataHandler(new DataHandler(body, "text/html;charset=UTF-8"));// 网页格式    
        mp.addBodyPart(bodyPart);
        mimeMsg.setContent(mp);// 设置邮件内容对象
        
		// 发送邮件
		javax.mail.Transport.send(mimeMsg);
	} 
	
	
	public static void main(String[] args) {
		String host = "smtp.163.com"; // smtp服务器
		String user = "yangabin1991@163.com"; // 用户名
		String pwd = "yangabin"; // 密码
		String from = "yangabin1991@163.com"; // 发件人地址
		String to = ",,371355163@qq.com"; // 收件人地址
		String subject = "测试html邮件"; // 邮件标题
		
		String body = 
			"  <div style=\"width:580px; border:1px solid #d0d0d0; padding:10px;\">\n" +
			"  <table border=\"2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"emailTemplate\">\n" + 
			"  <tr>\n" + 
			"    <td align=\"center\"><img src=\"http://e.viptrip365.com/images/logo/logoblue.jpg\" /></td>\n" + 
			"    <td colspan=\"2\" valign=\"top\">\n" + 
			"    <p>尊敬的<font color=\"red\">XXX</font>您好，贵公司XXX预订的航班信息如下：.........</p></td>\n" + 
			"  </tr>\n" + 
			"  <tr>\n" + 
			"    <td>乘机人姓名</td>\n" + 
			"    <td>乘机人证件号</td>\n" + 
			"    <td>总票价（含税）</td>\n" + 
			"  </tr>\n" + 
			"  <tr>\n" + 
			"    <td>李四</td>\n" + 
			"    <td>32021119870408657X</td>\n" + 
			"    <td>￥276.00元</td>\n" + 
			"  </tr>\n" + 
			"</table>\n" + 
			"\n" + 
			"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"emailTemplate\">\n" + 
			"  <tr>\n" + 
			"    <td width=\"20%\">航程1</td>\n" + 
			"    <td></td>\n" + 
			"  </tr>\n" + 
			"  <tr>\n" + 
			"    <td>出票时限</td>\n" + 
			"    <td><p>此行程航空公司没有显示最晚出票时限....</p></td>\n" + 
			"  </tr>\n" + 
			"  <tr>\n" + 
			"    <td>改签政策</td>\n" + 
			"    <td><p>改签规定：无</p></td>\n" + 
			"  </tr>\n" + 
			"</table>\n" + 
			"\n" + 
			"\n" + 
			"  </div>";
		
		if(to==null || (to!=null && !to.contains("@"))){
			//system.out.println("邮箱格式错误");
			return ;
		}
		try {
			Sendemail.sendHTML(host, from, user, pwd, to, null, null, subject, body);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
