<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<img src="<%=basePath%>/hotelimg/header.png" class="you" onclick="history.back();">
				</div>
				<div class="inbox" id="out">
					<div class="search" id="ser_box">
						<img src="<%=basePath%>/hotelimg/search.png">
						<input type="text" name="" id="ipt" value="" placeholder="关键词/酒店名称" >
					</div>
				</div>
				<div id="s_btn">
					<a href="javascript:;" >确定</a>
				</div>
			</header>
			<div id="bot_box">
				<ul id="oul">
				<%--<c:forEach items="${result}" var="key" varStatus="cou">
				<li onclick="sureKey()" id="key${cou.count }">${key.label}</li>
				</c:forEach>
				--%></ul>
			</div>
			<div id="history">
				<p style="margin: 1rem 0;text-indent: 1rem;">搜索历史</p>
				<div class="delete history" style="width: 100%;">
					<div class="word-break" id="" style=" z-index: 1000;"></div>
				</div>
				<div class="history" id="his-dele" style="text-align: center;">清除记录</div>
			</div>
		</section>
		<form action="hotel/returnpage.act" id="kform" method="post">
			<input id="keyw" name="keyw" value="" type="hidden">
			<input id="keyAssociateValue" name="keyAssociateValue" value="" type="hidden">
		</form>
		<form action="hotel/search.act" id="sform" method="post">
			<input id="content" name="content" value="" type="hidden">
			<input id="cityid" name="cityid" value="" type="hidden">
			<input id="cityname" name="cityname" value="" type="hidden">
			<input id="homeabroadflag" name="homeabroadflag" value="" type="hidden">
		</form>
	</body>
	
	<script type="text/javascript">
		function $(id) {
			return document.getElementById(id);
		}

		var ipt = $("ipt");
		var ser = $("ser_box");
		var bot = $("bot_box");
		var oul = $("oul");
		var base = "${applicationScope.ctx}";
		ipt.oninput = function() {
			var ss = ipt.value;
			var cityname=$.cookie("cityName");
			var type=$.cookie("homeabroadflag");
			$.ajax({
			    type: 'POST',
			    url:base+"/hotel/search.act",
			    data:{"content":ss,"cityname":cityname,"homeabroadflag":type},
			    async:false,
			    success: function(result) {
			    	var json = eval("("+result+")"); 
			     if(json!==null){
					for(var i=0; i<json.length; i++){
						var city=json[i].label;
						var cityid=json[i].value;
			   			var str="<li onclick=sureKey(); keyAssociateValue="+cityid+">"+city+"</li>";
						$("#oul").append(str);
					}
			   		}
	   			 }
					});
		}

		
		function sureKey(){
			$('body').on('click','#oul li',function(){
				var ss=$(this).html();
				var keyAssociateValue=$(this).attr('keyAssociateValue');
				if(ss!=''){
					$('#ipt').val(ss);
					$('#keyAssociateValue').val(keyAssociateValue);
					$("#keyw").val(ss);
					$("#kform").submit();
				}
			})
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
					$("#keyw").val(value);
					$("#kform").submit();
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
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript">	
	if(localStorage.length <=0){
				$("#history").hide();
			}else{
				$("#history").show();
			}
    
		/*搜索记录相关*/
		//从localStorage获取搜索时间的数组
		var hisTime;
		//从localStorage获取搜索内容的数组
		var hisItem;
		//从localStorage获取最早的1个搜索时间
		var firstKey;
		function init() {
			//每次执行都把2个数组置空
			hisTime = [];
			hisItem = [];
			//模拟localStorage本来有的记录
			//localStorage.setItem("a",12333);
			//本函数内的两个for循环用到的变量
			var i = 0
			for(; i < localStorage.length; i++) {
				if(!isNaN(localStorage.key(i))) {
					hisItem.push(localStorage.getItem(localStorage.key(i)));
					hisTime.push(localStorage.key(i));
				}
			}
			i = 0;
			//执行init(),每次清空之前添加的节点
			$(".delete").html("");
			for(; i < hisItem.length; i++) {
				//alert(hisItem);
				$(".delete").prepend('<div class="word-break" id=""style=" z-index: 1000;">' + hisItem[i] + '</div>')
			}
		}
		init();
		$("#s_btn").click(function() {
			var value = $("#ipt").val();
			var time = (new Date()).getTime();			
			if(!value) {
				alert("请输入搜索内容");
				return false;
			}
			$("#keyw").val(value);
			$("#kform").submit();
			//输入的内容localStorage有记录
			if($.inArray(value, hisItem) >= 0) {
				for(var j = 0; j < localStorage.length; j++) {
					if(value == localStorage.getItem(localStorage.key(j))) {
						localStorage.removeItem(localStorage.key(j));
					}
				}
				localStorage.setItem(time, value);	
				localStorage.setHistoryItems(time, value);				
			}
			//输入的内容localStorage没有记录
			else {
				//由于限制了只能有6条记录，所以这里进行判断
				if(hisItem.length > 10) {
					firstKey = hisTime[0]
					localStorage.removeItem(firstKey);
					localStorage.setItem(time, value);
				} else {
					localStorage.setItem(time, value)
				}
			}
			init();
			//正式的时候要提交的！！！
			//$("#form1").submit()

		});

		//清除记录功能
		$("#his-dele").click(function() {
			var f = 0;
			for(; f < hisTime.length; f++) {
				localStorage.removeItem(hisTime[f]);
			}
			init();
			$('#history').css('display','none')
		});
		//苹果手机不兼容出现input无法取值以下是解决方法
		$(function() {
			$('.word-break').click(function() {
				var div = $(this).text();
				$('#idNumber1').val(div);
			})
			//取到值以后button存储无法取值，这里强迫浏览器强行刷新可解决
			$('#search').click(function() {
				window.location.reload();
			})
		})
		
		$('body').on('click','.word-break',function(){
			$(this).css({
				'background':'#00AFEC',
				'color':'#ffffff'
			});
			var value = $(this).html();
			$("#keyw").val(value);
			$("#kform").submit();
		})
		/*---------返回上一页--------*/
		$('.nav .you').click(function() {
			history.go(-1);
		});
	</script>

</html>