<%-- 
    Document   : cart
    Created on : May 12, 2022, 2:54:58 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.CartDAO"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%@page import="models.entity.CartItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User user = (User) session.getAttribute("user");
    if (user == null)
        throw new ServletException(new AuthenticationException("You need to login first."));
    List<CartItem> list = (new CartDAO()).retrieveCartItems(user.getId());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/cart.css">
        <title>Cart - <%=application.getInitParameter("header")%></title>
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


        <div class="page">
            <div class="head">
                <h1 class="logo" style="float:left;margin-bottom:15px;">Cart</h1>
                <form id="checkout" method="POST" action="checkout.jsp" style="float: right;">
                    <%if (!list.isEmpty()) {%><button type="submit" name="checkout" value="">Checkout Items</button><%}%>
                </form>
            </div><br/>
            <div id="store_cart">
                <ul class="cart_head">
                    <li class="cart_head_title">

                    </li>
                    <li class="cart_head_product">
                        Product
                    </li>
                    <li class="cart_head_options">
                        Quantity
                    </li>
                    <li class="cart_head_price">
                        Price
                    </li>
                </ul>

                <%for (CartItem itm : list) {%>
                <ul class="cart_item">
                    <input type="text" form="checkout" name="itemsId" value="<%=itm.getProduct().getId()%>" hidden/>
                    <input type="text" form="checkout" name="itemsQty" value="<%=itm.getQuantity()%>" hidden/>
                    <input type="text" form="checkout" name="itemsPrice" value="<%=itm.getPrice()%>" hidden/>
                    <li class="cart_img_col">
                        <img height="100" width="100" style="object-fit: cover;object-position: center;" src="<%=itm.getProduct().getProductImg()%>">
                    </li>

                    <li class="cart_product_col">
                        <p><a href="<%=request.getContextPath()%>/product?name=<%=itm.getProduct().getSlug()%>"><%=itm.getProduct().getTitle()%></a></p>
                    </li>

                    <li class="cart_options_col">
                        <span>Quantity: </span>
                        <form action="<%=request.getContextPath()%>/cart" method="post">
                            <input name="cid" value="<%=itm.getId()%>" hidden/>
                            <input name="price" value="<%=itm.getPrice()%>" hidden/>
                            <input name="editItemQty" type="number" min="1" max="<%=itm.getProduct().getStock()%>" value="<%=itm.getQuantity()%>"/>
                            <button type="submit">Edit Quantity</button>
                        </form>
                    </li>

                    <li class="cart_price_col">
                        <h2><%=itm.getPrice()%></h2>
                    </li>
                    <li class="cart_del_col">
                        <form action="<%=request.getContextPath()%>/cart" method="post"><button name="deleteItem" value="<%=itm.getId()%>"><img src="https://i.imgur.com/bI4oD5C.png"></button></form>
                    </li>
                </ul>
                <%}%>

            </div>
        </div>

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
