<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>关键字搜索</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
	</head>

	<body>
		<section>
			<header class="nav" id="header">
				<div>
					<img src="<%=basePath%>/hotelimg/header.png" class="you">
				</div>
				<div class="inbox" id="out">
					<div class="search" id="ser_box">
						<img src="<%=basePath%>/hotelimg/search.png" onclick="javascript:goback();">
						<input type="text" name="" id="ipt" value="" placeholder="搜索国际城市名称">
					</div>
				</div>
				<div id="s_btn">
					<a href="javascript:;">确定</a>
				</div>
			</header>
			<div id="bot_box">
				<ul id="oul">
				</ul>
			</div>
		</section>
	</body>
	<script type="text/javascript">
		function $(id) {
			return document.getElementById(id);
		}

		var ipt = $("ipt");
		var ser = $("ser_box");
		var bot = $("bot_box");
		var oul = $("oul");

		ipt.oninput = function() {
			var ss = ipt.value;
			//var url = "http://suggestion.baidu.com/su?cb=queryList&wd=" + ss;
			//addScript(url);
		}

		ipt.onfocus = function() {
			var ss = ipt.value;
			var url = "http://suggestion.baidu.com/su?cb=queryList&wd=" + ss;
			addScript(url);

		}

		function queryList(data) {
			ss = document.getElementsByTagName("script")[0];
			document.body.removeChild(ss)

			var arr = data.s;
			oul.innerHTML = "";
			if(arr.length == 0) {
				bot.style.display = "none";
			} else {
				bot.style.display = "block";
			}

			for(var i = 0; i < arr.length; i++) {
				li = document.createElement("li");
				li.innerHTML = arr[i];
				li.onclick = function() {
					var value = this.innerHTML;
					var time = (new Date()).getTime();
					localStorage.setItem(time,value);
					window.location.href = "../hotel-index.html?vub=" + value;
				}
				oul.appendChild(li);
			}
		}

		function addScript(url) {
			var s = document.createElement("script");
			s.src = url;
			s.type = "text/javascript";
			document.body.appendChild(s);
		}
	</script>
	<script type="text/javascript" src="js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
		$("#s_btn").click(function() {
			var value = $("#ipt").val();
			var time = (new Date()).getTime();			
			if(!value) {
				alert("请输入搜索内容");
				return false;
			}
			window.location.href = "../hotel-index.html?vub=" + value;	

		});
		/*---------返回上一页--------*/
		$('.nav .you').click(function(){
			history.go(-1);
		});
	</script>
	</html>