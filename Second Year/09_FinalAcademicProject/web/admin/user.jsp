<%-- 
    Document   : type
    Created on : May 12, 2022, 3:02:55 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.UsersDAO"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    int id = -1;
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest") || user.getUserType().equals("employee")) {
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
    }
    if (request.getParameter("id") != null) {
        id = Integer.parseInt(request.getParameter("id"));
    }
    User u = (User) (new UsersDAO()).getUser(id);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/pay.css">
        <title>User Details - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="user.jsp">Add User</a>
                <a href="<%=request.getContextPath()%>/users?report=guest">Generate Site Guest List</a>
                <a href="<%=request.getContextPath()%>/users?report=employee">Generate Employee List</a>
                <div class="header-right">
                    <div class="dropdown">
                        <a href="#" class="dropbtn"><%=user.getFirstName()%> <%=user.getLastName()%></a>
                        <div class="dropdown-content">
                            <a href="<%=application.getContextPath()%>/logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div><br/>
        </header>
        <div class="frm-section">
            <div class="wrapper" style="background-color: #45aaf2;">
                <div class="container">
                    <form id="orderfrm" action="<%=request.getContextPath()%>/users" method="post">
                        <h1>
                            User Details
                        </h1>
                        <%if (request.getAttribute("err") != null) {%>
                        <div class="error">
                            <label><%=((Exception) request.getAttribute("err")).getMessage()%></label>
                        </div>
                        <%}%>
                        <div class="name">
                            <div>
                                <label for="f-name">First</label>
                                <input type="text" name="firstname" value="<%=u == null ? "" : u.getFirstName()%>" required></input>
                            </div>
                            <div>
                                <label for="l-name">Last</label>
                                <input type="text" name="lastname" value="<%=u == null ? "" : u.getLastName()%>" required></input>
                            </div>
                        </div>
                        <div class="address-info">
                            <div>
                                <label for="f-name">Month</label>
                                <input type="number" min="1" max="12" name="bmonth" value="<%=u == null ? "" : u.getBirthday()==null?"": u.getBirthday().getMonth()+1%>" required></input>
                            </div>
                            <div>
                                <label for="l-name">Day</label>
                                <input type="number" min="1" max="31" name="bday" value="<%=u == null ? "" : u.getBirthday()==null?"": u.getBirthday().getDate()%>" required></input>
                            </div>
                            <div>
                                <label for="l-name">Year</label>
                                <input type="number" name="byear" value="<%=u == null ? "" : u.getBirthday()==null?"": u.getBirthday().getYear()+1900%>" required></input>
                            </div>
                        </div>
                        <div class="street">
                            <label for="name">Street</label>
                            <input type="text" name="address" value="<%=u == null ? "" : u.getAddress()%>"></input>
                        </div>
                        <div class="address-info">
                            <div>
                                <label for="city">City</label>
                                <input type="text" name="city" value="<%=u == null ? "" : u.getCity()%>"></input>
                            </div>
                            <div>
                                <label for="state">Country</label>
                                <input type="text" name="country" value="<%=u == null ? "" : u.getCountry()%>"></input>
                            </div>
                            <div>
                                <label for="zip">Zip</label>
                                <input type="text" name="zipcode" value="<%=u == null ? "" : u.getZip_code()%>"></input>
                            </div>
                        </div>
                        <div>
                            <label for="number">Mobile Number</label>
                            <input type="tel" name="mobile" value="<%=u == null ? "" : u.getMobile()%>"></input>
                        </div>
                        <div>
                            <label for="email">Email</label>
                            <input type="email" name="email" value="<%=u == null ? "" : u.getEmail()%>" placeholder="*******@gmail.com"></input>
                        </div>
                        <div class="select">
                            <label for="type">User Type</label>
                            <select id="type" name="type">
                                <%if (u != null) {%><option value="<%=u.getUserType()%>" hidden selected="selected"><%=u.getUserType().toUpperCase()%></option><%}%>
                                <option value="guest">Guest</option>
                                <option value="employee">Employee</option>
                                <option value="admin">Admin</option>
                            </select>
                        </div>
                        <div>
                            <label for="username">Username : </label>
                            <input type="text" name="username" value="<%=u == null ? "" : u.getUsername()%>" required></input>
                        </div>
                        <div>
                            <label for="username">Password : </label>
                            <input name="password" type="password" value="<%=u == null ? "" : u.getPassword()%>"required></input>
                        </div>
                        <div class="btns">
                            <button type="submit" name="<%=u == null ? "add" : "edit"%>" value="<%=u == null ? "" : u.getId()%>"><%=u == null ? "Add User" : "Edit User"%></button>
                            <%if (u != null) {%><button type="submit" name="delete" value="<%=u.getId()%>">Delete</button><%}%>
                        </div>
                    </form>
                </div>
            </div>
        </div><br/><br/><br/>


        <div class="footer">
            <div class="insert-margin"></div>
            <div class="logo" stle="font-size:12px;margin:0px;"><%=application.getInitParameter("header")%></div><br/>
            <%=application.getInitParameter("footer")%>
            <div class="insert-border"></div>
            <div class="contact">
                Contact us through our Facebook page at <a target="_blank" href="https://www.facebook.com/richardmaru"><%=application.getInitParameter("header")%></a>
            </div>
        </div>
    </body>
</html>

