<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ include file="utils/sessionCheck.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Benvenuto</title>
</head>
<body>
	<h1>Benvenuto, <%= (String) request.getAttribute("nome") %> <%= (String) request.getAttribute("cognome") %>.️</h1>
	<a href="<%=request.getContextPath()%>/Account" >Pisello</a>
</body>
</html>