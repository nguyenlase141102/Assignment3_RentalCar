/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nguyenla.orderdetails.OrderLineDAO;
import nguyenla.orderdetails.OrderLineDTO;
import nguyenla.tblcar.CarDAO;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class SearchCarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SEARCHPAGE = "CarRental.jsp";
    private String url;
    private String type;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = SEARCHPAGE;
            /* TODO output your page here. You may use following sample code. */
            CarDAO carDAO = new CarDAO();
            OrderLineDAO ordDAO = new OrderLineDAO();
            String valueRental = request.getParameter("rentalDate");
            String valueReturn = request.getParameter("returnDate");

            Date rentalDateInt;
            Date returnDateInt;
            rentalDateInt = new SimpleDateFormat("yyyy-MM-dd").parse(valueRental);
            returnDateInt = new SimpleDateFormat("yyyy-MM-dd").parse(valueReturn);
            
            
            String valueAmount = request.getParameter("amountCar");
            String indexString = request.getParameter("index");
            List<CarDTO> listSearch = new ArrayList<>();

            int index;
            if (indexString == null) {
                index = 1;
            } else {
                index = Integer.parseInt(indexString);
            }
            int pageSize = 8;
            int countFood = 0;
            int endPage;

            String txtRadio = request.getParameter("radioValue");
            if (txtRadio.equals("category")) {
                String nameCategory = request.getParameter("categoryCar");

            } else if (txtRadio.equals("nameCar")) {
                String valueName = request.getParameter("txtSearchName");
//                endPage = countFood / pageSize;
//                if (countFood % pageSize != 0) {
//                    endPage++;
//                }
                List<OrderLineDTO> list = new ArrayList<>();
                list = carDAO.searchCar(valueName,valueRental, valueReturn,Integer.parseInt(valueAmount));
                if(list.size() != 0){
                     type = "This car out stock";
                }else{
                    listSearch = carDAO.listID(valueName);
                    
                }
            
               
            } else {
                request.setAttribute("errorRadio", "Please choose type search");
            }

            request.setAttribute("error",type);
            request.setAttribute("listSearch", listSearch);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
