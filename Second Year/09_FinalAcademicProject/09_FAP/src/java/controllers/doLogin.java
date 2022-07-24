/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.entity.User;
import models.dao.UsersDAO;
import models.exception.AuthenticationException;
import models.exception.DatabaseException;
import models.exception.NullValueException;
import nl.captcha.Captcha;

/**
 *
 * @author Richard Maru
 */
public class doLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the submitted username and password from the request body
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String answer = request.getParameter("answer");
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        //Check CAPTCHA
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        if (captcha == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        if (!captcha.isCorrect(answer)) {
            request.setAttribute("err", new AuthenticationException("Incorrect CAPTCHA answer."));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Checking of username and password values
        if (username == null) {
            throw new ServletException(new NullValueException("Invalid Request. Username is empty"));
        }
        if (password == null) {
            throw new ServletException(new NullValueException("Invalid Request. Password is empty"));
        }
        if (username.equals("")) {
            request.setAttribute("err", new NullValueException("Username is empty."));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        if (password.equals("")) {
            request.setAttribute("err", new NullValueException("Password is empty."));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        //Send Data to AccountController
        User user = new User(username.trim(), password.trim());
        UsersDAO userDAO = new UsersDAO();

        int success = -1;
        try {
            success = userDAO.login(user);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        }

        String type = user.getUserType();

        if (success == 1) {
            session.setAttribute("user", user);
            switch(type){
                case "guest": response.sendRedirect("home"); return;
                case "employee": response.sendRedirect("employee/index.jsp");return;
                case "admin": response.sendRedirect("admin/index.jsp");return;
            }
        } else {
            request.setAttribute("err", new AuthenticationException(success));
            request.getRequestDispatcher("login.jsp").forward(request, response);return;
            //throw new ServletException(new AuthenticationException(auth));
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
