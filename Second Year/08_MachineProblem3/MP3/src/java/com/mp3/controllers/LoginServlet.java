
package com.mp3.controllers;

import com.mp3.models.Account;
import com.mp3.models.AuthenticationException;
import com.mp3.models.NullValueException;
import com.mp3.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get the username and password stored in the Deployment Descriptor
        final String uname = getServletConfig().getInitParameter("uname");
        final String pword = getServletConfig().getInitParameter("pword");
        
        // Get the submitted username and password from the request body
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Checking of username and password values
        if (username == null || username.equals("")) throw new ServletException(new NullValueException("Username is empty"));
        if (password == null || password.equals("")) throw new ServletException(new NullValueException("Password is empty"));
        
        // Check if there is a User object
        User user = new User(uname, pword);
        if (user == null) throw new ServletException(new NullValueException("No USER!!"));
        
        // Get Account object from ServletContext
        ServletContext ctx = request.getServletContext();
        Account account = (Account)ctx.getAttribute("account");
        if (account == null) throw new ServletException(new NullValueException("No ACCOUNT!!"));
        
        // Validate login
        boolean authorized = user.validate(username, password);
        if (authorized) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("account", account);
            response.sendRedirect(request.getContextPath() +"/registeredUser/home");
        } else {
            if (user.isValid() == 0) throw new ServletException(new AuthenticationException("Incorrect Password"));
            if (user.isValid() == -1) throw new ServletException(new AuthenticationException("Incorrect Username"));
        }  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
