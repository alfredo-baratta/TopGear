<%@ page import="javax.servlet.http.HttpSession" %>

<%
    if (session == null ||  session.getAttribute("username") == null) {
        session.invalidate();
    	response.sendRedirect("login.jsp");
    }
%>
