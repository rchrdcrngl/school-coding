<%-- 
    Document   : notFound.jsp
    Created on : Nov 24, 2021, 8:54:41 AM
    Author     : Richard Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title><%=request.getServletContext().getInitParameter("header")%>: 404 Not Found</title>
    </head>
    <body>
        <style>
            *{
                margin:0;
                padding:0;
            }
            body{
                font-family: 'Lato', sans-serif;
                background-color: #1c1d21;
            }
            .header {
                overflow: hidden;
                background-color: #636e72;
                padding:10px 10px;
            }


            .header a {
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

            .header a.logo {
                font-size: 18px;
                font-family: 'Outfit', sans-serif;
                font-weight: bold;
            }

            .header a:hover {
                background-color: #82ccdd;
                color: white;
            }

            .header a.active {
                background-color: #00c988;
                color: white;
            }

            .header-right {
                float: right;
            }


            .footer {
                position: fixed;
                padding: 10px 0px;
                left: 0;
                bottom: 0;
                font-family: 'Roboto', sans-serif;
                font-weight: 300;
                width: 100%;
                background-color: #636e72;
                color: white;
                text-align: center;
            }

            .footer p{
                font-size:15px;
            }
            .content {
                width: 400px;
                margin: auto;
                text-align: center;
                padding-top: 150px;
            }

            .er-nu {
                display: inline-block;
                vertical-align: middle;
                font-size: 120px;
                color: #1e79c2;
                position:relative;
                -webkit-animation:top 3s 1; /* Safari 4+ */
                -moz-animation:    top 3s 1; /* Fx 5+ */
                -o-animation:      top 3s 1; /* Opera 12+ */
                animation:       top 3s 1; /* IE 10+, Fx 29+ */

            }
            @keyframes top {
                0% {
                    top:-140px;
                    color: #ff5722;
                }
                25%{
                    color: #4caf50;
                }
                50%{
                    color: #ffc107;
                }
                75%{
                    color: #00bcd4;
                }
                100% {
                    top:0px;
                }
            }
            .rnd {
                display: inline-block;
                vertical-align: middle;
                width: 70px;
                height: 94px;
                background: #1e79c2;
                border-radius: 24px;
                margin: 0px 10px;
                position:relative;
                -webkit-animation:bg 3s 1; /* Safari 4+ */
                -moz-animation:    bg 3s 1; /* Fx 5+ */
                -o-animation:      bg 3s 1; /* Opera 12+ */
                animation:       bg 3s 1; /* IE 10+, Fx 29+ */
            }
            @keyframes bg {
                0% {
                    top:-140px;
                    background: #ff5722;
                }
                25%{
                    background: #4caf50;
                }
                50%{
                    background: #ffc107;
                }
                75%{
                    background: #00bcd4;
                }
                100% {
                    top:0px;
                }
            }
            .ermsg {
                font-size: 23px;
                color: #1e79c2;
                text-transform: capitalize;
                position:relative;
                -webkit-animation:left 3s 1; /* Safari 4+ */
                -moz-animation:    left 3s 1; /* Fx 5+ */
                -o-animation:      left 3s 1; /* Opera 12+ */
                animation:       left 3s 1; /* IE 10+, Fx 29+ */
            }

            @keyframes left {
                0% {
                    bottom:-400px;
                    color: #ff5722;
                }
                25% {

                    color: #4caf50;
                }
                50% {

                    color: #ffc107;
                }
                75% {

                    color: #00bcd4;
                }
                100% {
                    bottom:0px;
                    color: #1e79c2;
                }
            }
            .eyes {
                width: 50px;
                margin: auto;
                margin-top: 25px;
            }

            .ey {
                width: 10px;
                height: 5px;
                background: #333;
                display: inline-block;
                border: 2px solid #fff;
                border-radius: 1px 2px 5px 5px;
                position:relative;
                -webkit-animation:up 2s infinite; /* Safari 4+ */
                -moz-animation:    up 2s infinite; /* Fx 5+ */
                -o-animation:      up 2s infinite; /* Opera 12+ */
                animation:       up 2s infinite; /* IE 10+, Fx 29+ */
            }
            @keyframes up {
                0% {
                    height: 3px;
                }
                25% {
                    height: 5px;
                }
                50% {
                    height: 3px;
                }
                75% {
                    height: 5px;
                }
                100% {
                    height: 3px;
                }
            }
            .ey:nth-child(1) {
                margin-right: 10px;
            }
            .muth {
                width: 15px;
                height: 4px;
                background: #fff;
                margin: auto;
                margin-top: 10px;
                border-radius: 0px 0px 3px 3px;
                position:relative;
                -webkit-animation:side 2s infinite; /* Safari 4+ */
                -moz-animation:    side 2s infinite; /* Fx 5+ */
                -o-animation:      side 2s infinite; /* Opera 12+ */
                animation:       side 2s infinite; /* IE 10+, Fx 29+ */
            }
            @keyframes side {
                0% {
                    right:0px;
                }
                25% {
                    right:1px;
                }
                50% {
                    right:0px;
                }
                75% {
                    right:1px;
                }
                100% {
                    right:0px;
                }
            }
        </style>

        <div class="header">
            <a href="<%= request.getContextPath()%>" class="logo"><%out.println(request.getServletContext().getInitParameter("header"));%></a>
        </div>

        <div class="content">

            <div class="er-nu">4</div>
            <div class="rnd">

                <div class="eyes">

                    <div class="ey"></div>
                    <div class="ey"></div>
                    <div class="muth"></div>
                </div>

            </div>
            <div class="er-nu">4</div>

            <div class="ermsg">The page could not be found</div>
        </div>

        <div class="footer">
            <p><%out.println(request.getServletContext().getInitParameter("footer"));%></p>
        </div>
    </body>
</html>
