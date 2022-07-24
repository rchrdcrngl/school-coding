<%-- 
    Document   : products
    Created on : May 12, 2022, 3:02:16 PM
    Author     : Richard Maru
--%>
<%@page import="models.dao.OrderDAO"%>
<%@page import="models.entity.Order"%>
<%@page import="java.util.List"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%!List<Order> list;%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest")||user.getUserType().equals("employee")) {
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
    }
    OrderDAO od = new OrderDAO();
    list = od.adminGetOrders();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=".../css/dbl.css">
        <style>
            @media (max-width: 900px) {
                .container td:nth-child(2),.container td:nth-child(4),
                .container th:nth-child(2),.container th:nth-child(4) { display: none; }
                .container {
                    width:80%;
                }
            }
        </style>
        <link rel="stylesheet" href=".../css/main.css">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Orders - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="<%=request.getContextPath()%>/product?report=sales">Generate Sales Report</a>
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
        <div class="logo">Order List</div><br/><br/>
        <table class="container">
            <thead>
                <tr>
                    <th><h1>ID</h1></th>
                    <th><h1>Receiver Name</h1></th>
                    <th><h1>Address</h1></th>
                    <th><h1>Contact No.</h1></th>
                    <th><h1>Total</h1></th>
                    <th><h1>Status</h1></th>
                    <th><h1>Tracking No.</h1></th>
                </tr>
            </thead>
            <tbody>
                <%for (Order o : list) {%>
                <tr>
                    <td><a href="order.jsp?id=<%=o.getId()%>"><%=o.getId()%></a></td>
                    <td><%=o.getUser().getFirstName()%> <%=o.getUser().getLastName()%></td>
                    <td><%=o.getUser().getCity() + ", " + o.getUser().getCountry()%></td>
                    <td><%=o.getUser().getMobile()%></td>
                    <td><%=o.getTotal()%></td>
                    <td><%=(o.getStatus() == -1) ? "Cancelled" : (o.getStatus() == 0) ? "To Process" : (o.getStatus() ==1) ? "Shipped" : (o.getStatus() == 2) ? "Received" : "-"%></td>
                    <td><%=o.getTrackingNo()%></td>
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
