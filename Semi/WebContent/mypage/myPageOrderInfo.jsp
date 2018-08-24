<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<title>MYPAGE</title>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
		<div>
		<h3>주문 상품 정보</h3>
		</div>
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th>주문번호</th>
						<th>ITEM</th>
						<th>PRODUCT</th>
						<th>QTY</th>
						<th>PRICE</th>
					</tr>
				</thead>
						<thead>
							<tr>
								<th rowspan="5">${o_num }</th>
								<!-- 이미지 누르면 상품 상세페이지로 넘기기 -->
								<th><img src="images/77.jpg" alt="product"></th>
								<c:forEach var="list" items="${volist }">
									<c:choose>
									<c:when test="${vo.oi_p_num==list.p_num}">
										<th>${list.p_name }</th>
									</c:when>
									<c:otherwise>
										<th>오류로 인해 해당 정보를 불러들이지 못했습니다..</th>
									</c:otherwise>
									</c:choose>
								</c:forEach>
								<th>${vo.oi_amount }</th>
								<th>${vo.oi_price }</th>
							</tr>
						</thead>
			</table>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>