package com.viptrip.wetrip.dao.ext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.viptrip.base.common.MyEnum.OrderTypeEnum;
import com.viptrip.base.dao.BaseDaoForJPA;
import com.viptrip.common.model.RefundPriceModel;
import com.viptrip.intlAirticket.entity.TIntlComment;
import com.viptrip.intlAirticket.entity.TIntlOrder;

import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TComment;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.service.BalancePay;
@Component
public class ComDao extends BaseDaoForJPA{
	@Resource
	private BalancePay balancePay;
	
	/*public <T> List<T> queryForList(Class<T> clz,String HQL,Object[] parameterArray){
		Query query = getEntityManager().createQuery(HQL);
		if(null!=parameterArray && parameterArray.length>0){
			for(int i=0;i<parameterArray.length;i++){
				query.setParameter(i+1, parameterArray[i]);
			}
		}
		List list = query.getResultList();
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				List l = (List)list.get(i);
				T t = null;
				try {
					t= clz.newInstance();
					
				} catch (InstantiationException | IllegalAccessException e) {
					logger.error(StringUtil.getExceptionStr(e));
				}
			}
		}
		
		return null;
	}*/
	
	/*public List queryForList(String HQL,Object[] parameterArray){
		Query query = getEntityManager().createQuery(HQL);
		if(null!=parameterArray && parameterArray.length>0){
			for(int i=0;i<parameterArray.length;i++){
				query.setParameter(i+1, parameterArray[i]);
			}
		}
		return query.getResultList();
	}*/
	 /**
     * 生成订单号
     * @param oid   订单id
     * @param orderType  订单类型(国内机票("AT"), 国际机票("IA"), 国内酒店("HT"), 国际酒店("IH"), 机加酒("AH"), 火车票("TT"), 签证("VA"),租车("CT"))
     * 订单号规则：orderType  +  yyMMdd  +  oid
     * @return
     * hx
     */
    public String creatOrderNo(String oid,String orderType) {
    	if(oid.length()<6){
    		for(int i = 0 ;i < 6 - oid.length();i++){
    			oid = "0"+oid;
    		}
    	}
    	String orderNo="";
    	SimpleDateFormat df = new SimpleDateFormat("yyMMdd");//设置日期格式
    	String date = df.format(new Date());
    	orderNo=orderType+date+oid;
    	return orderNo;
    }
    /**
     * 微信传过来的证件类型转化为系统所需要的证件类型
     * @param type   1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
     * @return  返回0为传过来错误数据
     * hx
     */
    @SuppressWarnings({ })
    public String getIdType(Integer type) {
    	String idType="";
    	if(1==type){
    		idType="1";
    	}else if(2==type){
    		idType="2";//
    	}else if(3==type){
    		idType="10";
    	}else if(4==type){
    		idType="3";
    	}else if(5==type){
    		idType="5";
    	}else if(6==type){
    		idType="4";
    	}else if(7==type){
    		idType="6";
    	}else if(99==type){
    		idType="99";
    	}else{
    		idType="0";
    	}
    	return idType;
    }
    /**
     * 证件类型转义
     * @param type   
     * @return  返回0为传过来错误数据
     * hx
     */
    @SuppressWarnings({ })
    public String getNameByIdType(String type) {
    	String idType="";
    	if("1".equals(type)){
    		idType="身份证";
    	}else if("2".equals(type)){
    		idType="因公护照";
    	}else if("3".equals(type)){
    		idType="回乡证";
    	}else if("4".equals(type)){
    		idType="因公港澳通行证";
    	}else if("5".equals(type)){
    		idType="军官证";
    	}else if("6".equals(type)){
    		idType="入台证";
    	}else if("7".equals(type)){
    		idType="大陆往来通行证";
    	}else if("8".equals(type)){
    		idType="因私护照";
    	}else if("9".equals(type)){
    		idType="因私港澳通行证";
    	}
    	return idType;
    }
    
    /**
     * 取数据库时间
     * 
     * @return Map
     */
    public Timestamp getDBTime() {
        String queryString = "select sysdate from dual ";
        try {
            List rows = this.queryBySQL(queryString, null);
//            List rows = queryForList(queryString);
            if (rows != null && rows.size() > 0) {
                return (Timestamp)rows.get(0);
//                Map map = (Map) rows.get(0);
//                return ((Timestamp) map.get("sysdate"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /** 
     * hx
     */ 
    public static double exectu(double val) {
        BigDecimal decimal = new BigDecimal(val);
        // 0表示保留0位小数， BigDecimal.ROUND_UP表示第2位小数后，只要有值，就向前进1
        decimal = decimal.setScale(0, BigDecimal.ROUND_UP);
        return val;
    }
    /** 
    * 儿童Y舱全价的50%，并且个位四舍五入计算
    * hx
    */ 
    public Double getMathet(Double y) {
        BigDecimal b = new BigDecimal((double) y / (double) 10 / (double) 2).setScale(0, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue() * 10;
    }
    // 婴儿Y舱全价的10%，并且个位四舍五入计算
    public Double getMathye(Double y) {
        BigDecimal b = new BigDecimal((double) y / (double) 10 / (double) 10).setScale(0, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue() * 10;
    }
    /** 
     * 提供精确的加法运算。 
     *  
     * @param v1 
     *            被加数 
     * @param v2 
     *            加数 
     * @return 两个参数的和 
     * 
     * hx
     */  
  
    public static double add(double v1, double v2)  
    {  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.add(b2).doubleValue();  
    } 
    /**
	 * 通过机构名称查询出该机构的总公司
	 * @param orgname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TAcOrg getOrgByOrgName(String  orgname) {
				
		String hql="from TAcOrg  where orgname ='"+orgname+"'  and status='1'";
		List<TAcOrg> list=queryForList(hql);
		if(list.size()>0){
			return list.get(0);
		}
			
	

		return null;

	}

    
	/**
     * 返回一个当前的时间，并按格式转换为字符串
     * 
     * @return String
     * 
     * hx
     */
    public static String getDateTime(String... pattern) {

        // GregorianCalendar gcNow = new GregorianCalendar();
        // return DateFormatUtils.format(gcNow,pattern);
        String sPtn = "yyyy-MM-dd HH:mm:ss";
        if (pattern != null && pattern.length > 0) {
            sPtn = pattern[0];
        }
        SimpleDateFormat formatter = new SimpleDateFormat(sPtn);
        return formatter.format(new Date());
    }
    /**
     * 将字符串转成日期
     * 
     * @param String
     *            str
     * @param String
     *            pattern
     * @return Date
     * 
     * hx
     */
    public static Date str2Date(String str, String pattern) {
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(str, new String[] { pattern });
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }/**
     * 将日期转成字符串
     * 
     * @param Date
     *            date
     * @param String
     *            [] patterns
     * @return String
     * 
     * hx
     */
    public static String date2Str(Date date, String pattern) {
        // return DateFormatUtils.format(date, pattern);
        try {
            if (date != null) {
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                return formatter.format(date);
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
 

	
	/**
	 *  nullTo - 把null转化为指定的字符串
	 *  hx
	 */
	public static String nullTo(Object s, String replace) {
		return s == null ? replace : s.toString();
	}

	public void deleteTOrder(long oid) {
        TOrder torder = queryForEntity(oid, TOrder.class);
        executeDelete(torder);
    }
	/**@title:查询乘机人是否存在tacuser里面
	 * @author:hx
	 * @date:2016-9-7
	 * @return:String 
	 */
	@SuppressWarnings("unused")
	public TAcUser getIsTacuser(TTravelItinerary tt) {
		TAcUser tacuser=null;
		tacuser=queryIsTacuser("idcard",tt.getPassengerName(),tt.getPassengerId());
		if(tacuser==null){
			tacuser=queryIsTacuser("passport",tt.getPassengerName(),tt.getPassengerId());
		}else if(tacuser==null){
			tacuser=queryIsTacuser("phone",tt.getPassengerName(),tt.getPassengerTel());
		}else if(tacuser==null){
			tacuser=queryIsTacuser("email",tt.getPassengerName(),tt.getPassengerEmail());
		}
		
		return tacuser;
	}
	/**@title:查询乘机人是否存在tacuser里面
	 * @author:hx
	 * @date:2016-9-7
	 * @return:String 
	 */
	public TAcUser queryIsTacuser(String attdef,String username,String idcard) {
		TAcUser tacuser=null;
		List<TAcUser> list = queryForList("from TAcUser where username='"+username+"' and "+attdef+"='"+idcard+"'");
		if(list.size()>0){
			tacuser=list.get(0);
		}
		return tacuser;
	}
	/**@title:查询客服和销售根据名字和电话
	 * @author:hx
	 * @date:2016-9-7
	 * @return:String 
	 */
	public List<TAcUser> getEmail(TAcOrg torg) {
		List<TAcUser> list = queryForList("from TAcUser where (username='"+torg.getClientname()+"' and phone='"+torg.getClientphone()+"') or (username='"+torg.getSellname()+"' and phone='"+torg.getSellphone()+"')");
		
		return list;
	}
	public String getIsWhere(String isWhere){
		String laiyuan="";
		if("0".equals(isWhere)){
      		 laiyuan="pc端";
      	 }else if("1".equals(isWhere)){
      		 laiyuan="app端";
      	 }else if("2".equals(isWhere)){
      		 laiyuan="网站";
      	 }else if("4".equals(isWhere)){
      		 laiyuan="宝库";
      	 }else if("5".equals(isWhere)){
      		 laiyuan="微信";
      	 }
		return laiyuan;
	}

		/**
		 * 计算该段行程总价（因服务费存在按订单收取，而服务费在乘机人保存时只保存在其中一个人上，所以会出现两端行程有的有服务费有的没服务费）
		 * @param Tlist 乘机人集合
		 * @return
		 */
		public String getPnrTotalPrice(List<TTravelItinerary> Tlist){
		
			double temptotalPrice=0;
			double tempprice=0;
			for (TTravelItinerary t : Tlist) {
				tempprice=add(t.getFlightSum(), t.getServiceFee() == null ? 0 : t.getServiceFee());
				tempprice=add(tempprice, t.getNightFee() == null ? 0 : t.getNightFee());
				tempprice=add(tempprice, t.getCustomerServiceFee() == null ? 0 : t.getCustomerServiceFee());
				temptotalPrice=add(temptotalPrice, tempprice);
			}
			

			return String.valueOf(temptotalPrice);
			
		}
		public List getBookFlights(String passengerId) {
			List ttList = null;
	        try {
	            ttList =queryBySQL(
	                            " select to_char(t.flight_time,'yyyy-MM-dd'),t.flight_number,p.cangwei from t_travel_itinerary t,t_pnr p where t.pnr_id=p.pnr_id and" +
	                            " t.passenger_Id = '"+passengerId+"' " +
	                            " and t.flight_Status not in (3,4,9,13)",null);
	        } catch (Exception e) {
	            // TODO: handle exception
	            ttList = null;
	        }

	        return ttList;
		}
		/*param   orderType:(1-国内订单   2-国际订单)    id:(订单id)    deleteReason: (删除理由)
		 * return  0:成功   1：订单类型不对   2:删除订单失败
		 * 
		 */
		public Integer deleteOrder(Integer orderType,Long id,String deleteReason){
			Integer flag=0;
			try {
				String orderNo="";
				Long companyId=null;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				RefundPriceModel refundPriceModel=new RefundPriceModel();
				if(1==orderType){//国内订单
					TOrder torder=queryForEntity(id, TOrder.class);
					torder.setOrderStatus("7"); // 修改订单状态为已删除
					torder.setOpReason(deleteReason); // 删除原因
					for (TPnr pnr : torder.getTPnrs()) {
			            for (TTravelItinerary ticket : pnr.getTTravelItineraries()) {
			                ticket.setFlightStatus("13");
			                ticket.setTravelItineraryNo(null);
			                executeUpdate(ticket);
			            }
			        }
					executeUpdate(torder);
					orderNo=torder.getOrderno();
					TAcOrg org=getOrgByOrgName(torder.getCompanyName());
					if(org!=null){
						companyId=org.getOrgid();
					}
					refundPriceModel.paymethod=torder.getPayMethod();
					refundPriceModel.orderNo=torder.getOrderno();
					refundPriceModel.companyId=companyId;
					refundPriceModel.bussiness=OrderTypeEnum.国内机票;
					refundPriceModel.type="1";//1-出票  2:-改期
					
					TComment comment = new TComment();
		            
		            comment.setPassengerName(torder.getSubscribeName());
		            comment.setCommentDate(dateFormat.format(new Date()));
		            comment.setOId(torder.getOId().longValue());
		            comment.setCommentMsg("订单删除-删除原因："+deleteReason);
		            executeSave(comment);
				}else if(2==orderType){//国际订单
				    TIntlOrder tintlorder=queryForEntity(id,TIntlOrder.class);
	                tintlorder.setIntlOrderStatus("7");
	                tintlorder.setOpReason(deleteReason);
	                executeUpdate(tintlorder);
	                orderNo=tintlorder.getIntlOrderno();
	                
	                refundPriceModel.paymethod=tintlorder.getIntlPayMethod();
					refundPriceModel.orderNo=tintlorder.getIntlOrderno();
					refundPriceModel.companyId=Long.valueOf(tintlorder.getCompanyOfAffiliationId());
					refundPriceModel.bussiness=OrderTypeEnum.国际机票;
					refundPriceModel.type="1";//1-出票  2:-改期
	                
	                TIntlComment intlcomment=new TIntlComment();
	                
	                intlcomment.setPassengerName(tintlorder.getIntlSubscribeName());
	                intlcomment.setCommentDate(dateFormat.format(new Date()));
	                intlcomment.setOId(tintlorder.getOrderid().longValue());
	                intlcomment.setCommentMsg("订单删除-删除原因："+deleteReason);
	                executeSave(intlcomment);
				}else{
					flag=1;
				}
				if(flag==0 & StringUtil.isNotEmpty(orderNo)){
					balancePay.refundTicketPrice(refundPriceModel);
				}
			} catch (Exception e) {
				flag=2;
			}
			
			return flag;
		}
		
		
		/**
	     * 通过in条件查询 
	     * 	查询语句 from Entity where fieldA in (:fieldA) 
	     * @param hql 查询语句
	     * @param conditions Map条件
	     * @return
	     */
		@SuppressWarnings("unchecked")
		public <E> List<E> queryWithInCondition(String hql,Map<String,Object> conditions){
	    	List<E> result = null;
	    	if(!StringUtil.isEmpty(hql)){
	    		Query query = getEntityManager().createQuery(hql);
	    		if(null!=conditions && !conditions.isEmpty()){
	    			for (Iterator<String> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
						String key = iterator.next();
						Object obj = conditions.get(key);
						if(null!=obj){
							query.setParameter(key, obj);
							/*if(obj instanceof Collection){
								query.set
							}else{
								query.setParameter(key, obj);
							}*/
						}
					}
	    		}
	    		result = query.getResultList();
	    	}
	    	return result;
	    } 
		

	    /**
	     * 从数据库得到序列值
	     * 
	     * @return Map
	     */
	    public Long getSeqNextVal(String seqName) {
	        String queryString = "select " + seqName + ".nextval from dual ";
	        try {
//	            List rows = jdbcTemplate.queryForList(queryString);
//	            if (rows != null && rows.size() > 0) {
//	                Map map = (Map) rows.get(0);
//	                return ((BigDecimal) map.get("nextval")).longValue();
//	            } else {
//	                return new Long(0);
//	            }
	            
	            List rows = this.queryBySQL(queryString, null);
	            if (rows != null && rows.size() > 0) {
	                return (Long)rows.get(0);
	            } else {
	                return new Long(0);
	            }
	        } catch (Exception e) {
	            return new Long(0);
	        }
	    }
}
