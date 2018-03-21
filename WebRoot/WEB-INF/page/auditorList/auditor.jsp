<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>审核人</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/allOrder.css" />
	</head>

	<body>
		<form id="subForm" action="flight/bookAirTicket.act" method="post">
	        <input type="hidden" id="bookinfo" name="bf" value='${bookinfo}' />
	        <input type="hidden" id="id" name="id" value='' />
	        <input type="hidden" id="userName" name="userName" value='' />
	        <input type="hidden" id="userPhone" name="userPhone" value='' />
	        <input type="hidden" id="email" name="email" value='' />
	        
	    </form>
		<section>
			<header>
				<div>
					<img src="../img/header.gif" onclick="back()"/>
				</div>
				<p class="zhixiang">审核人</p>
				<div class="addBtn">
					<img src="../img/addBtn.png" />
				</div>
			</header>
			<div class="table-content" style="margin-top:0.2rem">
				<ul class="passener-content pas-table show">
					 <c:forEach items="${result.data.list }" var="obj">
					<li>
						<div class="left-passener" onclick="xuanze(this)">
							<div class="middle-table">
								<div class="passener-name">
								    <input type="hidden" value="${obj.id}"><%--乘机人id--%>
								    <input type="hidden" value="${obj.name}"><%--乘机人名字--%>
								    <input type="hidden" value="${obj.mobile}"><%--乘机人手机号--%>
								    <input type="hidden" value="${obj.email}"><%--邮箱--%>
								    ${obj.name }
								</div>
								<div class="passener-num">
									<span>手机号</span>
									<span class="number-type">${obj.mobile }</span>
								</div>
								<div class="passener-type">
									<span>邮箱</span>
									<span class="number-type">${obj.email }</span>
								</div>
								<div class="passener-type">
								<!-- 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他 -->
								<span>部门名称</span>
								 <span class="number-type">${obj.departmentName }</span> 
								</div>
							</div>
						</div>
					</li>
						</c:forEach> 
				</ul>
			</div>
		</section>

	</body>
	<script type="text/javascript" src="../libs/jquery.min.js"></script>
	<script type="text/javascript" src="../libs/gublic.js"></script>
	<script type="text/javascript">
	function xuanze(obj){
		var id=$(obj).children().children().children().val();
	    var name=$(obj).children().children().children().next().val();
	    var mobile=$(obj).children().children().children().next().next().val();
	    var email=$(obj).children().children().children().next().next().next().val();
	    
	    $("#id").val(id);
	    $("#userName").val(name);
	    $("#userPhone").val(mobile);
	    $("#email").val(email);
	    $("#subForm").attr("action","<%=path %>/flight/toBookTicket.act");
		 $("#subForm").submit();
	}

	function back(){
	   window.history.back(-1); 
	   }
	</script>
	<!-- <script type="text/javascript">
		/*------选项卡的效果--------*/
		$('.tableBtn').eq(1).on('click', function() {
			$(this).parents('section').find('.addBtn img').addClass('imgShow');
			$(this).addClass('activeTable').siblings().removeClass('activeTable');
			$(this).parent().siblings('.emp-table').addClass('show').siblings().removeClass('show');
		});
		$('.tableBtn').eq(0).on('click', function() {
			$(this).parents('section').find('.addBtn img').removeClass('imgShow');
			$(this).addClass('activeTable').siblings().removeClass('activeTable');
			$(this).parent().siblings('.pas-table').addClass('show').siblings().removeClass('show');
		});
		/*-----------选择和未选择的按钮效果-----*/
		(function($) {
			$(document).ready(function() {
				$('.left-table img').click(function() {
					$(this).attr('src', $(this).attr('src') == '../img/chooseBtn.png' ? '../img/notBtn.png' : '../img/chooseBtn.png');
				});
			})
		})(jQuery);
		/*------请选择证件类型------------*/
		(function($) {
			$(document).ready(function() {
				$('.type-document img').click(function() {
					$(this).attr('src', $(this).attr('src') == '../img/choose.png' ? '../img/wei.png' : '../img/choose.png');
					$(this).parent().siblings().find('img').attr('src', $(this).attr('src') == '../img/choose.png' ? '../img/wei.png' : '../img/choose.png');
				});
			})
		})(jQuery);
		/*---------证件类型的显示和隐藏---------*/
		$('.left-passener').on('click',function(){
			var height = $("body").height();
			$('.bounced').height(height);
			$('.doc-type').animate({
				height:'show'
			},1500);
		});
		$('.doc-btn').on('click',function(){
			var height = $("body").height();
			$('.bounced').height(height);
			$('.doc-type').animate({
				height:'hide'
			},1500);
		});
	</script> -->
	
</html>