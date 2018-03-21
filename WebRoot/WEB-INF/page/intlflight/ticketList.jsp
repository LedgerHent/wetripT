<%@include file="../public/tag.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>
	
		</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/intlAllOrder.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
		
	
	</head>

	<body>
		<!--顶边栏-->
		<header class="headerData">
			<div   class="yesterday timeData">
				<div  id="qdate"><p class="color">前一天</p>
				<p class="color" id="YLowPrice">¥0</p></div>
			</div>
			<div class="today timeData">
				<p>
					<input id="NowDate" type="hidden" value="${dates}"/>
					<!-- <span class="nowYear" style="display:inline-block;">2018</span> -->
					<span class="nowData">${mt:fmtByMDStyle(dates,'MM-dd') } </span>


					

					<span class="nowWeek">${weeks}</span>

				</p>
				<p id="lowPrice">¥0</p>
			</div>
			<div  class="tomorrow timeData">
				<div id="hdate"><p>后一天</p>
				<p id="NLowPrice">¥0</p></div>
			</div>
		</header>
		<section>
			<div class="timeTips">
				<p>航班起降均为当地时间</p>
			</div>
			<ul class="itemFlight">
			
			<!--列表-->
		<div class="tic-list" style="">
		<%-- <c:if test="${result.status==0 and result.data != null and result.data.size()>0}"> --%>
		
			<c:forEach items="${result}" var="obj" >
			
			<li class="listFight" >
			<input type="hidden" value="${obj.mapKey}"   >
					<div class="flightLeft">
						<div class="timeTop">
							<div class="starting">
							
				
								<h4 class="hStart">${mt:fmtByStringStyle(obj.start,'HH:mm')}</h4>
								<p>${obj.orgAirPortStr}${obj.orgTerm}</p>
							</div>
							<div class="route">
								<p>${mt:getTimeStrDis(obj.start,obj.end,'yyyy-MM-dd HH:mm') }</p>
								
								
								<c:if test="${obj.transitCount==0}">
								<img src="<%=basePath%>img/zhifei.png" />
								<p class="stopCity">直飞</p>
								
								</c:if>
								
							<c:if test="${obj.transitCount!=0 and obj.transitCities.size()>0}">
								<img src="<%=basePath%>/img/turn.png" />
								<p class="turnCity">中转
								
								<c:forEach items="${obj.transitCities}" var="transcity">
								${transcity}
								</c:forEach>
								</p>
							
							</c:if>
								
							
							</div>
							
						
							
							<div class="endding">
								<h4 class="endH">
								<p class="endTime">  ${mt:fmtByStringStyle(obj.end,'HH:mm') }  	</p>
								
								<c:if test="${mt:fmtByDay(obj.start,obj.end,'yyyy-MM-dd HH:mm')!='0' }">								
								<p id="addOne">${mt:fmtByDay(obj.start,obj.end,'yyyy-MM-dd HH:mm')}</p>
								</c:if>							
								</h4>
								
								<p>${obj.detAirPortStr}${obj.orgTerm}</p>
							</div>
						</div>
						<div class="flightBot">
							<span>
							<%-- <img src="<%=basePath%>/img/CZ.png"/> --%>
							<img src="${applicationScope.iconServerURL}${obj.carrier }.gif" class="icon-main-li1-img" style="" />
							</span>
							<span class="aviation">${obj.carrierStr}${obj.airline}</span>
							<span class="boeing">${obj.planeType}</span>
							<span class="cangwei">${obj.cangweiDesc}</span>
						</div>
					</div>
					<div class="flightRight">
					
						
							
							<div class="TAX"><div class="priced">
							<span class="moneys">¥</span>
							<span ><fmt:formatNumber type="number" value="${obj.totalPrice+obj.totalTaxPrice}" pattern="#"/></span>
							</div>
							<div class="counPrice">含税价</div>
							</div>
							
							<div class="TAX" >
							<div class="priced">
							<span class="moneys">¥</span>
							<span class="compare"><fmt:formatNumber type="number" value="${obj.totalPrice}" pattern="#"/></span>
							</div>
							<div class="counPrice">不含税价</div>
							</div>
						
						
					</div>
				</li>
			
			</c:forEach>		
		
			</ul>
			<!--遮罩层-->
			<!--底边栏-->
			<footer class="tlist-footer">
				<a id="show_res" class="tlist-footer-a1">
					<div class="tlist-footer-a1-d1 iconImg" /><img src="<%=basePath%>img/bs-shaixuan.png" class="tlist-footer-a1-d1-img" /></div>
					<div class="tlist-footer-a1-d2 botContent">筛选</div>
				</a>
				<a class="tlist-footer-a2" id="item_date">
					<div class="tlist-footer-a2-d1 iconImg"><img src="<%=basePath%>img/bs-shijian.png" class="tlist-footer-a2-d1-img" /></div>
					<div id="date" class="tlist-footer-a2-d2 botContent">时间</div>
				</a>

				<a class="tlist-footer-a3" id="item_price" mode="1">
					<div class="tlist-footer-a3-d1 iconImg"><img src="<%=basePath%>img/jiage.png" class="tlist-footer-a3-d1-img" /></div>
					<div id="price" class="tlist-footer-a3-d2 botContent">含税价</div>
				</a>
			</footer>
			<div class="bounced">
				<div class="screeningContent" style="display: none;">
					<div class="screeningTitle">
						<div class="cancel">取消</div>
						<div class="empty">清空筛选</div>
						<div class="sure">确定</div>
					</div>
					<div class="screeningMessage">
						<div class="departurTime">
							<div class="departurTitle">起飞时间</div>
							<ul class="departurList">
								<li>
									<p class="hidden" value="0">不限</p>
									<p value="1">0:00—12:00</p>
									<p value="2">12:00—13:00</p>
									<p value="3">13:00—18:00</p>
									<p value="4">18:00—24:00</p>
								</li>

							</ul>
						</div>
						<div class="airlines">
							<div class="airlinesTitle">
								航空公司
							</div>
							<ul class="airlinesList">
								<li>
									<p class="hidden">不限</p>
									<p>南方航空<input value="CZ" type="hidden"></p>
									<p>吉祥航空<input value="HO" type="hidden"></p>
									<p>国际航空<input value="CA" type="hidden"></p>
									<p>东方航空<input value="MU" type="hidden"></p>
									<p>厦门航空<input value="MF" type="hidden"></p>
									<p>深圳航空<input value="ZH" type="hidden"></p>
									<p>扬子江航空<input value="Y8" type="hidden"></p>
									<p>上海航空<input value="FM" type="hidden"></p>
									<p>海南航空<input value="HU" type="hidden"></p>
								</li>
							</ul>
						</div>
						<div class="spaceChoose">
							<div class="spaceTitle">舱位选择</div>
							<ul class="spaceList">
								<li>
									<p class="hidden">不限</p>
									<p>经济舱</p>
									<p>公务舱</p>
									<p>头等舱</p>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div style="width:100%;height:4.5rem"></div>
		</section>
		
		
		<form id="form" action="intlflight/getFlightList.act" method="post" >

		<input type="hidden" id="OrgCity" name="orgCity" value="${intlFlightListQue.orgCity}"/>
		<input type="hidden" id="DetCity" name="detCity" value="${intlFlightListQue.detCity}"/>
		<input type="hidden" id="startTime"  name="startTime" value="${intlFlightListQue.startTime}"/>
		<input type="hidden" id="arrviTime"  name="arrviTime" value="${intlFlightListQue.arrviTime}"/>
		<input type="hidden" id="travelType" name="travelType" value="${intlFlightListQue.travelType}"/>
		<input type="hidden" id="travelfangshi" name="travelfangshi" value="${travelfangshi}"/>
		<input type="hidden" id="weeks" name="weeks" value=""/>
		<input type="hidden" id="cangwei" name="cangwei" value="${cangwei}"/>
		</form>
		
	</body>

</html>

	<script type="text/javascript" src="<%=basePath%>/libs/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/libs/Myconfirm.js"></script>
	<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript">
/*----------筛选的弹框--------*/

		

	$('#show_res').on('click', function() {
		$('.bounced').show();
		$('.screeningContent').slideDown("slow");
	});
	/*------点击取消，筛选选择框隐藏--*/
	$('.screeningTitle .cancel').on('click', function() {
		$('.screeningContent').slideUp("slow");
		$('.bounced').fadeOut();
	});
	/*-------点击起飞时间、航空选择、舱位选择-------*/
	var filterCompany = "不限";
	var filterTime = "0";
	var filterCabin = "不限";
	$('.departurList p').on('click', function() {
		$(this).addClass("hidden").siblings('p').removeClass('hidden');
		filterTime = $(this).attr('value');
	});

	$('.airlinesList p').on('click', function() {
		$(this).addClass("hidden").siblings('p').removeClass('hidden');
		filterCompany = $(this).find('input').val();
	});
	$('.spaceList p').on('click', function() {
		$(this).addClass("hidden").siblings('p').removeClass('hidden');
		filterCabin = $(this).html();
	});

	/*---------清空筛选的点击事件---------*/
	$('.screeningTitle .empty').on('click', function() {
		$('.screeningMessage ul').each(function() {
			$(this).find('p').eq(0).addClass("hidden").siblings('p').removeClass('hidden');
		});
		filterCompany = "不限";
		filterTime = "0";
		filterCabin = "不限";
	});
	/*-----------------确定的点击事件-------*/
	$('.screeningTitle .sure').on('click', function() {
		filter_time(); //起飞时间
		filter_company(); //航空公司
		filter_cabin(); //舱位选择
		$('.bounced').fadeOut();
		$(".screeningContent").slideUp('slow');
	

	});
	
	
	
	
	
	var objArr = [];
	
	function getUlArr(){
		objArr = new Array();
		var ulArr = $("li.listFight");
		for(var i=0;i<ulArr.length;i++){
			objArr.push(ulArr[i]);
		}
	} 
	
	
	/*--------时间的排序-----------*/
	$("#item_date").on("click ", function() {
		
		var str1 = "时间由早到晚 "
		var str2 = "时间由晚到早 "
		var str3 = "税价"
		
		//var ulArr = $("ul.tic-list-ul");
		getUlArr();
		
		if($(this).find("#date").html() == str1) {
			sort(objArr,1,'time');
			$(this).find("#date").html(str2)			
		} else {
			sort(objArr,0,'time');
			$(this).find("#date").html(str1)
		}
		
		$(this).find("#date").css("color", "#00afec")
		/* $("#price").html(str3)
		$("#price").css("color","#ffffff") */
		$(".tlist-footer-a3-d1-img").attr("src", "<%=basePath%>/img/bs-jiage.png")
		$(".tlist-footer-a2-d1-img").attr("src", "<%=basePath%>/img/time.png")
	
	});
	//flag=0 返回obj1<=obj2  flag=1 obj1>obj2
	function compareTime(obj1,obj2,flag){
		var val1 = $(obj1).find('.con-main-li1-d1').attr('timestamp');
		var val2 = $(obj2).find('.con-main-li1-d1').attr('timestamp');
		if(0==flag){
			return val1<=val2;
		}else{
			return val1>val2;
		}
	}
	function comparePrice(obj1,obj2,flag){
		var val1 = $(obj1).find(".compare").html();
		var val2 = $(obj2).find(".compare").html();
		
		if(0==flag){
			return parseFloat(val1)<=parseFloat(val2);
		}else{
			return parseFloat(val1)>parseFloat(val2);
		}
	}
	function sort(arr,flag,compareArea){
	
			//$(".listFight").empty();
			for(var i=0;i<arr.length-1;i++){
				for(var j=i+1;j<arr.length;j++){
					if('time' == compareArea){
						if(false == compareTime(arr[i],arr[j],flag)){
							var temp = arr[i];
							arr[i] = arr[j];
							arr[j] = temp;
						}
					}
					if('price' == compareArea){
						if(false == comparePrice(arr[i],arr[j],flag)){
							var temp = arr[i];
							arr[i] = arr[j];
							arr[j] = temp;
						}
						
						
					}
				
				}
			}
			if('price' == compareArea){
			
			if(arr.length>0){
			var lowprice=$(arr[0]).find(".compare").html();
			
			$('#lowPrice').html("￥"+lowprice)
			}
			
			
			
			}
			
			if('time' == compareArea){
			$(".itemFlight").append(arr);
			}
			
			}
			
	/*-----------默认为含税价的显示----*/
	/*taxPrice('含税价');*/
	/*-----------含税和未含税的筛选----------*/
	/* function taxPrice(string) {
	
	
		$('.counPrice').each(function() {
			$(this).html();
			if($(this).html() != string) {
				$(this).parents('.listFight').css('display','none');
			} else {
				$(this).parents('.listFight').css('display','block');
			}
		});
	} */
	$(function (){
	
	$('.counPrice').each(function() {
			$(this).html();
			if($(this).html() == "不含税价") {
				$(this).parents('.TAX').css('display','none');
			} 
		});
	

	})
	
	function taxPrice(string) {
	
	
		$('.counPrice').each(function() {
			$(this).html();
			if($(this).html() != string) {
				$(this).parents('.TAX').css('display','none');
			} else {
				$(this).parents('.TAX').css('display','block');
			}
		});
	}
	/*---------含税和未含税的排序---------*/
	$("#item_price ").on("click ", function() {
		var str1 = "不含税价 "
		var str2 = "含税价 "
		var str3 = "时间 ";
		var orderIdArray = [];
		var idIndex = [];
		var _length;
		var orderid = $(".counPrice");
		var list = $(".listFight");
		orderid.each(function(i) {
			var id = $(this).html();
			idIndex[id] = i; //orderid的序号
			orderIdArray.push(id); //orderid的值

		});
		if($(this).find("#price ").html() == str1) {
			$(this).find("#price ").html(str2);
			taxPrice('含税价');
		} else {
			$(this).find("#price ").html(str1).css("color ", "#00afec");
			$("#date ").html(str3).css("color ", "#ffffff ");
			taxPrice('不含税价');
		}
		$(".tlist-footer-a2-d1-img").attr("src", "<%=basePath%>/img/bs-shijian.png")
		$(".tlist-footer-a3-d1-img").attr("src", "<%=basePath%>/img/jiage.png")

	});

	/*------日期的效果=========*/

	var dates = new Date().getDate();
	var month = new Date().getMonth() + 1;
 	if($('.nowData').html().trim() === p(new Date().getMonth()+1) + "-" + p(new Date().getDate())){
		$('.yesterday p').addClass('color'); 
		
	}else{
		 $('.yesterday p').removeClass('color');
	};  
	
	
	/*--------提示框的显示-----*/
	function showMessage(mess) {
		$(this).MyConfirm({
			content: mess
		});
	}
	/*-------前一天的效果，减一天-------*/
	$('.yesterday').on('click', function() {
		if($('.yesterday p').is('.color')) {
			mess = "日期选择不能早于当前日期";
			showMessage(mess);
			return false;
		}

		
		
		
		var yesterday=getYesterDay($("#NowDate").val());
		var week = new Date(yesterday).getDay() ;
		
		weeks(week);
		$("#startTime").val(yesterday);
		
		 $("#form").submit();
		
		
	});

	/*-----星期显示为周几的形式，，，，封装函数------*/
	function weekZhou(day) {
		switch(day) {
			case 0:
				$('.nowWeek').html("周日");
				break;
			case 1:
				$('.nowWeek').html("周一");
				break;
			case 2:
				$('.nowWeek').html("周二");
				break;
			case 3:
				$('.nowWeek').html("周三");
				break;
			case 4:
				$('.nowWeek').html("周四");
				break;
			case 5:
				$('.nowWeek').html("周五");
				break;
			case 6:
				$('.nowWeek').html("周六");
				break;
		}
	}
	
	
	/*-----星期显示为周几的形式，，，，封装函数------*/
	function weeks(day) {
		switch(day) {
			case 0:
				$("#weeks").val("周日");
				
				break;
			case 1:
				$("#weeks").val("周一");
				
				break;
			case 2:
			$("#weeks").val("周二");
				
				break;
			case 3:
			$("#weeks").val("周三");
			
				break;
			case 4:
			$("#weeks").val("周四");
				
				break;
			case 5:
			$("#weeks").val("周五");
			
				break;
			case 6:
			$("#weeks").val("周六");
				
				break;
		}
	}
	/*----------补零--------*/
	function p(s) {
		return s < 10 ? '0' + s : s;
	};
	/*----------点击后一天的效果，天数增加一天-----*/
	$('.tomorrow').on('click', function() {
		
		var nextdate=getNextDay($("#NowDate").val())
		var week = new Date(nextdate).getDay();
		
		weeks(week);
		
		$("#startTime").val(nextdate);
		var arrviTime=$("#arrviTime").val();
		
/* 	var DarrviTime=	 new Date(arrviTime);
	var Dnextdate=	 new Date(nextdate); */
	
		if(arrviTime==nextdate ){
		
		showMessage("去程日期不得晚于返程日期！")
			/* $('.tomorrow  p').addClass('color'); */
		return;
		}
		
		$("#form").submit();
		
	});
	/*----航空公司的筛选----*/
	function filter_company() {
		if(filterCompany != "不限") {
			$("li.listFight").each(function() {
				if($(this).css("display") == 'block') {
				if(!filterCompany){
						$(this).show();
						return false;
					}
				
					/* filterCompany = filterCompany.substr(0, 2); */
					if($(this).find('.aviation').html().indexOf(filterCompany) <0 ) {
						$(this).hide();
					}
				}
			})
		}
	};
	
	
	/* 从搜索页带过来的舱位筛选条件 */
	$(function (){
	filterCabin=$("#cangwei").val();
	if(filterCabin != "不限") {
	$("li.listFight").each(function() {
	if($(this).find(".cangwei").html().indexOf(filterCabin)  <0  ) {
						$(this).hide();
					}
					})}
				$("#cangwei").val("不限");	
					
			
	})
	
	/*-------------机票舱位的筛选---------------*/
	function filter_cabin() {
		if(filterCabin != "不限") {
			$("li.listFight").each(function() {
				if($(this).css("display") == 'block') {
					/* if($(this).find(".cangwei").html() != filterCabin    ) {
						$(this).hide();
					} */
					if($(this).find(".cangwei").html().indexOf(filterCabin)  <0  ) {
						$(this).hide();
					}
			
				}
			})
		}
	};

	function filter_time() {
		var start = "00:00";
		var end = "24:00";
/* 		console.log(filterTime) */
		switch(filterTime) {
			case "0":
				filter(start, end);
				break;
			case "1":
				start = "00:00";
				end = "12:00";
				filter(start, end);
				break;
			case "2":
				start = "12:00";
				end = "13:00";
				filter(start, end);
				break;
			case "3":
				start = "13:00";
				end = "18:00";
				filter(start, end);
				break;
			case "4":
				start = "18:00";
				end = "24:00";
				filter(start, end);
				break;
		}
	}
	/*-------------时间起飞时间的筛选------------*/
	function filter(start, end) {
		$("li.listFight").each(function(obj, idx) {
			var $obj = $(this);
			$obj.css("display", "block");
			var deptime = $obj.find(".hStart").html();
			if(!time_range(start, end, deptime)) {
				$obj.hide();
			} else {
				$obj.show();
			}
		});
	}

	function time_range(beginTime, endTime, nowTime) {
		var result = true;
		if(beginTime && endTime && nowTime) {
			/* console.log(beginTime + "-" + endTime + "-" + nowTime); */
			var b = parseDate(beginTime, 'HH:mm');
			var e = parseDate(endTime, 'HH:mm');
			var n = parseDate(nowTime, 'HH:mm');
			result = (n.getTime() >= b.getTime() && n.getTime() <= e.getTime());
		}
		return result;
	}

	function parseDate(str, fmt) {
		fmt = fmt || 'yyyy-MM-dd';
		var obj = {
			y: 0,
			M: 1,
			d: 0,
			H: 0,
			h: 0,
			m: 0,
			s: 0,
			S: 0
		};
		fmt.replace(/([^yMdHmsS]*?)(([yMdHmsS])\3*)([^yMdHmsS]*?)/g, function(m, $1, $2, $3, $4, idx, old) {
			str = str.replace(new RegExp($1 + '(\\d{' + $2.length + '})' + $4), function(_m, _$1) {
				obj[$3] = parseInt(_$1);
				return '';
			});
			return '';
		});
		obj.M--; // 月份是从0开始的，所以要减去1
		var date = new Date(obj.y, obj.M, obj.d, obj.H, obj.m, obj.s);
		if(obj.S !== 0) date.setMilliseconds(obj.S); // 如果设置了毫秒
		return date;
	}
	/*---点击列表往舱位列表页面传参--*/
	$('.listFight').on('click', function() {
	
		var mapKey=$(this).find('input').val();
		var dates = $('.nowData').html();
		var weeks = $(this).find('.hStart').html();
		var counts = $(this).find('.endding .endTime').html();
		var type = $(this).find('.aviation').html();
		var travelfangshi=$("#travelfangshi").val();

		var travelType=$("#travelType").val();
		var f=${flag}
		 
		$(document).LoadingShow();
		if(travelType=='1'||f=='2'){

		window.location.href = "intlflight/getFlightInfo.act?name=" + dates  + "&idno=" + counts + "&id=" + type+"&travelfangshi="+travelfangshi+"&mapKey="+mapKey;
		}else{
		window.location.href = "intlflight/getIntlFlightReurnList.act?travelfangshi="+travelfangshi+"&mapKey="+mapKey;
		}
		$(window).unload(function(){$(document).LoadingHide();});
		
		
		
	});
	//获取当天航班列表最低价
	$(function (){
		
		getUlArr();
		
		sort(objArr,0,'price');
	
	})
	
	//格式话日期
	Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}       
	//获取后一天时间
	function getNextDay(d){
		
        d = new Date(d);
        d = +d + 1000*60*60*24;
        d = new Date(d);
        //return d;
        //格式化
        return d.pattern("yyyy-MM-dd");
       /*  return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate(); */
         
    }
    //获取前一天时间
    function getYesterDay(d){
		
        d = new Date(d);
        d = +d - 1000*60*60*24;
        d = new Date(d);
        //return d;
        //格式化
        return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
         
    }
   
   	var flag=${flag};
   
	//ajax请求 获取前一天 后一天航班最低价
	$(function(){
	if(flag=='2'){
	return;
	}

	var arrviTime=$("#arrviTime").val();
		
	

				var orgCity=$("#OrgCity").val();
				var detCity=$("#DetCity").val();
				var NextTime=getNextDay($("#NowDate").val());
				var YesterTime=getYesterDay($("#NowDate").val());
				var travelType=$("#travelType").val();
				var arrviTime=$("#arrviTime").val();
			
				param = "&orgCity="+ orgCity+ "&detCity="+detCity+"&NextTime="+NextTime+"&YesterTime="+ YesterTime+"&arrviTime="+arrviTime+ "&travelType="+travelType;
				$.ajax({
					type:"post",
					url:"intlflight/getLowestPrice.act",
					dataType:"text",
					data:"param="+param,
					success:function(priceArr){
					
					var	strs=priceArr.split("_"); 
					if(strs.length>0){
						$(NLowPrice).html(strs[0]);
					}
				
					if(strs.length>1){
					$(YLowPrice).html(strs[1]);
					}
						  
						
					
							
						
					},
					error:function(){
					/* $(this).MyConfirm({
							content : "请求失败"
					});	 */
					
					}
				});
			})
	
	$(function (){
	
		if(flag=='2'){
		
		$("#qdate").hide();
		$("#hdate").hide();
		}	
	
	})
</script>


