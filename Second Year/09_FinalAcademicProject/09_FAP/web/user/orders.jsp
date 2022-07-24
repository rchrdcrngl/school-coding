<%-- 
    Document   : orders.jsp
    Created on : May 12, 2022, 2:59:12 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.OrderDAO"%>
<%@page import="models.entity.Order"%>
<%@page import="java.util.List"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You need to login first."));
    }
    List<Order> list = (new OrderDAO()).userGetOrders(user.getId());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/cart.css">
        <link rel="stylesheet" href=".../css/dbl.css">
        <title>Orders - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <div class="header">
            <a class="logo" href="<%=request.getContextPath()%>"><%=application.getInitParameter("header")%></a>
            <div class="header-right">
                <form id="search" action="<%=request.getContextPath()%>/search"><input name="name" form="search" type="text" placeholder="Search.."></form>
                    <%if (user == null) {%>
                <a href="login.jsp">Login</a>
                <%} else {%>
                <div class="dropdown">
                    <a href="#" class="dropbtn"><%=user.getFirstName()%> <%=user.getLastName()%></a>
                    <div class="dropdown-content">
                        <a href="cart.jsp">Cart</a>
                        <a href="orders.jsp">Orders</a>
                        <%if (user != null && user.getUserType().equals("admin")) {%>
                        <a href="<%=request.getContextPath()%>/admin/index.jsp">Manage Store</a>
                        <%} else if (user != null && user.getUserType().equals("employee")) {%>
                        <a href="<%=request.getContextPath()%>/employee/index.jsp">Manage Store</a><%}%>
                        <a href="<%=request.getContextPath()%>/logout">Logout</a>
                    </div>
                    <%}%>
                </div>
            </div>
        </div><br/>

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
                    <td>
                        <%=(o.getStatus() == -1) ? "Cancelled" : (o.getStatus() == 0) ? "To Process" : (o.getStatus() == 1) ? "Shipped" : (o.getStatus() == 2) ? "Received" : "-"%>
                    </td>
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
