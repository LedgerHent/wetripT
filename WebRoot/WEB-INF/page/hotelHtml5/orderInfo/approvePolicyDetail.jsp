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
	<c:if test="${not empty approvalsDetail.approvals }">
		<p>
			<span class="aw">审批要求:</span> 
			<span>
				<c:if test="${approvalsDetail.approvals[0].type eq 1}">任一通过</c:if>
				<c:if test="${approvalsDetail.approvals[0].type eq 2}">全部通过</c:if>
				<c:if test="${approvalsDetail.approvals[0].type eq 3}">${approvalsDetail.approvals[0].count }通过</c:if>
				
			</span>
		</p>
	</c:if>
	<p>
		<span class="aw">审批类别:</span> 
		<span> 
			<c:if test="${approvalsDetail.type eq 1 }">同级审批</c:if> 
			<c:if test="${approvalsDetail.type eq 2 }">逐级审批</c:if>
		</span>
	</p>
	<c:forEach items="${approvalsDetail.approvals }" var="apv">
		<p>
			<span class="aw">${apv.flowId }级审批人</span>
			<span>
				<c:forEach items="${apv.auditors }" var="auditor">
					${auditor.name }&nbsp;
				</c:forEach>
			</span>
		</p>
	</c:forEach>
</body>
</html>