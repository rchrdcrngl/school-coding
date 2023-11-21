<%-- 
    Document   : nullError
    Created on : Nov 24, 2021, 8:43:30 AM
    Author     : Richard Maru
--%>
<%@ page isErrorPage = "true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires", "0");
    session.invalidate();
%>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="../css/styles.css">
        <title><%=request.getServletContext().getInitParameter("header")%>: Authentication Error</title>
    </head>
    <body>
        <div class="header">
            <a href="<%= request.getContextPath() %>" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
            <div class="header-right">
                <a href="logout">Login</a>
            </div>
        </div>
        
        <div class="error-container">
            <div class="error-box">
                <div class="title">Authentication Error</div>
                <center>
                    <br><br>
                    <div style="margin: 20;">
                        <h3><%= exception.getMessage()%></h3><br>
                        <a href="logout">Back to login</a>
                    </div>
                    <br><br>
                </center>
            </div>
        </div>

        
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
