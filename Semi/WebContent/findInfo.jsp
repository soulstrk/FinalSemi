<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>아이디/비밀번호 찾기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h2>아이디 찾기</h2>
			
				<div class="tab">
					<button class="tablinks" onclick="openCity(event, 'London')">아이디찾기</button>
					<button class="tablinks" onclick="openCity(event, 'Paris')">비밀번호찾기</button>
				</div>
				<div id="ID" class="tabcontent">
					<h3>아이디 찾기</h3>
					<form action="findinfo.do?cmd=findinfoOk&find=id" method="post">
					
					
					
					</form>
				</div>
				<div id="PWD" class="tabcontent">
					<h3>비밀번호 찾기</h3>
					<form action="findinfo.do?cmd=findinfoOk&find=pwd" method="post">
					
					</form>
				</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
<script>
	function openCity(evt, cityName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");
		}
		document.getElementById(cityName).style.display = "block";
		evt.currentTarget.className += " active";
	}
</script>
