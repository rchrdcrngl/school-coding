<%-- 
    Document   : index
    Created on : May 12, 2022, 2:46:49 PM
    Author     : Richard Maru
--%>

<%@page import="models.entity.User"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="models.entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    User user = (User) session.getAttribute("user");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/search.css">
        <title><%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <div class="header">
            <a class="logo" href="home"><%=application.getInitParameter("header")%></a>
            <%for (ProductType t : (List<ProductType>) request.getAttribute("types")) {%>
            <a href="home?type=<%=t.getId()%>"><%=t.getTitle()%></a>
            <%}%>
            <div class="dropdown">
                <button class="dropbtn">Categories
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <%for (Category c : (List<Category>) request.getAttribute("categories")) {%>
                    <a href="home?category=<%=c.getSlug()%>"><%=c.getTitle()%></a>
                    <% } %>
                </div>
            </div>

            <div class="header-right">
                <form id="search" action="search"><input name="name" form="search" type="text" placeholder="Search.."></form>
                    <%if (user == null) {%>
                <a href="login.jsp">Login</a>
                <%} else {%>
                <div class="dropdown">
                    <a href="#" class="dropbtn"><%=user.getFirstName()%> <%=user.getLastName()%></a>
                    <div class="dropdown-content">
                        <a href="user/cart.jsp">Cart</a>
                        <a href="user/orders.jsp">Orders</a>
                        <%if (user != null && user.getUserType().equals("admin")) {%>
                        <a href="admin/index.jsp">Manage Store</a>
                        <%} else if (user != null && user.getUserType().equals("employee")) {%>
                        <a href="employee/index.jsp">Manage Store</a><%}%>
                        <a href="logout">Logout</a>
                    </div>
                    <%}%>
                </div>

            </div>
        </div><br>

        <div class="product-list enlarge">
            <%for (Product p : (List<Product>) request.getAttribute("products")) {%>
            <div class="card">
                <a href="<%=request.getContextPath()%>/product?name=<%=p.getSlug()%>">
                    <div class="box-art">
                        <img src="<%=p.getProductImg()%>">
                        <div class="box-art-hover"></div>  
                    </div> 
                    <div style="padding:0 7px 7px 7px;">
                        <h2><%=p.getTitle()%></h2>
                        <p>PHP. <%=p.getPrice()%></p>
                    </div>
                </a>
            </div><%}%>

        </div><br/><br/>
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
