<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
	    <base href="<%=basePath%>">
		<%--<meta charset="UTF-8">
		--%><title>每间人数</title>
		<meta name="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
	</head>

	<body>
		<input type="hidden" id="adultCount" value="2">
		<input type="hidden" id="childCount" value="">
		<input type="hidden" id="childAgeDl" value="">
		<input type="hidden" id="roomCount" value="1">
		<section>
			<!-------儿童年龄-------->
			<div class="bounced ageAdd">
				<div class="choose-age">
					<p class="tle">儿童年龄</p>
					<ul class="ageItem">
						<li>
							<p><2岁</p>
							<p>2岁</p>
							<p>3岁</p>
							<p>4岁</p>
							<p>5岁</p>
							<p>6岁</p>
						</li>
						<li>
							
							<p>7岁</p>
							<p>8岁</p>
							<p>9岁</p>
							<p>10岁</p>
							<p>11岁</p>
							<p>12岁</p>
						</li>
						<li>
							
							<p>13岁</p>
							<p>14岁</p>
							<p>15岁</p>
							<p>16岁</p>
							<p>17岁</p>
							<p style="border:none;background:0;" class="disable" name="agew"></p>
						</li>
					</ul>
				</div>
			</div>
			<!---------每间人数的布局------->
			<div id="page">
				<header id="headers" class="header">
					<div>
						<img src="<%=basePath%>/hotelimg/header.png" / class="you" onclick="history.back();">
					</div>
					<p>每间人数</p>
				</header>
				<div class="rooms">
					<p>房间数</p>
					<div class="compute">
						<div class="minus operation" >－</div>
						<div class="amount">${room}</div>
						<div class="plus operation"   onclick="this.style.color='red';">＋</div>
					</div>
				</div>
				<div class="each">
					<div class="each-title">每间成人数和儿童数</div>
					<div class="adult">
						<p>成人</p>
						<div class="compute">
							<div class="minus operation">－</div>
							<div class="amount">${audlt}</div>
							<div class="plus operation">＋</div>
						</div>
					</div>
					<div class="child">
						<p>儿童</p>
						<div class="compute">
							<div class="minus operation">－</div>
							<div class="amount">${child}</div>
							<div class="plus operation">＋</div>
						</div>
					</div>
					<div id="age">
						<div class="age-title">儿童年龄</div>
						<ul class="total">
							<!--<li>
								<p>儿童</p>
								<div class="select">
									<span class="age">请选择</span>
									<img src="<%=basePath%>/hotelimg/you.png" />
								</div>
							</li>-->
						</ul>
					</div>
				</div>
				<div class="keep">
					<p>确&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</p>
				</div>
			</div>
		</section>
		
		
		
		<form id="subform" action="hotel/returnpage.act"  method="post" >
			<input  id="finalage" name="finalage" value="" type="hidden"/>
			<input  id="room" name="room" value="" type="hidden"/>
			<input  id="cont" name="cont" value="" type="hidden"/>
			<input  id="id" name="id" value="" type="hidden"/>
		</form>
	</body>
	<script type="text/javascript" src=" js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="js/hotelHtml5/hotelSearch/hotelSearch.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript">
		/*--------儿童年龄创建元素的效果-------*/
		
		var $obj;
		$('body').on('click','.total li',function(){
			$('.bounced').fadeIn();
			$obj = $(this);
		})
		$('.ageItem p').click(function() {
			$(this).css('background', '#00afec');
			$(this).css('color', '#FFFFFF');
			$('.bounced').fadeOut();
			$(this).css('background','');
			$(this).css('color','');
			$obj.find('.age').html($(this).html()).css('color', '#333333');
		});
		
		var num = $('.rooms .amount').html();
		/*------------每间人数页面的效果-----------*/
		$('.rooms .minus').click(function() {
			num--;
			if(num < 1) {
				num = 1;
				return false;
			}
			$('.rooms .amount').html(num);
		});
		$('.rooms .plus').click(function() {

			num++;
			if(num > 8) {
				num = 8;
				return false;
			}
			$('.rooms .amount').html(num);
		});

		/*----------------成人的时间效果-------*/
		var nums = $('.adult .amount').html();
		/*------------每间人数页面的效果-----------*/
		$('.adult .minus').click(function() {
			nums--;
			if(nums < 1) {
				nums = 1;
				return false;
			}
			$('.adult .amount').html(nums);
		});
		$('.adult .plus').click(function() {
			nums++;
			if(nums > 8) {
				nums = 8;
				return false;
			}
			$('.adult .amount').html(nums);
		});

		var numd = $('.child .amount').html();
	
		/*------------每间人数页面的效果-----------*/
		$('.child .minus').click(function() {
			numd--;
			if(numd < 0) {
				numd = 0;
				return false;
			}
			$('.child .amount').html(numd);
			if(numd == 0){
				$('#age').hide();
				$('#age .total').empty();
				return false;
			}
			$('.total li:last').remove();
			

		});
		$(function(){
			if(numd=='0'){
				$('#age').hide();
			}else{
				$('#age').show();
			}
		})
		/*儿童年龄 start*/
		$('.child .plus').click(function() {
			numd++;			
			if(numd > 3) {
				numd = 3;
				return false;
			}
			$('.child .amount').html(numd);
			if(numd != 0){
				$('#age').show();
			}
			if(numd=='1'){
				var str = '<li><p>儿童1</p><div class="select"><span class="age">请选择</span><img src="<%=basePath%>/hotelimg/you.png" /></div></li>';
				$('#age .total').append(str);
			}
			if(numd=='2'){
				var str = '<li><p>儿童2</p><div class="select"><span class="age">请选择</span><img src="<%=basePath%>/hotelimg/you.png" /></div></li>';
				$('#age .total').append(str);
			}
			if(numd=='3'){
				var str = '<li><p>儿童3</p><div class="select"><span class="age">请选择</span><img src="<%=basePath%>/hotelimg/you.png" /></div></li>';
				$('#age .total').append(str);
			}
		});
		var age="";
		var arr;
		for (var i=1;i<=numd;i++){
			var str = '<li><p>儿童'+i+'</p><div class="select"><span class="age">请选择</span><img src="<%=basePath%>/hotelimg/you.png"/></div></li>';
			$('#age .total').append(str);
		}
		/*end*/
		
		$(function(){
			var childage='${age}';
			if(childage!=''){
				var ss=childage.replace(/\|/g, ",");
			    arr = ss.split(",")
			  	var size=  $('.total li').find('.age').size();
			    for (var i=0;i<size;i++){
					$('.total li').find('.age').eq(i).html(arr[i]);
				}
			}
		})
		/*提示信息*/
	function showMessage(meg){
		$(this).MyConfirm({
			content:meg
		});
	}
	$('.keep').click(function(){
		var rooms = $('.rooms .amount').html();
		var adult = $('.adult .amount').html();
		var child = $('.child .amount').html();
		var flag=0;
		var agee="";
		$('.total li').each(function(){
			if($(this).find('.age').html() == "请选择"){
				flag=1;
			}else{
				agee=agee+$(this).find('.age').html()+"|";
			}
		})
		if(flag==1){
			showMessage("请选择儿童年龄！");
			return false;
		}
		var finalag=agee.substring(0,agee.length-1);
		$("#finalage").val(finalag);
		$("#room").val(rooms);
		$("#cont").val(adult);
		$("#id").val(child);
		
		var reg = /[^\d|]/g;
		$("#adultCount").val(adult);
		$("#childCount").val(child);
		$("#roomCount").val(rooms);
		$("#childAgeDl").val(finalag.replace(reg, ""));
		saveCommonInfoToCookie('index');
		$("#subform").submit();
	});
	
	</script>

</html>