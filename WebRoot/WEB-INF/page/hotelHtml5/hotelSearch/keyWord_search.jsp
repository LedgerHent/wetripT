<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>关键字搜索</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/hotelSearch/hotelSearch.js"></script>
	</head>
	<body>
		<!-- 页面标识 -->
		<input type="hidden" id="hisPage" value="${hisPage }">
		<input type="hidden" id="cityId" value=""/>
		<input type="hidden" id="cityName" value=""/>
		<input type="hidden" id="homeAbroadFlag" value=""/>
		<section>
			<header class="nav" id="header">
				<div>
					<img src="../hotelimg/header.png" class="you">
				</div>
				<div class="inbox" id="out" style="padding-right:0;">
					<div class="search" id="ser_box" style="display:flex;flex-direction:row;justify-content:space-between;padding-left:0;">
						<div style="display:flex;flex-direction:row;flex:1">
							<div style="margin: auto 0;"><img src="../hotelimg/search.png"></div>
							<div style="flex:1;">
								<input type="text"   id="keyWord" placeholder="关键词/酒店名称" value="" style="vertical-align: center;">
								<input type="hidden" id="keyAssociateValue" style="vertical-align: middle;">
							</div>
						</div>
						<div id="clearKey" style="margin-left:1rem;"><img src="../hotelimg/close.jpg" /></div>
					</div>
				</div>
				<div id="s_btn">
					<a href="javascript:;">确定</a>
				</div>
			</header>
			<div id="bot_box">
				<ul id="oul"></ul>
			</div>
			<div id="history">
				<p style="margin: 1rem 0;text-indent: 1rem;">搜索历史</p>
				<div class="delete history" style="width: 100%;">
					<div class="word-break" id="" style="z-index: 1000;"></div>
				</div>
				<div class="history" id="his-dele" style="text-align: center;">清除记录</div>
			</div>
		</section>
	</body>
	<script type="text/javascript">
		function $(id) {
			return document.getElementById(id);
		}

		var keyWord = $("keyWord");
		var ser = $("ser_box");
		var bot = $("bot_box");
		var oul = $("oul");

		keyWord.oninput = function() {
			var content = keyWord.value;
			keyWordSearch(content);
		}

		keyWord.onfocus = function() {
			var content = keyWord.value;
			keyWordSearch(content);
		}
		//关键字联想
		function keyWordSearch(content){
			var cityId = $("#cityId").val();
			var cityName = $("#cityName").val();
			 if(cityName.indexOf(",") > 0){
				cityName = cityName.split(",")[0];
			 }
			var homeAbroadFlag = $("#homeAbroadFlag").val();
			$.ajax({
				type:"POST",
				data:{
					"cityId":cityId,
					"cityName":cityName,
					"homeAbroadFlag":homeAbroadFlag,
					"content":content,
					"poiType":"9"
				},
				url:"${pageContext.request.contextPath}/hotelSearch/keyWordSearchList.act",
				success:function(data){
					var arr = data.busiData;
					oul.innerHTML = "";
					if(arr.length == 0) {
						bot.style.display = "none";
					} else {
						bot.style.display = "block";
					}
					for(var i = 0; i < arr.length; i++) {
						li = document.createElement("li");
						li.innerHTML = arr[i].label;
						li.setAttribute("keyAssociateValue", arr[i].value);
						oul.appendChild(li);
						
						//li点击事件：保存localStorage|保存cookie|跳转页面
						li.onclick = function() {
							//缓存搜索历史
							var value = this.innerHTML;
							var time = (new Date()).getTime();
							var hisValue = value+"|"+$(this).attr("keyAssociateValue");
							localStorage.setItem(time,hisValue);
							
							//选中关键字li
							selectKeyWord($(this));
						}
					}
				},
				error: function (xhr, status, error) {
					
			    }
			});
		}
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<script type="text/javascript">		
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
			if(localStorage.length <=0){
				$("#history").hide();
			}else{
				$("#history").show();
			}
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
				var key = hisItem[i];
				if(key!=""&& key.indexOf("|")!=-1){
					var keyWord = key.split("|")[0];
					var keyAssociateValue = key.split("|")[1];
				}else{
					var keyWord = key;
					var keyAssociateValue = "";
				}
				$(".delete").prepend('<div class="word-break" id=""style=" z-index: 1000;" keyAssociateValue="'+keyAssociateValue+'">' + keyWord + '</div>')
			}
		}
		init();
		
		//确定按钮
		$("#s_btn").click(function() {
			var value = $("#keyWord").val();
			var time = (new Date()).getTime();			
			if(!value) {
				$("#keyAssociateValue").val("");
			}
			//缓存酒店查询条件
			saveCommonInfoToCookie("hotelList");
			if($("#hisPage").val()=="hotelList"){
				window.location.href = "${pageContext.request.contextPath}/hotelSearch/init.act";
			}else{
				//返回首页处理
				window.location.href = "${pageContext.request.contextPath}/hotel/page.act";
			}
			if(value){
				//输入的内容localStorage有记录
				if($.inArray(value, hisItem) >= 0) {
					for(var j = 0; j < localStorage.length; j++) {
						if(value == localStorage.getItem(localStorage.key(j))) {
							localStorage.removeItem(localStorage.key(j));
						}
					}
					localStorage.setItem(time, value);				
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
			}
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
			selectKeyWord($(this));
		})
		//返回上一页
		$('.nav .you').click(function(){
			if($("#hisPage").val()=="hotelList"){
				window.location.href = "${pageContext.request.contextPath}/hotelSearch/init.act";
			}else{
				//返回首页处理
				window.location.href = "${pageContext.request.contextPath}/hotel/page.act";
			}
		});
		
		function selectKeyWord(obj){
			var text = obj.html();
			var value = obj.attr("keyAssociateValue");
			$("#keyWord").val(text);
			$("#keyAssociateValue").val(value);
			//保存cookie|跳转页面
			saveCommonInfoToCookie("hotelList");
			if($("#hisPage").val()=="hotelList"){
				window.location.href = "${pageContext.request.contextPath}/hotelSearch/init.act";
			}else{
				//返回首页处理
				window.location.href = "${pageContext.request.contextPath}/hotel/page.act";
			}
		}
	 $("body").children().click(function () {
			
	      //这里不要写任何代码
	
	}); 
	 $('#keyWord').focus();
	</script>
	<script type="text/javascript">
		$(function(){
			getCookieToCommonPage("hotelList");
			$("#clearKey").click(function(){
			    if($("#keyWord").val()!=""){
					$("#keyWord").val("");
					$("#keyAssociateValue").val("");
				}
			});
		})
	</script>
</html>