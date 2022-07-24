<%-- 
    Document   : home
    Created on : Mar 4, 2022, 9:47:23 PM
    Author     : Richard Maru
--%>
<%@page import="model.exception.DatabaseException"%>
<%@page import="model.record.RecordModel"%>
<%@page import="model.record.RecordModel"%>
<%@page import="model.exception.AuthenticationException"%>
<%@page import="model.user.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.record.Record"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!List<Record> res = new ArrayList<Record>();%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    Account account = (Account) session.getAttribute("user");
    if (account == null || !account.getType().equals("admin")) {
        throw new ServletException(new AuthenticationException("You need to login."));
    }
    RecordModel rec = new RecordModel();
    try {
        res = (List<Record>) rec.getAllRecords();
    } catch (DatabaseException e) {
        throw new ServletException(e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@300&display=swap" rel="stylesheet">
        <title><%=application.getInitParameter("header")%>: Admin Home</title>
    </head>
    <body>
        <div class="home-container" style="height: 100%;width:100%;position:fixed;">
            <div class="header">
                <a class="logo" href="#"><i class="material-icons" style="vertical-align: text-bottom;">coronavirus</i>     <%out.println(request.getServletContext().getInitParameter("header"));%></a>
                <div class="header-right">
                    <a href="<%=request.getContextPath() + "/logout"%>">Logout</a>
                </div>
            </div>
            <div class="row" style="top:15px;">
                <div class="side">
                    <div class="info">
                        <p>Name: <%=account.getName()%></p>
                        <p>ID No: <%=account.getIDNO()%></p><br>
                        <hr><br>
                        <div class="frm">
                            <div class="title">Search Record</div>
                            <form action="<%=request.getContextPath()%>/records" method="POST">
                                <input name="query"><br/>
                                <select name="type">
                                    <option value="name">By Name</option>
                                    <option value="id">By ID</option>
                                    <option value="manufacturer">By Manufacturer</option>
                                </select>
                                <button name="action" value="search" type="submit"><i class="material-icons" style="font-size: 12pt;">search</i> Search</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="main">
                    <div class="records-list">
                        <%=res.isEmpty() ? "There are no records found." : ""%>
                        <%  System.out.println("SIZE:" + res.size());
                            boolean end = true;
                            int i = 0;
                            for (i = 0; i < res.size() - 1; i++) {
                                Record r = (Record) res.get(i);
                                Record r2 = (Record) res.get(i + 1);
                                if (end) {%>
                        <div class="record-group">
                            <div class="group-header">
                                <div class="header-text"><%=r.getIdNO()%> - <%=r.getFirstName()%> <%=r.getLastName()%></div>
                            </div>
                            <div class="record-individual-container">
                                <%System.out.println(r.getIdNO());
                                        end = false;
                                    }
                                %>

                                <div class="record-individual">
                                    <div class="dosage-no"><%=r.getDosageNo()%></div>
                                    <div class="other-data">
                                        <div><%=r.getVaccineManufacturer()%></div>
                                        <div><%=r.getDosageLotNo()%></div>
                                        <div><%=r.getDosageDate()%></div>
                                        <div><%=r.getSiteCity()%>, <%=r.getSiteCountry()%></div>
                                    </div>
                                </div>

                                <%if (r.getIdNO() == r2.getIdNO()) {
                                        System.out.print("-");
                                        continue;

                                    } else {
                                        end = true;
                                        System.out.println("/");%>
                            </div></div>
                            <%}
                                }
                                System.out.println("i:" + i + " " + end);
                                if (res.size() - i >= 1) {
                                    Record r = (Record) res.get(res.size() - 1);
                                    System.out.println("PAHABOL: " + r.getIdNO());
                                    if (end == true) {%>
                        <div class="record-group">
                            <div class="group-header">
                                <div class="header-text"><%=r.getIdNO()%> - <%=r.getFirstName()%> <%=r.getLastName()%></div>
                            </div>
                            <div class="record-individual-container">
                                <%}%>
                                <div class="record-individual">
                                    <div class="dosage-no">
                                        <%=r.getDosageNo()%>
                                    </div>
                                    <div class="other-data">
                                        <div><%=r.getVaccineManufacturer()%></div>
                                        <div><%=r.getDosageLotNo()%></div>
                                        <div><%=r.getDosageDate()%></div>
                                        <div><%=r.getSiteCity()%>, <%=r.getSiteCountry()%></div>
                                    </div>
                                </div></div>
                                <%}%>
                        </div>
                    </div>
                </div>
                <div class="footer">
                    <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
                </div>
            </div>
    </body>
</html>
