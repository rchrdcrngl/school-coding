<%-- 
    Document   : type
    Created on : May 12, 2022, 3:02:55 PM
    Author     : Richard Maru
--%>

<%@page import="models.entity.ProductMeta"%>
<%@page import="java.util.List"%>
<%@page import="models.dao.CategoryDAO"%>
<%@page import="models.entity.Category"%>
<%@page import="models.dao.ProductTypeDAO"%>
<%@page import="models.entity.ProductType"%>
<%@page import="models.dao.ProductDAO"%>
<%@page import="models.entity.Product"%>
<%@page import="models.exception.AuthenticationException"%>
<%@page import="models.entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    Product p = null;
    int id = -1;
    if (user == null) {
        throw new ServletException(new AuthenticationException("You have to login first."));
    }
    if (user.getUserType().equals("guest") || user.getUserType().equals("employee")) {
        throw new ServletException(new AuthenticationException("You need ADMIN credentials to view this site."));
    }
    if (request.getParameter("id") != null) {
        id = Integer.parseInt(request.getParameter("id"));
        try{
            p = (Product) (new ProductDAO()).adminGetProduct(id);
        } catch (Exception e){
            response.sendRedirect(request.getContextPath());
        }
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href=".../css/main.css">
        <link rel="stylesheet" href=".../css/pay.css">
        <title>Product Details - <%=application.getInitParameter("header")%></title>
    </head>
    <body>
        <header> 
            <div class="header">
                <a class="logo" href="index.jsp"><%=application.getInitParameter("header")%></a>
                <a href="product.jsp">Add Product</a>
                <a href="category.jsp">Add Category</a>
                <a href="type.jsp">Add Type</a>
                <a href="<%=request.getContextPath()%>/product?report=products">Generate Product List</a>
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
                    <form id="orderfrm" action="<%=request.getContextPath()%>/product" method="post">
                        <h1>
                            Product Details
                        </h1>
                        <%if (request.getAttribute("err") != null) {%>
                        <div class="error">
                            <label><%=((Exception) request.getAttribute("err")).getMessage()%></label>
                        </div>
                        <%}%>
                        <div>
                            <label for="name">Name</label>
                            <input type="text" name="title" value="<%=p == null ? "" : p.getTitle()%>" required></input>
                        </div>
                        <div>
                            <label for="name">Description</label>
                            <textarea type="text" name="description" style="width:100%;"><%=p == null ? "" : p.getDescription()%></textarea>
                        </div>
                        <div>
                            <label for="name">Slug</label>
                            <input type="text" name="slug" value="<%=p == null ? " " : p.getSlug()%>"></input>
                        </div>
                        <div>
                            <label for="name">Product Type</label>
                            <select name="type" required>
                                <%if (p != null) {%><option value="<%=p.getProductType().getId()%>" hidden selected="selected"><%=p.getProductType().getTitle()%></option><%}%>
                                <%for (ProductType pt : (new ProductTypeDAO()).getProductTypes()) {%>
                                <option value="<%=pt.getId()%>"><%=pt.getTitle()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div>
                            <label for="name" style="text-align: left; align-content: flex-start;">Product Category</label>
                            <%for (Category ct : (new CategoryDAO()).getCategories()) {%>
                            <div><label for="ctg"><%=ct.getTitle()%></label>
                                <input id="ctg" value="<%=ct.getId()%>" type="checkbox" name="category" style="width:30px;letter-spacing: 0px;float:left; margin-right: 10px;" <%=p != null ? p.getCtgHash().contains(ct.getId()) ? "checked" : "" : ""%>/></div>
                                <%}%>
                        </div>
                        <div id="meta-container">
                            <label for="name">Product Meta</label>
                            <a id="add-meta" style="color: purple; text-decoration: underline;">ADD FIELD</a>
                            <%if (p != null) {
                                    for (ProductMeta mt : (List<ProductMeta>) p.getMeta()) {%>
                            <div class="name">
                                <div>
                                    <label for="f-name">Key</label>
                                    <input type="text" name="key" value="<%=mt.getKey()%>"></input>
                                </div>
                                <div>
                                    <label for="l-name">Value</label>
                                    <input type="text" name="value" value="<%=mt.getValue()%>"></input>
                                </div>
                            </div>
                            <%}
                            } else {%>
                            <div class="name">
                                <div>
                                    <label for="f-name">Key</label>
                                    <input type="text" name="key" value=""></input>
                                </div>
                                <div>
                                    <label for="l-name">Value</label>
                                    <input type="text" name="value" value=""></input>
                                </div>
                            </div><%}%>
                        </div>
                        <div>
                            <label for="name">SKU</label>
                            <input type="text" name="sku" value="<%=p == null ? "" : p.getSku()%>"></input>
                        </div>
                        <div>
                            <label for="name">Price</label>
                            <input type="number" step="any" name="price" value="<%=p == null ? "" : p.getPrice()%>" required></input>
                        </div>
                        <div>
                            <label for="name">Stock</label>
                            <input type="number" name="stock" value="<%=p == null ? "" : p.getStock()%>" required></input>
                        </div>
                        <div>
                            <label for="name">Manufacturer</label>
                            <input type="text" name="manufacturer" value="<%=p == null ? "" : p.getManufacturer()%>"></input>
                        </div>
                        <div>
                            <label for="name">Product Image URL</label>
                            <input type="text" name="imgurl" value="<%=p == null ? "" : p.getProductImg()%>"></input>
                        </div>
                        <div>
                            <div>
                                <label>Visible</label>
                                <input type="checkbox" name="active" style="width:30px;letter-spacing: 0px;float:left; margin-right: 10px;" <%=p != null ? p.isActive() ? "checked" : "" : ""%>/>
                            </div>
                        </div>
                        <div class="btns">
                            <button type="submit" name="<%=p == null ? "add" : "edit"%>" value="<%=p == null ? "" : p.getId()%>"><%=p == null ? "Add Product" : "Edit Product"%></button>
                            <%if (p != null) {%><button type="submit" name="delete" value="<%=p.getId()%>">Delete</button><%}%>
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

        <script>
            document.getElementById("add-meta").addEventListener("click", addMeta);
            function addMeta() {
                console.log("clicked");
                var outer_container = document.createElement("div");
                outer_container.setAttribute("class", "name");
                var keyContainer = document.createElement("div");
                var valContainer = document.createElement("div");
                var lblKey = document.createElement("label");
                var txtKey = document.createElement("input");
                lblKey.setAttribute("for", "key");
                lblKey.innerHTML = "Key";
                txtKey.setAttribute("type", "text");
                txtKey.setAttribute("name", "key");
                keyContainer.append(lblKey);
                keyContainer.append(txtKey);
                outer_container.append(keyContainer);

                var lblValue = document.createElement("label");
                var txtValue = document.createElement("input");
                lblValue.setAttribute("for", "key");
                lblValue.innerHTML = "Value";
                txtValue.setAttribute("type", "text");
                txtValue.setAttribute("name", "value");
                valContainer.append(lblValue);
                valContainer.append(txtValue);
                outer_container.append(valContainer);

                document.getElementById("meta-container").appendChild(outer_container);
            }
        </script>
    </body>
</html>

