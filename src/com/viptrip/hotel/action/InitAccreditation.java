package com.viptrip.hotel.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.hotel.HotelServer;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.ConfigUtil;

import etuf.v1_0.common.Common;
import etuf.v1_0.model.base.Config;

@Controller
@RequestMapping("/initAcrdtion")
public class InitAccreditation extends BaseAction{
	
	private static Logger logger = LoggerFactory.getLogger(HotelServer.class);
	private static final String HOTEL_PREFIX = "hotel";//配置文件属性前缀
	private static final String toSuccess = "public/testH5Success";//跳转页面
	
	/**
	 * 首次认证接口，返回加密连接
	 */
	@RequestMapping("/initAcrd")
	public String initAcrd(){
		//获取配置信息
		Config c= null;
		try {
			c = ConfigUtil.config(HOTEL_PREFIX);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		
		//连接地址
		String url = req.getRequestURL().toString();
		
		//加密连接参数
		String desUrlParm = req.getQueryString();
		String urlParm="";
		if(!Common.IsNullOrEmpty(desUrlParm)){
			try {
				urlParm = etuf.v1_0.common.EncryptHelper.DESDecrypt(
						URLDecoder.decode(desUrlParm,"utf-8"), c.getDesKey(),c.getDesIV(),c.getCharset());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			urlParm = "参数为空。";
		}
		resp.setHeader("Access-Control-Allow-Origin", "*");//可以跨域访问设置
		
		addAttr("desUrlParm", desUrlParm);
		addAttr("urlParm", urlParm);
		addAttr("url", url+"?"+desUrlParm);
		return toSuccess;
//		return url+"?"+desUrlParm;
	}
}
