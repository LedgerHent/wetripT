<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
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
					<div class="search" id="ser_box" style="display: flex;flex-direction:row;">
						<div style="margin: auto 0;">
							<img src="../hotelimg/search.png">
						</div>
						<div>
							<input type="text" name="" id="ipt" value="" placeholder="搜索城市名称" style="vertical-align:middle;">
						</div>
					</div>
					<ul style="width:100%;position: absolute;z-index:99990;background:#FFFFFF;left:0;top:5rem; display:none;" class="searchu">
				</ul>
				</div>
				<%--<div id="s_btn">
					<a href="javascript:;">确定</a>
				</div>
			--%></header>
			<div id="bot_box">
				<ul id="oul">
					
				</ul>
			</div>
		</section>
		<form action="returnpage.act" id="cform" method="post">
			<input id="cityname" name="cityname" type="hidden" value="">
			<input id="cityid" name="cityid" type="hidden" value="">
			<input id="pagee" name="pagee" type="hidden" value="">
		</form>
	</body>
	<script type="text/javascript" src="<%=basePath%>/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript">		
		//模糊搜索城市
		ipt.oninput = function() {
			var ss = ipt.value;
			$(".searchu").empty();
			//$(".searchu").children("li").remove();
			//var country=$(".tab-item a").html();//国内外标识
			var country='${country}';//国内外标识
			var flag="city";
			$.ajax({
			    type: 'POST',
			    url:"${applicationScope.ctx}/hotel/search.act",
			    data:{"content":ss,"homeabroadflag":country,"flag":flag},
			    async:false,
			    success: function(result) {
			    	var json = eval(result); 
			     if(json!=null){
					for(var i=0; i<json.length; i++){
						var city=json[i].label;
						var cityid=json[i].value;
			   			var str="<li style=\"height:4rem;line-height:4rem;border-bottom:0.01rem solid #cccccc;text-indent:1rem;font-size:1.4rem;\" class=\"country\" cityId="+cityid+">"+city+"</li>";
						$(".searchu").append(str);
						$(".searchu").show();
						$(".hiddens").hide();
					}
					
			   		}
	   			 }
					});
		}
		
		
		/*提示信息*/
		function showMessage(meg){
			$(this).MyConfirm({
				content:meg
			});
		}
		$("#s_btn").click(function() {
			var value = $("#ipt").val();
			var time = (new Date()).getTime();			
			if(!value) {
				showMessage("请输入搜索内容");
				return false;
			}
			window.location.href = "../hotel-index.html?vub=" + value;	

		});
		$('body').on('click','.searchu .country',function(){
		//$('#cityName').val($(this).html());
		//$('#cityId').val($(this).attr('cityId'));
		var cityname=$(this).html();
		var cityid=$(this).attr('cityId');
		var pagee="cityp";
		$('#cityname').val(cityname);
		$('#cityid').val(cityid);
		$('#pagee').val(pagee);
		$('#cform').submit();
		//window.location.href ="returnpage.act?cityname="+cityname+"&cityid="+cityid+"&pagee="+pagee;	
		//$(this).parents('.searchu').css('display','none');
		//$('.city_section ').addClass('displayNONE');
		//$('.hiddens').show();
		
	});
		/*---------返回上一页--------*/
		$('.nav .you').click(function(){
			history.go(-1);
		});
		$('#ipt').focus();
		 $("body").children().click(function () {});
	</script>
	<script type="text/javascript">
		
	</script>
	</html>