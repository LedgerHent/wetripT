package com.viptrip.intlAirticketPC.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

import com.viptrip.intlAirticketPC.model.flightModels.HangBan;
import com.viptrip.intlAirticketPC.model.flightModels.HangCheng;
import com.viptrip.intlAirticketPC.service.impl.PCFlightIntlServiceImpl;
import com.viptrip.util.DateUtil;
import com.viptrip.yeego.intlairticket.model.Request_QueryWebFlightsI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.model.base.output.OutputResult;

public class HangChengUtil {
	public Request_QueryWebFlightsI_1_0 request;
	public Response_QueryWebFlightsI_1_0 repson;
	public Boolean isGoAndBack;
	public String shortRule = "1";
	public String shortType = "1";
	public String baseKey="";
	/**
	 * 
	 * @param repson 接口返回数据
	 * @param isGoAndBack 是否往返  true:是  false:否
	 * @param shortRule 排序规则 1.按照出发时间排2.按照价格排序
	 * @param shortType 排序类型 1.降序 2.升序
	 */
	public HangChengUtil(Request_QueryWebFlightsI_1_0 request,Response_QueryWebFlightsI_1_0 repson,Boolean isGoAndBack,String shortRule,String shortType){
		this.request=request;
		this.repson = repson;
		this.isGoAndBack = isGoAndBack;
		this.shortRule = shortRule;
		this.shortType = shortType;
	}
	
	public HangChengUtil(Response_QueryWebFlightsI_1_0 repson,Boolean isGoAndBack,String shortRule,String shortType,String baseKey){
		this.request=null;
		this.repson = repson;
		this.isGoAndBack = isGoAndBack;
		this.shortRule = shortRule;
		this.shortType = shortType;
		this.baseKey=baseKey;
	}
	
	public List<HangCheng> getMoreCangweiList(
			OutputResult<Response_QueryWebFlightsI_1_0,String> resq) {
		
		Hashtable<String, Hashtable<String,Object[]>> flgithPrices_=resq.getResultObj().flgithPrices.get("F1");
		List<HangCheng> hangchengList=new ArrayList<HangCheng>();
		HangCheng hangcheng=null;
		for (Entry<String, Hashtable<String, Object[]>> et : flgithPrices_.entrySet()) {
			Object[] objs = (Object[]) et.getValue().get("ADT");
			hangcheng=new HangCheng();

			hangchengList.add(hangcheng);
		}
		return hangchengList;
	}

	
	public List<HangCheng> getMoreHangChengList(){
		Hashtable<String,Hashtable<String,Object[]>> flights = repson.flights;
		Hashtable<String, String[]> airLinesTable=repson.airLines;
		Hashtable<String, String[]> airPortsHt=repson.airPortsHt;
		String ticketGroup = repson.ticketGroup;
		Hashtable<String, Hashtable<String,Hashtable<String,Object[]>>> flgithPrices_=repson.flgithPrices;
		Map<String,HangCheng> hangChengMap = new HashMap<String,HangCheng>();
		Set<String> keys = flights.keySet();
		
		for(String key :keys){
			Hashtable<String,Object[]> value = flights.get(key);
			//构造预定用KEY
			StringBuffer optionCommenKey=new StringBuffer().append("");
			if(!isGoAndBack){
				Object[] qu = value.get("S1");
				optionCommenKey.append("OW_~S1");
				optionKey_part(airLinesTable, airPortsHt, optionCommenKey, qu,key);
				HangCheng qu_hangcheng = formatHangcheng(qu);//去程
				qu_hangcheng.setSubmitOption(optionCommenKey.toString());
				//如果非往返，则将价格信息赋值，否则放到回程list对应的值中
				formatPriceAndCangwei(key, qu_hangcheng,null,ticketGroup,flgithPrices_);
				String airline_key = "";
				for(HangBan hangban :qu_hangcheng.getHangbanList()){
					airline_key += hangban.getAirline()+",";
				}
				
				hangChengMap.put(airline_key, qu_hangcheng);
			}else{
				Object[] qu = value.get("S1");
				optionCommenKey.append("RT_~S1");
				optionKey_part(airLinesTable, airPortsHt, optionCommenKey, qu,key);
				HangCheng qu_hangcheng = formatHangcheng(qu);//去程
				String airline_key = "";
				for(HangBan hangban :qu_hangcheng.getHangbanList()){
					airline_key += hangban.getAirline()+",";
				}
				Object[] hui = value.get("S2");
				optionCommenKey.append("~S2");
				optionKey_part(airLinesTable, airPortsHt, optionCommenKey, hui,key);
				
				HangCheng hui_hangcheng = formatHangcheng(hui);
				hui_hangcheng.setSubmitOption(optionCommenKey.toString());
				formatPriceAndCangwei(key, hui_hangcheng,qu_hangcheng,ticketGroup,flgithPrices_);
				//hui_hangcheng.calculateZNumber(qu_hangcheng);//计算往返 余座
				
				if(!hangChengMap.containsKey(airline_key)){
					hangChengMap.put(airline_key, qu_hangcheng);
					List<HangCheng> huichengList = new ArrayList<HangCheng>();
					huichengList.add(hui_hangcheng);
					qu_hangcheng.setHuichengList(huichengList);
				}else{
					qu_hangcheng = hangChengMap.get(airline_key);
					qu_hangcheng.getHuichengList().add(hui_hangcheng);
				}
			}
			System.out.println(optionCommenKey.toString());
		}
		List<HangCheng> huichengList = new ArrayList<HangCheng>();
		List<HangCheng> huichengList2 = new ArrayList<HangCheng>();
		Iterator<HangCheng> iterator = hangChengMap.values().iterator();
		while (iterator.hasNext()) {
			HangCheng next = iterator.next();
			next.splitJointFlightKey(next);//拼接KEY
			if(isGoAndBack){
				//如果是往返，则对所以返程按照价格进行排序，并将最小价格给hangcheng对象
				Collections.sort(next.getHuichengList(), new Comparator<HangCheng>() {
					@Override
					public int compare(HangCheng o1, HangCheng o2) {
						return o1.getTotalPrice().compareTo(o2.getTotalPrice());
					}
				});
				//将最小价格给hangcheng对象，后面排序使用
				next.setTotalPrice(next.getHuichengList().get(0).getTotalPrice());
			}
			huichengList.add(next);
		}
		if(isGoAndBack){
			huichengList2=huichengList.get(0).getHuichengList().get(0).getMoreHangchengList();
		}else{
			huichengList2=huichengList.get(0).getMoreHangchengList();
		}
		//排序
		Collections.sort(huichengList2, new Comparator<HangCheng>() {
			@Override
			public int compare(HangCheng o1, HangCheng o2) {
					Double totalPrice =Double.valueOf(o1.getTotalPrice());
					Double totalPrice2 =Double.valueOf(o2.getTotalPrice());
					return totalPrice.compareTo(totalPrice2);
			}
		});
		System.out.println(JSONArray.fromObject(huichengList2).toString());
		return huichengList2;
	}
	
	public List<HangCheng> getHangChengList(){
		Hashtable<String,Hashtable<String,Object[]>> flights = repson.flights;
		Hashtable<String, String[]> airLinesTable=repson.airLines;
		Hashtable<String, String[]> airPortsHt=repson.airPortsHt;
		String ticketGroup = repson.ticketGroup;
		Map<String,HangCheng> hangChengMap = new HashMap<String,HangCheng>();
		Set<String> keys = flights.keySet();
		
		for(String key :keys){
			Hashtable<String,Object[]> value = flights.get(key);
			//构造预定用KEY
			StringBuffer optionCommenKey=new StringBuffer().append("");
			if(!isGoAndBack){
				Object[] qu = value.get("S1");//去程
				optionCommenKey.append("OW_~S1");
				optionKey_part(airLinesTable, airPortsHt, optionCommenKey, qu,key);
				HangCheng qu_hangcheng = formatHangcheng(qu);//去程
				qu_hangcheng.setSubmitOption(optionCommenKey.toString());//必须放在价格方法之前赋值
				//如果非往返，则将价格信息赋值，否则放到回程list对应的值中
				formatPriceAndCangwei(key, qu_hangcheng,null,ticketGroup);
				String airline_key = "";
				for(HangBan hangban :qu_hangcheng.getHangbanList()){
					airline_key += hangban.getAirline()+",";
				}
				
				hangChengMap.put(airline_key, qu_hangcheng);
			}else{
				Object[] qu = value.get("S1");
				optionCommenKey.append("RT_~S1");
				optionKey_part(airLinesTable, airPortsHt, optionCommenKey, qu,key);
				HangCheng qu_hangcheng = formatHangcheng(qu);//去程
				String airline_key = "";
				for(HangBan hangban :qu_hangcheng.getHangbanList()){
					airline_key += hangban.getAirline()+",";
				}
				Object[] hui = value.get("S2");
				optionCommenKey.append("~S2");
				optionKey_part(airLinesTable, airPortsHt, optionCommenKey, hui,key);
				
				HangCheng hui_hangcheng = formatHangcheng(hui);
				hui_hangcheng.setSubmitOption(optionCommenKey.toString());
				formatPriceAndCangwei(key, hui_hangcheng,qu_hangcheng,ticketGroup);
				hui_hangcheng.calculateZNumber(qu_hangcheng);//计算往返 余座
				
				if(!hangChengMap.containsKey(airline_key)){
					hangChengMap.put(airline_key, qu_hangcheng);
					List<HangCheng> huichengList = new ArrayList<HangCheng>();
					huichengList.add(hui_hangcheng);
					qu_hangcheng.setHuichengList(huichengList);
				}else{
					qu_hangcheng = hangChengMap.get(airline_key);
					qu_hangcheng.getHuichengList().add(hui_hangcheng);
				}
			}
			System.out.println(optionCommenKey.toString());
		}
		List<HangCheng> huichengList = new ArrayList<HangCheng>();
		Iterator<HangCheng> iterator = hangChengMap.values().iterator();
		while (iterator.hasNext()) {
			HangCheng next = iterator.next();
			next.splitJointFlightKey(next);//拼接KEY
			if(isGoAndBack){
				//如果是往返，则对所以返程按照价格进行排序，并将最小价格给hangcheng对象
				Collections.sort(next.getHuichengList(), new Comparator<HangCheng>() {
					@Override
					public int compare(HangCheng o1, HangCheng o2) {
						return o1.getTotalPrice().compareTo(o2.getTotalPrice());
					}
				});
				//将最小价格给hangcheng对象，后面排序使用
				next.setTotalPrice(next.getHuichengList().get(0).getTotalPrice());
			}
			huichengList.add(next);
		}
		
		//排序
		Collections.sort(huichengList, new Comparator<HangCheng>() {
			@Override
			public int compare(HangCheng o1, HangCheng o2) {
				if("1".equals(shortRule)){
					if("1".equals(shortType))
						return o1.getHangBan_fl().getStartDateTime().compareTo(o2.getHangBan_fl().getStartDateTime());
					else
						return o2.getHangBan_fl().getStartDateTime().compareTo(o1.getHangBan_fl().getStartDateTime());
				}else if("2".equals(shortRule)){
					if("1".equals(shortType))
						return o1.getTotalPrice().compareTo(o2.getTotalPrice());
					else
						return o2.getTotalPrice().compareTo(o1.getTotalPrice());
				}else
					return 0;
			}
		});
		System.out.println(JSONArray.fromObject(huichengList).toString());
		return huichengList;
	}
	
	private String  getOptionFromPriceAndCangwei(String key,Hashtable<String, String[]> airLinesTable,boolean isBack){
		Hashtable<String, Hashtable<String, Object[]>> ht = repson.flgithPrices
				.get(key);
		StringBuffer submitOption=new StringBuffer();
		for (Entry<String, Hashtable<String, Object[]>> et : ht.entrySet()) {
			Object[] objs = (Object[]) et.getValue().get("ADT");
			String totalPrice=obj2str(objs[0]);
			String priceBase=String.valueOf(objs[8]);
			String mainCarrier=String.valueOf(objs[12]);
			String baseFare=obj2str(((Object[]) et.getValue().get("BaseFare"))[0]);
			String totalFase=obj2str(((Object[]) et.getValue().get("TotalFare"))[0]);
			String cangwei=String.valueOf(objs[4]);
			
			cangwei=isBack?cangwei.split("\\/")[1]:cangwei.split("\\/")[0];
			cangwei=cangwei.replace(",","/");
			submitOption.append("^" + cangwei+ "*"
					+ totalPrice + "*"
					+ baseFare + "*"
					+ totalFase + "*"
					+ mainCarrier + "*"
					+ airLinesTable.get(mainCarrier)[0] + "*"
					+ priceBase);
			break;
		}
		return submitOption.toString();
	}
	private void optionKey_part(Hashtable<String, String[]> airLinesTable,
			Hashtable<String, String[]> airPortsHt,
			StringBuffer optionCommenKey, Object[] qu,String key) {
		ArrayList<Object> o1 = (ArrayList<Object>) qu[0];
		String avNumber=obj2str(o1.get(0));
		ArrayList<Object> o2 = (ArrayList<Object>) qu[1];
		for (int i = 0; i < o2.size(); i++) {
			ArrayList<Object> o2_ = (ArrayList<Object>) o2
					.get(i);
			String carrier=obj2str(o2_.get(0));
			String airline=obj2str(o2_.get(1));
			String airlineStr=obj2str(airLinesTable.get(carrier)[0]);
			String orgcity=obj2str(o2_.get(2));
			String orgcityStr=airPortsHt.get(orgcity)[1];
			String orgcityStr2=airPortsHt.get(orgcity)[2];
			
			String Detcity=obj2str(o2_.get(3));
			String DetcityStr=airPortsHt.get(Detcity)[1];
			String DetcityStr2=airPortsHt.get(Detcity)[2];
			String StartDate=obj2str(o2_.get(4));
			String StarttimeStr=obj2str(o2_.get(5));
			String EndDate=obj2str(o2_.get(6));
			String EndtimeStr=obj2str(o2_.get(7));
			String FlightTime=obj2str(o2_.get(8));
			String Planetype=obj2str(o2_.get(9));
			// flightCangwei.get(10],经停对象数组
			// String StopoverCity((String[])flightCangwei.get(10]);
			String stopoverCity="";
			String Orgterm=obj2str(o2_.get(11));
			String Detterm=obj2str(o2_.get(12));
			String food="";
			if (StringUtils.isEmpty(obj2str(o2_.get(13)))) {
				food="Y";
			} else {
				food="N";
			}
			String Mileage=obj2str(o2_.get(14));
			String StartDay=obj2str(o2_.get(16));
			String yule="";
			if (StringUtils.isEmpty(obj2str(o2_.get(17)))
					|| "0".equals(obj2str(o2_.get(17)))) {
				yule="0";
			} else {
				yule="1";
			}
			String eTicket="";
			if (StringUtils.isEmpty(obj2str(o2_.get(18)))
					|| "0".equals(obj2str(o2_.get(18)))) {
				eTicket="0";
			} else {
				eTicket="1";
			}
			String airlineshare=obj2str(o2_.get(19));
//					StringBuffer submitOption=new StringBuffer();
			optionCommenKey.append("@*" + key + "*"+avNumber + "*"
					+ carrier + "*"
					+ airline + "*"
					+ airlineStr + "*"
					+ orgcity + "*"
					+ orgcityStr + "*"
					+ Detcity + "*"
					+ DetcityStr+ "*"
					+ StartDate+ "*"
					+ StarttimeStr+ "*"
					+ EndDate+ "*"
					+ EndtimeStr+ "*"
					+ FlightTime+ "*"
					+ Planetype+ "*"
					+ stopoverCity+ "*"
					+ Orgterm+ "*"
					+ Detterm+ "*"
					+ food+ "*"
					+ Mileage+ "*"
					+ StartDay+ "*"
					+ yule+ "*"
					+ airlineshare+ "*"
					+ orgcityStr2+ "*"
					+ DetcityStr2+ "*");
		}
		
		String optionFromPriceAndCangwei = getOptionFromPriceAndCangwei(key, airLinesTable,optionCommenKey.toString().contains("~S2"));
		optionCommenKey.append(optionFromPriceAndCangwei);
	}
	
	/**
	 * 仓位、价格赋值
	 * @param key
	 * @param hangchegn
	 * @param ticketGroup 
	 */
	private void formatPriceAndCangwei(String key,HangCheng hangchegn,HangCheng wangfanGo, String ticketGroup){
		Hashtable<String, Hashtable<String, Object[]>> ht = repson.flgithPrices
				.get(key);
		DecimalFormat    df   = new DecimalFormat("######0.00"); 

		for (Entry<String, Hashtable<String, Object[]>> et : ht.entrySet()) {
			Object[] objs = (Object[]) et.getValue().get("ADT");
			hangchegn.setTotalPrice(obj2str(Math.ceil(Double.parseDouble(objs[0].toString())
					-Double.parseDouble(df.format((Double.parseDouble(objs[14].toString())*0.01*Double.parseDouble(objs[0].toString()))))
					-Double.parseDouble(objs[15].toString())
					-Double.parseDouble(objs[16].toString())
					+Double.parseDouble(objs[17].toString())
					+Double.parseDouble(objs[18].toString())
					)));
			hangchegn.setTotalTaxPrice(obj2str(((Object[]) et.getValue().get("TotalFare"))[0]));
			hangchegn.setTuiGaiSign(obj2str(objs[6]));
			hangchegn.getHangBan_fl().setCangwei(obj2str(objs[4]));
			List<String> exps=new ArrayList<String>();
			if(wangfanGo!=null){
				for(HangBan hb:wangfanGo.getHangbanList()){
					exps.add(hb.getCangwei());
				}
			}
			for(HangBan hb:hangchegn.getHangbanList()){
				exps.add(hb.getCangwei());
			}
			hangchegn.setSeatsLeft(PCFlightIntlServiceImpl.getSeatsLeftByCabin(exps,
					hangchegn.getHangBan_fl().getCangwei()));
			hangchegn.setMapKey(getMapKey(hangchegn,wangfanGo,ticketGroup));
			break;
		}
		
	}
	/**
	 * 仓位、价格赋值
	 * @param key
	 * @param hangchegn
	 */
	private void formatPriceAndCangwei(String key,HangCheng hangchegn,HangCheng wangfanGo,String ticketGroup,
			Hashtable<String, Hashtable<String,Hashtable<String,Object[]>>> flgithPrices_){

		Hashtable<String, Hashtable<String, Object[]>> ht = flgithPrices_.get(key);
		HangCheng hangchegn2=null;
		DecimalFormat    df   = new DecimalFormat("######0.00"); 
		List<HangCheng> morehangchengList=new ArrayList<HangCheng>();
		for (Entry<String, Hashtable<String, Object[]>> et : ht.entrySet()) {
			Object[] objs = (Object[]) et.getValue().get("ADT");
			try {
				hangchegn2=hangchegn.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			hangchegn2.setTuiGaiSign(obj2str(objs[6]));
			hangchegn2.setTotalPrice(obj2str(Math.ceil(Double.parseDouble(objs[0].toString())
					-Double.parseDouble(df.format((Double.parseDouble(objs[14].toString())*0.01*Double.parseDouble(objs[0].toString()))))
					-Double.parseDouble(objs[15].toString())
					-Double.parseDouble(objs[16].toString())
					+Double.parseDouble(objs[17].toString())
					+Double.parseDouble(objs[18].toString())
					
					)));
			hangchegn2.setTotalTaxPrice(obj2str(((Object[]) et.getValue().get("TotalFare"))[0]
					));
			String baseFare=obj2str(((Object[]) et.getValue().get("BaseFare"))[0]);
			
			HangBan hangBan_fl=null;
			try {
				hangBan_fl = hangchegn2.getHangBan_fl().clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hangBan_fl.setCangwei(obj2str(objs[4]));
			hangchegn2.setHangBan_fl(hangBan_fl);
			List<String> exps=new ArrayList<String>();
			if(wangfanGo!=null){
				for(HangBan hb:wangfanGo.getHangbanList()){
					exps.add(hb.getCangwei());
				}
			}
			for(HangBan hb:hangchegn2.getHangbanList()){
				exps.add(hb.getCangwei());
			}
			
			String newSubmitOption = getNewSubmitOption(hangchegn2, objs,
					baseFare);
			hangchegn2.setSubmitOption(newSubmitOption);
			hangchegn2.setSeatsLeft(PCFlightIntlServiceImpl.getSeatsLeftByCabin(exps,
					hangBan_fl.getCangwei()));
			hangchegn2.setMapKey(getMapKey(hangchegn2,wangfanGo,ticketGroup));
			morehangchengList.add(hangchegn2);
		}
		hangchegn.setMoreHangchengList(morehangchengList);
	}

	private String getNewSubmitOption(HangCheng hangchegn2, Object[] objs,
			String baseFare) {
		String hangchegn2options=hangchegn2.getSubmitOption();
		String[] split = hangchegn2options.split("~S2");
		String optionLeft=split[0];
		String rightOption="";
		String leftOption=optionLeft.split("\\^")[0];
		String leftOption2=optionLeft.split("\\^")[1];//J*4480*4480*4958*CA*国际航空*JOWDD9
		String[] cangweis=obj2str(objs[4]).split("\\/");
//		String newCangwei=obj2str(objs[4]).replace(",", "/");
		StringBuffer newRightOption=new StringBuffer().append("^" + cangweis[0].replace(",", "/") + "*"
				+ hangchegn2.getTotalPrice() + "*"
				+ baseFare + "*"
				+ hangchegn2.getTotalTaxPrice() + "*");
		newRightOption.append(leftOption2.split("\\*")[4]+ "*"
				+leftOption2.split("\\*")[5]+ "*"
				+leftOption2.split("\\*")[6]);
		if(split.length>1){
			String optionRight=split[1];
			rightOption="~S2"+optionRight.split("\\^")[0];
			String rightOption2=optionRight.split("\\^")[1];//J*4480*4480*4958*CA*国际航空*JOWDD9
			StringBuffer newRightOption2=new StringBuffer().append("^" + cangweis[1].replace(",", "/") + "*"
					+ hangchegn2.getTotalPrice() + "*"
					+ baseFare + "*"
					+ hangchegn2.getTotalTaxPrice() + "*");
			newRightOption2.append(rightOption2.split("\\*")[4]+ "*"
					+rightOption2.split("\\*")[5]+ "*"
					+rightOption2.split("\\*")[6]);
			rightOption=rightOption+newRightOption2.toString();
		}
		String newSubmitOption = leftOption+newRightOption.toString()+rightOption;
		return newSubmitOption;
	}
	//BJS_SIN_ADT_RT_2017-03-26_2017-04-02|CZ3122_CZ8489*CZ354_CZ3105
	private String getMapKey(HangCheng hangchegn,HangCheng wangfanGo,String ticketGroup){
		StringBuilder sb=new StringBuilder();
		if(request!=null){
			sb.append(request.orgcity);
			sb.append("_");
			sb.append(request.detcity);
			sb.append("_");
			sb.append((request.psgCode ==null || "".equals(request.psgCode))?"ADT":request.psgCode);
			sb.append("_");
			sb.append(request.travelType);
			sb.append("_");
			sb.append(request.starttime);
			if(wangfanGo!=null){
				sb.append("_");
				sb.append(request.arrvitime);
			}
			if(!"".equals(ticketGroup)){
				sb.append("_");
				sb.append(ticketGroup);
			}else{
				sb.append("_");
				sb.append("A");
			}
			
		}else{
			sb.append(baseKey);
		}
		sb.append("|");
		if(wangfanGo!=null){
			for(int i=0;i<wangfanGo.getHangbanList().size();i++){
				HangBan hb=wangfanGo.getHangbanList().get(i);
				if(i>0){
					sb.append("_");
				}
				sb.append(hb.getAirline());
			}
			sb.append("*");
		}
		for(int i=0;i<hangchegn.getHangbanList().size();i++){
			HangBan hb=hangchegn.getHangbanList().get(i);
			if(i>0){
				sb.append("_");
			}
			sb.append(hb.getAirline());
		}
		
		
		return sb.toString();
	}
	

	/**
	 * 根据 s1（去程）或s2(回程)  构建一个航程Obj
	 * @param qu
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private HangCheng formatHangcheng(Object[] qu) {
		HangCheng hangcheng = new HangCheng();
		ArrayList<Object> o1 = (ArrayList<Object>) qu[0]; 
		formatHangBan_fl(o1,hangcheng);
		ArrayList<Object> o2 = (ArrayList<Object>) qu[1];
		List<HangBan> hangBanList = new ArrayList<HangBan>();
		for(Object o: o2){
			ArrayList<Object> o3 = (ArrayList<Object>) o;
			hangBanList.add(formatHangBanList(o3));
		}
		hangcheng.setHangbanList(hangBanList);
		return hangcheng;
	}
	
	/**
	 * 格式化航班  对应 出发地--到达地  的航程信息
	 * @param o1
	 * @param hangcheng 
	 * @return
	 */
	private  void formatHangBan_fl(ArrayList<Object> o1, HangCheng hangcheng) {
		HangBan hangBan = new HangBan();
		hangBan.setStartAirportCode(obj2str(o1.get(2)));//出发地机场代码
		hangBan.setOrgTerm(obj2str(o1.get(3)));//出发地机场航站楼
		hangBan.setStartDateTime(obj2str(o1.get(4))+" "+obj2str(o1.get(5)));//出发地起飞时间
		hangBan.setEndAirportCode(obj2str(o1.get(6)));//目的地机场代码
		hangBan.setDetTerm(obj2str(o1.get(7)));//目的地机场航站楼
		hangBan.setEndDateTime(obj2str(o1.get(8))+" "+obj2str(o1.get(9)));//目的地到达时间
		hangBan.setTravlTime(obj2str(o1.get(11)));//航程时间
		hangBan.setTravlTime(DateUtil.getDiffTimeStr(Long.valueOf(Integer.valueOf(hangBan.getTravlTime())*60000)));
		//***设置航程的中转次数
		hangcheng.setTransitCount(obj2str(o1.get(10)));
		hangcheng.setHangBan_fl(formatChinese(hangBan));
	}
	
	/**
	 * 格式化航班list信息  对应每个航段的行程信息，
	 * 如中转航程  则分为两个航段，1：出发地-中转地 2：中转地-到达地
	 * 如非中转航程，则只有一个航段信息，且与格式化航班信息得到的内容大致相同
	 * @param o3.get(index)
	 * @returnDate
	 */
	private  HangBan formatHangBanList(ArrayList<Object> o3) {
		HangBan hangBan = new HangBan();
		hangBan.setCarrier(obj2str(o3.get(0)));//航司二字码
		hangBan.setAirline(obj2str(o3.get(1)));//航班号
		hangBan.setStartAirportCode(obj2str(o3.get(2)));//起飞机场代码
		hangBan.setEndAirportCode(obj2str(o3.get(3)));//到达机场代码
		hangBan.setStartDateTime(obj2str(o3.get(4))+" "+obj2str(o3.get(5)));//起飞时间
		hangBan.setEndDateTime(obj2str(o3.get(6))+" "+obj2str(o3.get(7)));//到达时间
		hangBan.setTravlTime(obj2str(o3.get(8)));//行程时间
		hangBan.setTravlTime(DateUtil.getDiffTimeStr(Long.valueOf(Integer.valueOf(hangBan.getTravlTime())*60000)));
		hangBan.setPlaneType(obj2str(o3.get(9)));//机型
		hangBan.setOrgTerm(obj2str(o3.get(11)));//起飞航站楼
		hangBan.setDetTerm(obj2str(o3.get(12)));//到达航站楼
		hangBan.setCangwei(obj2str(o3.get(15)));//仓位
		
		
		return formatChinese(hangBan);
	}
	
	/**
	 * obj转字符串
	 * @param object
	 * @return
	 */
	private  String obj2str(Object object) {
		if(object!=null){
			return object.toString();
		}else{
			return null;
		}
	}
	/**
	 * 根据航班中的二字码、机场代码等为对应的中文赋值
	 * @param hangBan
	 * @return
	 */
	private  HangBan formatChinese(HangBan hangBan) {
		String carrier = hangBan.getCarrier();
		if(carrier!=null&&!"".equals(carrier)){
			hangBan.setCarrierStr(obj2str(repson.airLines.get(carrier)[0]));
		}
		String startAirportCode = hangBan.getStartAirportCode();
		if(startAirportCode!=null&&!"".equals(startAirportCode)){
			hangBan.setOrgAirPortStr(obj2str(repson.airPortsHt.get(startAirportCode)[1]));
		}
		String endAirportCode = hangBan.getEndAirportCode();
		if(endAirportCode!=null&&!"".equals(endAirportCode)){
			hangBan.setDetAirPortStr(obj2str(repson.airPortsHt.get(endAirportCode)[1]));
		}
		return hangBan;
	}

}
