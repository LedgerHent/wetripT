<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>酒店查询条件</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name = "format-detection" content = "telephone=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/calendar.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/city.css" />
		<%--<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/cityselect.css" />
		--%><link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/swiper.min.css"/>
		<script type="text/javascript" src="js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/hotelHtml5/date.js"></script>
	<script type="text/javascript" src="http://m.caissa.com.cn/hotel/static/js/jquery.cookie.js?t=1508404412"></script>
	<script type="text/javascript" src="js/hotelHtml5/fixdiv.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/hotelHtml5/hotelSearch/hotelSearch.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GXkU8EHGVT4djH7BiaOiFyNi0WirfqNt"></script>
	<%--<script type="text/javascript" src="js/hotelHtml5/swiper.min.js"></script>
	--%></head>

	<body>
		<input type="hidden" id="hisPage" value="index">
		<!---------酒店入住和离店布局------->
		<div class="calendar"></div>

		<!-------出行选择的布局----->
		<div class="bounced typeAdd">
			<div class="matter" id="matter">
				<p class="way-title">出行方式</p>
				<div class="way-choose">
					<p tripType="0000700001">因公出行</p>
					<p tripType="0000700002">因私出行</p>
				</div>
			</div>
		</div>

		<!--------价格/星级的布局----->
		<div class="bounced startAdd">
			<div class="matter" id="rating">
				<div class="clickLi">
					<div style="display: flex;flex-direction: row;justify-content: space-between;">
						<p class="way-title">价格</p>
						<p class="empty">清空</p>
					</div>
					<div class="jg jg-">
						<p class="hidden" value="0" hotelPrice="00000400" price="00" hotelP="400以下">400以下</p>
						<p value="1" hotelPrice="04010700" price="00" hotelP="401-700">401-700</p>
						<p value="2" hotelPrice="07011000" price="00" hotelP="701-1000">701-1000</p>
					</div>
					<div class="jg jg-">
						<p value="3" hotelPrice="10011300" price="00" hotelP="1001-1300">1001-1300</p>
						<p hotelPrice="13011800" price="00" hotelP="1301-1800">1301-1800</p>
						<p hotelPrice="18009999" price="00" hotelP="1800以上">1800以上</p>
					</div>
				</div>
				<div class="clickLi">
					<p class="way-title">星级</p>
					<div class="jg jg-s">
						<p starLv="0,1,2" price="01" style="line-height: 1.4rem" starL="二星级以下/经济型">二星级以下/经济型</p>
						<p starLv="3" price="01" starL="三星/舒适">三星/舒适</p>
						<p starLv="4" price="01" starL="四星/高档">四星/高档</p>
						<p starLv="5,6" price="01" starL="五星/豪华">五星/豪华</p>
					</div>
				</div>
				<div class="sure">
					<p>确&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</p>
				</div>
			</div>
		</div>
		<!----------主体内容-------->
		<input type="hidden" name="tripType" id="tripType" value="" >
		<input type="hidden" id="homeAbroadFlag" name="homeAbroadFlag" value="">
		<input type="hidden" value="${roomCount}" id="roomCount" name="roomCount">
		<input type="hidden" value="${audltCount}" id="adultCount" name="adultCount">
		<input type="hidden" value="${childCount}" id="childCount" name="childCount">
		<input type="hidden" id="childAgeDl"  value="${fage}" name="childAgeDl">
		<input type="hidden" id="hotelPrice"  value="" name="hotelPrice">
		<input type="hidden" id="starLvs" name="starLvs" value="">
		<input type="hidden" id="cityId" name="cityId" value="75045">
		<input type="hidden" id="enterpriseId" name="enterpriseId" value="${user.orgid }">
		<input type="hidden" id="keyAssociateValue" name="keyAssociateValue" value=""><!-- 关键词ID -->
		<input type="hidden" id="tripUserId" name="tripUserId" value="${userId}">
		<section>
			<header id="header" class="head">
				<div>
					<img src="hotelimg/home.png" onclick="window.location.href='common/toHomePage.act';"/ class="home">
				</div>
				<p>酒店查询</p>
				<div>
					<img src="hotelimg/myicon.png" / class="myicon">
				</div>
			</header>
			<div id="roasting" style="overflow: hidden;">
				<div class="swiper-container" style="height: 100%; position: relative;">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<img src="hotelimg/bg.png" />
						</div>
						<%--<div class="swiper-slide"><img src="hotelimg/bg-1.png" /></div>
						<div class="swiper-slide"><img src="hotelimg/bg-2.png" /></div>
						<div class="swiper-slide"><img src="hotelimg/bg-3.png" /></div>
					--%></div>
					<!-- Add Pagination -->
					<div class="swiper-pagination" style="position: absolute; bottom: 5rem;"></div>
				</div>
				<ul class="tab haiwai" style="z-index: 999;">
					<li class="tab-item cityList1 cityList" value="1" id="cityg">
						<a href="javascript:;" type_code="1" homeAbroadFlag="36916">国内.港澳台酒店</a>
					</li>
					<li class="cityList2 cityList" value="2" id="countryh">
						<a href="javascript:;" type_code="2" homeAbroadFlag="-99">海外酒店</a>
					</li>
				</ul>
			</div>
			<div id="tab-content">
				<!-----国内港澳台----->
				<div>
					<div class="travel">
						<input type="text" name="" id="cause" value="" placeholder="因公出行/因私出行" readonly unselectable="on" onfocus="this.blur()"/ class="ipt">
					<%--<input type="text" name="" id="cause" value="" placeholder="请选择" readonly="readonly" / class="ipt">
					--%></div>
					<div class="bourn">
						
						<div class="city clear citySelect" style="flex: 1;">

							<div class="li_cs_le">
								<p class="li_span">目的地</p>

								<input class="li_p txtEllipsis" style="overflow: hidden; "  id="cityName" type="text" name="city" value=""  readonly unselectable="on" onfocus="this.blur()">

							</div>
							<a class="xian_right"></a>
						</div>
						<div class="localize">
							<div><img src="hotelimg/site.png" /></div>
							<p>当前位置</p>
						</div>
					</div>
					<div  class="check-date">
					<div class="check cease">
							<p>入住</p>
							<div>
								<span class="time entertime" id="checkStartDate" ></span>
								<input type="text" name="" value="" / class="c-date" id="startDate" style="display: none;">
								<span class="week">周五</span>
							</div>
						</div>
						<div class="few" id="nightC">1晚</div>
						<input type="hidden" id="nightCount" value="">
						<div class="outshop cease">
							<p>离店</p>
							<div>
								<span class="time leavetime" id="checkOutDate"></span>
								<input type="text" name=""  value="" / class="c-date" id="endDate" style="display: none;">
								<span class="week">周六</span>
							</div>
						</div>
					</div>
					<div class="travel">
						<input type="text" name=""  value="" id="count"  placeholder="房间数*1/每间成人数*2/每间儿童数*0"  readonly="readonly"/ class="ipt" >
					</div>
					<div class="travel" style="display:flex; flex-direction:row;">
						<input type="text" name="" value=""  id="level" placeholder="价格/星级"  readonly="readonly"/ class="ipt">
						<div style="margin:auto 0;" class="sp"><img src="<%=basePath%>/hotelimg/close.jpg" /></div>
					</div>
					<div class="travel" style="display:flex; flex-direction:row;">
						<input type="text" name="" id="keyWord" placeholder="关键词/商圈/地标/酒店" / class="ipt" value="">
						<div style="margin:auto 0;" class="kw"><img src="<%=basePath%>/hotelimg/close.jpg" /></div>
					</div>
					<div class="standard">
						<a href="javascript:;"><font color="red">差旅标准</font></a>
					</div>
					<div id="demand">
						<p>查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;询</p>
					</div>
				</div>
				<!-----海外----->
				<div></div>
			</div>
		</section>
		<!-- 全部订单列表和审核订单列表链接   -- 临时 -->
		<div align="center" style="padding: 10px 20px">
			<a href="<%=basePath%>orderInfo/listAllOrderInit.act">全部订单列表</a>
			<span style="padding-right: 5px;">&nbsp;&nbsp;&nbsp;</span>
			<a href="<%=basePath%>orderInfo/listApprovalPendingOrderInit.act">待审核订单列表</a>
			<div style="padding-bottom: 10px;"></div>
		</div>
		<!-- 全部订单列表和审核订单列表链接   -- 临时 -->
		<form action="hotel/room.act" id="sform" method="post">
			<input id="room" name="room" value="" type="hidden">
			<input id="audlt" name="audlt" value="" type="hidden">
			<input id="child" name="child" value="" type="hidden">
			<input id="cage" name="cage" value="" type="hidden">
		</form>
		<%--<form action="hotel/key.act" id="keyform" method="post"></form>--%>
		<form action="hotelSearch/keyWordSearchInit.act" id="keyform" method="post">
			<input id="hisp" type="hidden" value="">
		</form>
		<form action="hotel/travel.act" id="tform" method="post"></form>
		
			
		<div class="city_section displayNONE" style="background:#F8F8F8; z-index: 9999; position: absolute; left: 0;top: 0;width: 100%;height: 100%;">
			<header>
				<div class="ban_top displayNONE">
					<div class="ban_cen clear">
						<img src="hotelimg/header.png"/ class="you"><%--
						 <div class="gengduo index_section">全球酒店预订</div>
						--%><div class="qbgj displayNONE city_section">
							<span class="countryList countryList2 cityList qbgj_ri " type_code="2">海外</span>
							<span class="countryList countryList1 cityList qbgj_le le_bg" type_code="1">国内</span>
						</div>
					</div>
				</div>
			</header>

			<!--搜索输入框-->
			<div class="sousuo_sr" style="position: relative;">
				<div class="sousuo_bg" style="display:flex;flex-direction:row;">
					<div class="" style="margin:auto 1rem;"><img src="<%=basePath%>/hotelimg/search.png" style="width:1.6rem;height:1.6rem;"></div>
					<input id="sousuo_inp" class="ssousuo_inp" placeholder="搜索城市/区域/位置"  style="height:100%;">
				</div>
				<ul style="width:100%;position: absolute;z-index:99990;background:#FFFFFF;left:0;top:3.5rem; display:none;" class="searchu">
				</ul>			
			</div>
			
			<!--全部国家-->
			<div class="hiddens">
			<div class="quan_bu">
				<h3 class="quan_bu_h3 clear">
				<span class="h3_sp_le">热门城市</span>
			</h3>
				<div class="two">
					<ul class="clear cityList1 " id="homecity">
					</ul>
					<ul class="clear cityList2 displayNONE" id="abcity">
					</ul>

				</div>
			</div>
			<div class="sear_ch" style="line-height: 1.5rem; text-indent: 1rem;">按字母查找</div>
			<div class="one" >
				   <div class="letterCity letterCity1 cityList1" id="gn">
				   <div class="overflow">
					<div class="zi_mu">
						<span class="letterV">A</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fena0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
				  </div>
				  <div class="overflow">
					<div class="zi_mu">
						<span class="letterV">B</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenb0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">C</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenc0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">D</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fend0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">E</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fene0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">F</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenf0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">G</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feng0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">H</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenh0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">I</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feni0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">J</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenj0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">K</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenk0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">L</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenl0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">M</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenm0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">N</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenn0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">O</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feno0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">P</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenp0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">Q</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenq0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">R</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenr0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">S</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fens0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">T</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fent0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">U</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenu0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">V</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenv0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">W</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenw0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">X</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenx0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">Y</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feny0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">Z</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenz0" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
				</div>
				<div class="letterCity letterCity2 cityList2 displayNONE" id="gw">
					<div class="overflow">
						<div class="zi_mu">
							<span class="letterV">A</span>
							<span class="xiala img_down"></span>
						</div>
						<div class="zi_mu_fen">
							<span class="zi_mu_fena1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
						</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">B</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenb1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">C</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenc1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">D</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fend1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">E</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fene1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">F</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenf1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">G</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feng1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">H</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenh1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">I</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feni1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">J</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenj1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">K</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenk1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">L</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenl1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">M</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenm1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">N</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenn1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">O</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenno" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">P</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenp1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">Q</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenq1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">R</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenr1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">S</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fens1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">T</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fent1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">U</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenu1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">V</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenv1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">W</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenw1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">X</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenx1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">Y</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_feny1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
					<div class="overflow">
					<div class="zi_mu">
						<span class="letterV">Z</span>
						<span class="xiala img_down"></span>
					</div>
					<div class="zi_mu_fen">
						<span class="zi_mu_fenz1" style="padding: 0px;border-bottom: 0px;border-top: 1px solid #d1d1d1;"></span>
					</div>
					</div>
				</div>

			</div>
		</div>
		</div>
		<form action="hotel/allcity.act" id="allform" method="post">
			<input type="hidden" id="citytype" >
		</form>
		
	</body>

	
	<script type="text/javascript">
	//初始图标点亮：价格|星级
	function showLightPriceOrStar(value,pClassName,divClassName){
		var arr=value.split(',');
		$("."+divClassName).each(function(){
			for(var i=0;i<arr.length;i++){
				if($(this).attr(pClassName)==arr[i]){
					$(this).addClass('showLight');
				}
			}
		});
	}
	$(function(){
		getCookieToCommonPage("index");
		var homeAbroadFlag=$('#homeAbroadFlag').val();
		if(homeAbroadFlag=='-99'){
			$('#cityg').removeClass('tab-item');
			$('#countryh').addClass('tab-item');
			//$('#cityName').val('马尔代夫');
			//$('#cityId').val(56024);
		}
		var cityname=$('#cityName').val();
		var cityid=$('#cityId').val();
		var ci=$.cookie("cityName");
		if(ci!=undefined&&ci!=''){
			$("#cityId").val($.cookie("cityId"));
			$("#cityName").val($.cookie("cityName"));
		}else if(ci==undefined&&(cityname!=undefined&& cityname!='' && cityname!='null')){
			$("#cityId").val(cityid);
			$("#cityName").val(cityname);
		}else{
			$("#cityId").val(75021);
			$("#cityName").val("北京市");		
		}
		
		var star=$('#starLvs').val();
		var price=$('#hotelPrice').val();
		var prices;
		if(star!=''||price!=''){
		if(price=='00000400'||price=='400以下'){
			prices='400以下';
			console.log($(this))
		}
		if(price=='04010700'||price=='401-700'){
			prices='401-700';
		}
		if(price=='07011000'||price=='701-1000'){
			prices='701-1000';
		}
		if(price=='10011300'||price=='1001-1300'){
			prices='1001-1300';
		}
		if(price=='13011800'||price=='1301-1800'){
			prices='1301-1800';
		}
		if(price=='18009999'||price=='1800以上'){
			prices='1800以上';
		}
		showLightPriceOrStar(prices,"hotelP","jg- p");
		var ss='';var arr;var st='';var stars='';
		if(star.indexOf("/")!=-1 ){
			arr=star.split(',');
		}else{
			arr=star.split('|');
		}
		for(var i=0; i<arr.length; i++){
			ss=arr[i];
			if(ss=='0,1,2'||ss=='二星级以下/经济型'){
				st='二星级以下/经济型';
				stars=stars+st+",";
			}else if(ss=='3'||ss=='三星/舒适'){
				st='三星/舒适';
				stars=stars+st+",";
			}else if(ss=='4'||ss=='四星/高档'){
				st='四星/高档';
				stars=stars+st+",";
			}else if(ss=='5,6'||ss=='五星/豪华'){
				st='五星/豪华';
				stars=stars+st;
			}
		}
		showLightPriceOrStar(stars,"starL","jg-s p");
		var total='';
		if(prices==""||prices==undefined){
			total=stars;
		}else if(stars==""||stars==undefined){
			total=prices;
		}else{
			total=prices+","+stars;
		}
		$('#level').val(total);
		}
		var night=$('#nightCount').val();
		if(night.indexOf('undefined')<0){
			night=night.substring(0,night.indexOf('晚')+1);
			$('#nightC').html(night);
		}
		
	});
	$(function(){
		var child; var room; var audlt;
		var roomCount=$('#roomCount').val();
		var audltCount=$('#adultCount').val();
		var childCount=$('#childCount').val();
		var childAgeDl=$('#childAgeDl').val();
		var childr;
		var child; var room; var audlt;
		if('${childCount}'==''&&'${roomCount}'==''&&'${audltCount}'==''){
			child=0;room=1;audlt=2;
			$("#count").val("房间数*"+room+"，每间成人数*"+audlt+"，每间儿童数*"+child+"").css('color', '#333333');
		}else{
			$("#count").val("房间数*${roomCount}，每间成人数*${audltCount}，每间儿童数*${childCount}").css('color', '#333333');
		}
	});
	$("form").on('submit',function(event){
		setCookie();
	});
	
	/* 缓存 start */
	var homeabroadflag=$(".tab-item a").html();//海内外
	var cause=$("#cause").val();//出行方式
	$("#cause").val($.cookie("cause")).css('color', '#333333');
	var cityname=$("#cityName").val();//城市名称
	$("#cityName").val($.cookie("cityName")?$.cookie("cityName"):cityname).css('color', '#333333');
	var cityId=$("#cityId").val();//城市id
	$("#cityId").val($.cookie("cityId")?$.cookie("cityId"):cityId);
	var checkStartDate=$("#checkStartDate").html();//入住时间
	$("#checkStartDate").html($.cookie("checkStartDate")?$.cookie("checkStartDate"):checkStartDate).css('color', '#333333');
	var checkOutDate=$("#checkOutDate").html();//离店时间 
	$("#checkOutDate").html($.cookie("checkOutDate")?$.cookie("checkOutDate"):checkOutDate).css('color', '#333333');
	var nightC=$("#nightC").html();//间夜数 
	$("#nightC").html($.cookie("nightC")?$.cookie("nightC"):nightC).css('color', '#333333');
	var count=$("#count").val();//房间数 
	$("#count").val($.cookie("count")).css('color', '#333333');
	var level=$("#level").val();//价格星级 
	$("#level").val($.cookie("level")?$.cookie("level"):level).css('color', '#333333');
	var keyWord=$("#keyWord").val();//关键词 
	$("#keyWord").val($.cookie("keyWord")?$.cookie("keyWord"):keyWord).css('color', '#333333');
	var keyAssociateValue=$("#keyAssociateValue").val();//关键词 
	$("#keyAssociateValue").val($.cookie("keyAssociateValue")?$.cookie("keyAssociateValue"):keyAssociateValue).css('color', '#333333');
	$("#adultCount").val($.cookie("adultCount")==undefined?'2':$.cookie("adultCount"));
	$("#childCount").val($.cookie("childCount")==''?'0':$.cookie("childCount"));
	$("#childAgeDI").val($.cookie("childAgeDI"));
	$("#hotelPrice").val($.cookie("hotelPrice"));
	//设置cookie
	function setCookie(){
		$.cookie('homeabroadflag',$(".tab-item a").html());
		$.cookie('cause',$("#cause").val());
		$.cookie('cityName',$("#cityName").val());
		$.cookie('checkStartDate',$("#checkStartDate").html());
		$.cookie('nightC',$("#nightC").html());
		$.cookie('checkOutDate',$("#checkOutDate").html());
		$.cookie('count',$("#count").val());
		$.cookie('level',$("#level").val());
		$.cookie('keyWord',$("#keyWord").val());
		$.cookie('keyAssociateValue',$("#keyAssociateValue").val());
		$.cookie('cityId',$("#cityId").val());
		$.cookie('adultCount',$("#adultCount").val());
		$.cookie('childCount',$("#childCount").val());
		$.cookie('childAgeDI',$("#childAgeDI").val());
		$.cookie('hotelPrice',$("#hotelPrice").val());
	}
	/* end */
		//关键词
		if("${keyword}"!=""){
			$("#keyWord").val("${keyword}").css('color', '#333333');
		}
		
		function touch(){
			document.querySelector('body').addEventListener('touchstart', function (ev) {
			    event.preventDefault();
			});
		}
		
		/*-------因公因私出行-------*/
		$('#cause').click(function() {
			var walk=$(this).val();
			if(walk=='因公出行'){
				$('.way-choose p').eq(0).css('color', '#00AFEC');
			}else if(walk=='因私出行'){
				$('.way-choose p').eq(1).css('color', '#00AFEC');
			}
			$('.typeAdd').fadeIn();
			$('.typeAdd .matter').animate({
				height: '12rem'
			}, 1000);
			stop();
		});

		$('.way-choose p').click(function(e) {			
			e.stopPropagation();
			$(this).css('color', '#00AFEC').siblings().css('color', '');
			$('.typeAdd').fadeOut();
			$('#cause').val($(this).html()).css({ 'color': '#333333' });
			$('#cause').css('border', '');
			$('.travel').eq(0).css('border', '');
			remove();
		});

		/*-------地点插件-------*/
		$('.tab li').click(function() {
			$(this).addClass('tab-item').siblings().removeClass('tab-item');
			var value = $(this).attr('value');
			if(value == 1){
				$('.countryList').eq(1).addClass('le_bg').siblings().removeClass('le_bg');
				$('.two .clear').eq(1).addClass('displayNONE').siblings().removeClass('displayNONE');
				$('.one .letterCity').eq(1).addClass('displayNONE').siblings().removeClass('displayNONE');
				$('#cityName').val('北京市');
				$('#cityId').val(75021);
				$('#homeAbroadFlag').val(36916);
			}else{
				$('.countryList').eq(0).addClass('le_bg').siblings().removeClass('le_bg');
				$('.two .clear').eq(0).addClass('displayNONE').siblings().removeClass('displayNONE');
				$('.one .letterCity').eq(0).addClass('displayNONE').siblings().removeClass('displayNONE');
				$('#cityName').val('马尔代夫');
				$('#cityId').val(56024);
				$('#homeAbroadFlag').val(-99);
			}
			saveCommonInfoToCookie('index');
		});
		$('.countryList').click(function() {
			var index = $(this).index();
			if(index=='0'){
				$('#cityName').val('马尔代夫');
				$('#cityId').val(56024);
			}else if(index=='1'){
				$('#cityName').val('北京市');
				$('#cityId').val(75021);
			}
			$(this).addClass('le_bg').siblings().removeClass('le_bg');
			$('.two .clear').eq(index).addClass('displayNONE').siblings().removeClass('displayNONE');
			$('.one .letterCity').eq(index).addClass('displayNONE').siblings().removeClass('displayNONE');
			if($(this).attr('type_code') == 2){
				$('.tab li').eq(1).addClass('tab-item').siblings().removeClass('tab-item');
			}else{
				$('.tab li').eq(0).addClass('tab-item').siblings().removeClass('tab-item');
			}
		});
		$('.city_section .you').click(function(){
			$(this).parents('.city_section').addClass('displayNONE');
		});
		
		$('.citySelect').click(function(){
			var value = $('.tab-item').attr('value');
			if(value == 1){
				$('.countryList').eq(1).addClass('le_bg').siblings().removeClass('le_bg');
				$('.two .clear').eq(1).addClass('displayNONE').siblings().removeClass('displayNONE');
				$('.one .letterCity').eq(1).addClass('displayNONE').siblings().removeClass('displayNONE');
			}else{
				$('.countryList').eq(0).addClass('le_bg').siblings().removeClass('le_bg');
				$('.two .clear').eq(0).addClass('displayNONE').siblings().removeClass('displayNONE');
				$('.one .letterCity').eq(0).addClass('displayNONE').siblings().removeClass('displayNONE');
			}
			$('.city_section ').removeClass('displayNONE');
			$('.ban_top').removeClass('displayNONE');
		});
		
		$('.quan_bu_sp').on('click',function(){
			$('#cityName').val($(this).html());
			$('.city_section ').addClass('displayNONE');
		});
		
		$('body').on('click','.zi_mu_fen .cy',function(){//全部城市
			$('#cityName').val($(this).html());
			$('#cityId').val($(this).attr('cityId'));
			$('.city_section ').addClass('displayNONE');
		});
		$('body').on('click','.li_first .quan_bu_sp',function(){//热门城市
			$('#cityName').val($(this).html());
			$('#cityId').val($(this).attr('cityId'));
			$('.city_section ').addClass('displayNONE');
		});
		
		/*------定位当前位置------*/
		$('.localize').click(function() {
			//通过调用新浪IP地址库接口查询用户当前所在国家、省份、城市、运营商信息
			/*$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function() {
				$("#cityName").val(remote_ip_info.city);				
				if ($("#cityName").val()=="北京" || $("#cityName").val()=="北京") {
					$('#cityId').val("75021");
				}				
			});*/
			
			var geolocation = new BMap.Geolocation();    
			var gc = new BMap.Geocoder();     
			geolocation.getCurrentPosition( function(r) {   //定位结果对象会传递给r变量  
		        if(this.getStatus() == BMAP_STATUS_SUCCESS){  //通过Geolocation类的getStatus()可以判断是否成功定位。  
		            var pt = r.point;    
		            gc.getLocation(pt, function(rs){    
		                var addComp = rs.addressComponents;    
		                var cityName = addComp.city;
		            	$("#cityName").val(cityName).css('background','none');
			        	$.ajax({
			        		async: false,
			 				type:"POST",
			 				data:{
			 					"content":cityName,
			 					"poiType":"1"
			 				},
			 				url:"${pageContext.request.contextPath}/hotelSearch/keyWordSearchList.act",
			 				success:function(data){
			 					$('#cityId').val(data.busiData[0].value);
			 				},
			 				error: function (xhr, status, error) {
			 					
			 			    }
			 			});
		           });  
		       }else{  
		            //关于状态码    
		            //BMAP_STATUS_SUCCESS   检索成功。对应数值“0”。    
		            //BMAP_STATUS_CITY_LIST 城市列表。对应数值“1”。    
		            //BMAP_STATUS_UNKNOWN_LOCATION  位置结果未知。对应数值“2”。    
		            //BMAP_STATUS_UNKNOWN_ROUTE 导航结果未知。对应数值“3”。    
		            //BMAP_STATUS_INVALID_KEY   非法密钥。对应数值“4”。    
		            //BMAP_STATUS_INVALID_REQUEST   非法请求。对应数值“5”。    
		            //BMAP_STATUS_PERMISSION_DENIED 没有权限。对应数值“6”。(自 1.1 新增)    
		            //BMAP_STATUS_SERVICE_UNAVAILABLE   服务不可用。对应数值“7”。(自 1.1 新增)    
		            //BMAP_STATUS_TIMEOUT   超时。对应数值“8”。(自 1.1 新增)    
		            switch(this.getStatus()){  
		                case 2:  
		                    alert( '位置结果未知 获取位置失败.' );  
		                break;  
		                case 3:  
		                    alert( '导航结果未知 获取位置失败..' );  
		                break;  
		                case 4:  
		                    alert( '非法密钥 获取位置失败.' );  
		                break;  
		                case 5:  
		                    alert( '对不起,非法请求位置  获取位置失败.' );  
		                break;  
		                case 6:  
		                    alert( '对不起,当前 没有权限 获取位置失败.' );  
		                break;  
		                case 7:  
		                    alert( '对不起,服务不可用 获取位置失败.' );  
		                break;  
		                case 8:  
		                    alert( '对不起,请求超时 获取位置失败.' );  
		                break;  
		            }  
		      }          
		    },  
	    	{enableHighAccuracy: true}  
			) 
			/* var map = new BMap.Map("cityName");
			function myFun(result){
				var cityName = result.name;
				map.setCenter(cityName);
				$("#cityName").val(cityName).css('background','none');
				
	        	 $.ajax({
	        		async:false,
	 				type:"POST",
	 				data:{
	 					"content":cityName,
	 					"poiType":"1"
	 				},
	 				url:"${pageContext.request.contextPath}/hotelSearch/keyWordSearchList.act",
	 				success:function(data){
	 					$('#cityId').val(data.busiData[0].value);
	 				},
	 				error: function (xhr, status, error) {
	 					
	 			    }
	 			});
			}
			var myCity = new BMap.LocalCity();
			myCity.get(myFun); */
		});
		/*删除input框数据效果*/
		$('.travel .sp img').click(function() {
			$("#level").val('');
			$('.jg p').removeClass('showLight');
			$('#starLvs').val('');
			$('#hotelPrice').val('');
			saveCommonInfoToCookie("index");
		});
		$('.travel .kw img').click(function() {
			$("#keyWord").val('');
			$("#keyAssociateValue").val('');
		});
		/*----------价格/星级--------*/
		$('#level').click(function() {
			$('.startAdd').fadeIn();
			stop();
		});
		$('.jg- p').click(function(e) {
			e.stopPropagation();
			if($(this).hasClass('showLight')){
				$(this).removeClass('showLight');
				return false
			}
			$(this).addClass('showLight').parents('.jg').siblings().find('p').removeClass('showLight');
			$(this).addClass('showLight').siblings().removeClass('showLight');			
			
		});
		
		$('.jg-s p').click(function(e) {
			e.stopPropagation();
			$(this).toggleClass('showLight');
		});
		
		$('.sure').click(function(e) {
			e.stopPropagation();
			$('.startAdd').fadeOut();
			remove();
			var html;
			var arrH = [];
			var arr=[];
			var idIndex = [];
			$('.showLight').each(function(i) {
				html = $(this).html();
				idIndex[html] = i;
				arrH.push(html);
				var prr=$(this).attr('price');
				arr.push(prr);
			});
			var starl=[];
			if(arrH.length <= 1) {
				$('#level').val(arrH[0]).css('color', '#333333');
				if(arr[0]=='00'){
					$('#hotelPrice').val(arrH[0]);
				}else{
					$('#starLvs').val(arrH[0]);	
				}
			} else  if(arrH.length <= 2){
				$('#level').val(arrH[0] + '，' + arrH[1]).css('color', '#333333');
				if(arr[0]=='00'){
					$('#hotelPrice').val(arrH[0]);
					$('#starLvs').val(arrH[1]);
				}else{
					starl.push(arrH[0]);
					starl.push(arrH[1]);
					$('#starLvs').val(starl);	
				}
			}else if(arrH.length <= 3){
				$('#level').val(arrH[0] + '，' + arrH[1] + '，'+ arrH[2]).css('color', '#333333');
				if(arr[0]=='00'){
					$('#hotelPrice').val(arrH[0]);
					starl.push(arrH[1]);
					starl.push(arrH[2]);
					$('#starLvs').val(starl);
				}else{
					starl.push(arrH[0]);
					starl.push(arrH[1]);
					starl.push(arrH[2]);
					$('#starLvs').val(starl);	
				}
			}else if(arrH.length <= 4){
				$('#level').val(arrH[0] + '，' + arrH[1] + '，'+ arrH[2]+ '，'+ arrH[3]).css('color', '#333333');
				if(arr[0]=='00'){
					$('#hotelPrice').val(arrH[0]);
					starl.push(arrH[1]);
					starl.push(arrH[2]);
					starl.push(arrH[3]);
					$('#starLvs').val(starl);
				}else{
					starl.push(arrH[0]);
					starl.push(arrH[1]);
					starl.push(arrH[2]);
					starl.push(arrH[3]);
					$('#starLvs').val(starl);	
				}
			}else{
				$('#level').val(arrH[0] + '，' + arrH[1] + '，'+ arrH[2]+ '，'+ arrH[3]+ '，'+ arrH[4]).css('color', '#333333');
				$('#hotelPrice').val(arrH[0]);
				starl.push(arrH[1]);
				starl.push(arrH[2]);
				starl.push(arrH[3]);
				starl.push(arrH[4]);
				$('#starLvs').val(starl);
			}

		});

		$('.empty').click(function(e) {
			e.stopPropagation();
			$('.jg p').removeClass('showLight');
			$('#starLvs').val('');
			$('#hotelPrice').val('');
			saveCommonInfoToCookie("index");
		});
		/*-----酒店入住与酒店日期的插件---------*/
		var today = "周" + "日一二三四五六".charAt(new Date().getDay()+1);
		$('.week').eq(0).html(today);
		var tomorrow = "周" + "日一二三四五六".charAt((new Date().getDay()+2)%7);
		$('.week').eq(1).html(tomorrow);
		$('.check-date').hotelDate();
		//获得某月的最后一天  
        function getLastDay(year,month) {         
             var new_year = year;    //取当前的年份          
             var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）          
             if(month>12) {         
              new_month -=12;        //月份减          
              new_year++;            //年份增          
             }         
             var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天          
             return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期          
        	
        }
		 var dated = new Date();
		 var year = dated.getFullYear();
		 var month = dated.getMonth()+ 1;
		 var months = dated.getMonth()+ 2;
		 var days = dated.getDate();
		 var firstDay = months + "-" + "01";//下个月的第一天
		 if(getLastDay(year,month) == days){
		 	$('.leavetime').html(firstDay);
		 }

		/*-----删除除了div之外任何区域，遮罩层隐藏----------*/
		function hideLayer(e, Obj, idAttr) {
			var e = e || window.event; //浏览器兼容性   
			var elem = e.target || e.srcElement;
			while(elem) { //循环判断至跟节点，防止点击的是div子元素   
				if(elem.id && elem.id == Obj) {
					return;
				}
				elem = elem.parentNode;
			}
			$(idAttr).parents('.bounced').css('display', 'none'); //点击的不是div或其子元素 
			remove();
		}
		$('.typeAdd').bind('click', function(e) {
			hideLayer(e, 'matter', '#matter');
		});

		$('.startAdd').bind('click', function(e) {
			hideLayer(e, 'rating', '#rating');
			$('.jg p').removeClass('showLight');
		});

		
		/*---------关键词/商圈/地标/酒店效果插件-----------*/
		$('#keyWord').click(function() {
			//跳转关键字搜索页
			saveCommonInfoToCookie("index");
			var hisPage = $("#hisPage").val();
			$('#hisp').val(hisPage);
			$('#keyform').submit();
		});
		/*------差旅的效果-------*/
		$('.standard a').click(function() {
			$("#tform").submit();
			/*if($('#cause').val() == "" || $('#cause').val() == "因私出行") {
				//alert('不校验差旅标准');
				showMessage("不校验差旅标准");
			} else {
				$("#tform").submit();
			}*/
		});
		/*----------轮播图插件的效果----------*/
		 /*var swiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        paginationClickable: true
	    });*/
	    
		
	</script>
	<script type="text/javascript">
	var base = "${applicationScope.ctx}";
	
	//显示所有城市
	$(function(){
		$(this).LoadingShow();
		var citytype='';
		var jj="";
		for(var i=0;i<2;i++){
			if(i==0){
				citytype='国内';
				jj="0";
			}else if(i==1){
				citytype='';
				jj="1";
			}
		$.ajax({
		    type: 'POST',
		    url:base+"/hotel/allcity.act",
		    data:{"citytype":citytype},
		    async:false,
		    success: function(result) {
		    	var json = eval("("+result+")"); 
		    if(json!=null){
		       for(var i=0; i<json.length; i++){
		    		var cityname=json[i].cnName;
		    		var cityid=json[i].areaId;
		    		var cityindex=json[i].abName;
		    		var rega=/^a/;var regb=/^b/;var regc=/^c/;var regd=/^d/;var rege=/^e/;var regf=/^f/;var regg=/^g/;
		    		var regh=/^h/;var regi=/^i/;var regj=/^j/;var regk=/^k/;var regl=/^l/;var regm=/^m/;var regn=/^n/;
		    		var rego=/^o/;var regp=/^p/;var regq=/^q/;var regr=/^r/;var regs=/^s/;var regt=/^t/;var regu=/^u/;
		    		var regv=/^v/;var regw=/^w/;var regx=/^x/;var regy=/^y/;var regz=/^z/;
		    		var aa="<span flag=\"flag\" style=\"display: block;\" class=\"cy\" cityId="+cityid+">"+cityname+"</span>";
		    		if(rega.test(cityindex)){
		    			$('.zi_mu_fena'+jj).append(aa);
		    		}else if(regb.test(cityindex)){
		    			$('.zi_mu_fenb'+jj).append(aa);
		    		}else if(regc.test(cityindex)){
		    			$('.zi_mu_fenc'+jj).append(aa);
		    		}else if(regd.test(cityindex)){
		    			$('.zi_mu_fend'+jj).append(aa);
		    		}else if(rege.test(cityindex)){
		    			$('.zi_mu_fene'+jj).append(aa);
		    		}else if(regf.test(cityindex)){
		    			$('.zi_mu_fenf'+jj).append(aa);
		    		}else if(regg.test(cityindex)){
		    			$('.zi_mu_feng'+jj).append(aa);
		    		}else if(regh.test(cityindex)){
		    			$('.zi_mu_fenh'+jj).append(aa);
		    		}else if(regi.test(cityindex)){
		    			$('.zi_mu_feni'+jj).append(aa);
		    		}else if(regj.test(cityindex)){
		    			$('.zi_mu_fenj'+jj).append(aa);
		    		}else if(regk.test(cityindex)){
		    			$('.zi_mu_fenk'+jj).append(aa);
		    		}else if(regl.test(cityindex)){
		    			$('.zi_mu_fenl'+jj).append(aa);
		    		}else if(regm.test(cityindex)){
		    			$('.zi_mu_fenm'+jj).append(aa);
		    		}else if(regn.test(cityindex)){
		    			$('.zi_mu_fenn'+jj).append(aa);
		    		}else if(rego.test(cityindex)){
		    			$('.zi_mu_feno'+jj).append(aa);
		    		}else if(regp.test(cityindex)){
		    			$('.zi_mu_fenp'+jj).append(aa);
		    		}else if(regq.test(cityindex)){
		    			$('.zi_mu_fenq'+jj).append(aa);
		    		}else if(regr.test(cityindex)){
		    			$('.zi_mu_fenr'+jj).append(aa);
		    		}else if(regs.test(cityindex)){
		    			$('.zi_mu_fens'+jj).append(aa);
		    		}else if(regt.test(cityindex)){
		    			$('.zi_mu_fent'+jj).append(aa);
		    		}else if(regu.test(cityindex)){
		    			$('.zi_mu_fenu'+jj).append(aa);
		    		}else if(regv.test(cityindex)){
		    			$('.zi_mu_fenv'+jj).append(aa);
		    		}else if(regw.test(cityindex)){
		    			$('.zi_mu_fenw'+jj).append(aa);
		    		}else if(regx.test(cityindex)){
		    			$('.zi_mu_fenx'+jj).append(aa);
		    		}else if(regy.test(cityindex)){
		    			$('.zi_mu_feny'+jj).append(aa);
		    		}else{
		    			$('.zi_mu_fenz'+jj).append(aa);
		    		}
		        }
		    	}
   			 }
				});
		}
		
		//热门城市
		$.ajax({
		    type: 'POST',
		    url:base+"/hotel/hotcity.act",
		    async:false,
		    success: function(result) {
		    var json=JSON.parse(result);
		    var ss=json.homeHotSize;
		    var as=json.abroadHotSize;
		    if(ss=='18'){
		    	for(var i=0; i<18; i++){
		    		var cityh=json.homeCityArray[i];
		    		var cityn=cityh.substring(0,cityh.indexOf("|"));
		    		var aa=cityh.split('|');
		    		var cityid;
		    		 $.each(aa,function(n,value){
		    	            if(n==3){
		    	            cityid=value;
		    	            }
		    	        });
		    		var homestr="<li class=\"li_first\"><span class=\"quan_bu_sp\" cityId="+cityid+">"+cityn+"</span></li>";
		    		$("#homecity").append(homestr);
		    	}
		    }
		    if(as=='27'){
		    	for(var i=0; i<18; i++){
		    		var city=json.abroadCityArray[i];
		    		var cityna=city.substring(0,city.indexOf("|"));
		    		var aa=city.split('|');
		    		var cityidc;
		    		 $.each(aa,function(n,value){
		    	            if(n==1){
		    	            cityidc=value;
		    	            }
		    	        });
		    		var homestr="<li class=\"li_first\"><span class=\"quan_bu_sp\" cityId="+cityidc+">"+cityna+"</span></li>";
		    		$("#abcity").append(homestr);
		    	}
		    }
		 $(this).LoadingHide();
   			 }
				});
		
	})
	$('#count').click(function() {
		var room='${roomCount}';
		var audlt='${audltCount}';
		var child='${childCount}';
		var age='${fage}';
		$("#room").val(room);
		$("#audlt").val(audlt);
		$("#child").val(child);
		$("#cage").val(age);
		$("#sform").submit();
	})
	
	/*提示信息*/
	function showMessage(meg){
		$(this).MyConfirm({
			content:meg
		});
	}
	/*查询*/
	$('#demand').click(function(){
		var homeAbroadFlag=$('.tab-item a').attr('homeAbroadFlag');
		$("#homeAbroadFlag").val(homeAbroadFlag);
		var tripType=$("#cause").val();//出行方式
		if(tripType==''){
			showMessage("请选择因公还是因私出行！");
			$('#cause').css('border','0.1rem solid red');
			return false;
		}
		if(tripType=='因公出行'){
			$("#tripType").val("0000700001");
		}else if(tripType=='因私出行'){
			$("#tripType").val("0000700002");
		}
		var cityname=$("#cityName").val();//城市名称
		if(cityname==''){
			showMessage("目的地不能为空！");
			return false;
		}
		var cityid=$("#cityId").val();
		var tripUserId=$("#tripUserId").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		var enterpriseId=$("#enterpriseId").val();
		var checkStartDate=$("#checkStartDate").html();//入住时间
		if(checkStartDate==''){
			showMessage("入住时间不能为空！");
			return false;
		}
		var checkOutDate=$("#checkOutDate").html();//离店日期
		if(checkOutDate==''){
			showMessage("离店时间不能为空！");
			return false;
		}
		var nightt=$("#nightC").html();
		var few;
		few=nightt.substring(0,nightt.indexOf('晚'));
		if(few.indexOf('共')!=-1){
		few=few.substring(nightt.indexOf('共')+1,nightt.length);
		}else{
		few=few.substring(0,nightt.length);	
		}
		$("#nightCount").val(few);
		var roomCount=$('#roomCount').val();
		var adultCount=$('#adultCount').val();
		var childCount=$('#childCount').val();
		$("#roomCount").val(roomCount==''?'1':roomCount);//房间数
		$("#adultCount").val(adultCount);//成人数
		$("#childCount").val(childCount);//儿童数
		var sage;
		/*if('${fage}'.indexOf("以下")!=-1){
			sage='${fage}'.replace(/\以下/g,"");//儿童年龄 
		}*/
		if('${fage}'.indexOf("&lt;")!=-1){
			sage='${fage}'.replace(/\&lt;2岁/g,"1");//儿童年龄 
			//sage=1;
		}
		if('${fage}'.indexOf("岁")!=-1){
			sage='${fage}'.replace(/\岁/g,"");//儿童年龄 
		}
		if('${fage}'.indexOf("&lt;")!=-1&&'${fage}'.indexOf("岁")!=-1){
			sage='${fage}'.replace(/\&lt;2岁/g,"1");
			sage=sage.replace(/\岁/g,"");//儿童年龄 
		}
		$("#childAgeDl").val(sage);
		var price=[];
		$('.jg-').find('.showLight').each(function(){//价格
			price.push($(this).attr('hotelPrice'));
		})
		$("#hotelPrice").val(price);
		var sstar="";
		$('.jg-s').find('.showLight').each(function(){//星级
			var star = $(this).attr('starLv')+"|";
			sstar=sstar+star;
		})
		$("#starLvs").val(sstar);//酒店星级
		saveCommonInfoToCookie('index');
		window.location.href = base + "/hotelSearch/init.act";
		
	});
	$('.overflow .zi_mu').click(function(){
		$(this).siblings().slideToggle();
		console.log($(this).find('.xiala').hasClass('img_down'))
		if($(this).find('.xiala').hasClass('img_down')){

			$(this).find('.xiala').removeClass('img_down').addClass('img_top');
			return false;
		}
		if($(this).find('.xiala').hasClass('img_top')){
			$(this).find('.xiala').removeClass('img_top').addClass('img_down');
			return false;
		}
		
		
	})
	
	$('#sousuo_inp').click(function(){
		//var country=$(".tab-item a").html();//国内外标识
		var country=$(".tab-item a").attr('type_code');
		window.location.href = "hotel/citySP.act?country="+country;
	});
	$(function(){
		if('${pagee}'=='cityp'){
			$('#cityName').val('${cityname}');
			$('#cityId').val('${cityid}');
		}
	});
	 $("body").children().click(function () {
	
	      //这里不要写任何代码
	
	}); 
	//显示城市模糊搜索 
	/*
	sousuo_inp.oninput = function() {
			var ss = sousuo_inp.value;
			$(".searchu").children("li").remove();
			var country=$(".tab-item a").html();//国内外标识
			var flag="city";
			$.ajax({
			    type: 'POST',
			    url:base+"/hotel/search.act",
			    data:{"content":ss,"homeabroadflag":country,"flag":flag},
			    async:false,
			    success: function(result) {
			    	var json = eval(result); 
			     if(json!=null){
					for(var i=0; i<json.length; i++){
						var city=json[i].label;
						var cityid=json[i].value;
			   			var str="<li style=\"height:2rem;line-height:2rem;border-bottom:0.01rem solid #cccccc;text-indent:1rem;\" class=\"country\" cityId="+cityid+">"+city+"</li>";
						$(".searchu").append(str);
						$(".searchu").show();
						$(".hiddens").hide();
					}
					
			   		}
	   			 }
					});
		}*/
	
	function stop(){
		$('body,html').css({ 'overflow': "hidden" });
		document.ontouchmove=function(){return false;}
	}
	function remove(){
		$('body,html').css({ 'overflow': "" });
		document.ontouchmove=function(){return true;}
	}
	
	
	
	
	
	

	</script>
	<script type="text/javascript">
	/*跳转个人中心页*/
	$('.myicon').click(function(){
		window.location.href = "${pageContext.request.contextPath}/wetrip/show.act";
	});
	/*window.onload=function(){
		var days;
		var myDate = new Date();
		if('${coo}'=='coo'){
			 //构造当前日期对象
		      var mouth = myDate.getMonth()+1;
		      //获取年份
		      var year = myDate.getFullYear();
		      //定义当月的天数；
		      var days;
		      //当月份为二月时，根据闰年还是非闰年判断天数
		      if (mouth == 2) {
		        days = year % 4 == 0 ? 29 : 28;
		      } else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
		        //月份为：1,3,5,7,8,10,12 时，为大月.则天数为31；
		        days = 31;
		      } else {
		        //其他月份，天数为：30.
		        days = 30;
		      }
			removeCookie();
			var ss=myDate.getDate()
			if(ss==days){
				$("#checkStartDate").html(mouth+'-'+ss);
				if(mouth==12){
					$("#checkOutDate").html(01+'-'+01);	
				}
				$("#checkOutDate").html((mouth+1)+'-'+(01));
			}else{
				$("#checkStartDate").html(mouth+'-'+ss);
				$("#checkOutDate").html((mouth)+'-'+(ss+1));
			}
			
		}
	}*/
	</script>
</html>