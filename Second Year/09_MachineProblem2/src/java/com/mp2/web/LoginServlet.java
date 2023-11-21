/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp2.web;

import com.mp2.models.Login;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Richard Maru
 */
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticateUser(request, response);
    }
    protected void authenticateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Get the username and password stored in the Deployment Descriptor
        final String uname = getServletConfig().getInitParameter("uname");
        final String pword = getServletConfig().getInitParameter("pword");
            
        // Get the submitted username and password from the request body
        String username = request.getParameter("username");
        String password = request.getParameter("password");
            
        // Send data to Login model to authorize user
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        
        // Check whether the submitted values are equal with the values in the DD
        boolean authorized = login.validate(uname, pword);
        if (authorized) {
            response.sendRedirect("select.jsp");
        } else {
            request.setAttribute("login", login); // Pass the Login object to the error page
            RequestDispatcher view = request.getRequestDispatcher("error.jsp");
            view.forward(request, response);
        }
    }
}
