<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="js/jy_join.js?ver=4"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>MYPAGE</title>
<style>
	th{width:200px;}
	td{width:300px;}
	input{width:400px;}
	#email1{width: 400px;}
	#postcode,#address1,#address2{width: 200px;}
	#btn1{width:130px;}
</style>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>MYPAGE</h2>
			<table class="table table-striped" style="width:730px;">
			<tr>
				<th><label for="id">ID</label></th>
				<td><input type="text" id="id" value="${vo.id }" disabled="disabled"></td>
			</tr>
			<tr>
				<th><label for="pwd">PASSWORD</label></th>
				<td><input type="text" id="pwd" value="${vo.pwd }" disabled="disabled">
				</td>
			</tr>
			<tr>
				<th><label for="name">NAME</label></th>
				<td><input type="text" name="name" value="${vo.name }" disabled="disabled"></td>
			</tr>			
			<tr>
				<th><label for="email">EMAIL</label></th>
				<td>
			        <input type="text" id="email1" value=${vo.email } disabled="disabled">
				</td>
			</tr>
			<tr>
				<th><label for="pwd">PHONE</label></th>
				<td><input type="text" id="phone" value="${vo.phone }" disabled="disabled">
				</td>
			</tr>
			<tr>
				<th><label for="pwd">ADDRESS</label></th>
				<td><textarea id="addr" disabled="disabled">${vo.addr }</textarea>
				</td>
			</tr>			
		</table>
		<form action="mypage.do?cmd=delete" method="post" onsubmit="return deleteOk()">
			<input type="hidden" name="id" value="${vo.id }">
			<input type="hidden" name="pwd" value="${vo.pwd }">
			<input type="submit" value="Delete" style="width:100px;float:right;">
		</form>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
<script>
	function deleteOk(){
		var pwd=prompt("비밀번호를 입력해주세요", "");
		if(pwd==${vo.pwd}){
			return true;
		}
		alert("비밀번호가 일치하지 않아요");
		return false;
	}
</script>
<style>
#addr{width:400px;height:50px;overflow:hidden;resize:none;}
</style>
	
