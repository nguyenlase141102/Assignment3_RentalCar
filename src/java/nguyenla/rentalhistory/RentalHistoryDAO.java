/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.rentalhistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;

/**
 *
 * @author ANH NGUYEN
 */
public class RentalHistoryDAO {
    
    
        public  boolean addHistory(String userID,String dateOrder,float price,String payment,int orderID,String status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into rentalHistoryTBL values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, dateOrder);
                ps.setFloat(3, price);
                ps.setString(4,payment);
                ps.setInt(5, orderID);
                ps.setString(6,status);
                int r = ps.executeUpdate();
                if (r > 0) {
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
//
    public  List<RentalHistoryDTO> getHistory(String userID) throws SQLException, NamingException {
        List<RentalHistoryDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select userID,dateOrder,totalPrice,payment,orderID,status from rentalHistoryTBL where userID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String date = rs.getString(2);
                    Float price = rs.getFloat(3);
                    String payment = rs.getString(4);
                    int orderID = rs.getInt(5);
                    String status = rs.getString(6).trim();
                    RentalHistoryDTO h = new RentalHistoryDTO(id,date,price,payment,orderID,status);
                    list.add(h);
                }
                return list;
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
        return null;
    }
        public boolean updateStatus(String id) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update rentalHistoryTBL set status = 'inactive' where orderID = ?";
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
