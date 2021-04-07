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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.orderdetails.OrderLineDAO;
import nguyenla.orderdetails.OrderLineDTO;
import nguyenla.orderrental.OrderDAO;
import nguyenla.rentalhistory.RentalHistoryDAO;
import nguyenla.rentalhistory.RentalHistoryDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class DeleteOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String HISTORYPAGE = "UserHistory.jsp";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            url = HISTORYPAGE;
            
            RentalHistoryDAO historyDAO = new RentalHistoryDAO();
            OrderDAO ordDAO = new OrderDAO();
            OrderLineDAO orlDAO = new OrderLineDAO();
            String orderID = request.getParameter("hiddenOrder").trim();

            historyDAO.updateStatus(orderID);
            ordDAO.updateStatus(orderID);
            orlDAO.updateStatusByID(orderID);
            HttpSession session = request.getSession();
            String nameLogin = (String) session.getAttribute("nameLogin");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = new Date(System.currentTimeMillis());
            String currentDate = formatter.format(dateTime);

            List<RentalHistoryDTO> listHistory = new ArrayList<>();
            listHistory = historyDAO.getHistory(nameLogin);

            List<OrderLineDTO> listDAO = new ArrayList<>();
            listDAO = orlDAO.checkRental(currentDate);

            request.setAttribute("listDetails", listDAO);
            request.setAttribute("listHistory", listHistory);

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
            Logger.getLogger(DeleteOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DeleteOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DeleteOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
