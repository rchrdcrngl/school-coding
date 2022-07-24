<%-- 
    Document   : home
    Created on : Nov 23, 2021, 4:22:49 PM
    Author     : Richard Maru
--%>

<%@page import="com.mp3.models.Account"%>
<%@page import="com.mp3.models.User"%>
<%@page import="com.mp3.models.NullValueException"%>
<%@page import="com.mp3.models.AuthenticationException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!Account account;%>
<%!String uname;%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    User user = (User) session.getAttribute("user");
    account = (Account) session.getAttribute("account");
    if (user == null || account == null) {
        throw new ServletException(new AuthenticationException("You need to login."));
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
        <title><%out.println(request.getServletContext().getInitParameter("header"));%> - Home</title>
    </head>
    <body>
        <div class="home-body">
            <div class="header">
                <a href="#" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
                <div class="header-right">
                    <a class="active" href="#">Home</a>
                    <a href="withdraw">Withdraw</a>
                    <a href="deposit">Deposit</a>
                    <a href="logout">Logout</a>
                </div>
            </div>
            <div class="split left" style="left:-10%;">
                <div class="balance centered">
                    Username: <%=user.getUsername()%><br/><br/>
                    Password: <%=user.getPassword()%>
                </div>
            </div>   
            <div class="split right" style="right:10%;">
                <section>
                    <div class="card">
                        <div class="face front">
                            <h3 class="debit">Debit Account</h3>
                            <h3 class="bank"><%=request.getServletContext().getInitParameter("header")%></h3>
                            <img src="" class="chip">
                            <h3 class="number"><%=account.getAccountNumber()%></h3>
                            <h5 class="cardHolder"><%=account.getName()%></h5>
                        </div>
                        <div class="face back">
                            <div class="blackbar"></div>
                            <div class="ccvtext">
                                <h5>Current Balance:</h5>
                                <div class="whiteBar">
                                    PHP. <%=account.getBalance()%>
                                </div>
                            </div>
                            <h5 class="text">
                                Maintaining Balance: <%=Account.maintainingBalance%><br>
                                Penalty per transaction below maintaining balance: <%=Account.penalty%><br>
                                Penalties induced: <%=account.checkForPenalty()%>
                            </h5>
                        </div> 
                    </div>
                </section>
            </div>
        </div>


        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
