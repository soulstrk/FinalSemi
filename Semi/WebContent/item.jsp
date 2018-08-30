<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 부트스트랩  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<style>

	.grid_container{
	
	padding-top: 50px;
	display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-gap: 50px;
	
	}
	
	.center{
		
		padding: 0px 0px 0px 100px
	
	}
	
	
	.col-md-5 ul li{
	
		list-style:none;
		float:left;
		margin-right:20px;
		
	
	}
	
	a{
	
		color:gray;
		
	
	}


</style>
<title>Insert title here</title>



</head>
<body>






<div class="row" style="border: 1px solid red;">
<div class="col-md-5" ></div>

<div class="col-md-5">
<h3>서양화 & 동양화</h3>

	<ul>
		<h5><li><a href="#">서양화</a></li></h5>
		<h5><li><a href="#">동양화</a></li></h5>


	</ul>
</div>
</div>



<div class="center">
<div class="grid_container">


<c:forEach var ="vo" items="${requestScope.list}">

		<div class="col">
               <div class="card" style="width: 400px; height:700px">
                <a href="#"><img class="card-img-top" src="/Semi/painting/o/${vo.p_image}" alt="" style="height:500px"></a>
                <div class="card-body text-center" style="height: 100px; margin-top: 20px;">
                  <h3>
                    <a href="#">${vo.p_name}</a>
                  </h3>
                  <h5>${vo.p_price}원<img alt="cartImg" src="images/cart.png"></h5>
                </div>
                <div class="card-footer">
                 <h5><a href="#">구매하러 가기</a><img src="images/hwa.svg"></h5>
                </div>
               </div>
              </div>

</c:forEach>

</div>
</div>





</body>
</html>