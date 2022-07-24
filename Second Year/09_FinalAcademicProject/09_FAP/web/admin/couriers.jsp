<%-- 
    Document   : products
    Created on : May 12, 2022, 3:02:16 PM
    Author     : Richard Maru
--%>
<%@page import="models.dao.CourierDAO"%>
<%@page import="models.entity.Courier"%>
<%@page import="java.util.List"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%!List<Courier> list;%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest")) {
        throw new ServletException(new AuthenticationException("You need EMPLOYEE or ADMIN credentials to view this site."));
    }
    CourierDAO cd = new CourierDAO();
    list = cd.getCouriers();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href=".../css/dbl.css">
        <style>
            @media (max-width: 900px) {
                .container td:nth-child(0),.container td:nth-child(2),
                .container th:nth-child(0),.container th:nth-child(2){ display: none; }
                .container {
                    width:80%;
                }
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <title>Couriers - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="courier.jsp">Add New Courier</a>
                <div class="header-right">
                    <div class="dropdown">
                        <a href="#" class="dropbtn"><%=user.getFirstName()%> <%=user.getLastName()%></a>
                        <div class="dropdown-content">
                            <a href="<%=application.getContextPath()%>/logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div><br/><br/>
        </header>
        <div class="logo">Courier List</div><br/><br/>
        <table class="container">
            <thead>
                <tr>
                    <th><h1>ID</h1></th>
                    <th><h1>Courier Name</h1></th>
                    <th><h1>Description</h1></th>
                    <th><h1>Price</h1></th>
                </tr>
            </thead>
            <tbody>
                <%for (Courier c : list) {%>
            <tr>
                <td><%=c.getId()%></td>
                <td><a href="courier.jsp?id=<%=c.getId()%>"><%=c.getCourierName()%></a></td>
                <td><%=c.getDescription()%></td>
                <td><%=c.getPrice()%></td>
            </tr>
        <%}%>
    </tbody>
</table><br/><br/>
<div class="footer">
    <div class="insert-margin"></div>
    <div class="logo" stle="font-size:12px;margin:0px;"><%=application.getInitParameter("header")%></div><br/>
    <%=application.getInitParameter("footer")%>
    <div class="insert-border"></div>
    <div class="contact">
        Contact us through our Facebook page at <a target="_blank" href="https://www.facebook.com/richardmaru"><%=application.getInitParameter("header")%></a>
    </div>
</div>
</body>
</html>
