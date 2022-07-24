/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.exception;

/**
 *
 * @author Richard Maru
 */
public class AuthenticationException extends Exception{
    String message;
    public AuthenticationException(String errorMessage){
        super(errorMessage);
    }
    
    public AuthenticationException(int errcode){
        super(errcode==0? "Incorrect password.": "User does not exist.");
        message = errcode==0? "Incorrect password.": "User does not exist.";
    }
    
   
}
