<%-- 
    Document   : error
    Created on : Oct 10, 2021, 11:10:17 AM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mp2.models.Login" %>
<%

    // Default error message
    String error = "An error has occurred.";
    
    // Check if Error Page has been passed with a Login object
    if (request.getAttribute("login")!=null){
        
        // Get the error in the login process
        Login login = (Login) request.getAttribute("login");
        if (login.isValid()==0) error="Incorrect password";
        if (login.isValid()==-1) error="Incorrect username";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="css/styles.css">
        <title>MP2 - Error</title>
    </head>
    <div class="header">
            <a href="index.jsp" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
        </div>
         <div class="error-container">
            <div class="error-box">
                <div class="title"><%out.println(error);%></div>
            </div>
        </div>
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
</html>
