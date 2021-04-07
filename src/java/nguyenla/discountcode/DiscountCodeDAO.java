/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.discountcode;

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
public class DiscountCodeDAO {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public DiscountCodeDTO getCarInformation() throws SQLException, NamingException {
        try {
            con = Dbutil.openConnect();
            if (con != null) {
                String sql = "Select codeDiscount,startDay,expiryDay from discountTBL";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new DiscountCodeDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
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
}
