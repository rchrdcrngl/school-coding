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
import model.account.Account;
import model.account.AccountModel;
import model.exception.AuthenticationException;
import model.exception.DatabaseException;
import model.exception.NullValueException;

/**
 *
 * @author Richard Maru
 */
public class AccountController extends HttpServlet {
    AccountModel model;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String username = config.getInitParameter("dbUserName");
            String password = config.getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("databaseName"));
            model = new AccountModel(config.getInitParameter("jdbcClassName"), url.toString(), username, password);
        } catch (DatabaseException e){
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getAttribute("login")!=null){
            Account user = (Account) request.getAttribute("login");
            int auth = -1;
            try{
                auth = model.login(user);
            }catch(DatabaseException e){
                throw new ServletException(e);
            }

            String type = user.getType();
            HttpSession session = request.getSession();

            if(auth==1){
                session.setAttribute("user", user);
                response.sendRedirect("success.jsp");
            }else{
                request.setAttribute("err", new AuthenticationException(auth));
                request.getRequestDispatcher("index.jsp").forward(request, response);
                //throw new ServletException(new AuthenticationException(auth));
            }
        }else if (request.getAttribute("signup")!=null){
            Account user = (Account) request.getAttribute("signup");
            int auth = -1;
            try{
                auth = model.signup(user);
            }catch(DatabaseException e){
                request.setAttribute("err", e);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }

            String type = user.getType();

            if(auth==1){
                response.sendRedirect("index.jsp");
            }else{
                request.setAttribute("err", new AuthenticationException(auth));
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }else{
            throw new ServletException(new NullValueException("Invalid Request"));
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
