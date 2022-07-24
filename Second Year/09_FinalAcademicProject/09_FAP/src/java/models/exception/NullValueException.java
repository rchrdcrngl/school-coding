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
public class NullValueException extends Exception{
    public NullValueException(String errorMessage){
        super(errorMessage);
    }

}
