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
<div class="Tab">
	<div class="tabTitle">
		<div class="flyer highlight">企业员工</div>
		<div class="flyer">常旅客</div>
	</div>
	<div class="tabContent">
		<ul class="show">
			<c:forEach items="${staffList }" var="staff">
				<li>
					<p>
						<span class="passener-name">${staff.name }</span>
					</p>
					<p>
						<span class="name">手机号</span>
						<span class="passener-phone">${staff.mobile }</span>
					</p>
					<p>
						<span class="name">身份证</span>
						<span class="passener-type">
							<c:set var="idCard" value=""></c:set>
							<c:set var="passPort" value=""></c:set>
							<c:forEach items="${staff.ids }" var="card">
								<c:if test="${card.type eq '1' }">
									<c:set var="idCard" value="${card.num }"></c:set>
									${card.num }
								</c:if>
								<c:if test="${card.type eq '2' }">
									<c:set var="passPort" value="${card.num }"></c:set>
								</c:if>
							</c:forEach>
						</span>
					</p>
					<p>
						<span class="name">电子邮箱</span>
						<span class="passener-email">${staff.email }</span>
					</p>
					<input type="hidden" class="passener-guestId" value="${staff.id }" />
					<input type="hidden" class="passener-birthday" value="${staff.birthday }" />
					<input type="hidden" class="passener-deptId" value="${staff.departmentId }" />
					<input type="hidden" class="passener-deptName" value="${staff.departmentName }" />
					<!-- 员工的年龄类型无意义，所以设置死值1表示成人  --lihongfeng说的 ..-->
					<input type="hidden" class="passener-ageType" value="1" />
					<input type="hidden" class="passener-idCard" value="${idCard }" />
					<input type="hidden" class="passener-passPort" value="${passPort }" />
				</li>
			</c:forEach>
		</ul>
		<ul>
			<c:forEach items="${passengerList }" var="passenger">
				<li>
					<p class="editor">
						<span>
							<span class="passener-name">${passenger.name }</span>
							<!-- 2：儿童，3：婴儿 -->
							<c:if test="${passenger.type ne 1 }">(儿童)</c:if>
						</span>
						<!-- <span class="edit"><img src="../hotelimg/bianji.png"/></span> -->
					</p>
					<p>
						<span class="name">手机号</span>
						<span class="passener-phone">${passenger.mobile }</span>
					</p>
					<p>
						<span class="name">身份证</span>
						<span class="passener-type">
							<c:set var="idCard" value=""></c:set>
							<c:set var="passPort" value=""></c:set>
							<c:forEach items="${passenger.ids }" var="card">
								<c:if test="${card.type eq '1' }">
									<c:set var="idCard" value="${card.num }"></c:set>
									${card.num }
								</c:if>
								<c:if test="${card.type eq '2' }">
									<c:set var="passPort" value="${card.num }"></c:set>
								</c:if>
							</c:forEach>
						</span>
					</p>
					<p>
						<span class="name">电子邮箱</span>
						<span class="passener-email">${passenger.email }</span>
					</p>
					<input type="hidden" class="passener-guestId" value="" />
					<input type="hidden" class="passener-birthday" value="${passenger.birthday }" />
					<input type="hidden" class="passener-ageType" value="${passenger.type }" />
					<input type="hidden" class="passener-idCard" value="${idCard }" />
					<input type="hidden" class="passener-passPort" value="${passPort }" />
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<script type="text/javascript">
/*-----------添加乘机人页面选项卡的切换-----------*/
$('.tabTitle .flyer').click(function() {
	var index = $(this).index();
	$(this).addClass('highlight').siblings().removeClass('highlight');
	$('.tabContent ul').eq(index).addClass('show').siblings().removeClass('show');

});
$("body").children().click(function () {});
</script>
</body>
</html>