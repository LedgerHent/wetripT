<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>温馨提示</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
	</head>
<script type="text/javascript">
</script>
	<body style="display: block;">
		<section>
			<header>
				<div>
					<!-- <img src="img/header.gif" --> 
				</div>
				<p class="zhixiang">温馨提示</p>
			</header>
			<div class="status">
				<div class="orderStatus">
					<div class="orderImg">
						<img src="img/failure.png" />
					</div>
					<div class="orderContent">
						<p>${result}</p>
					</div>
				</div>
			</div>
		</section>
	</body>

</html>
<script src="libs/gublic.js"></script>