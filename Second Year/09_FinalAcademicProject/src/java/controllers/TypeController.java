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
import javax.servlet.http.HttpSession;
import models.dao.ProductTypeDAO;
import models.entity.Category;
import models.entity.User;
import models.exception.AuthenticationException;
import models.exception.DatabaseException;

/**
 *
 * @author USER
 */
public class TypeController extends HttpServlet {

    ProductTypeDAO ptd = new ProductTypeDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.invalidate();
            request.setAttribute("err", new AuthenticationException("You need to login before doing orders."));
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (user.getUserType().equals("employee")) {
            session.invalidate();
            request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("add") != null) {
            String title = request.getParameter("title");
            try {
                ptd.addProductType(title);
                response.sendRedirect("admin/products.jsp");
            } catch (DatabaseException ex) {
                ex.printStackTrace();
            }
            return;
        }
        if (request.getParameter("delete") != null) {
            int id = Integer.parseInt(request.getParameter("delete"));
            try {
                ptd.deleteProductType(id);
                response.sendRedirect("admin/products.jsp");
            } catch (DatabaseException ex) {
                ex.printStackTrace();
            }
            return;
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
