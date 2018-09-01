<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center"><big><big><big><big>&#94;ω&#94;v</big></big></big></big></h1>
<h2 align="center">글을 등록하는 곳입니다.</h2>
<form action="int.do" method="post">
<input type="hidden" name="num" value="${param.num }">
	<input type="hidden" name="ref" value="${param.ref }">
	<input type="hidden" name="lev" value="${param.lev }">
	<input type="hidden" name="step" value="${param.step }">
<table class="tg" align="center">
  <tr class="tr">
    <td class="td">닉네임 <input type="text" name="name" size="37%"></td>
  </tr>
  <tr class="tr">
    <td class="td">제목 <input type="text" name="title" size="40%"></td>
  </tr>
  <tr class="tr">
    <td id="3">내용 <br><textarea rows="5" cols="50" name="content" id="a3"></textarea></td>
  </tr>
  <tr>
    <td><input type="submit" value="등록" id="sa"> <input type="button" value="취소" id="xi" onclick="location.href='xiezuo.jsp'"></td>
  </tr>
</table>
</form>
</body>
</html>