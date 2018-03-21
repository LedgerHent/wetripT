package com.viptrip.wetrip.entity;




public class MessageModel {
	private String name;//称呼  
	private String ydr;//预订人  
	private String sex;//性别
	private String orderno;//订单号
	private String origintime;//起飞时间
	private String destinationtime;//到达时间
	private String cangwei;//舱位
	private String fightnumber;//航班号
	private String orgterm;//起飞航站楼
	private String detterm;//到达航站楼
	private String panssengername;//乘机人姓名
	private String passengeridtype;//证件类型
	private String passengerid;//证件号码
	private String returncost;//退票费
	private String updatecost;//改期费
	private String origincity;//起飞机场
	private String destinationcity;//到达机场
	private String oricity;//起飞
	private String descity;//到达城市
	private String websiteurl;//站内网址，给站内消息用
	private String end;//结尾
	private String totalPrice;//总价
	
	private String originDate;//出发日期
	
	private String destinationDate;//到达日期
	
	private String updateStatusTime;//订单取消时间
	
	private String seiviceTel;//400
	
	private String tpDate;//退款期限
	
	private String notifyMen;//通知人
	private String notifyTel;//通知人手机号
	private String adminMen;//管理员
	private String adminTel;//管理员手机号
    private String checkMen;//审核人
    private String checkTel; //审核人手机号
    private String subscribeMen;//预订人
    private String subscribeTel;//预订人手机号
    private String chengjiMen;//乘机人
    private String chengjiTel;//乘机人手机号
    private String payMethod;//付款方式
    private String isVerify;//是否需要审核
    private String orgname;
	
    private String airlineCompany;
    private String airline;
	/**
	 *  发送给审核人
	 * @param name 审核人名字
	 * @param ydr 预订人
	 * @param origintime 出发时间
	 * @param origincity 出发城市
	 * @param destinationcity 到达城市
	 * @param fightnumber 班次号
	 * @param panssengername 乘机人名字
	 * @param end 结束语
	 * @return MessageModel
	 */
	//尊敬的{name}先生/女士您好，贵公司员工{ydr}预订了{origintime}{origincity}至{destinationcity}{fightnumber}次航班，乘机人：{panssengername}因航空公司随时可能取消未出票的座位，请您尽快登录弘景商旅系统www.viptrip365.com进行审核，谢谢！
	public MessageModel ToCheckmen(String name,String ydr,String origintime,String originDate,String origincity,String destinationcity,String fightnumber,String cangwei,String totalPrice,String  panssengername ) {
		MessageModel m=new MessageModel();
		m.name=name;
		m.ydr=ydr;
		m.origintime=origintime;
		m.originDate=originDate;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.panssengername=panssengername;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		return m;
		}
	
	/***
	 * 预订成功给预订人(7个参数)
	 * @param name 预订人名字
	 * @param origincity 起飞城市
	 * @param destinationcity 到达城市
	 * @param fightnumber 航班号
	 * @param origintime 起飞时间
	 * @param destinationtime 到达时间
	 * @param panssengername 乘机人名字
	 * @return
	 */
	//尊敬的{ydr}先生/女士您好，您已成功预订{origincity}至{destinationcity}{fightnumber}次航班，起飞时间{origintime}，到达时间{destinationtime}，乘机人{panssengername}，该订单已提交贵公司管理员进行审核，谢谢！弘景商旅系统
	public MessageModel YDCG_to_YDR(String ydr,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String updateStatusTime ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.updateStatusTime=updateStatusTime;
		return m;
		}
	
	
	/**
	 * 发给所有客服的预订短信提醒
	 * @param ydr
	 * @param origincity
	 * @param destinationcity
	 * @param fightnumber
	 * @param origintime
	 * @param originDate
	 * @param destinationtime
	 * @param destinationDate
	 * @param panssengername
	 * @param cangwei
	 * @param totalPrice
	 * @param orderNo
	 * @return
	 */
	public MessageModel YDCFDXTZ(String ydr,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		return m;
		}
	
	/**
	 * 发给所有客服的短信发送成功提醒
	 * @param ydr
	 * @param origincity
	 * @param destinationcity
	 * @param fightnumber
	 * @param origintime
	 * @param originDate
	 * @param destinationtime
	 * @param destinationDate
	 * @param panssengername
	 * @param cangwei
	 * @param totalPrice
	 * @param orderNo
	 * @return
	 */
	public MessageModel SHDXTZ(String ydr,String checkMan,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.name=checkMan;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		return m;
		}
	
	
	/**
	 * 发给所有客服的短信发送成功提醒
	 * @param ydr
	 * @param origincity
	 * @param destinationcity
	 * @param fightnumber
	 * @param origintime
	 * @param originDate
	 * @param destinationtime
	 * @param destinationDate
	 * @param panssengername
	 * @param cangwei
	 * @param totalPrice
	 * @param orderNo
	 * @return
	 */
	public MessageModel CPDXTZ(String ydr,String checkMan,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.name=checkMan;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		return m;
		}
	
	/***
	 * 预订成功给预订人(7个参数)
	 * @param name 预订人名字
	 * @param origincity 起飞城市
	 * @param destinationcity 到达城市
	 * @param fightnumber 航班号
	 * @param origintime 起飞时间
	 * @param destinationtime 到达时间
	 * @param panssengername 乘机人名字
	 * @return
	 */
	//尊敬的{ydr}先生/女士您好，您已成功预订{origincity}至{destinationcity}{fightnumber}次航班，起飞时间{origintime}，到达时间{destinationtime}，乘机人{panssengername}，该订单已提交贵公司管理员进行审核，谢谢！弘景商旅系统
	public MessageModel YDCG_to_YD(String ydr,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String destinationtime, String  panssengername ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		return m;
		}
	/**
	 * 改期成功给乘机人的
	 * @param name 名字
	 * @param origincity 起飞城市
	 * @param destinationcity 到达城市
	 * @param fightnumber 班次号
	 * @param panssengername 乘机人
	 * @param updatecost 改期费
	 * @return
	 */
	//尊敬{name}先生/女士您好，您提交的{origincity}至{destinationcity}{fightnumber}航班的改期申请，贵公司管理员已审核确认，改期费{updatecost}元，弘景商旅系统已成功为您办理改期。谢谢！
	public MessageModel GQ_to_CJR(String ydr,String origincity,String oricity,
			String destinationcity,String descity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo,String orgterm,String detterm ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.oricity=oricity;
		m.destinationcity=destinationcity;
		m.descity=descity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		m.orgterm=orgterm;
		m.detterm=detterm;
		return m;
		}
	/**
	 * 出票之后发给乘机人的
	 * @param name 姓名
	 * @param origincity 起飞城市
	 * @param destinationcity  到达城市
	 * @param fightnumber 班次号
	 * @param origintime 起飞时间
	 * @param destinationtime 到达时间
	 * @param panssengername 乘机人
	 * @param passengeridtype 乘机人证件类型
	 * @param passengerid 乘机人证件id
	 * @return
	 */
	//尊敬的{name}先生/女士您好，您预订的{origincity}至{destinationcity}{fightnumber}次航班已成功为您出票，起飞时间{origintime}，到达时间{destinationtime}，乘机人{panssengername}{passengeridtype}{passengerid}，请提前90分钟到北京首都国际机场3号航站楼办理登机手续，弘景商旅系统祝您旅途愉快！
	public MessageModel CP_to_CJR(String ydr,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo,String orgterm,String detterm ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		m.orgterm=orgterm;
		m.detterm=detterm;
		return m;
	}
	
	
	/**
	 * 出票之后发给预订人的
	 * @param name 姓名
	 * @param origincity 起飞城市
	 * @param destinationcity  到达城市
	 * @param fightnumber 班次号
	 * @param origintime 起飞时间
	 * @param destinationtime 到达时间
	 * @param panssengername 乘机人
	 * @param passengeridtype 乘机人证件类型
	 * @param passengerid 乘机人证件id
	 * @return
	 */
	//尊敬的{name}先生/女士您好，您预订的{origincity}至{destinationcity}{fightnumber}次航班已成功为您出票，起飞时间{origintime}，到达时间{destinationtime}，乘机人{panssengername}{passengeridtype}{passengerid}，请提前90分钟到北京首都国际机场3号航站楼办理登机手续，弘景商旅系统祝您旅途愉快！
	public MessageModel CP_to_YDR(String ydr,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo,String orgterm,String detterm ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		m.orgterm=orgterm;
		m.detterm=detterm;
		return m;
	}
	/**
	 * 退票的时候发给乘机人的短信
	 * @param name 名字
	 * @param origintime 起飞时间
	 * @param origincity 起飞城市
	 * @param destinationcity 到达城市
	 * @param fightnumber 航班号
	 * @param panssengername 乘机人姓名
	 * @param returncost 退票费
	 * @return
	 */
	//尊敬{name}先生/女士您好，您提交的{origintime}{origincity}至{destinationcity}{fightnumber}次航班、乘机人{panssengername}的退票申请，贵公司管理员已审核确认，退票费{returncost}元，弘景商旅系统已成功为您办理退票。谢谢！
	public MessageModel TP_to_CJR(String ydr,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo,String tpDate ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origincity=origincity;
		m.oricity=oricity;
		m.destinationcity=destinationcity;
		m.descity=descity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		m.tpDate=tpDate;
		return m;
	}
	
	/**
	 * 审核不通过，发送给预订人
	 * @param ydr 预订人
	 * @param orderno 订单号
	 * @return
	 */
	//尊敬的{ydr}先生/女士您好，您的订单{orderno}审核未通过，请重新预订，或联系审核人！谢谢【凯撒商旅】
	public MessageModel SHBTG_to_YDR(String ydr,String checkMan,String origincity,
			String destinationcity,String fightnumber,
			String origintime,String originDate,String destinationtime,String destinationDate, String  panssengername,String cangwei,String totalPrice,String orderNo ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.name=checkMan;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.origintime=origintime;
		m.destinationtime=destinationtime;
		m.panssengername=panssengername;
		m.originDate=originDate;
		m.destinationDate=destinationDate;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderNo;
		return m;
		
	}
	/**
	 * 有退票申请，发给后台客服的站内消息
	 * @param orderno
	 * @return
	 */
	//各位工作人员请注意，订单{orderno}申请退票，请及时处理！
	public MessageModel TP_to_HTKF(String orderno,String panssengername){
		MessageModel m=new MessageModel();
		m.orderno=orderno;
		m.panssengername=panssengername;
		return m;
	}
	/**
	 * @param username
	 * @param orgname
	 * @return
	 */
	//{name}已新增{orgname}企业，请及时进行上线处理！
	public MessageModel QYXZ(String name, String orgname) {
		MessageModel m=new MessageModel();
		m.name=name;
		m.orgname=orgname;
		return m;
	}
	/**
	 * 有改期申请，发给后台客服的站内消息
	 * @param orderno
	 * @return
	 */
	//各位工作人员请注意，订单{orderno}申请改期，请及时处理！
	public MessageModel GQ_to_HTKF(String orderno,String panssengername){
		MessageModel m=new MessageModel();
		m.orderno=orderno;
		m.panssengername=panssengername;
		return m;
	} 
	/**
	 * 有改期申请，发给审核人站内消息
	 * @param orderno
	 * @return
	 */
	//尊敬的**先生/女士您好，乘机人：***，申请杭州至北京更改为*月*日MU5167航班，改期费**元，请回复“确认”代审或与4006020365联系，谢谢！【凯撒商旅】

	public MessageModel GQ_to_SHR(String orderno,String checkmen,String panssengername,String origincity,String destinationcity,String originDate,String fightnumber,String totalPrice,String seiviceTel){
		MessageModel m=new MessageModel();
		m.orderno=orderno;
		m.name=checkmen;
		m.panssengername=panssengername;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.originDate=originDate;
		m.fightnumber=fightnumber;
		m.totalPrice=totalPrice;
		m.seiviceTel=seiviceTel;
		return m;
	} 
	
	/**
	 * 有可以出票的订单，发给后台客服的站内消息
	 * @param orderno
	 * @return
	 */
	//各位工作人员请注意，订单{orderno}可出票，请及时处理！
	public MessageModel XCP_to_HTKF(String orderno){
		MessageModel m=new MessageModel();
		m.orderno=orderno;
		return m;
	} 
	
	
	
	/**
	 * 火车票出票发送给预订人
	 * @param orderno
	 * @param name
	 * @param ydr
	 * @param origintime
	 * @param originDate
	 * @param origincity
	 * @param destinationcity
	 * @param fightnumber
	 * @param cangwei
	 * @param totalPrice
	 * @param panssengername
	 * @return
	 */
	public MessageModel ToTrainIssuedSubscribe(String orderno,String ydr,String originDate,String origincity,String destinationcity,String fightnumber,String cangwei,String totalPrice,String  panssengername ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.originDate=originDate;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.panssengername=panssengername;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderno;
		return m;
		}
	
	/**
	 * 火车票退票发送给预订人
	 * @param orderno
	 * @param name
	 * @param ydr
	 * @param origintime
	 * @param originDate
	 * @param origincity
	 * @param destinationcity
	 * @param fightnumber
	 * @param cangwei
	 * @param totalPrice
	 * @param panssengername
	 * @return
	 */
	public MessageModel ToTrainRefundSubscribe(String orderno,String ydr,String originDate,String origincity,String destinationcity,String fightnumber,String cangwei,String totalPrice,String  panssengername,String seiviceTel ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.originDate=originDate;
		m.origincity=origincity;
		m.destinationcity=destinationcity;
		m.fightnumber=fightnumber;
		m.panssengername=panssengername;
		m.cangwei=cangwei;
		m.totalPrice=totalPrice;
		m.orderno=orderno;
		m.seiviceTel=seiviceTel;
		return m;
		}
	/**
	 * @param orgname2
	 * @return
	 */
	public MessageModel QYSX(String orgname2) {
		MessageModel m=new MessageModel();
		m.orgname=orgname2;
		return m;
	}
	
	/***
	 * 预订成功给预订人发提示短信(4个参数)
	 * @param ydr 预订人名字
	 * @param airlineCompany 航空公司
	 * @param airline 航班号
	 * @param origintime 出发时间
	 * @return
	 */
	//尊敬的{ydr}先生/女士您好，您已成功预订{origincity}至{destinationcity}{fightnumber}次航班，起飞时间{origintime}，到达时间{destinationtime}，乘机人{panssengername}，该订单已提交贵公司管理员进行审核，谢谢！弘景商旅系统
	public MessageModel SHIJIANTISHI_to_YDR(String ydr,String origintime,String airlineCompany,
			String airline ) {
		MessageModel m=new MessageModel();
		m.ydr=ydr;
		m.origintime=origintime;
		m.airlineCompany=airlineCompany;
		m.airline=airline;
		
		return m;
		}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYdr() {
		return ydr;
	}
	public void setYdr(String ydr) {
		this.ydr = ydr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getOrigintime() {
		return origintime;
	}
	public void setOrigintime(String origintime) {
		this.origintime = origintime;
	}
	
	public String getDestinationtime() {
		return destinationtime;
	}
	public void setDestinationtime(String destinationtime) {
		this.destinationtime = destinationtime;
	}
	public String getCangwei() {
		return cangwei;
	}
	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}
	public String getFightnumber() {
		return fightnumber;
	}
	public void setFightnumber(String fightnumber) {
		this.fightnumber = fightnumber;
	}
	public String getOrgterm() {
		return orgterm;
	}
	public void setOrgterm(String orgterm) {
		this.orgterm = orgterm;
	}
	public String getPanssengername() {
		return panssengername;
	}
	public void setPanssengername(String panssengername) {
		this.panssengername = panssengername;
	}
	public String getPassengeridtype() {
		return passengeridtype;
	}
	public void setPassengeridtype(String passengeridtype) {
		this.passengeridtype = passengeridtype;
	}
	public String getPassengerid() {
		return passengerid;
	}
	public void setPassengerid(String passengerid) {
		this.passengerid = passengerid;
	}
	public String getReturncost() {
		return returncost;
	}
	public void setReturncost(String returncost) {
		this.returncost = returncost;
	}
	public String getUpdatecost() {
		return updatecost;
	}
	public void setUpdatecost(String updatecost) {
		this.updatecost = updatecost;
	}
	public String getOrigincity() {
		return origincity;
	}
	public void setOrigincity(String origincity) {
		this.origincity = origincity;
	}
	public String getDestinationcity() {
		return destinationcity;
	}
	public void setDestinationcity(String destinationcity) {
		this.destinationcity = destinationcity;
	}
	public String getWebsiteurl() {
		return websiteurl;
	}
	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOriginDate() {
		return originDate;
	}
	public void setOriginDate(String originDate) {
		this.originDate = originDate;
	}
	public String getDestinationDate() {
		return destinationDate;
	}
	public void setDestinationDate(String destinationDate) {
		this.destinationDate = destinationDate;
	}
	public String getUpdateStatusTime() {
		return updateStatusTime;
	}
	public void setUpdateStatusTime(String updateStatusTime) {
		this.updateStatusTime = updateStatusTime;
	}
	public String getDetterm() {
		return detterm;
	}
	public void setDetterm(String detterm) {
		this.detterm = detterm;
	}
	public String getSeiviceTel() {
		return seiviceTel;
	}
	public void setSeiviceTel(String seiviceTel) {
		this.seiviceTel = seiviceTel;
	}
	public String getOricity() {
		return oricity;
	}
	public void setOricity(String oricity) {
		this.oricity = oricity;
	}
	public String getDescity() {
		return descity;
	}
	public void setDescity(String descity) {
		this.descity = descity;
	}
	public String getTpDate() {
		return tpDate;
	}
	public void setTpDate(String tpDate) {
		this.tpDate = tpDate;
	}
	public String getNotifyMen() {
		return notifyMen;
	}
	public void setNotifyMen(String notifyMen) {
		this.notifyMen = notifyMen;
	}
	public String getNotifyTel() {
		return notifyTel;
	}
	public void setNotifyTel(String notifyTel) {
		this.notifyTel = notifyTel;
	}
	public String getAdminMen() {
		return adminMen;
	}
	public void setAdminMen(String adminMen) {
		this.adminMen = adminMen;
	}
	public String getAdminTel() {
		return adminTel;
	}
	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}
	public String getCheckMen() {
		return checkMen;
	}
	public void setCheckMen(String checkMen) {
		this.checkMen = checkMen;
	}
	public String getCheckTel() {
		return checkTel;
	}
	public void setCheckTel(String checkTel) {
		this.checkTel = checkTel;
	}
	public String getSubscribeMen() {
		return subscribeMen;
	}
	public void setSubscribeMen(String subscribeMen) {
		this.subscribeMen = subscribeMen;
	}
	public String getSubscribeTel() {
		return subscribeTel;
	}
	public void setSubscribeTel(String subscribeTel) {
		this.subscribeTel = subscribeTel;
	}
	public String getChengjiMen() {
		return chengjiMen;
	}
	public void setChengjiMen(String chengjiMen) {
		this.chengjiMen = chengjiMen;
	}
	public String getChengjiTel() {
		return chengjiTel;
	}
	public void setChengjiTel(String chengjiTel) {
		this.chengjiTel = chengjiTel;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getIsVerify() {
		return isVerify;
	}
	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getAirlineCompany() {
		return airlineCompany;
	}

	public void setAirlineCompany(String airlineCompany) {
		this.airlineCompany = airlineCompany;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	
	
	
		
	
	
}
