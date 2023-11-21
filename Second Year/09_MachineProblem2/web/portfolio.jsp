 <%-- 
    Document   : portfolio
    Created on : Oct 10, 2021, 3:27:38 PM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mp2.models.Login" %>
<%@page import="com.mp2.models.ProfileInfo" %>
<%@ page import="java.util.*" %>
<%
    // Get the passed ProfileInfo and list from the request
    ProfileInfo profile = (ProfileInfo) request.getAttribute("profile");
    List hobbies = (List) request.getAttribute("hobbies");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="css/portfolio.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>MP2 - Personal Portfolio</title>
    </head>
    <body>
        <div class="header">
            <a href="select.jsp" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
            <div class="header-right">
                <a class="active" href="select.jsp">Select Profile</a>
                <a href="index.jsp">Logout</a>
            </div>
        </div>
            <br><br><br>
            <p>Hello! My name is</p><br>
            <h1><%out.println(profile.getName());%></h1><br> <!-- Name and info dynamically added into the HTML code -->
            <h2><%out.println(profile.getInfo());%></h2>
            <p>Hobbies</p>
            <ul> 
                <%
                    // Iterate through the list of hobbies and dynamically add
                    // the values to the HTML code
                    Iterator it = hobbies.iterator();
                    while (it.hasNext())
                    {
                        out.print("<li>" + it.next() + "</li>");
                    }
                %>
            </ul><br><br><br>
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
