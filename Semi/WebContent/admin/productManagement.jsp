<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div class="container-fluid" style="padding-top: 150px;">
	<div class="row">
		<div class="col-md-12 orderHistory" style="text-align: center;"><span>ORDER HISTORY</span>
		<a href="index.jsp?content1=admin/SaleStatistics.jsp" style="text-align: center; position: relative; left: 150px;" id="seeGraph">그래프 보기</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10" id="productManagement">
			<button type="button" style="float: left;" class="btn1"> &lt; </button>
			<button type="button" style="float: right;" class="btn2"> &gt; </button>
			<table class="table table-striped">
				<tr>
					<th class="f1">주문번호</th>
					<th class="f1">아이디</th>
					<th class="f1">주문일자</th>
					<th class="f1">배송지주소</th>
					<th>
					결제방법
					<div class="btn-group">
					  <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
					  </button>
					  <div class="dropdown-menu">
					    <a class="dropdown-item" href="adminProduct.do?payIndex=신용카드&adminNum=1">신용카드</a>
					    <a class="dropdown-item" href="adminProduct.do?payIndex=계좌이체&adminNum=1">계좌이체</a>
					    <a class="dropdown-item" href="adminProduct.do?payIndex=핸드폰결제&adminNum=1">핸드폰결제</a>
					    <a class="dropdown-item" href="adminProduct.do?payIndex=카카오페이&adminNum=1">카카오페이</a>
					    <a class="dropdown-item" href="adminProduct.do?payIndex=무통장&adminNum=1">무통장</a>
					  </div>
					</div>
					</th>
					<th>배송번호</th>
					<th>수령인폰번호</th>
					<th class="e1">기타사항</th>
					<th class="e1">수량</th>
					<th class="e1">결제금액</th>
					<th class="e1">주문상태</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td class="f1">${vo.oNum }</td>
					<td class="f1">${vo.oId }</td>
					<td class="f1">${vo.oDate }</td>
					<td class="f1">${vo.oAddr }</td>
					<td>${vo.oPaymethod }</td>
					<td>${vo.oDeliverynum }</td>
					<td>${vo.oPhone }</td>
					<td class="e1">${vo.oMsg }</td>
					<td class="e1">${vo.oAmount }</td>
					<td class="e1">${vo.oPayment }</td>
					<td class="e1">${vo.oState }</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-1"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$('.btn2').click(function() {
		$('.e1').show();
		$('.f1').hide();
	});
	$('.btn1').click(function() {
		$('.e1').hide();
		$('.f1').show();
	});
})

</script>	

