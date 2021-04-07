/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblcar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyenla.dbutil.Dbutil;
import nguyenla.orderdetails.OrderLineDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class CarDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int countAllCar() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select count(*) from carTBL";
                ps = con.prepareStatement(sql);
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
        }

        return 0;
    }

    public List<CarDTO> getAllCar(int index) throws SQLException, NamingException {
        List<CarDTO> listAllCar = new ArrayList<>();
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "with x as (select ROW_NUMBER() over(order by year desc) as r, * from carTBL where quantity > 0)\n"
                        + "select carID,carName,price,category,image  from x where r between ?*8-7 and ?*8";
                ps = con.prepareStatement(sql);
                ps.setInt(1, index);
                ps.setInt(2, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    CarDTO car = new CarDTO(rs.getString(1), rs.getString(2), rs.getString(4), rs.getFloat(3), rs.getString(5));
                    listAllCar.add(car);
                }
                return listAllCar;
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

    public CarDTO getCarByID(String carID) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select carID,carName,category,price,quantity,image,quantitySave from carTBL where carID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, carID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    CarDTO car = new CarDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
                    return car;
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
        return null;
    }

    public CarDTO getCarInformation(String carID) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select carID,carName,color,year,category,price,image from carTBL where carID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, carID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    CarDTO car = new CarDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getString(7));
                    return car;
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
        return null;
    }

    public int getQuantityCarByID(String id) throws SQLException, NamingException {

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select quantity  from carTBL where carID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
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

//        public String getBienSoXe(String carId) throws SQLException, NamingException {
//
//        try {
//            con = Dbutil.openConnect();
//            if (con != null) {
//                String sql = "Select biensoxe  from carTBL where carID = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, carId);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    return rs.getInt(1);
//                }
//
//            }
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return 0;
//    }
    public List<CarDTO> listID(String carName) throws SQLException, NamingException {
        try {
            List<CarDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select carID,carName,category,price,quantitySave,image from carTBL where carName like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + carName + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    CarDTO car = new CarDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(6), rs.getInt(5));
                    list.add(car);
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
        }
        return null;
    }

    public boolean reduceQuantity(int quantity, int quantityCart, String id) throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update carTBL set quantity = ?-? where carID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setInt(2, quantityCart);
                ps.setString(3, id);
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

    public boolean updateQuantity(int newQuantity, String id) throws SQLException, NamingException {

        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Update carTBL set quantity = ?  where carID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, newQuantity);
                ps.setString(2, id);
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

    public List<OrderLineDTO> searchCar(String nameCar, String rentalDate, String returnDate, int amount) throws SQLException, NamingException {
        try {
            List<OrderLineDTO> list = new ArrayList<>();
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "		Select carID,carName,rentalDate,returnDate,status,quantity from orderDetailsTBL where carName like ? and ? between rentalDate and returnDate or ?  between rentalDate and returnDate\n"
                        + "	or rentalDate < ? and returnDate > ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,"%"+nameCar+"%");
                ps.setString(2,rentalDate);
                ps.setString(3,returnDate);
                ps.setString(4,rentalDate);
                ps.setString(5,returnDate);
                rs = ps.executeQuery();
                while(rs.next()){
                    String carID = rs.getString(1);
                    String carName = rs.getString(2);
                    String rental = rs.getString(3);
                    String return2 = rs.getString(4);
                    String status = rs.getString(5);
                    int quantityNum = rs.getInt(6);
                    OrderLineDTO or = new OrderLineDTO(carID, carName, rental, return2, status, quantityNum);
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

//
//    public int countTypeName(String nameSearch,String rentalDate, String returnDate, int amount) throws SQLException, NamingException {
//        try {
//            con = Dbutil.openConnect();
//            if (con != null) {
//                String sql = "Select count(*) from orderDetailsTBL where carName like ? and status = ? and price between ? and ? and category = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + nameSearch + "%");
//                ps.setString(2, active);
//                ps.setFloat(3, from);
//                ps.setFloat(4, to);
//                ps.setString(5, category);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return 0;
//    }
//    public int countTypeCategory(String nameCategory,String rentalDate, String returnDate, int amount) throws SQLException, NamingException {
//        try {
//            con = Dbutil.openConnect();
//            if (con != null) {
//                String sql = "Select count(*) from orderDetailsTBL where carName like ? and status = ? and price between ? and ? and category = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + nameSearch + "%");
//                ps.setString(2, active);
//                ps.setFloat(3, from);
//                ps.setFloat(4, to);
//                ps.setString(5, category);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return 0;
//    }
//    public static List<CarDTO> searchWithName(String nameSearch,String rentalDate, String returnDate, int amount) throws SQLException, NamingException {
//        List<CarDTO> list = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = Dbutil.openConnect();
//            if (con != null) {
//                String sql = "with x as (select ROW_NUMBER() over(order by createDate desc) as r, * from foodTBL where name like ? and category = ? and price between ? and ? and status = ?)\n"
//                        + "select id,name,image,category,status,quantity,price,createDate  from x where r between ?*8-7 and ?*8";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + nameSearch + "%");
//                ps.setString(2, cate);
//                ps.setFloat(3, from);
//                ps.setFloat(4, to);
//                ps.setString(5, active);
//                ps.setInt(6, index);
//                ps.setInt(7, index);
//
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String id = rs.getString(1);
//                    String name = rs.getString(2);
//                    String image = rs.getString(3);
//                    String category = rs.getString(4);
//                    String status = rs.getString(5);
//                    int quantity = rs.getInt(6);
//                    float price = rs.getFloat(7);
//                    String date = rs.getString(8);
//                    FoodDTO getFood = new FoodDTO(id, name, image, date, category, status, quantity, price);
//                    list.add(getFood);
//                }
//                return list;
//            }
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return null;
//    }
//    public static List<CarDTO> searchWithCate(String nameCategory,String rentalDate, String returnDate, int amount) throws SQLException, NamingException {
//        List<CarDTO> list = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = Dbutil.openConnect();
//            if (con != null) {
//                String sql = "with x as (select ROW_NUMBER() over(order by createDate desc) as r, * from foodTBL where name like ? and category = ? and price between ? and ? and status = ?)\n"
//                        + "select id,name,image,category,status,quantity,price,createDate  from x where r between ?*8-7 and ?*8";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + nameSearch + "%");
//                ps.setString(2, cate);
//                ps.setFloat(3, from);
//                ps.setFloat(4, to);
//                ps.setString(5, active);
//                ps.setInt(6, index);
//                ps.setInt(7, index);
//
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String id = rs.getString(1);
//                    String name = rs.getString(2);
//                    String image = rs.getString(3);
//                    String category = rs.getString(4);
//                    String status = rs.getString(5);
//                    int quantity = rs.getInt(6);
//                    float price = rs.getFloat(7);
//                    String date = rs.getString(8);
//                    FoodDTO getFood = new FoodDTO(id, name, image, date, category, status, quantity, price);
//                    list.add(getFood);
//                }
//                return list;
//            }
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return null;
//    }
}
