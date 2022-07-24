<%-- 
    Document   : category
    Created on : May 12, 2022, 3:02:46 PM
    Author     : Richard Maru
--%>

<%@page import="models.dao.CategoryDAO"%>
<%@page import="models.entity.Category"%>
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
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("products.jsp");
        }
    }
    Category c = (Category) (new CategoryDAO()).getCategory(id);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/pay.css">
        <title>Category - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="category.jsp">Add New Category</a>
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
                    <form id="orderfrm" action="<%=request.getContextPath()%>/category" method="post">
                        <h1>
                            Category Details
                        </h1>
                        <%if (request.getAttribute("err") != null) {%>
                        <div class="error">
                            <label><%=((Exception) request.getAttribute("err")).getMessage()%></label>
                        </div>
                        <%}%>
                        <div>
                            <label for="number">Category Name</label>
                            <input type="text" name="title" value="<%=c == null ? "" : c.getTitle()%>"></input>
                        </div>
                        <div>
                            <label for="number">Category Slug</label>
                            <input type="text" name="slug" value="<%=c == null ? "" : c.getSlug()%>"></input>
                        </div>
                        <div class="btns">
                            <button type="submit" name="<%=c == null ? "add" : "edit"%>" value="<%=c == null ? "" : c.getId()%>"><%=c == null ? "Add Category" : "Edit Category"%></button>
                            <%if (c != null) {%><button type="submit" name="delete" value="<%=c.getId()%>">Delete</button><%}%>
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

