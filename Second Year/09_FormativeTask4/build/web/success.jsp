<%-- 
    Document   : success
    Created on : Mar 19, 2022, 9:40:04 AM
    Author     : Richard Maru
--%>

<%@page import="model.exception.AuthenticationException"%>
<%@page import="model.account.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    Account account = (Account) session.getAttribute("user");
    if (account == null || !(account.getType().equals("guest")||account.getType().equals("admin"))) {
        throw new ServletException(new AuthenticationException("You need to login."));
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success Page</title>
    </head>
    <body>
        <h1>Hello, <%=account.getFullName()%>!</h1>
        <p>Username: <%=account.getUsername()%></p>
        <p>Type: <%=account.getType()%></p>
        <a href="<%=request.getContextPath()%>/report">Print Report</a>
    </body>
</html>
