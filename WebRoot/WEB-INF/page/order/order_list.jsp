<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>全部订单</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
	</head>

	<body>
		<div id="container">
		<form method="post" action="order/detail.act">
			<input type="hidden" id="orderId" name="orderId" value="" />
		</form>
		<section id="content">
			<header>
				<div>
					<img id="back" src="img/header.gif" onclick="window.location.href='wetrip/show.act';" />
				</div>
				<p class="zhixiang"><span><c:if test="${status==101 }">机票待审核订单</c:if><c:if test="${status==0 }">机票订单</c:if></span></p>
			</header>
			
			<%-- <c:if test="${result.resultObj!=null && result.resultObj.status==0 && result.resultObj.list.size()>0 }">
				<c:forEach items="${result.resultObj.list }" var="obj" varStatus="vs">
					<div class="all-order">
						<div class="one-way">
							<div class="way-title">
								<div class="tit-left">
									<span class="way-type">
										<c:if test="${obj.tripType==1 }">单程</c:if>
										<c:if test="${obj.tripType==2 }">往返</c:if>
										<c:if test="${obj.tripType==3 }">联程</c:if>
									</span>
									<span class="way-num">订单号 ${obj.orderNo }</span>
								</div>
								<h5 class="audit">
									<!-- 1-待审核，2-待支付，3-已审核，4-已支付，5-已取消，7-已删除，8-补录，9-已提交（针对机加酒订单，预订提交后的第一个状态） -->
									<c:choose>
										<c:when test="${obj.status==1}">待审核</c:when>
										<c:when test="${obj.status==2}">待支付</c:when>
										<c:when test="${obj.status==3}">已审核</c:when>
										<c:when test="${obj.status==4}">已支付</c:when>
										<c:when test="${obj.status==5}">已取消</c:when>
										<c:when test="${obj.status==6}"></c:when>
										<c:when test="${obj.status==7}">已删除</c:when>
										<c:when test="${obj.status==8}">补录</c:when>
										<c:when test="${obj.status==9}">已提交</c:when>
									</c:choose>
								</h5>
							</div>
							<c:if test="${obj.tripType==1 }"><!-- 单程 -->
								<div class="way-trip">
									<div class="trip left">
										<h4>${applicationScope.cityMap.get(obj.tripInfo[0].depAirport) }<img src="img/dancheng.png" style="margin-top: 1rem;"/>${applicationScope.cityMap.get(obj.tripInfo[0].arrAirport)}</h4>
										<div class="adress">
											<span class="icon">
												<img src="img/go.png" class="img-icon" />
											</span>
											<span class="ytpes">${obj.flights[0].flightNo }</span>
											<span class="data">${obj.tripInfo[0].date }</span>
											<span>${mt:fmtByStyle(obj.flights[0].depDateTime,'HH:mm') } 至 ${mt:fmtByStyle(obj.flights[0].arrDateTime,'HH:mm') }</span>
										</div>
									</div>
									<div class="trip right">
										<h4>¥${obj.amount}</h4>
										<c:if test="${obj.status==2 }">
											<div class="price">支付倒计时<span class="audit time">900</span></div>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if test="${obj.tripType==2 }"><!-- 往返 -->
								<div class="way-trip">
									<div class="trip left">
										<h4>${applicationScope.cityMap.get(obj.tripInfo[0].depAirport) }<img src="img/wanfan.png" />${applicationScope.cityMap.get(obj.tripInfo[0].arrAirport)}</h4>
										<div class="adress">
											<span class="icon">
												<img src="img/go.png" class="img-icon" />
											</span>
											<span class="ytpes">${obj.flights[0].flightNo }</span>
											<span class="data">${obj.tripInfo[0].date }</span>
											<span>${mt:fmtByStyle(obj.flights[0].depDateTime,'HH:mm') }至 ${mt:fmtByStyle(obj.flights[0].arrDateTime,'HH:mm') }</span>
										</div>
										<div class="adress">
											<span class="icon">
												<img src="img/turn1.png" style="margin-top: 0;" class="img-icon" />
											</span>
											<span class="ytpes">${obj.flights[1].flightNo }</span>
											<span class="data">${obj.tripInfo[1].date }</span>
											<span>${mt:fmtByStyle(obj.flights[1].depDateTime,'HH:mm') }至${mt:fmtByStyle(obj.flights[1].arrDateTime,'HH:mm') }</span>
										</div>
									</div>
									<div class="trip right">
										<h4>¥${obj.amount}</h4>
										<c:if test="${obj.status==2 }">
											<div class="price">支付倒计时<span class="audit time">900</span></div>
										</c:if>
									</div>
								</div>
							</c:if>
							<c:if test="${obj.tripType==3 }"><!-- 联程 -->
								<div class="way-trip">
									<div class="trip left">
										<h4>${applicationScope.cityMap.get(obj.tripInfo[0].depAirport) }<img src="img/dancheng.png" style="margin-top: 1rem;"/>${applicationScope.cityMap.get(obj.tripInfo[0].arrAirport) }<img src="img/dancheng.png" style="margin-top: 0.2rem;"/>${applicationScope.cityMap.get(obj.tripInfo[1].arrAirport) }</h4>
										<div class="adress">
											<span class="icon">
												<img src="img/one.png" class="img-icon" />
											</span>
											<span class="ytpes">${obj.flights[0].flightNo }</span>
											<span class="data">${obj.tripInfo[0].date }</span>
											<span>${mt:fmtByStyle(obj.flights[0].depDateTime,'HH:mm') }至 ${mt:fmtByStyle(obj.flights[0].arrDateTime,'HH:mm') }</span>
										</div>
										<div class="adress">
											<span class="icon">
												<img src="img/two.png" style="margin-top: 0;" class="img-icon" />
											</span>
											<span class="ytpes">${obj.flights[1].flightNo }</span>
											<span class="data">${obj.tripInfo[1].date }</span>
											<span>${mt:fmtByStyle(obj.flights[1].depDateTime,'HH:mm') }至${mt:fmtByStyle(obj.flights[1].arrDateTime,'HH:mm') }</span>
										</div>
									</div>
									<div class="trip right">
										<h4>¥${obj.amount }</h4>
										<c:if test="${obj.status==2 }">
											<div class="price">支付倒计时<span class="audit time">900</span></div>
										</c:if>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</c:forEach>
				
			</c:if> --%>
			
		</section>
		</div>
	</body>
	<!-- <script type="text/javascript" src="libs/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
 	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript" src="js/page/comm.js"></script>
	<script type="text/javascript" src="js/page/DateUtil.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript">

		function p(s) {
			return s < 10 ? '0' + s : s;
		}
		
		function creatElement(type,html,attr){
			var result;
			if(type){
				result = $("<" + type + ">" + "</" + type + ">");
				if(html){
					result.html(html);
				}
				if(attr){
					for(var key in attr){
						if('style' == key){
							result.css(attr[key]);
						}else{
							result.attr(key,attr[key]);
						}
					}
				}
			}
			return result;
		}
		
		function div(data){
			if(data && data.status==0 && data.data.list && data.data.list.length>0){
				start += data.data.list.length;
				var list = data.data.list;
				for(var idx in list){
					var obj = list[idx];
					var str = '<div class="all-order" onclick="to_submit(this);" orderId="' + obj.orderId + '" id="list_' + (listIdx++) + '"><div class="one-way"><div class="way-title"><div class="tit-left"><span class="way-type">';
					if(1==obj.tripType){
						str += '单程';
					}
					if(2==obj.tripType){
						str += '往返';
					}
					if(3==obj.tripType){
						str += '联程';
					}
					str += '</span><span class="way-num">订单号 ' + obj.orderNo + '</span></div><h5 class="audit">';
					var st = "";
					switch (obj.status) {
						case '1': st = "待审核"; break;
						case '2': st = "待支付"; break;
						case '3': st = "已审核"; break;
						case '4': st = "已支付"; break;
						case '5': st = "已取消"; break;
						case '6': st = ""; break;
						case '7': st = "已删除"; break;
						case '8': st = "补录"; break;
						case '9': st = "已提交"; break;
					}
					str += st + '</h5></div>';
					if(1 == obj.tripType){//单程
						str += '<div class="way-trip"><div class="trip left"><h4>' + cityMap[obj.tripInfo[0].depAirport] + '<img src="img/dancheng.png" style="margin-top: 1rem;"/>' + cityMap[obj.tripInfo[0].arrAirport] + 
						       '</h4><div class="adress"><span class="icon"><img src="img/go.png" class="img-icon" /></span><span class="ytpes">' + 
						       obj.flights[0].flightNo + '</span><span class="data">' + obj.tripInfo[0].date + '</span><span>' + 
						       formatDate(obj.flights[0].depDateTime,'HH:mm') + '至' + formatDate(obj.flights[0].arrDateTime,'HH:mm') + '</span></div></div><div class="trip right"><h4>¥' + obj.amount + '</h4>'
							if(2==obj.status){
								str += '<div class="price">支付倒计时<span class="audit time">' + (obj.surplusPayTime=="00:00"?"已超时":obj.surplusPayTime) + '</span></div>';
							}
					}
					if(2 == obj.tripType){//往返
						
						str += '<div class="way-trip"><div class="trip left"><h4>' + cityMap[obj.tripInfo[0].depAirport] + '<img src="img/wanfan.png" />' + cityMap[obj.tripInfo[0].arrAirport] + 
							   '</h4><div class="adress"><span class="icon"><img src="img/go.png" class="img-icon" /></span>' +
							   '<span class="ytpes">' + obj.flights[0].flightNo + '</span>' +
							   '<span class="data">' + obj.tripInfo[0].date + '</span>' +
							   '<span>' + formatDate(obj.flights[0].depDateTime,'HH:mm') + '至' + formatDate(obj.flights[0].arrDateTime,'HH:mm') + '</span></div>';
							   if(obj.flights.length==2){
								   str += '<div class="adress"><span class="icon"><img src="img/turn1.png" style="margin-top: 1rem;" class="img-icon" /></span>' +
								   '<span class="ytpes">' + obj.flights[1].flightNo + '</span>' +
								   '<span class="data">' + obj.tripInfo[1].date + '</span>' +
								   '<span>' + formatDate(obj.flights[1].depDateTime,'HH:mm') + '至' + formatDate(obj.flights[1].arrDateTime,'HH:mm') + '</span></div></div>';
							   }
							   /* str += '</div><div class="trip right">'; */
 							   str += '<div class="trip right"><h4>¥' + obj.amount + '</h4>'; 
						if(2==obj.status){
							str += '<div class="price">支付倒计时<span class="audit time">' + (obj.surplusPayTime=="00:00"?"已超时":obj.surplusPayTime) + '</span></div>';
						}
					}
					
					if(3 == obj.tripType){//联程
						
						str += '<div class="way-trip"><div class="trip left"><h4>' + cityMap[obj.tripInfo[0].depAirport] + '<img src="img/dancheng.png" style="margin-top: 1rem;"/>' + cityMap[obj.tripInfo[0].arrAirport] + '<img src="img/dancheng.png" style="margin-top: 1rem;"/>' + cityMap[obj.tripInfo[1].arrAirport] + '</h4>' +
							   '<div class="adress"><span class="icon"><img src="img/one.png" class="img-icon" /></span>' +
							   '<span class="ytpes">' + obj.flights[0].flightNo + '</span>'+
							   '<span class="data">' + obj.tripInfo[0].date + '</span>' +
							   '<span>' + formatDate(obj.flights[0].depDateTime,'HH:mm') + '至' + formatDate(obj.flights[0].arrDateTime,'HH:mm') + '</span></div>'+
							   '<div class="adress"><span class="icon"><img src="img/two.png" style="margin-top: 1rem;" class="img-icon" /></span>' +
							   '<span class="ytpes">' + obj.flights[1].flightNo + '</span>'+
							   '<span class="data">' + obj.tripInfo[1].date + '</span>'+
							   '<span>' + formatDate(obj.flights[1].depDateTime,'HH:mm') + '至' + formatDate(obj.flights[1].arrDateTime,'HH:mm') + '</span></div></div>';
							   /* '<div class="trip right">'; */
						str += '<div class="trip right"><h4>¥' + obj.amount + '</h4>'; 
						if(2==obj.status){
							str += '<div class="price">支付倒计时<span class="audit time">' + (obj.surplusPayTime=="00:00"?"已超时":obj.surplusPayTime) + '</span></div>';
						}
					}
					str += '</div></div>';
					$("#content").append(str);
				}
			}else{
				showMsg("没有订单数据");
			}
		}
		
		var totalCount;
		var count = 10;
		var status = '${status}';
		var start = 0;
		var listIdx = 1;
		
		
		var interV = [];
		
		function SetRemainTime(idx) {
			var time = $(".time").eq(idx).html();
			var timeStr = time_to_sec(time);
			if(timeStr > 0) {
				timeStr = timeStr -1;
				$(".time").eq(idx).html(sec_to_time(timeStr));
			} else {
				$(".time").eq(idx).html("已超时");
				clearInterval(interV[idx])
			}
		}
		
		$(function(){
			getDataAndRender(status,start,count);//初始化数据第一页数据
			/* $("div[id^='list_']").on('click',function(){
				$("#orderId").val($(this).attr("orderId"));
				$("form").submit();
			});//绑定跳转事件 */
			
			
			
			/* new Timer($(".time"),null).begin(); */
		});
		
		//进入详情
		function to_submit(obj){
			$("#orderId").val($(obj).attr("orderId"));
			$("form").submit();
		}
		
		//获取数据
		function getDataAndRender(status,start,count){
			var para = {'status':status,'start':start,'count':count};
			$.ajax({
				url:'order/listJSON.act',
				async:false,
				data:para,
				success:function(data){
					if(!totalCount){
						if(data&&data.data){
							totalCount = data.data.totalCount;
						}
					}
					div(data);
				},
				dataType:'json'
			});
			//倒计时
			$(".time").each(function(idx){
				interV[idx] = window.setInterval(function()
													{
														SetRemainTime(idx);
													}, 1000)
			});
		}
		
		$(window).scroll(function(){
		　　var scrollTop = $(this).scrollTop();
		　　var scrollHeight = $(document).height();
		　　var windowHeight = $(this).height();
		　　if(scrollTop + windowHeight == scrollHeight){
				//console.log("totalCount=" + totalCount + ",listIdx=" + listIdx)
			　　if(totalCount > listIdx){
					getDataAndRender(status, start, count);
			   }
		　　}
		});
	</script>

</html>