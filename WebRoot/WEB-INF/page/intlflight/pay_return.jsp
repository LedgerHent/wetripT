<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>支付${success?"成功":"失败"}</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
	</head>

	<body onpagehide="$.fn.LoadingHide()">
		<section>
			<header>
				<!-- <div>
					<img src="img/header.gif" />
				</div> -->
				<p class="zhixiang">温馨提示</p>
			</header>
			<c:if test="${success }">
				<div style="display: ${success?'block':'none'};">
					<div class="indexContent">
						<div class="state">
							<div class="stateImg">
								<img src="img/smile.png " />
							</div>
							<p>恭喜您，支付成功!</p>
						</div>
						<div class="stateMessage">
							<div class="orderNumber">
								<span>订单号：</span>
								<span class="codeNum">${para.orderNo }</span>
							</div>
							<div class="orderpaid">
								<span class="amount">实付金额：</span>
								<span class="moneyOrder">${para.price }</span>
							</div>
							<div class="clickTurn">
								<div class="viewOrder" onclick="window.location.href='<%=basePath%>order/detail.act?orderId=${para.orderId }'">
									查看订单
								</div>
								<div class="viewOrder"  onclick="window.location.href='<%=basePath%>flight/search.act'">
									返回首页
								</div>
							</div>
						</div>
					</div>
					<footer class="footerSure" onclick="window.location.href='<%=basePath%>flight/search.act'">
						<p>确认</p>
					</footer>
				</div>
			</c:if>
			<c:if test="${!success }">
				<div style="display: ${success?'none':'block'};">
					<div class="indexContent">
						<div class="state failure">
							<div class="stateImg">
								<img src="img/cry.png" />
							</div>
							<p>很抱歉，支付失败!</p>
						</div>
						<div class="stateMessage">
							<div class="information">
								<span>交易状态：</span>
								<span>${para.msg }</span>
							</div>
							<!-- <div class="information">
								<span>提示信息：</span>
								<span>卡头有误，请从新输入，如有问题请联系人工客服。</span>
							</div> -->
							<div class="returnHome clickTurn">
								<div class="viewOrder"  onclick="window.location.href='<%=basePath%>flight/search.act'">
									返回首页
								</div>
							</div>
						</div>
					</div>
					<footer class="footerSure payAgain" onclick="window.location.href='<%=basePath%>flight/toPay.act?orderId=${para.orderId}';">
						<p>重新支付</p>
					</footer>
				</div>
			</c:if>
		</section>
	</body>
	<script type="text/javascript " src="libs/jquery.min.js "></script>
	<script type="text/javascript " src="libs/gublic.js "></script>

</html>
