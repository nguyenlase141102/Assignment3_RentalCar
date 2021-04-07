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
import nguyenla.orderdetails.OrderLineDTO;
import nguyenla.tblcar.CarDAO;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class ConfirmDateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String FAILED = "InformationCar.jsp";
    private final String SUCCESS = "RentalCart.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = FAILED;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            CarDAO car = new CarDAO();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            List<OrderLineDTO> list = new ArrayList<>();
            String hiddenQuantity = request.getParameter("hiddenQuantity");
            String hiddenImage = request.getParameter("hiddenImage");
            int quantityNumber = Integer.parseInt(hiddenQuantity);
            String txtRentalDate = request.getParameter("rentalDate");
            String txtReturnDate = request.getParameter("returnDate");
            String type = null;
            String carId = request.getParameter("carID").trim();
            String carName = request.getParameter("carName");
            String carColor = request.getParameter("carColor");
            String carYear = request.getParameter("carYear");
            String carCategory = request.getParameter("carCategory");
            String carPrice = request.getParameter("carPrice");
            Float priceFloat = Float.parseFloat(carPrice);

            if (txtRentalDate.equals("") && txtReturnDate.equals("")) {
                request.setAttribute("invalid", "Please choose date !!");
            } else {
               Date rentalDate = new SimpleDateFormat("yyyy-MM-dd").parse(txtRentalDate);
               Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(txtReturnDate);

                int time = (int) (returnDate.getTime() - rentalDate.getTime());
                if (time < 0) {
                    request.setAttribute("invalid", "Date invalid !!");
                } else {
                    int moneyDate = time / (24 * 60 * 60 * 1000);
                    if (moneyDate > 5) {
                        request.setAttribute("invalid", "Each car only  can rent when return - rental = 5 !!");
                    } else {
                            url = SUCCESS;
                            CarDTO cartCar = new CarDTO(carId, carName, carCategory, priceFloat, quantityNumber,hiddenImage,txtRentalDate,txtReturnDate, moneyDate);
//                            cart.getCart().replace(carId, cartCar);
                                list = car.searchCar(carName, txtRentalDate, txtReturnDate,3);
                                if(list != null){
                                    type = "This car out stock";
                                }else{
                                    
                                }
                           
                    }
                }
            }

            request.setAttribute("error",type);
            request.setAttribute("inId", carId);
            request.setAttribute("inName", carName);
            request.setAttribute("inColor", carColor);
            request.setAttribute("inYear", carYear);
            request.setAttribute("inCategory", carCategory);
            request.setAttribute("inPrice", carPrice);
            request.setAttribute("hiddenQuantity", hiddenQuantity);
            request.setAttribute("rentalDate", txtRentalDate);
            request.setAttribute("returnDate", txtReturnDate);

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
        } catch (ParseException ex) {
            Logger.getLogger(ConfirmDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ConfirmDateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ConfirmDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ConfirmDateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
