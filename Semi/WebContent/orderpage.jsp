<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style>
#a{

	    border-bottom: 2px solid;

}

#order_word{

	text-align: center;

}

#h_1{

	border-bottom: 1px solid #a6a6a6;
    border-top: 1px solid;
    
 }
 
 #h_3{
 	
 	    background: #f6f6f6;
    border-bottom: 1px solid;
 
 }
 
 
 #order_imformaion{
 
 	text-align: center;
 
 }
 
 
 #order_box{
 
 	border: 1px solid gray;
 
 
 }
 
 textarea {
    overflow: auto;
    resize: vertical;
    margin-bottom: 50px;
}

#button{

	text-align:center;
	padding: 0 210px;
}

#order_ok{
	
	    box-shadow: none;
    background: black;
    color: white;
    width: 170px;
    height: 50px;
    outline: none;
    border: none;
    margin-top: 40px;

}


#order_false{


	    box-shadow: none;
    background: white;
    color: black;
    width: 170px;
    height: 50px;
    outline: none;
    border: 1px solid black;
    margin-top: 40px;

}

.row {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -15px;
    margin-left: -15px;
    margin-top: 15px;
}

div{

	margin-bottom: 10px;
	
}
	


</style>





</head>


<%

	request.setCharacterEncoding("utf-8");
	String p_num = request.getParameter("p_num");
	System.out.println("p_num:"+p_num);
	String p_name = request.getParameter("p_name");
	String p_price = request.getParameter("p_price");
	String p_partist = request.getParameter("p_partist");
	
	String p_img = request.getParameter("p_img");
	String price = request.getParameter("price");
	String sprice = request.getParameter("sprice");//적립금
	String amount = request.getParameter("amount");
	
	
	// 세션 아이디 담기
	session.setAttribute("id", "admin");
	%>
	
	
<body onload="checkpoint();">


<script type="text/javascript">
	var tpoint;
	var xhr = null;
	var point = 0;
	var price;
	
	function checkpoint(){
		
		
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback;
		xhr.open('get','pcheck.do?cmd=cpoint',true);
		xhr.send();
		
	}
	
	function callback(){
		
		
		if(xhr.readyState==4 && xhr.status==200){
			
			var xml = xhr.responseXML;
			var p = xml.getElementsByTagName("point")[0];
			point = p.firstChild.nodeValue;

			tpoint = document.getElementsByName("totalp")[0];
			
			tpoint.value = point;
			
			price = document.getElementsByName("price")[0];
		
			price.innerHTML += <%=price%>;
			
			var p_price = document.getElementsByName("p_price1")[0];
			
			p_price.value=<%=price%>;
		
			
			
			
		}
		
		
	}
	
	var Price=<%=price%>;
	
	function cal1(event){
		
		var mpoint = document.getElementsByName("mpoint")[0]; // 사용할 포인트
	 	var ypoint = document.getElementsByName("totalp")[0]; // 총포인트 */
		var yprice= document.getElementsByName("price")[0]; //결제 금액 나오는 부분
		
		p_price = document.getElementsByName("p_price1")[0]; // form 으로 보낼 가격 결제 금액
		
		ypoint.value = point - mpoint.value;
		
		var viewprice = Price- mpoint.value;
		
		
		yprice.innerHTML = viewprice;
		
		p_price.value = viewprice;
		
		
		
	
		
		if(tpoint.value<0){
			
			alert("포인트를 사용할수 없습니다.");
			ypoint.value =0;
			viewprice=0;
			yprice.innerHTML = viewprice;
			
		}
		
		
	}



	function change1(obj){
		
		var value = obj.value;
		
		if(value=='직접입력'){
			alert(value);
			var select = document.createElement("input");
			
			select.setAttribute("type","text");
			document.body.appendChild(select);
			
		}
		
		
	}
	
	
	function checkit(){
		
		if(!document.getElementById("email1").value){
			
			alert("이메일을 입력하세요");
			document.getElementById("email1").focus();
			return false;
			
			
		}else if(!document.getElementsByName("phone1")[0].value){
			
			alert("번호를 입력하세요");
			document.getElementsByName("phone1")[0].focus();
			return false;
		}else if(!document.getElementsByName("phone1")[1].value){
			
			alert("번호를 입력하세요");
			document.getElementsByName("phone1")[1].focus();
			return false;
		}else if(!document.getElementsByName("phone1")[2].value){
			
			alert("번호를 입력하세요");
			document.getElementsByName("phone1")[2].focus();
			return false;
			
		}else if(!document.getElementsByName("phone")[0].value){
			
			alert("번호를 입력하세요");
			document.getElementsByName("phone")[0].focus();
			return false;
		}else if(!document.getElementsByName("phone")[1].value){
			
			alert("번호를 입력하세요");
			document.getElementsByName("phone")[1].focus();
			return false;
		}else if(!document.getElementsByName("phone")[2].value){
			
			alert("번호를 입력하세요");
			document.getElementsByName("phone")[2].focus();
			return false;
		}
		
		
		
	}
	
	
	


</script>



<form action="order.do" onsubmit="return checkit()">
<div class="container" style="margin : 0 20%;">


	<div class="row">
	
		<div class="col-lg-12" id="a">
		
			<h5>ORDER / PAYMENT</h5>
	</div>
	
			<div class="col-lg-12" id="order_word">주문상품 확인</div>
			
			<div class="col-lg-12" id="order_word">[CONFIRM CHECKOUT]</div>

	</div>
	
	<!-- 맨위에 주문상품 단어 확인 부분 -->
	
	
	
	
	
	
	<div class="row" id="h_1">
	
		<div class="col-lg-8"> 구입상품명</div>
		<div class="col-lg-1"> 옵션</div>
		<div class="col-lg-1"> 수량</div>
		<div class="col-lg-1"> 가격</div>
		<div class="col-lg-1"> 적립</div>
	</div>
	
	<!-- 디비가져와서 여기에다가 입력 해야함 -->
	<div class="row" id="h_2">
	
		<div class="col-lg-8"> <img src="painting/o/<%=p_img%>" style=" width: 50px;height: 50px;"><%=p_name %></div>
		<div class="col-lg-1"> 기본</div>
		<div class="col-lg-1"><input type="hidden" name="amount" value=<%=amount %>> <%=amount%></div>
		<input type="hidden" name="p_num" value=<%=p_num %>>
		<div class="col-lg-1"><input type="hidden" name="p_price1" value=""> <%=price%></div>
		
		<div class="col-lg-1"> <input type="hidden" name="sprice" value=<%=sprice %>><%=sprice%></div>
	</div>
	
	
	
	<div class="row" id="h_3">
	
		<div class="col-lg-1">총 포인트</div>
		<div class="col-lg-1"style="margin-right: 120px;"><input readonly="readonly" type="text" name="totalp" id="totalp" value="">  </div>
		<div class="col-lg-1"style=" margin-right: 248px;"><input type="text" name="mpoint" value="0" onKeyup="cal1(event)"></div>
		<div class="col-lg-1" style="margin-left: 10px;">결제금액</div>
		<div class="col-lg-1" name="price" ></div>
		<div class="col-lg-1" >원</div>
		<div class="col-lg-2"> (적립금:<%=sprice%>)</div>
		
	</div>

	
	
	
	<!-- 주문자 정보입력  --><!-- 주문자 정보입력  --><!-- 주문자 정보입력  --><!-- 주문자 정보입력  -->
	
	<div class="row">
	
	
		<div class="col-lg-12" id="order_imformaion"><h5>주문자 정보입력</h5></div>
		
		<div class="col-lg-12" id="order_imformaion">[ENTER INFORMATION]</div>

	</div>
	
	
	
	<!--주문자 정보입력 상자 !!!  --><!--주문자 정보입력 상자 !!!  --><!--주문자 정보입력 상자 !!!  -->
	
	<div class="row" id="order_box" name="order_box" style="height:150px;">
	
		<div class="col-lg-2">주문자 정보(필수)</div>
		<div class="col-lg-10">주문자 정보는 모두 필수 입력사항입니다. 상품 주문에 꼭 필요한 사항이므로 정확하게 기재해 주세요</div>
		
		
		<div class="col-lg-4">이름</div>
		<div class="col-lg-8">com</div>
		
		<div class="col-lg-4">e-mail</div>
		<div class="col-lg-8"><input type="text" id="email1"style="width:154px;">&nbsp@&nbsp
		
		<select name ="email" onchange="change1(this)">
			<option value="선택">선택</option>
			<option value="naver.com">naver.com</option>
			<option value="hotmail.com">hotmail.com</option>
			<option value="hanmail.net">hanmail.net</option>
			<option value="yahoo.co.kr">yahoo.co.kr</option>
			<option value="paran.com">paran.com</option>
			<option value="nate.com">nate.com</option>
			<option value="empal.com">empal.com</option>
			<option value="dreamwiz.com">dreamwiz.com</option>
			<option value="hanafos.com">hanafos.com</option>
			<option value="korea.com">korea.com</option>
			<option value="chol.com">chol.com</option>
			<option value="gmail.com">gmail.com</option>
			<option value="lycos.co.kr">lycos.co.kr</option>
			<option value="netian.com">netian.com</option>
			<option value="hanmir.com">hanmir.com</option>
			<option value="직접입력">직접입력</option>
		
		</select>
		
		
		</div>
		
		<div class="col-lg-4">연락처</div>
		<div class="col-lg-8"><input type="text" name="phone1"style="width:82px; height:28px;">-
		<input type="text" name="phone1" style="width:82px; height:28px;">-
		<input type="text" name="phone1" style="width:82px; height:28px;">
		</div>

	
	
	</div>
	
	
	
	
	<!-- 배송지 정보 입력 --><!-- 배송지 정보 입력 --><!-- 배송지 정보 입력 --><!-- 배송지 정보 입력 -->
	
	
	<div class="row">
	
	
		<div class="col-lg-12" id="order_imformaion"><h5>배송지 정보 입력</h5></div>
		
		<div class="col-lg-12" id="order_imformaion">[ENTER SHIPPING INFORMATION]</div>

	</div>
	
	
	
	<!--배송지 정보입력 상자 !!!  --><!--배송지 정보입력 상자 !!!  --><!--배송지 정보입력 상자 !!!  -->
	
	<div class="row" id="order_box" name="order_box">
	
		<div class="col-lg-3">배송지 정보 정보(필수)</div>
		<div class="col-lg-9">배송지 정보는  모두 필수 입력사항입니다. 상품 주문에 꼭 필요한 사항이므로 정확하게 기재해 주세요</div>
		
		
		<div class="col-lg-4">이름</div>
		<div class="col-lg-8">com</div>
		
		<div class="col-lg-4">연락처</div>
		<div class="col-lg-8"><input type="text" name="phone" style="width:82px; height:28px;">-
		<input type="text" name="phone"style="width:82px; height:28px;">-
		<input type="text" name="phone" style="width:82px; height:28px;">
		</div>
		
		<div class="col-lg-4">주소</div>
		<div class="col-lg-8"><input type="text" name="addr" class="postcodify_postcode5" value="" style="background-color: #d7d7d7;"><button type="button" id="postcodify_search_button">검색</button><br /></div>
		
		<div class="col-lg-4"></div>
		<div class="col-lg-8"><input type="text" name="addr" class="postcodify_address" value="" style="width:580px"><br /></div>
		<div class="col-lg-4"></div>
		<div class="col-lg-8"><input type="text" name="addr" class="postcodify_details"  placeholder="상세주소" style="width:580px"><br />
		</div>
		
		
				<!-- jQuery와 Postcodify를 로딩한다 -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
		
		<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
		<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
		

		
		
		<div class="col-lg-4">주문메시지</div>
		<div class="col-lg-8"><textarea rows="10" cols="80" name="msg"></textarea></div>
		
				
		<!-- 주소와 우편번호를 입력할 <input>들을 생성하고 적당한 name과 class를 부여한다 -->
		
		
		
		
		
		
		
	</div>
	
	
	<div class=row id="button">
	
		
		<div class="col-lg-6"><input type="submit" id="order_ok" value="주문하기"></div>
		<div class="col-lg-6"><input type="button" id="order_false" value="취소"></div>
		
	
	</div>
	</div>
	</form>

	
	



</body>
</html>