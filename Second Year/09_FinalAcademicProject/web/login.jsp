<%-- 
    Document   : login
    Created on : May 12, 2022, 2:55:06 PM
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
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="css/main.css">
        <title>Login - <%=application.getInitParameter("header")%></title>
    <body>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/doLogin">
                <a href="home"><div class="logo"><%=application.getInitParameter("header")%></div></a><br><br>

                <div class="input_box">
                    <input id="fl" type="text" class="text" name="username" placeholder="Username" required/>
                </div>
                <div class="input_box">
                    <input id="pass" type="password" class="password" data-type="password" name="password" placeholder="Password" required/>
                </div>
                <br>
                <div>
                    <img src="<%=request.getContextPath()%>/captcha.jpg" class="recaptcha_image" >
                    <input name="answer" placeholder="CAPTCHA Answer" required/>
                </div>
                <br>
                <input type="submit" name="submit" value="Login" class="submit_btn">
                <%if (request.getAttribute("err") != null) {%>
                <div class="ls-error">
                    <p><%=((Exception) request.getAttribute("err")).getMessage()%></p>
                </div>
                <%}%>
                <div style="font-family: 'VT323', monospace;"><br>Don't have an account? <a href="signup.jsp" class="for_pass">Sign up</a></div>
            </form>
        </div>
    </body>
</html>
