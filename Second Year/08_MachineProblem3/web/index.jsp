<%-- 
    Document   : index
    Created on : Nov 23, 2021, 3:25:25 PM
    Author     : Richard Maru
--%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires", "0");
    session.invalidate();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@900&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="css/styles.css">
        <title><%=request.getServletContext().getInitParameter("header")%></title>
    </head>
    <body style="background-image: url('https://images.unsplash.com/photo-1501167786227-4cba60f6d58f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80');">
        <div class="header">
            <a href="#" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
            <div class="header-right">
                <a href="registeredUser/login">Login</a>
         </div>
        </div>
         
         <div style="margin: 30px 0px 0px 30px;">
             <h1 style="font-weight: bold; font-family: 'Outfit', sans-serif;">WELCOME TO</h1>
             <h1 style="font-weight: bold; font-family: 'Outfit', sans-serif;">HAPPY BANK ONLINE!</h1>
         </div>
        
        
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
