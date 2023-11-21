/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp2.web;

import com.mp2.models.HobbyList;
import com.mp2.models.ProfileInfo;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PortfolioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PortfolioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PortfolioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            // Get the parameter value of name
            String name = request.getParameter("name");
            
            // Get the data to be presented from ProfileInfo and HobbyList model
            ProfileInfo profile = new ProfileInfo(name);
            HobbyList hb = new HobbyList();
            List hobbies = hb.getHobbies(name);
            
            // Checks if String parameter from request is valid
            if (profile.getName() == null) { 
                    // Go to Error Page if parameter value is not valid
                    response.sendRedirect("error.jsp");
            } else {
                    // Pass the ProfileInfo object and the list of hobbies to portfolio.jsp
                    request.setAttribute("profile", profile);
                    request.setAttribute("hobbies", hobbies);
                    RequestDispatcher view = request.getRequestDispatcher("portfolio.jsp");
                    view.forward(request, response);
                    out.println("</body>");
                    out.println("</html>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
