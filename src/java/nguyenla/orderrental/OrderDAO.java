/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.orderrental;

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
public class OrderDAO {

    public boolean addOrder(String userId, String payment, String date, Float totalPrice, String status, String codeDiscount, int checkOrder) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into orderTBL values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userId);
                ps.setString(2, date);
                ps.setString(3, payment);
                ps.setFloat(4, totalPrice);
                ps.setString(5, status);
                ps.setString(6, codeDiscount);
                ps.setInt(7, checkOrder);
                int row = ps.executeUpdate();
                if (row > 0) {
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

    public int getOrderID(int checkOrder) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select orderID from orderTBL where checkOrder = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, checkOrder);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public boolean updateStatus(String id) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update orderTBL set status = 'inactive' where orderID = ?";
                ps = con.prepareStatement(sql);;
                ps.setString(1, id);
                int row = ps.executeUpdate();
                while (row > 0) {
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
}
