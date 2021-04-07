/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tbluseraccount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class UserDAO implements Serializable {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public String checkUser(String userID, String password) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select status from userAccountTBL where userID = ? and userPassword = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getString(1);
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return "";
    }

    public String checkID(String userID) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select role from userAccountTBL where userID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String result = rs.getString(1);
                    return result;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return "";
    }

    public boolean registration(UserDTO u) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into userAccountTBL values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, u.getUserID());
                ps.setString(2, u.getUserPassword());
                ps.setString(3, u.getPhone());
                ps.setString(4, u.getName());
                ps.setString(5, u.getAddress());
                ps.setString(6, u.getCreateDate());
                ps.setString(7, u.getStatus());
                ps.setString(8, "customer");
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public boolean updateStatus(String userID) throws SQLException, NamingException {
        try {

            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update userAccountTBL set status = 'active' where userID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                int row = ps.executeUpdate();
                if(row > 0){
                    return true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
}
