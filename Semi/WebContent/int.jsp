<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#as{ padding-left:35%}
</style>
</head>
<body background="image/65442439_p0_master1200.jpg" style="background-size:100%; background-repeat: no-repeat;-webkit-user-select: none;cursor: zoom-in; size: 100%">
<h1 align="center">방명록작성하기</h1>
<form action="int.do" method="post" id="as">
	<input type="hidden"  name="num" value="${param.num }">
	<input type="hidden"  name="ref" value="${param.ref }">
	<input type="hidden"  name="lev" value="${param.lev }">
	<input type="hidden"  name="step" value="${param.step }">
	작성자 <br> <input type="text"  name="name"><br>
	제목  <br> <input type="text"   name="title"><br>
	내용 <br>
	<textarea  rows="7" cols="80" name="content"></textarea><br>
	<input type="submit" value="등록">
</form>
</body>
</html>