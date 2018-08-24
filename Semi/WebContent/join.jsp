<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="js/jy_join.js?ver=3"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> <!-- join페이지  waterPaitn 자바스크립트 -->

<div class="container" style="padding-left: 240px; padding-top: 80px; padding-bottom: 50px; font-family: 'Jeju Myeongjo', serif; font-size: 25px">
     <div class="page-header">
       <h1>회원가입</h1>
     </div>
     
     <div class="col-md-9 col-md-offset-3">
       <form role="form" action="members.do?number=1" method="post" name="form">
         <div class="form-group form-group-custom">
           <label for="id">아이디</label>
           <span style="color:red">*</span>&nbsp;&nbsp;
           <button type="button" class="btn btn-secondary" id="idcheck_btn" disabled="disabled" onclick="dupliChk()">중복검사</button>&nbsp;&nbsp;&nbsp;<span id="dupliChkSpan"></span>
           <input type="text" class="form-control" id="id" placeholder="아이디 8~12자 영대소문자 와 숫자" name="id" onkeyup="idLiveCheck()"><img class="join_image id" src="" id="img_id">
         </div>
         
         <div class="form-group form-group-custom">
           <label for="password">비밀번호</label>
           <span style="color:red">*</span>
           <input type="password" class="form-control" id="password1" placeholder="비밀번호 8~20자 영대소문자와 숫자,특수문자 조합" name="pwd" onkeyup="pwd1LiveCheck()">
           <img class="join_image pwd1" src="" id="img_pwd1">
         </div>
         <div class="form-group form-group-custom">
           <label for="password2">비밀번호 확인</label><span style="color:red">*</span>
           <input type="password" class="form-control" id="password2" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요" onkeyup="pwd2LiveCheck()">
           <span id="pwd_msg" style="color: red;"></span>
         </div><br>
         
         <div class="form-group">
           <label for="name">이름</label><span style="color:red">*</span>
           <input type="text" class="form-control" id="name" placeholder="이름을 입력해 주세요" name="name">
         </div>
         
         <div class="form-check">
		    <input type="radio" class="form-check-input" name="gender" value="남자" checked="checked">남
		 </div>
		 <div class="form-check">
		    <input type="radio" class="form-check-input" name="gender" value="여자">여
		 </div><br>
         
         <div class="form-group">
           <label for="email1">이메일</label><span style="color:red">*</span><br>
             <input type="text" id="email1" size="15" name="email1"> @ <input type="text" id="email2" size="15" name="email2" style="display: inline;">
	<select name="email_select" class="box" id="email_select" onchange="checkemailaddy();" style="margin-left: 300px;">
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
         </div>
         
         <div class="form-group">
           <label for="phone">핸드폰 번호</label><span style="color:red">*</span>
             <input type="text" class="form-control" id="phone" placeholder="- 없이 입력해 주세요" name="phone" onkeyup="phoneChk()">
             <img class="join_image phone" src="" id="img_phone">
         </div>
         
         <div class="form-group">
           비밀번호 찾기 질문<span style="color:red">*</span>
           <select name="q_num" id="q_sel">
           	<option value="" selected>질문선택</option>
           	<option value="1">당신의 고향은 어디입니까?</option>
           	<option value="2">당신의 초등학교 이름은?</option>
           	<option value="3">당신의 키는 몇입니까?</option>
           	<option value="4">당신의 취미는 무엇입니까?</option>
           	<option value="5">당신의 성격은 어떻습니까?</option>
           </select>
           <input type="text" class="form-control" name="answer" placeholder="답변을 입력해주세요"/>
         </div>
         
         <div class="form-group">
         <label for="addr">주소</label><span style="color:red">*</span><br>
         <input type="text" id="postcode" placeholder="우편번호" name = "postnum" readonly="readonly">
		<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" id="address1" placeholder="도로명 혹은 지번 주소" name = "addr1">
		<input type="text" id="address2" placeholder="상세주소" name = "addr2">
		<span id="guide" style="color:#999"></span>
		</div><br><br>
         
         <div class="form-group text-center">
           <button type="submit" class="btn btn-info" onclick="return finalCheck()">회원가입<i class="fa fa-check spaceLeft"></i></button>
           <button type="reset" class="btn btn-info">가입취소<i class="fa fa-times spaceLeft"></i></button>
         </div>
       </form>
     </div>
   </div>

