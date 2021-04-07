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
import java.util.ArrayList;
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
import nguyenla.tblcar.CarDAO;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class AddToCarttServlet extends HttpServlet {

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
    private String url;
    List<CarDTO> list = new ArrayList<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = LOADPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
           
            CarDAO carDAO = new CarDAO();
            String carID = request.getParameter("txtCarID").trim();
            String carName = request.getParameter("txtCarName").trim();

            //Load cart             
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            CarDTO cartToAdd;
            CarDTO getCar = carDAO.getCarByID(carID);
            int quantitySave = getCar.getSaveQuantity();

            if (cart != null) {
//                CarDTO car = cart.getCart().get(carID);

                cartToAdd = new CarDTO(carID, getCar.getCarName(), getCar.getCategory(), getCar.getPrice(), 1, getCar.getImage());
                list.add(cartToAdd);
            }

            if (cart == null) {
                cart = new CartDTO();
                cartToAdd = new CarDTO(carID, getCar.getCarName(), getCar.getCategory(), getCar.getPrice(), 1, getCar.getImage());
//                cart.addCar(cartToAdd);
                    list.add(cartToAdd);
                    System.out.println("list: "+list.size());
            }
           

            //Total Price
//            int total = 0;
//            for (CarDTO c : cart.getCart().values()) {
//                total += c.getPrice() * c.getQuantity() + c.getDate() * 10;
//            }

            session.setAttribute("listCar",list);
            session.setAttribute("intSave", quantitySave);
            session.setAttribute("CART", cart);
//            session.setAttribute("totalCart", total);
            request.setAttribute("addCart", "Add " + carName + "to cart success");

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
            Logger.getLogger(AddToCarttServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AddToCarttServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddToCarttServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddToCarttServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AddToCarttServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddToCarttServlet.class.getName()).log(Level.SEVERE, null, ex);
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
