/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.dao.CourierDAO;
import models.dao.OrderDAO;
import models.dao.UsersDAO;
import models.entity.Courier;
import models.entity.OrderItem;
import models.entity.User;
import models.exception.AuthenticationException;
import models.exception.DatabaseException;
import models.exception.NullValueException;
import models.util.Report;

/**
 *
 * @author Richard Maru
 */
public class OrderController extends HttpServlet {

    OrderDAO order = new OrderDAO();
    CourierDAO courier = new CourierDAO();
    UsersDAO ud = new UsersDAO();

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
        if (request.getParameter("checkout") != null) {
            try {
                System.out.println("CHECKOUT");
                //Get Receiver Details
                User u = new User();
                u.setId(user.getId());
                u.setFirstName(request.getParameter("firstname"));
                u.setLastName(request.getParameter("lastname"));
                u.setMobile(request.getParameter("mobile"));
                u.setEmail(request.getParameter("email"));
                u.setAddress(request.getParameter("address"));
                u.setCity(request.getParameter("city"));
                u.setCountry(request.getParameter("country"));
                u.setZip_code(request.getParameter("zipcode"));
                //Get Courier Details
                Courier c = courier.getCourier(Integer.parseInt(request.getParameter("courier")));
                //Get subtotal
                float subtotal = Float.parseFloat(request.getParameter("subtotal"));
                //Get Order Items
                List<OrderItem> items = new ArrayList<OrderItem>();
                String[] itemsId = request.getParameterValues("itemsId");
                String[] itemsQty = request.getParameterValues("itemsQty");
                String[] itemsPrice = request.getParameterValues("itemsPrice");
                for (int i = 0; i < itemsId.length; i++) {
                    items.add(new OrderItem(Integer.parseInt(itemsId[i]), Integer.parseInt(itemsQty[i]), Float.parseFloat(itemsPrice[i])));
                }
                if (request.getParameter("save") != null) {
                    ud.changeUserAddress(u);
                    user.setEmail(u.getEmail());
                    user.setAddress(u.getAddress());
                    user.setCity(u.getCity());
                    user.setCountry(u.getCountry());
                    user.setZip_code(u.getZip_code());
                    user.setMobile(u.getMobile());
                    System.out.println("SAVE");
                }
                //Place Order
                int orderId = order.userPlaceOrder(u, subtotal, c.getId(), (subtotal + c.getPrice()), items);
                response.sendRedirect("user/orders.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("product").forward(request, response);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            }
            return;
        }
        if (request.getParameter("updateTracking") != null) {
            if(user.getUserType().equals("guest")){
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have EMPLOYEE or ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            String trackingNo = request.getParameter("trackingNo");
            try {
                System.out.println(request.getParameter("updateTracking") + "-" + trackingNo);
                order.empEditOrderTracking(Integer.parseInt(request.getParameter("updateTracking")), trackingNo);
                response.sendRedirect("employee/orders.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("employee/orders.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            }
            return;
        }
        if (request.getParameter("receiveOrder") != null) {
            try {
                order.userReceiveOrder(Integer.parseInt(request.getParameter("receiveOrder")));
                response.sendRedirect(request.getContextPath()+"/user/orders.jsp");
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            }
            return;
        }
        if (request.getParameter("cancelOrder") != null) {
            try {
                order.userCancelOrder(Integer.parseInt(request.getParameter("cancelOrder")));
                response.sendRedirect(request.getContextPath()+"/user/orders.jsp");
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            }
            return;
        }
        if (request.getParameter("invoice") != null) {
            response.setContentType("application/pdf");
            try {
                Report r = new Report(response.getOutputStream(), user.getFirstName()+" "+user.getLastName());
                r.generateOrderInvoice(order.userGetOrderInvoiceData(Integer.parseInt(request.getParameter("invoice"))));
                r.getDocument().close();
                response.sendRedirect("user/orders.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("user/orders.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            } finally {
                response.getOutputStream().close();
            }
            return;
        }
        if (request.getParameter("slip") != null) {
            if(user.getUserType().equals("guest")){
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have EMPLOYEE or ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            response.setContentType("application/pdf");
            try {
                Report r = new Report(response.getOutputStream(), user.getFirstName()+" "+user.getLastName());
                r.generateOrderSlip(order.empGetOrderSlipData(Integer.parseInt(request.getParameter("slip"))));
                r.getDocument().close();
                response.sendRedirect("employee/orders.jsp");
            } catch (DatabaseException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new ServletException(new NullValueException("Invalid Request"));
            } finally {
                response.getOutputStream().close();
            }
            return;
        }
        response.sendRedirect(request.getContextPath());
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
