<%-- 
    Document   : withdraw
    Created on : Nov 27, 2021, 9:46:33 PM
    Author     : Richard Maru
--%>
<%@page import="com.mp3.models.User"%>
<%@page import="com.mp3.models.AuthenticationException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mp3.models.Account"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires", "0");
   
    
    User user = (User) session.getAttribute("user");
    if (user==null){
        throw new ServletException(new AuthenticationException("You need to login."));
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="../css/styles.css">
        <title><%=request.getServletContext().getInitParameter("header")%> - Withdraw</title>
    </head>
    <body>
        <div class="header">
            <a href="#" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
            <div class="header-right">
                <a href="home">Home</a>
                <a class="active" href="withdraw">Withdraw</a>
                <a href="deposit">Deposit</a>
                <a href="logout">Logout</a>
            </div>
        </div>
            
        <div class="container">
            <div class="frm">
                <div class="title">Enter Amount</div>
                <form action="doWithdraw" method="post">
                    <input type="number" step="0.01" name="amount" placeholder="Amount" required><br><br>
                    <input class="button" type="submit" value="Enter">
                </form>
            </div>
        </div>
        
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
