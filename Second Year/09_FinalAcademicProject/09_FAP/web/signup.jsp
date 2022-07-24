<%-- 
    Document   : signup
    Created on : May 12, 2022, 2:55:17 PM
    Author     : Richard Maru
--%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    session.removeAttribute("user");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/ls.css">
        <link rel="stylesheet" href="css/main.css">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Sign Up - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/doSignup">
                <a href="home"><div class="logo"><%=application.getInitParameter("header")%></div></a><br><br>
                <div class="input_box">
                    <input id="first" type="text" class="text" name="firstname" placeholder="First Name" required>
                </div>
                <div class="input_box">
                    <input id="last" type="text" class="text" name="lastname" placeholder="Last Name" required>
                </div>
                <div class="input_box">
                    <input id="user" type="text" class="text" name="username" placeholder="Username" required>
                </div>
                <div class="input_box">
                    <input id="pass" type="password" class="password" data-type="password" name="password" placeholder="Password" required><br>
                </div>
                <div class="input_box">
                    <input id="pass" type="password" class="password" data-type="password" name="confirmPassword" placeholder="Confirm Password"required>
                </div>
                <div>
                    <img src="<%=request.getContextPath()%>/captcha.jpg" class="recaptcha_image" >
                    <input name="answer" placeholder="CAPTCHA Answer" required/>
                </div>
                <br>
                <input type="submit" class="submit_btn">
                <%if (request.getAttribute("err") != null) {%>
                <div class="ls-error">
                    <p><%=((Exception) request.getAttribute("err")).getMessage()%></p>
                </div>
                <%}%>
                <div style="font-family: 'VT323', monospace;"><br>Already have an account? <a href="login.jsp" class="for_pass">Login</a></div>
            </form>
        </div>
    </body>
</html>
