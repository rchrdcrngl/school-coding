<%-- 
    Document   : 404
    Created on : Mar 6, 2022, 4:57:54 PM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<% response.setStatus(404); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/main.css">
        <title>404 Not Found</title>
    </head>
    <body>
        <div class="header">
            <a href="<%= request.getContextPath()%>" class="logo"><i class="material-icons" style="vertical-align: text-bottom;">coronavirus</i>     <%out.println(request.getServletContext().getInitParameter("header"));%></a>
        </div>

        <div class="error-container">
            <div class="error-box">
                <div class="title">Error 404</div>
                <center>
                    <br><br>
                    <div style="margin: 20;">
                        <h3>The site you are trying to access does not exist. Try checking the URL if it's correct.</h3><br>
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
