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

<title>MyPage</title>
<style>
a:hover {
	color: black;
}
ul {
	list-style: none;
	text-align: center;
}
</style>
<div class="container" style="
    margin-bottom: 441px;
">
<div class="row">
	<h2>MY PAGE</h2><br>
</div>

<div class="row">
	<br>
</div>
	<div class="row">
		<%
			String id = (String) session.getAttribute("id");
		%>
		<table class="table table-hover">
			<tr>
				<th colspan="4">나의 주문처리 현황</th>
			</tr>
			<tr>
				<td><div><br>
						<ul>
							<li>배송준비중</li>
							<c:choose>
								<c:when test="${state1 >= 1 }">
									<li><a href="mypage.do?cmd=orderlist&id=${id }">${state1 }</a></li>
								</c:when>
								<c:when test="${state1 == 0 }">
									<li>-</li>
								</c:when>
							</c:choose>
						</ul>
					</div></td>
				<td><div><br>
						<ul>
							<li>배송중</li>
							<c:choose>
								<c:when test="${state2 >= 1 }">
									<li><a href="mypage.do?cmd=orderlist&id=${id }">${state2 }</a></li>
								</c:when>
								<c:when test="${state2 == 0 }">
									<li>-</li>
								</c:when>
							</c:choose>
						</ul>
					</div></td>
				<td><div><br>
						<ul>
							<li>배송완료</li>
							<c:choose>
								<c:when test="${state3 >= 1 }">
									<li><a href="mypage.do?cmd=orderlist&id=${id }">${state3 }</a></li>
								</c:when>
								<c:when test="${state3 == 0 }">
									<li>-</li>
								</c:when>
							</c:choose>
						</ul>
					</div></td>
				<td>
					<div>
						<ul>
							<li><br></li>
							<li>취소:
							<c:choose>
								<c:when test="${state0 >= 1 }">
									<a href="mypage.do?cmd=orderlist&id=${id }">${state0 }</a>
								</c:when>
								<c:when test="${state0 == 0 }">
									-
								</c:when>
							</c:choose> 
							</li>
							<li>반품: <!-- 반품승인중 -->
							<c:choose>
								<c:when test="${state4 >= 1 }">
									<a href="mypage.do?cmd=orderlist&id=${id }">${state4 }</a>
								</c:when>
								<c:when test="${state4 == 0 }">
									-
								</c:when>
							</c:choose>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="col-md-2"></div>

<div class="row">
	<div class="col-md-6">
		<table class="table table-hover">
			<tr>
				<th><a href="mypage.do?cmd=orderlist&id=${id }"> <span
						style="size: 50; color: black;">ORDER</span><br> <span
						style="size: 20; color: black;">주문 내역 조회하기</span>
				</a></th>
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th><a href="mypage.do?cmd=pointlist&id=${id }"> <span
						style="size: 50; color: black;">POINT</span><br> <span
						style="size: 20; color: black;">적립금 내역 조회하기</span>
				</a></th>
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th><a href="mypage.do?cmd=customerboardlist&id=${id }"> <span
						style="size: 50; color: black;">FAQ</span><br> <span
						style="size: 20; color: black;">고객센터 문의게시물 조회하기</span>
				</a></th>
			</tr>
		</table>
	</div>
	<div class="col-md-6">
		<table class="table table-hover">
			<tr>
				<th><a href="mypage.do?cmd=info&id=${id }&info=info"> <span
						style="size: 50; color: black;">INFO</span><br> <span
						style="size: 20; color: black;">회원 정보 확인하기</span>
				</a></th>
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th><a href="mypage.do?cmd=update&id=${id }"> <span
						style="size: 50; color: black;">UPDATE</span><br> <span
						style="size: 20; color: black;">회원 정보 수정하기</span>
				</a></th>
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th><a href="mypage.do?cmd=review&id=${id }"> <span
						style="size: 50; color: black;">REVIEW</span><br> <span
						style="size: 20; color: black;">상품후기 확인하기</span>
				</a></th>
			</tr>
		</table>
	</div>
</div>
</div>