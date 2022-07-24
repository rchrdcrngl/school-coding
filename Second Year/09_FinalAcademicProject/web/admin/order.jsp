<%-- 
    Document   : order
    Created on : May 12, 2022, 2:59:22 PM
    Author     : Richard Maru
--%>
<%@page import="models.entity.Product"%>
<%@page import="models.dao.OrderDAO"%>
<%@page import="models.entity.Order"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest") || user.getUserType().equals("employee")) {
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
    }
    if (request.getParameter("id") == null) {
        response.sendRedirect("orders.jsp");
    }
    int orderId = -1;
    try {
        orderId = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException e) {
        response.sendRedirect("orders.jsp");
    }
    Order o = (new OrderDAO()).viewOrder(orderId);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=".../css/dbl.css">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/pay.css">
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
        </header><br/>
        <div class="frm-section">
            <div class="wrapper">
                <div class="container">
                    <h1>
                        Order #<%=o.getId()%> Details (<%=(o.getStatus() == -1) ? "Cancelled" : (o.getStatus() == 0) ? "To Process" : (o.getStatus() == 1) ? "Shipped" : (o.getStatus() == 2) ? "Received" : "-"%>)
                    </h1>
                    <div class="name">
                        <div>
                            <label for="f-name">First</label>
                            <input type="text" name="firstname" value="<%=o.getUser().getFirstName()%>" readonly></input>
                        </div>
                        <div>
                            <label for="l-name">Last</label>
                            <input type="text" name="lastname" value="<%=o.getUser().getLastName()%>" readonly></input>
                        </div>
                    </div>
                    <div class="street">
                        <label for="name">Street</label>
                        <input type="text" name="address" value="<%=o.getUser().getAddress()%>"readonly></input>
                    </div>
                    <div class="address-info">
                        <div>
                            <label for="city">City</label>
                            <input type="text" name="city" value="<%=o.getUser().getCity()%>" readonly></input>
                        </div>
                        <div>
                            <label for="state">Country</label>
                            <input type="text" name="country" value="<%=o.getUser().getCountry()%>" readonly></input>
                        </div>
                        <div>
                            <label for="zip">Zip</label>
                            <input type="text" name="zipcode" value="<%=o.getUser().getZip_code()%>" readonly></input>
                        </div>
                    </div>
                    <div>
                        <label for="number">Mobile Number</label>
                        <input type="tel" name="mobile" value="<%=o.getUser().getMobile()%>"readonly></input>
                    </div>
                    <div>
                        <label for="email">Email</label>
                        <input type="email" name="email" value="<%=o.getUser().getEmail()%>" readonly></input>
                    </div>
                    <div class="name">
                        <div>
                            <label for="f-name">Courier</label>
                            <input type="text" name="firstname" value="<%=o.getCourier().getCourierName()%>" readonly></input>
                        </div>
                        <div>
                            <label for="l-name">Tracking No.</label>
                            <input type="text" name="lastname" value="<%=o.getTrackingNo()%>" readonly></input>
                        </div>
                    </div>
                    <div>
                        <label for="total">Subtotal: </label>
                        <input type="number" name="subtotal" value="<%=o.getSubtotal()%>" readonly></input>
                    </div>
                    <div>
                        <label for="total">Total: </label>
                        <input type="number" name="subtotal" value="<%=o.getTotal()%>" readonly></input>
                    </div>
                    <div class="btns">
                        <form method="post" action="<%=request.getContextPath()%>/order">
                            <button type="submit" name="slip" value="<%=o.getId()%>">Order Slip</button>
                        </form>
                    </div>
                </div>
            </div>
        </div><br/>
        <table class="container">
            <thead>
                <tr>
                    <th><h1></h1></th>
                    <th><h1>Name</h1></th>
                    <th><h1>Quantity</h1></th>
                    <th><h1>Price</h1></th>
                </tr>
            </thead>
            <tbody>
                <%for (Product p : o.getProducts()) {%>
                <tr>
                    <td><img src="<%=p.getProductImg()%>" width="100" height="100" style="object-fit: cover;object-position: center;"/></td>
                    <td><a href="<%=request.getContextPath()%>/product?name=<%=p.getSlug()%>"><%=p.getTitle()%></a></td>
                    <td><%=p.getStock()%></td>
                    <td><%=p.getPrice()%></td>
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
