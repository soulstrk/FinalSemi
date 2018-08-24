<%@page import="ljy.vo.MembersVo"%>
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
	<script src="js/jy_join.js?ver=4"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>MYPAGE</title>
<style>
	th{width:200px;}
	td{width:300px;}
	input{width:400px;}
	#email2,#email1{width: 130px;}
	#postcode,#address1,#address2{width: 200px;}
	#btn1{width:130px;}
</style>
<%
	String resultMsg = (String) request.getAttribute("resultMsg");
	if (resultMsg != null && !resultMsg.equals("")) {
%>
	<script type="text/javascript">
		alert(resultMsg);
	</script>
<%
	}
	request.setCharacterEncoding("utf-8");
%>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>MYPAGE</h2>
			<form action="mypage.do?cmd=updateOk" method="post" onsubmit="return getSubmit()" name="form">
			<input type="hidden" name="id" value="${vo.id }">
			<input type="hidden" name="pwd" value="${vo.pwd }">
			<table class="table table-striped" style="width:730px;">
			<tr>
				<th><label for="id">ID</label></th>
				<td><input type="text" name="id1" value="${vo.id }" disabled="disabled"></td>
			</tr>
			<tr>
				<th><label for="pwd">PASSWORD</label></th>
				<td><input type="text" id="pwd1" name="pwd1" style="color:gray;"
				value="비밀번호 8~12자 영대소문자와 숫자, 특수문자 조합" 
				onclick="remove()" onkeyup="getPwdOk1()">&nbsp;&nbsp;&nbsp;<span id="sp1"><img src="" id="img1"></span>
				</td>
			</tr>
			<tr>
				<th><label for="pwd">PASSWORD CHECK</label></th>
				<td><input type="text" id="pwdck" name="pwdck" style="color:gray;"
				value="비밀번호 확인을 위해 다시 한번 입력해주세요" 
				onclick="remove2()" onkeyup="getPwdOk2()">&nbsp;&nbsp;&nbsp;<span id="sp2"><img src="" id="img2"></span></td>
			</tr>
			<tr>
				<th><label for="name">NAME</label></th>
				<td><input type="text" name="name" value="${vo.name }" disabled="disabled"></td>
			</tr>			
			<tr>
				<th><label for="email">EMAIL</label></th>
				<td><div>
			        <input name="email1" type="text" class="box" id="email1" size="15"> @ <input name="email2" type="text" class="box" id="email2" size="20">
					<select name="email_select" class="box" id="email_select" onchange="checkemailaddy();">
				    <option value="" selected>선택하세요</option>
				    <option value="naver.com">naver.com</option>
				    <option value="hotmail.com">hotmail.com</option>
				    <option value="hanmail.com">hanmail.com</option>
				    <option value="yahoo.co.kr">yahoo.co.kr</option>
				    <option value="google.com">google.com</option>
				    <option value="yahoo.com">yahoo.com</option>
				    <option value="yahoo.com">yahoo.com</option>
				    <option value="1">직접입력</option>
				</select>
         		</div></td>
			</tr>
			<tr>
				<th><label for="pwd">PHONE</label></th>
				<td><input type="text" name="phone" id="phone" value="${vo.phone }" 
					onclick="remove3()" onkeyup="getPhone()">&nbsp;&nbsp;&nbsp;<span><img src="" id="img3"></span></td>
			</tr>
			<tr>
				<th><label for="pwd">ADDRESS</label></th>
				<td><input type="text" id="postcode" placeholder="우편번호" name = "postnum" readonly="readonly">
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" id="btn1"><br>
					<input type="text" id="address1" placeholder="도로명 혹은 지번 주소" name = "addr1">
					<input type="text" id="address2" placeholder="상세주소" name = "addr2">
					<span id="guide" style="color:#999"></span></td>
			</tr>			
		</table>
		<input type="submit" value="Update" style="width:60px;height:40px;">
</form>
<script type="text/javascript">
function getSubmit(){
	var img1=document.getElementById("img1");
	var img2=document.getElementById("img2");
	var img3=document.getElementById("img3");
	//alert(img1.src);
	if(!(img1.src=="http://localhost:8081/Semi/images/success.png")){
		alert("수정할 비밀번호를 입력해주세요");
		return false;
	}
	if(!(img2.src=="http://localhost:8081/Semi/images/success.png")){
		alert("새 비밀번호 확인을 진행해주세요");
		return false;
	}
	var email1=document.getElementById("email1");
	var email_select=document.getElementById("email_select"); 
	var val="";
	if(email1.value==val || email_select.value==val){
		alert("수정할 이메일을 입력해주세요");
		email1.focus();
		return false;
	}
	if(!(img3.src=="http://localhost:8081/Semi/images/success.png")){ 
		alert("수정할 연락처를 입력해주세요");
		return false;
	}
	var postcode=document.getElementById("postcode");
	var address1=document.getElementById("address1");
	var address2=document.getElementById("address2");
	if(postcode.value==val || address1.value==val || address2.value==val){
		alert("수정할 주소록을 입력해주세요");
		return false;
	}
	var pwd=prompt("기존에 사용하신 비밀번호를 입력해주세요","");  
	if(pwd==${vo.pwd}){
		return true;
	}
	alert("비밀번호가 일치하지 않아요");
	return false;
}
</script>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>