<%-- 
    Document   : products.jsp
    Created on : May 12, 2022, 3:00:34 PM
    Author     : Richard Maru
--%>

<%@page import="models.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="models.dao.ProductDAO"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest")) {
        throw new ServletException(new AuthenticationException("You need EMPLOYEE or ADMIN credentials to view this site."));
    }
    ProductDAO pd = new ProductDAO();
    List<Product> list = pd.getStockList();
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href=".../css/dbl.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <title>Products - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
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
                    <th><h1>Stock</h1></th>
                    <th><h1>SKU</h1></th>
                    <th><h1>Manufacturer</h1></th>
                    <th><h1>Type</h1></th>
                </tr>
            </thead>
            <tbody>
                <%for (Product p : list) {%>
                <tr>
                    <td><%=p.getId()%></td>
                    <td><img src="<%=p.getProductImg()%>" width="100" height="100" style="object-fit: cover;object-position: center;"/></td>
                    <td><%=p.getTitle()%></td>
                    <td><form method="post" action="<%=request.getContextPath()%>/product"><input style="width:80px; margin:5px;" type="number" min="0" name="stock" value="<%=p.getStock()%>"/><button type="submit" name="updateStock" value="<%=p.getId()%>">Update</button></form></td>
                    <td><%=p.getSku()%></td>
                    <td><%=p.getManufacturer()%></td>
                    <td><%=p.getType()%></td>
                </tr>
                <%}%>
            </tbody>
        </table><br/><br/><br/>
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
