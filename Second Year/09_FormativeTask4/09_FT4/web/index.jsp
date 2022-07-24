
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.exception.*"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    //session.invalidate();
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
        <div class="home-container" style="height: 100vh;width:100%;position:fixed;">
            <div class="header">
                <a class="logo" href="#"><%=application.getInitParameter("header")%></a>
                <div class="header-right">
                    <a href="<%=request.getContextPath()%>/signup.jsp">Signup</a>
                </div>
            </div>
            <div class="login-container">
                <div class="login-frm">
                    <div class="title">Login</div>
                    <form method="post" action="<%=request.getContextPath()%>/doLogin">
                        <div><label>Username:</label><input name="username" required></div>
                        <div><label>Password: </label><input name="password" type="password" required></div><br>
                        <div>
                            <img src="<%=request.getContextPath()%>/captcha.jpg"/><br>
                            <label>Enter CAPTCHA code: </label><input name="answer" required/>
                        </div>
                        <br/>
                        <input class="button" type="submit" value="Enter">
                        <br/>
                        <%if (request.getAttribute("err") != null) {%>
                        <div class="error">
                            <p><%=((Exception) request.getAttribute("err")).getMessage()%></p>
                        </div>
                        <%}%>
                    </form>
                </div>
            </div>
            <div class="footer">
                <p><%=application.getInitParameter("footer")%></p>
            </div>
        </div>
    </body>
</html>
