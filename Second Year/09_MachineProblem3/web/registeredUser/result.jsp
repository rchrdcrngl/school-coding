<%-- 
    Document   : result
    Created on : Nov 27, 2021, 11:39:18 PM
    Author     : Richard Maru
--%>

<%@page import="com.mp3.models.User"%>
<%@page import="com.mp3.models.NullValueException"%>
<%@page import="com.mp3.models.Account"%>
<%@page import="com.mp3.models.AuthenticationException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    Integer success = (Integer) request.getAttribute("success");
    User user = (User) session.getAttribute("user");
    if (user==null){
        throw new ServletException(new AuthenticationException("You need to login."));
    }
    if (success == null) {
        throw new ServletException(new NullValueException("Invalid Request"));
    }
%>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="../css/styles.css">
        <link rel="stylesheet" href="../css/home.css">
        <title><%out.println(request.getServletContext().getInitParameter("header"));%> - Transaction</title>
    </head>
    <body>

        <%
            String message = "";
            if (request.getAttribute("deposit") != null) { //Deposit Transaction
                if (success == 1) {
                    message = "Deposit Successful!";
                } else {
                    message = "Something went wrong...";
                }
            } else if (request.getAttribute("withdraw") != null) { //Withdraw Transaction
                if (success == -1) {
                    message = "Insufficient Balance!";
                } else {
                    message = "Withdrawal Successful!";
                }
            } else { //Unknown Transaction
                response.sendRedirect("home.jsp");
            }
        %>

        <div class="home-body">
            <div class="header">
                <a href="#" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
                <div class="header-right">
                    <a href="home">Home</a>
                    <a href="withdraw">Withdraw</a>
                    <a href="deposit">Deposit</a>
                    <a href="logout">Logout</a>
                </div>
            </div>
            <div class="result-container">
                <div class="result-box">
                    <div class="split left">
                        <div style="border-radius:200px; width:100px; background: #faf9f5; margin:0px auto;">
                            <i style="color: #bc9266; font-size: 50px; line-height: 100px; margin-left:25px;">
                                <%if (success>=0) {out.println("✓");} else {out.println("X");}%>
                            </i>
                        </div><br>
                        <h1 class="centered"><%=message%></h1>
                    </div>
                    <div class="split right">
                        <h2>Previous Balance: <%=(Double) request.getAttribute("balance-prev")%></h2>
                        <%if (request.getAttribute("deposit") != null) {%><h2>Amount Deposited: <%=(Double) request.getAttribute("deposit")%></h2><%}%>
                        <%if (request.getAttribute("withdraw") != null) {%><h2>Amount Withdrawn: <%=(Double) request.getAttribute("withdraw")%></h2><%}%>
                        <h2>Current Balance: <%=(Double) request.getAttribute("balance-cur")%></h2>

                        <%if (success == 0) {%>
                        <p style="font-size: 12pt">You are below maintaining balance. You have been penalized by <%=Account.penalty%> every time you withdraw.</p>
                        <p style="font-size: 12pt">You have had <i><%=(Integer) request.getAttribute("penalty")%> penalty/s</i> since your last transaction below maintaining balance.</p>
                        <%}%><br/><br/>
                        <a href="home" style="color:#fff;">Back to Home</a>
                    </div>
                </div>
            </div>
        </div>



        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
