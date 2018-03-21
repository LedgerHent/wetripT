<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>

	<head>
	<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>我的常旅客</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
		
		
	<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
	</head>
	<body>
	<div id="container">
	
		<section>
			<div class="bounced">
				<div class="tips-alert">
					<div class="alert-title">确定要删除此条信息:</div>
					<div class="alert-btn">
						<div class="sure button">确认</div>
						<div class="cancal-alert button"> 取消</div>
					</div>
				</div>
			</div>
			<header>
				<div>
					<img src="img/header.gif" onclick="back()"/>
				</div>
				<p class="zhixiang">常旅客</p>
				<div class="addBtn">
					<a href="<%=path %>/ppp/addd.act?flag=my"><img src="img/addBtn.png" / style="display: block; margin-top:0.8rem;"></a>
				</div>
			</header>
			<ul class="passener-content emp-table" style="display: block; margin-top: 1rem;">
				<c:forEach items="${results.data }" varStatus="status" var="passenger">
				<li>
					<div class="left-passener">
						<div class="middle-table">
							<div class="passener-name">${passenger.name }</div>
							<input type="hidden" id="name${status.index }" value="${passenger.name }">
							<div class="passener-num">
								<span>手机号</span>
								<span class="number-type">${passenger.mobile }</span>
								<input type="hidden" id="id${status.index }" value="${passenger.id }">
								<input type="hidden" class="obj${status.index }" value="${passenger }">
							</div>
							<div class="passener-type">
								<c:if test="${passenger.ids[0].type==1 }"><span>身份证</span></c:if>
								<c:if test="${passenger.ids[0].type==2 }"><span>因公护照</span></c:if>
								<c:if test="${passenger.ids[0].type==3 }"><span>海员证</span></c:if>
								<c:if test="${passenger.ids[0].type==4 }"><span>回乡证</span></c:if>
								<c:if test="${passenger.ids[0].type==5 }"><span>军官证</span></c:if>
								<c:if test="${passenger.ids[0].type==6 }"><span>因公港澳通行证</span></c:if>
								<c:if test="${passenger.ids[0].type==7 }"><span>台胞证</span></c:if>
								<c:if test="${passenger.ids[0].type==99 }"><span>其他</span></c:if> 
								 <span class="number-type">${passenger.ids[0].num }</span> 
							</div>
						</div>
					</div>
					<div class="right-table">
						<a href="<%=path %>/ppp/updatee.act?passengerId=${passenger.id}&flag=my"><img src="img/bianji.png"  onclick="update()" /></a>
					</div>
				</li>
				</c:forEach>
				
			</ul>
		</section>
		</div>
	</body>

</html>
<script type="text/javascript" src="libs/jquery.min.js"></script>
<script type="text/javascript" src="libs/gublic.js"></script>
<script type="text/javascript"  src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"  src="js/MyConfirm.js"></script>
<script type="text/javascript"  src="js/page/comm.js"></script>
<script type="text/javascript">
	var num;
	$('.left-passener').on('click', function() {
		var height = $('body').height();
		$('.bounced').height(height);
		//$('.bounced').fadeIn();
		num = $(this).parents('li').index();
		var passname=$("#name"+num).val();
		showMsg(passname);
	});
	$('.cancal-alert').on('click', function() {
		$('.bounced').fadeOut();
	});
	$('.sure').on('click', function() {
		$('.bounced').fadeOut();
		var pid=$("#id"+num).val();
		window.location.href='<%=path%>/ppp/del.act?passengerId='+pid;
		$(this).parents('section').find('li').eq(num).hide();
	});
	
	
	function showMsg(passname){
  		//有很多可用参数，详见jquery插件，以下是用法示例
  		$(this).MyConfirm({
            content: "确认删除常旅客  "+passname+" 吗？",
            hasCancelBtn:true
        },callback);
  	}
	
	function callback(isConfirm){
  		/* $(this).MyConfirm({
            content: "你刚点击了"+(isConfirm?"确认":"取消") +"按钮。"
        }); */
        if(isConfirm==true){
        var pid=$("#id"+num).val();
  		window.location.href='<%=path%>/ppp/del.act?passengerId='+pid;
        }
  	}
  	
	 function back(){
	   window.history.back(-1); 
	   }
</script>

