<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<% String id=(String)session.getAttribute("id"); %>
<title>MYPAGE</title>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>MY FAQ</h2>
			<br>
			<table class="table table-hover" style="width: 730px;">
				<tr>
					<td>NUM</td>
					<td>TITLE</td>
					<td>DATE</td>
					<td>RESULT</td>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.b_num }</td>
						<td><a href="customerboard.do?cmd=info&b_num=${vo.b_num }">${vo.b_title }</a></td>
						<td>${vo.b_date }</td>
						<c:choose>
							<c:when test="${vo.b_result==0 }">
								<td style="color: red">[답변중]</td>
							</c:when>
							<c:when test="${vo.b_result==1 }">
								<td style="color: blue">[답변완료]</td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
			<c:choose>
				<c:when test="${startPage >10 }">
					<a href="mypagedo?cmd=customerboardlist&id=<%=id %>&pageNum=${startPage-1}"> << </a>				
				</c:when>
				<c:otherwise>
					<<
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${i==pageNum}">
					<a href="mypage.do?cmd=customerboardlist&id=<%=id %>&pageNum=${i}"><span style="color:black;">[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="mypage.do?cmd=customerboardlist&id=<%=id %>&pageNum=${i}"><span style="color:gray;">[${i }]</span></a>
				</c:otherwise>	
			</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${endPage < PageCount }">
					<a href="mypage.do?cmd=customerboardlist&id=<%=id %>&pageNum=${endPage+1}"> >> </a>
				</c:when>
				<c:otherwise>
					>>
				</c:otherwise>
			</c:choose>			
		</div>
		<div class="col-md-2"></div>
	</div>
</div>