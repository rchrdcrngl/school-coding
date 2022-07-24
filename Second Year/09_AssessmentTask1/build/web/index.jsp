<%-- 
    Document   : index
    Created on : Mar 4, 2022, 10:34:30 PM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.exception.*"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    session.invalidate();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="styles/main.css">
        <title><%=application.getInitParameter("header")%></title>
    </head>
    <body style="height: 100%;width:100%;position:fixed;">
        <div style="overflow:hidden; position:relative;">
            <img class="bg" src="resources/clip-1664.png">
            <div class="header">
                <p class="logo"><i class="material-icons" style="vertical-align: text-bottom;">coronavirus</i>     <%out.println(request.getServletContext().getInitParameter("header"));%></p>
            </div>
            <div class="login-container">
                <div class="login-frm">
                    <div class="title">Login</div>
                    <form method="post" action="login">
                        <input type="text" name="username" placeholder="Username" required><br><br>
                        <input type="password" name="password" placeholder="Password" required><br><br>
                        <input class="button" type="submit" value="Enter">
                    </form>
                    <%if (request.getAttribute("err") != null) {%>
                    <div class="error">
                        <p><%=((Exception) request.getAttribute("err")).getMessage()%></p>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>


        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>

</body>
</html>
