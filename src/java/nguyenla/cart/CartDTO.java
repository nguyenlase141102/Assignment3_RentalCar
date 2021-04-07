/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nguyenla.tblcar.CarDTO;

/**
 *
 * @author ANH NGUYEN
 */
public class CartDTO implements Serializable {
  
     List<CarDTO> listCar  = new ArrayList<>();
    private Map<String, CarDTO> cart;
    private String customerName;

    public CartDTO() {
    }

    public CartDTO(Map<String, CarDTO> cart, String customerName) {
        this.cart = cart;
        this.customerName = customerName;
    }

    public Map<String, CarDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CarDTO> cart) {
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void addCar(CarDTO car) {
//        if (this.cart == null) {
//            this.cart = new HashMap<String, CarDTO>();
//        }
//        if (this.cart.containsKey(car.getBiensoxe())) {
//            int newQuantity = this.cart.get(car.getBiensoxe()).getQuantity();
//            car.setQuantity(newQuantity + 1);
//        }
//        cart.put(car.getCarID(), car);
            
            if(this.cart == null){
                listCar = new ArrayList<>();
            }else{
               listCar.add(car);
            }
            

    }

    public void deleteCart(String id) {
        if (cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            cart.remove(id);
        }
    }

    public void updateCartCar(String id, CarDTO dto) {
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, dto);
            }
        }
    }

    public boolean checkDate(Map<String, CarDTO> cartCheck) {
        for (CarDTO c : cartCheck.values()) {
            String rentalDate = c.getRentalDate();
            String returnDate = c.getReturnDate();
            if (rentalDate == null && returnDate == null) {
                return false;
            }
        }
        return true;
    }
}
