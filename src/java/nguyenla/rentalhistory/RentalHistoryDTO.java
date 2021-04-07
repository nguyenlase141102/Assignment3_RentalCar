/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.rentalhistory;

/**
 *
 * @author ANH NGUYEN
 */
public class RentalHistoryDTO {
    private int historyID;
    private String userID;
    private String createOrder;
    private float  total;
    private String payment;
    private int orderID;
    private String status;
    
    
    public RentalHistoryDTO() {
    }

    public RentalHistoryDTO(int historyID, String userID, String createOrder, float total, String payment) {
        this.historyID = historyID;
        this.userID = userID;
        this.createOrder = createOrder;
        this.total = total;
        this.payment = payment;
    }

    public RentalHistoryDTO(String userID, String createOrder, float total, String payment, int orderID,String status) {
        this.userID = userID;
        this.createOrder = createOrder;
        this.total = total;
        this.payment = payment;
        this.orderID = orderID;
        this.status = status;
    }

    
    
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(String createOrder) {
        this.createOrder = createOrder;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    
    
}
