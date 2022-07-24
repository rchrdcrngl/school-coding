<%-- 
    Document   : products
    Created on : May 12, 2022, 3:02:16 PM
    Author     : Richard Maru
--%>
<%@page import="models.dao.UsersDAO"%>
<%@page import="models.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%!List<User> list;%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest")) {
        throw new ServletException(new AuthenticationException("You need EMPLOYEE or ADMIN credentials to view this site."));
    }
    UsersDAO ud = new UsersDAO();
    list = ud.getUserList();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=".../css/dbl.css">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <title>Users - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="user.jsp">Add User</a>
                <a href="<%=request.getContextPath()%>/users?report=guest">Generate Site Guest List</a>
                <a href="<%=request.getContextPath()%>/users?report=employee">Generate Employee List</a>
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
        <div class="logo">User List</div><br/><br/>
        <table class="container">
            <thead>
                <tr>
                    <th><h1>ID</h1></th>
                    <th><h1>Name</h1></th>
                    <th><h1>User Type</h1></th>
                    <th><h1>Birthday</h1></th>
                    <th><h1>Mobile</h1></th>
                    <th><h1>Email</h1></th>
                    <th><h1>Address</h1></th>
                    <th><h1>Username</h1></th>
                    <th><h1>Registered Date</h1></th>
                </tr>
            </thead>
            <tbody>
                <%for (User u : list) {%>
                <tr>
                    <td><%=u.getId()%></td>
                    <td><a href="user.jsp?id=<%=u.getId()%>"><%=u.getFirstName()%> <%=u.getLastName()%></a></td>
                    <td><%=u.getUserType()%></td>
                    <td><%=String.valueOf(u.getBirthday())%></td>
                    <td><%=u.getMobile()%></td>
                    <td><%=u.getEmail()%></td>
                    <td><%=u.getAddress() + "," + u.getCity() + "," + u.getCountry() + " " + u.getZip_code()%></td>
                    <td><%=u.getUsername()%></td>
                    <td><%=String.valueOf(u.getRegisteredAt())%></td>
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
