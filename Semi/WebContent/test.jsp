<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩  -->


<style type="text/css">

.SMP-container {
    width: 1200px;
    margin: 0 auto;
}

.SMP-main-prd .SMP-main-prd-container {
    overflow: hidden;
    width: 1300px;
    margin-left: -68px;
    margin-right: -30px;
}

.SMP-main-prd .SMP-main-prd-container > ul > li {
    float: left;
    width: 255px;
    margin: 0 30px;
    height: 520px;
}


.SMP-main-prd .SMP-main-prd-container ul li .thumbnail {
    position: relative;
    margin-bottom: 10px;
}



.SMP-main-prd .SMP-main-prd-container ul li p.icon {
    height: 15px;
    margin-bottom: 5px;
}

.SMP-main-prd .SMP-main-prd-container ul li p.pname {
    margin-top: 10px;
    margin-bottom: 3px;
    font-size: 12px;
    font-weight: 600;
    font-family: "Yoon Gothic", "Open Sans", "malgun gothic", "Nanum Gothic", 돋움, dotum;
    color: rgb(136, 136, 136);
    letter-spacing: -1px;
    
    border-bottom: 1px solid #bfbfbf;
}

.addcode {
    font-size: 11px;
}


.SMP-main-prd .SMP-main-prd-container ul li p.subname {
    color: rgb(127, 127, 127);
    font-size: 12px;
    font-family: "Nanum Gothic", 돋움, dotum;
    height: 10px;
}



dl, ul, ol, menu, li {
    list-style: none;
}

.SMP-main-prd .SMP-main-prd-container ul li p.price span.SMS_main_display_origin_p {
    color: #a8a8a8;
    font-size: 14px;
    text-decoration: line-through;
    padding-right: 15px;
}

.SMP-main-prd .SMP-main-prd-container ul li p.price span {
    color: #F44336;
    
}

.SMP-container {
    width: 1200px;
    margin: 0 auto;
}

.ttl-sort {
    padding-top: 54px;
    margin-bottom: 60px;
}

.total-sort-container {
    border-bottom: 1px solid transparent;
    border-color: #999;
    padding-bottom:75px;
    ding-right: 14px;
}

.total-sort-container > ul > li {
   
   float:right;
   padding:10px;
}

#cate-tit .cate-tit-container h2 {
    border-bottom: 2px solid #333;
    text-align: left;
    font-size: 44px;
    color: #333;
    margin-bottom: 0;
}

.category-list-container > span{

	display: inline-block;
    zoom: 1;
    width: 175px;
    text-align: center;
    padding-left: 11px;
    padding-right: 11px;

}

#se{

	text-align: center;

}


</style>

</head>
<body>

<div id="cate-tit">

	<div class="SMP-container">
	
		<div class ="cate-tit-container">
			<h2>수채화</h2>
		
		</div>
	
	</div>


</div>

<!-- 카테코리 상자  -->

<div class=category-list>
	
	<div class="SMP-container" style="border: 1px solid black;">
	
	
		<div class="category-list-container">
		
			<span><a href="opainting.do?cmd=oriental">서양화</a></span>
			<span><a href="">동양화</a></span>
		</div>
	
	
	
	
	</div>

</div>






<!-- product list --><!-- product list --><!-- product list -->

<div class="product-list" >

<div class="SMP-container">

<div class="SMP-main-prd" >

<div class="SMP-main-prd-container">


<ul class="cb_clear">

	<c:forEach var ="vo" items="${requestScope.list}">
	<li id="SMS_pid" class="product" >
		<div class="thumbnail">
		
		<!--  상세페이지로 가는 곳!!!! -->
		
		
	<a href="opainting.do?cmd=detail&p_num=${vo.p_num}"><img class="card-img-top" src="/Semi/painting/o/${vo.p_image}" alt=""></a>
		</div>
	
	
	<p class="icon" alt="아이콘">
	
		<span class="MK-product-icons">
		
			<img src="http://www.iaanart.com/shopimages/iaanart05/prod_icons/1?1453792063"></a>
		</span>
	</p>
	
	<p class="pname" alt="상품명">${vo.p_name}</p>
	
	<p class="addcode" alt="작품명">이안아트</p>
	<p class="subname" alt="작품설명">전체사이즈</p>
	
	<p class="price">
		<span class="SMS_main_display_origin_p" data-originprice="120000">
		
			20000
		</span>
		
		<span class="SMS_main_display_sales_p" data-salesprice="120000">
		
			120000 won
		</span>
	
	</p>
	</li>
	</c:forEach>
	



</ul>
</div>


</div>


</div>



</div>





<!-- 페이지 번호 페이징 !!!!  -->
<div style="text-align: center;">
	<c:forEach var="i" begin="${startPageNum}" end="${endPageNum}">
		
		<c:choose>
		
			<c:when test="${i==pageNum}">
			
				<a href="opainting.do?cmd=oriental&pageNum=${i}
				&search=${param.search}&keyword=${param.keyword}">
				
					<span>[${i}]</span>
				
				</a>
			</c:when>
			<c:otherwise>
			
				<a href="opainting.do?cmd=oriental&pageNum=${i}
				&search=${param.search}&keyword=${param.keyword}">
				
					<span>[${i}]</span>
				
				</a>
			
			</c:otherwise>
		
		</c:choose>
	
	</c:forEach>

</div>





<div id ="se" name = "se" >


<form action="opainting.do?cmd=oriental" method="post">
<select name="search">

	<option value="p_name">작품 이름</option>
	<option value="p_artist">작가</option>

</select>

<input type="text" name="keyword">
<input type="submit" value="검색">

</form>



</div>






</body>
</html>