<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>


<!DOCTYPE html>
<html>
	<head>
	    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
		<title>订单填写</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
	</head>
	<body>
		<div class="bounceds bounced">
			<div class="boun-con">
				<p class="change">退改政策</p>
				<div class="content">
				<div class="con-top color">
					<p>
						<img src="img/type.png" /> {quTrip.airline}
					</p>
					<p>${mt:fmtByStyle(quTrip.startdate,'HH月MM日')}</p>
					<p>总时长约${mt:getTimeDis(quTrip.startdate,quTrip.arrvidate)}</p>
				</div>
				<div class="con-top con-mid">
					<div class="start">
						<h3>${mt:fmtByStyle(quTrip.startdate,'HH:mm')}</h3>
						<p>${applicationScope.cityMap.get(quTrip.orgcity)}${quTrip.orgterm}</p>
					</div>
					<div class="fly">
						<p style="color: #11b4ed; text-align: center;">直飞</p>
						<img src="img/zhifei.png" / >
					</div>
					<div class="finish">
						<h3>${mt:fmtByStyle(quTrip.arrvidate,'HH:mm')}</h3>
						<p>${applicationScope.cityMap.get(quTrip.detcity)}${quTrip.detterm}</p>
					</div>
				</div>
				<div class="con-top color con-bot">
					<p>${quTrip.planetype}</p>
					<p>${quTrip.food=='Y'?"有餐饮":"无餐饮"}</p>
					<p>${traveType}</p>
				</div>
			</div>
				
			</div>
		</div>
		<div class="bounced bun">
			<div class="tips">
				<p class="change">退改政策</p>
				<div class="contents">
					<div style="margin-bottom:1rem;">
						<p>
						<span>退票条件</span> 航前和航后的判定标准以航班起飞前2小时作为时间划分截点:
					</p>
					<p>(一)航班规定离站时间2小时之前，30%；</p>
					<p>(二)航班规定离站时间2小时以内及飞后，50%；</p>
					</div>
					<p>
						<span>签转条件</span> 以实际航班日期为准，航前和航后的判定标准以航班起飞前2小时作为时间划分截点:
					</p>
					<p>(一)航班规定离站时间2小时之前，20%；</p>
					<p>(二)航班规定离站时间2小时以内及飞后，30%。</p>
					<p>改期费与升舱费同时发生时，则同时需要收取改期费和升舱费差额。不得签转</p>
				</div>
			</div>
		</div>
		<section>
			<header>
				<div>
					<img src="img/header.gif"/>
				</div>
				<p class="zhixiang"><span>北京</span><img src="img/jiantou.png" /><span>上海</span></p>
			</header>
			<div class="title" style="background: #FFFFFF;">
				<div class="title-con">
					<div class="word">
						<div class="top">
							<span>${mt:fmtByStyle(quTrip.startdate,'HH月MM日')}</span>
							<span class="week">${quTripWeek}</span>
							<span>${mt:fmtByStyle(quTrip.startdate,'HH:mm')}</span>
							<span>
								<img src="img/type.png"/>
								${quTrip.airline}
							</span>
						</div>
						<div class="bottom">${applicationScope.cityMap.get(quTrip.orgcity)}${quTrip.orgterm}-${applicationScope.cityMap.get(quTrip.detcity)}${quTrip.detterm}</div>
					</div>
					<img src="img/you.png" style="width: 1.05rem; height: 1.8rem; margin-right: 1rem;"/>
				</div>
				<div class="jingji">
					<div class="btns">
						<div class="pref">
							${quTrip.cbClass}舱
						</div>
						<p class="official">${traveType}</p>						
					</div>
					<div class="btn">
						<div class="btn-left">
							<span>票价:<span class="money">¥${quTrip.price}</span></span>
							<span>机场税:<span class="money">¥${quTrip.taxfee}</span></span>
							<span>燃油税:<span class="money">¥${quTrip.fueltax}</span></span>
						</div>
						<div class="btn-right">
							<a href="javascript:;" class="btna">改退政策</a>
						</div>
					</div>
				</div>
			</div>
			<ul class="item-order">
				<li class="shift-order">
					<div class="list btns">
						<div class="message-order ">
							乘机人
						</div>
						<div>
							<a onclick="showWin()" href="#" mce_href="#"><img src="img/add.png" class="imgs" ></a>
						</div>
					</div>
				</li>
				<li class="shift-order">
					<div class="list btns">
						<div class="message-order btn">
							通知人
						</div>
						<div>
							<img src="img/add.png" class="imgs">
						</div>
					</div>
				</li>
				<li class="shift-order">
					<div class="list module btns" style="border-bottom: 0.1rem dotted #CCCCCC;">
						<div class="message-order ">
							差旅说明
							<span class="type">起飞或到达时间不合适</span>
						</div>
						<div>

							<img src="img/you.png" />

						</div>
					</div>
					<div class="list module btns">
						<div class="message-order ">
							结算方式
							<span class="type">预付款方式</span>
						</div>
						<div>

							<img src="img/you.png" />

						</div>
					</div>
				</li>
				<li class="shift-order">
					<div class="list btns">
						<div class="message-order btn">
							订单审核人
						</div>
						<div>
							<img src="img/add.png" class="imgs">
						</div>
					</div>
				</li>
			</ul>
			<footer class="footer-price">
				<p class="price">总价：<span>¥0</span></p>
				<p class="true">确认</p>
			</footer>
		</section>

	</body>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript">
	/*该退政策提示框的显示和隐藏*/

$('.btna').on('click', function() {
	$('.bun').fadeIn();
});
$('.closedBtn img').on('click', function() {
	$('.bounced').fadeOut();
});
/*航班详情提示框的显示和隐藏*/
$('.title-con').on('click', function() {
	$('.bounceds').fadeIn();
});
$("#addPassenger").on('click', function() {
	window.location.href="ppp/pppp.act?userId=250827";
})

$(function(){
		var mapKey = "${mapKey}";
		var timestamp = "${timestamp}";
		alert("mapKey=" + mapKey);
		alert("timestamp=" + timestamp);
	})
	</script>

</html>