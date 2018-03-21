package com.viptrip.common.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.common.model.IntegrationModel;
import com.viptrip.common.model.Request_GetIntegral;
import com.viptrip.common.model.Request_GetInterest;
import com.viptrip.common.model.Request_IntegralConsumption;
import com.viptrip.common.model.Request_IntegralRefund;
import com.viptrip.common.model.Request_InterestRefund;
import com.viptrip.common.model.Response_GetInterest;
import com.viptrip.common.service.IntegralAndInterestService;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.Orderintegral;
import com.viptrip.wetrip.entity.OrgInterestDetail;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcOrgInterest;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.base.common.MyEnum;

@Service
@Transactional
public class IntegralAndInterestServiceImpl  implements IntegralAndInterestService {
	
	@Resource
	private ComDao comDao;
	/**
	 * 根据userid查询该用户的总积分
	 * @param subscribeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int gettotalIntegral(Request_GetIntegral para){
		int totalIntegrals=0;
		
		List<Object[]> qList = (List<Object[]>) comDao.queryBySQL("select sum(INTEGRAL),USERID from ORDERINTEGRAL where USERID="+para.userId+" group by USERID",null);
		if(null!=qList && qList.size()>0){
			totalIntegrals=Integer.valueOf(qList.get(0)[0].toString());
		}
		return totalIntegrals;
	}
	@Override
	public String integralConsumption(Request_IntegralConsumption para) {
		String status="0";
		IntegrationModel integrationModel=new IntegrationModel();
		integrationModel.userid=Long.valueOf(para.userId);
		//1：机票订单成交发放积分   2：酒店订单成交发放积分   3：机加酒订单成交发放积分    4：签到发放积分   5：过期直减积分    6：订单审核未通过或者取消或删除发放积分   -1 ：机票提交订单直减积分   -2：酒店提交订单直减积分  -3：机加酒订单成交直减积分
		if(1==para.businessType || 2==para.businessType){//0-不限|1-国内机票|2-国际机票|3-国内酒店|4-国际酒店|5-火车票|6-签证|7-租车
			integrationModel.source=MyEnum.IntegralType.机票提交订单直减积分;
		}else if(3==para.businessType || 4==para.businessType){
			integrationModel.source=MyEnum.IntegralType.酒店提交订单直减积分;
		}
		TAcUser user=comDao.queryForEntity(para.userId, TAcUser.class);
		if(user==null){
			status="请确认用户编号";
			return status;
		}
		TAcOrg org=comDao.queryForEntity(user.getOrgid(), TAcOrg.class);
		integrationModel.companyId=Long.valueOf(org.getCompany());
		integrationModel.businesstype=para.businessType;
		integrationModel.orderNo=para.orderNo;
		integrationModel.integration=-para.amount;
		status=useIntegration(integrationModel);
		return status;
	}
	
	/**
	 * @author hx(暂时只使用机票，机加酒，酒店提交订单,)
	 * @param integration(消费积分为负数 )
	 * @param source 0：积分完善   1：机票订单成交发放积分   2：酒店订单成交发放积分   3：机加酒订单成交发放积分    4：签到发放积分   5：过期直减积分    6：订单审核未通过或者取消或删除发放积分   -1 ：机票提交订单直减积分   -2：酒店提交订单直减积分  -3：机加酒订单成交直减积分
	 */
	public String useIntegration(IntegrationModel integrationModel){
		String flag="0";//表示积分消费成功
		String hql=" from Orderintegral t where t.usestate in (1,2) and t.userid="+integrationModel.userid+" order by t.issuedate asc ";
		List<Orderintegral> orderintegrals=comDao.queryForList(hql);
		
		if(integrationModel.source==MyEnum.IntegralType.机票提交订单直减积分 || integrationModel.source==MyEnum.IntegralType.酒店提交订单直减积分 ||integrationModel.source==MyEnum.IntegralType.机加酒订单成交直减积分){
			if(orderintegrals!=null && orderintegrals.size()>0){
				String AnyUnUsed="";//未用完的
				String used="";//已用完的
				
				long unUsedIntegra=0;
				
				long consumecount=-integrationModel.integration;
				
				for (Orderintegral orderintegral:orderintegrals) {
					if(consumecount>0){
						long count=orderintegral.getIntegral();
						if(orderintegral.getUsestate()==2){//表示该积分已使用过，查询剩余积分
							count=orderintegral.getConsumeId();
						}
						if(count==consumecount){
							if(StringUtil.isNotEmpty(used)){
								used+=",";
							}
							used+=orderintegral.getId();
							consumecount=0L;
							break;
						}else if(count>consumecount){
							if(StringUtil.isNotEmpty(AnyUnUsed)){
								AnyUnUsed+=",";
							}
							AnyUnUsed+=orderintegral.getId();
							
							unUsedIntegra=count-consumecount;
							
							consumecount=0L;
							
							
							break;
						}else if(count<consumecount){
							if(StringUtil.isNotEmpty(used)){
								used+=",";
							}
							used+=orderintegral.getId();
							
							consumecount=consumecount-count;
						}
					}
				}
				if(consumecount<=0){
					integrationModel.useState=5;
					if(StringUtil.isNotEmpty(AnyUnUsed)){
						integrationModel.consumeId=Long.valueOf(AnyUnUsed);
					}else{
						integrationModel.consumeId=Long.valueOf(used.split(",")[used.split(",").length-1]);
					}
					saveOrderintegral(integrationModel);
					
					if(StringUtil.isNotEmpty(AnyUnUsed)){//未用完的
						comDao.executeUpdate("update Orderintegral set usestate=2,consumeId="+unUsedIntegra+"  where id in("+AnyUnUsed+") ");
					}
					if(StringUtil.isNotEmpty(used)){//已用完的
						comDao.executeUpdate("update Orderintegral set usestate=3,consumeId="+0+" where id in("+used+")");
					}
					
				}else{
					flag="积分不够扣除本次消费";
				}
				
			}else{
				flag="暂无积分";
			}//积分完善(0),机票订单成交发放积分(1),酒店订单成交发放积分(2),机加酒订单成交发放积分(3),签到发放积分(4),过期直减积分(5),订单审核未通过或者取消或删除发放积分(6),机票提交订单直减积分(-1),酒店提交订单直减积分(-2),机加酒订单成交直减积分(-3);
		}else if(integrationModel.source==MyEnum.IntegralType.积分完善 || integrationModel.source==MyEnum.IntegralType.机票订单成交发放积分 
				|| integrationModel.source==MyEnum.IntegralType.酒店订单成交发放积分
				||integrationModel.source==MyEnum.IntegralType.机加酒订单成交发放积分 ||integrationModel.source==MyEnum.IntegralType.签到发放积分 
				||integrationModel.source==MyEnum.IntegralType.订单审核未通过或者取消或删除发放积分){
			integrationModel.useState=1;
			saveOrderintegral(integrationModel);
		}
		
		return flag;
	}
	public String saveOrderintegral(IntegrationModel integrationModel){
		String status="0";
		try {
			Orderintegral orderintegral=new Orderintegral();
			orderintegral.setOrderno(integrationModel.orderNo);
			orderintegral.setBusinesstype(integrationModel.businesstype);
			orderintegral.setOrgid(integrationModel.companyId);
			orderintegral.setUserid(integrationModel.userid);
			orderintegral.setIntegral(integrationModel.integration);
			orderintegral.setSource(integrationModel.source.getValue());
			orderintegral.setOrgname(integrationModel.orgmane);
			orderintegral.setConsumeId(integrationModel.consumeId);
			
			Date date=new Date();
			orderintegral.setIssuedate(new Date());
			if(integrationModel.source==MyEnum.IntegralType.积分完善 || integrationModel.source==MyEnum.IntegralType.机票订单成交发放积分 
					|| integrationModel.source==MyEnum.IntegralType.酒店订单成交发放积分
					||integrationModel.source==MyEnum.IntegralType.机加酒订单成交发放积分 ||integrationModel.source==MyEnum.IntegralType.签到发放积分 
					||integrationModel.source==MyEnum.IntegralType.订单审核未通过或者取消或删除发放积分){//为发放积分，需要有过期截止日期
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String dateStr=DateUtil.getLaterNyearDate(sdf.format(date),Const.INTEGRATION_OVERDUE);
				orderintegral.setExpirydate(DateUtil.str2Date(dateStr, "yyyy-MM-dd"));
			}
			
			
			orderintegral.setUsestate(integrationModel.useState);
			
			TAcOrg tacorg=(TAcOrg)comDao.queryForEntity(integrationModel.companyId,TAcOrg.class);
			if(tacorg!=null){
				orderintegral.setOrgname(tacorg.getOrgname());
			}
			
			comDao.executeSave(orderintegral);
		} catch (Exception e) {
			e.printStackTrace();
			status="发放积分异常,请联系管理员！";
		}
		return status;
	}
	/**
	 * 返还积分方法
	 * @param soucce 0：积分完善   1：机票订单成交发放积分   2：酒店订单成交发放积分   3：机加酒订单成交发放积分    4：签到发放积分   5：过期直减积分    6：订单审核未通过或者取消或删除发放积分 
		                                              -1 ：机票提交订单直减积分   -2：酒店提交订单直减积分  -3：机加酒订单成交直减积分
	 * @param orderNo 订单号
	 * @return
	 */
	@Override
	public String backIntegration(Request_IntegralRefund para) {

		String status="0";
		String hql=" from Orderintegral where source=? and orderno='"+para.orderNo+"'";
		List<Orderintegral> orderintegrals=null;
		//0-不限|1-国内机票|2-国际机票|3-国内酒店|4-国际酒店|5-火车票|6-签证|7-租车
		Integer [] param=new Integer[1];
		if(para.businessType==1 || para.businessType==2){
			param[0]=MyEnum.IntegralType.机票提交订单直减积分.getValue();
			orderintegrals=comDao.queryForList(hql,param);
		}else if(para.businessType==3 || para.businessType==4){
			param[0]=MyEnum.IntegralType.酒店提交订单直减积分.getValue();
			orderintegrals=comDao.queryForList(hql,param);
		}
		if(orderintegrals==null || orderintegrals.size()==0){
			return "未查到该订单的消费积分详情！";
		}
		IntegrationModel integrationModel=new IntegrationModel();
		integrationModel.userid=orderintegrals.get(0).getUserid();
		integrationModel.orderNo=orderintegrals.get(0).getOrderno();
		integrationModel.businesstype=para.businessType;
		integrationModel.integration=-orderintegrals.get(0).getIntegral();
		integrationModel.source=MyEnum.IntegralType.订单审核未通过或者取消或删除发放积分;
		integrationModel.useState=1;
		integrationModel.companyId=orderintegrals.get(0).getOrgid();
		integrationModel.orgmane=orderintegrals.get(0).getOrgname();
		
		Orderintegral useOrderIntegral=(Orderintegral)comDao.queryForEntity(orderintegrals.get(0).getConsumeId(),Orderintegral.class);
		integrationModel.issuedate=useOrderIntegral.getIssuedate();
		status=saveOrderintegral(integrationModel);
		return status;
	
	}
	/**
	 * 利息消费
	 */
	@Override
	public String useInterest(Request_IntegralConsumption para) {
		String status="0";
		try {
			status=useOrRefundInterest(para.userId,para.orderNo,-para.amount,0);//type(0: 使用   1：返还)   interest(消费利息   为负数,返还为正)
			
		} catch (Exception e) {
			status="利息扣除失败";
		}
	    return status;
	}
	private String useOrRefundInterest(Long userId,String orderNo,long amount,Integer type) {
		String status="0";
		try {
			TAcUser user=comDao.queryForEntity(userId, TAcUser.class);
			if(user==null){
				status="请确认用户编号";
				return status;
			}
			TAcOrg org=comDao.queryForEntity(user.getOrgid(), TAcOrg.class);
			
			long interest=amount;
			
			String hql="from TAcOrgInterest  where orgid="+Long.valueOf(org.getCompany())+"";
			List<TAcOrgInterest> list = comDao.queryForList(hql);
			if(list==null || list.size()==0 || list.get(0).getInterest()<(0-interest)){
				status="利息不够！";
			}
			//修改利息表
			TAcOrgInterest tAcOrgInterest=list.get(0);
			tAcOrgInterest.setInterest(list.get(0).getInterest()+interest);
			comDao.executeSave(tAcOrgInterest);
			
			
			//利息明细表
			OrgInterestDetail orgInterestDetail=new OrgInterestDetail();
			orgInterestDetail.setOrgid(Long.valueOf(org.getCompany()));
			orgInterestDetail.setInterest(Double.valueOf(interest));
			orgInterestDetail.setOrderno(orderNo);
			orgInterestDetail.setCreattime(new Date());
			orgInterestDetail.setType(1);//type(0: 使用   1：返还)
			
			comDao.executeSave(orgInterestDetail);
		} catch (Exception e) {
			status="未知错误";
		}
		return status;
	}
	/**
	 * 利息查询
	 */
	@Override
	public double gettotalInterest(Request_GetInterest para) {
		return getTotalInterest(para.userId);
	}
	public double getTotalInterest(Long userId){
		double status=0;
		TAcUser user=comDao.queryForEntity(userId, TAcUser.class);
		if(user!=null){
			TAcOrg org=comDao.queryForEntity(user.getOrgid(), TAcOrg.class);
			String hql="from TAcOrgInterest  where orgid="+Long.valueOf(org.getCompany())+"";
			List<TAcOrgInterest> list = comDao.queryForList(hql);
			if(list!=null && list.size()>0){
				status=list.get(0).getInterest();
			}
		}
		return status;
	}
	/**
	 * 返还利息
	 */
	@Override
	public String refundInterest(Request_InterestRefund para) {
		String status="0";
		try {
			status=useOrRefundInterest(para.userId,para.orderNo,para.amount,1);//type(0: 使用   1：返还)   interest(消费利息   为负数,返还为正)
			
		} catch (Exception e) {
			status="返还利息失败,请联系相关人员";
		}
	    return status;
	}
}
