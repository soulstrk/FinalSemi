<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MYPAGE</title>
<div class="container"  style="margin-bottom: 600px;">
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
								<c:forEach var="list" items="${volist }">
									<c:choose>
									<c:when test="${vo.oi_p_num==list.p_num}">
									<th><img src="painting/o/${list.p_image }" alt="product"></th>
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