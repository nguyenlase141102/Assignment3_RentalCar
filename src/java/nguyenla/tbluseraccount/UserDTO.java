/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tbluseraccount;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class UserDTO implements Serializable{
    private String userID;
    private String userPassword;
    private String phone;
    private String name;
    private String address;
    private String createDate;
    private String status;
    private String code;
    private String email;
    private String role;
    
    public UserDTO(String userID, String email, String code) {
        this.name = userID;
        this.email = email;
        this.code = code;
    }

    public UserDTO(String status, String role) {
        this.status = status;
        this.role = role;
    }
    
    
    
    public UserDTO() {
    }

    public UserDTO(String userID, String userPassword, String phone, String name, String address, String createDate, String status) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
}
