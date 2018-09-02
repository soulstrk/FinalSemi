<%@page import="ljy.vo.MembersVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <% String id=(String)session.getAttribute("id");%>
<c:if test="${!empty cartMsg }">
<script type="text/javascript">
	$(document).ready(function() {
		$("#cartModal").modal();
	})
</script>
</c:if>

<style>
#productDetail, .tmb-info, .thumb-wrap{

	width:550px;f

}

.tmb-info, .tmb-info-container, .thumb-wrap, .thumb-ctrl{
    padding-top: 10px;
    padding-bottom: 10px;
    text-align: center;
}


dl, ul, ol, menu, li{
    list-style: none;
}

a{

	text-decoration:none;
	

}

.frofel{

	
    margin: 14px 180px;
    border: 1px solid black;
    width: 246px;
    height: 40px;


}

.frofel a{

	line-height:39px;

}

.right_centainer{

	width:500px;
	height: 490px;
	margin: 26px 650px 0 0;
	float:right;
}

.first_box dt{

	float:left;

}

#comments td{
	font-size: 22px;	
}
#comments th{
	font-size: 22px;	
}


</style>

<script type="text/javascript">

var sell_price;
var amount;

function init () {
	sell_price = document.form.sell_price.value;
	amount = document.form.amount.value;
	document.form.sum.value = sell_price;
	change();
}

function add () {
	hm = document.form.amount;
	sum = document.form.sum;
	hm.value ++ ;

	sum.value = parseInt(hm.value) * sell_price;
}

function del () {
	hm = document.form.amount;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			sum.value = parseInt(hm.value) * sell_price;
		}
}

function change () {
	hm = document.form.amount;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
	sum.value = parseInt(hm.value) * sell_price;
} 




function sendorderpage(){
	
	var price=document.getElementsByName("sum")[0].value;
	var amount= document.form.amount.value; // 폼에 있으면 이런식으로도 가능하다.
	var sprice =(price*0.2);
	location.href="index.jsp?content1=orderpage.jsp&p_num=${vo.p_num}&p_name=${vo.p_name}&p_price=${vo.p_price}&p_partist=${vo.p_artist}&p_img=${vo.p_image}&sprice="+sprice+"&price="+price+"&amount="+amount;
}


function sendCart(){
	var amount= document.form.amount.value; // 폼에 있으면 이런식으로도 가능하다.
	<% if(session.getAttribute("id") != null){ %>
	location.href="cart.do?cmd=insert&c_p_num=${vo.p_num}&amount="+amount;
	<% }else { %>
	alert('로그인 후에 이용 가능합니다.');
	return;
	<% } %>
}
</script>


</head>

<body onload="init();">

<div class="tmb-info-container cb_clear" style="border: 1px solid black;">
		
		
		<!--상세 보기 왼쪽 사진  -->
		<!--오른쪽 부분  -->
		
		<div class="right_centainer">
		
    <div class="right_hbox" style="border-bottom: 2px solid black;height:  40px;">
        
        <div class="rtitle" name="p_name" style="  float: left;">
        	${vo.p_name}
        </div>
   
    </div>


<dl class="first_box">

    <dt style="
    float: left;">
    	소비자가
    </dt>
    
    <dd style="text-decoration:line-through; color:red;">100.000 won</dd>
</dl>


<dl class="first_box">
    
    <dt>판매가</dt>
    <dd>${vo.p_price} won</dd>
    
</dl>

<dl class="first_box">

    <dt>작가명</dt>
    <dd>${vo.p_artist} 작가</dd>

</dl>


<dl class="first_box">

    <dt>수량</dt>
    <dd>${vo.p_stock}개</dd>

</dl>


<form name="form" method="get">
수량 : <input type="hidden" name="sell_price" value="${vo.p_price}">
<input type="text" name="amount" value="1" size="3" onchange="change();">
<input type="button" value=" + " onclick="add();"><input type="button" value=" - " onclick="del();"><br>

금액 : <input type="text" name="sum" size="11" readonly="readonly">원
</form>


 
<div class="prd-btns type-2 cb_clear" style="
    margin-top: 144px;
    border-top: 1px solid gray;
    border-bottom: 1px solid gray;
    text-align: center;
">
<a class="buy" href="javascript:sendorderpage();" alt="주문하기" style="
    float: left;
    width: 132px;
    margin-right: -132px;
    border-color: #333;
    background: #f9ebeb;
">바로 구매하기<span class="ico"></span></a>
<a class="cart" href="javascript:sendCart()" alt="장바구니" style="
    border-color: #a0a0a0;
    color: black;
">장바구니 담기<span class="ico"></span></a>
<a class="wish" href="#" alt="위시리스트" style="
    border-color: #a0a0a0;
    color: black;
">관심상품 등록<span class="ico"></span></a>
</div>
    
		
</div>
		
		
		<div class="thumb-wrap">
		
			<div class="thumb" style="margin-left: 511px; width:450px;">
				
					<div class="thumb-container">
					
						<ul style="width: 540px;">
							<li class="origin_img">
								
								<img src="painting/o/${vo.p_image}"style="width:540px; height:340px;">
							
							</li>
						
						</ul>
							
					</div>
					
					<div style="margin-left: 167px;">
						
						상품 이미지를 클릭
					
					</div>
					
						<div class="frofel" style="border:1px solid black;">
						
							<a href="#">작가 프로필</a>
						
						</div>
					
			</div>
		</div>
</div>

<div class="container">
	<div class="row" style="padding-top: 40px;">
	<div><h2 style="color:red;">상품후기</h2></div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div style="border: 1px solid white; height: 400px; overflow: scroll;" class="container">
				<table class="table table-striped" id="comments">
					<tr>
						<th width="100px">아이디</th>
						<th width="*" style="text-align: center;">상품후기</th>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
		<% if(id != null){ %> 
			<c:forEach var="v" items="${list1 }">
				<c:choose>
					<c:when test="${v.p_num== vo.p_num}">
						<input type="hidden" id="reviewok" value="${v.p_num }">
					</c:when>
					<c:otherwise>
						<input type="hidden" id="reviewok" value="-1">
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<input type="text" placeholder="상품후기를 입력해주세요." id="com" style="width: 400px;"><button type="button" onclick="comInsert()">입력</button>
		<% } else{	%>
			<input type="text" placeholder="로그인 이후에 이용 가능합니다." id="com" style="width: 400px;" disabled="disabled"><button type="button">입력</button>
		<% } %>		
		</div>
	</div>
</div>

<script type="text/javascript">

	$(document).ready(function() {
		getList();
		insertv();
	})
	
<%	
	if(id!=null){
%>		
	var xhr=null;
	function insertv(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=insertView;
		xhr.open('get','cart.do?cmd=insertView&id=<%=id%>&p_num=${vo.p_num}',true);
		xhr.send();
	};	
	
	function insertView(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data=xhr.responseText;
			var json=JSON.parse(data);
			if(json.n>0){
				return;
			}
		}		
	}
<%	
	}
%>
	
	function getList() {
		$.ajax({
			url : "productComments.do?cmd=getList&pNum=${vo.p_num}",
			Type : 'post',
			dataType : 'json',
			success : function(data){
				for (var i = 0; i < data.arr.length; i++) {
					$('#comments').append('<tr><td>'+data.arr[i].id+'</td><td style="text-align : center;">'+data.arr[i].content+'</td></tr>');
				}
			}
		});
	}
	
	function comInsert() {
		var reviewok=document.getElementById("reviewok");
		if(reviewok.value == "-1"){
			alert("구매 후 사용가능한 서비스입니다.");
			return;
		}
		
		
		var comment = $('#com').val();
		$.ajax({
			url : "productComments.do?cmd=insert&comment="+comment+"&pNum=${vo.p_num}",
			dataType : 'json',
			Data : 'get',
			success: function(data) {
				$('#comments').append('<tr><td>'+data.id+'</td><td style="text-align : center;">'+data.content+'</td></tr>');
				alert('댓글 작성 성공');
				$('#com').val(' ');
			}
		})
	}
</script>
</body>
<!-- 장바구니 모달 -->
	<div id="cartModal" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	  <div class="modal-content" id="modalContent">
	      <div class="modal-header" id="modal_header">
	          <h2 class="text-center">장바구니에 물건이 담겼습니다.</h2>
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	      </div>
	      <div class="modal-body">
	              <button class="btn btn-info btn-lg btn-block" onclick="location.href='opainting.do?cmd=detail&p_num=${vo.p_num}'">쇼핑 계속하기</button>
	              <button class="btn btn-info btn-lg btn-block" onclick="location.href='cart.do?cmd=cart'">장바구니 보기</button>
	      </div>
	      <div class="modal-footer">
	          <div class="col-md-12">
			  </div>	
	      </div>
	  </div>
	  </div>
	</div>