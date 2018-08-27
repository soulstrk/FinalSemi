<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/memberManagement.css?ver=1" rel="stylesheet">

<c:if test="${num eq 1 }"> <!--  attr('href','admin.do?col=gender&adminNum=2&num=2'); -->
<script type="text/javascript">
	alert('gdgd');
	$('#abc').text('abc');
</script>
</c:if>
<div class="container-fluid" style="padding-top: 150px;">
	<div class="row">
	<div class="col-md-3"></div>
	<!-- 검색창 -->
	<div class="col-md-6">
		<input type="text" class="form-control form-control-lg" id="myInput" placeholder="찾고자 하는 검색어를 입력해주세요.">
	</div>
	<div class="col-md-3"></div>
	<!-- /검색창 -->
	
	<!-- 회원정보 목록창 -->
	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<table class="table table-striped table-hover" id="myTable">
				<tr>
					<th>아이디</th>
					<th><a href="admin.do?col=gender&adminNum=2&num=1">성별</a></th>
					<th><a href="admin.do?col=name&adminNum=2&num=1">이름</a></th>
					<th>주소</th>
					<th>핸드폰번호</th>
					<th><a href="admin.do?col=email&adminNum=2&num=1">이메일</a></th>
					<th><a href="admin.do?col=point&adminNum=2&num=1">포인트</a></th>
					<th><a href="admin.do?col=regdate&adminNum=2&num=1">가입날짜</a></th>
					<th>탈퇴</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr id="content">
					<td>${vo.id }</td>
					<td>${vo.gender }</td>
					<td>${vo.name }</td>
					<td>${vo.addr }</td>
					<td>${vo.phone }</td>
					<td>${vo.email }</td>
					<td>${vo.point }</td>
					<td>${vo.regdate }</td>
					<td><a href="" onclick="deleteMember('${vo.id}')"><img src="images/cancel.png" alt="cancel"/></a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-1"></div>
	</div>
</div>






<!-- 정렬 toggle 모드를 만들기 위한 스크립트 -->
	<c:if test="${arrayNum eq 1 }"> 
	<script type="text/javascript">
	$(document).ready(function() {
		$('a:contains("성별")').attr('href','admin.do?col=gender&adminNum=2&num=2');
		$('a:contains("이름")').attr('href','admin.do?col=name&adminNum=2&num=2');
		$('a:contains("이메일")').attr('href','admin.do?col=email&adminNum=2&num=2');
		$('a:contains("포인트")').attr('href','admin.do?col=point&adminNum=2&num=2');
		$('a:contains("가입날짜")').attr('href','admin.do?col=regdate&adminNum=2&num=2'); 
	});
	</script>
	</c:if>
	<c:if test="${arrayNum eq 2 }"> <!--  attr('href','admin.do?col=gender&adminNum=2&num=2'); -->
	<script type="text/javascript">
	$(document).ready(function() {
		$('a:contains("성별")').attr('href','admin.do?col=gender&adminNum=2&num=1');
		$('a:contains("이름")').attr('href','admin.do?col=name&adminNum=2&num=1');
		$('a:contains("이메일")').attr('href','admin.do?col=email&adminNum=2&num=1');
		$('a:contains("포인트")').attr('href','admin.do?col=point&adminNum=2&num=1');
		$('a:contains("가입날짜")').attr('href','admin.do?col=regdate&adminNum=2&num=1');
	});
	</script>
	</c:if>
	
<!-- 필터 -->
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable #content").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

function deleteMember(id) {
	if(confirm(id+"회원정보를 삭제하시겠습니까?")){
	$(document).ready(function() {
		$.ajax({
			url : "admin.do?id="+id+"&adminNum=4",
			dataType : 'json',
			Type : 'get',
			success: function(data) {
				alert('삭제 성공');
				}
			})
		})
	}
	
}
</script>
	
