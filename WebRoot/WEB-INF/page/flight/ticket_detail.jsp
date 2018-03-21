<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
		<title>
			舱位列表
			<%-- <c:if test="${tripType==1 }">舱位列表</c:if>
			<c:if test="${(tripType==2 || tripType==3)&&flag==1 }"> —— </c:if> --%>
		</title>
		
		<%--
		<%
		  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		  response.setHeader("Expires", "0");
		  response.setHeader("Pragma","no-cache");
		%>
		--%>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css" />
		
		<!-- 使用自定义弹框，必须要引用如下两个js和一个css -->
		<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="js/MyConfirm.js"></script>
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
	</head>
	<body onpagehide="$.fn.LoadingHide()">
	<div id="container">
		<form id="one" action="flight/toBookTicket.act" method="post">
			<input type="hidden" id="timestamp" name="timestamp" value="${timestamp }" /><!-- 时间戳 -->
			<input type="hidden" id="mapKey1" name="mapKey1" value="${mapKey1}" />
			<input type="hidden" id="mapKey" name="mapKey" value="${mapKey}" />
			<input type="hidden" id="excessinfo1" name="excessinfo1" value="${excessinfo1}" />
			<input type="hidden" id="excessinfo" name="excessinfo" value="${excessinfo}" />
			<input type="hidden" id="flag" name="flag" value="${flag }" />
			<input type="hidden" id="type" name="type" value="${type }" />
			<input type="hidden" id="tripType" name="tripType" value="${tripType }" />
			<input type="hidden" id="cabinType" name="cabinType"  value="${cabinType }" />
			<input type="hidden" id="airline"  name="airline" value="${airline }" />
			<input type="hidden" id="selectusers" name="selectusers" value="${selectusers }"/>
			<c:forEach items="${trips}" var="trip" varStatus="st">
				<input type="hidden" id="flowId${st.count}"  name="flowId" value="${trip.flowId }" />
				<input type="hidden" id="depAirport${st.count}" name="depAirport" value="${trip.depAirport }" />
				<input type="hidden" id="arrAirport${st.count}" name="arrAirport" value="${trip.arrAirport }" />
				<input type="hidden" id="date${st.count}" name="date" value="${trip.date }" />
			</c:forEach>
			<!-- <input type="hidden" id="flowId2"  name="flowId" value="2" />
			<input type="hidden" id="depAirport2" name="depAirport" value="SHA" />
			<input type="hidden" id="arrAirport2" name="arrAirport" value="PEK" />
			<input type="hidden" id="date2" name="date" value="20170508" /> -->
			
			<input type="hidden" id="timeArea" name="listParam_timeArea" value="${fp.listParam_timeArea }"/>
			<input type="hidden" id="code" name="listParam_code" value="${fp.listParam_code }"/>
			<input type="hidden" id="cabin" name="listParam_cabin" value="${fp.listParam_cabin }"/>
			
		</form>
	
		
		<section>
			<header>
				<div>
					<img id="back" src="img/header.gif"/>
				</div>
				<p class="zhixiang"><span>${applicationScope.cityMap.get(result.data.depAirport)}</span><img src="img/jiantou.png" /><span>${applicationScope.cityMap.get(result.data.arrAirport)}</span></p>
			</header>
			
			<div class="content-space">
				<div class="con-top color">
					<p>
						<img src="${applicationScope.iconServerURL}${result.data.airlineCode }.gif" />${result.data.flightNo}
					</p>
					<div class="start">
						<h3>${mt:fmtByStyle(result.data.depDateTime,'HH:mm')}</h3>
						<p>${applicationScope.airportNameMap.get(result.data.depAirport)}${result.data.depPointAT}</p>
					</div>
					<p>${applicationScope.planeTypeMap.get(result.data.planeType)}</p>
				</div>
				<div class="con-top con-mid">
					<p style="margin-top: 0.2rem;">${mt:fmtByStyle(result.data.depDateTime,'MM-dd')}</p>
					<div class="fly">
						<c:if test="${result.data.stops!=null&&result.data.stops.size()>0 && result.data.stops.get(0)!=null}">
							<p style="color: #11b4ed;">经停   
							<%-- ${fn:substring(result.data.stops.get(0).airportCode,result.data.stops.get(0).airportCode.length()-3,result.data.stops.get(0).airportCode.length())} --%>
							${applicationScope.cityMap.get(fn:substring(result.data.stops.get(0).airportCode,result.data.stops.get(0).airportCode.length()-3,result.data.stops.get(0).airportCode.length())) }</p>
							<img src="img/jt.png"  style="width: 9rem;" />
							<p style="color: #11b4ed;">${mt:getTimeDis(result.data.stops.get(0).arrDateTime,result.data.stops.get(0).depDateTime) }</p>
						</c:if>
						<c:if test="${result.data.stops==null||result.data.stops.size()<=0 || result.data.stops.get(0)==null}">
							<p style="color: #11b4ed;">直飞</p>
							<img src="img/zhifei.png"  style="width: 9rem;" />
						</c:if>
					</div>
					<p><!-- 有餐饮 --></p>
				</div>
				<div class="con-top color con-bot">
					<p>总时长约${mt:getTimeDis(result.data.depDateTime,result.data.arrDateTime)}</p>
					<div class="finish">
						<h3>${mt:fmtByStyle(result.data.arrDateTime,'HH:mm')}</h3>
						<p>${applicationScope.airportNameMap.get(result.data.arrAirport)}${result.data.arrPointAT}</p>
					</div>
					<p>${type==1?"因公出行":"因私出行" }</p>
				</div>
			</div>
			
			
			<ul class="item-space">
				<c:if test="${result.data.cabins != null &&  result.data.cabins.size()>0}">
					<c:forEach items="${result.data.cabins}" var="cabin" varStatus="status">
						<li class="shift-space">
							<div class="list-space">
								<div class="message-space">
									<h3><span>¥</span>${cabin.price }</h3>
									<c:set var="char2cabin" value="${result.data.airlineCode}_${cabin.cabin}"></c:set>
									<div class="discount">${applicationScope.cabinNameMap.get(char2cabin)}${mt:fmtDiscount(cabin.discount)}
										<a id="rule" href="javascript:;" refund="${cabin.ticketRule.refund }" endorsement="${cabin.ticketRule.endorsement }">改退政策</a>
										<%-- <h6>超标 ${cabin.excessinfo }</h6> --%>
									</div>
									
								</div>
								<div class="ticket" name="list_${status.index }" value="${cabin.mapKey }^${cabin.excessinfo}">
									<c:if test="${cabin.showRule eq null}">
										<c:if test="${tripType==2&&flag==1}">
											<p class="make ticket-two">订返程</p>
										</c:if>
										<c:if test="${tripType==3&&flag==1}">
											<p class="make ticket-tree">订第二程</p>
										</c:if>
										<c:if test="${tripType==1||flag==2}">
											<p class="make ticket-one">订</p>
										</c:if>
									</c:if>
									<c:if test="${cabin.showRule ne null}">
										<c:if test="${tripType==2&&flag==1}">
											<!-- <p class="make ticket-two">订返程</p> -->
											<c:if test='${not empty cabin.showRule and cabin.showRule eq "10"}'>
				                  		     	<p class="make ticket-two">隐藏</p>
				                  		     </c:if>
				                  		     <c:if test='${not empty cabin.showRule and cabin.showRule eq "21"}'>
				                  		     	<p class="make ticket-two">超标</p>
				                  		     </c:if>
				                  		     <c:if test='${empty cabin.showRule or cabin.showRule eq "20"}'>
				                  		     	<p class="make ticket-two">订返程</p>
				                  		     </c:if>
										</c:if>
										<c:if test="${tripType==3&&flag==1}">
											<!-- <p class="make ticket-tree">订第二程</p> -->
											<c:if test='${not empty cabin.showRule and cabin.showRule eq "10"}'>
				                  		     	<p class="make ticket-two">隐藏</p>
				                  		     </c:if>
				                  		     <c:if test='${not empty cabin.showRule and cabin.showRule eq "21"}'>
				                  		     	<p class="make ticket-two">超标</p>
				                  		     </c:if>
				                  		     <c:if test='${empty cabin.showRule or cabin.showRule eq "20"}'>
				                  		     	<p class="make ticket-two">订第二程</p>
				                  		     </c:if>
										</c:if>
										<c:if test="${tripType==1||flag==2}">
											<!-- <p class="make ticket-one">订</p> -->
											<c:if test='${not empty cabin.showRule and cabin.showRule eq "10"}'>
				                  		     	<p class="make ticket-two">隐藏</p>
				                  		     </c:if>
				                  		     <c:if test='${not empty cabin.showRule and cabin.showRule eq "21"}'>
				                  		     	<p class="make ticket-two">超标</p>
				                  		     </c:if>
				                  		     <c:if test='${empty cabin.showRule or cabin.showRule eq "20"}'>
				                  		     	<p class="make ticket-two">订</p>
				                  		     </c:if>
										</c:if>
										
										
									 
		                  		     
									</c:if>
									<p class="remaining">
										<c:if test="${cabin.remain==10}">大于9张</c:if>
										<c:if test="${cabin.remain!=10}">剩${cabin.remain }张</c:if>
									</p>
								</div>
							</div>
						</li>
					</c:forEach>
				</c:if>
			</ul>

		</section>
	</div>
	<div class="bounced">
		<div class="tips">
			<p class="change">退改政策</p>
			<div class="contents">
				<div style="margin-bottom: 1rem;">
					<p>
						<span>退票条件</span>
					</p>
					<p id="rule_re">(一)航班规定离站时间2小时之前，30%； (二)航班规定离站时间2小时以内及飞后，50%；</p>
				</div>
				<p>
					<span>签转条件</span> 
				</p>
				<p id="rule_en"></p>
			</div>
			<div class="closedBtn">
				<img src="img/close.png"/>
			</div>
		</div>
	</div>
	</body>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/page/comm.js"></script>
	<script type="text/javascript">
		/*该退政策提示框的显示和隐藏*/
		
		var list = "flight/getFlightList.act";
		var detail = "flight/getFlightDetail.act";

		$('.discount a').on('click', function() {
		
			$("#rule_re").html("");
			$("#rule_en").html("");
			var refund = $(this).attr("refund");
			var endor = $(this).attr("endorsement");
			if(!refund){
				refund = "暂无退票规定信息，一切以航司规定为准。";
			}
			if(!endor){
				endor = "暂无签转规定信息，一切以航司规定为准。";
			}
			$("#rule_re").html(refund);
			$("#rule_en").html(endor);
			
			//$('.bounced').fadeIn();
			$('.bounced').layerShow('bounced');
		});
		$('.closedBtn img').on('click', function() {
			//$('.bounced').fadeOut();
			$('.bounced').layerHide('bounced');
		});
		
		
		$(function(){
			/* $("rule").on('click', function() {
				$("#rule_re").val($(this).attr("refund"));
				$("#rule_en").val($(this).attr("endorsement"));
			}); */
		
		
		
			$("div[name^='list_']").on('click',function(obj){
				var tripType = $("#tripType").val();
				var flag = $("#flag").val();
				var selectValue = $(this).attr("value");
				var mapkey=selectValue.split("^")[0];
				var excessinfo1=selectValue.split("^")[1];
				
				//alert(selectValue+"...."+mapkey+"...."+excessinfo1+"...."+tripType);
				if((tripType==2||tripType==3)&&flag==1){//联程或者往返 第一程 则 跳转到第二程列表页面
					var url = "flight/getFlightList.act";//flight/toBookTicket.act
					$("#flag").val(2);//将航程标识设置为第二程
					$("#mapKey1").val(mapkey);
					$("#excessinfo1").val(excessinfo1);
					$("#one").attr("action",url);
				}else{
					var url = "flight/toBookTicket.act";//flight/toBookTicket.act
					$("#one").attr("action",url);
					if(1==tripType){//如果是单程
						$("#mapKey").val(mapkey);
						$("#excessinfo").val(excessinfo1);
					}else{//如果是第二程
						mapkey = $("#mapKey1").val() + "," + mapkey;
						$("#mapKey").val(mapkey);
						var excessionfo1_1=$("#excessinfo1").val();
						var excessinfo2=excessionfo1_1 + "," + excessinfo1;
						$("#excessinfo").val(excessinfo2);
					}
				}
				
				var excessinfo=$("#excessinfo").val();//最后的值
				//差旅管控相关逻辑
				if(tripType=="1"){
			      if(typeof(flag) == "undefined" || excessinfo=="" || excessinfo=="0"){
			     	 $(document).LoadingShow();
					 setTimeout(function(){
						$("#one").submit();
					 },100)
					 return;
			      }
			     }else{
			     	if(typeof(excessinfo) == "undefined" || excessinfo=="" || excessinfo=="0"){
				     	 $(document).LoadingShow();
						 setTimeout(function(){
							$("#one").submit();
						 },100)
						 return;
			     	}
			         var flag22=excessinfo.split("|")[0];
			         var flag11=excessinfo.split("|")[1];
			         //alert(flag22+"_________"+flag11);
			     	 if(typeof(flag22) == "undefined" || (flag22=="" && flag11=="") || (flag22=="0" && flag11=="0")){
				     	 $(document).LoadingShow();
						 setTimeout(function(){
							$("#one").submit();
						 },100)
				     	 return;
			     	 }
			     }
			    
			     var excessType_all2="";
			     var xingchengstr=excessinfo.split("|");
			     for(var j = 0; j < xingchengstr.length; j++){
			         if(xingchengstr[j]!="0"){
			     		 var flags=xingchengstr[j].split("~");
					     for(var i = 0; i < flags.length; i++){
					        var flagstr=flags[i];
					        var excessType=flagstr.split("_")[1];//超标准预定规则
					       	//级别 顺序 10 21 20如果有需要选择理由的，选择理由。
							if(excessType_all2==""){
							    excessType_all2=excessType;
							}else{
								if(excessType=="10"){
						           excessType_all2=excessType;
						        }
						        if(excessType=="21" && excessType_all2!="10"){
						           excessType_all2=excessType;
						        }
						        if(excessType=="20" && excessType_all2==""){
						           excessType_all2=excessType;
						        } 
							}
					     }
				     }
			     }
			     //同种提示内容做姓名合并
			     var flagsmap = new Map();  
			     var flaguserStr="";
			     var xingchengstr2=excessinfo.split("|")[0];
			     //alert("xingchengstr2:"+xingchengstr2);
			     if(xingchengstr2!="0"){
				     var flags=xingchengstr2.split("~");
				     for(var i = 0; i < flags.length; i++){
				       var flagstr=flags[i];
				       //var excessType=flagstr.split("_")[1];
				       var overproofType=flagstr.split("_")[0];
				       var userStr=flagstr.split("_")[2];
				       
				       var keys=overproofType;
				       flaguserStr=flagsmap.get(keys);
				       if(flaguserStr==false){
				          flaguserStr="";
				          flaguserStr+=userStr;
				       }else{
				          flagsmap.removeByKey(keys);
				       	  flaguserStr+="、"+userStr;
				       }
				       flagsmap.put(overproofType,flaguserStr);
				     }
			     }
			     
			     
			     var message_over="";
			     var message="";
			     var selectMessage="";
		     	 var array = flagsmap.keys();
			     for(var cc in array) {
			     	var overproofType=array[cc];
			        var userStr=flagsmap.get(array[cc]);
			         ///alert(overproofType+"__"+userStr);
		     		 if(overproofType=="1"){
			     	    message_over+="【"+userStr+"】最低价超标。</br>";
			     	 }else if(overproofType=="2"){
			     	    message_over+=" 【"+userStr+"】金额超标。</br>";
			     	 }else if(overproofType=="3"){
			     	    message_over+=" 【"+userStr+"】折扣超标。</br>";
			     	 }else if(overproofType=="4"){
			     	    message_over+=" 【"+userStr+"】舱位超标。</br>";
			     	 }else if(overproofType=="5"){
			     	    message_over+=" 【"+userStr+"】所选航班所属航司不符合该企业下的差旅预订规则。</br>";
			     	 }else if(overproofType=="6"){
			     	 	message_over+=" 【"+userStr+"】所选航线不符合该企业下的差旅预订规则。</br>";
			     	 }else if(overproofType=="7"){
			     	 	message_over+=" 【"+userStr+"】所选航班不符合该企业下的差旅预订规则。</br>";
			     	 }else if(overproofType=="8"){
			     	 	message_over+=" 【"+userStr+"】提前预订天数超标。</br>";
			     	 }else if(overproofType=="9999"){
			     	 	message_over+=" 【"+userStr+"】默认规则超标。</br>";
			     	 }
				 }
				 //alert("excessType_all2:"+excessType_all2);
				 if(excessType_all2=="10"){
				    $(this).MyConfirm({
							content : ""+message_over+"不允许预订！"
					},function(){
			    		//$(document).layerShow();
			    		$(document).layerHide();
  					});
  					
  					
				   // easyAlert2('超标',""+message_over+"  &nbsp;&nbsp;&nbsp;&nbsp;不允许预订！",'error');
				    return;
				 }else{
				 	if(excessType_all2=="21"){
				 		 var qucheng='0';
					     var fancheng='0';
					     wangfan ='1_1,2_0';//去程表示_是否超标,回程标识_是否超标   1:去程 2：回程    1：超标   0：不超标
					     if(tripType==1){
					     	wangfan=flag;
					     }else{
					     	wangfan=flag.split("|")[1]+","+flag.split("|")[0];
					     }
					     
					     var wfs=wangfan.split(",");
					     qucheng=wfs[0];
					     if(wfs.length>1){
					         fancheng=wfs[1];
					     }
					     
					     var b=0;
					     if(qucheng=='0' && fancheng=='0'){
					     
					     }else{
					     	//alert("qucheng:"+qucheng+"******fancheng:"+fancheng);
					     	var params = new Object();
					     	params.qucheng=qucheng;//去程是否超标
					     	params.fancheng=fancheng;//回程是否超标
					     	params.overproofType=overproofType;//超标类型
					     	params.message_over=message_over;//提示信息
					     	
					     	
					     }
				 	}else{
				 		if(excessType_all2=="20" && xingchengstr2!="0"){
							$(this).MyConfirm({
									content : message_over+" &nbsp;&nbsp;请确认是否继续预订？",
									hasCancelBtn : true,
									cancelBtnName:"取消",
									confirmBtnName:"确定"
							},function(r){
					    		if(r){
									$(document).LoadingShow();
									setTimeout(function(){
										$("#one").submit();
									},100)
					 			}else{
								    return;
								}
					    		//$(document).layerHide();
		  					});	 
				    	}else{
				    	   //window.location=encodeURI(action);
				    	   	$(document).LoadingShow();
							setTimeout(function(){
								$("#one").submit();
							},100)
				    	}
				 	}
				 	
				 	//return;
				
					$(document).LoadingShow();
					setTimeout(function(){
						$("#one").submit();
					},100)
				 }
				
				
			});
			
			$("form").on('submit',function(){
				$(document).LoadingShow();
			});
			
			$("#back").on('click',goBack);
		})
		
		function goBack(){
			history.go(-1);
		}
		
		/*
	 * MAP对象，实现MAP功能
	 *
	 * 接口：
	 * size()     获取MAP元素个数
	 * isEmpty()    判断MAP是否为空
	 * clear()     删除MAP所有元素
	 * put(key, value)   向MAP中增加元素（key, value) 
	 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False
	 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL
	 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	 * containsKey(key)  判断MAP中是否含有指定KEY的元素
	 * containsValue(value) 判断MAP中是否含有指定VALUE的元素
	 * values()    获取MAP中所有VALUE的数组（ARRAY）
	 * keys()     获取MAP中所有KEY的数组（ARRAY）
	 *
	 * 例子：
	 * var map = new Map();
	 *
	 * map.put("key", "value");
	 * var val = map.get("key")
	 * ……
	 *
	 */
	function Map() {
	    this.elements = new Array();
	
	    //获取MAP元素个数
	    this.size = function() {
	        return this.elements.length;
	    };
	
	    //判断MAP是否为空
	    this.isEmpty = function() {
	        return (this.elements.length < 1);
	    };
	
	    //删除MAP所有元素
	    this.clear = function() {
	        this.elements = new Array();
	    };
	
	    //向MAP中增加元素（key, value) 
	    this.put = function(_key, _value) {
	        this.elements.push( {
	            key : _key,
	            value : _value
	        });
	    };
	
	    //删除指定KEY的元素，成功返回True，失败返回False
	    this.removeByKey = function(_key) {
	        var bln = false;
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].key == _key) {
	                    this.elements.splice(i, 1);
	                    return true;
	                }
	            }
	        } catch (e) {
	            bln = false;
	        }
	        return bln;
	    };
	    
	    //删除指定VALUE的元素，成功返回True，失败返回False
	    this.removeByValue = function(_value) {//removeByValueAndKey
	        var bln = false;
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].value == _value) {
	                    this.elements.splice(i, 1);
	                    return true;
	                }
	            }
	        } catch (e) {
	            bln = false;
	        }
	        return bln;
	    };
	    
	    //删除指定VALUE的元素，成功返回True，失败返回False
	    this.removeByValueAndKey = function(_key,_value) {
	        var bln = false;
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].value == _value && this.elements[i].key == _key) {
	                    this.elements.splice(i, 1);
	                    return true;
	                }
	            }
	        } catch (e) {
	            bln = false;
	        }
	        return bln;
	    };
	
	    //获取指定KEY的元素值VALUE，失败返回NULL
	    this.get = function(_key) {
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].key == _key) {
	                    return this.elements[i].value;
	                }
	            }
	        } catch (e) {
	            return false;
	        }
	        return false;
	    };
	
	    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	    this.element = function(_index) {
	        if (_index < 0 || _index >= this.elements.length) {
	            return null;
	        }
	        return this.elements[_index];
	    };
	
	    //判断MAP中是否含有指定KEY的元素
	    this.containsKey = function(_key) {
	        var bln = false;
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].key == _key) {
	                    bln = true;
	                }
	            }
	        } catch (e) {
	            bln = false;
	        }
	        return bln;
	    };
	
	    //判断MAP中是否含有指定VALUE的元素
	    this.containsValue = function(_value) {
	        var bln = false;
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].value == _value) {
	                    bln = true;
	                }
	            }
	        } catch (e) {
	            bln = false;
	        }
	        return bln;
	    };
	    
	    //判断MAP中是否含有指定VALUE的元素
	    this.containsObj = function(_key,_value) {
	        var bln = false;
	        try {
	            for (i = 0; i < this.elements.length; i++) {
	                if (this.elements[i].value == _value && this.elements[i].key == _key) {
	                    bln = true;
	                }
	            }
	        } catch (e) {
	            bln = false;
	        }
	        return bln;
	    };
	
	    //获取MAP中所有VALUE的数组（ARRAY）
	    this.values = function() {
	        var arr = new Array();
	        for (i = 0; i < this.elements.length; i++) {
	            arr.push(this.elements[i].value);
	        }
	        return arr;
	    };
	    
	    //获取MAP中所有VALUE的数组（ARRAY）
	    this.valuesByKey = function(_key) {
	        var arr = new Array();
	        for (i = 0; i < this.elements.length; i++) {
	            if (this.elements[i].key == _key) {
	                arr.push(this.elements[i].value);
	            }
	        }
	        return arr;
	    };
	
	    //获取MAP中所有KEY的数组（ARRAY）
	    this.keys = function() {
	        var arr = new Array();
	        for (i = 0; i < this.elements.length; i++) {
	            arr.push(this.elements[i].key);
	        }
	        return arr;
	    };
	    
	    //获取key通过value
	    this.keysByValue = function(_value) {
	        var arr = new Array();
	        for (i = 0; i < this.elements.length; i++) {
	            if(_value == this.elements[i].value){
	                arr.push(this.elements[i].key);
	            }
	        }
	        return arr;
	    };
	    
	    //获取MAP中所有KEY的数组（ARRAY）
	    this.keysRemoveDuplicate = function() {
	        var arr = new Array();
	        for (i = 0; i < this.elements.length; i++) {
	            var flag = true;
	            for(var j=0;j<arr.length;j++){
	                if(arr[j] == this.elements[i].key){
	                    flag = false;
	                    break;
	                } 
	            }
	            if(flag){
	                arr.push(this.elements[i].key);
	            }
	        }
	        return arr;
	    };
	}
		
		function bookTicket(){
		}
		
		
	</script>

</html>