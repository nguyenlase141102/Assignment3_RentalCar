/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.orderdetails;

/**
 *
 * @author ANH NGUYEN
 */
public class OrderLineDTO {
    private String carID;
    private String userID;
    private String carName;
    private String rentalDate;
    private String returnDate;
    private String status;
    private int quantity;
    private int orderID;
    
    
    public OrderLineDTO() {
    }

    public OrderLineDTO(String carName, int orderID) {
        this.carName = carName;
        this.orderID = orderID;
    }

    public OrderLineDTO(String rentalDate, String returnDate) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public OrderLineDTO(String carName, String rentalDate, String returnDate, String status, int quantity) {
        this.carName = carName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
        this.quantity = quantity;
    }

    public OrderLineDTO(String carID, String carName, String rentalDate, String returnDate, String status, int quantity) {
        this.carID = carID;
        this.carName = carName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
        this.quantity = quantity;
    }

    

    public OrderLineDTO(String carName, String rentalDate, String returnDate, int quantity, int orderID) {
        this.carName = carName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
    
    
    
}
