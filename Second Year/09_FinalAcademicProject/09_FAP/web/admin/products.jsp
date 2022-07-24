<%-- 
    Document   : products
    Created on : May 12, 2022, 3:02:16 PM
    Author     : Richard Maru
--%>
<%@page import="models.dao.ProductTypeDAO"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.dao.CategoryDAO"%>
<%@page import="models.entity.Category"%>
<%@page import="models.dao.ProductDAO"%>
<%@page import="models.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%!List<Product> list;%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest") || user.getUserType().equals("employee")) {
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
    }
    ProductDAO pd = new ProductDAO();
    list = pd.getProductList();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href=".../css/dbl.css">
        <style>
            @media (max-width: 900px) {
                .container td:nth-child(2),.container td:nth-child(6),.container td:nth-child(7),.container td:nth-child(8),.container td:nth-child(9),
                .container th:nth-child(2),.container th:nth-child(6),.container th:nth-child(7),.container th:nth-child(8),.container th:nth-child(9) { display: none; }
                .container {
                    width:80%;
                }
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <title>Products - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="product.jsp">Add Product</a>
                <a href="category.jsp">Add Category</a>
                <a href="type.jsp">Add Type</a>
                <a href="<%=request.getContextPath()%>/product?report=products">Generate Product List</a>
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
        <div class="logo">Product List</div><br/><br/>
        <table class="container">
            <thead>
                <tr>
                    <th><h1>ID</h1></th>
                    <th><h1>Image</h1></th>
                    <th><h1>Name</h1></th>
                    <th><h1>Price</h1></th>
                    <th><h1>Stock</h1></th>
                    <th><h1>SKU</h1></th>
                    <th><h1>Manufacturer</h1></th>
                    <th><h1>Date Published</h1></th>
                    <th><h1>Active</h1></th>
                </tr>
            </thead>
            <tbody>
                <%for (Product p : list) {%>
                <tr>
                    <td><%=p.getId()%></td>
                    <td><img src="<%=p.getProductImg()%>" width="100" height="100" style="object-fit: cover;object-position: center;"/></td>
                    <td><a href="product.jsp?id=<%=p.getId()%>"><%=p.getTitle()%></a></td>
                    <td><%=p.getPrice()%></td>
                    <td><%=p.getStock()%></td>
                    <td><%=p.getSku()%></td>
                    <td><%=p.getManufacturer()%></td>
                    <td><%=p.getPublishedAt()%></td>
                    <td><%=p.isActive()%></td>
                </tr>
                <%}%>
            </tbody>
        </table><br/>
        <div>
            <div style="color:white">Product Category List</div><br/>
            <table class="container">
                <thead>
                    <tr>
                        <th><h1>ID</h1></th>
                        <th><h1>Image</h1></th>
                        <th><h1>Slug</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Category c : (new CategoryDAO()).getCategories()) {%>
                    <tr>
                        <td><%=c.getId()%></td>
                        <td><a href="category.jsp?id=<%=c.getId()%>"><%=c.getTitle()%></a></td>
                        <td><%=c.getSlug()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div><br/>
        <div>
            <div style="color:white">Product Types List</div><br/>
            <table class="container">
                <thead>
                    <tr>
                        <th><h1>ID</h1></th>
                        <th><h1>Type</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <%for (ProductType t : (new ProductTypeDAO()).getProductTypes()) {%>
                    <tr>
                        <td><%=t.getId()%></td>
                        <td><a href="type.jsp?id=<%=t.getId()%>"><%=t.getTitle()%></a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
        <br/><br/><br/><br/><br/>
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
