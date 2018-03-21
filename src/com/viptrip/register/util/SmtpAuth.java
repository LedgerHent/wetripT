/**
* <p>文件名: Sendemail.java</p>
* <p>描述：smtp服务器的密码验证</p>
* <p>版权: Copyright (c) 2013 Viptrip Co. Ltd.</p>
* <p>公司: Viptrip Beijing Office</p>
* <p>All right reserved.</p>
* @创建时间： 2010-12-23 下午04:29:03 
* 
* @版本： V1.0
* <p>类修改者		 修改日期			修改说明   </p>   
* 
*/
package com.viptrip.register.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * @类描述: smtp服务器的密码验证 ---- 一期直接移植代码
 * 
 */
public class SmtpAuth extends Authenticator {
	private String user, password;

	public void setUserinfo(String getuser, String getpassword) {
		user = getuser;
		password = getpassword;
	}

	public  PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
}
