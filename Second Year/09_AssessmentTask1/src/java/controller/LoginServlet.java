/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.JDBCModel;
import model.exception.AuthenticationException;
import model.exception.DatabaseException;
import model.exception.NullValueException;
import model.record.Record;
import model.record.RecordModel;
import model.user.*;

/**
 *
 * @author Richard Maru
 */
public class LoginServlet extends HttpServlet {

    AccountModel model;
    RecordModel rec;
    
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
            rec = new RecordModel(config.getInitParameter("jdbcClassName"), url.toString(), username, password);
        } catch (DatabaseException e){
            throw new ServletException(e);
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the submitted username and password from the request body
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Checking of username and password values
        if (username == null) throw new ServletException(new NullValueException("Invalid Request. Username is empty"));
        if (password == null) throw new ServletException(new NullValueException("Invalid Request. Password is empty"));
        if (username.equals("")){
            request.setAttribute("err", new NullValueException("Username is empty."));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        if (password.equals("")){
            request.setAttribute("err", new NullValueException("Password is empty."));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Account user = new Account(username.trim(), password.trim());
        
        int auth = -1;
        try{
            auth = model.authorize(user);
        }catch(DatabaseException e){
            throw new ServletException(e);
        }

        String type = user.getType();
        HttpSession session = request.getSession();
        
        if(auth==1){
            session.setAttribute("user", user);
            if(type.equals("admin")){
                response.sendRedirect("admin/home.jsp");
            }else if(type.equals("user")){
                response.sendRedirect("user/home.jsp");
            }
        }else{
            request.setAttribute("err", new AuthenticationException(auth));
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
        throw new ServletException(new NullValueException("Invalid Request."));
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
