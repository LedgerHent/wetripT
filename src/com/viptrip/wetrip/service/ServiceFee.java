package com.viptrip.wetrip.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.viptrip.intlAirticket.entity.TIntlAirTicketItinerary;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.entity.TIntlServicefee;
import com.viptrip.intlAirticket.entity.TIntlTravelItinerary;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcServicefee;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
@Service
@Transactional
public class ServiceFee {
	
	@Resource
	private ComDao comDao;
	/**
	 * 国内机票服务费收取
	 * @param oid
	 */
	public void saveSubscribeServiceFee(long oid){
		TOrder tOrder = comDao.queryForEntity(oid, TOrder.class);// 获取订单信息
		
		List<TPnr> thePnr =new ArrayList<TPnr>(tOrder.getTPnrs());
		//计算该订单总的预订时票面价
		double totalPrice=0;//不含保险不含税
		double totalServiceFee=0;//服务费总额
		for(TPnr pnr : thePnr){
			List<TTravelItinerary> ttList =new ArrayList<TTravelItinerary>(pnr.getTTravelItineraries());
			for (TTravelItinerary tTravelItinerary : ttList) {
				totalPrice=comDao.add(totalPrice, tTravelItinerary.getTicketPrice());//预订时票价
			}
		}
		//机构信息
		TAcOrg	tacorg =comDao.getOrgByOrgName(tOrder.getCompanyName());
		
		TAcServicefee  fee=null;
		boolean night=getTheNight(new Date());//是否为夜间
		boolean is=true;//记录收取服务费在一个人上
		
		String pnrtemp="";
		boolean isPnr;
		for(TPnr pnr : thePnr){
		    //判断往返pnr相同则视为同一张票不收取服务费
            if(!pnrtemp.equals(pnr.getPnr())){
                pnrtemp=pnr.getPnr();
                isPnr=true;
            }else{
                isPnr=false;
            }
			String type=pnr.getFlighttype();//航程类型，1-去程(单程)航班，2-回程航班
			
			List<TTravelItinerary> ttList = new ArrayList<TTravelItinerary>(pnr.getTTravelItineraries());
			
			for (TTravelItinerary passengers : ttList) {
				//判断  是否收取服务费，否（1），是（2）
				if(tacorg.getServiceStatus()!=null &&"2".equals(tacorg.getServiceStatus())){
					//初始化设置为0
					passengers.setCustomerServiceFee(0d);//客户预订服务费
					passengers.setServiceFee(0d);//客服预订服务费
					passengers.setNightFee(0d);//夜间服务费
					
				////计算服务费的方式，按机票收取（1），按订单收取（2）。设置之后，以下各服务费均按此方式收取。
					//按机票收取
					if(tacorg.getCalcServiceFeeType()!=null && "1".equals(tacorg.getCalcServiceFeeType())){
						//机票是否按阶梯方式收取，否（1），是（2）	
						if(tacorg.getLadderWay()!=null && "1".equals(tacorg.getLadderWay())){
							//判断是否为客服预订
							if(tOrder.getProxySubscribeName()==null || tOrder.getProxySubscribeName().equals("")){
								//收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
								if(tacorg.getCustomerServiceFeeType()!=null && "1".equals(tacorg.getCustomerServiceFeeType())){
								    //判断往返pnr相同则视为同一张票不收取服务费
		                            if(isPnr==false){
		                                continue;
		                            }
									passengers.setCustomerServiceFee(tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() );
									totalServiceFee=comDao.add(totalServiceFee, tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee());
									
								}else if(tacorg.getCustomerServiceFeeType()!=null && "2".equals(tacorg.getCustomerServiceFeeType())){
									
									passengers.setCustomerServiceFee(passengers.getTicketPrice()*( (tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() ) *0.01));
									totalServiceFee=comDao.add(totalServiceFee, passengers.getTicketPrice()*( (tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() ) *0.01));
								}
							}else{
								//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
								if(tacorg.getServiceFeeType()!=null && "1".equals(tacorg.getServiceFeeType())){
								  //判断往返pnr相同则视为同一张票不收取服务费
		                            if(isPnr==false){
		                                continue;
		                            }
									passengers.setServiceFee(tacorg.getServiceFee()==null? 0:tacorg.getServiceFee() );
									totalServiceFee=comDao.add(totalServiceFee, tacorg.getServiceFee()==null? 0:tacorg.getServiceFee());
									
								}else if(tacorg.getServiceFeeType()!=null && "2".equals(tacorg.getServiceFeeType())){
									
									passengers.setServiceFee(passengers.getTicketPrice()*( (tacorg.getServiceFee()==null? 0:tacorg.getServiceFee() ) *0.01));
									totalServiceFee=comDao.add(totalServiceFee, passengers.getTicketPrice()*( (tacorg.getServiceFee()==null? 0:tacorg.getServiceFee() ) *0.01));
								}
							}
						}else{  //机票按阶梯方式收取
							  fee=getTAcServicefee(passengers.getTicketPrice(), tacorg,"1");
							  
							//判断是否为客服预订
								if(tOrder.getProxySubscribeName()==null || "".equals(tOrder.getProxySubscribeName())){
									//收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
									if(fee.getCustomerservicefeetype()!=null && "1".equals(fee.getCustomerservicefeetype())){
									  //判断往返pnr相同则视为同一张票不收取服务费
			                            if(isPnr==false){
			                                continue;
			                            }
										passengers.setCustomerServiceFee(fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() );
										totalServiceFee=comDao.add(totalServiceFee, fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee());
										
									}else if(fee.getCustomerservicefeetype()!=null && "2".equals(fee.getCustomerservicefeetype())){
										
										passengers.setCustomerServiceFee(passengers.getTicketPrice()*( (fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() ) *0.01));
										totalServiceFee=comDao.add(totalServiceFee, passengers.getTicketPrice()*( (fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() ) *0.01));
									}
								}else{
									//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
									if(fee.getServicefeetype()!=null && "1".equals(fee.getServicefeetype())){
									  //判断往返pnr相同则视为同一张票不收取服务费
			                            if(isPnr==false){
			                                continue;
			                            }
										passengers.setServiceFee(fee.getServicefee()==null? 0:fee.getServicefee() );
										totalServiceFee=comDao.add(totalServiceFee, fee.getServicefee()==null? 0:fee.getServicefee());
									}else if(fee.getServicefeetype()!=null && "2".equals(fee.getServicefeetype())){
										passengers.setServiceFee(passengers.getTicketPrice()*( (fee.getServicefee()==null? 0:fee.getServicefee() ) *0.01));
										totalServiceFee=comDao.add(totalServiceFee, passengers.getTicketPrice()*( (fee.getServicefee()==null? 0:fee.getServicefee() ) *0.01));
									}
								}
						}
						////计算服务费的方式，按机票收取（1），按订单收取（2）。设置之后，以下各服务费均按此方式收取。
					}else if(tacorg.getCalcServiceFeeType()!=null && "2".equals(tacorg.getCalcServiceFeeType())){       //按订单收取（2）
						//机票是否按阶梯方式收取，否（1），是（2）	
						if(tacorg.getLadderWay()!=null && "1".equals(tacorg.getLadderWay())){
								//判断是否是往返
								if("2".equals(tOrder.getProcessstate())){
									//如果是往返则在第二段行程增加
									if("1".equals(type)){
										//只记录在一个人上
										if(is){
											//判断是否为客服预订
											if(tOrder.getProxySubscribeName()==null || "".equals(tOrder.getProxySubscribeName())){
												//收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
												if(tacorg.getCustomerServiceFeeType()!=null && "1".equals(tacorg.getCustomerServiceFeeType())){
													
													
													passengers.setCustomerServiceFee(tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() );
													totalServiceFee=comDao.add(totalServiceFee, tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee());
													
												}else if(tacorg.getCustomerServiceFeeType()!=null && "2".equals(tacorg.getCustomerServiceFeeType())){
													
													passengers.setCustomerServiceFee(totalPrice*( (tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() ) *0.01));
													totalServiceFee=comDao.add(totalServiceFee, totalPrice*( (tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() ) *0.01));
												}
											}else{
											
												//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
												if(tacorg.getServiceFeeType()!=null && "1".equals(tacorg.getServiceFeeType())){
													passengers.setServiceFee(tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee());
													totalServiceFee=comDao.add(totalServiceFee, tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee());
												}else if(tacorg.getServiceFeeType()!=null && "2".equals(tacorg.getServiceFeeType())){
													passengers.setServiceFee(totalPrice*( ( tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee() )*0.01));
													totalServiceFee=comDao.add(totalServiceFee, totalPrice*( ( tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee() )*0.01));
												}
											}								
											is=false;
									    }
									}
									
									//判断是否是往返
								}else{
										//只记录在一个人上
										if(is){
											//判断是否为客服预订
											if(tOrder.getProxySubscribeName()==null || "".equals(tOrder.getProxySubscribeName())){
												//收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
												if(tacorg.getCustomerServiceFeeType()!=null && "1".equals(tacorg.getCustomerServiceFeeType())){
													passengers.setCustomerServiceFee(tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() );
													totalServiceFee=comDao.add(totalServiceFee, tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee());
												}else if(tacorg.getCustomerServiceFeeType()!=null && "2".equals(tacorg.getCustomerServiceFeeType())){
													passengers.setCustomerServiceFee(totalPrice*( (tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() ) *0.01));
													totalServiceFee=comDao.add(totalServiceFee, totalPrice*( (tacorg.getCustomerServiceFee()==null? 0:tacorg.getCustomerServiceFee() ) *0.01));
												}
											}else{
												//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
												if(tacorg.getServiceFeeType() !=null && "1".equals(tacorg.getServiceFeeType())){
													passengers.setServiceFee(tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee());
													totalServiceFee=comDao.add(totalServiceFee, tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee());
												}else if(tacorg.getServiceFeeType() !=null && "2".equals(tacorg.getServiceFeeType())){
													passengers.setServiceFee(totalPrice*((  tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee() )*0.01));
													totalServiceFee=comDao.add(totalServiceFee, totalPrice*((  tacorg.getServiceFee()==null ? 0:tacorg.getServiceFee() )*0.01));
												}
											}
											is=false;
										}
								}
						}else{
							fee=getTAcServicefee(totalPrice, tacorg,"1");
							//判断是否是往返
							if("2".equals(tOrder.getProcessstate())){
								//如果是往返则在第二段行程增加
								if("1".equals(type)){
									//只记录在一个人上
									if(is){
										//判断是否为客服预订
										if(tOrder.getProxySubscribeName()==null || "".equals(tOrder.getProxySubscribeName())){
											//收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
											if(fee.getCustomerservicefeetype()!=null && "1".equals(fee.getCustomerservicefeetype())){
												passengers.setCustomerServiceFee(fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() );
												totalServiceFee=comDao.add(totalServiceFee, fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee());
												
											}else if(fee.getCustomerservicefeetype()!=null && "2".equals(fee.getCustomerservicefeetype())){
												
												passengers.setCustomerServiceFee(totalPrice*( (fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() ) *0.01));
												totalServiceFee=comDao.add(totalServiceFee, totalPrice*( (fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() ) *0.01));
											}
										}else{
											//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
											if(fee.getServicefeetype()!=null && "1".equals(fee.getServicefeetype())){
												passengers.setServiceFee(fee.getServicefee()==null ? 0:fee.getServicefee());
												totalServiceFee=comDao.add(totalServiceFee, fee.getServicefee()==null ? 0:fee.getServicefee());
											}else if(fee.getServicefeetype()!=null && "2".equals(fee.getServicefeetype())){
												passengers.setServiceFee(totalPrice*( ( fee.getServicefee()==null ? 0:fee.getServicefee() )*0.01));
												totalServiceFee=comDao.add(totalServiceFee, totalPrice*( ( fee.getServicefee()==null ? 0:fee.getServicefee() )*0.01));
											}
										}
										is=false;
								    }
								}
								//判断是否是往返
							}else{
									//只记录在一个人上
									if(is){
										//判断是否为客服预订
										if(tOrder.getProxySubscribeName()==null || "".equals(tOrder.getProxySubscribeName())){
											//收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
											if(fee.getCustomerservicefeetype()!=null && "1".equals(fee.getCustomerservicefeetype())){
												passengers.setCustomerServiceFee(fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() );
												totalServiceFee=comDao.add(totalServiceFee, fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee());
												
												
											}else if(fee.getCustomerservicefeetype()!=null && "2".equals(fee.getCustomerservicefeetype())){
												passengers.setCustomerServiceFee(totalPrice*( (fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() ) *0.01));
												totalServiceFee=comDao.add(totalServiceFee, totalPrice*( (fee.getCustomerservicefee()==null? 0:fee.getCustomerservicefee() ) *0.01));
											}
										}else{
											//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
											if(fee.getServicefeetype() !=null && "1".equals(fee.getServicefeetype())){
												passengers.setServiceFee(fee.getServicefee()==null ? 0:fee.getServicefee());
												totalServiceFee=comDao.add(totalServiceFee, fee.getServicefee()==null ? 0:fee.getServicefee());
											}else if(fee.getServicefeetype() !=null && "2".equals(fee.getServicefeetype())){
												passengers.setServiceFee(totalPrice*((  fee.getServicefee()==null ? 0:fee.getServicefee() )*0.01));
												totalServiceFee=comDao.add(totalServiceFee, totalPrice*((  fee.getServicefee()==null ? 0:fee.getServicefee() )*0.01));
											}
										}
										is=false;
									}
							    }
						    }
						}
					//收取夜间服务费  收取夜间服务费方式，定额（1）百分比（2），20点至8点为夜间服务时间，此期间内的出票、改期、退票均收取相等的服务费（且是在原有的各种服务费基础上累加）。计算百分比也按票面价计算。
					
					if(night){
						//判断是否为客服预订
						if(tOrder.getProxySubscribeName()!=null && !"".equals(tOrder.getProxySubscribeName())){
							passengers.setNightFee(tacorg.getNightFee()==null? 0:tacorg.getNightFee());
							totalServiceFee=comDao.add(totalServiceFee, tacorg.getNightFee()==null? 0:tacorg.getNightFee());
						}
					}
					//计算 服务费  end
					comDao.executeUpdate(passengers);
					}
				}
		}
		tOrder.setTotalPrice(tOrder.getTotalPrice()+totalServiceFee);
		tOrder.setTotalServiceFee(totalServiceFee);
		comDao.executeUpdate(tOrder);
}
	
	
	/**
	 * 返回阶梯服务费
	 * @param price
	 * @param tacorg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TAcServicefee getTAcServicefee(double price ,TAcOrg	tacorg,String type){
		List<TAcServicefee> list = comDao.queryForList(
				" from   TAcServicefee  where  type='"+type+"' and startprice <= "+price+" and nvl(endprice, 99999999) >= "+price+" and orgid = "+tacorg.getOrgid()+" ");
		if(list.size()>0){
			return list.get(0);
		}
		return new TAcServicefee();
	
	}
	/**
     * 返回是否是夜間值班時間
     * 
     * @param date
     * @return true 是夜間 false 是白天
     */
    @SuppressWarnings("deprecation")
    public static boolean getTheNight(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = sdf.format(date).split(":")[0] + sdf.format(date).split(":")[1];// 當前時間

        String beginDate = Config.NIGHTBEGIN;
        String endDate = Config.NIGHTEND;
        int begin = Integer.valueOf(beginDate.split(":")[0] + beginDate.split(":")[1]);
        int end = Integer.valueOf(endDate.split(":")[0] + endDate.split(":")[1]);

        boolean is = false;
        int hour = Integer.valueOf(currentDate);

        if (hour >= begin || hour < end) {
            is = true;// System.out.println("晚班"+hour);
        } else {
            is = false;// System.out.println("早班"+hour);
        }
        return is;
    }
    
    
    /**
     * 国际机票服务费收取
     * @param oid
     */
    @SuppressWarnings({ "unchecked", "unused" })
    public void saveIntlSubscribeServiceFee(TIntlOrder tOrde,List<TIntlTravelItinerary> psgList,int segmentCount,double tempTotalPrice,double tempPrice){
        //服务费收取
        
            double totalServiceFee=0;//服务费总额
            int a = 0;//"1":非亚洲    "2":亚洲
            //机构信息
            TAcOrg  tacorg = comDao.queryForEntity(Long.valueOf(tOrde.getCompanyOfAffiliationId()), TAcOrg.class);
//            TAcOrg  tacorg =(TAcOrg)this.findById(TAcOrg.class,Long.valueOf(tOrde.getCompanyOfAffiliationId()) );
            //行程信息
            List<TIntlAirTicketItinerary> tiatis = comDao.queryForList("from TIntlAirTicketItinerary where orderid = "+tOrde.getOrderid()+" and intlVoyageStatus='0' order by intlSegmentNo asc");
            //国际机票收取服务费规则
            List<TIntlServicefee> servicefees=comDao.queryForList("from TIntlServicefee where orgid ="+Long.valueOf(tOrde.getCompanyOfAffiliationId()));
            //国际机票按航段收取服务费规则
            //List<TIntlServicefee> hangsduanServicefees=dao.findByHQL("from TIntlServicefee where orgid = ? ", Long.valueOf(tOrde.getCompanyOfAffiliationId()));
            boolean night=this.getTheNight(new Date());//是否为夜间
            
            List<String> areaList=new ArrayList<String>();
            double tiatisLength=Math.ceil(tiatis.size()/4);
            for (int j = 0; j < tiatis.size();j++) {     
                TIntlAirTicketItinerary tiati=tiatis.get(j);
                String intlDepartureAirport=tiati.getIntlDepartureAirport();
                List<String> area1=this.isAirportArea(intlDepartureAirport);
                if(area1!=null && area1.size()>0){
                    areaList.add(area1.get(0));
                }
                String intlReachAirport=tiati.getIntlReachAirport();
                List<String> area=this.isAirportArea(intlReachAirport);
                if(area!=null && area.size()>0){
                    areaList.add(area.get(0));
                }else{
                    
                }
            }
            
            
            
            
            
            //行程按票号组合
            String intlDepartureAirport="";
            String intlReachAirport="";
            List<String> area1= null;
            List<String> area2= null;
            /*List<String> area3 = null;
            List<String> area4 =null;
            */
            
            /*int xingchengs;
            if(segmentCount>0&&segmentCount<=4){
                xingchengs=1;
            }else if(segmentCount>4&&segmentCount<=8){
                xingchengs=2;
            }else if(segmentCount>8&&segmentCount<=12){
                xingchengs=3;
            }else{
                xingchengs=4;
            }*/
            
            /*for (int j = 0; j < tiatis.size();j++) {
                TIntlAirTicketItinerary tiati=tiatis.get(j);
                if(j==0){
                    intlDepartureAirport=tiati.getIntlDepartureAirport();
                     area3=this.isAirportArea(intlDepartureAirport);
                }
                if(j==(tiatis.size()-1)){
                    intlReachAirport=tiati.getIntlReachAirport();
                     area4=this.isAirportArea(intlReachAirport);
                }
            }*/
            
            if("2".equals(tacorg.getIntlServiceStatus())){
                TIntlTravelItinerary passengers=null;
                if("1".equals(servicefees.get(0).getNationalCalcServiceFeeType())){//按机票收取
                    a=2;
                }else if("2".equals(servicefees.get(0).getNationalCalcServiceFeeType())){//按航段收取
                    a=1;
                }
                /*for (TIntlAirTicketItinerary tiati: tiatis) {
                    intlDepartureAirport=tiati.getIntlDepartureAirport();
                    area1=this.isAirportArea(intlDepartureAirport);
                    intlReachAirport=tiati.getIntlReachAirport();
                    area2=this.isAirportArea(intlReachAirport);
                    if(area1.size()!=0&&area2.size()!=0){
                if("1".equals(area1.get(0)) || "1".equals(area2.get(0))){
                    a=1;//非亚洲(所有行程中只要有一个出发城市或者目的城市不属于亚洲则属于非亚洲)
                    break;
                }else{
                    a=2;//亚洲(所有行程中出发城市和目的城市全部属于亚洲则属于亚洲)
                    continue;
                }   
              }
            }*/
        
                if(a!=0){
                if(a==2){//按机票收取
                    for (int i = 0; i < psgList.size(); i++) {
                        passengers=psgList.get(i);
                        passengers.setServiceFee(0d);//客服预订服务费
                        passengers.setNightFee(0d);//夜间服务费
                        for (TIntlServicefee jipiaoServicefee : servicefees) {
                            
                            if(this.isHaveArea(areaList, jipiaoServicefee.getToarea())){
                                /*if("1".equals(jipiaoServicefee.getNationalServiceFeeType())){//定额
                                    
                                    passengers.setServiceFee(jipiaoServicefee.getNationalServiceFee()==null?0:jipiaoServicefee.getNationalServiceFee()*xingchengs);                        
                                }else{//百分比
                                    passengers.setServiceFee(jipiaoServicefee.getNationalServiceFee()==null?0:jipiaoServicefee.getNationalServiceFee()*0.01*passengers.getIntlTicketPrice()*xingchengs);
                                }
                                
                                if(night){
                                    if("1".equals(jipiaoServicefee.getNationalNightFeeType())){//定额
                                        passengers.setNightFee(jipiaoServicefee.getNationalNightFee()==null?0:jipiaoServicefee.getNationalNightFee()*xingchengs);
                                    }else{//百分比
                                        passengers.setNightFee(jipiaoServicefee.getNationalNightFee()==null?0:jipiaoServicefee.getNationalNightFee()*0.01*passengers.getIntlTicketPrice()*xingchengs);
                                    }
                                }*/
                                
                                Double nationalServiceFee = jipiaoServicefee.getNationalServiceFee();
                                if("1".equals(jipiaoServicefee.getNationalServiceFeeType())){//定额
                                    passengers.setServiceFee(nationalServiceFee==null?0:nationalServiceFee);                           
                                }else{//百分比
                                    passengers.setServiceFee(nationalServiceFee==null?0:nationalServiceFee*0.01*passengers.getIntlTicketPrice());
                                }
                                if(night){
                                    Double nationalNightFee = jipiaoServicefee.getNationalNightFee();
                                    if("1".equals(jipiaoServicefee.getNationalNightFeeType())){//定额
                                        passengers.setNightFee(nationalNightFee==null?0:nationalNightFee);
                                    }else{//百分比
                                        passengers.setNightFee(nationalNightFee==null?0:nationalNightFee*0.01*passengers.getIntlTicketPrice());
                                    }
                                }
                            }
                        }
                        totalServiceFee+=passengers.getServiceFee();
                        totalServiceFee+=passengers.getNightFee();
                        comDao.executeUpdate(passengers);
                    };
                }
                if(a==1){//按航段收取
                    Double chupiaofuwufei=0.00;
                    Double nightfee=0.00;
                    for (int i = 0; i < psgList.size(); i++) {
                        passengers=psgList.get(i);
                        passengers.setServiceFee(0d);//客服预订服务费
                        passengers.setNightFee(0d);//夜间服务费
                        chupiaofuwufei=0.00;
                        nightfee=0.00;
                        for (TIntlAirTicketItinerary tiati: tiatis) {
                            area1=this.isAirportArea(tiati.getIntlDepartureAirport());
                            area2=this.isAirportArea(tiati.getIntlReachAirport());
                        for (TIntlServicefee hangduanServicefee : servicefees) {
                            /*if(!area1.equals(hangduanServicefee.getStartarea()) && !area2.equals(hangduanServicefee.getToarea())){*/    //修改前
                            if(!area1.contains(hangduanServicefee.getStartarea())&& !area2.contains(hangduanServicefee.getToarea())){   //修改后
                                //passengers.setServiceFee(hangduanServicefee.getNationalServiceFee()==null?0:hangduanServicefee.getNationalServiceFee()*segmentCount);                 
                                chupiaofuwufei+=hangduanServicefee.getNationalServiceFee();
                                if(night){
                                    //passengers.setNightFee(hangduanServicefee.getNationalNightFee()==null?0:hangduanServicefee.getNationalNightFee()*segmentCount);
                                    nightfee+=hangduanServicefee.getNationalNightFee();
                                }
                            }
                        }
                            
                      }
                        passengers.setServiceFee(chupiaofuwufei);
                        passengers.setNightFee(nightfee);
                        totalServiceFee+=passengers.getServiceFee()==null?0:passengers.getServiceFee();
                        totalServiceFee+=passengers.getNightFee()==null?0:passengers.getNightFee();
                        comDao.executeUpdate(passengers);
                    }
                }
            }
            }
            tOrde.setIntlOrdernoPrice(comDao.add(tOrde.getIntlOrdernoPrice(),totalServiceFee));
            tOrde.setTotalServiceFee(totalServiceFee);
            comDao.executeUpdate(tOrde);
    }
    
    public List<String> isAirportArea(String airports) {
        String sql="select isasia from t_ac_airarea  where isintlservicefee='2' and airports "
                  +" like '%"+airports+"%' ";
        List<String> isasias=(List<String>)comDao.queryBySQL(sql, null);
        return isasias;
//        return comDao.queryBySQLForObject(sql,null,String.class);
//        return  this.dao.createSQLQuery(sql).list();
    }
    
    //判断区域是否为非亚洲和亚洲，因为数据库字段历史遗留问题toArea=1为亚洲，arealist.value=1为非亚洲
    //逻辑：如果集合包含非亚洲的，服务费必须返回非亚洲设置的服务费；不含非亚洲的，就返回亚洲的服务费
    public boolean isHaveArea(List<String> areaList,String toArea){
        boolean haveFZ=false;//havaFZ=true标识 有非亚洲节点
        //areaList集合 1是非亚洲  2是亚洲地区
        for (int i = 0; i < areaList.size(); i++) {
              if(areaList.get(i)!=null && "1".equals(areaList.get(i))){
                  haveFZ=true;
              }
        }
        if(haveFZ && !toArea.equals("1")){
            return true;
        }
        
        if(!haveFZ && toArea.equals("1")){
            return true;
        }
//      for (int i = 0; i < areaList.size(); i++) {
//        if(!toArea.equals(areaList.get(i))){
//            return true;
//        }
//      }
        return false;
    }
}
