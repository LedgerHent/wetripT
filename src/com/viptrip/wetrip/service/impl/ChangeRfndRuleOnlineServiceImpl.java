package com.viptrip.wetrip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.util.DateUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.model.Request_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.model.Response_QueChangeRfndRuleOnline;
import com.viptrip.wetrip.model.Response_QueFee4ChangeRfndRule;
import com.viptrip.wetrip.service.ChangeRfndRuleOnlineService;
import com.viptrip.wetrip.service.ServiceFee;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
@Service
@Transactional
public class ChangeRfndRuleOnlineServiceImpl implements
		ChangeRfndRuleOnlineService {
	private static Logger logger = LoggerFactory.getLogger(FlightService.class);
	@Resource
	private ServiceFee serviceFee;
	@Resource
	private ComDao comDao;
	public OutputResult<Response_QueChangeRfndRuleOnline, String> queStatus4ChangeRfndRule(
			Request_QueFee4ChangeRfndRule req){
		Request_QueChangeRfndRuleOnline reqRule = new Request_QueChangeRfndRuleOnline();
		reqRule.air2char=req.air2char;
		reqRule.cangwei=req.cangwei;
		reqRule.type=req.type;
		OutputResult<Response_QueChangeRfndRuleOnline, String> or = queStatus4ChangeRfndRule(reqRule);
		return or;
	}
	/**
	 * 是否可在线退票、改期状态查询
	 * @param req 请求参数model
	 * @return
	 */
	@Override
	public OutputResult<Response_QueChangeRfndRuleOnline, String> queStatus4ChangeRfndRule(
			Request_QueChangeRfndRuleOnline req) {
		OutputResult<Response_QueChangeRfndRuleOnline, String> or = new OutputResult<Response_QueChangeRfndRuleOnline, String>();
		Response_QueChangeRfndRuleOnline resp = new Response_QueChangeRfndRuleOnline();
		String sql="select e.endorseid from t_endorse e where e.isdel=0 and e.air2char=? and e.cangwei=? " +
				"and e.endorseid in(select c.t_endorde_id from CHANGE_REFUND_RULE c where c.type=? " +
				"group by c.t_endorde_id) and e.effectivedate<=to_date(?,'yyyy-MM-dd') " +
				"and e.expirydate>=to_date(?,'yyyy-MM-dd')";
		try {
			or.code=Constant.Code_Succeed;
			resp.returnStatic=1;//不允许退票或改期
			@SuppressWarnings("static-access")
			String nowTime = comDao.getDateTime("yyyy-MM-dd");
			List<String> parm=new ArrayList<String>();
			parm.add(req.air2char);
			parm.add(req.cangwei);
			parm.add(req.type);
			parm.add(nowTime);
			parm.add(nowTime);
			List returnRule = comDao.queryBySQL(sql,parm.toArray());
			if(null!=returnRule){
				if(returnRule.size()==1){
					resp.returnStatic=0;
					or.setResultObj(resp);
					or.result=returnRule.get(0).toString();//退改规则id保存在result中。
				}else{
					if(returnRule.size()>1){
						or.result="找到多条匹配信息，请联系客服进行处理。";
					}else{
						or.result="未找到相关规则匹配信息，不允许在线修改。";
					}
					
					or.setResultObj(resp);
				}
			}else or.result="未找到相关规则匹配信息，请联系客服进行处理。";
			if(logger.isDebugEnabled()){
				logger.info("当前时间："+nowTime+",参数："+parm.toArray().toString());
			}
		} catch (Exception e) {
			logger.error("错误代码：20171110，queStatus4ChangeRfndRule==========="+e.getMessage());
			or.code=Constant.Code_Failed;
		}
		
		
		return or;
	}
	
	/**
	 * 在线退改的费用查询
	 * @param req 请求参数model
	 * @return
	 */
	@Override
	public OutputResult<Response_QueFee4ChangeRfndRule, String> queFee(
			String result, Request_QueFee4ChangeRfndRule req) {
		OutputResult<Response_QueFee4ChangeRfndRule, String> or = new OutputResult<Response_QueFee4ChangeRfndRule, String>();
		Response_QueFee4ChangeRfndRule resp = new Response_QueFee4ChangeRfndRule();
		//获取当前系统时间和起飞时间的差值，单位分钟。
		Long delta_T=DateUtil.getDiffMinute(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") ,req.date, "yyyy-MM-dd HH:mm:ss");
		Double returnPrice = 0d;
		//筛选符合折扣范围的规则内容,折扣为-1的不允许在线退改。
		String sql="select r.flowid,c.offset_minute,c.amount_limit,"//c.ruleid,r.ruleid,c.t_endorde_id,
					+" r.minute_lower_limit,r.minute_upper_limit,r.free_times,r.cost_percent"
					+" from CHANGE_REFUND_RULE c,CHANG_REFUND_RULE_DETAIL r"
					+" where c.ruleid=r.ruleid and c.type=? and c.state=1 and r.state=1 "
					+" and c.t_endorde_id=? "
					+" and c.discount_lower_limit<? and c.discount_upper_limit>=? and c.discount_upper_limit<>-1 "
					+" order by r.minute_lower_limit desc";
		try {
		    or.code=Constant.Code_Succeed;
			List<Object> parm=new ArrayList<Object>();  
			parm.add(req.type);
			parm.add(Long.valueOf(result));
			parm.add(req.discount);
			parm.add(req.discount);
			List returnRule = comDao.queryBySQL(sql,parm.toArray());
			if(req.type.equals("1")) req.changeNum = 1;//退票
			if(null!=returnRule && returnRule.size()>0){int i=1;
				for (Object[] obj : (List<Object[]>)returnRule) {
					//比较时间范围
					if(trueTime(obj[1].toString(),obj[3].toString(),obj[4].toString(),delta_T,i)){
						//判断是否免费改期次数内
						if(req.changeNum>Integer.valueOf(obj[5].toString())){
							//退改价格=最低收费>(舱位价格*费用%100)? 最低收费: (舱位价格*费用%100)
							returnPrice = Double.valueOf(req.price*Integer.valueOf(obj[6].toString())/100);//舱位价格*费用%100
							returnPrice=Double.valueOf(obj[2].toString())>returnPrice?
											Double.valueOf(obj[2].toString()):returnPrice;
						}else{
							returnPrice=0d;
						}
					}i++;
				}
				resp.price=returnPrice;
				resp.servicePrice=getOrgServcePrice(req.userId,req.type);
				or.setResultObj(resp);
				
				
			}else{
				or.result="未找到相关规则匹配信息，请联系客服进行处理。";
				or.code=Constant.Code_Failed;
			}
			
			if(logger.isDebugEnabled()){
				logger.info("当前时间："+new Date()+",参数："+parm.toArray().toString());
			}
		} catch (Exception e) {
		    or.code=Constant.Code_Failed;
			logger.error("错误代码：20171120，queFee===查询在线退改费用方法========"+e.getMessage());
		}
		return or;
	}
	
	/**
	 * 比较时间范围
	 * @param offsetMinute
	 * @param minuteLowerLimit
	 * @param minuteUpperLimit 
	 * @param delta_T
	 * @param index 
	 * @return
	 */
	private boolean trueTime(String offsetMinute, String minuteLowerLimit, String minuteUpperLimit, Long delta_T, int index) {
		boolean flag = false;
		Integer offsetMinuteI = Integer.valueOf(offsetMinute);
		Integer lowerI = Integer.valueOf(minuteLowerLimit);
		Integer upperI = Integer.valueOf(minuteUpperLimit);
		if(index==1){//第一次循环，即当前是最远时间段的判断，例如48小时(含)以前
			flag = delta_T>=upperI*60+offsetMinuteI?true:false;
		}else if(upperI*60==-1){//当前是最后时间段的判断，例如起飞后
			flag = delta_T<lowerI*60+offsetMinuteI?true:false;
			flag = flag?!(delta_T>=lowerI*60):flag;//判断是否在误差范围内
		}else{
			flag = delta_T<lowerI*60+offsetMinuteI&&delta_T>=upperI*60+offsetMinuteI?!(delta_T>=lowerI*60):false;
		}
		
		return flag;
	}
	
	/**
	 * 方法为系统统一方法，直接保存[TotalPrice，TotalServiceFee]到数据库表。
	 * 获取企业的服务费-国内机票
	 * @param type 
	 * @param orgId
	 * @return
	 */
	private Double getOrgServcePrice(Long userId, String type) {
		Double serviceFee=0d;
		TAcUser user = comDao.queryForEntity(Long.parseLong(userId.toString()),TAcUser.class);
		if(user != null){
			TAcOrg depatement = comDao.queryForEntity(user.getOrgid(),TAcOrg.class);
			if(depatement != null){
				TAcOrg org = comDao.queryForEntity(Long.parseLong(depatement.getCompany()),TAcOrg.class);
				// 判断 是否收取服务费，否（1），是（2）
				if (org.getServiceStatus() != null&& org.getServiceStatus().equals("2")) {
					boolean night = DateUtil.isNight(DateUtil.format(new Date(), "HH:mm"));// 是否为夜间
					// //计算服务费的方式，按机票收取
					// 是否改期退款 0-否 1-是 退票费只记录在出票的那张票上
					if ("1".equals(type)) {//1：退票，2：改期
						// 收取退票服务费，定额
						serviceFee=org.getRefundServiceFee() == null ? 0d : org.getRefundServiceFee();
						// 收取夜间服务费
						// 收取夜间服务费方式，定额（1）百分比（2），20点至8点为夜间服务时间，此期间内的出票、改期、退票均收取相等的服务费（且是在原有的各种服务费基础上累加）。计算百分比也按票面价计算。
					}
					if ("2".equals(type)) {
						// 收取退票服务费，定额
						serviceFee=org.getChangeServiceFee() == null ? 0d : org.getChangeServiceFee();
					}
					if (night) {
						serviceFee+= org.getNightFee() == null ? 0d : org.getNightFee();
					}
				}
			}
		}
		return serviceFee;
	}

}
