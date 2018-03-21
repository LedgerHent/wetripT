<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>搜索</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/swiper.min.css" />
	</head>

	<body>
		<header class="nav" id="header">
			<div>
				<img src="<%=basePath%>/hotelimg/header.png" class="you">
			</div>
			<div class="inbox" id="out">
				<div class="search" id="ser_box" style="display:flex;flex-direction:row">
					<div style="margin:auto 0;">
						<img src="<%=basePath%>/hotelimg/search.png">
					</div>
					<div style="width:80%">
					<input type="text" name="" id="myInput" value=""  placeholder="查找其他人员信息" style="vertical-align: middle;">
					</div>
				</div>
			</div>
			<%--<div id="s_btn">
				<a href="javascript:;">确定</a>
			</div>
		--%></header>
		<form action="hotel/travel.act" id="tform" method="post">
		<input id="name" type="hidden" value="" name="name">
		<input id="userId" type="hidden" value="" name="userId">
		</form>
		<table id="myTable">
		</table>
		<script type="text/javascript" src="js/hotelHtml5/jquery-1.8.2.min.js"></script>
		<script>
		$('#myInput').focus();
		myInput.oninput=function () {
				var base = "${applicationScope.ctx}";
				var texti=$("#myInput").val();
				$("#myTable").empty();
				if(texti!=''){
				$.ajax({
			    type: 'POST',
			    url:base+"/hotel/travelinfo.act",
			    data:{"username":texti},
			    async:false,
			    success: function(result) {
			    	var arr=result.split(",");
			    	for(i = 0; i < arr.length-1; i++){
			    		var sa=arr[i];
			    		var info=sa.substring(0,sa.indexOf('&'));
			    		var userId=sa.substring(sa.indexOf('&')+1,sa.lenght);
			    		var str="<tr class=\"e\" id=\"tb\"style=\"text-indent: 1rem;line-height:4rem;border-bottom: 1px solid #ddd;font-size:1.4rem;\"><td tid="+userId+" >"+info+"</td></tr>";
			    		$("#myTable").append(str);
			    	}
	   			 }
					});
				}
				// 声明变量 
				/*var input, filter, table, tr, td, i;
				input = document.getElementById("myInput");
				filter = input.value.toUpperCase();
				table = document.getElementById("myTable");
				tr = table.getElementsByTagName("tr");
				// 循环表格每一行，查找匹配项 
				for(i = 0; i < tr.length; i++) {
					td = tr[i].getElementsByTagName('td')[0];
					if(td) {
						if(td.innerHTML.toUpperCase().indexOf(filter) > -1) {
							tr[i].style.display = "block";
							tr[i].style.background = "#F8F8F8";
							tr[i].className += " one";
						} else {
							tr[i].style.background = "";
							tr[i].style.display = "none";
						}

					}

				}*/
			}

			$('body').on('click', '.e', function() {
				$('#myInput').val($(this).find('td').eq(0).html());
				var sd = $('#myInput').val();
				var userid=$(this).find('td').attr('tid');
				window.location.href = "hotel/travel.act?name=" + sd+"&userId="+userid;
			});
			/*-----返回上一页------*/
			$('.nav .you').click(function() {
				//history.go(-1);
				window.location.href = "${pageContext.request.contextPath}/hotel/travel.act";
			});
			 $("body").children().click(function () {});
		</script>
	</body>

</html>