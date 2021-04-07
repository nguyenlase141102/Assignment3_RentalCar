/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyenla.tbluseraccount.UserDAO;
import nguyenla.tbluseraccount.UserDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class VerifyCodeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String REGISPAGE = "RegisterAccount.jsp";
    private final String CARPAGE = "LoadHomeServlet";
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = REGISPAGE;
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("authCode");
            UserDAO userDAO = new UserDAO();
            String code = request.getParameter("authCode");
            String checkLogin = (String) session.getAttribute("nameLogin");
            String txtID = request.getParameter("txtId").trim();
            String txtPassword = request.getParameter("txtPassword").trim();
            String txtPhone = request.getParameter("txtPhone").trim();
            String txtName = request.getParameter("txtName").trim();
            String txtAddress = request.getParameter("txtAddress").trim();

            if (code.equals(user.getCode())) {
                request.setAttribute("verify", "Verify Email success");
                if (checkLogin != null) {
                    url = CARPAGE;
                    userDAO.updateStatus(checkLogin);
                    session.setAttribute("status", "active");
                    request.setAttribute("emailSuccess", "Verify email success ");
                }
            } else {
                request.setAttribute("verify", "Verify Email failed ");
            }

            request.setAttribute("valueId", txtID);
            request.setAttribute("valuePassword", txtPassword);
            request.setAttribute("valuePhone", txtPhone);
            request.setAttribute("valueName", txtName);
            request.setAttribute("valueAddress", txtAddress);
            
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
            Logger.getLogger(VerifyCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(VerifyCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerifyCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(VerifyCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
