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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import nguyenla.orderdetails.OrderLineDAO;
import nguyenla.orderdetails.OrderLineDTO;
import nguyenla.orderrental.OrderDAO;
import nguyenla.rentalhistory.RentalHistoryDAO;
import nguyenla.tblcar.CarDAO;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class CheckOutCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOADPAGE = "LoadHomeServlet";
    private final String RENTALPAGE = "RentalCart.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            url = LOADPAGE;
            /* TODO output your page here. You may use following sample code. */
            OrderDAO orderDAO = new OrderDAO();
            OrderLineDAO orderLineDAO = new OrderLineDAO();
            CarDAO carDAO = new CarDAO();
            RentalHistoryDAO rentalHistory = new RentalHistoryDAO();
            HttpSession session = request.getSession();
            List<OrderLineDTO> listSave = new ArrayList<>();

            CartDTO cart = (CartDTO) session.getAttribute("CART");
            String userID = (String) session.getAttribute("nameLogin");
            listSave = (List<OrderLineDTO>) session.getAttribute("listSave");

            List<CarDTO> listQuantity = (List<CarDTO>) session.getAttribute("ListQuantity");

            if (listQuantity != null) {
                for (int i = 0; i < listQuantity.size(); i++) {
                    String carID = listQuantity.get(i).getCarID();
                    int maxQuantity = listQuantity.get(i).getSaveQuantity();
                    carDAO.updateQuantity(maxQuantity, carID);
                }
            }

            String paymentMethod = request.getParameter("cmbPayment");
            String totalPriceString = request.getParameter("totalPrice");
            Float totalFloat = Float.parseFloat(totalPriceString);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = new Date(System.currentTimeMillis());
            String currentDate = formatter.format(dateTime);

            Random dice = new Random();
            String checkOrder = dice.nextInt(1000000) + "";
            int checkOrderInt = Integer.parseInt(checkOrder);

            boolean result = true;
            for (CarDTO c : cart.getCart().values()) {
                String rentalDate = c.getRentalDate();
                String returnDate = c.getReturnDate();
                String carName = c.getCarName();
                if (rentalDate == null && returnDate == null) {
                    url = RENTALPAGE;
                    request.setAttribute("invalidDate", "Please choose date for : " + carName);
                    result = false;
                }
            }

            orderDAO.addOrder(userID, paymentMethod, currentDate, totalFloat, "active", "113", checkOrderInt);
            int orderID = orderDAO.getOrderID(checkOrderInt);

            if (result) {
                for (CarDTO c : cart.getCart().values()) {
                    String carID = c.getCarID();
                    String carName = c.getCarName();
                    String rentalDate = c.getRentalDate();
                    String returnDate = c.getReturnDate();
                    String status = "active";
                    int quantityCart = c.getQuantity();
                    orderLineDAO.addOrderLine(orderID, carID, carName, rentalDate, returnDate, status, quantityCart);
                    int quantityCar = carDAO.getQuantityCarByID(carID);
                    carDAO.reduceQuantity(quantityCar, quantityCart, carID);

                    session.removeAttribute("CART");
                    session.removeAttribute("resultQuantity");
                    request.setAttribute("confirmMess", "Rental car to success!!");
                }
                rentalHistory.addHistory(userID, currentDate, totalFloat, paymentMethod, orderID, "active");

                if (listSave != null) {
                    for (int i = 0; i < listSave.size(); i++) {
                        String rental = listSave.get(i).getRentalDate();
                        String returnDate = listSave.get(i).getReturnDate();
                        orderLineDAO.updateStatus("inactive", rental, returnDate);
                    }
                }


            }
            session.removeAttribute("ListQuantity");
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
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
