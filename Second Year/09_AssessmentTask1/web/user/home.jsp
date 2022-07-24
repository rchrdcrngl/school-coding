<%-- 
    Document   : home
    Created on : Mar 4, 2022, 10:16:13 PM
    Author     : Richard Maru
--%>

<%@page import="model.exception.DatabaseException"%>
<%@page import="model.record.RecordModel"%>
<%@page import="model.exception.AuthenticationException"%>
<%@page import="model.user.Account"%>
<%@page import="java.util.List"%>
<%@page import="model.record.Record"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!List<Record> res;%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    Account account = (Account) session.getAttribute("user");
    if (account == null || !account.getType().equals("user")) {
        throw new ServletException(new AuthenticationException("You need to login."));
    }
    RecordModel rec = new RecordModel();
    try {
        res = (List<Record>) rec.idSearch(account.getIDNO());
    } catch (DatabaseException e) {
        throw new ServletException(e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <script type="text/javascript" src= "https://code.jquery.com/jquery-2.1.1.min.js"></script> 
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../styles/main.css">
        <title><%=application.getInitParameter("header")%>: User Home</title>
    </head>
    <body>
        <div class="home-container" style="height: 100vh;width:100%;position:fixed;">
            <div class="header">
                <a class="logo" href="#"><i class="material-icons" style="vertical-align: text-bottom;">coronavirus</i>     <%out.println(request.getServletContext().getInitParameter("header"));%></a>
                <div class="header-right">
                    <a href="addRecord.jsp">Add Record</a>
                    <a href="<%=request.getContextPath() + "/logout"%>">Logout</a>
                </div>
            </div>
            <div class="row" style="top:15px;">
                <div class="side">
                    <div class="info">
                        <p>Name: <%=account.getName()%></p>
                        <p>ID No: <%=account.getIDNO()%></p>
                    </div>
                </div>

                <div class="main">
                    <div class="records-list">
                        <%=res.isEmpty() ? "There are no records found." : ""%>
                        <%for (Record r : res) {%>
                        <div class="record">
                            <form class="record-data" action="editRecord.jsp" method="POST">
                                <div class="dosage-no"><%=r.getDosageNo()%></div>
                                    <div class="other-data">
                                        <div><%=r.getVaccineManufacturer()%></div>
                                        <div><%=r.getDosageLotNo()%></div>
                                        <div><%=r.getDosageDate()%></div>
                                        <div><%=r.getSiteCity()%>, <%=r.getSiteCountry()%></div>
                                        <input type="hidden" style="font-size: 1.5em;" name="dosage-lotno" value="<%=r.getDosageLotNo()%>" readonly>
                                        <input type="hidden"name="dosage-no" class="centered" value="<%=r.getDosageNo()%>" readonly>
                                        <input type="hidden"name="dosage-date" value="<%=r.getDosageDate()%>" readonly>
                                        <input type="hidden"name="vax-man" value="<%=r.getVaccineManufacturer()%>" readonly>
                                        <input type="hidden"name="site-city" value="<%=r.getSiteCity()%>" readonly>
                                        <input type="hidden"name="site-country" value="<%=r.getSiteCountry()%>" readonly>
                                        <div style="margin-top: 5px; color:lightblue;">
                                            <button name="recno" type="submit" value="<%=r.getRecordNO()%>"><i class="material-icons">edit</i></button>
                                            <a href="<%=request.getContextPath()%>/records?action=delete&delete=<%=r.getRecordNO()%>"><i class="material-icons">delete</i></a>
                                        </div>
                                    </div>
                            </form>
                        </div>

                        <%}%>
                    </div>
                </div>
                <div class="footer">
                    <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
                </div>
            </div>

    </body>
</html>
