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
	<style type="text/css">
		.myTable {border-collapse: collapse;width: 100%;border: 1px solid #ddd;font-size: 18px;background: #FFFFFF;}
		.myTable th,#myTable td {text-align: left;padding: 12px;}
		.myTable tr {border-bottom: 1px solid #ddd;}
	</style>
	<script type="text/javascript">
		var idFlag = '${idFlag }';
	</script>
</head>

<body>
<table class="myTable">
	<c:forEach items="${list }" var="dept">
		<tr class="e">
			<td class="dept">
				<p>${dept.attribute }</p>
				<span>
					<input type="hidden" class="deptId" value="${dept.v }"/>
					<input type="hidden" class="deptName" value="${dept.n }"/>
				</span>
			</td>
		</tr>
	</c:forEach>
</table>
<script>
	//设值
	$(".dept").click(function(){
		var id = $(this).find('.deptId').val();
		var name = $(this).find('.deptName').val();
		parent.setDeptInfo(idFlag, id, name);
	});
</script>
</body>
</html>