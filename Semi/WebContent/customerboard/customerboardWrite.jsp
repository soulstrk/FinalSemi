<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/yi_js.js"></script>

<%
	
	String id = (String) session.getAttribute("id");
	request.setCharacterEncoding("utf-8");
	String write=(String)request.getAttribute("write"); 
	String b_title=(String)request.getAttribute("b_title");
	if(b_title.equals("20자 이내로 작성해주세요")){
		write="insert";
	}else{
		write="updateOk";
	}
	String b_content=(String)request.getAttribute("b_content");
//	String b_result=(String)request.getAttribute("b_result"); 
%>
<title>FAQ</title>
<div class="container" style="margin-bottom: 430px;">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form action="customerboard.do?cmd=<%=write %>" method="post" onsubmit="return getSubmit()">
				<table class="table table-striped">
					<tr>
						<th>Title</th>
						<td>
							<input type="text" name="b_title" id="b_title" 
								style="width: 400px; color: gray;" value="<%=b_title %>" onclick="getBlank1()">
						</td>
						<th>ID</th>
						<td><%=id%></td>
					</tr>
					<tr>
						<th>Content</th>
						<td colspan="5"><textarea id="b_content" name="b_content"
								style="width: 580px; height: 300px;color: gray;resize: none;" onclick="getBlank()" ><%=b_content %></textarea></td>
					</tr>
					<tr>
						<th>Public/Private</th>
						<td colspan="5">
							<label for="public">Public </label> 
							<input type="radio" name="b_public_private" value="1" checked="checked">
							&nbsp;&nbsp;&nbsp; 
							<label for="public">Private </label> 
							<input type="radio" name="b_public_private" value="-1"></td>
					</tr>
				</table>
				
				<input type="hidden" name="b_id" value="<%=id%>">
				<input type="hidden" name="b_num" value="${param.b_num }">
			<!--  <input type="hidden" name="b_result" value="${param.b_result }">-->	
				<input type="submit" value="Upload">
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
