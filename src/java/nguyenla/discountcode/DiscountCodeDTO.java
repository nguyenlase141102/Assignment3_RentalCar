/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.discountcode;

/**
 *
 * @author ANH NGUYEN
 */
public class DiscountCodeDTO {
    private int code;
    private String startDay;
    private String expiredDay;

    public DiscountCodeDTO() {
    }

    public DiscountCodeDTO(int code, String startDay, String expiredDay) {
        this.code = code;
        this.startDay = startDay;
        this.expiredDay = expiredDay;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getExpiredDay() {
        return expiredDay;
    }

    public void setExpiredDay(String expiredDay) {
        this.expiredDay = expiredDay;
    }
    
    
    
    
    
}
