<%-- 
    Document   : checkout
    Created on : May 12, 2022, 2:58:39 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.UsersDAO"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="models.dao.CourierDAO"%>
<%@page import="models.entity.Courier"%>
<%@page import="java.util.Arrays"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%@page import="models.entity.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User u = (User) session.getAttribute("user");
    if (u == null) {
        throw new ServletException(new AuthenticationException("You need to login"));
    }
    UsersDAO ud = new UsersDAO();
    User user = ud.getUser(u.getId());

    List<Courier> couriers = (new CourierDAO()).getCouriers();
    String[] itemsId = request.getParameterValues("itemsId");
    String[] itemsQty = request.getParameterValues("itemsQty");
    String[] itemsPrice = request.getParameterValues("itemsPrice");
    if(couriers==null||itemsId==null||itemsQty==null||itemsPrice==null){
        response.sendRedirect(request.getContextPath());
    }
    double subtotal = 0;
    for (int i = 0; i < itemsPrice.length; i++) {
        subtotal = (Double.parseDouble(itemsPrice[i]) * Integer.parseInt(itemsQty[i])) + subtotal;
    }
    BigDecimal bd = new BigDecimal(subtotal).setScale(2, RoundingMode.HALF_UP);
    subtotal = bd.doubleValue();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/pay.css">
        <title>Checkout - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <div class="header">
            <a class="logo" href="<%=request.getContextPath()%>"><%=application.getInitParameter("header")%></a>
            <div class="header-right">
                <form id="search" action="search"><input name="name" form="search" type="text" placeholder="Search.."></form>
                    <%if (user == null) {%>
                <a href="login.jsp">Login</a>
                <%} else {%>
                <div class="dropdown">
                    <a href="#" class="dropbtn"><%=user.getFirstName()%> <%=user.getLastName()%></a>
                    <div class="dropdown-content">
                        <a href="<%=request.getContextPath()%>/cart">Cart</a>
                        <%if (user != null && user.getUserType().equals("admin")) {%>
                        <a href="<%=request.getContextPath()%>/admin/index.jsp">Manage Store</a>
                        <%} else if (user != null && user.getUserType().equals("employee")) {%>
                        <a href="<%=request.getContextPath()%>/employee/index.jsp">Manage Store</a><%}%>
                        <a href="<%=request.getContextPath()%>/logout">Logout</a>
                    </div>
                    <%}%>
                </div>
            </div>
        </div><br>
        <div class="frm-section">
            <div class="wrapper">
                <div class="container">
                    <%for (int i = 0; i < itemsId.length; i++) {
                            Float price = Float.parseFloat(itemsPrice[i]) * Integer.parseInt(itemsQty[i]);%>
                    <input type="text" form="orderfrm" name="itemsId" value="<%=itemsId[i]%>" hidden/>
                    <input type="text" form="orderfrm" name="itemsQty" value="<%=itemsQty[i]%>" hidden/>
                    <input type="text" form="orderfrm" name="itemsPrice" value="<%=price%>" hidden/>
                    <%}%>
                    <form id="orderfrm" action="<%=request.getContextPath()%>/order" method="post">
                        <input type="text" name="userid" value="<%=user.getId()%>" hidden></input>
                        <h1>
                            <i class="fas fa-shipping-fast"></i>
                            Payment Method: E-Wallet
                        </h1>
                        <div style="">
                            <img height="150" width="130" src=".../resources/site/gcash.jpg"/>
                            <img style="float:right;" height="150" width="150" src=".../resources/site/paymaya.jpg"/>
                        </div>
                        <h1>
                            <i class="fas fa-shipping-fast"></i>
                            Shipping Details
                        </h1>
                        <div class="name">
                            <div>
                                <label for="f-name">First</label>
                                <input type="text" name="firstname" value="<%=user.getFirstName()%>"></input>
                            </div>
                            <div>
                                <label for="l-name">Last</label>
                                <input type="text" name="lastname" value="<%=user.getLastName()%>"></input>
                            </div>
                        </div>
                        <div class="street">
                            <label for="name">Street</label>
                            <input type="text" name="address" value="<%=user.getAddress()%>"></input>
                        </div>
                        <div class="address-info">
                            <div>
                                <label for="city">City</label>
                                <input type="text" name="city" value="<%=user.getCity()%>"></input>
                            </div>
                            <div>
                                <label for="state">Country</label>
                                <input type="text" name="country" value="<%=user.getCountry()%>"></input>
                            </div>
                            <div>
                                <label for="zip">Zip</label>
                                <input type="text" name="zipcode" value="<%=user.getZip_code()%>"></input>
                            </div>
                        </div>
                        <div>
                            <label for="number">Mobile Number</label>
                            <input type="tel" name="mobile" value="<%=user.getMobile()%>"></input>
                        </div>
                        <div>
                            <label for="email">Email</label>
                            <input type="email" name="email" value="<%=user.getEmail()%>" placeholder="*******@gmail.com"></input>
                        </div>
                        <div class="select">
                            <label for="courier">Select Courier</label>
                            <select id="courier" name="courier">
                                <%for (Courier c : couriers) {%>
                                    <option value="<%=c.getId()%>"><%=c.getCourierName()%> - <%=c.getDescription()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div>
                            <label for="total">Subtotal : </label>
                            <input type="number" name="subtotal" value="<%=subtotal%>" readonly></input>
                        </div>
                        <div style="text-align: left; align-content: flex-start;">
                            <label><input type="checkbox" name="save" style="width:30px;letter-spacing: 0px;float:left; margin-right: 10px;">Save shipping details</input></label>
                        </div>

                        <div class="btns">
                            <button type="submit" name="checkout" value="">Purchase</button>
                            <a href="<%=request.getContextPath()%>/cart">Back to cart</a>
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
