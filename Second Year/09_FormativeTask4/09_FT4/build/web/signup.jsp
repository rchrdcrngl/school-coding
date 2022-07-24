<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    //session.invalidate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="styles/main.css">
        <title><%=application.getInitParameter("header")%>: Signup</title>
    </head>
    <body>
        <div class="home-container" style="height: 100vh;width:100%;position:fixed;">
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <div class="header-right">
                    <a href="<%=request.getContextPath()%>">Login</a>
                </div>
            </div>
            <div class="frm-container">
                <div class="frm2">
                    <div class="title">Signup</div>
                    <form action="<%=request.getContextPath()%>/doSignup" method="POST">
                        <div class="row">
                            <div><label>First Name:</label><input name="firstname" required></div>
                            <div><label>Last Name:</label><input name="lastname" required></div>
                        </div>
                        <div class="row">
                            <div style="flex:1;"><label>Username:</label><input name="username" required></div>
                        </div>
                        <div class="row">
                            <div><label>Password:</label><input name="password" type="password" required></div>
                            <div><label>Confirm Password:</label><input name="confirm-pwd" type="password" required></div>
                        </div><br>
                        <div class="row">
                            <label for="usertype">User Type:    </label>
                            <select name="type" id="usertype">
                              <option value="guest">Guest</option>
                              <option value="admin">Admin</option>
                            </select>
                        </div><br>
                        <div>
                            <img src="<%=request.getContextPath()%>/captcha.jpg"/><br>
                            <label>Enter CAPTCHA code: </label><input name="answer" required/>
                        </div>
                        <button type="submit" style="margin-right: 10px;">Signup</button>
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
