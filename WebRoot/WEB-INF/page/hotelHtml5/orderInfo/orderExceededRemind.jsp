<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新单填写</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
</head>

<body>
<div class="bounced variation" style="display: block;">
	<c:set value="" var="superRuleReason_c"></c:set>
	<div id="variation">
		<div class="remind">变动提醒</div>
		<div style="font-size: 1rem;color: #666666; padding: 0 1rem;line-height: 1.6rem;">
			<p>${msg }</p>
			<div>
				<p>请选择超规原因：</p>
				<c:forEach items="${list }" var="reason" varStatus="i">
					<c:choose>
						<c:when test="${i.index eq 0 }">	
							<c:set value="${reason }" var="superRuleReason_c"></c:set>
							<div class="radios">
								<div style="margin: auto 0;" name="normal"><img src="../hotelimg/radio.png"/></div>
								<p>${reason }</p>
							</div>
						</c:when>
						<c:otherwise>
							<div class="radios">
								<div style="margin: auto 0;" name="normal"><img src="../hotelimg/radio_.png"/></div>
								<p>${reason }</p>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<input type="hidden" value="${superRuleReason_c }" id="superRuleReason_c" />
				<div class="radios">
					<div style="margin: auto 0;" name="other">
						<c:choose>
							<c:when test="${empty list }"><img src="../hotelimg/radio.png"/></c:when>
							<c:otherwise><img src="../hotelimg/radio_.png"/></c:otherwise>
						</c:choose>
					</div>
					<p>其他</p>
				</div>
			</div>
			<textarea name="a" placeholder="" class="reason" id="cancel_reason" oninput="setReason(this)"></textarea>
			<div class="ve_btn">
				<div style="">
					<a href="javascript:void(0);" onclick="confirm()">确认预订</a>
				</div>
				<div>
					<a href="javascript:void(0);" onclick="cancel()">我再想想</a>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
/**
 * ------变动提醒的效果--------
 */
$(function(){
	parent.setSuperRuleReason($("#superRuleReason_c").val());
});
function confirm(){
	parent.exceededRemind_confirm();
}
function cancel(){
	parent.exceededRemind_cancle();
}
function setReason(obj){
	var value = $(obj).val();
	parent.setSuperRuleReason(value);
}
$('.radios div').click(function(){
	var name = $(this).attr("name");
	if(name == 'normal'){
		var value = $(this).parent().find("p").text();
		parent.setSuperRuleReason(value);
	}else{
		parent.setSuperRuleReason("");
	}
	$(this).find('img').attr('src',$(this).find('img').attr('src') == "../hotelimg/radio.png" ? "../hotelimg/radio_.png" : "../hotelimg/radio.png");
	if($(this).find('img').attr('src') == "../hotelimg/radio.png"){
		$(this).parent().siblings().find('img').attr('src',"../hotelimg/radio_.png");
	}
});
</script>
</body>
</html>