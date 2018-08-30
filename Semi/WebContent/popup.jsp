<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

	.popup_header_container{
	
		display: grid;
		grid-template-columns:1fr 1fr;
	
	}
	
	.popup_header_container img{
	
		width:249px;
		height:180px;
		float:left;
	
	}

</style>

<script type="text/javascript">


function checkcookie2(cookieName){
	
	var search = cookieName + "=";
	var cookie = document.cookie;/* 현재 쿠키에 있는 값을 들고 오기 */
	alert(cookie);
	if(cookie.length>0){ /*쿠키가 존재할 경우  */
		
		startindex = cookie.indexOf(cookieName); /* 쿠키이름의 위치를 구한다. */
		alert(startindex);
		
		if(startindex != -1){/* 만약에 쿠키가 존재한다면 */
			
			startindex = cookie.indexOf("=",startindex)+1;
			alert(startindex);
			endindex = cookie.indexOf(escape(";"),startindex);
			
			var cook = unescape(cookie.substring(startindex, endindex));
			alert(cook);
			
			if(cook == "finally"){
				
				var div=document.getElementById("div_popup");
				div.style.display="none";
			}
		
			
			
			
		}
		
		
	}
	
		
	
	
	
	
}


</script>



<body onload="checkcookie2('popcookie')">

<div id="div_popup" style="width:500px; display:block;height:400px; border: 1px solid black; position:absolute; left:200px; top:200px;">
 
 <div class="popup_header_container" style="height:90%; border: 1px solid black;">
 
 	<div><img src="images/1.jpg" alt="erro"></div>
 	<div><img src="images/1.jpg" alt="erro"></div>
 	<div><img src="images/1.jpg" alt="erro"></div>
 	<div><img src="images/1.jpg" alt="erro"></div>
 	
 
 
 </div>
 
 <div id="popup_footer" >
 
 	<div id="popup_footer_left" align="left" style="width:300px;">
 	
 		<input type="checkbox" onclick="oncheck(this);">7일간 보지않습니다.
 		
 	</div>
 	
 	
 	<a href="javascript:displaying()">닫기</a>
 		
 
 </div>
 </div>
   


<script type="text/javascript">


function displaying(){

	var div = document.getElementById("div_popup");
	div.style.display="none";
	
	
}

function oncheck(checkbox){
	
	
	if(checkbox.value == "on"){
		
		setCookie("popcookie","finally;",1);/* 체크박스를 체크했을때 쿠키 설정 */
		/*setCookie(이름, 값, 시간)  */
		
		var div=document.getElementById("div_popup");
		div.style.display="none";
		
	}else{
		
		alert("쿠키 정보가 없습니다.");
	}
	
	
	
}


function setCookie(name,value,expire){
	/*expire는 보통 hour, day로 나뉜다.hour 일때는 setHours로 시간을 정하고 day 일때는 setDate를 사용한다.*/
	
	var todayValue = new Date(); //쿠키의 시간을 주기위해서는 쿠키를 설정시 현재 시간을 구해야함
	todayValue.setDate(todayValue.getDate() + expire);
	/*여기서는 Date 원하는 날짜를 설정   */
	
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" +todayValue.toGMTString()+";"
	/* 이정보들을 cookie 에 담아준다. 3개의 파라미터로 구성되는데 
	1. 키=벨류값: MAP 형식으로 키와 벨류를 저장
	2. expires= Date 타입값 : 쿠키의 시간을 정함
	3. path: 쿠키 생성 위치: 쿠키 생성 위치를 지정한*/
	
	alert(document.cookie);
	
	
	
}

</script>


</body>
</html>