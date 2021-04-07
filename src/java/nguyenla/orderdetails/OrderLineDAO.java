/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.orderdetails;

import java.sql.Connection;
import java.sql.Date;
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
public class OrderLineDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean addOrderLine(int orderID, String carID, String carName, String rentalDate, String returnDate, String status, int quantity) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Insert into orderDetailsTBL values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderID);
                ps.setString(2, carID);
                ps.setString(3, carName);
                ps.setString(4, rentalDate);
                ps.setString(5, returnDate);
                ps.setString(6, status);
                ps.setInt(7, quantity);
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

    public List<OrderLineDTO> getDateCar(String carID) throws SQLException, NamingException {
        try {
            List<OrderLineDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select carName,rentalDate,returnDate,quantity,status from orderDetailsTBL where carID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, carID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String carname = rs.getString(1);
                    String rental = rs.getString(2);
                    String returnDate = rs.getString(3);
                    int quantity = rs.getInt(4);
                    String status = rs.getString(5);
                    OrderLineDTO ord = new OrderLineDTO(carname, rental, returnDate, status, quantity);
                    list.add(ord);
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

    public List<OrderLineDTO> getCarByOrder(int orderID) throws SQLException, NamingException {
        try {
            List<OrderLineDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select orderID,carName,rentalDate,returnDate,quantity from orderDetailsTBL where orderID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int orderID2 = rs.getInt(1);
                    String carName = rs.getString(2);
                    String rentalDate = rs.getString(3);
                    String returnDate = rs.getString(4);
                    int quantity = rs.getInt(5);
                    OrderLineDTO ord = new OrderLineDTO(carName, rentalDate, returnDate, quantity, orderID2);
                    list.add(ord);
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

    public List<OrderLineDTO> searchByName(String carName) throws SQLException, NamingException {
        try {
            List<OrderLineDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select orderID,carName,rentalDate,returnDate,quantity from orderDetailsTBL where carName like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + carName + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt(1);
                    String carName2 = rs.getString(2);
                    String rentalDate = rs.getString(3);
                    String returnDate = rs.getString(4);
                    int quantity = rs.getInt(5);
                    OrderLineDTO ord = new OrderLineDTO(carName2, rentalDate, returnDate, quantity, orderID);
                    list.add(ord);
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

    public List<OrderLineDTO> searchByDate(String rental, String returnDate) throws SQLException, NamingException {
        try {
            List<OrderLineDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select orderID,carName,rentalDate,returnDate,quantity from orderDetailsTBL where rentalDate = ? and returnDate = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, rental);
                ps.setString(2, returnDate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt(1);
                    String carName = rs.getString(2);
                    String rentalDate2 = rs.getString(3);
                    String returnDate2 = rs.getString(4);
                    int quantity = rs.getInt(5);
                    OrderLineDTO ord = new OrderLineDTO(carName, rentalDate2, returnDate2, quantity, orderID);
                    list.add(ord);
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

    public boolean updateStatus(String status, String rentalDate, String returnDate) throws SQLException, NamingException {

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update orderDetailsTBL set status = ?  where rentalDate = ? and returnDate = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, status);
                ps.setString(2, rentalDate);
                ps.setString(3, returnDate);
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

    public List<OrderLineDTO> checkRental(String currentDate) throws SQLException, NamingException {
        try {
            List<OrderLineDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "select orderID,carName from orderDetailsTBL where ? between rentalDate and returnDate ";
                ps = con.prepareStatement(sql);
                ps.setString(1, currentDate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt(1);
                    String carName = rs.getString(2);
                    OrderLineDTO or = new OrderLineDTO(carName, orderId);
                    list.add(or);

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

    public boolean updateStatusByID(String id) throws SQLException, NamingException {

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update orderDetailsTBL set status = 'inactive' where orderID = ?";
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

    public int getAllQuantityMinusByCarID(String carID, Date rentalDate, Date returnDate) throws NamingException, SQLException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "SELECT SUM(quantity) as 'minus' FROM orderDetailsTBL "
                        + "WHERE status = ? AND carID = ? AND "
                        + "((? >= rentalDate AND ? < returnDate ) OR (? >  rentalDate AND ? <=  returnDate))";

                ps = con.prepareStatement(sql);
                ps.setString(1, "active");
                ps.setString(2, carID);
                ps.setDate(3, rentalDate);
                ps.setDate(4, rentalDate);
                ps.setDate(5, returnDate);
                ps.setDate(6, returnDate);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    System.out.println(rs.getInt(1));
                    return rs.getInt("minus");
                    
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

}
