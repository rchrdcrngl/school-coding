<%-- 
    Document   : select
    Created on : Oct 14, 2021, 1:47:34 PM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>MP2 - Select Portfolio</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="header">
            <a href="#" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
            <div class="header-right">
                <a href="index.jsp">Logout</a>
            </div>
        </div>
            <div class="login-container">
            <div class="login-frm">
                <div class="title">Select Portfolio</div>
                <!-- form with a GET method request -->
                <form method="get" action="portfolio" style="text-align:center;">
                    <select name="name" size="1" style="text-align:center;">
                    <option value="caringal">Richard Maru Caringal</option>
                    <option value="pagulayan">Christian Charles Pagulayan</option>
                    </select><br><br>
                <input class="button" type="submit" value="Enter">
                </form>
            </div>
        </div>
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
