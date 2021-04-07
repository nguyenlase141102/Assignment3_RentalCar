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
import nguyenla.tbluseraccount.SendEmail;
import nguyenla.tbluseraccount.UserDAO;
import nguyenla.tbluseraccount.UserDTO;
import nguyenla.validation.CheckValid;

/**
 *
 * @author ANH NGUYEN
 */
public class RegistrationnServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOGINPAGE = "LoginAccount.jsp";
    private final String REGISPAGE = "RegisterAccount.jsp";
    private final String VERIFYCODE = "VerifyCode.jsp";
    private String url;
    private String typeError;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            url = REGISPAGE;
            /* TODO output your page here. You may use following sample code. */

            UserDAO userDAO = new UserDAO();;

            String txtID = request.getParameter("userID").trim();
            String txtPassword = request.getParameter("userPassword").trim();
            String txtPhone = request.getParameter("userPhone").trim();
            String txtName = request.getParameter("userName").trim();
            String txtAddress = request.getParameter("userAddress").trim();
            String checkVerify = request.getParameter("checkVerify");
            String txtEmail = request.getParameter("userEmail");

            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date dateTime = new Date(System.currentTimeMillis());
            String currentDate = formatter.format(dateTime);
            String status;

            SendEmail sm = new SendEmail();
            HttpSession session = request.getSession();
            String code = sm.getRandom();

            UserDTO userEmail = new UserDTO(txtID, txtEmail, code);
            boolean checkEmail = sm.sendEmail(userEmail);

            if (checkEmail) {
                url = VERIFYCODE;
                session.setAttribute("authCode", userEmail);
            } else {
                if (checkVerify.equals("success")) {
                    status = "active";
                } else {
                    status = "new";
                }

                if (txtID.isEmpty()) {
                    typeError = "ID not empty !!";
                } else if (txtPassword.isEmpty()) {
                    typeError = "Password not empty !!";
                } else if (txtPhone.isEmpty()) {
                    typeError = "Phone not empty !!";
                } else if (txtName.isEmpty()) {
                    typeError = "Name not empty !!";
                } else if (txtAddress.isEmpty()) {
                    typeError = "Address not empty !!";
                } else if (!CheckValid.checkCharacters(txtID)) {
                    typeError = "ID doesn't contain special characters !!";
                } else if (!CheckValid.checkCharacters(txtPassword)) {
                    typeError = "Password doesn't contain special characters !!";
                } else if (!CheckValid.isNumber(txtPhone)) {
                    typeError = "Phone must be number !!";
                } else if (!CheckValid.checkCharacters(txtName)) {
                    typeError = "Name doesn't contain special characters !!";
                } else if (!CheckValid.checkCharacters(txtAddress)) {
                    typeError = "Address doesn't contain special characters !!";
                } else if (!userDAO.checkID(txtID).equals("")) {
                    typeError = "This Account has already !! ";
                } else {
                    url = LOGINPAGE;
                    UserDTO userDTO = new UserDTO(txtID, txtPassword, txtPhone, txtName, txtAddress, currentDate, status);
                    if (userDAO.registration(userDTO)) {
                        request.setAttribute("success", "Create account success");
                    }

                }
            }

            request.setAttribute("errorRegistration", typeError);
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
            Logger.getLogger(RegistrationnServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegistrationnServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrationnServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegistrationnServlet.class.getName()).log(Level.SEVERE, null, ex);
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
