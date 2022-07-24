/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.CategoryDAO;
import models.dao.ProductDAO;
import models.dao.ProductTypeDAO;
import models.entity.Category;
import models.entity.Product;
import models.entity.ProductType;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;

/**
 *
 * @author Richard Maru
 */
public class HomeServlet extends HttpServlet {

    DatabaseHelper db;
    ProductDAO p = new ProductDAO();
    ProductTypeDAO t = new ProductTypeDAO();
    CategoryDAO c = new CategoryDAO();

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
            db = new DatabaseHelper(config.getInitParameter("jdbcClassName"), url.toString(), username, password);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //View products per category
        if (request.getParameter("category") != null) {
            try {
                List<Product> productlist = p.getAllProductsByCategory(request.getParameter("category"));
                List<ProductType> typelist = t.getProductTypes();
                List<Category> ctglist = c.getCategories();
                request.setAttribute("categories", ctglist);
                request.setAttribute("products", productlist);
                request.setAttribute("types", typelist);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            } catch (DatabaseException ex) {
                ex.printStackTrace();
            }
        }
        //View products per type
        if (request.getParameter("type") != null) {
            try {
                List<Product> productlist = p.getAllProductsByType(Integer.parseInt(request.getParameter("type")));
                List<ProductType> typelist = t.getProductTypes();
                List<Category> ctglist = c.getCategories();
                request.setAttribute("categories", ctglist);
                request.setAttribute("products", productlist);
                request.setAttribute("types", typelist);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            } catch (DatabaseException ex) {
                ex.printStackTrace();
            }
        }
        //View top products
        try {
            List<Product> productlist = p.getTopProducts();
            List<ProductType> typelist = t.getProductTypes();
            List<Category> ctglist = c.getCategories();
            request.setAttribute("categories", ctglist);
            request.setAttribute("products", productlist);
            request.setAttribute("types", typelist);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } catch (DatabaseException ex) {
            ex.printStackTrace();
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
