<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<link href="css/mainPage.css?ver=9" rel="stylesheet">
</head>
<!-- 회원가입 성공 실패 메세지 -->
<c:if test="${!empty signMsg }"><script>var signMsg = "${signMsg}";alert(signMsg);</script></c:if>
<c:if test="${!empty sessionScope.loginMsg }"><script>var loginMsg = "${sessionScope.loginMsg}";alert(loginMsg);</script>
<% session.removeAttribute("loginMsg"); %></c:if> <!-- 수정사항 -->
<c:if test="${empty list 
}">
<script type="text/javascript">
$(document).ready(function() {
	location.href = "mainList.do"
})
</script>
</c:if>

    <!-- 슬라이드쇼 부분  -->
    <div class="container-fluid">
      <div class="row">
     	 <div id="demo" class="carousel slide coco" data-ride="carousel">

		  <!-- Indicators -->
		  <ul class="carousel-indicators">
		    <li data-target="#demo" data-slide-to="0" class="active"></li>
		    <li data-target="#demo" data-slide-to="1"></li>
		    <li data-target="#demo" data-slide-to="2"></li>
		    <li data-target="#demo" data-slide-to="2"></li>
		  </ul>
		
		  <!-- 슬라이드쇼 그림 -->
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img class="s_img" src="images/s3.jpg" alt="Los Angeles">
		    </div>
		    <div class="carousel-item">
		    <img class="s_img" src="images/slide1.png" alt="New York">
		    </div>
		    <div class="carousel-item">
		      <img class="s_img" src="images/s3.jpg" alt="Chicago">
		    </div>
		    <div class="carousel-item">
		      <img class="s_img" src="images/slide1.png" alt="Chicago">
		    </div>
		  </div>
		
		  <!-- Left and right controls -->
		  <a class="carousel-control-prev" href="#demo" data-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </a>
		  <a class="carousel-control-next" href="#demo" data-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </a>
		
		</div>
      </div> 
	  <div class="row">
        <div class="col-lg-12">
          <h1 class="my-4" id="best10" > Best Picture 10 </h1>
        </div>
      </div>
     </div>
     <!-- /슬라이드쇼 부분 -->
     
     <!-- 두번째 container 시작 // 상품이미지 -->
     <div class="container-fluid">  
          <div class="row" id="goodsRow">
          	<div class="col-md-1" style="height: 150px;"></div>
          	
          	<div class="col-md-10">
          	 <div class="row" id="conRow">
          	 <c:forEach var="vo" items="${list }" varStatus="status">
          	  <div class="col">
               <div class="card" style="width: 310px; margin-bottom: 30px; height: 550px;">
                <img class="card-img-top" src="pImages/${vo.pImage }" alt="test">
                  <a class="card-img-overlay" href="#">
				    <span class="best" style="display: block;">Best${status.index+1 }</span>
				  </a>
				  <div class="card-footer" style="background-color: white;">
				  	<ul>
				  		<li id="goodsTitle">${vo.pName }</li>
				  		<li id="subname">${vo.pArtist }</li>
				  		<li id="customer">33,000원</li>
				  		<li id="price">${vo.pPrice }</li>
				  		<li style="margin-top: 5px;"><span><img src="images/hit.gif" alt="abc" /></span>
				  		<c:choose>
				  			<c:when test="${vo.pBest == 2 }">
				  				<span style="float: right;"><img src="images/icon_05.gif" alt="abc" /></span>
				  			</c:when>
				  			<c:when test="${vo.pBest == 3 }">
				  				<span style="float: right;"><img src="images/freedelivery.gif" alt="abc" /></span>
				  			</c:when>
							<c:otherwise>
								
							</c:otherwise>
				  		</c:choose>
				  		</li>
				  	</ul>
				  </div>
               </div>
              </div>
              </c:forEach>
             </div>
            <div class="col-md-1" style="height: 150px;"></div>
          </div>
     </div>
     <div class="row">
        <div class="col-lg-12">
          <h1 class="my-4" id="best10" style="position: relative; bottom: 100px;"> SALE </h1>
        </div>
      </div>
     </div>    
    <!-- 두번째 container 끝 -->
    <!-- 세번째 container 할인상품 -->
    
    <div class="container-fluid">  
          <div class="row" id="goodsRow">
          	<div class="col-md-1" style="height: 150px;"></div>
          	
          	<div class="col-md-10">
          	 <div class="row" id="conRow">
          	 <c:forEach var="vo" items="${saleList }" varStatus="status">
          	  <div class="col">
               <div class="card" style="width: 310px; margin-bottom: 30px; height: 550px;">
                <a href="#"><img class="card-img-top" src="pImages/${vo.pImage }" alt="test"></a>
                  <a class="card-img-overlay" href="#">
				    <span class="best" style="display: block;">${vo.pDiscountRate }%</span>
				  </a>
				  <div class="card-footer" style="background-color: white;">
				  	<ul>
				  		<li id="goodsTitle">${vo.pName }</li>
				  		<li id="subname">${vo.pArtist }</li>
				  		<li id="customer">33,000원</li>
				  		<li id="price">${vo.pPrice }</li>
				  		<li style="margin-top: 5px;"><span><img src="images/sale.gif" alt="abc" /></span>
				  		<c:choose>
				  			<c:when test="${vo.pBest == 2 }">
				  				<span style="float: right;"><img src="images/icon_05.gif" alt="abc" /></span>
				  			</c:when>
				  			<c:when test="${vo.pBest == 3 }">
				  				<span style="float: right;"><img src="images/freedelivery.gif" alt="abc" /></span>
				  			</c:when>
							<c:otherwise>
								
							</c:otherwise>
				  		</c:choose>
				  		</li>
				  	</ul>
				  </div>
               </div>
              </div>
              </c:forEach>
             </div>
            <div class="col-md-1" style="height: 150px;"></div>
          </div>
     </div>
     </div> 
     <!-- 세번째 container 끝 -->

