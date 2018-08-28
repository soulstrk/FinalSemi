<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>아이디/비밀번호 찾기</title>
<!-- 폼: https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_form_basic&stacked=h 
	 탭: https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_ref_js_tab&stacked=h -->
<c:if test="${!empty dontFind }"><script type="text/javascript">alert('${dontFind }')</script></c:if>
<c:if test="${!empty findPwd }"><script type="text/javascript">alert('찾으신 비밀번호는 : ${findPwd } 입니다')</script></c:if>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>아이디/비밀번호 찾기</h2>
				 <ul class="nav nav-tabs">
				    <li class="active"><a data-toggle="tab" href="#home">아이디</a></li>
				    <li><a data-toggle="tab" href="#menu1">비밀번호</a></li>
				  </ul>
				<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
				<form action="findinfo.do?cmd=findinfo" method="post">
     				<div class="form-group">
				      <label for="id">phone:</label>
				      <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone" style="width:400px;">
				    </div>
					<div class="form-group">
				      <label for="id">email:</label>
				      <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" style="width:400px;">
				    </div>
				   	 <button type="submit" class="btn btn-default">Submit</button>
				  </form>
				  
				  <!-- ----------------------------------------------- -->
    				</div>
				<div id="menu1" class="tab-pane fade">
			      <form action="findinfo.do?cmd=findPwd" method="post">
				  <div id="home" class="tab-pane fade in active">
     				<div class="form-group">
				      <label for="id">ID:</label>
				      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id" style="width:400px;">
				    </div>
					<div class="form-group">
					  <label for="question">Question:</label>
					  <select name="q_num" class="form-control" id="q_num" style="width:400px;">
					       	<option value="" selected>질문선택</option>
					       	<option value="1">당신의 고향은 어디입니까?</option>
					       	<option value="2">당신의 초등학교 이름은?</option>
					       	<option value="3">당신의 키는 몇입니까?</option>
					       	<option value="4">당신의 취미는 무엇입니까?</option>
					       	<option value="5">당신의 성격은 어떻습니까?</option>
					</select>
					</div>
					<div class="form-group">
					  <label for="answer">Answer:</label>
					  <input type="text" class="form-control" id="answer" placeholder="Enter answer" name="answer" style="width:400px;">
					</div>
				   	 <button type="submit" class="btn btn-default">Submit</button>
				    </div>
				  </form> </div>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
</div>
