/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.validation;

/**
 *
 * @author ANH NGUYEN
 */
public class CheckValid {
    
    public static boolean checkCharacters(String value){
        boolean result;
        result = !value.matches("^[a-zA-Z0-9 ]{0,300}$") ? false : true;
        return result;
    }
    public static boolean isNumber(String value){
        boolean result;
        result = !value.matches("^[0-9]{10}$") ? false : true;
        return result;
    }
}
