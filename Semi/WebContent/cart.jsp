<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Cart</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/cartPage.css" />
<link rel="stylesheet" href="css/test.css?ver=1" />
<script src="js/yi_js.js"></script>
<% String id=(String)session.getAttribute("id");// %>
<div class="container-fulid" style="margin-bottom: 540px;">
	<div class="row" style="height: 100px;"></div>
	<div class="row" style="height: 100px;">
		<div class="col-md-12">
			<div style="text-align: center">MY CART</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="cart_borad_tap">
				<ul class="cb_clear pointer">
					<li><a href="cart.do?cmd=cart&id=<%=id %>" class="on">CART</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
	<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="table-cart table-fill-prd">
				<table summary="번호, 사진, 제품명, 수량, 적립, 가격, 배송비, 취소">
				<caption>장바구니 담긴 상품</caption>
				<colgroup>
					<col width="40" />
					<col width="300" />
					<col width="300" />
					<col width="150" />
					<col width="150" />
					<col width="115" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="first_stk"><input type="checkbox"
							name="allcheck" checked onclick="getCheck('basketchks')"/></th>
						<th scope="col">이미지</th>
						<th scope="col">상품명</th>
						<th scope="col">수량</th>
						<th scope="col">가격</th>
						<th scope="col">취소</th>
					</tr>
				</thead>
				<tbody> <!-- 상품정보 담는 곳 --> 
				<c:forEach var="vo" items="${list }"> <!-- 장바구니 정보 불러오기 -->
					<tr class="nbg">
		<!-- 체크박스 --><td><input type="checkbox" name="basketchks"checked="checked"/></td>
					  <c:forEach var="pvo" items="${plist }"> <!-- 상품 정보 불러오기 -->
					  	<c:choose>
					  		<c:when test="${vo.c_p_num == pvo.p_num }"> 
		<!-- 이미지    --><td><div class="thumb"><a href="opainting.do?cmd=detail&p_num=${pvo.p_num }"><img src="painting/o/${pvo.p_image }" alt="상품 섬네일" id="CartImg"/></a></div></td>
		<!-- 상품제목 --><td>${pvo.p_name }</td>
		<!-- 상품수량 --><td><form action="cart.do?cmd=update" method="post">
						  <input type="hidden" name="c_num" value="${vo.c_num }">
						  <input type="hidden" name="id" value="<%=id%>">
						  <input type="text" name="c_amount" value="${vo.c_amount }" style="width:50px;">
						  <input type="submit" value="수정" style="width:50px;height:34px;"></form></td> 
		<!-- 상품가격 --><td>${pvo.p_price}</td> <!-- /////////// * vo.c_amount 갯수 곱한 값을 넣어야 하는지 -->
		<!-- 상품취소 --><td><a href="cart.do?cmd=delete&c_num=${vo.c_num }&c_id=${vo.c_id}">삭제</a></td>
							</c:when>
					    </c:choose>
					  </c:forEach>
					</tr>
				</c:forEach>
				</tbody>
				</table>
				
				<div style="float: right;">총 구매 가격: ${price }원</div> <br><br><br>
				<div class="btn-order-ctrl"> <!-- 밑에 세개 버튼 -->
					<a href="#" class="checkout">상품주문하기</a>
					<a href="cart.do?cmd=delete&c_num=-1&c_id=<%=id %>" class="cart_del">장바구니 비우기</a>
					<a href="cart.do?cmd=index" class="home">쇼핑계속하기</a>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
