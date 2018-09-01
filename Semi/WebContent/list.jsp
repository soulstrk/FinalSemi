<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String context=application.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=context%>/common.css">
</head>
<body background="image/천공의섬.jpg" style="width:100%; height:100%; background-repeat: no-repeat;" >
<div id="wrap">
<div id="header" style="display:table-cell;vertical-align:middle">
<h2>게시글 목록</h2>
</div>
<c:if test="${empty list }">
	<script type="text/javascript">
		location.href="listo.do";
	</script>
</c:if>
<div id="content">
<table align="center" border="1" width="700">
    <tr>
        <th>이름</th>
        <th>제목</th>
    </tr>
    <c:forEach var="vo" items="${list }">
    <tr>
        <td>${vo.name }</td>
        <td>
    <c:if test="${vo.lev>0 }">
    &nbsp;&nbsp;
        <c:forEach var="i" begin="1" end="${vo.lev }">
        </c:forEach>
                  ┕>
    </c:if>
    <a href="detail.do?num=${vo.num }">${vo.title }|${vo.lev }</a>
        </td>      
    </tr>
    </c:forEach>
</table>
</div>
<div id="footer">
<div>
      <c:choose>
           <c:when test="${sp>10 }">
                  <a href="listo.do?pNum=${sP-1 }">이전|</a>
           </c:when>
      </c:choose>
      <c:forEach var="i" begin="${stP }" end="${endP }">
      <c:choose>
        <c:when test="${pNum==i}"> 
               <a href="listo.do?pNum=${i }"><span style="color:#ff3beb">|${i }|</span></a>
        </c:when>
        <c:otherwise>
               <a href="listo.do?pNum=${i }"><span style="color:#007fef">|${i }|</span></a>
        </c:otherwise>
      </c:choose>
      </c:forEach>
      <c:choose>
          <c:when test="${endP<pC }">
              <a href="listo.do?pNum=${endP+1 }">|다음</a>
          </c:when>
          <c:otherwise>
                               다음
          </c:otherwise>
      </c:choose>
      </div>
</div>
</div>
</body>
</html>