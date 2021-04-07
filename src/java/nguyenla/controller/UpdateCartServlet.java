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
import javax.servlet.http.HttpSession;
import nguyenla.cart.CartDTO;
import nguyenla.orderdetails.OrderLineDAO;
import nguyenla.orderdetails.OrderLineDTO;
import nguyenla.tblcar.CarDAO;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class UpdateCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String RENTALPAGE = "RentalCart.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = RENTALPAGE;
            /* TODO output your page here. You may use following sample code. */
            CarDAO carDAO = new CarDAO();
            HttpSession session = request.getSession();

            String quantityString = request.getParameter("quantityCart");
            List<OrderLineDTO> listOrlDTO = new ArrayList<>();
            List<OrderLineDTO> listSave = new ArrayList<>();
            List<CarDTO> listQuantity = (List<CarDTO>) session.getAttribute("ListQuantity");

            if (listQuantity == null) {
                listQuantity = new ArrayList<>();
            }

            OrderLineDTO orlDTO = new OrderLineDTO();
            OrderLineDAO orlDAO = new OrderLineDAO();
            int quantityNumber = Integer.parseInt(quantityString);
            String carID = request.getParameter("hiddenID").trim();
            CartDTO cartUpdate = (CartDTO) session.getAttribute("CART");
            CarDTO car = cartUpdate.getCart().get(carID);
            String rentalDateString = car.getRentalDate();
            String returnDateString = car.getReturnDate();

            CarDTO updateCart = null;
            CarDTO carDate = new CarDTO();
            int newQuantity = 0;
            int quantityCar = carDAO.getQuantityCarByID(carID);

            int quantityCheck = (int) session.getAttribute("intSave");

            if (rentalDateString == null && returnDateString == null) {
                request.setAttribute("outStock", "Please choose date");
            } else {
                listOrlDTO = orlDAO.getDateCar(carID);
                if (listOrlDTO.size() == 0) {
                    if (quantityCar >= quantityNumber) {
                        updateCart = new CarDTO(car.getCarID(), car.getCarName(), car.getCategory(), car.getPrice(), quantityNumber, car.getImage(), rentalDateString, returnDateString, 1);
                        cartUpdate.updateCartCar(carID, updateCart);
                    } else {
                        request.setAttribute("outStock", "This car out stock");
                    }
                } else {
                    for (int i = 0; i < listOrlDTO.size(); i++) {
                        OrderLineDTO getCar = listOrlDTO.get(i);
                        String rentalSql = getCar.getRentalDate();
                        String returnSql = getCar.getReturnDate();
                        String statusCar = getCar.getStatus().trim();
                        int rentalQuantity = getCar.getQuantity();

//                        
                        Date rentalDateInt;
                        Date returnDateInt;
                        rentalDateInt = new SimpleDateFormat("yyyy-MM-dd").parse(rentalDateString);
                        returnDateInt = new SimpleDateFormat("yyyy-MM-dd").parse(returnDateString);

                        Date rentalSqlInt;
                        Date returnSqlInt;

                        rentalSqlInt = new SimpleDateFormat("yyyy-MM-dd").parse(rentalSql);
                        returnSqlInt = new SimpleDateFormat("yyyy-MM-dd").parse(returnSql);

                        int a = (int) (rentalDateInt.getTime() - returnSqlInt.getTime());
                        int date = a / (24 * 60 * 60 * 1000);

                        if (date < 0) {
//                                quantityCar = carDAO.getQuantityCarByID(carID);
                            if (quantityCar >= quantityNumber) {
                                updateCart = new CarDTO(car.getCarID(), car.getCarName(), car.getCategory(), car.getPrice(), quantityNumber, car.getImage(), rentalDateString, returnDateString, 1);
                                cartUpdate.updateCartCar(carID, updateCart);
                            } else {
                                request.setAttribute("outStock", "Max quantity is " + quantityCar);
                            }
                        } else if (date >= 0) {
                            OrderLineDTO orlSave = new OrderLineDTO(rentalSql, returnSql);
                            listSave.add(orlSave);

                            newQuantity = quantityCar + rentalQuantity; //7
                            quantityCar = newQuantity;

//                            carDAO.updateQuantity(newQuantity, carID);
                        }

                    }

                    if (newQuantity == quantityCheck) {
                        if (newQuantity >= quantityNumber) {
                            updateCart = new CarDTO(car.getCarID(), car.getCarName(), car.getCategory(), car.getPrice(), quantityNumber, car.getImage(), rentalDateString, returnDateString, 1);
                            cartUpdate.updateCartCar(carID, updateCart);
                        } else {
                            request.setAttribute("outStock", "Max quantity is " + newQuantity);
                        }
                    }
                    CarDTO carQuantity = new CarDTO(carID, quantityCar);
                    listQuantity.add(carQuantity);
                }
            }


            int total = 0;
            for (CarDTO c : cartUpdate.getCart().values()) {
                total += c.getPrice() * c.getQuantity() + c.getDate() * 10;
            }

            session.setAttribute("ListQuantity", listQuantity);
            session.setAttribute("newQuantity", newQuantity);
            session.setAttribute("listSave", listSave);
            session.setAttribute("totalCart", total);
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
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
