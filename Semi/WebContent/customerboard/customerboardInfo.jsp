<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>FAQ</title>
<div class="container" style="margin-bottom: 600px;">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
		<%
			String id=(String)session.getAttribute("id");
		%>
			<form action="customerboard.do?cmd=update" method="post">
				<table class="table table-striped">
					<tr>
						<th>ID</th>
						<td>${vo.b_id }</td>
						<th>Date</th>
						<td>${vo.b_date }</td>
					</tr>
					<tr>
						<th>Title</th>
						<td colspan="3">${vo.b_title }</td>
					</tr>
					<tr>
						<th>Content</th>
						<td colspan="3">${vo.b_content }</td>
					</tr>
				</table>
				<input type="hidden" name="b_num" value="${vo.b_num }">
				<input type="hidden" name="b_id" value="${vo.b_id }">
				<input type="hidden" name="b_title" value="${vo.b_title }">
				<input type="hidden" name="b_content" value="${vo.b_content }">
				<input type="hidden" name="b_public_private" value="${vo.b_public_private }">
				<input type="hidden" name="b_result" value="${vo.b_result }">
				<c:set var="id" value="<%=id %>"/>
				<c:choose>
					<c:when test="${id == vo.b_id }">
						<input type="submit" value="Update">
					</c:when>
				</c:choose>
			</form>
			<br>
			<c:set var="m" value="관리자 확인 중입니다.."/>
				<c:choose>
					<c:when test="${msg ne m}">
					<table class="table table-striped">
						<tr>
							<th><label for="id">Manager</label></th>
							<td>
							<textarea name="content" style="width: 350px;resize: none;" 
								disabled="disabled">${msg }</textarea>
							</td>
						</tr>
					</table>			
					</c:when>
					<c:when test="${msg eq m}">
					<table class="table table-striped">
						<tr>
							<th><label for="id">Manager</label></th>
							<td>
							<textarea name="content" style="width: 350px;resize: none;color:gray;" 
								readonly="readonly">${msg }</textarea>
							</td>
						</tr>
					</table>			
					</c:when>
				</c:choose>
			<c:set var="admin" value="admin"/>
			<c:choose>
				<c:when test="${id eq admin }">	
				<form action="customerboard.do?cmd=insert" method="post">
					<input type="hidden" name="b_num" value="${vo.b_num}"> 
					<input type="hidden" name="b_id" value="<%=id%>"> 
					<input type="hidden" name="b_title" value="A.${vo.b_title }"> 
					<input type="hidden" name="b_ref" value="${vo.b_ref}"> 
					<input type="hidden" name="b_is" value="${vo.b_is}"> 
					<input type="hidden" name="b_public_private" value="1"> 
					<input type="hidden" name="b_result" value="${vo.b_result }">
					<table class="table table-striped">
						<tr>
							<th><label for="id">Manager</label></th>
							<td><textarea name="b_content" style="width: 350px;" ></textarea></td>
							<td><input type="submit" value="Response"></td>
						</tr>
					</table>
				</form>
				</c:when>
			</c:choose>
			<input type="button" value="List" onclick="getList()">
<script type="text/javascript">
	function getList(){
		location.href="customerboard.do?cmd=list";
	}
</script>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>