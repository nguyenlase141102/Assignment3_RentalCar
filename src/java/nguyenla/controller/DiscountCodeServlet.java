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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.discountcode.DiscountCodeDAO;
import nguyenla.discountcode.DiscountCodeDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class DiscountCodeServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            DiscountCodeDAO discountDAO = new DiscountCodeDAO();
            DiscountCodeDTO discountDTO = new DiscountCodeDTO();
            discountDTO = discountDAO.getCarInformation();
            String txtCode = request.getParameter("txtDiscount").trim();
            String totalPriceString = request.getParameter("totalPrice");
            int totalPriceInt = Integer.parseInt(totalPriceString);
            int codeInt = Integer.parseInt(txtCode);

            if (codeInt == discountDTO.getCode()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateTime = new Date(System.currentTimeMillis());
                String currentDate = formatter.format(dateTime);
                String endDay = discountDTO.getExpiredDay();
                Date start, end;
                
                start = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
                end = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(endDay);

                int a = (int) (start.getTime() - end.getTime());
                int result = a / (24 * 60 * 60 * 1000);

                if (result < 0) {
                    int totalDiscount = totalPriceInt - 70;
                    session.setAttribute("totalCart", totalDiscount);
                }
                session.setAttribute("discount","Check Code Success");
            }else{
                request.setAttribute("discount","Code not valid");
            }

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
            Logger.getLogger(DiscountCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DiscountCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DiscountCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DiscountCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DiscountCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DiscountCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
