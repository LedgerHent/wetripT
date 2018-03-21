<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<style>
		#screening{width:100%; position: absolute;left:0;top:4rem;background:#ffffff;text-indent:1rem;z-index:999999991}
		.screen-choose p{line-height: 4rem;font-size: 1.5rem;border-top: 0.1rem solid #CCCCCC;cursor: pointer;color:#333333;text-align:left;}
		.batch{display:flex;flex-direction:row;justify-content:space-between;padding:0 1rem;text-align:center;background:#ffffff;height:3.7rem;line-height:3.4rem;}
		.batch li{flex:1;}
		.batch .highlight{border-bottom: 0.25rem solid #00AFEC;}
		.batch .highlight a{color: #00AFEC;}
		.batch a{color:#333333;font-size:1.6rem;}
		
	</style>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/common.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript">
		var userId = '${userId}';
		var pageSize = '10';
		var pageNum = '1';
		//searchType 1/全部订单; 2/待审核订单
		var searchType = '${searchType }';
		var orderState = '${orderState }';
		var apState = '${apState }';
		
		var hasNextPage = '';
		function setHasNetPage(flag){
			hasNextPage = flag;
		}
	</script>
</head>

<body>
<!--筛选的条件  -->
<div class="bounced amount_">
	<div id="matter"></div>
</div>
<section>
	<header class="header" style="background:#00afec; position:relative; z-index:9999999999;">
		<div style="display:flex;flex-direction:row;padding:0 1rem;">
			<div>
				<img src="../hotelimg/header.png" / class="you">
			</div>
			<c:choose>
				<c:when test="${searchType eq 1 }">
					<p>酒店订单</p>
					<div class="screening">
						<a href="javascript:void(0);" style="font-size:1.8rem; color:#ffffff;">筛选</a>
					</div>
				</c:when>
				<c:otherwise>
					<p>酒店待批</p>
				</c:otherwise>
			</c:choose>
		</div>	
		<c:choose>
			<c:when test="${searchType eq 1 }">
				<div id="screening" style="display:none;">
					<div class="screen-choose">
						<p id="" class="screen_state" onclick="reloadScreen('')">全部</p>
						<p id="0000400001" class="screen_state" onclick="reloadScreen('0000400001')">待审核</p>
						<p id="0000400002" class="screen_state" onclick="reloadScreen('0000400002')">待支付</p>
						<p id="0000400003" class="screen_state" onclick="reloadScreen('0000400003')">待确认</p>
						<p id="0000400004" class="screen_state" onclick="reloadScreen('0000400004')">订单完成</p>
						<p id="0000400005" class="screen_state" onclick="reloadScreen('0000400005')">订单取消</p>
						<p id="0000400015" class="screen_state" onclick="reloadScreen('0000400015')">未出行订单</p>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<!-- 我的待批/我的已批 -->
				<ul style="" class="batch">
					<li class="highlight">
						<a href="javascript:void(0);" onclick="reloadScreen('0')">我的待批</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="reloadScreen('2')">我的已批</a>
					</li>
				</ul>
			</c:otherwise>
		</c:choose>	
		
	</header>
	<ul id="order_list"></ul>
</section>
<script type="text/javascript">

/* 筛选的效果时间 */
var flag = 0;
	$('.screening').click(function(){
		if(flag == 0){
			$('.bounced').fadeIn();
			flag = 1;
			$('#screening').slideDown();
			
			return false;
		}
		
		if(flag == 1){
			$('.bounced').fadeOut();
			flag = 0;
			$('#screening').slideUp();
			
			return false;
		}
	});
	
	/**
	*	筛选--(全部订单库)
	*/
	function reloadScreen(state){
		$('.bounced').fadeOut();
		flag = 0;
		$('#screening').slideUp();
		$('body,html').css({ 'overflow': "" });
		pageNum = '1';
		pageSize = '10';
		if(searchType == 1){
			orderState = state;
		}else{
			apState = state;
		}
		listOrder(userId, searchType, state, pageNum, pageSize, false);
	}

	$(function(){
		init(false);
	});
	
	function init(initFlag){
		if(searchType == 1){
			listOrder(userId, searchType, orderState, pageNum, pageSize, initFlag);
		}else if(searchType == 2){
			listOrder(userId, searchType, apState, pageNum, pageSize, initFlag);
		}
	}
	
	/**
	*	加载页面
	*   flag 判断是append()还是html()	true为append()
	*   searchType 1:全部订单（state：orderState）;  2:待审批（已审批）订单（state：apState）
	**/
	function listOrder(userId, searchType, state, pageNum, pageSize, flag){
		$(this).LoadingShow();
		var url = base_path + '/orderInfo/listOrderInfo.act';
		var data = {"userId": userId, "searchType": searchType, "orderState": state, "pageNum": pageNum, "pageSize": pageSize};
		if(searchType == '2'){
			url = base_path + '/orderInfo/listApprovalPendingOrder.act';
			data = {"userId": userId, "searchType": searchType, "apState": state, "pageNum": pageNum, "pageSize": pageSize};
		}
		$.ajax({
			type: 'post',
			url: url,
			data: data,
			success: function(resp){
				$(this).LoadingHide();
				if(flag){
					$("#order_list").append(resp);
				}else{
					$("#order_list").html(resp);
				}
			}
		});
	}
	
	/**
	* 跳转详情页面
	*/
	function toOrderDetail(orderNo){
	 	var url = base_path + 'hotelOrder/orderpage.act?flag=' + searchType + '&orderNo=' + orderNo;
		window.location.href = url; 
	}
	
	/*-------倒计时的效果----*/
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
			$('.countdown .time_').html(p(minute) + ":" + p(second));
		}
	});
	/*---------补零---------*/
	function p(s) {
		return s < 10 ? '0' + s : s;
	};
	
	$('.enter_right .audit').click(function(e){
		e.stopPropagation();
		$('.proces').fadeIn();
	});
	
	$('.batch li').click(function(){
		$(this).addClass('highlight').siblings().removeClass('highlight');
	})
	
	/**=============================================================================================================================**/
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
	    	if('true' == hasNextPage){
		    	pageNum = parseInt(pageNum);
		    	pageNum++;
		    	init(true);
	    	}
	    }
    };
	
	$('.header .you').click(function() {
		//window.location.href = "${pageContext.request.contextPath}/hotel/page.act";
		history.go(-1);
	});
</script>
</body>
</html>