<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>MYPAGE</title>
<%
	String id = (String) session.getAttribute("id");
%>
<div class="container" style="margin-bottom: 730px;">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>POINT LIST</h2>
			<br>
			<table class="table table-hover" style="width: 730px;">
				<tr>
					<td>DATE</td>
					<td>주문번호</td>
					<td>POINT(+)</td>
					<td>POINT(-)</td>
				</tr>
				<c:forEach var="orderpointvo" items="${vo }">
					<tr>
						<c:forEach var="ordervo" items="${list }">
						<c:choose>
							<c:when test="${orderpointvo.o_num==ordervo.o_num }">
								<td>${ordervo.o_date }</td>
							</c:when>
							<c:otherwise>
								<td>오류</td>
							</c:otherwise>
						</c:choose>
						</c:forEach>
						<td><a href="mypage.do?cmd=orderinfo&o_num=${orderpointvo.o_num }" style="color:black;">${orderpointvo.o_num }</a></td>
						<td>${orderpointvo.plus_point }</td>
						<td>${orderpointvo.minus_point }</td>
					</tr>
			</c:forEach> 
			</table>
			<!-- 페이징처리 -->
			<c:choose>
				<c:when test="${startPage > 10 }">
					<a href="mypage.do?cmd=pointlist&id=<%=id%>&pageNum=${startPage-1}"> << </a>
				</c:when>
				<c:otherwise>
					<<
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${pageNum eq i }">
						<a href="mypage.do?cmd=pointlist&id=<%=id%>&pageNum=${i}"><span style="color:black;">[${i }]</span></a>		
					</c:when>
					<c:otherwise>
						<a href="mypage.do?cmd=pointlist&id=<%=id%>&pageNum=${i}"><span style="color:gray;">[${i }]</span></a>		
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${endPage < pageCount }">
					<a href="mypage.do?cmd=pointlist&id=<%=id%>&pageNum=${endPage +1}"> >> </a>
				</c:when>
				<c:otherwise>
					>>
				</c:otherwise>
			</c:choose>
			
						
		</div>
		<div class="col-md-2"></div>
	</div>
</div>