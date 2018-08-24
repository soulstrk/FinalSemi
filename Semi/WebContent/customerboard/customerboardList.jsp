<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<%
	String id = (String) session.getAttribute("id");
	if (id == null) {
%>
 		<script type="text/javascript">
			alert("로그인 후 이용가능한 페이지입니다.");
			location.href = "customerboard.do?cmd=index";
		</script> 
<%
	}
%>
<title>FAQ</title>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>FAQ</h2><br>
		</div>
		<div class="col-md-2"></div>
</div>
<div class="row">
<div class="col-md-2"></div>
<div class="col-md-8">
<table class="table table-hover">
	<tr>
		<th>ID</th>
		<th>Title</th>
		<th>Date</th>
		<th>Result</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.b_id }</td>
			<c:choose>
				<c:when test="${vo.b_public_private ==1 }">
					<td><a href="customerboard.do?cmd=info&b_num=${vo.b_num }">${vo.b_title }</a></td>
				</c:when>
				<c:when test="${vo.b_public_private ==-1 }">
					<c:set var="id" value="<%=id %>"/>
					<c:set var="admin" value="admin"/>
					<c:choose>
					<c:when test="${id eq admin || id eq vo.b_id }">
						<td><a href="customerboard.do?cmd=info&b_num=${vo.b_num }">${vo.b_title }</a></td>
					</c:when>
					<c:when test="${id ne admin }"> <!-- !empty admin -->
						<td>[비밀글] ${vo.b_title }</td>
					</c:when>
					</c:choose>
				</c:when>
			</c:choose>
			<td>${vo.b_date }</td>
			<c:choose>  
				<c:when test="${vo.b_result==0 }">
					<td style="color:red">[답변중]</td>
				</c:when>
				<c:when test="${vo.b_result==1 }">
					<td style="color:blue">[답변완료]</td>
				</c:when>
			</c:choose>
		</tr>
	</c:forEach>
</table>
</div>
<div class="col-md-2"></div>
</div>

<div class="row">
<div class="col-md-2"></div>
<div class="col-md-8">
<div style="float:left;">
<form action="customerboard.do?cmd=write" method="post"> 
	<input type="submit" value="write">
</form>
</div>

<form action="customerboard.do?cmd=list" method="post">
<div style="float:right;">
<select name="select">
	<option value="id" selected="selected">ID</option>
	<option value="title">Title</option>
	<option value="content">Content</option>
</select>
<input type="text" name="find">
<input type="submit" value="Find">
</div>

<c:choose>
	<c:when test="${startPage>10 }">
		<a href="customerboard.do?cmd=list&pageNum=${startPage-1 }&select=${select}&find=${find}"> << </a>
	</c:when>
	<c:otherwise>
		<<
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${pageNum ==i }">
			<a href="customerboard.do?cmd=list&pageNum=${i }&select=${select}&find=${find}"><span style="color:black;">[${i}]</span></a>
		</c:when>
		<c:otherwise>
			<a href="customerboard.do?cmd=list&pageNum=${i }&select=${select}&find=${find}"><span style="color:gray;">[${i}]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="customerboard.do?cmd=list&pageNum=${endPage+1 }&select=${select}&find=${find}" onclick="get()">>></a>
	</c:when>
	<c:otherwise>
		>>
	</c:otherwise>
</c:choose>
</form>
</div>
</div>
<div class="col-md-2"></div>
</div>
