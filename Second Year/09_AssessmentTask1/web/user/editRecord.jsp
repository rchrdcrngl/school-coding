<%-- 
    Document   : editRecord
    Created on : Mar 4, 2022, 10:31:43 PM
    Author     : Richard Maru
--%>

<%@page import="model.exception.NullValueException"%>
<%@page import="model.exception.AuthenticationException"%>
<%@page import="model.user.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) session.getAttribute("user");
    if (account == null || !account.getType().equals("user")) {
        throw new ServletException(new AuthenticationException("You need to login."));
    }
    if(request.getParameter("recno")==null) throw new ServletException(new NullValueException("Invalid Request."));
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
        <link rel="stylesheet" href="../styles/main.css">
        <title><%=application.getInitParameter("header")%>: Edit Record</title>
    </head>
    <body>
        <div class="home-container" style="height: 100vh;width:100%;position:fixed;">
            <div class="header">
                <a class="logo" href="home.jsp"><i class="material-icons" style="vertical-align: text-bottom;">coronavirus</i>     <%out.println(request.getServletContext().getInitParameter("header"));%></a>
                <div class="header-right">
                    <a href="#">Add Record</a>
                    <a href="<%=request.getContextPath() + "/logout"%>">Logout</a>
                </div>
            </div>
            <div class="frm-container">
                <div class="frm2">
                    <div class="title">Edit Record</div>
                    <form action="<%=request.getContextPath()%>/records" method="POST">
                        <%String[] date = (request.getParameter("dosage-date")).split("-");%>
                        <input name="ID_NO" value="<%=account.getIDNO()%>" type="hidden">
                        <input name="RECORD_NO" value="<%=request.getParameter("recno")%>" type="hidden">
                        <div class="row">
                            <div><label>Dosage No:</label><input name="DOSAGE_NO" type="number" max="9" min="1" value="<%=request.getParameter("dosage-no")%>" required></div>
                            <div><label>Dosage Lot No:</label><input name="LOT_NO" value="<%=request.getParameter("dosage-lotno")%>" required></div>
                        </div>
                        <div class="row">
                            <div><label>Month:</label><input name="MONTH" type="number" min="1" max="12" value="<%=date[1]%>" required></div>
                            <div><label>Day:</label><input name="DAY" type="number" min="1" max="31" value="<%=date[2]%>" required></div>
                            <div><label>Year:</label><input name="YEAR" type="number" minlength="4" maxlength="4" value="<%=date[0]%>" required></div>
                        </div>
                        <div class="row">
                            <div><label>Vaccine Manufacturer:</label><input name="MANUFACTURER" value="<%=request.getParameter("vax-man")%>" required></div>
                        </div>
                        <div class="row">
                            <div><label>Site City:</label><input name="SITE_CITY" value="<%=request.getParameter("site-city")%>"></div>
                            <div><label>Site Country:</label><input name="SITE_COUNTRY" value="<%=request.getParameter("site-country")%>"></div>
                        </div>
                        <button name="action" value="edit" type="submit">Edit Record</button>
                    </form>
                </div>
            </div>
            <div class="footer">
                <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
            </div>
        </div>
    </body>
</html>
