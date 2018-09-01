<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h2{font-size:3em;color: #ffffff ;}
a{font-size:1.5em; color: #51F41F;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body background="image/69d8316cb657102a064fbff0bf38fac8.jpg" style="no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover; background-repeat: no-repeat;">
<c:choose>
	<c:when test="${requestScope.cd=='ok' }">
		<h2>글등록성공</h2>
	</c:when>
	<c:otherwise>
		<h2>글등록실패</h2>
	</c:otherwise>
</c:choose>
<a href="listo.do">글목록으로</a>
<img src="image/nana.png" style=" width: 500px;height: auto;margin-left: auto; margin-right: auto; display: block;">
</body>
</html>