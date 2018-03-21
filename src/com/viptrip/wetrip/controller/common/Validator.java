package com.viptrip.wetrip.controller.common;

import com.viptrip.util.RegUtil;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * 数据合法性公用类
 * @author lxd
 *
 */
public class Validator {
	
	/**
	 * 手机号以及验证码合法性检查
	 * @param mc
	 * @param isCheckCodeNeeded
	 * @return
	 */
	public static OutputSimpleResult MobileAndCheckCode(MobileAndCheckCode mc,boolean isCheckCodeNeeded){
		OutputSimpleResult osr=new OutputSimpleResult();
		
		if (mc != null) {
			if (!Common.IsNullOrEmpty(mc.mobile)) {
				if (RegUtil.isValidMobile(mc.mobile)) {
					if(isCheckCodeNeeded){
						if (!Common.IsNullOrEmpty(mc.checkCode)){
							osr.code = Constant.Code_Succeed;
						}else {
							osr.result = "验证码不能为空！";
						}
					}else{
						osr.code = Constant.Code_Succeed;
					}
				} else {
					osr.result = "无效的手机号码，请确认后重试。";
				}
			} else {
				osr.result = "用户手机号不能为空！";
			}
		} else {
			osr.result = "请求数据为null，请确认后重试。";
		}
		
		return osr;
	}
}
