<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>
			<c:if test="${tripType==1 || flag==1}">${applicationScope.cityMap.get(trips[0].depAirport)} ⇀ ${applicationScope.cityMap.get(trips[0].arrAirport)}</c:if>
			<c:if test="${(tripType==2 || tripType==3) && flag==2}">${applicationScope.cityMap.get(trips[1].depAirport)} ⇀ ${applicationScope.cityMap.get(trips[1].arrAirport)}</c:if>
		</title>
		
		<%-- --%>
		<%
		  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		  response.setHeader("Expires", "0");
		  response.setHeader("Pragma","no-cache");
		%>
		
		
		<!-- <link href="css/mui.min.css" rel="stylesheet" /> -->
		<!-- <link href="css/style.css" rel="stylesheet" /> -->
		<!--<link rel="stylesheet" href="css/app.css" />-->
		<!-- <link href="css/mui.picker.css" rel="stylesheet" />
		<link href="css/mui.poppicker.css" rel="stylesheet" /> -->
		<link rel="stylesheet" type="text/css" href="css/reset.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
		
		<style>
			
		/*新的css代  */
		
			html {
				font-size: 20px;
				font-family: Arial, "Microsoft YaHei";
				font-weight: normal;
			}
			
			body {
				background: #F0EFF5;
			}
			
			
			.tlist-top {
				background: #00AFEC;
				display: flex;
				width: 100%;
				height: 4rem;
			}
			
			.tlist-top1 {
				flex: 1;
				cursor: pointer
			}
			
			.tlist-top1-d1 {
				padding-left: 0.75rem;
				padding-top: 1rem;
				color: #ffffff;
				font-size: 1.2rem;
				text-align: center;
				text-align: left;
			}
			
			.tlist-top1-d2 {
				padding-left: 0.75rem;
				color: #ffffff;
				font-size: 1.2rem;
				text-align: center;
				text-align: left;
			}
			
			.tlist-top2 {
				flex: 1;
				margin: auto;
			}
			
			.tlist-top2-div {
				height: 3.5rem;
				width: 10rem;
				margin: auto;
				margin-top: 0.25rem;
				margin-bottom: 0.25rem;
				background: #f0eff5;
				border-radius: 5px;
				
			}
			
			.tlist-top2-div-d1 {
				font-size: 1.2rem;
				padding-top: 0.65rem;
				padding-bottom: 0.3rem;
				text-align: center;
				color:#ffce00;
			}
			
			.tlist-top2-div-d2 {
				font-size: 1.2rem;
				text-align: center;
				color:#ffce00;
			}
			
			.tlist-top3 {
				flex: 1;
				cursor: pointer;
			}
			
			.tlist-top3-d1 {
				padding-right: 0.75rem;
				padding-top: 1rem;
				font-size: 1.2rem;
				text-align: right;
				color: #ffffff
			}
			
			.tlist-top3-d2 {
				padding-right: 0.75rem;
				font-size: 1.2rem;
				text-align: right;
				color: #ffffff
			}
			
			
			.tlist-footer {
				background: #434456;
				display: flex;
				height: 5rem;
				position: fixed;
				left: 0;
				bottom: 0;
				width: 100%;
			}
			
			.tlist-footer-a1 {
				flex: 1;
			}
			
			.tlist-footer-a1-d1 {
				height: 3.4rem;
				text-align: center;
				line-height: 3.4rem;
				display: flex;
				
			}
			.tlist-footer-a1-d1-img {
				width: 2.3rem;
				height: 2.3rem;
				text-align: center;
				margin: auto;
			}
			
			.tlist-footer-a1-d2 {
				font-size: 1rem;
				text-align: center;
				color: #FFFFFF;
			}
			
			.tlist-footer-a2 {
				flex: 1;
			}
			
			.tlist-footer-a2-d1 {
				height: 3.4rem;
				text-align: center;
				line-height: 3.4rem;
				display: flex;
			}
			
			.tlist-footer-a2-d1-img {
				width: 2.3rem;
				height: 2.3rem;
				text-align: center;
				margin: auto;
			}
			
			.tlist-footer-a2-d2 {
				font-size:1rem;
				text-align: center;
				color: #FFFFFF;
			}
			
			.tlist-footer-a3 {
				flex: 1;
			}
			
			.tlist-footer-a3-d1 {
				height: 3.4rem;
				text-align: center;
				line-height: 3.4rem;
				display: flex;
			}
			
			.tlist-footer-a3-d1-img {
				width: 2.3rem;
				height: 2.3rem;
				text-align: center;
				margin: auto;
			}
			
			.tlist-footer-a3-d2 {
				font-size: 1rem;
				text-align: center;
				color: #00AFEC
			}
			
			
			.tic-list {
				padding-top: 0.2rem;
			}
			.tic-list ul{
				cursor: pointer
			}
			.tic-list-li1 {
				border-radius: 0.5rem;
				margin-left: 0.5rem;
				margin-top: 0.5rem;
				margin-right: 0.5rem;
				margin-bottom: 0.5rem;
				background: #FFFFFF;
				width: auto;
				height: 9rem;
			}
			
			.tic-list-li1-con {
				height: 7rem;
			}
			
			.tic-list-li1-con-main {
				display: flex;
				height: 7rem;
			}
			
			.con-main-li1 {
				flex: 20%;
			}
			
			.con-main-li1-d1 {
				font-size: 1.8rem;
				padding-top: 2rem;
				color: #222222;
				padding-bottom: 1rem;
				padding-left: 1rem;
			}
			
			.con-main-li1-d2 {
				font-size: 1.2rem;
				color: #666666;
				padding-left: 1rem;
			}
			
			.con-main-li2 {
				flex: 20%;
				text-align: center;
			}
			
			.con-main-li2-d1 {
				font-size: 1rem;
				padding-top: 2rem;
			}
			
			
			
			.con-main-li2-d2-img {
				width: 9rem;
				height: 0.5rem;
			}
			
			.con-main-li2-d2 {
				color: #00AFEC;
				font-size: 1rem;
				padding-top: 0.25rem;
			}
			
			.con-main-li3 {
				flex: 20%;
				text-align: center;
			}
			
			.con-main-li3-d1 {
				font-size: 1.8rem;
				padding-top: 2rem;
				color: #222222;
				padding-bottom: 1rem;
			}
			
			.con-main-li3-d2 {
				font-size: 1.2rem;
				color: #666666;
			}
			
			.con-main-li4 {
				flex: 40%;
			}
			
			.con-main-li4-d1 {
				color: #ff7e00;
				font-size: 2rem;
				text-align: right;
				padding-top: 2rem;
				padding-bottom: 0.5rem;
				margin-right: 1rem;
			}
			
			.con-main-li4-d1-i {
				font-size: 1rem;
				font-style: normal;
			}
			
			.con-main-li4-d2 {
				border: 1px solid #ffa66b;
				line-height: 1.5rem;
				text-align: center;
				border-radius: 5rem;
				width: 4rem;
				height: 1.5rem;
				display: flex;
				float: right;
				margin-right: 1rem;
			}
			
			.con-main-li4-d2-span {
				font-size: 0.9rem;
				flex: 1;
				color: #999999;
			}
			
			.tic-list-li1-con-icon-main {
				display: flex;
			}
			
			.icon-main-li1 {
				display: flex;
				padding-left: 1rem;
			}
			
			.icon-main-li1-img {
				width: 1rem;
				height: 1rem;
			}
			
			.icon-main-li1-span {
				font-size: 1rem;
				color: #999999;
				padding-left: 1rem;
			}
			
			.icon-main-li2 {
				font-size: 1rem;
				color: #999999;
				padding-left: 1rem;
			}
			
			.icon-main-li3 {
				font-size: 1rem;
				color: #999999;
				padding-left: 1rem;
			}
			
			
			.res-u1 {
				display: flex;
			}
			
			.res-u2 {
				display: flex;
			}
			
			.res-u3 {
				display: flex;
			}
			
			.grid {
				display: flex;
				width: auto;
				height: 2.5rem;
				border: 1px solid #CCCCCC;
				flex: 1;
				margin-right: 0.75rem;
				border-radius: 5px;
			}
			
			.grid span {
				margin: auto;
				line-height: 1.25rem;
				font-size: 1rem;
			}
			
			.active {
				color: #00AFEC;
				border: 1px solid #00AFEC;
			}
			
			.res-mask {
				width: 100%;
				height: 100%;
				background-color: #000000;
				opacity: 0.5;
				display: none;
				z-index: 66;
				position: absolute;
				top: 0;
				left: 0;
			}
			
			.sel-res {
				display: none;
				position: absolute;
				left: 0;
				bottom: 0;
				z-index: 99;
				width: 100%;
				padding-bottom: 1rem;
				background: #FFFFFF;
				position: fixed;
				top: 0;
				left: 0;
				"

			}
			
			.res-header-main {
				display: flex;
				padding: 0 0.75rem;
				height: 4rem;
				background: #124354;
				line-height: 4rem;
			}
			
			.res-header-main-li1 {
				flex: 1;
				text-align: left;
				color: #FFFFFF;
				font-size: 1.3rem;
			}
			
			.res-header-main-li2 {
				flex: 1;
				text-align: center;
				margin: auto;
			}
			
			.res-header-main-li2-span {
				margin: auto;
				color: #FFFFFF;
				width: 6rem;
				height: 2.5rem;
				background: #6293a4;
				display: block;
				line-height: 2.5rem;
				font-size: 1.2rem;
				border-radius: 5px;
			}
			
			.res-header-main-li3 {
				flex: 1;
				text-align: right;
				color: #FFFFFF;
				font-size: 1.3rem;
			}
			
			
			.scroll-up {
				display: none;
				position: fixed;
				bottom: 5rem;
				right: 1rem;
			}
			
			.price-1 {
				color: #FFFFFF;
			}
			
			.scroll-up-img {
			    width: 3rem;
    			height: 2.5rem;
			}

.res{
	display: flex;
}
.res-con{
	flex: 1;margin-left: 0.75rem;margin-right: 0.75rem; 
}
.res-con-nav{
	display: flex; padding-top: 1.5rem;
}
.res-con-nav-span{
	color: #CCCCCC;font-size: 1rem;
}
.res-con-main{
	margin-top: 1rem;
}
.hk-nav{
	flex: 1;margin-left: 0.75rem;margin-right: 0.75rem;
}
.hk-nav-main{
	display: flex; padding-top:1.5rem;
}
.hk-nav-main-span{
	color: #CCCCCC; font-size: 1rem; 
}
.cabin-select{
	flex: 1;margin-left: 0.75rem;margin-right: 0.75rem; 
}
.cabin-select-nav{
	display: flex; padding-top: 1.5rem;
}
.cabin-select-nav-span{
	color: #CCCCCC; font-size: 1rem; 
}
.res-u2{
				width: 100%;
			 	display: flex;
  				flex-flow: row wrap;
  				align-content: flex-start;
			}
			.res-u2 li{
				flex: 0 0 22.5%;
				margin-top: 1rem;
				 margin-right: 2%;
			}		
/* 筛选弹出表单的第四个*/
			.res-u2 li:nth-child(4){			
				margin-right: 0;
			}
			.res-u2 li:nth-child(8){			
				margin-right: 0;
			}	
		</style>
		
		
	</head>

	<body style="display:none" onpagehide="$.fn.LoadingHide()">
		<div id="container">
		<form id="one" action="flight/getFlightDetail.act" method="post">
			<input type="hidden" id="timestamp" name="timestamp" value="${timestamp }" /><!-- 时间戳 -->
			<input type="hidden" id="mapKey1" name="mapKey1" value="${mapKey1}" />
			<input type="hidden" id="mapKey" name="mapKey" value="${mapKey}" />
			<input type="text" id="excessinfo1" name="excessinfo1" value="${excessinfo1}" />
			<input type="hidden" id="flag" name="flag" value="${flag}" /><!-- 说明查的是哪一航程-->
			<input type="hidden" id="type" name="type" value="${type }" />
			<input type="hidden" id="tripType" name="tripType" value="${tripType }" />
			<input type="hidden" id="cabinType" name="cabinType"  value="${cabinType }" />
			<input type="hidden" id="airline"  name="airline" value="${airline }" />
			<input type="hidden" id="selectusers" name="selectusers" value="${selectusers }"/>
			<c:forEach items="${trips}" var="trip" varStatus="st">
				<input type="hidden" id="flowId${st.count}"  name="flowId" value="${trip.flowId }" />
				<input type="hidden" id="depAirport${st.count}" name="depAirport" value="${trip.depAirport }" />
				<input type="hidden" id="arrAirport${st.count}" name="arrAirport" value="${trip.arrAirport }" />
				<input type="hidden" id="date${st.count}" name="date" value="${trip.date }" />
			</c:forEach>
			<input type="hidden" id="timeArea" name="listParam_timeArea" value="${fp.listParam_timeArea }"/>
			<input type="hidden" id="code" name="listParam_code" value="${fp.listParam_code }"/>
			<input type="hidden" id="cabin" name="listParam_cabin" value="${fp.listParam_cabin }"/>
			
		</form>
		<!--顶边栏-->
		<header class="tlist-top">
			<a id="before" class="tlist-top1">
				<div class="tlist-top1-d1"><span>前一天</span></div>
				<div class="tlist-top1-d2"><span>￥0</span></div>
			</a>
			<a class="tlist-top2">
				<div class="tlist-top2-div">
					<div id="today" class="tlist-top2-div-d1" value="${flag==1?trips[0].date:trips[1].date }"></div>
					<div class="tlist-top2-div-d2">￥<span style="font-size:1.4rem">${result.data!=null?result.data.get(0).price:0}</span></div>
				</div>
			</a>
			<a id="after" class="tlist-top3">
				<div class="tlist-top3-d1"><span>后一天</span></div>
				<div class="tlist-top3-d2"><span>￥0</span></div>
			</a>
		</header>
		<!--底边栏-->
		<footer class="tlist-footer">
			<a id="show_res" class="tlist-footer-a1">
				<div class="tlist-footer-a1-d1"/><img src="img/bs-shaixuan.png" class="tlist-footer-a1-d1-img" /></div>
				<div class="tlist-footer-a1-d2">筛选</div>
			</a>
			<a class="tlist-footer-a2" id="item_date" mode="0">
				<div class="tlist-footer-a2-d1"><img src="img/bs-shijian.png" class="tlist-footer-a2-d1-img" /></div>
				<div id="date" class="tlist-footer-a2-d2">时间</div>
			</a>

			<a class="tlist-footer-a3" id="item_price" class="paixu" mode="0">
				<div class="tlist-footer-a3-d1"><img src="img/jiage.png" class="tlist-footer-a3-d1-img" /></div>
				<div id="price" class="tlist-footer-a3-d2">价格由低到高</div>
			</a>
		</footer>
		<!--筛选表单-->
		<!--遮罩层-->
		<div id="res_mask" class="res-mask" style=""></div>
		<!--遮罩层-->
		<div id="sel_res" class="sel-res" style=" ">
			<!--筛选表单头部-->
			<div class="res-header">
				<ul class="res-header-main" style=" ">
					<li id="del_res" class="res-header-main-li1" style="">取消</li>
					<li class="res-header-main-li2" style=" ">
						<span id="clear_f " class="res-header-main-li2-span" style="  ">
					清空筛选
					</span>
					</li>
					<li class="res-header-main-li3" style="">确定</li>
				</ul>
			</div>
			<!--分割线-->
			<div class="res" style=" ">
				<div class="res-con" style="">
					<div class="res-con-nav" style=" ">
						<span id=" " class="res-con-nav-span" style=" ">
					起飞时间
					</span>
					</div>
					<div class="res-con-main" style="">
						<ul class="res-u1 " style="display: flex; ">
							<li class="grid active "><span val="0">不限</span></li>
							<li class="grid "><span val="1">0:00-12:00</span></li>
							<li class="grid "><span val="2">12:00-13:00</span></li>
							<li class="grid " style="margin-right: 0;"><span val="3">13:00-18:00</span></li>
						</ul>
					</div>
				</div>
			</div>
			<!--分割线-->
			<div class="hk ">
				<div class="res " style="display: flex; ">
					<div class="hk-nav " style=" ">
						<div class="hk-nav-main" style="  ">
							<span id="" class="hk-nav-main-span" style="">
								航空公司
							</span>
						</div>
						<div style="margin-top: 0.2rem; ">
							<ul class="res-u2 res-u22 ">
								<li class="grid active "><span value="0">不限</span></li>
								<c:if test="${alcodes!=null&&alcodes.size()>0 }">
									<c:forEach items="${alcodes }" var="code">
										<li class="grid "><span value="${code}">${applicationScope.ac2NameMap.get(code) }</span></li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--分割线-->
			<div class="res " style=" display: flex; ">
				<div class="cabin-select" style="">
					<div class="cabin-select-nav" style="  ">
						<span id=" " class="cabin-select-nav-span" style="">
					舱位选择
				</span>
					</div>
					<div style="margin-top: 0.2rem; ">
						<ul class="res-u3 " style="display: flex; ">
							<li class="grid active "><span value="不限">不限</span></li>
							<li class="grid "><span value="经济舱">经济舱</span></li>
							<li class="grid "><span value="公务舱">公务舱</span></li>
							<li class="grid " style="margin-right: 0; " value="头等舱"><span>头等舱</span></li>
						</ul>

					</div>
				</div>
			</div>
		</div>
		<!--列表-->
		<div class="tic-list" style="">
			<c:if test="${result.status==0 and result.data != null and result.data.size()>0}">
			<c:forEach items="${result.data }"  var="obj" varStatus="status">
				<c:if test="${status.index==0 }">
					<c:set var="key" value="${obj.flightNo}" />
			<ul class="tic-list-ul" cabin="${applicationScope.cabinNameMap.get(obj.cabin) }" name="cabinList" id="list_${status.index}" mapkey="${obj.mapKey }">
				<li class="tic-list-li1" style="">
					<div class="tic-list-li1-con" style="">
						<ul class="tic-list-li1-con-main" style="">
							<li class="con-main-li1" style="">
								<div class="con-main-li1-d1" style="" timestamp="${obj.depDateTime.getTime() }">
									${mt:fmtByStyle(obj.depDateTime,'HH:mm') }
								</div>
								<div class="con-main-li1-d2" style="">
									${applicationScope.cityMap.get(obj.depAirport) }
								</div>
							</li>
							<li class="con-main-li2" style="">
								<div class="con-main-li2-d1" style="">
									${mt:getTimeDis(obj.depDateTime,obj.arrDateTime) }
								</div>
								<div class="con-main-li2-d2" style="">
									<c:if test="${obj.stops>0 }">
										<img src="icon/dd.png.gif" class="con-main-li2-d2-img" style="" />
									</c:if>
									<c:if test="${obj.stops<=0 }">
										<img src="img/zhifei.png" class="con-main-li2-d2-img" style="" />
									</c:if>
								</div>
								<div class="con-main-li2-d2" style="">
									${obj.stops>0?'经停':'直飞' }
								</div>
							</li>
							<li class="con-main-li3" style="">
								<div class="con-main-li3-d1" style="">
									${mt:fmtByStyle(obj.arrDateTime,'HH:mm') }
								</div>
								<div class="con-main-li3-d2" style="">
									${applicationScope.cityMap.get(obj.arrAirport) }
								</div>
							</li>
							<li class="con-main-li4" style="">
								<div class="con-main-li4-d1" style="">
									<i class="con-main-li4-d1-i" style="">￥</i><span class="orderid">${obj.price }</span>
								</div>
								<div class="con-main-li4-d2" style="">
									<span class="con-main-li4-d2-span" style="">${mt:fmtDiscount(obj.discount) }</span>
								</div>
							</li>

						</ul>
					</div>
					<div style="" class="tic-list-li1-con-icon">
						<ul class="tic-list-li1-con-icon-main" style="">
							<li class="icon-main-li1" style="">
								<img src="${applicationScope.iconServerURL}${obj.airline }.gif" class="icon-main-li1-img" style="" />
								<span class="icon-main-li1-span" value="${obj.airline }" style="">
										${obj.flightNo}
									</span>
							</li>
							<li class="icon-main-li2" style="">
								${applicationScope.planeTypeMap.get(obj.planeType) }
							</li>
							<c:set var="cabinkey" value="${obj.airline}_${obj.cabin}" />
							<li class="icon-main-li3" style="">
								${applicationScope.cabinNameMap.get(cabinkey) }
							</li>
							<li class="icon-main-li3"  style="text-align:right;"> 
								<c:if test="${obj.excessinfo.split('_')[0] ne '0'}">
									不符合差旅标准 
								</c:if>
							</li>
						</ul>
					</div>
				</li>
			</ul>
			</c:if>
			<!--第二种-->
			<c:if test="${status.index>0 }">
					<c:choose>
						<c:when test="${obj.flightNo ne key}">
							<c:set var="key" value="${obj.flightNo }" />
			<ul class="tic-list-ul" cabin="${applicationScope.cabinNameMap.get(obj.cabin) }" name="cabinList" id="list_${status.index}" mapkey="${obj.mapKey }">
				<li class="tic-list-li1" style="">
					<div class="tic-list-li1-con" style="">
						<ul class="tic-list-li1-con-main" style="">
							<li class="con-main-li1" style="">
								<div class="con-main-li1-d1" style=""  timestamp="${obj.depDateTime.getTime() }" >
									${mt:fmtByStyle(obj.depDateTime,'HH:mm') }
								</div>
								<div class="con-main-li1-d2" style="">
									${applicationScope.cityMap.get(obj.depAirport) }
								</div>
							</li>
							<li class="con-main-li2" style="">
								<div class="con-main-li2-d1" style="">
									${mt:getTimeDis(obj.depDateTime,obj.arrDateTime) }
								</div>
								<div class="con-main-li2-d2" style="">
									<c:if test="${obj.stops>0 }">
										<img src="icon/dd.png.gif" class="con-main-li2-d2-img" style="" />
									</c:if>
									<c:if test="${obj.stops<=0 }">
										<img src="img/zhifei.png" class="con-main-li2-d2-img" style="" />
									</c:if>
								</div>
								<div class="con-main-li2-d2" style="">
									${obj.stops>0?'经停':'直飞' }
								</div>
							</li>
							<li class="con-main-li3" style="">
								<div class="con-main-li3-d1" style="">
									${mt:fmtByStyle(obj.arrDateTime,'HH:mm') }
								</div>
								<div class="con-main-li3-d2" style="">
									${applicationScope.cityMap.get(obj.arrAirport) }
								</div>
							</li>
							<li class="con-main-li4" style="">
								<div class="con-main-li4-d1" style="">
									<i class="con-main-li4-d1-i" style="">￥</i><span class="orderid">${obj.price }</span>
								</div>
								<div class="con-main-li4-d2" style="">
									<span class="con-main-li4-d2-span" style="">${mt:fmtDiscount(obj.discount) }</span>
								</div>
							</li>

						</ul>
					</div>
					<div style="" class="tic-list-li1-con-icon">
						<ul class="tic-list-li1-con-icon-main" style="">
							<li class="icon-main-li1" style="">
								<img src="${applicationScope.iconServerURL}${obj.airline }.gif" class="icon-main-li1-img" style="" />
								<span class="icon-main-li1-span" value="${obj.airline }" style="">
										${obj.flightNo}
									</span>
							</li>
							<li class="icon-main-li2" style="">
								${applicationScope.planeTypeMap.get(obj.planeType) }
							</li>
							<c:set var="cabinkey" value="${obj.airline}_${obj.cabin}" />
							<li class="icon-main-li3" style="">
								${applicationScope.cabinNameMap.get(cabinkey) }
							</li>
							<li class="icon-main-li3" style="text-align: right;border: 0px solid red;"> 
								<c:if test="${obj.excessinfo.split('_')[0] ne '0'}">
									不符合差旅标准 
								</c:if>
							</li>
						</ul>
					</div>
				</li>
			</ul>
									</c:when>
					</c:choose>
				</c:if>
			</c:forEach>
		</c:if>
		</div>
		<!--防止最后一个列表被底边栏遮挡  -->
		<div style="width:100%;height:4.5rem"></div>
		<!--点击返回顶部按钮-->
	
		<div class="scroll-up" style="display: none;position: fixed;bottom: 80px;right: 20px;">
			<img src="./images/top.png" style="width: 40px;height: 30px;">
		</div>
	</div>
	</body>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/page/DateUtil.js"></script>
	<script type="text/javascript" src="js/page/comm.js"></script>
	<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript">
	    //window.onpagehide=LoadingHide();
		//$(document).LoadingHide();
	/* 	$.pagehide= function (fn){
		  if(typeof window.onpageshow=="undefined")
		 $(document).LoadingHide();
		 else
		 $(window).bind("pageshow",fn);
		};
		
		$.pageshow(function (){
		  alert("page show");
		  alert(typeof window.onpageshow == "undefined")
		
		}); */
		//$(window).unload($.fn.LoadingHide());
		//监听视口大小
		function setSize() {
			var iHtml = document.querySelector('html');
			var w = iHtml.getBoundingClientRect().width;
			w = w > 750 ? 750 : w;
			iHtml.style.fontSize = w / 37.5 + 'px';
			$("body").show();
		}
		setSize();
		window.addEventListener('resize', setSize, false);
		window.addEventListener('orientantionchange', setSize, false);
		//列表顶部
		var list = "flight/getFlightList.act";
		var detail = "flight/getFlightDetail.act";
		var datePattern = "yyyy-MM-dd";
		var canClick = true;
		$(function(){
			var code = "${result.status}";
			var msg = "${result.data}";
			if(1==code){
				showMsg(msg);
			}
			
			$("#after").on("click",function(){				
				setTimeout(function(){
				 	after();
				},40) 
			});
			$("#before").on("click",function(){								
				setTimeout(function(){
				 	before();
				},40) 
			})
			setTodayContent();
			canClick = isBeforeCanClick();
			setBottomPrice();
			
			if(!msg){
				showMsg("没有查询到航班数据，请重试");
				return false;
			}
			
			$("ul[id^='list_']").on("click",function(){
			
				var mapkey = $(this).attr("mapkey");
				$("#mapKey").val(mapkey);
				$("#one").attr("action",detail);
				$(document).LoadingShow();
				setTimeout(function(){
				 	$("#one").submit();
				},200);
				//alert(mapkey);
			});
			
			$("form").on('submit',function(){
				$(document).LoadingShow();
			});
			
			$(window).unload(function(){ $(document).LoadingHide();});
			
		});
			
			
		//设置顶部最低价
		function setBottomPrice(){
			var flag = $("#flag").val();
			var cabinType = $("#cabinType").val();
			var airline = $("#airline").val();
			var date = $("#date" + flag).val();
			var depAirport = $("#depAirport" + flag).val();
			var arrAirport = $("#arrAirport" + flag).val();
			$.ajax({
				url:'flight/getBottomPrice.act',
				dataType:'json',
				type:'POST',
				async:true,
				data:{'cabinType':cabinType,'date':date,'depAirport':depAirport,'arrAirport':arrAirport,'airline':airline},
				success:function(data){
					if(data.status==0){
						if(data.data[0]){
							$("#before>:nth-child(2)").html("￥"+ data.data[0] + ".0");
						}else{
							$("#before>:nth-child(2)").html("￥0");
						}
						if(data.data[1]){
							$("#after>:nth-child(2)").html("￥"+ data.data[1] + ".0");
						}else{
							$("#after>:nth-child(2)").html("￥0");
						}
					}
				},
				error:function(){
					$("#before>:nth-child(2)").html("￥0");
					$("#after>:nth-child(2)").html("￥0");
				}
			});
		}
		
		
		//判断前一天是否可以点击 如果不能则变灰
		function isBeforeCanClick(){
			var flag = $("#flag").val();
			var base;//比较基准日期
			var curr;//当前查询的日期
			if(flag==1){//第一程
				curr = $("#date1").val();
				base = parseDate(formatDate(new Date(),datePattern),datePattern);//第一程取系统当前日期
			}else if(flag==2){//第二程
				curr = $("#date2").val();
				base = parseDate($("#date1").val(),datePattern);//第二程取第一程日期
			}
			var before = getYesterday(parseDate(curr,datePattern));//获取前一天
			if(before.getTime()<base.getTime()){
				$("#before").children().css("color","gray");
				return false;
			}else{
				return true;
			}
		}
		
		
		
		//设置顶部当前日期 星期
		function setTodayContent(){
			var flag = $("#flag").val();
			var date;
			if(1==flag){
				date = $("#date1").val();
			}else if(2==flag){
				date = $("#date2").val();
			}
			$("#today").html(formatDate(parseDate(date,datePattern).getTime(),'MM-dd w'));
		}
		
		//前一天查询
		function before(){
			var flag = $("#flag").val();
			if(canClick){
				var flag = $("#flag").val();
				var today = $("#today").attr("value");
				var date = formatDate((parseDate(today,datePattern).getTime() - 24*3600*1000),datePattern);//减一天				
				changeDate(date);
			}else{
				if(1==flag){
					showMsg("日期选择不能早于当前日期");
				}else if(2==flag){
					var tripType = $("#tripType").val();
					if(2==tripType){
						showMsg("返程日期不能早于去程日期");
					}else if(3==tripType){
						showMsg("第二程日期不能早于第一程日期");
					}
				}
			}
		}
		
		//后一天查询
		function after(){
			var flag = $("#flag").val();
			var today = $("#today").attr("value");
			var date = formatDate((parseDate(today,datePattern).getTime() + 24*3600*1000),datePattern);//加一天			
			changeDate(date);
			
		}
		
		//改变日期查询
		function changeDate(date){
			var flag = $("#flag").val();
			if(1==flag){
				$("#date1").val(date);
			}else if(2==flag){
				$("#date2").val(date);
			}
			$("#one").attr("action",list);
			
			
			setTimeout(function(){
			 	$("#one").submit();
			},100)
		}
		
	</script>
	<script type="text/javascript">
	
	//筛选
	$("#show_res ").on("click ", function() {
		$("#sel_res ").show()
		$("#res_mask ").show()
	})
	$("#del_res").on("click ", function() {
		$("#sel_res ").hide()
		$("#res_mask ").hide()

	})
	$(".grid").on("click ", function() {
		$(this).addClass("active ")
		$(this).siblings().removeClass("active ")

	})
	$(".hk .grid").on("click ", function() {
		$(this).parents(".hk ").find(".grid ").removeClass("active ")

		$(this).addClass("active ")

	})
	
	$(".res-header-main-li2-span").on("click", function(){
		$('.res-u1').children('*:nth-child(1)').click();
		$('.res-u2').children('*:nth-child(1)').click();
		$('.res-u3').children('*:nth-child(1)').click();
		timeSelected = "0";
		airline = "0";
		cabinCt = "不限";
	})

	
	
	//返回顶部
	$(window).on("scroll ", function() {
		var top = $(this).scrollTop(); // 当前窗口的滚动距离
		if(top > 500) {
			$('.scroll-up').show()
		} else {
			$('.scroll-up').hide()

		}
	})

	function scroll(scrollTo, time) {
		var scrollFrom = parseInt(document.body.scrollTop),
			i = 0,
			runEvery = 5; // run every 5ms

		scrollTo = parseInt(scrollTo);
		time /= runEvery;

		var interval = setInterval(function() {
			i++;

			document.body.scrollTop = (scrollTo - scrollFrom) / time * i + scrollFrom;

			if(i >= time) {
				clearInterval(interval);
			}
		}, runEvery);
	}
	$('.scroll-up').click(function() {
		scroll('0px', 500);
	});	
	//底部效果
	//时间排序
	$("#item_date ").on("click ", function() {
		var str1 = "时间由早到晚 "
		var str2 = "时间由晚到早 "
		var str3 = "价格 "
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
		$("#price").html(str3)
		$("#price").css("color","#ffffff")
		$(".tlist-footer-a3-d1-img").attr("src","img/bs-jiage.png")
		$(".tlist-footer-a2-d1-img").attr("src","img/time.png")
	});
	
	
	//价格排序
	$("#item_price ").on("click ", function() {
		var str1 = "价格由低到高 "
		var str2 = "价格由高到低 "
		var str3 = "时间 ";
		//var ulArr = $("ul.tic-list-ul");
		getUlArr();
		if($(this).find("#price").html() == str1) {
			sort(objArr,1,'price');
			$(this).find("#price").html(str2);
		} else {
			sort(objArr,0,'price');
			$(this).find("#price").html(str1)
					
		}
		$(this).find("#price").css("color", "#00afec");

		$("#date").html(str3)
		$("#date").css("color", "#ffffff")	
		$(".tlist-footer-a2-d1-img").attr("src","img/bs-shijian.png")
		$(".tlist-footer-a3-d1-img").attr("src","img/jiage.png")			
	});
	
     
	//时间筛选
	var time_range = function (beginTime, endTime, nowTime) {
		var result = true;
		if(beginTime && endTime && nowTime){
			var b = parseDate(beginTime,'HH:mm');
			var e = parseDate(endTime,'HH:mm');
			var n = parseDate(nowTime,'HH:mm');
			result = (n.getTime()>=b.getTime() && n.getTime()<=e.getTime());
		}
		return result;
	}

		//起飞时间
		var deparTimes = $(".con-main-li1-d1")	

			$(".con-main-li1-d1").each(function(index,data){ 


		})
		//起飞时间段
		var theString=''
		var timeQuantum=$(".res-u1 .grid span")
		timeQuantum.on('click',function(){
				
			timeSelected = $(this).attr("val");


	});
	
	$(".res-u2 li.grid").on('click',function(){
		airline = $(this).find("span").attr("value");
	});
	
	$(".res-u3 li.grid").on('click',function(){
		cabinCt = $($(this).children()[0]).attr("value");
	});
	
	$(".res-header-main-li3").on('click',function(){
		filter_param();
		$("#sel_res").hide();
		$("#res_mask").hide();
	});
	var timeSelected = "0";
	var airline = "0";
	var cabinCt = "不限";
	function filter_param(){
		filter_time();
		filter_airline();
		filter_cabin();
	}
	
	function filter_time(){
		var start = "00:00";
		var end = "24:00";
		switch (timeSelected){
			case "0":
				filter(start,end);
				break;
			case "1":
				start = "00:00";
				end = "12:00";
				filter(start,end);
				break;
			case "2":
				start = "12:00";
				end = "13:00";
				filter(start,end);
				break;
			case "3":
				start = "13:00";
				end = "18:00";
				filter(start,end);
				break;
		}
	}
	
	function filter(start,end){
		$(".tic-list-ul").each(function(obj,idx){
			var $obj = $(this);
			$obj.css("display","block");
			var deptime = $obj.find(".con-main-li1-d1").html();
			if(!time_range(start,end,deptime)){
				$obj.css("display","none");
			}else{
				$obj.css("display","block");
			}
		});
	}
	
	function filter_airline(){
		$(".tic-list-ul:visible").each(function(obj,idx){
			var $obj = $(this);
			var air = $obj.find(".icon-main-li1-span").attr("value");
			if("0" != airline){
				if(air!=airline){
					$obj.css("display","none");
				}
			}
		});
	}
	

	function filter_cabin(){
		$(".tic-list-ul:visible").each(function(obj,idx){
			var $obj = $(this);
			var cabinSelect = $obj.find(".icon-main-li3").html();
			console.log('cabinSelect=' + cabinSelect + ",cabinCt=" + cabinCt);
			if("不限" != cabinCt){
				if($.trim(cabinSelect)!=$.trim(cabinCt)){
					$obj.css("display","none");
				}
			}
		});
	}
	
	//航空公司
	$(".icon-main-li1-span").html();
	//舱位选择
	$(".icon-main-li3").html();
	
	
	function sort(arr,flag,compareArea){
		$(".tic-list").empty();
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
		$(".tic-list").append(arr);
		
		$("ul[id^='list_']").on("click",function(){
			var mapkey = $(this).attr("mapkey");
			$("#mapKey").val(mapkey);
			$("#one").attr("action",detail);
			$("#one").submit();
			//alert(mapkey);
		});
		
	}
	
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
	
	//flag=0 返回obj1<=obj2  flag=1 obj1>obj2
	function comparePrice(obj1,obj2,flag){
		var val1 = $(obj1).find(".orderid").html();
		var val2 = $(obj2).find(".orderid").html();
		if(0==flag){
			return parseFloat(val1)<=parseFloat(val2);
		}else{
			return parseFloat(val1)>parseFloat(val2);
		}
	}
	
	var objArr = [];
	
	function getUlArr(){
		objArr = new Array();
		var ulArr = $("ul.tic-list-ul");
		for(var i=0;i<ulArr.length;i++){
			objArr.push(ulArr[i]);
		}
	} 

</script>
</html>
