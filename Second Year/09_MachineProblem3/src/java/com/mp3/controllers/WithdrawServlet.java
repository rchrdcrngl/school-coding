
package com.mp3.controllers;

import com.mp3.models.Account;
import com.mp3.models.AuthenticationException;
import com.mp3.models.NullValueException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WithdrawServlet extends HttpServlet {
    
    String amount;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("user")!=null){ //Session still valid
            if (amount==null) throw new ServletException(new NullValueException("Invalid Request"));
                        
            double amt = Double.parseDouble(request.getParameter("amount"));
            if (amt<=0) throw new ServletException(new NullValueException("Invalid Value"));
            
            Account account = (Account) session.getAttribute("account");
            request.setAttribute("balance-prev", account.getBalance()); //Stores in request the previous balance
            int withdraw = account.withdraw(amt); //Withdraws the requested amount from account
            request.setAttribute("balance-cur", account.getBalance());  //Stores in request the current balance
            request.setAttribute("withdraw", amt); //Stores in the request the amount withdrawn
            request.setAttribute("success",withdraw);
            
            if (withdraw == 1){ //Successful withdraw
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);
                
            } else if (withdraw == 0){ //Successful withdraw with penalty
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                request.setAttribute("penalty", account.checkForPenalty());
                rd.forward(request, response);
                
            } else { //Insufficient Balance
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);
                
            }
            
        } else { //Invalid Session
            throw new ServletException(new AuthenticationException("You have to login again."));
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
        amount = request.getParameter("amount"); //Ensures that the amount parameter taken is from the request body (POST method)
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
