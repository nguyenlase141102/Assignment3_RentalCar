/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblcar;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class CarDTO implements Serializable {

    private String carID;
    private String carName;
    private String color;
    private String year;
    private String category;
    private Float price;
    private int quantity;
    private String image;
    private String rentalDate;
    private String returnDate;
    private int date;
    private int saveQuantity;
    private String biensoxe;
    public CarDTO() {
    }

    public CarDTO(String carID, int saveQuantity) {
        this.carID = carID;
        this.saveQuantity = saveQuantity;
    }

    public String getBiensoxe() {
        return biensoxe;
    }

    public void setBiensoxe(String biensoxe) {
        this.biensoxe = biensoxe;
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

    public int getSaveQuantity() {
        return saveQuantity;
    }

    public void setSaveQuantity(int saveQuantity) {
        this.saveQuantity = saveQuantity;
    }

    public CarDTO(String carID, String carName, String category, Float price, String image) {
        this.carID = carID;
        this.carName = carName;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public CarDTO(String carID, String carName, String category, Float price, int quantity, String image) {
        this.carID = carID;
        this.carName = carName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public CarDTO(String carID, String carName, String category, Float price, String image, int saveQuantity) {
        this.carID = carID;
        this.carName = carName;
        this.category = category;
        this.price = price;
        this.image = image;
        this.saveQuantity = saveQuantity;
    }

    public CarDTO(String carID, String carName, String category, Float price, int quantity, String image, int saveQuantity) {
        this.carID = carID;
        this.carName = carName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.saveQuantity = saveQuantity;
    }

    public CarDTO(String carID, String carName, String category, Float price, int quantity, String image, String rentalDate, String returnDate, int date) {
        this.carID = carID;
        this.carName = carName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.date = date;
    }

    public CarDTO(String carID, String carName, String color, String year, String category, Float price, String image) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
