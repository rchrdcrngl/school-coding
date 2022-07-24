<%-- 
    Document   : login
    Created on : Nov 23, 2021, 3:44:10 PM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires", "0");
    session.invalidate();
%>

<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="../css/home.css">
        <link rel="stylesheet" href="../css/styles.css">
        <title><%=request.getServletContext().getInitParameter("header")%> - Login</title>
    </head>
    <body>
        <div class="form-body">
            <div class="header">
                <a href="<%=request.getContextPath()%>" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
            </div>
            
            
             <div id="wrap">
                <form class="form" method="post" action="doLogin">
                    <h1 class="form-header">Login</h1>
                    <div class="form-content">
                      <div class="form-container">
                        <span class="tip-text">Username:</span>
                        <div class="case">
                          <input type="text" name="username" class="form-input">
                        </div>
                      </div>
                      <div class="form-container">
                        <span class="tip-text">Password:</span>
                        <div class="case">
                          <input type="password" name="password" class="form-input">
                        </div>
                      </div>
                      <div class="form-button">
                        <button class="btn-login" type="submit">
                          <span class="btn-text">Login</span>
                        </button>
                      </div>
                </div>
            </form>
           </div>
        </div> 
        
        
        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
