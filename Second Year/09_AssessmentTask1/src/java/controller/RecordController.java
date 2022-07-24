/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.exception.DatabaseException;
import model.exception.NullValueException;
import model.record.Record;
import model.record.RecordModel;
import utility.ValidationUtility;

/**
 *
 * @author Richard Maru
 */
public class RecordController extends HttpServlet {

    RecordModel model;
    
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
            model = new RecordModel(config.getInitParameter("jdbcClassName"), url.toString(), username, password);
        } catch (DatabaseException e){
            throw new ServletException(e);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        List<Record> res;
        int recordNO, idNO, dosageNo;
        String vaccineManufacturer, dosageLotNo, siteCity, siteCountry;
        Date dosageDate;
        if(action!=null){
            try{
                switch(action){
                    case "all":     System.out.println("ALL");
                                    //Fetch all records through model
                                    res = model.getAllRecords();
                                    request.setAttribute("res", res);
                                    break;
                    case "search":  System.out.println("SEARCH");
                                    //Check if parameters are null
                                    if(ValidationUtility.inputIsNull(new String[]{request.getParameter("type"), request.getParameter("query")}))throw new ServletException(new NullValueException("Invalid Request."));
                                    String type = request.getParameter("type");
                                    String query = request.getParameter("query");
                                    //Check the type of search the user wants, call the appropriate method accordingly
                                    switch(type.trim()){
                                            case "id": res = model.idSearch(Integer.parseInt(query.trim()));request.setAttribute("res", res);break;
                                            case "manufacturer": res = model.manufacturerSearch(query.trim().toUpperCase());request.setAttribute("res", res);break;
                                            case "name": res = model.nameSearch(query.trim());request.setAttribute("res", res);break;
                                    }
                                    //Forward results to admin/search page
                                    request.getRequestDispatcher("admin/search.jsp").forward(request, response);
                                    return;
                                    //break;
                    case "add":     System.out.println("ADD");
                                    //Check if parameters are null
                                    if(ValidationUtility.inputIsNull(new String[]{
                                        request.getParameter("ID_NO"), request.getParameter("DOSAGE_NO"), request.getParameter("SITE_COUNTRY"),
                                        request.getParameter("YEAR"), request.getParameter("MONTH"),request.getParameter("DAY"),
                                        request.getParameter("MANUFACTURER"), request.getParameter("LOT_NO"), request.getParameter("SITE_CITY")
                                    }))
                                        throw new ServletException(new NullValueException("Invalid Request."));
                                    //Parse and format data
                                    idNO = Integer.parseInt(request.getParameter("ID_NO"));
                                    dosageNo = Integer.parseInt(request.getParameter("DOSAGE_NO"));
                                    dosageDate = ValidationUtility.parseDate(request.getParameter("YEAR"), request.getParameter("MONTH"), request.getParameter("DAY"));
                                    vaccineManufacturer = request.getParameter("MANUFACTURER").toUpperCase();
                                    dosageLotNo = request.getParameter("LOT_NO");
                                    siteCity = request.getParameter("SITE_CITY").toUpperCase();
                                    siteCountry = request.getParameter("SITE_COUNTRY").toUpperCase();
                                    // 
                                    model.addRecord(idNO, dosageNo, dosageDate, vaccineManufacturer.trim(), dosageLotNo.trim(), siteCity.trim(), siteCountry.trim());
                                    break;
                                    
                    case "edit":    System.out.println("EDIT");
                                    //Check if parameters are null
                                    if(ValidationUtility.inputIsNull(new String[]{
                                        request.getParameter("RECORD_NO"), request.getParameter("ID_NO"), request.getParameter("DOSAGE_NO"),
                                        request.getParameter("YEAR"), request.getParameter("MONTH"),request.getParameter("DAY"),
                                        request.getParameter("MANUFACTURER"), request.getParameter("LOT_NO"), request.getParameter("SITE_CITY"), 
                                        request.getParameter("SITE_COUNTRY")
                                    }))
                                        throw new ServletException(new NullValueException("Invalid Request."));
                                    //Parse and format data
                                    recordNO = Integer.parseInt(request.getParameter("RECORD_NO"));
                                    idNO = Integer.parseInt(request.getParameter("ID_NO"));
                                    dosageNo = Integer.parseInt(request.getParameter("DOSAGE_NO"));
                                    dosageDate = ValidationUtility.parseDate(request.getParameter("YEAR"), request.getParameter("MONTH"), request.getParameter("DAY"));
                                    vaccineManufacturer = request.getParameter("MANUFACTURER").toUpperCase();
                                    dosageLotNo = request.getParameter("LOT_NO");
                                    siteCity = request.getParameter("SITE_CITY").toUpperCase();
                                    siteCountry = request.getParameter("SITE_COUNTRY").toUpperCase();
                                    //Edit Record through model
                                    model.editRecord(recordNO, idNO, dosageNo, dosageDate, vaccineManufacturer.trim(), dosageLotNo.trim(), siteCity.trim(), siteCountry.trim());
                                    break;
                                    
                    case "delete":  System.out.println("DELETE");
                                    //Check if paramters are null
                                    if(ValidationUtility.inputIsNull(new String[]{request.getParameter("delete")}))throw new ServletException(new NullValueException("Invalid Request."));
                                    //Format data necessary
                                    int recordNo = Integer.parseInt(request.getParameter("delete"));
                                    //Delete records through model
                                    model.deleteRecord(recordNo);
                                    break;
                    default:;
                }
            }catch(DatabaseException e){
                throw new ServletException(e);
            }catch(NumberFormatException e){
                throw new ServletException(new NullValueException("Invalid input for selected search type."));
            }
        }else{
            //Action is not provided, throw error
            throw new ServletException(new NullValueException("Invalid Request."));
        }
        response.sendRedirect("user/home.jsp");
        
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
