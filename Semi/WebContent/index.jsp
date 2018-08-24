<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String content1 = request.getParameter("content1");
	if(content1 == null){ content1 = "mainPage.jsp"; };
%>
	<!-- head -->
		<jsp:include page="head_footer/head.html"/>
	<body style="background-image: url('images/swirl_@2X.png');">
	<!-- nav -->
		<jsp:include page="head_footer/nav.jsp"/>
	<!-- content -->
		<jsp:include page="<%=content1%>"/>
	<!-- footer -->
		<jsp:include page="head_footer/footer.jsp"/>
	</body>
</html>



