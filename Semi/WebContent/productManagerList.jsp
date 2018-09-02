<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <style type="text/css">
  	#list{
  		vertical-align: middle;
		font-family: monospace;
		font-size: 19px;
  	}
  </style>
<c:if test="${!empty msg }"><script type="text/javascript">alert('${msg}');</script></c:if>
<c:if test="${empty list }">
<script type="text/javascript">window.location.href = "adminProduct.do?adminNum=3&pageNum=1"</script>
</c:if>
<div class="container-fluid">

<div class="row" style="height: 200px;">
	<div class="col-md-12" style="padding-top: 90px;">
		<div style="text-align: center; font-family: 'Jeju Myeongjo', serif; font-size: 50px;">Product management</div>
		<ul class="pagination">
		<c:choose>
			<c:when test="${startPage > 10 }">
				<li class="page-item"><a class="page-link" href="adminProduct.do?adminNum=3&pageNum=${startPage-1 }">Previous</a></li>
			</c:when>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<li class="page-item"><a class="page-link" href="adminProduct.do?adminNum=3&pageNum=${i }&col=${col}&searchStr=${searchStr}&arrayStr=${arrayStr}">${i }</a></li>
		</c:forEach>
		<c:choose>
			<c:when test="${endPage < count }">
				<li class="page-item"><a class="page-link" href="adminProduct.do?adminNum=3&pageNum=${endPage+1 }">Previous</a></li>
			</c:when>
		</c:choose>
		</ul>
	</div>
</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped">
				<tr>
					<th width="80px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_name&arrayStr=asc">상품이름</a></th>
					<th width="100px">이미지</th>
					<th width="100px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_price&arrayStr=desc">가격</a></th>
					<th width="60px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_stock&arrayStr=desc">재고</a></th>
					<th width="60px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_kind&arrayStr=asc">종류</a></th>
					<th width="60px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_discountok&arrayStr=desc">할인여부</a></th>
					<th width="60px">작가</th>
					<th width="220px">설명</th>
					<th width="40px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_best&arrayStr=desc">Best</a></th>
					<th width="80px"><a href="adminProduct.do?adminNum=3&pageNum=1&col=p_discount_rate&arrayStr=desc">할인율</a></th>
					<th width="80px">수정하기</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td id="list">${vo.pName}</td>
					<td><img src="painting/o/${vo.pImage }" alt="${vo.pName }" style="width: 150px;"></td>
					<td id="list">${vo.pPrice } 원</td>
					<td id="list">${vo.pStock } 개</td>
					<td id="list">${vo.pKind }</td>
					<td id="list">
					<c:choose>
						<c:when test="${vo.pDiscountok == 1}">
							할인상품
						</c:when>
						<c:otherwise>
							비할인상품
						</c:otherwise>
					</c:choose>
					</td>
					<td id="list">${vo.pArtist }</td>
					<td id="list">${vo.pExplain }</td>
					<td id="list">
					<c:if test="${vo.pDiscountok == 1}">
						<img src="images/sale.gif" alt="" />
					</c:if>
					<c:choose>
						<c:when test="${vo.pBest == 1}">
							<img src="images/best.gif" alt="" />
						</c:when>
						<c:when test="${vo.pBest == 2}">
							<img src="images/ingi.gif" alt="" />
						</c:when>
						<c:when test="${vo.pBest == 3}">
							<img src="images/freedelivery.gif" alt="" />
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose>
					</td>
					<td id="list">${vo.pDiscountRate }%</td>
					<td id="list"><a href="adminProduct.do?adminNum=4&pNum=${pNum }">수정하기</a></td>
				</tr>
				</c:forEach>
			</table>
			

		</div>
	</div>
</div>
