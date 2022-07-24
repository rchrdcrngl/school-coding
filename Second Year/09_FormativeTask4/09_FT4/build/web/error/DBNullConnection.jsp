<%-- 
    Document   : SQLException
    Created on : Mar 6, 2022, 4:58:15 PM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/main.css">
        <title>Database Error</title>
    </head>
    <body>
        <div class="header">
            <a href="<%= request.getContextPath()%>" class="logo"><i class="material-icons" style="vertical-align: text-bottom;">coronavirus</i>     <%=application.getInitParameter("header")%></a>
        </div>

        <div class="error-container">
            <div class="error-box">
                <div class="title">Database Error</div>
                <center>
                    <br><br>
                    <div style="margin: 20;">
                        <h3><%= exception.getMessage()%></h3><br>
                    </div>
                    <br><br>
                </center>
            </div>
        </div>

        <div class="footer">
            <p><%=application.getInitParameter("footer")%></p>
        </div>
    </body>
</html>
