<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.viptrip.hotelHtml5.common.ConfigConstants" %>
<c:set var="SUCCESS_CODE" value="<%=ConfigConstants.H5_SEARCH_RESULT_CODE.SUCCESS_CODE%>"/>  
<!DOCTYPE html>
<html>
	<head>
		<title>酒店查询条件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name = "format-detection" content = "telephone=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/calendar.css" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/hotelSearch/hotelSearch.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/date.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
		<style type="text/css">
			.sort{
				width:0.9rem;
				heigth:1.5rem;
			}
			.week{
				display:none;
			}
		</style>
	</head>
	<body>
		<!-- 页面标识 -->
		<input type="hidden" id="hisPage" value="hotelList">
		
		<!-- 首页查询条件 -->
		<input type="hidden" id="homeAbroadFlag" value=""/>
		<input type="hidden" id="tripUserId" value=""/>
		<input type="hidden" id="enterpriseId" value=""/>
		<input type="hidden" id="tripType" value=""/>
		<input type="hidden" id="cityId" value=""/>
		<input type="hidden" id="cityName" value=""/>
		<input type="hidden" id="checkStartDate" value=""/>
		<input type="hidden" id="checkOutDate" value=""/>
		<input type="hidden" id="nightCount" value=""/>
		<input type="hidden" id="adultCount" value=""/>       
		<input type="hidden" id="childCount" value=""/>
		<input type="hidden" id="childAgeDl" value=""/>  
		<input type="hidden" id="roomCount" value=""/>
		  
		<!-- 排序：默认排序 -->  
		<input type="hidden" id="sort" value="0"/>
		<input type="hidden" id="sortWay" value="0"/>
		
		<!-- 快速筛选 ：星级|价格|差旅政策 -->
		<input type="hidden" id="starLvs" value=""/>
		<input type="hidden" id="hotelPrice" value=""/>
		<input type="hidden" id="tripPolicyRemindType" value=""/>
		
		<!-- 分页 -->
		<input type="hidden" id="pageSize" value="10"/>
		<input type="hidden" id="choicePageNum" value="1">
		<input type="hidden" id="agreementPageNum" value="1">
		
		<!--------价格/星级的布局----->
		<div class="bounced startAdd">
			<div class="matter" id="rating">
				<div class="clickLi">
					<div style="display: flex;flex-direction: row;justify-content: space-between;">
						<p class="way-title">价格</p>
						<p class="empty">清空</p>
					</div>
					<div class="jg jg-">
						<p hotelPrice="00000400">400以下</p>
						<p hotelPrice="04010700">401-700</p>
						<p hotelPrice="07011000">701-1000</p>
					</div>
					<div class="jg jg-">
						<p hotelPrice="10011300">1001-1300</p>
						<p hotelPrice="13011800">1301-1800</p>
						<p hotelPrice="18009999">1800以上</p>
					</div>
				</div>
				<div class="clickLi">
					<p class="way-title">星级</p>
					<div class="jg jg-s">
						<p starLv="0,1,2" style="line-height: 1.4rem">二星级以下 /经济型</p>
						<p starLv="3">三星/舒适</p>
						<p starLv="4">四星/高档</p>
						<p starLv="5,6">五星/豪华</p>
					</div>
				</div>
				<div class="sure">
					<p>确<label style="padding:10px;"></label>定</p>
				</div>
			</div>
		</div>
		
		<!-------排序的布局----->
		<div class="bounced typeAdd">
			<div class="matter" id="matter">
				<p class="way-title">排序</p>
				<div class="way-choose">
					<p sort="1" sortWay="0">价格从低到高&nbsp;<img class="sort" src="../hotelimg/asc.png"></p>
					<p sort="1" sortWay="1">价格从高到低&nbsp;<img class="sort" src="../hotelimg/desc.png"></p>
					<p sort="2" sortWay="0">星级从低到高&nbsp;<img class="sort" src="../hotelimg/asc.png"></p>
					<p sort="2" sortWay="1">星级从高到低&nbsp;<img class="sort" src="../hotelimg/desc.png"></p>
				</div>
			</div>
		</div>
		
		<!-------酒店入住和离店插件------->
		<div class="calendar"></div>
		
		<!---------主体内容------>
		<section>
			<header class="nav" id="header">
				<div>
					<img src="../hotelimg/header.png"  class="you">
				</div>
				<div class="inbox">
					<div class="dates">
						<p>
							<span>入住</span><span class="time entertime" id="checkStartDateSpan"></span>
							<input type="text"  class="c-date" id="startDate" style="display: none;">
						</p>
						<p>
							<span>离店</span><span class="time leavetime" id="checkOutDateSpan"></span>
							<input type="text"  class="c-date" id="endDate" style="display: none;">
						</p>
					</div>
					<div class="search" style="display:flex;flex-direction:row; padding-right:0;justify-content:space-around;padding-left:0;">
						<div style="display:flex;flex-direction:row;flex:1">
							<div style="margin:auto 0;">
								<img src="../hotelimg/search.png" />
							</div>
							<div style="flex:1;">
								<input type="text"   id="keyWord"  placeholder="关键词/酒店名称" value=""  style="vertical-align: center"/>
								<input type="hidden" id="keyAssociateValue" value=""/>
							</div>
						</div>
						<div id="clearKey" style="margin-left:1rem;"><img src="../hotelimg/close.jpg" /></div>
					</div>
				</div>
			</header>
			<div id="sort">
				<div class="pStart">
					<span>价格/星级</span>
					<img src="../hotelimg/h-1.png" />
				</div>
				<div class="rank">
					<span>排序</span>
					<img src="../hotelimg/h-1.png" />
				</div>
			</div>
			<div id="feature">
				<div>
					<a href="javascript:;" id="">符合差旅标准</a>
				</div>
			</div>
			<div id="hotelList">
				<ul class="hotel-tab">
					<li class="highlight">
						<a href="javascript:;">精选酒店</a>
					</li>
					<li id="agreementHotelTitle" style="display:none;">
						<a href="javascript:;">协议酒店</a>
					</li>
				</ul>
				<div class="list-content" ></div>
			</div>
		</section>
	</body>
	
	<script type="text/javascript">/**--酒店查询条件--**/
		$(function(){
			getCookieToCommonPage("hotelList");
			searchList("html");
			$("#clearKey").click(function(){
				if($("#keyWord").val()!=""){
					$("#keyWord").val("");
					$("#keyAssociateValue").val("");
					searchList("html");
				}
			});
		});
		
		//酒店入住与酒店日期的插件
		$('.dates').hotelDate();

		//价格/星级低高排序的效果
		$('.rank').click(function() {
			$('.typeAdd').fadeIn();
			$('.typeAdd .matter').animate({
				height: '20rem'
			}, 1000);
		});
		//排序查询：价格|星级
		$('.way-choose p').click(function() {
			$(this).css('color', '#00AFEC').siblings().css('color', '');
			$(".sort").each(function(){
				if($(this).attr("src")=="../hotelimg/selectAsc.png"){
					$(this).attr('src','../hotelimg/asc.png');
				}
				if($(this).attr("src")=="../hotelimg/selectDesc.png"){
					$(this).attr('src','../hotelimg/desc.png');
				}
			});
			$(this).find('img').attr('src', $(this).find('img').attr('src') == '../hotelimg/asc.png' ? '../hotelimg/selectAsc.png' : '../hotelimg/selectDesc.png');
			$('.typeAdd').fadeOut();
			
			$("#sort").val($(this).attr("sort"));
			$("#sortWay").val($(this).attr("sortWay"));
			searchList("html");
		});

		//点击：价格/星级
		$('.pStart').click(function() {
			$('.startAdd').fadeIn();
			
			$('.jg p').removeClass('showLight');
			showLightPriceOrStar("starLvs","starLv","jg-s p");
			showLightPriceOrStar("hotelPrice","hotelPrice","jg- p");
		});
		
		//初始图标点亮：价格|星级
		function showLightPriceOrStar(id,pClassName,divClassName){
			var str = $("#"+id).val();
			var StrArray = [];
			if(typeof(str)!="undefined" && str.indexOf("|")!=-1){
				StrArray = str.split('|');
			}else{
				StrArray.push(str);
			}
			$("."+divClassName).each(function(){
				for(var i=0;i<StrArray.length;i++){
					if($(this).attr(pClassName)==StrArray[i]){
						$(this).addClass('showLight');
					}
				}
			});
		}
		
		//价格单选->点亮图标
		$('.jg- p').click(function() {
			if($(this).hasClass('showLight')){
				$(this).removeClass('showLight');
				return false
			}
			$(this).addClass('showLight').parents('.jg').siblings().find('p').removeClass('showLight');
			$(this).addClass('showLight').siblings().removeClass('showLight');			
			
		});
		//星级多选->点亮图标
		$('.jg-s p').click(function() {
			$(this).toggleClass('showLight');
		});
		
		//确定查询：星级|价格
		$('.sure').click(function() {
			$('.startAdd').fadeOut();
			
			confirmPriceOrStar("starLvs","starLv","jg-s p");
			confirmPriceOrStar("hotelPrice","hotelPrice","jg- p");
			searchList("html");
		});
		
		//确定选择：星级|价格
		function confirmPriceOrStar(id,pClass,divClass){
			var value = "";
			$("."+divClass).each(function(){
				if($(this).attr("class")=="showLight" && $(this).attr(pClass)){
					value += $(this).attr(pClass)+"|";
				}
			});
			if(value!=""){
				value = value.substring(0,value.length-1);
			}
			$("#"+id).val(value);
		}
		
		//清空
		$('.empty').click(function() {
			$('.jg p').removeClass('showLight');
			
		});

		//符合差旅政策筛选
		$('#feature div').click(function(){
			$(this).toggleClass('aC');
			if($(this).attr("class")=="aC"){
				$("#tripPolicyRemindType").val("0000100004");
			}else{
				$("#tripPolicyRemindType").val("");
			}
			searchList("html");
		});

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
		}
		$('.typeAdd').bind('click', function(e) {
			hideLayer(e, 'matter', '#matter');
		});

		$('.startAdd').bind('click', function(e) {
			hideLayer(e, 'rating', '#rating');
		});
		var url = window.location.href; //获取当前页面的url
		var len = url.length; //获取url的长度值
		var a = url.indexOf("?"); //获取第一次出现？的位置下标
		var b = url.substr(a + 1, len); //截取问号之后的内容		
		var c = b.split("&"); //从指定的地方将字符串分割成字符串数组
		var arr = new Array(); //新建一个数组
		for(var i = 0; i < c.length; i++) {
			var d = c[i].split("=")[1]; //从=处将字符串分割成字符串数组,并选择第2个元素
			arr.push(d); //将获取的元素存入到数组中	
		}
		if(c[0].split("=")[1] == undefined){
			$('.search input').val('');			
		}else{
			$('.search input').val(decodeURI(arr[0]))
		}
	</script>
	
	<script type="text/javascript">/**--不同页面交互--**/
		//返回首页
		$('.nav .you').click(function() {
			saveCommonInfoToCookie("hotelList");
			window.location.href = "${pageContext.request.contextPath}/hotel/page.act";
		});
	
		//跳转关键字搜索页
		$('.search input').click(function() {
			saveCommonInfoToCookie("hotelList");
			window.location.href = "${pageContext.request.contextPath}/hotelSearch/keyWordSearchInit.act?hisPage="+$("#hisPage").val();
		});
	</script>
	
	<script type="text/javascript">/**--酒店列表下滑分页加载--**/
		function getDocumentTop() {
		    var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
		    if (document.body) {
		        bodyScrollTop = document.body.scrollTop;
		    }
		    if (document.documentElement) {
		        documentScrollTop = document.documentElement.scrollTop;
		    }
		    scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;    return scrollTop;
		}
			 
		//可视窗口高度
		function getWindowHeight() {
		    var windowHeight = 0;    if (document.compatMode == "CSS1Compat") {
		        windowHeight = document.documentElement.clientHeight;
		    } else {
		        windowHeight = document.body.clientHeight;
		    }
		    return windowHeight;
		}
			 
		//滚动条滚动高度
		function getScrollHeight() {
		    var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
		    if (document.body) {
		        bodyScrollHeight = document.body.scrollHeight;
		    }
		    if (document.documentElement) {
		        documentScrollHeight = document.documentElement.scrollHeight;
		    }
		    scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;    return scrollHeight;
		}

		//滚动条最低端加载
		window.onscroll = function () {
		    if(getScrollHeight() == getWindowHeight() + getDocumentTop()){
		    	$('.hotel-tab li').each(function(){
		    		var title = $.trim($(this).text());
					if(title=="精选酒店"){
						var choicePageNum = $("#choicePageNum").val();
						var choiceHasNextPage = "false";
						$(".choiceHasNextPage").each(function(){
							choiceHasNextPage = $(this).val();
						});
						if(choiceHasNextPage=="true"){
							choicePageNum++;
							$("#choicePageNum").val(choicePageNum);
							searchList("append");
						}
					}else if(title=="协议酒店"){
						var agreementPageNum = $("#agreementPageNum").val();
						var agreementHasNextPage = false;
						$(".agreementHasNextPage").each(function(){
							agreementHasNextPage = $(this).val();
						});
						if(agreementHasNextPage==true){
							agreementPageNum++;
							$("#agreementPageNum").val(agreementPageNum);
							searchList("append");
						}
					}
		    	});
		    }
		}
	</script>
	
	<script type="text/javascript">/**--精选酒店/协议酒店的选项卡效果--**/
		$('.hotel-tab li').click(function() {
			$(this).addClass('highlight').siblings().removeClass('highlight');
			//切换协议or精选酒店列表
			var title = $.trim($(this).text());
			if(title=="精选酒店"){
				$(".choiceHotel_list").css("display","");
				$(".agreementHotel_list").css("display","none");
			}else if(title=="协议酒店"){
				$(".choiceHotel_list").css("display","none");
				$(".agreementHotel_list").css("display","");
			}
		});
	</script>
</html>