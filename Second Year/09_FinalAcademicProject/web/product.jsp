<%-- 
    Document   : product
    Created on : May 12, 2022, 2:54:52 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.CategoryDAO"%>
<%@page import="models.dao.ProductTypeDAO"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%@page import="models.entity.Category"%>
<%@page import="models.entity.ProductMeta"%>
<%@page import="java.util.List"%>
<%@page import="models.entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!Product p;%>
<%
    p = (Product) request.getAttribute("product");
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/product.css">
        <title><%=p.getTitle()%> - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <div class="header">
            <a class="logo" href="home"><%=application.getInitParameter("header")%></a>
            <%for (ProductType t : (List<ProductType>) (new ProductTypeDAO()).getProductTypes()) {%>
            <a href="home?type=<%=t.getId()%>"><%=t.getTitle()%></a>
            <%}%>
            <div class="dropdown">
                <button class="dropbtn">Categories
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <%for (Category c : (List<Category>) (new CategoryDAO()).getCategories()) {%>
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
        <div class="product-area">
            <div class="container">
                <div class="product-image">  
                    <img src="<%=p.getProductImg()%>" alt="" class="product-pic">
                </div>
                
                <form id="cart" method="POST" action="cart">
                    <div class="product-details">
                        <header>
                            <h1 class="title"><%=p.getTitle()%></h1>
                            <span class="colorCat">Stocks Available: <%=p.getStock()%></span>
                            <div class="price">
                                <span class="current">Php. <%=p.getPrice()%></span>
                            </div>
                        </header>
                        <article>
                            <h5>Description</h5>
                            <p><%=p.getDescription()%></p>
                            <h5>Other Specifications</h5>
                            <p>SKU: <%=p.getSku()%></p>
                            <%for (ProductMeta m : (List<ProductMeta>) p.getMeta()) {%>
                            <p><%=m.getKey()%>: <%=m.getValue()%></p>
                            <%}%>
                            <h5>Product Category</h5>
                            <div class="category-container">
                                <%=p.getType()%>: 
                                <%for (Category c : (List<Category>) p.getCategories()) {%>
                                <a class="category" style="color:purple;" href="<%=request.getContextPath()%>?category=<%=c.getSlug()%>"><%=c.getTitle()%>, </a>
                                <%}%>
                            </div>
                        </article>
                        <div class="controls">
                            <div class="qty">
                                <h5>Quantity:</h5>
                                <input name="qty" value="1" form="cart" type="number" min="1" max="<%=p.getStock()%>"></input>
                            </div>
                        </div><br>
                        <%if (p.isActive() == true) {%>
                        <div class="foot">
                            <input name="price" value="<%=p.getPrice()%>" hidden/>
                            <input name="slug" value="<%=p.getSlug()%>" hidden/>
                            <button type="submit" form="cart" name="addtocart" value="<%=p.getId()%>">
                                <img src="http://co0kie.github.io/codepen/nike-product-page/cart.png" alt="">
                                <span>add to cart</span>
                            </button>
                        </div>
                        <%} else {%>

                        <%}%>
                    </div>
                </form>
            </div>
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
