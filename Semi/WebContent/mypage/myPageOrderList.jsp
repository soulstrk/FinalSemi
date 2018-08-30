<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script src="js/yi_js.js?ver=5" ></script>

<title>MYPAGE</title>
<%	String id = (String) session.getAttribute("id");  %>
<div class="container">
	<div style="height:80vh;" class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
		<br>
		<br>
		<br>
			<h2>ORDER LIST</h2>
			<p>기간 검색시 지난 주문내역을 조회하실 수 있습니다.<br>
			주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.
			</p>
			<!--  <div>-->
				<form method="post" action="mypage.do?cmd=orderlist&id=<%=id %>&date=date" onsubmit="return getDateReturn()"> 
					<div class="btn-group"> 
					    <button type="button" class="w3-button w3-white w3-border" id="btn1" onclick="getDate('btn1')">오늘</button>
					    <button type="button" class="w3-button w3-white w3-border" id="btn2" onclick="getDate('btn2')">1주일</button>
					    <button type="button" class="w3-button w3-white w3-border" id="btn3" onclick="getDate('btn3')">1개월</button>
					    <button type="button" class="w3-button w3-white w3-border" id="btn4" onclick="getDate('btn4')">3개월</button>
					    <button type="button" class="w3-button w3-white w3-border" id="btn5" onclick="getDate('btn5')">6개월</button>
					</div>
					<input type="date" name="date1" id="d1">~ <!-- 조회시작일 -->
					<input type="date" name="date2" id="d2"><!-- 조회마감일 -->
					<input type="submit" value="조회">
				
			<!-- </div>-->
			<br>
			<table class="table table-hover">
				<tr>
					<td>DATE[주문번호]</td>
					<td>PRICE</td>
					<td>STATE</td>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td><a href="mypage.do?cmd=orderinfo&id=${vo.o_id }&o_num=${vo.o_num}">${vo.o_date }[${vo.o_num }]</a></td>
					<td>${vo.o_payment }</td>
					<c:choose>
						<c:when test="${vo.o_state eq -1}">
							<td>[주문취소]</td>
						</c:when>
						<c:when test="${vo.o_state eq 1}">
							<td>[배송준비중]</td>
						</c:when>
						<c:when test="${vo.o_state eq 2}">
							<td>[배송중]</td>
						</c:when>
						<c:when test="${vo.o_state eq 3}">
							<td>[배송완료]</td>
						</c:when>
						<c:when test="${vo.o_state eq 4}">
							<td>[반품신청중]</td>
						</c:when>
						<c:when test="${vo.o_state eq 5}">
							<td>[반품승인완료]</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach> 
			</table>
			
			<!-- 페이징처리 -->
			<c:choose>
				<c:when test="${startPage > 10 }">
					<a href="mypage.do?cmd=orderlist&pageNum=${ startPage-1 }&id=<%=id%>"> << </a>
				</c:when>
				<c:otherwise>
					<<
				</c:otherwise>
			</c:choose>		
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${i==pageNum}">
						<a href="mypage.do?cmd=orderlist&pageNum=${ i}&id=<%=id%>"> <span style="color:black;">[${i }]</span> </a>
					</c:when>				
					<c:otherwise>
						<a href="mypage.do?cmd=orderlist&pageNum=${ i}&id=<%=id%>"> <span style="color:gray;">[${i }]</span> </a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${endPage < pageCount }">
					<a href="mypage.do?cmd=orderlist&pageNum=${ endPage+1 }&id=<%=id%>"> >> </a>
				</c:when>
				<c:otherwise>
					>>
				</c:otherwise>
			</c:choose>	
			</form>	
		</div>
		<div class="col-md-1"></div>
	</div>
</div>