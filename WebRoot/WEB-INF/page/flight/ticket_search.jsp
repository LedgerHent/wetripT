<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<title>凯撒商旅</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="css/reset.css">
		<link rel="stylesheet" type="text/css" href="css/allOrder.css">
		<link rel="stylesheet" type="text/css" href="css/ticket_reservation.css?_=<%=Math.random()%>">
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
		<style type="text/css">
			.bounced {    
			    z-index: 1000;
			}
			.calendar-bounding-box{
			    position: absolute !important;
			    top: 7rem !important;
			    width: 100% !important;
			    left: 0 !important;
			    padding-top: 0 !important;
			}
			.calendar-bounding-box .content-box {
			    
			    padding: 0 !important; 
			    border: none !important; 
			    
			}
			.calendar-bounding-box .content-box .inner {
				padding: 0 !important;
			}
			.inner table tbody tr {
				height: 40px !important;
			    line-height: 40px !important;
			}
			.calendar-bounding-box .content-box .inner table td {
			     padding: 0 !important; 
			     width: auto !important; 
			     height: auto !important; 
			     font-weight: 500 !important; 
			   	 border: none !important; 
			}
			.inner table tbody tr td a {
			    margin:auto !important;
			}
			.calendar-bounding-box .content-box .inner h4 {
			    font-size: 20px !important;
			    border:none !important;
			}
			.selected-date {
			    background-color: #ffffff !important;
			}
			.calendar-input-wrap {
			    position: relative; 
			    display: inline-block;
			}
			div.calendar-input-wrap input{
				font-size: 22px;
				padding-left:0.5rem;
				width:4.5rem;
				height:28px
			}
			.wf-search-u2-li3 input{
				text-align:right;
			}
			input, select {
			    vertical-align: bottom; 
			}
			.spancolor{
				font-size:14px;
				color:#ffb24d;
			}
		</style>
		
	</head>

	<body style="display:none" onpagehide="$.fn.LoadingHide()">	
		<div id="container">
		<div id="contont_ticket">
		<form id="one" action="flight/getFlightList.act" method="post">
			<input type="hidden" id="timestamp" name="timestamp" value="" /><!-- 时间戳 -->
			<input type="hidden" id="flag" name="flag" value="1" /><!-- 说明查的是第一程 -->
			<input type="hidden" id="type" name="type" value="1" />
			<input type="hidden" id="tripType" name="tripType" value="2" />
			<input type="hidden" id="cabinType" name="cabinType"  value="0" />
			<input type="hidden" id="airline"  name="airline" value="" />
			
			<input type="hidden" id="flowId1"  name="flowId" value="" />
			<input type="hidden" id="depAirport1" name="depAirport" value="" />
			<input type="hidden" id="arrAirport1" name="arrAirport" value="" />
			<input type="hidden" id="date1" name="date" value="" />
			<input type="hidden" id="flowId2"  name="flowId" value="" />
			<input type="hidden" id="depAirport2" name="depAirport" value="" />
			<input type="hidden" id="arrAirport2" name="arrAirport" value="" />
			<input type="hidden" id="date2" name="date" value="" />
			<input type="hidden" id="policyControl" name="policyControl" value="${policyControl }" />
		</form>
		<form id="wf" action="flight/getFlightList.act"></form>
		<form id="lc" action="flight/getFlightList.act"></form>
		
		<!--header头部-->
		<div class="header">
		   
				<ul class="res-header-main" style=" ">
					<li id="del_res" class="res-header-main-li1" style="">
					 <img src="img/home.png" onclick="window.location.href='common/toHomePage.act';" style="width:2rem;height: 2rem;padding-top: 1rem;cursor: pointer;padding-left:0.4rem;"></li>
					 <li class="res-header-main-li2" style=" ">
						<span id="clear_f " class="res-header-main-li2-span" style="  ">
							机票预订
						</span>
					</li>
					<li class="res-header-main-li3" style="padding-right: 0.4rem;">
						<c:if test="${user==null }" >登录</c:if>
						<c:if test="${user!=null }" >
							<div onclick="window.location.href='wetrip/show.act';return false;">
								<img src="images/myicon.png" style="width: 1.9rem;height: 1.9rem; margin-top:1rem;" /><a href="" style="display: inline-block;padding-top: 0.2rem;padding-left: 1rem;color:#ffffff;">${user.username}</a>
							</div>
						</c:if>
					</li>
				</ul>
		</div>
		<!--tab栏-->
		<div class="tic-tab">
			<img src="images/airbg.jpg" class="tab-bg" />				
			<div class="tab-main "  >
				<ul class="tab-main-con " >
					<li tripType="1" class="red1 active" style="flex: 33%;text-align: center;height: 4rem;line-height: 4rem;font-size: 1.3rem;color: #FFFFFF;cursor: pointer ">单程</li>
					<li tripType="2" class="red1 " style="flex: 33%;text-align: center;height: 4rem;line-height: 4rem;font-size: 1.3rem;color: #FFFFFF;cursor: pointer">往返</li>
					<li tripType="3" class="red1 " style="flex: 33%;text-align: center;height: 4rem;line-height: 4rem;font-size: 1.3rem;color: #FFFFFF;cursor: pointer">联程</li>					
				</ul>
			</div>
		</div>			
		<!-- </div> -->
		<!--搜索-->
		<div class="tic-search">
		<!--单程搜索-->
		<div id="1"  class="item1 " style="display: block;">
			<div class="item1-main " >
				<ul  class="item1-main-ul1 " >
					<li class="adress_ch "  >
						<div class="adress_ch_d1 " >出发城市</div>
						<div class="adress_ch_d2 gr_zone_ids" ttype="dep" type="1" flowId="1" flag="1">北京</div>
					</li>
					<li class="item1-main-ul1-li2 "  >
						<img src="icon/dc.png.gif " class="item1-main-ul1-li2-img " onclick="exchangeCity(1);" />
					</li>
					<li class="adress_ch " >
						<div class="adress_ch_d3 " >到达城市</div>
						<div class="adress_ch_d4 gr_zone_ids" ttype="arr" type="1" flowId="1" flag="2">上海</div>
					</li>				
				</ul>
				<ul id="sel_data" class="item1-main-ul2 ">
					<li class="item1-main-ul2-li " >
						<div class="item1-main-ul2-li-d1 " >去程</div>
						<!-- <tt class="item1-main-ul2-li-d2 date-select" ttype="date" type="1" flowId="1" ><span class="departureDate"></span><span id="departureDate_week_once" style="color: #fed077;font-size: 0.2rem; padding-left: 0.1rem; "></span></tt> -->
						
						<input id="J_DepDate" type="text" class="f-text" onfocus="this.blur()"/>
						<span id="J_DepDate_span" class="spancolor"></span>
					</li>
									
				</ul>
				<ul class="item1-main-ul3 ">
					<li class="item1-main-ul3-li1 " >
						<div class="item1-main-ul3-li1-d1 " >机票舱位</div>
						<div class="item1-main-ul3-li1-d2 " triptype="1" value="0" onclick="showCS(this);" >不限</div>
					</li>
					<li class="item1-main-ul3-li2 ">
						<div class="item1-main-ul3-li2-d1 ">出行方式</div>
						<div class="item1-main-ul3-li2-d2 " tripway="1" onclick="setTypeH(this);" val="1">因公出行</div>
					</li>				
				</ul>
				<ul class="item1-main-ul4 ">
					<li class="item1-main-ul4-li ">
						<button class="tic_search "  onclick="">搜&nbsp;&nbsp;&nbsp;&nbsp;索</button>
					</li>
									
				</ul>
			</div>						
		</div>
		<!--往返搜索-->
		<div id="2"  class="item1 " style="">
			<div class="wf-search "style="">
			<ul  class="wf-search-u1 "style="">
				<li class="wf-search-u1-li1 "style="">
					<div class="wf-search-u1-li1-d1 "style="  " >出发城市</div>
					<div class="wf-search-u1-li1-d2 gr_zone_ids"style=" " ttype="dep" type="2" flowId="1" flag="1">北京</div>
				</li>
				<li class="wf-search-u1-li2 "style=" ">
					<img src="icon/lc.png.gif " class="wf-search-u1-li2-img "style="  "  onclick="exchangeCity(2);"  />
				</li>
				<li class="wf-search-u1-li3 "style="">
					<div class="wf-search-u1-li3-d1 "style=" ">到达城市</div>
					<div class="wf-search-u1-li3-d2 gr_zone_ids"style="" ttype="arr" type="2" flowId="1" flag="2">上海</div>
				</li>				
			</ul>
			<ul  class="wf-search-u2 "style="">
				<li class="wf-search-u2-li1 "style=" ">
					<div class="wf-search-u2-li1-d1 "style="  ">去程</div>
					<!-- <tt  id="seleDate1" class="wf-search-u2-li1-d2 date-select" style=" " ttype="date" type="2" flowId="1"><span class="departureDate">02月28日</span><span id="departureDate_week_back1" style="color: #fed077;font-size: 0.2rem; padding-left: 0.1rem; ">周二</span></tt> -->
					<input id="J_DepDate_wf" type="text" class="f-text" value="" style="" onfocus="this.blur()"/>
					<span id="J_DepDate_wf_span" class="spancolor"></span>
				</li>
				<li class="wf-search-u2-li2 "style=" ">
					<img src="icon/data.png.gif " class="wf-search-u2-li2-img "style=" "/>
				</li>
				<li class="wf-search-u2-li3 "style="">
					<div class="wf-search-u2-li3-d1 "style=" ">返程</div>
					<!-- <tt  id="seleDate2" class="wf-search-u2-li3-d2 date-select"style="" ttype="date" type="2" flowId="2"><span id="departureDate_week_back2" style="color: #fed077;font-size: 0.2rem; padding-right: 0.1rem; "></span><span class="departureDate-back">--</span></tt> -->
					<span id="J_EndDate_wf_span" class="spancolor"></span>
					<input id="J_EndDate_wf" type="text" class="f-text" value="" onfocus="this.blur()"/>
				</li>				
			</ul>
			<ul class="wf-search-u3 "style="">
				<li class="wf-search-u3-li1 "style=" ">
					<div class="wf-search-u3-li1-d1 "style="">机票舱位</div>
					<div class="wf-search-u3-li1-d2 "style=" " triptype="2" value="0" onclick="showCS(this);">不限</div>
				</li>
				<li class="wf-search-u3-li2 "style="">
					<div class="wf-search-u3-li2-d1 "style="">出行方式</div>
					<div class="wf-search-u3-li2-d2 "style="  " tripway="2" onclick="setTypeH(this);" val="1">因公出行</div>
				</li>				
			</ul>
			<ul class="wf-search-u4 "style="">
				<li class="wf-search-u4-li "style="">
					<button class="tic_search " style="">搜&nbsp;&nbsp;&nbsp;&nbsp;索</button>
				</li>
								
			</ul>
		</div>						
		</div>
		<!--联程搜索-->
		<div id="3"  class="item1 " style=" ">
			<div class="lc-search "style=" ">
			<ul  class="lc-search-u1"style=" ">
				<li class="lc-search-u1-li1"style=" ">
					<div class="lc-search-u1-li1-d1"style=" ">出发城市</div>
					<div class="lc-search-u1-li1-d2 gr_zone_ids"style=" " ttype="dep" type="3" flowId="1" flag="1">北京</div>
				</li>
				<li class="lc-search-u1-li2"style="">
					<img src="icon/dc.png.gif " class="lc-search-u1-li2-img"style="  " onclick="exchangeCity(3);" />
				</li>
				<li class="lc-search-u1-li3"style=" ">
					<div class="lc-search-u1-li3-d1"style=" ">到达城市</div>
					<div class="lc-search-u1-li3-d2 gr_zone_ids"style=" " ttype="arr" type="3" flowId="1" flag="2">上海</div>
				</li>				
			</ul>
			<ul class="lc-search-u2"style=" ">
				<li class="lc-search-u2-li"style=" ">
					<div class="lc-search-u2-li-d1 "style="  ">第一程</div>
					<!-- <tt id="seleDate3" class="lc-search-u2-li-d2 date-select"style=" " ttype="date" type="3" flowId="1"><span class="departureDate">02月28日</span><span id="departureDate_week_conF1" style="color: #fed077;font-size: 0.2rem; padding-left: 0.1rem; ">周二</span></tt> -->
					<input id="J_DepDate_lc" type="text" class="f-text" value="" onfocus="this.blur()" />
					<span id="J_DepDate_lc_span" class="spancolor"></span>
				</li>
								
			</ul>
			<ul  class="lc-search-u3"style=" ">
				<li class="lc-search-u3-li1"style=" ">
					<div class="lc-search-u3-li1-d1"style="  ">出发城市</div>
					<div class="lc-search-u3-li1-d2 gr_zone_ids"style=" " ttype="dep" type="3" flowId="2" flag="1">北京</div>
				</li>
				<li class="lc-search-u3-li2"style=" ">
					<img src="icon/dc.png.gif " class="lc-search-u3-li2-img"style=" "/>
				</li>
				<li class="lc-search-u3-li3"style="">
					<div class="lc-search-u3-li3-d1"style=" ">到达城市</div>
					<div class="lc-search-u3-li3-d2 gr_zone_ids"style=" " ttype="arr" type="3" flowId="2" flag="2">上海</div>
				</li>				
			</ul>
			<ul class="lc-search-u4"style=" ">
				<li class="lc-search-u4-li"style=" ">
					<div class="lc-search-u4-li-d1"style="  ">第二程</div>
					<!-- <tt  id="seleDate4" class="lc-search-u4-li-d2 date-select"style=" " ttype="date" type="3"  flowId="2"><span class="departureDate-back">02月28日</span><span id="departureDate_week_conF2" style="color: #fed077;font-size: 0.2rem; padding-left: 0.1rem; ">周二</span></tt> -->
					<input id="J_EndDate_lc" type="text" class="f-text" value="" onfocus="this.blur()"/>
					<span id="J_EndDate_lc_span" class="spancolor"></span>
				</li>
								
			</ul>
			<ul class="lc-search-u5"style=" ">
				<li class="lc-search-u5-li1"style=" ">
					<div class="lc-search-u5-li1-d1" style=" ">机票舱位</div>
					<div class="lc-search-u5-li1-d2" style=" " triptype="3" value="0" onclick="showCS(this);">不限</div>
				</li>
				<li class="lc-search-u5-li2"style="">
					<div class="lc-search-u5-li2-d1" style="">出行方式</div>
					<div class="lc-search-u5-li2-d2" style="111" tripway="3" onclick="setTypeH(this);" val="1">因公出行</div>
				</li>				
			</ul>
			<ul class="lc-search-u6"style=" ">
				<li class="lc-search-u6-li"style=" ">
					<button class="tic_search " style=" ">搜&nbsp;&nbsp;&nbsp;&nbsp;索</button>
				</li>								
			</ul>
		</div>						
		</div>
	</div>
	</div>
	<!--日历选择开始-->
	
		<div id="J_Calendar" class="calendar" style="display: none;background: #FFFFFF;z-index:10001;
    position: fixed;
    top: 0;
    left: 0;">
			<div class="calendar-header">
				<div class="calendar-back">
					<img src="img/header.gif"/>
				</div>
				<p class="zhixiang">选择日期</p>
			</div>
			<div class="headerWeek tn-calendar-head">
			<table>
			<tbody>
			<tr>
			<th>日</th>
			<th>一</th>
			<th>二</th>
			<th>三</th>
			<th>四</th>
			<th>五</th>
			<th>六</th>
			</tr>
			</tbody>
			</table>
			</div>
		</div>
	<!--日历选择结束-->
	<!--城市选择开始-->
		<div class="loan_jm1" style="display: none;">
			<ul>
				<li class="loan_jm_l1">
					<span class="loan_jm_spa1">工作所在城市</span>
					<span class="wbk_srn select_show select_gr" id="" data-id="130100"></span><b></b>
				</li>
			</ul>
		</div>
		<div class="city-container" style="z-index: 9999; display: none;">
			<!--热门城市  -->
			<div class="j_hotcity_noshow" data-component="HotCityList">
			<div class="city-title">热门城市</div>
			<ul class="city-list flt-ui-city-block-list" data-template="CityBlockList">
				<li class="cityvalue" data-id="PEK" ><span>北京</span></li>
				<li class="cityvalue" data-id="SHA" ><span>上海</span></li>
				<li class="cityvalue" data-id="TSN" ><span>天津</span></li>
				<li class="cityvalue" data-id="CKG" ><span>重庆</span></li>
				<li class="cityvalue" data-id="DLC" ><span>大连</span></li>
				<li class="cityvalue" data-id="TAO"><span>青岛</span></li>
				<li class="cityvalue" data-id="XIY"><span>西安</span></li>
				<li class="cityvalue" data-id="NKG"><span>南京</span></li>
				<li class="cityvalue" data-id="HGH"><span>杭州</span></li>
				<li class="cityvalue" data-id="XMN"><span>厦门</span></li>
				<li class="cityvalue" data-id="CTU"><span>成都</span></li>
				<li class="cityvalue" data-id="SZX"><span>深圳</span></li>
				<li class="cityvalue" data-id="CAN"><span>广州</span></li>
				<li class="cityvalue" data-id="KMG"><span>昆明</span></li>
				<li class="cityvalue" data-id="SYX"><span>三亚</span></li>
				<li class="cityvalue" data-id="TNA" ><span>济南</span></li>
				<li class="cityvalue" data-id="CSX"><span>长沙</span></li>
				<li class="cityvalue" data-id="FOC"><span>福州</span></li>
				<li class="cityvalue" data-id="WUH"><span>武汉</span></li>
				<li class="cityvalue" data-id="CGO"><span>郑州</span></li>

			</ul>
		</div>
			<div class="city">
				<div class="city-list"><span class="city-letter" id="A1">A</span>
					<p class="cityvalue" data-id="YIE">阿尔山市</p>
					<p class="cityvalue" data-id="AKU">阿克苏</p>
					<p class="cityvalue" data-id="RHT">阿拉善右旗</p>
					<p class="cityvalue" data-id="AXF">阿拉善左旗</p>
					<p class="cityvalue" data-id="AAT">阿勒泰</p>
					<p class="cityvalue" data-id="AOG">鞍山</p>
					<p class="cityvalue" data-id="AKA">安康</p>
					<p class="cityvalue" data-id="AQG">安庆</p>
					<p class="cityvalue" data-id="AVA">安顺</p>
					<p class="cityvalue" data-id="AYN">安阳</p>
				</div>
				<div class="city-list"><span class="city-letter" id="B1">B</span>
					<p class="cityvalue" data-id="RLK">巴彦淖尔</p>
					<p class="cityvalue" data-id="AEB">百色</p>
					<p class="cityvalue" data-id="BFU">蚌埠</p>
					<p class="cityvalue" data-id="BAV">包头</p>
					<p class="cityvalue" data-id="BSD">保山</p>
					<p class="cityvalue" data-id="BHY">北海</p>
					<p class="cityvalue" data-id="PEK">北京</p>
					<p class="cityvalue" data-id="NAY">北京南苑</p>
					<p class="cityvalue" data-id="BFJ">毕节</p>
					<p class="cityvalue" data-id="BPL">博乐</p>
					<p class="cityvalue" data-id="BAR">博鳌</p>
				</div>
				<div class="city-list"><span class="city-letter" id="C1">C</span>
					<p class="cityvalue" data-id="BPX">昌都</p>
					<p class="cityvalue" data-id="CGD">常德</p>
					<p class="cityvalue" data-id="CZX">常州</p>
					<p class="cityvalue" data-id="NBS">长白山</p>
					<p class="cityvalue" data-id="CGQ">长春</p>
					<p class="cityvalue" data-id="CNI">长海</p>
					<p class="cityvalue" data-id="CSX">长沙</p>
					<p class="cityvalue" data-id="CIH">长治</p>
					<p class="cityvalue" data-id="CHG">朝阳</p>
					<p class="cityvalue" data-id="CTU">成都</p>
					<p class="cityvalue" data-id="CEH">承德</p>
					<p class="cityvalue" data-id="JUH">池州</p>
					<p class="cityvalue" data-id="CIF">赤峰</p>
				</div>
				<div class="city-list"><span class="city-letter" id="D1">D</span>
					<p class="cityvalue" data-id="DAX">达县</p>
					<p class="cityvalue" data-id="DLU">大理</p>
					<p class="cityvalue" data-id="DLC">大连</p>
					<p class="cityvalue" data-id="DQA">大庆</p>
					<p class="cityvalue" data-id="DAT">大同</p>
					<p class="cityvalue" data-id="DDG">丹东</p>
					<p class="cityvalue" data-id="DCY">稻城</p>
					<p class="cityvalue" data-id="LUM">德宏芒市</p>
					<p class="cityvalue" data-id="HXD">德令哈</p>
					<p class="cityvalue" data-id="DIG">迪庆</p>
					<p class="cityvalue" data-id="DOY">东营</p>
					<p class="cityvalue" data-id="DNH">敦煌</p>
				</div>
				<div class="city-list"><span class="city-letter" id="E1">E</span>
					<p class="cityvalue" data-id="EJN">额济纳旗</p>
					<p class="cityvalue" data-id="DSN">鄂尔多斯</p>
					<p class="cityvalue" data-id="ENH">恩施</p>
					<p class="cityvalue" data-id="ERL">二连浩特</p>
				</div>
				<div class="city-list"><span class="city-letter" id="F1">F</span>
					<p class="cityvalue" data-id="FUO">佛山</p>
					<p class="cityvalue" data-id="FOC">福州</p>
					<p class="cityvalue" data-id="FYJ">抚远</p>
					<p class="cityvalue" data-id="FUG">阜阳</p>
					<p class="cityvalue" data-id="FYN">富蕴</p>
				</div>
				<div class="city-list"><span class="city-letter" id="G1">G</span>
					<p class="cityvalue" data-id="KOW">赣州</p>
					<p class="cityvalue" data-id="GOQ">格尔木</p>
					<p class="cityvalue" data-id="GYU">固原</p>
					<p class="cityvalue" data-id="GHN">广汉</p>
					<p class="cityvalue" data-id="GYS">广元</p>
					<p class="cityvalue" data-id="CAN">广州</p>
					<p class="cityvalue" data-id="KWL">桂林</p>
					<p class="cityvalue" data-id="KWE">贵阳</p>
				</div>
				<div class="city-list"><span class="city-letter" id="H1">H</span>
					<p class="cityvalue" data-id="HRB">哈尔滨</p>
					<p class="cityvalue" data-id="HMI">哈密</p>
					<p class="cityvalue" data-id="HAK">海口</p>
					<p class="cityvalue" data-id="HLD">海拉尔</p>
					<p class="cityvalue" data-id="HDG">邯郸</p>
					<p class="cityvalue" data-id="HZG">汉中</p>
					<p class="cityvalue" data-id="HGH">杭州</p>
					<p class="cityvalue" data-id="HTN">和田</p>
					<p class="cityvalue" data-id="HFE">合肥</p>
					<p class="cityvalue" data-id="HCJ">河池</p>
					<p class="cityvalue" data-id="HEK">黑河</p>
					<p class="cityvalue" data-id="HNY">衡阳</p>
					<p class="cityvalue" data-id="HET">呼和浩特</p>
					<p class="cityvalue" data-id="HJJ">怀化</p>
					<p class="cityvalue" data-id="HIA">淮安</p>
					<p class="cityvalue" data-id="TXN">黄山</p>
				</div>
				<div class="city-list"><span class="city-letter" id="J1">J</span>
					<p class="cityvalue" data-id="JXA">鸡西</p>
					<p class="cityvalue" data-id="KNC">吉安</p>
					<p class="cityvalue" data-id="JIL">吉林</p>
					<p class="cityvalue" data-id="TNA">济南</p>
					<p class="cityvalue" data-id="JNG">济宁</p>
					<p class="cityvalue" data-id="JGN">嘉峪关</p>
					<p class="cityvalue" data-id="JMU">佳木斯</p>
					<p class="cityvalue" data-id="JGD">加格达奇</p>
					<p class="cityvalue" data-id="SWA">揭阳</p>
					<p class="cityvalue" data-id="JIC">金昌</p>
					<p class="cityvalue" data-id="JNZ">锦州</p>
					<p class="cityvalue" data-id="JJN">晋江</p>
					<p class="cityvalue" data-id="SHS">荆州</p>
					<p class="cityvalue" data-id="JGS">井冈山</p>
					<p class="cityvalue" data-id="JDZ">景德镇</p>
					<p class="cityvalue" data-id="JIU">九江</p>
					<p class="cityvalue" data-id="JZH">九寨沟</p>
					<p class="cityvalue" data-id="CHW">酒泉</p>
				</div>
				<div class="city-list"><span class="city-letter" id="K1">K</span>
					<p class="cityvalue" data-id="KJI">喀纳斯</p>
					<p class="cityvalue" data-id="KHG">喀什</p>
					<p class="cityvalue" data-id="KJH">凯里</p>
					<p class="cityvalue" data-id="KGT">康定</p>
					<p class="cityvalue" data-id="CGN">科隆</p>
					<p class="cityvalue" data-id="KRY">克拉玛依</p>
					<p class="cityvalue" data-id="KCA">库车</p>
					<p class="cityvalue" data-id="KRL">库尔勒</p>
					<p class="cityvalue" data-id="KMG">昆明</p>
				</div>
				<div class="city-list"><span class="city-letter" id="L1">L</span>
					<p class="cityvalue" data-id="LXA">拉萨</p>
					<p class="cityvalue" data-id="LHW">兰州</p>
					<p class="cityvalue" data-id="HZH">黎平</p>
					<p class="cityvalue" data-id="LLB">荔波</p>
					<p class="cityvalue" data-id="LJG">丽江</p>
					<p class="cityvalue" data-id="LYG">连云港</p>
					<p class="cityvalue" data-id="LZY">林芝</p>
					<p class="cityvalue" data-id="LNJ">临沧</p>
					<p class="cityvalue" data-id="LFQ">临汾</p>
					<p class="cityvalue" data-id="LYI">临沂</p>
					<p class="cityvalue" data-id="LZH">柳州</p>
					<p class="cityvalue" data-id="LPF">六盘水</p>
					<p class="cityvalue" data-id="LCX">龙岩</p>
					<p class="cityvalue" data-id="LDG">罗定</p>
					<p class="cityvalue" data-id="LYA">洛阳</p>
					<p class="cityvalue" data-id="LLV">吕梁</p>
					<p class="cityvalue" data-id="LZO">泸州</p>
				</div>
				<div class="city-list"><span class="city-letter" id="M1">M</span>
					<p class="cityvalue" data-id="NZH">满洲里</p>
					<p class="cityvalue" data-id="MXZ">梅县</p>
					<p class="cityvalue" data-id="MIG">绵阳</p>
					<p class="cityvalue" data-id="OHE">漠河</p>
					<p class="cityvalue" data-id="MDG">牡丹江</p>
				</div>
				<div class="city-list"><span class="city-letter" id="N1">N</span>
					<p class="cityvalue" data-id="NLT">那拉提</p>
					<p class="cityvalue" data-id="KHN">南昌</p>
					<p class="cityvalue" data-id="NAO">南充</p>
					<p class="cityvalue" data-id="NKG">南京</p>
					<p class="cityvalue" data-id="NNG">南宁</p>
					<p class="cityvalue" data-id="NTG">南通</p>
					<p class="cityvalue" data-id="NNY">南阳</p>
					<p class="cityvalue" data-id="NNN">嫩江</p>
					<p class="cityvalue" data-id="NGB">宁波</p>
					<p class="cityvalue" data-id="NLH">宁蒗</p>
				</div>
				<div class="city-list"><span class="city-letter" id="P1">P</span>
					<p class="cityvalue" data-id="PZI">攀枝花</p>
					<p class="cityvalue" data-id="SYM">普洱</p>			
				</div>
				<div class="city-list"><span class="city-letter" id="Q1">Q</span>
					<p class="cityvalue" data-id="NDG">齐齐哈尔</p>
					<p class="cityvalue" data-id="JIQ">黔江</p>
					<p class="cityvalue" data-id="IQM">且末</p>
					<p class="cityvalue" data-id="BPE">秦皇岛</p>
					<p class="cityvalue" data-id="TAO">青岛</p>
					<p class="cityvalue" data-id="IQN">庆阳</p>
					<p class="cityvalue" data-id="JUZ">衢州</p>
				</div>
				<div class="city-list"><span class="city-letter" id="R1">R</span>
					<p class="cityvalue" data-id="RKZ">日喀则</p>
					<p class="cityvalue" data-id="RIZ">日照</p>
				</div>
				<div class="city-list"><span class="city-letter" id="S1">S</span>
					<p class="cityvalue" data-id="SYX">三亚</p>
					<p class="cityvalue" data-id="SHA">上海</p>
					<p class="cityvalue" data-id="PVG">上海浦东</p>
					<p class="cityvalue" data-id="HSC">韶关</p>
					<p class="cityvalue" data-id="SZX">深圳</p>
					<p class="cityvalue" data-id="SHE">沈阳</p>
					<p class="cityvalue" data-id="WDS">十堰</p>
					<p class="cityvalue" data-id="SJW">石家庄</p>
					<p class="cityvalue" data-id="SZV">苏州</p>
					<p class="cityvalue" data-id="FUD">绥芬河</p>
					<p class="cityvalue" data-id="SUN">遂宁</p>
					<p class="cityvalue" data-id="TCG">塔城</p>
				</div>
				<div class="city-list"><span class="city-letter" id="T1">T</span>
					<p class="cityvalue" data-id="HYN">台州</p>
					<p class="cityvalue" data-id="TYN">太原</p>
					<p class="cityvalue" data-id="TVS">唐山</p>
					<p class="cityvalue" data-id="TCZ">腾冲</p>
					<p class="cityvalue" data-id="TSN">天津</p>
					<p class="cityvalue" data-id="THQ">天水</p>
					<p class="cityvalue" data-id="TNH">通化</p>
					<p class="cityvalue" data-id="TGO">通辽</p>
					<p class="cityvalue" data-id="TEN">铜仁</p>
					<p class="cityvalue" data-id="TLQ">吐鲁番</p>
				</div>
				<div class="city-list"><span class="city-letter" id="W1">W</span>
					<p class="cityvalue" data-id="WXN">万州</p>
					<p class="cityvalue" data-id="WEH">威海</p>
					<p class="cityvalue" data-id="WEF">潍坊</p>
					<p class="cityvalue" data-id="WNZ">温州</p>
					<p class="cityvalue" data-id="WNH">文山</p>
					<p class="cityvalue" data-id="WUA">乌海</p>
					<p class="cityvalue" data-id="HLH">乌兰浩特</p>
					<p class="cityvalue" data-id="URC">乌鲁木齐</p>
					<p class="cityvalue" data-id="WUX">无锡</p>
					<p class="cityvalue" data-id="WUZ">梧州</p>
					<p class="cityvalue" data-id="WUH">武汉</p>
					<p class="cityvalue" data-id="WUS">武夷山</p>
				</div>
				<div class="city-list"><span class="city-letter" id="X1">X</span>
					<p class="cityvalue" data-id="XIY">西安</p>
					<p class="cityvalue" data-id="XIC">西昌</p>
					<p class="cityvalue" data-id="XNN">西宁</p>
					<p class="cityvalue" data-id="JHG">西双版纳</p>
					<p class="cityvalue" data-id="XIL">锡林浩特</p>
					<p class="cityvalue" data-id="XMN">厦门</p>
					<p class="cityvalue" data-id="GXH">夏河</p>
					<p class="cityvalue" data-id="XFN">襄樊</p>
					<p class="cityvalue" data-id="XEN">兴城</p>
					<p class="cityvalue" data-id="ACX">兴义</p>
					<p class="cityvalue" data-id="XNT">邢台</p>
					<p class="cityvalue" data-id="XUZ">徐州</p>
				</div>
				<div class="city-list"><span class="city-letter" id="Y1">Y</span>
					<p class="cityvalue" data-id="YNT">烟台</p>
					<p class="cityvalue" data-id="YNZ">盐城</p>
					<p class="cityvalue" data-id="ENY">延安</p>
					<p class="cityvalue" data-id="YNJ">延吉</p>
					<p class="cityvalue" data-id="YTY">扬州</p>
					<p class="cityvalue" data-id="LDS">伊春</p>
					<p class="cityvalue" data-id="YIN">伊宁</p>
					<p class="cityvalue" data-id="YBP">宜宾</p>
					<p class="cityvalue" data-id="YIH">宜昌</p>
					<p class="cityvalue" data-id="YIC">宜春</p>
					<p class="cityvalue" data-id="YIW">义乌</p>
					<p class="cityvalue" data-id="INC">银川</p>
					<p class="cityvalue" data-id="LLF">永州</p>
					<p class="cityvalue" data-id="UYN">榆林</p>
					<p class="cityvalue" data-id="YUS">玉树</p>
					<p class="cityvalue" data-id="YCU">运城</p>
				</div>
				<div class="city-list"><span class="city-letter" id="Z1">Z</span>
					<p class="cityvalue" data-id="ZHA">湛江</p>
					<p class="cityvalue" data-id="DYG">张家界</p>
					<p class="cityvalue" data-id="ZQZ">张家口</p>
					<p class="cityvalue" data-id="YZY">张掖</p>
					<p class="cityvalue" data-id="ZAT">昭通</p>
					<p class="cityvalue" data-id="CGO">郑州</p>
					<p class="cityvalue" data-id="ZHY">中卫</p>
					<p class="cityvalue" data-id="CKG">重庆</p>
					<p class="cityvalue" data-id="HSN">舟山</p>
					<p class="cityvalue" data-id="ZUH">珠海</p>
					<p class="cityvalue" data-id="ZYI">遵义</p>
				</div>
			</div>
			 <div class="letter">
				<ul>
					<li>
						<a href="javascript:;">A</a>
					</li>
					<li>
						<a href="javascript:;">B</a>
					</li>
					<li>
						<a href="javascript:;">C</a>
					</li>
					<li>
						<a href="javascript:;">D</a>
					</li>
					<li>
						<a href="javascript:;">E</a>
					</li>
					<li>
						<a href="javascript:;">F</a>
					</li>
					<li>
						<a href="javascript:;">G</a>
					</li>
					<li>
						<a href="javascript:;">H</a>
					</li>
					<li>
						<a href="javascript:;">J</a>
					</li>
					<li>
						<a href="javascript:;">K</a>
					</li>
					<li>
						<a href="javascript:;">L</a>
					</li>
					<li>
						<a href="javascript:;">M</a>
					</li>
					<li>
						<a href="javascript:;">N</a>
					</li>
					<li>
						<a href="javascript:;">P</a>
					</li>
					<li>
						<a href="javascript:;">Q</a>
					</li>
					<li>
						<a href="javascript:;">R</a>
					</li>
					<li>
						<a href="javascript:;">S</a>
					</li>
					<li>
						<a href="javascript:;">T</a>
					</li>
					<li>
						<a href="javascript:;">W</a>
					</li>
					<li>
						<a href="javascript:;">X</a>
					</li>
					<li>
						<a href="javascript:;">Y</a>
					</li>
					<li>
						<a href="javascript:;">Z</a>
					</li>
				</ul> 
			</div>
		</div>
	<!--城市选择结束-->
	
	
	<div id="cs" class="bounced instructe" style="display: none;">
		<div class="payment-con" id="test">
			<div class="payment-title">
				<p>
					<p>舱位类型</p>
				</p>
			</div>
			<div class="payment-choose">
				<p>
					<span value="0">不限</span>
					<img src="img/choose.png"  class="selected" />
				</p>
				<p>
					<span value="1">经济舱</span>
					<img src="img/choose.png"  class="selected" />
				</p>
				<p>
					<span value="2">公务舱</span>
					<img src="img/choose.png"  class="selected" />
				</p>
				<p>
					<span value="3">头等舱</span>
					<img src="img/choose.png"  class="selected" />
				</p>
				<p>
					<span value="23">公务舱和头等舱</span>
					<img src="img/choose.png"  class="selected" />
				</p>
			</div>
		</div>
	</div>

	</div>
	
	</body>
		<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/page/flight/ticket_list.js"></script>
		<script type="text/javascript" src="js/page/DateUtil.js"></script>
		<script type="text/javascript" src="js/page/comm.js"></script>
		<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
		<script type="text/javascript">
			//tripType=1&tripInfo[0].depAirport=PEK&tripInfo[0].ArrAirport=SHA&tripInfo[0].date=2017-05-10&pageNum=1&numPerPage=2
			
			var type = 1;//1因公 2因私
			var tripType = 1;//1为单程 2为往返 3为联程
			var cabinType = 0;//
			var airline = "";
			type = $.cookie("type")?$.cookie("type"):type;
			tripType = $.cookie("tripType")?$.cookie("tripType"):tripType;
			cabinType = $.cookie("cabinType")?$.cookie("cabinType"):cabinType;
			airline = $.cookie("airline")?$.cookie("airline"):airline;
			
			var list = "flight/getFlightList.act";
			var detail = "flight/getFlightDetail.act";
			
			var defaultDate1 = parseDate(formatDate(new Date().getTime()+24*3600*1000),'yyyy-MM-dd HH:mm:ss');
			var defaultDate2 = parseDate(formatDate(new Date().getTime()+48*3600*1000),'yyyy-MM-dd HH:mm:ss');
			
			/* var trips = [];
			var passengers =[];
			var param = null; */
			
			//往返查询 测试 
			function testRT(){
				setTripType(2);
				setAirline("");
				setCabinType(0);
				setParam(1, "PEK", "SHA", "2017-05-10");
				setParam(2, "SHA", "PEK", "2017-05-15");
			}
			//单程查询测试 
			function testOne(){
				setTripType(1);
				setAirline("");
				setCabinType(0);
				setParam(1, "PEK", "SHA", "2017-04-21");
			}
			
			//查询的方法
			function search(){
					//testOne();
					var flag = checkParam();
					if(1 != flag){
						showMsg(flag);
						return false;
					}else{
						var policyControl=$("#policyControl").val();
						var type=getType();//1因公 2因私
						//alert("搜索"+type+"________"+policyControl);
						if(type==1 && policyControl==1){
							 //alert("选择乘机人页面");
							 $("#one").attr("action","ppp/selectPassengerList.act");
					 		 $("#one").submit();
					 		 //testOne();
							/* var flag = checkParam();
							if(1 != flag){
								showMsg(flag);
								return false;
							}else{
								var time = new Date();
								$("#timestamp").val(time.getTime());
								 $(document).LoadingShow();
								setTimeout(function(){
								 	$("#one").submit();
								},100) 
								
							} */
						}else{
							var time = new Date();
							$("#timestamp").val(time.getTime());
							 $(document).LoadingShow();
							setTimeout(function(){
							 	$("#one").submit();
							},100) 
						}
					}
			}
			
			//检查参数
			function checkParam(){
				if(!$("#cabinType").val())
					return "舱位类型不能为空";
				/* if(!$("#airline").val())
					return "舱位类型不能为空"; */
				if(!$("#type").val())
					return "出行方式不能为空";
				var prefix1 = (tripType==1?"":(tripType==2?"去程":"第一程"));
				var prefix2 = (tripType>1?(tripType==2?"返程":"第二程"):"");
				if(!$("#depAirport1").val())
					return prefix1 + "出发城市不能为空";
				if(!$("#arrAirport1").val())
					return prefix1 + "到达城市不能为空";
				if(!$("#date1").val())
					return prefix1 + "出发日期不能为空";
				else{
					if(parseDate($("#date1").val(),"yyyy-MM-dd").getTime()<parseDate(formatDate(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd").getTime()){
						return prefix1 + "出发日期不能晚于当前日期";
					}
				}
				if(tripType>=2){
					if(!$("#depAirport2").val())
						return prefix2 + "出发城市不能为空";
					if(!$("#arrAirport2").val())
						return prefix2 + "到达城市不能为空";
					if(!$("#date2").val())
						return prefix2 + "出发日期不能为空";
					else{
						if(parseDate($("#date2").val(),"yyyy-MM-dd").getTime()<parseDate(formatDate(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd").getTime()){
							return prefix2 + "出发日期不能晚于当前日期";
						}
					}
					var date1 = $("#date1").val();
					var date2 = $("#date2").val();
					if(parseDate(date1,'yyyy-MM-dd').getTime()>parseDate(date2,'yyyy-MM-dd').getTime())
						return prefix1 + "出发日期不能晚于" + prefix2 + "出发日期";
					
				}
				return 1;
			}
			
			//设置因公因私出行 1因公 2因私
			function setType(t){
				type = t;
				$("#type").val(type);
			}
			
			//获取因公因私出行 1因公 2因私
			function getType(){
				var t = $("#type").val();
				return t;
			}
			//设置 1位单程 2为往返 3为联程
			function setTripType(tt){
				tripType = tt;
				$("#tripType").val(tt);
			}
			//舱位类型
			function setCabinType(ct){
				cabinType = ct;
				$("#cabinType").val(cabinType);
			}
			//设置航空公司
			function setAirline(al){
				airline = al;
				$("#airline").val(al);
			}
			
			
			//setParam(1,"PEK",NULL,NULL);
			//setParam(2,NULL,"PEK",NULL);
			//2015-10-01
			
			//设置参数   第几航程  起飞机场 到达机场 日期
			function setParam(flowId,depAirport,arrAirport,date,replaceFlag){
				if(flowId){
					$("#flowId" + flowId).val(flowId);
					if(depAirport){
						$("#depAirport" + flowId).val(depAirport);
					}else{
						if(replaceFlag)
							$("#depAirport" + flowId).val("");
					}
					if(arrAirport){
						$("#arrAirport" + flowId).val(arrAirport);
					}else{
						if(replaceFlag)
							$("#arrAirport" + flowId).val("");
					}
					if(date){
						$("#date" + flowId).val(date);
					}else{
						if(replaceFlag)
							$("#date" + flowId).val("");
					}
				}
			}
			//初始化参数
			function initParam(){
				/* type = $.cookie("type")?$.cookie("type"):type;
				tripType = $.cookie("tripType")?$.cookie("tripType"):tripType;
				cabinType = $.cookie("cabinType")?$.cookie("cabinType"):cabinType;
				airline = $.cookie("airline")?$.cookie("airline"):airline; */
				var flowId1 = $.cookie("flowId1")?$.cookie("flowId1"):1;
				var depAirport1 = $.cookie("depAirport1")?$.cookie("depAirport1"):"PEK";
				var arrAirport1 = $.cookie("arrAirport1")?$.cookie("arrAirport1"):"SHA";
				var date1 = $.cookie("date1")?$.cookie("date1"):formatDate(defaultDate1,"yyyy-MM-dd");
				var flowId2 = $.cookie("flowId2")?$.cookie("flowId2"):2;
				var depAirport2 = $.cookie("depAirport2")?$.cookie("depAirport2"):"SHA";
				var arrAirport2 = $.cookie("arrAirport2")?$.cookie("arrAirport2"):"PEK";
				var date2 = $.cookie("date2")?$.cookie("date2"):formatDate(defaultDate2,"yyyy-MM-dd");
				if(parseDate(date1,"yyyy-MM-dd").getTime()<parseDate(formatDate(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd").getTime()){
					date1 = formatDate(defaultDate1,"yyyy-MM-dd");
				}
				if(parseDate(date2,"yyyy-MM-dd").getTime()<parseDate(formatDate(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd").getTime()){
					date2 = formatDate(defaultDate2,"yyyy-MM-dd");
				}
				setParam(1, depAirport1, arrAirport1, date1);
				setPageType(tripType, type);
				setPageCabin(tripType, cabinType);
				if(1==tripType){
					setPageArrCity(tripType, 1, arrAirport1);
					setPageDepCity(tripType, 1, depAirport1);
					setPageDate(tripType, 1, parseDate(date1,'yyyy-MM-dd'));
				}else{
					setPageArrCity(tripType, 1, arrAirport1);
					setPageDepCity(tripType, 1, depAirport1);
					if(2==tripType){
						setParam(2, arrAirport1, depAirport1, date2);
					}else if(3==tripType){
						if(depAirport1==arrAirport2){
							setPageDepCity(tripType, 2, depAirport2);
							setPageArrCity(tripType, 2, null);
							setParam(2, depAirport2, null, date2,true);
							
						}else{
							setPageDepCity(tripType, 2, depAirport2);
							setPageArrCity(tripType, 2, arrAirport2);
							setParam(2, depAirport2, arrAirport2, date2);
						}
					}
					setPageDate(tripType, 1, parseDate(date1,'yyyy-MM-dd'));
					setPageDate(tripType, 2, parseDate(date2,'yyyy-MM-dd'));
				}
				
			}
			//设置cookie
			function setCookie(){
				$.cookie("type",$("#type").val());
				$.cookie("tripType",$("#tripType").val());
				$.cookie("cabinType",$("#cabinType").val());
				$.cookie("airline",$("#airline").val());
				$.cookie("flowId1",$("#flowId1").val());
				$.cookie("depAirport1",$("#depAirport1").val());
				$.cookie("arrAirport1",$("#arrAirport1").val());
				$.cookie("date1",$("#date1").val());
				$.cookie("flowId2",$("#flowId2").val());
				$.cookie("depAirport2",$("#depAirport2").val());
				$.cookie("arrAirport2",$("#arrAirport2").val());
				$.cookie("date2",$("#date2").val());
				$.cookie("policyControl",$("#policyControl").val());
			}
			
			$(function(){
				$(".tic_search").on("click",search);
				
				var code = "${result.status}";
				var msg = "${result.data}";
				if(1==code){
					showMsg(msg);
					resetParam();
				}
				
				
				tripType = $.cookie("tripType")?$.cookie("tripType"):tripType;
				if(tripType){
					$(".red1").eq(tripType-1).click();
				}
				
				$("form").on('submit',function(event){
					setCookie();
					/* $(document).LoadingShow(); */
				});
				
				$(window).unload(function(){$(document).LoadingHide();});
				
				$("span[id^='date_J_']").on('click',function(){
					var id = $(this).attr("id");
					$("#" + id.substring(id.indexOf("_") + 1)).trigger("click");
				});
				
				setSize();
				
			});
			
			//交换城市
			function exchangeCity(type){
				if(1==type){//单程
					var  depA = $("#depAirport1").val();
					var  arrA = $("#arrAirport1").val();
					setParam(1, arrA, depA, null, 0);
					setPageDepCity(1, 1, arrA);
					setPageArrCity(1, 1, depA);
				}else if(2==type){
					var  depA = $("#depAirport1").val();
					var  arrA = $("#arrAirport1").val();
					setParam(1, arrA, depA, null, 0);
					setParam(2, depA, arrA, null, 0);
					setPageDepCity(2, 1, arrA);
					setPageArrCity(2, 1, depA);
				}else if(3==type){
					var  depA1 = $("#depAirport1").val();
					var  arrA1 = $("#arrAirport1").val();
					var  depA2 = $("#depAirport2").val();
					setParam(1, arrA1, depA1, null, 0);
					setParam(2, depA1, null, null, 0);
					setPageDepCity(3, 1, arrA1);
					setPageArrCity(3, 1, depA1);
					setPageDepCity(3, 2, depA1);
				}
			}
			
			function resetParam(){}
		</script>
<script>
	//动态设置html跟字体大小！ 当前屏幕宽度/7.5(假设750px的设计稿跟字体100，设计稿最大宽度7.5rem,
		//7.5！  css中：375px/字体大小=7.5
		//document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';		
		/* 监听视口大小 S */
		function setSize() {
			var iHtml = document.querySelector('html');
			var w = iHtml.getBoundingClientRect().width;
			w = w > 750 ? 750 : w;
			iHtml.style.fontSize = w / 37.5 + 'px';
			$("body").show();
		}
		window.addEventListener('resize', setSize, false); 
		window.addEventListener('orientantionchange', setSize, false);
		/* 监听视口大小 E */
		
   //tab栏切换
	var btn = $('.red1');
	var items = $('.item1');
	for(i = 0; i < btn.length; i++) {
		btn[i].index = i
		btn[i].onclick = function() {
			for(j = 0; j < btn.length; j++) {
				btn[j].className = ''
				items[j].style.display = 'none'
			}
			this.className = 'active'
			items[this.index].style.display = 'block';
			var ttype = $(this).attr('tripType');
			setTripType(ttype);//设置航程类型
			if(ttype==1){
				initParam();
				setCabinType($("div[triptype=" + ttype + "]").attr('value'));
			}
			if(ttype==2){
				initParam();
				setCabinType($("div[triptype=" + ttype + "]").attr('value'));
			}
			if(ttype==3){
				initParam();
				setCabinType($("div[triptype=" + ttype + "]").attr('value'));
			}
		}
	}
	</script>
	<!--日历-->
<script src="http://yui.yahooapis.com/3.5.1/build/yui/yui-min.js"></script>
<script>
	
	//单程日历模块
		var oCal1,oCal2,oCal3;
				YUI({
				    modules: {
				        'trip-calendar': {
				            fullpath: 'js/trip-calendar.js',
				            type    : 'js',
				            requires: ['trip-calendar-css']
				        },
				        'trip-calendar-css': {
				            fullpath: 'css/trip-calendar.css',
				            type    : 'css'
				        }
				    }
				}).use('trip-calendar', function(Y) {   
				    oCal1 = new Y.TripCalendar({
				        minDate         : new Date,     //最小时间限制
				        triggerNode     : '#J_DepDate', //第一个触节点
				        count:12,
				        selectedDate:new Date,
				    });
				    oCal1.on('render', function(e){
				       $("#J_Calendar").show();
				    });
				    oCal1.on('dateclick', function(e){
				     	$("#J_Calendar").hide();
				       	calSetDate(this.getCurrentNode(),e.date);
				       	setInputLength("J_DepDate");
				    });
				});
				//wangfan日历模块
				YUI({
				    modules: {
				        'trip-calendar': {
				            fullpath: 'js/trip-calendar.js',
				            type    : 'js',
				            requires: ['trip-calendar-css']
				        },
				        'trip-calendar-css': {
				            fullpath: 'css/trip-calendar.css',
				            type    : 'css'
				        }
				    }
				}).use('trip-calendar', function(Y) {   
				    oCal2 = new Y.TripCalendar({
				        minDate         : new Date,     //最小时间限制
				        triggerNode     : '#J_DepDate_wf', //第一个触节点
				        finalTriggerNode: '#J_EndDate_wf',  //最后一个触发节点
				        count:12,
				        selectedDate:new Date,
				    });
				    oCal2.on('render', function(e){
				       $("#J_Calendar").show();
				       var id = $(this.getCurrentNode()).attr("id");
				       if(id=="J_EndDate_wf"){
				       		this.set("maxDate","");
				       }
				    });
				    oCal2.on('dateclick', function(e){
				    	calSetDate(this.getCurrentNode(),e.date);
				    	$("#J_Calendar").hide();
				    	setInputLength("J_DepDate_wf")
				    	setInputLength("J_EndDate_wf")
				    });
				});
				//liancheng日历模块
				YUI({
			    	modules: {
				        'trip-calendar': {
				            fullpath: 'js/trip-calendar.js',
				            type    : 'js',
				            requires: ['trip-calendar-css']
				        },
				        'trip-calendar-css': {
				            fullpath: 'css/trip-calendar.css',
				            type    : 'css'
				        }
			    	}
				}).use('trip-calendar', function(Y) {   
				    oCal3 = new Y.TripCalendar({
				        minDate         : new Date,     //最小时间限制
				        triggerNode     : '#J_DepDate_lc', //第一个触节点
				        finalTriggerNode: '#J_EndDate_lc',  //最后一个触发节点
				        count:12,
				        selectedDate:new Date,
				    });
				    oCal3.on('render', function(e){
				       $("#J_Calendar").show();
				       var id = $(this.getCurrentNode()).attr("id");
				       if(id=="J_EndDate_lc"){
				       		this.set("maxDate","");
				       }
				    });
				    oCal3.on('dateclick', function(e){
				    	calSetDate(this.getCurrentNode(),e.date);
				    	$("#J_Calendar").hide();
				    	setInputLength("J_DepDate_lc")
				    	setInputLength("J_EndDate_lc")
				    });
				});
	
 	//日历用来设置form的日期方法
 	function calSetDate(node,date){
 		var id = $(node).attr("id");
 		if("J_DepDate" == id){
 			setParam(1,null,null,date);
 			setPageDate(1,1,parseDate(date,'yyyy-MM-dd'));
 			$.cookie("date1",parseDate(date,'yyyy-MM-dd'));
 		}
 		if("J_DepDate_wf" == id){
 			setParam(1,null,null,date);
 			setPageDate(2,1,parseDate(date,'yyyy-MM-dd'));
 			$.cookie("date1",parseDate(date,'yyyy-MM-dd'));
 		}
 		if("J_EndDate_wf" == id){
 			setParam(2,null,null,date);
 			setPageDate(2,2,parseDate(date,'yyyy-MM-dd'));
 			$.cookie("date2",parseDate(date,'yyyy-MM-dd'));
 			oCal2.set("maxDate",parseDate(date,'yyyy-MM-dd'));
 		}
 		if("J_DepDate_lc" == id){
 			setParam(1,null,null,date);
 			setPageDate(3,1,parseDate(date,'yyyy-MM-dd'));
 			$.cookie("date1",parseDate(date,'yyyy-MM-dd'));
 		}
 		if("J_EndDate_lc" == id){
 			setParam(2,null,null,date);
 			setPageDate(3,2,parseDate(date,'yyyy-MM-dd'));
 			$.cookie("date2",parseDate(date,'yyyy-MM-dd'));
 			
 			oCal3.set("maxDate",parseDate(date,'yyyy-MM-dd'));
 		}

 	}
 
</script>
	<!--城市选择js代码-->
	<script type="text/javascript">
		//加载城市事件
		var tCity = ''
		$('body').on('click', '.gr_zone_ids', function() {
			var zid = $(this).attr('id');
			$('.city-container').show();
			$("#contont_ticket").hide()
			tCity = $(this)
		});
	
		//选择城市 
		$('body').on('click', '.cityvalue', function() {
			var type = $('.city-container').data('type');
			tCity.html($(this).html()).attr('data-id', $(this).attr('data-id'));
			$('.city-container').hide();
			$("#contont_ticket").show()
			var fId = tCity.attr('flowId'); 
			var flag = tCity.attr('flag'); 
			var dep,arr,dateTime;
			if(flag==1){
				dep = $(this).attr('data-id');
				arr = "";
			}else{
				arr = $(this).attr('data-id');
				dep = "";
			}
			setParam(fId, dep, arr, null);
			if(tripType==2){
				setParam(fId==1?2:1, arr, dep, null);
			}
	
		});
		//字母对应搜索
		$('body').on('click', '.letter a', function() {
			var s = $(this).html();
			$(window).scrollTop($('#' + s + '1').offset().top);
		});
		//设置因公因私
		function setTypeH(obj){
			var val = $(obj).attr("val")==1?2:1;
			$(obj).attr("val",val);
			var str = "因" + (val==1?"公":"私") + "出行";
			$(obj).html(str);
			setType(val);
		}
		//设置页面日期
		function setPageDate(type,flowId,date){
			var p2 = "";
			var p3 = "";
			if(type==1){
				p3 = "";
			}
			if(type==2){
				p3="_wf";
			}
			if(type==3){
				p3="_lc";
			}
			if(flowId==1){
				p2 = "J_DepDate";
			}
			if(flowId==2){
				p2 = "J_EndDate";
			}
			$("#"+p2+p3).val("");
			$("#"+p2+p3 + "_span").html("");
			$("#"+p2+p3).val(formatDate(date,'M月d日'));
			$("#"+p2+p3 + "_span").html(formatDate(date,'w'));
			
			setInputLength(p2+p3);
			
		}
		//设置页面起飞城市
		function setPageDepCity(type,flowId,city){
			if(!city){
				$("div.gr_zone_ids[ttype='dep'][type='" + type + "'][flowId='" + flowId + "']").html("");
			}else
				$("div.gr_zone_ids[ttype='dep'][type='" + type + "'][flowId='" + flowId + "']").html(cityMap[city]);
		}
		//设置页面到达城市
		function setPageArrCity(type,flowId,city){
			if(!city){
				$("div.gr_zone_ids[ttype='arr'][type='" + type + "'][flowId='" + flowId + "']").html("");
			}else
				$("div.gr_zone_ids[ttype='arr'][type='" + type + "'][flowId='" + flowId + "']").html(cityMap[city]);
		}
		//设置页面仓位信息
		function setPageCabin(tripType,cabin){
			var str = "不限";
			if(1==cabin){
				str = "经济舱";
			}
			if(2==cabin){
				str = "公务舱";
			}
			if(3==cabin){
				str = "头等舱";
			}
			if(23==cabin){
				str = "公务舱和头等舱";
			}
			$("div[triptype='" + tripType + "']").html(str);
			$("div[triptype='" + tripType + "']").attr("value",cabin);
		}
		//设置页面因公因私
		function setPageType(tripType,type){
			var str = "因公出行";
			if(type==2){
				str = "因私出行";
			}
			$("div[tripway='" + tripType + "']").html(str);
			$("div[tripway='" + tripType + "']").attr("val",type);
		}
		
		var cabinTypeObj;
		var cabinClickEle;
		
		function showCS(obj){
			cabinTypeObj = obj;
			var num = $("#test>.payment-choose>p").length*4.4 + 2.4 + 'rem';
			//var num = ($('div[id] ul').length-10) * 28 /20 + 'rem';
			
			cabinClickEle = obj;
			
			$('.instructe .payment-choose p').each(function(){
				if($(this).children().eq(0).html() == $(cabinClickEle).html()){
					$(this).css('color', '#00AFEC').siblings().css('color', '');
					$(this).find('.selected').show().parents('.instructe .payment-choose p').siblings().find('.selected').hide();
				}
			});
			
			layerShow('.instructe', '.instructe .payment-con', num);
		}
		
		
		function layerShow(obj, attrObj, numLength) {
			var height = $("body").height();
			$('.bounced').height(height);
			$(obj).fadeIn();
			$(attrObj).animate({
				height: numLength
			}, 1000)
		};
		
		$('.instructe').bind('click', function(e) {
			hideLayer(e,'test','#test');
		});
		
		function hideLayer(e,Obj,idAttr){
			var e = e || window.event; //浏览器兼容性   
			var elem = e.target || e.srcElement;
			while(elem) { //循环判断至跟节点，防止点击的是div子元素   
				if(elem.id && elem.id == Obj) {
					return;
				}
				elem = elem.parentNode;
			}
			$(idAttr).parents('.bounced').css('display', 'none'); //点击的不是div或其子元素 
		}
			
		$('.instructe .payment-choose p').on('click', function() {
			$(this).css('color', '#00AFEC').siblings().css('color', '');
			$(this).find('.selected').show().parents('.instructe .payment-choose p').siblings().find('.selected').hide();
			$('.instructe').delay(500).fadeOut(0);
			$(cabinTypeObj).html($(this).find('span').eq(0).html());
			$(cabinClickEle).attr("value",$(this).find('span').eq(0).attr("value"));
			setCabinType($(this).find('span').eq(0).attr("value"));
			
			//$('.message-order .insurance-type').html($(this).find('span').eq(0).html());
		});
		
	</script>
	<!--日历返回按钮  -->
	<script>
		$("#J_Calendar").on("click",function(){
			$("#J_Calendar").hide();
			$("#contont_ticket").show();
		})
	</script>
		<script>
		function setInputLength(thisInput) {
			var testLength = document.getElementById(thisInput).value.length
			var inputLength = testLength*18<=0?84:testLength*18;
			inputLength = inputLength+"px"
			document.getElementById(thisInput).style.width=inputLength
		}

</script>
</html>


