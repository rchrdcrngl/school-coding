<%-- 
    Document   : nullError
    Created on : Nov 24, 2021, 8:43:30 AM
    Author     : Richard Maru
--%>
<%@ page isErrorPage = "true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires", "0");
    session.invalidate();
%>
<html>
    <head>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }
            body{
                /*background-color: #232B40;*/
                background: linear-gradient(#141e30, #243b55);
                background-attachment: fixed;
                background-size: cover;
            }

            .logo {
                font-family: 'Press Start 2P', cursive;
                font-size: 24px;
                color: white;
            }

            .header {
                background-color: #34495e;
                padding:10px;
                overflow:hidden;
            }


            .header a {
                float: left;
                color: white;
                text-align: center;
                padding: 12px;
                text-decoration: none;
                font-family: 'Roboto', sans-serif;
                font-size: 14px;
                font-weight: 700;
                border-radius: 4px;
                display: block;
            }

            .header p{
                float: left;
                color: white;
                text-align: center;
                padding: 12px;
                text-decoration: none;
                font-family: 'Roboto', sans-serif;
                font-size: 18px;
                font-weight: 700;
                border-radius: 4px;
            }

            .header .logo {
                font-family: 'Press Start 2P', cursive;
                font-size: 20px;
                color:white;
            }

            .header a:hover {
                background-color: #2c3e50;
                color: white;
                box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
            }

            .header a.active {
                background-color: #2c3e50;
                color: white;
                box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
            }

            .header-right {
                float: right;
            }

            .dropdown {
                float: left;
                overflow: hidden;
            }

            /* Dropdown button */
            .dropdown .dropbtn {
                color: white;
                text-align: center;
                padding: 12px;
                text-decoration: none;
                font-family: 'Roboto', sans-serif;
                font-size: 14px;
                font-weight: 700;
                border-radius: 4px;
                border: none;
                outline: none;
                background-color: transparent;
            }

            /* Add a red background color to navbar links on hover */
            .navbar a:hover, .dropdown:hover .dropbtn {
                background-color: #e67e22;
            }

            /* Dropdown content (hidden by default) */
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #2c3e50;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1000;
                border-radius: 4px;
            }

            .header-right .dropdown .dropdown-content{
                right:10px;
            }

            /* Links inside the dropdown */
            .dropdown-content a {
                float: none;
                text-decoration: none;
                color: white;
                text-align: center;
                padding: 12px;
                font-family: 'Roboto', sans-serif;
                font-size: 14px;
                border-radius: 4px;
                display: block;
            }

            /* Add a grey background color to dropdown links on hover */
            .dropdown-content a:hover {
                background-color: #34495e;
            }

            /* Show the dropdown menu on hover */
            .dropdown:hover .dropdown-content {
                display: block;
            }

            /* Style the search box inside the navigation bar */
            .header input[type=text] {
                font-family: 'Roboto', sans-serif;
                font-size: 12px;
                float:right;
                padding: 12px;
                border: none;
                margin-right:5px;
                border-radius: 4px;
                color:white;
                background: #2c3e50;
            }
            .header form{
                float: left;
                color: white;
                text-align: center;
                border-radius: 4px;
                display: block;
            }


            .footer {
                color: #8F98A0;
                font-size: 12px;
                line-height: 16px;
                background-color: rgba(0,0,0,0.5);
                padding: 10px 0px;
                position:fixed;
                width:100%;
                left: 0;
                bottom: 0;
                text-align: center;
            }   
            .footer .insert-margin {
                height: 10px;
            }
            .footer .insert-border {
                max-width: 940px; 
                margin: 0 auto;
                border-top: 1px solid #29363d;
            }
            .footer .disclaimer {
                text-align: left;
                max-width: 870px; 
                margin: 8px auto;
            }  
            .footer a {
                color: white;
                text-decoration: underline;
            }

            .error-container{
                max-width: 440px;
                padding: 0 20px;
                margin: 170px auto;
            }
            .error-box{
                width: 100%;
                background: #fff;
                border-radius: 5px;
                box-shadow: 0px 4px 10px 1px rgba(0,0,0,0.1);
            }
            .error-box .title{
                height: 90px;
                background: #e46b64;
                color: #fff;
                border-radius: 5px;
                padding: 10px;
                font-family: 'Press Start 2P', cursive;
                font-size: 24px;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            @media screen and (max-width: 1000px) {

                .header a {
                    float: none;
                    display: none;
                    text-align: left;
                    font-size: 13px;
                    line-height:18px;
                }
                .header a.logo {
                    text-align: center;
                    margin-bottom: 5px;
                    display:block;
                }
                .header .dropbtn{
                    display:none;
                }
                .header-right {
                    float: none;
                    display:block;
                }
                .header-right a{
                    font-size: 13px;
                    display:block;
                }

                .footer {
                    font-size: 10px;
                }
                .footer .insert-margin {
                    height: 5px;
                }

                .header form{
                    float: none;
                    display: block;
                    margin-left:10px;
                }
                .header input[type=text] {
                    border: 1px solid #ccc;
                    width:100%;
                    margin-bottom: 10px;
                }
                .error-box .title{
                    height:60px;
                    font-size:20px;
                }
            }
        </style>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/main.css">
        <title><%=application.getInitParameter("header")%>: Authentication Error</title>
    </head>
    <body>
        <div class="header">
            <a href="<%= request.getContextPath()%>" class="logo"><%=application.getInitParameter("header")%></a>
        </div>
        
        <div class="error-container">
            <div class="error-box">
                <div class="title">Authentication Error</div>
                <center>
                    <br><br>
                    <div style="margin: 20;">
                        <h3>Authentication Error</h3><br>
                        <%=exception.getMessage()%>
                        <a href="<%=request.getContextPath()+"/logout"%>">Back to login</a>
                    </div>
                    <br><br>
                </center>
            </div>
        </div>

        
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
