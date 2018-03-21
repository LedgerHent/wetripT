package com.viptrip.warning.service;

import com.dxpt.entity.Message;
import com.dxpt.service.MessageService;
import com.dxpt.service.impl.MessageServiceImpl;

import com.viptrip.util.StringUtil;
import com.viptrip.warning.entity.PayLimitation;
import com.viptrip.warning.entity.PayWarning;
import com.viptrip.warning.entity.PayWarningPerson;
import com.viptrip.warning.resource.MEnum;
import com.viptrip.warning.resource.MEnum.Otype;
import com.viptrip.warning.resource.MEnum.WarningMethod;
import com.viptrip.warning.resource.MEnum.WarningStatus;
import com.viptrip.warning.util.GlobalWarningUtil;
import com.viptrip.warning.vo.MSM;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.util.Sendemail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

@Service
@Transactional
public class WarningManager{
	final static Logger logger =  (Logger) LoggerFactory.getLogger(WarningManager.class);
	@Resource
	private ComDao dao;


	/**
	 * 自动修正预付款用户余额
	 */
	public void autoCorrectLeftLimitation(){
		//insert into pay_limitation select seq_pay_limitation.nextval,10268,2,7,1, b.rl-p.al,b.rl,sysdate,null from (select sum(money) rl from t_ac_org_balance where orgid=10268) b, (select left_limitation al from pay_limitation where id=(select max(id) from pay_limitation where orgid=10268)) p
		String hql = "select orgid from PayLimitation where type=1 group by orgid";
		List list = dao.queryForList(hql);
		if(null!=list && list.size()>0){
			for(Object r:list){
				System.out.println(r);
				hql = "select nvl(SUM(money),0) from TAcOrgBalance where orgid=" + r;
				Double money = (Double)dao.queryForObject(hql, null);
				hql = "from PayLimitation where id=(select max(id) from PayLimitation where orgid= " + r + ")";
				PayLimitation o = (PayLimitation)dao.queryForObject(hql, null);
				if(null!=money&&null!=o&&!money.equals(o.getLeftLimitation())){
					PayLimitation pl = new PayLimitation();
					pl.setAmount(money-o.getLeftLimitation());
					pl.setOrgid((Long)r);
					pl.setLeftLimitation(money);
					pl.setOptime(new Date());
					pl.setType(1);
					pl.setOtype(Otype.额度校正.getCode());
					pl.setState(1);
					dao.executeSave(pl);
				}
			}
		}
	}

	/**
	 * 初始化额度管理表
	 */
	public void initPayLimitationData(){
		//select * from t_ac_org org where parentid in(10,20) and orgid>0 and not exists ( select * from pay_limitation pl where pl.orgid=org.orgid )
		String hql = " from TAcOrg org where parentid in(10,20) and orgid>0 and not exists(select 1 from PayLimitation pl where pl.orgid=org.orgid)";
		List<TAcOrg> list = dao.queryForList(hql, new Object[]{});
		if(null!=list && list.size()>0){
			for(TAcOrg org:list){
				Integer payType = getEnterprisePayType(org.getOrgid());
				Double payLimitationFromDatabase = getPayLimitationFromDatabase(org.getOrgid());
				payLimitationFromDatabase = payLimitationFromDatabase==null?0d:payLimitationFromDatabase;
				PayLimitation payLimitation = new PayLimitation(org.getOrgid(), payType, Otype.初始额度.code(), payLimitationFromDatabase, payLimitationFromDatabase, null);
//				dao.saveOrUpdate(payLimitation);
				dao.executeUpdate(payLimitation);
			}
		}

	}


	

	/**
	 * 月结企业额度自动回正
	 */
	public void autoReviseMonLimiation(){
		String hql = " from PayLimitation where id in (select max(id) from PayLimitation where type=2 group by orgid)";
		List<PayLimitation> list = dao.queryForList(hql, new Object[]{});
		if(null!=list && list.size()>0){
			for(PayLimitation pl:list){
				reviseMonLimitation(pl.getOrgid());
			};
		}
	}

	/**
	 * 月结企业额度回正
	 * @param orgid
	 */
	public void reviseMonLimitation(Long orgid){
		if(null!=orgid){
			String hql = "from TAcOrg where orgid=?";
			TAcOrg org = (TAcOrg) dao.queryForObject(hql,new Object[]{orgid});
			Double creditLine = org.getCreditLine()==null?0:org.getCreditLine();
			PayLimitation pl = getLatestRecode(orgid);
			if(null!=pl){
				if(null!=org){
					/*Integer settlementDay = org.getSettlementDay();
					int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					if(day == settlementDay){*/
						Double leftLimitation = pl.getLeftLimitation()==null?0:pl.getLeftLimitation();
						Double revise = creditLine - leftLimitation;//需要修正的值
						PayLimitation payLimitation = new PayLimitation(pl.getOrgid(), 2, Otype.月结回正.code(), revise, creditLine, null);
						dao.executeUpdate(payLimitation);
					/*}*/
				}
			}else{

				//Double payLimitationFromDatabase = getPayLimitationFromDatabase(orgid);
				PayLimitation payLimitation = new PayLimitation(orgid, 2, Otype.初始额度.code(), creditLine, creditLine, null);
				dao.executeUpdate(payLimitation);
			}
		}
	}

	/**
	 * 修正企业剩余额度
	 * @param orgid 企业id
	 * @param actualValue 实际剩余额度
	 */
	public void correctLimitation(Long orgid,Double actualValue){
		if(null!=orgid){
			checkTempLimitationIsInvalid(orgid);
			PayLimitation latestRecode = getLatestRecode(orgid);
			Double leftLimitation = 0D;
			if(null!=latestRecode){
				leftLimitation = latestRecode.getLeftLimitation();
				leftLimitation = leftLimitation==null?0:leftLimitation;
				if(null!= actualValue && leftLimitation!=actualValue){
					Double cVal = actualValue - leftLimitation;
					changeLimitation(orgid,cVal,Otype.额度校正,null);
				}
			}else{
				PayLimitation payLimitation = new PayLimitation(orgid, 2, Otype.额度校正.code(), actualValue, actualValue, null);
				dao.executeUpdate(payLimitation);
			}

		}
	}


	/**
	 * 检查数据库的临时额度是否失效
	 * @param orgid
	 */
	@SuppressWarnings("unchecked")
	private void checkTempLimitationIsInvalid(Long orgid){
		String hql = "from PayLimitation where otype=2 and state=1 and sysdate>=invalidTime ";
		Object[] param = null;
		if(null!=orgid){
			hql += " and orgid=? ";
			param = new Object[]{orgid};
		}
		List<PayLimitation> list = dao.queryForList(hql, param);
		if(null!=list&&list.size()>0){
			for (PayLimitation payLimitation : list) {
				PayLimitation latestRecode = getLatestRecode(orgid);
				PayLimitation limitation = new PayLimitation(payLimitation.getOrgid(), payLimitation.getType(), Otype.临额失效.code(), -payLimitation.getAmount(), latestRecode.getLeftLimitation()-payLimitation.getAmount(), null);
				dao.executeUpdate(limitation);//添加一条减额的数据
				
				payLimitation.setState(0);//将原临时额度失效
				dao.executeUpdate(payLimitation);
			}
		}
	}



	/**
	 * 改变额度
	 * @param orgid 企业id
	 * @param amount 大于0为增加额度，比如充值；小于0为减少额度，比如正常消费
	 * @param otype Otype操作类型
	 * @param date 失效日期 otype为临时调额时使用 yyyy-MM-dd
	 */
	public MSM changeLimitation(Long orgid,Double amount,Otype otype,String date){
		MSM result = new MSM(MEnum.State.成功);
		if (null!=orgid && null!=amount && null!=otype){
			if(amount.doubleValue()>=0){
				increase(orgid,amount,otype,date);
			}else{
				result = decrease(orgid,-amount.doubleValue(),otype,date);
			}
		}else{
			result = new MSM(MEnum.State.失败,"参数不足");
		}
		return result;
	}

	/**
	 * 增加额度
	 * @param orgid 
	 * @param amount 金额
	 * @param otype 操作类型
	 * @param date 临时额度到期时间 yyyy-MM-dd 格式日期字符串  只有在临时额度时才会生效
	 */
	public void increase(Long orgid,Double amount,Otype otype,String date){
		
		if(null != orgid && amount != null && otype != null){
			checkTempLimitationIsInvalid(orgid);
			
			Integer type = getEnterprisePayType(orgid);
			//以下为计算剩余额度
			Double leftLimitation = 0D;
			PayLimitation recode = getLatestRecode(orgid);
			if(null==recode){
				Double limit = getPayLimitationFromDatabase(orgid);
				limit = limit==null?0:limit.doubleValue();
				recode = new PayLimitation(orgid,type,Otype.初始额度.code(),limit,limit,null);
				dao.executeUpdate(recode);
			}
			leftLimitation = recode.getLeftLimitation()==null?0D:recode.getLeftLimitation();
			leftLimitation += amount;
			//以上为计算剩余额度
			Date invalidTime = null;
			if(StringUtil.isNotEmpty(date)){
				try {
					invalidTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date + " 23:59:59");
				} catch (ParseException e) {
					logger.error(StringUtil.getExceptionStr(e));
				}
			}
			PayLimitation payLimitation = new PayLimitation(orgid, type, otype.code(), amount, leftLimitation, invalidTime);
			
			dao.executeUpdate(payLimitation);//保存
			
			if(MEnum.Otype.手动调额 == otype || Otype.月结回正==otype || Otype.退款调额==otype || Otype.临时调额==otype){//如果是手动调额、月结回正、退款调额、临时调额 则 更新通知状态
				updateWarningStatus(orgid, WarningStatus.初始状态);
			}
		}
	}

	/**
	 * 减少额度
	 * @param orgid
	 * @param amount
	 * @param otype
	 * @return 返回预存款或者月结是否可用
	 */
	public MSM decrease(Long orgid, Double amount, Otype otype,String date){
		MSM result = new MSM(MEnum.State.成功);
		if(null!=orgid && null != amount && null != otype){
			checkTempLimitationIsInvalid(orgid);
			Integer type = getEnterprisePayType(orgid);
			Double leftLimitation = 0D;
			PayLimitation latestRecode = getLatestRecode(orgid);
			if(null==latestRecode){
				Double limit = getPayLimitationFromDatabase(orgid);
				limit = limit==null?0:limit.doubleValue();
				latestRecode = new PayLimitation(orgid,type,Otype.初始额度.code(),limit,limit,null);
				dao.executeUpdate(latestRecode);
			}
			if(null==latestRecode.getLeftLimitation() || latestRecode.getLeftLimitation()<amount){
				result = new MSM(MEnum.State.失败,getPageTips(type));
				leftLimitation = latestRecode.getLeftLimitation()==null?0:latestRecode.getLeftLimitation();
                checkAndSend(orgid, type, leftLimitation);//检查是否需要发送通知
			}else{
				Date invalidTime = null;
				if(StringUtil.isNotEmpty(date)){
					try {
						invalidTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date + " 23:59:59");
					} catch (ParseException e) {
						logger.error(StringUtil.getExceptionStr(e));
					}
				}
				leftLimitation = latestRecode.getLeftLimitation() - amount;
				PayLimitation payLimitation = new PayLimitation(orgid, type, otype.code(), -amount, leftLimitation, invalidTime);
				dao.executeUpdate(payLimitation);

                checkAndSend(orgid, type, leftLimitation);//检查是否需要发送通知
			}
		}
		return result;
	}


    /**
     * 检查是否需要发送通知
     * @param orgid
     * @param type 1-预付款 2-月结
     * @param leftLimitation 剩余额度
     */
    private void checkAndSend(Long orgid, Integer type, Double leftLimitation){
		/*
		  提醒条件：
		每次扣款后，企业额度（含预付款和月结授信）低于企业设置的阀值或者低于全局阀值

		第一次达到提醒条件：
		通知配置的企业联系人（最多5个）、客维部门（邮件）、财务部门（邮件）
		*如果企业阀值和全局阀值一起被触发，只提醒一次

		第N次（N>1）达到提醒条件：
		通知：客维部门、财务部门
		*企业联系人最多会接收到两次通知
	 */
        PayWarning warning = getWarning(orgid, type);
        if(null!=warning){
            //判断是否已经发送过
            boolean sendFlag = false;
            boolean sendFlag1 = false;
            if(leftLimitation<=warning.getWarningValue()){
                if(1==warning.getState()&&(MEnum.WarningStatus.初始状态.code()==warning.getStatus() || WarningStatus.已全局通知.code()==warning.getStatus())){
                    sendNotifyToEnterprise(warning,WarningStatus.asWarningStatus(warning.getStatus()),2, leftLimitation);//对企业员工发送通知 当前为企业通知
                    sendFlag = true;
                }
                sendNotifyToCSAndFo(warning,WarningStatus.asWarningStatus(warning.getStatus()),leftLimitation,2);//对客服和财务发送通知
				sendFlag1 = true;
            }
            if(getGlobalWarningStatus()){
                Double warningAmount = getGlobalWarningAmount();
                if(null!=warningAmount && leftLimitation<=warningAmount){
                    if(!sendFlag && (MEnum.WarningStatus.初始状态.code()==warning.getStatus() || MEnum.WarningStatus.已企业通知.code()==warning.getStatus())){
                        sendNotifyToEnterprise(warning,WarningStatus.asWarningStatus(warning.getStatus()),1, leftLimitation);
                    }
                    if(!sendFlag1){
						sendNotifyToCSAndFo(warning,WarningStatus.asWarningStatus(warning.getStatus()),leftLimitation,1);//对客服和财务发送通知
					}
                }

            }
        }else{
        	warning = new PayWarning();
        	warning.setOrgid(orgid);
        	//warning.setStatus(3);
			if(getGlobalWarningStatus()){
				Double warningAmount = getGlobalWarningAmount();
				if(null!=warningAmount && leftLimitation<=warningAmount){
					sendNotifyToCSAndFo(warning,null,leftLimitation,1);//对客服和财务发送通知
				}
			}
		}
    }


    /**
	 * 更新通知状态
	 * @param orgid
	 * @param status
	 */
	public void updateWarningStatus(Long orgid,WarningStatus status){
		if(null != orgid && null != status){
			try {
				PayWarning pw = (PayWarning) dao.queryForObject("from PayWarning where orgid=?", new Object[]{orgid});
				if(pw!=null){
					pw.setStatus(status.code());
					dao.executeUpdate(pw);

				}
			} catch (Exception e) {
				logger.error("com.viptrip.warning.service.WarningManager.updateWarningStatus()调用发生异常，参数为：orgid→" + orgid.longValue() + "，status→" + status.getCode());
				logger.error(StringUtil.getExceptionStr(e));
			}
		}
	}
	
	/**
	 * 获取企业支付类型
	 * @param orgid
	 * @return 1-预付款 2-月结
	 */
	private Integer getEnterprisePayType(Long orgid){
		Integer result = null;
		//数据库1-预付款 0-月结
		if(null!=orgid){
			String hql = "from TAcOrg where orgid=?";
			List list = dao.queryForList(hql, new Object[]{orgid});
			if(null!=list && list.size()==1){
				TAcOrg org = (TAcOrg) list.get(0);
				result = "1".equals(org.getIsBalancePay())?1:2;
			}
		}
		return result;
	}

	/**
	 * 获取企业
	 * @param orgid
	 * @return
	 */
	private TAcOrg getOrg(Long orgid){
		TAcOrg result = null;
		//数据库1-预付款 0-月结
		if(null!=orgid){
			String hql = "from TAcOrg where orgid=?";
			result = (TAcOrg)dao.queryForObject(hql,new Object[]{orgid});
		}
		return result;
	}
	
	/**
	 * 获取支付额度
	 * @param orgid
	 * @return
	 */
	private PayLimitation getLatestRecode(Long orgid){
		PayLimitation result = null;
		if(null!=orgid && orgid > 0){
			String hql = " from PayLimitation where id=(select max(id) from PayLimitation where orgid=?)";
			try {
				result = (PayLimitation) dao.queryForObject(hql, new Object[]{orgid});
			} catch (Exception e) {
				logger.error("com.viptrip.warning.service.WarningManager.getLatestRecode()调用发生异常，参数为：orgid→" + orgid.longValue());
				logger.error(StringUtil.getExceptionStr(e));
			}
		}
		return result;
	}

	/**
	 * 获取预付款企业余额/月结企业余额
	 * 		预付余额=t_ac_org_balance的余额
	 * 		月结余额=初始额度-当月1日至现在的付款
	 * @param orgid
	 * @return
	 */
	public Double getPayLimitationFromDatabase(Long orgid){
		Double balance = 0d;
		if(null!=orgid){
			Integer payType = getEnterprisePayType(orgid);
			if(null!=payType){
				StringBuffer hql = new StringBuffer();
				if(1==payType){
					hql.append("SELECT nvl(SUM(money),0) FROM TAcOrgBalance T WHERE orgid=?");
					balance = (Double) dao.queryForObject(hql.toString(),new Object[]{orgid});
					balance = (balance==null?0:balance);
				}else{
					hql.append("select nvl(creditLine,0) from TAcOrg where orgid=?");
					balance = (Double) dao.queryForObject(hql.toString(),new Object[]{orgid});
					balance = (balance==null?0:balance);
					//计算月结用户已经使用多少额度
					Date endDate = Calendar.getInstance().getTime();//当前日期

					Date startDate = GlobalWarningUtil.getStartSettleDate();
					if(null==startDate){
						Calendar startDate1 = Calendar.getInstance();//开始日期
						startDate1.set(Calendar.DAY_OF_MONTH,1);
						startDate1.set(Calendar.HOUR,0);
						startDate1.set(Calendar.MINUTE,0);
						startDate1.set(Calendar.SECOND,0);
						startDate = startDate1.getTime();
					}

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					//select sum(nvl(t.RECEIVE_PRICE,0)+nvl(t.SERVICEFEE,0)+nvl(t.CHANGESERVICEFEE,0)+nvl(t.REFUNDSERVICEFEE,0)+nvl(t.NIGHTFEE,0)+nvl(t.CUSTOMERSERVICEFEE,0)) from t_ticket_profitability t where t.pay_type =1 group by company_name;

					Double usedLimit = null;
					hql = new StringBuffer();
					hql.append("select sum(nvl(t.RECEIVE_PRICE,0)+nvl(t.SERVICEFEE,0)+nvl(t.CHANGESERVICEFEE,0)+nvl(t.REFUNDSERVICEFEE,0)+nvl(t.NIGHTFEE,0)+nvl(t.CUSTOMERSERVICEFEE,0)) from t_ticket_profitability t where t.pay_type =1 and company_name=(select orgname from t_ac_org where orgid="  + orgid + ") ");
					hql.append(" and to_char(create_time,'yyyymmdd')>='" + sdf.format(startDate.getTime()) + "' and to_char(create_time,'yyyymmdd')<='" + sdf.format(endDate) + "' group by company_name");

					//usedLimit = (Double)dao.findUniqueBySQL(hql.toString());
					BigDecimal val = (BigDecimal) dao.getEntityManager().createNativeQuery(hql.toString()).getSingleResult();
					
					usedLimit = val==null?0:val.doubleValue();
					
					if(logger.isDebugEnabled() && balance-usedLimit<0){
						logger.debug("orgid=" + orgid + "初始化企业额度,初始值balance=" + balance.doubleValue());
						logger.debug("orgid=" + orgid + "初始化企业额度SQL=" + hql.toString());
						logger.debug("orgid=" + orgid + "初始化企业额度,消费额度为userLimit=" + (usedLimit==null?0:usedLimit.doubleValue()));
						logger.debug("-------------------------------------------------------------------------------------------------");
					}

					balance = balance - usedLimit;
				}
			}
		}
		return balance;
	}

	/**
	 * 获取月结企业初始额度
	 * @param orgid
	 * @return
	 */
	public Double getInitialCredit(Long orgid){
		Double balance = null;
		if(null!=orgid){
			StringBuffer hql = new StringBuffer();
			hql.append("select nvl(creditLine,0) from TAcOrg where orgid=?");
			BigDecimal val = (BigDecimal) dao.queryForObject(hql.toString(),new Object[]{orgid});
			balance = (val==null?0:val.doubleValue());
		}
		return balance;
	}

	/**
	 * 获取预警
	 * @param orgid
	 * @param type 1-预付款 2-月结
	 * @return
	 */
	public PayWarning getWarning(Long orgid,Integer type){
		PayWarning result = null;
		if(null!=orgid && null!=type){
			String hql = " from PayWarning where orgid=? and type=?";
			try {
				result = (PayWarning) dao.queryForObject(hql, new Object[]{orgid,type});
			} catch (Exception e) {
				logger.error("com.viptrip.warning.service.WarningManager.updateWarningStatus()调用发生异常，参数为：orgid→" + orgid.longValue() + "，type→" + type.intValue());
				logger.error(StringUtil.getExceptionStr(e));
			}
		}
		return result;
	}
	
	/**
	 * 设置全局预警开关
	 * @param warningValue 预警开关值
	 * @param status 预警开关
	 */
	public void setGlogbalWarning(Double warningValue,Integer status){
		if(null!=status){
			GlobalWarningUtil.setGlobalWarningStatus(status);
		}
		if(null!=warningValue && warningValue.doubleValue()>=0){
			GlobalWarningUtil.setGlobalWarningVal(warningValue);
		}
	}

	/**
	 * 设置企业初始额度值
	 * @param orgid 企业id
	 * @param value 额度值
	 */
	public void setInitalLimit(Long orgid,Double value){
		if(null!=orgid && null!=value && value.doubleValue()>=0){
			TAcOrg org = getOrg(orgid);
			if(null!=org){
				Double old = org.getCreditLine()==null?0:org.getCreditLine();
				PayLimitation latestRecode = getLatestRecode(orgid);
				if(null!=latestRecode){
					changeLimitation(orgid,value-old,Otype.手动调额,null);
				}else{
					PayLimitation payLimitation = new PayLimitation(orgid, 2, Otype.初始额度.code(), value, value, null);
					dao.executeUpdate(payLimitation);
				}
				String sql = "update t_ac_org set credit_line=:val where orgid=:orgid";
				dao.getEntityManager().createNativeQuery(sql).setParameter("val",value).setParameter("orgid",orgid).executeUpdate();
			}
		}
	}
	
	/**
	 * 获取全局预警状态
	 * @return
	 */
	private boolean getGlobalWarningStatus(){
		boolean result = false;
		Integer status = GlobalWarningUtil.getGlobalWarningStatus();
		result = (1==status);
		return result;
	}
	
	/**
	 * 获取全局预警额度
	 * @return
	 */
	private Double getGlobalWarningAmount(){
		return GlobalWarningUtil.getGlobalWarningVal();
	}
	
	/**
	 * 对企业用户发送提醒
	 * @param warning
	 * @param ws 当前发送状态
	 * @param type 1-全局通知 2-企业通知
	 */
	private void sendNotifyToEnterprise(PayWarning warning,WarningStatus ws,Integer type,Double left){
		switch (ws) {
			case 初始状态:
				WarningStatus w = 1==type?WarningStatus.已全局通知:WarningStatus.已企业通知;
				warning.setStatus(w.code());
				break;
			case 已全局通知:
			case 已企业通知:
				warning.setStatus(WarningStatus.全局和企业通知.code());
				break;
		}
		warning.setWarningTime(new Date());
		dao.executeUpdate(warning);//更新通知状态
		
		//发送通知
		List<PayWarningPerson> persons = getWarningPersons(warning.getOrgid(), null);
		if (GlobalWarningUtil.isTest())//如果是测试
			persons = GlobalWarningUtil.getTestPersonList(warning.getOrgid());
		if(null!=persons && persons.size()>0){
			for (Iterator<PayWarningPerson> iterator = persons.iterator(); iterator.hasNext();) {
				PayWarningPerson payWarningPerson = (PayWarningPerson) iterator
						.next();
				sendNotify(payWarningPerson,warning,left,type);
			}
		}
	}
	
	/**
	 * 对客服和财务发送提醒
	 * @param warning
	 * @param ws 当前发送状态
	 * @param left 剩余的额度
	 * @param warningType 通知方式 1-全局通知 2-企业通知
	 */
	private void sendNotifyToCSAndFo(PayWarning warning,WarningStatus ws,Double left,Integer warningType){
		List<String> addrs = GlobalWarningUtil.getEmailAddrBG();
		String addr = "";
		String content = null;
		if(1==warningType){
			content = GlobalWarningUtil.getGlobalWarningText(true);
		}else{
			content = GlobalWarningUtil.getWarningTplBG(WarningMethod.邮件);
		}
		content = contentFlagReplace(warning,content,left);
		String check = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		for(String address:addrs){
			if(StringUtil.isNotEmpty(address)&&GlobalWarningUtil.regxVerify(check,address)){
				addr += address + ",";
			}
		}
		if(addr.length()>0 && addr.lastIndexOf(",")==addr.length()-1){
			addr = addr.substring(0, addr.length()-1);
		}
		sendByEmail(addr,content,GlobalWarningUtil.getEmailTitle(false));
	}

	/**
	 * 将内容中的占位符替换掉
	 * @param warning
	 * @param content 原内容
	 * @param left 剩余额度
	 * @return
	 */
	private String contentFlagReplace(PayWarning warning,String content,Double left){
		DecimalFormat df = new DecimalFormat("#.00");
		TAcOrg org = getOrg(warning.getOrgid());
		if(content.indexOf("$oname")>=0){
			String oname = org==null?"":org.getOrgname();
			content = content.replaceAll("\\$oname",oname);
		}
		if(content.indexOf("$limit")>=0){
			Double value = warning==null?0:warning.getWarningValue();
			content = content.replaceAll("\\$limit",df.format(value.doubleValue()));
		}
		if(content.indexOf("$left")>=0){
			String l = left==null?"":df.format(left.doubleValue());
			content = content.replaceAll("\\$left",l);
		}
		if(content.indexOf("$global")>=0){
			String l = GlobalWarningUtil.getGlobalWarningVal()==null?"":df.format(GlobalWarningUtil.getGlobalWarningVal().doubleValue());
			content = content.replaceAll("\\$global",l);
		}
		return content;
	}

	/**
	 * 获取页面提示信息
	 * @param payMethod 支付方式 1-预付款 2-月结
	 * @return
	 */
	private String getPageTips(Integer payMethod){
		String result = null;
		if(null!=payMethod){
			if(1==payMethod)
				result = GlobalWarningUtil.getPageTipPre();
			else
				result = GlobalWarningUtil.getPageTipMon();
		}
		return result;
	}
	/**
	 * 发送短信
	 * @param mobile 手机号
	 * @param content 内容
	 */
	private void sendBySMS(String mobile,String content){
		if(StringUtil.isNotEmpty(mobile)&&StringUtil.isNotEmpty(content)){
			String regx = "^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$";
			if (GlobalWarningUtil.regxVerify(regx,mobile)){
				MessageService messageService = new MessageServiceImpl();
				Message msg = new Message();
				msg.setMessage(content);
				msg.setMobliephone(mobile);
				msg.setUser_key(GlobalWarningUtil.getUserKey());
				boolean b = messageService.saveMsg(msg);
			}
		}
	}

	/**
	 * 对设置的企业用户发送提醒
	 * @param person
	 * @param warning
	 * @param left 剩余额度
	 * @param warningType 通知类型 1-全局通知 2-企业通知
	 */
	private void sendNotify(PayWarningPerson person,PayWarning warning,Double left,Integer warningType){
		if(null!=person){
			Integer method = person.getMethod();
			if(method!=null){
				String content = null;
				if(1==warningType){
					content = GlobalWarningUtil.getGlobalWarningText(false);
				}else{
					content = GlobalWarningUtil.getWarningTplContent(WarningMethod.asWarningMethod(person.getMethod()));
				}

				content = contentFlagReplace(warning,content,left);

				String title = GlobalWarningUtil.getEmailTitle(true);
				String check = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
				switch (WarningMethod.asWarningMethod(method)) {
				case 短信:
					sendBySMS(person.getMobile(), content);
					break;
				case 邮件:
					if(StringUtil.isNotEmpty(person.getEmail())&&GlobalWarningUtil.regxVerify(check,person.getEmail())){
						sendByEmail(person.getEmail(), content,title);
					}
					break;
				case 短信和邮件:
					sendBySMS(person.getMobile(), content);
					if(StringUtil.isNotEmpty(person.getEmail())&&GlobalWarningUtil.regxVerify(check,person.getEmail())){
						sendByEmail(person.getEmail(), content,title);
					}
					break;
				}
			}
		}
	}

	/**
	 * 发送邮件
	 * @param email 邮件地址
	 * @param content 内容
	 */
	private void sendByEmail(String email,String content,String title){
		//String check = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		if(StringUtil.isNotEmpty(email)&&StringUtil.isNotEmpty(content)){
			//if (GlobalWarningUtil.regxVerify(check,email))
				new Thread(new MR(title,content,email)).start();

		}
	}
	
	/**
	 * 获取预警通知用户
	 * @param orgid
	 * @param type 通知方式 短信或者邮件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PayWarningPerson> getWarningPersons(Long orgid,Integer type){
		List<PayWarningPerson> result = null;
		if(null!=orgid){
			result = new ArrayList<PayWarningPerson>();
			String hql = "from PayWarningPerson where orgid=?";
			if(null!=type){
				hql += " and method=?";
				result = dao.queryForList(hql, new Object[]{orgid,type});
			}else{
				result = dao.queryForList(hql, new Object[]{orgid});
			}
		}
		return result;
	}



	protected long countSQLResult2(final String sql, final Object[] values) {
		String fromSql = sql;

		//fromSql = StringUtils.substringBefore(fromSql, "order by");

		String countSql = "select count(*) from ( " + fromSql+" )";

		try {
			javax.persistence.Query query = dao.getEntityManager().createNativeQuery(countSql);
			if(null!=values && values.length>0){
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			BigDecimal count = (BigDecimal) query.getSingleResult();
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countSql, e);
		}
	}

	private class MR implements Runnable{
		private String title;
		private String content;
		private String email;
		public MR() {
		}

		public MR(String title, String content, String email) {
			this.title = title;
			this.content = content;
			this.email = email;
		}

		@Override
		public void run() {
			int count = 1;
			while(count<=7){
				try{
					Sendemail.sendHTML(email, title, content);
					logger.info("发送邮件成功，当前为第" + (count) + "次发送，邮件地址为：" + email + ",主题为：" + title + ",内容为：" + content);
					break;
				}catch (Exception e){
					logger.error("发送邮件失败，当前为第" + (count++) + "次发送，邮件地址为：" + email + ",主题为：" + title + ",内容为：" + content + "\r\n" + StringUtil.getExceptionStr(e));
					try {
						Thread.sleep(1000*((Double)Math.pow(2,count-1)).longValue());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

}
