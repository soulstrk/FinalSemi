<%@page import="com.sun.xml.internal.bind.v2.runtime.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	response.setCharacterEncoding("text/html;charset=utf-8");
	String msg = (String)request.getAttribute("msg");
	System.out.println(msg);

	if(msg ==null){
		
		msg = "상품 구입에 실패 하셨습니다.";
		
		%>
		<script>
		
			alert(<%=msg%>);
			location.href="index.jsp?content1=coarderpage.jsp";
			
		</script>
	<%
	}else{
		System.out.println("메시지 도착");
		
		%>
			<script type="text/javascript">
			
				alert("<%=msg%>");
				location.href="index.jsp?content1=mainPage.jsp";
				
				</script>
		<%
	}
	
%>




</body>
</html>