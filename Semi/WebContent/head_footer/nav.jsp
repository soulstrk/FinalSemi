<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<!-- 헤더부분 -->
<nav class="navbar navbar-expand-lg navbar-white" style="height: 90px;">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02">
    <span class="navbar-toggler-icon"></span>
  </button>
  <!-- 메뉴창들 -->
  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav" style="margin-left: 300px;">
	  <li class="nav-item active">
      	<i class="fa fa-home w3-xxxlarge"></i>
      </li>
      <li class="nav-item active" > <!-- 로그인 / 로그아웃 처리 -->
      	<%
      		//예인: 아이디 변수명 줬음
      		String id=(String)session.getAttribute("id");
			if(id != null){ 
		%>
			<a class="nav-link" href="logout.jsp" id="login_logout"><b>Logout</b></a>
		<%
			} else{
		%>
			<a class="nav-link" href="" data-toggle="modal" data-target="#loginModal" id="login_logout"><b>Login</b></a>
		<% } %>
      </li>
      <li class="nav-item active">
        <a class="nav-link active" href="index.jsp?content1=join.jsp"><b>Join</b></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="javascript:alert('미구현')"><b>Cart</b></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link active" href="customerboard.do?cmd=list"><b>FAQ</b></a>
      </li>
      <li class="nav-item active">
        <div class="dropdown">
        <a class="nav-link active dropdown-toggle" href="customerboard.do?cmd=list" data-toggle="dropdown"><b>Picture</b>
		  <span class="caret"></span></a>
		  <ul class="dropdown-menu">
		    <li><a href="#">동양화</a></li>
		    <li><a href="#">서양화</a></li>
		  </ul>
		</div>
      </li>
      <li class="nav-item active"> <!-- 관리자페이지 처리 -->
        <%
        	String adminChk = (String)session.getAttribute("id");
        	if(adminChk != null){
			if(adminChk.equals("admin")){ 
		%>
			&nbsp;
			<div class="dropdown dropright" style="margin-left: 30px;">
			  <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">
			    Admin
			  </button>
			  <div class="dropdown-menu">
			    <a class="dropdown-item" href="adminProduct.do?adminNum=1">주문/매출 통계</a>
			    <a class="dropdown-item" href="admin.do?adminNum=1&num=0">회원관리</a>
			    <a class="dropdown-item" href="index.jsp?content1=admin/productEnroll.jsp">상품등록</a>
			  </div>
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
					 $('#searchForm').css('left','655px'); 
					 $('img[src="images/logo_on.png"]').css('left','170px'); 
					 $('#myPage').css('left','580px');
				})
			</script>
		<%
			} else{	
		%>
			<a class="nav-link active" href="index.jsp?content1=join.jsp" style="display: none;"><b>회원가입</b></a>
		<% }} %>
      </li>
      <li class="nav-item active">
        <a class="navbar-brand" href="index.jsp?content1=mainPage.jsp"><img src="images/logo_on.png" style="width: 215px; height: 110px; position: relative; left: 280px; padding-bottom: 30px;"></a>
      </li>
      <li class="nav-item active" id="myPage">
        <a class="nav-link" href="mypage.do?cmd=info&id=<%=id%>&info=mypage"><b>My page</b></a>
      </li>
      <li class="nav-item active">
        <form class="form-inline my-2 my-lg-2" id="searchForm"style="position: relative; left: 755px;" method="get" action="#">
	      <input class="form-control mr-sm-2" type="text" placeholder="Search">
	      <button class="btn" type="submit"><img src="images/magnifier.png"></button>
	    </form>
      </li>
      <!-- 예인: 마이페이지 추가함 -->
    </ul>
  </div>
</nav>

<!--login modal-->
	<div id="loginModal" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	  <div class="modal-content" id="modalContent">
	      <div class="modal-header" id="modal_header">
	          <h1 class="text-center">Login</h1>
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	      </div>
	      <div class="modal-body">
	          <form class="form col-md-12 center-block" method="post" action="members.do">
	            <div class="form-group">
	              <input type="hidden" value="3" name="number">
	              <input type="text" class="form-control input-lg" placeholder="아이디를 입력해주세요" name="id">
	            </div>
	            <div class="form-group">
	              <input type="password" class="form-control input-lg" placeholder="비밀번호를 입력해주세요" name="pwd">
	            </div>
	            <div class="form-group">
	              <button class="btn btn-info btn-lg btn-block">로그인</button>
	              <span><a href="index.jsp?content1=join.jsp">회원가입</a></span><span style="float: right;"><a href="#" id="findInfo">아이디/비밀번호 찾기</a></span>
	            </div>
	          </form>
	      </div>
	      <div class="modal-footer">
	          <div class="col-md-12">
	          <button class="btn btn-info" data-dismiss="modal" aria-hidden="true">취소</button>
			  </div>	
	      </div>
	  </div>
	  </div>
	</div>
	

