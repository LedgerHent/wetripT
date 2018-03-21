<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>
	<head>
	    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" type="text/css" href="home/homeCss/all_index.css" />
		<style type="text/css">
			@font-face {
				font-family: "方正字迹-叶根友特楷简体";
				src: url("home/homeCss/FZZJ-YGYTKJW.ttf");
			}
			
			p {
				font-family: "方正字迹-叶根友特楷简体";
				font-size: 2.8rem;
				color: #e1e1e1;
				text-align: center;
				margin: 5rem 0;
			}
		</style>
	</head>

	<%--<body onpagehide="$.fn.LoadingHide()">
	    <div id="container">
	        <ul>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=国内机票';">国内机票</span></li>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=国际机票';">国际机票</span></li>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=酒店';">酒店</span></li>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=火车票';">火车票</span></li> 
	        </ul>
	        <ul>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=国内机票订单列表';">国内机票订单列表</span></li>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=酒店订单列表';">酒店订单列表</span></li>
	            <li><span onclick="window.location.href='common/toBusiness.act?type=火车票订单列表';">火车票订单列表</span></li>  
	        </ul>
	        
	        
		</div>
	</body>--%>
	<body>
		<section style="margin: auto;max-width:32.5rem;">
			<div id="banner">
				<img src="home/homeImg/banner.png" />
			</div>
			<div id="index_img">
				<div class="index_t">
					<div>
						<span onclick="window.location.href='common/toBusiness.act?type=国内机票';"><img src="home/homeImg/in_ticket.png" /></span>
					</div>
					<div>
						<span onclick="window.location.href='common/toBusiness.act?type=国际机票';"><img src="home/homeImg/inter_ticket.png" /></span>
					</div>
					<div>
						<span onclick="window.location.href='common/toBusiness.act?type=火车票';"><img src="home/homeImg/ticket.png" /></span>
					</div>
				</div>
				<div class="index_b">
					<div>
						<span onclick="window.location.href='common/toBusiness.act?type=酒店';"><img src="home/homeImg/hotel.png" /></span>
					</div>
					<div>
						<a href="tel:4006-020-365"><img src="home/homeImg/service.png" /></a>
					</div>
				</div>
			</div>
			<p>凯撒商旅，服务保证</p>
		</section>
	</body>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript">
		
		
		$('#index_img img').each(function(index){
			$(this).mousedown(function() {
				addClass(this);
			});
			$(this).mouseup(function() {
				removeClass(this);
			});
			
			$(this).on('touchstart',function() {
				addClass(this);
			});
			
			$(this).on('touchend',function() {
				removeClass(this);
			});
			
		})
		
		function addClass(obj){
			$(obj).parent().addClass('imgScale')
		}
		
		function removeClass(obj){
			$(obj).parent().removeClass('imgScale')
		}
	</script>

</html>