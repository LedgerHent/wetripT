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
	<script type="text/javascript">
		var plicyRuleListLength = '${plicyRuleListLength }';
	</script>
</head>

<body>
	<c:forEach items="${approvalList }" var="approval" varStatus="i">
		<li>
			<c:choose>
				<c:when test="${i.index eq 0 }">
					<div class="radioBtn">
						<img src="../hotelimg/choose.png" />
						<input type="hidden" class="approvePolicyId" id="id_0" value="${approval.id eq -1 ? 'approvepolicyid_empty_flag' : approval.id }" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="radioBtn">
						<img src="../hotelimg/wei.png" />
						<input type="hidden" class="approvePolicyId" value="${approval.id }" />
					</div>
				</c:otherwise>
			</c:choose>
			<p><c:if test="${not empty approval.matchName }">${approval.matchName }-</c:if>${approval.name }</p>
			<c:if test="${approval.id ne '-1'}">
				<img src="../hotelimg/tips.png" / class="tips" onclick="showDetail('${approval.id }')">
			</c:if>
		</li>
	</c:forEach>
	<script type="text/javascript">
		$(function(){
			parent.setPlicyRuleListLength(plicyRuleListLength);
			parent.setApprovePolicyId($("#id_0").val());
		});
		/*-----审批政策的事件---------*/
		$('.radioBtn').click(function() {
			var cur_img = $(this).find('img').attr('src');
			if('../hotelimg/choose.png' == cur_img){
				return;
			}else{
				var approveId = $(this).find('.approvePolicyId').val();
				$(this).parents('li').siblings().find('.radioBtn img').attr('src', '../hotelimg/wei.png');
				$(this).find('img').attr('src', '../hotelimg/choose.png');
				parent.setApprovePolicyId(approveId);
			}
		});
		
		function showDetail(id){
			parent.showApprovalDetail(id);
		}
		
	</script>
</body>
</html>