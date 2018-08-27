<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/productEnroll.css" />
<c:if test="${!empty msg }">
<script type="text/javascript">
	alert('${msg}');
</script>
</c:if>

<div class="container">
	<div class="row" style="height: 300px;">
		<div class="col-md-12">
			<div style="text-align: center;" id="ppp">Product registration</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
		<form action="productUpdate.do" method="post" enctype="multipart/form-data">
			<div class="input-group mb-3">
			   <div class="input-group-prepend" style="width: 200px;">
			     <span class="input-group-text">상품이름</span>
			   </div>
			   <input type="text" class="form-control" name="pName" value="${vo.pName }">
			   <input type="hidden" name="pNum" value="${vo.pNum }">
			</div>
			
			<div class="input-group mb-3">
			   <div class="input-group-prepend" style="width: 200px;">
			     <span class="input-group-text">상품가격</span>
			   </div>
			   <input type="text" class="form-control" name="pPrice" value="${vo.pPrice }">
			</div>
			<div class="input-group mb-3">
			   <div class="input-group-prepend" style="width: 200px;">
			     <span class="input-group-text">입고수량</span>
			   </div>
			   <input type="text" class="form-control" name="pStock" value="${vo.pStock }">
			</div>
			<div class="input-group mb-3">
			   <div class="input-group-prepend" style="width: 200px;">
			     <span class="input-group-text">작가</span>
			   </div>
			   <input type="text" class="form-control" name="pArtist" value="${vo.pArtist }">
			</div>
			<div class="input-group mb-3" >
			   <div class="input-group-prepend" id="dc" style="width: 200px; display: none;">
			     <span class="input-group-text">할인율 (%)</span>
			   </div>
			   <input type="text" class="form-control dc" name="pDiscountRate" disabled="disabled" value="${vo.pDiscountRate }">
			</div>
			<div class="form-check">
			  <label class="form-check-label">
			  	<span class="dc">할인여부</span><br>
			    <c:if test="${vo.pDiscountok == 1 }">
					<input type="radio" class="form-check-input" value="1" name="pDiscountok" id="dcok1" onclick="abc()" checked="checked">O<br>
			    <input type="radio" class="form-check-input" value="-1" name="pDiscountok" id="dcok2" onclick="abc()">X			  		
			  	</c:if>
			  	<c:if test="${vo.pDiscountok == -1 }">
					<input type="radio" class="form-check-input" value="1" name="pDiscountok" id="dcok1" onclick="abc()">O<br>
			    <input type="radio" class="form-check-input" value="-1" name="pDiscountok" id="dcok2" onclick="abc()" checked="checked">X			  		
			  	</c:if>
			  </label>
			  <div class="form-check">
			  <label class="form-check-label">
			  	<span class="dc">인기상품,무료배송 설정</span><br>
			    <input type="radio" class="form-check-input" value="2" name="pBest" id="dcok1" onclick="abc()">인기상품<br>
			    <input type="radio" class="form-check-input" value="3" name="pBest" id="dcok2" onclick="abc()">무료배송
			  </label>
			</div>
			</div>
			<div class="form-check">
			  <label class="form-check-label">
			  	<span class="dc">상품 카테고리</span><br>
			    <input type="radio" class="form-check-input" value="동양화" name="pKind">동양화<br>
			    <input type="radio" class="form-check-input" value="서양화" name="pKind">서양화
			  </label>
			</div><br>
			<div class="form-group">
			  <label for="comment">상품설명</label>
			  <textarea class="form-control" rows="5" id="comment" name="pExplain">${vo.pExplain }</textarea>
			</div>
			<br>
			<input type="submit" value="상품등록" style="padding: 22px;"/>
		</form>
		</div>
		</div>
	<div class="col-md-3"></div>
	<div class="row" style="height: 350px;"></div>
</div>
	<!-- <form action="../adminEnroll.do" method="post" enctype="multipart/form-data">
		이름 <input type="text" name="name"/><br>
		가격 <input type="text" name="price"/><br>
		재고 <input type="text" name="stock"/><br />
		종류 <input type="text" name="kind"/><br />
		할인여부<br>
		O<input type="radio" value="1" name="discountok">X<input type="radio" value="-1" name="discountOk"><br />
		아티스트<input type="text" value="" name="artist"/><br />
		설명<input type="text" value="" name="explain"/><br />
		할인율<input type="text" value="" name="discountRate"/><br />
		
		<input type="file" name="file" id="file"/>
		<input type="submit" value="전송" />
	</form> -->
<script type="text/javascript">
function abc() {
	$(document).ready(function() {
		if($('#dcok1').is(':checked')){
			$('#dc').css('display','block');
			$('.dc').attr('disabled',false);
		}
		if($('#dcok2').is(':checked')){
			$('#dc').css('display','none');
			$('.dc').attr('disabled',true);
		}
	});
}
</script>
