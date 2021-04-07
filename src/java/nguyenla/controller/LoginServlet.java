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
import nguyenla.tbluseraccount.VerifyRecaptcha;
import nguyenla.validation.CheckValid;

/**
 *
 * @author ANH NGUYEN
 */
public class LoginServlet extends HttpServlet {

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
    private final String LOGINPAGE = "LoginAccount.jsp";
    private String url;
    private String typeError;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            url = LOGINPAGE;
            HttpSession session = request.getSession();

            UserDAO userDAO = new UserDAO();
            String txtUser = request.getParameter("txtUser").trim();
            String txtPass = request.getParameter("password").trim();
            String status = userDAO.checkUser(txtUser, txtPass).trim();
            // get reCAPTCHA request param
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.Verify(gRecaptchaResponse);
            
            
            if (txtUser.isEmpty()) {
                typeError = "User ID not empty !!";
            } else if (txtPass.isEmpty()) {
                typeError = "Password not empty !!";
            } else if (!CheckValid.checkCharacters(txtUser)) {
                typeError = "User ID doesn't contain special characters !!";
            } else if (!CheckValid.checkCharacters(txtPass)) {
                typeError = "Passwords doesn't contain special characters !!";
            } else if (status.equals("")) {
                typeError = "This Account not available !!";
            } else if (verify == false) {
                typeError = "You missed the Recaptcha !!";
            } else {
                url = LOADPAGE;
                session.setAttribute("nameLogin", txtUser);
                session.setAttribute("status",status);
                session.setAttribute("loginAlready","loginSuccess");
                request.setAttribute("welcomeUser", "Welcome to " + txtUser);
            }

            request.setAttribute("error", typeError);
            request.setAttribute("txtUserID", txtUser);
            request.setAttribute("txtPassword", txtPass);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
