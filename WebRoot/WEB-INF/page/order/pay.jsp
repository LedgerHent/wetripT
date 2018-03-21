<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>机票支付</title>
		<link rel="stylesheet" type="text/css" href="css/gublic.css" />
		<link rel="stylesheet" type="text/css" href="css/btn.css" />
		<link rel="stylesheet" type="text/css" href="css/pay.css" />
	</head>

	<body>
		<section>
			<div id="header"></div>
			<div class="content">
				<div class="cn-pay">
					<p>
						订单号
						<span class="number">${result.data.data.orderNo }</span>
					</p>
					<p>
						应付金额
						<span class="number1">¥${result.data.data.amount }</span>
					</p>
				</div>
				<ul class="pay-type">
					<li class="treasure" style="background: url(img/zhifubao.png) no-repeat;background-size: 0.96rem 0.96rem; background-position: center left;">
						<span style="margin-left: 1.36rem;">支付宝支付</span>
						<span class="u-icon-checkTitle highlight"></span>
					</li>
					<li class="weixin" style="background: url(img/weixin.png) no-repeat; background-size: 0.96rem 0.96rem; background-position: center left;">
						<span style="margin-left: 1.36rem;">微信支付</span>
						<span class="u-icon-checkTitle"></span>
					</li>
				</ul>
			</div>
			<footer class="footer">
				确定
			</footer>
		</section>
	</body>
	<script type="text/javascript" src="libs/zepto.min.js"></script>
	<script type="text/javascript">
		$('#header').load('header.html');
		$('.u-icon-checkTitle').on('touchstart',function(){
			$(this).parent().siblings().find('.u-icon-checkTitle').removeClass('highlight');
			$(this).toggleClass('highlight');
		});
		(function(){
			var html = document.documentElement;
			var hWidth = html.getBoundingClientRect().width;
			html.style.fontSize = hWidth/15 + 'px';
		})()
	</script>

</html>