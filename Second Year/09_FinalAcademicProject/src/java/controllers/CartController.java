/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.dao.CartDAO;
import models.entity.CartItem;
import models.entity.User;
import models.exception.AuthenticationException;
import models.exception.DatabaseException;

/**
 *
 * @author Richard Maru
 */
public class CartController extends HttpServlet {

    CartDAO cart = new CartDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.invalidate();
            request.setAttribute("err", new AuthenticationException("You need to login before you can access the cart."));
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("addtocart") != null) {
            try {
                System.out.println("|" + request.getParameter("price") + "|");
                int success = cart.addToCart(user.getId(), Integer.parseInt(request.getParameter("addtocart")), Integer.parseInt(request.getParameter("qty")), Float.parseFloat(request.getParameter("price").trim()));
                if (success == 1) {
                    response.sendRedirect("product?name=" + request.getParameter("slug"));
                } else {
                    request.setAttribute("err", new DatabaseException("An error has occured in adding item to cart"));
                    request.getRequestDispatcher("product").forward(request, response);
                }
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }
            return;
        }
        if (request.getParameter("deleteItem") != null) {
            try {
                int success = cart.deleteItem(Integer.parseInt(request.getParameter("deleteItem")));
                if (success == 1) {
                    response.sendRedirect("user/cart.jsp");
                } else {
                    request.setAttribute("err", new DatabaseException("An error has occured in deleting item to cart"));
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }
            return;
        }
        if (request.getParameter("editItemQty") != null) {
            int qty = Integer.parseInt(request.getParameter("editItemQty"));
            int cid = Integer.parseInt(request.getParameter("cid"));
            Float price = Float.parseFloat(request.getParameter("price")) * qty;
            try {
                int success = cart.editItemQty(cid, qty, price);
                if (success == 1) {
                    response.sendRedirect("user/cart.jsp");
                } else {
                    request.setAttribute("err", new DatabaseException("An error has occured in editing item in cart"));
                    request.getRequestDispatcher("cart").forward(request, response);
                }
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
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
