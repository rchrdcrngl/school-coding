<%-- 
    Document   : index
    Created on : May 19, 2022, 10:00:16 AM
    Author     : Richard Maru
--%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest") || user.getUserType().equals("employee"))
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=".../css/dbl.css">
        <link rel="stylesheet" href=".../css/main.css">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Admin Dashboard - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <div class="dashboard">
            <h2 class="dashboard-header">Admin Dashboard</h2>

            <div class="dashboard-container">
                <a class="btn" href="products.jsp">Manage Products</a>
                <a class="btn" href="couriers.jsp">Manage Couriers</a>
                <a class="btn" href="orders.jsp"">Manage Orders</a>
                <a class="btn" href="users.jsp">Manage Users</a>
                <a class="btn" href="<%=request.getContextPath()%>/employee/index.jsp">Employee View</a>
                <a class="btn" href="<%=request.getContextPath()%>/home">Continue as Guest</a>
                <a class="btn" href="<%=request.getContextPath()%>/logout">Logout</a>
            </div>
            <div class="footer">
                <div class="insert-margin"></div>
                <div class="logo" stle="font-size:12px;margin:0px;"><%=application.getInitParameter("header")%></div><br/>
                <%=application.getInitParameter("footer")%>
                <div class="insert-border"></div>
                <div class="contact">
                    Socials : 
                </div>
            </div>
        </div>

    </body>
</html>
