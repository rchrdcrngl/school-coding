/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.dao.UsersDAO;
import models.entity.User;
import models.exception.DatabaseException;
import models.exception.NullValueException;
import models.util.Report;

public class UserController extends HttpServlet {
    UsersDAO ud = new UsersDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(request.getParameter("add")!=null){
            try {
                User u = new User();
                u.setFirstName(request.getParameter("firstname"));
                u.setLastName(request.getParameter("lastname"));
                u.setBirthday(new Date(Integer.parseInt(request.getParameter("byear"))-1900,Integer.parseInt(request.getParameter("bmonth"))-1,Integer.parseInt(request.getParameter("bday"))));
                u.setMobile(request.getParameter("mobile"));
                u.setEmail(request.getParameter("email"));
                u.setAddress(request.getParameter("address"));
                u.setCity(request.getParameter("city"));
                u.setCountry(request.getParameter("country"));
                u.setZip_code(request.getParameter("zipcode"));
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("password"));
                System.out.println(request.getParameter("type"));
                u.setUserType(request.getParameter("type"));
                UsersDAO ud = new UsersDAO();
                ud.addUser(u);
                response.sendRedirect("admin/users.jsp");
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }  
            return;
        }
        if(request.getParameter("edit")!=null){
            try {
                User u = new User();
                u.setId(Integer.parseInt(request.getParameter("edit")));
                u.setFirstName(request.getParameter("firstname"));
                u.setLastName(request.getParameter("lastname"));
                u.setBirthday(new Date(Integer.parseInt(request.getParameter("byear"))-1900,Integer.parseInt(request.getParameter("bmonth"))-1,Integer.parseInt(request.getParameter("bday"))));
                u.setMobile(request.getParameter("mobile"));
                u.setEmail(request.getParameter("email"));
                u.setAddress(request.getParameter("address"));
                u.setCity(request.getParameter("city"));
                u.setCountry(request.getParameter("country"));
                u.setZip_code(request.getParameter("zipcode"));
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("password"));
                System.out.println(request.getParameter("type"));
                u.setUserType(request.getParameter("type"));
                UsersDAO ud = new UsersDAO();
                ud.editUser(u);
                response.sendRedirect("admin/users.jsp");
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }  
            return;
        }
        if (request.getParameter("report") != null && request.getParameter("report").equals("employee")) {
            response.setContentType("application/pdf");
            try {
                Report r = new Report(response.getOutputStream(), user.getFirstName()+" "+user.getLastName());
                r.generateEmployeeList(ud.getAdminEmployeeListData());
                r.getDocument().close();
                response.sendRedirect("admin/users.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("employee/users.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            } finally {
                response.getOutputStream().close();
            }
            return;
        }
        if (request.getParameter("report") != null && request.getParameter("report").equals("guest")) {
            response.setContentType("application/pdf");
            try {
                Report r = new Report(response.getOutputStream(), user.getFirstName()+" "+user.getLastName());
                r.generateGuestList(ud.getAdminUserReportData());
                r.getDocument().close();
                response.sendRedirect("admin/users.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("employee/users.jsp").forward(request, response);
            } catch (Exception e) {
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
