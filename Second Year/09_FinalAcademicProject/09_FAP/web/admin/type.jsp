<%-- 
    Document   : type
    Created on : May 12, 2022, 3:02:55 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.ProductTypeDAO"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest")||user.getUserType().equals("employee")) {
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
    }
    int id = -1;
    if (request.getParameter("id") != null) {
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("products.jsp");
        }
    }
    ProductType pt = (new ProductTypeDAO()).getProductType(id);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/pay.css">
        <title>Product Type - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="type.jsp">Add New Product Type</a>
                <div class="header-right">
                    <div class="dropdown">
                        <a href="#" class="dropbtn"><%=user.getFirstName()%> <%=user.getLastName()%></a>
                        <div class="dropdown-content">
                            <a href="<%=application.getContextPath()%>/logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div><br/>
        </header>
        <div class="frm-section">
            <div class="wrapper" style="background-color: #45aaf2;">
                <div class="container">
                    <form id="orderfrm" action="<%=request.getContextPath()%>/ptype" method="post">
                        <h1>
                            Product Type Details
                        </h1>
                        <div>
                            <label for="number">Type</label>
                            <input type="text" name="title" value="<%=pt==null?"":pt.getTitle()%>"></input>
                        </div>
                        <div class="btns">
                            <button type="submit" name="<%=pt == null ? "add" : "delete"%>" value="<%=pt == null ? "" : pt.getId()%>"><%=pt == null ? "Add Type" : "Delete Type"%></button>
                        </div>
                    </form>
                </div>
            </div>
        </div><br/><br/><br/>


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

