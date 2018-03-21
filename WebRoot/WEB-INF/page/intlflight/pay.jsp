<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<%-- <base href="<%=basePath%>"> --%>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>机票支付</title>
		<link rel="stylesheet" type="text/css" href="../css/intlAllOrder.css" />
	</head>

	<body>
		<div class="wrap">
			<header class="headerS"></header>
			<section>
				<div class="statusOrder">
					<div class="orderS">
						<div>
							<img src="../img/success.png"/>
						</div>
						<div class="sTitle">
							<p>订单提交成功</p>
							<p><c:if test="${payMethod=='预付款支付'||payMethod=='公司月结'}">待审核</c:if><c:if test="${payMethod!='预付款支付'&&payMethod!='公司月结'}">待支付</c:if></p>
						</div>
					</div>
					<div class="orderD">
						<div>
							<span>订单号：</span>
							<span>${data.orderno}</span>
							<a href="../intlflight/orderDetail.act?orderId=${data.orderId}">查看详情＞＞</a>
						</div>
						<div>
							<p>
								<span>支付金额：</span>
								<span class="payMoney">${data.totalPrice}元</span>
							</p>
							<p>(含服务费：<span>${totalServiceFee}</span>元)</p>
						</div>
						<c:if test="${payMethod!='预付款支付'&&payMethod!='公司月结'}">
							<div>
								<span>支付倒计时：</span>
								<span class="payCount">900</span>
							</div>
						</c:if>

					</div>
				</div>
				<c:if test="${payMethod!='预付款支付'&&payMethod!='公司月结'}">
				<div class="payType">
					<div class="typeTitle">支付方式</div>
					<ul class="typeList">
						<li class="treasure">
							<span>支付宝支付</span>
							<span>
								<img src="../img/choose.png"/>
							</span>
						</li>
						<li class="weixin">
							<span>微信支付</span>
							<span>
								<img src="../img/wei.png"/>
							</span>
						</li>
					</ul>
				</div>
				</c:if>
			</section>
			<c:if test="${payMethod!='预付款支付'&&payMethod!='公司月结'}">
			<footer class="toPay">
				<p>去支付</p>
			</footer>
			</c:if>
		</div>
	</body>
	<script type="text/javascript" src="../libs/jquery.min.js"></script>
	<script type="text/javascript">
	/*-----页面加载头部----------*/
		$(".headerS").load("../header.html", function(data) {
			$('.zhixiang').html('温馨提示');
			$('header div img').on('click', function() {
				window.history.back(-1);
			});
		});



		/*------支付宝和微信支付的选择-----*/
		(function($) {
			$(document).ready(function() {
				$('.typeList img').click(function() {
					$(this).attr('src', $(this).attr('src') == '../img/choose.png' ? '../img/wei.png' : '../img/choose.png');
					$(this).parents('.typeList li').siblings().find('img').attr('src', $(this).attr('src') == '../img/choose.png' ? '../img/wei.png' : '../img/choose.png');
				});
			})
		})(jQuery);
		
		/*----倒计时的效果--------*/

		var SysSecond;
		var InterValObj;
		$(document).ready(function() {
			SysSecond = parseInt($(".payCount").html());
			InterValObj = window.setInterval(SetRemainTime, 1000);
		});

		function SetRemainTime() {
			if(SysSecond > 0) {
				SysSecond = SysSecond - 1;
				var second = Math.floor(SysSecond % 60);
				var minite = Math.floor((SysSecond / 60) % 60);
				$(".payCount").html(p(minite) + ":" + p(second));
			} else {
				window.clearInterval(InterValObj);
			}
		};

		function p(s) {
			return s < 10 ? '0' + s : s;
		};
		var url = window.location.href; //获取当前页面的url
		var len = url.length; //获取url的长度值
		var a = url.indexOf("?"); //获取第一次出现？的位置下标
		var b = url.substr(a + 1, len); //截取问号之后的内容
		var c = b.split("&"); //从指定的地方将字符串分割成字符串数组	
		var arr = new Array(); //新建一个数组
		for(var i = 0; i < c.length; i++) {
			var d = c[i].split("=")[1]; //从=处将字符串分割成字符串数组,并选择第2个元素			
		}
		/* $('.payMoney').html(c[0].split("=")[1]+'元'); */
		$('.cn-pay .number1').html('¥' +c[0].split("=")[1] );
		
		$('.toPay').on('click',function(){
			window.location.href = "payStatus.html"
		})
	</script>

</html>
