<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty msg }">
	<script type="text/javascript">alert('${msg }');</script>
</c:if>
<div class="container-fluid" style="width: 1800px; padding-top: 100px;">
<div class="row">
	<div class="col-md-2">
		<h1>배송 관리</h1>
	</div>
	<div class="col-md-3"><h3>배송전 상품</h3></div>
	<div class="col-md-7">
		<button type="button" style="margin-right: 10px;" class="btn btn-success" onclick="location.href='deliveryManagement.do?cmd=delivering'">
		배송 전 상품보기
		</button>
		<button type="button" style="margin-right: 10px;" class="btn btn-success" onclick="location.href='deliveryManagement.do?cmd=deliveryComplete'">
		배송 중 상품보기
		</button>
		<button type="button" class="btn btn-success" onclick="location.href='deliveryManagement.do?cmd=final'">
		배송완료 상품보기
		</button>
	</div>
</div>
<div class="row">
	<table class="table table-striped">
		<tr>
			<th width="80px">배송번호</th>
			<th width="160px">주문자 아이디</th>
			<th width="400px">배송지 주소</th>
			<th width="150px">결제방법</th>
			<th width="160px">핸드폰 번호</th>
			<th width="170px">결제 금액</th>
			<th width="100px">수량</th>
			<th width="120px">배송 상태</th>
			<c:if test="${empty finalMsg }">
			<th width="60px">상태변경</th>
			</c:if>
		</tr>
		<c:forEach var="vo" items="${orderList }">
		<tr>
			<td>${vo.oDeliverynum }</td>
			<td>${vo.oId }</td>
			<td>${vo.oAddr }</td>
			<td>${vo.oPaymethod }</td>
			<td>${vo.oPhone }</td>
			<td>${vo.oPayment }</td>
			<td>${vo.oAmount }</td>
			<td>
			<c:if test="${vo.oState eq 1 }">
				배송 전
			</c:if>
			<c:if test="${vo.oState eq 2 }">
				배송 중
			</c:if>
			<c:if test="${vo.oState eq 3 }">
				배송 완료
			</c:if>
			</td>
			<c:if test="${empty finalMsg }">
			<td>
			<a href="deliveryManagement.do?cmd=changeState&deliveryNum=${vo.oDeliverynum}&id=${vo.oId}">변경</a>
			</td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
</div>
</div>
</body>
</html>