<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<title>MYPAGE</title>
<%	String id = (String) session.getAttribute("id");  %>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>ORDER LIST</h2>
			<br>
			<table class="table table-hover" style="width: 730px;">
				<tr>
					<td>DATE[주문번호]</td>
					<td>PRICE</td>
					<td>STATE</td>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td><a href="mypage.do?cmd=orderinfo&id=${vo.o_id }&o_num=${vo.o_num}">${vo.o_date }[${vo.o_num }]</a></td>
					<td>${vo.o_payment }</td>
					<td>${vo.o_state }</td>
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
			
			
		</div>
		<div class="col-md-2"></div>
	</div>
</div>