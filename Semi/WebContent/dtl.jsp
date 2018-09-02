<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#a{font-size:1.5em;color:#ffb399; font-weight:bold; }
#b{font-size:1em;color:#99ff66; }
 table {
   background-color: rgb(0, 175, 251, 0.63);
  }
   th, td {
    border: 1px solid  #e500ff;
}
</style>
</head>
<body background="image/1486746902110.jpeg" style="background-size:100%; background-repeat: no-repeat;-webkit-user-select: none;cursor: zoom-in; size: 100%">
<h1 align="center" style="color: yellow;">상세글보기</h1>
<table border="1" width="900" align="center" id="t">
   <tr>
     <td id="a">이름</td>
     <td id="b">${vo.name }</td>
   </tr>
   <tr>
      <td id="a">제목</td>
      <td id="b">${vo.title }</td>
   </tr>
   <tr>
      <td id="a">내용</td>
      <td><textarea rows="7" cols="80" readonly="readonly">${vo.content }</textarea>
   </tr>
   <tr>
      <td colspan="2"   style="text-align: center;">
          <a href=
	      "int.jsp?num=${vo.num }&ref=${vo.ref}&lev=${vo.lev}&step=${vo.step}" style="color:#ccff99;text-decoration:none; font-size:1.5em;">
		     답글</a>
      </td>
   </tr>
</table>
</body>
</html>