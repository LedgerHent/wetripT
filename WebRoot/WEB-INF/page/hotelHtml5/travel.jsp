<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>差旅标准</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
	</head>

	<body>
		<section>
			<header class="travel_title">
				<div class="top_m">
					<div>
						<img src="<%=basePath%>/hotelimg/header.png" / class="you" onclick="dd()">
					</div>
					<p>差旅标准</p>
				</div>
				<div class="bto_m">
					<div class="search">
						<div style="margin: auto 0;">
							<img src="<%=basePath%>/hotelimg/search.png" />
						</div>
						<div style="margin:auto 0;">
							<input type="text" name="" id="message" value=""  placeholder="查找其他人员信息"/ style="vertical-align: middle">
						</div>
					</div>
					<div class="self">
						<div>
							<img src="<%=basePath%>/hotelimg/icon_.png"/ style="width: 1.5rem; height: 1.5rem;">
						</div>
						<p>本人</p>
					</div>
				</div>
			</header>
		
			<ul id="travel_list">
				<li class="nape">
					<p class="city_title">国内港澳台酒店</p>
					<ul class="nape_">
				
						<li>
							<span class="width">城市</span>
							<span>住宿金额/酒店星级上限标准</span>
						</li>
				<c:if test="${rule!=null }">		
				<c:forEach items="${rule.h}" var="my">
						<li>
							<span class="width">
							<c:if test="${fn:contains(my.recKey,'1') }">一级</c:if>
							<c:if test="${fn:contains(my.recKey,'2') }">二级</c:if> 
							<c:if test="${fn:contains(my.recKey,'3') }">三级</c:if> 
							<c:if test="${fn:contains(my.recKey,'4') }">四级</c:if> 
							<c:if test="${fn:contains(my.recKey,'5') }">五级</c:if> 
							<c:if test="${fn:contains(my.recKey,'6') }">六级</c:if> 
							<c:if test="${fn:contains(my.recKey,'7') }">七级</c:if> 
							<c:if test="${fn:contains(my.recKey,'8') }">八级</c:if> 
							<c:if test="${fn:contains(my.recKey,'9') }">九级</c:if> 
							<c:if test="${fn:contains(my.recKey,'10') }">十级</c:if> 
							 
							</span>
							<span>${my.topPrice}元/
							<c:if test="${fn:contains(my.topLv,'5') }">五星级</c:if> 
							<c:if test="${fn:contains(my.topLv,'4') }">四星级</c:if>
							<c:if test="${fn:contains(my.topLv,'3') }">三星级</c:if>
							<c:if test="${fn:contains(my.topLv,'2') }">二星级</c:if>
							<c:if test="${fn:contains(my.topLv,'1') }">一星级</c:if>
							<c:if test="${fn:contains(my.topLv,'0') }">无星级</c:if>
							<c:if test="${fn:contains(my.topLv,'99') }">不限</c:if>
							</span>
						</li>
				</c:forEach>
				</c:if>
				
				
					</ul>
				</li>
				<li class="nape">
					<p class="city_title">海外酒店</p>
					<ul class="nape_">
						<li>
							<span class="width">洲</span>
							<span>住宿金额上限标准</span>
						</li>
						<c:if test="${rule!=null }">
						<c:forEach items="${rule.a}" var="ruleb">
						<li>
							<span class="width">
								<c:if test="${fn:contains(ruleb.recKey,'0') }">所在大洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'1') }">亚洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'2') }">欧洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'3') }">非洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'4') }">大洋洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'5') }">南美洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'6') }">北美洲</c:if>
								<c:if test="${fn:contains(ruleb.recKey,'7') }">南极洲</c:if>
							</span>
							<span>${ruleb.topPrice }元</span>
						</li>
						</c:forEach>
						</c:if>
					</ul>
				</li>
			</ul>
			<p id="tips">没有匹配的差旅标准，请联系企业管理员维护差旅标准</p>
		</section>
	</body>
	<script type="text/javascript" src="js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
	$('#message').focus();
	$(function(){
		if('${rule}'==null||'${rule}'==''||'${rule}'=='{}'){
			$('#travel_list').hide();
			$('#tips').show();
		}
	});
		var url = window.location.href; //获取当前页面的url
		var len = url.length; //获取url的长度值
		var a = url.indexOf("?"); //获取第一次出现？的位置下标
		var b = url.substr(a + 1, len); //截取问号之后的内容		
		var c = b.split("&"); //从指定的地方将字符串分割成字符串数组
		var arr = new Array(); //新建一个数组
		for(var i = 0; i < c.length; i++) {
			var d = c[i].split("=")[1]; //从=处将字符串分割成字符串数组,并选择第2个元素
			//console.log(d)
			arr.push(d); //将获取的元素存入到数组中
		}
		if(c[0].split("=")[1] == undefined){
			$('.search input').val('');
		}else{
			$('.search input').val(decodeURI(arr[0])).css('color','#333333');
		}
		
		if($('.search input').val() == "朱亚文 - 技术部"){
			$('#travel_list').css('display','none');
			$('#tips').css('display','block')
		}
		
		$('.search input').click(function(){
			window.location.href = "hotel/traveler.act"
		});
		
		/*---------返回上一页--------*/
		
		$('.self').click(function(){
			if('${rule}'==null||'${rule}'==''){
				$('#travel_list').hide();
				$('#tips').show();
			}else{
			$('#travel_list').css('display','block');
			$('#tips').css('display','none');
			$('.search input').val('本人姓名');
			window.location.href = "hotel/travel.act"
			}
		})
		function dd(){
			window.location.href = "${pageContext.request.contextPath}/hotel/page.act";
		}
	</script>
</html>