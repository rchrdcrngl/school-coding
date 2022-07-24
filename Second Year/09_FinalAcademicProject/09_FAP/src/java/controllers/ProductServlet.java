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
import models.dao.CartDAO;
import models.dao.ProductDAO;
import models.entity.Category;
import models.entity.Product;
import models.entity.ProductMeta;
import models.entity.ProductType;
import models.entity.User;
import models.exception.AuthenticationException;
import models.exception.DatabaseException;
import models.exception.NullValueException;
import models.util.Report;

/**
 *
 * @author Richard Maru
 */
public class ProductServlet extends HttpServlet {

    ProductDAO p = new ProductDAO();
    CartDAO cart = new CartDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (request.getParameter("name") != null) {
            try {
                Product product = p.userGetProduct(request.getParameter("name").trim());
                request.setAttribute("product", product);
                request.getRequestDispatcher("product.jsp").forward(request, response);
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }catch(NullPointerException e){
                response.sendRedirect(request.getContextPath());
            }
            return;
        }
        if (request.getParameter("id") != null) {
            if (user == null) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            try {
                Product product = p.adminGetProduct(Integer.parseInt(request.getParameter("id").trim()));
                request.setAttribute("product", product);
                request.getRequestDispatcher("admin/product.jsp").forward(request, response);
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            } catch(NullPointerException e){
                response.sendRedirect(request.getContextPath());
            }
            return;
        }
        if (request.getParameter("updateStock") != null) {
            if (user == null) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            try {
                System.out.println(Integer.parseInt(request.getParameter("updateStock")) + "-" + Integer.parseInt(request.getParameter("stock")));
                p.updateProductStock(Integer.parseInt(request.getParameter("updateStock")), Integer.parseInt(request.getParameter("stock")));
                response.sendRedirect("employee/products.jsp");
                return;
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }catch(NullPointerException e){
                response.sendRedirect(request.getContextPath());
            }
        }
        if (request.getParameter("add") != null) {
            if (user == null || !user.getUserType().equals("admin")) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            try {
                Product prod = new Product();
                String title = request.getParameter("title");
                prod.setTitle(request.getParameter("title"));
                String slug = request.getParameter("slug");
                if (slug.trim().equals("")) {
                    slug = title.substring(0, slug.length() < 20 ? title.length() : 20).toLowerCase().replace(" ", "-");
                }
                prod.setSlug(slug);
                prod.setDescription(request.getParameter("description"));
                System.out.println(request.getParameter("type"));
                prod.setType(new ProductType(Integer.parseInt(request.getParameter("type"))));
                prod.setPrice(Float.parseFloat(request.getParameter("price")));
                prod.setStock(Integer.parseInt(request.getParameter("stock")));
                prod.setProductImg(request.getParameter("imgurl"));
                prod.setSku(request.getParameter("sku"));
                prod.setManufacturer(request.getParameter("manufacturer"));
                List<Category> ctgList = new ArrayList<Category>();
                if (request.getParameter("category") != null) {
                    for (String c : (String[]) request.getParameterValues("category")) {
                        ctgList.add(new Category(Integer.parseInt(c)));
                    }
                }
                prod.setCategories(ctgList);
                List<ProductMeta> metaList = new ArrayList<ProductMeta>();
                String[] keys = request.getParameterValues("key");
                String[] values = request.getParameterValues("value");
                for (int i = 0; i < keys.length; i++) {
                    metaList.add(new ProductMeta(keys[i], values[i]));
                }
                prod.setMeta(metaList);
                p.addProduct(prod);
                response.sendRedirect("admin/products.jsp");
                return;
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }
        }
        if (request.getParameter("edit") != null) {
            if (user == null || !user.getUserType().equals("admin")) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            try {
                Product prod = new Product();
                prod.setId(Integer.parseInt(request.getParameter("edit")));
                String title = request.getParameter("title");
                prod.setTitle(request.getParameter("title"));
                String slug = request.getParameter("slug");
                if (slug.trim().equals("")) {
                    slug = title.substring(0, slug.length() < 20 ? title.length() : 20).toLowerCase().replace(" ", "-");
                }
                prod.setSlug(slug);
                prod.setDescription(request.getParameter("description"));
                System.out.println(request.getParameter("type"));
                prod.setType(new ProductType(Integer.parseInt(request.getParameter("type"))));
                prod.setPrice(Float.parseFloat(request.getParameter("price")));
                prod.setStock(Integer.parseInt(request.getParameter("stock")));
                prod.setProductImg(request.getParameter("imgurl"));
                prod.setSku(request.getParameter("sku"));
                prod.setManufacturer(request.getParameter("manufacturer"));
                List<Category> ctgList = new ArrayList<Category>();
                if (request.getParameter("category") != null) {
                    for (String c : (String[]) request.getParameterValues("category")) {
                        ctgList.add(new Category(Integer.parseInt(c)));
                    }
                }
                prod.setCategories(ctgList);
                List<ProductMeta> metaList = new ArrayList<ProductMeta>();
                String[] keys = request.getParameterValues("key");
                String[] values = request.getParameterValues("value");
                if (keys == null) {
                    keys = new String[]{""};
                    values = new String[]{""};
                }
                prod.setActive(request.getParameter("active") != null);
                for (int i = 0; i < keys.length; i++) {
                    metaList.add(new ProductMeta(keys[i], values[i]));
                }
                prod.setMeta(metaList);
                p.editProduct(prod);
                response.sendRedirect("admin/products.jsp");
                return;
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }
        }
        if (request.getParameter("delete") != null) {
            if (user == null || !user.getUserType().equals("admin")) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            try {
                p.deleteProduct(Integer.parseInt(request.getParameter("delete")));
                response.sendRedirect("admin/products.jsp");
                return;
            } catch (DatabaseException ex) {
                throw new ServletException(ex);
            }
        }
        if (request.getParameter("report") != null && request.getParameter("report").equals("sales")) {
            if (user == null || !user.getUserType().equals("admin")) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            response.setContentType("application/pdf");
            try {
                Report r = new Report(response.getOutputStream(), user.getFirstName() + " " + user.getLastName());
                r.generateSalesReport(p.getSalesReportData());
                r.getDocument().close();
                response.sendRedirect("admin/orders.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("admin/orders.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            } finally {
                response.getOutputStream().close();
            }
            return;
        }
        if (request.getParameter("report") != null && request.getParameter("report").equals("products")) {
            if (user == null || !user.getUserType().equals("admin")) {
                session.invalidate();
                request.setAttribute("err", new AuthenticationException("You need to have ADMIN credentials"));
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            response.setContentType("application/pdf");
            try {
                Report r = new Report(response.getOutputStream(), user.getFirstName() + " " + user.getLastName());
                r.generateProductList(p.getProductListReportData());
                r.getDocument().close();
                response.sendRedirect("admin/products.jsp");
            } catch (DatabaseException ex) {
                request.setAttribute("err", ex);
                request.getRequestDispatcher("admin/products.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException(new NullValueException("Invalid Request"));
            } finally {
                response.getOutputStream().close();
            }
            return;
        }
        response.sendRedirect("home");
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
