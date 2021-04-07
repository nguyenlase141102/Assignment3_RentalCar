/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.cart.CartDTO;
import nguyenla.tblcar.CarDAO;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class InforCarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String INFORPAGE = "InformationCar.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = INFORPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            CarDAO carDAO = new CarDAO();
            String carId = request.getParameter("hiddenID").trim();
            String quantity = request.getParameter("hiddenQuantity");
            String checkClick = request.getParameter("checkChoose");
            CarDTO getCar = carDAO.getCarInformation(carId);

        
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = new Date(System.currentTimeMillis());
            String currentDate = formatter.format(dateTime);

            
            String carID = getCar.getCarID();
            String carName = getCar.getCarName();
            String color = getCar.getColor();
            String year = getCar.getYear();
            String category = getCar.getCategory();
            Float price = getCar.getPrice();
            String image = getCar.getImage();

            CarDTO car = cart.getCart().get(carId);
            String rentalDate = null;
            String returnDate = null;
            
            if (checkClick.equals("success")) {
                rentalDate = car.getRentalDate();
                returnDate = car.getReturnDate();
            }

            request.setAttribute("inId", carID);
            request.setAttribute("inName", carName);
            request.setAttribute("inColor", color);
            request.setAttribute("inYear", year);
            request.setAttribute("inCategory", category);
            request.setAttribute("inPrice", price);
            request.setAttribute("inImage", image);
            request.setAttribute("hiddenQuantity", quantity);
            request.setAttribute("inImage", image);
            session.setAttribute("rentalDate", rentalDate);
            session.setAttribute("returnDate", returnDate);
            session.setAttribute("current",currentDate);

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
            Logger.getLogger(InforCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(InforCarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InforCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(InforCarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
