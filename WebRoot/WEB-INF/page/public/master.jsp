<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/masterpage" prefix="fms" %>
<%@include file="../public/tag.jsp" %>
<fms:MasterPage id="master">
<html>
<head>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/ticket_reservation.css?_=<%=Math.random()%>">
	<title><fms:ContentPlaceHolder id="title"/></title>
</head>

<body>
	<div class="header">
				
				<ul class="res-header-main" style=" ">
					<li id="del_res" class="res-header-main-li1" style="">
					<c:if test="${flag==1 }"><!-- flag=1显示home和个人中心 -->
					<img src="img/home.png" onclick="" style="width:2rem;height: 2rem;padding-top: 1rem;cursor: pointer;padding-left:0.4rem;">
					</c:if>
					<c:if test="${flag==2 }"><!-- flag=2显示返回箭头 -->
					<img id="back" onclick="javascript:history.back(-1);" src="<%=basePath %>img/header.gif"/  style="vertical-align: middle;margin-left:1.3rem;">
					</c:if>
					</li>
					<li class="res-header-main-li2" style=" ">
						<span id="clear_f " class="res-header-main-li2-span" style="  ">
							${pageType }
						</span>
					</li>
					<li class="res-header-main-li3" style="padding-right: 0.4rem;">
						<c:if test="${user==null }" >登录</c:if>
						<c:if test="${flag==1 }">
						<c:if test="${user!=null }" >
							<div onclick="window.location.href='<%=basePath%>/wetrip/show.act';return false;">
								<img src="<%=basePath %>/images/myicon.png" style="width: 1.9rem;height: 1.9rem; margin-top:1rem;" /><a href="" style="display: inline-block;padding-top: 0.2rem;padding-left: 1rem;color:#ffffff;">${user.username}</a>
							</div>
						</c:if>
						</c:if>
					</li> 
				</ul>
		</div>
	<fms:ContentPlaceHolder id="nav"/>
	<fms:ContentPlaceHolder id="main"/>
</body>
		<!-- <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="libs/data/mobiscroll_date.js" charset="gb2312"></script>
		<script type="text/javascript" src="libs/data/mobiscroll.js"></script>
		<script type="text/javascript" src="js/MyConfirm.js"></script> -->
</html>
</fms:MasterPage>