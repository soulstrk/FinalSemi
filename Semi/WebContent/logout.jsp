<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	session.removeAttribute("vo");
	session.removeAttribute("id");
	request.setAttribute("signMsg", "로그아웃 되셨습니다");
	request.getRequestDispatcher("index.jsp?content1=mainPage.jsp").forward(request, response);
%>