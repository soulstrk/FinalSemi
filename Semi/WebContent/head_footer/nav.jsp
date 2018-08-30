<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 추가 Sidebar (최근 본 상품 5개) -->  
<script src="js/yi_js.js?ver=1"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> 
<%	String id=(String)session.getAttribute("id"); %>
   <div>
	   <div class="w3-sidebar w3-bar-block w3-border-right" style="display:none; top:0px; width:700px;" id="mySidebar">
		  <button onclick="w3_close()" class="w3-bar-item w3-large">Close &times;</button>
		  <div id="view" style="color:gray;">
		  </div>
	   </div>
	   <input type="hidden" name="id" id="id" value="<%=id%>">
	   <button id="btn1" class="w3-button w3-gray w3-xlarge" onclick="getView()" style="position:fixed;z-index: 1;top:0px;"><span style="color:white;">☰</span></button>
   </div>
   
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
        <a class="nav-link" href="cart.do?cmd=cart&id=<%=id%>"><b>Cart</b></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link active" href="customerboard.do?cmd=list"><b>FAQ</b></a>
      </li>
      <li class="nav-item active">
        <div class="dropdown">
        <a class="nav-link active dropdown-toggle" href="customerboard.do?cmd=list" data-toggle="dropdown"><b>Picture</b>
		  <span class="caret"></span></a>
		  <ul class="dropdown-menu">
		    <li><a href="opainting.do?cmd=oriental&p_kind=서양화">동양화</a></li>
		    <li><a href="opainting.do?cmd=oriental&p_kind=동양화">서양화</a></li>
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
			    <a class="dropdown-item" href="adminProduct.do?adminNum=3&pageNum=1">상품관리</a>
			  </div>
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
					 $('#searchForm').css('left','655px'); 
					 $('img[src="images/hd_logo.png"]').css('left','150px'); 
					 $('#myPage').css('left','580px');
					 $('#live').css('left','410px');
				})
			</script>
		<%
			} else{	
		%>
			<a class="nav-link active" href="index.jsp?content1=join.jsp" style="display: none;"><b>회원가입</b></a>
		<% }} %>
      </li>
      <li class="nav-item active">
        <a class="navbar-brand" href="../mainList.do"><img src="images/hd_logo.png" style="width: 223px; height: 45px; position: relative; left: 250px; padding-bottom: 0px;"></a>
      </li>
      <li class="nav-item active" style="position: relative; left: 350px; width: 120px;" id="live">
        <a class="navbar-brand" href="#"><img alt="" src="" class="rounded-circle" id="liveImage" style="width: 90px; height: 90px;"></a>
      </li>
      <li class="nav-item active" style="position: relative; left: 350px; width: 120px;" id="live">
        <span id="liveSpan"></span>
      </li>
      <li class="nav-item active" id="myPage">
        <a class="nav-link" href="mypage.do?cmd=info&id=<%=id%>&info=mypage&date=x&date1=0&date2=0"><b>My page</b></a>
      </li>
      <li class="nav-item active">
        <form class="form-inline my-2 my-lg-2" id="searchForm"style="position: relative; left: 555px;" method="post" action="search.do?pageNum=1">
	      <input class="form-control mr-sm-2" type="text" placeholder="Search" name="search" id="searchP" onkeyup="searchW()" autocomplete=off>
	      <button class="btn" type="submit"><img src="images/magnifier.png"></button>
	    </form>
      </li>
    </ul>
    <div style="background-color:white; color: black; border: 1px solid; position: relative; left: 500px; top:280px; width: 240px; z-index: 1; height: 500px; display: none; overflow: scroll; font-family: 'Nanum Gothic', serif; font-weight: bold;" id="testing"></div>
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
	              <span><a href="index.jsp?content1=join.jsp">회원가입</a></span><span style="float: right;"><a href="index.jsp?content1=findInfo.jsp" id="findInfo">아이디/비밀번호 찾기</a></span>
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
	
	
<script type="text/javascript">

var cnt = 3;
setInterval(function() {
	$(document).ready(function liveSearch() {
		$.ajax({
			url : 'live_search.do',
			dataType : 'json',
			Type : 'post',
			success: function(data) {
					cnt = cnt % 3;
					var pNum = data.liveList[cnt].pNum;
					var pImage = data.liveList[cnt].pImage;
					var pName = data.liveList[cnt].pName;
					$('#liveImage').attr('src','pImages/'+pImage);
					$('#liveSpan').html('<h3>'+(cnt+1)+'</h3><a href="opainting.do?cmd=detail&p_num='+pNum+'" style="font-size:25px;">'+pName+'</a>');
					cnt++;
				}
			});
		})
}, 2000)


function draw(msg) {
	$('.test').text(msg);
}

function searchW() {
	 var word = $('#searchP').val();
	 if(word == ""){
		 $('#testing').css('display','none');
		 return;
	 }
	 $('#testing').css('display','block');
	 $.ajax({
		url : "searchWord.do?cmd=1&word="+word,
		dataType : 'json',
		success: function(data) {
			if(data.msg == 1){
				return;
			}else{
				$('#testing').text(' ');
				for (var i = 0; i < data.list.length; i++) {
					var pNum = data.list[i].pNum;
					var pName = data.list[i].pName;
					var pPrice = data.list[i].pPrice;
					var pImage = data.list[i].pImage;
					$('#testing').append('<a href="opainting.do?cmd=detail&p_num='+pNum+'">'+pName+' &nbsp&nbsp;&nbsp;&nbsp;'+pPrice+'원</a><br>');
					$('#testing').append('<img src=pImages/'+pImage+' style="width:180px; height:140px;"><br><br>');
				}
			}
		}
	});
}
</script>	
	

