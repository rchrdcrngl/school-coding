/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.UsersDAO;
import models.entity.User;
import models.exception.AuthenticationException;
import models.exception.DatabaseException;
import models.exception.NullValueException;
import nl.captcha.Captcha;

/**
 *
 * @author Richard Maru
 */
public class doSignup extends HttpServlet {
    UsersDAO model = new UsersDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Get the submitted values from the request body
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm_pwd = request.getParameter("confirmPassword");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String answer = request.getParameter("answer");

        //Check CAPTCHA
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        if (captcha == null) {
            response.sendRedirect("signup.jsp");
            return;
        }
        if (captcha.isCorrect(answer)==false) {
            request.setAttribute("err", new AuthenticationException("Incorrect CAPTCHA answer."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Checking the validity of values
        if (username == null) {
            throw new ServletException(new NullValueException("Invalid Request. Username is empty"));
        }
        if (password == null) {
            throw new ServletException(new NullValueException("Invalid Request. Password is empty"));
        }
        if (confirm_pwd == null) {
            throw new ServletException(new NullValueException("Invalid Request. Confirm Password is empty"));
        }
        if (firstname == null) {
            throw new ServletException(new NullValueException("Invalid Request. First Name is empty"));
        }
        if (lastname == null) {
            throw new ServletException(new NullValueException("Invalid Request. Last Name is empty"));
        }
        if (username.trim().equals("")) {
            request.setAttribute("err", new NullValueException("Username is empty."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        if (password.trim().equals("")) {
            request.setAttribute("err", new NullValueException("Password is empty."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        if (confirm_pwd.trim().equals("")) {
            request.setAttribute("err", new NullValueException("Confirm Password is empty."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        if (firstname.trim().equals("")) {
            request.setAttribute("err", new NullValueException("First Name is empty."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        if (lastname.trim().equals("")) {
            request.setAttribute("err", new NullValueException("Last Name is empty."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        //Check if password and confirm password is same
        if (!password.trim().equals(confirm_pwd.trim())) {
            request.setAttribute("err", new AuthenticationException("Mismatch in Confirm Password field."));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        //Send data to model
        User user = new User(firstname, lastname, username.trim(), password.trim(), "guest");
        int success = -1;
        try {
            success = model.signup(user);
        } catch (DatabaseException e) {
            request.setAttribute("err", e);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

        if (success == 1) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("err", new AuthenticationException(success));
            request.getRequestDispatcher("signup.jsp").forward(request, response);
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
