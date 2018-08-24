<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<% String id=(String)session.getAttribute("id"); %>
<title>MYPAGE</title>
<div class="container">
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
							<td>${vo.adminOk }</td>
							<td>${vo.product_num }</td>
							<td>${vo.content }</td>
							<td><a href="mypage.do?cmd=reviewDelete&id=<%=id %>&adminOk=${vo.adminOk }"><span style="color:red;">[delete]</span></a></td>
						</tr>
						</c:when>
				</c:choose>
			</c:forEach> 
			</table>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>