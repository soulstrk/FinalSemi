<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String id=(String)session.getAttribute("id");
	String resultMsg=(String)request.getAttribute("resultMsg");
	if(resultMsg!=null && !resultMsg.equals("")){
		%>
		<script>
			alert('<%= resultMsg%>');   
		</script>
	<%  
		if(resultMsg.equals("탈퇴되었습니다.")){
			%>
			<script>
				location.href="logout.jsp";
			</script>
		<%  
		}else if(resultMsg.equals("정상적으로 글이 업로드되었습니다.") ||resultMsg.equals("정상적으로 글이 업데이트되었습니다.") ){
			%>
		<script>
			location.href="customerboard.do?cmd=list";
		</script>
	<%  
		}else if(resultMsg.equals("해당 상품후기가 삭제되었습니다.")){
			%>
			<script>
				location.href="mypage.do?cmd=review&id=<%=id%>"; 
			</script>
		<%  
			
		}else{
			%>
			<script>
				location.href="index.jsp";
			</script>
		<%  
		}
	}else{
		%>
		<script>
		location.href="index.jsp";
		</script>
	<%
	}
%>