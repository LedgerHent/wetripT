<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新单填写</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/common.js"></script>
	<script type="text/javascript">
		var bookingUserId = '${orderResponse.bookingUserId }';
	</script>
</head>

<body>
<section>
	<header class="header" id="header">
		<div style="margin: auto 0;">
			<img src="../hotelimg/header.png" / class="you">
		</div>
		<p>酒店信息</p>
	</header>
	<div class="statusOrder">
		<div class="orderS">
	 <c:if test="${isDetail ne 'detail'}"> 
			<div>
				<img src="../hotelimg/success.png" />
			</div>
			<div class="sTitle">
				<p>订单提交成功</p>
				<p>${orderResponse.orderStatueName }</p>
			</div>
		 </c:if> 
		</div>
		<div class="orderD">
			<div>
				<span>订单号：</span>
				<span>${orderResponse.orderNo }</span>
				<a href="javascript:;void(0)" onclick="toOrderDetail('${orderResponse.orderNo }')">查看详情＞＞</a>
			</div>
			<div>
				<p>
					<span>支付金额：</span>
					<span class="payMoney">${orderResponse.actualPayablePrice }元</span>
				</p>
				<p>(含服务费：${orderResponse.serviceFee }元)</p>
			</div>
<!-- 			<div>
				<span>支付倒计时：</span>
				<span class="payCount"></span>
			</div> -->
		</div>
	</div>
	<c:if test="${orderResponse.orderStatue eq '0000400002' }">
		<div class="payType">
			<div class="typeTitle">支付方式</div>
			<ul class="typeList">
				<li class="treasure">
					<span>支付宝支付</span>
					<span>
						<img src="../hotelimg/choose.png" class="zhifubao"/>
					</span>
				</li>
				<li class="weixin">
					<span>微信支付</span>
					<span>
						<img src="../hotelimg/wei.png"/>
					</span>
				</li>
			</ul>
			
		</div>
		<footer class="toPay">
			<p>去支付</p>
		</footer>
	</c:if>
</section>
</body>
	<script type="text/javascript">
		/*------支付宝和微信支付的选择-----*/
		(function($) {
			$(document).ready(function() {
				$('.typeList img').click(function() {
					$(this).attr('src', $(this).attr('src') == '../hotelimg/choose.png' ? '../hotelimg/wei.png' : '../hotelimg/choose.png');
					$(this).parents('.typeList li').siblings().find('img').attr('src', $(this).attr('src') == '../hotelimg/choose.png' ? '../hotelimg/wei.png' : '../hotelimg/choose.png');
				});
			})
		})(jQuery);

		$(function() {
			var countTime = parseInt(900); //在这里设置每道题的答题时长
			function countDown(countTime) {
				var timer = setInterval(function() {
					if(countTime >= 0) {
						showTime(countTime);
						countTime--;
					} else {
						clearInterval(timer);
					}
				}, 1000);
			}
			countDown(countTime);

			function showTime(countTime) {
				var minute = Math.floor(countTime / 60);
				var second = countTime - minute * 60;
				$('.payCount').html(p(minute) + ":" + p(second))
			}
		});
		/*---------补零---------*/
		function p(s) {
			return s < 10 ? '0' + s : s;
		};

		$('.toPay').on('click', function() {
		    var alipay=$(".typeList .zhifubao").attr("src");
		    var payType="";
		    if(alipay=='../hotelimg/choose.png'){  //已选择支付宝
		      payType=11;
		    }else{
		      payType=21;
		    }
		window.location.href=base_path +'/hotelOrder/payOrder.act?from=orderSuccess&orderId='+'${orderResponse.orderNo}'+'&payType='+payType+'&price='+${orderResponse.actualPayablePrice};
		});
		
		/*---------返回上一页--------*/
		$('.header .you').click(function() {
			window.location.href = base_path + 'orderInfo/listAllOrderInit.act';
		});
	
		/**
		*	跳转订单详情页
		*/
		function toOrderDetail(orderNo){
			window.location.href = base_path + 'hotelOrder/orderpage.act?flag=1&orderNo=' + orderNo;
		}
	</script>
</body>
</html>