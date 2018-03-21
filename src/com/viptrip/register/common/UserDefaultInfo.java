package com.viptrip.register.common;

import java.util.Date;
import java.util.Random;

import com.viptrip.base.common.MyEnum;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TAcUserrole;
import etuf.v1_0.common.Common;

/**
 * 设置用户的默认信息
 * @author Administrator
 *
 */
public class UserDefaultInfo {

	/**
	 * 用户默认信息
	 * @param user
	 * @return
	 */
	public static TAcUser saveDefaultInfo(TAcUser user){
		user.setSex(MyEnum.Sex.保密.toString());//保密
		user.setUsertype("0");//普通用户
		user.setCreateTime(new Date());
		user.setCreateBy("系统默认");
		user.setStatus("0");//初始状态
		user.setPwdWrongCount(0l);//密码连续 错误次数
		user.setAllowlogin("0");//默认允许登录
		user.setIsbiller("0");//默认不是账单接收人
		user.setBalanceRemind("0");//预付款提醒方式
		return user;
	}
	
	/**
	 * 用户角色信息
	 * @param user
	 * @param roleId 角色ID
	 * @return
	 */
	public static TAcUserrole saveUserRole(Long userId,Long roleId){
		if(roleId==null)
			roleId=67l;//默认设置为67，普通员工
		TAcUserrole urole = new TAcUserrole();
		urole.setUserid(userId);
		urole.setRoleid(roleId);
		return urole;
	}
	/**
	 * 用户默认角色信息 - 67，普通员工
	 * @param user
	 * @return
	 */
	public static TAcUserrole saveUserRole(Long userId){
		TAcUserrole urole = saveUserRole(userId, null);
		return urole;
	}
	
	/**
	 * 生成用户密码，默认为123456，有身份证为身份证后6位，或指定为随机6位整数。
	 * @param idCard  身份证号
	 * @param isRandom 是否是随机密码
	 * @return
	 */
	public static String createPwd(String idCard,boolean isRandom){
		String pwd = "";
		if(isRandom){
			pwd = createRandom();//随机6位整数。
		}else{
			if (!Common.IsNullOrEmpty(idCard)){
				//身份证后6位
				pwd = idCard.substring(idCard.length()-6,idCard.length());//如有身份证，加密密码为身份证后6位
			}else{
				//123456
				pwd = "123456";
			}
		}
		
		return pwd;
	}
	
	/**
	 * 生成6位随机数
	 * @return
	 */
	 public static String createRandom() {
		Random random = new Random();
		
		String result = "";

		for (int i = 0; i < 6; i++) {

			result += random.nextInt(10);

		}
		return result;
	}
}
