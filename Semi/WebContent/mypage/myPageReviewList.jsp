<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String id=(String)session.getAttribute("id"); %>
<title>MYPAGE</title>
<div class="container"  style="margin-bottom: 730px;">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>REVIEW LIST</h2>
			<br>
			<table class="table table-hover" style="width: 730px;">
				<tr>
					<td>NUM</td>
					<td>PRODUCT</td>
					<td>CONTENT</td>
					<td>DELETE</td>
				</tr>
				<c:forEach var="vo" items="${list }">
					<c:choose>
						<c:when test="${vo ne null }">
						<tr>
							<td>${vo.comments_num }</td>
							<td>${vo.p_num }</td>
							<td>${vo.content }</td>
							<td><a href="mypage.do?cmd=reviewDelete&id=<%=id %>&comments_num=${vo.comments_num}"><span style="color:red;">[delete]</span></a></td>
						</tr>
						</c:when>
				</c:choose>
			</c:forEach> 
			</table>
			
			<c:choose>
				<c:when test="${startPage > 10 }">
					<a href="mypage.do?cmd=review&id=<%=id%>&pageNum=${startPage-1}"> << </a>
				</c:when>
				<c:otherwise>
					<<
				</c:otherwise>
			</c:choose>

			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${pageNum eq i }">
						<a href="mypage.do?cmd=review&id=<%=id%>&pageNum=${i}"><span style="color:black;">[${i }]</span></a>
					</c:when>
					<c:otherwise>
						<a href="mypage.do?cmd=review&id=<%=id%>&pageNum=${i}"><span style="color:gray;">[${i }]</span></a>					
					</c:otherwise>			
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${endPage < pageCount }">
					<a href="mypage.do?cmd=review&id=<%=id%>&pageNum=${endPage +1}"> >> </a>
				</c:when>
				<c:otherwise>
					>>
				</c:otherwise>
			</c:choose>
			
		</div>
		<div class="col-md-2"></div>
	</div>
</div>